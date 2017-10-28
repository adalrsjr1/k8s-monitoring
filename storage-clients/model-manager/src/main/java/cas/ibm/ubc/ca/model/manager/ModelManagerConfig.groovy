package cas.ibm.ubc.ca.model.manager

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ModelManagerConfig {
	
	private static Logger LOG = LoggerFactory.getLogger(ModelManagerConfig.class)
	
	private Properties properties

	public ModelManagerConfig() {
		properties = loadProperties()
	}
	
	private static Properties loadProperties() {
		ClassLoader cl = ModelManagerConfig.class.getClassLoader()
		
		InputStream inputStream
		Properties properties = new Properties()
		
		try {
			inputStream = cl.getResourceAsStream("model-manager.properties")
			properties.load(inputStream)
		}
		catch(IOException e) {
			LOG.error(e.getMessage())
			throw new RuntimeException(e);
		}
		finally {
			inputStream?.close()
		}
		
		return properties
	}
		
	public String get(String key) {
		return properties.get(key)
	}	
}
