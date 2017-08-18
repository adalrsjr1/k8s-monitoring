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
	
	public void testPodUtilPodFullName() {
		assert pods[0] != null
		
		assert "fluentd-bsrv2" == PodUtil.podFullName(pods[0])
	}
	
	public void testPodUtilPodName() {
		assert pods[0] != null
		
		assert "fluentd" == PodUtil.podName(pods[0])
	}
	
	public void testPodUtilPodUid() {
		assert pods[0] != null
		
		assert "741f5806-7327-11e7-93ff-080027d26b4e" == PodUtil.podId(pods[0])
	}
	
	public void testPodUtilPodVersion() {
		assert pods[0] != null
		
		assert ["fluent/fluentd-kubernetes-daemonset:elasticsearch"] == PodUtil.podVersion(pods[0])
	}

}
