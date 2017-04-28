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

import com.hp.ov.sdk.dto.networking.ConnectionProperty;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueFormat;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueType;
import com.hp.ov.sdk.dto.networking.switches.Switch;
import com.hp.ov.sdk.dto.networking.switches.SwitchManagementConnection;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.SwitchClient;

public class SwitchesResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static SwitchesResource instance;

    private SwitchClient client;

    private SwitchesResource() {
        client = oneViewClient.switches();
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new SwitchesResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Switch switches = (Switch) getResource(client.getByName(name));
        return switches == null ? "" : switches.getResourceId();
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

    @Override
    public String update(String id) {
        Switch switchObj = client.getById(id);
        return taskToString(client.update(id, switchObj));
    }

    public String getEnvironmentalConfiguration(String id) {
        return objToString(client.getEnvironmentalConfiguration(id));
    }

    public String refresh(String id) {
        return taskToString(client.refresh(id));
    }

    public String getStatistics(String id) {
        return objToString(client.getStatistics(id));
    }

    public String getPortStatistics(String id) {
        return objToString(client.getPortStatistics(id, resourceProperties.get("port")));
    }

    public String updatePorts(String id) {
        Switch switchOjb = client.getById(id);
        return taskToString(client.updatePorts(id, builderUpdatePorts(switchOjb)));
    }

    @Override
    public Switch builder() {
        Switch switchObj = new Switch();
        SwitchManagementConnection mgmt = new SwitchManagementConnection();

        List<ConnectionProperty> properties = new ArrayList<>();
        properties.add(new ConnectionProperty("userName", resourceProperties.get("user"), ValueFormat.Unknown,
                ValueType.String));
        properties.add(new ConnectionProperty("hostname", resourceProperties.get("name"), ValueFormat.Unknown,
                ValueType.String));
        properties.add(new ConnectionProperty("password", resourceProperties.get("password"),
                ValueFormat.SecuritySensitive, ValueType.String));

        mgmt.setConnectionProperties(properties);

        switchObj.setSwitchManagementConnection(mgmt);
        switchObj.setType(getCategory());
        return switchObj;
    }

    private List<Port> builderUpdatePorts(Switch switchOjb) {
        String portUpdate = resourceProperties.get("port");
        List<Port> ports = new ArrayList<>();
        for (Port p : switchOjb.getPorts()) {
            if (p.getName().equals(portUpdate)) {
                p.setEnabled(!p.getEnabled());
                ports.add(p);
                break;
            }
        }
        return ports;
    }

}
