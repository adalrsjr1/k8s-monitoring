package cas.ibm.ubc.ca.model.manager

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
import cas.ibm.ubc.ca.monitoring.MonitoringApplication
import model.Cluster

class ModelManager implements ReificationInterface {
	private static final Logger LOG = LoggerFactory.getLogger(ModelManager.class)

	private final Long monitoringInterval
	private final String monitoringUrl
	private final String modelStorageUrl
	private final TimeUnit timeUnit

	private final InspectionInterface monitoring
	private final ModelHandler modelHandler

	private final AffinitiesAnalyzer analyzer
	private final AdaptationPlanner planner

	private final ReificationInterface managedCluster

	private Boolean stopped = false
	private static int version = 1

	public ModelManager(ModelManagerConfig config, ReificationInterface managedCluster) {
		this.monitoringInterval = Long.parseLong(config.get("modelmanager.monitoring.interval"))
		this.modelStorageUrl = config.get("modelmanager.model.storage")
		this.timeUnit = TimeUnit.valueOf(config.get("modelmanager.monitoring.timeunit").toUpperCase())

		monitoring = new MonitoringApplication()
		modelHandler = new ModelHandler(this.modelStorageUrl)

		this.managedCluster = managedCluster

		analyzer = new AffinitiesAnalyzer()
		planner = new AdaptationPlanner(modelHandler)
	}

	public Cluster createModel() {
		Cluster cluster = modelHandler.updateModel(
				"${version++}",
				monitoring.environment(),
				monitoring.hosts(),
				monitoring.applications(),
				monitoring.services(),
				monitoring.messages(TimeInterval.last(monitoringInterval, timeUnit)),
				["cpu/usage_rate", "memory/node_utilization", "cpu/usage_rate", "memory/usage"],
				[monitoring.metricsHost("cpu/node_utilization", TimeInterval.last(monitoringInterval, timeUnit)),
					monitoring.metricsHost("memory/node_utilization", TimeInterval.last(monitoringInterval, timeUnit)),
					monitoring.metricsService("cpu/usage", TimeInterval.last(monitoringInterval, timeUnit)),
					monitoring.metricsService("memory/usage", TimeInterval.last(monitoringInterval, timeUnit)),
				])

		return cluster
	}

	public List updateModel() {
		Cluster cluster
		List affinities
		try {
			modelHandler.saveModel()
			cluster = createModel()
			affinities = analyzer.calculate(cluster, modelHandler.resource)
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
		watcher.start()

		LOG.info "Calculating affinities..."
		def cluster = modelHandler.getOrCreateCluster(monitoring.environment())
		LOG.info "Affinities calcuated [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms."
		modelHandler.saveModel()

		watcher.reset()
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
