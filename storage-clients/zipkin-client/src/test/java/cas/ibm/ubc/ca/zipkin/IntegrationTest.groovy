package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.zipkin.pogos.Message

import java.util.List
import java.util.Map
import java.util.concurrent.TimeUnit

class IntegrationTest extends GroovyTestCase {
	private final HOST = 'localhost'
	private final PORT = 9411

	private ZipkinClient client
	private Map clientSpan
	private Map serverSpan
	private List fetchedMessages

	void setUp() {
		clientSpan = clientSpan()
		serverSpan = serverSpan()
		requestor().createSpans([clientSpan, serverSpan, anotherClientSpan()])

		client = new ZipkinClient(HOST, PORT)
		fetchedMessages = client.getMessages('orders', '1h')
	}

	void testReturnsNonEmptyMessages() {
		assertFalse(fetchedMessages.isEmpty())
	}

	void testReturnsProperlyTypedMessages() {
		def message = fetchedMessages.get(0)

		assert message instanceof Message
	}

	void testReturnsProperMessageAttributes() {
		def serverSpanSrEndpoint = serverSpan['annotations'].get(0)['endpoint']
		def message = fetchedMessages.get(0)

		assertEquals(clientSpan['traceId'], message.correlationId)
		assertEquals(clientSpan['timestamp'], message.timestamp)
		assertEquals(clientSpan['duration'], message.totalTime)
		assertEquals(serverSpanSrEndpoint['ipv4'], message.targetIp)
		assertEquals(serverSpanSrEndpoint['serviceName'], message.targetName)
	}

	void testOnlyReturnsMessagesSentFromSpecifiedService() {
		assertEquals(1, fetchedMessages.size)
	}

	private requestor() {
		MessagesInspectionInterfaceFactory.create(HOST, PORT, 10, TimeUnit.SECONDS)
		//new ZipkinRequestor(HOST, PORT, 10, TimeUnit.SECONDS)
	}

	private clientSpan() {
		[
			traceId: '804712224c4ed401',
			id: '92cc834a81da72f6',
			name: 'orders: get /users',
			timestamp: System.currentTimeMillis() * 1000,
			duration: 35000,
			annotations: [
				[
					timestamp: System.currentTimeMillis() * 1000,
					value: 'cs',
					endpoint: [
						serviceName: "orders",
						ipv4: "10.0.0.3",
						port: 80
					]
				]
			]
		]
	}

	private serverSpan() {
		[
			traceId: '804712224c4ed401',
			id: '0de9781fc4681a8b',
			name: 'users: get /users',
			parentId: '92cc834a81da72f6',
			timestamp: System.currentTimeMillis() * 1000,
			duration: 1450,
			annotations: [
				[
					timestamp: System.currentTimeMillis() * 1000,
					value: 'sr',
					endpoint: [
						serviceName: "user",
						ipv4: "10.0.0.4",
						port: 80
					]
				]
			]
		]
	}

	private anotherClientSpan() {
		[
			traceId: '804712224c4ed401',
			id: '1167542df84f066b',
			name: 'users: get /codes',
			timestamp: System.currentTimeMillis() * 1000,
			duration: 35000,
			annotations: [
				[
					timestamp: System.currentTimeMillis() * 1000,
					value: 'cs',
					endpoint: [
						serviceName: "user",
						ipv4: "10.0.0.4",
						port: 80
					]
				]
			]
		]
	}
}
