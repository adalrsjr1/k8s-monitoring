package cas.ibm.ubc.ca.graphql;

import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLObjectType.newObject;

import java.util.Map;

import graphql.GraphQL;

public class Hello {
	public static void main(String[] args) {
		GraphQLObjectType queryType = newObject()
				.name("helloWorld")
				.field(newFieldDefinition()
						.type(GraphQLString)
						.name("hello")
						.staticValue("world"))
				.build();
		
		GraphQLSchema schema = GraphQLSchema.newSchema()
				.query(queryType)
				.build();
		
		GraphQL graphQL = GraphQL.newGraphQL(schema).build();
		
		Map<String, Object> result = graphQL.execute("{hello}").getData();
		System.out.println(result);
		
	}

}
