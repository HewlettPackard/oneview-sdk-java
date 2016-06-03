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

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.ServerProfileAdaptor;
import com.hp.ov.sdk.adaptors.ServerProfileTemplateAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class ServerProfileTemplateClientImpl implements ServerProfileTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileTemplateClientImpl.class);
    private static final int TIMEOUT = 1200000; // in milliseconds = 20 mins

    private final ResourceAdaptor resourceAdaptor;
    private final ServerProfileTemplateAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private ServerProfileTemplateClientImpl(HttpRestClient httpClient, ResourceAdaptor resourceAdaptor,
            ServerProfileTemplateAdaptor adaptor, TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.httpClient = httpClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static ServerProfileTemplateClient getClient() {
        return new ServerProfileTemplateClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new ServerProfileTemplateAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public ServerProfileTemplate getServerProfileTemplateById(RestParams params, String resourceId) {
        LOGGER.info("ServerProfileTemplateClientImpl : getServerProfileTemplateById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileTemplateClientImpl : getServerProfileTemplateById : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfileTemplate serverProfileTemplateDto = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("ServerProfileTemplateClientImpl : getServerProfileTemplateById : Name :" + serverProfileTemplateDto.getName());
        LOGGER.info("ServerProfileTemplateClientImpl : getServerProfileTemplateById : End");

        return serverProfileTemplateDto;
    }

    @Override
    public ResourceCollection<ServerProfileTemplate> getAllServerProfileTemplates(RestParams params) {
        LOGGER.info("ServerProfileTemplateClientImpl : getAllServerProfileTemplates : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileTemplateClientImpl : getAllServerProfileTemplates : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATES,
                    null);
        }

        ResourceCollection<ServerProfileTemplate> serverProfileTemplateCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, ServerProfileTemplate.class);

        LOGGER.debug("ServerProfileTemplateClientImpl : getAllServerProfileTemplates : Count :" + serverProfileTemplateCollectionDto.getCount());
        LOGGER.info("ServerProfileTemplateClientImpl : getAllServerProfileTemplates : End");

        return serverProfileTemplateCollectionDto;
    }

    @Override
    public ServerProfileTemplate getServerProfileTemplateByName(RestParams params, String name) {
        ServerProfileTemplate serverProfileTemplateDto = null;
        LOGGER.info("ServerProfileTemplateClientImpl : getServerProfileTemplateByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileTemplateClientImpl : getServerProfileTemplateByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATES,
                    null);
        }

        ResourceCollection<ServerProfileTemplate> serverProfileTemplateCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, ServerProfileTemplate.class);

        if (serverProfileTemplateCollectionDto.getCount() != 0) {
            serverProfileTemplateDto = serverProfileTemplateCollectionDto.getMembers().get(0);
        } else {
            serverProfileTemplateDto = null;
        }

        if (serverProfileTemplateDto == null) {
            LOGGER.error("ServerProfileTemplateClientImpl : getServerProfileTemplateByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATE,
                    null);
        }
        LOGGER.info("ServerProfileTemplateClientImpl : getServerProfileTemplateByName : End");

        return serverProfileTemplateDto;
    }

    @Override
    public ServerProfile getNewServerProfile(RestParams params, String resourceId) {
        LOGGER.info("ServerProfileTemplateClientImpl : getNewServerProfile : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI,
                resourceId,
                SdkConstants.SERVER_PROFILE_TEMPLATE_NEW_PROFILE));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ServerProfileTemplateClientImpl : getNewServerProfile : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATE,
                    null);
        }
        // Call adaptor to convert to DTO
        final ServerProfile serverProfileDto = new ServerProfileAdaptor().buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("ServerProfileTemplateClientImpl : getNewServerProfile : Name :" + serverProfileDto.getName());
        LOGGER.info("ServerProfileTemplateClientImpl : getNewServerProfile : End");

        return serverProfileDto;
    }

    @Override
    public TaskResourceV2 createServerProfileTemplate(RestParams params, ServerProfileTemplate serverProfileTemplateDto,
            boolean aSync) {
        LOGGER.info("ServerProfileTemplateClientImpl : createServerProfileTemplate : Start");
        String returnObj = null;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // validate params
        if (serverProfileTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileTemplateDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileTemplateClientImpl : createServerProfileTemplate : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileTemplateClientImpl : createServerProfileTemplate : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileTemplateClientImpl : createServerProfileTemplate : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateServerProfileTemplate(RestParams params, String resourceId,
            ServerProfileTemplate serverProfileTemplateDto, boolean aSync) {
        LOGGER.info("ServerProfileTemplateClientImpl : updateServerProfileTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (serverProfileTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_PROFILE_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating server profile dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(serverProfileTemplateDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileTemplateClientImpl : updateServerProfileTemplate : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileTemplateClientImpl : updateServerProfileTemplate : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileTemplateClientImpl : updateServerProfileTemplate : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteServerProfileTemplate(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("ServerProfileTemplateClientImpl : deleteServerProfileTemplate : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId));

        String returnObj = httpClient.sendRequest(params);

        LOGGER.debug("ServerProfileTemplateClientImpl : deleteServerProfileTemplate : response from OV : " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_PROFILE_TEMPLATE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ServerProfileTemplateClientImpl : deleteServerProfileTemplate : returnObj =" + returnObj);
        LOGGER.debug("ServerProfileTemplateClientImpl : deleteServerProfileTemplate : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerProfileTemplateClientImpl : deleteServerProfileTemplate : End");

        return taskResourceV2;
    }

}
