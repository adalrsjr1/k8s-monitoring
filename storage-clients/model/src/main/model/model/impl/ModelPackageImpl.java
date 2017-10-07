/**
 */
package model.impl;

import java.util.Map;

import model.Affinity;
import model.Application;
import model.Cluster;
import model.ElementWithResources;
import model.Environment;
import model.Host;
import model.Message;
import model.ModelFactory;
import model.Service;
import model.ServiceInstance;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see model.ModelFactory
 * @model kind="package"
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://gfads.cin.ufpe.br/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ModelPackageImpl eINSTANCE = model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link model.impl.ClusterImpl <em>Cluster</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ClusterImpl
	 * @see model.impl.ModelPackageImpl#getCluster()
	 * @generated
	 */
	public static final int CLUSTER = 0;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CLUSTER__APPLICATIONS = 0;

	/**
	 * The feature id for the '<em><b>Hosts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CLUSTER__HOSTS = 1;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CLUSTER__ENVIRONMENT = 2;

	/**
	 * The number of structural features of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CLUSTER_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Move</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CLUSTER___MOVE__STRING_STRING_STRING_STRING = 0;

	/**
	 * The number of operations of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CLUSTER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link model.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ApplicationImpl
	 * @see model.impl.ModelPackageImpl#getApplication()
	 * @generated
	 */
	public static final int APPLICATION = 1;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION__NAME = 1;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.ElementWithResourcesImpl <em>Element With Resources</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ElementWithResourcesImpl
	 * @see model.impl.ModelPackageImpl#getElementWithResources()
	 * @generated
	 */
	public static final int ELEMENT_WITH_RESOURCES = 8;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT = 0;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_WITH_RESOURCES__METRICS = 1;

	/**
	 * The number of structural features of the '<em>Element With Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_WITH_RESOURCES_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Element With Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ELEMENT_WITH_RESOURCES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ServiceImpl
	 * @see model.impl.ModelPackageImpl#getService()
	 * @generated
	 */
	public static final int SERVICE = 2;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE__RESOURCE_LIMIT = ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE__METRICS = ELEMENT_WITH_RESOURCES__METRICS;

	/**
	 * The feature id for the '<em><b>Has Affinities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE__HAS_AFFINITIES = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE__NAME = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE__PORT = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_FEATURE_COUNT = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_OPERATION_COUNT = ELEMENT_WITH_RESOURCES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.AffinityImpl <em>Affinity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.AffinityImpl
	 * @see model.impl.ModelPackageImpl#getAffinity()
	 * @generated
	 */
	public static final int AFFINITY = 3;

	/**
	 * The feature id for the '<em><b>With</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AFFINITY__WITH = 0;

	/**
	 * The feature id for the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AFFINITY__DEGREE = 1;

	/**
	 * The number of structural features of the '<em>Affinity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AFFINITY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Affinity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int AFFINITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.ServiceInstanceImpl <em>Service Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ServiceInstanceImpl
	 * @see model.impl.ModelPackageImpl#getServiceInstance()
	 * @generated
	 */
	public static final int SERVICE_INSTANCE = 4;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__RESOURCE_LIMIT = SERVICE__RESOURCE_LIMIT;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__METRICS = SERVICE__METRICS;

	/**
	 * The feature id for the '<em><b>Has Affinities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__HAS_AFFINITIES = SERVICE__HAS_AFFINITIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__PORT = SERVICE__PORT;

	/**
	 * The feature id for the '<em><b>Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__MESSAGES = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__CONTAINER = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__ID = SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE__ADDRESS = SERVICE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Service Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Service Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_INSTANCE_OPERATION_COUNT = SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.MessageImpl <em>Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.MessageImpl
	 * @see model.impl.ModelPackageImpl#getMessage()
	 * @generated
	 */
	public static final int MESSAGE = 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__DESTINATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Avg Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__AVG_RESPONSE_TIME = 3;

	/**
	 * The feature id for the '<em><b>Avg Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__AVG_SIZE = 4;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__TIMESTAMP = 5;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE__UID = 6;

	/**
	 * The number of structural features of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.HostImpl <em>Host</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.HostImpl
	 * @see model.impl.ModelPackageImpl#getHost()
	 * @generated
	 */
	public static final int HOST = 6;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HOST__RESOURCE_LIMIT = ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HOST__METRICS = ELEMENT_WITH_RESOURCES__METRICS;

	/**
	 * The feature id for the '<em><b>Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HOST__SERVICES = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HOST__NAME = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Host</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HOST_FEATURE_COUNT = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Host</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int HOST_OPERATION_COUNT = ELEMENT_WITH_RESOURCES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.StringToLongMapImpl <em>String To Long Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.StringToLongMapImpl
	 * @see model.impl.ModelPackageImpl#getStringToLongMap()
	 * @generated
	 */
	public static final int STRING_TO_LONG_MAP = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_TO_LONG_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_TO_LONG_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Long Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_TO_LONG_MAP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Long Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_TO_LONG_MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.Environment <em>Environment</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.Environment
	 * @see model.impl.ModelPackageImpl#getEnvironment()
	 * @generated
	 */
	public static final int ENVIRONMENT = 9;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass clusterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass applicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass affinityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass serviceInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass messageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hostEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringToLongMapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementWithResourcesEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum environmentEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see model.impl.ModelPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ((EFactory)ModelFactory.INSTANCE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModelPackageImpl#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelPackageImpl init() {
		if (isInited) return (ModelPackageImpl)EPackage.Registry.INSTANCE.getEPackage(ModelPackageImpl.eNS_URI);

		// Obtain or create and register package
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theModelPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackageImpl.eNS_URI, theModelPackage);
		return theModelPackage;
	}


	/**
	 * Returns the meta object for class '{@link model.Cluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cluster</em>'.
	 * @see model.Cluster
	 * @generated
	 */
	public EClass getCluster() {
		return clusterEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link model.Cluster#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Applications</em>'.
	 * @see model.Cluster#getApplications()
	 * @see #getCluster()
	 * @generated
	 */
	public EReference getCluster_Applications() {
		return (EReference)clusterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link model.Cluster#getHosts <em>Hosts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hosts</em>'.
	 * @see model.Cluster#getHosts()
	 * @see #getCluster()
	 * @generated
	 */
	public EReference getCluster_Hosts() {
		return (EReference)clusterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Cluster#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment</em>'.
	 * @see model.Cluster#getEnvironment()
	 * @see #getCluster()
	 * @generated
	 */
	public EAttribute getCluster_Environment() {
		return (EAttribute)clusterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the '{@link model.Cluster#move(java.lang.String, java.lang.String, java.lang.String, java.lang.String) <em>Move</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Move</em>' operation.
	 * @see model.Cluster#move(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * @generated
	 */
	public EOperation getCluster__Move__String_String_String_String() {
		return clusterEClass.getEOperations().get(0);
	}


	/**
	 * Returns the meta object for class '{@link model.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see model.Application
	 * @generated
	 */
	public EClass getApplication() {
		return applicationEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link model.Application#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see model.Application#getServices()
	 * @see #getApplication()
	 * @generated
	 */
	public EReference getApplication_Services() {
		return (EReference)applicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Application#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Application#getName()
	 * @see #getApplication()
	 * @generated
	 */
	public EAttribute getApplication_Name() {
		return (EAttribute)applicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link model.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see model.Service
	 * @generated
	 */
	public EClass getService() {
		return serviceEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link model.Service#getHasAffinities <em>Has Affinities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Has Affinities</em>'.
	 * @see model.Service#getHasAffinities()
	 * @see #getService()
	 * @generated
	 */
	public EReference getService_HasAffinities() {
		return (EReference)serviceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Service#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Service#getName()
	 * @see #getService()
	 * @generated
	 */
	public EAttribute getService_Name() {
		return (EAttribute)serviceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Service#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see model.Service#getPort()
	 * @see #getService()
	 * @generated
	 */
	public EAttribute getService_Port() {
		return (EAttribute)serviceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for class '{@link model.Affinity <em>Affinity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Affinity</em>'.
	 * @see model.Affinity
	 * @generated
	 */
	public EClass getAffinity() {
		return affinityEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link model.Affinity#getWith <em>With</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>With</em>'.
	 * @see model.Affinity#getWith()
	 * @see #getAffinity()
	 * @generated
	 */
	public EReference getAffinity_With() {
		return (EReference)affinityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Affinity#getDegree <em>Degree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Degree</em>'.
	 * @see model.Affinity#getDegree()
	 * @see #getAffinity()
	 * @generated
	 */
	public EAttribute getAffinity_Degree() {
		return (EAttribute)affinityEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link model.ServiceInstance <em>Service Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Instance</em>'.
	 * @see model.ServiceInstance
	 * @generated
	 */
	public EClass getServiceInstance() {
		return serviceInstanceEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link model.ServiceInstance#getMessages <em>Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Messages</em>'.
	 * @see model.ServiceInstance#getMessages()
	 * @see #getServiceInstance()
	 * @generated
	 */
	public EReference getServiceInstance_Messages() {
		return (EReference)serviceInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.ServiceInstance#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container</em>'.
	 * @see model.ServiceInstance#getContainer()
	 * @see #getServiceInstance()
	 * @generated
	 */
	public EAttribute getServiceInstance_Container() {
		return (EAttribute)serviceInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.ServiceInstance#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see model.ServiceInstance#getId()
	 * @see #getServiceInstance()
	 * @generated
	 */
	public EAttribute getServiceInstance_Id() {
		return (EAttribute)serviceInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.ServiceInstance#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see model.ServiceInstance#getAddress()
	 * @see #getServiceInstance()
	 * @generated
	 */
	public EAttribute getServiceInstance_Address() {
		return (EAttribute)serviceInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for class '{@link model.Message <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message</em>'.
	 * @see model.Message
	 * @generated
	 */
	public EClass getMessage() {
		return messageEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link model.Message#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see model.Message#getSource()
	 * @see #getMessage()
	 * @generated
	 */
	public EReference getMessage_Source() {
		return (EReference)messageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference '{@link model.Message#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see model.Message#getDestination()
	 * @see #getMessage()
	 * @generated
	 */
	public EReference getMessage_Destination() {
		return (EReference)messageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Message#getName()
	 * @see #getMessage()
	 * @generated
	 */
	public EAttribute getMessage_Name() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getAvgResponseTime <em>Avg Response Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avg Response Time</em>'.
	 * @see model.Message#getAvgResponseTime()
	 * @see #getMessage()
	 * @generated
	 */
	public EAttribute getMessage_AvgResponseTime() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getAvgSize <em>Avg Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avg Size</em>'.
	 * @see model.Message#getAvgSize()
	 * @see #getMessage()
	 * @generated
	 */
	public EAttribute getMessage_AvgSize() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see model.Message#getTimestamp()
	 * @see #getMessage()
	 * @generated
	 */
	public EAttribute getMessage_Timestamp() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see model.Message#getUid()
	 * @see #getMessage()
	 * @generated
	 */
	public EAttribute getMessage_Uid() {
		return (EAttribute)messageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * Returns the meta object for class '{@link model.Host <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Host</em>'.
	 * @see model.Host
	 * @generated
	 */
	public EClass getHost() {
		return hostEClass;
	}

	/**
	 * Returns the meta object for the reference list '{@link model.Host#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Services</em>'.
	 * @see model.Host#getServices()
	 * @see #getHost()
	 * @generated
	 */
	public EReference getHost_Services() {
		return (EReference)hostEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link model.Host#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Host#getName()
	 * @see #getHost()
	 * @generated
	 */
	public EAttribute getHost_Name() {
		return (EAttribute)hostEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String To Long Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String To Long Map</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueDataType="org.eclipse.emf.ecore.ELongObject"
	 * @generated
	 */
	public EClass getStringToLongMap() {
		return stringToLongMapEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToLongMap()
	 * @generated
	 */
	public EAttribute getStringToLongMap_Key() {
		return (EAttribute)stringToLongMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToLongMap()
	 * @generated
	 */
	public EAttribute getStringToLongMap_Value() {
		return (EAttribute)stringToLongMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link model.ElementWithResources <em>Element With Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element With Resources</em>'.
	 * @see model.ElementWithResources
	 * @generated
	 */
	public EClass getElementWithResources() {
		return elementWithResourcesEClass;
	}

	/**
	 * Returns the meta object for the map '{@link model.ElementWithResources#getResourceLimit <em>Resource Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Resource Limit</em>'.
	 * @see model.ElementWithResources#getResourceLimit()
	 * @see #getElementWithResources()
	 * @generated
	 */
	public EReference getElementWithResources_ResourceLimit() {
		return (EReference)elementWithResourcesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the map '{@link model.ElementWithResources#getMetrics <em>Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Metrics</em>'.
	 * @see model.ElementWithResources#getMetrics()
	 * @see #getElementWithResources()
	 * @generated
	 */
	public EReference getElementWithResources_Metrics() {
		return (EReference)elementWithResourcesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for enum '{@link model.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Environment</em>'.
	 * @see model.Environment
	 * @generated
	 */
	public EEnum getEnvironment() {
		return environmentEEnum;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		clusterEClass = createEClass(CLUSTER);
		createEReference(clusterEClass, CLUSTER__APPLICATIONS);
		createEReference(clusterEClass, CLUSTER__HOSTS);
		createEAttribute(clusterEClass, CLUSTER__ENVIRONMENT);
		createEOperation(clusterEClass, CLUSTER___MOVE__STRING_STRING_STRING_STRING);

		applicationEClass = createEClass(APPLICATION);
		createEReference(applicationEClass, APPLICATION__SERVICES);
		createEAttribute(applicationEClass, APPLICATION__NAME);

		serviceEClass = createEClass(SERVICE);
		createEReference(serviceEClass, SERVICE__HAS_AFFINITIES);
		createEAttribute(serviceEClass, SERVICE__NAME);
		createEAttribute(serviceEClass, SERVICE__PORT);

		affinityEClass = createEClass(AFFINITY);
		createEReference(affinityEClass, AFFINITY__WITH);
		createEAttribute(affinityEClass, AFFINITY__DEGREE);

		serviceInstanceEClass = createEClass(SERVICE_INSTANCE);
		createEReference(serviceInstanceEClass, SERVICE_INSTANCE__MESSAGES);
		createEAttribute(serviceInstanceEClass, SERVICE_INSTANCE__CONTAINER);
		createEAttribute(serviceInstanceEClass, SERVICE_INSTANCE__ID);
		createEAttribute(serviceInstanceEClass, SERVICE_INSTANCE__ADDRESS);

		messageEClass = createEClass(MESSAGE);
		createEReference(messageEClass, MESSAGE__SOURCE);
		createEReference(messageEClass, MESSAGE__DESTINATION);
		createEAttribute(messageEClass, MESSAGE__NAME);
		createEAttribute(messageEClass, MESSAGE__AVG_RESPONSE_TIME);
		createEAttribute(messageEClass, MESSAGE__AVG_SIZE);
		createEAttribute(messageEClass, MESSAGE__TIMESTAMP);
		createEAttribute(messageEClass, MESSAGE__UID);

		hostEClass = createEClass(HOST);
		createEReference(hostEClass, HOST__SERVICES);
		createEAttribute(hostEClass, HOST__NAME);

		stringToLongMapEClass = createEClass(STRING_TO_LONG_MAP);
		createEAttribute(stringToLongMapEClass, STRING_TO_LONG_MAP__KEY);
		createEAttribute(stringToLongMapEClass, STRING_TO_LONG_MAP__VALUE);

		elementWithResourcesEClass = createEClass(ELEMENT_WITH_RESOURCES);
		createEReference(elementWithResourcesEClass, ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT);
		createEReference(elementWithResourcesEClass, ELEMENT_WITH_RESOURCES__METRICS);

		// Create enums
		environmentEEnum = createEEnum(ENVIRONMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		serviceEClass.getESuperTypes().add(this.getElementWithResources());
		serviceInstanceEClass.getESuperTypes().add(this.getService());
		hostEClass.getESuperTypes().add(this.getElementWithResources());

		// Initialize classes, features, and operations; add parameters
		initEClass(clusterEClass, Cluster.class, "Cluster", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCluster_Applications(), this.getApplication(), null, "applications", null, 0, -1, Cluster.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCluster_Hosts(), this.getHost(), null, "hosts", null, 0, -1, Cluster.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCluster_Environment(), this.getEnvironment(), "environment", null, 0, 1, Cluster.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getCluster__Move__String_String_String_String(), null, "move", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "application", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "serviceId", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "sourceHost", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "destinationHost", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(applicationEClass, Application.class, "Application", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getApplication_Services(), this.getService(), null, "services", null, 0, -1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getApplication_Name(), ecorePackage.getEString(), "name", null, 0, 1, Application.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceEClass, Service.class, "Service", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getService_HasAffinities(), this.getAffinity(), null, "hasAffinities", null, 0, -1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getService_Name(), ecorePackage.getEString(), "name", null, 0, 1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getService_Port(), ecorePackage.getEIntegerObject(), "port", null, 0, 1, Service.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(affinityEClass, Affinity.class, "Affinity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAffinity_With(), this.getService(), null, "with", null, 0, 1, Affinity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAffinity_Degree(), ecorePackage.getEFloatObject(), "degree", null, 0, 1, Affinity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(serviceInstanceEClass, ServiceInstance.class, "ServiceInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getServiceInstance_Messages(), this.getMessage(), null, "messages", null, 0, -1, ServiceInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceInstance_Container(), ecorePackage.getEString(), "container", null, 0, 1, ServiceInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceInstance_Id(), ecorePackage.getEString(), "id", null, 0, 1, ServiceInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getServiceInstance_Address(), ecorePackage.getEString(), "address", null, 0, 1, ServiceInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(messageEClass, Message.class, "Message", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMessage_Source(), this.getServiceInstance(), null, "source", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMessage_Destination(), this.getServiceInstance(), null, "destination", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_Name(), ecorePackage.getEString(), "name", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_AvgResponseTime(), ecorePackage.getELongObject(), "avgResponseTime", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_AvgSize(), ecorePackage.getELongObject(), "avgSize", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_Timestamp(), ecorePackage.getELongObject(), "timestamp", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMessage_Uid(), ecorePackage.getEString(), "uid", null, 0, 1, Message.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hostEClass, Host.class, "Host", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHost_Services(), this.getServiceInstance(), null, "services", null, 0, -1, Host.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHost_Name(), ecorePackage.getEString(), "name", null, 0, 1, Host.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringToLongMapEClass, Map.Entry.class, "StringToLongMap", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringToLongMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringToLongMap_Value(), ecorePackage.getELongObject(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementWithResourcesEClass, ElementWithResources.class, "ElementWithResources", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementWithResources_ResourceLimit(), this.getStringToLongMap(), null, "resourceLimit", null, 0, -1, ElementWithResources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementWithResources_Metrics(), this.getStringToLongMap(), null, "metrics", null, 0, -1, ElementWithResources.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(environmentEEnum, Environment.class, "Environment");
		addEEnumLiteral(environmentEEnum, Environment.KUBERNETES);
		addEEnumLiteral(environmentEEnum, Environment.DOCKER_SWARM);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.obeo.fr/dsl/dnc/archetype
		createArchetypeAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.obeo.fr/dsl/dnc/archetype</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createArchetypeAnnotations() {
		String source = "http://www.obeo.fr/dsl/dnc/archetype";	
		addAnnotation
		  (applicationEClass, 
		   source, 
		   new String[] {
			 "archetype", "Role"
		   });
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {
		/**
		 * The meta object literal for the '{@link model.impl.ClusterImpl <em>Cluster</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ClusterImpl
		 * @see model.impl.ModelPackageImpl#getCluster()
		 * @generated
		 */
		public static final EClass CLUSTER = eINSTANCE.getCluster();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CLUSTER__APPLICATIONS = eINSTANCE.getCluster_Applications();

		/**
		 * The meta object literal for the '<em><b>Hosts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CLUSTER__HOSTS = eINSTANCE.getCluster_Hosts();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute CLUSTER__ENVIRONMENT = eINSTANCE.getCluster_Environment();

		/**
		 * The meta object literal for the '<em><b>Move</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EOperation CLUSTER___MOVE__STRING_STRING_STRING_STRING = eINSTANCE.getCluster__Move__String_String_String_String();

		/**
		 * The meta object literal for the '{@link model.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ApplicationImpl
		 * @see model.impl.ModelPackageImpl#getApplication()
		 * @generated
		 */
		public static final EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference APPLICATION__SERVICES = eINSTANCE.getApplication_Services();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute APPLICATION__NAME = eINSTANCE.getApplication_Name();

		/**
		 * The meta object literal for the '{@link model.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ServiceImpl
		 * @see model.impl.ModelPackageImpl#getService()
		 * @generated
		 */
		public static final EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Has Affinities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SERVICE__HAS_AFFINITIES = eINSTANCE.getService_HasAffinities();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute SERVICE__NAME = eINSTANCE.getService_Name();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute SERVICE__PORT = eINSTANCE.getService_Port();

		/**
		 * The meta object literal for the '{@link model.impl.AffinityImpl <em>Affinity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.AffinityImpl
		 * @see model.impl.ModelPackageImpl#getAffinity()
		 * @generated
		 */
		public static final EClass AFFINITY = eINSTANCE.getAffinity();

		/**
		 * The meta object literal for the '<em><b>With</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference AFFINITY__WITH = eINSTANCE.getAffinity_With();

		/**
		 * The meta object literal for the '<em><b>Degree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute AFFINITY__DEGREE = eINSTANCE.getAffinity_Degree();

		/**
		 * The meta object literal for the '{@link model.impl.ServiceInstanceImpl <em>Service Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ServiceInstanceImpl
		 * @see model.impl.ModelPackageImpl#getServiceInstance()
		 * @generated
		 */
		public static final EClass SERVICE_INSTANCE = eINSTANCE.getServiceInstance();

		/**
		 * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference SERVICE_INSTANCE__MESSAGES = eINSTANCE.getServiceInstance_Messages();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute SERVICE_INSTANCE__CONTAINER = eINSTANCE.getServiceInstance_Container();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute SERVICE_INSTANCE__ID = eINSTANCE.getServiceInstance_Id();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute SERVICE_INSTANCE__ADDRESS = eINSTANCE.getServiceInstance_Address();

		/**
		 * The meta object literal for the '{@link model.impl.MessageImpl <em>Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.MessageImpl
		 * @see model.impl.ModelPackageImpl#getMessage()
		 * @generated
		 */
		public static final EClass MESSAGE = eINSTANCE.getMessage();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MESSAGE__SOURCE = eINSTANCE.getMessage_Source();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference MESSAGE__DESTINATION = eINSTANCE.getMessage_Destination();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute MESSAGE__NAME = eINSTANCE.getMessage_Name();

		/**
		 * The meta object literal for the '<em><b>Avg Response Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute MESSAGE__AVG_RESPONSE_TIME = eINSTANCE.getMessage_AvgResponseTime();

		/**
		 * The meta object literal for the '<em><b>Avg Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute MESSAGE__AVG_SIZE = eINSTANCE.getMessage_AvgSize();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute MESSAGE__TIMESTAMP = eINSTANCE.getMessage_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute MESSAGE__UID = eINSTANCE.getMessage_Uid();

		/**
		 * The meta object literal for the '{@link model.impl.HostImpl <em>Host</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.HostImpl
		 * @see model.impl.ModelPackageImpl#getHost()
		 * @generated
		 */
		public static final EClass HOST = eINSTANCE.getHost();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference HOST__SERVICES = eINSTANCE.getHost_Services();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute HOST__NAME = eINSTANCE.getHost_Name();

		/**
		 * The meta object literal for the '{@link model.impl.StringToLongMapImpl <em>String To Long Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.StringToLongMapImpl
		 * @see model.impl.ModelPackageImpl#getStringToLongMap()
		 * @generated
		 */
		public static final EClass STRING_TO_LONG_MAP = eINSTANCE.getStringToLongMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_TO_LONG_MAP__KEY = eINSTANCE.getStringToLongMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_TO_LONG_MAP__VALUE = eINSTANCE.getStringToLongMap_Value();

		/**
		 * The meta object literal for the '{@link model.impl.ElementWithResourcesImpl <em>Element With Resources</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ElementWithResourcesImpl
		 * @see model.impl.ModelPackageImpl#getElementWithResources()
		 * @generated
		 */
		public static final EClass ELEMENT_WITH_RESOURCES = eINSTANCE.getElementWithResources();

		/**
		 * The meta object literal for the '<em><b>Resource Limit</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT = eINSTANCE.getElementWithResources_ResourceLimit();

		/**
		 * The meta object literal for the '<em><b>Metrics</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ELEMENT_WITH_RESOURCES__METRICS = eINSTANCE.getElementWithResources_Metrics();

		/**
		 * The meta object literal for the '{@link model.Environment <em>Environment</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.Environment
		 * @see model.impl.ModelPackageImpl#getEnvironment()
		 * @generated
		 */
		public static final EEnum ENVIRONMENT = eINSTANCE.getEnvironment();

	}

} //ModelPackageImpl
