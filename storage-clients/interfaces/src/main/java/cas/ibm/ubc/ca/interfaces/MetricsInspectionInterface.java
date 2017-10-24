package cas.ibm.ubc.ca.interfaces;

import java.util.Map;

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;

public interface MetricsInspectionInterface {
	Map<String, Double> metricsService(String measurement, TimeInterval timeInterval);
	Map<String, Double> metricsHost(String measurement, TimeInterval timeInterval);
	Double metricService(String id, String measurement, TimeInterval timeInterval);
	Double metricHost(String id, String measurement, TimeInterval timeInterval);
}
