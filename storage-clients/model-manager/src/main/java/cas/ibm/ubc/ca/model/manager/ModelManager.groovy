package cas.ibm.ubc.ca.model.manager

import cas.ibm.ubc.ca.model.adapters.ClusterAdapter
import cas.ibm.ubc.ca.model.adapters.ModelFactoryAdapter
import okhttp3.OkHttpClient

import model.Cluster
import model.Environment

class ModelManager {
	final ClusterAdapter clusterAdapter
	final Cluster cluster
	final ModelFactoryAdapter modelFactory
	
	final OkHttpClient httpClient
	
	final String monitoring = "monitoring/path"
	
	public ModelManager(String environment) {
		modelFactory = ModelFactoryAdapter.getINSTANCE()
		
		cluster = modelFactory.createCluster()
		cluster.environment = Environment.getByName(environment)
		
		httpClient = new OkHttpClient()
	}
}
