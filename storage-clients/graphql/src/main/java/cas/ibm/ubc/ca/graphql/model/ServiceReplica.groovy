package cas.ibm.ubc.ca.graphql.model

import cas.ibm.ubc.ca.k8s.K8sSession
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames=true)
@EqualsAndHashCode
class ServiceReplica {
	Integer quantity
	List<Service> replicas
	
	static DataFetcher serviceReplicas = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			def name = environment.arguments.name
			println name
//			K8sSession k8sSession = ModelSession.getInstance().getK8sSession()
//			
//			k8sSession.pods("sock-shop").collect([] as Set) { pod ->
//				Service.create(pod)
//			}
			
			return new ServiceReplica(quantity:4, replicas:null)
		}
	}
}
