package cas.ibm.ubc.ca.zipkin

import cas.ibm.ubc.ca.zipkin.pogos.Annotation
import cas.ibm.ubc.ca.zipkin.pogos.Dependency
import cas.ibm.ubc.ca.zipkin.pogos.Span
import cas.ibm.ubc.ca.zipkin.pogos.Trace

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

import okhttp3.HttpUrl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

class ZipkinRequestor {
	static final MediaType JSON \
		= MediaType.parse('application/json; charset=utf-8')

	private final OkHttpClient httpClient

	final String host
	final int port

	ZipkinRequestor(String host, int port, int timeout, TimeUnit timeUnit) {
		
		httpClient = new OkHttpClient.Builder()
					             .readTimeout(timeout, timeUnit)
								 .writeTimeout(timeout, timeUnit)
								 .build()
		
		this.host = host
		this.port = port
	}
	
	private createRequest(HttpUrl url, body = null) {
		def builder = new Request.Builder().url(url)
		if(body)
			builder.post(body)

		builder.build()
	}

	private HttpUrl createUrl(path) {
		createUrl(path, [:])
	}
	
	private HttpUrl createUrl(path, Map parameters) {
		HttpUrl.Builder builder = new HttpUrl.Builder()
								 .scheme("http")
								 .host(host)
								 .port(port)
								 .addPathSegments('api/v1/')
								 .addPathSegment(path)
		
		builder = parameters.inject(builder) { b, entry ->
			b.addQueryParameter(entry.key, entry.value.toString())
		}
		
		builder.build()
								 
	}

	List<String> getServices() {
		HttpUrl url = createUrl("services")
		Request request = createRequest(url)

		Response response = httpClient.newCall(request).execute()

		String json = response.body().string()
		
		Type type = new TypeToken<List<String>>() {}.getType()
		new Gson().fromJson(json, type)
	}
	
	List<Span> getSpans(String serviceName) {
		HttpUrl url = createUrl("spans",[serviceName:(serviceName)])
		Request request = createRequest(url)

		Response response = httpClient.newCall(request).execute()
		
		String json = response.body().string()
		
		Type type = new TypeToken<List<String>>() {}.getType()
		new Gson().fromJson(json, type)
	}
	
	/**
	 * Named argument function. You can find the arguments doc at
	 * See <a href="http://zipkin.io/zipkin-api/#/default/get_traces</a>
	 * @param args a map of arguments @see<a href="http://zipkin.io/zipkin-api/#/default/get_traces">
	 * @return
	 */
	List<List<Trace>> getTraces(Map args) {
		HttpUrl url = createUrl("traces",args)
		Request request = createRequest(url)

		Response response = httpClient.newCall(request).execute()
		
		String json = response.body().string()
		
		Type type = new TypeToken<Collection<Collection<Trace>>>(){}.getType()
		new Gson().fromJson(json, type)
	}
	
	List<Trace> getTrace(String traceId) {
		HttpUrl url = createUrl("trace", [traceId:(traceId)])
		Request request = createRequest(url)

		Response response = httpClient.newCall(request).execute()
		
		String json = response.body().string()
		
		Type type = new TypeToken<List<String>>() {}.getType()
		new Gson().fromJson(json, type)
	}
	
	List<Trace> getDependencies(Long endTs) {
		HttpUrl url = createUrl("dependencies", [endTs:(endTs)])
		Request request = createRequest(url)

		Response response = httpClient.newCall(request).execute()
		
		String json = response.body().string()
		Type type = new TypeToken<List<Dependency>>() {}.getType()
		new Gson().fromJson(json, type)
	}
	
	void createSpans(List<Span> spansList) {
		def url = createUrl('spans')
		def json = new Gson().toJson(spansList)
		def body = RequestBody.create(JSON, json)
		def request = createRequest(url, body)

		httpClient.newCall(request).execute()
	}
}
