package cas.ibm.ubc.ca.graphql.model

import cas.ibm.ubc.ca.k8s.K8sSession
import cas.ibm.ubc.ca.k8s.model.NamespaceUtil
import cas.ibm.ubc.ca.k8s.model.PodUtil
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.Namespace as Ns

@ToString(includeNames=true)
@EqualsAndHashCode
class Namespace {
	String name
	Collection<Service> services
	
	static DataFetcher namespace = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			def name = environment.arguments.name
			
			K8sSession k8sSession = ModelSession.getInstance().getK8sSession()
			
			Ns namespace = k8sSession.namespace(name)
			List<Pod> pods = k8sSession.allServices(name)
			
			Set set = pods.inject([] as Set) {set, pod ->
				set << Service.create(pod)	
			}
			
			return new Namespace(name:name, services:set as List)
			
		}
	}
	
	static DataFetcher namespaces = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			K8sSession k8sSession = ModelSession.getInstance().getK8sSession()
			
			List<Ns> namespaces = k8sSession.namespaces()
			
			List<Namespace> result = namespaces.inject([]) { list, ns ->
				String name = NamespaceUtil.namespaceName(ns)
				List<Pod> pods = k8sSession.allServices(name)
				
				Set set = pods.inject([] as Set) {set, pod ->
					set << Service.create(pod)
				}
					
				list << new Namespace(name:name, services:set as List) 
			}
			
			
			
			return result
			
		}
	}
}
