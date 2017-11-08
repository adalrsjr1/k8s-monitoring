package cas.ibm.ubc.ca.model.manager.analyzer

import org.eclipse.emf.common.util.ECollections
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import com.google.common.base.Stopwatch
import java.util.concurrent.TimeUnit
import model.Affinity
import model.Application
import model.Cluster
import model.Message
import model.Service
import model.ServiceInstance

class AffinitiesAnalyzer {
	
	private static Logger LOG = LoggerFactory.getLogger(AffinitiesAnalyzer.class)
	public AffinitiesAnalyzer() {}
	
	private Map messagesCache(Cluster cluster) {
		Map map = [:] 
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		int count = 0
		
		while(iterator.hasNext()) {
			def obj = iterator.next()
			if(obj instanceof Message) {
				count++
				
				Service src = obj.getSource()
				Service dst = obj.getDestination()
				
				String key = "${src?.name}:${dst?.name}"
				String keyReverse = "${dst?.name}:${src?.name}"
				
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
				map[key][1] += obj.messageSize != null ? obj.messageSize : 0
				map[key][2] = src
				map[key][3] = dst
			}
			
		}	
		return map
	}
	
	private Float normalize(Long number, Long total) {
		if(number == 0) return 0
		return (Double) number / total
	}
	
	private Float affinity(Long messages, Long totalMessages, 
		Long data, Long totalData, Float weight) {
		
		return normalize(messages, totalMessages) * weight +
		normalize(data, totalData) * (1.0f - weight)
	}

	public Queue calculate(Cluster cluster, Resource resource) {
		Stopwatch watch = Stopwatch.createStarted()
		Map cache = messagesCache(cluster)
		
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		
		Set services = [] as Set
		def comparator = new Comparator() {
			@Override
			public int compare(def o1, def o2) {
				return Float.compare( o2.getDegree(), o1.getDegree() )
			}
		}
		
		Queue affinities = new PriorityQueue(comparator)
		
		cache.each { k, v ->
			ServiceInstance src = v[2]
			ServiceInstance dst = v[3]
			
			Application application = cluster.applications[src.getApplication()]
			int nMsg = v[0]
			int msgSize = v[1]
			def value = affinity(nMsg, application.totalMessages,
				msgSize, application.totalData, application.weight)
			

			Affinity aff = factory.createAffinity(resource)
			
			aff.setDegree(value)
			aff.setWith(dst)
						
			src.hasAffinities << aff
			affinities << aff
			services << src
		}
		
//		services.each { Service svc ->
//			ECollections.sort(svc.getHasAffinities(), comparator)
//		}
		
		LOG.info("Affinities calculated in {} ms", watch.elapsed(TimeUnit.MILLISECONDS))
		watch.stop()
		
		return affinities
	}	
	
}
