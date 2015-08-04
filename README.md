# oneview-sdk-java
Java SDK for HP OneView:

The Java SDK for HP OneView enables Java developers to easily build integration and scalable solutions with HP OneView. SDK provides client API's to consume OneView REST API's.

This version of SDK is built against HP OneView v1.20.

Features:

In summary, this version of SDK covers following 3 features:

1.REST API:

The most commonly used REST APIs of OneView are covered in this version of SDK. Resources like Network, Network Set, Logical Interconnect Group, Enclosure Group, Enclosure, Storage System, Storage Pool, Storage Volume Template, Storage Volumes, Server Profiles and Firmware are part of SDK. Additionally, get calls for Logical Interconnect, Uplink Sets, and Server Hardware are included .

2.Listening on SCMB messages and metrics Data:

SDK has APIs to	listen on SCMB (State Change Message Bus)  and MSMB (Metric Service Message Bus) messages and notify the consumers.

3.Sample programs:

To get started quickly, this SDK is bundled with sample programs under maven "samples" project. Users can leverage a majority of the code in sample programs to start using the SDK. The SDK includes sample programs for resources like Network, Network Set, Logical Interconnect Group, Enclosure Group, Enclosure, Storage System, Storage Pool, Storage Volume Template, Storage Volumes, Server Profiles, firmware, SCMB and MSMB modules.

Release Name:

SDK is packaged as maven project "oneview-sdk-java-lib" and delivered. The maven "samples" project contains SDK samples.

Software Requirement:

Jdk 1.7 to compile and run SDK APIs
Openssl and
maven

Steps to generate keystore and truststore:

	1. Fetch CA certificate of server
		openssl s_client -host <<IP_Address>> -port <<PortNumber>>
		Where port number is 443
		Example:
		openssl s_client –host 15.199.201.9 –port 443
		Save Server Certificate from -----BEGIN CERTIFICATE----- to -----END CERTIFICATE----- as default-server.crt
	2. Added Server Cert as follows:
		keytool -import -v -trustcacerts -alias myservercert -file <<servercert>> -keystore <<TrustStorePath>>
		Example: keytool -import -v -trustcacerts -alias myservercert –file default-server.crt -keystore TrustStore
	3. Added Client Cert as Follows:
		openssl pkcs12 -export -name myclientcert -in default-client.crt -inkey default-client.key -out myclient.p12
	4. Generate Key Store for client
		keytool -importkeystore -destkeystore <<KeyStorePath>> -srckeystore <<SrcKeyStorePath .P12 file>> -srcstoretype pkcs12 -alias myclientcert 
		Example: keytool -importkeystore –destkeystore KeyStore – srckeystore myclient.p12 -srcstoretype pkcs12 -alias myclientcert

How to create SDK library:

	1. download the git projects to your local development environment
	2. run "mvn clean install" from top level pom.xml directory
	3. Above step creates SDK library as a jar file

How to run samples:

	1. setup "samples" project as maven project. This is sub project. It has dependency has "oneview-sdk-java-lib" project.
	2. Create truststore and keystore as mentioned above. These are currently placed in src/main/resource. But user is free to change the location
	3. Edit SampleRestParam.java file to set appliance IP and credentials.
	4. Run any of the sample programs. 

Compatibility:

This version of SDK is built against HP OneView v1.20.
