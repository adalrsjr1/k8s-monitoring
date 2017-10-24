package cas.ibm.ubc.ca.zipkin.pogos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import groovy.transform.Canonical
import groovy.transform.ToString
/*
 * Generated automatically
 *  # http://www.jsonschema2pojo.org/
 *  # http://zipkin.io/zipkin-api/#/default/
 */
@ToString(includeNames=true)
class Endpoint {

	@SerializedName("serviceName")
	@Expose
	String serviceName;
	@SerializedName("ipv4")
	@Expose
	String ipv4;
	@SerializedName("ipv6")
	@Expose
	String ipv6;
	@SerializedName("port")
	@Expose
	Integer port;

}