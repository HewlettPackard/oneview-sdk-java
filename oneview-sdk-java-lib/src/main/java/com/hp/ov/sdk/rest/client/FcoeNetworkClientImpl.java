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
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.FcoeNetwork;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class FcoeNetworkClientImpl implements FcoeNetworkClient {

    private Logger LOGGER = LoggerFactory.getLogger(FcoeNetworkClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    protected FcoeNetworkClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static FcoeNetworkClient getClient() {
        return new FcoeNetworkClientImpl(HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public FcoeNetwork getFcoeNetwork(RestParams params, String resourceId) {
        LOGGER.trace("FcoeNetworkClientImpl : getFcoeNetwork : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FCOE_NETWORK_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("FcoeNetworkClientImpl : getFcoeNetwork : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FCOE_NETWORK, null);
        }

        FcoeNetwork fcoeNetwork = adaptor.buildResourceObject(response, FcoeNetwork.class);

        LOGGER.trace("FcoeNetworkClientImpl : getFcoeNetwork : End");

        return fcoeNetwork;
    }

    @Override
    public ResourceCollection<FcoeNetwork> getAllFcoeNetworks(RestParams params) {
        LOGGER.trace("FcoeNetworkClientImpl : getAllFcoeNetworks : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FCOE_NETWORK_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("FcoeNetworkClientImpl : getAllFcoeNetworks : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FCOE_NETWORKS, null);
        }

        ResourceCollection<FcoeNetwork> fcoeNetworks = adaptor.buildResourceCollection(response, FcoeNetwork.class);

        LOGGER.trace("FcoeNetworkClientImpl : getAllFcoeNetworks : End");

        return fcoeNetworks;
    }

    @Override
    public FcoeNetwork getFcoeNetworkByName(RestParams params, String name) {
        LOGGER.trace("FcoeNetworkClientImpl : getFcoeNetworkByName : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FCOE_NETWORK_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("FcoeNetworkClientImpl : getFcoeNetworkByName : response from OV : " + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FCOE_NETWORKS, null);
        }

        FcoeNetwork fcoeNetwork = null;
        ResourceCollection<FcoeNetwork> fcoeNetworkCollection = adaptor.buildResourceCollection(returnObj,
                FcoeNetwork.class);

        if (!fcoeNetworkCollection.isEmpty()) {
            fcoeNetwork = fcoeNetworkCollection.getMembers().get(0);
        }

        if (fcoeNetwork == null) {
            LOGGER.error("FcoeNetworkClientImpl : getFcoeNetworkByName : Not found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.FCOE_NETWORK, null);
        }
        LOGGER.trace("FcoeNetworkClientImpl : getFcoeNetworkByName : End");

        return fcoeNetwork;
    }

    @Override
    public TaskResourceV2 createFcoeNetwork(RestParams params, FcoeNetwork fcoeNetwork, boolean aSync) {
        LOGGER.trace("FcoeNetworkClientImpl : createFcoeNetwork : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (fcoeNetwork == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.FCOE_NETWORK, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FCOE_NETWORK_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(fcoeNetwork, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("FcoeNetworkClientImpl : createFcoeNetwork : response = " + response);

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("FcoeNetworkClientImpl : createFcoeNetwork : taskResource = " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("FcoeNetworkClientImpl : createFcoeNetwork : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 updateFcoeNetwork(RestParams params, String resourceId,
            FcoeNetwork fcoeNetwork, boolean aSync) {

        LOGGER.trace("FcoeNetworkClientImpl : updateFcoeNetwork : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (fcoeNetwork == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.FCOE_NETWORK, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.FCOE_NETWORK_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(fcoeNetwork, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("FcoeNetworkClientImpl : updateFcoeNetwork : response = " + response);

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("FcoeNetworkClientImpl : updateFcoeNetwork : taskResource = " + taskResource);

        if (taskResource != null && aSync == false) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }

        LOGGER.trace("FcoeNetworkClientImpl : updateFcoeNetwork : End");

        return taskResource;
    }

    @Override
    public TaskResourceV2 deleteFcoeNetwork(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("FcoeNetworkClientImpl : deleteFcoeNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FCOE_NETWORK_URI, resourceId));

        String response = restClient.sendRequest(params);
        LOGGER.debug("FcoeNetworkClientImpl : deleteFcoeNetwork : response from OV :" + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FCOE_NETWORK, null);
        }

        TaskResourceV2 taskResourceV2 = adaptor.buildResourceObject(response, TaskResourceV2.class);

        LOGGER.debug("FcoeNetworkClientImpl : deleteFcoeNetwork : taskResource =" + taskResourceV2);

        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("FcoeNetworkClientImpl : deleteFcoeNetwork : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        FcoeNetwork fcoeNetwork = this.getFcoeNetworkByName(params, name);

        if (null != fcoeNetwork) {
            resourceId = UrlUtils.getResourceIdFromUri(fcoeNetwork.getUri());
        }
        return resourceId;
    }
}
