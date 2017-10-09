/**
 */
package model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Affinity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link model.Affinity#getWith <em>With</em>}</li>
 *   <li>{@link model.Affinity#getDegree <em>Degree</em>}</li>
 * </ul>
 *
 * @model
 * @generated
 */
public interface Affinity {
	/**
	 * Returns the value of the '<em><b>With</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>With</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>With</em>' reference.
	 * @see #setWith(Service)
	 * @model
	 * @generated
	 */
	Service getWith();

	/**
	 * Sets the value of the '{@link model.Affinity#getWith <em>With</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>With</em>' reference.
	 * @see #getWith()
	 * @generated
	 */
	void setWith(Service value);

	/**
	 * Returns the value of the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Degree</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Degree</em>' attribute.
	 * @see #setDegree(Float)
	 * @model
	 * @generated
	 */
	Float getDegree();

	/**
	 * Sets the value of the '{@link model.Affinity#getDegree <em>Degree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Degree</em>' attribute.
	 * @see #getDegree()
	 * @generated
	 */
	void setDegree(Float value);

} // Affinity
