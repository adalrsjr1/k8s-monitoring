package cas.ibm.ubc.ca.k8s

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodList
import io.fabric8.kubernetes.api.model.ReplicationController
import io.fabric8.kubernetes.client.Config
import io.fabric8.kubernetes.client.ConfigBuilder
import io.fabric8.kubernetes.client.DefaultKubernetesClient
import io.fabric8.kubernetes.client.KubernetesClient
import io.fabric8.kubernetes.client.dsl.Listable

class K8sClient {
	
	private static KubernetesClient instance
	
	private K8sClient() { }
	
	static KubernetesClient client() {
		if (Objects.nonNull(instance)) { 
			return instance
		}
			
//		Config config = new ConfigBuilder().withMasterUrl("http://localhost:8888").build()
		Config config = new ConfigBuilder().withMasterUrl("http://localhost:8001").build()
		instance = new DefaultKubernetesClient(config)
		
		return instance
	}
	
	static K8sSession session() {
		return new K8sSession(pods:client().pods(), namespaces:client().namespaces())
	}
	
	static void main(String[] args) {
		
		K8sClient.client().namespaces().list().items.each {
			String name = it.metadata.name
			K8sClient.client().pods().inNamespace(name).list().items.each {
				p -> println p.metadata.name
			}
		}
		
		
		
		Pod pod = K8sClient.client().pods().inNamespace("sock-shop").withName("catalogue-db-1956862931-w5c5c").get()
		
//		println pod
		
		Namespace nsDefault = K8sClient.client().namespaces().withName("sock-shop").get()
//		println nsDefault
//		println k8sClient.namespaces().list()
		
	}

}
