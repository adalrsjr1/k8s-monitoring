package cas.ibm.ubc.ca.influx

import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import okhttp3.OkHttpClient

import java.util.concurrent.TimeUnit

import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory

class MetricsInspectionInterfaceFactory {

	public static MetricsInspectionInterface create(host, port, user, password, database, timeout) {
		String url = "http://${host}:${port}"
		Integer intTimeout = Integer.parseInt(timeout)
		def builder = new OkHttpClient.Builder().readTimeout(intTimeout, TimeUnit.MILLISECONDS).connectTimeout(intTimeout, TimeUnit.MILLISECONDS);
		InfluxDB influxDB = InfluxDBFactory.connect(url, user, password, builder)
		return new Sampler(influxDB, database);
	}
}
