package cas.ibm.ubc.ca.model.manager.executor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.diff.JsonDiff;

import cas.ibm.ubc.ca.interfaces.ReificationInterface;
import cas.ibm.ubc.ca.interfaces.messages.Moviment;
import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.ApiResponse;
import io.kubernetes.client.Configuration;
import io.kubernetes.client.apis.AppsV1beta1Api;
import io.kubernetes.client.models.AppsV1beta1Deployment;
import io.kubernetes.client.models.AppsV1beta1DeploymentList;
import io.kubernetes.client.models.AppsV1beta1DeploymentSpec;
import io.kubernetes.client.models.V1PodSpec;
import io.kubernetes.client.models.V1PodTemplateSpec;
import io.kubernetes.client.util.Config;


// using the kubectl: kubectl --kubeconfig=$KUBE_TEST patch deployment web-server -p '{"spec": {"template":{"spec":{"nodeSelector":{"kubernetes.io/hostname":"swarm"}}}}}'
public class MoveExecutor implements ReificationInterface {
	private static final Logger LOG = LoggerFactory.getLogger(MoveExecutor.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	private AppsV1beta1Api api;
	
	public MoveExecutor(String host, int port) {
		api = connect(host, port);
	}
	
	private AppsV1beta1Api connect(String host, int port) {
		ApiClient client = Config.fromUrl("http://" + host + ":" +port);
		Configuration.setDefaultApiClient(client);
		
		return new AppsV1beta1Api(client);
	}
	
	private Map[] determineJsonPatch(Object current, Object desired) {

		JsonNode desiredNode = mapper.convertValue(desired, JsonNode.class);
		JsonNode currentNode = mapper.convertValue(current, JsonNode.class);

		return mapper.convertValue(JsonDiff.asJson(currentNode, desiredNode), Map[].class);
	}

	private AppsV1beta1Deployment getDeployment(AppsV1beta1Api v1betaApi, String name) {
		AppsV1beta1DeploymentList list;
		try {
			list = v1betaApi.listDeploymentForAllNamespaces(null, null, null, null, null, null, null, null, null);
			for(AppsV1beta1Deployment deployment : list.getItems()) {
				String deploymentName = deployment.getMetadata().getName();
				if(name.equals(deploymentName)) {
					return deployment;
				}
			}
		} catch (ApiException e) {
			LOG.error(e.getMessage());
			throw new RuntimeException(e);
		}

		return new AppsV1beta1Deployment();
	}

	private Map<String, String> getNodeSelector(AppsV1beta1Deployment deployment) {
		AppsV1beta1DeploymentSpec spec = deployment.getSpec();

		V1PodTemplateSpec temp = spec.getTemplate();

		V1PodSpec podSpec = temp.getSpec();

		Map<String, String> map = podSpec.getNodeSelector();

		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean innerMove(String namespace, String service, String targetNode) {
		// solution based on this snippet:
		// https://github.com/spinnaker/clouddriver/pull/1868/files#diff-150b8912ec8e0cd49b16cb79345b11e7R98'

		AppsV1beta1Api v1betaApi = api;

		// both "current" and "desired" must have the same attributes
		// but they cannot be the same object
		AppsV1beta1Deployment current = getDeployment(v1betaApi, service);
		AppsV1beta1Deployment desired = getDeployment(v1betaApi, service);

		Map<String, String> nodeSelector = getNodeSelector(desired);
		Map[] result;
		if(nodeSelector == null) {
			result = new Map[]{new HashMap<String, String>()};
			result[0].put("op", "add");
			result[0].put("path","/spec/template/spec/nodeSelector");
			Map<String, String> patch = new HashMap<String, String>();
			patch.put("kubernetes.io/hostname", targetNode);
			result[0].put("value",patch);
		}
		else {
			nodeSelector.put("kubernetes.io/hostname", targetNode);
			result = determineJsonPatch(current, desired);
		}

		boolean booleanResult = false;
		try {
			v1betaApi.patchNamespacedDeploymentWithHttpInfo(service, namespace, result, "true");
			booleanResult = true;
		}
		catch (ApiException e) {
			LOG.debug(e.getResponseBody());
			throw new RuntimeException(e);
		}
		return booleanResult;
		
	}
	
	public boolean move(List<Moviment> adaptationScript) {
		Consumer<Moviment> c =  m -> move(m);
		try {
			adaptationScript.stream().forEach(c);
		} 
		catch (Exception e) {
			LOG.warn("one or more messages weren't applied sucessfuly");
			LOG.error(e.getMessage());
		}
		return true;
	}

	public boolean move(Moviment moviment) {
		return innerMove(moviment.application, moviment.service, moviment.hostDestination);
	}

	/*
	 * Does not implement this class using static methods!!!
	 */
	public static void main(String[] args) throws Exception {
		ReificationInterface executor = new MoveExecutor("127.0.0.1", 8001);
		
		executor.move(Moviment.create("sock-shop", "catalogue", "minikube", "minikube"));
	}
}