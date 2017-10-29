package cas.ibm.ubc.ca.model

import java.lang.reflect.Type
import java.util.List
import java.util.Map

import cas.ibm.ubc.ca.interfaces.InspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MonitoringMock implements InspectionInterface {

	private String jsonApplications
	private String jsonHosts
	private String jsonServices
	private String jsonMetrics

	public MonitoringMock() {
		jsonApplications = loadJson("applications.json").text
		jsonHosts = loadJson("hosts.json").text
		jsonServices = loadJson("services.json").text
		jsonMetrics = loadJson("metrics.json").text
	}

	private File loadJson(String path) {
		ClassLoader cl = this.class.getClassLoader()
		new File(cl.getResource(path).getFile())
	}

	private def parseJson(Type type, String json) {
		println json
		new Gson().fromJson(json, type);
	}

	@Override
	public List hosts() {
		Type type = new TypeToken<List>(){}.getType();
		return parseJson(type, jsonHosts)
	}

	@Override
	public List services() {
		Type type = new TypeToken<List>(){}.getType();
		return parseJson(type, jsonServices)
	}

	@Override
	public Map<String, Float> applications() {
		Type type = new TypeToken<Map<String, Float>>(){}.getType();
		return parseJson(type, jsonApplications)
	}

	@Override
	public String cluster() {
		"KUBERNETES"
	}

	@Override
	public List messages(TimeInterval timeInterval) {
		[]
	}

	@Override
	public List messages(String serviceInstance, TimeInterval timeInterval) {
		[]
	}

	private Map extractMetrics(String context, String measurement, TimeInterval timeInterval) {
		Type type = new TypeToken<Map>(){}.getType();
		Map metrics = parseJson(type, jsonMetrics)

		String[] measures = measurement.split("/")

		return metrics[context][measures[0]][measures[1]]
	}

	@Override
	public Map<String, Double> metricsService(String measurement, TimeInterval timeInterval) {
		return extractMetrics("services", measurement, timeInterval)
	}

	@Override
	public Map<String, Double> metricsHost(String measurement, TimeInterval timeInterval) {
		return extractMetrics("hosts", measurement, timeInterval)
	}

	@Override
	public Double metricService(String id, String measurement, TimeInterval timeInterval) {
		return extractMetrics("services", measurement, timeInterval)[id]
	}

	@Override
	public Double metricHost(String id, String measurement, TimeInterval timeInterval) {
		return extractMetrics("hosts", measurement, timeInterval)[id]
	}
}
