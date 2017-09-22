/**
 */
package model;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.Service#getAffinities <em>Affinities</em>}</li>
 *   <li>{@link model.Service#getName <em>Name</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Service extends ElementWithResources {
	/**
	 * Returns the value of the '<em><b>Affinities</b></em>' containment reference list.
	 * The list contents are of type {@link model.Affinity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Affinities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Affinities</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	List<Affinity> getAffinities();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link model.Service#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Service
