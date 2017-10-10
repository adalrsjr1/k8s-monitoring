package test.cas.ibm.ubc.ca.k8s

import static org.junit.Assert.*

import cas.ibm.ubc.ca.k8s.KubernetesInspection

class TestKubernetesInspection extends GroovyTestCase {
	static KubernetesInspection inspection 
	void setUp() {
		inspection = new KubernetesInspection()
	}
	
	public void testMergeServiceInstance() {
		Map service = [
				name: "testService",
				application:"app1",
				selector:[sel1:"selector1"]
			]
		
		Map instance1 = [
				application: "app1",
				labels: [sel1:"selector1"]
			]
			
		Map instance2 = [
				application: "app1",
				labels: [sel1:"selector2"]
			]
		
		assert inspection.mergeServiceInstance(service, instance1) ==
				[name:"testService",application:"app1",
					selector:[sel1:"selector1"], labels:[sel1:"selector1"]]
				
		assert inspection.mergeServiceInstance(service, instance2) !=
				[name:"testService",application:"app1",
					selector:[sel1:"selector1"], labels:[sel1:"selector1"]]
			
	}

}
