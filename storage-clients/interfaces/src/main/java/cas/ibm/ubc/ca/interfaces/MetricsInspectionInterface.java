package cas.ibm.ubc.ca.interfaces;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface MetricsInspectionInterface {
	List metrics();
	List metrics(String container);
}
