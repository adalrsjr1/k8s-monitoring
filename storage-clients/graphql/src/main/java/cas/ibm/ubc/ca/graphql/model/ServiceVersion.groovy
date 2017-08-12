package cas.ibm.ubc.ca.graphql.model

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString(includeNames=true)
@EqualsAndHashCode
class ServiceVersion {
	List<String> version
}
