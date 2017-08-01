package cas.ibm.ubc.ca.zipkin.requests;

public class DependenciesRequest extends ZipkinRequest {
	static public class Builder {
		private final Integer endT;
		private String loopback;
		
		public Builder(int endT) {
			this.endT = endT;
		}
		
		Builder withLoopback(String loopback) {
			this.loopback = loopback;
			return this;
		}
		
		DependenciesRequest build() {
			return new DependenciesRequest(this);
		}
	}
	
	private Integer endT;
	private String loopback;
	
	private DependenciesRequest() { }
	
	private DependenciesRequest(Builder builder) {
		this.endT = builder.endT;
		addQueryParameter("endT", endT);
		this.loopback = builder.loopback;
		addQueryParameter("loopback", loopback);
	}
	
	public Integer getEndT() {
		return endT;
	}

	public String getLoopback() {
		return loopback;
	}
}
