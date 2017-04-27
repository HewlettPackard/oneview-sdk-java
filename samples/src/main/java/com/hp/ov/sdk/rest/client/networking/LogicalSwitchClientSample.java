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
import com.hp.ov.sdk.dto.networking.ConnectionProperty;
import com.hp.ov.sdk.dto.networking.logicalswitches.AddLogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.LogicalSwitch;
import com.hp.ov.sdk.dto.networking.logicalswitches.SnmpV1Configuration;
import com.hp.ov.sdk.dto.networking.logicalswitches.SnmpVersion;
import com.hp.ov.sdk.dto.networking.logicalswitches.SwitchCredentialConfiguration;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueFormat;
import com.hp.ov.sdk.dto.networking.logicalswitches.ValueType;
import com.hp.ov.sdk.dto.networking.logicalswitchgroup.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.networking.switches.SwitchManagementConnection;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class LogicalSwitchClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalSwitchClientSample.class);

    // These are variables to be defined by the user
    // ================================
    private static final String LOGICAL_SWITCH_NAME = "Logical-Switch_SAMPLE";
    private static final String LOGICAL_SWITCH_MANAGEMENT_HOST = "172.18.16.1";
    // ================================

    private final LogicalSwitchClient client;
    private final LogicalSwitchGroupClient groupClient;

    private LogicalSwitchClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.client = oneViewClient.logicalSwitch();
        this.groupClient = oneViewClient.logicalSwitchGroup();
    }

    private void createLogicalSwitch() {
        AddLogicalSwitch logicalSwitch = this.buildAddLogicalSwitch();

        TaskResource taskResource = this.client.create(logicalSwitch);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getLogicalSwitch() {
        LogicalSwitch logicalSwitch = this.client.getByName(LOGICAL_SWITCH_NAME).get(0);

        logicalSwitch = client.getById(logicalSwitch.getResourceId());

        LOGGER.info("Logical switch returned to client: {}", logicalSwitch.toJsonString());
    }

    private void getAllLogicalSwitches() {
        ResourceCollection<LogicalSwitch> logicalSwitches = client.getAll();

        LOGGER.info("Logical switches returned to client: {}", logicalSwitches.toJsonString());
    }

    private void getLogicalSwitchByName() {
        LogicalSwitch logicalSwitch = client.getByName(LOGICAL_SWITCH_NAME).get(0);

        LOGGER.info("Logical switch returned to client: {}", logicalSwitch.toJsonString());
    }

    private void updateLogicalSwitch() {
        LogicalSwitch logicalSwitch = this.client.getByName(LOGICAL_SWITCH_NAME).get(0);
        AddLogicalSwitch addLogicalSwitch = this.buildUpdateAddLogicalSwitch(logicalSwitch);

        TaskResource taskResource = this.client.update(logicalSwitch.getResourceId(), addLogicalSwitch);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void deleteLogicalSwitch() {
        LogicalSwitch logicalSwitch = client.getByName(LOGICAL_SWITCH_NAME).get(0);

        TaskResource taskResource = this.client.delete(logicalSwitch.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void refreshLogicalSwitch() {
        LogicalSwitch logicalSwitch = client.getByName(LOGICAL_SWITCH_NAME).get(0);

        TaskResource taskResource = this.client.refresh(logicalSwitch.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private AddLogicalSwitch buildAddLogicalSwitch() {
        LogicalSwitchGroup logicalSwitchGroup =
                this.groupClient.getByName(LogicalSwitchGroupClientSample.LOGICAL_SWITCH_GROUP_NAME).get(0);

        AddLogicalSwitch addLogicalSwitch = new AddLogicalSwitch();
        LogicalSwitch logicalSwitch = new LogicalSwitch();
        SwitchCredentialConfiguration switchCredentialConfiguration = new SwitchCredentialConfiguration();
        SnmpV1Configuration snmpV1Configuration = new SnmpV1Configuration();

        snmpV1Configuration.setCommunityString("public");

        switchCredentialConfiguration.setSnmpPort("161");
        switchCredentialConfiguration.setLogicalSwitchManagementHost(LOGICAL_SWITCH_MANAGEMENT_HOST);
        switchCredentialConfiguration.setSnmpVersion(SnmpVersion.SNMPv1);
        switchCredentialConfiguration.setSnmpV1Configuration(snmpV1Configuration);

        logicalSwitch.setSwitchCredentialConfiguration(Lists.newArrayList(switchCredentialConfiguration));
        logicalSwitch.setLogicalSwitchGroupUri(logicalSwitchGroup.getUri());

        logicalSwitch.setType(ResourceCategory.RC_LOGICAL_SWITCH);
        logicalSwitch.setType(ResourceCategory.RC_LOGICAL_SWITCH_V300);

        logicalSwitch.setName(LOGICAL_SWITCH_NAME);

        SwitchManagementConnection switchManagementConnection = new SwitchManagementConnection();

        ConnectionProperty user = new ConnectionProperty("SshBasicAuthCredentialUser",
                "dcs", ValueFormat.Unknown, ValueType.String);
        ConnectionProperty password = new ConnectionProperty("SshBasicAuthCredentialPassword",
                "dcs", ValueFormat.SecuritySensitive, ValueType.String);

        switchManagementConnection.setConnectionProperties(Lists.newArrayList(user, password));

        addLogicalSwitch.setLogicalSwitchCredentials(Lists.newArrayList(switchManagementConnection));
        addLogicalSwitch.setLogicalSwitch(logicalSwitch);

        return addLogicalSwitch;
    }

    private AddLogicalSwitch buildUpdateAddLogicalSwitch(LogicalSwitch logicalSwitch) {
        AddLogicalSwitch addLogicalSwitch = new AddLogicalSwitch();
        SwitchManagementConnection switchManagementConnection = new SwitchManagementConnection();

        ConnectionProperty user = new ConnectionProperty("SshBasicAuthCredentialUser",
                "dcs", ValueFormat.Unknown, ValueType.String);
        ConnectionProperty password = new ConnectionProperty("SshBasicAuthCredentialPassword",
                "dcs", ValueFormat.SecuritySensitive, ValueType.String);

        switchManagementConnection.setConnectionProperties(Lists.newArrayList(user, password));

        addLogicalSwitch.setLogicalSwitchCredentials(Lists.newArrayList(switchManagementConnection));
        addLogicalSwitch.setLogicalSwitch(logicalSwitch);

        return addLogicalSwitch;
    }

    public static void main(String[] args) {
        
        /* This resource is available only on C7000 */
        LogicalSwitchClientSample sample = new LogicalSwitchClientSample();

        sample.createLogicalSwitch();

        sample.getLogicalSwitch();
        sample.getAllLogicalSwitches();
        sample.getLogicalSwitchByName();
        sample.refreshLogicalSwitch();
        sample.updateLogicalSwitch();
        sample.deleteLogicalSwitch();
    }
}
