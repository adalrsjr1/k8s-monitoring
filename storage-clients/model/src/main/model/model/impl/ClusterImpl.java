/**
 */
package model.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import model.Application;
import model.Cluster;
import model.Environment;
import model.Host;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cluster</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link model.impl.ClusterImpl#getHosts <em>Hosts</em>}</li>
 *   <li>{@link model.impl.ClusterImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link model.impl.ClusterImpl#getApplications <em>Applications</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ClusterImpl extends MinimalEObjectImpl.Container implements Cluster {
	/**
	 * The cached value of the '{@link #getHosts() <em>Hosts</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHosts()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Host> hosts;

	/**
	 * The default value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected static final Environment ENVIRONMENT_EDEFAULT = Environment.KUBERNETES;

	/**
	 * The cached value of the '{@link #getEnvironment() <em>Environment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvironment()
	 * @generated
	 * @ordered
	 */
	protected Environment environment = ENVIRONMENT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getApplications() <em>Applications</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplications()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Application> applications;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClusterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackageImpl.Literals.CLUSTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Host> getHosts() {
		if (hosts == null) {
			hosts = new EcoreEMap<String,Host>(ModelPackageImpl.Literals.STRING_TO_HOST, StringToHostImpl.class, this, ModelPackageImpl.CLUSTER__HOSTS);
		}
		return hosts.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvironment(Environment newEnvironment) {
		Environment oldEnvironment = environment;
		environment = newEnvironment == null ? ENVIRONMENT_EDEFAULT : newEnvironment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackageImpl.CLUSTER__ENVIRONMENT, oldEnvironment, environment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map<String, Application> getApplications() {
		if (applications == null) {
			applications = new EcoreEMap<String,Application>(ModelPackageImpl.Literals.STRING_TO_APPLICATION, StringToApplicationImpl.class, this, ModelPackageImpl.CLUSTER__APPLICATIONS);
		}
		return applications.map();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void move(String application, String serviceId, String sourceHost, String destinationHost) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackageImpl.CLUSTER__HOSTS:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Host>)getHosts()).eMap()).basicRemove(otherEnd, msgs);
			case ModelPackageImpl.CLUSTER__APPLICATIONS:
				return ((InternalEList<?>)((EMap.InternalMapView<String, Application>)getApplications()).eMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackageImpl.CLUSTER__HOSTS:
				if (coreType) return ((EMap.InternalMapView<String, Host>)getHosts()).eMap();
				else return getHosts();
			case ModelPackageImpl.CLUSTER__ENVIRONMENT:
				return getEnvironment();
			case ModelPackageImpl.CLUSTER__APPLICATIONS:
				if (coreType) return ((EMap.InternalMapView<String, Application>)getApplications()).eMap();
				else return getApplications();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackageImpl.CLUSTER__HOSTS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Host>)getHosts()).eMap()).set(newValue);
				return;
			case ModelPackageImpl.CLUSTER__ENVIRONMENT:
				setEnvironment((Environment)newValue);
				return;
			case ModelPackageImpl.CLUSTER__APPLICATIONS:
				((EStructuralFeature.Setting)((EMap.InternalMapView<String, Application>)getApplications()).eMap()).set(newValue);
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
			case ModelPackageImpl.CLUSTER__HOSTS:
				getHosts().clear();
				return;
			case ModelPackageImpl.CLUSTER__ENVIRONMENT:
				setEnvironment(ENVIRONMENT_EDEFAULT);
				return;
			case ModelPackageImpl.CLUSTER__APPLICATIONS:
				getApplications().clear();
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
			case ModelPackageImpl.CLUSTER__HOSTS:
				return hosts != null && !hosts.isEmpty();
			case ModelPackageImpl.CLUSTER__ENVIRONMENT:
				return environment != ENVIRONMENT_EDEFAULT;
			case ModelPackageImpl.CLUSTER__APPLICATIONS:
				return applications != null && !applications.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ModelPackageImpl.CLUSTER___MOVE__STRING_STRING_STRING_STRING:
				move((String)arguments.get(0), (String)arguments.get(1), (String)arguments.get(2), (String)arguments.get(3));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (environment: ");
		result.append(environment);
		result.append(')');
		return result.toString();
	}

} //ClusterImpl
