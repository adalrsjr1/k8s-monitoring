package cas.ibm.ubc.ca.zipkin.requests;

public class TraceRequest extends ZipkinRequest {
	static public class  Builder {
		
		private String traceId;

		private String scheme;
		private String host;
		private Integer port;
		private String paths;
		
		public Builder(String scheme, String host, Integer port, String paths,
				String traceId) {
			this.scheme = scheme;
			this.host = host;
			this.port = port;
			this.paths = paths;
			this.traceId = traceId;
		}
		
		public TraceRequest build() {
			return new TraceRequest(this);
		}
	}
	
	private String traceId;
	
	private TraceRequest() { }
	
	private TraceRequest(Builder builder) {
		urlBuilder.scheme(builder.scheme);
		urlBuilder.host(builder.host);
		urlBuilder.port(builder.port);
		urlBuilder.addPathSegments(builder.paths);
		traceId = builder.traceId;
		addPathSegment(traceId);
	}

	public String getTraceId() {
		return traceId;
	}
	
}
