package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.zipkin.pogos.Trace
import cas.ibm.ubc.ca.zipkin.util.ZipkinTraceUtil

class ZipkinCache {
	Map<String,List<List<Trace>>> traces
	
	// list of messages ordered by ip
	Map<String, List> messages
	
	ZipkinCache(Map<String,List<List<Trace>>> traces) {
		this.traces = traces
		
		messages = new LinkedHashMap()
		
		def values = traces.values()

		values.each { v ->
			messages << mapTracesByIp(v)
		}		
	}
	
	//TODO: How to get an operation?
	// Is it an operation an aggregation of several annotations
	// of a trace (message)
	
	private Map mapTracesByIp(List<List<Trace>> traces) {
		Map map = new LinkedHashMap()
		traces.each { list ->
			list.each { trace ->
				String ip = ZipkinTraceUtil.getFirstIP(trace)
				Map message = [traceId: trace.traceId, id:trace.id, timestamp:trace.timestamp, duration:trace.duration]
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
	
	List getMessagesByIp(String ip) {
		messages.getOrDefault(ip, [])
	}
	
	List<String> getOperations(String serviceCaller) {
		List<List<Trace>> traces = traces[serviceCaller]
		
		traces.each{trace ->
		
		trace.inject([] as Set) { result, t ->
			println  t.binaryAnnotations.inject([:]) {map, bAnn ->
				map["{$t.duration}B::${bAnn.endpoint.ipv4}[${bAnn.endpoint.port}]"]  = bAnn.value
				map
			}
			println  t.annotations.inject([:]) {map, ann ->
				map["{$t.duration}${ann.timestamp}:${ann.endpoint.ipv4}[${ann.endpoint.port}]"]  = ann.value
				map
			}
			result
		}
		println ""
		}
	}
	
	
		
}
