package test.cas.ibm.ubc.ca.k8s.model.util

import java.util.Collections.UnmodifiableList

import groovy.json.JsonSlurper
import io.fabric8.kubernetes.api.model.ContainerBuilder
import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.NamespaceBuilder
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodBuilder
import io.fabric8.kubernetes.api.model.PodSpecBuilder

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
	
	static void main(String[] args) {
		println KubernetesApiMock.getMock("namespaces.json", Namespace.class)
	}
	
}
