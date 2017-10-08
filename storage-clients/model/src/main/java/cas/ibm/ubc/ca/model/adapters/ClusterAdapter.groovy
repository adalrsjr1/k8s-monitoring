package cas.ibm.ubc.ca.model.adapters

import java.util.List
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.spi.CalendarNameProvider

import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.model.reification.MoveCommand
import groovy.transform.Synchronized
import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.ServiceInstance
import model.impl.ClusterImpl

abstract class ClusterAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(ClusterAdapter.class)
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(2)
	
	/*
	 * Optimist update, this function try to adapt the application and the
	 * model in parallel. If the adaptation cannot be applied on application
	 * the model is rolled back, imposing an overhead.
	 */
	@Synchronized
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
		
		Application eApplication = cluster.getApplications().find {Application app ->
			app.getName() == application
		}
		
		assert eApplication != null
		
		ServiceInstance eService = eApplication.getServices().find {ServiceInstance service ->
			service.getId() == serviceId
		}
		
		assert eService != null
		
		Map<String, Host> hosts = cluster.getHosts().inject([:]) {result, Host host ->
			if(host.getName() == sourceHost || host.getName() == destinationHost) {
				result[host.getName()] = host
			}
			result
		}
		
		hosts[sourceHost].getServices().remove(eService)
		hosts[destinationHost].getServices().add(eService)
		LOG.info("model updated.")
	}
	
}
