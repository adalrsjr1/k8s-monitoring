package cas.ibm.ubc.ca.influx

import static org.influxdb.dto.QueryResult.Series

import org.influxdb.InfluxDB
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult

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
			return "cpu/usage_rate" // milicores
		if(Measurement.CPU == measurement && tag == "nodename")
			return "cpu/usage_rate" // milicores
		if(Measurement.MEMORY == measurement && tag == "pod_name")
			return "memory/usage"
		if(Measurement.MEMORY == measurement && tag == "nodename")
			return "memory/usage"
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
			result[tag] =  Math.floor(value * 100000) / 100000
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
		downsampleMap(measurement, DownsamplerFunction.MEAN,
			"pod_name", "${timeInterval.getIntervalInMillis()}ms")
	}

	@Override
	public Map<String, Double> metricsHost(Measurement measurement, TimeInterval timeInterval) {
		downsampleMap(measurement, DownsamplerFunction.MEAN,
			"nodename", "${timeInterval.getIntervalInMillis()}ms")
	}

	@Override
	public Double metricService(String id, Measurement measurement, TimeInterval timeInterval) {
		downsample(measurement, DownsamplerFunction.MEAN,
			"pod_name", id, "${timeInterval.getIntervalInMillis()}ms")
	}

	@Override
	public Double metricHost(String id, Measurement measurement, TimeInterval timeInterval) {
		downsample(measurement, DownsamplerFunction.MEAN,
			"nodename", id, "${timeInterval.getIntervalInMillis()}ms")
	}

	
	
}
