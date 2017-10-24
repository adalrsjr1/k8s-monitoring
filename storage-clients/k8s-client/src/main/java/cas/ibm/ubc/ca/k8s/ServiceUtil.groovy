package cas.ibm.ubc.ca.k8s

import io.fabric8.kubernetes.api.model.Service
import io.fabric8.kubernetes.api.model.ServiceBuilder

class ServiceUtil {
	
	static final NULL_SERVICE = new ServiceBuilder()
										.withNewSpec()
											.endSpec()
										.withNewMetadata()
											 .withName("null")
											.withNamespace("null")
											.and()
											.build()
	
	static String serviceName(Service service) {
		service.getMetadata().getName()
	}
	
	static String serviceNamespace(Service service) {
		service.getMetadata().getNamespace()
	}
	
	static String serviceId(Service service) {
		service.getMetadata().getUid()
	}
	
	static String serviceIp(Service service) {
		service.getSpec().getClusterIP()
	}
	
	static Service nullService() {
		NULL_SERVICE
	}
}
