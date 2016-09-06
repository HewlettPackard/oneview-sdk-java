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

package com.hp.ov.sdk.rest.client.facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class UnmanagedDeviceClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnmanagedDeviceClientSample.class);

    // These are variables to be defined by user
    // ================================
    private static final String UNMANAGED_DEVICE_RESOURCE_ID = "9ba5afcd-804f-4a00-9492-6482d26e80d8";
    private static final String UNMANAGED_DEVICE_NAME = "Sample UnmanagedDevice";
    // ================================

    private final UnmanagedDeviceClient unmanagedDeviceClient;

    public UnmanagedDeviceClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.unmanagedDeviceClient = oneViewClient.unmanagedDevice();
    }

    private void getUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getById(UNMANAGED_DEVICE_RESOURCE_ID);

        LOGGER.info("UnmanagedDevice object returned to client : " + unmanagedDevice.toJsonString());
    }

    private void getAllUnmanagedDevices() {
        ResourceCollection<UnmanagedDevice> unmanagedDevices = this.unmanagedDeviceClient.getAll();

        LOGGER.info("UnmanagedDevices returned to client : " + unmanagedDevices.toJsonString());
    }

    private void getUnmanagedDeviceByName() {
        ResourceCollection<UnmanagedDevice> unmanagedDevices
                = this.unmanagedDeviceClient.getByName(UNMANAGED_DEVICE_NAME);

        LOGGER.info("UnmanagedDevices returned to client : " + unmanagedDevices.toJsonString());
    }

    private void addUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = new UnmanagedDevice();

        unmanagedDevice.setName(UNMANAGED_DEVICE_NAME);
        unmanagedDevice.setModel("Procurve 4200VL");
        unmanagedDevice.setDeviceType("Server");
        unmanagedDevice.setIpv4Address("192.168.0.2");
        unmanagedDevice.setMac("68:a5:99:az:71:wc");

        UnmanagedDevice addedUnmanagedDevice = this.unmanagedDeviceClient.add(unmanagedDevice);

        LOGGER.info("UnmanagedDevice object returned to client : " + addedUnmanagedDevice.toJsonString());
    }

    private void updateUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getByName(UNMANAGED_DEVICE_NAME).get(0);
        String resourceId = unmanagedDevice.getResourceId();

        unmanagedDevice.setDeviceType("Blade System");

        UnmanagedDevice updatedUnmanagedDevice = this.unmanagedDeviceClient.update(resourceId, unmanagedDevice);

        LOGGER.info("UnmanagedDevice object returned to client : " + updatedUnmanagedDevice.toJsonString());
    }

    private void removeUnmanagedDevice() {
        UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getByName(UNMANAGED_DEVICE_NAME).get(0);
        String response = this.unmanagedDeviceClient.remove(unmanagedDevice.getResourceId());

        LOGGER.info("Response returned to client : " + response);
    }

    private void removeUnmanagedDeviceByFilter() {
        String filter = "name='" + UNMANAGED_DEVICE_NAME +"'";
        TaskResourceV2 task = this.unmanagedDeviceClient.removeByFilter(filter, false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getEnvironmentalConfiguration() {
        UnmanagedDevice unmanagedDevice = this.unmanagedDeviceClient.getByName(UNMANAGED_DEVICE_NAME).get(0);
        String resourceId = unmanagedDevice.getResourceId();

        EnvironmentalConfiguration environmentalConfiguration
                = this.unmanagedDeviceClient.getEnvironmentalConfiguration(resourceId);

        LOGGER.info("EnvironmentalConfiguration object returned to client : " + environmentalConfiguration);
    }

    public static void main(String[] args) {
        UnmanagedDeviceClientSample sample = new UnmanagedDeviceClientSample();

        sample.getUnmanagedDevice();
        sample.getAllUnmanagedDevices();
        sample.addUnmanagedDevice();
        sample.updateUnmanagedDevice();
        sample.getUnmanagedDeviceByName();
        sample.getEnvironmentalConfiguration();

        sample.removeUnmanagedDevice();

        sample.addUnmanagedDevice();
        sample.removeUnmanagedDeviceByFilter();
    }

}
