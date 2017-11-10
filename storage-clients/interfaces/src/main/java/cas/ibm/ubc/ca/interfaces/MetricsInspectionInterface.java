package cas.ibm.ubc.ca.interfaces;

import java.util.Map;

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;

public interface MetricsInspectionInterface {
	
	public static enum Measurement {
		CPU, MEMORY
	}
	
	Map<String, Double> metricsService(Measurement measurement, TimeInterval timeInterval);
	Map<String, Double> metricsHost(Measurement measurement, TimeInterval timeInterval);
	Double metricService(String id, Measurement measurement, TimeInterval timeInterval);
	Double metricHost(String id, Measurement measurement, TimeInterval timeInterval);
}
