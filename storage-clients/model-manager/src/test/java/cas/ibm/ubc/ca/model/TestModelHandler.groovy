package cas.ibm.ubc.ca.model

import java.util.concurrent.TimeUnit

import org.eclipse.emf.ecore.util.EcoreUtil
import org.junit.FixMethodOrder
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout
import org.junit.runners.MethodSorters;

import cas.ibm.ubc.ca.interfaces.InspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.*
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import model.Application
import model.Cluster
import model.ServiceInstance

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TestModelHandler extends GroovyTestCase {
	static final ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	
	InspectionInterface monitor
	ModelHandler handler
	
	@Rule
	public Timeout globalTimeout = new Timeout(1000)
	
	public void setUp() {
		super.setUp()
		
		monitor = new MonitoringMock()
		handler = new ModelHandler("src/test/resource/")
	}
	
	void testCreateMetricIntoService() {
		
		ServiceInstance svc = factory.createServiceInstance()
		svc.name = "name"
		svc.id = svc.name
		svc.metrics["test1"] = (Double)3.14
		svc.metrics["test2"] = (Double)1.23
		svc.metrics["test3"] = (Double)2.56
		
		def nSvc = factory.createServiceInstance()
		nSvc.name = "name"
		nSvc.id = svc.name
		handler.createMetric(nSvc, 
			"name", 
			["test1","test2","test3"], 
			[ ["name":3.14, "name1":1],
			  ["name1":3, "name":1.23],
			  ["name":2.56]
				])
		
		assert svc.metrics == nSvc.metrics

		assert EcoreUtil.equals(svc, nSvc)	
	}
	
	void testCreateMetrics() {
		Cluster cluster = factory.createCluster()
		ServiceInstance service = factory.createServiceInstance()
		service.name = "svc"
		service.id = "svc"
		Application app = factory.createApplication()
		app.name = "app"
		
		cluster.applications["app"] = app
		app.services["svc"] = service
		handler.createMetrics(cluster, ["test"], [["svc":3.1415]])
		
		assert 3.1415 == cluster.applications["app"].services["svc"].metrics["test"]
	}
	
	void testFillModelMessages10() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster, 
			monitor.environment(), 
			monitor.hosts(), 
			monitor.applications(), 
			monitor.services(), 
			monitor.messages(TimeInterval.last(10, TimeUnit.MILLISECONDS)), 
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
	void testFillModelMessages100() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(100, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
	void testFillModelMessages10000() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(10000, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
	void testFillModelMessages100000() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(100000, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
	void testFillModelMessages1000() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(1000, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
	void testFillModelMessages1000000() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(1000000, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
	void testFillModelMessages10000000() {
		Cluster cluster = factory.createCluster()
		
		handler.fillModel(cluster,
			monitor.environment(),
			monitor.hosts(),
			monitor.applications(),
			monitor.services(),
			monitor.messages(TimeInterval.last(10000000, TimeUnit.MILLISECONDS)),
			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
	}
	
//	void testFillModelMessages100000000() {
//		Cluster cluster = factory.createCluster()
//		
//		handler.fillModel(cluster,
//			monitor.environment(),
//			monitor.hosts(),
//			monitor.applications(),
//			monitor.services(),
//			monitor.messages(TimeInterval.last(100000000, TimeUnit.MILLISECONDS)),
//			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
//			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
//				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
//				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
//				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
//				])
//	}
//	
//	void testFillModelMessages1000000000() {
//		Cluster cluster = factory.createCluster()
//		
//		handler.fillModel(cluster,
//			monitor.environment(),
//			monitor.hosts(),
//			monitor.applications(),
//			monitor.services(),
//			monitor.messages(TimeInterval.last(1000000000, TimeUnit.MILLISECONDS)),
//			["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
//			[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
//				monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
//				monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
//				monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
//				])
//	}

}
