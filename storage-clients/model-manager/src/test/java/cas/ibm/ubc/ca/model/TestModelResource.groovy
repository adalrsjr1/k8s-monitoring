package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.model.manager.ModelHandler

class TestModelResource extends GroovyTestCase {

	private static final String PATH = "/home/adalrsjr1/Code/ibm-stack/storage-clients/model-manager/src/test/resources/"
	
	void test() {
		ModelHandler handler = new ModelHandler(PATH)

		assert handler.resourceSet
		assert handler.resource		
	}
	
	void testSaveResource() {
		ModelHandler handler = new ModelHandler(PATH)

		assert handler.resource
		handler.createCluster("")
		
		String name = handler.saveModel()
		
		assert new File(name).exists()
	}
	
	void tearDown() {
		File f = new File(PATH)
		
		if(f.isDirectory()) {
			f.listFiles().each {
				if(it.name.contains(".xmi"))
					it.delete()
			}
		}
	}
	
}
