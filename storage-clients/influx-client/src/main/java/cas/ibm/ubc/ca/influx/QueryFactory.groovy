package cas.ibm.ubc.ca.influx

import cas.ibm.ubc.ca.influx.QueryArgs.QueryType
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement
import groovy.transform.CompileStatic
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

@Builder
class QueryArgs {
	static enum QueryType { DERIVATIVE, SIMPLE }
	
	Measurement measurement
	DownsamplerFunction function
	String tag
	Long duration
	String id
	QueryType type
	
	// https://www.ibm.com/support/knowledgecenter/en/SS8TQM_1.1.0/manage_resources/metrics.html
	/* // measurements
	 * cpu/limit
	 * cpu/node_allocatable
	 * cpu/node_capacity
	 * cpu/node_reservation
	 * cpu/node_utilization
	 * cpu/request
	 * cpu/usage
	 * cpu/usage_rate
	 * memory/cache
	 * memory/limit
	 * memory/node_allocatable
	 * memory/node_capacity
	 * memory/node_reservation
	 * memory/node_utilization
	 * memory/request
	 * memory/usage
	 */
	
	/* //tags
	 * container_name
	 * nodename
	 * pod_name
	 * namespace_name
	 */
	
	// https://github.com/kubernetes/heapster/blob/v1.3.0-beta.0/docs/storage-schema.md
	// https://github.com/kubernetes/heapster/blob/v1.3.0-beta.0/docs/model.md
	String mapMeasurement(boolean derivative) {
		if(Measurement.CPU == measurement && tag == "pod_name")
			if(derivative) {
				return "cpu/usage"
			}
			return "cpu/usage_rate" // milicores
		if(Measurement.CPU == measurement && tag == "nodename")
			if(derivative) {
				return "cpu/usage"
			}
			return "cpu/usage_rate" // milicores
		if(Measurement.MEMORY == measurement && tag == "pod_name")
			return "memory/usage"
		if(Measurement.MEMORY == measurement && tag == "nodename")
			return "memory/usage"
	}
	
	/**
	 * Trasforming memory size from Giga to Byte
	 * @return
	 */
	String memoryRatio() {
		if(Measurement.MEMORY != measurement) {
			return ""
		}
		return "/${1024**3}"
	}
}

class QueryFactory {
	static InfluxdbQuery create(QueryArgs args) {
		switch(args.type) {
			case QueryType.DERIVATIVE: return new DerivativeQuery(args)
			case QueryType.SIMPLE: return new SimpleQuery(args)
		}
	}	
}

interface InfluxdbDerivativeQuery extends InfluxdbQuery {
	String groupQueryMap()
	String groupQuery()
}

interface InfluxdbQuery {
	String queryMap()
	String query()
}

class DerivativeQuery implements InfluxdbDerivativeQuery {
	
	private final QueryArgs args
	
	DerivativeQuery(QueryArgs args) {
		this.args = args
	}
	
	String queryMap() {
		return """SELECT non_negative_derivative(${args.function}(value))/1000000000 AS "result" 
	   INTO deriv_runtime
       FROM "${args.mapMeasurement(true)}"
       WHERE time > now() - ${args.duration}ms AND "${args.tag}" != ''
             GROUP BY time(1s), ${args.tag}"""
	}
	
	String groupQueryMap() {
		return "SELECT ${args.function}(result) from deriv_runtime WHERE pod_name != '' GROUP BY ${args.tag}"
	}
	
	String query() {
		if(!args.id) {
			throw new RuntimeException("QueryArgs.id must be set!")
		} 
		return """SELECT non_negative_derivative(${args.function}(value))/1000000000 AS "result" INTO deriv_runtime
       FROM "${args.mapMeasurement(false)}"
       WHERE time > now() - ${args.duration}ms AND "${args.tag}" != ''
             AND ${args.tag} = '${args.id}' GROUP BY time(1s), ${args.tag}""" 
	}
	
	String groupQuery() {
		if(!args.id) {
			throw new RuntimeException("QueryArgs.id must be set!")
		}
		return "SELECT max(result) from deriv_runtime WHERE pod_name != '' AND pod_name = '${args.id}'GROUP BY ${args.tag}"
	}
}

class SimpleQuery implements InfluxdbQuery {
	private final QueryArgs args
	
	SimpleQuery(QueryArgs args) {
		this.args = args
	}
	
	String queryMap() {
		"""SELECT ${args.function}(value)${args.memoryRatio()}
       FROM "${args.mapMeasurement(false)}"
       WHERE time > now() - ${args.duration}ms AND "${args.tag}" != ''
             GROUP BY ${args.tag}"""
	}
	
	String query() {
		if(!args.id) {
			throw new RuntimeException("QueryArgs.id must be set!")
		}
		"""SELECT ${args.function}(value)${args.memoryRatio()}
       FROM "${args.mapMeasurement(false)}"
       WHERE time > now() - ${args.duration}ms AND "${args.tag}" != ''
             AND ${args.tag} = '${args.id}'"""
	}
	
}
