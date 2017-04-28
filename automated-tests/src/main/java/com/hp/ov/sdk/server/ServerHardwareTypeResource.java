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

import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.server.ServerHardwareTypeClient;

public class ServerHardwareTypeResource extends BasicResource implements Resource, UpdateResource, RemoveResource {

    private static ServerHardwareTypeResource instance;

    private ServerHardwareTypeClient client;

    public ServerHardwareTypeResource() {
        client = oneViewClient.serverHardwareType();
    }

    public static Resource getInstance() {
        if (instance == null) {
            instance = new ServerHardwareTypeResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        ServerHardwareType hardwareType = (ServerHardwareType) getResource(client.getByName(name));
        return hardwareType == null ? "" : hardwareType.getResourceId();
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
    public String update(String id) {
        ServerHardwareTypeUpdate hardwareTypeUpdate = new ServerHardwareTypeUpdate();
        hardwareTypeUpdate.setDescription(resourceProperties.get("description"));
        return objToString(client.update(id, hardwareTypeUpdate));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

}
