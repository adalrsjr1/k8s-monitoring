package cas.ibm.ubc.ca.influx.exception;

public class NoResultsException extends BaseException {
  public NoResultsException() {
    super("Query didn't return any results.");
  }
}
