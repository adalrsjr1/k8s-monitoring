package cas.ibm.ubc.ca.interfaces;

import java.util.List;

@SuppressWarnings("rawtypes")
public interface MessagesInspectionInterface {
	List messages();
	List messages(String serviceInstance);
}
