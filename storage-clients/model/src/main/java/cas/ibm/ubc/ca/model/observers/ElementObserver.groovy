package cas.ibm.ubc.ca.model.observers

import model.Cluster
import org.eclipse.emf.common.notify.Adapter
import org.eclipse.emf.common.notify.Notification
import org.eclipse.emf.common.notify.impl.AdapterImpl

// observes a single element
class ElementObserver {
	private Cluster cluster

	ElementObserver(Cluster cluster) {
		this.cluster = cluster

		Adapter adapter = new AdapterImpl() {
					public void notifyChanged(Notification notification) {
						super.notifyChanged(notification)
						println "${notification}"
					}
				}
		this.cluster.eAdapters().add(adapter)
	}
}