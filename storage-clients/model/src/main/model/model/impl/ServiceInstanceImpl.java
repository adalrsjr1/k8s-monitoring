/**
 */
package model.impl;

import java.util.Collection;
import java.util.List;

import java.util.Map;
import model.ElementWithResources;
import model.Host;
import model.Message;
import model.ServiceInstance;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ServiceInstanceImpl#getResourceLimit <em>Resource Limit</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getMetrics <em>Metrics</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getId <em>Id</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getContainers <em>Containers</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getTotalMessages <em>Total Messages</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getTotalData <em>Total Data</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getHost <em>Host</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceInstanceImpl extends ServiceImpl implements ServiceInstance {
	/**
	 * The cached value of the '{@link #getResourceLimit() <em>Resource Limit</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceLimit()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Double> resourceLimit;

	/**
	 * The cached value of the '{@link #getMetrics() <em>Metrics</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetrics()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Double> metrics;

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
	 * The default value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected String address = ADDRESS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContainers() <em>Containers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<String> containers;

	/**
	 * The default value of the '{@link #getTotalMessages() <em>Total Messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalMessages()
	 * @generated
	 * @ordered
	 */
	protected static final Long TOTAL_MESSAGES_EDEFAULT = new Long(0L);

	/**
	 * The cached value of the '{@link #getTotalMessages() <em>Total Messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalMessages()
	 * @generated
	 * @ordered
	 */
	protected Long totalMessages = TOTAL_MESSAGES_EDEFAULT;

	/**
	 * The default value of the '{@link #getTotalData() <em>Total Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalData()
	 * @generated
	 * @ordered
	 */
	protected static final Long TOTAL_DATA_EDEFAULT = new Long(0L);

	/**
	 * The cached value of the '{@link #getTotalData() <em>Total Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTotalData()
	 * @generated
	 * @ordered
	 */
	protected Long totalData = TOTAL_DATA_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected Host host;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ServiceInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.SERVICE_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Double> getResourceLimit() {
		if (resourceLimit == null) {
			resourceLimit = new EcoreEMap<String,Double>(ModelPackageImpl.Literals.STRING_TO_DOUBLE_MAP, StringToDoubleMapImpl.class, this, ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT);
		}
		return resourceLimit.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Double> getMetrics() {
		if (metrics == null) {
			metrics = new EcoreEMap<String,Double>(ModelPackageImpl.Literals.STRING_TO_DOUBLE_MAP, StringToDoubleMapImpl.class, this, ModelPackageImpl.SERVICE_INSTANCE__METRICS);
		}
		return metrics.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<Message> getMessages() {
		if (messages == null) {
			messages = new EObjectContainmentEList<Message>(Message.class, this, ModelPackageImpl.SERVICE_INSTANCE__MESSAGES);
		}
		return messages;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_INSTANCE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(String newAddress) {
		String oldAddress = address;
		address = newAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_INSTANCE__ADDRESS, oldAddress, address));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getContainers() {
		if (containers == null) {
			containers = new EDataTypeUniqueEList<String>(String.class, this, ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS);
		}
		return containers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getTotalMessages() {
		return totalMessages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalMessages(Long newTotalMessages) {
		Long oldTotalMessages = totalMessages;
		totalMessages = newTotalMessages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_INSTANCE__TOTAL_MESSAGES, oldTotalMessages, totalMessages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getTotalData() {
		return totalData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTotalData(Long newTotalData) {
		Long oldTotalData = totalData;
		totalData = newTotalData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_INSTANCE__TOTAL_DATA, oldTotalData, totalData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Host getHost() {
		if (host != null && ((EObject)host).eIsProxy()) {
			InternalEObject oldHost = (InternalEObject)host;
			host = (Host)eResolveProxy(oldHost);
			if (host != oldHost) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackageImpl.SERVICE_INSTANCE__HOST, oldHost, host));
			}
		}
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Host basicGetHost() {
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHost(Host newHost) {
		Host oldHost = host;
		host = newHost;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_INSTANCE__HOST, oldHost, host));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Double>)getResourceLimit()).eMap()).basicRemove(otherEnd, msgs);
			case ModelPackageImpl.SERVICE_INSTANCE__METRICS:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Double>)getMetrics()).eMap()).basicRemove(otherEnd, msgs);
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				return ((InternalEList<?>)getMessages()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT:
				if (coreType) return ((EMap.InternalMapView<String, Double>)getResourceLimit()).eMap();
				else return getResourceLimit();
			case ModelPackageImpl.SERVICE_INSTANCE__METRICS:
				if (coreType) return ((EMap.InternalMapView<String, Double>)getMetrics()).eMap();
				else return getMetrics();
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				return getMessages();
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				return getId();
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				return getAddress();
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				return getContainers();
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_MESSAGES:
				return getTotalMessages();
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_DATA:
				return getTotalData();
			case ModelPackageImpl.SERVICE_INSTANCE__HOST:
				if (resolve) return getHost();
				return basicGetHost();
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
			case ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Double>)getResourceLimit()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__METRICS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Double>)getMetrics()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				getMessages().clear();
				getMessages().addAll((Collection<? extends Message>)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				setId((String)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				setAddress((String)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_MESSAGES:
				setTotalMessages((Long)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_DATA:
				setTotalData((Long)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__HOST:
				setHost((Host)newValue);
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
			case ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT:
				getResourceLimit().clear();
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__METRICS:
				getMetrics().clear();
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				getMessages().clear();
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				getContainers().clear();
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_MESSAGES:
				setTotalMessages(TOTAL_MESSAGES_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_DATA:
				setTotalData(TOTAL_DATA_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__HOST:
				setHost((Host)null);
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
			case ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT:
				return resourceLimit != null && !resourceLimit.isEmpty();
			case ModelPackageImpl.SERVICE_INSTANCE__METRICS:
				return metrics != null && !metrics.isEmpty();
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				return messages != null && !messages.isEmpty();
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				return containers != null && !containers.isEmpty();
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_MESSAGES:
				return TOTAL_MESSAGES_EDEFAULT == null ? totalMessages != null : !TOTAL_MESSAGES_EDEFAULT.equals(totalMessages);
			case ModelPackageImpl.SERVICE_INSTANCE__TOTAL_DATA:
				return TOTAL_DATA_EDEFAULT == null ? totalData != null : !TOTAL_DATA_EDEFAULT.equals(totalData);
			case ModelPackageImpl.SERVICE_INSTANCE__HOST:
				return host != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ElementWithResources.class) {
			switch (derivedFeatureID) {
				case ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT: return ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT;
				case ModelPackageImpl.SERVICE_INSTANCE__METRICS: return ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ElementWithResources.class) {
			switch (baseFeatureID) {
				case ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT: return ModelPackageImpl.SERVICE_INSTANCE__RESOURCE_LIMIT;
				case ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS: return ModelPackageImpl.SERVICE_INSTANCE__METRICS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", address: ");
		result.append(address);
		result.append(", containers: ");
		result.append(containers);
		result.append(", totalMessages: ");
		result.append(totalMessages);
		result.append(", totalData: ");
		result.append(totalData);
		result.append(')');
		return result.toString();
	}

} //ServiceInstanceImpl
