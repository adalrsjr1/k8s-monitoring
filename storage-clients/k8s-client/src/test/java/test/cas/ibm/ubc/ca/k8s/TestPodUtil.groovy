package test.cas.ibm.ubc.ca.k8s

import cas.ibm.ubc.ca.k8s.PodUtil
import io.fabric8.kubernetes.api.model.Pod
import io.fabric8.kubernetes.api.model.PodBuilder
import test.cas.ibm.ubc.ca.k8s.util.KubernetesApiMock

class TestPodUtil extends GroovyTestCase {

	static List pods
	
	protected void setUp() throws Exception {
		super.setUp()
		pods = KubernetesApiMock.getMock("pods.json", Pod.class)
	}
	
	public void testPodUtilPodName() {
		assert pods[0] != null
		assert pods[1] != null
		
		assert "fluentd-bsrv2" == PodUtil.podName(pods[0])
		
		assert "heapster-2879080360-bsmrm" == PodUtil.podName(pods[1])
	}
	
	public void testPodUtilPodGeneratedName() {
		assert pods[9] != null
		assert pods[10] != null
		
		assert "front-end-3297960554-" == PodUtil.podGenerateName(pods[9])
		
		assert "front-end-3297960554-" == PodUtil.podGenerateName(pods[10])
	}
	
	public void testPodUtilPodNameAndPattern() {
		assert pods[0] != null
		
		assert "fluentd-bsrv2" == PodUtil.podName(pods[0], ".*fluentd.*")
		assert "fluentd-bsrv2" == PodUtil.podName(pods[0], ".*bsrv2.*")
		assert "fluentd-bsrv2" == PodUtil.podName(pods[0], ".*d-b.*")
		
	}
	
	public void testPodUtilPodUid() {
		assert pods[0] != null
		
		assert "741f5806-7327-11e7-93ff-080027d26b4e" == PodUtil.podId(pods[0])
	}
	
	public void testPodUtilPodNamespace() {
		assert pods[0] != null
		
		assert "kube-system" == PodUtil.podNamespace(pods[0])
	}
	
	public void testPodUtilPodVersion() {
		assert pods[0] != null
		
		assert ["fluent/fluentd-kubernetes-daemonset:elasticsearch"] == PodUtil.podVersion(pods[0])
		
		assert ["gcr.io/google_containers/heapster-amd64:v1.4.0",
			"gcr.io/google_containers/heapster-amd64:v1.4.0"] == PodUtil.podVersion(pods[1])
	}

}
