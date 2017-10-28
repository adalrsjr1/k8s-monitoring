package cas.ibm.ubc.ca.monitoring

import java.util.List
import java.util.Map

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import groovy.transform.Canonical

@Canonical
class MonitoringApplication implements ClusterInspectionInterface, 
	MessagesInspectionInterface, MetricsInspectionInterface {

	private ClusterInspectionInterface clusterMonitor
	private MessagesInspectionInterface messagesMonitor
	private MetricsInspectionInterface metricsMonitor

	public List hosts() {
		return clusterMonitor.hosts();
	}

	public List services() {
		return clusterMonitor.services();
	}

	public Map<String, Float> applications() {
		return clusterMonitor.applications();
	}

	public String cluster() {
		return clusterMonitor.cluster();
	}

	public List messages(TimeInterval timeInterval) {
		return messagesMonitor.messages(timeInterval);
	}

	public List messages(String serviceInstance, TimeInterval timeInterval) {
		return messagesMonitor.messages(serviceInstance, timeInterval);
	}

	public Map<String, Double> metricsService(String measurement, TimeInterval timeInterval) {
		return metricsMonitor.metricsService(measurement, timeInterval);
	}

	public Map<String, Double> metricsHost(String measurement, TimeInterval timeInterval) {
		return metricsMonitor.metricsHost(measurement, timeInterval);
	}

	public Double metricService(String id, String measurement, TimeInterval timeInterval) {
		return metricsMonitor.metricService(id, measurement, timeInterval);
	}

	public Double metricHost(String id, String measurement, TimeInterval timeInterval) {
		return metricsMonitor.metricHost(id, measurement, timeInterval);
	}
}
