package cas.ibm.ubc.ca.interfaces.messages;

public class Moviment {
	public final String application;
	public final String service;
	public final String hostSource;
	public final String hostDestination;
	
	private static final Moviment NON_MOVE = Moviment.create("", "", "", "");
	
	private Moviment(String application, String service, String hostSource, String hostDestination) {
		super();
		this.application = application;
		this.service = service;
		this.hostSource = hostSource;
		this.hostDestination = hostDestination;
	}
	
	public static Moviment create(String application, String service, String hostSource, String hostDestination) {
		return new Moviment(application, service, hostSource, hostDestination);
	}

	public Moviment createUndoMoviment() {
		return Moviment.create(application, service, hostDestination, hostSource);
	}
	
	public static Moviment nonMove() {
		return NON_MOVE;
	}
	
	public String getApplication() {
		return application;
	}

	public String getService() {
		return service;
	}

	public String getHostSource() {
		return hostSource;
	}

	public String getHostDestination() {
		return hostDestination;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((application == null) ? 0 : application.hashCode());
		result = prime * result + ((hostDestination == null) ? 0 : hostDestination.hashCode());
		result = prime * result + ((hostSource == null) ? 0 : hostSource.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moviment other = (Moviment) obj;
		if (application == null) {
			if (other.application != null)
				return false;
		} else if (!application.equals(other.application))
			return false;
		if (hostDestination == null) {
			if (other.hostDestination != null)
				return false;
		} else if (!hostDestination.equals(other.hostDestination))
			return false;
		if (hostSource == null) {
			if (other.hostSource != null)
				return false;
		} else if (!hostSource.equals(other.hostSource))
			return false;
		if (service == null) {
			if (other.service != null)
				return false;
		} else if (!service.equals(other.service))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if(this == nonMove()) {
			return "nonMove";
		}
		return hostSource + "[" + application + "." + service + "]" + " --> " + hostDestination + "[" + application + "." + service + "]";
	}
	
}