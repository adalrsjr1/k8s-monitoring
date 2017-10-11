package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.common.util.URI as EmfURI

import com.google.gson.Gson
import com.google.gson.stream.JsonReader

import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.ModelFactory
import model.Service
import model.ServiceInstance

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
	
	ResourceSet getXmiResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		return resSet
	}
	
	void test() {
		ResourceSet rs = getXmiResourceSet()
		EmfURI emfURI = EmfURI.createURI("src/test/resources/model.xmi")
		Resource resource = rs.createResource(emfURI)
		
		ModelFactory factory = ModelFactoryAdapter.INSTANCE

		Cluster cluster = factory.createCluster()
		cluster.environment = Environment.getByName(environment)

		assert cluster != null
		assert cluster.environment == Environment.KUBERNETES
		
		resource.getContents().add(cluster)
		
		applications.each { item ->
			Application app = factory.createApplication()
			app.name = item
			
			// always first to add to resource 
			resource.getContents().add(app)
			cluster.applications[(item)] = app
			
		}
		
		assert cluster.applications.collect { it.value.name } == applications
		
		hosts.each { item ->
			Host host = factory.createHost()
			host.name = item.name
			item.hostAddress.each {
				host.hostAddress << it
			}
			assert host.hostAddress.size == 2
			
			resource.getContents().add(host)
			cluster.hosts[item.name] = host
			
		}
		
		assert cluster.hosts.collect { it.value.name } == hosts.collect { it.name }

		services.each { s ->
			Service service = factory.createServiceInstance()
			service.name = s.name
			service.id = s.uid
			service.address = s.address
			service.hostAddress = s.hostAddress
			service.application = s.application
			
			service.containers.addAll(s.containers)
			
			resource.getContents().add(service)
			
			if(cluster.applications.containsKey(service.application)) {
				cluster.applications[s.application].services[service.id] = service
			}
			
			assert cluster.applications[s.application].services[service.id] != null
			
			
			
			cluster.hosts.values().find { h ->
				h.hostAddress.contains(service.hostAddress)				
			}.services[service.id] = service
			
			assert cluster.hosts.values().find { h ->
				h.hostAddress.contains(service.hostAddress)				
			}.services[service.id] != null
			
		}
		
		resource.save(Collections.EMPTY_MAP)
	}

}
