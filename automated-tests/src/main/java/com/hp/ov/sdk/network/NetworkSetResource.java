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

import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.rest.client.networking.NetworkSetClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;

public class NetworkSetResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static NetworkSetResource instance;

    private NetworkSetClient client;

    private ResourceDtoUtils resourceDtoUtils;

    private List<String> networkList;

    private NetworkSetResource() {
        category.put("V_300", "network-setV300");
        category.put("V_200", "network-set");
        client = oneViewClient.networkSet();
        resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
    }

    public static NetworkSetResource getInstance() {
        if (instance == null) {
            instance = new NetworkSetResource();
        }
        return instance;
    }

    public void setNetworkNames(List<String> list) {
        this.networkList = list;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        NetworkSet networkSet = (NetworkSet) getResource(client.getByName(name));
        return networkSet == null ? "" : networkSet.getResourceId();
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
        NetworkSet networkSet = client.getById(id);
        networkSet.setName(this.resourceProperties.get("name"));
        return taskToString(client.update(id, networkSet));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    public NetworkSet builder() {
        NetworkSet networkSet = resourceDtoUtils.buildNetworkSetDto(this.resourceProperties.get("name"),
                this.networkList);
        networkSet.setType(getCategory());
        return networkSet;
    }
}
