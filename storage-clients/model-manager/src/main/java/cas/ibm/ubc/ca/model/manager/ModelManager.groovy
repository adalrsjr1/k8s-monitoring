package cas.ibm.ubc.ca.model.manager

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantReadWriteLock

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.common.base.Stopwatch
import com.google.common.util.concurrent.ThreadFactoryBuilder

import cas.ibm.ubc.ca.model.adapters.ClusterAdapter
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer
import cas.ibm.ubc.ca.model.manager.planner.AdaptationPlanner
import groovy.transform.Synchronized
import model.Cluster
import okhttp3.OkHttpClient

class ModelManager {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock()
	private static final Logger LOG = LoggerFactory.getLogger(ModelManager.class)
	
	private final ExecutorService threads = Executors.newCachedThreadPool(
		new ThreadFactoryBuilder().setNameFormat("model-manager-%d").build())
	
	private final Long monitoringInterval
	private final String monitoringUrl
	private final String modelStorageUrl
	
	private final InspectionInterface monitoringClient
	private final ModelHandler modelHandler
	
	private final AffinitiesAnalyzer analyzer
	private final AdaptationPlanner planner
	
	private Boolean stopped = false
	
	public ModelManager(ModelManagerConfig config) {
		this.monitoringInterval = config.MONITORING_INTERVAL 
		this.monitoringUrl = config.MONITORING_URL
		this.modelStorageUrl = config.MODEL_STORAGE_URL
		
		monitoringClient = new MonitoringClient(new OkHttpClient(), 
			this.monitoringUrl)
		modelHandler = new ModelHandler(this.monitoringUrl)
			
		analyzer = new AffinitiesAnalyzer()
		planner = new AdaptationPlanner(modelHandler)	 
	}
	
	public Cluster createModel() {
		return modelHandler.updateModel(
			monitoringClient.cluster(), 
			monitoringClient.hosts(), 
			monitoringClient.applications(), 
			monitoringClient.services(), 
			monitoringClient.metrics(), 
			monitoringClient.messages())
	}
	
	public Cluster updateModel() {
		Cluster cluster
		try {
			lock.writeLock()
			modelHandler.saveModel()
			cluster = createModel()
			analyzer.calculate(cluster)
		}
		catch(Exception e) {
			LOG.error "It cannot possible to create/update the model"
			throw new RuntimeException(e)
		}
		finally {
			lock.writeLock().unlock()
			return cluster
		}
	}
	
	public void stop() {
		stop = true
		threads.shutdown()
	}
	
	public void start() {
		
		threads.execute {
			Stopwatch watcher = Stopwatch.createStarted()
			while(!stopped) {
				watcher.reset()
				watcher.start()
				
				LOG.info "Updating model..."
				updateModel()
				LOG.info "Model updated [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms."
				
				watcher.reset()
				
				LOG.info "Calculating affinities..."
				analyzer.calculate(modelHandler.getCluster())
				LOG.info "Affinities calcuated [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms."
				
				Thread.sleep(this.monitoringInterval)
			}
		}
	}
	
	void move(String application, String serviceId, String sourceHost, String destinationHost) {
		try {
			lock.writeLock()
			Stopwatch watcher = Stopwatch.createStarted()
			LOG.info "Reconfiguring application..."
			ClusterAdapter.move(modelHandler.getCluster(), 
				application, 
				serviceId, 
				sourceHost, 
				destinationHost)
			LOG.info "Application reconfigured [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms"
			modelHandler.saveModel()
		}
		finally {
			lock.writeLock().unlock()
		}
	}
}
