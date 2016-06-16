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

import com.google.common.collect.Lists;
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.AddLogicalSwitch;
import com.hp.ov.sdk.dto.LogicalSwitch;
import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SnmpV1Configuration;
import com.hp.ov.sdk.dto.SnmpVersion;
import com.hp.ov.sdk.dto.SwitchCredentialConfiguration;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ConnectionProperty;
import com.hp.ov.sdk.dto.generated.SwitchManagementConnection;
import com.hp.ov.sdk.dto.generated.ValueFormat;
import com.hp.ov.sdk.dto.generated.ValueType;
import com.hp.ov.sdk.rest.client.OneViewClient;

public class LogicalSwitchClientSample {

    // These are variables to be defined by the user
    // ================================
    private static final String LOGICAL_SWITCH_RESOURCE_ID = "060af776-fb29-4d6c-a1f1-7e513ab4e677";
    private static final String LOGICAL_SWITCH_NAME = "Logical-Switch_SAMPLE";
    private static final String LOGICAL_SWITCH_MANAGEMENT_HOST = "172.18.16.1";
    // ================================

    private final LogicalSwitchClient client;
    private final LogicalSwitchGroupClient groupClient;

    private LogicalSwitchClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.client = oneViewClient.logicalSwitch();
        this.groupClient = oneViewClient.logicalSwitchGroup();
    }

    private void createLogicalSwitch() {
        AddLogicalSwitch logicalSwitch = this.buildAddLogicalSwitch();

        TaskResourceV2 task = this.client.create(logicalSwitch, false);

        System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                "Task object returned to client : " + task);
    }

    private void getLogicalSwitch() {
        LogicalSwitch logicalSwitch = client.getById(LOGICAL_SWITCH_RESOURCE_ID);

        System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                "LogicalSwitch object returned to client : " + logicalSwitch);
    }

    private void getAllLogicalSwitches() {
        ResourceCollection<LogicalSwitch> logicalSwitches = client.getAll();

        System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                "LogicalSwitches returned to client (count) : " + logicalSwitches.getCount());
    }

    private void getLogicalSwitchByName() {
        LogicalSwitch logicalSwitch = client.getByName(LOGICAL_SWITCH_NAME);

        System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                "LogicalSwitch object returned to client : " + logicalSwitch);
    }

    private void updateLogicalSwitch() {
        LogicalSwitch logicalSwitch = this.client.getByName(LOGICAL_SWITCH_NAME);
        AddLogicalSwitch addLogicalSwitch = this.buildUpdateAddLogicalSwitch(logicalSwitch);

        TaskResourceV2 task = this.client.update(logicalSwitch.getResourceId(), addLogicalSwitch, false);

        System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                "Task object returned to client : " + task);
    }

    private void deleteLogicalSwitch() {
        LogicalSwitch logicalSwitch = client.getByName(LOGICAL_SWITCH_NAME);

        TaskResourceV2 task = this.client.delete(logicalSwitch.getResourceId(), false);

        System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                "Task object returned to client : " + task);
    }

    private void refreshLogicalSwitch() {
        LogicalSwitch logicalSwitch = client.getByName(LOGICAL_SWITCH_NAME);

        TaskResourceV2 task = this.client.refresh(logicalSwitch.getResourceId(), false);

        System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                "Task object returned to client : " + task);
    }

    private AddLogicalSwitch buildAddLogicalSwitch() {
        LogicalSwitchGroup logicalSwitchGroup =
                this.groupClient.getByName(LogicalSwitchGroupClientSample.LOGICAL_SWITCH_GROUP_NAME);

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
        logicalSwitch.setLogicalSwitchGroupUri(ResourceUris.LOGICAL_SWITCH_GROUPS_URI
                + logicalSwitchGroup.getResourceId());
        logicalSwitch.setType(ResourceCategory.RC_LOGICAL_SWITCHES);
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
