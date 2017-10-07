/**
 */
package model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://gfads.cin.ufpe.br/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link model.impl.ClusterImpl <em>Cluster</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ClusterImpl
	 * @see model.impl.ModelPackageImpl#getCluster()
	 * @generated
	 */
	int CLUSTER = 0;

	/**
	 * The feature id for the '<em><b>Applications</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__APPLICATIONS = 0;

	/**
	 * The feature id for the '<em><b>Hosts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__HOSTS = 1;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER__ENVIRONMENT = 2;

	/**
	 * The number of structural features of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_FEATURE_COUNT = 3;

	/**
	 * The operation id for the '<em>Move</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER___MOVE__STRING_STRING_STRING = 0;

	/**
	 * The number of operations of the '<em>Cluster</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLUSTER_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link model.impl.ApplicationImpl <em>Application</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ApplicationImpl
	 * @see model.impl.ModelPackageImpl#getApplication()
	 * @generated
	 */
	int APPLICATION = 1;

	/**
	 * The feature id for the '<em><b>Services</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__SERVICES = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION__NAME = 1;

	/**
	 * The number of structural features of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Application</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.ElementWithResourcesImpl <em>Element With Resources</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ElementWithResourcesImpl
	 * @see model.impl.ModelPackageImpl#getElementWithResources()
	 * @generated
	 */
	int ELEMENT_WITH_RESOURCES = 8;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT = 0;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_WITH_RESOURCES__METRICS = 1;

	/**
	 * The number of structural features of the '<em>Element With Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_WITH_RESOURCES_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Element With Resources</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_WITH_RESOURCES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ServiceImpl
	 * @see model.impl.ModelPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 2;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__RESOURCE_LIMIT = ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__METRICS = ELEMENT_WITH_RESOURCES__METRICS;

	/**
	 * The feature id for the '<em><b>Has Affinities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__HAS_AFFINITIES = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__NAME = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PORT = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = ELEMENT_WITH_RESOURCES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.AffinityImpl <em>Affinity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.AffinityImpl
	 * @see model.impl.ModelPackageImpl#getAffinity()
	 * @generated
	 */
	int AFFINITY = 3;

	/**
	 * The feature id for the '<em><b>With</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINITY__WITH = 0;

	/**
	 * The feature id for the '<em><b>Degree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINITY__DEGREE = 1;

	/**
	 * The number of structural features of the '<em>Affinity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINITY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Affinity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AFFINITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.ServiceReplicaImpl <em>Service Replica</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.ServiceReplicaImpl
	 * @see model.impl.ModelPackageImpl#getServiceReplica()
	 * @generated
	 */
	int SERVICE_REPLICA = 4;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__RESOURCE_LIMIT = SERVICE__RESOURCE_LIMIT;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__METRICS = SERVICE__METRICS;

	/**
	 * The feature id for the '<em><b>Has Affinities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__HAS_AFFINITIES = SERVICE__HAS_AFFINITIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__NAME = SERVICE__NAME;

	/**
	 * The feature id for the '<em><b>Port</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__PORT = SERVICE__PORT;

	/**
	 * The feature id for the '<em><b>Messages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__MESSAGES = SERVICE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Container</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__CONTAINER = SERVICE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__ID = SERVICE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA__ADDRESS = SERVICE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Service Replica</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA_FEATURE_COUNT = SERVICE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Service Replica</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_REPLICA_OPERATION_COUNT = SERVICE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.MessageImpl <em>Message</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.MessageImpl
	 * @see model.impl.ModelPackageImpl#getMessage()
	 * @generated
	 */
	int MESSAGE = 5;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__DESTINATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__NAME = 2;

	/**
	 * The feature id for the '<em><b>Avg Response Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__AVG_RESPONSE_TIME = 3;

	/**
	 * The feature id for the '<em><b>Avg Size</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__AVG_SIZE = 4;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__TIMESTAMP = 5;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE__UID = 6;

	/**
	 * The number of structural features of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Message</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.impl.HostImpl <em>Host</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.HostImpl
	 * @see model.impl.ModelPackageImpl#getHost()
	 * @generated
	 */
	int HOST = 6;

	/**
	 * The feature id for the '<em><b>Resource Limit</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOST__RESOURCE_LIMIT = ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT;

	/**
	 * The feature id for the '<em><b>Metrics</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOST__METRICS = ELEMENT_WITH_RESOURCES__METRICS;

	/**
	 * The feature id for the '<em><b>Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOST__SERVICES = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOST__NAME = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Host</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOST_FEATURE_COUNT = ELEMENT_WITH_RESOURCES_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Host</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HOST_OPERATION_COUNT = ELEMENT_WITH_RESOURCES_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link model.impl.StringToLongMapImpl <em>String To Long Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.impl.StringToLongMapImpl
	 * @see model.impl.ModelPackageImpl#getStringToLongMap()
	 * @generated
	 */
	int STRING_TO_LONG_MAP = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_LONG_MAP__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_LONG_MAP__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String To Long Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_LONG_MAP_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String To Long Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_TO_LONG_MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link model.Environment <em>Environment</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see model.Environment
	 * @see model.impl.ModelPackageImpl#getEnvironment()
	 * @generated
	 */
	int ENVIRONMENT = 9;


	/**
	 * Returns the meta object for class '{@link model.Cluster <em>Cluster</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cluster</em>'.
	 * @see model.Cluster
	 * @generated
	 */
	EClass getCluster();

	/**
	 * Returns the meta object for the containment reference list '{@link model.Cluster#getApplications <em>Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Applications</em>'.
	 * @see model.Cluster#getApplications()
	 * @see #getCluster()
	 * @generated
	 */
	EReference getCluster_Applications();

	/**
	 * Returns the meta object for the containment reference list '{@link model.Cluster#getHosts <em>Hosts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hosts</em>'.
	 * @see model.Cluster#getHosts()
	 * @see #getCluster()
	 * @generated
	 */
	EReference getCluster_Hosts();

	/**
	 * Returns the meta object for the attribute '{@link model.Cluster#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Environment</em>'.
	 * @see model.Cluster#getEnvironment()
	 * @see #getCluster()
	 * @generated
	 */
	EAttribute getCluster_Environment();

	/**
	 * Returns the meta object for the '{@link model.Cluster#move(java.lang.String, java.lang.String, java.lang.String) <em>Move</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Move</em>' operation.
	 * @see model.Cluster#move(java.lang.String, java.lang.String, java.lang.String)
	 * @generated
	 */
	EOperation getCluster__Move__String_String_String();

	/**
	 * Returns the meta object for class '{@link model.Application <em>Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Application</em>'.
	 * @see model.Application
	 * @generated
	 */
	EClass getApplication();

	/**
	 * Returns the meta object for the containment reference list '{@link model.Application#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Services</em>'.
	 * @see model.Application#getServices()
	 * @see #getApplication()
	 * @generated
	 */
	EReference getApplication_Services();

	/**
	 * Returns the meta object for the attribute '{@link model.Application#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Application#getName()
	 * @see #getApplication()
	 * @generated
	 */
	EAttribute getApplication_Name();

	/**
	 * Returns the meta object for class '{@link model.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see model.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the containment reference list '{@link model.Service#getHasAffinities <em>Has Affinities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Has Affinities</em>'.
	 * @see model.Service#getHasAffinities()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_HasAffinities();

	/**
	 * Returns the meta object for the attribute '{@link model.Service#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Service#getName()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Name();

	/**
	 * Returns the meta object for the attribute '{@link model.Service#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Port</em>'.
	 * @see model.Service#getPort()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_Port();

	/**
	 * Returns the meta object for class '{@link model.Affinity <em>Affinity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Affinity</em>'.
	 * @see model.Affinity
	 * @generated
	 */
	EClass getAffinity();

	/**
	 * Returns the meta object for the reference '{@link model.Affinity#getWith <em>With</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>With</em>'.
	 * @see model.Affinity#getWith()
	 * @see #getAffinity()
	 * @generated
	 */
	EReference getAffinity_With();

	/**
	 * Returns the meta object for the attribute '{@link model.Affinity#getDegree <em>Degree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Degree</em>'.
	 * @see model.Affinity#getDegree()
	 * @see #getAffinity()
	 * @generated
	 */
	EAttribute getAffinity_Degree();

	/**
	 * Returns the meta object for class '{@link model.ServiceReplica <em>Service Replica</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Replica</em>'.
	 * @see model.ServiceReplica
	 * @generated
	 */
	EClass getServiceReplica();

	/**
	 * Returns the meta object for the containment reference list '{@link model.ServiceReplica#getMessages <em>Messages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Messages</em>'.
	 * @see model.ServiceReplica#getMessages()
	 * @see #getServiceReplica()
	 * @generated
	 */
	EReference getServiceReplica_Messages();

	/**
	 * Returns the meta object for the attribute '{@link model.ServiceReplica#getContainer <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Container</em>'.
	 * @see model.ServiceReplica#getContainer()
	 * @see #getServiceReplica()
	 * @generated
	 */
	EAttribute getServiceReplica_Container();

	/**
	 * Returns the meta object for the attribute '{@link model.ServiceReplica#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see model.ServiceReplica#getId()
	 * @see #getServiceReplica()
	 * @generated
	 */
	EAttribute getServiceReplica_Id();

	/**
	 * Returns the meta object for the attribute '{@link model.ServiceReplica#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see model.ServiceReplica#getAddress()
	 * @see #getServiceReplica()
	 * @generated
	 */
	EAttribute getServiceReplica_Address();

	/**
	 * Returns the meta object for class '{@link model.Message <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message</em>'.
	 * @see model.Message
	 * @generated
	 */
	EClass getMessage();

	/**
	 * Returns the meta object for the reference '{@link model.Message#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see model.Message#getSource()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_Source();

	/**
	 * Returns the meta object for the reference '{@link model.Message#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see model.Message#getDestination()
	 * @see #getMessage()
	 * @generated
	 */
	EReference getMessage_Destination();

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Message#getName()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_Name();

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getAvgResponseTime <em>Avg Response Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avg Response Time</em>'.
	 * @see model.Message#getAvgResponseTime()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_AvgResponseTime();

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getAvgSize <em>Avg Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Avg Size</em>'.
	 * @see model.Message#getAvgSize()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_AvgSize();

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see model.Message#getTimestamp()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_Timestamp();

	/**
	 * Returns the meta object for the attribute '{@link model.Message#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see model.Message#getUid()
	 * @see #getMessage()
	 * @generated
	 */
	EAttribute getMessage_Uid();

	/**
	 * Returns the meta object for class '{@link model.Host <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Host</em>'.
	 * @see model.Host
	 * @generated
	 */
	EClass getHost();

	/**
	 * Returns the meta object for the reference list '{@link model.Host#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Services</em>'.
	 * @see model.Host#getServices()
	 * @see #getHost()
	 * @generated
	 */
	EReference getHost_Services();

	/**
	 * Returns the meta object for the attribute '{@link model.Host#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see model.Host#getName()
	 * @see #getHost()
	 * @generated
	 */
	EAttribute getHost_Name();

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
	EClass getStringToLongMap();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToLongMap()
	 * @generated
	 */
	EAttribute getStringToLongMap_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringToLongMap()
	 * @generated
	 */
	EAttribute getStringToLongMap_Value();

	/**
	 * Returns the meta object for class '{@link model.ElementWithResources <em>Element With Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element With Resources</em>'.
	 * @see model.ElementWithResources
	 * @generated
	 */
	EClass getElementWithResources();

	/**
	 * Returns the meta object for the map '{@link model.ElementWithResources#getResourceLimit <em>Resource Limit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Resource Limit</em>'.
	 * @see model.ElementWithResources#getResourceLimit()
	 * @see #getElementWithResources()
	 * @generated
	 */
	EReference getElementWithResources_ResourceLimit();

	/**
	 * Returns the meta object for the map '{@link model.ElementWithResources#getMetrics <em>Metrics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Metrics</em>'.
	 * @see model.ElementWithResources#getMetrics()
	 * @see #getElementWithResources()
	 * @generated
	 */
	EReference getElementWithResources_Metrics();

	/**
	 * Returns the meta object for enum '{@link model.Environment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Environment</em>'.
	 * @see model.Environment
	 * @generated
	 */
	EEnum getEnvironment();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link model.impl.ClusterImpl <em>Cluster</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ClusterImpl
		 * @see model.impl.ModelPackageImpl#getCluster()
		 * @generated
		 */
		EClass CLUSTER = eINSTANCE.getCluster();

		/**
		 * The meta object literal for the '<em><b>Applications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLUSTER__APPLICATIONS = eINSTANCE.getCluster_Applications();

		/**
		 * The meta object literal for the '<em><b>Hosts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLUSTER__HOSTS = eINSTANCE.getCluster_Hosts();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLUSTER__ENVIRONMENT = eINSTANCE.getCluster_Environment();

		/**
		 * The meta object literal for the '<em><b>Move</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation CLUSTER___MOVE__STRING_STRING_STRING = eINSTANCE.getCluster__Move__String_String_String();

		/**
		 * The meta object literal for the '{@link model.impl.ApplicationImpl <em>Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ApplicationImpl
		 * @see model.impl.ModelPackageImpl#getApplication()
		 * @generated
		 */
		EClass APPLICATION = eINSTANCE.getApplication();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference APPLICATION__SERVICES = eINSTANCE.getApplication_Services();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute APPLICATION__NAME = eINSTANCE.getApplication_Name();

		/**
		 * The meta object literal for the '{@link model.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ServiceImpl
		 * @see model.impl.ModelPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Has Affinities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__HAS_AFFINITIES = eINSTANCE.getService_HasAffinities();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__NAME = eINSTANCE.getService_Name();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__PORT = eINSTANCE.getService_Port();

		/**
		 * The meta object literal for the '{@link model.impl.AffinityImpl <em>Affinity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.AffinityImpl
		 * @see model.impl.ModelPackageImpl#getAffinity()
		 * @generated
		 */
		EClass AFFINITY = eINSTANCE.getAffinity();

		/**
		 * The meta object literal for the '<em><b>With</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AFFINITY__WITH = eINSTANCE.getAffinity_With();

		/**
		 * The meta object literal for the '<em><b>Degree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AFFINITY__DEGREE = eINSTANCE.getAffinity_Degree();

		/**
		 * The meta object literal for the '{@link model.impl.ServiceReplicaImpl <em>Service Replica</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ServiceReplicaImpl
		 * @see model.impl.ModelPackageImpl#getServiceReplica()
		 * @generated
		 */
		EClass SERVICE_REPLICA = eINSTANCE.getServiceReplica();

		/**
		 * The meta object literal for the '<em><b>Messages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_REPLICA__MESSAGES = eINSTANCE.getServiceReplica_Messages();

		/**
		 * The meta object literal for the '<em><b>Container</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_REPLICA__CONTAINER = eINSTANCE.getServiceReplica_Container();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_REPLICA__ID = eINSTANCE.getServiceReplica_Id();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE_REPLICA__ADDRESS = eINSTANCE.getServiceReplica_Address();

		/**
		 * The meta object literal for the '{@link model.impl.MessageImpl <em>Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.MessageImpl
		 * @see model.impl.ModelPackageImpl#getMessage()
		 * @generated
		 */
		EClass MESSAGE = eINSTANCE.getMessage();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__SOURCE = eINSTANCE.getMessage_Source();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE__DESTINATION = eINSTANCE.getMessage_Destination();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__NAME = eINSTANCE.getMessage_Name();

		/**
		 * The meta object literal for the '<em><b>Avg Response Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__AVG_RESPONSE_TIME = eINSTANCE.getMessage_AvgResponseTime();

		/**
		 * The meta object literal for the '<em><b>Avg Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__AVG_SIZE = eINSTANCE.getMessage_AvgSize();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__TIMESTAMP = eINSTANCE.getMessage_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE__UID = eINSTANCE.getMessage_Uid();

		/**
		 * The meta object literal for the '{@link model.impl.HostImpl <em>Host</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.HostImpl
		 * @see model.impl.ModelPackageImpl#getHost()
		 * @generated
		 */
		EClass HOST = eINSTANCE.getHost();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HOST__SERVICES = eINSTANCE.getHost_Services();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HOST__NAME = eINSTANCE.getHost_Name();

		/**
		 * The meta object literal for the '{@link model.impl.StringToLongMapImpl <em>String To Long Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.StringToLongMapImpl
		 * @see model.impl.ModelPackageImpl#getStringToLongMap()
		 * @generated
		 */
		EClass STRING_TO_LONG_MAP = eINSTANCE.getStringToLongMap();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_LONG_MAP__KEY = eINSTANCE.getStringToLongMap_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_TO_LONG_MAP__VALUE = eINSTANCE.getStringToLongMap_Value();

		/**
		 * The meta object literal for the '{@link model.impl.ElementWithResourcesImpl <em>Element With Resources</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.impl.ElementWithResourcesImpl
		 * @see model.impl.ModelPackageImpl#getElementWithResources()
		 * @generated
		 */
		EClass ELEMENT_WITH_RESOURCES = eINSTANCE.getElementWithResources();

		/**
		 * The meta object literal for the '<em><b>Resource Limit</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_WITH_RESOURCES__RESOURCE_LIMIT = eINSTANCE.getElementWithResources_ResourceLimit();

		/**
		 * The meta object literal for the '<em><b>Metrics</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_WITH_RESOURCES__METRICS = eINSTANCE.getElementWithResources_Metrics();

		/**
		 * The meta object literal for the '{@link model.Environment <em>Environment</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see model.Environment
		 * @see model.impl.ModelPackageImpl#getEnvironment()
		 * @generated
		 */
		EEnum ENVIRONMENT = eINSTANCE.getEnvironment();

	}

} //ModelPackage
