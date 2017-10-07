package cas.ibm.ubc.ca.model.adapters

import java.util.List

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import model.Application
import model.Cluster
import model.Environment
import model.Host
import model.impl.ClusterImpl

abstract class ClusterAdapter {
	private static final Logger LOG = LoggerFactory.getLogger(ClusterAdapter.class)
	
	public static void move(Cluster cluster, String application, String serviceId, String destinationHost) {
		
	}
	
	public static boolean environmentStub() {
		LOG.info("environment: moving service...")
		Thread.sleep(1000)
		LOG.info("environment: moving finished.")
	}



}
