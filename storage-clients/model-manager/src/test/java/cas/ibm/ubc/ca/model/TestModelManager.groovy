package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import org.junit.Test

import cas.ibm.ubc.ca.model.manager.ModelManager
import cas.ibm.ubc.ca.model.manager.ModelManagerConfig

class TestModelManager extends TestMonitoringBase {

	void test() {
		ModelManagerConfig config = new ModelManagerConfig()
		ModelManager manager = new ModelManager(config)
		manager.start()
	}
	
}
