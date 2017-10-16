package cas.ibm.ubc.ca.model.manager

import java.lang.reflect.Type
import java.util.List

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import cas.ibm.ubc.ca.interfaces.ClusterInspectionInterface
import cas.ibm.ubc.ca.interfaces.MessagesInspectionInterface
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class MonitoringClient implements ClusterInspectionInterface, 
	MessagesInspectionInterface, MetricsInspectionInterface{

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8")
		
	private final OkHttpClient httpClient
	private Gson jsonParser
	private final String url
		
	
	
	MonitoringClient(OkHttpClient httpClient, String url) {
		this.httpClient = httpClient
		jsonParser = new Gson()
		this.url = url + "/model"
	}
	
	private Response innerRequest(String path) {
		Request request = new Request.Builder()
									 .url(path)
									 .build()
		
		return httpClient.newCall(request).execute()
	}
	
	private def request(String params) {
		String path = url + params
		Response response = innerRequest(path)
		
		String json = response.body().string()
		
		
		
		def result
		try {
			Type listType = new TypeToken<List>(){}.getType();
			result = jsonParser.fromJson(json, listType)
		}
		catch(Exception) {
			result = jsonParser.fromJson(json, String.class)
		}
		
		return result
	}
	
	@Override
	public List metrics() {
		request("/metrics")
	}

	@Override
	public List metrics(String container) {
		request("/metrics/${container}")
	}

	@Override
	public List messages() {
		request("/messages")
	}

	@Override
	public List messages(String serviceInstance) {
		request("/messages/${serviceInstance}")
	}

	@Override
	public List hosts() {
		request("/hosts")
	}

	@Override
	public List services() {
		request("/services")
	}

	@Override
	public List applications() {
		request("/applications")
	}

	@Override
	public String cluster() {
		request("/cluster")
	}

}
