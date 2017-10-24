/**
 */
package model;

import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Application</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.Application#getServices <em>Services</em>}</li>
 *   <li>{@link model.Application#getName <em>Name</em>}</li>
 *   <li>{@link model.Application#getTotalMessages <em>Total Messages</em>}</li>
 *   <li>{@link model.Application#getTotalData <em>Total Data</em>}</li>
 *   <li>{@link model.Application#getWeight <em>Weight</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Application {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link model.Service},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Services</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @model mapType="model.StringToService&lt;org.eclipse.emf.ecore.EString, model.Service&gt;"
	 * @generated
	 */
	Map<String, Service> getServices();

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
	 * Sets the value of the '{@link model.Application#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Total Messages</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Messages</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Messages</em>' attribute.
	 * @see #setTotalMessages(Long)
	 * @model default="0"
	 * @generated
	 */
	Long getTotalMessages();

	/**
	 * Sets the value of the '{@link model.Application#getTotalMessages <em>Total Messages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Messages</em>' attribute.
	 * @see #getTotalMessages()
	 * @generated
	 */
	void setTotalMessages(Long value);

	/**
	 * Returns the value of the '<em><b>Total Data</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Total Data</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Total Data</em>' attribute.
	 * @see #setTotalData(Long)
	 * @model default="0"
	 * @generated
	 */
	Long getTotalData();

	/**
	 * Sets the value of the '{@link model.Application#getTotalData <em>Total Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Data</em>' attribute.
	 * @see #getTotalData()
	 * @generated
	 */
	void setTotalData(Long value);

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(Float)
	 * @model default="0.0"
	 * @generated
	 */
	Float getWeight();

	/**
	 * Sets the value of the '{@link model.Application#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(Float value);

} // Application
