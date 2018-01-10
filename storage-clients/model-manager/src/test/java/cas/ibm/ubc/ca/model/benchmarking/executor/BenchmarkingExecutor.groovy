package cas.ibm.ubc.ca.model.benchmarking.executor

import java.io.IOException

import org.junit.BeforeClass
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runners.MethodSorters

import com.carrotsearch.junitbenchmarks.BenchmarkOptions
import com.carrotsearch.junitbenchmarks.BenchmarkRule
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart
import com.carrotsearch.junitbenchmarks.annotation.LabelType

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import cas.ibm.ubc.ca.model.benchmarking.BenchmarkConfig
import cas.ibm.ubc.ca.model.manager.executor.MoveExecutor

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@BenchmarkMethodChart(filePrefix = "moves-benchmark")
@BenchmarkHistoryChart(labelWith = LabelType.RUN_ID, maxRuns = 20)
class BenchmarkingExecutor {
	static ReificationInterface managedCluster
	
	static final String HOST = "127.0.0.1"
	static final int PORT = 8001
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule()
	
	@BeforeClass
	static void setUp() {
		 managedCluster = new MoveExecutor(HOST, PORT)
	}
	
	@BeforeClass
	public static void loadProperties() throws IOException {
		BenchmarkConfig.loadConfig()
	}
	
	static final String APPLICATION = "sock-shop"
	static final String SERVICE = "user"
	static final String SOURCE = "minikube"
	static final String TARGET = "minikube"
//	static final URL SERVICE_URL = new URL("")
	
	private void move(int n) {
		Moviment prior = Moviment.nonMove()
		Moviment moviment = Moviment.nonMove()
		for(i in 0..n) {
			moviment = Moviment.create(APPLICATION, SERVICE, SOURCE, TARGET)
			managedCluster.move(moviment)
			moviment = moviment.createUndoMoviment()
		}
		
//		applicationIsUp(SERVICE_URL)
	}
	
	private void applicationIsUp(URL) {
		URL url = new URL("http://www.google.com")
		
		HttpURLConnection connection = url.openConnection()
		
		while(connection.getResponseCode() != 200) { }
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 5)
	void test0001_moves() {
		move(1)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0002_moves() {
		move(2)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0003_moves() {
		move(3)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0004_moves() {
		move(4)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0005_moves() {
		move(5)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0006_moves() {
		move(6)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0007_moves() {
		move(7)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0008_moves() {
		move(8)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0009_moves() {
		move(9)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0010_moves() {
		move(10)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0020_moves() {
		move(20)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0030_moves() {
		move(30)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0040_moves() {
		move(40)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0050_moves() {
		move(50)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0060_moves() {
		move(60)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0070_moves() {
		move(70)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0080_moves() {
		move(80)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0090_moves() {
		move(90)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0100_moves() {
		move(100)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0200_moves() {
		move(200)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0300_moves() {
		move(300)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0400_moves() {
		move(400)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0500_moves() {
		move(500)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0600_moves() {
		move(600)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0700_moves() {
		move(700)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0800_moves() {
		move(800)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test0900_moves() {
		move(900)
	}
	
	@Test
	@BenchmarkOptions(benchmarkRounds = 10, warmupRounds = 1)
	void test1000_moves() {
		move(1000)
	}
	
}
