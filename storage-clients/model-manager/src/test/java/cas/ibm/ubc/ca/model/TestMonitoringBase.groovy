package cas.ibm.ubc.ca.model

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.*

import com.github.tomakehurst.wiremock.WireMockServer

import cas.ibm.ubc.ca.model.manager.MonitoringClient
import okhttp3.OkHttpClient

class TestMonitoringBase extends GroovyTestCase {

	protected String jsonApplications
	protected String jsonHosts
	protected String jsonServices
	
	protected WireMockServer monitoringMock = new WireMockServer(options().port(8888));
	
	protected MonitoringClient testClient
	
	//http://wiremock.org/docs/stubbing/
	public void setUp() {
		jsonApplications = loadJson("applications.json").text
		jsonHosts = loadJson("hosts.json").text
		jsonServices = loadJson("services.json").text
		monitoringMock.start()
		testClient = new MonitoringClient(new OkHttpClient(), "localhost", 8888)
	}
	
	public void tearDown() {
		monitoringMock.stop()
	}
	
	private File loadJson(String path) {
		ClassLoader cl = this.class.getClassLoader()
		new File(cl.getResource(path).getFile())
		
	}
	
	void testBase() {
		assert jsonApplications != null
		assert jsonHosts != null
		assert jsonServices != null
	}
}
