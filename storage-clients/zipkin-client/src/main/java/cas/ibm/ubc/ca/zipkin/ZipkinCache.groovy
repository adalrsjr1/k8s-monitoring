package cas.ibm.ubc.ca.zipkin

import java.util.concurrent.TimeUnit

import cas.ibm.ubc.ca.zipkin.pogos.BinaryAnnotation
import cas.ibm.ubc.ca.zipkin.pogos.Trace
import cas.ibm.ubc.ca.zipkin.util.ZipkinTraceUtil

// http://cloud.spring.io/spring-cloud-static/spring-cloud-sleuth/1.2.0.RELEASE/
class ZipkinCache {
	Map<String,List<List<Trace>>> traces
	
	// list of messages ordered by ip
	Map<String, List> messages
	
	Map<String, Set> operations = [:]
	
	private static ZipkinCache INSTANCE 
	
	ZipkinCache(Map<String,List<List<Trace>>> traces) {
		this.traces = traces
		
		messages = new LinkedHashMap()
		
		def values = traces.values()

		values.each { v ->
			messages << mapTracesByIp(v)
		}		
	}
	
	public static ZipkinCache zipkinCache() {
		if(!INSTANCE) {
			ZipkinRequestor requestor = new ZipkinRequestor("192.168.99.100", 30002, 10, TimeUnit.SECONDS)
			
			INSTANCE = new ZipkinCache(requestor.getTraces([limit:100000]))
		}
	}
	
	//TODO: How to get an operation?
	// Is it the operation an aggregation of several annotations
	// of a trace (message)
	
	private Map mapTracesByIp(List<List<Trace>> traces) {
		Map map = new LinkedHashMap()
		traces.each { list ->
			list.each { trace ->
				String ip = ZipkinTraceUtil.getFirstIP(trace)
				Map message = [traceId: trace.traceId, id:trace.id,
					timestamp:trace.timestamp, duration:trace.duration,
					message:getMessagePath(trace.binaryAnnotations)]
				
				if(map.containsKey(ip)) {
					map[ip] << message
				}
				else {
					map[ip] = [message]
				}
			}
		}
		
		return map
		
	}
	
	private Map getMessagePath(Collection<BinaryAnnotation> annotations) {
		annotations.inject([:]) { result, annotation ->
			if(annotation.key ==~ /http.*/ || annotation.key ==~ /error.*/) {
				result[annotation.key] = annotation.value
			}
			result
		}
	}
	
	List getMessagesByIp(String ip) {
		messages.getOrDefault(ip, [])
	}
	
	Set getOperationByServiceName(String ip) {
		List messages = getMessagesByIp(ip)
		
		messages.inject([] as Set) { result, message ->
			result << message.message
			result
		}
		
	}
	
	
		
}
