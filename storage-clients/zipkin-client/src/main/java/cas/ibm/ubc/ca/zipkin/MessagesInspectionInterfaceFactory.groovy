package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import java.util.concurrent.TimeUnit

class MessagesInspectionInterfaceFactory {
	public static MessagesInspectionInterface create(host, port, timeout, TimeUnit timeUnit) {
		return new ZipkinRequestor(host, Integer.parseInt(port), 
			Integer.parseInt(timeout), timeUnit)
	}
}
