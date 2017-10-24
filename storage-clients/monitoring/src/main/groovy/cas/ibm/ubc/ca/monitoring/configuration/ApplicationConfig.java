package cas.ibm.ubc.ca.monitoring.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface;
import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface;
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface;
import cas.ibm.ubc.ca.k8s.KubernetesInspection;

@Configuration
class ApplicationConfig {

	@Bean
	public ClusterInspectionInterface clusterInspectionInterface(@Value("${monitoring.cluster.url}") String url) {
		return new KubernetesInspection(url, 0);
	}
	
//	@Bean
//	public MetricsInspectionInterface metricsInspectionInterface() {
//		return null;
//	}
//	
//	@Bean
//	public MessagesInspectionInterface messagesInspectionInterface() {
//		return null;
//	}
}
