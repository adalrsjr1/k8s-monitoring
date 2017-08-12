package cas.ibm.ubc.ca.influx.pojos

import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder(prefix='with')
@ToString(includeNames=true)
class InfluxLogEvent {

	String clusterName
	String hostname
	String podId
	String podName
	String uid
	
}
