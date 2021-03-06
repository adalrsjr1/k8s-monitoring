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

import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement
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

	private Integer createMessages(Cluster cluster, Map services, List messages) {
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
					try {
						def m
						synchronized(messages) {
							// DO NOT ACCESS BY INDEX
							// IT IS A LINKED LIST! O(n) TO ACCESS ANY ELEMENT
							m = messages.remove(0)
						}

						ServiceInstance s = services[m["sourceName"]]
						Message msg
						synchronized (s.messages) {
							msg = factory.createMessage(resource)

							s.messages.push(msg)

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
					catch(ArrayIndexOutOfBoundsException e) {
						LOG.warn(e.getCause().toString())
						continue
					}
				}
				latch.countDown()
			})
		}



		latch.await()
		tPool.shutdown();
		return counter.get()
	}

	/* // measurements
	 * cpu/limit
	 * cpu/node_allocatable
	 * cpu/node_capacity
	 * cpu/node_reservation
	 * cpu/node_utilization
	 * cpu/request
	 * cpu/usage
	 * cpu/usage_rate
	 * memory/cache
	 * memory/limit
	 * memory/node_allocatable
	 * memory/node_capacity
	 * memory/node_reservation
	 * memory/node_utilization
	 * memory/request
	 * memory/usage
	 */
	private String keyCpuMemory(String tag) {
		if(tag.contains("cpu"))
			return "cpu"
		if(tag.contains("memory"))
			return "memory"
		return tag
	}

	private void createVoidMetric(ElementWithResources element, String id, List<Measurement> keys) {
		createMetric(element, id, keys, [[:],[:]])
	}

	private void createHostMetric(ElementWithResources element, String id, Measurement key, Map metric) {
		Host host = element
		host.metrics[key.name] = (Double) metric[id] ?: 0
	}

	private Double transformCpuValue(Integer cores, Double cpu) {
		//		if(cpu == null)
		//			return 0
		//		def value = cpu * 100 / cores
		//		if(value <= 100) {
		//			return value
		//		}
		//		return 100
		return Math.round(cpu ?: 0)
	}

	private void createServiceMetric(ElementWithResources element, String id, Measurement key, Map metric) {
		ServiceInstance svc = element

		if(key.name == "cpu") {
			svc.metrics[key.name] = transformCpuValue(svc.host.cores, metric[id])
		}
		else if (key.name == "memory") {
			svc.metrics[key.name] = (Double) metric[id] ?: 0
		}
	}

	private void createMetric(ElementWithResources element, String id, List<Measurement> keys, List<Map> metrics) {
		assert keys.size() == metrics.size()

		Iterator<Measurement> keyIt = keys.iterator()
		Iterator metricsIt = metrics.iterator()

		while(keyIt.hasNext() && metricsIt.hasNext()) {
			def key = keyIt.next()
			def metric = metricsIt.next()

			if(element instanceof Host) {
				createHostMetric(element, id, key, metric)
			}
			else if(element instanceof ServiceInstance) {
				createServiceMetric(element, id, key, metric)
			}
		}

	}

	private void createMetrics(Cluster cluster, List<Measurement> keys, List<Map<String, Double>> metrics) {
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		for(def eObject in iterator) {
			if(eObject instanceof ServiceInstance) {
				ServiceInstance service = eObject

				createMetric(service, service.id, keys, metrics)
				LOG.debug("created metric for {} metrics: {}", service.name, service.metrics)
				mergeMap(service.host.resourceReserved, service.metrics)
				mergeMap(service.host.metrics, service.metrics)
				LOG.debug("host name: {} metrics: {}", service.host.name, service.host.metrics)
			}
		}
	}

	private Map createServices(Cluster cluster, List services) {
		Map modelServices = [:]
		services.each { s ->
			Service service = factory.createServiceInstance(getResource())

			service.name = s.name
			service.id = s.uid
			service.stateful = s.stateful
			service.address = s.address
			service.application = s.application

			if(s.containers)
				service.containers.addAll(s.containers)

			if(cluster.applications.containsKey(service.application)) {
				cluster.applications[s.application].services[service.id] = service
			}

			Host host = cluster.hosts.values().find { h ->
				h.hostAddress.contains(s.hostAddress)
			}

			host.services[service.id] = service

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
			//			host.cores = item.cores
			host.resourceLimit.putAll(item.limits)

			//			host.resourceLimit["cpu"] /= (host.cores * 10) // passing limits to percentage
			host.cores = host.resourceLimit["cpu"] / 1000
			createVoidMetric(host, host.name, [Measurement.CPU, Measurement.MEMORY])
			LOG.debug("host cores: {}", host.cores)
			LOG.debug("host limit: {}", host.resourceLimit)
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

		def messagesCount = createMessages(cluster, modelServices, messages)
		LOG.info("Model created in {} ms", messagesCount, watch.elapsed(TimeUnit.MILLISECONDS))
	}

	// TODO: implement this
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
//				resource.save(Collections.EMPTY_MAP)
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

	// target += source
	private Map mergeMap(Map target, Map source) {
		Set keys = source.keySet()
		for( String key in keys) {
			def value = target.getOrDefault(key, 0.0)
			LOG.debug ("key: {} value: {}", key, value)
			//			value += source[key]
			value += Math.round(source[key])
			target[key] = (Double)value
		}
		return target
	}

	// target -= source
	private Map splitMap(Map target, Map source) {
		Set keys = source.keySet()
		for( String key in keys) {
			def value = target.getOrDefault(key, 0.0)
			//			value -= source[key]
			value -= Math.round(source[key])
			target[key] = (Double)value
		}
		return target
	}

	// implements undo
	public Boolean moveOnModel(ServiceInstance service, Host hostDestination) {
		Boolean result = false
		try {
			Host src = service.getHost()
			hostDestination.services[(service.name)] = service
			LOG.debug "before move:: reserverd: {} to add: {}", hostDestination.resourceReserved, service.metrics
			src.services.remove(service.name)
			splitMap(src.resourceReserved, service.metrics)
			LOG.debug "splitting  :: reserverd: {} to add: {}", hostDestination.resourceReserved, service.metrics
			mergeMap(hostDestination.resourceReserved, service.metrics)
			LOG.debug "merging    :: reserverd: {} to add: {}", hostDestination.resourceReserved, service.metrics
			LOG.debug "after  move:: reserverd: {} to add: {}", hostDestination.resourceReserved, service.metrics
			result = true
		}
		catch(Exception e) {
			result = false
			LOG.error e.getMessage()
			throw new RuntimeException(e)
		}
		finally {
			return result
		}
	}

	//	public Boolean undoMoveOnModel(ServiceInstance service, Host hostDestination) {
	//		LOG.warn "Reverting model to: {}", moviment
	//		Moviment undo = moviment.createUndoMoviment()
	//		return moveOnModel(service, hostDestination)
	//	}

	//	public boolean moveOnModel(Moviment moviment) {
	//		if(moviment == Moviment.nonMove()) {
	//			return true
	//		}
	//
	//		Cluster cluster = getCluster()
	//		try {
	//			String app = moviment.application
	//			String svc = moviment.service
	//			String src = moviment.hostSource
	//			String dst = moviment.hostDestination
	//
	//			Host srcHost = cluster.hosts[src]
	//			Host dstHost = cluster.hosts[dst]
	//
	//			ServiceInstance service = cluster.applications[app].services.values().find {
	//				it.name == svc || it.id == svc
	//			}
	//
	//			//TODO: there is a bug on model.xmi update
	//			// it is mantaining two "intances" at same time
	//			// can arise ERRORS when loading
	//
	//			dstHost.services[service.id] = service
	//			service.setHost(dstHost)
	//			srcHost.services.remove(svc)
	//
	//			return true
	//		}
	//		catch(Exception e) {
	//			LOG.warn "The model can't be udpated with the moving: {}", moviment
	//			LOG.error e.message
	//			return false
	//		}
	//	}
	//
}
