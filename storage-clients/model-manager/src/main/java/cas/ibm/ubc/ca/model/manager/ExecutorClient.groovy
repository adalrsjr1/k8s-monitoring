package cas.ibm.ubc.ca.model.manager

import javax.naming.OperationNotSupportedException

import cas.ibm.ubc.ca.interfaces.Moviment
import cas.ibm.ubc.ca.interfaces.ReificationInterface

class ExecutorClient implements ReificationInterface {

	@Override
	public boolean move(Moviment moviment) {
		throw new OperationNotSupportedException()
		return false;
	}

	@Override
	public boolean move(List<Moviment> adaptationScript) {
		throw new OperationNotSupportedException()
		return false;
	}

}
