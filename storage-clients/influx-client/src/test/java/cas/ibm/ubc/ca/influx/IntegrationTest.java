package cas.ibm.ubc.ca.influx;

import cas.ibm.ubc.ca.influx.exception.NoResultsException;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {
  private final String INFLUX_HOST = "http://localhost:8086"; 
  private final String INFLUX_USER = "root";
  private final String INFLUX_PASS = "root";
  private final String DB_NAME = "integration_test";
  private final String RETENTION_POLICY = "autogen";
  private final String FIELD = "value";
  private final String MEASUREMENT = "cpu";
  private final Integer[] VALUES = { 12, 12, 4 };

  private InfluxDB influxDB;
  private BatchPoints batchPoints; 
  private int pointsCount;
  private Sampler sampler;

  @Before
  public void setClient() {
    influxDB = InfluxDBFactory.connect(INFLUX_HOST, INFLUX_USER, INFLUX_PASS);
    sampler = new Sampler(influxDB, DB_NAME);
  }

  @Before
  public void setBatchPoints() {
    batchPoints = BatchPoints.database(DB_NAME)
                             .retentionPolicy(RETENTION_POLICY)
                             .consistency(ConsistencyLevel.ALL)
                             .build();
  }

  @Before
  public void setupDatabase() {
    influxDB.createDatabase(DB_NAME);
    writeBatchPoints();
  }

  @After
  public void deleteDatabase() {
    influxDB.deleteDatabase(DB_NAME);
  }

  @Test
  public void lastHalfHourMean() {
    double mean = sampler.downsample(MEASUREMENT,
                                     DownsamplerFunction.MEAN,
                                     "30m");

    assertEquals(mean(VALUES), mean, 0.1);
  }

  @Test
  public void lastTenSecondsMean() {
    double mean = sampler.downsample(MEASUREMENT,
                                     DownsamplerFunction.MEAN,
                                     "10s");

    assertEquals(VALUES[0], mean, 0.1);
  }

  @Test(expected = NoResultsException.class)
  public void nonExistingMeasurement() {
    double mean = sampler.downsample("non_existing_measurement",
                                     DownsamplerFunction.MEAN,
                                     "10s");
  }

  private void writeBatchPoints() {
    for(int val : VALUES) {
      batchPoints.point(buildPoint(val));
    }

    influxDB.write(batchPoints);
  }

  private Point buildPoint(int value) {
    long time = System.currentTimeMillis() - pointsCount * secondsToMillis(10);
    pointsCount++;

    return Point.measurement(MEASUREMENT)
                .time(time, TimeUnit.MILLISECONDS)
                .addField(FIELD, value)
                .build();
  }

  private double mean(Integer[] values) {
    return (double) sum(values) / values.length;
  }

  private int sum(Integer[] values) {
    int sum = 0;
    for(int val : values) {
      sum += val;
    }

    return sum;
  }

  private long secondsToMillis(int seconds) {
    return seconds * 1000;
  }
}
