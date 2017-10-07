package cas.ibm.ubc.ca.model.factories

import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.impl.AdapterImpl
import org.slf4j.LoggerFactory
import cas.ibm.ubc.ca.model.ClusterAdapter
import model.Affinity
import model.Application
import model.Cluster
import model.Host
import model.Message
import model.ModelFactory
import model.Service
import model.ServiceReplica

// abstracts the ModelFactory to automatically add the adapters
// to the EObjects instances
class ModelFactoryAdapter implements ModelFactory {

	private static final ModelFactory factory = ModelFactory.INSTANCE

	static final ModelFactory INSTANCE = new ModelFactoryAdapter()
	
	private Adapter adapter = new AdapterImpl() {
		static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AdapterImpl.class)
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification)
			LOG.trace "${notification}"
		}
	}
	
	private ModelFactoryAdapter() { }

	@Override
	public Cluster createCluster() {
//		Cluster cluster = new ClusterAdapter(factory.createCluster())
		Cluster cluster = factory.createCluster()
		
		
		
		cluster.eAdapters().add(adapter)
		return cluster
	}

	@Override
	public Application createApplication() {
		Application application = factory.createApplication()
		application.eAdapters().add(adapter)
		return application
	}

	@Override
	public Service createService() {
		Service service = factory.createService()
		service.eAdapters().add(adapter)
		return service;
	}

	@Override
	public Affinity createAffinity() {
		Affinity affinity = factory.createAffinity()
		affinity.eAdapters().add(adapter)
		return affinity;
	}

	@Override
	public ServiceReplica createServiceReplica() {
		ServiceReplica serviceReplica = factory.createServiceReplica()
		serviceReplica.eAdapters().add(adapter)
		return serviceReplica

	}

	@Override
	public Message createMessage() {
		Message message = factory.createMessage()
		message.eAdapters().add(adapter)
		return message
	}

	@Override
	public Host createHost() {
		Host host = factory.createHost()
		host.eAdapters().add(adapter)
		return host
	}

}
