package cas.ibm.ubc.ca.zipkin.pogos

import groovy.transform.Canonical

@Canonical
class Message {
	String correlationId
	Long timestamp
	Long totalTime
	String targetIp
	String targetName
	String sourceIp 
	String sourceName
}

