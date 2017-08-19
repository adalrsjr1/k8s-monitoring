package test.cas.ibm.ubc.ca.k8s

import java.util.List

import cas.ibm.ubc.ca.k8s.ServiceUtil
import io.fabric8.kubernetes.api.model.Service
import test.cas.ibm.ubc.ca.k8s.util.KubernetesApiMock

class TestServiceUtil extends GroovyTestCase {
	static List services
	
	protected void setUp() throws Exception {
		super.setUp()
		services = KubernetesApiMock.getMock("services.json", Service.class)
	}
	
	void testServiceUtilName() {
		assert services[0] != null
		assert services[1] != null
		
		assert "kubernetes" == ServiceUtil.serviceName(services[0])
		assert "heapster" == ServiceUtil.serviceName(services[1])
	}
	
	void testServiceUtilNamespace() {
		assert services[0] != null
		assert services[1] != null
		
		assert "default" == ServiceUtil.serviceNamespace(services[0])
		assert "kube-system" == ServiceUtil.serviceNamespace(services[1])
	}

}
