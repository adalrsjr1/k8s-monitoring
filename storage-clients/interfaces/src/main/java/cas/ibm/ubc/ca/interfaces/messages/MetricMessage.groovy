package cas.ibm.ubc.ca.interfaces.messages

import groovy.transform.Canonical

@Canonical
class MetricMessage {
	public final Double value
	public final String id
}

class MetricContainerMessage extends MetricMessage{
	
	public static final type = "CONTAINER"
}

class HostContainerMessage extends MetricMessage{
	public static final type = "HOST"
}