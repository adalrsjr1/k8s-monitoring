package cas.ibm.ubc.ca.model

import cas.ibm.ubc.ca.interfaces.InspectionInterface
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval
import java.util.concurrent.TimeUnit

class TestMonitoringMock extends GroovyTestCase {

	InspectionInterface monitor
	
	void setUp() {
		monitor = new MonitoringMock()
	}
	
	void testApplications() {
		assert ["default":1.0,"kube-public":1.0,"kube-system":0.5,"sock-shop":0.33333,"zipkin":0.635]
		monitor.applications()
	}
	
	public void testHosts() {
		assert ["gfads1","gfads2"] == monitor.hosts().collect([]) { it.name }
	}
	
	public void testServices() {
		assert ["elasticsearch", "heapster", "kibana", "kube-dns",
			"kubernetes-dashboard", "monitoring-grafana", "monitoring-influxdb", "carts", "carts-db", "catalogue",
			"catalogue-db", "front-end", "orders", "orders-db", "payment", "queue-master", "shipping", "user",
			"user-db", "zipkin", "zipkin-mysql"] == monitor.services().collect([]) { it.name} 
	}
	
	public void testCluster() {
		assert "KUBERNETES" == monitor.cluster()
	}
	
	public void testMetricsServices() {
		assert  ["": 1.4476510658599611E13,
                "carts-5488c95848-8z77d": 8.684822690657225E11,
                "carts-db-787f4b7896-w7f75": 4.02796614891475E12,
                "catalogue-56775599c4-qw4lr": 9.434158212582117E11,
                "catalogue-db-64989577db-sbv55": 4.836332348875442E11,
                "elasticsearch-86f5559b66-wpzhx": 1.1953988623985107E13,
                "etcd-gfads1": 1.203107283021083E13,
                "fluentd-4wtds": 3.87320417515301E12,
                "fluentd-9n5t6": 4.2804062325446426E12,
                "front-end-6ffc4ccbb9-7m5w4": 8.18338370122075E10,
                "heapster-5c79f4b57f-2ksdq": 1.515210708139081E12,
                "kibana-6648467f87-7kwpg": 4.2037239211472114E12,
                "kube-apiserver-gfads1": 4.108963073812938E13,
                "kube-controller-manager-gfads1": 2.77649368992071E13,
                "kube-dns-545bc4bfd4-hrfj4": 1.6630781575823955E12,
                "kube-proxy-rhmjx:3.8446304249158735E12, kube-proxy-s5wzf": 4.3102707474946743E12,
                "kube-scheduler-gfads1": 1.1552754276826469E13,
                "kubernetes-dashboard-69c5c78645-brrq2": 3.5504631529012915E11,
                "monitoring-grafana-5bccc9f786-lkp75": 5.115913627447192E11,
                "monitoring-influxdb-85cb4985d4-29k5s": 3.5293491205277344E12,
                "orders-587d6fd58b-5jwld": 8.821114662891833E11,
                "orders-db-66f56c7d6d-k4mh8": 3.232825453225774E12,
                "payment-744ccc9cf7-wpw22": 9.63358655276185E11,
                "queue-master-6f49748d6c-jphmj": 7.172953627929808E11,
                "rabbitmq-759988c4c-87jh2": 2.804971857408274E12,
                "shipping-7979d48bfc-b8kld": 8.290597242218833E11,
                "user-6dc4956797-z82hx": 1.0618205077199575E12,
                "user-db-65585649f9-v72w7": 3.2624825824317144E12,
                "weave-net-4smwg": 1.3122277665541028E12,
                "weave-net-zg2nd": 1.3934324813082544E12,
                "zipkin-64f8475c9-9sbqt": 5.467070799927183E11,
                "zipkin-cron-5fd9647bdf-8prx9": 2.5617400191792935E12,
                "zipkin-mysql-79944fc86d-kllkz": 4.926771119593125E11] == monitor.metricsService("cpu/usage", null)
	}
	
	public void testMetricsHosts() {
		assert ["gfads1": 4000.0,
                "gfads2": 4000.0] == monitor.metricsHost("cpu/capacity", null)
	}
	
	public void testMetricsService() {
		assert 4.108963073812938E13 == monitor.metricService("kube-apiserver-gfads1", "cpu/usage", null)
	}
	
	public void testMetricsHost() {
		assert 1.2597956608E10 == monitor.metricHost("gfads2","memory/node_capacity", null)
		
	}
	
	public void test10Messages() {
		assert 10 == monitor.messages(TimeInterval.last(10, TimeUnit.MILLISECONDS)).size()
	}
	
	public void test100Messages() {
		assert 100 == monitor.messages(TimeInterval.last(100, TimeUnit.MILLISECONDS)).size()
	}
	
	public void testMessage() {
		println monitor.messages(TimeInterval.last(1, TimeUnit.MILLISECONDS))[0]["sourceName"]
	}
}
