package cas.ibm.ubc.ca.interfaces;

import java.util.List;

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;

@SuppressWarnings("rawtypes")
public interface MessagesInspectionInterface {
	List messages(TimeInterval timeInterval);
	List messages(String serviceInstance, TimeInterval timeInterval);
}
