package cas.ibm.ubc.ca.monitoring

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@ComponentScan("cas.ibm.ubc.ca.monitoring")
class MonitoringApplication {

	static void main(String[] args) {
		SpringApplication.run MonitoringApplication, args
	}
}
