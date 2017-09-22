/**
 */
package model;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cluster</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.Cluster#getApplications <em>Applications</em>}</li>
 *   <li>{@link model.Cluster#getHosts <em>Hosts</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Cluster extends EObject {
	/**
	 * Returns the value of the '<em><b>Applications</b></em>' containment reference list.
	 * The list contents are of type {@link model.Application}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Applications</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Applications</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	List<Application> getApplications();

	/**
	 * Returns the value of the '<em><b>Hosts</b></em>' containment reference list.
	 * The list contents are of type {@link model.Host}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hosts</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hosts</em>' containment reference list.
	 * @model containment="true"
	 * @generated
	 */
	List<Host> getHosts();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void move(ServiceReplica service, Host destination);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void exchange(ServiceReplica serviceA, ServiceReplica serviceB);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void provision(String name, Map resources);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void remove(String name);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void updateResources(ElementWithResources element, Map resources);

} // Cluster
