package cas.ibm.ubc.ca.k8s

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.NamespaceList
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodList
import io.fabric8.kubernetes.api.model.Service
import io.fabric8.kubernetes.api.model.ServiceBuilder
import io.fabric8.kubernetes.api.model.ServiceList

class K8sCache {
	private PodList pods
	private NamespaceList namespaces
	private ServiceList services

	void refresh() {
		throw new UnsupportedOperationException("Not implemented yet")
	}
	
	Namespace namespace(String name) {
		namespaces.items.find { ns ->
			NamespaceUtil.namespaceName(ns) == name
		}
	}
	
	List<Namespace> namespaces() {
		namespaces.getItems()
	}

	List<Pod> getPods() {
		pods.getItems()
	}
	
	List<Pod> getPodsByNamespace(String namespace) {
		pods.getItems().findAll { pod ->
			PodUtil.podNamespace(pod) == namespace
		}
	}
	
	Pod getPodByNamespaceAndName(String namespace, String name) {
		pods.getItems().find { pod ->
			PodUtil.podNamespace(pod) == namespace && PodUtil.podName(pod) == name
		}
	}
	
	List<Pod> getPodsByNamespaceAndNameRegex(String namespace, String regex) {
		pods.getItems().findAll { pod ->
			PodUtil.podNamespace(pod) == namespace && PodUtil.podName(pod,regex)
		}
	}
	
	List<Pod> getPodsByName(String name) {
		pods.getItems().findAll { pod ->
			PodUtil.podName(pod) == name
		}
	}
	
	List<Pod> replicas(String serviceName) {
		pods.getItems().findAll { pod ->
			PodUtil.podName(pod, ".*${serviceName}.*")
		}
	}
	
	List<Service> getServices() {
		services.getItems()
	}
	
	Service getServiceByName(String name) {
		services.getItems().find { service ->
			ServiceUtil.serviceName(service) == name
		}
	}
	
	Service getServiceByPod(Pod pod) {
		Service service = getServices().find { service ->
			PodUtil.podName(pod, ".*${ServiceUtil.serviceName(service)}.*")
		}
		
		if (!service) {
			service = new ServiceBuilder()
			.withNewMetadata()
				 .withName("null")
				.withNamespace("null")
				.and()
				.build()
		}
		
		service
		
	}
	
	List<Service> getServiceByNamespace(Namespace ns) {
		
		List services = getServices().findAll { service ->
			service.getMetadata().getNamespace() == NamespaceUtil.namespaceName(ns)
		}
		if(services.size() == 0) {
		services << new ServiceBuilder()
			.withNewMetadata()
				 .withName("null")
				.withNamespace("null")
				.and()
				.build()
		}
		
		services
	}
	
	
	
}
	