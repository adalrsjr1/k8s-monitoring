package test.cas.ibm.ubc.ca.k8s.util

import groovy.json.JsonSlurper
import io.fabric8.kubernetes.api.model.ContainerBuilder
import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.NamespaceBuilder
import io.fabric8.kubernetes.api.model.NamespaceList
import io.fabric8.kubernetes.api.model.NamespaceListBuilder
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodBuilder
import io.fabric8.kubernetes.api.model.PodList
import io.fabric8.kubernetes.api.model.PodListBuilder
import io.fabric8.kubernetes.api.model.PodSpecBuilder
import io.fabric8.kubernetes.api.model.Service
import io.fabric8.kubernetes.api.model.ServiceBuilder
import io.fabric8.kubernetes.api.model.ServiceList
import io.fabric8.kubernetes.api.model.ServiceListBuilder

class KubernetesApiMock {
	static List getMock(String filename, Class clazz) {
		String json = KubernetesApiMock.class.getClassLoader()
							.getResource(filename).getText("UTF-8")
		JsonSlurper slurper = new JsonSlurper()
		
		def result = slurper.parseText(json)
		
		
		return Collections.unmodifiableList(
		result.items.inject([]) { list, metaobject ->
			if (clazz == Pod.class) {
				list << createPod(metaobject)
			}
			else if (clazz == Namespace.class) {
				list << createNamespace(metaobject)
			}
			else if (clazz == Service.class) {
				list << createService(metaobject)
			}
		})
	}
	
	// to expand this method according the needs
	static Pod createPod(def metaPod) {
		
		new PodBuilder()
			.withSpec(new PodSpecBuilder()
							.withContainers(
								metaPod.spec.containers.inject([]) { list, cont ->
									list << new ContainerBuilder()
													.withImage(cont.image)
													.withName(cont.name)
													.build()
									})
							.build()			
					)
			.withNewMetadata()
				.withName(metaPod.metadata.name)
				.withNamespace(metaPod.metadata.namespace)
				.withUid(metaPod.metadata.uid)
				.withGenerateName(metaPod.metadata.generateName)
				.and()
				.build()
				
	}
	
	static Namespace createNamespace(def metaNamespace) {
		new NamespaceBuilder()
				.withNewMetadata()
					.withName(metaNamespace.metadata.name)
					.endMetadata()
				.build()
	}
	
	static Service createService(def metaService){
		 new ServiceBuilder()
		 		.withNewMetadata()
				 	 .withName(metaService.metadata.name)
					 .withUid(metaService.metadata.uid)
					 .withNamespace(metaService.metadata.namespace)
					 .and()
					 .build()
	}

	static PodList createPodList(List pods) {
		new PodListBuilder().addAllToItems(pods).build()
	}
		
	static NamespaceList createNamespaceList(List namespaces) {
		new NamespaceListBuilder().addAllToItems(namespaces).build()
	}
	
	static ServiceList createServiceList(List services) {
		new ServiceListBuilder().addAllToItems(services).build()
	}
	
	static void main(String[] args) {
		println KubernetesApiMock.getMock("services.json", Service.class)
	}
	
}
