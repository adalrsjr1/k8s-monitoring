package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.model.manager.planner.HeuristicAdaptationPlanner
import model.Affinity
import model.Host
import model.Service
import model.ServiceInstance

class TestAffinitiesCalculation2 extends GroovyTestCase {
	static final ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE
	
	private static Affinity createAffinity(Service src, Service dst, Float degree) {
		Affinity aff = factory.createAffinity()
		src.hasAffinities << aff
		aff.setDegree(degree)
		aff.setWith(dst)
		return aff
	}
	
	private static Host createHost(String name, Map metrics, ServiceInstance svc) {
		Host host = factory.createHost()
		host.setName(name)
		host.metrics.putAll(metrics)
		host.resourceLimit.putAll(metrics)
		host.services[svc.name] = svc
		host.resourceReserved.putAll(svc.metrics)
		
		return host
	}
	
	private static ServiceInstance createService(String name, Map metrics) {
		ServiceInstance svc = factory.createServiceInstance()
		svc.setName(name)
		svc.setId(name)
		svc.setApplication("app")
		svc.metrics.putAll(metrics)
		return svc
	}
	
	private static Map createMetrics(double cpu, double memory) {
		return ["cpu":cpu, "memory":memory]
	}
	
	private static Queue createQueue() {
		def comparator = new Comparator() {
			@Override
			public int compare(def o1, def o2) {
				return Float.compare( o2.getDegree(), o1.getDegree() )
			}
		}
		return new PriorityQueue(comparator)
	}
	
	void testSumMetrics() {
		ModelHandler handler = new ModelHandler("")
		HeuristicAdaptationPlanner planner = new HeuristicAdaptationPlanner(handler)
		
		assert planner.sumMetrics(["a":1.0], ["a":1.0]) == ["a":2.0]
	}
	
	void testCase1() {
		Queue queue = createQueue()
		
		Host hostA,hostB,hostC,hostD
		ServiceInstance a, b, c, d
		
		a = createService( "a", createMetrics(740, 640) )
		b = createService( "b", createMetrics(710, 600) )
		c = createService( "c", createMetrics(920, 880) )
		d = createService( "d", createMetrics(270, 140) )
		
		hostA = createHost("hostA", createMetrics(2000, 4000), a )
		hostB = createHost("hostB", createMetrics(2000, 4000), b )
		hostC = createHost("hostC", createMetrics(2000, 4000), c )
		hostD = createHost("hostD", createMetrics(2000, 4000), d )

		queue << createAffinity(a, b, 0.80f)
		queue << createAffinity(b, c, 0.72f)
		queue << createAffinity(a, c, 0.68f)
		queue << createAffinity(c, d, 0.53f)
				
		ModelHandler handler = new ModelHandler("")
		HeuristicAdaptationPlanner planner = new HeuristicAdaptationPlanner(handler)
		
		println planner.execute(queue)
	}
	
	void testCase2() {
		Queue queue = createQueue()
		
		Host hostA,hostB,hostC,hostD
		ServiceInstance a, b, c, d
		
		a = createService( "a", createMetrics(650, 520) )
		b = createService( "b", createMetrics(400, 250) )
		c = createService( "c", createMetrics(370, 230) )
		d = createService( "d", createMetrics(130, 40) )
		
		hostA = createHost("hostA", createMetrics(2000, 4000), a )
		hostB = createHost("hostB", createMetrics(2000, 4000), b )
		hostC = createHost("hostC", createMetrics(2000, 4000), c )
		hostD = createHost("hostD", createMetrics(2000, 4000), d )

		queue << createAffinity(a, b, 0.80f)
		queue << createAffinity(a, c, 0.80f)
		queue << createAffinity(c, d, 0.25f)
				
		ModelHandler handler = new ModelHandler("")
		HeuristicAdaptationPlanner planner = new HeuristicAdaptationPlanner(handler)
		
		println planner.execute(queue)
	}
	
	void testCase3() {
		Queue queue = createQueue()
		
		Host hostA,hostB,hostC,hostD
		ServiceInstance a, b, c, d
		
		a = createService( "a", createMetrics(800, 720) )
		b = createService( "b", createMetrics(400, 250) )
		c = createService( "c", createMetrics(530, 230) )
		d = createService( "d", createMetrics(130, 40) )
		
		hostA = createHost("hostA", createMetrics(2000, 4000), a )
		hostB = createHost("hostB", createMetrics(2000, 4000), b )
		hostC = createHost("hostC", createMetrics(2000, 4000), c )
		hostD = createHost("hostD", createMetrics(2000, 4000), d )

		queue << createAffinity(a, b, 0.80f)
		queue << createAffinity(a, c, 0.80f)
		queue << createAffinity(c, d, 0.25f)
				
		ModelHandler handler = new ModelHandler("")
		HeuristicAdaptationPlanner planner = new HeuristicAdaptationPlanner(handler)
		
		println planner.execute(queue)
	}
	
	void testCase4() {
		Queue queue = createQueue()
		
		Host hostA,hostB,hostC,hostD
		ServiceInstance a, b, c, d
		
		a = createService( "a", createMetrics(170, 70) )
		b = createService( "b", createMetrics(330, 190) )
		c = createService( "c", createMetrics(330, 190) )
		d = createService( "d", createMetrics(170, 70) )
		
		hostA = createHost("hostA", createMetrics(2000, 4000), a )
		hostB = createHost("hostB", createMetrics(2000, 4000), b )
		hostC = createHost("hostC", createMetrics(2000, 4000), c )
		hostD = createHost("hostD", createMetrics(2000, 4000), d )

		queue << createAffinity(a, b, 0.33f)
		queue << createAffinity(a, c, 0.33f)
		queue << createAffinity(a, d, 0.33f)
				
		ModelHandler handler = new ModelHandler("")
		HeuristicAdaptationPlanner planner = new HeuristicAdaptationPlanner(handler)
		
		println planner.execute(queue)
	}
	
	void testCase5() {
		Queue queue = createQueue()
		
		Host hostA,hostB,hostC,hostD
		ServiceInstance a, b, c, d
		
		a = createService( "a", createMetrics(500, 350) )
		b = createService( "b", createMetrics(170, 70) )
		c = createService( "c", createMetrics(170, 70) )
		d = createService( "d", createMetrics(170, 70) )
		
		hostA = createHost("hostA", createMetrics(2000, 4000), a )
		hostB = createHost("hostB", createMetrics(2000, 4000), b )
		hostC = createHost("hostC", createMetrics(2000, 4000), c )
		hostD = createHost("hostD", createMetrics(2000, 4000), d )

		queue << createAffinity(a, b, 0.33f)
		queue << createAffinity(b, c, 0.33f)
		queue << createAffinity(a, c, 0.33f)
		queue << createAffinity(c, d, 0.33f)
				
		ModelHandler handler = new ModelHandler("")
		HeuristicAdaptationPlanner planner = new HeuristicAdaptationPlanner(handler)
		
		println planner.execute(queue)
	}
}
