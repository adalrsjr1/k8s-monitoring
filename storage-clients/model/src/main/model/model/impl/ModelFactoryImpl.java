/**
 */
package model.impl;

import java.util.Map;

import model.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ModelFactoryImpl eINSTANCE = init();

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactoryImpl init() {
		try {
			ModelFactoryImpl theModelFactory = (ModelFactoryImpl)EPackage.Registry.INSTANCE.getEFactory(ModelPackageImpl.eNS_URI);
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackageImpl.CLUSTER: return (EObject)createCluster();
			case ModelPackageImpl.AFFINITY: return (EObject)createAffinity();
			case ModelPackageImpl.SERVICE_INSTANCE: return (EObject)createServiceInstance();
			case ModelPackageImpl.MESSAGE: return (EObject)createMessage();
			case ModelPackageImpl.HOST: return (EObject)createHost();
			case ModelPackageImpl.STRING_TO_LONG_MAP: return (EObject)createStringToLongMap();
			case ModelPackageImpl.STRING_TO_SERVICE: return (EObject)createStringToService();
			case ModelPackageImpl.STRING_TO_HOST: return (EObject)createStringToHost();
			case ModelPackageImpl.STRING_TO_SERVICE_INSTANCE: return (EObject)createStringToServiceInstance();
			case ModelPackageImpl.APPLICATION: return (EObject)createApplication();
			case ModelPackageImpl.STRING_TO_APPLICATION: return (EObject)createStringToApplication();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackageImpl.ENVIRONMENT:
				return createEnvironmentFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackageImpl.ENVIRONMENT:
				return convertEnvironmentToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cluster createCluster() {
		ClusterImpl cluster = new ClusterImpl();
		return cluster;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Affinity createAffinity() {
		AffinityImpl affinity = new AffinityImpl();
		return affinity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceInstance createServiceInstance() {
		ServiceInstanceImpl serviceInstance = new ServiceInstanceImpl();
		return serviceInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Message createMessage() {
		MessageImpl message = new MessageImpl();
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Host createHost() {
		HostImpl host = new HostImpl();
		return host;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Long> createStringToLongMap() {
		StringToLongMapImpl stringToLongMap = new StringToLongMapImpl();
		return stringToLongMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Service> createStringToService() {
		StringToServiceImpl stringToService = new StringToServiceImpl();
		return stringToService;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Host> createStringToHost() {
		StringToHostImpl stringToHost = new StringToHostImpl();
		return stringToHost;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, ServiceInstance> createStringToServiceInstance() {
		StringToServiceInstanceImpl stringToServiceInstance = new StringToServiceInstanceImpl();
		return stringToServiceInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Application createApplication() {
		ApplicationImpl application = new ApplicationImpl();
		return application;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Application> createStringToApplication() {
		StringToApplicationImpl stringToApplication = new StringToApplicationImpl();
		return stringToApplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Environment createEnvironmentFromString(EDataType eDataType, String initialValue) {
		Environment result = Environment.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEnvironmentToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackageImpl getModelPackage() {
		return (ModelPackageImpl)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackageImpl getPackage() {
		return ModelPackageImpl.eINSTANCE;
	}

} //ModelFactoryImpl
