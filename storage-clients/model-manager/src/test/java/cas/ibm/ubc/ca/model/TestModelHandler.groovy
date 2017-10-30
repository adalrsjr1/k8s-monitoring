package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.interfaces.InspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.*
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import java.util.concurrent.TimeUnit
import model.Application
import model.Cluster
import model.ServiceInstance

class TestModelHandler extends GroovyTestCase {
	static final ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	
	InspectionInterface monitor
	ModelHandler handler
	
	public void setUp() {
		super.setUp()
		
		monitor = new MonitoringMock()
		handler = new ModelHandler("src/test/resource/")
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
		handler.createMetrics(cluster, "test", ["svc":3.1415])
		
		assert 3.1415 == cluster.applications["app"].services["svc"].metrics["test"]
	}
	
	void testFillModel10Messages() {
//		Cluster cluster = factory.createCluster()
//		handler.fillModel(cluster, 
//			monitor.environment(), 
//			monitor.hosts(), 
//			monitor.applications(), 
//			monitor.services(), 
//			monitor.messages(TimeInterval.last(10, TimeUnit.MILLISECONDS)), 
//			[
//				monitor.metricsHost(measurement, timeInterval),
//				monitor.metricsService(measurement, timeInterval),
//				])
	}

}
