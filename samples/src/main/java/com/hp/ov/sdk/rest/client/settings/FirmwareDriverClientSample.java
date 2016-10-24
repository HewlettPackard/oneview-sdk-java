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

package com.hp.ov.sdk.rest.client.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.firmware.FwBaseline;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FirmwareDriverClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmwareDriverClientSample.class);

    // These are variables to be defined by user
    // ================================
    public static final String FIRMWARE_DRIVER_NAME = "Service Pack for ProLiant";

    private static final String FIRMWARE_DRIVER_RESOURCE_ID = "SPPgen9snap6_2016_0405_87";
    // ================================

    private final FirmwareDriverClient client;

    public FirmwareDriverClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.client = oneViewClient.firmwareDriver();
    }

    private void getFirmwareDriverById() {
        FwBaseline firmwareDriver = this.client.getById(FIRMWARE_DRIVER_RESOURCE_ID);

        LOGGER.info("Firmware driver returned to client: {}", firmwareDriver.toJsonString());
    }

    private void getAllFirmwareDrivers() {
        ResourceCollection<FwBaseline> firmwareDrivers = this.client.getAll();

        LOGGER.info("Firmware drivers returned to client: {}", firmwareDrivers.toJsonString());
    }

    private void getFirmwareDriverByName() {
        FwBaseline firmwareDriver = this.client.getByName(FIRMWARE_DRIVER_NAME).get(0);

        LOGGER.info("Firmware driver returned to client: {}", firmwareDriver.toJsonString());
    }

    private void deleteFirmwareDriver() {
        FwBaseline firmwareDriver = this.client.getByName(FIRMWARE_DRIVER_NAME).get(0);
        TaskResource task = this.client.delete(firmwareDriver.getResourceId());

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    public static void main(String[] args) {
        FirmwareDriverClientSample sample = new FirmwareDriverClientSample();

        sample.getFirmwareDriverById();
        sample.getAllFirmwareDrivers();
        sample.getFirmwareDriverByName();
        sample.deleteFirmwareDriver();
    }

}
