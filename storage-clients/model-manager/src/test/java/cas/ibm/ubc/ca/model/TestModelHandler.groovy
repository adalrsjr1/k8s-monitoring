package cas.ibm.ubc.ca.model

import static com.github.tomakehurst.wiremock.client.WireMock.*

import cas.ibm.ubc.ca.model.manager.ModelHandler
import model.Cluster

class TestModelHandler extends TestMonitoringBase {

	ModelHandler handler
	
	public void setUp() {
		super.setUp()
		
		monitoringMock.stubFor(get("/model/cluster")
			.willReturn(okJson("KUBERNETES")))

		monitoringMock.stubFor(get("/model/services")
			.willReturn(okJson(jsonServices)))
		
		monitoringMock.stubFor(get("/model/hosts")
			.willReturn(okJson(jsonHosts)))
		
		monitoringMock.stubFor(get("/model/applications")
			.willReturn(okJson(jsonApplications)))
		
		handler = new ModelHandler("src/test/resource/")
	}
	
	void testCreateAndSave() {
		String env = testClient.cluster()
		
		Cluster cluster = handler.createCluster(env)
		assert cluster != null
		
		Map apps = testClient.applications()
		List applications = handler.createApplications(cluster, apps)
//		assert apps != null
//		assert apps.empty != false
		
		List hh = testClient.hosts()
		List hosts = handler.createHosts(cluster, hh)
//		assert hosts != null
//		assert hosts.empty != false
		
		List ss = testClient.services()
		List services = handler.createServices(cluster, ss)
//		assert services != null
//		assert services.empty != false
		
		handler.saveModel()
		
		 
	}

}
