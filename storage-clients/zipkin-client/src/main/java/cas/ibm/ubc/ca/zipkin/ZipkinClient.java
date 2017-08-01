package cas.ibm.ubc.ca.zipkin;

import cas.ibm.ubc.ca.zipkin.requests.TracesRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ZipkinClient {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/
	
	static final OkHttpClient client = new OkHttpClient();
	
	public ZipkinClient() {
	}
	
	public static OkHttpClient getClient() {
		return client;
	}

	
	
	public static void main(String[] args) throws Exception {
		
		TracesRequest tracesRequest = new TracesRequest.Builder("http", "192.168.99.100",
				30002, "zipkin/api/v1/traces")
				                      .build();
		
		Request request = tracesRequest.get();
		
		Response response = ZipkinClient.getClient().newCall(request).execute();
		
		System.out.println(response.body().string());
		
	}
}
