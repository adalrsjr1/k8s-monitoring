package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import java.util.concurrent.TimeUnit

class MessagesInspectionInterfaceFactory {
	public static MessagesInspectionInterface create(String host, int port, int timeout, TimeUnit timeUnit) {
		return new ZipkinRequestor(host, port, timeout, timeUnit)
	}
}
