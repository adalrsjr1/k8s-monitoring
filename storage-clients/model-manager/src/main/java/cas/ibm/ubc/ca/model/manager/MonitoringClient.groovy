package cas.ibm.ubc.ca.model.manager

import java.lang.reflect.Type
import java.util.List
import java.util.Map

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.Response

class MonitoringClient implements ClusterInspectionInterface, 
	MessagesInspectionInterface, MetricsInspectionInterface{

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8")
		
	private final OkHttpClient httpClient
	private Gson jsonParser
	
	private final String host
	private final int port	
	
	
	MonitoringClient(OkHttpClient httpClient, String host, int port) {
		this.httpClient = httpClient
		jsonParser = new Gson()
		
		this.host = host
		this.port = port
	}
	
	private Response innerRequest(String pathSegment, Map query) {
		HttpUrl.Builder url = new HttpUrl.Builder()		
		                         .scheme("http")
								 .host(host)
								 .port(port)
		                         .addPathSegment("model")
								 .addPathSegments(pathSegment);
								 println query
		query.each { k, v -> 
			
			url.addQueryParameter(k, v)
		}
		println url.build()
		Request request = new Request.Builder()
									 .url(url.build())
									 .build()
											 
		return httpClient.newCall(request).execute()
	}	
	
	private def request(String pathSegment, Map query, Type type) {
		String path = pathSegment
		Response response = innerRequest(path, query)
		
		String json = response.body().string()
		
		def result = jsonParser.fromJson(json, type)
		
		return result
	}
	
	@Override
	public List hosts() {
		request("hosts", [:], new TypeToken<List>(){}.getType())
	}

	@Override
	public List services() {
		request("services", [:], new TypeToken<List>(){}.getType())
	}

	@Override
	public Map<String, Float> applications() {
		request("applications", [:], new TypeToken<Map>(){}.getType())
	}

	@Override
	public String cluster() {
		request("cluster", [:], new TypeToken<String>(){}.getType())
	}

	@Override
	public Map<String, Double> metricsService(String measurement, TimeInterval timeInterval) {
		request("metrics/service", 
			["measurement": measurement, "timeInterval": timeInterval], 
			new TypeToken<String>(){}.getType())
	}

	@Override
	public Map<String, Double> metricsHost(String measurement, TimeInterval timeInterval) {
		request("metrics/host/{host}/{measurement}/{timeInterval}", [:], new TypeToken<String>(){}.getType())
	}

	@Override
	public Double metricService(String id, String measurement, TimeInterval timeInterval) {
		request("metrics/service/{service}/{measurement}/{timeInterval}", [:], new TypeToken<String>(){}.getType())
	}

	@Override
	public Double metricHost(String id, String measurement, TimeInterval timeInterval) {
		request("metrics/host/{host}/{measurement}/{timeInterval}", [:], new TypeToken<String>(){}.getType())
	}

	@Override
	public List messages(TimeInterval timeInterval) {
		request("messages/{timeInterval}", [:], new TypeToken<String>(){}.getType())
	}

	@Override
	public List messages(String serviceInstance, TimeInterval timeInterval) {
		request("messages/{service}/{timeInterval}", [:], new TypeToken<String>(){}.getType())
	}

}
