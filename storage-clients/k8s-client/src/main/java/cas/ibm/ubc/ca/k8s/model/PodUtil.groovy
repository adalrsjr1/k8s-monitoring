package cas.ibm.ubc.ca.k8s.model

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.api.model.Pod

class PodUtil {

	static String podFullName (Pod pod) {
		pod.getMetadata().getName()
	}
	
	static String podName(Pod pod) {
		String fullName = podFullName(pod)
		
		String[] splittedName = fullName.split("-")
		int nameSize = splittedName.size()
		
		// is it a magic number? 
		// https://github.com/kubernetes/community/blob/master/contributors/design-proposals/identifiers.md
		if(nameSize <= 3) {
			return fullName.split("-")[0]
		}
		
		return "${splittedName[0]}-${splittedName[1]}"
	}
	
	static String podId (Pod pod) {
		pod.getMetadata().getUid()
	}
	
	static  List<String> podVersion(Pod pod) {
		pod.getSpec().getContainers().inject([]) { list, p ->
			list << p.image
		}
	}	
	
}
