package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import org.junit.Test

import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.planner.AdaptationPlanner
import model.Affinity
import model.Host
import model.ServiceInstance

class AdaptationPlannerInterceptorCompareMetrics0 implements Interceptor {
	
		boolean invokeMethod = true
		
		@Override
		public Object beforeInvoke(Object object, String methodName, Object[] arguments) {
			
			if(methodName == "compareMetrics") {
				invokeMethod = false
			}
			else if(methodName == "fitsOnHost") {
				invokeMethod = false
			}
		}
	
		@Override
		public Object afterInvoke(Object object, String methodName, Object[] arguments, Object result) {
			if(methodName == "compareMetrics") {
				invokeMethod = true
				result = 0
			}
			else if(methodName == "fitsOnHost") {
				invokeMethod = true
				result = false
			}
			return result;
		}
	
		@Override
		public boolean doInvoke() {
			return invokeMethod;
		}
	
	}

class AdaptationPlannerInterceptorCompareMetricsGreaterThen1 implements Interceptor {

	boolean invokeMethod = true
	
	@Override
	public Object beforeInvoke(Object object, String methodName, Object[] arguments) {
		
		if(methodName == "compareMetrics") {
			invokeMethod = false
		}
		else if(methodName == "fitsOnHost") {
			invokeMethod = false
		}
	}

	@Override
	public Object afterInvoke(Object object, String methodName, Object[] arguments, Object result) {
		if(methodName == "compareMetrics") {
			invokeMethod = true
			result = 1
		}
		else if(methodName == "fitsOnHost") {
			invokeMethod = true
			result = true
		} 
		return result;
	}

	@Override
	public boolean doInvoke() {
		return invokeMethod;
	}

}

class AdaptationPlannerInterceptorCompareMetricsLessThen1 implements Interceptor {
	
		boolean invokeMethod = true
		
		@Override
		public Object beforeInvoke(Object object, String methodName, Object[] arguments) {
			
			if(methodName == "compareMetrics") {
				invokeMethod = false
			}
			else if(methodName == "fitsOnHost") {
				invokeMethod = false
			}
		}
	
		@Override
		public Object afterInvoke(Object object, String methodName, Object[] arguments, Object result) {
			if(methodName == "compareMetrics") {
				invokeMethod = true
				result = -1
			}
			else if(methodName == "fitsOnHost") {
				invokeMethod = true
				result = true
			}
			return result;
		}
	
		@Override
		public boolean doInvoke() {
			return invokeMethod;
		}
	
	}

class TestAdaptationPlanner extends GroovyTestCase {

	ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
	
	void testMoveService() {
		AdaptationPlanner planner = new AdaptationPlanner(null)
		
		ServiceInstance service = factory.createServiceInstance()
		Host hostDestination = factory.createHost()
		Host hostSource = factory.createHost()
		
		hostSource.services["svc"] = service
		
		assert service.host == hostSource
		assert planner.moveService(service, hostDestination)
		assert service.host == hostDestination
	}

	void testNotMove() {
		Affinity affinity = factory.createAffinity()

		ServiceInstance svc1 = factory.createServiceInstance()
		svc1.name = "svc1"
		ServiceInstance svc2 = factory.createServiceInstance()
		svc2.name = "svc2"

		Host host1 = factory.createHost()
		host1.name = "host1"
		Host host2 = factory.createHost()
		host2.name = "host2"

		host1.services["svc1"] = svc1
		host2.services["svc2"] = svc2

		affinity.setWith(svc2)
		svc1.hasAffinities << affinity

		def proxy = ProxyMetaClass.getInstance(AdaptationPlanner)
		def interceptor = new AdaptationPlannerInterceptorCompareMetrics0()
		proxy.interceptor = interceptor
		
		proxy.use {
			AdaptationPlanner planner = new AdaptationPlanner(null)
			
			assert planner.move(affinity) == false
			assert svc1.host == host1
		}
		
		
	}
	
	void testNotMoveAtoB() {
		Affinity affinity = factory.createAffinity()

		ServiceInstance svc1 = factory.createServiceInstance()
		svc1.name = "svc1"
		ServiceInstance svc2 = factory.createServiceInstance()
		svc2.name = "svc2"

		Host host1 = factory.createHost()
		host1.name = "host1"
		Host host2 = factory.createHost()
		host2.name = "host2"

		host1.services["svc1"] = svc1
		host2.services["svc2"] = svc2

		affinity.setWith(svc2)
		svc1.hasAffinities << affinity

		
		def proxy = ProxyMetaClass.getInstance(AdaptationPlanner)
		def interceptor = new AdaptationPlannerInterceptorCompareMetricsGreaterThen1()
		proxy.interceptor = interceptor
		
		proxy.use {
			AdaptationPlanner planner = new AdaptationPlanner(null)
			
			assert planner.compareMetrics(null,null) == 1
			assert planner.fitsOnHost(null,null,null) == true
			
			assert planner.move(affinity) == true
			assert svc1.host == host2
			assert svc2.host == host2
		}
				
		
	}
	
	void testNotMoveBtoA() {
		Affinity affinity = factory.createAffinity()

		ServiceInstance svc1 = factory.createServiceInstance()
		svc1.name = "svc1"
		ServiceInstance svc2 = factory.createServiceInstance()
		svc2.name = "svc2"

		Host host1 = factory.createHost()
		host1.name = "host1"
		Host host2 = factory.createHost()
		host2.name = "host2"

		host1.services["svc1"] = svc1
		host2.services["svc2"] = svc2

		affinity.setWith(svc2)
		svc1.hasAffinities << affinity

		
		def proxy = ProxyMetaClass.getInstance(AdaptationPlanner)
		def interceptor = new AdaptationPlannerInterceptorCompareMetricsLessThen1()
		proxy.interceptor = interceptor
		
		proxy.use {
			AdaptationPlanner planner = new AdaptationPlanner(null)
			
			assert planner.compareMetrics(null,null) == -1
			assert planner.fitsOnHost(null,null,null) == true
			
			assert planner.move(affinity) == true
			assert svc1.host == host1
			assert svc2.host == host1
		}
				
		
	}
	
}
