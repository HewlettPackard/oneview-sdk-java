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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.fcsans.DeviceManagerResponse;
import com.hp.ov.sdk.rest.client.OneViewClient;

/*
 * DeviceManagerClientSample is a sample program to consume the network advisor by managing the
 * san manager  of HPE OneView. It invokes APIs of DeviceManagerClient which is in sdk library to
 * perform GET/PUT/POST/DELETE operations on san manager resource
 */
public class FcSanDeviceManagerClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSanDeviceManagerClientSample.class);

    private final FcSanDeviceManagerClient fcSanDeviceManagerClient;

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "2e1fabc7-88eb-4884-899b-a6740130d094";
    private static final String RESOURCE_NAME = "172.18.15.1";
    private static final String HOSTNAME = "Host";
    private static final String HOSTNAME_VALUE = "172.18.15.1";
    private static final String PASSWORD = "Password";
    private static final String PASSWORD_VALUE = "dcs";
    // ================================

    private FcSanDeviceManagerClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        fcSanDeviceManagerClient = oneViewClient.fcSanDeviceManager();
    }

    private void getFcSanDeviceManagerById() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getById(RESOURCE_ID);

        LOGGER.info("DeviceManagerResponse object returned to client: {}", deviceManager.toJsonString());
    }

    private void getAllFcSanDeviceManagers() {
        ResourceCollection<DeviceManagerResponse> deviceManagers = this.fcSanDeviceManagerClient.getAll();

        LOGGER.info("DeviceManagers returned to client: {}", deviceManagers.toJsonString());
    }

    private void getFcSanDeviceManagerByName() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("DeviceManagerResponse object returned to client: {}", deviceManager.toJsonString());
    }

    private void updateFcSanDeviceManager() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);

        deviceManager = updateHostConnectionDetails(deviceManager);
        deviceManager.setRefreshState(RefreshState.RefreshPending);

        TaskResource taskResource = fcSanDeviceManagerClient.update(deviceManager.getResourceId(),
                deviceManager);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void removeFcSanDeviceManager() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);
        TaskResource taskResource = this.fcSanDeviceManagerClient.remove(deviceManager.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void removeFcSanDeviceManager120_200() {
        DeviceManagerResponse deviceManager = this.fcSanDeviceManagerClient.getByName(RESOURCE_NAME).get(0);
        String response = this.fcSanDeviceManagerClient.remove_V120_200(deviceManager.getResourceId());

        LOGGER.info("String object returned to client: {}", response);
    }

    private DeviceManagerResponse updateHostConnectionDetails(DeviceManagerResponse deviceManager) {
        for (Property property : deviceManager.getConnectionInfo()) {
            if (property.getName().equalsIgnoreCase(HOSTNAME)) {
                property.setValue(HOSTNAME_VALUE);
            }
            if (property.getName().equalsIgnoreCase(PASSWORD)) {
                property.setValue(PASSWORD_VALUE);
            }
        }
        return deviceManager;
    }

    public static void main(final String[] args) throws Exception {
        FcSanDeviceManagerClientSample client = new FcSanDeviceManagerClientSample();

        client.getFcSanDeviceManagerById();
        client.getAllFcSanDeviceManagers();
        client.getFcSanDeviceManagerByName();
        client.updateFcSanDeviceManager();

        //  OneView 1.2 & 2.0
        client.removeFcSanDeviceManager120_200();

        // OneView 3.0
        client.removeFcSanDeviceManager();
    }
}
