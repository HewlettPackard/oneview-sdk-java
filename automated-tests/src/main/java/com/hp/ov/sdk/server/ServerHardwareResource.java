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

import java.util.Map;

import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.LicensingIntent;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.ConfigurationState;
import com.hp.ov.sdk.dto.servers.serverhardware.PhysicalServerPowerControl;
import com.hp.ov.sdk.dto.servers.serverhardware.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.servers.serverhardware.RefreshStateRequest;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerFirmwareInventory;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerPowerControlRequest;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.server.FirmwareInventoryFilter;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;

public class ServerHardwareResource extends BasicResource implements CreateResource, RemoveResource {

    private static ServerHardwareResource instance;

    private ServerHardwareClient client;

    private ServerHardwareResource() {
        client = oneViewClient.serverHardware();
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new ServerHardwareResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        ServerHardware serverHardware = (ServerHardware) getResource(client.getByName(name));
        return serverHardware == null ? "" : serverHardware.getResourceId();
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
        client.add(builder());
    }

    @Override
    public String remove(String id) {
        return objToString(client.remove(id));
    }

    public String updatePowerState(String id) {
        TaskResource task = client.updatePowerState(id, builderPowerState());
        return task.getTaskStatus();
    }

    public String updateRefreshState(String id) {
        TaskResource task = client.updateRefreshState(id, builderRefreshState());
        return task.getTaskStatus();
    }

    private RefreshStateRequest builderRefreshState() {
        RefreshStateRequest refreshStateRequest = new RefreshStateRequest();
        refreshStateRequest.setHostname(resourceProperties.get("hostname"));
        refreshStateRequest.setUsername(resourceProperties.get("username"));
        refreshStateRequest.setPassword(resourceProperties.get("username"));
        return refreshStateRequest;
    }

    public String getEnvironmentConfiguration(String id) {
        return objToString(client.getEnvironmentConfiguration(id));
    }

    public String getIloSsoUrl(String id) {
        return objToString(client.getIloSsoUrl(id));
    }

    public String getJavaRemoteConsoleUrl(String id) {
        return objToString(client.getJavaRemoteConsoleUrl(id));
    }

    public String getRemoteConsoleUrl(String id) {
        return objToString(client.getRemoteConsoleUrl(id));
    }

    public String getUtilization(String id) {
        return objToString(client.getUtilization(id));
    }

    public int getFirmwareInventoryByFilter(String resourceID) {

        FirmwareInventoryFilter filter = new FirmwareInventoryFilter();
        filter.setComponentLocation(emptyField(resourceProperties.get("componentLocation")));
        filter.setComponentName(emptyField(resourceProperties.get("componentName")));
        filter.setComponentVersion(emptyField(resourceProperties.get("componentVersion")));
        filter.setServerModel(emptyField(resourceProperties.get("serverModel")));
        filter.setServerName(emptyField(resourceProperties.get("serverName")));

        ResourceCollection<ServerFirmwareInventory> collection = client.getServerFirmwareInventoryByFilter(filter);

        return collection != null ? collection.getCount() : -1;
    }

    public String getFirmwareInventory(String id) {
        return objToString(client.getServerFirmwareInventory(id));
    }

    public String updateEnvironmentConfiguration(String id) {
        EnvironmentalConfigurationUpdate request = new EnvironmentalConfigurationUpdate();
        request.setCalibratedMaxPower(Integer.valueOf(resourceProperties.get("calibratedMaxPower")));
        return objToString(client.updateEnvironmentConfiguration(id, request));
    }

    public String updateMpFirmwareVersion(String id) {
        return objToString(client.updateMpFirmwareVersion(id));
    }

    public String patch(String id) {
        Patch patch = new Patch();
        patch.setOp(Patch.PatchOperation.valueOf(resourceProperties.get("op")));
        patch.setPath(resourceProperties.get("path"));
        patch.setValue(resourceProperties.get("value"));
        return objToString(client.patch(id, patch));
    }

    public AddServer builder() {
        AddServer server = new AddServer();
        server.setHostname(resourceProperties.get("name"));
        server.setUsername(resourceProperties.get("username"));
        server.setPassword(resourceProperties.get("username"));
        server.setLicensingIntent(LicensingIntent.valueOf(resourceProperties.get("licensingIntent")));
        server.setConfigurationState(ConfigurationState.valueOf(resourceProperties.get("configurationState")));
        server.setForce(Boolean.valueOf(resourceProperties.get("force")));
        return server;
    }

    private ServerPowerControlRequest builderPowerState() {
        ServerPowerControlRequest serverPowerControlRequest = new ServerPowerControlRequest();
        serverPowerControlRequest
                .setPowerControl(PhysicalServerPowerControl.valueOf(resourceProperties.get("powerControl")));
        serverPowerControlRequest.setPowerState(PhysicalServerPowerState.valueOf(resourceProperties.get("powerState")));
        return serverPowerControlRequest;
    }

}
