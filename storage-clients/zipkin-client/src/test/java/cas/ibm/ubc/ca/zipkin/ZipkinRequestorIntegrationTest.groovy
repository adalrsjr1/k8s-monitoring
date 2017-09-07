package cas.ibm.ubc.ca.zipkin

import java.util.concurrent.TimeUnit

class ZipkinRequestorIntegrationTest extends GroovyTestCase {
	private ZipkinRequestor requestor;
	private spansList;

	void setUp() {
		requestor = new ZipkinRequestor('localhost', 9411, 10, TimeUnit.SECONDS)
		spansList = [[
			traceId: '804712224c4ed401',
			id: '804712224c4ed401',
			name: 'unknown',
			annotations: [
				[
					endpoint: [
						serviceName: 'users',
						ipv4: '0.0.0.0',
						port: '1234'
					],
					timestamp: 0,
					value: 'sr'
				]
			]
		]]
	}

	void testCreateSpan() {
		requestor.createSpans(spansList)

		def createdSpans = requestor.getSpans('users')

		assertNotNull createdSpans
		assert createdSpans.size() != 0
	}
}
