package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Dependency
import cas.ibm.ubc.ca.zipkin.pogos.Span
import cas.ibm.ubc.ca.zipkin.pogos.Trace

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

import org.junit.Before

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class ZipkinRequestorFile {
	private static final ClassLoader classloader = Thread.currentThread().getContextClassLoader()

	static String getFile(String filename, Boolean fullpath) {
		if(!fullpath)
			return classloader.getResource("${filename}-trace.json").getText("UTF-8")
		return classloader.getResource(filename)
	}
	
	static String getFile(String filename) {
		getFile(filename, false)
	}
	
	static List<String> getServices() {
		return ["carts","catalogue","orders","payment","shipping","user"]
	}
	
	static List<List<Trace>> getTraces(String service) {
		String json = getFile(service)
		
		Type type = new TypeToken<Collection<Collection<Trace>>>(){}.getType()
		new Gson().fromJson(json, type)
	}	
	
	public static void main(String[] args) {
		List<List<Trace>> traces = ZipkinRequestorFile.getTraces("orders")
		List<Trace> firstTrace = traces.first()
		
		StringBuffer sb1 = new StringBuffer()
		LinkedHashMap map = new LinkedHashMap()
		firstTrace.inject(map) {m, trace ->
			m[trace.id] = trace.parentId
			println trace.parentId + " " + trace.id
			sb1.append(trace.parentId + " " + trace.id)
			m
		}
		
		println ""
		
		StringBuffer sb2 = new StringBuffer()
		map.each {k,v ->
			sb2.append(v + " " + k)
			println v + " " + k
		}
		
	}
}
