package cas.ibm.ubc.ca.model.benchmarking.modelcreation;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement;
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;
import cas.ibm.ubc.ca.model.benchmarking.BenchmarkConfig
import cas.ibm.ubc.ca.model.benchmarking.MonitoringMock
import cas.ibm.ubc.ca.model.manager.ModelHandler;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.carrotsearch.junitbenchmarks.annotation.LabelType;
import org.junit.runners.MethodSorters;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@BenchmarkMethodChart(filePrefix = "model-creation-1000000_messages")
@BenchmarkHistoryChart(labelWith = LabelType.RUN_ID, maxRuns = 20)
class BenchmarkingModelCreation_m_1000000 {
	
	static final String BENCHMARKING_PATH = BenchmarkConfig.BENCHMARK_PATH
	static private createFiles(svcs, hosts, msgs) {
		def p = "${BenchmarkConfig.PROCESS_NAME_NO_ARGS} ${hosts} ${svcs} ${msgs}".execute()
		p.waitFor()
	}
	
	static private void createMock(svcs, hosts, msgs) {
//		createFiles(svcs, hosts, msgs)
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
	}
	
	@BeforeClass
	public static void loadProperties() throws IOException {
		BenchmarkConfig.loadConfig()
	}
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	// 1_1_10_1_2 == 1x10^2 == 100
	// svcs hosts messages
	static final int MESSAGES = 1000000
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_10_Services() {
		createMock(10,10,MESSAGES)
	}
	
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_11_Services() {
		createMock(11,11,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_12_Services() {
		createMock(12,12,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_13_Services() {
		createMock(13,13,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_14_Services() {
		createMock(14,14,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_15_Services() {
		createMock(15,15,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_20_Services() {
		createMock(20,20,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_50_Services() {
		createMock(50,50,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_100_Services() {
		createMock(100,100,MESSAGES)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_1000_Services() {
		createMock(1000,1000,MESSAGES)
	}
	
}
