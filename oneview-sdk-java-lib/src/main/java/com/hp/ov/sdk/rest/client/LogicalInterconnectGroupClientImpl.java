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
package com.hp.ov.sdk.rest.client;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.LogicalInterconnectGroupAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalInterconnectGroupClientImpl implements LogicalInterconnectGroupClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(LogicalInterconnectGroupClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    private static final int API_200 = 200;

    private final LogicalInterconnectGroupAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private JSONObject jsonObject;

    protected LogicalInterconnectGroupClientImpl(LogicalInterconnectGroupAdaptor adaptor,
        TaskAdaptor taskAdaptor,
        TaskMonitorManager taskMonitor) {

        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static LogicalInterconnectGroupClient getClient() {
        return new LogicalInterconnectGroupClientImpl(
                new LogicalInterconnectGroupAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    /*
     * (non-Javadoc)
     *
     * @see com.hp.ov.sdk.rest.services.LogicalInterconnectGroupClient#
     * getLogicalInterconnectGroup(com.hp.ov.sdk.rest.client.RestParams,
     * java.lang.String)
     */
    @Override
    public LogicalInterconnectGroups getLogicalInterconnectGroup(final RestParams params, final String resourceId) {

        LOGGER.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO
        final LogicalInterconnectGroups logicalInterconnectGroupDto = adaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : Name :"
                + logicalInterconnectGroupDto.getName());
        LOGGER.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : End");

        return logicalInterconnectGroupDto;
    }

    @Override
    public LogicalInterconnectGroupCollectionV2 getAllLogicalInterconnectGroups(final RestParams params) {
        LOGGER.info("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO

        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : members count :"
                + logicalInterconnectGroupCollectionDto.getCount());
        LOGGER.info("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : End");

        return logicalInterconnectGroupCollectionDto;
    }

    @Override
    public LogicalInterconnectGroups getLogicalInterconnectGroupByName(final RestParams params, final String name) {
        LOGGER.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : Start");
        // String query = "filter=\"name=\'" + name + "\'\"";
        final String query = UrlUtils.createFilterString(name);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : query = " + query);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO
        LogicalInterconnectGroups logicalInterconnectGroupDto;
        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (logicalInterconnectGroupCollectionDto.getCount() != 0) {
            logicalInterconnectGroupDto = logicalInterconnectGroupCollectionDto.getMembers().get(0);
        } else {
            logicalInterconnectGroupDto = null;
        }

        if (logicalInterconnectGroupDto == null) {
            LOGGER.error("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : resource not found for name :"
                    + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUPS, null);
        }
        LOGGER.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : End");

        return logicalInterconnectGroupDto;
    }

    @Override
    public TaskResourceV2 createLogicalInterconnectGroup(final RestParams params,
            final LogicalInterconnectGroups logicalInterconnectGroupDto, final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : Start");
        String returnObj = null;

        // validate params
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        if (logicalInterconnectGroupDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(logicalInterconnectGroupDto, params.getApiVersion());
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectGroup(final RestParams params, final String resourceId,
            final List<UplinkSet> uplinkSetDto, final boolean aSync, final boolean useJsonRequest) {

        LOGGER.info("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : Start");
        String returnObj = null;

        // validate params
        if (uplinkSetDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.UPLINKSET, null);
        }

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating dto.

        // fetch LIG data using resourceId and create JSON request from dto
        final LogicalInterconnectGroups logicalInterconnectGroupDto = getLogicalInterconnectGroup(params, resourceId);
        logicalInterconnectGroupDto.setUplinkSets(uplinkSetDto);
        jsonObject = adaptor.buildJsonObjectFromDto(logicalInterconnectGroupDto, params.getApiVersion());

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : End");
        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteLogicalInterconnectGroup(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectGroupClient : deleteLogicalInterconnectGroup : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : returnObj =" + returnObj);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : End");

        return taskResourceV2;
    }

    @Override
    public InterconnectSettingsV2 getDefaultInterconnectSettings(final RestParams params) {
        LOGGER.info("LogicalInterconnectGroupClientImpl : getDefaultInterconnectSettings : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI,
                SdkConstants.DEFAULT_SETTINGS));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : getDefaultInterconnectSettings : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO
        final InterconnectSettingsV2 interconnectSettingsDto = adaptor.buildInterconnectSettingsDto(returnObj);

        LOGGER.info("LogicalInterconnectGroupClientImpl : getDefaultInterconnectSettings : End");

        return interconnectSettingsDto;
    }

    @Override
    public InterconnectSettingsV2 getInterconnectSettings(final RestParams params, final String resourceId) {
        return this.getInterconnectSettings(params, resourceId, "placeholder");
    }

    @Override
    public InterconnectSettingsV2 getInterconnectSettings(final RestParams params, final String resourceId, final String settingId) {
        LOGGER.info("LogicalInterconnectGroupClientImpl : getInterconnectSettings : Start");

        // validate args
        if (null == params || resourceId.equals("") || settingId.equals("")) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        if (params.getApiVersion() < API_200) {
            params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId,
                    SdkConstants.SETTINGS, settingId));
        } else {
            params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId,
                    SdkConstants.SETTINGS));
        }

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("LogicalInterconnectGroupClientImpl : getInterconnectSettings : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO
        final InterconnectSettingsV2 interconnectSettingsDto = adaptor.buildInterconnectSettingsDto(returnObj);

        LOGGER.info("LogicalInterconnectGroupClientImpl : getInterconnectSettings : End");

        return interconnectSettingsDto;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        LogicalInterconnectGroups logicalInterconnectGroupDto = getLogicalInterconnectGroupByName(creds, name);

        if (null != logicalInterconnectGroupDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(logicalInterconnectGroupDto.getUri());
        }
        return resourceId;
    }

}
