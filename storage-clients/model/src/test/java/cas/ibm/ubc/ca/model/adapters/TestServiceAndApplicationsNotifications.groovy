package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import model.Application
import model.Message
import model.ServiceInstance

class TestServiceAndApplicationsNotifications extends GroovyTestCase {

	void testServiceNotificationFirstCreateThenAdd() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		ServiceInstance service = factory.createServiceInstance()
		
		assert service != null
		
		assert service.totalMessages == 0L
		
		Message m = factory.createMessage()
		m.messageSize = 3L
		service.messages << m
		
		assert service.totalMessages == 1L
		assert service.totalData == 3L
		
		m = factory.createMessage()
		m.messageSize = 1L
		service.messages << m
		assert service.totalData == 4L
		
		m = factory.createMessage()
		m.messageSize = 5L
		service.messages << m
		assert service.totalData == 9L
		
		m = factory.createMessage()
		m.messageSize = 0L
		service.messages << m
		assert service.totalData == 9L
		
		assert service.totalMessages == 4L 
	}
	
	void testServiceNotificationFirstAddThenCreate() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		ServiceInstance service = factory.createServiceInstance()
		
		assert service != null
		
		assert service.totalMessages == 0L
		
		Message m = factory.createMessage()
		service.messages << m
		m.messageSize = 3L
		
		assert service.totalMessages == 1L
		assert service.totalData == 3L
		
		m = factory.createMessage()
		service.messages << m
		m.messageSize = 1L
		assert service.totalData == 4L
		
		m = factory.createMessage()
		m.messageSize = 5L
		service.messages << m
		
		assert service.totalData == 9L
		
		m = factory.createMessage()
		m.messageSize = 0L
		service.messages << m
		assert service.totalData == 9L
		
		assert service.totalMessages == 4L
	}
	
	void testApplicationNotificationFirstCreateThenAdd() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		
		Application application = factory.createApplication()
		application.setName("app")
			
		ServiceInstance service = factory.createServiceInstance()
		
		assert service != null
		
		assert service.totalMessages == 0L
		
		Message m = factory.createMessage()
		m.messageSize = 3L
		service.messages << m
		
		assert service.totalMessages == 1L
		assert service.totalData == 3L
		
		m = factory.createMessage()
		m.messageSize = 1L
		service.messages << m
		assert service.totalMessages == 2L
		assert service.totalData == 4L
		
		m = factory.createMessage()
		m.messageSize = 5L
		service.messages << m
		assert service.totalMessages == 3L
		assert service.totalData == 9L
		
		m = factory.createMessage()
		m.messageSize = 0L
		service.messages << m
		assert service.totalMessages == 4L
		assert service.totalData == 9L
		
		
		
		application.services ["service1"] = service
		assert service.getApplication() == "app"
		assert application.totalData == 9L
		assert application.totalMessages == 4L
	}
	
	void testApplicationNotificationFirstAddThenCreate() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		
		Application application = factory.createApplication()
		application.setName("app")
		
		ServiceInstance service = factory.createServiceInstance()
		
		service = factory.createServiceInstance()
		
		assert service != null
		
		assert service.totalMessages == 0L
		
		application.services ["service2"] = service
		assert service.getApplication() == "app"
		
		Message m = factory.createMessage()
		m.messageSize = 3L
		service.messages << m
		
		assert service.totalMessages == 1L
		assert service.totalData == 3L
		
		m = factory.createMessage()
		m.messageSize = 1L
		service.messages << m
		assert service.totalMessages == 2L
		assert service.totalData == 4L
		
		m = factory.createMessage()
		m.messageSize = 5L
		service.messages << m
		assert service.totalMessages == 3L
		assert service.totalData == 9L
		
		m = factory.createMessage()
		m.messageSize = 0L
		service.messages << m
		assert service.totalMessages == 4L
		assert service.totalData == 9L
		
		assert application.totalMessages == 4L
		assert application.totalData == 9L
		
		
	}
	
	void testMessageCreation() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		Application application = factory.createApplication()
		ServiceInstance service = factory.createServiceInstance()
		service.name = "svc"
		service.id = "svc"
		
		application.services["svc"] = service
		
		service.messages << factory.createMessage()
		assert service.totalMessages == 1L
		assert application.totalMessages == 1L
		
		service.messages << factory.createMessage()
		assert service.totalMessages == 2L
		assert application.totalMessages == 2L
	}
	
	void testFullApplication() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		Application application = factory.createApplication()
		ServiceInstance service = factory.createServiceInstance()
		service.totalData = 10L
		service.totalMessages = 4L
		
		application.services["s1"] = service
		
		assert application.totalMessages == 4L
		assert application.totalData == 10L
		
		service = factory.createServiceInstance()
		service.totalData = 3L
		service.totalMessages = 5L
		
		application.services["s2"] = service
		
		assert application.totalMessages == 9L
		assert application.totalData == 13L
	}
	
	private ServiceInstance createService(application, name, factory) {
		ServiceInstance s = factory.createServiceInstance()
		
		s.name = name
		s.id = name
		
		application.services[s.id] = s
		
		return s
	}
	
	private model.Message createMessage(ServiceInstance s1, ServiceInstance s2, f) {
		model.Message m = f.createMessage()
		
		m.source = s1
		m.destination = s2
		s1.messages << m
		
		m.messageSize = 1L
		
		return m
	}
	
	void testRandom() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		
		Application application = factory.createApplication()
		
		def user = createService(application, "user-db-65585649f9-nfwv9", factory)
		def payment = createService(application, "payment-748bb4dbdb-r6tz8", factory)
		def front = createService(application, "front-end-6ffc4ccbb9-tfxhb", factory)
		def carts = createService(application, "carts-db-787f4b7896-9xtwl", factory)
		def shipping = createService(application, "shipping-76cfc6d787-6s9bd", factory)
		def orders = createService(application, "orders-9c66f8db6-6kscv", factory)
		def queue = createService(application, "queue-master-599cfcc7-mqgf8", factory)
		def catalogue = createService(application, "catalogue-6c69b85d67-rslg6", factory)
		
		/*user-db-65585649f9-nfwv9 --> carts-db-787f4b7896-9xtwl
		payment-748bb4dbdb-r6tz8 --> carts-db-787f4b7896-9xtwl
		front-end-6ffc4ccbb9-tfxhb --> queue-master-599cfcc7-mqgf8
		carts-db-787f4b7896-9xtwl --> catalogue-6c69b85d67-rslg6
		shipping-76cfc6d787-6s9bd --> user-767d6bb97f-bptj2
		carts-db-787f4b7896-9xtwl --> user-767d6bb97f-bptj2
		user-db-65585649f9-nfwv9 --> user-767d6bb97f-bptj2
		front-end-6ffc4ccbb9-tfxhb --> orders-9c66f8db6-6kscv
		orders-9c66f8db6-6kscv --> catalogue-6c69b85d67-rslg6
		carts-75958c9dc6-b6478 --> user-db-65585649f9-nfwv9*/
		
		createMessage(user, carts, factory)
		createMessage(payment, carts, factory)
		createMessage(front, queue, factory)
		createMessage(carts, catalogue, factory)
		createMessage(shipping, user, factory)
		createMessage(carts, user, factory)
		createMessage(user, user, factory)
		createMessage(front, orders, factory)
		createMessage(orders, catalogue, factory)
		createMessage(carts, user, factory)
		
		assert application.totalData == 10L
		assert application.totalMessages == 10L
	}
	
}
