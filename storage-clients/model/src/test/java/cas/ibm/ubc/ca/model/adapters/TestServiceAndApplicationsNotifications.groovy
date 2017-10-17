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
	
}
