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

package com.hp.ov.sdk.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.Command;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.ReplaceDriveEnclosure;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLiFirmware;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLogicalInterconnect;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.networking.SasLogicalInterconnectClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.util.URIUtils;

public class SasLogicalInterconnectResource extends BasicResource implements Resource {

    private static SasLogicalInterconnectResource instance;

    private SasLogicalInterconnectClient client;
    private FirmwareDriverClient fwClient;

    public SasLogicalInterconnectResource() {
        client = oneViewClient.sasLogicalInterconnect();
        fwClient = oneViewClient.firmwareDriver();
    }

    public static SasLogicalInterconnectResource getInstance() {
        if (instance == null) {
            instance = new SasLogicalInterconnectResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        SasLogicalInterconnect sasLogicalInterconnect = (SasLogicalInterconnect) getResource(client.getByName(name));
        return sasLogicalInterconnect == null ? "" : sasLogicalInterconnect.getResourceId();
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

    public String getFirmware(String id) {
        return objToString(client.getFirmware(id));
    }

    public String applyConfiguration(String id) {
        return taskToString(client.applyConfiguration(id));
    }

    public String updateCompliance(String id) {
        SasLogicalInterconnect sasLogicalInterconnect = client.getById(id);
        return taskToString(client.updateCompliance(id, sasLogicalInterconnect));
    }

    public String updateMultipleCompliance(String name) {
        ResourceCollection<SasLogicalInterconnect> interconnects = client.getByName(name);
        List<String> uris = new ArrayList<>();

        for (SasLogicalInterconnect interconnect : interconnects.getMembers()) {
            uris.add(interconnect.getUri());
        }

        return taskToString(client.updateCompliance(uris));
    }

    public String updateFirmware(String id) {
        SasLiFirmware firmware = new SasLiFirmware();

        firmware.setCommand(Command.Stage);
        firmware.setForce(false);
        firmware.setSppUri(fwClient.getByName(resourceProperties.get("fwName")).get(0).getUri());

        return taskToString(client.updateFirmware(id, firmware));

    }

    public String replaceDriveEnclosure(String id) {
        SasLogicalInterconnect interconnect = client.getById(id);

        ReplaceDriveEnclosure replace = new ReplaceDriveEnclosure();

        String driveEnclosureSerialNumber = URIUtils.getResourceIdFromUri(interconnect.getDriveEnclosureUris().get(0));

        replace.setOldSerialNumber(driveEnclosureSerialNumber);
        replace.setNewSerialNumber(driveEnclosureSerialNumber);

        return taskToString(client.replaceDriveEnclosure(interconnect.getResourceId(), replace));
    }

}
