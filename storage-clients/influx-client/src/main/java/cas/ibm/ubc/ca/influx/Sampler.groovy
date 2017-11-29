package cas.ibm.ubc.ca.influx

import static org.influxdb.dto.QueryResult.Series

import org.influxdb.InfluxDB
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult

import cas.ibm.ubc.ca.influx.QueryArgs.QueryType
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
	
	private QueryResult executeQueryMap(QueryArgs args) {
		InfluxdbQuery queryStatement = QueryFactory.create(args)
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

	@Override
	public Map<String, Double> metricsService(Measurement measurement, TimeInterval timeInterval) {
		
		QueryArgs args =  QueryArgs.builder().measurement(measurement)
			   .function(DownsamplerFunction.MEAN)
			   .tag("pod_name")
			   .duration(timeInterval.getIntervalInMillis())
			   .type(QueryType.SIMPLE)
			   .build()
			   
		return downsampleMap(args)
	}

	@Override
	public Map<String, Double> metricsHost(Measurement measurement, TimeInterval timeInterval) {
		return ["cpu":0.0, "memory":0.0]
	}

	@Override
	public Double metricService(String id, Measurement measurement, TimeInterval timeInterval) {
		QueryArgs args =  QueryArgs.builder().measurement(measurement)
							     .function(DownsamplerFunction.MEAN)
								 .tag("pod_name")
								 .duration(timeInterval.getIntervalInMillis())
								 .type(QueryType.SIMPLE)
								 .build()
		
		return downsampleValue(args)
		
	}

	@Override
	public Double metricHost(String id, Measurement measurement, TimeInterval timeInterval) {
		return 0.0
	}

	
	
}
