package cas.ibm.ubc.ca.k8s

import io.fabric8.kubernetes.api.model.Service

class ServiceUtil {
	static String serviceName(Service service) {
		service.getMetadata().getName()
	}
	
	static String serviceNamespace(Service service) {
		service.getMetadata().getNamespace()
	}
}
