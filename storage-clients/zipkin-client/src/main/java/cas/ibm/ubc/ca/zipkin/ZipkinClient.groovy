package cas.ibm.ubc.ca.zipkin;

import java.util.List

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Message
import cas.ibm.ubc.ca.zipkin.pogos.Trace

public class ZipkinClient implements MessagesInspectionInterface {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/

	private ZipkinRequestor requestor;

	public ZipkinClient(host, port) {
		requestor = new ZipkinRequestor(host, port, 10,	TimeUnit.SECONDS)
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

				def childSpan = trace.find { it.parentId == span.id }
				if(childSpan) {
					def endpoint = childSpan.annotations.find {
						it.value == 'sr'
					}['endpoint']
					message.targetIp = endpoint['ipv4']
					message.targetName = endpoint['serviceName']
				}

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
	
	public static void main(String[] args) throws Exception {
		
		ZipkinRequestor requestor = new ZipkinRequestor("192.168.99.100", 30002, 10, TimeUnit.SECONDS)
		
		def services = requestor.getServices()
		println services
//
//		ExecutorService tPool = Executors.newFixedThreadPool(1)
//		
//		Map mapTraces = [:]
//		
//		CountDownLatch latch = new CountDownLatch(services.size())
//		
//		services.each { serviceName ->
//			tPool.execute {
////				mapTraces[serviceName] = requestor.getTraces(serviceName:(serviceName),limit:10) 
//			requestor.getTraces(serviceName:(serviceName),limit:10).each {
//			//			 it.each{
////						  println "${it.parentId}:${it.id}:${it.name} :: ${it.annotations}"
////						  print serviceName + "-->"
////						  printTrace(it)
//						  println it
////						}
//					}
////					latch.countDown()
//			}
//		}
//		
////		latch.await()
////		tPool.shutdown()
////		println mapTraces
//									 
	}
}
