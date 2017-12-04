package cas.ibm.ubc.ca.model.benchmarking.analysis;

import org.junit.AfterClass
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
@BenchmarkMethodChart(filePrefix = "model-analysis-100_messages")
@BenchmarkHistoryChart(labelWith = LabelType.RUN_ID, maxRuns = 20)
class BenchmarkingAnalysis_m_100 {
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
	// 1_1_10_1_2 == 1x10^2 == 100
	// svcs hosts messages
	static final int MESSAGES = 100
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_10_Messages_10_Services() {
		def x = createMock(10,10,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_11_Messages_11_Services() {
		def x = createMock(11,11,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_12_Messages_12_Services() {
		def x = createMock(12,12,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_13_Messages_13_Services() {
		def x = createMock(13,13,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_14_Messages_14_Services() {
		def x = createMock(14,14,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_15_Messages_15_Services() {
		def x = createMock(15,15,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_16_Messages_16_Services() {
		def x = createMock(16,16,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_17_Messages_17_Services() {
		def x = createMock(17,17,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_18_Messages_18_Services() {
		def x = createMock(18,18,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_19_Messages_19_Services() {
		def x = createMock(19,19,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_20_Messages_20_Services() {
		def x = createMock(20,20,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_30_Messages_30_Services() {
		def x = createMock(30,30,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_40_Messages_40_Services() {
		def x = createMock(40,40,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_50_Messages_50_Services() {
		def x = createMock(50,50,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_60_Messages_60_Services() {
		def x = createMock(60,60,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_70_Messages_70_Services() {
		def x = createMock(70,70,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_80_Messages_80_Services() {
		def x = createMock(80,80,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_90_Messages_90_Services() {
		def x = createMock(90,90,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_100_Messages_100_Services() {
		def x = createMock(100,100,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_200_Messages_200_Services() {
		def x = createMock(200,200,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_300_Messages_300_Services() {
		def x = createMock(300,300,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_400_Messages_400_Services() {
		def x = createMock(400,400,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_500_Messages_500_Services() {
		def x = createMock(500,500,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_600_Messages_600_Services() {
		def x = createMock(600,600,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_700_Messages_700_Services() {
		def x = createMock(700,700,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_800_Messages_800_Services() {
		def x = createMock(800,800,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_900_Messages_900_Services() {
		def x = createMock(900,900,MESSAGES)
	}
	@Test
	@BenchmarkOptions(benchmarkRounds = 5, warmupRounds = 1)
	void testBuildModel_1000_Messages_1000_Services() {
		def x = createMock(1000,1000,MESSAGES)
	}

}
