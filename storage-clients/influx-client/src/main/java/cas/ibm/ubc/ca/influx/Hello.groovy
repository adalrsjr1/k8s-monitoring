package cas.ibm.ubc.ca.influx;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;

import com.google.gson.Gson;
import groovy.json.JsonSlurper
import cas.ibm.ubc.ca.influx.pojos.InfluxLogEvent;

public class Hello {
	public static void main(String[] args) {
		InfluxDB influxDB = InfluxDBFactory.connect("http://10.66.66.22:32105", "root", "root");
		String dbName = "k8s";
		
//		Query query = new Query("SHOW MEASUREMENTS", dbName);
//		Query query = new Query("SELECT mean(value) FROM \"cpu/usage_rate\" where container_name = 'heapster' and time <= now() GROUP BY time(10s) fill(0)", dbName);
		Query query = new Query("select * from \"log/events\"", dbName)
		QueryResult result = influxDB.query(query);
		
		def slurper = new JsonSlurper()
		
		def value = result.getResults()
//		value.series[0].values[0].each {
//			println it
//		}
		
		println value
		
//		println slurper.parseText(value[0].series.values[0][0][6]).involvedObject.name
		
		
		
		Gson gson = new Gson();
//		
//		System.out.println(result.getResults());
//		System.out.println(gson.toJson(result.getResults()));
//		
//		System.out.println(gson.fromJson(gson.toJson(result.getResults()),result.getResults().getClass()));;

//println InfluxLogEvent.builder().withUid("test").build()
//SELECT mean(value) FROM "cpu/usage_rate" where container_name = 'carts' and time >= '2017-08-01T20:00:00Z' GROUP BY time(10m) fill(0)
	}
}
