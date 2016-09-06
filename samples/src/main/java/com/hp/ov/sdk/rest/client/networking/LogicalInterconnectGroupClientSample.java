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
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.InterconnectTypeName;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.UplinkSetGroup;
import com.hp.ov.sdk.dto.samples.UplinkSetValue;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.samples.ResourceDtoUtilsWrapper;

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

    public static final String resourceName = "LIG_PROD";

    private static final InterconnectTypeName permittedInterconnectType = InterconnectTypeName.HP_VC_FlexFabric_20_40_F8_Module;
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402", "Prod_403");
    private static final List<String> logicalInterconnectGroupName_A = Arrays.asList("FC_Network_A");
    private static final List<String> logicalInterconnectGroupName_B = Arrays.asList("FC_Network_B");
    private static final List<String> ethPort = Arrays.asList("X5", "X6");
    private static final List<String> fcPort = Arrays.asList("X2");
    private static final String ethUplinkSetType = "Ethernet";
    private static final String fcUplinkSetType = "FibreChannel";
    private static final String ethUplinkSetName = "EthernetUplinkSet";
    private static final String fcAUplinkSetName = "FCUplinkSetA";
    private static final String fcBUplinkSetName = "FCUplinkSetB";
    private static final String resourceId = "710a4017-4523-4522-a61b-cde6aec37de8";
    private static final String settingId = "dcd89c40-57b6-4551-9486-acc9090785fa";
    // ================================

    private final LogicalInterconnectGroupClient client;

    private ResourceDtoUtils resourceDtoUtils;

    private LogicalInterconnectGroupClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();
        this.client = oneViewClient.logicalInterconnectGroup();

        resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
    }

    private void getLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = client.getById(resourceId);

        LOGGER.info("LogicalInterconnectGroup object returned to client : " + logicalInterconnectGroup);
    }

    private void getAllLogicalInterconnectGroups() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getAll();

        LOGGER.info("LogicalInterconnectGroups returned to client (count) : " + logicalInterconnectGroups.getCount());
    }

    private void getLogicalInterconnectGroupByName() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getByName(resourceName);

        LOGGER.info("LogicalInterconnectGroup object returned to client : " + logicalInterconnectGroups.getMembers().get(0).toJsonString());
    }

    private void createLogicalInterconnectGroup() {
        LogicalInterconnectGroup logicalInterconnectGroup = this.buildTestLogicalInterconnectGroup();
        logicalInterconnectGroup.setName(resourceName);
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP);
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V200);
        logicalInterconnectGroup.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V300);

        TaskResourceV2 task = this.client.create(logicalInterconnectGroup, false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectGroup() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getByName(resourceName);
        LogicalInterconnectGroup lig = logicalInterconnectGroups.getMembers().get(0);
        lig.setName(resourceName + "_Update");

        final List<UplinkSetGroup> uplinkSetDto = buildUplinkSetGroupDto();
        lig.setUplinkSets(uplinkSetDto);

        TaskResourceV2 task = this.client.update(lig.getResourceId(), lig, false);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void deleteLogicalInterconnectGroup() {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = client.getByName(resourceName);
        LogicalInterconnectGroup lig = logicalInterconnectGroups.getMembers().get(0);
        TaskResourceV2 task = this.client.delete(lig.getResourceId(), false);

        LOGGER.info("Task object returned to client : " + task);
    }

    private void getDefaultInterconnectSettings() {
        InterconnectSettingsV2 interconnectSettingsDto = client.getDefaultInterconnectSettings();

        LOGGER.info("InterconnectSettingsV2 object returned to client : " + interconnectSettingsDto.toJsonString());
    }

    private void getInterconnectSettings() {
        // To run getInterconnectSettings on OneView 1.2, you need settingID and resourceID of LIG
        // for OV 2.0 & 3.0, you just need the resourceID
        InterconnectSettingsV2 interconnectSettingsDto = client.getInterconnectSettings(resourceId, settingId);
//        InterconnectSettingsV2 interconnectSettingsDto = client.getInterconnectSettings(resourceId);

        LOGGER.info("InterconnectSettingsV2 object returned to client : " + interconnectSettingsDto.toJsonString());
    }

    private LogicalInterconnectGroup buildTestLogicalInterconnectGroup() {
        HashMap<Integer, InterconnectTypeName> bayPermittedInterconnectMaps = new HashMap<>(interconnectEntries.size());

        bayPermittedInterconnectMaps.put(interconnectEntries.get(0), permittedInterconnectType);
        bayPermittedInterconnectMaps.put(interconnectEntries.get(1), permittedInterconnectType);

        LogicalInterconnectGroup dto = resourceDtoUtils.buildLogicalInterconnectGroupDto(resourceName, bayPermittedInterconnectMaps);
        dto.setEnclosureIndexes(Arrays.asList(1));
        dto.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP);
        dto.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V200);
        dto.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V300);

        for (InterconnectMapEntryTemplate entry : dto.getInterconnectMapTemplate().getInterconnectMapEntryTemplates()) {
            entry.setEnclosureIndex(1);
        }

        return  dto;
    }

    private List<UplinkSetGroup> buildUplinkSetGroupDto() {
        List<UplinkSetGroup> uplinkSetGroupDto = new ArrayList<UplinkSetGroup>();

        final HashMap<Integer, List<String>> ethBayPortMap = new HashMap<Integer, List<String>>();
        ethBayPortMap.put(1, ethPort);
        ethBayPortMap.put(2, ethPort);
        final HashMap<Integer, List<String>> fcBayPortMapA = new HashMap<Integer, List<String>>();
        fcBayPortMapA.put(1, fcPort);
        final HashMap<Integer, List<String>> fcBayPortMapB = new HashMap<Integer, List<String>>();
        fcBayPortMapB.put(2, fcPort);

        final List<UplinkSetValue> uplinkSetValues = new ArrayList<UplinkSetValue>();
        final UplinkSetValue ethUplinkSetValue = new UplinkSetValue();
        ethUplinkSetValue.setBayPortMap(ethBayPortMap);
        ethUplinkSetValue.setLigName(resourceName);
        ethUplinkSetValue.setNetworkNames(networkNames);
        ethUplinkSetValue.setUplinkSetName(ethUplinkSetName);
        ethUplinkSetValue.setUplinkSetType(ethUplinkSetType);

        final UplinkSetValue fcAUplinkSetValue = new UplinkSetValue();
        fcAUplinkSetValue.setBayPortMap(fcBayPortMapA);
        fcAUplinkSetValue.setLigName(resourceName);
        fcAUplinkSetValue.setNetworkNames(logicalInterconnectGroupName_A);
        fcAUplinkSetValue.setUplinkSetName(fcAUplinkSetName);
        fcAUplinkSetValue.setUplinkSetType(fcUplinkSetType);

        final UplinkSetValue fcBUplinkSetValue = new UplinkSetValue();
        fcBUplinkSetValue.setBayPortMap(fcBayPortMapB);
        fcBUplinkSetValue.setLigName(resourceName);
        fcBUplinkSetValue.setNetworkNames(logicalInterconnectGroupName_B);
        fcBUplinkSetValue.setUplinkSetName(fcBUplinkSetName);
        fcBUplinkSetValue.setUplinkSetType(fcUplinkSetType);

        uplinkSetValues.add(ethUplinkSetValue);
        uplinkSetValues.add(fcAUplinkSetValue);
        uplinkSetValues.add(fcBUplinkSetValue);

        ResourceDtoUtilsWrapper resourceDtoUtilsWrapper = new ResourceDtoUtilsWrapper(resourceDtoUtils);
        uplinkSetGroupDto = resourceDtoUtilsWrapper.buildUplinkSetGroupDto(uplinkSetValues);

        return uplinkSetGroupDto;
    }

    public static void main(final String[] args) throws Exception {
        LogicalInterconnectGroupClientSample client = new LogicalInterconnectGroupClientSample();

        client.getAllLogicalInterconnectGroups();
        client.createLogicalInterconnectGroup();
        client.getLogicalInterconnectGroup();
        client.getLogicalInterconnectGroupByName();
        client.getDefaultInterconnectSettings();

        client.getInterconnectSettings();
        client.updateLogicalInterconnectGroup();
        client.deleteLogicalInterconnectGroup();
    }
}
