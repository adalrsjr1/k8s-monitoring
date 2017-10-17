package cas.ibm.ubc.ca.model.manager.analyzer

import model.Cluster
import model.Message
import model.Service
import model.ServiceInstance
import model.impl.ModelPackageImpl

import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.Notifier
import org.eclipse.emf.common.notify.impl.AdapterImpl
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil

import cas.ibm.ubc.ca.model.manager.ModelHandler

class AffinitiesAnalyzer {

	ModelHandler modelHandler
	
	AffinitiesAnalyzer(ModelHandler modelHandler) {
		this.modelHandler = modelHandler
	}

	private Double normalize(Long total, Long number) {
		return (Double) total / number
	}
	
	private List getAllServices(Cluster cluster) {
		List<ServiceInstance> services = []
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		while(iterator.hasNext()) {
			def object = iterator.next()
			if(object instanceof ServiceInstance) {
				services << object
			}
		}
		
		return services
	}
	
	private List getAllServicePerApplication(Cluster cluster) {
		Map map = [:]
		
		return cluster.applications.inject(map) { result, app ->
			result[app.name] = app.services.values()
			result
		}
	}
	
	private Map getAllApplications(Cluster cluster) {
		Map<String, Float> map = [:]
		
		return cluster.applications.inject(map) { result, app ->
			result[app.name] = app.weight
			result
		}
	}
	
	public Map messagesCache(Cluster cluster) {
		Map map = [:] 
		
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)

		while(iterator.hasNext()) {
			def obj = iterator.next()
			if(obj instanceof Message) {
				String key = "${obj.source.name}:${obj.destination.name}"
				if(!map.containsKey(key)) {
					map[key] = new ArrayList([0,0])
				}
				
				// result[source:destination] = [#messages, #data]
						
				map[key][0] += 1
				map[key][1] += obj.messageSize
			}
			
		}		
		return map
	}
	
	void calculate(Cluster cluster) {
		Map applications = getAllApplications(cluster)
		Map services = getAllServices(cluster)
		
		services.each {String app, List svcs ->
			
		}
	}	
	
}
