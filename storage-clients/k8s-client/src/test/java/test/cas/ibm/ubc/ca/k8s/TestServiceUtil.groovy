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
	
	void testServiceId() {
		assert services[0] != null
		assert services[1] != null
		
		assert "62e8f2cd-7182-11e7-8225-080027d26b4e" == ServiceUtil.serviceId(services[0])
		assert "d833906d-731e-11e7-93ff-080027d26b4e" == ServiceUtil.serviceId(services[1])
	}
	
	void testServiceIp() {
		assert services[0] != null
		assert services[1] != null
		
		assert "10.0.0.1" == ServiceUtil.serviceIp(services[0])
		assert "10.0.0.85" == ServiceUtil.serviceIp(services[1])
	}

}
