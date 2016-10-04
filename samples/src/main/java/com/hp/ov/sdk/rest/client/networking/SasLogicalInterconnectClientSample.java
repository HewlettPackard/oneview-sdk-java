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

package com.hp.ov.sdk.rest.client.networking;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.Command;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.ReplaceDriveEnclosure;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLiFirmware;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLogicalInterconnect;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClientSample;
import com.hp.ov.sdk.util.JsonPrettyPrinter;
import com.hp.ov.sdk.util.UrlUtils;

public class SasLogicalInterconnectClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalInterconnectClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String SAS_LOGICAL_INTERCONNECT_NAME
            = "test-logical-enclosure-SAS-Logical-Interconnect-Group-Sample-1";
    // ================================

    private final SasLogicalInterconnectClient client;
    private final FirmwareDriverClient fwClient;

    private SasLogicalInterconnectClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.sasLogicalInterconnect();
        this.fwClient = oneViewClient.firmwareDriver();
    }

    private void getSasLogicalInterconnectById() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        interconnect = client.getById(interconnect.getResourceId());

        LOGGER.info("SAS logical interconnect returned to client: {}", interconnect.toJsonString());
    }

    private void getAllSasLogicalInterconnects() {
        ResourceCollection<SasLogicalInterconnect> interconnects = client.getAll();

        LOGGER.info("SAS logical interconnects returned to client: {}", interconnects.toJsonString());
    }

    private void getSasLogicalInterconnectByName() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        LOGGER.info("SAS logical interconnect returned to client: {}", interconnect.toJsonString());
    }

    private void getSasLogicalInterconnectFirmware() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        SasLiFirmware firmware = client.getFirmware(interconnect.getResourceId());

        LOGGER.info("SAS LI firmware returned to client: {}", JsonPrettyPrinter.print(firmware));
    }

    private void updateSasLogicalInterconnectFirmware() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        SasLiFirmware firmware = new SasLiFirmware();

        firmware.setCommand(Command.Stage);
        firmware.setForce(false);
        firmware.setSppUri(fwClient.getByName(FirmwareDriverClientSample.FIRMWARE_DRIVER_NAME).get(0).getUri());

        TaskResource task = this.client.updateFirmware(interconnect.getResourceId(), firmware);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void replaceSasLogicalInterconnectDriveEnclosure() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        ReplaceDriveEnclosure replace = new ReplaceDriveEnclosure();

        String driveEnclosureSerialNumber = UrlUtils.getResourceIdFromUri(interconnect.getDriveEnclosureUris().get(0));

        replace.setOldSerialNumber(driveEnclosureSerialNumber);
        replace.setNewSerialNumber(driveEnclosureSerialNumber);

        TaskResource taskResource = client.replaceDriveEnclosure(interconnect.getResourceId(), replace);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void applySasLogicalInterconnectConfiguration() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        TaskResource taskResource = client.applyConfiguration(interconnect.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateSasLogicalInterconnectCompliance() {
        SasLogicalInterconnect interconnect = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME).get(0);

        TaskResource taskResource = client.updateCompliance(interconnect.getResourceId(), interconnect);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateMultipleSasLogicalInterconnectCompliance() {
        ResourceCollection<SasLogicalInterconnect> interconnects = client.getByName(SAS_LOGICAL_INTERCONNECT_NAME);
        List<String> uris = new ArrayList<>();

        for (SasLogicalInterconnect interconnect : interconnects.getMembers()) {
            uris.add(interconnect.getUri());
        }

        TaskResource taskResource = client.updateCompliance(uris);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    public static void main(String[] args) {
        SasLogicalInterconnectClientSample sample = new SasLogicalInterconnectClientSample();

        sample.getSasLogicalInterconnectById();
        sample.getSasLogicalInterconnectByName();
        sample.getAllSasLogicalInterconnects();

        sample.applySasLogicalInterconnectConfiguration();
        sample.updateSasLogicalInterconnectCompliance();
        sample.updateMultipleSasLogicalInterconnectCompliance();
        sample.replaceSasLogicalInterconnectDriveEnclosure();

        sample.getSasLogicalInterconnectFirmware();
        sample.updateSasLogicalInterconnectFirmware();
    }

}
