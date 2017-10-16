package cas.ibm.ubc.ca.model.manager

import cas.ibm.ubc.ca.interfaces.ReificationInterface
import javax.naming.OperationNotSupportedException

class ExecutorClient implements ReificationInterface {

	@Override
	public boolean move(String application, String service, String hostSource, String hostDestination) {
		throw new OperationNotSupportedException()
		return false;
	}

}
