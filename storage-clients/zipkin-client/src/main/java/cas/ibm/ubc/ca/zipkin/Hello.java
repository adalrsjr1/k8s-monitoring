package cas.ibm.ubc.ca.zipkin;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Hello {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/
	public static void main(String[] args) throws Exception {
		OkHttpClient client = new OkHttpClient();
		
		Request request = new Request.Builder()
									 .url("http://192.168.99.100:30002/zipkin/api/v1/traces")
				                     .build();
		
		Response response = client.newCall(request).execute();
		
		System.out.println(response.body().string());
		
	}
}
