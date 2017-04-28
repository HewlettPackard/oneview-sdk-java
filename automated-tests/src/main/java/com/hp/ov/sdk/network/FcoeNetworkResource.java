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

import java.util.Map;

import com.hp.ov.sdk.dto.networking.fcoenetworks.FcoeNetwork;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.rest.client.networking.FcoeNetworkClient;

public class FcoeNetworkResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static FcoeNetworkResource instance;

    private FcoeNetworkClient client;

    private FcoeNetworkResource() {
        category.put("V_300", "fcoe-networkV300");
        category.put("V_200", "fcoe-network");
        client = oneViewClient.fcoeNetwork();
    }

    public static FcoeNetworkResource getInstance() {
        if (instance == null) {
            instance = new FcoeNetworkResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        FcoeNetwork fcoeNetwork = (FcoeNetwork) getResource(client.getByName(name));
        return fcoeNetwork == null ? "" : fcoeNetwork.getResourceId();
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
        client.create(builder());
    }

    @Override
    public String update(String id) {
        return taskToString(client.update(id, builderUpdate(client.getById(id))));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    public FcoeNetwork builder() {
        FcoeNetwork fcoeNetwork = new FcoeNetwork();
        fcoeNetwork.setName(resourceProperties.get("name"));
        fcoeNetwork.setType(getCategory());
        fcoeNetwork.setVlanId(Integer.parseInt(resourceProperties.get("vlanID")));
        return fcoeNetwork;
    }

    public FcoeNetwork builderUpdate(FcoeNetwork fcoeNetwork) {
        fcoeNetwork.setName(resourceProperties.get("name"));
        return fcoeNetwork;
    }
}
