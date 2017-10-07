package cas.ibm.ubc.ca.model.observers

import model.Cluster
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.ecore.util.EContentAdapter

// observes a tree of elements
class TotalObserver {
	private Cluster cluster

	TotalObserver(Cluster cluster) {
		this.cluster = cluster

		EContentAdapter adapter = new EContentAdapter() {
					public void notifyChanged(Notification notification) {
						super.notifyChanged(notification)
						println "${notification}"
					}
				}
		this.cluster.eAdapters().add(adapter)
	}
}