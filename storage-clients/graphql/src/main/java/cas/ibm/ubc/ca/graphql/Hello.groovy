package cas.ibm.ubc.ca.graphql;

import graphql.schema.Coercing;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLScalarType;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.io.File;
import java.util.Map;

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import cas.ibm.ubc.ca.graphql.model.ModelSchema;
import cas.ibm.ubc.ca.graphql.model.Service;
import cas.ibm.ubc.ca.k8s.K8sCache
import graphql.GraphQL;

class XXXInterceptor implements Interceptor {
	private static Logger logger 
	long time
	boolean invokeMethod = true
	
   synchronized Object beforeInvoke(Object obj, String name, Object[] args) {
	   time = System.nanoTime()
   }
	
   synchronized Object afterInvoke(Object obj, String name, Object[] args, Object result) {
	   logger = LoggerFactory.getLogger(obj.class)
	   logger.info("{} {} ms",name, (System.nanoTime() - time)/1000000)
	   result
   }

	@Override
	synchronized public boolean doInvoke() {
		return invokeMethod;
	}
}

public class Hello {
	
//	static {
//		GraphQL.metaClass.timedExecute { String requestString ->
//			def init = System.currentTimeMillis()
//			def result = delegate.execute(requestString)
//			println (System.currentTimeMillis() - init)
//			return result
//			
//		}
//	}
	
	public static void main(String[] args) {
		def proxy = ProxyMetaClass.getInstance(K8sCache)
		def proxy2 = ProxyMetaClass.getInstance(GraphQL)
		def interceptor = new XXXInterceptor()
		proxy.interceptor = interceptor
		proxy2.interceptor = interceptor
		
		
		
		proxy.use {
		proxy2.use{
//		proxy.use([proxy2], {
		GraphQLObjectType queryType = ModelSchema.queryType; 
		
		GraphQLSchema schema = GraphQLSchema.newSchema()
				                            .query(queryType)
				                            .build();
		
		GraphQL graphQL = GraphQL.newGraphQL(schema).build();
	
			
		
//		Map<String, Object > result0 = graphQL.execute("{services {name}}").getData();
//		Map<String, Object > result1 = graphQL.execute("{services {name},service(name:\"catalogue-db\"){name}}").getData();
//		Map<String, Object > result3 = graphQL.execute("{service(name:\"catalogue-db\"){name}}").getData();
		Map<String, Object > result4 = graphQL.execute("{namespace(name:\"sock-shop\") {name, services{name} }}").getData();
//		Map<String, Object > result5 = graphQL.execute("{namespaces {name, services{name} }}").getData();
//		Map<String, Object > result6 = graphQL.execute("{service(name:\"front-end\"){name, serviceVersions{ n_replicas, version, replicas{id}}}}").getData();
		
		System.out.println(result4);
		}}
	}
	
}
