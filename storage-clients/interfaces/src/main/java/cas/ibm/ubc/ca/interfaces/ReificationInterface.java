package cas.ibm.ubc.ca.interfaces;

import java.util.List;

import cas.ibm.ubc.ca.interfaces.messages.Moviment;

public interface ReificationInterface {

	boolean move(List<Moviment> adaptationScript);
	
	boolean move(Moviment moviment);
}
