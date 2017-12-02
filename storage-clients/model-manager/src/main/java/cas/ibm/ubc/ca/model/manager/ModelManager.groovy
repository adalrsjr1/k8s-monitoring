package cas.ibm.ubc.ca.model.manager

import java.lang.reflect.Constructor
import java.util.concurrent.TimeUnit

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.common.base.Stopwatch

import cas.ibm.ubc.ca.interfaces.InspectionInterface
import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer
import cas.ibm.ubc.ca.model.manager.planner.AdaptationPlanner
import cas.ibm.ubc.ca.model.manager.planner.HeuristicAdaptationPlanner
import cas.ibm.ubc.ca.model.manager.planner.Z3AdaptationPlanner
import cas.ibm.ubc.ca.monitoring.MonitoringApplication
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement
import model.Cluster

class ModelManager implements ReificationInterface {
	private static final Logger LOG = LoggerFactory.getLogger(ModelManager.class)

	private final Long monitoringInterval
	private final String monitoringUrl
	private final String modelStorageUrl
	private final TimeUnit timeUnit
	private final String plannerName
	
	private final InspectionInterface monitoring
	private final ModelHandler modelHandler

	private final AffinitiesAnalyzer analyzer
	private final AdaptationPlanner planner

	private final ReificationInterface managedCluster

	private Boolean stopped = false
	private static int version = 1

	private static AdaptationPlanner instantiatePlanner(String classname, modelHandler) {
		String CLASSNAME_PREFIX = "cas.ibm.ubc.ca.model.manager.planner."
		Class<AdaptationPlanner> plannerClass = Class.forName(CLASSNAME_PREFIX + classname)
		Constructor constructor = plannerClass.getConstructor(ModelHandler.class)
		
		return constructor.newInstance(modelHandler)
	}
	
	public ModelManager(ModelManagerConfig config, ReificationInterface managedCluster) {
		this.monitoringInterval = Long.parseLong(config.get("modelmanager.monitoring.interval"))
		this.modelStorageUrl = config.get("modelmanager.model.storage")
		this.timeUnit = TimeUnit.valueOf(config.get("modelmanager.monitoring.timeunit").toUpperCase())
		this.plannerName = config.get("modelmanager.planner.class")
		
		monitoring = new MonitoringApplication()
		modelHandler = new ModelHandler(this.modelStorageUrl)

		this.managedCluster = managedCluster

		analyzer = new AffinitiesAnalyzer()
		planner = ModelManager.instantiatePlanner(this.plannerName, modelHandler)
//		planner = new HeuristicAdaptationPlanner(modelHandler)
//		planner = new Z3AdaptationPlanner(modelHandler)
	}

	public Cluster createModel() {
		
		Cluster cluster = modelHandler.updateModel(
				"${version++}",
				monitoring.environment(),
				monitoring.hosts(),
				monitoring.applications(),
				monitoring.services(),
				monitoring.messages(TimeInterval.last(monitoringInterval, timeUnit)),
				[Measurement.CPU, Measurement.MEMORY, Measurement.CPU, Measurement.MEMORY],
				[monitoring.metricsHost(Measurement.CPU, TimeInterval.last(monitoringInterval, timeUnit)),
					monitoring.metricsHost(Measurement.MEMORY, TimeInterval.last(monitoringInterval, timeUnit)),
					monitoring.metricsService(Measurement.CPU, TimeInterval.last(monitoringInterval, timeUnit)),
					monitoring.metricsService(Measurement.MEMORY, TimeInterval.last(monitoringInterval, timeUnit)),
				])

		return cluster
	}

	public List updateModel() {
		Cluster cluster
		List affinities = []
		Stopwatch watcher = Stopwatch.createStarted()
		try {
			modelHandler.saveModel()
			cluster = createModel()
			LOG.info "Calculating affinities..."
			affinities = analyzer.calculate(cluster, modelHandler.resource)
			LOG.info "Affinities calculated [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms."
		}
		catch(Exception e) {
			LOG.error "It cannot possible to create/update the model"
			e.printStackTrace()
		}
		finally {
			return affinities
		}
	}

	public void stop() {
		stop = true
	}

	public void start() {

		Stopwatch watcher = Stopwatch.createStarted()

		LOG.info "Updating model..."
		List affinities = updateModel()
		LOG.info "Model updated [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms."

		watcher.reset()
		
		def cluster = modelHandler.getOrCreateCluster(monitoring.environment())
		modelHandler.saveModel()

		watcher.start()

		LOG.info "Applying adaptation on Model..."
		List adaptationScript = planner.execute(affinities)
		move(adaptationScript)
		LOG.info "Adaptation Complete [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms."
		modelHandler.saveModel()
	}

	@Override
	public boolean move(List<Moviment> adaptationScript) {
		adaptationScript.each {
			move(it)
		}
	}

	@Override
	public boolean move(Moviment moviment) {
		if(moviment == Moviment.nonMove()) {
			LOG.trace "<<<NON_MOVE>>>"
			return true
		}
		LOG.trace ("moving on model [{}] ...", moviment.toString())
		try {
			Stopwatch watcher = Stopwatch.createStarted()
			LOG.info "Reconfiguring application..."
			managedCluster.move(moviment)
			LOG.info "Application reconfigured [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms"
			modelHandler.saveModel()
		}
		catch(Exception e) {
			LOG.warn "New placement cannot be applied into cluster..."
//			modelHandler.undoMoveOnModel(moviment)
			LOG.error(e.getCause())
			e.printStackTrace()
		}
	}

}
