/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.fcsans;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.storage.FcSanDeviceManagerClient;
import com.hp.ov.sdk.rest.client.storage.FcSanProviderClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * DeviceManagerClientSample is a sample program to consume the network advisor by managing the
 * san manager  of HPE OneView. It invokes APIs of DeviceManagerClient which is in sdk library to
 * perform GET/PUT/POST/DELETE operations on san manager resource
 */
public class FcSansDeviceManagerClientSample {

    private final FcSanDeviceManagerClient fcSanDeviceManagerClient;
    private final FcSanProviderClient fcSanProviderClient;

    // test values - user input
    // ================================
    private static final String PROVIDER_NAME = "Brocade Network Advisor";
    private static final String RESOURCE_ID = "841d3cdc-81c0-4644-baaa-b16b2df77547";
    private static final String RESOURCE_NAME = "172.18.15.1";
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

    private FcSansDeviceManagerClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        
        fcSanDeviceManagerClient = oneViewClient.fcSanDeviceManager();
        fcSanProviderClient = oneViewClient.fcSanProvider();
    }

    private void getFcSanDeviceManagerById() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getById(RESOURCE_ID);

        System.out.println("FcSanDeviceManagerClientSample : getFcSanDeviceManager : " +
                "DeviceManagerResponse object returned to client : " + deviceManager.toJsonString());
    }

    private void getAllFcSanDeviceManagers() {
        ResourceCollection<DeviceManagerResponse> deviceManagers = this.fcSanDeviceManagerClient.getAll();

        System.out.println("FcSanDeviceManagerClientSample : getAllFcSanDeviceManagers : " +
                "DeviceManagers returned to client : " + deviceManagers.toJsonString());
    }

    private void getFcSanDeviceManagerByName() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);

        System.out.println("FcSanDeviceManagerClientSample : getFcSanDeviceManagerByName : " +
                "DeviceManagerResponse object returned to client : " + deviceManager.toJsonString());
    }

    private void addFcSanDeviceManager() {
        SanProviderResponse sanProvider = fcSanProviderClient.getByName(PROVIDER_NAME);
        DeviceManagerResponse deviceManager = this.buildDeviceManager(sanProvider);

        TaskResourceV2 taskResource = fcSanDeviceManagerClient.add(sanProvider.getDeviceManagersUri(),
                deviceManager, false);

        System.out.println("FcSanDeviceManagerClientSample : addFcSanDeviceManager : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateFcSanDeviceManager() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);

        deviceManager = updateHostConnectionDetails(deviceManager);
        deviceManager.setRefreshState(RefreshState.RefreshPending);

        TaskResourceV2 taskResource = fcSanDeviceManagerClient.update(deviceManager.getResourceId(),
                deviceManager, false);

        System.out.println("FcSanDeviceManagerClientSample : updateFcSanDeviceManager : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void removeFcSanDeviceManager() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);
        TaskResourceV2 taskResource = this.fcSanDeviceManagerClient.remove(deviceManager.getResourceId(), false);

        System.out.println("FcSanDeviceManagerClientSample : removeFcSanDeviceManager : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private DeviceManagerResponse updateHostConnectionDetails(DeviceManagerResponse deviceManager) {
        for (Property property : deviceManager.getConnectionInfo()) {
            if (property.getName().equalsIgnoreCase("host")) {
                property.setValue(HOSTNAME_VALUE);
            }
            if (property.getName().equalsIgnoreCase("password")) {
                property.setValue(PASSWORD_VALUE);
            }
        }
        return deviceManager;
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
        FcSansDeviceManagerClientSample client = new FcSansDeviceManagerClientSample();

        client.addFcSanDeviceManager();
        client.getFcSanDeviceManagerById();
        client.getAllFcSanDeviceManagers();
        client.getFcSanDeviceManagerByName();
        client.updateFcSanDeviceManager();
        client.removeFcSanDeviceManager();
    }
}
