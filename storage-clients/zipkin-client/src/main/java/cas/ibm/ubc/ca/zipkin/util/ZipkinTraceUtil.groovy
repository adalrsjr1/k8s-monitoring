package cas.ibm.ubc.ca.zipkin.util

import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.BinaryAnnotation
import cas.ibm.ubc.ca.zipkin.pogos.Trace

class ZipkinTraceUtil {

	static String getFirstIP(Trace trace) {
		if(!trace) return null
		String ipv4 = trace?.getAnnotations()?.first()?.getEndpoint()?.getIpv4() 
		
		if(!ipv4) {
			ipv4 = trace?.getBinaryAnnotations()?.first()?.getEndpoint()?.getIpv4()
		}
		
		return ipv4
	}
	
	static Map getPath(Trace trace, String serviceName) {
		Map m = trace.getAnnotations().inject([:] as LinkedHashMap) { result, annotation ->
			def map = [:]
			map["traceId"] = trace.getTraceId()
			map["parentId"] = trace.getParentId()
			map["isSource"] = (trace.getParentId() == null)
			map["id"] = trace.getId()
			map["duration"] = trace.getDuration()
			map["endpoint"] = annotation.getEndpoint().getIpv4()
			map["port"] = annotation.getEndpoint().getPort()
			map["service"] = annotation.getEndpoint().getServiceName()
			map["timestamp"] = trace.getTimestamp()
			 
			map[annotation.hashCode()] = [annotation.getValue()]
			
			result[serviceName] = map
			result
		}
		
		trace.getBinaryAnnotations().inject(m as LinkedHashMap) { result, annotation ->
			
			def map = result[serviceName]
			if(map == null) {
				map = [(serviceName):[:]]
			}
			map[(annotation.getKey())] << annotation.getValue()
			result
		}
	}
	
	static String getOperation(Map map) {
		println map
//		map.values().find { 
//			println it.endpoint + " " + it.key + " " + it.value
//		}
	}
}
