package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import org.codehaus.groovy.runtime.metaclass.NewMetaMethod
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockClassRule
import com.github.tomakehurst.wiremock.junit.WireMockRule

import cas.ibm.ubc.ca.model.manager.MonitoringClient

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;


import groovy.util.GroovyTestCase
import okhttp3.OkHttpClient

class TestMonitoringClient extends GroovyTestCase {

	String jsonApplications
	String jsonHosts
	String jsonServices
	
	String jsonEnvironment = "KUBERNETES"
	
	WireMockServer monitoringMock = new WireMockServer(options().port(8888));
	
	MonitoringClient testClient
	
	//http://wiremock.org/docs/stubbing/
	public void setUp() {
		jsonApplications = loadJson("applications.json").text
		jsonHosts = loadJson("hosts.json").text
		jsonServices = loadJson("services.json").text
		monitoringMock.start()
		testClient = new MonitoringClient(new OkHttpClient(), "http://localhost:8888")
	}
	
	public void tearDown() {
		monitoringMock.stop()
	}
	
	private File loadJson(String path) {
		ClassLoader cl = this.class.getClassLoader()
		new File(cl.getResource(path).getFile())
		
	}
	
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
