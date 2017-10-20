package cas.ibm.ubc.ca.model.manager.planner

import javax.naming.OperationNotSupportedException

import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import cas.ibm.ubc.ca.interfaces.Moviment
import cas.ibm.ubc.ca.interfaces.ReificationInterface
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
//		this.modelHandler = modelHandler 	
	}
	
	/**
	 * Calculte if metric1 is greater of less than metric2
	 * If metric1 > metric2 then return -1
	 * If metric < metric2 then return +1
	 * If metric == metric2 then return 0
	 * @param metric1
	 * @param metric2
	 * @return
	 */
	private Integer compareMetrics(def metric1, def metric2) {
		LOG.warn("Metrics Comparison is not implemented yet!") 
		throw new UnsupportedOperationException("Metrics Comparison is not implemented yet!")
		return 0
	}
	
	/**
	 * Calculate if the sum of metric1 and metric2 fits on host
	 * @param metric1
	 * @param metric2
	 * @param host
	 * @return
	 */
	private Boolean fitsOnHost(def metric1, def metric2, def host) {
		LOG.warn("Metrics Comparison is not implemented yet!")
		def result = sumMetrics(metric1, metric2)
		throw new UnsupportedOperationException("Metrics Comparison is not implemented yet!")
		return false
	}
	
	private Integer sumMetrics(def metric1, def metric2) {
		LOG.warn("Metrics Sum is not implemented yet!")
		throw new UnsupportedOperationException("Metrics Sum is not implemented yet!")
		return 0
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

		Integer metricsComparison = compareMetrics(svc1.getHost(), svc2.getHost())
		
		Boolean result = false
		
		Host src, dst
		if(metricsComparison <= 0 && fitsOnHost(svc1, svc2, svc1.getHost())) {
			LOG.debug("Moving {} to {}...", svc2.name, svc1.host.name)
			src = svc2.host
			dst = svc1.host
			result = moveService(svc2, dst)
			if(result) {
				addToScript(svc2, src, dst)
			}
		}
		else if(metricsComparison > 0 && fitsOnHost(svc1, svc2, svc2.getHost())) {
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
