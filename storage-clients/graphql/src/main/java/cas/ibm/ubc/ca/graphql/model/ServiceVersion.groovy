package cas.ibm.ubc.ca.graphql.model

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.k8s.K8sCache
import cas.ibm.ubc.ca.k8s.PodUtil
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import io.fabric8.kubernetes.api.model.Pod

@Canonical
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
			
			K8sCache k8sCache = ModelSession.getInstance().getK8sCache()
			List<Pod> pods = k8sCache.services(name)
			
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
