/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

import com.hp.ov.sdk.adaptors.FcNetworkAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class FcNetworkClientImpl implements FcNetworkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcNetworkClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final ResourceAdaptor resourceAdaptor;
    private final FcNetworkAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    protected FcNetworkClientImpl(HttpRestClient httpClient, ResourceAdaptor resourceAdaptor,
            FcNetworkAdaptor adaptor, TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {

        this.httpClient = httpClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static FcNetworkClient getClient() {
        return new FcNetworkClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new FcNetworkAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public FcNetwork getFcNetwork(final RestParams params, final String resourceId) {
        LOGGER.info("FcNetworkClientImpl : getFcNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FcNetworkClientImpl : getFcNetwork : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORK, null);
        }
        // Call adaptor to convert to DTO

        final FcNetwork fcNetworkDto = adaptor.buildDto(returnObj);

        LOGGER.debug("FcNetworkClientImpl : getFcNetwork : name :" + fcNetworkDto.getName());
        LOGGER.info("FcNetworkClientImpl : getFcNetwork : End");

        return fcNetworkDto;
    }

    @Override
    public ResourceCollection<FcNetwork> getFcNetworkByFilter(final RestParams params, final Integer start, final Integer count) {
        LOGGER.info("FcNetworkClientImpl : getFcNetworkByFilter : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("start", start.toString());
        query.put("count", count.toString());
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FcNetworkClientImpl : getFcNetworkByFilter : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORKS, null);
        }

        ResourceCollection<FcNetwork> fcNetworkCollectionDto = resourceAdaptor.buildResourceCollection(returnObj,
                FcNetwork.class);

        LOGGER.debug("FcNetworkClientImpl : getFcNetworkByFilter : count :" + fcNetworkCollectionDto.getCount());
        LOGGER.info("FcNetworkClientImpl : getFcNetworkByFilter : End");

        return fcNetworkCollectionDto;
    }

    @Override
    public ResourceCollection<FcNetwork> getAllFcNetworks(final RestParams params) {
        LOGGER.info("FcNetworkClientImpl : getAllFcNetworks : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FcNetworkClientImpl : getAllFcNetworks : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        ResourceCollection<FcNetwork> fcNetworkCollectionDto = resourceAdaptor.buildResourceCollection(returnObj,
                FcNetwork.class);

        LOGGER.debug("FcNetworkClientImpl : getAllFcNetworks : count :" + fcNetworkCollectionDto.getCount());
        LOGGER.info("FcNetworkClientImpl : getAllFcNetworks : End");

        return fcNetworkCollectionDto;
    }

    @Override
    public FcNetwork getFcNetworkByName(final RestParams params, final String name) {

        LOGGER.info("FcNetworkClientImpl : getFcNetworkByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FcNetworkClientImpl : getFcNetworkByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // Call adaptor to convert to DTO

        ResourceCollection<FcNetwork> fcNetworkCollectionDto = resourceAdaptor.buildResourceCollection(returnObj,
                FcNetwork.class);

        FcNetwork fcNetworkDto;
        if (!fcNetworkCollectionDto.isEmpty()) {
            fcNetworkDto = fcNetworkCollectionDto.getMembers().get(0);
        } else {
            fcNetworkDto = null;
        }

        if (fcNetworkDto == null) {
            LOGGER.error("FcNetworkClientImpl : getFcNetworkByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.FC_NETWORK, null);
        }
        LOGGER.info("FcNetworkClientImpl : getFcNetworkByName : End");

        return fcNetworkDto;
    }

    @Override
    public TaskResourceV2 createFcNetwork(final RestParams params, final FcNetwork fcNetworkDto, final boolean aSync,
            final boolean useJsonRequest) {
        LOGGER.info("FcNetworkClientImpl : createFcNetwork : Start");
        String returnObj = null;

        // validate params
        if (fcNetworkDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI));

        // check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        if (useJsonRequest == true) {
            final FcNetwork dto = adaptor.buildDto(fcNetworkDto.getJsonRequest().getBody());
            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(dto);

        } else {

            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(fcNetworkDto);

        }
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("FcNetworkClientImpl : createFcNetwork : returnObj =" + returnObj);
        LOGGER.debug("FcNetworkClientImpl : createFcNetwork : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("FcNetworkClientImpl : createFcNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateFcNetwork(final RestParams params, final String resourceId, final FcNetwork fcNetworkDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("FcNetworkClientImpl : updateFcNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (fcNetworkDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.FC_NETWORKS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating server profile dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(fcNetworkDto);
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("FcNetworkClientImpl : updateFcNetwork : returnObj =" + returnObj);
        LOGGER.debug("FcNetworkClientImpl : updateFcNetwork : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("FcNetworkClientImpl : updateFcNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteFcNetwork(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("FcNetworkClientImpl : deleteFcNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_NETWORK_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FcNetworkClientImpl : deleteFcNetwork : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FC_NETWORK, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("FcNetworkClientImpl : deleteFcNetwork : returnObj =" + returnObj);
        LOGGER.debug("FcNetworkClientImpl : deleteFcNetwork : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("FcNetworkClientImpl : deleteFcNetwork : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        FcNetwork fcNetwork = getFcNetworkByName(params, name);

        if (null != fcNetwork.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(fcNetwork.getUri());
        }
        return resourceId;
    }
}
