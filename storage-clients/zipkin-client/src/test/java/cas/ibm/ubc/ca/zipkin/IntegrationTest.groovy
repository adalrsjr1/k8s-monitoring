package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

import cas.ibm.ubc.ca.zipkin.pogos.Message

import com.google.gson.Gson

import java.time.Instant

import java.util.Map
import java.util.concurrent.TimeUnit

class IntegrationTest extends GroovyTestCase {
	private final HOST = 'localhost'
	private final PORT = 9411

	private ZipkinClient client
	private Map clientSpan
	private Map serverSpan
	private ZipkinRequestor requestorTestsHelper

	void setUp() {
		clientSpan = clientSpan()
		serverSpan = serverSpan()
		requestorTestsHelper = new ZipkinRequestor(HOST, PORT, 10,
			TimeUnit.SECONDS)
		requestorTestsHelper.createSpans(spansList())

		client = MessagesInspectionInterfaceFactory.create(HOST, PORT)
	}

	void testReturnsManyMessages() {
		def file = ZipkinRequestorFile.getFile('orders')
		def list = new Gson().fromJson(file, Collection.class)
		list.each { listItem -> requestorTestsHelper.createSpans(listItem) }

		def timeInterval = TimeInterval.create(1512099016063, 1512099216063)
		def messages = fetchMessages(null, timeInterval)

		assert messages.size == 13
		messages.each { message ->
			assert message.timestamp != null
			assert message.totalTime != null
			assert message.totalSize != null
			//assert message.targetIp != null  // db RPCs missing this info
			assert message.targetName != null
			assert message.sourceIp != null
			assert message.sourceName != null
 		}
	}

	void testReturnsNonEmptyMessages() {
		assertFalse(fetchMessages('orders').isEmpty())
	}

	void testReturnsProperlyTypedMessages() {
		def message = fetchMessages('orders').get(0)

		assert message instanceof Message
	}

	void testReturnsProperMessageAttributes() {
		def serverSpanSrEndpoint = serverSpan.annotations.get(0).endpoint
		def clientSpanCsEndpoint = clientSpan.annotations.get(0).endpoint
		def message = fetchMessages('orders').find {
			it.correlationId == clientSpan.traceId
		}

		assert message != null
		assertEquals(clientSpan.traceId, message.correlationId)
		assertEquals(clientSpan.timestamp, message.timestamp)
		assertEquals(clientSpan.duration, message.totalTime)
		assertEquals(clientSpanCsEndpoint.ipv4, message.sourceIp)
		assertEquals(clientSpanCsEndpoint.serviceName, message.sourceName)
		assertEquals(serverSpanSrEndpoint.ipv4, message.targetIp)
		assertEquals(serverSpanSrEndpoint.serviceName, message.targetName)
		assertEquals(284, message.totalSize)
	}

	void testOnlyReturnsMessagesSentFromSpecifiedServiceWithinGivenInterval() {
		assertEquals(3, fetchMessages('orders').size)
	}

	void testReturnsAllMessagesSentFromAnyServiceWithinGivenInterval() {
		assertEquals(4, fetchMessages().size)
	}

	void testReturnsMessageExtractedFromDbSpan() {
		def messages = fetchMessages('orders')
		def dbMessage = messages.find {
			it.targetName == 'orders-db'
		}

		assert dbMessage != null
		assert dbMessage.totalSize == 960
	}

	private fetchMessages(serviceInstance = null, interval = null) {
		def timeInterval = interval ?: TimeInterval.last(60, TimeUnit.SECONDS)

		if(serviceInstance != null)
			client.messages(serviceInstance, timeInterval)
		else
			client.messages(timeInterval)
	}

	private spansList() {
		[
			clientSpan,
			serverSpan,
			anotherClientSpan(),
			olderClientSpan(),
			multipleHostsClientSpan(),
			multipleHostsClientSpanChild(),
			dbSpan()
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
			],
			binaryAnnotations: [
				[
					key: 'http.content-length',
					value: '0'
				],
				[
					key: 'http.response.content-length',
					value: '284'
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
				],
				[
					timestamp: System.currentTimeMillis() * 1000,
					value: 'sr',
					endpoint: [
						serviceName: 'codes',
						ipv4: '10.0.0.6',
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

	private multipleHostsClientSpan() {
		[
			traceId: '1e42f3f728c316fd',
			id: '9ac2c468d01d5bed',
			name: 'orders: get /carts',
			timestamp: (System.currentTimeMillis() - 2000) * 1000,
			duration: 35000,
			annotations: [
				[
					timestamp: (System.currentTimeMillis() - 2000) * 1000,
					value: 'cs',
					endpoint: [
						serviceName: 'orders',
						ipv4: '10.0.0.3',
						port: 80
					]
				],
				[
					timestamp: (System.currentTimeMillis() - 2000) * 1000,
					value: 'sr',
					endpoint: [
						serviceName: 'carts',
						ipv4: '10.0.0.5',
						port: 80
					]
				]
			]
		]
	}

	private multipleHostsClientSpanChild() {
		[
			traceId: '1e42f3f728c316fd',
			id: '390d0dd10700f32d',
			name: 'carts: get /anything',
			parentId: '9ac2c468d01d5bed',
			timestamp: System.currentTimeMillis() * 1000,
			duration: 2792
		]
	}

	private dbSpan() {
		[
			traceId: '804712224c4ed401',
			id: '054a5f71953cc779',
			name: 'crudrepository.save(..)',
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
			],
			binaryAnnotations: [
				[key: "db.query.result.size", value: "545"],
				[key: "db.query.size", value: "415"],
				[key: "peer.address", value: "orders-db:27017"]
			]
		]
	}
}
