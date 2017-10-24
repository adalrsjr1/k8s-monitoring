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
@Canonical
@ToString(includeNames=true)
class Annotation {

	@SerializedName("endpoint")
	@Expose
	Endpoint endpoint;
	@SerializedName("timestamp")
	@Expose
	Long timestamp;
	@SerializedName("value")
	@Expose
	String value;

}