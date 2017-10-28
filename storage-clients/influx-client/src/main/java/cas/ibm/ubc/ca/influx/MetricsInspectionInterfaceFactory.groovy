package cas.ibm.ubc.ca.influx

import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface
import org.influxdb.InfluxDB
import org.influxdb.InfluxDBFactory

class MetricsInspectionInterfaceFactory {

	public static MetricsInspectionInterface create(host, user, pass, database) {
		InfluxDB influxDB = InfluxDBFactory.connect(host, user, pass)
		return new Sampler(influxDB, database);
	}
	
}
