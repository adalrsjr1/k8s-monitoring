package cas.ibm.ubc.ca.model

import java.lang.reflect.InvocationTargetException

import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.common.util.TreeIterator
import org.eclipse.emf.common.util.URI as EmfURI
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl
import org.emfjson.jackson.resource.JsonResourceFactory

import cas.ibm.ubc.ca.model.factories.ModelFactoryAdapter
import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.Message
import model.ModelFactory
import model.ServiceReplica

abstract class ECluster implements Cluster {
	protected Cluster cluster

	ECluster (Cluster cluster) {
		this.cluster = cluster
	}

	@Override
	public EClass eClass() {
		return cluster.eClass();
	}

	@Override
	public Resource eResource() {
		return cluster.eResource();
	}

	@Override
	public EObject eContainer() {
		return cluster.eContainer();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		return cluster.eContainingFeature();
	}

	@Override
	public EReference eContainmentFeature() {
		return cluster.eContainmentFeature;
	}

	@Override
	public EList<EObject> eContents() {
		return cluster.eContents;
	}

	@Override
	public TreeIterator<EObject> eAllContents() {
		return cluster.eAllContents;
	}

	@Override
	public boolean eIsProxy() {
		return cluster.eIsProxy();
	}

	@Override
	public EList<EObject> eCrossReferences() {
		return cluster.eCrossReferences();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		return cluster.eGet(feature);
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		return cluster.eGet(feature, resolve);
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		cluster.eSet(feature, newValue)
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		return cluster.eIsSet(feature);
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		cluster.eUnset(feature)
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		return cluster.eInvoke(operation, arguments);
	}

	@Override
	public EList<Adapter> eAdapters() {
		return cluster.eAdapters();
	}

	@Override
	public boolean eDeliver() {
		return cluster.eDeliver();
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		cluster.eSetDeliver(deliver)
	}

	@Override
	public void eNotify(Notification notification) {
		cluster.eNotify(notification)
	}

	@Override
	public List<Application> getApplications() {
		return cluster.getApplications();
	}

	@Override
	public List<Host> getHosts() {
		return cluster.getHosts();
	}

	@Override
	public Environment getEnvironment() {
		return cluster.getEnvironment();
	}

	@Override
	public void setEnvironment(Environment value) {
		cluster.setEnvironment(value)
	}

	@Override
	public abstract void move(String application, String serviceId, String destinationHost)

}

class ClusterAdapter extends ECluster {

	public ClusterAdapter(Cluster cluster) {
		super(cluster)
	}

	public Cluster getECluster() {
		return cluster
	}

	@Override
	public void move(String application, String serviceId, String destinationHost) {
		println "moving..."
	}

}

class HelloWorld {

	public static ResourceSet getJsonResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("*", new JsonResourceFactory());
		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();

		return resSet
	}

	public static ResourceSet getXmiResourceSet() {
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("*", new XMIResourceFactoryImpl());

		// Obtain a new resource set
		ResourceSet resSet = new ResourceSetImpl();
		return resSet
	}

	// Json
	public static void main(String[] args) {
		//				ModelFactory factory = ModelFactory.INSTANCE
		ModelFactory factory = ModelFactoryAdapter.INSTANCE

		Cluster cluster = factory.createCluster()
		//		ElementObserver observer = new ElementObserver(cluster)
		//		TotalObserver observer = new TotalObserver(cluster)
		cluster.setEnvironment(Environment.KUBERNETES)

		Application application1 = factory.createApplication()
		application1.setName("Application1")
		cluster.applications << application1

		Application application2 = factory.createApplication()
		application2.setName("Application2")

		cluster.applications << application2

		application2.setName("Application21")

		ServiceReplica service1 = factory.createServiceReplica()
		service1.setName("service1")
		service1.setAddress("1.1.1.1")
		service1.setId("0123")
		service1.setPort(80)

		ServiceReplica service2 = factory.createServiceReplica()
		service2.setName("service2")
		service2.setAddress("0.0.0.0")
		service2.setId("0123")
		service2.setPort(80)

		application2.getServices().add(service1)
		application2.getServices().add(service2)

		Message message = factory.createMessage()
		message.setDestination(service2)
		message.setSource(service1)

		service1.getMessages().add(message)


		Host host = factory.createHost()
		cluster.getHosts().add(host)
		host.setName("host1")

		host.metrics["cpu"] = 100L
		host.metrics["cpu"] = 90L


		host.getServices().add(service1)
		host.getServices().add(service2)

		ResourceSet rs = getJsonResourceSet()
		//		ResourceSet rs = getXmiResourceSet()

		EmfURI emfURI = EmfURI.createURI("src/main/resources/model/model.json")
		Resource resource = rs.createResource(emfURI)

		//		resource.getContents() << cluster.getECluster()

		//		resource.save(null)

		// Get the first model element and cast it to the right type, in my
		// example everything is hierarchical included in this first node
		resource.getContents().add(cluster)
		resource.getContents().add(application1);
		resource.getContents().add(application2);

		resource.getContents().add(service1)
		resource.getContents().add(service2)

		// now save the content.
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cluster.move("application1", "service1", "host1")
	}

	// PLAIN
	//	static void main(String[] args) {
	//		//		ModelFactory factory = ModelFactory.INSTANCE
	//		ModelFactory factory = ModelFactoryAdapter.INSTANCE
	//
	//		Cluster cluster = factory.createCluster()
	//
	//		//		ElementObserver observer = new ElementObserver(cluster)
	//		//		TotalObserver observer = new TotalObserver(cluster)
	//
	//		Application application1 = factory.createApplication()
	//		application1.setName("Application1")
	//		cluster.applications << application1
	//
	//		Application application2 = factory.createApplication()
	//		application2.setName("Application2")
	//
	//		cluster.applications << application2
	//
	//		application2.setName("Application3")
	//
	//		Host host = factory.createHost()
	//		cluster.hosts << host
	//		host.setName("host1")
	//
	//		host.metrics["cpu"] = 100L
	//		host.metrics["cpu"] = 90L
	//
	//	}

	// CDO
	//	static void main(String[] args) {
	//		// Enable logging and tracing
	//		OMPlatform.INSTANCE.setDebugging(true);
	//		OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);
	//		OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);
	//
	//		// Prepare receiveExecutor
	//		ExecutorService receiveExecutor = ThreadPool.create();
	//
	//		// Prepare bufferProvider
	//		IBufferProvider bufferProvider = Net4jUtil.createBufferPool();
	//		LifecycleUtil.activate(bufferProvider);
	//
	//		IProtocolProvider protocolProvider = new FactoriesProtocolProvider(new org.eclipse.emf.cdo.internal.net4j.protocol.CDOClientProtocolFactory());
	//
	//		// Prepare selector
	//		org.eclipse.net4j.internal.tcp.TCPSelector selector = new org.eclipse.net4j.internal.tcp.TCPSelector();
	//		selector.activate();
	//
	//		// Prepare connector
	//		org.eclipse.net4j.internal.tcp.TCPClientConnector connector = new org.eclipse.net4j.internal.tcp.TCPClientConnector();
	//		connector.getConfig().setBufferProvider(bufferProvider);
	//		connector.getConfig().setReceiveExecutor(receiveExecutor);
	//		connector.getConfig().setProtocolProvider(protocolProvider);
	//		connector.getConfig().setNegotiator(null);
	//		connector.setSelector(selector);
	//		connector.setHost("localhost"); //$NON-NLS-1$
	//		connector.setPort(2036);
	//		connector.activate();
	//
	//		// Create configuration
	//		CDONet4jSessionConfiguration configuration = CDONet4jUtil.createNet4jSessionConfiguration();
	//		configuration.setConnector(connector);
	//		configuration.setRepositoryName("repo1"); //$NON-NLS-1$
	//
	//		// Open session
	//		CDOSession session = configuration.openNet4jSession();
	//
	//		session.getPackageRegistry().putEPackage(ModelPackageImpl.eINSTANCE);
	//
	//		// Open transaction
	//		CDOTransaction transaction = session.openTransaction();
	//
	//		// Get or create resource
	//		CDOResource resource = transaction.getOrCreateResource("/myCluster"); //$NON-NLS-1$
	//
	//		// Work with the resource and commit the transaction
	//
	//		ModelFactory factory = ModelFactoryAdapter.INSTANCE
	//
	//		Cluster cluster = resource.contents[0]
	//
	//		//		println cluster.applications
	//
	//		//				ElementObserver observer = new ElementObserver(cluster)
	//		//				TotalObserver observer = new TotalObserver(cluster)
	//
	//		Application application1 = factory.createApplication()
	//		application1.setName("Application1")
	//		cluster.applications << application1
	//
	//		Application application2 = factory.createApplication()
	//		application2.setName("Application2")
	//
	//		cluster.applications << application2
	//
	//		application2.setName("Application3")
	//
	//		Host host = factory.createHost()
	//		cluster.hosts << host
	//		host.setName("host1")
	//
	//		host.metrics["cpu"] = 100L
	//		host.metrics["cpu"] = 90L
	//
	//		transaction.commit()
	//
	//		// Cleanup
	//		session.close();
	//		connector.deactivate();
	//	}
}
