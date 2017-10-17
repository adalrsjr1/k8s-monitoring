package cas.ibm.ubc.ca.model.adapters

import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.Notifier
import org.eclipse.emf.common.notify.impl.AdapterImpl
import org.eclipse.emf.common.notify.impl.NotificationImpl
import org.eclipse.emf.ecore.EcorePackage
import org.eclipse.emf.ecore.impl.EAttributeImpl
import org.eclipse.emf.ecore.util.EContentAdapter
import org.eclipse.emf.ecore.util.EcoreUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import model.Affinity
import model.Application
import model.Cluster
import model.Host
import model.Message
import model.ModelFactory
import model.Service
import model.ServiceInstance
import model.impl.ClusterImpl
import model.impl.MessageImpl
import model.impl.ServiceImpl
import model.impl.StringToServiceImpl

// abstracts the ModelFactory to automatically add the adapters
// to the EObjects instances
class ModelFactoryAdapter implements ModelFactory {
	private static final Logger LOG = LoggerFactory.getLogger(AdapterImpl.class)
	
	private static final ModelFactory factory = ModelFactory.INSTANCE
	static final ModelFactory INSTANCE = new ModelFactoryAdapter()
	
	// http://www.vogella.com/tutorials/EclipseEMFNotification/article.html
	private Adapter serviceAdapter = new AdapterImpl() {
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification)

			Notifier notifier = notification.getNotifier()
			def newValue = notification.getNewValue()
			if(newValue instanceof MessageImpl) {
				if(notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.SET) {
					println notifier
					println notification
					target.totalMessages += 1L
					target.totalData += newValue.messageSize 
				}
			}
						
		}
	}
	
	private AdapterImpl applicationAdapter = new AdapterImpl() {
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification)
			
			Notifier notifier = notification.getNotifier()
			def newValue = notification.getNewValue()
			if(newValue instanceof StringToServiceImpl) {
				notifier.totalMessages += newValue.value.totalMessages
				notifier.totalData += newValue.value.totalData
			}
			else if(notifier instanceof ServiceInstance){
				println target
			}
		}
	}
	
	private Adapter adapter = new AdapterImpl() {
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification)
			
//			LOG.trace "${notification}"
		}
	}

	private ModelFactoryAdapter() { }

	@Override
	public Cluster createCluster() {
		Cluster cluster = factory.createCluster()
		cluster.eAdapters().add(adapter)
		return cluster
	}
	
	@Override
	public Application createApplication() {
		Application application = factory.createApplication()
		application.eAdapters().add(adapter)
		application.eAdapters().add(applicationAdapter)
		return application
	}

	@Override
	public Affinity createAffinity() {
		Affinity affinity = factory.createAffinity()
		affinity.eAdapters().add(adapter)
		return affinity;
	}

	@Override
	public ServiceInstance createServiceInstance() {
		ServiceInstance serviceInstance = factory.createServiceInstance()
		serviceInstance.eAdapters().add(adapter)
		serviceInstance.eAdapters().add(serviceAdapter)
		return serviceInstance

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
