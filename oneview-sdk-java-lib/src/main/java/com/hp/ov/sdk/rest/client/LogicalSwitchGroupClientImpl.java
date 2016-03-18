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

package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LogicalSwitchGroup;
import com.hp.ov.sdk.dto.LogicalSwitchGroupCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalSwitchGroupClientImpl implements LogicalSwitchGroupClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalSwitchGroupClientImpl.class);

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    public LogicalSwitchGroupClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static LogicalSwitchGroupClient getClient() {
        return new LogicalSwitchGroupClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance()
        );
    }

    @Override
    public LogicalSwitchGroup getLogicalSwitchGroup(RestParams params, String resourceId) {
        LOGGER.trace("LogicalSwitchGroupClientImpl : getLogicalSwitchGroup : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchGroupClientImpl : getLogicalSwitchGroup : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        LogicalSwitchGroup logicalSwitchGroup = adaptor.buildResourceObject(response, LogicalSwitchGroup.class);

        LOGGER.trace("LogicalSwitchGroupClientImpl : getLogicalSwitchGroup : End");

        return logicalSwitchGroup;
    }

    @Override
    public LogicalSwitchGroup getLogicalSwitchGroupByName(RestParams params, String name) {
        LOGGER.trace("LogicalSwitchGroupClientImpl : getLogicalSwitchGroupByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, UrlUtils.createFilterString(name)));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchGroupClientImpl : getLogicalSwitchGroupByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        LogicalSwitchGroup logicalSwitchGroup = null;
        LogicalSwitchGroupCollection groupCollection = adaptor.buildResourceObject(response,
                LogicalSwitchGroupCollection.class);

        if (groupCollection.getCount() > 0) {
            logicalSwitchGroup = groupCollection.getMembers().get(0);
        }

        if (logicalSwitchGroup == null) {
            LOGGER.error("LogicalSwitchGroupClientImpl : getLogicalSwitchGroupByName : no logical switch group " +
                    "found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        LOGGER.trace("LogicalSwitchGroupClientImpl : getLogicalSwitchGroupByName : End");

        return logicalSwitchGroup;
    }

    @Override
    public LogicalSwitchGroupCollection getAllLogicalSwitchGroups(RestParams params) {
        LOGGER.trace("LogicalSwitchGroupClientImpl : getAllLogicalSwitchGroups : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_SWITCH_GROUPS_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchGroupClientImpl : getAllLogicalSwitchGroups : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        LogicalSwitchGroupCollection collection = adaptor.buildResourceObject(response,
                LogicalSwitchGroupCollection.class);

        LOGGER.trace("LogicalSwitchGroupClientImpl : getAllLogicalSwitchGroups : End");

        return collection;
    }

    @Override
    public TaskResourceV2 createLogicalSwitchGroup(RestParams params, LogicalSwitchGroup logicalSwitchGroup,
            boolean aSync) {
        LOGGER.trace("LogicalSwitchGroupClientImpl : createLogicalSwitchGroup : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (logicalSwitchGroup == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_SWITCH_GROUPS_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(logicalSwitchGroup, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("LogicalSwitchGroupClientImpl : createLogicalSwitchGroup : response = " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchGroupClientImpl : createLogicalSwitchGroup : taskResource = " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchGroupClientImpl : createLogicalSwitchGroup : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 updateLogicalSwitchGroup(RestParams params, String resourceId,
            LogicalSwitchGroup logicalSwitchGroup, boolean aSync) {

        LOGGER.trace("LogicalSwitchGroupClientImpl : updateLogicalSwitchGroup : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (logicalSwitchGroup == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(logicalSwitchGroup, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("LogicalSwitchGroupClientImpl : updateLogicalSwitchGroup : response = " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchGroupClientImpl : updateLogicalSwitchGroup : taskResource = " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchGroupClientImpl : updateLogicalSwitchGroup : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 deleteLogicalSwitchGroup(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("LogicalSwitchGroupClientImpl : deleteLogicalSwitchGroup : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCH_GROUPS_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchGroupClientImpl : deleteLogicalSwitchGroup : response = " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCH_GROUPS, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchGroupClientImpl : deleteLogicalSwitchGroup : taskResource = " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchGroupClientImpl : deleteLogicalSwitchGroup : End");

        return taskResource;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        LogicalSwitchGroup logicalSwitchGroup = this.getLogicalSwitchGroupByName(params, name);

        if (null != logicalSwitchGroup) {
            resourceId = UrlUtils.getResourceIdFromUri(logicalSwitchGroup.getUri());
        }
        return resourceId;
    }

}
