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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.networking.EthernetNetworkType;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.LocationEntry;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.NetworkType;
import com.hp.ov.sdk.dto.networking.OpSpeed;
import com.hp.ov.sdk.dto.networking.PortConfigInfo;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.dto.networking.uplinksets.ConnectionMode;
import com.hp.ov.sdk.dto.networking.uplinksets.ManualLoginRedistributionState;
import com.hp.ov.sdk.dto.networking.uplinksets.UplinkSet;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.networking.InterconnectClient;
import com.hp.ov.sdk.rest.client.networking.UplinkSetClient;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.URIUtils;

public class UplinkSetResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static UplinkSetResource instance;

    private UplinkSetClient client;

    private EnclosureClient enclosureClient;
    private InterconnectClient interconnectClient;

    public UplinkSetResource() {
        category.put("V_300", "uplink-setV300");
        category.put("V_200", "uplink-setV3");
        client = oneViewClient.uplinkSet();
        enclosureClient = oneViewClient.enclosure();
        interconnectClient = oneViewClient.interconnect();
    }

    public static UplinkSetResource getInstance() {
        if (instance == null) {
            instance = new UplinkSetResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        UplinkSet uplinkSet = (UplinkSet) getResource(client.getByName(name));
        return uplinkSet == null ? "" : uplinkSet.getResourceId();
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

    public void createSynergy() {
        client.create(builderSynergy());
    }

    @Override
    public UplinkSet builder() {
        String resourceId = null;
        UplinkSet uplinkSets = new UplinkSet();

        uplinkSets.setCategory(resourceProperties.get("category"));

        Enclosure enclosuresDto = enclosureClient.getByName(resourceProperties.get("enclosure")).get(0);

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(resourceProperties.get("bay")) == enclosuresDto.getInterconnectBays()
                    .get(i).getBayNumber()) {
                uplinkSets.setLogicalInterconnectUri(enclosuresDto.getInterconnectBays()
                        .get(0).getLogicalInterconnectUri());
            }
        }

        uplinkSets.setName(resourceProperties.get("name"));

        uplinkSets.setType(getCategory());
        uplinkSets.setConnectionMode(ConnectionMode.valueOf(resourceProperties.get("connectionMode")));
        uplinkSets.setEthernetNetworkType(EthernetNetworkType.valueOf(resourceProperties.get("ethernetNetworkType")));
        uplinkSets.setNetworkType(NetworkType.valueOf(resourceProperties.get("networkType")));

        uplinkSets.setManualLoginRedistributionState(
                ManualLoginRedistributionState.valueOf(resourceProperties.get("manualLoginRedistributionState")));

        List<String> networkUris = new ArrayList<>();
        List<String> FC_NETWORK_NAME = Arrays.asList(resourceProperties.get("fc_network"));
        for (int i = 0; i < FC_NETWORK_NAME.size(); i++) {
            networkUris.add(oneViewClient.fcNetwork().getByName(FC_NETWORK_NAME.get(i)).get(0).getUri());
        }
        uplinkSets.setFcNetworkUris(networkUris);

        List<PortConfigInfo> portConfigInfos = new ArrayList<>();
        PortConfigInfo portConfigInfo = new PortConfigInfo();
        portConfigInfo.setDesiredSpeed(OpSpeed.valueOf(resourceProperties.get("desiredSpeed")));

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(resourceProperties.get("bay")) == enclosuresDto.getInterconnectBays().get(i)
                    .getBayNumber()) {
                if (null != enclosuresDto.getInterconnectBays().get(i).getInterconnectUri()) {
                    resourceId = URIUtils
                            .getResourceIdFromUri(enclosuresDto.getInterconnectBays().get(i).getInterconnectUri());
                }
                Interconnect interconnectsDto = interconnectClient.getById(resourceId);
                for (int j = 0; j < interconnectsDto.getPortCount(); j++) {
                    if (interconnectsDto.getPorts().get(j).getPortName()
                            .equalsIgnoreCase(resourceProperties.get("port"))) {
                        portConfigInfo.setPortUri(interconnectsDto.getPorts().get(j).getUri());
                    }
                }
            }
        }

        Location location = new Location();
        List<LocationEntry> locationEntries = new ArrayList<>();
        LocationEntry locationEntry_two = new LocationEntry();
        LocationEntry locationEntry_one = new LocationEntry();
        locationEntry_one.setValue(enclosuresDto.getUri());
        locationEntry_one.setType(LocationType.Enclosure);
        locationEntries.add(locationEntry_one);

        locationEntry_two.setValue(resourceProperties.get("bay"));
        locationEntry_two.setType(LocationType.Bay);
        locationEntries.add(locationEntry_two);

        LocationEntry locationEntry_three = new LocationEntry();
        locationEntry_three.setValue(resourceProperties.get("port"));
        locationEntry_three.setType(LocationType.Port);
        locationEntries.add(locationEntry_three);

        location.setLocationEntries(locationEntries);
        portConfigInfo.setLocation(location);
        portConfigInfos.add(portConfigInfo);
        uplinkSets.setPortConfigInfos(portConfigInfos);

        return uplinkSets;
    }

    public UplinkSet builderSynergy() {
        String resourceId = null;
        UplinkSet uplinkSets = new UplinkSet();

        uplinkSets.setCategory(resourceProperties.get("category"));

        Enclosure enclosuresDto = enclosureClient.getByName(resourceProperties.get("enclosure")).get(0);

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(resourceProperties.get("bay")) == enclosuresDto.getInterconnectBays()
                    .get(i).getBayNumber()) {
                uplinkSets.setLogicalInterconnectUri(enclosuresDto.getInterconnectBays()
                        .get(0).getLogicalInterconnectUri());
            }
        }

        uplinkSets.setName(resourceProperties.get("name"));
        uplinkSets.setType(getCategory());
        uplinkSets.setConnectionMode(ConnectionMode.valueOf(resourceProperties.get("connectionMode")));
        uplinkSets.setEthernetNetworkType(EthernetNetworkType.valueOf(resourceProperties.get("ethernetNetworkType")));
        uplinkSets.setNetworkType(NetworkType.valueOf(resourceProperties.get("networkType")));

        uplinkSets.setManualLoginRedistributionState(
                ManualLoginRedistributionState.valueOf(resourceProperties.get("manualLoginRedistributionState")));

        ResourceDtoUtils resourceDto = new ResourceDtoUtils(oneViewClient);
        String LIG_URI = resourceDto.getLogicalInterconnectByName(resourceProperties.get("logicalInterconnectName")).getUri();
        uplinkSets.setLogicalInterconnectUri(LIG_URI);

        List<String> networkUris = new ArrayList<>();
        List<String> FC_NETWORK_NAME = Arrays.asList(resourceProperties.get("fc_network"));
        for (int i = 0; i < FC_NETWORK_NAME.size(); i++) {
            networkUris.add(oneViewClient.fcNetwork().getByName(FC_NETWORK_NAME.get(i)).get(0).getUri());
        }

        uplinkSets.setFcNetworkUris(networkUris);

        List<PortConfigInfo> portConfigInfos = new ArrayList<>();
        PortConfigInfo portConfigInfo = new PortConfigInfo();
        portConfigInfo.setDesiredSpeed(OpSpeed.valueOf(resourceProperties.get("desiredSpeed")));

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(resourceProperties.get("bay")) == enclosuresDto.getInterconnectBays().get(i)
                    .getBayNumber()) {
                if (null != enclosuresDto.getInterconnectBays().get(i).getInterconnectUri()) {
                    resourceId = URIUtils
                            .getResourceIdFromUri(enclosuresDto.getInterconnectBays().get(i).getInterconnectUri());
                }
                Interconnect interconnectsDto = interconnectClient.getById(resourceId);
                for (int j = 0; j < interconnectsDto.getPortCount(); j++) {
                    if (interconnectsDto.getPorts().get(j).getPortName()
                            .equalsIgnoreCase(resourceProperties.get("port"))) {
                        portConfigInfo.setPortUri(interconnectsDto.getPorts().get(j).getUri());
                    }
                }
            }
        }

        Location location = new Location();
        List<LocationEntry> locationEntries = new ArrayList<>();
        LocationEntry locationEntry_two = new LocationEntry();
        LocationEntry locationEntry_one = new LocationEntry();
        locationEntry_one.setValue(enclosuresDto.getUri());
        locationEntry_one.setType(LocationType.Enclosure);
        locationEntries.add(locationEntry_one);

        locationEntry_two.setValue(resourceProperties.get("bay"));
        locationEntry_two.setType(LocationType.Bay);
        locationEntries.add(locationEntry_two);

        LocationEntry locationEntry_three = new LocationEntry();
        locationEntry_three.setValue(resourceProperties.get("port"));
        locationEntry_three.setType(LocationType.Port);
        locationEntries.add(locationEntry_three);

        location.setLocationEntries(locationEntries);
        portConfigInfo.setLocation(location);
        portConfigInfos.add(portConfigInfo);
        uplinkSets.setPortConfigInfos(portConfigInfos);

        return uplinkSets;
    }

    @Override
    public String update(String id) {
        UplinkSet uplinkSet = client.getById(id);
        uplinkSet.setName(resourceProperties.get("name"));
        return taskToString(client.update(id, uplinkSet));
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

}
