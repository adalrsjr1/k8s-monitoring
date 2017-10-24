package cas.ibm.ubc.ca.interfaces;

import java.util.List;

import cas.ibm.ubc.ca.interfaces.messages.MetricMessage;
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;

@SuppressWarnings("rawtypes")
public interface MetricsInspectionInterface {
	List<MetricMessage> metrics(String measurement, TimeInterval timeInterval);
	Double metricContainer(String container, String measurement, TimeInterval timeInterval);
	Double metricHost(String host, String measurement, TimeInterval timeInterval);
}
