package cas.ibm.ubc.ca.zipkin.requests;

public class SpansRequest extends ZipkinRequest {

	static public class  Builder {
		private String serviceName;

		private String scheme;
		private String host;
		private Integer port;
		private String paths;
		
		public Builder(String scheme, String host, Integer port, String paths,
				String serviceName) {
			this.serviceName = serviceName;
			this.scheme = scheme;
			this.host = host;
			this.port = port;
			this.paths = paths;
		}
		
		SpansRequest build() {
			return new SpansRequest(this);
		}
	}
	
	private String serviceName;
	
	private SpansRequest() { }
	
	private SpansRequest(Builder builder) {
		urlBuilder.scheme(builder.scheme);
		urlBuilder.host(builder.host);
		urlBuilder.port(builder.port);
		urlBuilder.addPathSegments(builder.paths);
		serviceName = builder.serviceName;
		addQueryParameter("serviceName", serviceName);
	}

	public String getServiceName() {
		return serviceName;
	}
	
}
