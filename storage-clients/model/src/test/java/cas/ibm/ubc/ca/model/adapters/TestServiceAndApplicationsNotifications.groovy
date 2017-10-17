package cas.ibm.ubc.ca.model.adapters

import static org.junit.Assert.*

import org.junit.Test
import model.Application
import model.Message
import model.Service
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

	void testApplicationNotification() {
		ModelFactoryAdapter factory = ModelFactoryAdapter.getINSTANCE()
		
		Application application = factory.createApplication()
		
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
		
		application.services ["service1"] = service
		assert application.totalData == 9L
		assert application.totalMessages == 4L
		
		service = factory.createServiceInstance()
		
		assert service != null
		
		assert service.totalMessages == 0L
		
		application.services ["service2"] = service
		
		m = factory.createMessage()
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
		
		assert application.totalData == 18L
		assert application.totalMessages == 8L
		
	}
	
}
