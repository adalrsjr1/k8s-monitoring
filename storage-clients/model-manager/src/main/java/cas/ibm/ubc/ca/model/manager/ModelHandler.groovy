package cas.ibm.ubc.ca.model.manager

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

import org.eclipse.emf.common.util.URI as EmfURI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import com.google.common.base.Stopwatch

import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import model.Application
import model.Cluster
import model.ElementWithResources
import model.Environment
import model.Host
import model.Message
import model.Service
import model.ServiceInstance

class ModelHandler {
	private static Logger LOG = LoggerFactory.getLogger(ModelHandler.class)
	private static final ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	private static Long version = 1

	private final ResourceSet resourceSet

	private String modelStoragePath
	private Resource resource

	private final Collection history = new TreeSet()

	public Cluster cluster

	public ModelHandler(String modelStoragePath) {
		this.modelStoragePath = modelStoragePath
		resourceSet = getXmiResourceSet()
		resource = createResource(modelStoragePath, "${version++}", resourceSet)
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

	public Resource getResource() {
		return resource
	}

	public Cluster getOrCreateCluster(String environment) {
		if(cluster) {
			return cluster
		}
		cluster = createCluster(environment)
		return cluster
	}
	
	public Cluster getCluster() {
		return cluster
	}

	private void createMessages(Cluster cluster, Map services, List messages) {
		int threads = Runtime.getRuntime().availableProcessors();
		ExecutorService tPool = Executors.newFixedThreadPool(threads)
		
		def tasks = messages.size()
		int chunckSize = (tasks + threads - 1 ) / threads
		AtomicInteger counter = new AtomicInteger()
		CountDownLatch latch = new CountDownLatch(threads)

		for(int i = 0; i < threads; i++) {
			int start = i * chunckSize
			int end = Math.min(start + chunckSize, tasks)
			tPool.execute({
				for(int j = start; j < end; j++) {
					def m
					synchronized(messages) {
						// DO NOT ACCESS BY INDEX
						// IT IS A LINKED LIST! O(n) TO ACCESS ANY ELEMENT
						m = messages.remove(0)
					}

					ServiceInstance s = services[m["sourceName"]]
					Message msg
					synchronized (s) {
						msg = factory.createMessage(resource)
						s.messages.add(0, msg)
						msg.source = s
					}

					msg.avgResponseTime = m["totalTime"]
					def destination = services[m["targetName"]]
					msg.destination = destination == null || destination == "" ? s : destination
					msg.messageSize = m["totalSize"]
					msg.name = ""
					msg.timestamp = m["timestamp"]
					msg.uid = m["correlationId"]

					counter.incrementAndGet()
				}
				latch.countDown()
			})
		}

		
		
		latch.await()
		tPool.shutdown();
		LOG.info "Number of messages {}" , counter.get()
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
			Service service = factory.createServiceInstance(getResource())

			service.name = s.name
			service.id = s.uid
			service.address = s.address
			service.application = s.application

			service.containers.addAll(s.containers)

			if(cluster.applications.containsKey(service.application)) {
				cluster.applications[s.application].services[service.id] = service
			}

			cluster.hosts.values().find { h ->
				h.hostAddress.contains(s.hostAddress)
			}.services[service.id] = service

			modelServices[service.id] = service
			modelServices[service.name] = service
		}
		return modelServices
	}

	private void createApplications(Cluster cluster, Map applications) {
		applications.each { k, v ->
			Application app = factory.createApplication(getResource())
			app.name = k
			app.weight = v

			cluster.applications[(k)] = app
		}
	}

	private void createHosts(Cluster cluster, List hosts) {
		hosts.each { item ->
			Host host = factory.createHost(getResource())
			host.name = item.name
			item.hostAddress.each {
				host.hostAddress << it
			}

			cluster.hosts[item.name] = host
		}
	}

	private Cluster createCluster(String environment) {
		Cluster cluster = factory.createCluster(getResource())
		cluster.environment = Environment.getByName(environment)

		return cluster
	}

	private void fillModel(Cluster cluster, String environment, List hosts, Map applications,
			List services, List messages, List metricsKeys, List metrics) {

		Stopwatch watch = Stopwatch.createStarted()

		createApplications(cluster, applications)
		createHosts(cluster, hosts)
		Map modelServices = createServices(cluster, services)
		createMetrics(cluster, metricsKeys, metrics)

		createMessages(cluster, modelServices, messages)
		LOG.info("Model created in {} ms", messages.size(), watch.elapsed(TimeUnit.MILLISECONDS))
	}
	
	public Cluster updateModelFromAsyncMonitoring(String version, Future<String> environment, 
		Future<List> hosts, Future<Map> applications, Future<List> services, Future<List> messages, 
		List metricsKeys, Future<List> metrics) {
		
		while(!environment.isDone() && !hosts.isDone() && !applications.isDone() &&
			!services.isDone() && !messages.isDone() && !metrics.isDone()) {
			
			environment.isDone()	?:  LOG.info ("Loading environment...")
			hosts.isDone()			?:  LOG.info ("Loading hosts...")
			applications.isDone()	?:  LOG.info ("Loading applications...")
			services.isDone() 		?:  LOG.info ("Loading services...")
			messages.isDone() 		?:  LOG.info ("Loading messages...")
			metrics.isDone()		?:  LOG.info ("Loading metrics...")
			
			Thread.sleep(100)
		}
		
		return updateModel(version, environment, hosts, applications, services, messages,
			metricsKeys, metrics)
		
	}

	public Cluster updateModel(String version, String environment, List hosts, Map applications,
			List services, List messages, List metricsKeys, List metrics) {

		if(resource) {
			String lastVersion = saveModel()
			history.add(lastVersion)

			destroyResource()
		}
		createResource(modelStoragePath, version, resourceSet)

		cluster = createCluster(environment)
		fillModel(cluster, environment, hosts, applications,
				services, messages, metricsKeys, metrics)

		return cluster
	}

	private void destroyResource() {
		resourceSet.getResources().remove(resource)
	}

	public String saveModel() {
		try {
			if(resource) {
				Stopwatch watcher = Stopwatch.createStarted()
				LOG.info ("Saving model...")
				resource.save(Collections.EMPTY_MAP)
				LOG.info ("The model was save [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms.")
				watcher.stop()
				return resource.getURI().toString()
			}
			LOG.info ("The model can't be saved. The resource wasn't created.")

		}
		catch(IOException e) {
			LOG.info ("The model wasn't save.")
			LOG.error("The current model cannot be saved. Caused by {}", e.message)
			throw new RuntimeException(e)
		}
		return ""
	}

	public Cluster loadModel(String url) {
		Resource resource
		try {
			ResourceSet rs = getXmiResourceSet()
			EmfURI emfURI = EmfURI.createURI(url)
			Stopwatch watcher = Stopwatch.createStarted()
			LOG.info "Loading model..."
			resource = rs.getResource(emfURI, true)
			LOG.info ("The model was save [${watcher.elapsed(TimeUnit.MILLISECONDS)}] ms.")
		}
		catch(IOException e) {
			LOG.info ("The model wasn't loaded.")
			LOG.error("The current model cannot be loaded. Caused by {}", e.message)
			throw new RuntimeException(e)
		}
		return resource.getContents().get(0)
	}

	public Collection getHistory() {
		return history
	}

	public boolean moveOnModel(Moviment moviment) {
		if(moviment == Moviment.nonMove()) {
			return true
		}
		
		Cluster cluster = getCluster()
		try {
			String app = moviment.application
			String svc = moviment.service
			String src = moviment.hostSource
			String dst = moviment.hostDestination
			
			Host srcHost = cluster.hosts[src]
			Host dstHost = cluster.hosts[dst]
			
			ServiceInstance service = cluster.applications[app].services.values().find {
				it.name == svc || it.id == svc
			}
			
			//TODO: there is a bug on model.xmi update
			// it is mantaining two "intances" at same time
			// can arise ERRORS when loading 
				
			dstHost.services[service.id] = service
			service.setHost(dstHost)
			srcHost.services.remove(svc)
			
			return true
		}
		catch(Exception e) {
			LOG.warn "The model can't be udpated with the moving: {}", moviment
			LOG.error e.message
			return false
		}
	}
	
	public boolean undoMoveOnModel(Moviment moviment) {
		LOG.warn "Reverting model to: {}", moviment
		Moviment undo = moviment.createUndoMoviment()
		return moveOnModel(undo)
	}
}
