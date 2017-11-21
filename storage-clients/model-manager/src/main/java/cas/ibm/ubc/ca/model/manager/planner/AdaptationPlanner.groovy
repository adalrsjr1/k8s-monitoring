package cas.ibm.ubc.ca.model.manager.planner

import java.util.List

import cas.ibm.ubc.ca.interfaces.messages.Moviment

interface AdaptationPlanner {
	List<Moviment> execute(List affinities)
}
