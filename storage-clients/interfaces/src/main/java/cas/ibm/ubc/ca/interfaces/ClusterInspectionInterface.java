package cas.ibm.ubc.ca.interfaces;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface ClusterInspectionInterface {
	List hosts();
	List services();
	Map<String, Float> applications();
	String cluster();
}
