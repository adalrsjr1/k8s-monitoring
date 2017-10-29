package cas.ibm.ubc.ca.model.manager.executor

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import cas.ibm.ubc.ca.interfaces.messages.Moviment

class MoveExecutor implements ReificationInterface {

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
