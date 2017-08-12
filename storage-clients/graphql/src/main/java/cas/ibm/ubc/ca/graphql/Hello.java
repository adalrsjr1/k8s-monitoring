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

import cas.ibm.ubc.ca.graphql.model.ModelSchema;
import cas.ibm.ubc.ca.graphql.model.Service;
import graphql.GraphQL;

public class Hello {
	public static void main(String[] args) {
		GraphQLObjectType queryType = ModelSchema.queryType; 
		
		GraphQLSchema schema = GraphQLSchema.newSchema()
				                            .query(queryType)
				                            .build();
		
		GraphQL graphQL = GraphQL.newGraphQL(schema).build();
		
//		Map<String, Object > result0 = graphQL.execute("{services {name}}").getData();
//		Map<String, Object > result1 = graphQL.execute("{services {name},service(name:\"catalogue-db\"){name}}").getData();
//		Map<String, Object > result3 = graphQL.execute("{service(name:\"catalogue-db\"){name}}").getData();
//		Map<String, Object > result4 = graphQL.execute("{namespace(name:\"sock-shop\") {name, services{name,id} }}").getData();
//		Map<String, Object > result5 = graphQL.execute("{namespaces {name, services{name} }}").getData();
		Map<String, Object > result6 = graphQL.execute("{service(name:\"catalogue-db\"){name, serviceVersions{ n_replicas, version, replicas{name}}}}").getData();
		
		System.out.println(result6);
		
//		SchemaParser schemaParser = new SchemaParser();
//		SchemaGenerator schemaGenerator = new SchemaGenerator();
//
//		File schemaFile = new File("src/main/resources/model.graphqls");
//
//		TypeDefinitionRegistry typeRegistry = schemaParser.parse(schemaFile);
//		RuntimeWiring wiring = buildRuntimeWiring();
//		GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
//		
//		GraphQL graphQL = GraphQL.newGraphQL(graphQLSchema).build();
//		
//		Object obj = graphQL.execute("{services}");
//		System.out.println(obj);
	}

	static RuntimeWiring buildRuntimeWiring() {
	    return RuntimeWiring.newRuntimeWiring()
	            .scalar(CustomScalar)
	            // this uses builder function lambda syntax
	            .type("QueryType", typeWiring -> typeWiring
	                    .dataFetcher("services", new StaticDataFetcher(Service.getServices()))
	                    .dataFetcher("service", new StaticDataFetcher(Service.getService()))
	            )
//	            .type("Human", typeWiring -> typeWiring
//	                    .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
//	            )
//	            // you can use builder syntax if you don't like the lambda syntax
//	            .type("Droid", typeWiring -> typeWiring
//	                    .dataFetcher("friends", StarWarsData.getFriendsDataFetcher())
//	            )
//	            // or full builder syntax if that takes your fancy
//	            .type(
//	                    newTypeWiring("Character")
//	                            .typeResolver(StarWarsData.getCharacterTypeResolver())
//	                            .build()
//	            )
	            .build();
	}
	
	public static GraphQLScalarType CustomScalar = new GraphQLScalarType("Custom", "Custom Scalar", new Coercing<Integer, Integer>() {
        @Override
        public Integer serialize(Object input) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public Integer parseValue(Object input) {
            throw new UnsupportedOperationException("Not implemented");
        }

        @Override
        public Integer parseLiteral(Object input) {
            throw new UnsupportedOperationException("Not implemented");
        }
});

	
}
