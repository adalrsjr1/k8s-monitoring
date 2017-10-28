package cas.ibm.ubc.ca.model.manager

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment

class ExecutorClient implements ReificationInterface {

	@Override
	public boolean move(Moviment moviment) {
		throw new UnsupportedOperationException()
		return false;
	}

	@Override
	public boolean move(List<Moviment> adaptationScript) {
		throw new UnsupportedOperationException()
		return false;
	}

}
