package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import java.util.concurrent.TimeUnit

class MessagesInspectionInterfaceFactory {
	public static MessagesInspectionInterface create(host, String port,
											String timeout, TimeUnit timeUnit) {
		new ZipkinRequestor(host, Integer.parseInt(port),
			Integer.parseInt(timeout), timeUnit)
	}

	public static MessagesInspectionInterface create(host, int port,
											int timeout, TimeUnit timeUnit) {
		new ZipkinRequestor(host, port, timeout, timeUnit)
	}
}
