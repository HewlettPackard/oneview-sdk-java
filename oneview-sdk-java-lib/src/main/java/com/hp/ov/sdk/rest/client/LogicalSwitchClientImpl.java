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
import com.hp.ov.sdk.dto.AddLogicalSwitch;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LogicalSwitch;
import com.hp.ov.sdk.dto.LogicalSwitchCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalSwitchClientImpl implements LogicalSwitchClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalSwitchClientImpl.class);

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    public LogicalSwitchClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static LogicalSwitchClientImpl getClient() {
        return new LogicalSwitchClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance()
        );
    }

    @Override
    public LogicalSwitch getLogicalSwitch(RestParams params, String resourceId) {
        LOGGER.trace("LogicalSwitchClientImpl : getLogicalSwitch : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_SWITCHES_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchClientImpl : getLogicalSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        LogicalSwitch logicalSwitch = adaptor.buildResourceObject(response, LogicalSwitch.class);

        LOGGER.trace("LogicalSwitchClientImpl : getLogicalSwitch : End");

        return logicalSwitch;
    }

    @Override
    public LogicalSwitch getLogicalSwitchByName(RestParams params, String name) {
        LOGGER.trace("LogicalSwitchClientImpl : getLogicalSwitchByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, UrlUtils.createFilterString(name)));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchClientImpl : getLogicalSwitchByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        LogicalSwitch logicalSwitch = null;
        LogicalSwitchCollection groupCollection = adaptor.buildResourceObject(response, LogicalSwitchCollection.class);

        if (groupCollection.getCount() > 0) {
            logicalSwitch = groupCollection.getMembers().get(0);
        }

        if (logicalSwitch == null) {
            LOGGER.error("LogicalSwitchClientImpl : getLogicalSwitchByName : no logical switch " +
                    "found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        LOGGER.trace("LogicalSwitchClientImpl : getLogicalSwitchByName : End");

        return logicalSwitch;
    }

    @Override
    public LogicalSwitchCollection getAllLogicalSwitches(RestParams params) {
        LOGGER.trace("LogicalSwitchClientImpl : getAllLogicalSwitches : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_SWITCHES_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchClientImpl : getAllLogicalSwitches : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        LogicalSwitchCollection collection = adaptor.buildResourceObject(response, LogicalSwitchCollection.class);

        LOGGER.trace("LogicalSwitchClientImpl : getAllLogicalSwitches : End");

        return collection;
    }

    @Override
    public TaskResourceV2 createLogicalSwitch(RestParams params, AddLogicalSwitch addLogicalSwitch, boolean aSync) {
        LOGGER.trace("LogicalSwitchClientImpl : createLogicalSwitch : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (addLogicalSwitch == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_SWITCHES_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(addLogicalSwitch, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("LogicalSwitchClientImpl : createLogicalSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchClientImpl : createLogicalSwitch : taskResource : " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchClientImpl : createLogicalSwitch : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 updateLogicalSwitch(RestParams params, String resourceId,
            AddLogicalSwitch addLogicalSwitch, boolean aSync) {

        LOGGER.trace("LogicalSwitchClientImpl : updateLogicalSwitch : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (addLogicalSwitch == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(addLogicalSwitch, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("LogicalSwitchClientImpl : updateLogicalSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchClientImpl : updateLogicalSwitch : taskResource : " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchClientImpl : updateLogicalSwitch : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 deleteLogicalSwitch(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("LogicalSwitchClientImpl : deleteLogicalSwitch : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_SWITCHES_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchClientImpl : deleteLogicalSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchClientImpl : deleteLogicalSwitch : taskResource : " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchClientImpl : deleteLogicalSwitch : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 refreshLogicalSwitch(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("LogicalSwitchClientImpl : refreshLogicalSwitch : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_SWITCHES_URI, resourceId, ResourceUris.LOGICAL_SWITCHES_REFRESH_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("LogicalSwitchClientImpl : refreshLogicalSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_SWITCHES, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("LogicalSwitchClientImpl : refreshLogicalSwitch : taskResource : " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("LogicalSwitchClientImpl : refreshLogicalSwitch : End");

        return taskResource;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        LogicalSwitch logicalSwitch = this.getLogicalSwitchByName(params, name);

        if (null != logicalSwitch) {
            resourceId = UrlUtils.getResourceIdFromUri(logicalSwitch.getUri());
        }
        return resourceId;
    }
}
