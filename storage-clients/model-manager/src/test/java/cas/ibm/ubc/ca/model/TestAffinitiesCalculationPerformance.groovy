package cas.ibm.ubc.ca.model

import java.util.concurrent.TimeUnit

import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer
import cas.ibm.ubc.ca.zipkin.pogos.Message
import model.Affinity
import model.Cluster
import model.Service
import model.ServiceInstance

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestAffinitiesCalculationPerformance extends GroovyTestCase{

	synchronized static ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE

	static MonitoringMock monitor = new MonitoringMock()
	static ModelHandler handler = new ModelHandler("")
	
	void innerTest(def n) {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(n, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
		AffinitiesAnalyzer aa = new AffinitiesAnalyzer()
		
		aa.calculate(cluster)
		
		assert cluster.applications["sock-shop"].totalMessages == n
	}
	
	void testCalculateAffinityMessages10() {
		innerTest(10)
	}

	void testCalculateAffinityMessages100() {
		innerTest(100)
	}
	
	void testCalculateAffinityMessages1000() {
		innerTest(1000)
	}
	
	void testCalculateAffinityMessages10000() {
		innerTest(10000)
	}
	
	void testCalculateAffinityMessages100000() {
		innerTest(100000)
	}
	
	void testCalculateAffinityMessages1000000() {
		innerTest(1000000)
	}
//	
//	void testCalculateAffinityMessages10000000() {
//		innerTest(1000000)
//	}
//	
//	void testCalculateAffinityMessages100000000() {
//		innerTest(100000000)
//	}
//	
//	void testCalculateAffinityMessages1000000000() {
//		innerTest(1000000000)
//	}
	
}
