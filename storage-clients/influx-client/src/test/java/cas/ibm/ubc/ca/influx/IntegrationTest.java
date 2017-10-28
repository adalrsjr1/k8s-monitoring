package cas.ibm.ubc.ca.influx;

import cas.ibm.ubc.ca.influx.exception.NoResultsException;

import java.util.HashMap;
import java.util.Map;
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
	}

	@Before
	public void setDefaultDownsampleArgs() {
		measurementToDownsample = MEASUREMENT;
		functionToDownsample = DownsamplerFunction.MEAN;
		tagToDownsample = "containerName";
		idToDownsample = COMMON_CONTAINER_NAME;
		periodToDownsample = "1h";
	}

	@After
	public void deleteDatabase() {
		influxDB.deleteDatabase(DB_NAME);
	}

	@Test
	public void lastHalfHourMean() {
		writeBatchPoints(true);
		periodToDownsample = "30m";

		assertEquals(mean(VALUES), downsample(), 0.1);
	}

	@Test
	public void lastHalfHourMeanGrouped() {
		writeBatchPoints(false);
		periodToDownsample = "30m";

		Map<String, Double> m = new HashMap<>();
		m.put("c0",12.0);
		m.put("c1",12.0);
		m.put("c2",4.0);
		
		assertEquals(m, downsampleMap());
	}

	@Test
	public void lastTenSecondsMean() {
		writeBatchPoints(true);
		periodToDownsample = "10s";

		assertEquals(VALUES[0], downsample(), 0.1);
	}

	@Test(expected = NoResultsException.class)	
	public void nonExistingMeasurement() {
		writeBatchPoints(true);
		measurementToDownsample = "non_existing_measurement";

		downsample();
	}

	@Test
	public void filterByContainer() {
		writeBatchPoints(false);
		idToDownsample = "c0";

		assertEquals(12, downsample(), 0.1);
	}

	private void writeBatchPoints(boolean sameContainer) {
		String containerName = COMMON_CONTAINER_NAME;
		for(int i = 0; i < VALUES.length; i++) {
			if(!sameContainer)
				containerName = "c" + i;

			batchPoints.point(buildPoint(VALUES[i], containerName));
		}

		influxDB.write(batchPoints);
	}

	private Point buildPoint(int value, String containerName) {
		long time = System.currentTimeMillis() - pointsCount * secondsToMillis(10);
		pointsCount++;

		return Point.measurement(MEASUREMENT)
				.time(time, TimeUnit.MILLISECONDS)
				.addField(FIELD, value)
				.tag("containerName", containerName)
				.build();
	}

	private double downsample() {
		return sampler.downsample(measurementToDownsample,
				functionToDownsample,
				tagToDownsample,
				idToDownsample,
				periodToDownsample);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Double> downsampleMap() {
		return sampler.downsampleMap(measurementToDownsample,
				functionToDownsample,
				tagToDownsample,
				periodToDownsample);
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
