package cas.ibm.ubc.ca.model.benchmarking;

import org.junit.AfterClass
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement;
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;
import cas.ibm.ubc.ca.model.manager.ModelHandler;
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer
import groovy.transform.CompileStatic;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.carrotsearch.junitbenchmarks.annotation.LabelType;
import org.junit.runners.MethodSorters;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@BenchmarkMethodChart(filePrefix = "model-analysis")
@BenchmarkHistoryChart(labelWith = LabelType.RUN_ID, maxRuns = 20)
class BenchmarkingAnalysis {
	
	static final String BENCHMARKING_PATH = BenchmarkConfig.BENCHMARK_PATH
	@AfterClass
	public static void cleanup() {
		BenchmarkConfig.cleanupXmi()
	}
	
	static private createFiles(svcs, hosts, msgs) {
		def p = "${BenchmarkConfig.PROCESS_NAME_NO_ARGS} ${hosts} ${svcs} ${msgs}".execute()
		p.waitFor()
	}
	
	static private void createMock(svcs, hosts, msgs) {
		createFiles(svcs, hosts, msgs)
		String pHosts = BenchmarkConfig.getHostsPath(svcs, hosts, msgs)
		String pSvcs = BenchmarkConfig.getSvcsPath(svcs, hosts, msgs)
		String pMsgs = BenchmarkConfig.getMsgsPath(svcs, hosts, msgs)
		String pMetrics = BenchmarkConfig.getMetricsPath(svcs, hosts, msgs)
		def mock = new MonitoringMock(pHosts, pSvcs, pMetrics, pMsgs)
		
		ModelHandler handler = new ModelHandler(BENCHMARKING_PATH)
		handler.updateModel(
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
		
		AffinitiesAnalyzer analyzer = new AffinitiesAnalyzer()
		def cluster = handler.cluster
		def resource = handler.resource
		analyzer.calculate(cluster, resource)
	}
	
	@BeforeClass
	public static void loadProperties() throws IOException {
		BenchmarkConfig.loadConfig()
	}
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	
	// 1_10_2 == 1x10^2 == 100
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_2_Messages_1_10_1_Services() {
		createMock(10,10,100)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_2_Messages_1_10_2_Services() {
		createMock(100,100,100)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_2_Messages_1_10_3_Services() {
		createMock(1000,1000,100)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_2_Messages_5_10_2_Services() {
		createMock(500,500,100)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_3_Messages_1_10_1_Services() {
		createMock(10,10,1000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_3_Messages_1_10_2_Services() {
		createMock(100,100,1000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_3_Messages_1_10_3_Services() {
		createMock(1000,1000,1000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_3_Messages_5_10_2_Services() {
		createMock(500,500,1000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_4_Messages_1_10_1_Services() {
		createMock(10,10,10000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_4_Messages_1_10_2_Services() {
		createMock(100,100,10000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_4_Messages_1_10_3_Services() {
		createMock(1000,1000,10000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_4_Messages_5_10_2_Services() {
		createMock(500,500,10000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_5_Messages_1_10_1_Services() {
		createMock(10,10,100000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_5_Messages_1_10_2_Services() {
		createMock(100,100,100000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_5_Messages_1_10_3_Services() {
		createMock(1000,1000,100000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_5_Messages_5_10_2_Services() {
		createMock(500,500,100000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_6_Messages_1_10_1_Services() {
		createMock(10,10,1000000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_6_Messages_1_10_2_Services() {
		createMock(100,100,1000000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_6_Messages_1_10_3_Services() {
		createMock(1000,1000,1000000)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_1_10_6_Messages_5_10_2_Services() {
		createMock(500,500,1000000)
	}
}
