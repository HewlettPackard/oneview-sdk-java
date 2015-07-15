# oneview-sdk-java
Java SDK for HP OneView:

The Java SDK for HP OneView enables Java developers to easily build integration and scalable solutions with HP OneView. SDK provides client API's to consume OneView REST API's.

This version of SDK is built against HP OneView v1.20.

Features:

In summary, this version of SDK covers following 3 features:

1.REST API:

The most commonly used REST APIs are covered in this version of SDK. The resources like Network, Network Set, Logical Interconnect Group, Enclosure Group, Enclosure, Storage System, Storage 	Pool, Storage Volume Template, Storage Volumes, Server Profiles and firmware are part of SDK. Also, get calls for Logical Interconnect, Uplink Sets, and Server Hardware are included as well.
Please refer Appendix section to get the complete of lists APIs and non supported APIs.

2.Listening on SCMB messages and metrics Data:

SDK has APIs to	listen on SCMB and MSMB messages and notify the consumers.

3.Sample programs:

To quickly get started, SDK is bundled with sample programs. User can leverage the most of code in sample programs to start using it. SDK includes sample programs for resources like Network, Network Set, Logical Interconnect Group, Enclosure Group, Enclosure, Storage System, Storage Pool, Storage Volume Template, Storage Volumes, Server Profiles, firmware, SCMB and MSMB modules.

Release Name:

ov-sdk-app_v0.2_2015-07-13.zip. It is packaged as maven project and delivered.

Software Requirement:

Eclipse
Jdk 1.7 or above
Openssl

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

How to use:

	1. Import SDK maven project into eclipse IDE
	2. Place the Keystore and TrustStore generated under src/main/resource folder
	3. Update HPOneView Credentials located at com.hp.ov.sdk.rest.login.SampleRestParams object
	4. From src/main/test folder, run any of the test cases.

Compatibility:

This version of SDK works with OneView v1.20. If user prefers to try with 1.1 or 2.0 rev, it is required to update the DTOs before start using it.

Appendix:

Implemented APIs for each Resource
	 
	 ConnectionTemplate
  		getConnectionTemplate
  		updateConnectionTemplate
  		getConnectionTemplateByName
	 Networks
  		createNetwork
  		createNetworkInBulk
  		deleteNetwork
  		getNetwork
  		getAllNetworks
  		getNetworkByName
  		updateNetwork
	 FcNetwork
  		getFcNetwork
  		getAllFcNetworks
  		getFcNetworkByFilter
  		getFcNetworkByName
  		createFcNetwork
  		updateFcNetwork
  		deleteFcNetwork
	 Logical Interconnect Group
  		getLogicalInterconnectGroup
  		getAllLogicalInterconnectGroups
  		getLogicalInterconnectGroupByName
  		createLogicalInterconnectGroup
  		updateLogicalInterconnectGroup
  		deleteLogicalInterconnectGroup
	 EnclosureGroups
  		getEnclosureGroup
  		getAllEnclosureGroups
  		getEnclosureGroupByName
  		createEnclosureGroup
  		updateEnclosureGroup
  		deleteEnclosureGroup
	 Enclosure
  		getEnclosure
  		getAllEnclosures
  		getEnclosureByName
  		createEnclosure
  		updateEnclosure
  		deleteEnclosure
	 StorageSystem
  		getStorageSystem
  		getStoragePoolsForStorageSystem
  		getAllManagedPortsForStorageSystem
  		getManagedPortsForStorageSystem
  		getAllStorageSystems
  		getStorageSystemByName
  		createStorageSystem
  		updateStorageSystem
  		deleteStorageSystem
	 StoragePool
  		getStoragePool
  		getAllStoragePools
  		getStoragePoolByName
  		createStoragePool
  		updateStoragePool
  		deleteStoragePool
	 StorageVolumeTemplate
  		getStorageVolumeTemplate
  		getAllStorageVolumeTemplates
  		getStorageVolumeTemplateByName
  		createStorageVolumeTemplate
  		updateStorageVolumeTemplate
  		deleteStorageVolumeTemplate
	 StorageVolumes
  		getStorageVolume
  		getAllStorageVolumes
  		getStorageVolumeByName
  		createStorageVolume
  		updateStorageVolume
  		deleteStorageVolume
	 ServerProfile
  		getServerProfile
  		getAllServerProfile
  		getServerProfileByName
  		getAvailableNetworksForServerProfile
  		getAvailableServersForServerProfile
  		getProfilePortsForServerProfile
  		createServerProfile
  		updateServerProfile
  		deleteServerProfile
  		deleteServerProfileByFilter
  		copyServerProfile
	 Firmware
  		getFirmwareDriver
  		getAllFirmwareDrivers
  		getFirmwareDriverByName
	 InterconnectType
  		getInterconnectType
  		getAllInterconnectType
  		getInterconnectTypeByName
	 LogicalInterconnects
  		getLogicalInterconnectByName
  		getLogicalInterconnect
  		getAllLogicalInterconnects
  		updateLogicalInterconnectSnmpConfigurationById
  		updateLogicalInterconnectComplianceById
  		updateLogicalInterconnectFirmwareById
	 UplinkSets
  		getUplinkSet
  		getAllUplinkSet
  		deleteUplinkSet
  		updateUplinkSet
	 ServerHardware
  		getServerHardware
  		getAllServerHardwares
  		getServerHardwareWithNoProfile
  		getServerHardwareByName
  		updatePowerServer
  		getPowerState
  		
Not Yet Implemented

	 ConnectionTemplate
  		getAllConnectionTemplates
  		getDefaultConnectionTemplateForConnectionTemplate
	 Logical Interconnect Group
  		getDefaultInterconnectSettings
  		getInterconnectSettings
	 EnclosureGroups
  		getConfigurationScript
  		updateConfigurationScript
	 Enclosure
  		getActiveOaSsoUrl
  		updateCompliance
  		updateConfiguration
  		updateEnclosureFwBaseline
  		getEnvironmentalConfiguration
  		updateEnvironmentalConfiguration
  		updateRefreshState
  		getScript
  		updateScriptgetStandbyOaSsoUrl
  		getUtilization
	 StorageVolumeTemplate
		  getConnectableVolumeTemplates
	 StorageVolumes
		  getAttachableVolumes
	 Switches
  		getAllSwitches
  		createSwitch
  		getSwitch
  		refreshSwitch
  		updateSwitch
  		deleteSwitch
		  getSwitchEnvironmentConfiguration
	 ServerProfile
  		getAvailableStorageSystemForServerProfile
  		getAvailableStorageSystemsForServerProfile
  		getAvailableTargetsForServerProfile
  		getMessagesForServerProfile
	 Firmware
		  deleteFirmwareDriver
	 LogicalInterconnects
  		getLogicalInterconnectFirmwareById
  		getLogicalInterconnectForwardingInformationBase
  		createLogicalInterconnectForwardingInformationBase
  		getLogicalInterconnectForwardingInformationBaseDump
  		getLogicalInterconnectSnmpConfigurationById
  		createLogicalInterconnectSupportDump
  		getLogicalInterconnectUnassignedUplinkPortsForPortMonitor
  		updateLogicalInterconnectConfiguration
  		getLogicalInterconnectPortMonitorConfiguration
  		updateLogicalInterconnectPortMonitorConfiguration
  		getLogicalInterconnectTelementaryConfiguration
  		updateLogicalInterconnectTelementaryConfiguration
	 UplinkSets
		  createUplinkSet
	 ServerHardware
  		addServerHardware
  		getBiosForServerHardware
  		getEnvironmentConfigurationForServerHardware
  		updateEnvironmentConfigurationForServerHardware
  		getIloSsoUrlForServerHardware
  		getJavaRemoteConsoleUrlForServerHardware
  		updateMpFirmwareVersionForServerHardware
  		updateRefreshStateForServerHardware
  		getRemoteConsoleUrlForServerHardware
  		getUtilizationForServerHardware


