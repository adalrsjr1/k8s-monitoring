package cas.ibm.ubc.ca.graphql.model

import cas.ibm.ubc.ca.k8s.K8sCache
import cas.ibm.ubc.ca.k8s.NamespaceUtil
import cas.ibm.ubc.ca.k8s.PodUtil
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.Namespace as Ns
import io.fabric8.kubernetes.api.model.Service as Srv

@Canonical
class Namespace {
	String name
	Collection<Service> services
	
	static DataFetcher namespace = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			def name = environment.arguments.name
			
			K8sCache k8sCache = ModelSession.getInstance().getK8sCache()
			
			Ns namespace = k8sCache.namespace(name)
			List<Srv> services = k8sCache.getServiceByNamespace(namespace)
			
			Set set = services.inject([] as Set) {set, pod ->
				set << Service.create(pod)	
			}
			
			return new Namespace(name:name, services:set as List)
			
		}
	}
	
	static DataFetcher namespaces = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			K8sCache k8sCache = ModelSession.getInstance().getK8sCache()
			
			List<Ns> namespaces = k8sCache.namespaces()
			
			List<Namespace> result = namespaces.inject([]) { list, ns ->
				List<Srv> services = k8sCache.getServiceByNamespace(ns)
				
				Set set = services.inject([] as Set) {set, pod ->
					set << Service.create(pod)
				}
					
				list << new Namespace(name:NamespaceUtil.namespaceName(ns), services:set as List) 
			}
			
			
			
			return result
			
		}
	}
}
