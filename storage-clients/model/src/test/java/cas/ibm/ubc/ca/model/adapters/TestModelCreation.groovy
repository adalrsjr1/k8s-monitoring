package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import org.eclipse.emf.ecore.util.EcoreUtil

import com.google.gson.Gson
import com.google.gson.stream.JsonReader

import model.Application
import model.Cluster
import model.Environment
import model.ModelFactory
import model.Service
import org.junit.Test

class TestModelCreation extends GroovyTestCase {

	List services
	List hosts
	List applications
	String environment = "KUBERNETES"
	
	void setUp() {
		ClassLoader cl = this.class.classLoader
		Gson gson = new Gson()
		
		JsonReader jsonReader 
		
		jsonReader = new JsonReader(new FileReader(new File(cl.getResource("services.json").getFile())))
		services = gson.fromJson(jsonReader, List.class)
		
		jsonReader = new JsonReader(new FileReader(new File(cl.getResource("hosts.json").getFile())))
		hosts = gson.fromJson(jsonReader, List.class)
		
		jsonReader = new JsonReader(new FileReader(new File(cl.getResource("applications.json").getFile())))
		applications = gson.fromJson(jsonReader, List.class)
	}
	
	void test() {
		ModelFactory factory = ModelFactoryAdapter.INSTANCE

		Cluster cluster = factory.createCluster()
		cluster.environment = Environment.getByName(environment)

		assert cluster != null
		assert cluster.environment == Environment.KUBERNETES
		
		applications.each { item ->
			Application app = factory.createApplication()
			app.name = item
						
			cluster.applications[item] = app
			
			services.each { s ->
				if( s.application == item ) {
					Service service = factory.createServiceInstance()
					service.name = s.name
					service.id = s.uid
					service.address = s.address
					app.services[s.name] = service
				}	
			}
		}
		
	}

}
