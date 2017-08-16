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

package com.hp.ov.sdk.storage;

import java.util.Map;

import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.fcsans.DeviceManagerResponse;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.storage.FcSanDeviceManagerClient;

public class FcSanDeviceManagerResource extends BasicResource implements RemoveResource, UpdateResource {

    private static FcSanDeviceManagerResource instance;

    private FcSanDeviceManagerClient client;

    public FcSanDeviceManagerResource() {
        client = oneViewClient.fcSanDeviceManager();
    }

    public static FcSanDeviceManagerResource getInstance() {
        if (instance == null) {
            instance = new FcSanDeviceManagerResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        DeviceManagerResponse deviceManager = (DeviceManagerResponse) getResource(client.getByName(name));
        return deviceManager == null ? "" : deviceManager.getResourceId();
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
    public String remove(String id) {
        return taskToString(client.remove(id));
    }

    @Override
    public String update(String id) {
        return taskToString(client.update(id, builderUpdate(client.getById(id))));
    }

    public String updateSynergy(String id) {
        return taskToString(client.update(id, builderUpdateSynergy(client.getById(id))));
    }

    public DeviceManagerResponse builderUpdate(DeviceManagerResponse deviceManager) {
        for (Property property : deviceManager.getConnectionInfo()) {
            if (property.getName().equalsIgnoreCase("host")) {
                property.setValue(resourceProperties.get("name"));
            }
            if (property.getName().equalsIgnoreCase("password")) {
                property.setValue(resourceProperties.get("password"));
            }
        }
        deviceManager.setRefreshState(RefreshState.valueOf(resourceProperties.get("refreshState")));
        return deviceManager;

    }

    public DeviceManagerResponse builderUpdateSynergy(DeviceManagerResponse deviceManager) {
        for (Property property : deviceManager.getConnectionInfo()) {
            if (property.getName().equalsIgnoreCase("host")) {
                property.setValue(resourceProperties.get("name"));
            }
            if (property.getName().equalsIgnoreCase("SnmpPort")) {
                property.setValue(Integer.valueOf(resourceProperties.get("snmpPort")));
            }
            if (property.getName().equalsIgnoreCase("SnmpAuthString")) {
                property.setValue(resourceProperties.get("password"));
            }
        }
        deviceManager.setRefreshState(RefreshState.valueOf(resourceProperties.get("refreshState")));
        return deviceManager;
    }
}
