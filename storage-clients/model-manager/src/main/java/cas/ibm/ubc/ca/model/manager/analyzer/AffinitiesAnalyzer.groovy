package cas.ibm.ubc.ca.model.manager.analyzer

import model.Affinity
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

import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler

class AffinitiesAnalyzer {

	ModelHandler modelHandler
	
	AffinitiesAnalyzer(ModelHandler modelHandler) {
		this.modelHandler = modelHandler
	}
	
	private Map getAllApplications(Cluster cluster) {
		Map<String, Float> map = [:]
		
		return cluster.applications.values().inject(map) { result, app ->
			result[app.name] = app.getWeight()
			result
		}
	}
	
	public Map messagesCache(Cluster cluster) {
		Map map = [:] 
		
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)

		while(iterator.hasNext()) {
			def obj = iterator.next()
			if(obj instanceof Message) {
				Service src = obj.getSource()
				Service dst = obj.getDestination()
				
				String key = "${src.name}:${dst.name}"
				String keyReverse = "${dst.name}:${src.name}"
				
				if(!map.containsKey(key) && !map.containsKey(keyReverse)) {
					map[key] = [0,0, null, null] as ArrayList
				}
				else {
					if(map.containsKey(keyReverse)) {
						key = keyReverse
						def aux = src
						src = dst
						dst = aux
					}
				}
				
				// result[source:destination] = [#messages, #data, src, dst]
				// graph unidirected		
				map[key][0] += 1
				map[key][1] += obj.messageSize
				map[key][2] = src
				map[key][3] = dst
			}
			
		}		
		return map
	}
	
	private Float normalize(Long number, Long total) {
		return (Double) number / total
	}
	
	private Float affinity(Long messages, Long totalMessages, 
		Long data, Long totalData, Float weight) {
		
		return normalize(messages, totalMessages) * weight +
		normalize(data, totalData) * (1.0f - weight)
		
	}
	
	public void calculate(Cluster cluster) {
		Map cache = messagesCache(cluster)
		
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		
		cache.each { k, v ->
			Affinity aff = factory.createAffinity()
			def value = affinity()
			aff.setDegree(value)
		}
	}	
	
}
