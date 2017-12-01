package cas.ibm.ubc.ca.zipkin.decorators

import static cas.ibm.ubc.ca.zipkin.ZipkinClient.*

import cas.ibm.ubc.ca.zipkin.interfaces.ISpan
import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Trace
import cas.ibm.ubc.ca.zipkin.pogos.Endpoint

class TraceDecorator implements ISpan {
	@Delegate Trace trace

	private Endpoint serverEndpoint
	private Annotation clientSendAnnotation
	private Long dbQueryResultSize
	private Long dbQuerySize
	private Long requestSize
	private Long responseSize
	private String peerAddress

	TraceDecorator(trace) {
		if(trace) {
			this.trace = trace
			this.serverEndpoint = findServerEndpoint()
			this.clientSendAnnotation = findClientSendAnnotation()
			this.dbQueryResultSize = findDbQueryResultSize()
			this.dbQuerySize = findDbQuerySize()
			this.requestSize = findRequestContentLength()
			this.responseSize = findResponseContentLength()
			this.peerAddress = findPeerAddress()
		}
	}

	private Endpoint findServerEndpoint() {
		findAnnotation(SERVER_RECEIVE_ANNOTATION)?.endpoint
	}

	private Annotation findClientSendAnnotation() {
		findAnnotation(CLIENT_SEND_ANNOTATION)
	}

	private Long findDbQueryResultSize() {
		findAndParseBinaryAnnotationValueToLong(DB_QUERY_RESULT_SIZE_ANNOTATION)
	}

	private Long findDbQuerySize() {
		findAndParseBinaryAnnotationValueToLong(DB_QUERY_SIZE_ANNOTATION)
	}

	private Long findRequestContentLength() {
		findAndParseBinaryAnnotationValueToLong(HTTP_REQUEST_CONTENT_LENGTH_ANNOTATION)
	}

	private Long findResponseContentLength() {
		findAndParseBinaryAnnotationValueToLong(HTTP_RESPONSE_CONTENT_LENGTH_ANNOTATION)
	}

	private Long findAndParseBinaryAnnotationValueToLong(String annotation) {
		def value = findBinaryAnnotationValue(annotation)
		if(value)
			value = Long.valueOf(value)

		value
	}

	private String findPeerAddress() {
		findBinaryAnnotationValue(PEER_ADDRESS_ANNOTATION)
	}

	private Annotation findAnnotation(String annotation) {
		trace.annotations?.find { it.value == annotation }
	}

	private String findBinaryAnnotationValue(String annotation) {
		trace.binaryAnnotations.find { it.key == annotation }?.value
	}
}
