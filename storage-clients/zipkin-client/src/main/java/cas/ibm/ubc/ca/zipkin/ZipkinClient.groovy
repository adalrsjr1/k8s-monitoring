package cas.ibm.ubc.ca.zipkin;

import java.util.List

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Endpoint
import cas.ibm.ubc.ca.zipkin.pogos.Message
import cas.ibm.ubc.ca.zipkin.pogos.Trace

public class ZipkinClient implements MessagesInspectionInterface {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/

	private ZipkinRequestor requestor;

	public ZipkinClient(host, port, timeout, TimeUnit timeunit) {
		requestor = new ZipkinRequestor(host, port, timeout, timeunit)
	}
	
	public ZipkinClient(host, port) {
		this(host, port, 10, TimeUnit.SECONDS)
	}

	@Override
	public List messages(TimeInterval timeInterval) {
		messages(null, timeInterval)
	}

	@Override
	public List messages(String serviceInstance, TimeInterval timeInterval) {
		def params = [
			endTs: timeInterval.getEnd(),
			lookback: timeInterval.getIntervalInMillis()
		]
		def tracesList = requestor.getTraces(params)
		def messages = []

		tracesList.each { trace ->
			trace.each { span ->
				def clientSendAnnotation = span.annotations.find {
					it.value == 'cs'
				}
				if(!clientSendAnnotation || serviceInstance && \
					clientSendAnnotation?.serviceName() != serviceInstance)
					return

				def message = new Message()
				message.correlationId = span.traceId
				message.timestamp = span.timestamp
				message.totalTime = span.duration
				message.sourceIp = clientSendAnnotation.endpoint.ipv4
				message.sourceName = clientSendAnnotation.endpoint.serviceName

				// missing: message.totalSize

				def serverEndpoint = findServerEndpoint(span, trace)
				message.targetIp = serverEndpoint?.ipv4
				message.targetName = serverEndpoint.serviceName

				messages << message
			}
		}

		messages
	}

	public static getServiceName(List annotations) {
		annotations.inject([]) { result, a ->
			
			def m = [serviceName:(a.endpoint.serviceName)]
//			m["ip"] = (a.endpoint.ipv4)
			m["value"] = a.value
			result << m
		}
	}
	
	public static printTrace(List<Trace> trace) {
		trace.each { Trace t->

			print "[${getServiceName(t.annotations)}][${getServiceName(t.binaryAnnotations)}] -> "
		}
		println ""
	}
	
//	public static void main(String[] args) throws Exception {
//		
//		ZipkinClient client = new ZipkinClient("10.66.66.32", 30002, 10000, TimeUnit.SECONDS)
//		println client.messages(TimeInterval.last(2, TimeUnit.DAYS))				 
//	}

	private Endpoint findServerEndpoint(span, trace) {
		def childSpan = trace.find { it.parentId == span.id }
		def spanServerEndpoint = span.getServerEndpoint()
		def childSpanServerEndpoint = childSpan?.getServerEndpoint()

		spanServerEndpoint ?: childSpanServerEndpoint
	}
}
