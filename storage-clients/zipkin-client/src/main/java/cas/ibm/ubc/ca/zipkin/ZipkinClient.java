package cas.ibm.ubc.ca.zipkin;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cas.ibc.ubc.ca.zipkin.pojos.ZipkinSpanPojo;
import cas.ibc.ubc.ca.zipkin.pojos.ZipkinTracePojo;
import cas.ibm.ubc.ca.zipkin.requests.TracesRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZipkinClient {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/

	private static final OkHttpClient client = new OkHttpClient();

	public final String scheme;
	public final String host;
	public final Integer port;
	
	public ZipkinClient(String scheme, String host, Integer port) {
		this.scheme = scheme;
		this.host = host;
		this.port = port;
	}

	public static OkHttpClient getClient() {
		return client;
	}
	
	public List<ZipkinTracePojo> getTraces() {
		TracesRequest tracesRequest = new TracesRequest.Builder(scheme, host,
				port, "zipkin/api/v1/traces")
				.build();

		Request request = tracesRequest.get();

		List<ZipkinTracePojo> traces = null;
		
		try(Response response = getClient().newCall(request).execute()){
			//		System.out.println(response.body().string());
			Gson gson = new Gson();
			Type type = new TypeToken<List<List<ZipkinSpanPojo>>>(){}.getType();

			List<List<ZipkinSpanPojo>> tracesList = gson.fromJson(response.body().string(), type);
			traces = new LinkedList<>();
			for(List<ZipkinSpanPojo>  l : tracesList) {
				traces.add(new ZipkinTracePojo(l));
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
		
		return traces;
	}
	

	public static void main(String[] args) throws Exception {

		ZipkinClient client = new ZipkinClient("http", "192.168.99.100", 30002);
		
		System.out.println(client.getTraces());


	}
}
