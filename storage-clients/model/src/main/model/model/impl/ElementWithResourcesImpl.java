/**
 */
package model.impl;

import java.util.Map;

import model.ElementWithResources;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Element With Resources</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ElementWithResourcesImpl#getResourceLimit <em>Resource Limit</em>}</li>
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
	protected EMap<String, Long> resourceLimit;

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
	public Map<String, Long> getResourceLimit() {
		if (resourceLimit == null) {
			resourceLimit = new EcoreEMap<String,Long>(ModelPackageImpl.Literals.STRING_TO_LONG_MAP, StringToLongMapImpl.class, this, ModelPackageImpl.ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT);
		}
		return resourceLimit.map();
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
				if (coreType) return ((EMap.InternalMapView<String, Long>)getResourceLimit()).eMap();
				else return getResourceLimit();
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
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Long>)getResourceLimit()).eMap()).set(newValue);
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
		}
		return super.eIsSet(featureID);
	}

} //ElementWithResourcesImpl
