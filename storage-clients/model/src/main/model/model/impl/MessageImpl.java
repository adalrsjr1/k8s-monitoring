/**
 */
package model.impl;

import model.Message;
import model.ServiceInstance;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.MessageImpl#getSource <em>Source</em>}</li>
 *   <li>{@link model.impl.MessageImpl#getDestination <em>Destination</em>}</li>
 *   <li>{@link model.impl.MessageImpl#getName <em>Name</em>}</li>
 *   <li>{@link model.impl.MessageImpl#getAvgResponseTime <em>Avg Response Time</em>}</li>
 *   <li>{@link model.impl.MessageImpl#getTimestamp <em>Timestamp</em>}</li>
 *   <li>{@link model.impl.MessageImpl#getUid <em>Uid</em>}</li>
 *   <li>{@link model.impl.MessageImpl#getMessageSize <em>Message Size</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MessageImpl extends MinimalEObjectImpl.Container implements Message {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected ServiceInstance source;

	/**
	 * The cached value of the '{@link #getDestination() <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected ServiceInstance destination;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAvgResponseTime() <em>Avg Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvgResponseTime()
	 * @generated
	 * @ordered
	 */
	protected static final Long AVG_RESPONSE_TIME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAvgResponseTime() <em>Avg Response Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvgResponseTime()
	 * @generated
	 * @ordered
	 */
	protected Long avgResponseTime = AVG_RESPONSE_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected static final Long TIMESTAMP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTimestamp() <em>Timestamp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimestamp()
	 * @generated
	 * @ordered
	 */
	protected Long timestamp = TIMESTAMP_EDEFAULT;

	/**
	 * The default value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected String uid = UID_EDEFAULT;

	/**
	 * The default value of the '{@link #getMessageSize() <em>Message Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageSize()
	 * @generated
	 * @ordered
	 */
	protected static final Long MESSAGE_SIZE_EDEFAULT = new Long(0L);

	/**
	 * The cached value of the '{@link #getMessageSize() <em>Message Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessageSize()
	 * @generated
	 * @ordered
	 */
	protected Long messageSize = MESSAGE_SIZE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MessageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.MESSAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceInstance getSource() {
		if (source != null && ((EObject)source).eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (ServiceInstance)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackageImpl.MESSAGE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceInstance basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(ServiceInstance newSource) {
		ServiceInstance oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceInstance getDestination() {
		if (destination != null && ((EObject)destination).eIsProxy()) {
			InternalEObject oldDestination = (InternalEObject)destination;
			destination = (ServiceInstance)eResolveProxy(oldDestination);
			if (destination != oldDestination) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackageImpl.MESSAGE__DESTINATION, oldDestination, destination));
			}
		}
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceInstance basicGetDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestination(ServiceInstance newDestination) {
		ServiceInstance oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__DESTINATION, oldDestination, destination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getAvgResponseTime() {
		return avgResponseTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvgResponseTime(Long newAvgResponseTime) {
		Long oldAvgResponseTime = avgResponseTime;
		avgResponseTime = newAvgResponseTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__AVG_RESPONSE_TIME, oldAvgResponseTime, avgResponseTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getTimestamp() {
		return timestamp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimestamp(Long newTimestamp) {
		Long oldTimestamp = timestamp;
		timestamp = newTimestamp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__TIMESTAMP, oldTimestamp, timestamp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUid(String newUid) {
		String oldUid = uid;
		uid = newUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Long getMessageSize() {
		return messageSize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMessageSize(Long newMessageSize) {
		Long oldMessageSize = messageSize;
		messageSize = newMessageSize;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.MESSAGE__MESSAGE_SIZE, oldMessageSize, messageSize));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackageImpl.MESSAGE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ModelPackageImpl.MESSAGE__DESTINATION:
				if (resolve) return getDestination();
				return basicGetDestination();
			case ModelPackageImpl.MESSAGE__NAME:
				return getName();
			case ModelPackageImpl.MESSAGE__AVG_RESPONSE_TIME:
				return getAvgResponseTime();
			case ModelPackageImpl.MESSAGE__TIMESTAMP:
				return getTimestamp();
			case ModelPackageImpl.MESSAGE__UID:
				return getUid();
			case ModelPackageImpl.MESSAGE__MESSAGE_SIZE:
				return getMessageSize();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackageImpl.MESSAGE__SOURCE:
				setSource((ServiceInstance)newValue);
				return;
			case ModelPackageImpl.MESSAGE__DESTINATION:
				setDestination((ServiceInstance)newValue);
				return;
			case ModelPackageImpl.MESSAGE__NAME:
				setName((String)newValue);
				return;
			case ModelPackageImpl.MESSAGE__AVG_RESPONSE_TIME:
				setAvgResponseTime((Long)newValue);
				return;
			case ModelPackageImpl.MESSAGE__TIMESTAMP:
				setTimestamp((Long)newValue);
				return;
			case ModelPackageImpl.MESSAGE__UID:
				setUid((String)newValue);
				return;
			case ModelPackageImpl.MESSAGE__MESSAGE_SIZE:
				setMessageSize((Long)newValue);
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
			case ModelPackageImpl.MESSAGE__SOURCE:
				setSource((ServiceInstance)null);
				return;
			case ModelPackageImpl.MESSAGE__DESTINATION:
				setDestination((ServiceInstance)null);
				return;
			case ModelPackageImpl.MESSAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackageImpl.MESSAGE__AVG_RESPONSE_TIME:
				setAvgResponseTime(AVG_RESPONSE_TIME_EDEFAULT);
				return;
			case ModelPackageImpl.MESSAGE__TIMESTAMP:
				setTimestamp(TIMESTAMP_EDEFAULT);
				return;
			case ModelPackageImpl.MESSAGE__UID:
				setUid(UID_EDEFAULT);
				return;
			case ModelPackageImpl.MESSAGE__MESSAGE_SIZE:
				setMessageSize(MESSAGE_SIZE_EDEFAULT);
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
			case ModelPackageImpl.MESSAGE__SOURCE:
				return source != null;
			case ModelPackageImpl.MESSAGE__DESTINATION:
				return destination != null;
			case ModelPackageImpl.MESSAGE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackageImpl.MESSAGE__AVG_RESPONSE_TIME:
				return AVG_RESPONSE_TIME_EDEFAULT == null ? avgResponseTime != null : !AVG_RESPONSE_TIME_EDEFAULT.equals(avgResponseTime);
			case ModelPackageImpl.MESSAGE__TIMESTAMP:
				return TIMESTAMP_EDEFAULT == null ? timestamp != null : !TIMESTAMP_EDEFAULT.equals(timestamp);
			case ModelPackageImpl.MESSAGE__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
			case ModelPackageImpl.MESSAGE__MESSAGE_SIZE:
				return MESSAGE_SIZE_EDEFAULT == null ? messageSize != null : !MESSAGE_SIZE_EDEFAULT.equals(messageSize);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", avgResponseTime: ");
		result.append(avgResponseTime);
		result.append(", timestamp: ");
		result.append(timestamp);
		result.append(", uid: ");
		result.append(uid);
		result.append(", messageSize: ");
		result.append(messageSize);
		result.append(')');
		return result.toString();
	}

} //MessageImpl
