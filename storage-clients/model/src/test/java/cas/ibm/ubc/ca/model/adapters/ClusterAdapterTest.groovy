package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import model.Application
import model.Cluster
import model.Host
import model.ModelFactory
import model.ServiceInstance

class ClusterAdapterTest extends GroovyTestCase {

	private static Cluster cluster
	private static Application application
	private static Host host1, host2
	private static ServiceInstance service

	protected void setUp() throws Exception {
		ModelFactory factory = ModelFactoryAdapter.INSTANCE

		cluster = factory.createCluster()
		application = factory.createApplication()
		application.setName("application1")
		
		service = factory.createServiceInstance()
		service.setId("service1")
		assert service != null
		application.services["service1"] = service
		assert application.services["service1"] != null
		
		cluster.applications["application1"] = application

		assert cluster.applications["application1"].services["service1"] != null
		
		host1 = factory.createHost()
		host1.setName("host1")
		cluster.hosts["host1"] = host1

		host2 = factory.createHost()
		host2.setName("host2")
		cluster.hosts["host2"] = host2

		cluster.hosts["host1"].services["service1"] = service
	}

	public void testModelInstance() {
		assert cluster != null
		assert application != null
		assert host1 != null
		assert host2 != null
		assert service != null

		assert cluster.getHosts().size() == 2
		assert cluster.hosts == ["host1":host1, "host2":host2]
		assert cluster.getApplications().size() == 1
		assert cluster.applications == ["application1":application]

		assert cluster.applications["application1"].services.size() == 1
		assert cluster.applications["application1"].services == ["service1":service]
		
	}

	public void testModelUpdate() {

		ClusterAdapter.updateModel(cluster, "application1", "service1", "host1", "host2")

		assert cluster.hosts["host1"].getServices().size() == 0
		assert cluster.hosts["host2"].getServices().size() == 1

	}

}
