package test.cas.ibm.ubc.ca.k8s.model

import java.util.List

import cas.ibm.ubc.ca.k8s.model.NamespaceUtil
import io.fabric8.kubernetes.api.model.Namespace
import test.cas.ibm.ubc.ca.k8s.model.util.KubernetesApiMock

class TestNamespaceUtil extends GroovyTestCase {

	static List ns
	
	protected void setUp() throws Exception {
		super.setUp()
		ns = KubernetesApiMock.getMock("namespaces.json", Namespace.class)
	}
	
	public void testNamespaceUtilNamespaceName() {
		assert ns[0] != null && ns.size() > 1
		
		assert "default" == NamespaceUtil.namespaceName(ns[0])
		assert "kube-public" == NamespaceUtil.namespaceName(ns[1])
	}

}
