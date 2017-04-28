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

import com.hp.ov.sdk.dto.networking.fcnetworks.FabricType;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;

public class FcNetworkResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static FcNetworkResource instance;

    private FcNetworkClient client;

    private String sanUri;

    private FcNetworkResource() {
        category.put("V_300", "fc-networkV300");
        category.put("V_200", "fc-networkV2");
        client = oneViewClient.fcNetwork();
    }

    public static FcNetworkResource getInstance() {
        if (instance == null) {
            instance = new FcNetworkResource();
        }
        return instance;
    }

    public void setSanUri(String sanUri) {
        this.sanUri = sanUri;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    public FcNetwork getByName(String name) {
        return (FcNetwork) getResource(client.getByName(name));
    }

    @Override
    public String findByName(String name) {
        FcNetwork fcNetwork = (FcNetwork) getResource(client.getByName(name));
        return fcNetwork == null ? "" : fcNetwork.getResourceId();
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

    public FcNetwork builder() {
        FcNetwork fcNetwork = new FcNetwork();
        fcNetwork.setName(resourceProperties.get("name"));
        fcNetwork.setType(getCategory());
        fcNetwork.setAutoLoginRedistribution(Boolean.valueOf(resourceProperties.get("autoLoginRedistribution")));
        fcNetwork.setFabricType(FabricType.valueOf(resourceProperties.get("fabricType")));
        fcNetwork.setLinkStabilityTime(Integer.parseInt(resourceProperties.get("linkStabilityTime")));
        if (sanUri != null) {
            fcNetwork.setManagedSanUri(sanUri);
        }
        return fcNetwork;
    }

    private FcNetwork builderUpdate(FcNetwork fcNetwork) {
        fcNetwork.setName(resourceProperties.get("name"));
        fcNetwork.setLinkStabilityTime(Integer.parseInt(resourceProperties.get("linkStabilityTime")));
        return fcNetwork;
    }

}
