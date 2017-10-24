package cas.ibm.ubc.ca.zipkin;

public class ZipkinClient {
	//http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
	// http://zipkin.io/zipkin-api/#/paths/


	public static void main(String[] args) throws Exception {
		
		ZipkinRequestor requestor = new ZipkinRequestor("192.168.99.100", 30002)
		
//		println requestor.getServices()
//		requestor.getTraces(endTs:1503361800000).each {
//			 it.each{
//			 println it.hashCode()
//			}
//		}
		
		println requestor.getDependencies(1703361800000)
		
									 
	}
}
