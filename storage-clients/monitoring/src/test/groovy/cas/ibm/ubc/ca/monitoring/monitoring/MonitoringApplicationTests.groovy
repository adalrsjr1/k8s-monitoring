package cas.ibm.ubc.ca.monitoring.monitoring

import cas.ibm.ubc.ca.monitoring.MonitoringApplication

class MonitoringApplicationTests extends GroovyTestCase {

	void testLoadProperties() { 
		Properties properties  = MonitoringApplication.loadProperties()

		assert properties != null
		assert properties.isEmpty() == false		
		
		assert properties["cluster.inspection.host"]
		assert properties["cluster.inspection.port"]
		assert properties["cluster.inspection.timeout"]
		
		assert properties["messages.inspection.host"]
		assert properties["messages.inspection.port"]
		assert properties["messages.inspection.timeout"]
		
		assert properties["metrics.inspection.host"]
		assert properties["metrics.inspection.port"]
		assert properties["metrics.inspection.influx.user"]
		assert properties["metrics.inspection.influx.password"]
		assert properties["metrics.inspection.influx.database"]
		
	}
	
	void testInstantiate() {
		MonitoringApplication monitor = new MonitoringApplication()
		
		assert monitor
		assert monitor.clusterMonitor
		assert monitor.messagesMonitor
		assert monitor.metricsMonitor
	}

}
