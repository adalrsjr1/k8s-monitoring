package cas.ibm.ubc.ca.model

import org.eclipse.emf.common.util.URI as EmfURI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.emfjson.jackson.resource.JsonResourceFactory

import cas.ibm.ubc.ca.model.adapters.ClusterAdapter
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.Message
import model.ModelFactory
import model.ServiceInstance

class HelloWorld {

	public static ResourceSet getJsonResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("*", new JsonResourceFactory());
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		return resSet
	}

	public static ResourceSet getXmiResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("*", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		return resSet
	}

	// Json
	public static void main(String[] args) {
		//				ModelFactory factory = ModelFactory.INSTANCE
		ModelFactory factory = ModelFactoryAdapter.INSTANCE

		Cluster cluster = factory.createCluster()
		//		ElementObserver observer = new ElementObserver(cluster)
		//		TotalObserver observer = new TotalObserver(cluster)
		cluster.setEnvironment(Environment.KUBERNETES)

		Application application1 = factory.createApplication()
		application1.setName("Application1")
		
		
		cluster.getApplications()["Application1"] = application1

		Application application2 = factory.createApplication()
		application2.setName("Application2")

		cluster.applications["Application2"] = application2

		application2.setName("Application21")

		ServiceInstance service1 = factory.createServiceInstance()
		service1.setName("service1")
		service1.setAddress("1.1.1.1")
		service1.setId("0123")
		service1.setPort(80)

		ServiceInstance service2 = factory.createServiceInstance()
		service2.setName("service2")
		service2.setAddress("0.0.0.0")
		service2.setId("0123")
		service2.setPort(80)

		application2.getServices().add(service1)
		application2.getServices().add(service2)

		Message message = factory.createMessage()
		message.setDestination(service2)
		message.setSource(service1)

		service1.getMessages().add(message)


		Host host = factory.createHost()
		cluster.getHosts().add(host)
		host.setName("host1")

		host.metrics["cpu"] = 100L
		host.metrics["cpu"] = 90L


		host.getServices().add(service1)
		host.getServices().add(service2)

		ResourceSet rs = getJsonResourceSet()
		//		ResourceSet rs = getXmiResourceSet()

		EmfURI emfURI = EmfURI.createURI("src/main/resources/model/model.json")
		Resource resource = rs.createResource(emfURI)

		//		resource.getContents() << cluster.getECluster()

		//		resource.save(null)

		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(cluster)
		resource.getContents().add(application1);
		resource.getContents().add(application2);

		resource.getContents().add(service1)
		resource.getContents().add(service2)

//		// now save the content.
//		try {
//			resource.save(Collections.EMPTY_MAP);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ClusterAdapter.move(cluster, "application1", "service1", "host1", "host2")
	}

	// PLAIN
	//	static void main(String[] args) {
	//		//		ModelFactory factory = ModelFactory.INSTANCE
	//		ModelFactory factory = ModelFactoryAdapter.INSTANCE
	//
	//		Cluster cluster = factory.createCluster()
	//
	//		//		ElementObserver observer = new ElementObserver(cluster)
	//		//		TotalObserver observer = new TotalObserver(cluster)
	//
	//		Application application1 = factory.createApplication()
	//		application1.setName("Application1")
	//		cluster.applications << application1
	//
	//		Application application2 = factory.createApplication()
	//		application2.setName("Application2")
	//
	//		cluster.applications << application2
	//
	//		application2.setName("Application3")
	//
	//		Host host = factory.createHost()
	//		cluster.hosts << host
	//		host.setName("host1")
	//
	//		host.metrics["cpu"] = 100L
	//		host.metrics["cpu"] = 90L
	//
	//	}


}
