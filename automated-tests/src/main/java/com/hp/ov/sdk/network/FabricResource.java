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

import java.util.Map;

import com.hp.ov.sdk.dto.networking.fabric.Fabric;
import com.hp.ov.sdk.dto.networking.fabric.VlanPool;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.Resource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.FabricClient;

public class FabricResource extends BasicResource implements Resource, UpdateResource {

    private static FabricResource instance;

    private FabricClient client;

    public FabricResource() {
        client = oneViewClient.fabric();
    }

    public static FabricResource getInstance() {
        if (instance == null) {
            instance = new FabricResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Fabric fabric = (Fabric) getResource(client.getByName(name));
        return fabric == null ? "" : fabric.getResourceId();
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
        VlanPool vlanPool = client.getReservedVlanRange(id);
        vlanPool.setStart(vlanPool.getStart() - 1);
        return taskToString(client.updateReservedVlanRange(id, vlanPool));
    }

    public String getReservedVlanRange(String id) {
        return objToString(client.getReservedVlanRange(id));
    }

}
