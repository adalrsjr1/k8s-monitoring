package cas.ibm.ubc.ca.graphql.model

import cas.ibm.ubc.ca.k8s.K8sClient
import cas.ibm.ubc.ca.k8s.K8sSession

class ModelSession {
	private static ModelSession instance 
	
	K8sSession k8sSession  
	
	private ModelSession() {
		k8sSession = K8sClient.session()
	}
	
	static ModelSession getInstance() {
		if(Objects.isNull(instance)) {
			instance = new ModelSession()
		}
		instance
	}
	
	void refresh() {
		k8sSession = k8sClient.session()
	}
}
