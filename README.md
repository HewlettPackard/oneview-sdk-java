# HPE OneView SDK for Java

The **HPE OneView SDK for Java** enables Java developers to easily integrate their Java solutions with the HPE OneView. You can get started in a few minutes downloading the project and building it using [Maven](https://maven.apache.org/) project management tool.

The SDK provides clients for the REST API specification of each resource type in the HPE OneView resource model.

## Getting Started ##

### Minimum Requirements ###
* Java **1.7**
* Maven **3.0.5**
* OpenSSL

### Build from source ###
Once the repository has been cloned from GitHub, you can build the SDK using Maven. To build the project you must use the command:

```sh
mvn clean install
```

### Generate KeyStore and TrustStore to store SSL certificates ###
In order to enable the SDK to establish a SSL connection between the client and the server, it is necessary to generate `KeyStore` and `TrustStore` files.

Below are the steps to perform this task:

#### Fetch the authorization token from OneView using a REST client ####
Example:
```
curl -X POST -H "Content-Type:application/json" -d '{"userName":"{USERNAME}","password":"{PASSWORD}"}' \
-k https://{HOST}/rest/login-sessions
```
Replace `{USERNAME}`, `{PASSWORD}` and `{HOST}` with your OneView credentials and host information.

#### Fetch server CA certificate ####
Example:
```
openssl s_client -showcerts -host {HOST} -port 443
```
Copy the server certificate content from `-----BEGIN CERTIFICATE-----` to `-----END CERTIFICATE-----` (inclusive).
Paste the content into a file called `default-server.crt`.

#### Fetch the SSL client certificates for RabbitMQ using a REST client ####
Example:
```
curl -X GET -H "Auth:{AUTHORIZATION_TOKEN}" -H "X-Api-Version:200" -k https://{HOST}/rest/certificates/client/rabbitmq/keypair/default  
```
* Paste the content of `base64SSLCertData` into a file called `default-client.crt`
* Paste the content of `base64SSLKeyData` into a file called `default-client.key`

> Note: Remove the `\n` characters or replace them by an actual *new line*.

If you receive a response containing the message `Certificate/Private key file is missing`, you will have to perform a request to the OneView to generate the certificates.
Example:
```
curl -X POST -H "Auth:{AUTHORIZATION_TOKEN}" -H "X-Api-Version:200" -H "Content-Type:application/json" -d '{"commonName":"default","type":"RabbitMqClientCertV2"}' -k https://{HOST}/rest/certificates/client/rabbitmq
```

#### Generate `TrustStore` ####
Example:
```
keytool -import -v -trustcacerts -alias myservercert file default-server.crt -keystore TrustStore
```
> Note: Choose the option *yes* if prompted to trust the certificate.

#### Generate `KeyStore` ####
Example:
```
openssl pkcs12 -export -name myclientcert -in default-client.crt -inkey default-client.key -out myclient.p12

keytool -importkeystore -destkeystore KeyStore -srckeystore myclient.p12 -srcstoretype pkcs12 -alias myclientcert
```

### Example Programs ###
The SDK comes with several sample programs in the `samples` directory. For each of the supported resource types, there is a corresponding sample file. To run one of them, we recommend the use of an IDE ([Eclipse](https://eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/)).

> Note: If you choose to use Eclipse IDE, you will need to generate the Eclipse IDE files (`\*.classpath`, `\*.project`, `\*.wtpmodules` and the `.settings` folder). You can generate these files using Maven Eclipse Plugin with the command `mvn clean eclipse:clean eclipse:eclipse`

The file `SamplesConstants.java` must be changed to match the following information:
* Paths for both `KeyStore` and `TrustStore` files and their respective passwords
* OneView credentials and host information

> Note: Instead of changing the location of SSL files you can just place them inside the directory `samples/src/main/resources`

## License ##
The OneView SDK for Java is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
