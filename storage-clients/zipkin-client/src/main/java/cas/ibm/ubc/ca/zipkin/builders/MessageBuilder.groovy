package cas.ibm.ubc.ca.zipkin.builders

import cas.ibm.ubc.ca.zipkin.interfaces.ISpan
import cas.ibm.ubc.ca.zipkin.pogos.Message
import cas.ibm.ubc.ca.zipkin.pogos.Trace

class MessageBuilder {
	private ISpan span
	private ISpan childSpan
	private Message message

	MessageBuilder(span, childSpan) {
		this.span = span
		this.childSpan = childSpan
		this.message = new Message()
	}

	Message build() {
		message.correlationId = span.traceId
		message.timestamp = span.timestamp
		message.totalTime = span.duration
		message.sourceIp = span.clientSendAnnotation.endpoint.ipv4
		message.sourceName = span.clientSendAnnotation.endpoint.serviceName

		fetchTotalSizeFromDbQueryResultSizeAnnotation()
		fetchTotalSizeFromContentLengthAnnotations()

		def serverEndpoint = span.serverEndpoint ?: childSpan.serverEndpoint
		message.targetIp = serverEndpoint?.ipv4
		message.targetName = serverEndpoint?.serviceName
		fetchTargetNameFromPeerAddressAnnotation()

		message
	}

	private void fetchTargetNameFromPeerAddressAnnotation() {
		if(message.targetName || span.peerAddress == null)
			return

		message.targetName = span.peerAddress.split(':')[0]
	}

	private void fetchTotalSizeFromDbQueryResultSizeAnnotation() {
		if(span.dbQueryResultSize == null)
			return

		message.totalSize = Long.valueOf(span.dbQueryResultSize)
	}

	private void fetchTotalSizeFromContentLengthAnnotations() {
		if(message.totalSize || span.requestSize == null || span.responseSize == null)
			return

		message.totalSize = span.requestSize + span.responseSize
	}
}
