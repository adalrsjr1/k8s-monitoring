package cas.ibm.ubc.ca.model.benchmarking.save.smtsolver;

import org.junit.AfterClass
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import cas.ibm.ubc.ca.interfaces.MetricsInspectionInterface.Measurement;
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;
import cas.ibm.ubc.ca.model.benchmarking.BenchmarkConfig
import cas.ibm.ubc.ca.model.benchmarking.MonitoringMock
import cas.ibm.ubc.ca.model.manager.ModelHandler;
import cas.ibm.ubc.ca.model.manager.analyzer.AffinitiesAnalyzer
import cas.ibm.ubc.ca.model.manager.planner.HeuristicAdaptationPlanner
import cas.ibm.ubc.ca.model.manager.planner.Z3SolverAdaptationPlanner
import groovy.transform.CompileStatic;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.carrotsearch.junitbenchmarks.annotation.LabelType;
import org.junit.runners.MethodSorters;
import java.util.concurrent.TimeUnit;
import model.Affinity
import model.ServiceInstance

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@BenchmarkMethodChart(filePrefix = "model-planning-smt_solver-hosts_diff-100000_messages")
@BenchmarkHistoryChart(labelWith = LabelType.RUN_ID, maxRuns = 20)
class BenchmarkingPlanningSMTSolver_m_100000 {
	static final String MOVES_FILENAME = "benchmarking-planner-smt_solver-moves.txt"
	static final String BENCHMARKING_PATH = BenchmarkConfig.BENCHMARK_PATH
	@AfterClass
	public static void cleanup() {
		BenchmarkConfig.cleanupXmi()
	}
	
	static private createFiles(svcs, hosts, msgs) {
		def p = "${BenchmarkConfig.PROCESS_NAME_NO_ARGS} ${hosts} ${svcs} ${msgs}".execute()
		p.waitFor()
	}
	
	static private int createMock(svcs, hosts, msgs) {
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
		def affinities = analyzer.calculate(cluster, resource)
		
		return calculateHostsDiff(handler, cluster, affinities)
		
	}
	
	private static int calculateHostsDiff(def handler, def cluster, def affinities) {
		Iterator it = cluster.applications["APP"].services.values().iterator()
		Map map = [:]
		while(it.hasNext()) {
			ServiceInstance s = it.next()
			map[s.name] = s.host.name
		}
		int initial_size = map.size()
		
		Z3SolverAdaptationPlanner planner = new Z3SolverAdaptationPlanner(handler,BenchmarkConfig.Z3_WAIT_TIME)
		map = planner.execute(affinities).inject(map) { Map result, Moviment m ->
			result[m.service] = m.hostDestination
			result
		}
		
		int final_size = map.values().toUnique().size()
		int diff = initial_size - final_size
		return diff
	}
	
	@BeforeClass
	public static void loadProperties() throws IOException {
		BenchmarkConfig.loadConfig()
		BenchmarkConfig.createFile(MOVES_FILENAME)
	}
	
	
	public static void printNumberMoves(text) {
		BenchmarkConfig.appendToFile(MOVES_FILENAME, this.getSimpleName()+"_"+text)
	}
	
	public static void printNumberMoves(file, text) {
		BenchmarkConfig.appendToFile(file, this.getSimpleName()+"_"+text)
	}
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	// 1_1_10_1_2 == 1x10^2 == 100
	// svcs hosts messages
	static final int MESSAGES = 100000
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_10_Messages_10_Services() {
		def x = createMock(10,10,MESSAGES); printNumberMoves("savings.txt","10_10_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_11_Messages_11_Services() {
		def x = createMock(11,11,MESSAGES); printNumberMoves("savings.txt","11_11_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_12_Messages_12_Services() {
		def x = createMock(12,12,MESSAGES); printNumberMoves("savings.txt","12_12_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_13_Messages_13_Services() {
		def x = createMock(13,13,MESSAGES); printNumberMoves("savings.txt","13_13_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_14_Messages_14_Services() {
		def x = createMock(14,14,MESSAGES); printNumberMoves("savings.txt","14_14_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_15_Messages_15_Services() {
		def x = createMock(15,15,MESSAGES); printNumberMoves("savings.txt","15_15_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_20_Messages_20_Services() {
		def x = createMock(20,20,MESSAGES); printNumberMoves("savings.txt","20_20_${MESSAGES}=${x}")
	}
	@Test(timeout=600000L)
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 1)
	void testBuildModel_30_Messages_30_Services() {
		def x = createMock(30,30,MESSAGES); printNumberMoves("savings.txt","30_30_${MESSAGES}=${x}")
	}
}
