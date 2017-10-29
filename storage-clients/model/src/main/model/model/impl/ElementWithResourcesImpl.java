/**
 */
package model.impl;

import java.util.Map;

import model.ElementWithResources;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element With Resources</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ElementWithResourcesImpl#getResourceLimit <em>Resource Limit</em>}</li>
 *   <li>{@link model.impl.ElementWithResourcesImpl#getMetrics <em>Metrics</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ElementWithResourcesImpl extends MinimalEObjectImpl.Container implements ElementWithResources {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ElementWithResourcesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.ELEMENT_WITH_RESOURCES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Double> getResourceLimit() {
		if (resourceLimit == null) {
			resourceLimit = new EcoreEMap<String,Double>(ModelPackageImpl.Literals.STRING_TO_DOUBLE_MAP, StringToDoubleMapImpl.class, this, ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT);
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
			metrics = new EcoreEMap<String,Double>(ModelPackageImpl.Literals.STRING_TO_DOUBLE_MAP, StringToDoubleMapImpl.class, this, ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS);
		}
		return metrics.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Double>)getResourceLimit()).eMap()).basicRemove(otherEnd, msgs);
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Double>)getMetrics()).eMap()).basicRemove(otherEnd, msgs);
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
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT:
				if (coreType) return ((EMap.InternalMapView<String, Double>)getResourceLimit()).eMap();
				else return getResourceLimit();
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS:
				if (coreType) return ((EMap.InternalMapView<String, Double>)getMetrics()).eMap();
				else return getMetrics();
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
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Double>)getResourceLimit()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Double>)getMetrics()).eMap()).set(newValue);
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
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT:
				getResourceLimit().clear();
				return;
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS:
				getMetrics().clear();
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
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT:
				return resourceLimit != null && !resourceLimit.isEmpty();
			case ModelPackageImpl.ELEMENT_WITH_RESOURCES__METRICS:
				return metrics != null && !metrics.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ElementWithResourcesImpl
