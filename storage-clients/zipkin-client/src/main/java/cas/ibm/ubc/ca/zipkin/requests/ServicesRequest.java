package cas.ibm.ubc.ca.zipkin.requests;

public class ServicesRequest extends ZipkinRequest {
	
	static public class  Builder {
		private String scheme;
		private String host;
		private Integer port;
		private String paths;
		
		public Builder(String scheme, String host, Integer port, String paths){
			this.scheme = scheme;
			this.host = host;
			this.port = port;
			this.paths = paths;
		}
		
		public ServicesRequest build() {
			return new ServicesRequest(this);
		}
	}
	
	private ServicesRequest(Builder builder) { 
		urlBuilder.scheme(builder.scheme);
		urlBuilder.host(builder.host);
		urlBuilder.port(builder.port);
		urlBuilder.addPathSegments(builder.paths);
	}
}
