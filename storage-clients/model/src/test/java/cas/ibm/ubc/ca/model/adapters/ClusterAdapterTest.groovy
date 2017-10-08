package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import org.eclipse.emf.ecore.util.EcoreUtil

import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.ModelFactory
import model.ServiceInstance
import org.junit.Test

class ClusterAdapterTest extends GroovyTestCase {

	private static Cluster cluster
	private static Application application
	private static Host host1, host2
	private static ServiceInstance service

	protected void setUp() throws Exception {
		ModelFactory factory = ModelFactoryAdapter.INSTANCE

		cluster = factory.createCluster()

		application = factory.createApplication()
		application.setName("Application1")
		cluster.applications.add(application)

		service = factory.createServiceInstance()
		service.setId("service1")

		application.getServices().add(service)

		host1 = factory.createHost()
		host1.setName("host1")
		host1.getServices().add(service)
		cluster.getHosts().add(host1)

		host2 = factory.createHost()
		host2.setName("host2")
		cluster.getHosts().add(host2)


		host1.getServices().add(service)
	}

	public void testModelInstance() {
		assert cluster != null
		assert application != null
		assert host1 != null
		assert host2 != null
		assert service != null

		assert cluster.getApplications().size() == 1
		assert cluster.getHosts().size() == 2
		assert application.getServices().size() == 1

		assert (host1.getServices().size() == 1 || host2.getServices().size() == 1) == true
	}

	public void testModelUpdate() {

		ClusterAdapter.updateModel(cluster, "Application1", "service1", "host1", "host2")

		assert cluster.getHosts().find {
			it.name == "host1"
		}.getServices().size() == 0

		assert cluster.getHosts().find {
			it.name == "host2"
		}.getServices().size() == 1
	}
	
	public void testMove() {
		ClusterAdapter.move(cluster, "Application1", "service1", "host1", "host2")
	} 

}
