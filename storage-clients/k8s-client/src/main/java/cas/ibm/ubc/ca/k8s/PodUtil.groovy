package cas.ibm.ubc.ca.k8s

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.Pod

class PodUtil {

	static String podName (Pod pod) {
		pod.getMetadata().getName()
	}
	
	static String podName (Pod pod, String regex) {
		String name = PodUtil.podName(pod) 
		if(name ==~ /$regex/) {
			return name
		}
		return ""
	}
	
	static String podGenerateName(Pod pod) {
		pod.getMetadata().getGenerateName()
	}
	
	static String podNamespace(Pod pod) {
		pod.getMetadata().getNamespace()
	}
		
	static String podId (Pod pod) {
		pod.getMetadata().getUid()
	}
	
	static String podIp(Pod pod) {
		pod.getStatus().getPodIP()
	}
	
	static String podHostIp(Pod pod) {
		pod.getStatus().getHostIP()
	}
	
	static  List<String> podVersion(Pod pod) {
		// it is tested for pods with a single container
		pod.getSpec().getContainers().inject([]) { list, p ->
			list << p.image
		}
	}
	
}
