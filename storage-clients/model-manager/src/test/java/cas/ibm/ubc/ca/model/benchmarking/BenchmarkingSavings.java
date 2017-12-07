package cas.ibm.ubc.ca.model.benchmarking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	cas.ibm.ubc.ca.model.benchmarking.save.heuristic.BenchmarkingPlanningHeuristic_m_10.class, 
	cas.ibm.ubc.ca.model.benchmarking.save.heuristic.BenchmarkingPlanningHeuristic_m_100.class,
	cas.ibm.ubc.ca.model.benchmarking.save.heuristic.BenchmarkingPlanningHeuristic_m_1000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.heuristic.BenchmarkingPlanningHeuristic_m_10000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.heuristic.BenchmarkingPlanningHeuristic_m_100000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smt.BenchmarkingPlanningSMT_m_10.class, 
	cas.ibm.ubc.ca.model.benchmarking.save.smt.BenchmarkingPlanningSMT_m_100.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smt.BenchmarkingPlanningSMT_m_1000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smt.BenchmarkingPlanningSMT_m_10000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smt.BenchmarkingPlanningSMT_m_100000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smtsolver.BenchmarkingPlanningSMTSolver_m_10.class, 
	cas.ibm.ubc.ca.model.benchmarking.save.smtsolver.BenchmarkingPlanningSMTSolver_m_100.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smtsolver.BenchmarkingPlanningSMTSolver_m_1000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smtsolver.BenchmarkingPlanningSMTSolver_m_10000.class,
	cas.ibm.ubc.ca.model.benchmarking.save.smtsolver.BenchmarkingPlanningSMTSolver_m_100000.class,
	
})
public class BenchmarkingSavings {

}
