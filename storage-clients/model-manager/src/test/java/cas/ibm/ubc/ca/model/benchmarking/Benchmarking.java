package cas.ibm.ubc.ca.model.benchmarking;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cas.ibm.ubc.ca.model.benchmarking.analysis.BenchmarkingAnalysis_m_10;
import cas.ibm.ubc.ca.model.benchmarking.analysis.BenchmarkingAnalysis_m_100;
import cas.ibm.ubc.ca.model.benchmarking.analysis.BenchmarkingAnalysis_m_1000;
import cas.ibm.ubc.ca.model.benchmarking.analysis.BenchmarkingAnalysis_m_10000;
import cas.ibm.ubc.ca.model.benchmarking.analysis.BenchmarkingAnalysis_m_100000;
import cas.ibm.ubc.ca.model.benchmarking.modelcreation.BenchmarkingModelCreation_m_10;
import cas.ibm.ubc.ca.model.benchmarking.modelcreation.BenchmarkingModelCreation_m_100;
import cas.ibm.ubc.ca.model.benchmarking.modelcreation.BenchmarkingModelCreation_m_1000;
import cas.ibm.ubc.ca.model.benchmarking.modelcreation.BenchmarkingModelCreation_m_10000;
import cas.ibm.ubc.ca.model.benchmarking.modelcreation.BenchmarkingModelCreation_m_100000;

@RunWith(Suite.class)
@SuiteClasses({ 
	BenchmarkingModelCreation_m_10.class, 
	BenchmarkingModelCreation_m_100.class,
	BenchmarkingModelCreation_m_1000.class,
	BenchmarkingModelCreation_m_10000.class,
	BenchmarkingModelCreation_m_100000.class,
	BenchmarkingAnalysis_m_10.class, 
	BenchmarkingAnalysis_m_100.class,
	BenchmarkingAnalysis_m_1000.class,
	BenchmarkingAnalysis_m_10000.class,
	BenchmarkingAnalysis_m_100000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.heuristic.BenchmarkingPlanningHeuristic_m_10.class, 
	cas.ibm.ubc.ca.model.benchmarking.planner.heuristic.BenchmarkingPlanningHeuristic_m_100.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.heuristic.BenchmarkingPlanningHeuristic_m_1000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.heuristic.BenchmarkingPlanningHeuristic_m_10000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.heuristic.BenchmarkingPlanningHeuristic_m_100000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smt.BenchmarkingPlanningSMT_m_10.class, 
	cas.ibm.ubc.ca.model.benchmarking.planner.smt.BenchmarkingPlanningSMT_m_100.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smt.BenchmarkingPlanningSMT_m_1000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smt.BenchmarkingPlanningSMT_m_10000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smt.BenchmarkingPlanningSMT_m_100000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smtsolver.BenchmarkingPlanningSMTSolver_m_10.class, 
	cas.ibm.ubc.ca.model.benchmarking.planner.smtsolver.BenchmarkingPlanningSMTSolver_m_100.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smtsolver.BenchmarkingPlanningSMTSolver_m_1000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smtsolver.BenchmarkingPlanningSMTSolver_m_10000.class,
	cas.ibm.ubc.ca.model.benchmarking.planner.smtsolver.BenchmarkingPlanningSMTSolver_m_100000.class,
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
public class Benchmarking {

}
