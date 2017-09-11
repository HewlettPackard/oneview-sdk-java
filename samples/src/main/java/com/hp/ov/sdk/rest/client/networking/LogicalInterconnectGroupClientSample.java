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

import static com.hp.ov.sdk.rest.client.settings.ScopeClient.SCOPES_URI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.InterconnectSettings;
import com.hp.ov.sdk.dto.networking.NetworkType;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectTypeName;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup.RedundancyType;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.UplinkSetGroup;
import com.hp.ov.sdk.dto.samples.UplinkSetValue;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;

/*
 * LogicalInterconnectGroupClientSample is a sample program, acts as a recipe for representing the available networks,
 * uplink sets, stacking links, and interconnect settings for a set of physical interconnects in a single enclosure
 * of HPE OneView to a logical interconnect group. It invokes APIs of LogicalInterconnectGroupClient which is in sdk
 * library to perform GET/PUT/POST/DELETE operations on logical interconnect group resource
 */
public class LogicalInterconnectGroupClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalInterconnectGroupClientSample.class);

    // test values - user input
    // ================================
    public static final List<Integer> interconnectEntries = Arrays.asList(Integer.valueOf(1), Integer.valueOf(2));

    public static final String RESOURCE_NAME = "LIG_PROD";

    private static final String RESOURCE_NAME_UPDATED = RESOURCE_NAME + "_Updated";
    private static final InterconnectTypeName permittedInterconnectType = InterconnectTypeName.Virtual_Connect_SE_16Gb_FC_Module_for_Synergy;
    private static final InterconnectTypeName interconnectType = permittedInterconnectType;
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402", "Prod_403");
    private static final List<String> logicalInterconnectGroupName_A = Arrays
            .asList(FcNetworkClientSample.FC_NETWORK_NAME_A);
    private static final List<String> logicalInterconnectGroupName_B = Arrays
            .asList(FcNetworkClientSample.FC_NETWORK_NAME_B);
    private static final List<String> ethPort = Arrays.asList("X5", "X6");
    private static final List<String> fcPort = Arrays.asList("Q1:1", "Q1:2");

    private static final String ethUplinkSetName = "EthernetUplinkSet";
    private static final String fcAUplinkSetName = "FCUplinkSetA";
    private static final String fcBUplinkSetName = "FCUplinkSetB";
    private static final String SETTING_ID = "dcd89c40-57b6-4551-9486-acc9090785fa";
    private static final String FABRIC_URI = "/rest/fabrics/a7896ce7-c11d-4658-829d-142bc66a85e4";
    private static final String LOGICAL_DOWNLINK_URI = "/rest/logical-downlinks/2ece260c-7997-441d-b1b9-d3296fe89505";
    private static final String SCOPE_ID = "2d108000-f8d1-4104-8457-7a5aa3c1bb40";
    // ================================

    private final LogicalInterconnectGroupClient client;

    private ResourceDtoUtils resourceDtoUtils;

    private LogicalInterconnectGroupClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();
        this.client = oneViewClient.logicalInterconnectGroup();

        resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
    }

    private void getLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = client.getByName(RESOURCE_NAME).get(0);

        logicalInterconnectGroup = client.getById(logicalInterconnectGroup.getResourceId());

        LOGGER.info("LogicalInterconnectGroup object returned to client : " + logicalInterconnectGroup.toJsonString());
    }

    private void getAllLogicalInterconnectGroups() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getAll();

        LOGGER.info("LogicalInterconnectGroups returned to client (count) : " + logicalInterconnectGroups.getCount());
    }

    private void getLogicalInterconnectGroupByName() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getByName(RESOURCE_NAME);

        LogicalInterconnectGroup logicalInterconnectGroup = logicalInterconnectGroups.getMembers().get(0);

        LOGGER.info("LogicalInterconnectGroup object returned to client : " + logicalInterconnectGroup.toJsonString());
    }

    private void createLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = this.buildTestLogicalInterconnectGroup();
        logicalInterconnectGroup.setName(RESOURCE_NAME);
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP); // v120
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V200); // v200
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V300); // v300 or v500

        TaskResource task = this.client.create(logicalInterconnectGroup);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void createLogicalInterconnectGroupSynergy() {
        LogicalInterconnectGroup logicalInterconnectGroup = this.buildTestLogicalInterconnectGroupSynergy();
        logicalInterconnectGroup.setName(RESOURCE_NAME);
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V300); // v300 or v500
        logicalInterconnectGroup.setEnclosureType(EnclosureType.SY12000);

        TaskResource task = this.client.create(logicalInterconnectGroup);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void patchLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = client.getByName(RESOURCE_NAME).get(0);

        Patch patch = new Patch();

        // Logical Interconnect Group patch supports the update of scopeUris
        patch.setOp(PatchOperation.replace);
        patch.setPath("/scopeUris");
        List<String> scopeUris = logicalInterconnectGroup.getScopeUris(); // Gets the current scope(s)
        scopeUris.add(SCOPES_URI + "/" + SCOPE_ID); // Assigns Logical Interconnect Group to a new scope
        // scopeUris.remove(SCOPES_URI + "/" + SCOPE_ID); // Unassigns Logical Interconnect Group from a scope
        patch.setValue(scopeUris);

        TaskResource task = this.client.patch(logicalInterconnectGroup.getResourceId(), patch);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectGroup() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getByName(RESOURCE_NAME);
        LogicalInterconnectGroup logicalInterconnectGroup = logicalInterconnectGroups.getMembers().get(0);

        logicalInterconnectGroup.setName(RESOURCE_NAME_UPDATED);

        final List<UplinkSetGroup> uplinkSetDto = buildUplinkSetGroupDto();
        logicalInterconnectGroup.setUplinkSets(uplinkSetDto);

        TaskResource task = this.client.update(logicalInterconnectGroup.getResourceId(), logicalInterconnectGroup);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void deleteLogicalInterconnectGroup() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client
                .getByName(RESOURCE_NAME_UPDATED);
        LogicalInterconnectGroup logicalInterconnectGroup = logicalInterconnectGroups.getMembers().get(0);
        TaskResource task = this.client.delete(logicalInterconnectGroup.getResourceId());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getDefaultInterconnectSettings() {
        InterconnectSettings interconnectSettingsDto = client.getDefaultInterconnectSettings();

        LOGGER.info("Interconnect settings returned to client : " + interconnectSettingsDto.toJsonString());
    }

    private void getInterconnectSettings() {
        LogicalInterconnectGroup logicalInterconnectGroup = client.getByName(RESOURCE_NAME).get(0);

        // To run getInterconnectSettings on OneView 1.2, you need settingID and
        // resourceID of LIG
        // for OV 2.0 & 3.0, you just need the resourceID
        // InterconnectSettings interconnectSettingsDto =
        // client.getInterconnectSettings(
        // logicalInterconnectGroup.getResourceId(), SETTING_ID);
        InterconnectSettings interconnectSettingsDto = client
                .getInterconnectSettings(logicalInterconnectGroup.getResourceId());

        LOGGER.info("Interconnect settings returned to client : " + interconnectSettingsDto.toJsonString());
    }

    private LogicalInterconnectGroup buildTestLogicalInterconnectGroup() {
        HashMap<Integer, InterconnectTypeName> bayPermittedInterconnectMaps = new HashMap<>(interconnectEntries.size());

        bayPermittedInterconnectMaps.put(interconnectEntries.get(0), permittedInterconnectType);
        bayPermittedInterconnectMaps.put(interconnectEntries.get(1), permittedInterconnectType);

        LogicalInterconnectGroup group = resourceDtoUtils.buildLogicalInterconnectGroupDto(RESOURCE_NAME,
                bayPermittedInterconnectMaps);
        group.setEnclosureIndexes(Arrays.asList(1));
        group.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP);
        group.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V200);
        group.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V300);

        for (InterconnectMapEntryTemplate entry : group.getInterconnectMapTemplate()
                .getInterconnectMapEntryTemplates()) {
            entry.setEnclosureIndex(1);
        }
        return group;
    }

    private LogicalInterconnectGroup buildTestLogicalInterconnectGroupSynergy() {
        int interconnectBaySet = 1;
        int enclosureIndex = 1;

        if (InterconnectTypeName.Virtual_Connect_SE_16Gb_FC_Module_for_Synergy.equals(interconnectType)) {
            interconnectBaySet = 2;
            enclosureIndex = -1;
        }

        LogicalInterconnectGroup group = resourceDtoUtils.buildLogicalInterconnectGroupSynergyDto(RESOURCE_NAME,
                interconnectType, interconnectBaySet, LOGICAL_DOWNLINK_URI);

        group.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V300);
        group.setRedundancyType(RedundancyType.Redundant);
        group.setCategory("logical-interconnect-groups");
        group.setFabricUri(FABRIC_URI);

        group.setEnclosureIndexes(Arrays.asList(enclosureIndex));

        for (InterconnectMapEntryTemplate entry : group.getInterconnectMapTemplate()
                .getInterconnectMapEntryTemplates()) {
            entry.setEnclosureIndex(enclosureIndex);
        }
        return group;
    }

    private List<UplinkSetGroup> buildUplinkSetGroupDto() {
        List<UplinkSetGroup> uplinkSetGroupDto;

        final HashMap<Integer, List<String>> ethBayPortMap = new HashMap<>();
        ethBayPortMap.put(1, ethPort);
        ethBayPortMap.put(2, ethPort);
        final HashMap<Integer, List<String>> fcBayPortMapA = new HashMap<>();
        fcBayPortMapA.put(2, fcPort);
        final HashMap<Integer, List<String>> fcBayPortMapB = new HashMap<>();
        fcBayPortMapB.put(5, fcPort);

        final List<UplinkSetValue> uplinkSetValues = new ArrayList<UplinkSetValue>();
        final UplinkSetValue ethUplinkSetValue = new UplinkSetValue();
        ethUplinkSetValue.setBayPortMap(ethBayPortMap);
        ethUplinkSetValue.setLigName(RESOURCE_NAME);
        ethUplinkSetValue.setNetworkNames(networkNames);
        ethUplinkSetValue.setUplinkSetName(ethUplinkSetName);
        ethUplinkSetValue.setUplinkSetType(NetworkType.Ethernet);

        final UplinkSetValue fcAUplinkSetValue = new UplinkSetValue();
        fcAUplinkSetValue.setBayPortMap(fcBayPortMapA);
        fcAUplinkSetValue.setLigName(RESOURCE_NAME);
        fcAUplinkSetValue.setNetworkNames(logicalInterconnectGroupName_A);
        fcAUplinkSetValue.setUplinkSetName(fcAUplinkSetName);
        fcAUplinkSetValue.setUplinkSetType(NetworkType.FibreChannel);

        final UplinkSetValue fcBUplinkSetValue = new UplinkSetValue();
        fcBUplinkSetValue.setBayPortMap(fcBayPortMapB);
        fcBUplinkSetValue.setLigName(RESOURCE_NAME);
        fcBUplinkSetValue.setNetworkNames(logicalInterconnectGroupName_B);
        fcBUplinkSetValue.setUplinkSetName(fcBUplinkSetName);
        fcBUplinkSetValue.setUplinkSetType(NetworkType.FibreChannel);

        uplinkSetValues.add(ethUplinkSetValue);
        uplinkSetValues.add(fcAUplinkSetValue);
        uplinkSetValues.add(fcBUplinkSetValue);

        uplinkSetGroupDto = buildUplinkSetGroupDto(uplinkSetValues);

        return uplinkSetGroupDto;
    }

    public List<UplinkSetGroup> buildUplinkSetGroupDto(final List<UplinkSetValue> uplinkSetValues) {
        final List<UplinkSetGroup> uplinkSetGroupDto = new ArrayList<>();

        for (UplinkSetValue uplinkSetValue : uplinkSetValues) {
            uplinkSetGroupDto.add(resourceDtoUtils.buildUplinkSetDto(uplinkSetValue.getLigName(),
                    uplinkSetValue.getUplinkSetName(), uplinkSetValue.getUplinkSetType(),
                    uplinkSetValue.getBayPortMap(), uplinkSetValue.getNetworkNames()));
        }

        return uplinkSetGroupDto;
    }

    public static void main(final String[] args) throws Exception {
        LogicalInterconnectGroupClientSample client = new LogicalInterconnectGroupClientSample();

        client.createLogicalInterconnectGroup();

        client.createLogicalInterconnectGroupSynergy();

        client.getAllLogicalInterconnectGroups();
        client.getLogicalInterconnectGroup();
        client.getLogicalInterconnectGroupByName();
        client.getDefaultInterconnectSettings();
        client.getInterconnectSettings();

        client.patchLogicalInterconnectGroup();
        client.updateLogicalInterconnectGroup();
        client.deleteLogicalInterconnectGroup();
    }

}
