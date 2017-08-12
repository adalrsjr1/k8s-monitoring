package cas.ibm.ubc.ca.influx.pojos

import groovy.transform.ToString
import groovy.transform.builder.Builder

@Builder(prefix='with')
@ToString(includeNames=true)
class InfluxCpuUsageRate {
	
	String clusterName
	String containerBaseImage
	String containerName
	String labels
	String namespaceName
	String nodename
	String podName
	String type
	Integer value
	
}
