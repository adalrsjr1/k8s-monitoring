package test.cas.ibm.ubc.ca.k8s

import java.util.List
import cas.ibm.ubc.ca.k8s.K8sCache
import cas.ibm.ubc.ca.k8s.NamespaceUtil
import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.Pod
import test.cas.ibm.ubc.ca.k8s.util.KubernetesApiMock

class TestK8sCache extends GroovyTestCase {
	static List pods
	static List ns
	
	static K8sCache cache
	
	protected void setUp() throws Exception {
		super.setUp()
		pods = KubernetesApiMock.getMock("pods.json", Pod.class)
		ns = KubernetesApiMock.getMock("namespaces.json", Namespace.class)
		
		cache = new K8sCache(pods:KubernetesApiMock.createPodList(pods), 
							 namespaces:KubernetesApiMock.createNamespaceList(ns))
	}
	
	void testNamespace() {
		Namespace ns = null 
		ns = cache.namespace("default")
		
		assert ns != null
		assert NamespaceUtil.namespaceName(ns) == "default"
		
		ns = cache.namespace("sock-shop")
		assert ns != null
		assert NamespaceUtil.namespaceName(ns) == "sock-shop"
		
		ns = cache.namespace("kube-system")
		assert ns != null
		assert NamespaceUtil.namespaceName(ns) == "kube-system"
	}
	
	void testNamespaces() {
		assert cache.namespaces() == ns
	}
	
	void testPods() {
		assert cache.getPods() == pods
	}
	
	void testPodsByNamespace() {
		assert cache.getPodsByNamespace("default") == []
		assert cache.getPodsByNamespace("zipkin").size() == 3
		assert cache.getPodsByNamespace("kube-system").size() == 5 
	}
	
	void testPodsByNamespaceAndName() {
		assert cache.getPodByNamespaceAndName("kube-system","fluentd-bsrv2") != null
		assert cache.getPodByNamespaceAndName("kube-system","fluentd-bsrv2") == pods[0]
	}

	void testPodsByNamespaceAndNameRegex() {
		assert cache.getPodsByNamespaceAndNameRegex("kube-system",".*fluentd.*") == [pods[0]]
		assert cache.getPodsByNamespaceAndNameRegex("kube-system",".*bsrv2.*") == [pods[0]]
		
		assert cache.getPodsByNamespaceAndNameRegex("zipkin",".*zipkin.*").size() == 3
	}
	
	void testPodsReplicas() {
		assert cache.replicas("front-end-3297960554-").size() == 2
		assert cache.replicas("front-end-3297960554-") == pods.subList(9, 11)
	}
}
