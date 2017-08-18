package cas.ibm.ubc.ca.k8s

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.NamespaceList
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodList
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
	
	List<Pod> replicas(String generatedName) {
		pods.getItems().findAll { pod ->
			PodUtil.podGenerateName(pod) == generatedName
		}
	}
	
}
	