package cas.ibm.ubc.ca.model.manager.planner

import org.eclipse.emf.ecore.util.EcoreUtil

import model.Affinity
import model.Cluster
import model.Host
import model.ServiceInstance

class AdaptationPlanner {

	public AdaptationPlanner() { }
	
	Boolean checkingMove(Affinity affinity, List hosts) {
		
	}
	
	void calculate(Cluster cluster) {
		
		List affinities = []
		List hosts = []
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		while( iterator.hasNext() ) {
			def obj = iterator.next()

			if(Affinity.isInstance(obj)) {
				affinities << obj
			}
			else if(Host.isInstance(obj)) {
				hosts << obj
			}
		}
		
		for(Affinity affinity in affinities) {
			
			
			
		}
		
	}
	
}
