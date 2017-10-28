package cas.ibm.ubc.ca.influx

import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory

class MetricsInspectionInterfaceFactory {

	public static MetricsInspectionInterface create(host, port, user, password, database) {
		String url = "http://${host}:${port}"
		
		InfluxDB influxDB = InfluxDBFactory.connect(url, user, password)
		return new Sampler(influxDB, database);
	}
	
}
