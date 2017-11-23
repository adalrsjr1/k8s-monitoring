package cas.ibm.ubc.ca.influx;

public enum DownsamplerFunction {
  MEAN   ("MEAN"),
  SUM	 ("SUM"), 
  MEDIAN ("MEDIAN");

  private String name;

  DownsamplerFunction(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }
}
