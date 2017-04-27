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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.FirmwareUpdateOn;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.PatchFirmwareValue;
import com.hp.ov.sdk.dto.servers.logicalenclosure.SupportDump;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;

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
    private final EnclosureClient enclosureClient;

    // test values - user input
    // ================================
    private static final String RESOURCE_NAME = "Encl1";
    private static final String RESOURCE_NAME_UPDATED = RESOURCE_NAME + "_Updated";

    private static final String FIRMWARE_NAME = "Service Pack for ProLiant";
    private static final String ENCLOSURE_ID = "09SGH100X6J1";
    // ================================

    private LogicalEnclosureClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.logicalEnclosureClient = oneViewClient.logicalEnclosure();
        this.firmwareDriverClient = oneViewClient.firmwareDriver();
        this.enclosureGroupClient = oneViewClient.enclosureGroup();
        this.enclosureClient = oneViewClient.enclosure();
    }

    private void getLogicalEnclosureById() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        logicalEnclosure = logicalEnclosureClient.getById(logicalEnclosure.getResourceId());

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

        TaskResource taskResource = this.logicalEnclosureClient.create(addLogicalEnclosure,
                TaskTimeout.of(300000));

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }
    
    private void createMultipleLogicalEnclosure() {
        AddLogicalEnclosure addMultipleLogicalEnclosure = buildMultipleAddLogicalEnclosure();

        TaskResource taskResource = this.logicalEnclosureClient.create(addMultipleLogicalEnclosure);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        logicalEnclosure.setName(RESOURCE_NAME_UPDATED);

        TaskResource taskResource = this.logicalEnclosureClient.update(logicalEnclosure.getResourceId(),
                logicalEnclosure, TaskTimeout.of(300000));

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

        TaskResource taskResource = this.logicalEnclosureClient.patch(logicalEnclosure.getResourceId(), patch,
                TaskTimeout.of(300000));

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void deleteLogicalEnclosure() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.logicalEnclosureClient.delete(logicalEnclosure.getResourceId(),
                TaskTimeout.of(300000));

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateLogicalEnclosureFromGroup() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.logicalEnclosureClient.updateFromGroup(logicalEnclosure.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateLogicalEnclosureConfiguration() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);

        TaskResource taskResource = this.logicalEnclosureClient.updateConfiguration(
                logicalEnclosure.getResourceId(), TaskTimeout.of(300000));

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

        TaskResource taskResource = this.logicalEnclosureClient.updateConfigurationScript(
                logicalEnclosure.getResourceId(), "\"name=Enclosure_test_script\"", TaskTimeout.of(300000));

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void createLogicalEnclosureSupportDump() {
        LogicalEnclosure logicalEnclosure = logicalEnclosureClient.getByName(RESOURCE_NAME).get(0);
        SupportDump supportDump = new SupportDump("testDump01", true, false);

        TaskResource taskResource = this.logicalEnclosureClient.createSupportDump(
                logicalEnclosure.getResourceId(), supportDump, TaskTimeout.of(300000));

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
     }

    private AddLogicalEnclosure buildAddLogicalEnclosure() {
        String enclosureGroupUri = enclosureGroupClient.getByName(
                EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();
        String enclosureUri = enclosureClient.getByName(EnclosureClientSample.RESOURCE_NAME).get(0).getUri();
        String firmwareDriverUri = firmwareDriverClient.getByName(FIRMWARE_NAME).get(0).getUri();

        AddLogicalEnclosure addLogicalEnclosure = new AddLogicalEnclosure();

        addLogicalEnclosure.setName(RESOURCE_NAME);
        addLogicalEnclosure.setEnclosureGroupUri(enclosureGroupUri);
        addLogicalEnclosure.setEnclosureUris(Arrays.asList(enclosureUri));
        addLogicalEnclosure.setFirmwareBaselineUri(firmwareDriverUri);
        addLogicalEnclosure.setForceInstallFirmware(false);

        return addLogicalEnclosure;
    }

    private AddLogicalEnclosure buildMultipleAddLogicalEnclosure() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();
        
        String enclosureURI1 = enclosureClient.getByName("0000A66101").get(0).getUri();
        String enclosureURI2 = enclosureClient.getByName("0000A66102").get(0).getUri();
        String enclosureURI3 = enclosureClient.getByName("0000A66103").get(0).getUri();
        
        String firmwareDriverUri = firmwareDriverClient.getByName(FIRMWARE_NAME).get(0).getUri();

        AddLogicalEnclosure addLogicalEnclosure = new AddLogicalEnclosure();

        addLogicalEnclosure.setName(RESOURCE_NAME);
        addLogicalEnclosure.setEnclosureGroupUri(enclosureGroupUri);
        addLogicalEnclosure.setFirmwareBaselineUri(firmwareDriverUri);
        addLogicalEnclosure.setForceInstallFirmware(false);
        
        List<String> enclosureUris = new ArrayList<String>();
        enclosureUris.add(enclosureURI1);
        enclosureUris.add(enclosureURI2);
        enclosureUris.add(enclosureURI3);
        
        addLogicalEnclosure.setEnclosureUris(enclosureUris);

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

        client.createLogicalEnclosure(); // only in 3.0 with Synergy
        client.createMultipleLogicalEnclosure(); // only in 3.0 with Synergy
        client.deleteLogicalEnclosure(); // only in 3.0 with Synergy
    }
}
