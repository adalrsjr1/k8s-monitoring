/**
 */
package model;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.ServiceInstance#getMessages <em>Messages</em>}</li>
 *   <li>{@link model.ServiceInstance#getId <em>Id</em>}</li>
 *   <li>{@link model.ServiceInstance#getAddress <em>Address</em>}</li>
 *   <li>{@link model.ServiceInstance#getContainers <em>Containers</em>}</li>
 *   <li>{@link model.ServiceInstance#getTotalMessages <em>Total Messages</em>}</li>
 *   <li>{@link model.ServiceInstance#getTotalData <em>Total Data</em>}</li>
 *   <li>{@link model.ServiceInstance#getHost <em>Host</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface ServiceInstance extends Service, ElementWithResources {
	/**
	 * Returns the value of the '<em><b>Messages</b></em>' containment reference list.
	 * The list contents are of type {@link model.Message}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Messages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Messages</em>' containment reference list.
	 * @model containment="true" ordered="false"
	 * @generated
	 */
	List<Message> getMessages();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @model id="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link model.ServiceInstance#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @model
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link model.ServiceInstance#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Returns the value of the '<em><b>Containers</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containers</em>' attribute list.
	 * @model
	 * @generated
	 */
	List<String> getContainers();

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
	 * Sets the value of the '{@link model.ServiceInstance#getTotalMessages <em>Total Messages</em>}' attribute.
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
	 * Sets the value of the '{@link model.ServiceInstance#getTotalData <em>Total Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Total Data</em>' attribute.
	 * @see #getTotalData()
	 * @generated
	 */
	void setTotalData(Long value);

	/**
	 * Returns the value of the '<em><b>Host</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host</em>' reference.
	 * @see #setHost(Host)
	 * @model
	 * @generated
	 */
	Host getHost();

	/**
	 * Sets the value of the '{@link model.ServiceInstance#getHost <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' reference.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(Host value);

} // ServiceInstance
