package cas.ibm.ubc.ca.model.adapters

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.model.reification.MoveCommand
import model.Application
import model.Cluster
import model.Host
import model.ServiceInstance

abstract class ClusterAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(ClusterAdapter.class)
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2)
	
	/*
	 * Optimist update, this function try to adapt the application and the
	 * model in parallel. If the adaptation cannot be applied on application
	 * the model is rolled back, imposing an overhead.
	 */
	public static boolean move(Cluster cluster, String application, String serviceId, 
		String sourceHost, String destinationHost) {
		
		Future future = threadPool.submit({
			new MoveCommand(application, serviceId, sourceHost, destinationHost).execute()
		} as Callable<Boolean>)
		
		threadPool.execute({
			updateModel(cluster, application, serviceId, sourceHost, destinationHost)
		})
		
		// revert the model if the adaptation cannot be applied on application
		LOG.debug("waiting for apply changes on application")
		// blocking
		if(future.get() == false) {
			LOG.info("reverting the model to prior version")
			updateModel(cluster, application, serviceId, destinationHost, sourceHost)
			return false
		}
		return true
	}
	
	private static void updateModel(Cluster cluster, String application, String serviceId,
		String sourceHost, String destinationHost) {
		
		LOG.info("updating model...")
		LOG.trace("[$sourceHost] $application.$serviceId -> $destinationHost")
		
		Application eApplication = cluster.applications[application]
		
		assert eApplication != null
		
		ServiceInstance eService = eApplication.services[(serviceId)]
		
		assert eService != null
		
		Host eSourceHost = cluster.hosts[(sourceHost)]
		Host eDestinationHost = cluster.hosts[(destinationHost)]
		
		eSourceHost.services.remove(serviceId)
		eDestinationHost.services[(serviceId)] = eService
		LOG.info("model updated.")
	}
	
}
