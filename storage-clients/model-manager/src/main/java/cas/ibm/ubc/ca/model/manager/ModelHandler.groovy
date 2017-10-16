package cas.ibm.ubc.ca.model.manager

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.eclipse.emf.common.util.URI as EmfURI
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.model.adapters.ClusterAdapter
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import groovy.transform.Synchronized
import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.Service

class ModelHandler {
	private static Logger LOG = LoggerFactory.getLogger(ModelHandler.class)
	
	private final ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	private final ResourceSet resourceSet
	
	private String modelStoragePath = "src/main/resources/"
	private Resource resource
	
	Cluster cluster

	ModelHandler(String modelStoragePath) {
		this.modelStoragePath = modelStoragePath
		
		resourceSet = getXmiResourceSet()
		resource = createResource(modelStoragePath, resourceSet)
	}
	
	private ResourceSet getXmiResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		return resSet
	}
	
	private Resource createResource() {
		String filename = System.currentTimeMillis().toString()
		EmfURI emfURI = EmfURI.createURI(url + filename + ".xmi")
		Resource resource = resourceSet.createResource(emfURI)
		return resource
	}
	
	private void createMessages(List messages) {
		LOG.warn("Messages can't be created yet. Implement this!")
	}
	
	private void createMetrics(List metrics) {
		LOG.warn("Messages can't be created yet. Implement this!")
	}
	
	private void createServices(List services) {
		services.each { s ->
			Service service = factory.createServiceInstance()
			service.name = s.name
			service.id = s.uid
			service.address = s.address
			service.hostAddress = s.hostAddress
			service.application = s.application
			
			service.containers.addAll(s.containers)
			
			if(resource) {
				resource.getContents().add(service)
			}
			
			if(cluster.applications.containsKey(service.application)) {
				cluster.applications[s.application].services[service.id] = service
			}
			
			cluster.hosts.values().find { h ->
				h.hostAddress.contains(service.hostAddress)
			}.services[service.id] = service
			
		}
	}
	
	private void createApplications(List applications) {
		applications.each { item ->
			Application app = factory.createApplication()
			app.name = item
			
			if(resource) {
				resource.getContents().add(app)
			}
			cluster.applications[(item)] = app			
		}
	}
	
	private void createHosts(List hosts) {
		hosts.each { item ->
			Host host = factory.createHost()
			host.name = item.name
			item.hostAddress.each {
				host.hostAddress << it
			}
			
			if(resource) {
				resource.getContents().add(host)
			}
			
			cluster.hosts[item.name] = host
		}
	}
	
	private Cluster createCluster(String environment) {
		Cluster cluster = factory.createCluster()
		cluster.environment = Environment.getByName(environment)
		
		if(resource) {
			resource.getContents().add(cluster)
		}
		
		return cluster
	}
	
	@Synchronized	
	Cluster updateModel(String environment, List hosts, List applications,
		List services, List metrics, List messages) {
	
		if(resource) {
			saveModel()
			destroyResource()
		}
		createResource()
		
		createCluster(environment)
		createApplications(applications)
		createHosts(hosts)
		createServices(services)
		createMetrics(metrics)
		createMessages(messages)
		
		return cluster
	}
	
	List getMessages(String application) {
		// messages
	}
	
	List getAllMessages() {
		// all messages
	}
	
	List getAffinities(String service) {
		// affinities
	}
	
	List getAllAffinities() {
		// all affinities
	}
	
	void setAffinities(List affinities) {
		// affinities
	}

	private void destroyResource() {
		resourceSet.getResources().remove(resource)
	}
	
	void saveModel() {
		try {
			if(resource) {
				LOG.info ("Saving model...")
				resource.save(Collections.EMPTY_MAP)
				LOG.info ("The model was save.")
				return
			}
			LOG.info ("The model can't be saved. There was any resource created.")
		}
		catch(IOException e) {
			LOG.info ("The model wasn't save.")
			LOG.error("The current model cannot be saved. Caused by {}", e.message)
			throw new RuntimeException(e)
		}
	}
	
}
