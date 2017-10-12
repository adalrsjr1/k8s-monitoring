package cas.ibm.ubc.ca.k8s.interfaces

interface ClusterInspectionInterface {
	List hosts()
	List services()
	List applications()
	String cluster()
}
