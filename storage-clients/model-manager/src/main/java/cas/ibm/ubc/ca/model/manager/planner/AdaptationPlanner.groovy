package cas.ibm.ubc.ca.model.manager.planner

import java.util.Map

import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.model.manager.ModelHandler
import groovy.transform.Memoized
import model.Affinity
import model.Cluster
import model.Host
import model.Service
import model.ServiceInstance

class AdaptationPlanner {
	private static final Logger LOG = LoggerFactory.getLogger(AdaptationPlanner.class)

	private ModelHandler modelHandler

	private List<Moviment> adaptationScript = []

	public AdaptationPlanner(ModelHandler modelHandler) {
		this.modelHandler = modelHandler
	}

	/**
	 * <p>Calculte if metric1 is greater of less than metric2</p>
	 * <p>If metric1 > metric2 then return > 0</p>
	 * <p>If metric1 < metric2 then return < 0</p>
	 * <p>If metric == metric2 then return 0</p>
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
		Double sum = 0

		sum = l.inject(sum) { r, i ->
			def value = i == null ? 0 : i
			r += value * value
			return r
		}
		return sum
	}

	/**
	 * Calculate if the sum of metric1 and metric2 fits on host
	 * @param metric1
	 * @param metric2
	 * @param host
	 * @return
	 */
	private Boolean innerFitsOnHost(Map metric1, Map metric2, Map limits, Map reservation) {
		def result = sumMetrics(metric1, metric2)
		
		LOG.debug "svc1:        {}", metric1
		LOG.debug "svc2:        {}", metric2
		LOG.debug "limits:      {}", limits
		LOG.debug "reservation: {}", reservation
		return reservation.cpu < limits.cpu && reservation.memory < limits.memory \
			   && result.cpu < limits.cpu && result.memory < limits.memory
	}

	private Double cores(Host host) {
		return host.resourceLimit.cpu / 1000.0
	}
	
	private Boolean checkServicesPerCore(Host host) {
//		return (host.services.size() + 1) <= cores(host)
		return true
	}
	
	private Boolean fitsOnHost(ServiceInstance svc1, ServiceInstance svc2, Host host) {
		Map zero = ["cpu":0.0, "memory":0.0]
		if(checkServicesPerCore(host) && !host.services.containsKey(svc1.id) && !host.services.containsKey(svc2.id)) {
			return innerFitsOnHost(svc1.metrics, svc2.metrics, host.resourceLimit, host.resourceReserved)
		}
		
		if(checkServicesPerCore(host) && host.services.containsKey(svc1.id) && !host.services.containsKey(svc2.id)) {
			return innerFitsOnHost(zero, svc2.metrics, host.resourceLimit, host.resourceReserved)
		}
		
		if(checkServicesPerCore(host) && !host.services.containsKey(svc1.id) && host.services.containsKey(svc2.id)) {
			return innerFitsOnHost(svc1.metrics, zero, host.resourceLimit, host.resourceReserved)
		}
		
		return false
		
	}
	
	private Map sumMetrics(Map metric1, Map metric2) {
		Map result = [:]

		Set keys = [] as Set
		keys.addAll(metric1.keySet())
		keys.addAll(metric2.keySet())

		return keys.inject(result) { r, i ->
			def vv = metric1.getOrDefault(i, 0)
			def v1 = vv == null ? 0 : vv
			vv = metric2.getOrDefault(i, 0)
			def v2 = vv == null ? 0 : vv

			r[i] = v1 + v2
			return r
		}

	}

//	// TODO: implement undo for cases where the merge/split fails
//	private Boolean moveService(ServiceInstance service, Host hostDestination) {
//		Boolean result = false
//		try {
//			Host src = service.getHost()
//			hostDestination.services[(service.name)] = service
//			mergeMap(hostDestination.resourceReserved, service.metrics)
//			
//			src.services.remove(service.name)
//			splitMap(src.resourceReserved, service.metrics)
//			result = true
//		}
//		catch(Exception e) {
//			result = false
//			LOG.error e.getMessage()
//			throw new RuntimeException(e)
//		}
//		finally {
//			return result
//		}
//	}
	
	private ServiceInstance getAffinityServiceSrc(Affinity affinity) {
		return affinity.eContainer()
	}

	private Moviment createMove(Service service, Host source, Host destination) {
		String application = service.application
		String svcName = service.name
		String hostSource = source.name
		String hostDestination = destination.name

		return createInnerMove(application, svcName, hostSource, hostDestination)
	}

	private Moviment createInnerMove(String application, String service,
			String hostSource, String hostDestination) {

		return Moviment.create(application, service,
				hostSource, hostDestination)
	}
	
	private Moviment canMove(Affinity affinity, Set moved) {
		ServiceInstance svc1 = getAffinityServiceSrc(affinity)
		ServiceInstance svc2 = affinity.getWith()

		Moviment result
		Host src, dst
		
		def moveService = { svc, hsource, htarget ->
			if( modelHandler.moveOnModel(svc, htarget) ) {
				return createMove(svc, hsource, htarget)
			}
		}
		
		if(!moved.contains(svc2) && fitsOnHost(svc1, svc2, svc1.getHost())) {
			LOG.debug("Moving {} to {}...", svc2.name, svc1.host.name)
			src = svc2.host
			dst = svc1.host
			result = moveService(svc2, src, dst)
			moved << svc1
			moved << svc2
		}
		else if(!moved.contains(svc1) && fitsOnHost(svc1, svc2, svc2.getHost())) {
			LOG.debug("Moving {} to {}...", svc1.name, svc2.host.name)
			src = svc1.host
			dst = svc2.host
			result = moveService(svc1, src, dst)
			moved << svc1
			moved << svc2
		}
		else {
			LOG.info("The moviment {}-[{}]->{} will not be applied",
					getAffinityServiceSrc(affinity).name, affinity.degree, affinity.with.name)
			result = Moviment.nonMove()
		}

		return result
	}

	List<Moviment> execute(List affinities) {
		Set moved = [] as Set
		while( affinities.size() > 0 ) {
			def obj = affinities.remove(0)
			adaptationScript << canMove(obj, moved)
		}
		return adaptationScript
	}

}
