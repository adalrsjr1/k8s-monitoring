This is the Zipkin client to collecting messages traces from the k8s cluster.

### Built with

- Java 8
- Maven

### Project management

Use common Maven goals. For instance:

    $ mvn compile

compiles the project; while

    $ mvn install

compiles and install it to your local Maven repository.

For more information on managing a Maven project, check out [Maven docs][maven].

### Tests

Run with

    $ mvn test

#### Integration

Integration tests depend on a Zipkin instance running on localhost. It may be
provided through

    $ docker run -d -p 9411:9411 openzipkin/zipkin # or
    $ bin/start-zipkin-docker # which runs docker command above

[maven]: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Lifecycle_is_Made_Up_of_Phases
