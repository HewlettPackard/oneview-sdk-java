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

package com.hp.ov.sdk.network;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.rest.client.networking.InterconnectClient;

public class InterconnectResource extends BasicResource implements Resource {

    private static InterconnectResource instance;

    private InterconnectClient client;

    public InterconnectResource() {
        client = oneViewClient.interconnect();
    }

    public static InterconnectResource getInstance() {
        if (instance == null) {
            instance = new InterconnectResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Interconnect interconnect = (Interconnect) getResource(client.getByName(name));
        return interconnect == null ? "" : interconnect.getResourceId();
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

    public String getStatistics(String id) {
        return objToString(client.getStatistics(id));
    }

    public String getPortStatistics(String id) {
        String port = resourceProperties.get("port");
        return objToString(client.getPortStatistics(id, port));
    }

    public String getSubPortStatistics(String id) {
        String port = resourceProperties.get("port");
        String subport = resourceProperties.get("subport");
        return objToString(client.getSubportStatistics(id, port, Integer.parseInt(subport)));
    }

    public String getNamedServers(String id) {
        return objToString(client.getNamedServers(id));
    }

    public String patch(String id) {
        Patch patch = new Patch();
        patch.setOp(Patch.PatchOperation.valueOf(resourceProperties.get("op")));
        patch.setPath(resourceProperties.get("path"));
        patch.setValue(resourceProperties.get("value"));
        return objToString(client.patch(id, patch));
    }

    public String resetInterconnectPortProtection(String id) {
        return taskToString(client.resetPortProtection(id));
    }

    public String updateInterconnectPort(String id) {
        Interconnect interconnect = client.getById(id);
        Port port = interconnect.getPorts().get(0);
        port.setEnabled(Boolean.valueOf(resourceProperties.get("portEnable")));
        return taskToString(client.updatePort(id, port));
    }

    public String updateInterconnectPorts(String id) {
        Interconnect interconnect = client.getById(id);
        Port port = interconnect.getPorts().get(0);
        port.setEnabled(Boolean.valueOf(resourceProperties.get("portEnable")));
        List<Port> ports = Arrays.asList(port);
        return taskToString(client.updatePorts(id, ports));
    }

}
