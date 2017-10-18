package cas.ibm.ubc.ca.model.manager

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.locks.ReentrantReadWriteLock

import cas.ibm.ubc.ca.model.adapters.ClusterAdapter
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer

import com.google.common.util.concurrent.ThreadFactoryBuilder
import okhttp3.OkHttpClient

import model.Cluster
import model.Environment

class ModelManager {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock()
	
	private final String monitoringURL = "http://localhost:8888"
	private final String modelStoragePath = "src/main/resources/model/"
	
	private final MonitoringClient monitoringClient
	private final ModelHandler modelHandler
	
	private final AffinitiesAnalyzer analyzer
	
	private Long modelUpdateInterval
	private Boolean stopped = false
	
	private ExecutorService threads = Executors.newCachedThreadPool(
		new ThreadFactoryBuilder().setNameFormat("model-manager-%d"))
	
	public ModelManager(Long modelUpdateInterval) {
		this.modelUpdateInterval = modelUpdateInterval 
		
		monitoringClient = new MonitoringClient(new OkHttpClient(), monitoringURL)
		modelHandler = new ModelHandler(modelStoragePath)	
		analyzer = new AffinitiesAnalyzer()	 
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
		finally {
			lock.writeLock().unlock()
			return cluster
		}
	}
	
	public void stop() {
		stop = true
	}
	
	public void start() {
		while(!stopped) {
			Thread.sleep(modelUpdateInterval)
			updateModel()
		}
	}
	
	void move(String application, String serviceId, String sourceHost, String destinationHost) {
		try {
			lock.writeLock()
			ClusterAdapter.move(modelHandler.getCluster(), 
				application, 
				serviceId, 
				sourceHost, 
				destinationHost)
			modelHandler.saveModel()
		}
		finally {
			lock.writeLock().unlock()
		}
	}
}
