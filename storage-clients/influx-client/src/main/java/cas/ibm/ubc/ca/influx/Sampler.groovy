package cas.ibm.ubc.ca.influx

import static org.influxdb.dto.QueryResult.Series

import org.influxdb.InfluxDB
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult
import cas.ibm.ubc.ca.influx.QueryArgs.QueryType
import cas.ibm.ubc.ca.influx.exception.NoResultsException
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

import java.math.RoundingMode
import java.text.DecimalFormat

public class Sampler implements MetricsInspectionInterface {
	private InfluxDB client
	private String database

	public Sampler(InfluxDB client, String database) {
		this.client = client
		this.database = database
	}
	
	private QueryResult executeQueryMap(QueryArgs args) {
		InfluxdbQuery queryStatement = QueryFactory.create(args)
		println queryStatement.query()
		Query query = new Query(queryStatement.queryMap(), database)
		QueryResult result = client.query(query)
		
		if(QueryType.DERIVATIVE == args.type) {
			InfluxdbDerivativeQuery derivativeQueryStatement = (InfluxdbDerivativeQuery) queryStatement
			query = new Query(derivativeQueryStatement.groupQueryMap(), database)
			result = client.query(query)
		}
		
		return result
	}
	
	private QueryResult executeQuery(QueryArgs args) {
		InfluxdbQuery queryStatement = QueryFactory.create(args)
		println queryStatement.query()
		Query query = new Query(queryStatement.query(), database)
		QueryResult result = client.query(query)
		
		if(QueryType.DERIVATIVE == args.type) {
			InfluxdbDerivativeQuery derivativeQueryStatement = (InfluxdbDerivativeQuery) queryStatement
			query = new Query(derivativeQueryStatement.groupQuery(), database)
			result = client.query(query)
		}
		
		return result
	}
	
	public Map downsampleMap(QueryArgs args) {
		QueryResult result = executeQueryMap(args)
		parseList(result)
	}
	
	public double downsampleValue(QueryArgs args) {
		QueryResult result = executeQuery(args)
		parse(result)
	}
	
	public Map downsampleDerivativeMap(Object... args) {
		Query query = new Query(buildDerivativeQueryMap(*args), database)
		QueryResult result = client.query(query)

		Query queryGroup = new Query(buildDerivativeGroupQueryMap(*args), database)
		QueryResult resultGroup = client.query(queryGroup)
		
		parseList(resultGroup)
	}
	
	public double downsampleDerivative(Object... args) {
		Query query = new Query(buildDerivativeQuery(*args), database)
		QueryResult result = client.query(query)

		Query queryGroup = new Query(buildDerivativeGroupQuery(*args), database)
		QueryResult resultGroup = client.query(queryGroup)
		
		parseList(resultGroup)
	}
	
	public Map downsampleMap(Object... args) {
		Query query = new Query(buildQueryMap(*args), database)
		QueryResult result = client.query(query)

		parseList(result)
	}
	
	public double downsample(Object... args) {
		Query query = new Query(buildQuery(*args), database)
		QueryResult result = client.query(query)

		parse(result)
	}
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
	private String mapMeasurement(Measurement measurement, String tag) {
		if(Measurement.CPU == measurement && tag == "pod_name")
//			return "cpu/usage" 
			return "cpu/usage_rate" // milicores
		if(Measurement.CPU == measurement && tag == "nodename")
			return "cpu/usage_rate" // milicores
		if(Measurement.MEMORY == measurement && tag == "pod_name")
			return "memory/usage"
		if(Measurement.MEMORY == measurement && tag == "nodename")
			return "memory/usage"
	}
	// SELECT non_negative_derivative(sum(value))/1000000000 AS "result" INTO deriv_runtime FROM "cpu/usage" \
	//		WHERE time > now() - 5h AND time < now() -3h AND pod_name = '' GROUP BY time(1m)
	// SELECT max(result) from deriv_runtime WHERE pod_name != '' GROUP BY pod_name
	// https://github.com/google/cadvisor/issues/1232
	// https://github.com/influxdata/influxdb/issues/5898
	private String buildDerivativeQueryMap(Measurement measurement, DownsamplerFunction function, String tag, def duration) {
		// millicores to percentage
		"""SELECT non_negative_derivative(${function}(value))/1000000000 AS "result" INTO deriv_runtime
       FROM "${mapMeasurement(measurement, tag)}"
       WHERE time > now() - ${duration} AND "${tag}" != ''
             GROUP BY time(1s), ${tag}"""
	}
	
	private String buildDerivativeGroupQueryMap(Measurement measurement, DownsamplerFunction function, String tag, def duration) {
		"SELECT max(result) from deriv_runtime WHERE pod_name != '' GROUP BY ${tag}"
	}
	
	private String buildDerivativeQuery(Measurement measurement, DownsamplerFunction function, String tag, String id, def duration) {
		"""SELECT non_negative_derivative(${function}(value))/1000000000 AS "result" INTO deriv_runtime 
       FROM "${mapMeasurement(measurement, tag)}"
       WHERE time > now() - ${duration} AND "${tag}" != ''
             AND ${tag} = '${id}' GROUP BY time(1s), ${tag}""" 
	}
	
	private String buildDerivativeGroupQuery(Measurement measurement, DownsamplerFunction function, String tag, String id, def duration) {
		"SELECT max(result) from deriv_runtime WHERE pod_name != '' AND pod_name = '${id}'GROUP BY ${tag}"
	}
	
	private String buildQueryMap(Measurement measurement,
			DownsamplerFunction function, String tag, def duration) {
		def ratio = ""
		// transforming memory from bytes to giga
		if(measurement == Measurement.MEMORY) {
			ratio = "/${1024**3}"
		}
		"""SELECT ${function}(value)${ratio}
       FROM "${mapMeasurement(measurement, tag)}"
       WHERE time > now() - ${duration} AND "${tag}" != ''
             GROUP BY ${tag}"""
	}

	private String buildQuery(Measurement measurement,
			DownsamplerFunction function, String tag, String id, def duration) {
		"""SELECT ${function}(value)
       FROM "${mapMeasurement(measurement, tag)}"
       WHERE time > now() - ${duration} AND "${tag}" != ''
             AND ${tag} = '${id}'"""
	}
	
	private double parse(QueryResult queryResult) {
		 getSeries(queryResult).get(0)
	                           .getValues()
	                           .get(0)
	                           .get(1)
	}
	
	private Map<String, Double> parseList(QueryResult queryResult) {
		Map result = [:]
		
		getSeriesList(queryResult).each {
			
			def tag = it.getTags().values()[0]
			
			def value = it.getValues()
					.get(0)
					.get(1)
			result[tag] =  Math.floor(value * 100000) / 100000 // decrease the precision point
		}
		return result
	}

	private List<Series> getSeries(QueryResult queryResult) {
		List<Series> seriesList = queryResult.getResults()
				.get(0)
				.getSeries()

		if(seriesList == null)
			throw new NoResultsException()

		return seriesList
	}
	
	private List<Series> getSeriesList(QueryResult queryResult) {
		List<Series> seriesList = queryResult.getResults()
				.get(0)
				.getSeries()

		if(seriesList == null)
			throw new NoResultsException()

		return seriesList
	}

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
	
	@Override
	public Map<String, Double> metricsService(Measurement measurement, TimeInterval timeInterval) {
		
		QueryArgs args =  QueryArgs.builder().measurement(measurement)
			   .function(DownsamplerFunction.MEAN)
			   .tag("pod_name")
			   .duration(timeInterval.getIntervalInMillis())
			   .type(QueryType.SIMPLE)
			   .build()
			   
		return downsampleMap(args)
		
		
		
//		downsampleMap(measurement, DownsamplerFunction.MEAN,
//			"pod_name", "${timeInterval.getIntervalInMillis()}ms")
		if(measurement == Measurement.CPU) {
			return downsampleDerivativeMap(measurement, DownsamplerFunction.SUM,
				"pod_name", "${timeInterval.getIntervalInMillis()}ms")
		}
		else {
			return downsampleMap(measurement, DownsamplerFunction.MEAN,
				"pod_name", "${timeInterval.getIntervalInMillis()}ms")
		}
	}

	@Override
	public Map<String, Double> metricsHost(Measurement measurement, TimeInterval timeInterval) {
//		downsampleMap(measurement, DownsamplerFunction.MEAN,
//			"nodename", "${timeInterval.getIntervalInMillis()}ms")
//		if(measurement == Measurement.CPU) {
//			return downsampleDerivativeMap(measurement, DownsamplerFunction.SUM,
//				"nodename", "${timeInterval.getIntervalInMillis()}ms")
//		}
//		else {
//			return downsampleMap(measurement, DownsamplerFunction.MEAN,
//				"nodename", "${timeInterval.getIntervalInMillis()}ms")
//		}
		
		return ["cpu":0.0, "memory":0.0]
	}

	@Override
	public Double metricService(String id, Measurement measurement, TimeInterval timeInterval) {
		QueryArgs args =  ARGS_BUILDER.measurement(measurement)
							     .function(DownsamplerFunction.MEAN)
								 .tag("pod_name")
								 .duration(timeInterval.getIntervalInMillis())
								 .type(QueryType.SIMPLE)
								 .build()
		
		return downsample(args)
		
		
		
		if(measurement == Measurement.CPU) {
			return downsampleDerivative(measurement, DownsamplerFunction.SUM,
				"pod_name", "${timeInterval.getIntervalInMillis()}ms")
		}
		else {
			return downsample(measurement, DownsamplerFunction.SUM,
				"pod_name", "${timeInterval.getIntervalInMillis()}ms")
		}
	}

	@Override
	public Double metricHost(String id, Measurement measurement, TimeInterval timeInterval) {
//		if(measurement == Measurement.CPU) {
//			return downsampleDerivative(measurement, DownsamplerFunction.SUM,
//				"nodename", "${timeInterval.getIntervalInMillis()}ms")
//		}
//		else {
//			return downsample(measurement, DownsamplerFunction.SUM,
//				"nodename", "${timeInterval.getIntervalInMillis()}ms")
//		}
		return 0.0
	}

	
	
}
