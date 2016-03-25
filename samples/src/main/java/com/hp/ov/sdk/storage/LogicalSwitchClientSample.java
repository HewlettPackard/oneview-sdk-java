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

package com.hp.ov.sdk.storage;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.AddLogicalSwitch;
import com.hp.ov.sdk.dto.LogicalSwitch;
import com.hp.ov.sdk.dto.LogicalSwitchCollection;
import com.hp.ov.sdk.dto.SnmpV1Configuration;
import com.hp.ov.sdk.dto.SnmpVersion;
import com.hp.ov.sdk.dto.SwitchCredentialConfiguration;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ConnectionProperty;
import com.hp.ov.sdk.dto.generated.SwitchManagementConnection;
import com.hp.ov.sdk.dto.generated.ValueFormat;
import com.hp.ov.sdk.dto.generated.ValueType;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.LogicalSwitchClient;
import com.hp.ov.sdk.rest.client.LogicalSwitchClientImpl;
import com.hp.ov.sdk.rest.client.LogicalSwitchGroupClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.switches.LogicalSwitchGroupClientSample;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class LogicalSwitchClientSample {

    // These are variables to be defined by user
    // ================================
    private static final String RESOURCE_ID = "ce938da9-1266-4a4d-bb1f-5581b0f1366e";
    private static final String LOGICAL_SWITCH_NAME = "LOGICAL_SWITCH-Sample";
    private static final String LOGICAL_SWITCH_MANAGEMENT_HOST = "172.18.16.1";
    // ================================

    private final LogicalSwitchClient logicalSwitchClient;

    public LogicalSwitchClientSample() {
        this.logicalSwitchClient = LogicalSwitchClientImpl.getClient();
    }

    private void getLogicalSwitch() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitch logicalSwitch = this.logicalSwitchClient.getLogicalSwitch(params, RESOURCE_ID);

            System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                    "LogicalSwitch object returned to client : " + logicalSwitch);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                    "no response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitch : " +
                    "arguments are null ");
        }
    }

    private void getLogicalSwitchByName() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitch logicalSwitch = this.logicalSwitchClient.getLogicalSwitchByName(params, LOGICAL_SWITCH_NAME);

            System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                    "LogicalSwitch object returned to client : " + logicalSwitch);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                    "no response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : getLogicalSwitchByName : " +
                    "arguments are null ");
        }
    }

    private void getAllLogicalSwitches() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitchCollection collection = this.logicalSwitchClient.getAllLogicalSwitches(params);

            System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                    "LogicalSwitchCollection returned to client (count) : " + collection.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                    "no response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : getAllLogicalSwitches : " +
                    "arguments are null ");
        }
    }

    private void createLogicalSwitch() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            AddLogicalSwitch addLogicalSwitch = buildAddLogicalSwitch();

            TaskResourceV2 task = this.logicalSwitchClient.createLogicalSwitch(params, addLogicalSwitch, false);

            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchClientSample : createLogicalSwitch : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private AddLogicalSwitch buildAddLogicalSwitch() {
        RestParams params = HPOneViewCredential.createCredentials();
        String logicalSwitchGroupId = LogicalSwitchGroupClientImpl.getClient()
                .getId(params, LogicalSwitchGroupClientSample.LOGICAL_SWITCH_GROUP_NAME);

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
        logicalSwitch.setLogicalSwitchGroupUri("/rest/logical-switch-groups/" + logicalSwitchGroupId);
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

    private void updateLogicalSwitch() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            AddLogicalSwitch addLogicalSwitch = buildUpdateAddLogicalSwitch(params);

            addLogicalSwitch.getLogicalSwitch().setName(LOGICAL_SWITCH_NAME + "_Updated");

            TaskResourceV2 task = this.logicalSwitchClient.updateLogicalSwitch(params,
                    UrlUtils.getResourceIdFromUri(addLogicalSwitch.getLogicalSwitch().getUri()), addLogicalSwitch, false);

            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchClientSample : updateLogicalSwitch : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private AddLogicalSwitch buildUpdateAddLogicalSwitch(RestParams params) {
        LogicalSwitch logicalSwitch = this.logicalSwitchClient.getLogicalSwitchByName(params, LOGICAL_SWITCH_NAME);
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

    private void deleteLogicalSwitch() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.logicalSwitchClient.getId(params, LOGICAL_SWITCH_NAME + "_Updated");

            TaskResourceV2 task = this.logicalSwitchClient.deleteLogicalSwitch(params, resourceId, false);

            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchClientSample : deleteLogicalSwitch : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private void refreshLogicalSwitch() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.logicalSwitchClient.getId(params, LOGICAL_SWITCH_NAME);

            TaskResourceV2 task = this.logicalSwitchClient.refreshLogicalSwitch(params, resourceId, false);

            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchClientSample : refreshLogicalSwitch : "
                    + "errors in task, please check task resource for more details");
        }
    }

    public static void main(String[] args) {
        LogicalSwitchClientSample client = new LogicalSwitchClientSample();

        client.createLogicalSwitch();
        client.getLogicalSwitch();
        client.getAllLogicalSwitches();
        client.getLogicalSwitchByName();
        client.refreshLogicalSwitch();
        client.updateLogicalSwitch();
        client.deleteLogicalSwitch();
    }
}
