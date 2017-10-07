package cas.ibm.ubc.ca.model.adapters

import java.util.List
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.ExecutorService
import java.util.spi.CalendarNameProvider

import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.model.reification.MoveCommand
import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.ServiceInstance
import model.impl.ClusterImpl

abstract class ClusterAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(ClusterAdapter.class)
	
	private static ExecutorService threadPool = Executors.newCachedThreadPool()
	
	public static boolean move(Cluster cluster, String application, String serviceId, 
		String sourceHost, String destinationHost) {
		
		Future future = threadPool.submit({
			new MoveCommand(application, serviceId, sourceHost, destinationHost).execute()
		})
		
		threadPool.execute({
			updateModel(cluster, application, serviceId, sourceHost, destinationHost)
		})
		
		if(future.get() == false) {
			revertModel(cluster, application, serviceId, sourceHost, destinationHost)
		}
	}
	
	private static void updateModel(Cluster cluster, String application, String serviceId,
		String sourceHost, String destinationHost) {
		
		LOG.info("updating model...")
		LOG.trace(LOG.trace("[$sourceHost] $application.$serviceId -> $destinationHost"))
		
		Application eApplication = cluster.getApplications().find {Application app ->
			app.getName() == application
		}
		
		ServiceInstance eService = eApplication.getServices().find {ServiceInstance service ->
			service.getId() == serviceId
		}
		
		
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
	
	private static boolean revertModel(Cluster cluster, String application, String serviceId, 
		String sourceHost, String destinationHost) {
		updateModel(cluster, application, destinationHost, sourceHost)
	}
	
}
