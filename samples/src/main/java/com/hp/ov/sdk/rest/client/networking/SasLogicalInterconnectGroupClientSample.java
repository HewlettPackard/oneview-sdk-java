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

package com.hp.ov.sdk.rest.client.networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.LogicalLocationEntry;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectType;
import com.hp.ov.sdk.dto.networking.sasinterconnect.SasInterconnectTypeName;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasInterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasInterconnectMapTemplate;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnectgroup.SasLogicalInterconnectGroup;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class SasLogicalInterconnectGroupClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalInterconnectGroupClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID = "8ff0eda0-c9b5-43f3-9e5e-013c509202b8";

    private static final String SAS_LOGICAL_INTERCONNECT_GROUP_NAME = "SAS-Logical-Interconnect-Group-Sample";
    private static final String SAS_LOGICAL_INTERCONNECT_GROUP_NAME_UPDATED
            = SAS_LOGICAL_INTERCONNECT_GROUP_NAME + "_Updated";
    // ================================

    private final SasLogicalInterconnectGroupClient client;
    private final SasInterconnectTypeClient typeClient;

    private SasLogicalInterconnectGroupClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.sasLogicalInterconnectGroup();
        this.typeClient = oneViewClient.sasInterconnectType();
    }

    private void createSasLogicalInterconnectGroup() {
        SasLogicalInterconnectGroup interconnectGroup = buildSasLogicalInterconnectGroup();

        TaskResource task = this.client.create(interconnectGroup);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void getSasLogicalInterconnectGroupById() {
        SasLogicalInterconnectGroup interconnectGroup = client.getById(SAS_LOGICAL_INTERCONNECT_GROUP_RESOURCE_ID);

        LOGGER.info("SAS logical interconnect group returned to client: {}", interconnectGroup.toJsonString());
    }

    private void getAllSasLogicalInterconnectGroups() {
        ResourceCollection<SasLogicalInterconnectGroup> interconnectGroups = client.getAll();

        LOGGER.info("SAS logical interconnect groups returned to client: {}", interconnectGroups.toJsonString());
    }

    private void getSasLogicalInterconnectGroupByName() {
        SasLogicalInterconnectGroup interconnectGroup = client.getByName(SAS_LOGICAL_INTERCONNECT_GROUP_NAME).get(0);

        LOGGER.info("SAS logical interconnect group returned to client: {}", interconnectGroup.toJsonString());
    }

    private void updateSasLogicalInterconnectGroup() {
        SasLogicalInterconnectGroup interconnectGroup = client.getByName(SAS_LOGICAL_INTERCONNECT_GROUP_NAME).get(0);

        interconnectGroup.setName(SAS_LOGICAL_INTERCONNECT_GROUP_NAME_UPDATED);

        TaskResource task = this.client.update(interconnectGroup.getResourceId(), interconnectGroup);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void deleteSasLogicalInterconnectGroup() {
        SasLogicalInterconnectGroup interconnectGroup
                = client.getByName(SAS_LOGICAL_INTERCONNECT_GROUP_NAME_UPDATED).get(0);

        TaskResource task = this.client.delete(interconnectGroup.getResourceId());

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private SasLogicalInterconnectGroup buildSasLogicalInterconnectGroup() {
        SasLogicalInterconnectGroup interconnectGroup = new SasLogicalInterconnectGroup();
        SasInterconnectType interconnectType = typeClient.getByName(
                SasInterconnectTypeName.SYNERGY_12GB_SAS_CONNECTION_MODULE).get(0);

        SasInterconnectMapTemplate interconnectMapTemplate = new SasInterconnectMapTemplate();

        LogicalLocationEntry bayOne = new LogicalLocationEntry(1, null, LocationType.Bay);
        LogicalLocationEntry bayFour = new LogicalLocationEntry(4, null, LocationType.Bay);
        LogicalLocationEntry enclOne = new LogicalLocationEntry(1, null, LocationType.Enclosure);

        LogicalLocation enclOneBayOne = new LogicalLocation();
        enclOneBayOne.setLocationEntries(Lists.newArrayList(bayOne, enclOne));

        SasInterconnectMapEntryTemplate enclOneBayOneEntry = new SasInterconnectMapEntryTemplate();
        enclOneBayOneEntry.setEnclosureIndex(1);
        enclOneBayOneEntry.setPermittedInterconnectTypeUri(interconnectType.getUri());
        enclOneBayOneEntry.setLogicalLocation(enclOneBayOne);

        LogicalLocation enclOneBayFour = new LogicalLocation();
        enclOneBayFour.setLocationEntries(Lists.newArrayList(bayFour, enclOne));

        SasInterconnectMapEntryTemplate enclOneBayFourEntry = new SasInterconnectMapEntryTemplate();
        enclOneBayFourEntry.setEnclosureIndex(1);
        enclOneBayFourEntry.setPermittedInterconnectTypeUri(interconnectType.getUri());
        enclOneBayFourEntry.setLogicalLocation(enclOneBayFour);

        interconnectMapTemplate.setInterconnectMapEntryTemplates(Lists.newArrayList(
                enclOneBayOneEntry, enclOneBayFourEntry));

        interconnectGroup.setInterconnectMapTemplate(interconnectMapTemplate);
        interconnectGroup.setEnclosureType(EnclosureType.SY12000);
        interconnectGroup.setInterconnectBaySet(1);
        interconnectGroup.setEnclosureIndexes(Lists.newArrayList(1));
        interconnectGroup.setName(SAS_LOGICAL_INTERCONNECT_GROUP_NAME);
        interconnectGroup.setType(ResourceCategory.RC_SAS_LOGICAL_INTERCONNECT_GROUP);

        return interconnectGroup;
    }

    public static void main(String[] args) {
        SasLogicalInterconnectGroupClientSample sample = new SasLogicalInterconnectGroupClientSample();

        sample.createSasLogicalInterconnectGroup();
        sample.getSasLogicalInterconnectGroupById();
        sample.getSasLogicalInterconnectGroupByName();
        sample.getAllSasLogicalInterconnectGroups();
        sample.updateSasLogicalInterconnectGroup();
        sample.deleteSasLogicalInterconnectGroup();
    }
}
