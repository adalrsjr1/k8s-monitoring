package cas.ibm.ubc.ca.monitoring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cas.ibm.ubc.ca.k8s.KubernetesInspection;
import cas.ibm.ubc.ca.k8s.interfaces.ClusterInspectionInterface;

@Configuration
class ApplicationConfig {

	@Bean
	public ClusterInspectionInterface clusterInspectionInterface(@Value("${monitoring.cluster.url}") String url) {
		return new KubernetesInspection(url, 0);
	}
	
}
