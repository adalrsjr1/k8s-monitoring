package cas.ibm.ubc.ca.zipkin;

import java.util.List

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

import cas.ibm.ubc.ca.zipkin.builders.MessageBuilder
import cas.ibm.ubc.ca.zipkin.decorators.TraceDecorator
import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Endpoint
import cas.ibm.ubc.ca.zipkin.pogos.Message
import cas.ibm.ubc.ca.zipkin.pogos.Trace

public class ZipkinClient implements MessagesInspectionInterface {
	final static String \
		CLIENT_SEND_ANNOTATION = 'cs',
		DB_QUERY_SIZE_ANNOTATION = 'db.query.size',
		DB_QUERY_RESULT_SIZE_ANNOTATION = 'db.query.result.size',
		HTTP_REQUEST_CONTENT_LENGTH_ANNOTATION = 'http.content-length',
		HTTP_RESPONSE_CONTENT_LENGTH_ANNOTATION = 'http.response.content-length',
		PEER_ADDRESS_ANNOTATION = 'peer.address',
		SERVER_RECEIVE_ANNOTATION = 'sr'

	private ZipkinRequestor requestor
	private int maxTracesPerRequest = 1000
	private static final int DEFAULT_REQUESTOR_TIMEOUT = 10

	public ZipkinClient(host, port, timeout, maxTracesPerRequest, TimeUnit timeunit) {
		requestor = new ZipkinRequestor(host, port, timeout, timeunit)
		this.maxTracesPerRequest = maxTracesPerRequest
	}
	
	public ZipkinClient(host, port, maxTracesPerRequest = 1000) {
		this(host, port, DEFAULT_REQUESTOR_TIMEOUT, maxTracesPerRequest, TimeUnit.SECONDS)
	}

	@Override
	public List messages(TimeInterval timeInterval) {
		messages(null, timeInterval)
	}

	@Override
	public List messages(String serviceInstance, TimeInterval timeInterval) {
		def tracesList = requestor.getTraces(toParams(timeInterval))
		def messages = []

		tracesList.each { trace ->
			trace.each { span ->
				span = new TraceDecorator(span)
				def clientSendAnnotation = span.clientSendAnnotation
				if(!clientSendAnnotation || serviceInstance && \
					clientSendAnnotation?.serviceName() != serviceInstance)
					return

				def childSpan = trace.find { it.parentId == span.id }
				childSpan = new TraceDecorator(childSpan)
				messages << new MessageBuilder(span, childSpan).build()
			}
		}

		messages
	}

	private Map toParams(timeInterval) {
		[
			endTs: timeInterval.getEnd(),
			lookback: timeInterval.getIntervalInMillis(),
			limit: maxTracesPerRequest
		]
	}
}
