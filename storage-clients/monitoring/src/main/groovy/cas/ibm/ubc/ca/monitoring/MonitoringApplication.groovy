package cas.ibm.ubc.ca.monitoring


import java.util.concurrent.TimeUnit

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.influx.MetricsInspectionInterfaceFactory
import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import cas.ibm.ubc.ca.k8s.ClusterInspectionInterfaceFactory
import cas.ibm.ubc.ca.zipkin.MessagesInspectionInterfaceFactory


class MonitoringApplication implements ClusterInspectionInterface,
MessagesInspectionInterface, MetricsInspectionInterface {
	private static Logger LOG = LoggerFactory.getLogger(MonitoringApplication.class)

	private ClusterInspectionInterface clusterMonitor
	private MessagesInspectionInterface messagesMonitor
	private MetricsInspectionInterface metricsMonitor

	private Properties properties

	public MonitoringApplication() {
		properties = loadProperties()

		clusterMonitor = ClusterInspectionInterfaceFactory.create(
			properties["cluster.inspection.host"] + ":" + properties["cluster.inspection.port"], 
			properties["cluster.inspection.timeout"])
		
		messagesMonitor = MessagesInspectionInterfaceFactory.create(
			properties["messages.inspection.host"], 
			properties["messages.inspection.port"], 
			properties["messages.inspection.timeout"], 
			TimeUnit.MILLISECONDS)
		
		
		metricsMonitor = MetricsInspectionInterfaceFactory.create(
			properties["metrics.inspection.host"], 
			properties["metrics.inspection.port"], 
			properties["metrics.inspection.influx.user"],
			properties["metrics.inspection.influx.password"],
			properties["metrics.inspection.influx.database"])
		
	}

	private static Properties loadProperties() {
		ClassLoader cl = MonitoringApplication.class.getClassLoader()
		InputStream inputStream
		
		Properties properties = new Properties()
		
		try {
			inputStream = cl.getResourceAsStream("application.properties")
			properties.load(inputStream)
		}
		catch(IOException e) {
			LOG.error(e.getMessage())
			throw new RuntimeException(e)
		}
		finally {
			inputStream?.close()
		}
		return properties
	}

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
