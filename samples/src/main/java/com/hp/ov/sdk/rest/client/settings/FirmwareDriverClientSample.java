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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class FirmwareDriverClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String FIRMWARE_DRIVER_RESOURCE_ID = "bp-hp-service-pack-for-proliant-oneview-2014-11-30-05";
    private static final String FIRMWARE_DRIVER_NAME = "hp-firmware-a1b08f8a6b-HPGH-1_1_i386";
    // ================================

    private final FirmwareDriverClient client;

    public FirmwareDriverClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.firmwareDriver();
    }

    private void getFirmwareDriverById() {
        FwBaseline firmwareDriver = this.client.getById(FIRMWARE_DRIVER_RESOURCE_ID);

        System.out.println("FirmwareDriverClientSample : getFirmwareDriver : " +
                "FwBaseline object returned to client : " + firmwareDriver.toJsonString());
    }

    private void getAllFirmwareDrivers() {
        ResourceCollection<FwBaseline> firmwareDrivers = this.client.getAll();

        System.out.println("FirmwareDriverClientSample : getAllFirmwareDrivers : " +
                "FwBaselines returned to client : " + firmwareDrivers.toJsonString());
    }

    private void getFirmwareDriverByName() {
        FwBaseline firmwareDriver = this.client.getByName(FIRMWARE_DRIVER_NAME).get(0);

        System.out.println("FirmwareDriverClientSample : getFirmwareDriverByName : " +
                "FwBaseline object returned to client : " + firmwareDriver.toJsonString());
    }

    private void deleteFirmwareDriver() {
        FwBaseline firmwareDriver = this.client.getByName(FIRMWARE_DRIVER_NAME).get(0);
        TaskResourceV2 task = this.client.delete(firmwareDriver.getResourceId(), false);

        System.out.println("FirmwareDriverClientSample : deleteFirmwareDriver : " +
                "Task object returned to client : " + task.toJsonString());
    }

    public static void main(String[] args) {
        FirmwareDriverClientSample sample = new FirmwareDriverClientSample();

        sample.getFirmwareDriverById();
        sample.getAllFirmwareDrivers();
        sample.getFirmwareDriverByName();
        sample.deleteFirmwareDriver();
    }

}
