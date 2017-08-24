This is the InfluxDB client to collecting metrics for the k8s cluster.

### Built with

- Java 8
- [influxdb-java][influxdb-java]
- Maven

### Project management

Use common Maven goals. For instance:

    $ mvn compile

compiles the project; while

    $ mvn install

compiles and install it to your local Maven repository.

For more information on managing a Maven project, check out [Maven docs][maven].

### Tests

#### Setup

You'll need to have a local InfluxDB installed and running as tests use default
settings (localhost, port 8086, user root, password root) to access the Influx
database.

#### Run

    $ mvn test

[influxdb-java]: https://github.com/influxdata/influxdb-java
[maven]: https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Lifecycle_is_Made_Up_of_Phases
