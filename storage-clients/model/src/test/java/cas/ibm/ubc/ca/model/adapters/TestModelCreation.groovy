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
import model.Message
import model.ModelFactory
import model.Service
import model.ServiceInstance

class TestModelCreation extends GroovyTestCase {

	List services
	List hosts
	Map applications
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
		applications = gson.fromJson(jsonReader, Map.class)
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
		
		applications.each { k,v ->
			Application app = factory.createApplication()
			app.name = k
			app.weight = v
			
			// always first to add to resource 
			resource.getContents().add(app)
			cluster.applications[(k)] = app
			
		}
		
		Map result = cluster.applications.inject([:]) { result, app ->
			result[app.key] = app.value.weight
			result
		}

		Map expected = ["default":1.0f,"kube-public":1.0f,"kube-system":0.5f,"sock-shop":0.33333f,"zipkin":0.635f]
		assert result == expected
		
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
			service.application = s.application
			
			service.containers.addAll(s.containers)
			
			resource.getContents().add(service)
			
			if(cluster.applications.containsKey(service.application)) {
				cluster.applications[s.application].services[service.id] = service
			}
			
			assert cluster.applications[s.application].services[service.id] != null
		}
		
		resource.save(Collections.EMPTY_MAP)
	}
	
	void testRandomGeneration() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE
		Cluster cluster = factory.createCluster()

		Application app = factory.createApplication()
		
		cluster.applications["app1"] = app

		int c = 5
		
		for(i in (1..c)) {
			ServiceInstance s = factory.createServiceInstance()
			String svc = "svc${i}"
			s.name = svc
			
			println ">>> $svc"
			
			cluster.applications["app1"].services[svc] = s
		}
		assert cluster.applications["app1"].services.size() == c 
		
		Map svcs = cluster.applications["app1"].services
				
		for(j in (1..(c*2))) {
			Message m = factory.createMessage()
			
			String src = "svc${(j%c)+1}"
			String dst = "svc${((j+1)%c)+1}"
			
			m.setSource(svcs[src])
			m.setDestination(svcs[dst])
			
			println "::: $src --> $dst"
			m.setMessageSize(1)

			svcs[src].messages << m
		}

		assert app.totalMessages == c*2
		assert app.totalData == c*2
	}
	
	void testAutomaticallySetHostToService() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE
		
		Host host1 = factory.createHost()
		Host host2 = factory.createHost()
		
		ServiceInstance svc1 = factory.createServiceInstance()
		ServiceInstance svc2 = factory.createServiceInstance()
		
		host1.services["svc1"] = svc1
		assert svc1.host == host1
		host2.services["svc2"] = svc2
		assert svc2.host == host2
		
		host1.services["svc2"] = host2.services.remove("svc2")
		assert svc2.host == host1
		host2.services["svc1"] = host1.services.remove("svc1")
		assert svc1.host == host2
		
		assert host1.services.keySet().contains("svc2") == true
		assert host1.services.keySet().contains("svc1") == false
		assert host2.services.keySet().contains("svc2") == false
		assert host2.services.keySet().contains("svc1") == true
	}
	
	void testManyHostsServices() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE

		Cluster cluster = factory.createCluster()
				
		Host host1 = factory.createHost()
		host1.name = "host1"
		host1.hostAddress << "ip1"
		Host host2 = factory.createHost()
		host2.name = "host2"
		host2.hostAddress << "ip2"
		
		cluster.hosts["host1"] = host1
		cluster.hosts["host2"] = host2
		
		ServiceInstance svc1 = factory.createServiceInstance()
		svc1.name = "svc1"
		assert svc1.name == "svc1"
		ServiceInstance svc2 = factory.createServiceInstance()
		svc2.name = "svc2"
		assert svc2.name == "svc2"
		
		cluster.hosts.values().find { h ->
			h.hostAddress.contains("ip1")
		}.services[(svc1.name)] = svc1
		
		cluster.hosts.values().find { h ->
			h.hostAddress.contains("ip2")
		}.services[(svc2.name)] = svc2
		
		
		assert svc1.host == host1
		assert host1.services["svc1"] == svc1
		assert svc2.host == host2
		assert host2.services["svc2"] == svc2
	}

}
