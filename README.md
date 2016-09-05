# HPE OneView SDK for Java

The **HPE OneView SDK for Java** enables Java developers to easily integrate their Java solutions with the HPE OneView. You can get started in minutes downloading, and building the project using [Maven](https://maven.apache.org/) project management tool.

The SDK provides clients for the REST API specification of each resource type in the HPE OneView resource model.

Javadocs are available at [http://hewlettpackard.github.io/oneview-sdk-java/](http://hewlettpackard.github.io/oneview-sdk-java/)

## Getting Started ##

### Minimum requirements ###
* Java **1.7**
* Maven **3.0.5**
* OpenSSL

### Build from source ###
Once the repository has been cloned from GitHub, you can build the SDK using Maven. To build the project you must use the command:

```sh
mvn clean install
```

### Generate TrustStore to store SSL server certificate ###
To enable the SDK to establish a SSL connection between the client and the server, it is necessary to generate a `TrustStore` file containing the credentials of the OneView server.

Below are the steps to perform this task:

#### Fetch the authorization token from OneView using a REST client ####
Example:
```sh
curl -X POST -H "X-Api-Version:{VERSION}" -H "Content-Type:application/json" \
-d '{"userName":"{USERNAME}","password":"{PASSWORD}"}' \
-k https://{HOST}/rest/login-sessions
```
Replace `{VERSION}`, `{USERNAME}`, `{PASSWORD}` and `{HOST}` respectively by the API version, OneView credentials and host information.

#### Fetch server CA certificate ####
Example:
```sh
openssl s_client -showcerts -host {HOST} -port 443
```
Copy the server certificate content from `-----BEGIN CERTIFICATE-----` to `-----END CERTIFICATE-----` (inclusive).
Paste the content into a file called `default-server.crt`.

#### Generate `TrustStore` ####
Example:
```sh
keytool -import -v -trustcacerts -alias myservercert -file default-server.crt -keystore TrustStore
```
> Note: Choose the *yes* option, when prompted to trust the certificate.

### Example programs ###
The SDK comes with several sample programs inside the `samples` module. For each of the supported resource types, there is a corresponding sample file. To run one of them, we recommend the use of an IDE ([Eclipse](https://eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/)).

> Note: If you choose to use Eclipse IDE, you will need to generate the Eclipse IDE files (`\*.classpath`, `\*.project`, `\*.wtpmodules` and the `.settings` folder). You can generate these files using Maven Eclipse Plugin with the command `mvn clean eclipse:clean eclipse:eclipse`.

The file `SamplesConstants.java` must be updated to contain the following information:
* Path for the `TrustStore` file and its password
* OneView credentials and host information

> Note: Instead of changing the location of your SSL file you can just place it inside the directory `samples/src/main/resources`.

#### Message Bus samples ####
Before running the Message Bus samples, it is necessary to execute a HTTP POST request to the OneView server in order to generate the RabbitMQ certificate files.
Below is an example that ilustrates how you can perform this:
```sh
curl -X POST -H "Auth:{AUTHORIZATION_TOKEN}" -H "X-Api-Version:{VERSION}" -H "Content-Type:application/json" \
-d '{"commonName":"default","type":"RabbitMqClientCertV2"}' -k https://{HOST}/rest/certificates/client/rabbitmq
```

## SDK Logging Configuration ##
The OneView SDK for Java uses the Simple Logging Facade for Java (SLF4J) for logging. The SLF4J serves as a simple facade or abstraction for various logging frameworks, such as `java.util.logging`, `logback` and `log4j`. SLF4J allows the end-user to plug in the desired logging framework at deployment time.

We highly recommend that you read the [SLF4J user manual](http://www.slf4j.org/manual.html) to understand how you can use one of the "SLF4J bindings" to configure the SDK logs.

For example, the `samples` module uses the `log4j` as the underlying logging framework. To do this, we simply declare `org.slf4j:slf4j-log4j12` as a dependency in the pom.xml file as shown below.
```xml
<dependency>
  <groupId>org.slf4j</groupId>
  <artifactId>slf4j-log4j12</artifactId>
</dependency>
```

## License ##
The OneView SDK for Java is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
