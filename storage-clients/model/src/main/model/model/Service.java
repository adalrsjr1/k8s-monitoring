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
 *   <li>{@link model.Service#getHasAffinities <em>Has Affinities</em>}</li>
 *   <li>{@link model.Service#getName <em>Name</em>}</li>
 *   <li>{@link model.Service#getApplication <em>Application</em>}</li>
 * </ul>
 *
 * @model abstract="true"
 * @generated
 */
public interface Service extends ElementWithResources {
	/**
	 * Returns the value of the '<em><b>Has Affinities</b></em>' containment reference list.
	 * The list contents are of type {@link model.Affinity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Affinities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Affinities</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	List<Affinity> getHasAffinities();

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

	/**
	 * Returns the value of the '<em><b>Application</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Application</em>' attribute.
	 * @see #setApplication(String)
	 * @model
	 * @generated
	 */
	String getApplication();

	/**
	 * Sets the value of the '{@link model.Service#getApplication <em>Application</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Application</em>' attribute.
	 * @see #getApplication()
	 * @generated
	 */
	void setApplication(String value);

} // Service
