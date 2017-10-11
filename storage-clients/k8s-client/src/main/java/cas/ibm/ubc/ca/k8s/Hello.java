package cas.ibm.ubc.ca.k8s;

import java.io.IOException;

import com.google.gson.Gson;

import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1Pod;
import io.kubernetes.client.models.V1PodList;
import io.kubernetes.client.util.Config;

public class Hello {
//	static void main(String[] args) {
//		Config config = new ConfigBuilder().withMasterUrl("http://localhost:8001").build();
//		KubernetesClient client = new DefaultKubernetesClient(config);
//		
//		client.extensions().replicaSets().inNamespace("default").withName("nginx-deployment-379064426").edit()
//			.editSpec()
//				.editOrNewTemplate()
//					.editOrNewSpec()
//						.whatINeedToWriteHere()
//						.probablyINeedToUseAnotherK8sApi()
//					.endSpec()
//				.endTemplate()
//			.endSpec()
//			.done();
//				
//	}
	
	public static void main(String[] args) throws IOException, ApiException{
		
//        ApiClient client = Config.fromUrl("http://127.0.0.1:8001");
//        Configuration.setDefaultApiClient(client);
//
//        client.setConnectTimeout(0);
//        
//        CoreV1Api api = new CoreV1Api();
//        
//        V1PodList list = api.listPodForAllNamespaces(null, null, null, null, null, null, null, null, null);
//        for (V1Pod item : list.getItems()) {
//            System.out.println(item.getMetadata().getName());
//        }
		
		KubernetesInspection inspect = new KubernetesInspection("http://127.0.0.1:8001", 0);
		System.out.println (new Gson().toJson(inspect.applications())); 
    }
}
