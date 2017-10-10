/**
 */
package model;

import java.util.List;
import java.util.Map;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cluster</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.Cluster#getHosts <em>Hosts</em>}</li>
 *   <li>{@link model.Cluster#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link model.Cluster#getApplications <em>Applications</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Cluster {
	/**
	 * Returns the value of the '<em><b>Hosts</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link model.Host},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hosts</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hosts</em>' map.
	 * @model mapType="model.StringToHost&lt;org.eclipse.emf.ecore.EString, model.Host&gt;"
	 * @generated
	 */
	Map<String, Host> getHosts();

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' attribute.
	 * The literals are from the enumeration {@link model.Environment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' attribute.
	 * @see model.Environment
	 * @see #setEnvironment(Environment)
	 * @model
	 * @generated
	 */
	Environment getEnvironment();

	/**
	 * Sets the value of the '{@link model.Cluster#getEnvironment <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' attribute.
	 * @see model.Environment
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(Environment value);

	/**
	 * Returns the value of the '<em><b>Applications</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type list of {@link model.Application},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applications</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applications</em>' map.
	 * @model mapType="model.StringToApplication&lt;org.eclipse.emf.ecore.EString, model.Application&gt;"
	 * @generated
	 */
	Map<String, List<Application>> getApplications();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void move(String application, String serviceId, String sourceHost, String destinationHost);

} // Cluster
