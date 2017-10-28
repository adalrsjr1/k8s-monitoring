package cas.ibm.ubc.ca.k8s

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface

class ClusterInspectionInterfaceFactory {

	static ClusterInspectionInterface create(String url, int timeout) {
		KubernetesInspection kubernetesClient = new KubernetesInspection(url, timeout)
		return kubernetesClient
	}
	
}
