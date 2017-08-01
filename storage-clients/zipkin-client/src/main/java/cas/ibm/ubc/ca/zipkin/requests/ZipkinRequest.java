package cas.ibm.ubc.ca.zipkin.requests;

import java.util.Objects;

import okhttp3.HttpUrl;
import okhttp3.Request;

public abstract class ZipkinRequest {

	private Request request;
	
	protected HttpUrl.Builder urlBuilder = new HttpUrl.Builder();
	
	public Request get() {
		
		request = new Request.Builder()
				            .url(urlBuilder.build())
				            .build();
		
		return request;
	}
	
	protected void addQueryParameter(String key, Object value) {
		if(Objects.nonNull(key) && Objects.nonNull(value)) {
			urlBuilder.addQueryParameter(key, value.toString());
		}
	}
	
	protected void addPathSegment(String pathSegment) {
		if(Objects.nonNull(pathSegment)) {
			urlBuilder.addEncodedPathSegment(pathSegment);
		}
	}
	
}
