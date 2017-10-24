package cas.ibm.ubc.ca.k8s

import java.util.Map

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import io.kubernetes.client.ApiClient
import io.kubernetes.client.Configuration
import io.kubernetes.client.apis.CoreV1Api
import io.kubernetes.client.models.V1Namespace
import io.kubernetes.client.models.V1NamespaceList
import io.kubernetes.client.models.V1Node
import io.kubernetes.client.models.V1NodeAddress
import io.kubernetes.client.models.V1NodeList
import io.kubernetes.client.models.V1Pod
import io.kubernetes.client.models.V1PodList
import io.kubernetes.client.models.V1Service
import io.kubernetes.client.models.V1ServiceList
import io.kubernetes.client.util.Config

// https://github.com/kubernetes-client/java/blob/master/kubernetes/README.md
class KubernetesInspection implements ClusterInspectionInterface {

	final String url
	final int timeout

	private CoreV1Api api

	/**
	 * Timeout == 0 -> no timeout
	 * @param url
	 * @param timeout
	 */
	public KubernetesInspection(String url, int timeout) {
		this.url = url
		this.timeout = timeout
		api = client()
	}
	
	private KubernetesInspection() { } 

	private def client() {
		ApiClient client = Config.fromUrl(url);
		Configuration.setDefaultApiClient(client);

		client.setConnectTimeout(timeout);

		return new CoreV1Api();
	}

	@Override
	// https://kubernetes.io/docs/concepts/configuration/manage-compute-resources-container/
	public List hosts() {

		V1NodeList list = api.listNode(null, null, null, null, null, null, null, null, null)

		// allocatable gives the amount of resources that are availabe to Pods {metrics}
		// capacity gives the limit of resources that are avilable in the node {limits}
		return list.getItems().inject([]) { List result, V1Node item ->
			Map host = [
				name: item.metadata.name,
				limits: item.status.capacity,
				metrics: [:],
				services: [],
				hostAddress: item.status.addresses.inject([]) { List r, V1NodeAddress a ->
					r << a.address
					r					
				}
			]

			result << host
			result
		}
	}

	private List auxServices() {
		V1ServiceList list = api.listServiceForAllNamespaces(null, null, null, null, null, null, null, null, null)

		return list.getItems().inject([]) { List result, V1Service item ->
			Map service = [
				name: item.metadata.name,
				application: item.metadata.namespace,
				selector: item.spec.selector,
				ports: item.spec.ports.inject([]) {r,p -> r << p.port},
			]
			result << service
			result
		}
	}

	private List auxInstance() {
		V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null)

		return list.getItems().inject([]) { List result, V1Pod item ->
			Map replica = [
				uid: item.metadata.name,
				limits: item.spec.containers.inject([:]) { m, c ->
					m[c.name] = c.resources.limits != null ? c.resources.limits : [:]
					m
				},
				name: item.metadata.labels["name"],
				metrics: [:],
				messages: [],
				application: item.metadata.namespace,
				address: item.status.podIP,
				hostAddress: item.status.hostIP,
				labels: item.metadata.labels,
				containers: item.spec.containers.collect{ it.name }
			]

			result << replica
			result
		}

	}
	
	private Boolean mergeServiceInstance(Map service, Map instance) {
		if(service.application == instance.application) {
			Map selection = instance.labels.subMap(service.selector.keySet())
			if(selection == service.selector) {
				instance.putAll(service)
				return true
			}
		}
		return false
	}

	@Override
	public List services() {
		List s = auxServices()
		List r = auxInstance()

		List result = []

		while(!r.empty) {
			Map instance = r.remove(0)
			for (Map service in s) {
				if(mergeServiceInstance(service, instance)) {
					result << instance
					break
				}
				
			}
		}
		return result
	}

	@Override
	public Map<String, Float> applications() {

		V1NamespaceList list = api.listNamespace(null, null, null, null, null, null, null, null, null)

		return list.getItems().inject([]) { List result, V1Namespace item ->
			result << item.metadata.name
			result
		}

	}
	
	@Override
	public String cluster() {
		return "KUBERNETES"
	}

}
