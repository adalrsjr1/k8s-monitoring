package cas.ibc.ubc.ca.zipkin.pojos;

public class ZipkinEndpointPojo {
// teste
	public final String ipv4;
	public final String ipv6;
	public final Integer port;
	public final String serviceName;
	
	public ZipkinEndpointPojo(String ipv4, String ipv6, Integer port, String serviceName) {
		super();
		this.ipv4 = ipv4;
		this.ipv6 = ipv6;
		this.port = port;
		this.serviceName = serviceName;
	}

	public String getIpv4() {
		return ipv4;
	}

	public String getIpv6() {
		return ipv6;
	}

	public Integer getPort() {
		return port;
	}

	public String getServiceName() {
		return serviceName;
	}

	@Override
	public String toString() {
		return "ZipkinEndpointPojo [ipv4=" + ipv4 + ", ipv6=" + ipv6 + ", port=" + port + ", serviceName=" + serviceName
				+ "]";
	}
	
	
}
