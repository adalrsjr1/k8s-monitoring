package cas.ibm.ubc.ca.model

import static org.junit.Assert.*

import org.junit.Test

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.model.manager.planner.AdaptationPlanner
import groovy.mock.interceptor.MockFor
import groovy.mock.interceptor.StubFor
import model.Affinity
import model.Cluster
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
			assert svc1.host == host1
			assert svc2.host == host1
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
			assert svc1.host == host2
			assert svc2.host == host2
		}
	}

	void testMagnitude() {
		AdaptationPlanner planner = new AdaptationPlanner(null)

		assert 0 == planner.magnitude([0,0,0])
		assert Math.sqrt(2.0) == planner.magnitude([1,1])
		assert 5.0 == planner.magnitude([3,4])
		assert 10.0 == planner.magnitude([6,8])

	}

	private void merge(Map m1, Map m2) {
		m2.keySet().each {
			m1[it] = (Double) m2[it]
		}
	}

	private Cluster createAffinity(Map host1Metric, Map host2Metric,
			Map svc1Metric, Map svc2Metric, Float affinityDegree) {
		Affinity affinity = factory.createAffinity()

		Host host1 = factory.createHost()
		host1.name = "host1"
		merge(host1.metrics, host1Metric)

		Host host2 = factory.createHost()
		host2.name = "host2"
		merge(host2.metrics, host2Metric)

		ServiceInstance svc1 = factory.createServiceInstance()
		svc1.name = "svc1"
		merge(svc1.metrics, svc1Metric)

		ServiceInstance svc2 = factory.createServiceInstance()
		svc2.name = "svc2"
		merge(svc2.metrics, svc2Metric)

		host1.services["svc1"] = svc1
		host2.services["svc2"] = svc2

		affinity.setWith(svc2)
		affinity.setDegree(affinityDegree)
		svc1.hasAffinities << affinity

		Cluster c = factory.createCluster()
		c.hosts["host1"] = host1
		c.hosts["host2"] = host2

		return c
	}

	boolean checkCluster(Cluster c, Float degree) {
		assert ["host1", "host2"] == c.hosts.values().collect([]){it.name}

		assert !c.hosts["host1"].metrics.empty
		assert !c.hosts["host2"].metrics.empty

		assert "svc1" == c.hosts["host1"].services["svc1"].name
		assert "svc2" == c.hosts["host2"].services["svc2"].name

		def aff = c.hosts["host1"].services["svc1"].hasAffinities[0]
		assert degree == aff.getDegree()
	}

	void testMoveSvc2ToHost1() {

		def host1Metric = ['a':100.0]
		def host2Metric = ['a':1.0]
		def svc1Metric = ['a':1.0]
		def svc2Metric = ['a':1.0]

		Float degree = 10f

		Cluster c = createAffinity(host1Metric, host2Metric,
				svc1Metric, svc2Metric, degree)

		checkCluster(c, degree)

		def affinity = c.hosts["host1"].services["svc1"].hasAffinities[0]

		AdaptationPlanner planner = new AdaptationPlanner(null)

		assert planner.move(affinity)
		assert 2 ==  c.hosts["host1"].services.size()
		assert 0 == c.hosts["host2"].services.size()
		assert new Moviment(null, "svc2", "host2", "host1") == planner.adaptationScript[0]

	}

	void testMoveSvc1ToHost2() {

		def host1Metric = ['a':1.0]
		def host2Metric = ['a':100.0]
		def svc1Metric = ['a':1.0]
		def svc2Metric = ['a':1.0]

		Float degree = 10f

		Cluster c = createAffinity(host1Metric, host2Metric,
				svc1Metric, svc2Metric, degree)

		checkCluster(c, degree)

		def affinity = c.hosts["host1"].services["svc1"].hasAffinities[0]

		AdaptationPlanner planner = new AdaptationPlanner(null)

		assert planner.move(affinity)
		assert 2 ==  c.hosts["host2"].services.size()
		assert 0 == c.hosts["host1"].services.size()
		assert new Moviment(null, "svc1", "host1", "host2") == planner.adaptationScript[0]
		
	}


	void testNotMove() {

		def host1Metric = ['a':1.0]
		def host2Metric = ['a':1.0]
		def svc1Metric = ['a':1.0]
		def svc2Metric = ['a':1.0]

		Float degree = 10f

		Cluster c = createAffinity(host1Metric, host2Metric,
				svc1Metric, svc2Metric, degree)

		checkCluster(c, degree)

		def affinity = c.hosts["host1"].services["svc1"].hasAffinities[0]

		AdaptationPlanner planner = new AdaptationPlanner(null)

		assert planner.move(affinity) == false
		assert 1 ==  c.hosts["host2"].services.size()
		assert 1 == c.hosts["host1"].services.size()
		assert 1 == planner.affinitiesWaiting.size()
	}

}
