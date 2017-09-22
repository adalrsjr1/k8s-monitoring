/**
 */
package model;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element With Resources</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.ElementWithResources#getResourceLimit <em>Resource Limit</em>}</li>
 * </ul>
 *
 * @model abstract="true"
 * @generated
 */
public interface ElementWithResources extends EObject {
	/**
	 * Returns the value of the '<em><b>Resource Limit</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.Long},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Limit</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Limit</em>' map.
	 * @model mapType="model.StringToLongMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.ELongObject&gt;"
	 * @generated
	 */
	Map<String, Long> getResourceLimit();

} // ElementWithResources
