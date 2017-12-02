package cas.ibm.ubc.ca.model.benchmarking

import javax.swing.filechooser.FileFilter

class BenchmarkConfig {
	public static final String BENCHMARK_PATH = "/home/adalrsjr1/Code/ibm-stack/benchmarking-input/"
	public static final String PROCESS_NAME_NO_ARGS = "python /home/adalrsjr1/Code/ibm-stack/storage-clients/model-manager/src/test/resources/random_generator.py"
	public static final double MIN = 0.0
	public static final double MAX = 1000.0 
	
	public static loadConfig() {
		Properties p = new Properties();
		p.load(new FileInputStream(new File("src/test/resources/jub.properties")));
		for(String k:p.stringPropertyNames()){
			System.setProperty(k,p.getProperty(k));
		}
	}
	
	private static String getPath(type, svcs, hosts, msgs) {
		return BENCHMARK_PATH + "${type}-random-${svcs}-${hosts}-${msgs}.json"
	}
	
	public static String getHostsPath(svcs, hosts, msgs) {
		return getPath("hosts", svcs, hosts, msgs)
	}
	
	public static String getSvcsPath(svcs, hosts, msgs) {
		return getPath("svcs", svcs, hosts, msgs)
	}
	
	public static String getMsgsPath(svcs, hosts, msgs) {
		return getPath("msgs", svcs, hosts, msgs)
	}
	
	public static String getMetricsPath(svcs, hosts, msgs) {
		return getPath("metrics", svcs, hosts, msgs)
	}
	
	public static void cleanupXmi() {
		
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name =~ /(.)*\.xmi/
			}
		}
		new File(BENCHMARK_PATH).listFiles(filter).each { File f ->
			f.delete()
		}
	}
	
	public static void createFile(name) {
		new File(BENCHMARK_PATH+"$name").createNewFile()
	}
	
	public static void appendToFile(name, text) {
		new File(BENCHMARK_PATH+"$name").append(text+"\n", "UTF-8")
	}
	
}
