/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.firmware.FwBaseline;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.FirmwareUpdateOn;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.PatchFirmwareValue;
import com.hp.ov.sdk.dto.servers.logicalenclosure.SupportDump;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.LogicalEnclosureClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;

public class LogicalEnclosureResource extends BasicResource implements CreateResource, UpdateResource, RemoveResource {

    private static LogicalEnclosureResource instance;

    private LogicalEnclosureClient client;

    private EnclosureClient enclosureClient;
    private EnclosureGroupClient enclosureGroupClient;
    private FirmwareDriverClient firmwareDriverClient;

    public LogicalEnclosureResource() {
        client = oneViewClient.logicalEnclosure();
        enclosureClient = oneViewClient.enclosure();
        enclosureGroupClient = oneViewClient.enclosureGroup();
        firmwareDriverClient = oneViewClient.firmwareDriver();
    }

    public static LogicalEnclosureResource getInstance() {
        if (instance == null) {
            instance = new LogicalEnclosureResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        LogicalEnclosure logicalEnclosure = (LogicalEnclosure) getResource(client.getByName(name));
        return logicalEnclosure == null ? "" : logicalEnclosure.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        client.create(builder(), TaskTimeout.of(5400000));
    }

    public String createMultipleLogicalEnclosure(String id) {
        client.create(buildMultipleAddLogicalEnclosure(), TaskTimeout.of(5400000));
        return id;
    }

    public String createOneLogicalEnclosure(String id) {
        client.create(buildOneAddLogicalEnclosure(), TaskTimeout.of(5400000));
        return id;
    }

    @Override
    public AddLogicalEnclosure builder() {
        EnclosureGroup enclosureGroup = (EnclosureGroup) getResource(
                enclosureGroupClient.getByName(resourceProperties.get("enclosureGroup")));
        Enclosure enclosure = (Enclosure) getResource(enclosureClient.getByName(resourceProperties.get("enclosure")));
        FwBaseline firmware = (FwBaseline) getResource(
                firmwareDriverClient.getByName(resourceProperties.get("firmware")));

        AddLogicalEnclosure addLogicalEnclosure = new AddLogicalEnclosure();
        addLogicalEnclosure.setName(resourceProperties.get("name"));
        addLogicalEnclosure.setEnclosureGroupUri(enclosureGroup.getUri());
        addLogicalEnclosure.setEnclosureUris(Arrays.asList(enclosure.getUri()));
        addLogicalEnclosure.setFirmwareBaselineUri(firmware.getUri());
        addLogicalEnclosure.setForceInstallFirmware(false);

        return addLogicalEnclosure;
    }

    public AddLogicalEnclosure buildMultipleAddLogicalEnclosure() {
        EnclosureGroup enclosureGroup = (EnclosureGroup) getResource(
                enclosureGroupClient.getByName(resourceProperties.get("enclosureGroup")));

        String enclosureUri1 = enclosureClient.getByName(resourceProperties.get("enclosureURI1")).get(0).getUri();
        String enclosureUri2 = enclosureClient.getByName(resourceProperties.get("enclosureURI2")).get(0).getUri();
        String enclosureUri3 = enclosureClient.getByName(resourceProperties.get("enclosureURI3")).get(0).getUri();

        FwBaseline firmware = (FwBaseline) getResource(
                firmwareDriverClient.getByName(resourceProperties.get("firmware")));

        AddLogicalEnclosure addLogicalEnclosure = new AddLogicalEnclosure();
        addLogicalEnclosure.setName(resourceProperties.get("name"));
        addLogicalEnclosure.setEnclosureGroupUri(enclosureGroup.getUri());

        List<String> enclosureUris = new ArrayList<String>();

        enclosureUris.add(enclosureUri1);
        enclosureUris.add(enclosureUri2);
        enclosureUris.add(enclosureUri3);

        addLogicalEnclosure.setEnclosureUris(enclosureUris);
        addLogicalEnclosure.setFirmwareBaselineUri(firmware.getUri());
        addLogicalEnclosure.setForceInstallFirmware(false);

        return addLogicalEnclosure;
    }

    public AddLogicalEnclosure buildOneAddLogicalEnclosure() {
        EnclosureGroup enclosureGroup = (EnclosureGroup) getResource(
                enclosureGroupClient.getByName(resourceProperties.get("enclosureGroup")));

        String enclosureUri1 = enclosureClient.getByName(resourceProperties.get("enclosureURI1")).get(0).getUri();

        FwBaseline firmware = (FwBaseline) getResource(
                firmwareDriverClient.getByName(resourceProperties.get("firmware")));

        AddLogicalEnclosure addLogicalEnclosure = new AddLogicalEnclosure();
        addLogicalEnclosure.setName(resourceProperties.get("name"));
        addLogicalEnclosure.setEnclosureGroupUri(enclosureGroup.getUri());

        List<String> enclosureUris = new ArrayList<String>();

        enclosureUris.add(enclosureUri1);

        addLogicalEnclosure.setEnclosureUris(enclosureUris);
        addLogicalEnclosure.setFirmwareBaselineUri(firmware.getUri());
        addLogicalEnclosure.setForceInstallFirmware(false);

        return addLogicalEnclosure;
    }

    public String createSupportDump(String id) {
        SupportDump supportDump = new SupportDump(resourceProperties.get("supportDump"), true, false);
        return taskToString(client.createSupportDump(id, supportDump, TaskTimeout.of(700000)));
    }

    public String updateLogicalEnclosureFromGroup(String id) {
        return taskToString(client.updateFromGroup(id));
    }

    public String updateLogicalEnclosureConfiguration(String id) {
        return taskToString(client.updateConfiguration(id, TaskTimeout.of(700000)));
    }

    public String getLogicalEnclosureConfigurationScript(String id) {
        return objToString(client.getConfigurationScript(id));
    }

    public String updateLogicalEnclosureConfigurationScript(String id) {
        String script = resourceProperties.get("script");
        return objToString(client.updateConfigurationScript(id, script, TaskTimeout.of(3600000)));
    }

    public String patch(String id) {
        LogicalEnclosure logicalEnclosure = client.getById(id);
        Patch patch = new Patch();
        patch.setOp(PatchOperation.valueOf(resourceProperties.get("op")));
        patch.setPath(resourceProperties.get("path"));
        patch.setValue(new PatchFirmwareValue(logicalEnclosure.getFirmware().getFirmwareBaselineUri(),
                FirmwareUpdateOn.valueOf(resourceProperties.get("value")), true));

        return taskToString(client.patch(id, patch, TaskTimeout.of(3600000)));
    }

    @Override
    public String update(String id) {
        LogicalEnclosure logicalEnclosure = client.getById(id);
        logicalEnclosure.setName(resourceProperties.get("name"));
        return taskToString(client.update(id, logicalEnclosure, TaskTimeout.of(3600000)));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id, TaskTimeout.of(5400000)));
    }

}
