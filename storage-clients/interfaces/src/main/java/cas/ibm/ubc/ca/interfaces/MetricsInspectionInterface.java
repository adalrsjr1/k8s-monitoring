package cas.ibm.ubc.ca.interfaces;

import java.util.List;

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;

@SuppressWarnings("rawtypes")
public interface MetricsInspectionInterface {
	List metrics(TimeInterval timeInterval);
	List metrics(String container, TimeInterval timeInterval);
}
