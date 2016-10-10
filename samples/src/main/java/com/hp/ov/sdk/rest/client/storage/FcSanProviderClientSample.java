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
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.TaskResource;
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
    private static final String RESOURCE_ID = "0aa1f4e1-3b5e-4233-af1a-f849dc64da69";
    private static final String RESOURCE_NAME = "Brocade San Plugin";
    private static final String HOSTNAME = "Host";
    private static final String HOSTNAME_VALUE = "172.18.15.1";
    private static final String USERNAME = "Username";
    private static final String USERNAME_VALUE = "dcs";
    private static final String PASSWORD = "Password";
    private static final String PASSWORD_VALUE = "dcs";
    private static final String PORT = "Port";
    private static final String USE_SSL = "UseSsl";
    private static final String USE_SSL_VALUE = "true";
    // ================================

    private FcSanProviderClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

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

    private DeviceManagerResponse buildDeviceManager(SanProviderResponse sanProviderResponse) {
        DeviceManagerResponse deviceManagerResponseDto = new DeviceManagerResponse();
        List<Property> connectionInfo = new ArrayList<>();
        String portValue = "";
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

    public static void main(final String[] args) throws Exception {
        FcSanProviderClientSample client = new FcSanProviderClientSample();

        client.addFcSanManager();

        client.getFcSanProviderById();
        client.getAllFcSanProviders();
        client.getFcSanProviderByName();
    }
}
