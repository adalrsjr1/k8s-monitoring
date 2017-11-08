/**
 */
package model;

import java.util.List;
import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Host</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.Host#getName <em>Name</em>}</li>
 *   <li>{@link model.Host#getServices <em>Services</em>}</li>
 *   <li>{@link model.Host#getHostAddress <em>Host Address</em>}</li>
 *   <li>{@link model.Host#getResourceReserved <em>Resource Reserved</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Host extends ElementWithResources {
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
	 * Sets the value of the '{@link model.Host#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link model.ServiceInstance},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @model mapType="model.StringToServiceInstance&lt;org.eclipse.emf.ecore.EString, model.ServiceInstance&gt;"
	 * @generated
	 */
	Map<String, ServiceInstance> getServices();

	/**
	 * Returns the value of the '<em><b>Host Address</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host Address</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host Address</em>' attribute list.
	 * @model
	 * @generated
	 */
	List<String> getHostAddress();

	/**
	 * Returns the value of the '<em><b>Resource Reserved</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.Double},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Reserved</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Reserved</em>' map.
	 * @model mapType="model.StringToDoubleMap&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EDoubleObject&gt;"
	 * @generated
	 */
	Map<String, Double> getResourceReserved();

} // Host
