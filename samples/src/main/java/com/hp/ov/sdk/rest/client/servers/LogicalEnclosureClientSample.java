/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.servers;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.FirmwareUpdateOn;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.PatchFirmwareValue;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;

/*
 * LogicalEnclosureClientSample is a sample program to consume the REST API of c7000 enclosure managed
 * by HPE OneView. It invokes APIs of LogicalEnclosureClient which is in sdk library to perform GET/PUT/POST/DELETE
 * operations on logical enclosure resource
 */
public class LogicalEnclosureClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalEnclosureClientSample.class);

    private final LogicalEnclosureClient logicalEnclosureClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final EnclosureGroupClient enclosureGroupClient;

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "d5708093-b91d-42f2-8035-6c283a290adb";
    private static final String RESOURCE_NAME = "Encl1";
    private static final String RESOURCE_NAME_UPDATED = RESOURCE_NAME + "_Updated";

    private static final String FIRMWARE_NAME = "Service Pack for ProLiant";
    private static final String ENCLOSURE_ID = "09SGH100X6J1";
    // ================================

    private LogicalEnclosureClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.logicalEnclosureClient = oneViewClient.logicalEnclosure();
        this.firmwareDriverClient = oneViewClient.firmwareDriver();
        this.enclosureGroupClient = oneViewClient.enclosureGroup();
    }

    private void getLogicalEnclosureById() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getById(RESOURCE_ID);

        LOGGER.info("Logical enclosure returned to client: {}", logicalEnclosure.toJsonString());
    }

    private void getAllLogicalEnclosures() {
        ResourceCollection<LogicalEnclosure> logicalEnclosures = logicalEnclosureClient.getAll();

        LOGGER.info("Logical enclosures returned to client: {}", logicalEnclosures.toJsonString());
    }

    private void getLogicalEnclosureByName() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("Logical enclosure returned to client: {}", logicalEnclosure.toJsonString());
    }

    private void createLogicalEnclosure() {
        AddLogicalEnclosure addLogicalEnclosure = buildAddLogicalEnclosure();

        TaskResourceV2 taskResource = this.logicalEnclosureClient.create(addLogicalEnclosure, false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        logicalEnclosure.setName(RESOURCE_NAME_UPDATED);

        TaskResourceV2 taskResource = this.logicalEnclosureClient.update(logicalEnclosure.getResourceId(),
                logicalEnclosure, false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void patchLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);
        Patch patch = new Patch();

        // Enclosure patch supports the update of Name and Rack Name
        patch.setOp(PatchOperation.replace);
        patch.setPath("/firmware");
        patch.setValue(new PatchFirmwareValue(
                logicalEnclosure.getFirmware().getFirmwareBaselineUri(),
                FirmwareUpdateOn.EnclosureOnly,
                true));

        TaskResourceV2 taskResource = this.logicalEnclosureClient.patch(logicalEnclosure.getResourceId(), patch, false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void deleteLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 taskResource = this.logicalEnclosureClient.delete(logicalEnclosure.getResourceId(), false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateLogicalEnclosureFromGroup() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 taskResource = this.logicalEnclosureClient.updateFromGroup(logicalEnclosure.getResourceId(), false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateLogicalEnclosureConfiguration() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 taskResource = this.logicalEnclosureClient.updateConfiguration(
                logicalEnclosure.getResourceId(), false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getLogicalEnclosureConfigurationScript() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        String script = this.logicalEnclosureClient.getConfigurationScript(
                logicalEnclosure.getResourceId());

        LOGGER.info("Logical enclosure script returned to client: {}", script);
    }

    private void updateLogicalEnclosureConfigurationScript() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 taskResource = this.logicalEnclosureClient.updateConfigurationScript(
                logicalEnclosure.getResourceId(), "\"name=Enclosure_test_script\"", false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void createLogicalEnclosureSupportDump() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);
        SupportDump supportDump = new SupportDump("testDump01", true, false);

        TaskResourceV2 taskResource = this.logicalEnclosureClient.createSupportDump(
                logicalEnclosure.getResourceId(), supportDump, false);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
     }

    private AddLogicalEnclosure buildAddLogicalEnclosure() {
        EnclosureGroup enclosureGroup = enclosureGroupClient.getByName(
                EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0);
        AddLogicalEnclosure addLogicalEnclosure = new AddLogicalEnclosure();

        addLogicalEnclosure.setName(RESOURCE_NAME);
        addLogicalEnclosure.setEnclosureGroupUri(enclosureGroup.getUri());
        addLogicalEnclosure.setEnclosureUris(Arrays.asList("/rest/enclosures/" + ENCLOSURE_ID));
        addLogicalEnclosure.setFirmwareBaselineUri(
                firmwareDriverClient.getByName(FIRMWARE_NAME).get(0).getUri());
        addLogicalEnclosure.setForceInstallFirmware(false);

        return addLogicalEnclosure;
    }

    public static void main(final String[] args) throws Exception {
        LogicalEnclosureClientSample client = new LogicalEnclosureClientSample();

        client.getLogicalEnclosureById();
        client.getAllLogicalEnclosures();
        client.getLogicalEnclosureByName();

        client.updateLogicalEnclosureFromGroup();
        client.updateLogicalEnclosureConfiguration();

        client.getLogicalEnclosureConfigurationScript();
        client.updateLogicalEnclosureConfigurationScript();
        client.createLogicalEnclosureSupportDump();

        client.patchLogicalEnclosure();
        client.updateLogicalEnclosure();

        client.createLogicalEnclosure(); // only in 3.0
        client.deleteLogicalEnclosure(); // only in 3.0
    }
}
