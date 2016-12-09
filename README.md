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
The SDK is available in the Apache Maven Central Repository (back then known as Maven Central). Thus, the recommended way to use the OneView SDK for Java in your project is to consume it from Maven. You can add the **HPE OneView Java SDK** as a dependency of your project by adding the following lines to your `pom.xml`:

```xml
<dependency>
  <groupId>com.hpe.oneview</groupId>
  <artifactId>oneview-sdk-java</artifactId>
  <version>3.0.0</version>
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

Inside the project's `bin` folder you can find a bash script (`build-truststore.sh`) that  can be used to automate the creation of the TrustStore file. It can also serve as reference if you decide to run the commands manually.

### Example programs ###
The SDK comes with several sample programs inside the `samples` module. For each of the supported resource types, there is a corresponding sample file. To run one of them, we recommend the use of an IDE ([Eclipse](https://eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/)).

> Note: If you choose to use Eclipse IDE, you will need to generate the Eclipse IDE files (`\*.classpath`, `\*.project`, `\*.wtpmodules` and the `.settings` folder). You can generate these files using Maven Eclipse Plugin with the command `mvn clean eclipse:clean eclipse:eclipse`.

The file `oneview_java_sdk_config.properties` must be updated to contain the following information:
* Path for the `TrustStore` file and its password
* OneView credentials and host information

> Note: Instead of changing the location of your TrustStore file, you can just place it inside the directory `samples/src/main/resources`.

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

## License ##
The OneView SDK for Java is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
