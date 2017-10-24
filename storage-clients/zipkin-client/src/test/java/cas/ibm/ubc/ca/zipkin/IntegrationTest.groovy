package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.zipkin.pogos.Message

import java.util.List
import java.util.concurrent.TimeUnit

class IntegrationTest extends GroovyTestCase {
	private final HOST = 'localhost'
	private final PORT = 9411

	private ZipkinClient client
	private List fetchedMessages

	void setUp() {
		requestor().createSpans(spansList())

		client = new ZipkinClient(HOST, PORT)
		fetchedMessages = client.getMessages('users', '1h')
	}

	void testReturnsNonEmptyMessages() {
		assertFalse(fetchedMessages.isEmpty())
	}

	void testReturnsProperlyTypedMessages() {
		def message = fetchedMessages.get(0)

		assert message instanceof Message
	}

	void testReturnsProperMessageAttributes() {
		def message = fetchedMessages.get(0)

		assertEquals('804712224c4ed401', message.correlationId)
		assertEquals(1508854219415000, message.timestamp)
		assertEquals(3500, message.totalTime)
	}

	private requestor() {
		new ZipkinRequestor(HOST, PORT, 10, TimeUnit.SECONDS)
	}

	private spansList() {
		[[
			traceId: '804712224c4ed401',
			id: '804712224c4ed401',
			name: 'unknown',
			timestamp: 1508854219415000,
			duration: 3500,
			annotations: [
				[
					timestamp: 1508854219415000,
					endpoint: [
						serviceName: 'users',
						ipv4: '0.0.0.0',
						port: '1234'
					],
					value: 'cs'
				]
			]
		]]
	}
}
