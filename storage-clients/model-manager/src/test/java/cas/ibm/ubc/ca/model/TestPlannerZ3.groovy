package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.model.manager.planner.Z3AdaptationPlanner
import model.Affinity
import model.Host
import model.ServiceInstance
import org.junit.Test

class TestPlannerZ3 extends GroovyTestCase {
	private static final ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	private static final Random r = new Random(31)
	private static final Long z3WaitTime = 60*1000*10 
	private static int N = 10
	
	private static final List randomList = fillList(1000)
	
	private static fillList(int n) {
		List list = []
		
		for(int i in 0..<n) {
			int value = r.nextInt(n)
			while(list.contains(value)) {
				value = r.nextInt(n)
			}
			list.add(0, value)
		}
		return list
	}
	
	private Affinity createAffinity() {
		ServiceInstance svc1, svc2
		
		int n = r.nextInt(N)
		
		svc1 = createService(n)
		svc2 = createService(randomList[n])
		
		float w = r.nextFloat()
		
		Affinity a = factory.createAffinity()
		
		svc1.hasAffinities.add(a)
		a.setDegree(w)
		a.setWith(svc2)

		return a

	}

	private ServiceInstance createService(int n) {
		ServiceInstance s = factory.createServiceInstance()
		s.application = "APP"
		s.name = "svc_$n"
		s.id = s.name
		s.metrics.putAll(createMetric(s))
		s.setHost(createHost(n))
		
		return s
	}
	
	private Host createHost(int n) {
		Host h = factory.createHost()
		h.name = "host_$n"
		h.metrics.putAll(["cpu":0.toDouble(), "memory":0.toDouble()])
		h.resourceLimit.putAll(createMetric(h))
		
		return h
	}
	
	private Map createMetric(def type) {
		
		if(type instanceof Host) {
			return createMetricHost()
		}
		if(type instanceof ServiceInstance) {
			return createMetricService()
		}
	}
	
	private Map createMetricHost() {
		return ["cpu":4000.toDouble(), "memory":4.toDouble()]
	}
	
	private Map createMetricService() {
		return ["cpu": r.nextInt(100).toDouble(), "memory": r.nextFloat().toDouble()]
	}
	
	
	private static int calculateHostsDiff(List moves) {
		Map map = moves.inject([:]) { Map result, Moviment m ->
			result[m.service] = m.hostDestination
			result
		}
		int final_number_hosts = map.values().toUnique().size()
		return N - final_number_hosts
	}
	
	
	private static final Z3AdaptationPlanner planner = new Z3AdaptationPlanner(null,z3WaitTime)
	
	void testInstance_10() {
		List affinities = []
		N = 10
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
	
	void testInstance_11() {
		List affinities = []
		N = 11
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
	
	void testInstance_12() {
		List affinities = []
		N = 12
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
	
	void testInstance_13() {
		List affinities = []
		N = 13
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
	
	void testInstance_14() {
		List affinities = []
		N = 14
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
	
	void testInstance_15() {
		List affinities = []
		N = 15
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
	
	void testInstance_20() {
		List affinities = []
		N = 20
		for (i in 0..<N) {
			affinities << createAffinity()
		}
		
		List result = []
			result = planner.execute(affinities)
		println "diff: " + calculateHostsDiff(result)
	}
}
