package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

import cas.ibm.ubc.ca.zipkin.pogos.Message

import java.time.Instant

import java.util.Map
import java.util.concurrent.TimeUnit

class IntegrationTest extends GroovyTestCase {
	private final HOST = 'localhost'
	private final PORT = 9411

	private ZipkinClient client
	private Map clientSpan
	private Map serverSpan

	void setUp() {
		clientSpan = clientSpan()
		serverSpan = serverSpan()
		requestor().createSpans(spansList())

		client = MessagesInspectionInterfaceFactory.create(HOST, PORT)
	}

	void testReturnsNonEmptyMessages() {
		assertFalse(fetchMessages('orders').isEmpty())
	}

	void testReturnsProperlyTypedMessages() {
		def message = fetchMessages('orders').get(0)

		assert message instanceof Message
	}

	void testReturnsProperMessageAttributes() {
		def serverSpanSrEndpoint = serverSpan['annotations'].get(0)['endpoint']
		def message = fetchMessages('orders').get(0)

		assertEquals(clientSpan['traceId'], message.correlationId)
		assertEquals(clientSpan['timestamp'], message.timestamp)
		assertEquals(clientSpan['duration'], message.totalTime)
		assertEquals(serverSpanSrEndpoint['ipv4'], message.targetIp)
		assertEquals(serverSpanSrEndpoint['serviceName'], message.targetName)
	}

	void testOnlyReturnsMessagesSentFromSpecifiedServiceWithinGivenInterval() {
		assertEquals(1, fetchMessages('orders').size)
	}

	void testReturnsAllMessagesSentFromAnyServiceWithinGivenInterval() {
		assertEquals(2, fetchMessages().size)
	}

	private fetchMessages(serviceInstance = null) {
		def timeInterval = TimeInterval.last(60, TimeUnit.SECONDS)

		if(serviceInstance != null)
			client.messages(serviceInstance, timeInterval)
		else
			client.messages(timeInterval)
	}

	private requestor() {
		new ZipkinRequestor(HOST, PORT, 10, TimeUnit.SECONDS)
	}

	private spansList() {
		[
			clientSpan,
			serverSpan,
			anotherClientSpan(),
			olderClientSpan()
		]
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

	private olderClientSpan() {
		def fiveHoursAgo = Instant.now()
								  .minusSeconds(18000)
								  .toEpochMilli() * 1000

		[
			traceId: '595f042cde9be1ee',
			id: '75b7ea54c19fb18a',
			name: 'orders: get /users',
			timestamp: fiveHoursAgo,
			duration: 100,
			annotations: [
				[
					timestamp: fiveHoursAgo,
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
}
