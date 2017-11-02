package cas.ibm.ubc.ca.k8s;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.diff.JsonDiff;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.ApiResponse;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.models.AppsV1beta1Deployment;
import io.kubernetes.client.models.AppsV1beta1DeploymentList;
import io.kubernetes.client.util.Config;


// using the kubectl: kubectl --kubeconfig=$KUBE_TEST patch deployment web-server -p '{"spec": {"template":{"spec":{"nodeSelector":{"kubernetes.io/hostname":"swarm"}}}}}'
public class Hello {

	static final ObjectMapper mapper = new ObjectMapper();
	
	public static Map[] determineJsonPatch(Object current, Object desired) {
		
		JsonNode desiredNode = mapper.convertValue(desired, JsonNode.class);
		JsonNode currentNode = mapper.convertValue(current, JsonNode.class);

		return mapper.convertValue(JsonDiff.asJson(currentNode, desiredNode), Map[].class);
	}

	public static AppsV1beta1Deployment getDeployment(AppsV1beta1Api v1betaApi, String name) throws Exception {
		AppsV1beta1DeploymentList list = v1betaApi.listDeploymentForAllNamespaces(null, null, null, null, null, null, null, null, null);

		for(AppsV1beta1Deployment deployment : list.getItems()) {
			String deploymentName = deployment.getMetadata().getName();
			if(name.equals(deploymentName)) {
				return deployment;
			}
		}
		return null;
	}


	static Map<String, String> getNodeSelector(AppsV1beta1Deployment deployment) {
		return deployment.getSpec().getTemplate().getSpec().getNodeSelector();
	}

	/*
	 * Does not implement this class using static methods!!!
	 */
	public static void main(String[] args) throws Exception {
		// solution based on this snippet:
		// https://github.com/spinnaker/clouddriver/pull/1868/files#diff-150b8912ec8e0cd49b16cb79345b11e7R98'
		String service = "web-server"; // use "redis-cache" or "web-server"
		String node = "swarm5"; // use "swarm1" or "swarm5"

		// the client is using the proxy address
		ApiClient client = Config.fromUrl("http://127.0.0.1:8001");
		Configuration.setDefaultApiClient(client);

		AppsV1beta1Api v1betaApi = new AppsV1beta1Api(client);

		// both "current" and "desired" must have the same attributes
		// but they cannot be the same object
		AppsV1beta1Deployment current = Hello.getDeployment(v1betaApi, service);
		AppsV1beta1Deployment desired = Hello.getDeployment(v1betaApi, service);
		
		getNodeSelector(desired).put("kubernetes.io/hostname", node);
		Map[] result = determineJsonPatch(current, desired);
		try {

			ApiResponse<AppsV1beta1Deployment> response = v1betaApi.patchNamespacedDeploymentWithHttpInfo(service, "default", result, "true");
			System.out.println(response);
		}
		catch (ApiException e) {
			System.out.println(e.getResponseBody());
			e.printStackTrace();
		}

	}

}
