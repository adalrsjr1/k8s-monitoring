package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import java.util.LinkedHashMap.LinkedKeySet
import model.Affinity
import model.Application
import model.Cluster
import model.Message
import model.Service
import model.ServiceInstance

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
				
		for(j in (1..(c*2))) {
			Message m = factory.createMessage()
			
			String src = "svc${(j%c)+1}"
			String dst = "svc${((j+1)%c)+1}"
			
			m.setSource(svcs[src])
			m.setDestination(svcs[dst])
			
			m.setMessageSize(1L)

			svcs[src].messages << m
		}
		
		for(j in (1..(c*2))) {
			Message m = factory.createMessage()
			
			String dst = "svc${(j%c)+1}"
			String src = "svc${((j+1)%c)+1}"
			
			m.setSource(svcs[src])
			m.setDestination(svcs[dst])
			
			m.setMessageSize(1L)

			svcs[src].messages << m
		}
				
	}
	
	public void testCache() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer(null)
		assert analyzer != null
		
		
		Map cache = analyzer.messagesCache(cluster)

		assert cache != null		
		assert !cache.empty
		assert cache.size() == 5
		assert (((cache.keySet()) as LinkedHashSet).class ) ==
		 ((["svc1:svc2", "svc2:svc3", "svc3:sv4", "svc4:svc5", "svc5:svc1"] as Set).class)

		cache.each { k, v ->
			assert v.contains(null) == false
			assert v[0] == 4
			assert v[1] == 4
			assert v[2].getName() == k.split(":")[0]
			assert v[3].getName()== k.split(":")[1]
		} 
		
	}
	
	public void testGetAppApplications() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer(null)
		
		Map applications = analyzer.getAllApplications(cluster)
		
		assert applications == ["app1":0.5f]
	}
	
	public void testNormalize() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer(null)
		
		assert analyzer.normalize(1,10) == 0.1f
		assert analyzer.normalize(1,100) == 0.01f
		assert analyzer.normalize(5,100) == 0.05f
		assert analyzer.normalize(5, 20) == 0.25f
	}
	
	public void testAffinity() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer(null)
		
		assert analyzer.affinity(1,10,1,10, 0.5) == 0.1f
		
	}
	
	public void testCalculate() {
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer(null)
		
		analyzer.calculate(cluster)
		
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		while(iterator.hasNext()) {
			def obj = iterator.next()
			if(obj instanceof Affinity) {
				assert obj.degree == 0.2f
			}
		}
	}

}
