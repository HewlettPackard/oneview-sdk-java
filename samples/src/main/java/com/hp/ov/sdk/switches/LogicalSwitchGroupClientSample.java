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

package com.hp.ov.sdk.switches;

import com.google.common.collect.Lists;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.LogicalSwitchGroupCollection;
import com.hp.ov.sdk.dto.SwitchMapEntryTemplate;
import com.hp.ov.sdk.dto.SwitchMapTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalLocation;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.LogicalSwitchGroupClient;
import com.hp.ov.sdk.rest.client.LogicalSwitchGroupClientImpl;
import com.hp.ov.sdk.rest.client.SwitchTypeClient;
import com.hp.ov.sdk.rest.client.SwitchTypeClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

public class LogicalSwitchGroupClientSample {

    // These are variables to be defined by user
    // ================================
    public static final String LOGICAL_SWITCH_GROUP_NAME = "LOGICAL_SWITCH_GROUP-Sample";

    private static final String RESOURCE_ID = "b231a2fe-5fc8-43de-997b-324b7a1fbcca";
    // ================================

    private final LogicalSwitchGroupClient logicalSwitchGroupClient;
    private final SwitchTypeClient switchTypeClient;

    public LogicalSwitchGroupClientSample() {
        this.logicalSwitchGroupClient = LogicalSwitchGroupClientImpl.getClient();
        this.switchTypeClient = SwitchTypeClientImpl.getClient();
    }

    private void getLogicalSwitchGroup() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitchGroup group = this.logicalSwitchGroupClient.getLogicalSwitchGroup(params, RESOURCE_ID);

            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroup : " +
                    "LogicalSwitchGroup object returned to client : " + group);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroup : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroup : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroup : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroup : " +
                    "no response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroup : " +
                    "arguments are null ");
        }
    }

    private void getLogicalSwitchGroupByName() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitchGroup group = this.logicalSwitchGroupClient.getLogicalSwitchGroupByName(params,
                    LOGICAL_SWITCH_GROUP_NAME);

            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroupByName : " +
                    "LogicalSwitchGroup object returned to client : " + group);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroupByName : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroupByName : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroupByName : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroupByName : " +
                    "no response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getLogicalSwitchGroupByName : " +
                    "arguments are null ");
        }
    }

    private void getAllLogicalSwitchGroups() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitchGroupCollection collection = this.logicalSwitchGroupClient.getAllLogicalSwitchGroups(params);

            System.out.println("LogicalSwitchGroupClientSample : getAllLogicalSwitchGroups : " +
                    "LogicalSwitchGroupCollection object returned to client (count) : " + collection.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getAllLogicalSwitchGroups : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getAllLogicalSwitchGroups : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchGroupClientSample : getAllLogicalSwitchGroups : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getAllLogicalSwitchGroups : " +
                    "no response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchGroupClientSample : getAllLogicalSwitchGroups : " +
                    "arguments are null ");
        }
    }

    private void createLogicalSwitchGroup() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitchGroup group = buildLogicalSwitchGroup(params);

            TaskResourceV2 task = this.logicalSwitchGroupClient.createLogicalSwitchGroup(params, group, false);

            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchGroupClientSample : createLogicalSwitchGroup : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private void updateLogicalSwitchGroup() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            LogicalSwitchGroup group = buildUpdateLogicalSwitchGroup(params);

            String resourceId = UrlUtils.getResourceIdFromUri(group.getUri());

            TaskResourceV2 task = this.logicalSwitchGroupClient.updateLogicalSwitchGroup(params, resourceId,
                    group, false);

            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private void deleteLogicalSwitchGroup() {
        RestParams params = null;
        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = this.logicalSwitchGroupClient.getId(params, LOGICAL_SWITCH_GROUP_NAME + "_Updated");

            TaskResourceV2 task = this.logicalSwitchGroupClient.deleteLogicalSwitchGroup(params, resourceId, false);

            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "task object returned to client : " + task);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "the resource you are looking is not found ");
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "no such url : " + params.getUrl());
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "appliance not reachable at : " + params.getHostname());
        } catch (final SDKNoResponseException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "No response from appliance : " + params.getHostname());
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : " +
                    "arguments are null ");
        } catch (final SDKTasksException e) {
            System.out.println("LogicalSwitchGroupClientSample : deleteLogicalSwitchGroup : "
                    + "errors in task, please check task resource for more details");
        }
    }

    private LogicalSwitchGroup buildLogicalSwitchGroup(RestParams params) {
        InterconnectType type = switchTypeClient.getSwitchTypeByName(params, SwitchTypeClientSample.SWITCH_TYPE_NAME);

        LogicalSwitchGroup group = new LogicalSwitchGroup();

        group.setType(ResourceCategory.RC_LOGICAL_SWITCH_GROUP);
        group.setName(LOGICAL_SWITCH_GROUP_NAME);
        group.setState("Active");

        SwitchMapTemplate switchMapTemplate = new SwitchMapTemplate();
        SwitchMapEntryTemplate switchMapEntryTemplate = new SwitchMapEntryTemplate();
        LogicalLocation logicalLocation = new LogicalLocation();

        logicalLocation.setLocationEntries(Lists.newArrayList(new LocationEntry(Integer.valueOf(1), null,
                LocationEntry.Type.StackingMemberId)));
        switchMapEntryTemplate.setLogicalLocation(logicalLocation);
        switchMapEntryTemplate.setPermittedSwitchTypeUri(type.getUri());

        switchMapTemplate.setSwitchMapEntryTemplates(Lists.newArrayList(switchMapEntryTemplate));

        group.setSwitchMapTemplate(switchMapTemplate);

        return group;
    }

    private LogicalSwitchGroup buildUpdateLogicalSwitchGroup(RestParams params) {
        InterconnectType type = this.switchTypeClient.getSwitchTypeByName(params,
                SwitchTypeClientSample.SWITCH_TYPE_NAME);

        LogicalSwitchGroup group = this.logicalSwitchGroupClient.getLogicalSwitchGroupByName(params,
                LOGICAL_SWITCH_GROUP_NAME);

        SwitchMapTemplate switchMapTemplate = group.getSwitchMapTemplate();

        SwitchMapEntryTemplate switchMapEntryTemplate = new SwitchMapEntryTemplate();
        LogicalLocation logicalLocation = new LogicalLocation();

        logicalLocation.setLocationEntries(Lists.newArrayList(new LocationEntry(Integer.valueOf(2),
                null, LocationEntry.Type.StackingMemberId)));
        switchMapEntryTemplate.setLogicalLocation(logicalLocation);
        switchMapEntryTemplate.setPermittedSwitchTypeUri(type.getUri());

        switchMapTemplate.getSwitchMapEntryTemplates().add(switchMapEntryTemplate);

        group.setName(group.getName() + "_Updated");

        return group;
    }

    public static void main(String[] args) {
        LogicalSwitchGroupClientSample client = new LogicalSwitchGroupClientSample();

        client.createLogicalSwitchGroup();
        client.getAllLogicalSwitchGroups();
        client.getLogicalSwitchGroup();
        client.getLogicalSwitchGroupByName();
        client.updateLogicalSwitchGroup();
        client.deleteLogicalSwitchGroup();
    }

}
