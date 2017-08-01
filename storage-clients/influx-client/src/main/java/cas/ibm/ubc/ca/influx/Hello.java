package cas.ibm.ubc.ca.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

public class Hello {
	public static void main(String[] args) {
		InfluxDB influxDB = InfluxDBFactory.connect("http://kay.cs.ubc.ca:8086", "root", "root");
		String dbName = "k8s";
		
		Query query = new Query("SHOW TAG KEYS", dbName);
		QueryResult result = influxDB.query(query);
		
		System.out.println(result.getResults());
		
	}
}
