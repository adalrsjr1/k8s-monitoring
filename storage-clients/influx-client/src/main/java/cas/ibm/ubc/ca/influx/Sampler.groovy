package cas.ibm.ubc.ca.influx

import cas.ibm.ubc.ca.influx.exception.NoResultsException

import java.util.List

import static org.influxdb.dto.QueryResult.Series
import org.influxdb.InfluxDB
import org.influxdb.dto.Query
import org.influxdb.dto.QueryResult

public class Sampler {
  private InfluxDB client
  private String database

  public Sampler(InfluxDB client, String database) {
    this.client = client
    this.database = database
  }

  public double downsample(String measurement, DownsamplerFunction function,
                                        String containerName, String duration) {
    Query query = new Query(buildQueryString(measurement, function,
                                             containerName, duration),
                            database)
    QueryResult result = client.query(query)

    parse(result)
  }

  private String buildQueryString(String measurement,
          DownsamplerFunction function, String containerName, String duration) {
    """SELECT ${function}(value)
       FROM ${measurement}
       WHERE time > now() - ${duration}
             AND container_name = '${containerName}'"""
  }

  private double parse(QueryResult queryResult) {
    getSeries(queryResult).get(0)
                          .getValues()
                          .get(0)
                          .get(1)
  }

  private List<Series> getSeries(QueryResult queryResult) {
    List<Series> seriesList = queryResult.getResults()
                                         .get(0)
                                         .getSeries()

    if(seriesList == null)
      throw new NoResultsException()

    return seriesList
  }
}
