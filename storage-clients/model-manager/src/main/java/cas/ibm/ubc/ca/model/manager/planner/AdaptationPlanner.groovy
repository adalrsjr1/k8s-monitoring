package cas.ibm.ubc.ca.model.manager.planner

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

	private List affinitiesWaiting = []
	private List<Moviment> adaptationScript = []

	public AdaptationPlanner(ModelHandler modelHandler) {
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
			def vv = metric1.getOrDefault(i, 0)
			def v1 = vv == null ? 0 : vv
			vv = metric2.getOrDefault(i, 0)
			def v2 = vv == null ? 0 : vv

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

	private Moviment canMove(Affinity affinity) {
		ServiceInstance svc1 = getAffinityServiceSrc(affinity)
		ServiceInstance svc2 = affinity.getWith()

		Double metricsComparison =
				compareMetrics(svc1.getHost().metrics, svc2.getHost().metrics)

		Moviment result

		Host src, dst

		if(metricsComparison > 0 && fitsOnHost(svc1.metrics, svc2.metrics,
		svc1.getHost().metrics)) {
			LOG.debug("Moving {} to {}...", svc2.name, svc1.host.name)
			src = svc2.host
			dst = svc1.host
			if(moveService(svc2, dst)) {
				result = createMove(svc2, src, dst)
			}
		}
		else if(metricsComparison < 0 && fitsOnHost(svc1.metrics, svc2.metrics,
		svc2.getHost().metrics)) {
			LOG.debug("Moving {} to {}...", svc1.name, svc2.host.name)
			src = svc1.host
			dst = svc2.host
			if(moveService(svc1, svc2.getHost())) {
				result = createMove(svc1, src, dst)
			}
		}
		else {
			LOG.info("affinity {}-[{}]->{} cannot be applied yet!",
					getAffinityServiceSrc(affinity).name, affinity.degree, affinity.with.name)
			// service cannot be moved from some reason
			affinitiesWaiting << affinity
			result = Moviment.nonMove()
		}

		if(!modelHandler.moveOnModel(result)) {
			modelHandler.undoMoveOnModel(result)
		}

		return result
	}

	private void waitingAffinitiesHandler(List affinities) {
		LOG.warn "WaitingAffinitiesHandler not implemented yet!!!"
	}

	List<Moviment> execute(Cluster cluster) {

		Iterator iterator = EcoreUtil.getAllContents(cluster, true)

		while( iterator.hasNext() ) {
			def obj = iterator.next()

			if(obj instanceof Affinity) {
				adaptationScript << canMove(obj)
			}
		}

		waitingAffinitiesHandler(affinitiesWaiting)

		return adaptationScript
	}

}
