/**
 */
package model.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.Host;
import model.ServiceInstance;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Host</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.HostImpl#getName <em>Name</em>}</li>
 *   <li>{@link model.impl.HostImpl#getServices <em>Services</em>}</li>
 *   <li>{@link model.impl.HostImpl#getHostAddress <em>Host Address</em>}</li>
 *   <li>{@link model.impl.HostImpl#getResourceReserved <em>Resource Reserved</em>}</li>
 *   <li>{@link model.impl.HostImpl#getCores <em>Cores</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HostImpl extends ElementWithResourcesImpl implements Host {
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
	 * The cached value of the '{@link #getServices() <em>Services</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, ServiceInstance> services;

	/**
	 * The cached value of the '{@link #getHostAddress() <em>Host Address</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHostAddress()
	 * @generated
	 * @ordered
	 */
	protected EList<String> hostAddress;

	/**
	 * The cached value of the '{@link #getResourceReserved() <em>Resource Reserved</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceReserved()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Double> resourceReserved;

	/**
	 * The default value of the '{@link #getCores() <em>Cores</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCores()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CORES_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCores() <em>Cores</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCores()
	 * @generated
	 * @ordered
	 */
	protected Integer cores = CORES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HostImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.HOST;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.HOST__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, ServiceInstance> getServices() {
		if (services == null) {
			services = new EcoreEMap<String,ServiceInstance>(ModelPackageImpl.Literals.STRING_TO_SERVICE_INSTANCE, StringToServiceInstanceImpl.class, this, ModelPackageImpl.HOST__SERVICES);
		}
		return services.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getHostAddress() {
		if (hostAddress == null) {
			hostAddress = new EDataTypeUniqueEList<String>(String.class, this, ModelPackageImpl.HOST__HOST_ADDRESS);
		}
		return hostAddress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Double> getResourceReserved() {
		if (resourceReserved == null) {
			resourceReserved = new EcoreEMap<String,Double>(ModelPackageImpl.Literals.STRING_TO_DOUBLE_MAP, StringToDoubleMapImpl.class, this, ModelPackageImpl.HOST__RESOURCE_RESERVED);
		}
		return resourceReserved.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getCores() {
		return cores;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCores(Integer newCores) {
		Integer oldCores = cores;
		cores = newCores;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.HOST__CORES, oldCores, cores));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackageImpl.HOST__SERVICES:
				return ((InternalEList<?>)((EMap.InternalMapView<String, ServiceInstance>)getServices()).eMap()).basicRemove(otherEnd, msgs);
			case ModelPackageImpl.HOST__RESOURCE_RESERVED:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Double>)getResourceReserved()).eMap()).basicRemove(otherEnd, msgs);
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
			case ModelPackageImpl.HOST__NAME:
				return getName();
			case ModelPackageImpl.HOST__SERVICES:
				if (coreType) return ((EMap.InternalMapView<String, ServiceInstance>)getServices()).eMap();
				else return getServices();
			case ModelPackageImpl.HOST__HOST_ADDRESS:
				return getHostAddress();
			case ModelPackageImpl.HOST__RESOURCE_RESERVED:
				if (coreType) return ((EMap.InternalMapView<String, Double>)getResourceReserved()).eMap();
				else return getResourceReserved();
			case ModelPackageImpl.HOST__CORES:
				return getCores();
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
			case ModelPackageImpl.HOST__NAME:
				setName((String)newValue);
				return;
			case ModelPackageImpl.HOST__SERVICES:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, ServiceInstance>)getServices()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.HOST__HOST_ADDRESS:
				getHostAddress().clear();
				getHostAddress().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackageImpl.HOST__RESOURCE_RESERVED:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Double>)getResourceReserved()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.HOST__CORES:
				setCores((Integer)newValue);
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
			case ModelPackageImpl.HOST__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackageImpl.HOST__SERVICES:
				getServices().clear();
				return;
			case ModelPackageImpl.HOST__HOST_ADDRESS:
				getHostAddress().clear();
				return;
			case ModelPackageImpl.HOST__RESOURCE_RESERVED:
				getResourceReserved().clear();
				return;
			case ModelPackageImpl.HOST__CORES:
				setCores(CORES_EDEFAULT);
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
			case ModelPackageImpl.HOST__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackageImpl.HOST__SERVICES:
				return services != null && !services.isEmpty();
			case ModelPackageImpl.HOST__HOST_ADDRESS:
				return hostAddress != null && !hostAddress.isEmpty();
			case ModelPackageImpl.HOST__RESOURCE_RESERVED:
				return resourceReserved != null && !resourceReserved.isEmpty();
			case ModelPackageImpl.HOST__CORES:
				return CORES_EDEFAULT == null ? cores != null : !CORES_EDEFAULT.equals(cores);
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
		result.append(", hostAddress: ");
		result.append(hostAddress);
		result.append(", cores: ");
		result.append(cores);
		result.append(')');
		return result.toString();
	}

} //HostImpl
