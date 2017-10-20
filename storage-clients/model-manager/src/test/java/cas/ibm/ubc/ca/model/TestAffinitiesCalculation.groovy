package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import java.util.LinkedHashMap.LinkedKeySet
import model.Affinity
import model.Application
import model.Cluster
import model.Message
import model.Service
import model.ServiceInstance
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.Test

import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer

class TestAffinitiesCalculation extends GroovyTestCase{

	Cluster cluster
	
	void setUp() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE
		cluster = factory.createCluster()

		Application app = factory.createApplication()
		app.name = "app1"
		app.setWeight(0.5f)
		cluster.applications["app1"] = app

		int c = 5
		
		for(i in (1..c)) {
			ServiceInstance s = factory.createServiceInstance()
			String svc = "svc${i}"
			s.name = svc
			cluster.applications["app1"].services[svc] = s
			assert s.getApplication() == "app1"
		}
		
		Map svcs = cluster.applications["app1"].services
		
		Long msgSize = 1L

		Random r = new Random(13L*7L*31L)
		
		createMessage(factory, svcs, "svc1", "svc2", 3L)
		createMessage(factory, svcs, "svc1", "svc3", 5L)
		createMessage(factory, svcs, "svc1", "svc4", 7L)
		createMessage(factory, svcs, "svc1", "svc5", 7L)
		createMessage(factory, svcs, "svc1", "svc2", 11L)
		createMessage(factory, svcs, "svc1", "svc3", 3L)
		createMessage(factory, svcs, "svc1", "svc4", 5L)
		createMessage(factory, svcs, "svc1", "svc5", 3L)
		createMessage(factory, svcs, "svc2", "svc3", 2L)
		createMessage(factory, svcs, "svc3", "svc4", 1L)
						
//		for(j in (1..(c*2))) {
//			
//			Message m = factory.createMessage()
//			
//			String src = "svc${(j%c)+1}"
//			String dst = "svc${((j+1)%c)+1}"
//			
//			m.setSource(svcs[src])
//			m.setDestination(svcs[dst])
//			
//			//m.setMessageSize(((j%c)+1) + (((j+1)%c)+1))
//			m.setMessageSize(r.nextLong() % 10)
//			svcs[src].messages << m
//		}
//		
//		for(j in (1..(c*2))) {
//			Message m = factory.createMessage()
//			
//			String dst = "svc${(j%c)+1}"
//			String src = "svc${((j+1)%c)+1}"
//			
//			m.setSource(svcs[src])
//			m.setDestination(svcs[dst])
//			
//			//m.setMessageSize(((j%c)+1) + (((j+1)%c)+1))
//			m.setMessageSize(r.nextLong() % 10)
//			svcs[src].messages << m
//		}
		
				
	}
	
	private Message createMessage(def factory, def svcs, String src, String dst, Long size) {
		Message m = factory.createMessage()
		
		m.setSource(svcs[src])
		m.setDestination(svcs[dst])
		
		m.setMessageSize(size)
		
		svcs[src].messages << m
		
		return m
	}
	
	public void testCache() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer()
		assert analyzer != null
		
		
		Map cache = analyzer.messagesCache(cluster)

		assert cache != null		
		assert !cache.empty
		assert cache.size() == 6
		assert (((cache.keySet()) as LinkedHashSet).class ) ==
		 ((["svc1:svc2", "svc1:svc3", "svc1:sv4", "svc1:svc5", "svc2:svc3", "svc3:svc4"] as Set).class)

		def aux = [0.18510638, 0.20638299, 0.22765958, 0.24893618, 0.0712766, 0.060638297]
		
		Map svcs = cluster.applications["app1"].services
		
		assert cache["svc1:svc2"] == [2,14,svcs["svc1"],svcs["svc2"]]
		assert cache["svc1:svc3"] == [2,8,svcs["svc1"],svcs["svc3"]]
		assert cache["svc1:svc4"] == [2,12,svcs["svc1"],svcs["svc4"]]
		assert cache["svc1:svc5"] == [2,10,svcs["svc1"],svcs["svc5"]]
		assert cache["svc2:svc3"] == [1,2,svcs["svc2"],svcs["svc3"]]
		assert cache["svc3:svc4"] == [1,1,svcs["svc3"],svcs["svc4"]]
		
		
//		cache.each { k, v ->
//			assert v.contains(null) == false
//			assert v[0] == 4
//			def idx = k.split(/:|svc/)
////			assert v[1] == 4*(Integer.parseInt(idx[1]) + Integer.parseInt(idx[3]))
//			assert v[2].getName() == k.split(":")[0]
//			assert v[3].getName()== k.split(":")[1]
//		} 
		
	}
	
	public void testNormalize() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer()
		
		assert analyzer.normalize(1,10) == 0.1f
		assert analyzer.normalize(1,100) == 0.01f
		assert analyzer.normalize(5,100) == 0.05f
		assert analyzer.normalize(5, 20) == 0.25f
	}
	
	public void testAffinity() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer()
		
		assert analyzer.affinity(1,10,1,10, 0.5) == 0.1f
		
	}
	
	public void testCalculate() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer()
		
		analyzer.calculate(cluster)
		
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		def aux = ["svc1:svc2", 
		 "svc1:svc4",
		 "svc1:svc5",
		 "svc1:svc3",
		 "svc2:svc3",
		 "svc3:svc4"]
		
		def sortedAffinities = []
		while(iterator.hasNext()) {
			def obj = iterator.next()
			if(obj instanceof Affinity) {
				sortedAffinities << "${obj.eContainer().name}:${obj.with.name}"
			}
		}

		assert aux == sortedAffinities
	}

}
