package cas.ibm.ubc.ca.interfaces;

import java.util.List;

public interface ReificationInterface {

	boolean move(List<Moviment> adaptationScript);
	
	boolean move(Moviment moviment);
}
