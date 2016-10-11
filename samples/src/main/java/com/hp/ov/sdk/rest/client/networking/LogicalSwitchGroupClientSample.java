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
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.LogicalLocationEntry;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectType;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.SwitchMapEntryTemplate;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.SwitchMapTemplate;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class LogicalSwitchGroupClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalSwitchGroupClientSample.class);

    // ================================
    // These are variables to be defined by user
    protected static final String LOGICAL_SWITCH_GROUP_NAME = "Logical-Switch-Group_SAMPLE";
    // ================================

    private final LogicalSwitchGroupClient client;
    private final SwitchTypeClient typeClient;

    private LogicalSwitchGroupClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.logicalSwitchGroup();
        this.typeClient = oneViewClient.switchType();
    }

    private void createLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = this.buildLogicalSwitchGroup();

        TaskResource task = this.client.create(logicalSwitchGroup);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void getLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = client.getByName(LOGICAL_SWITCH_GROUP_NAME).get(0);

        logicalSwitchGroup = client.getById(logicalSwitchGroup.getResourceId());

        LOGGER.info("Logical switch group returned to client: {}", logicalSwitchGroup.toJsonString());
    }

    private void getAllLogicalSwitchGroups() {
        ResourceCollection<LogicalSwitchGroup> logicalSwitchGroups = client.getAll();

        LOGGER.info("Logical switch groups returned to client: {}", logicalSwitchGroups.toJsonString());
    }

    private void getLogicalSwitchGroupByName() {
        LogicalSwitchGroup logicalSwitchGroup = client.getByName(LOGICAL_SWITCH_GROUP_NAME).get(0);

        LOGGER.info("Logical switch group returned to client: {}", logicalSwitchGroup.toJsonString());
    }

    private void updateLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = client.getByName(LOGICAL_SWITCH_GROUP_NAME).get(0);

        logicalSwitchGroup = this.buildUpdateLogicalSwitchGroup(logicalSwitchGroup);

        TaskResource task = this.client.update(logicalSwitchGroup.getResourceId(), logicalSwitchGroup);

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private void deleteLogicalSwitchGroup() {
        LogicalSwitchGroup logicalSwitchGroup = client.getByName(LOGICAL_SWITCH_GROUP_NAME).get(0);

        TaskResource task = this.client.delete(logicalSwitchGroup.getResourceId());

        LOGGER.info("Task object returned to client: {}", task.toJsonString());
    }

    private LogicalSwitchGroup buildLogicalSwitchGroup() {
        InterconnectType type = this.typeClient.getByName(SwitchTypeClientSample.SWITCH_TYPE_NAME).get(0);

        LogicalSwitchGroup group = new LogicalSwitchGroup();

        group.setType(ResourceCategory.RC_LOGICAL_SWITCH_GROUP);
        group.setType(ResourceCategory.RC_LOGICAL_SWITCH_GROUP_V300);
        group.setName(LOGICAL_SWITCH_GROUP_NAME);
        group.setState("Active");

        SwitchMapTemplate switchMapTemplate = new SwitchMapTemplate();
        SwitchMapEntryTemplate switchMapEntryTemplate = new SwitchMapEntryTemplate();
        LogicalLocation logicalLocation = new LogicalLocation();

        logicalLocation.setLocationEntries(Lists.newArrayList(
                new LogicalLocationEntry(Integer.valueOf(1), null, LocationType.StackingMemberId)));
        switchMapEntryTemplate.setLogicalLocation(logicalLocation);
        switchMapEntryTemplate.setPermittedSwitchTypeUri(type.getUri());

        switchMapTemplate.setSwitchMapEntryTemplates(Lists.newArrayList(switchMapEntryTemplate));

        group.setSwitchMapTemplate(switchMapTemplate);

        return group;
    }
    private LogicalSwitchGroup buildUpdateLogicalSwitchGroup(LogicalSwitchGroup logicalSwitchGroup) {
        InterconnectType type = this.typeClient.getByName(SwitchTypeClientSample.SWITCH_TYPE_NAME).get(0);

        SwitchMapTemplate switchMapTemplate = logicalSwitchGroup.getSwitchMapTemplate();

        SwitchMapEntryTemplate switchMapEntryTemplate = new SwitchMapEntryTemplate();
        LogicalLocation logicalLocation = new LogicalLocation();

        logicalLocation.setLocationEntries(Lists.newArrayList(
                new LogicalLocationEntry(Integer.valueOf(2), null, LocationType.StackingMemberId)));
        switchMapEntryTemplate.setLogicalLocation(logicalLocation);
        switchMapEntryTemplate.setPermittedSwitchTypeUri(type.getUri());

        switchMapTemplate.getSwitchMapEntryTemplates().add(switchMapEntryTemplate);

        return logicalSwitchGroup;
    }

    public static void main(String[] args) {
        LogicalSwitchGroupClientSample sample = new LogicalSwitchGroupClientSample();

        sample.createLogicalSwitchGroup();

        sample.getLogicalSwitchGroup();
        sample.getLogicalSwitchGroupByName();
        sample.getAllLogicalSwitchGroups();
        sample.updateLogicalSwitchGroup();
        sample.deleteLogicalSwitchGroup();
    }
}
