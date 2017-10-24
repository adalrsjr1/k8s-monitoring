package cas.ibm.ubc.ca.monitoring.controllers

import java.util.List
import java.util.Map

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval


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
	@RequestMapping(value = "/messages/{timeInterval}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List messages(TimeInterval timeInterval) {
		return null;
	}
	@Override
	@RequestMapping(value = "/messages/{service}/{timeInterval}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List messages(String serviceInstance, TimeInterval timeInterval) {
		return null;
	}
	@Override
	@RequestMapping(value = "/metrics/{measurement}/{timeInterval}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Map<String, Double> metricsService(String measurement, TimeInterval timeInterval) {
		return null;
	}
	@Override
	@RequestMapping(value = "/metrics/{measurement}/{timeInterval}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Map<String, Double> metricsHost(String measurement, TimeInterval timeInterval) {
		return null;
	}
	@Override
	@RequestMapping(value = "/metrics/{service}/{measurement}/{timeInterval}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Double metricService(String id, String measurement, TimeInterval timeInterval) {
		return null;
	}
	@Override
	@RequestMapping(value = "/metrics/{host}/{measurement}/{timeInterval}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Double metricHost(String id, String measurement, TimeInterval timeInterval) {
		return null;
	}
	@Override
	@RequestMapping(value = "/hosts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List hosts() {
		return null;
	}
	@Override
	@RequestMapping(value = "/services", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public List services() {
		return null;
	}
	@Override
	@RequestMapping(value = "/applications", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public Map<String, Float> applications() {
		return null;
	}
	@Override
	@RequestMapping(value = "/cluster", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
	public String cluster() {
		return null;
	}	
	
	
}
