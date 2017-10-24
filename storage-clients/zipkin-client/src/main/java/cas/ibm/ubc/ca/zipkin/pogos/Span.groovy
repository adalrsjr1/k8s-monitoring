package cas.ibm.ubc.ca.zipkin.pogos;

import java.util.List;
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
class Span {

	@SerializedName("traceId")
	@Expose
	String traceId;
	@SerializedName("name")
	@Expose
	String name;
	@SerializedName("parentId")
	@Expose
	String parentId;
	@SerializedName("id")
	@Expose
	String id;
	@SerializedName("timestamp")
	@Expose
	Long timestamp;
	@SerializedName("duration")
	@Expose
	Integer duration;
	@SerializedName("debug")
	@Expose
	Boolean debug;
	@SerializedName("annotations")
	@Expose
	List<Annotation> annotations = null;
	@SerializedName("binaryAnnotations")
	@Expose
	List<BinaryAnnotation> binaryAnnotations = null;
}