package cas.ibm.ubc.ca.k8s.model

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.Pod

class PodUtil {

	static String podFullName (Pod pod) {
		pod.getMetadata().getName()
	}
	
	static String podName(Pod pod) {
		String generateName = pod.getMetadata().getGenerateName() 
		return generateName.substring(0, generateName.length()-1)
	}
	
	static String podId (Pod pod) {
		pod.getMetadata().getUid()
	}
	
	static  List<String> podVersion(Pod pod) {
		// it is tested for pods with a single container
		pod.getSpec().getContainers().inject([]) { list, p ->
			list << p.image
		}
	}	
	
}
