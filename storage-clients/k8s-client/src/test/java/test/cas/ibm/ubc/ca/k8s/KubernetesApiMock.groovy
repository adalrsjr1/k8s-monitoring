package test.cas.ibm.ubc.ca.k8s

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import cas.ibm.ubc.ca.k8s.ClusterInspectionInterfaceFactory
import cas.ibm.ubc.ca.k8s.KubernetesInspection
import groovy.json.JsonSlurper

class KubernetesApiMock extends GroovyTestCase {

	void test() {}	
	
	void testParseLimits() {
		def k = new KubernetesInspection("",0)
		def result = k.parseLimits(["memory":"4046492Ki"])
		assert result == ["memory":(Double)4046492]
	}
}
