package cas.ibm.ubc.ca.k8s

import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface

class ClusterInspectionInterfaceFactory {

	static ClusterInspectionInterface create(url, timeout) {
		KubernetesInspection kubernetesClient = new KubernetesInspection("http://"+url, Integer.parseInt(timeout))
		return kubernetesClient
	}
	
}
