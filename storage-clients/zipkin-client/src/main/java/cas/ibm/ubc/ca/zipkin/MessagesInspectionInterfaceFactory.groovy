package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import java.util.concurrent.TimeUnit

class MessagesInspectionInterfaceFactory {
	public static MessagesInspectionInterface create(host, String port,
											String timeout, TimeUnit timeUnit) {
		new ZipkinClient(host, Integer.parseInt(port))
	}

	public static MessagesInspectionInterface create(host, int port) {
		new ZipkinClient(host, port)
	}
}
