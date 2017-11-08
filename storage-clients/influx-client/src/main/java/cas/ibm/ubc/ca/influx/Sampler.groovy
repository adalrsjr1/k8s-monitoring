package cas.ibm.ubc.ca.influx

import static org.influxdb.dto.QueryResult.Series

import org.influxdb.InfluxDB
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult

import cas.ibm.ubc.ca.influx.exception.NoResultsException
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval

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
	
	private String buildQueryMap(String measurement,
			DownsamplerFunction function, String tag, def duration) {
		"""SELECT ${function}(value)
       FROM "${measurement}"
       WHERE time > now() - ${duration}
             GROUP BY ${tag}"""
	}

	private String buildQuery(String measurement,
			DownsamplerFunction function, String tag, String id, def duration) {
		"""SELECT ${function}(value)
       FROM "${measurement}"
       WHERE time > now() - ${duration}
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
					
			result[tag] = value
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
	public Map<String, Double> metricsService(String measurement, TimeInterval timeInterval) {
		downsampleMap(measurement, DownsamplerFunction.MEAN,
			"pod_name", "${timeInterval.getIntervalInMillis()}ms")
	}

	@Override
	public Map<String, Double> metricsHost(String measurement, TimeInterval timeInterval) {
		downsampleMap(measurement, DownsamplerFunction.MEAN,
			"nodename", "${timeInterval.getIntervalInMillis()}ms")
	}

	@Override
	public Double metricService(String id, String measurement, TimeInterval timeInterval) {
		downsample(measurement, DownsamplerFunction.MEAN,
			"pod_name", id, "${timeInterval.getIntervalInMillis()}ms")
	}

	@Override
	public Double metricHost(String id, String measurement, TimeInterval timeInterval) {
		downsample(measurement, DownsamplerFunction.MEAN,
			"nodename", id, "${timeInterval.getIntervalInMillis()}ms")
	}

	
	
}
