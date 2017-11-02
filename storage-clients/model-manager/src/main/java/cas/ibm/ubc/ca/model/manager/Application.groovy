package cas.ibm.ubc.ca.model.manager

import java.util.List

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment

class Application {

	public static void main(String[] args) {
		ModelManagerConfig config = new ModelManagerConfig()
		
		ReificationInterface managedCluster = [
				move: { 
					println "MOVING >>> " + it
					true
				}
			] as ReificationInterface
		
		
		ModelManager manager = new ModelManager(config, managedCluster)
		
		manager.start()
	}
	
}
