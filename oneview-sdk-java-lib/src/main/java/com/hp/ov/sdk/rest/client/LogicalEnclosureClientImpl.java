/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.LogicalEnclosureAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SupportDump;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.logicalenclosure.AddLogicalEnclosure;
import com.hp.ov.sdk.dto.servers.logicalenclosure.LogicalEnclosure;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalEnclosureClientImpl implements LogicalEnclosureClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnclosureClientImpl.class);
    private static final int TIMEOUT = 300000; // in milliseconds

    private final LogicalEnclosureAdaptor adaptor;
    private final ResourceAdaptor resourceAdaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private LogicalEnclosureClientImpl(HttpRestClient httpClient, LogicalEnclosureAdaptor adaptor, ResourceAdaptor resourceAdaptor,
            TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
        this.resourceAdaptor = resourceAdaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static LogicalEnclosureClient getClient() {
        return new LogicalEnclosureClientImpl(
                HttpRestClient.getClient(),
                new LogicalEnclosureAdaptor(),
                new ResourceAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public LogicalEnclosure getLogicalEnclosure(RestParams params, String resourceId) {

        LOGGER.info("LogicalEnclosureClientImpl : getLogicalEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalEnclosureClientImpl : getLogicalEnclosure : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        final LogicalEnclosure logicalEnclosureDto = adaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : getLogicalEnclosure : name :" + logicalEnclosureDto.getName());
        LOGGER.info("LogicalEnclosureClientImpl : getLogicalEnclosure : End");

        return logicalEnclosureDto;
    }

    @Override
    public ResourceCollection<LogicalEnclosure> getAllLogicalEnclosures(RestParams params) {
        LOGGER.info("LogicalEnclosureClientImpl : getAllLogicalEnclosures : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI));

        // call rest client
        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalEnclosureClientImpl : getAllLogicalEnclosures : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }

        ResourceCollection<LogicalEnclosure> logicalEnclosureListDto =
                resourceAdaptor.buildResourceCollection(returnObj, LogicalEnclosure.class);

        LOGGER.debug("LogicalEnclosureClientImpl : getAllLogicalEnclosures : members count :" + logicalEnclosureListDto.getCount());
        LOGGER.info("LogicalEnclosureClientImpl : getAllLogicalEnclosures : End");

        return logicalEnclosureListDto;
    }

    @Override
    public LogicalEnclosure getLogicalEnclosureByName(RestParams params, String name) {
        LogicalEnclosure logicalEnclosureDto = null;
        LOGGER.info("LogicalEnclosureClientImpl : getLogicalEnclosureByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalEnclosureClientImpl : getLogicalEnclosureByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }

        ResourceCollection<LogicalEnclosure> logicalEnclosureListDto =
                resourceAdaptor.buildResourceCollection(returnObj, LogicalEnclosure.class);

        if (logicalEnclosureListDto.getCount() != 0) {
            logicalEnclosureDto = logicalEnclosureListDto.getMembers().get(0);
        } else {
            logicalEnclosureDto = null;
        }

        if (logicalEnclosureDto == null) {
            LOGGER.error("LogicalEnclosureClientImpl : getLogicalEnclosureByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        LOGGER.info("LogicalEnclosureClientImpl : getLogicalEnclosureByName : End");

        return logicalEnclosureDto;
    }

    @Override
    public TaskResourceV2 createLogicalEnclosure(RestParams params, AddLogicalEnclosure addLogicalEnclosureDto,
            boolean aSync, boolean useJsonRequest) {
        LOGGER.info("LogicalEnclosureClientImpl : createLogicalEnclosure : Start");
        String returnObj = null;

        // validate params
        if (addLogicalEnclosureDto == null || params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(addLogicalEnclosureDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : createLogicalEnclosure : returnObj =" + returnObj);
        LOGGER.debug("LogicalEnclosureClientImpl : createLogicalEnclosure : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalEnclosureClientImpl : createLogicalEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalEnclosure(RestParams params, String resourceId, LogicalEnclosure logicalEnclosureDto,
            boolean aSync, boolean useJsonRequest) {
        LOGGER.info("EnclosureClientImpl : updateLogicalEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (logicalEnclosureDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(logicalEnclosureDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : updateLogicalEnclosure : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : updateLogicalEnclosure : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : updateLogicalEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 patchLogicalEnclosure(RestParams params, String resourceId, Patch patchDto, boolean aSync,
            boolean useJsonRequest) {
        LOGGER.info("EnclosureClientImpl : patchEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (patchDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PATCH);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        JSONArray jsonArray = adaptor.buildJsonArrayDto(patchDto);
        returnObj = httpClient.sendRequest(params, jsonArray);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("EnclosureClientImpl : patchEnclosure : returnObj =" + returnObj);
        LOGGER.debug("EnclosureClientImpl : patchEnclosure : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("EnclosureClientImpl : patchEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteLogicalEnclosure(RestParams params, String resourceId, boolean aSync) {

        LOGGER.info("LogicalEnclosureClientImpl : deleteLogicalEnclosure : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalEnclosureClientImpl : deleteLogicalEnclosure : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : deleteLogicalEnclosure : returnObj =" + returnObj);
        LOGGER.debug("LogicalEnclosureClientImpl : deleteLogicalEnclosure : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalEnclosureClientImpl : deleteLogicalEnclosure : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateFromGroup(RestParams params, String resourceId, boolean aSync) {
        LOGGER.info("EnclosureClientImpl : updateFromGroup : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.UPDATE_FROM_GROUP));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalEnclosureClientImpl : updateFromGroup : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        // Call adaptor to convert to DTO

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : updateFromGroup : returnObj =" + returnObj);
        LOGGER.debug("LogicalEnclosureClientImpl : updateFromGroup : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalEnclosureClientImpl : updateFromGroup : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateConfiguration(RestParams params, String resourceId, boolean aSync) {
        LOGGER.info("LogicalEnclosureClientImpl : updateConfiguration : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId,
                SdkConstants.CONFIGURATION));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("LogicalEnclosureClientImpl : updateConfiguration : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.CONFIGURATION,
                    null);
        }
        // Call adaptor to convert to DTO

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : updateConfiguration : returnObj =" + returnObj);
        LOGGER.debug("LogicalEnclosureClientImpl : updateConfiguration : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalEnclosureClientImpl : updateConfiguration : End");

        return taskResourceV2;
    }

    @Override
    public String getScript(RestParams params, String resourceId) {
        LOGGER.info("LogicalEnclosureClientImpl : getScript : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("EnclosureV2Client : getScript : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SCRIPT, null);
        }

        LOGGER.info("LogicalEnclosureClientImpl : getScript : End");

        return returnObj;
    }

    @Override
    public TaskResourceV2 updateScript(RestParams params, String resourceId, String scriptData, boolean aSync) {
        LOGGER.info("LogicalEnclosureClientImpl : updateScript : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (scriptData == null || scriptData.isEmpty()) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SCRIPT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.SCRIPT));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in updating refreshStateConfigDto dto.

        returnObj = httpClient.sendRequest(params, scriptData);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : updateScript : returnObj =" + returnObj);
        LOGGER.debug("LogicalEnclosureClientImpl : updateScript : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }

        LOGGER.info("LogicalEnclosureClientImpl : updateScript : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 createSupportDump(RestParams params, SupportDump supportDumpDto, String resourceId, boolean aSync,
            boolean useJsonRequest) {
        LOGGER.info("LogicalEnclosureClientImpl : createSupportDump : Start");
        String returnObj = null;

        // validate params
        if (supportDumpDto == null || params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.LOGICAL_ENCLOSURE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.LOGICAL_ENCLOSURE_URI, resourceId, SdkConstants.SUPPORT_DUMP));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(supportDumpDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("LogicalEnclosureClientImpl : createSupportDump : returnObj =" + returnObj);
        LOGGER.debug("LogicalEnclosureClientImpl : createSupportDump : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("LogicalEnclosureClientImpl : createSupportDump : End");

        return taskResourceV2;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        LogicalEnclosure logicalEnclosure = getLogicalEnclosureByName(params, name);

        if (null != logicalEnclosure.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(logicalEnclosure.getUri());
        }
        return resourceId;
    }

}
