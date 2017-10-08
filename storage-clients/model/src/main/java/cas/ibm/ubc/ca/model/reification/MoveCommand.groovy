package cas.ibm.ubc.ca.model.reification

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import groovy.transform.TupleConstructor

@TupleConstructor
class MoveCommand implements IReificationCommand {
	private static final Logger LOG = LoggerFactory.getLogger(MoveCommand.class)

	String application
	String serviceId
	String sourceHost
	String destinationHost
	
	
	@Override
	public boolean execute() {
		LOG.info("environment: moving...")
		LOG.trace("[$sourceHost] $application.$serviceId -> $destinationHost")
		Thread.sleep(1000)
		LOG.info("environment: moving finished.")
		throw new RuntimeException("MoveCommand.execute() not implemented yet.")
		return false
	}

}
