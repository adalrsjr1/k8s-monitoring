package cas.ibm.ubc.ca.influx;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import cas.ibm.ubc.ca.influx.QueryArgs.QueryType;
import cas.ibm.ubc.ca.influx.exception.NoResultsException;
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface;
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement;

@RunWith(JUnit4)
public class IntegrationTest extends GroovyTestCase {
	private final String INFLUX_HOST = "localhost";
	private final String INFLUX_PORT = "8086";
	private final String INFLUX_USER = "root";
	private final String INFLUX_PASS = "root";
	private final String DB_NAME = "integration_test";
	private final String RETENTION_POLICY = "autogen";
	private final String FIELD = "value";
	private final Measurement MEASUREMENT = Measurement.CPU;
	private final String MEASUREMENT_NAME = "cpu/usage_rate"
	private final List VALUES = [ 12, 12, 4 ];
	private final String COMMON_CONTAINER_NAME = "c1";

	private InfluxDB influxDB;
	private BatchPoints batchPoints; 
	private int pointsCount;
	private Sampler sampler;
	private Object[] downsampleArgs;
	private String measurementToDownsample,
	tagToDownsample,
	idToDownsample,
	periodToDownsample;
	private DownsamplerFunction functionToDownsample;

	@Before
	public void setClient() {
		influxDB = InfluxDBFactory.connect("http://"+INFLUX_HOST + ":" + INFLUX_PORT, INFLUX_USER, INFLUX_PASS);
		
		MetricsInspectionInterface inspection = MetricsInspectionInterfaceFactory.create(INFLUX_HOST, INFLUX_PORT, INFLUX_USER, INFLUX_PASS, DB_NAME);
		assertNotNull(inspection);		
		sampler = (Sampler)inspection;
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
	}

	@Before
	public void setDefaultDownsampleArgs() {
		measurementToDownsample = "cpu/usage_rate";
		functionToDownsample = DownsamplerFunction.MEAN;
		tagToDownsample = "containerName";
		idToDownsample = COMMON_CONTAINER_NAME;
		periodToDownsample = "3600000";
	}

	@After
	public void deleteDatabase() {
//		influxDB.deleteDatabase(DB_NAME);
	}

	@Test
	public void testLastHalfHourMean() {
		writeBatchPoints(true);
		periodToDownsample = 30*60*1000+"";

		assertEquals(mean(VALUES), downsample(), 0.1);
	}

	@Test
	public void testLastHalfHourMeanGrouped() {
		writeBatchPoints(false);
		periodToDownsample = 30*60*1000+"";

		Map<String, Double> m = new HashMap<>();
		m.put("c0",12.0);
		m.put("c1",12.0);
		m.put("c2",4.0);
		
		assertEquals(m, downsampleMap());
	}

	@Test
	public void testLastTenSecondsMean() {
		writeBatchPoints(true);
		periodToDownsample = 10*1000+"";

		assertEquals(VALUES[0], downsample(), 0.1);
	}

	@Test(expected = NoResultsException.class)	
	public void testNonExistingMeasurement() {
		writeBatchPoints(true);
		measurementToDownsample = "non_existing_measurement";

		downsample();
	}

	@Test
	public void testFilterByContainer() {
		writeBatchPoints(false);
		idToDownsample = "c0";

		assertEquals(12, downsample(), 0.1);
	}

	private void writeBatchPoints(boolean sameContainer) {
		String containerName = COMMON_CONTAINER_NAME;
		for(int i = 0; i < VALUES.size(); i++) {
			if(!sameContainer)
				containerName = "c" + i;

			batchPoints.point(buildPoint(VALUES[i], containerName));
		}

		influxDB.write(batchPoints);
	}

	private Point buildPoint(int value, String containerName) {
		long time = System.currentTimeMillis() - pointsCount * secondsToMillis(10);
		pointsCount++;

		return Point.measurement(MEASUREMENT_NAME)
				.time(time, TimeUnit.MILLISECONDS)
				.addField(FIELD, value)
				.tag("containerName", containerName)
				.build();
	}

	private double downsample() {
		QueryArgs args = QueryArgs.builder()
				                .measurement(MEASUREMENT)
				                .function(functionToDownsample)
				                .tag(tagToDownsample)
				                .id(idToDownsample)
				                .duration(Long.parseLong(periodToDownsample))
				                .type(QueryType.SIMPLE)
				                .build();
		
		return sampler.downsampleValue(args);
		
//		return sampler.downsample(measurementToDownsample,
//				functionToDownsample,
//				tagToDownsample,
//				idToDownsample,
//				periodToDownsample);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Double> downsampleMap() {
		QueryArgs args = QueryArgs.builder()
								  .measurement(MEASUREMENT)
							      .function(functionToDownsample)
								  .tag(tagToDownsample)
								  .id(COMMON_CONTAINER_NAME)
								  .duration(Long.parseLong(periodToDownsample))
								  .type(QueryType.SIMPLE)
								  .build();

		return sampler.downsampleMap(args);
		
//		
//		return sampler.downsampleMap(measurementToDownsample,
//				functionToDownsample,
//				tagToDownsample,
//				periodToDownsample);
	}

	private double mean(List values) {
		return (double) sum(values) / values.size();
	}

	private int sum(List values) {
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
