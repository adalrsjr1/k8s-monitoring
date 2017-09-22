/**
 */
package model.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.Message;
import model.ServiceReplica;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreEMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Replica</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ServiceReplicaImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link model.impl.ServiceReplicaImpl#getMetrics <em>Metrics</em>}</li>
 *   <li>{@link model.impl.ServiceReplicaImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link model.impl.ServiceReplicaImpl#getId <em>Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceReplicaImpl extends ServiceImpl implements ServiceReplica {
	/**
	 * The cached value of the '{@link #getMessages() <em>Messages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessages()
	 * @generated
	 * @ordered
	 */
	protected EList<Message> messages;

	/**
	 * The cached value of the '{@link #getMetrics() <em>Metrics</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetrics()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Long> metrics;

	/**
	 * The default value of the '{@link #getContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTAINER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getContainer() <em>Container</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainer()
	 * @generated
	 * @ordered
	 */
	protected String container = CONTAINER_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceReplicaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.SERVICE_REPLICA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Message> getMessages() {
		if (messages == null) {
			messages = new EObjectEList<Message>(Message.class, this, ModelPackageImpl.SERVICE_REPLICA__MESSAGES);
		}
		return messages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Long> getMetrics() {
		if (metrics == null) {
			metrics = new EcoreEMap<String,Long>(ModelPackageImpl.Literals.STRING_TO_LONG_MAP, StringToLongMapImpl.class, this, ModelPackageImpl.SERVICE_REPLICA__METRICS);
		}
		return metrics.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getContainer() {
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContainer(String newContainer) {
		String oldContainer = container;
		container = newContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_REPLICA__CONTAINER, oldContainer, container));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_REPLICA__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackageImpl.SERVICE_REPLICA__MESSAGES:
				return getMessages();
			case ModelPackageImpl.SERVICE_REPLICA__METRICS:
				if (coreType) return ((EMap.InternalMapView<String, Long>)getMetrics()).eMap();
				else return getMetrics();
			case ModelPackageImpl.SERVICE_REPLICA__CONTAINER:
				return getContainer();
			case ModelPackageImpl.SERVICE_REPLICA__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackageImpl.SERVICE_REPLICA__MESSAGES:
				getMessages().clear();
				getMessages().addAll((Collection<? extends Message>)newValue);
				return;
			case ModelPackageImpl.SERVICE_REPLICA__METRICS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Long>)getMetrics()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.SERVICE_REPLICA__CONTAINER:
				setContainer((String)newValue);
				return;
			case ModelPackageImpl.SERVICE_REPLICA__ID:
				setId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackageImpl.SERVICE_REPLICA__MESSAGES:
				getMessages().clear();
				return;
			case ModelPackageImpl.SERVICE_REPLICA__METRICS:
				getMetrics().clear();
				return;
			case ModelPackageImpl.SERVICE_REPLICA__CONTAINER:
				setContainer(CONTAINER_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_REPLICA__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackageImpl.SERVICE_REPLICA__MESSAGES:
				return messages != null && !messages.isEmpty();
			case ModelPackageImpl.SERVICE_REPLICA__METRICS:
				return metrics != null && !metrics.isEmpty();
			case ModelPackageImpl.SERVICE_REPLICA__CONTAINER:
				return CONTAINER_EDEFAULT == null ? container != null : !CONTAINER_EDEFAULT.equals(container);
			case ModelPackageImpl.SERVICE_REPLICA__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (container: ");
		result.append(container);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //ServiceReplicaImpl
