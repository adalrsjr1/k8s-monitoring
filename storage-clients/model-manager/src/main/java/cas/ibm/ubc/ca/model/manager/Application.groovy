package cas.ibm.ubc.ca.model.manager

class Application {

	public static void main(String[] args) {
		ModelManagerConfig config = new ModelManagerConfig()
		ModelManager manager = new ModelManager(config)
		
		manager.start()
	}
	
}
