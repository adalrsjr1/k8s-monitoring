package cas.ibc.ubc.ca.zipkin.pojos;

public class ZipkinAnnotationPojo {

	public final ZipkinEndpointPojo endpoint;
	public final Long timestamp;
	public final String value;
	
	public ZipkinAnnotationPojo(ZipkinEndpointPojo endpoint, Long timestamp, String value) {
		super();
		this.endpoint = endpoint;
		this.timestamp = timestamp;
		this.value = value;
	}
	
	public ZipkinEndpointPojo getEndpoint() {
		return endpoint;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "ZipkinAnnotationPojo [endpoint=" + endpoint + ", timestamp=" + timestamp + ", value=" + value + "]";
	}
	
}
