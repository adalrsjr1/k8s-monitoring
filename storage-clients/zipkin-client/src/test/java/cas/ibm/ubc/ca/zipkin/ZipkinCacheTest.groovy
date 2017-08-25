package cas.ibm.ubc.ca.zipkin

import java.awt.AWTKeyStroke
import java.awt.Component.BaselineResizeBehavior
import java.io.ObjectInputStream.FilterValues
import java.util.concurrent.ConcurrentSkipListMap.KeySet

import cas.ibm.ubc.ca.zipkin.pogos.Trace

class ZipkinCacheTest extends GroovyTestCase {
	Map<String, List<List<Trace>>> traces
	List<String> services 
	
	protected void setUp() throws Exception {
		super.setUp()
		
		services = ZipkinRequestorFile.getServices()
		
		traces = services.inject([:]) { map, service ->
			map[service] = ZipkinRequestorFile.getTraces(service)
			map
		}
		
	}
	
	void testZipkinCacheInitialization() {
		ZipkinCache cache = new ZipkinCache(traces)
		
		assert cache.getTraces().isEmpty() == false
		assert cache.getTraces().keySet().size() == 6
		assert cache.getMessages().isEmpty() == false
	}
	
	void testMapTracesByIp() {
		ZipkinCache cache = new ZipkinCache(traces)
		Map map = cache.mapTracesByIp(traces.values().first())
		assert map.isEmpty() == false
		assert map.keySet() == ["172.17.0.11", "127.0.0.1", "172.17.0.18", "172.17.0.8"] as Set
		assert map.values().first().first().keySet() == ["traceId", "id", "timestamp", "duration"] as Set
	}
	
	void testGetMessagesByIp() {
		ZipkinCache cache = new ZipkinCache(traces)
		assert cache.getMessagesByIp("0.0.0.0") == []
		assert cache.getMessagesByIp("172.17.0.11").isEmpty() == false
		println cache.getMessagesByIp("172.17.0.11")
	}

}
