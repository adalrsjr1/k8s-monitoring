package cas.ibm.ubc.ca.model.manager

import java.util.concurrent.TimeUnit

import org.eclipse.emf.common.util.URI as EmfURI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.common.base.Stopwatch

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import groovy.transform.Synchronized
import model.Application
import model.Cluster
import model.ElementWithResources
import model.Environment
import model.Message
import model.Host
import model.Service
import model.ServiceInstance

class ModelHandler implements ReificationInterface {
	private static Logger LOG = LoggerFactory.getLogger(ModelHandler.class)
	private static final ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	private static Long version = 1
		
	private final ResourceSet resourceSet
	
	private String modelStoragePath
	private Resource resource
	
	public Cluster cluster

	
	public ModelHandler(String modelStoragePath) {
		this.modelStoragePath = modelStoragePath
		
		resourceSet = getXmiResourceSet()
		resource = createResource(modelStoragePath, "${version++}", resourceSet)
	}
	
	public Cluster getCluster() {
		cluster
	}
	
	private ResourceSet getXmiResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		return resSet
	}
	
	private Resource createResource(String url, String version, ResourceSet resourceSet) {
		String filename = System.currentTimeMillis().toString()
		EmfURI emfURI = EmfURI.createURI(url + filename + "_" + version + ".xmi")
		Resource resource = resourceSet.createResource(emfURI)
		return resource
	}
	
	private void createMessages(Cluster cluster, Map services, List messages) {
		messages.each { m ->
			Message msg = factory.createMessage()
			msg.avgResponseTime = m["totalTime"]
			msg.source = services[m["sourceName"]]
			msg.destination = services[m["targetName"]]
			msg.messageSize = m["totalSize"]
			msg.name = ""
			msg.timestamp = m["timestamp"]
			msg.uid = m["correlationId"]
		}
	}
	
	private void createMetric(ElementWithResources element, String id, List keys, List<Map> metrics) {
		
		Iterator keyIt = keys.iterator()
		Iterator metricsIt = metrics.iterator()
		
		while(keyIt.hasNext() && metricsIt.hasNext()) {
			def key = keyIt.next()
			def metric = metricsIt.next()
			element.metrics[key] = (Double) metric[id]
		}
		
	}
	
	private void createMetrics(Cluster cluster, List<String> keys, List<Map<String, Double>> metrics) {
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		for(def eObject in iterator) {
			if(eObject instanceof ServiceInstance) {
				ServiceInstance service = eObject
				
				createMetric(service, service.id, keys, metrics)
			}
			else if(eObject instanceof Host) {
				Host host = eObject
				
				createMetric(host, host.name, keys, metrics)
			}
		}
	}
	
	private Map createServices(Cluster cluster, List services) {
		Map modelServices = [:]
		services.each { s ->
			Service service = factory.createServiceInstance()
			service.name = s.name
			service.id = s.uid
			service.address = s.address
			service.application = s.application
			
			service.containers.addAll(s.containers)
			
			if(resource) {
				resource.getContents().add(service)
			}
			
			if(cluster.applications.containsKey(service.application)) {
				cluster.applications[s.application].services[service.id] = service
			}
			
			cluster.hosts.values().find { h ->
				h.hostAddress.contains(s.hostAddress)
			}.services[service.id] = service
			
			modelServices[service.id] = service
		}
		return modelServices
	}
	
	private void createApplications(Cluster cluster, Map applications) {
		applications.each { k, v ->
			Application app = factory.createApplication()
			app.name = k
			app.weight = v
			
			if(resource) {
				resource.getContents().add(app)
			}
			cluster.applications[(k)] = app			
		}
	}
	
	private void createHosts(Cluster cluster, List hosts) {
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
	
	private void fillModel(Cluster cluster, String environment, List hosts, Map applications,
		List services, List messages, List metricsKeys, List metrics) {
		createApplications(cluster, applications)
		createHosts(cluster, hosts)
		Map modelServices = createServices(cluster, services)
		createMessages(cluster, modelServices, messages)
		createMetrics(cluster, metricsKeys, metrics)
	}
	
	
	
	@Synchronized	
	public Cluster updateModel(String version, String environment, List hosts, List applications,
		List services, List messages, List metrics) {
	
		if(resource) {
			saveModel()
			destroyResource()
		}
		createResource(modelStoragePath, version, resourceSet)
		
		cluster = createCluster(environment)
		fillModel(cluster, environment, hosts, applications, 
			services, messages, metrics)
		
		metrics.each { m ->
			createMetrics(cluster, m)
		}
		
		return cluster
	}
	
	private void destroyResource() {
		resourceSet.getResources().remove(resource)
	}
	
	public void saveModel() {
		try {
			if(resource) {
				Stopwatch watcher = Stopwatch.createStarted()
				LOG.info ("Saving model...")
				resource.save(Collections.EMPTY_MAP)
				LOG.info ("The model was save [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms.")
				watcher.stop()
				return
			}
			LOG.info ("The model can't be saved. The resource wasn't created.")
		}
		catch(IOException e) {
			LOG.info ("The model wasn't save.")
			LOG.error("The current model cannot be saved. Caused by {}", e.message)
			throw new RuntimeException(e)
		}
	}

	@Override
	public boolean move(List<Moviment> adaptationScript) {
		adaptationScript.each {
			move(it)
		}
	}

	@Override
	public boolean move(Moviment moviment) {
		LOG.trace ("moving [{}] ...", moviment.toString())
		return false;
	}
	
}
