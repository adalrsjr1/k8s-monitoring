package cas.ibm.ubc.ca.monitoring.controllers

import java.util.List

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import cas.ibm.ubc.ca.k8s.interfaces.ClusterInspectionInterface

@RestController
@RequestMapping("/model")
class ModelController implements ClusterInspectionInterface, MetricsInspectionInterface, 
		MessagesInspectionInterface {

	@Autowired
	ClusterInspectionInterface clusterInspection
	
	@Autowired
	MetricsInspectionInterface metricsInspection
	
	@Autowired
	MessagesInspectionInterface messagesInspection
	
	@Override
	@RequestMapping(value = "/hosts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List hosts() {
		return clusterInspection.hosts()
	}

	@Override
	@RequestMapping(value = "/services", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List services() {
		return clusterInspection.services()
	}

	@Override
	@RequestMapping(value = "/applications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List applications() {
		return clusterInspection.applications()
	}

	@Override
	@RequestMapping(value = "/cluster", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public String cluster() {
		return clusterInspection.cluster()
	}
	
	@Override
	@RequestMapping(value = "/metrics", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List metrics() {
		return metricsInspection.metrics()
	}
	
	@Override
	@RequestMapping(value = "/metrics/{container}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List metrics(@PathVariable  String container) {
		return metricsInspection.metrics(container)
	}
	
	@Override
	@RequestMapping(value = "/cluster", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List messages() {
		return messagesInspection.messages()
	}

	@Override
	@RequestMapping(value = "/cluster/{serviceInstance}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List messages(@PathVariable String serviceInstance) {
		return messagesInspection.messages(serviceInstance)
	}
	
}
