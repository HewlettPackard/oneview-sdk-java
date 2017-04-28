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

import com.hp.ov.sdk.dto.networking.EthernetNetworkType;
import com.hp.ov.sdk.dto.networking.ethernet.Bandwidth;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.dto.networking.ethernet.Purpose;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient;

public class EthernetNetworkResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static EthernetNetworkResource instance;

    private EthernetNetworkClient client;

    private Map<String, Double> bandwidthProperties;

    private EthernetNetworkResource() {
        category.put("V_300", "ethernet-networkV300");
        category.put("V_200", "ethernet-networkV3");
        client = oneViewClient.ethernetNetwork();
    }

    public static EthernetNetworkResource getInstance() {
        if (instance == null) {
            instance = new EthernetNetworkResource();
        }
        return instance;
    }

    public void setBandwidthValues(Map<String, Double> map) {
        this.bandwidthProperties = map;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        Network network = (Network) getResource(client.getByName(name));
        return network == null ? "" : network.getResourceId();
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

    public int getAssociatedProfiles(String resourceID) {
        List<String> uris = client.getAssociatedProfiles(resourceID);
        if (uris != null) {
            return uris.size();
        }
        return -1;
    }

    public int getAssociatedUplinkGroups(String resourceID) {
        List<String> uris = client.getAssociatedUplinkGroups(resourceID);
        if (uris != null) {
            return uris.size();
        }
        return -1;
    }

    public Network builder() {
        Network network = new Network();
        network.setName(resourceProperties.get("name"));
        network.setType(getCategory());
        network.setEthernetNetworkType(EthernetNetworkType.valueOf(resourceProperties.get("ethernetType")));
        network.setVlanId(Integer.parseInt(resourceProperties.get("vlanId")));
        network.setPurpose(Purpose.valueOf(resourceProperties.get("purpose")));
        network.setPrivateNetwork(Boolean.parseBoolean(resourceProperties.get("private")));
        network.setSmartLink(Boolean.parseBoolean(resourceProperties.get("smartLink")));
        
        return network;
    }

    private Network builderUpdate(Network network) {
        network.setName(resourceProperties.get("name"));
        network.setEthernetNetworkType(EthernetNetworkType.valueOf(resourceProperties.get("ethernetNetworkType")));
        network.setPurpose(Purpose.valueOf(resourceProperties.get("purpose")));
        network.setPrivateNetwork(Boolean.parseBoolean(resourceProperties.get("privateNetwork")));
        network.setSmartLink(Boolean.parseBoolean(resourceProperties.get("smartLink")));
        return network;
    }

    private ConnectionTemplate builderConnectionTemplate() {
        Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(bandwidthProperties.get("maxBandwidth"));
        bandwidth.setTypicalBandwidth(bandwidthProperties.get("typicalBandwidth"));

        ConnectionTemplate connectionTemplate = new ConnectionTemplate();
        connectionTemplate.setBandwidth(bandwidth);
        return connectionTemplate;
    }

}
