package cas.ibm.ubc.ca.interfaces;

import java.util.Map;

import cas.ibm.ubc.ca.interfaces.messages.TimeInterval;

public interface MetricsInspectionInterface {
	
	public static enum Measurement {
		CPU, MEMORY
	}
	
	/**
	 * <p>It returns a list of maps with cpu and memory values in a given interval. </p>
	 * <p>cpu in milicores </p>
	 * <p>memory in bytes </p>
	 * 
	 * @param measurement
	 * @param timeInterval
	 * @return
	 */
	Map<String, Double> metricsService(Measurement measurement, TimeInterval timeInterval);
	/**
	 * <p>It returns a list of maps with cpu and memory values in a given interval. </p>
	 * <p>cpu in milicores </p>
	 * <p>memory in bytes </p>
	 * 
	 * @param measurement
	 * @param timeInterval
	 * @return
	 */
	Map<String, Double> metricsHost(Measurement measurement, TimeInterval timeInterval);
	/**
	 * <p>It returns a value in a given interval. </p>
	 * <p>cpu in milicores, or </p>
	 * <p>memory in bytes </p>
	 * 
	 * @param measurement
	 * @param timeInterval
	 * @return
	 */
	Double metricService(String id, Measurement measurement, TimeInterval timeInterval);
	/**
	 * <p>It returns a value in a given interval. </p>
	 * <p>cpu in milicores, or </p>
	 * <p>memory in bytes </p>
	 * 
	 * @param measurement
	 * @param timeInterval
	 * @return
	 */
	Double metricHost(String id, Measurement measurement, TimeInterval timeInterval);
}
