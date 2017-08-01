package cas.ibm.ubc.ca.zipkin.requests;

public class TracesRequest extends ZipkinRequest {
	static public class  Builder {
		private String serviceName;
		private String spanName;
		private String annotationQuery;
		private Integer minDuration;
		private Integer maxDuration;
		private String endT;
		private String loopback;
		private Integer limit;

		private String scheme;
		private String host;
		private Integer port;
		private String paths;
		
		public Builder(String scheme, String host, Integer port, String paths) {
			this.scheme = scheme;
			this.host = host;
			this.port = port;
			this.paths = paths;
		}
		
		public Builder withServiceName(String serviceName) {
			this.serviceName = serviceName;
			return this;
		}
		public Builder withSpanName(String spanName) {
			this.spanName = spanName;
			return this;
		}
		public Builder withAnnotationQuery(String annotationQuery) {
			this.annotationQuery = annotationQuery;
			return this;
		}
		public Builder withMinDuration(Integer minDuration) {
			this.minDuration = minDuration;
			return this;
		}
		public Builder withMaxDuration(Integer maxDuration) {
			this.maxDuration = maxDuration;
			return this;
		}
		public Builder withEndT(String endT) {
			this.endT = endT;
			return this;
		}
		public Builder withLoopback(String loopback) {
			this.loopback = loopback;
			return this;
		}
		public Builder withLimit(Integer limit) {
			this.limit = limit;
			return this;
		}
		
		public TracesRequest build() {
			return new TracesRequest(this);
		}
	}
	
	private String serviceName;
	private String spanName;
	private String annotationQuery;
	private Integer minDuration;
	private Integer maxDuration;
	private String endT;
	private String loopback;
	private Integer limit;
	
	private TracesRequest() { }
	private TracesRequest(Builder builder) {
		urlBuilder.scheme(builder.scheme);
		urlBuilder.host(builder.host);
		urlBuilder.port(builder.port);
		urlBuilder.addEncodedPathSegments(builder.paths);
		
		serviceName = builder.serviceName;
		addQueryParameter("serviceName", serviceName);
		spanName = builder.spanName;
		addQueryParameter("spanName", spanName);
		annotationQuery = builder.annotationQuery;
		addQueryParameter("annotationQuery", annotationQuery);
		minDuration = builder.minDuration;
		addQueryParameter("minDuration", minDuration);
		maxDuration = builder.maxDuration;
		addQueryParameter("maxDuration", maxDuration);
		endT = builder.endT;
		addQueryParameter("endT", endT);
		loopback = builder.loopback;
		addQueryParameter("loopback", loopback);
		limit = builder.limit;
		addQueryParameter("limit", limit);
	}
	
	public String getServiceName() {
		return serviceName;
	}
	public String getSpanName() {
		return spanName;
	}
	public String getAnnotationQuery() {
		return annotationQuery;
	}
	public Integer getMinDuration() {
		return minDuration;
	}
	public Integer getMaxDuration() {
		return maxDuration;
	}
	public String getEndT() {
		return endT;
	}
	public String getLoopback() {
		return loopback;
	}
	public Integer getLimit() {
		return limit;
	}
	
	
}
