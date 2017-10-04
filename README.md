# HPE OneView SDK for Java

The **HPE OneView SDK for Java** enables Java developers to easily integrate their Java solutions with the HPE OneView. You can get started in minutes downloading, and building the project using [Maven](https://maven.apache.org/) project management tool or just adding the SDK as a dependency of a Maven project.

The SDK provides clients for the REST API specification of each resource type available in the HPE OneView.

The project Javadocs are available at [http://hewlettpackard.github.io/oneview-sdk-java/](http://hewlettpackard.github.io/oneview-sdk-java/)

## Getting Started ##

### Minimum requirements ###
* Java **1.7**
* Maven **3.0.5**
* OpenSSL

### Add as Maven dependency ###
The SDK is available in the [Apache Maven Central Repository](https://search.maven.org/) (back then known as Maven Central). Thus, the recommended way to use the OneView SDK for Java in your project is to consume it from Maven. You can add the **HPE OneView Java SDK** as a dependency of your project by adding the following lines to your `pom.xml`:

```xml
<dependency>
  <groupId>com.hpe.oneview</groupId>
  <artifactId>oneview-sdk-java</artifactId>
  <version>3.2.1</version>
</dependency>
```

### Building from source ###
Once you check out the code from GitHub, you can build the SDK using Maven. To build the project from its source, you can use the following commands:

```sh
$ git clone git@github.com:HewlettPackard/oneview-sdk-java.git
$ mvn -f oneview-sdk-java/pom.xml clean install
```

### Generate TrustStore to store SSL server certificate ###
To enable the SDK to establish a SSL connection to the HPE OneView server, it is necessary to generate a `TrustStore` file containing the server credentials.

Below are the steps to perform this task:

#### Fetch server CA certificate ####
Example:
```sh
$ openssl s_client -showcerts -host <host> -port 443
```
Copy the server certificate content from `-----BEGIN CERTIFICATE-----` to `-----END CERTIFICATE-----` (inclusive).
Paste the content into a file called `default-server.crt`.

#### Generate `TrustStore` ####
Example:
```sh
$ keytool -import -v -trustcacerts -alias myservercert -file default-server.crt -keystore TrustStore
```
> Note: Choose the *yes* option, when prompted to trust the certificate.

Inside the project's `bin` folder you can find a bash script (`build-truststore.sh`) that  can be used to automate the creation of the `TrustStore` file. It can also serve as reference if you decide to run the commands manually.

#### Image Streamer ####
To use the SDK with an Image Streamer, repeat the steps above to fetch server CA certificate and generate the `TrustStore` pointing to the Image Streamer appliance.

Example:
```sh
$ openssl s_client -showcerts -host <image_streamer_host> -port 443
```
Example:
```sh
$ keytool -import -v -trustcacerts -alias myservercert -file default-server.crt -keystore TrustStore
```
> Note: Choose the *yes* option, when prompted to trust the certificate.

### Setting your OneView version ###
The OneView Java SDK supports the API endpoints `120, 200, 201, 300, 500 (partially)`.
The current `default` API version used by the Java SDK is `300`.

To change the API to execute the samples, you must set the API version on the [sdk-config-sample.properties](oneview-sdk-java/samples/src/main/resources/sdk-config-sample.properties):

```properties
oneview.api_version=500
```

The API list is as follows:

- HPE OneView 1.20 API version: `120`
- HPE OneView 2.00 API version: `200`
- HPE OneView 3.00 API version: `300`
- HPE OneView 3.10 API version: `500`

### Example programs ###
The SDK comes with several sample programs inside the `samples` module. For each of the supported resource types, there is a corresponding sample file. To run one of them, we recommend the use of an IDE ([Eclipse](https://eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/)).

> Note: If you choose to use Eclipse IDE, you will need to generate the Eclipse IDE files (`\*.classpath`, `\*.project`, `\*.wtpmodules` and the `.settings` folder). You can generate these files using Maven Eclipse Plugin with the command `mvn clean eclipse:clean eclipse:eclipse`.

The file `oneview_java_sdk_config.properties` must be updated to contain the following information:
* Path for the `TrustStore` file and its password
* OneView credentials and host information

> Note: Instead of changing the location of your `TrustStore` file, you can just place it inside the directory `samples/src/main/resources`.

#### Message Bus samples ####
Before running the Message Bus samples, it is necessary to execute a HTTP POST request to the OneView server in order to generate the RabbitMQ certificate files.
Below is an example that illustrates how you can perform this:
```sh
$ curl -X POST -H "Auth:{AUTHORIZATION_TOKEN}" \
-H "X-Api-Version:{VERSION}" \
-H "Content-Type:application/json" \
-d '{"commonName":"default","type":"RabbitMqClientCertV2"}' \
-k https://{HOST}/rest/certificates/client/rabbitmq
```

## API Implementation ##
A status of the HPE OneView REST interfaces that have been implemented in this Java library can be found in the [endpoints-support.md](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/endpoints-support.md).

## SDK Logging Configuration ##
The OneView SDK for Java uses the Simple Logging Facade for Java (SLF4J) for logging. The SLF4J serves as a simple facade or abstraction for various logging frameworks, such as `java.util.logging`, `logback` and `log4j`. SLF4J allows the end-user to plug in the desired logging framework at deployment time.

We highly recommend that you read the [SLF4J user manual](http://www.slf4j.org/manual.html) to understand how you can use one of the "SLF4J bindings" to configure the SDK logs.

For example, the `samples` module uses the `log4j` as the underlying logging framework. To do this, we simply declare `org.slf4j:slf4j-log4j12` as a dependency in the pom.xml file as shown below.
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
  <version>1.7.21</version>
</dependency>
```

## Contributing ##
You know the drill. Fork it, branch it, change it, commit it, and pull-request it.
We are passionate about improving this project, and are glad to accept help to make it better. However, keep the following in mind:

We reserve the right to reject changes that we feel do not fit the scope of this project. For feature additions, please open an issue to discuss your ideas before doing the work.

#### Naming Convention for OneView Resources ####
The following summarizes code structure and naming conventions for the Java SDK.

- **Packages:** The package is named according to the **HPE OneView API Reference** group, with all characters in lowercase, separated by dots, as the standard Java package naming convention, for example: `com.hp.ov.sdk.rest.client.networking`.
- **Classes:** We are using camel case to define the class name, for example: **FcNetwork.java**.
- **Client classes:** Clients are named using format `<ClassName>Client.java`, for example: **FcNetworkClient.java**.
- **Examples:** Samples are named using format `<ClassNameClient>Sample.java`, for example: **FcNetworkClientSample.java**.
- **Tests:**  The unit tests are named using format `<ClassNameClient>Test.java`, for example: **FcNetworkClientTest.java**.

## Testing ##
When contributing code to this project, we require tests to accompany the code being delivered.
That ensures a higher standing of quality, and also helps to avoid minor mistakes and future regressions.

### Unit Tests ###
To write unit tests we use [JUnit](http://junit.org/) and [Mockito](http://site.mockito.org/). 

It is important that all methods implemented in SDK should have a correspondent test method.
Method names should start with `should`.

To execute unit tests for a class, just run it as `JUnit` Test.

### BDD (Behavior Driven Development) Tests ###
We are using [Cucumber](https://cucumber.io/docs/reference/jvm) as the BDD framework to develop functional tests.

The test artifacts are at [Automated Test Folder](https://github.com/HewlettPackard/oneview-sdk-java/tree/master/automated-tests).

There are 2 test suites, one for **C7000** [AllTestsC7000.java](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/automated-tests/src/test/java/com/hp/ov/sdk/oneview/AllTestsC7000.java) and other for **Synergy** [AllTestsSynergy.java](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/automated-tests/src/test/java/com/hp/ov/sdk/oneview/AllTestsSynergy.java) that are responsible for executing tests for all the resources implemented.

To execute the test suite, just run it as `JUnit` Test.

Test scenarios are described in `.feature` files, for example [fcNetwork.feature](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/automated-tests/src/test/resources/cucumber/network/fcNetwork.feature).

The tests are independent among resources, so it is possible to test a single resource. To execute the **FC Network** test scenarios, for example, just run [FcNetworkBDDTest.java](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/automated-tests/src/test/java/com/hp/ov/sdk/resources/network/FcNetworkBDDTest.java) as a `JUnit` Test.

For more information about requirements please check the comments in the test suite files.

#### Setting configuration properties for tests ####
There are 2 `.properties ` files required to execute BDD tests:

##### [oneview_java_sdk_config.properties](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/automated-tests/src/test/resources/oneview_java_sdk_config.properties) #####
This file has the same properties found in [sdk-config-sample.properties](oneview-sdk-java/samples/src/main/resources/sdk-config-sample.properties) used for samples. You should set attributes as TrustStore, OneView credentials, and the API version:

```properties
truststore.file=src/main/resources/TrustStore
oneview.api_version=300
oneview.hostname=10.10.10.10
oneview.username=administrator
oneview.password=admin
oneview.domain=local
```

##### [oneView.properties](https://github.com/HewlettPackard/oneview-sdk-java/blob/master/automated-tests/src/test/resources/oneView.properties) #####
This file has additional attributes such as Storage and Enclosure credentials that are used by tests:

```properties
storageSystemHostname=10.10.10.10
storageSystemPassword=dcs
enclosureHostname=10.10.10.10
domain=LOCAL
username=administrator
enclosurePassword=dcs
file_sdk_config=src/test/resources/oneview_java_sdk_config.properties
version=V_300
storageSystemUsername=dcs
hostname=10.10.10.10
password=rainforest
enclosureUsername=dcs
```

## Feature Requests ##
If you have a need not being met by the current implementation, please let us know (via a new issue). This feedback is crucial for us to deliver a useful product. Do not assume that we have already thought of everything, because we assure you that is not the case.

## License ##
The OneView SDK for Java is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
