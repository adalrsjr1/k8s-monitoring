package cas.ibm.ubc.ca.model.manager.planner

import java.lang.reflect.Type
import java.util.List
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.spotify.docker.client.DockerClient
import com.spotify.docker.client.exceptions.DockerException
import com.spotify.docker.client.messages.AutoValue_Container
import com.spotify.docker.client.messages.ContainerConfig
import com.spotify.docker.client.messages.ContainerCreation
import com.spotify.docker.client.messages.HostConfig
import com.spotify.docker.client.messages.HostConfig.Bind
import cas.ibm.ubc.ca.interfaces.messages.Moviment
import groovy.transform.builder.Builder
import model.Affinity
import model.Host
import model.ServiceInstance

@Builder
class CallZ3OnDockerConfig {
	String scriptPath
	String imageName
	String containerWorkdir
	String containerName
	
	boolean isEmpty() {
		return !scriptPath && !imageName && !containerWorkdir && !containerName
	}
}

class CallZ3OnDocker {
	private static String SCRIPT_PATH = "/home/adalrsjr1/Code/ibm-stack/storage-clients/com.sanbox/src/main/resources/z3"
	private static String IMAGE_NAME = "adalrsjr1/z3"
	private static String CONTAINER_WORKDIR = "/home/z3"
	private static String CONTAINER_NAME = "z3"
	private static String CONTAINER_ID
	private static CallZ3OnDocker INSTANCE
	
	
	private DockerClient client
	
	private CallZ3OnDocker() {
		client = DefaultDockerClient.fromEnv().build();
		CONTAINER_ID = z3IsRunning()
		if(!CONTAINER_ID)
			CONTAINER_ID = startZ3()
	}
	
	private static void setConfig(CallZ3OnDockerConfig config) {
		CallZ3OnDocker.CONTAINER_NAME = config.containerName ?: CONTAINER_NAME
		CallZ3OnDocker.CONTAINER_WORKDIR = config.containerWorkdir ?: CONTAINER_WORKDIR
		CallZ3OnDocker.IMAGE_NAME = config.imageName ?: IMAGE_NAME
		CallZ3OnDocker.SCRIPT_PATH = config.scriptPath ?: SCRIPT_PATH
	}
	
	public static synchronized CallZ3OnDocker getInstance() {
		return INSTANCE
	}
	
	public static CallZ3OnDocker create(CallZ3OnDockerConfig config) {
		if(config) {
			setConfig(config)
		}
		INSTANCE = new CallZ3OnDocker()
		return getInstance()
	}
	
	public static String getId() {
		return CONTAINER_ID
	}
	
	private String z3IsRunning() {
		List containers = client.listContainers()
		
		containers.find { AutoValue_Container container ->
			container.image == IMAGE_NAME
		}?.id
	}
	
	private String startZ3() {
		Bind bind = Bind.builder()
						.from(SCRIPT_PATH)
						.to(CONTAINER_WORKDIR)
						.readOnly(true)
						.build()
		
		HostConfig hostConfig = HostConfig.builder()
										  .appendBinds(bind)
										  .build()
		
		ContainerConfig config = ContainerConfig.builder()
					   .image(IMAGE_NAME)
					   .tty(true)
					   .hostConfig(hostConfig)
					   .build()
					   
		ContainerCreation container = client.createContainer(config, CONTAINER_NAME)
		client.startContainer(container.id())
		return container.id()
	}
	
	void stopZ3() {
		client.stopContainer(CONTAINER_ID, 0)
		client.removeContainer(CONTAINER_ID)
		client.close()
		INSTANCE = null
	}
	
	private String getZ3Id() {
		return CONTAINER_ID
	}
	
	// the java-client api has a bug to run command programatically
	String execOnZ3(String script) {
		try {
			def process = "docker exec ${CONTAINER_NAME} python $script".execute()
			process.waitFor()
			return process.text
		}
		catch(DockerException e) {
			stopZ3()
			throw new RuntimeException(e)
		}
	}
	
	private void init() {
		String id = z3IsRunning()
		if(!id) {
			startZ3()
		}
	}
		
	
	public static void main(String[] args) {
		CallZ3OnDockerConfig config = CallZ3OnDockerConfig.builder()
													  .containerName("z4")
													  .build()
		CallZ3OnDocker.create()
		println CallZ3OnDocker.instance.execOnZ3("hello.py")
		CallZ3OnDocker.getInstance().stopZ3()
			
	}
}

class Z3AdaptationPlanner implements AdaptationPlanner {
	private static final String RESOURCES_PATH = "/home/adalrsjr1/Code/ibm-stack/storage-clients/model-manager/src/main/resources/z3"
	private static final Gson GSON = new Gson()
	Z3AdaptationPlanner() {
		CallZ3OnDockerConfig config = CallZ3OnDockerConfig.builder()
														  .containerName("z3")
														  .build()
		CallZ3OnDocker.create(config)
	}
	
	private Map hostToMap(Host hostObj) {
		Map hostMap = [:]
		with(hostObj) {
			hostMap["name"] = name
			hostMap["cpu"] = resourceReserved["cpu"]
			hostMap["memory"] = resourceReserved["memory"]
			hostMap["cores"] = metrics["cores"]
		}
		return hostMap	
	}
	
	private Map serviceToMap(ServiceInstance serviceObj) {
		Map serviceMap = [:]
		with(serviceObj) {
			serviceMap["name"] = id
			serviceMap["host"] = hostToMap(host)
			serviceMap["cpu"] = metrics["cpu"]
			serviceMap["memory"] = metrics["memory"]
			serviceMap["stateful"] = stateful
 		}
		return serviceMap
	}
	
	private Map affinityToMap(Affinity affinityObj) {
		
		Map affinityMap = [:]
		with(affinityObj) {
			affinityMap["affinity"] = degree
			affinityMap["source"] = serviceToMap(affinityObj.eContainer)
			affinityMap["target"] = serviceToMap(with)
		}
		
		return affinityMap
	}
	
	private String affinitiesToJson(List<Affinity> affinities) {
		
		List affinitiesMap = affinities.inject([]) { result, affinity ->
			result << affinityToMap(affinity)
		}
		
		return GSON.toJson(affinitiesMap)
	}
	
	private keepJson(String json) {
		new File(RESOURCES_PATH + "allocz3.json").withWriter("UTF-8") { writer ->
			writer.write(json)
		}
	}
	
	private Map affinitiesSources(List affinities) {
		return affinities.inject([:]){ map, Affinity affinity ->
			ServiceInstance source = affinity.eContainer
			map << [(source.id) : Moviment.create(source.application, 
				source.id, source.host.name, "")] 
		}
	}
	
	private List<Moviment> jsonToMoviments(Map affinitiesSource, String json) {
		Type type = new TypeToken<List<Map>>() {}.getType();
		List partialMoves = GSON.fromJson(json, type)
		
		return partialMoves.inject([]) { list, partialMove ->
			Moviment moviment = affinitiesSource[partialMove["job"]]
			list << Moviment.create(moviment.application, 
				moviment.service, moviment.hostSource, partialMove["hosts"])
		}
	}
	
	
	private String runOnZ3() {
		return CallZ3OnDocker.instance.execOnZ3(RESOURCES_PATH + "allocz3.py")
	}
	
	@Override
	public List<Moviment> execute(List affinities) {
		String affinitiesJson = affinitiesToJson(affinities)
		keepJson(affinitiesJson)
		String z3Results = runOnZ3()
		return jsonToMoviments(z3Results)
	}

}
