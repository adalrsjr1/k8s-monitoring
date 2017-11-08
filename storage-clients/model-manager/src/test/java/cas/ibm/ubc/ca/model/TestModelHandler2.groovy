package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import model.Host
import model.ServiceInstance

class TestModelHandler2 extends GroovyTestCase {

	final static ModelHandler handler = new ModelHandler("")
	final static ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE
	
	void testSplitMetrics() {
		
		assert handler.splitMap(["a":(Double)10.0], ["a":(Double)10.0]) == ["a":(Double)0.0]
		assert handler.splitMap(["a":(Double)10.0, "b":(Double)5.0], ["a":(Double)10.0, "b":(Double)3.0]) == ["a":(Double)0.0, "b":(Double)2.0]
		
		Map a = ["a":(Double)10.0]
		handler.splitMap(a, ["a":(Double)10.0]) 
		assert a == ["a":(Double)0.0]
	}
	
	void testMergeMetrics() {
		assert handler.mergeMap(["a":(Double)10.0], ["a":(Double)10.0]) == ["a":(Double)20.0]
		assert handler.mergeMap(["a":(Double)10.0, "b":(Double)5.0], ["a":(Double)10.0, "b":(Double)3.0]) == ["a":(Double)20.0, "b":(Double)8.0]
		
		Map a = ["a":(Double)10.0]
		handler.mergeMap(a, ["a":(Double)10.0])
		assert a == ["a":(Double)20.0]
	}
	
	void testMoveOnModel() {
		Host a,b
		ServiceInstance svc
		
		a = factory.createHost()
		b = factory.createHost()
		svc = factory.createServiceInstance()
		
		a.setName("hostA")
		a.metrics.putAll(["a":(Double)100.0])
		a.resourceReserved.putAll(["a":(Double)10.0])
		b.setName("hostB")
		b.metrics.putAll(["a":(Double)200.0])
		b.resourceReserved.putAll(["a":(Double)20.0])
		
		svc.setName("svc")
		svc.setId("svc")
		svc.setHost(a)
		svc.metrics.putAll("a":(Double)1.0)
		
		handler.moveOnModel(svc, b)
		assert a.resourceReserved == ["a":(Double)9.0]
		assert b.resourceReserved == ["a":(Double)21.0]
		
	}
	
	
	
}
