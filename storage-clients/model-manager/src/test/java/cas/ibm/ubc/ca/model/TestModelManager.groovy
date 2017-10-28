package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import org.junit.Test

import cas.ibm.ubc.ca.model.manager.ModelManager
import cas.ibm.ubc.ca.model.manager.ModelManagerConfig

class TestModelManager {

	void testMonitoringCconfig() {
		ModelManagerConfig config = new ModelManagerConfig()
		ModelManager manager = new ModelManager(config)
		
		assert manager.monitoring
		assert manager.monitoring.clusterMonitor
		assert manager.monitoring.messagesMonitor
		assert manager.monitoring.metricsMonitor
	}
	
	void testModelManagerConfig() {
		ModelManagerConfig config = new ModelManagerConfig()

		assert config.properties
		assert config.properties.isEmpty()
		assert config.get("modelmanager.model.storage")
		assert config.get("modelmanager.monitoring.interval")
				
	}
	
	void test() {
		ModelManagerConfig config = new ModelManagerConfig()
		ModelManager manager = new ModelManager(config)
		manager.start()
	}
	
}
