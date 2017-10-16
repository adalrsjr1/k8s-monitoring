package cas.ibm.ubc.ca.model

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static org.junit.Assert.*

class TestMonitoringClient extends TestMonitoringBase {
	
	public void testApplications() {
		monitoringMock.stubFor(get("/model/applications")
						.willReturn(okJson(jsonApplications)))
			
		assert testClient.applications() == ["default", "kube-public", "kube-system", "sock-shop", "zipkin"]
	}
	
	public void testHosts() {
		monitoringMock.stubFor(get("/model/hosts")
						.willReturn(okJson(jsonHosts)))
		
		assert testClient.hosts().collect{ it.name } == ["gfads1","gfads2"]
	}
	
	public void testServices() {
		monitoringMock.stubFor(get("/model/services")
			.willReturn(okJson(jsonServices)))

		assert testClient.services().collect { it.name 	} == ["elasticsearch", "heapster", "kibana", "kube-dns", 
			"kubernetes-dashboard", "monitoring-grafana", "monitoring-influxdb", "carts", "carts-db", "catalogue", 
			"catalogue-db", "front-end", "orders", "orders-db", "payment", "queue-master", "shipping", "user", 
			"user-db", "zipkin", "zipkin-mysql"]
	}
	
	public void testCluster() {
		monitoringMock.stubFor(get("/model/cluster")
			.willReturn(okJson("KUBERNETES")))
		
		assert testClient.cluster() == "KUBERNETES"
		
		monitoringMock.stubFor(get("/model/cluster")
			.willReturn(okJson("DOCKER_SWARM")))
		
		assert testClient.cluster() == "DOCKER_SWARM"
	}
	
	public void testMetrics() {
		assert false
	}
	
	public void testMetricsContainer() {
		assert false
	}

	public void testMessages() {
		assert false
	}
	
	public void testMessagesServiceInstance() {
		assert false
	}
}
