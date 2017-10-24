package cas.ibm.ubc.ca.monitoring.monitoring

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class MonitoringApplicationTests {

	@Autowired
	TestRestTemplate restTemplate
	
	@Test
	void contextLoads() {
		String body = restTemplate.getForObject("/model/applications", List.class)
		println body
		assert true
	}

}
