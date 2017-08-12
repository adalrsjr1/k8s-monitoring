package cas.ibm.ubc.ca.graphql.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.k8s.K8sSession
import cas.ibm.ubc.ca.k8s.model.PodUtil
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.fabric8.kubernetes.api.model.Pod

@ToString(includeNames=true)
@EqualsAndHashCode
class ServiceVersion {
	private static Logger logger = LoggerFactory.getLogger(ServiceVersion.class)
	List<String> version
	Integer n_replicas
	List<Service> replicas
		
	ServiceVersion(List<String> version, Integer nReplicas, List<Service> replicas) {
		this.version = Collections.unmodifiableList(version)
		this.n_replicas = nReplicas
		this.replicas = replicas
	}
	
	static DataFetcher serviceVersion = new DataFetcher() {
		Object get(DataFetchingEnvironment environment) {
			String name = environment.source.name
			
			K8sSession k8sSession = ModelSession.getInstance().getK8sSession()
			List<Pod> pods = k8sSession.services(name)
			
			return createVersions(pods)
		}
	}
	
	static private List<ServiceVersion> createVersions(List<Pod> pods) {
		logger.warn("It is necessary implement this method correctly: createVersions")
		
		List<Service> replicas = pods.inject([]) {l, pod ->
			l << Service.create(pod)
		}
		
		return [new ServiceVersion(PodUtil.podVersion(pods[0]), pods.size(),replicas)]
	}
}
