package cas.ibm.ubc.ca.zipkin;

import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Trace

public class ZipkinClient {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/


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
