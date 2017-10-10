package cas.ibm.ubc.ca.k8s.interfaces

interface ReificationInterface {

	boolean move(String application, String service, String hostSource, String hostDestination)
}
