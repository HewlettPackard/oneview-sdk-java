/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client.networking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.LocationEntry;
import com.hp.ov.sdk.dto.LocationType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.EthernetNetworkType;
import com.hp.ov.sdk.dto.networking.NetworkType;
import com.hp.ov.sdk.dto.networking.OpSpeed;
import com.hp.ov.sdk.dto.networking.PortConfigInfo;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.dto.networking.uplinksets.ConnectionMode;
import com.hp.ov.sdk.dto.networking.uplinksets.ManualLoginRedistributionState;
import com.hp.ov.sdk.dto.networking.uplinksets.UplinkSet;
import com.hp.ov.sdk.dto.servers.enclosure.Enclosure;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.servers.EnclosureClientSample;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * UplinkSetClientSample is a sample program to assign/consume networks of HPE OneView to uplink ports of interconnect.
 * It invokes APIs of UplinkSetClient which is in sdk library to perform GET/PUT/POST/DELETE operations
 * on uplink set resource
 */
public class UplinkSetClientSample {

    private final UplinkSetClient uplinkSetClient;
    private final EnclosureClient enclosureClient;
    private final InterconnectClient interconnectClient;
    private final OneViewClient oneViewClient;

    // These are variables to be defined by user
    // ================================
    private static final String RESOURCE_ID = "04120653-c369-4fae-8cbb-0b1787bdc009";
    private static final String RESOURCE_NAME = "Uplink-Sample";
    private static final String RESOURCE_NAME_UPDATED = RESOURCE_NAME + "_Updated";

    private static final List<String> FC_NETWORK_NAME = Arrays.asList("FC_Network_A");
    private static final String PORT_VALUE = "X3";
    private static final String BAY_VALUE = "2";
    // ================================

    private UplinkSetClientSample() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();

        uplinkSetClient = oneViewClient.uplinkSet();
        enclosureClient = oneViewClient.enclosure();
        interconnectClient = oneViewClient.interconnect();
    }

    private void getUplinkSetById() {
        UplinkSet uplinkSet = uplinkSetClient.getById(RESOURCE_ID);

        System.out.println("UplinkSetClientSample : getUplinkSetById : " +
                "UplinkSet object returned to client : " + uplinkSet.toJsonString());
    }

    private void getAllUplinkSets() {
        ResourceCollection<UplinkSet> uplinkSets = uplinkSetClient.getAll();

        System.out.println("UplinkSetClientSample : getAllUplinkSets : " +
                "UplinkSets returned to client : " + uplinkSets.toJsonString());
    }

    private void getUplinkSetByName() {
        UplinkSet uplinkSet = uplinkSetClient.getByName(RESOURCE_NAME).get(0);

        System.out.println("UplinkSetClientSample : getUplinkSetByName : " +
                "UplinkSet object returned to client : " + uplinkSet.toJsonString());
    }

    private void createUplinkSet() {
        UplinkSet uplinkSet = buildUplinkSet();

        TaskResourceV2 task = this.uplinkSetClient.create(uplinkSet, false);

        System.out.println("UplinkSetClientSample : createUplinkSet : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateUplinkSet() {
        UplinkSet uplinkSet = uplinkSetClient.getByName(RESOURCE_NAME).get(0);

        uplinkSet.setName(RESOURCE_NAME_UPDATED);

        TaskResourceV2 task = this.uplinkSetClient.update(uplinkSet.getResourceId(), uplinkSet, false);

        System.out.println("UplinkSetClientSample : updateUplinkSet : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void deleteUplinkSet() {
        UplinkSet uplinkSet = uplinkSetClient.getByName(RESOURCE_NAME_UPDATED).get(0);

        TaskResourceV2 task = this.uplinkSetClient.delete(uplinkSet.getResourceId(), false);

        System.out.println("UplinkSetClientSample : deleteUplinkSet : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private UplinkSet buildUplinkSet() {
        RestParams params = HPOneViewCredential.createCredentials();
        String resourceId = null;
        UplinkSet uplinkSetsDto = new UplinkSet();

        uplinkSetsDto.setCategory(ResourceCategory.RC_LOGICAL_INTERCONNECTS);

        Enclosure enclosuresDto = enclosureClient.getByName(EnclosureClientSample.RESOURCE_NAME).get(0);

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(BAY_VALUE) == enclosuresDto.getInterconnectBays().get(i).getBayNumber()) {
                uplinkSetsDto.setLogicalInterconnectUri(enclosuresDto.getInterconnectBays().get(0).getLogicalInterconnectUri());
            }
        }

        uplinkSetsDto.setType(ResourceCategory.RC_UPLINKS_SETS); //OV 1.2
        uplinkSetsDto.setType(ResourceCategory.RC_UPLINKS_SETSV200); //OV 2.0
        uplinkSetsDto.setConnectionMode(ConnectionMode.Auto);
        uplinkSetsDto.setEthernetNetworkType(EthernetNetworkType.NotApplicable);
        uplinkSetsDto.setNetworkType(NetworkType.FibreChannel);
        uplinkSetsDto.setName(RESOURCE_NAME);
        uplinkSetsDto.setManualLoginRedistributionState(ManualLoginRedistributionState.Supported);
        List<String> networkUris = new ArrayList<>();
        for (int i = 0; i < FC_NETWORK_NAME.size(); i++) {
            networkUris.add(oneViewClient.fcNetwork().getByName(FC_NETWORK_NAME.get(i)).get(0).getUri());
        }
        uplinkSetsDto.setFcNetworkUris(networkUris);

        List<PortConfigInfo> portConfigInfos = new ArrayList<>();
        PortConfigInfo portConfigInfo = new PortConfigInfo();
        portConfigInfo.setDesiredSpeed(OpSpeed.Auto);

        for (int i = 0; i < enclosuresDto.getInterconnectBayCount(); i++) {
            if (Integer.parseInt(BAY_VALUE) == enclosuresDto.getInterconnectBays().get(i).getBayNumber()) {
                if (null != enclosuresDto.getInterconnectBays().get(i).getInterconnectUri()) {
                    resourceId = UrlUtils.getResourceIdFromUri(enclosuresDto.getInterconnectBays().get(i).getInterconnectUri());
                }

                Interconnect interconnectsDto = interconnectClient.getById(resourceId);
                for (int j = 0; j < interconnectsDto.getPortCount(); j++) {
                    if (interconnectsDto.getPorts().get(j).getPortName().equalsIgnoreCase(PORT_VALUE)) {
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

        locationEntry_two.setValue(BAY_VALUE);
        locationEntry_two.setType(LocationType.Bay);
        locationEntries.add(locationEntry_two);

        LocationEntry locationEntry_three = new LocationEntry();
        locationEntry_three.setValue(PORT_VALUE);
        locationEntry_three.setType(LocationType.Port);
        locationEntries.add(locationEntry_three);

        location.setLocationEntries(locationEntries);
        portConfigInfo.setLocation(location);
        portConfigInfos.add(portConfigInfo);
        uplinkSetsDto.setPortConfigInfos(portConfigInfos);

        return uplinkSetsDto;
    }

    public static void main(final String[] args) {
        UplinkSetClientSample client = new UplinkSetClientSample();

        client.getUplinkSetById();
        client.getAllUplinkSets();
        client.createUplinkSet();
        client.getUplinkSetByName();
        client.updateUplinkSet();
        client.deleteUplinkSet();
    }
}
