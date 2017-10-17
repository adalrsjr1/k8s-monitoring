package cas.ibm.ubc.ca.model.manager.analyzer

import model.Cluster
import model.Message
import model.Service
import model.ServiceInstance
import model.impl.ModelPackageImpl

import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.Notifier
import org.eclipse.emf.common.notify.impl.AdapterImpl
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.util.EcoreUtil

import cas.ibm.ubc.ca.model.manager.ModelHandler

class AffinitiesAnalyzer {

	ModelHandler modelHandler
	
	AffinitiesAnalyzer(ModelHandler modelHandler) {
		this.modelHandler = modelHandler
	}

	private getAllServices(Cluster cluster) {
		List<ServiceInstance> services = []
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		while(iterator.hasNext()) {
			def object = iterator.next()
			if(object instanceof ServiceInstance) {
				services << object
			}
		}
		
		return services
	}
	
	void calculate() {
		Cluster cluster = modelHandler.cluster

		
	}	
	
}
