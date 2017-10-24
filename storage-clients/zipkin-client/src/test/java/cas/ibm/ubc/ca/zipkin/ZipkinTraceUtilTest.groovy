package cas.ibm.ubc.ca.zipkin

import static org.junit.Assert.*

import java.util.List
import java.util.Map

import org.junit.Test

import cas.ibm.ubc.ca.zipkin.pogos.Trace
import cas.ibm.ubc.ca.zipkin.util.ZipkinTraceUtil
import groovy.util.GroovyTestCase

class ZipkinTraceUtilTest extends GroovyTestCase {
	static Map<String, List<List<Trace>>> traces
	static List<String> services 
	
	static getFirstListOfTraces() {
		traces.values().first()
	}
	
	static getFirstTraceList() {
		getFirstListOfTraces().first()
	}
	
	static getFirstTrace() {
		getFirstTraceList().first()
	}
	
	protected void setUp() throws Exception {
		super.setUp()
		
		services = ZipkinRequestorFile.getServices()
		
		traces = services.inject([:] as LinkedHashMap) { map, service ->
			map[service] = ZipkinRequestorFile.getTraces(service)
			map
		}
	}
	
	void testGetFirstIP() {
		assert ZipkinTraceUtil.getFirstIP(getFirstTrace()) != null
		assert ZipkinTraceUtil.getFirstIP(getFirstTrace()) != ""
		assert ZipkinTraceUtil.getFirstIP(getFirstTrace()) == "172.17.0.11"
		assert ZipkinTraceUtil.getFirstIP(new Trace()) == null
	}
	
//	void test() { 
//		traces.each {k,v ->
//			v.each {
//				println it.size()
//			}
//		}
//	}
	
//	void testGetPath() {
//		traces.each { k,v ->
//			println getFirstTrace().size()
//			getFirstTrace().each { t->
//				t.each {
//					println "# " + it.parentId + " " + it.id + " " + it.annotations.inject([]) {result, a -> result << (" " + a.endpoint.serviceName + " : " + a.endpoint.ipv4)}
//					println "B " + it.parentId + " " + it.id + " " + it.binaryAnnotations.inject([]) {result, a -> result << (" " + a.endpoint.serviceName + " : " + a.endpoint.ipv4)}
//					
//				}
//			}
//			println "\n"
//		}
//	}

//	void testGetPath() {
//		traces.each { k, v ->
//			getFirstListTrace().each { t ->
//				t.each {
//					println ZipkinTraceUtil.getPath(it, v)
//				}
//			}
//			
//		}
//	}
//	
//		
//	void testGetOperation() {
//		traces.each { k,v ->
//			v.each { t->
//				t.each {
//					ZipkinTraceUtil.getOperation(ZipkinTraceUtil.getPath(it, k))
//				}
//				println ""
//			}
//			println "\n"
//		}
//		
//	}

}
