package cas.ibm.ubc.ca;

import java.net.InetAddress;
import java.util.Collections;

import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsRequest;
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.SecureString;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.xpack.client.PreBuiltXPackTransportClient;
import org.elasticsearch.xpack.security.authc.support.UsernamePasswordToken;

import com.carrotsearch.hppc.cursors.ObjectCursor;


public class Hello {
	public static void main(String[] args) throws Exception {
		
		Settings settings = Settings.builder()
									// java_client:java_client
									.put("cluster.name", "docker-cluster")
									.put("xpack.security.user", "elastic:changeme")
									.build();
		
		TransportClient client = new PreBuiltXPackTransportClient(settings)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("kay.cs.ubc.ca"),9300));
		
		String token =  UsernamePasswordToken.basicAuthHeaderValue("java_client", new SecureString("java_client".toCharArray()));
		client.filterWithHeader(Collections.singletonMap("Authorization", token));
		
		XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
		
		
		
		GetMappingsResponse mResponse = client.admin().indices().getMappings(
				new GetMappingsRequest().types("fluentd")).get();
		
		// getting schema
		for (ObjectCursor<ImmutableOpenMap<String, MappingMetaData>> o: mResponse.mappings().values()) {
			System.out.println(o.value.get("fluentd").getSourceAsMap());
		}
		
//		GetResponse response = client.prepareGet("logstash-2017.07.28", "fluentd", "AV2KtlApfHueWWeW2q06").get();
		
		// scrolling
		//https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-scrolling.html
//		SearchResponse response = client.prepareSearch()
//				.setTypes("fluentd")
//				.setFrom(0)
//				.setSize(1000)
//				.get();
//		
//		do {
//			for(SearchHit hit : response.getHits().getHits()) {
//				System.out.println(hit.getSourceAsString());
//			}
//		}while(response.getHits().getHits().length != 0);
//		
//		System.out.println(response);
//		
		client.close();
	}
}
