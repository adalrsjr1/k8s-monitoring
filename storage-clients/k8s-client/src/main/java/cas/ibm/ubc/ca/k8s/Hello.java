package cas.ibm.ubc.ca.k8s;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class Hello {
	static void main(String[] args) {
		Config config = new ConfigBuilder().withMasterUrl("http://localhost:8001").build();
		KubernetesClient client = new DefaultKubernetesClient(config);
		
		client.extensions().replicaSets().inNamespace("default").withName("nginx-deployment-379064426").edit()
			.editSpec()
				.editOrNewTemplate()
					.editOrNewSpec()
						.whatINeedToWriteHere()
						.probablyINeedToUseAnotherK8sApi()
					.endSpec()
				.endTemplate()
			.endSpec()
			.done();
				
	}
}
