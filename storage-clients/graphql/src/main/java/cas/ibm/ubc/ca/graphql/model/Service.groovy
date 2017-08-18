package cas.ibm.ubc.ca.graphql.model

import cas.ibm.ubc.ca.k8s.K8sCache
import cas.ibm.ubc.ca.k8s.model.PodUtil
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.FieldDataFetcher
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.fabric8.kubernetes.api.model.Pod

@Canonical
class Service {
	String id
	String name
//	ServiceVersion serviceVersion
	
	static Service create(Pod pod) {
		
			new Service(id:PodUtil.podId(pod), name:PodUtil.podName(pod))
	}
	
	static DataFetcher services = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			K8sCache k8sCache = ModelSession.getInstance().getK8sCache()
			
			k8sCache.pods().collect([] as Set) { pod ->
				Service.create(pod)
			}
		}
	}
	
	static DataFetcher service = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			def name = environment.arguments.name
			
			K8sCache k8sCache = ModelSession.getInstance().getK8sCache()
			
			Pod pod = k8sCache.service(name)
			Service.create(pod)
		}
	}
	
}
