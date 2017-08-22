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
class Dependency {

	@SerializedName("parent")
	@Expose
	String parent;
	@SerializedName("child")
	@Expose
	String child;
	@SerializedName("callCount")
	@Expose
	Integer callCount;
	@SerializedName("errorCount")
	@Expose
	Integer errorCount;

}