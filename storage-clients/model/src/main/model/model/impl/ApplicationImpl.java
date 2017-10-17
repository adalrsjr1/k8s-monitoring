/**
 */
package model.impl;

import java.util.Map;

import model.Application;
import model.Service;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ApplicationImpl#getServices <em>Services</em>}</li>
 *   <li>{@link model.impl.ApplicationImpl#getName <em>Name</em>}</li>
 *   <li>{@link model.impl.ApplicationImpl#getTotalMessages <em>Total Messages</em>}</li>
 *   <li>{@link model.impl.ApplicationImpl#getTotalData <em>Total Data</em>}</li>
 *   <li>{@link model.impl.ApplicationImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ApplicationImpl extends MinimalEObjectImpl.Container implements Application {
	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Service> services;

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
	 * The default value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final Float WEIGHT_EDEFAULT = new Float(0.0F);

	/**
	 * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected Float weight = WEIGHT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ApplicationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.APPLICATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Service> getServices() {
		if (services == null) {
			services = new EcoreEMap<String,Service>(ModelPackageImpl.Literals.STRING_TO_SERVICE, StringToServiceImpl.class, this, ModelPackageImpl.APPLICATION__SERVICES);
		}
		return services.map();
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.APPLICATION__NAME, oldName, name));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.APPLICATION__TOTAL_MESSAGES, oldTotalMessages, totalMessages));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.APPLICATION__TOTAL_DATA, oldTotalData, totalData));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Float getWeight() {
		return weight;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeight(Float newWeight) {
		Float oldWeight = weight;
		weight = newWeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.APPLICATION__WEIGHT, oldWeight, weight));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackageImpl.APPLICATION__SERVICES:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Service>)getServices()).eMap()).basicRemove(otherEnd, msgs);
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
			case ModelPackageImpl.APPLICATION__SERVICES:
				if (coreType) return ((EMap.InternalMapView<String, Service>)getServices()).eMap();
				else return getServices();
			case ModelPackageImpl.APPLICATION__NAME:
				return getName();
			case ModelPackageImpl.APPLICATION__TOTAL_MESSAGES:
				return getTotalMessages();
			case ModelPackageImpl.APPLICATION__TOTAL_DATA:
				return getTotalData();
			case ModelPackageImpl.APPLICATION__WEIGHT:
				return getWeight();
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
			case ModelPackageImpl.APPLICATION__SERVICES:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Service>)getServices()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.APPLICATION__NAME:
				setName((String)newValue);
				return;
			case ModelPackageImpl.APPLICATION__TOTAL_MESSAGES:
				setTotalMessages((Long)newValue);
				return;
			case ModelPackageImpl.APPLICATION__TOTAL_DATA:
				setTotalData((Long)newValue);
				return;
			case ModelPackageImpl.APPLICATION__WEIGHT:
				setWeight((Float)newValue);
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
			case ModelPackageImpl.APPLICATION__SERVICES:
				getServices().clear();
				return;
			case ModelPackageImpl.APPLICATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackageImpl.APPLICATION__TOTAL_MESSAGES:
				setTotalMessages(TOTAL_MESSAGES_EDEFAULT);
				return;
			case ModelPackageImpl.APPLICATION__TOTAL_DATA:
				setTotalData(TOTAL_DATA_EDEFAULT);
				return;
			case ModelPackageImpl.APPLICATION__WEIGHT:
				setWeight(WEIGHT_EDEFAULT);
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
			case ModelPackageImpl.APPLICATION__SERVICES:
				return services != null && !services.isEmpty();
			case ModelPackageImpl.APPLICATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackageImpl.APPLICATION__TOTAL_MESSAGES:
				return TOTAL_MESSAGES_EDEFAULT == null ? totalMessages != null : !TOTAL_MESSAGES_EDEFAULT.equals(totalMessages);
			case ModelPackageImpl.APPLICATION__TOTAL_DATA:
				return TOTAL_DATA_EDEFAULT == null ? totalData != null : !TOTAL_DATA_EDEFAULT.equals(totalData);
			case ModelPackageImpl.APPLICATION__WEIGHT:
				return WEIGHT_EDEFAULT == null ? weight != null : !WEIGHT_EDEFAULT.equals(weight);
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
		result.append(", totalMessages: ");
		result.append(totalMessages);
		result.append(", totalData: ");
		result.append(totalData);
		result.append(", weight: ");
		result.append(weight);
		result.append(')');
		return result.toString();
	}

} //ApplicationImpl
