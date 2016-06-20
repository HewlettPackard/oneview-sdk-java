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

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class ServerHardwareTypeClientImpl implements ServerHardwareTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHardwareTypeClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    private ServerHardwareTypeClientImpl(HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static ServerHardwareTypeClient getClient() {
        return new ServerHardwareTypeClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public ServerHardwareType getServerHardwareType(RestParams params, String resourceId) {
        LOGGER.trace("ServerHardwareTypeClientImpl : getServerHardwareType : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_TYPE_URI, resourceId));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareTypeClientImpl : getServerHardwareType : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPE, null);
        }

        ServerHardwareType serverHardwareType = adaptor.buildResourceObject(returnObj, ServerHardwareType.class);

        LOGGER.trace("ServerHardwareTypeClientImpl : getServerHardwareType : End");

        return serverHardwareType;
    }

    @Override
    public ResourceCollection<ServerHardwareType> getAllServerHardwareTypes(RestParams params) {
        LOGGER.trace("ServerHardwareTypeClientImpl : getAllServerHardwareTypes : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_TYPE_URI));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareTypeClientImpl : getAllServerHardwareTypes : response from OV : " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPES, null);
        }

        ResourceCollection<ServerHardwareType> serverHardwareTypes
                = adaptor.buildResourceCollection(returnObj, ServerHardwareType.class);

        LOGGER.debug("ServerHardwareTypeClientImpl : getAllServerHardwareTypes : members count : "
                + serverHardwareTypes.getCount());
        LOGGER.trace("ServerHardwareTypeClientImpl : getAllServerHardwareTypes : End");

        return serverHardwareTypes;
    }

    @Override
    public ServerHardwareType getServerHardwareTypeByName(RestParams params, String name) {
        LOGGER.trace("ServerHardwareTypeClientImpl : getServerHardwareTypeByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_TYPE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("ServerHardwareTypeClientImpl : getServerHardwareTypeByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPE, null);
        }

        ServerHardwareType serverHardwareType = null;
        ResourceCollection<ServerHardwareType> collection = adaptor.buildResourceCollection(response,
                ServerHardwareType.class);

        if (collection.getCount() > 0) {
            serverHardwareType = collection.getMembers().get(0);
        }

        if (serverHardwareType == null) {
            LOGGER.warn("ServerHardwareTypeClientImpl : getServerHardwareTypeByName : no server hardware type " +
                    "found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPE, null);
        }

        LOGGER.trace("ServerHardwareTypeClientImpl : getServerHardwareTypeByName : End");

        return serverHardwareType;
    }

    @Override
    public ServerHardwareType updateServerHardwareType(RestParams params, String resourceId,
            ServerHardwareTypeUpdate serverHardwareTypeUpdate) {
        LOGGER.trace("ServerHardwareTypeClientImpl : updateServerHardwareType : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (serverHardwareTypeUpdate == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPE, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_TYPE_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(serverHardwareTypeUpdate, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("ServerHardwareTypeClientImpl : updateServerHardwareType : response = " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPE, null);
        }

        ServerHardwareType serverHardwareType = adaptor.buildResourceObject(response, ServerHardwareType.class);

        LOGGER.trace("ServerHardwareTypeClientImpl : updateServerHardwareType : End");

        return serverHardwareType;
    }

    @Override
    public TaskResourceV2 deleteServerHardwareType(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("ServerHardwareTypeClientImpl : deleteServerHardwareType : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SERVER_HARDWARE_TYPE_URI, resourceId));

        String response = restClient.sendRequest(params);
        LOGGER.debug("ServerHardwareTypeClientImpl : deleteServerHardwareType : response from OV :" + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SERVER_HARDWARE_TYPE, null);
        }

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("ServerHardwareTypeClientImpl : deleteServerHardwareType : taskResource =" + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("ServerHardwareTypeClientImpl : deleteServerHardwareType : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        ServerHardwareType serverHardwareType = getServerHardwareTypeByName(params, name);

        if (null != serverHardwareType.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(serverHardwareType.getUri());
        }
        return resourceId;
    }
}
