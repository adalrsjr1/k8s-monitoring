package cas.ibm.ubc.ca.model.benchmarking

import java.lang.reflect.Type
import java.util.List
import java.util.Map
import java.util.concurrent.TimeUnit
import cas.ibm.ubc.ca.interfaces.InspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.zipkin.pogos.Message
import groovy.transform.Memoized

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonWriter

class MonitoringMock implements InspectionInterface {

	private String jsonHosts
	private String jsonServices
	private String jsonMetrics
	private String jsonMessages

	public MonitoringMock(host, svcs, metrics, msgs) {
		jsonHosts = loadJson(host).text
		jsonServices = loadJson(svcs).text
		jsonMetrics = loadJson(metrics).text
		jsonMessages = loadJson(msgs).text
	}

	private File loadJson(String path) {
		new File(path)
	}

	private def parseJson(Type type, String json) {
		new Gson().fromJson(json, type);
	}

	@Override
	public List hosts() {
		Type type = new TypeToken<List>(){}.getType();
		return parseJson(type, jsonHosts)
	}

	@Override
	public List services() {
		Type type = new TypeToken<List<Map>>(){}.getType();
		return parseJson(type, jsonServices)
	}

	@Override
	public Map<String, Float> applications() {
		return ["APP": 0.5]
	}

	@Override
	public String cluster() {
		"KUBERNETES"
	}

	public List messages(TimeInterval timeInterval) {
		messages(null, timeInterval)
	}
	
	public List messages(String serviceInstance, TimeInterval timeInterval) {
		Gson gson = new Gson()
		Type type = new TypeToken<List<Message>>(){}.getType();
		
		return gson.fromJson(jsonMessages, type)
	}

	private Map extractMetrics(String context, Measurement measurement, TimeInterval timeInterval) {
		Type type = new TypeToken<Map>(){}.getType();
		Map metrics = parseJson(type, jsonMetrics)
		String measures = measurement.name()

		if(context == 'services' && measures == 'CPU')
			measures = 'usage'
		if(context == 'hosts' && measures == 'CPU')
			measures = 'node_utilization'
		if(context == 'services' && measures == 'MEMORY')
			measures = 'usage'
		if(context == 'hosts' && measures == 'MEMORY')
			measures = 'node_utilization'

		return metrics[context][measurement.name().toLowerCase()][measures]
	}

	@Override
	public Map<String, Double> metricsService(Measurement measurement, TimeInterval timeInterval) {
		return extractMetrics("services", measurement, timeInterval)
	}

	@Override
	public Map<String, Double> metricsHost(Measurement measurement, TimeInterval timeInterval) {
		return extractMetrics("hosts", measurement, timeInterval)
	}

	@Override
	public Double metricService(String id, Measurement measurement, TimeInterval timeInterval) {
		return extractMetrics("services", measurement, timeInterval)[id]
	}

	@Override
	public Double metricHost(String id, Measurement measurement, TimeInterval timeInterval) {
		return extractMetrics("hosts", measurement, timeInterval)[id]
	}

	@Override
	public String environment() {
		return "KUBERNETES";
	}
	
	static void main(String[] args) {
		MonitoringMock mock = new MonitoringMock()
		
		ModelHandler modelHandler = new ModelHandler("/home/adalrsjr1/Code/ibm-stack/benchmarking-input/")
		
		modelHandler.updateModel(
				"0",
				mock.environment(),
				mock.hosts(),
				mock.applications(),
				mock.services(),
				mock.messages(TimeInterval.last(0, TimeUnit.MILLISECONDS)),
				[Measurement.CPU, Measurement.MEMORY, Measurement.CPU, Measurement.MEMORY],
				[mock.metricsHost(Measurement.CPU, TimeInterval.last(0, TimeUnit.MILLISECONDS)),
					mock.metricsHost(Measurement.MEMORY, TimeInterval.last(0, TimeUnit.MILLISECONDS)),
					mock.metricsService(Measurement.CPU, TimeInterval.last(0, TimeUnit.MILLISECONDS)),
					mock.metricsService(Measurement.MEMORY, TimeInterval.last(0, TimeUnit.MILLISECONDS)),
				])
	}
}