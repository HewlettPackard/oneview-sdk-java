/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hp.ov.sdk.rest.client.storage;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.fcsans.DeviceManagerResponse;
import com.hp.ov.sdk.dto.fcsans.SanProviderResponse;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * FcSanProviderClientSample is a sample program to consume the network advisor by managing the
 * san providers of HPE OneView. It invokes APIs of FcSanProviderClient which is in sdk library to
 * perform GET/PUT/POST/DELETE operations on SAN provider resource
 */
public class FcSanProviderClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSanProviderClientSample.class);

    private final FcSanProviderClient fcSanProviderClient;

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "848c191d-c995-4cd5-a7ba-e627435dd5f2";
    private static final String RESOURCE_NAME = "Cisco";
    private static final String HOSTNAME = "Host";
    private static final String HOSTNAME_VALUE = "172.18.20.1";
    private static final String USERNAME = "Username";
    private static final String USERNAME_VALUE = "dcs";
    private static final String PASSWORD = "Password";
    private static final String PASSWORD_VALUE = "dcs";
    private static final String PORT = "Port";
    private static final String USE_SSL = "UseSsl";
    private static final String USE_SSL_VALUE = "true";
    // ================================
    
    // ========== Synergy values - Cisco ============ //
    private static final String SNMPUSERNAME = "SnmpUserName";
    private static final String SNMPUSERNAME_VALUE = "dcs-SHA";
    private static final String SNMPAUTHLEVEL = "SnmpAuthLevel";
    private static final String SNMPAUTHLEVEL_VALUE = "AUTHNOPRIV";
    private static final String SNMPAUTHPROTOCOL = "SnmpAuthProtocol";
    private static final String SNMPAUTHPROTOCOL_VALUE = "SHA";
    private static final String SNMPAUTHSTRING = "SnmpAuthString";
    private static final String SNMPAUTHSTRING_PASSWORD = "hpinvent!";
    private static final String SNMPPORT = "SnmpPort";
    private static final Integer SNMP_PORT_VALUE = 161;
    // ================================
    
    private FcSanProviderClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        fcSanProviderClient = oneViewClient.fcSanProvider();
    }

    private void getFcSanProviderById() {
        SanProviderResponse provider = this.fcSanProviderClient.getById(RESOURCE_ID);

        LOGGER.info("SanProviderResponse object returned to client: {}", provider.toJsonString());
    }

    private void getAllFcSanProviders() {
        ResourceCollection<SanProviderResponse> providers = this.fcSanProviderClient.getAll();

        LOGGER.info("SAN Providers returned to client: {}", providers.toJsonString());
    }

    private void getFcSanProviderByName() {
        SanProviderResponse provider = this.fcSanProviderClient.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("SanProviderResponse object returned to client: {}", provider.toJsonString());
    }

    private void addFcSanManager() {
        SanProviderResponse provider = this.fcSanProviderClient.getByName(RESOURCE_NAME).get(0);

        DeviceManagerResponse deviceManager = this.buildDeviceManager(provider);
        TaskResource taskResource = this.fcSanProviderClient.addSanManager(provider.getResourceId(), deviceManager);

        LOGGER.info("Task object returned to the client: {}", taskResource.toJsonString());
    }

    private void addFcSanManagerSynergy() {
        SanProviderResponse provider = this.fcSanProviderClient.getById(RESOURCE_ID);
  
        DeviceManagerResponse deviceManagerSynergy = this.buildDeviceManagerSynergy(provider);
        TaskResource taskResource = this.fcSanProviderClient.addSanManager(provider.getResourceId(), deviceManagerSynergy);
  
        LOGGER.info("Task object returned to the client: {}", taskResource.toJsonString());
    }

    private DeviceManagerResponse buildDeviceManager(SanProviderResponse sanProviderResponse) {
        DeviceManagerResponse deviceManagerResponseDto = new DeviceManagerResponse();
        List<Property> connectionInfo = new ArrayList<>();
        Object portValue = "";
        for (Property property : sanProviderResponse.getDefaultConnectionInfo()) {
            if (property.getDisplayName().contains("Port")) {
                portValue = property.getValue();
            }
        }

        Property host = new Property();
        host.setName(HOSTNAME);
        host.setValue(HOSTNAME_VALUE);

        Property port = new Property();
        port.setName(PORT);
        port.setValue(portValue);

        Property user = new Property();
        user.setName(USERNAME);
        user.setValue(USERNAME_VALUE);

        Property password = new Property();
        password.setName(PASSWORD);
        password.setValue(PASSWORD_VALUE);

        Property useSsl = new Property();
        useSsl.setName(USE_SSL);
        useSsl.setValue(USE_SSL_VALUE);

        connectionInfo.add(host);
        connectionInfo.add(port);
        connectionInfo.add(user);
        connectionInfo.add(password);
        connectionInfo.add(useSsl);

        deviceManagerResponseDto.setConnectionInfo(connectionInfo);

        return deviceManagerResponseDto;
    }
    
    private DeviceManagerResponse buildDeviceManagerSynergy(SanProviderResponse sanProviderResponse) {
        DeviceManagerResponse deviceManagerResponseDtoSynergy = new DeviceManagerResponse();
        List<Property> connectionInfo = new ArrayList<>();
  
        Property host = new Property();
        host.setName(HOSTNAME);
        host.setValue(HOSTNAME_VALUE);
  
        Property snmpPort = new Property();
        snmpPort.setName(SNMPPORT);
        snmpPort.setValue(SNMP_PORT_VALUE);
  
        Property user = new Property();
        user.setName(SNMPUSERNAME);
        user.setValue(SNMPUSERNAME_VALUE);
  
        Property authLevel = new Property();
        authLevel.setName(SNMPAUTHLEVEL);
        authLevel.setValue(SNMPAUTHLEVEL_VALUE);
  
        Property authProtocol = new Property();
        authProtocol.setName(SNMPAUTHPROTOCOL);
        authProtocol.setValue(SNMPAUTHPROTOCOL_VALUE);
  
        Property password = new Property();
        password.setName(SNMPAUTHSTRING);
        password.setValue(SNMPAUTHSTRING_PASSWORD);
  
        connectionInfo.add(host);
        connectionInfo.add(snmpPort);
        connectionInfo.add(user);
        connectionInfo.add(authLevel);
        connectionInfo.add(authProtocol);
        connectionInfo.add(password);
  
        deviceManagerResponseDtoSynergy.setConnectionInfo(connectionInfo);
  
        System.out.println(deviceManagerResponseDtoSynergy.toJsonString());
        return deviceManagerResponseDtoSynergy;
    }

    public static void main(final String[] args) throws Exception {
        FcSanProviderClientSample client = new FcSanProviderClientSample();

        client.addFcSanManagerSynergy();
        client.addFcSanManager();
        
        client.getAllFcSanProviders();
        client.getFcSanProviderById();
        client.getFcSanProviderByName();
        
    }
}
