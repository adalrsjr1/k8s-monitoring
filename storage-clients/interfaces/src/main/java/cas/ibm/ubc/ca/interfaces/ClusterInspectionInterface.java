package cas.ibm.ubc.ca.interfaces;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface ClusterInspectionInterface {
	List hosts();
	List services();
	List applications();
	String cluster();
}
