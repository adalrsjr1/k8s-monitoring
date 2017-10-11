/**
 */
package model.impl;

import java.util.Collection;
import java.util.List;

import model.Message;
import model.ServiceInstance;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ServiceInstanceImpl#getMessages <em>Messages</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getId <em>Id</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getHostAddress <em>Host Address</em>}</li>
 *   <li>{@link model.impl.ServiceInstanceImpl#getContainers <em>Containers</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ServiceInstanceImpl extends ServiceImpl implements ServiceInstance {
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
	 * The default value of the '{@link #getHostAddress() <em>Host Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostAddress()
	 * @generated
	 * @ordered
	 */
	protected static final String HOST_ADDRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHostAddress() <em>Host Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostAddress()
	 * @generated
	 * @ordered
	 */
	protected String hostAddress = HOST_ADDRESS_EDEFAULT;

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
	public String getHostAddress() {
		return hostAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHostAddress(String newHostAddress) {
		String oldHostAddress = hostAddress;
		hostAddress = newHostAddress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.SERVICE_INSTANCE__HOST_ADDRESS, oldHostAddress, hostAddress));
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
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
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
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				return getMessages();
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				return getId();
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				return getAddress();
			case ModelPackageImpl.SERVICE_INSTANCE__HOST_ADDRESS:
				return getHostAddress();
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				return getContainers();
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
			case ModelPackageImpl.SERVICE_INSTANCE__HOST_ADDRESS:
				setHostAddress((String)newValue);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				getContainers().clear();
				getContainers().addAll((Collection<? extends String>)newValue);
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
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				getMessages().clear();
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				setId(ID_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				setAddress(ADDRESS_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__HOST_ADDRESS:
				setHostAddress(HOST_ADDRESS_EDEFAULT);
				return;
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				getContainers().clear();
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
			case ModelPackageImpl.SERVICE_INSTANCE__MESSAGES:
				return messages != null && !messages.isEmpty();
			case ModelPackageImpl.SERVICE_INSTANCE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case ModelPackageImpl.SERVICE_INSTANCE__ADDRESS:
				return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
			case ModelPackageImpl.SERVICE_INSTANCE__HOST_ADDRESS:
				return HOST_ADDRESS_EDEFAULT == null ? hostAddress != null : !HOST_ADDRESS_EDEFAULT.equals(hostAddress);
			case ModelPackageImpl.SERVICE_INSTANCE__CONTAINERS:
				return containers != null && !containers.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", address: ");
		result.append(address);
		result.append(", hostAddress: ");
		result.append(hostAddress);
		result.append(", containers: ");
		result.append(containers);
		result.append(')');
		return result.toString();
	}

} //ServiceInstanceImpl
