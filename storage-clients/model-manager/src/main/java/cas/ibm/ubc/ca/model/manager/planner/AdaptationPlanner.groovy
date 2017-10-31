package cas.ibm.ubc.ca.model.manager.planner

import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import groovy.transform.Memoized
import model.Affinity
import model.Cluster
import model.Host
import model.Service
import model.ServiceInstance

class AdaptationPlanner {
	private static final Logger LOG = LoggerFactory.getLogger(AdaptationPlanner.class)
	
	private ReificationInterface modelHandler
	
	private List affinitiesWaiting = []
	private List<Moviment> adaptationScript = []
	
	public AdaptationPlanner(ReificationInterface modelHandler) {
		this.modelHandler = modelHandler 	
	}
	
	/**
	 * Calculte if metric1 is greater of less than metric2
	 * If metric1 > metric2 then return > 0
	 * If metric1 < metric2 then return < 0
	 * If metric == metric2 then return 0
	 * @param metric1
	 * @param metric2
	 * @return
	 */
	private Double compareMetrics(Map metric1, Map metric2) {
		return magnitude(metric1.values() as List) - magnitude(metric2.values() as List)
	}
	
	@Memoized
	/**
	 * Vector norm L2
	 * @param l
	 * @return
	 */
	private Double magnitude(List l) {
		int sum = 0
		sum = l.inject(sum) { r, i ->
			r += i*i
			return r
		}
		return Math.sqrt(sum)
	}
	
	/**
	 * Calculate if the sum of metric1 and metric2 fits on host
	 * @param metric1
	 * @param metric2
	 * @param host
	 * @return
	 */
	private Boolean fitsOnHost(Map metric1, Map metric2, Map host) {
		def result = sumMetrics(metric1, metric2)
		return compareMetrics(result, host)
	}
	
	private Map sumMetrics(Map metric1, Map metric2) {
		Map result = [:]
		
		Set keys = [] as Set
		keys.addAll(metric1.keySet())
		keys.addAll(metric2.keySet())
		
		return keys.inject(result) { r, i ->
			def v1 = metric1.getOrDefault(i, 0)
			def v2 = metric2.getOrDefault(i, 0)
			
			r[i] = v1 + v2
			return r
		}
				
	}
	
	private ServiceInstance getAffinityServiceSrc(Affinity affinity) {
		return affinity.eContainer()
	}
	
	private Boolean moveService(ServiceInstance service, Host hostDestination) {
		Boolean result = false
		try {
			Host src = service.getHost()
			hostDestination.services[(service.name)] = service
			src.services.remove(service.name)
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
	
	private addToScript(Service service, Host source, Host destination) {
		String application = service.application
		String svcName = service.name
		String hostSource = source.name
		String hostDestination = destination.name
		
		addToScript(application, svcName, hostSource, hostDestination)
	}
	
	private void addToScript(String application, String service, 
			String hostSource, String hostDestination) {
			
		adaptationScript << Moviment.create(application, service, 
			hostSource, hostDestination) 
	}		
	
	private Boolean move(Affinity affinity) {
		ServiceInstance svc1 = getAffinityServiceSrc(affinity)
		ServiceInstance svc2 = affinity.getWith()

		Integer metricsComparison = 
			compareMetrics(svc1.getHost().metrics, svc2.getHost().metrics)
		
		Boolean result = false
		
		Host src, dst
		if(metricsComparison > 0 && fitsOnHost(svc1.metrics, svc2.metrics,
			 svc1.getHost().metrics)) {
			LOG.debug("Moving {} to {}...", svc2.name, svc1.host.name)
			src = svc2.host
			dst = svc1.host
			result = moveService(svc2, dst)
			if(result) {
				addToScript(svc2, src, dst)
			}
		}
		else if(metricsComparison < 0 && fitsOnHost(svc1.metrics, svc2.metrics, 
			svc2.getHost().metrics)) {
			LOG.debug("Moving {} to {}...", svc1.name, svc2.host.name)
			src = svc1.host
			dst = svc2.host
			result = moveService(svc1, svc2.getHost())
			if(result) {
				addToScript(svc1, src, dst)
			}
		}
		else {
			LOG.info("affinity {}-[{}]->{} cannot be applied yet!",
				getAffinityServiceSrc(affinity).name, affinity.degree, affinity.with.name)
			// service cannot be moved from some reason
			affinitiesWaiting << affinity
			result = false
		}
		return result
	}
	
	private void waitingAffinitiesHandler(List affinities) {
		LOG.warn("Metrics Comparison is not implemented yet!")
		throw new UnsupportedOperationException("Metrics Comparison is not implemented yet!")
	}
	
	void calculate(Cluster cluster) {
		
		Iterator iterator = EcoreUtil.getAllContents(cluster, true)
		
		while( iterator.hasNext() ) {
			def obj = iterator.next()
			
			if(obj instanceof Service) {
				move(obj.hasAffinities.get(0))
			}
		}
		
		waitingAffinitiesHandler(affinitiesWaiting)
		
		modelHandler.move(adaptationScript)
	}
	
}
