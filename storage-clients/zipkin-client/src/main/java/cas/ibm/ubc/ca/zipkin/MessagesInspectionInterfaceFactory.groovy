package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import java.util.concurrent.TimeUnit

class MessagesInspectionInterfaceFactory {
	public static MessagesInspectionInterface create(host, port,
											timeout, limit, TimeUnit timeUnit) {
		new ZipkinClient(host, Integer.parseInt(port), Integer.parseInt(timeout), Integer.parseInt(limit), timeUnit)
	}

	public static MessagesInspectionInterface create(host, int port) {
		new ZipkinClient(host, port)
	}
}
