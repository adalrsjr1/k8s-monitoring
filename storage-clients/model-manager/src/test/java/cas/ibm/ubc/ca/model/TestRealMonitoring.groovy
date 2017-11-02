package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import cas.ibm.ubc.ca.model.manager.ModelHandler
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer
import cas.ibm.ubc.ca.monitoring.MonitoringApplication

import java.util.concurrent.TimeUnit
import model.Cluster

class TestRealMonitoring extends GroovyTestCase {

	synchronized static ModelFactoryAdapter factory = ModelFactoryAdapter.INSTANCE

	static MonitoringApplication monitor = new MonitoringApplication()
	static ModelHandler handler = new ModelHandler("")

	void test() {
		Cluster cluster = factory.createCluster()

		handler.fillModel(cluster,
				monitor.environment(),
				monitor.hosts(),
				monitor.applications(),
				monitor.services(),
				monitor.messages(TimeInterval.last(90, TimeUnit.DAYS)),
				["cpu/node_utilization", "memory/node_utilization", "cpu/usage", "memory/usage"],
				[monitor.metricsHost("cpu/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
					monitor.metricsHost("memory/node_utilization", TimeInterval.last(10, TimeUnit.MINUTES)),
					monitor.metricsService("cpu/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
					monitor.metricsService("memory/usage", TimeInterval.last(10, TimeUnit.MINUTES)),
				])
		AffinitiesAnalyzer aa = new AffinitiesAnalyzer()

		aa.calculate(cluster)

//		assert cluster.applications["sock-shop"].totalMessages == n
	}
}
