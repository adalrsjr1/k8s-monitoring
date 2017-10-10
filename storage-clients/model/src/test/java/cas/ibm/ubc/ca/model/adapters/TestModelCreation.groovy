package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import com.google.gson.Gson
import com.google.gson.stream.JsonReader

import org.junit.Test

class TestModelCreation extends GroovyTestCase {

	List services
	List hosts
	List applications
	
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
		
	}

}
