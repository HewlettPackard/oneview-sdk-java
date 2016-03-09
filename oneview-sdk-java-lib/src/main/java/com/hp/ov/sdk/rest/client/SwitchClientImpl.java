/*
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
import com.hp.ov.sdk.dto.SwitchCollection;
import com.hp.ov.sdk.dto.SwitchPortStatistics;
import com.hp.ov.sdk.dto.SwitchStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.generated.Switch;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class SwitchClientImpl implements SwitchClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    protected SwitchClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static SwitchClient getClient() {
        return new SwitchClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public Switch getSwitch(final RestParams params, final String resourceId) {
        LOGGER.trace("SwitchClientImpl : getSwitch : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCHES_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchClientImpl : getSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        Switch switchObj = adaptor.buildResourceObject(response, Switch.class);

        LOGGER.trace("SwitchClientImpl : getSwitch : End");

        return switchObj;
    }

    @Override
    public SwitchCollection getAllSwitches(final RestParams params) {
        LOGGER.trace("SwitchClientImpl : getAllSwitches : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCHES_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchClientImpl : getAllSwitches : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        SwitchCollection switches = adaptor.buildResourceObject(response, SwitchCollection.class);

        LOGGER.trace("SwitchClientImpl : getAllSwitches : End");

        return switches;
    }

    @Override
    public TaskResourceV2 createSwitch(RestParams params, Switch switchObj, boolean aSync) {
        LOGGER.trace("SwitchClientImpl : createSwitch : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (switchObj == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCHES_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(switchObj, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("SwitchClientImpl : createSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        TaskResourceV2 task = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if (task != null && aSync == false) {
            task = taskMonitor.checkStatus(params, task.getUri(), TIMEOUT);
        }

        LOGGER.trace("SwitchClientImpl : createSwitch : End");

        return task;
    }

    @Override
    public TaskResourceV2 updateSwitch(RestParams params, String resourceId, Switch switchDto, boolean aSync) {
        LOGGER.trace("SwitchClientImpl : updateSwitch : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (switchDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCHES_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(switchDto, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("SwitchClientImpl : updateSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        TaskResourceV2 task = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if (task != null && aSync == false) {
            task = taskMonitor.checkStatus(params, task.getUri(), TIMEOUT);
        }

        LOGGER.trace("SwitchClientImpl : updateSwitch : End");

        return task;
    }

    @Override
    public TaskResourceV2 deleteSwitch(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("SwitchClientImpl : deleteSwitch : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCHES_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchClientImpl : deleteSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        TaskResourceV2 task = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if (task != null && aSync == false) {
            task = taskMonitor.checkStatus(params, task.getUri(), TIMEOUT);
        }

        LOGGER.trace("SwitchClientImpl : deleteSwitch : End");

        return task;
    }

    @Override
    public TaskResourceV2 refreshSwitch(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("SwitchClientImpl : refreshSwitch : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCHES_URI, resourceId));

        String response = restClient.sendRequest(params, new JSONObject("{}"));

        LOGGER.debug("SwitchClientImpl : refreshSwitch : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        TaskResourceV2 task = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if (task != null && aSync == false) {
            task = taskMonitor.checkStatus(params, task.getUri(), TIMEOUT);
        }

        LOGGER.trace("SwitchClientImpl : refreshSwitch : End");

        return task;
    }

    @Override
    public EnvironmentalConfiguration getSwitchEnvironmentalConfiguration(RestParams params, String resourceId) {
        LOGGER.trace("SwitchClientImpl : getSwitchEnvironmentalConfiguration : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SWITCHES_URI, resourceId,
                ResourceUris.SWITCHES_ENVIRONMENTAL_CONFIGURATION_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchClientImpl : getSwitchEnvironmentalConfiguration : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        EnvironmentalConfiguration environmentalConfiguration = adaptor.buildResourceObject(response,
                EnvironmentalConfiguration.class);

        LOGGER.trace("SwitchClientImpl : getSwitchEnvironmentalConfiguration : End");

        return environmentalConfiguration;
    }

    @Override
    public SwitchStatistics getSwitchStatistics(RestParams params, String resourceId) {
        LOGGER.trace("SwitchClientImpl : getSwitchStatistics : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SWITCHES_URI, resourceId,
                ResourceUris.SWITCHES_STATISTICS_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchClientImpl : getSwitchStatistics : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        SwitchStatistics statistics = adaptor.buildResourceObject(response, SwitchStatistics.class);

        LOGGER.trace("SwitchClientImpl : getSwitchStatistics : End");

        return statistics;
    }

    @Override
    public SwitchPortStatistics getSwitchPortStatistics(RestParams params, String resourceId, String portName) {
        LOGGER.trace("SwitchClientImpl : getSwitchPortStatistics : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SWITCHES_URI, resourceId,
                ResourceUris.SWITCHES_STATISTICS_URI, portName));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchClientImpl : getSwitchPortStatistics : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        SwitchPortStatistics portStatistics = adaptor.buildResourceObject(response, SwitchPortStatistics.class);

        LOGGER.trace("SwitchClientImpl : getSwitchPortStatistics : End");

        return portStatistics;
    }

    @Override
    public Switch getSwitchByName(RestParams params, String name) {
        LOGGER.trace("SwitchClientImpl : getSwitchByName : Start");

        String query = UrlUtils.createFilterString(name);
        LOGGER.debug("SwitchClientImpl : getSwitchByName : query = " + query);

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.SWITCHES_URI, query));

        final String response = restClient.sendRequest(params);
        LOGGER.debug("SwitchClientImpl : getSwitchByName : response from OV :" + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCHES, null);
        }

        Switch switchObj = null;
        SwitchCollection switchCollection = adaptor.buildResourceObject(response, SwitchCollection.class);

        if (switchCollection.getCount() != 0) {
            for (Switch member : switchCollection.getMembers()) {
                if (name.equalsIgnoreCase(member.getName())) {
                    switchObj = member;
                    break;
                }
            }
        }

        if (switchObj == null) {
            LOGGER.error("SwitchClientImpl : getSwitchByName : resource not found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.SWITCHES, null);
        }
        LOGGER.trace("SwitchClientImpl : getSwitchByName : End");

        return switchObj;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Switch switchDto = getSwitchByName(params, name);

        if (null != switchDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(switchDto.getUri());
        }
        return resourceId;
    }

}
