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

import com.hp.ov.sdk.adaptors.NetworkSetAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NetworkSetClientImpl implements NetworkSetClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkSetClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final NetworkSetAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private JSONObject jsonObject;

    protected NetworkSetClientImpl(NetworkSetAdaptor adaptor, TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static NetworkSetClient getClient() {
        return new NetworkSetClientImpl(
                new NetworkSetAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public NetworkSets getNetworkSets(final RestParams params, final String resourceId) {
        LOGGER.info("NetworkSetClientImpl : getNetworkSet : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.NETWORK_SETS_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("NetworkSetClientImpl : getNetworkSet : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKSET, null);
        }
        // Call adaptor to convert to DTO

        final NetworkSets networkSetDto = adaptor.buildDto(returnObj);

        LOGGER.debug("NetworkSetClientImpl : getNetworkSet : Name :" + networkSetDto.getName());
        LOGGER.info("NetworkSetClientImpl : getNetworkSet : End");

        return networkSetDto;
    }

    @Override
    public NetworkSetCollection getAllNetworkSets(final RestParams params) {
        LOGGER.info("NetworkSetClientImpl : getAllNetworkSets : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.NETWORK_SETS_URI));

        // call rest client
        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("NetworkSetClientImpl : getAllNetworkSets : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKSETS, null);
        }
        // Call adaptor to convert to DTO

        final NetworkSetCollection networkSetCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("NetworkSetClientImpl : getAllNetworkSets : members count :" + networkSetCollectionDto.getCount());
        LOGGER.info("NetworkSetClientImpl : getAllNetworkSets : End");

        return networkSetCollectionDto;
    }

    @Override
    public NetworkSets getNetworkSetsByName(final RestParams params, final String name) {

        LOGGER.info("NetworkSetClientImpl : getNetworkSetByName : Start");

        final String query = UrlUtils.createFilterString(name);
        LOGGER.debug("NetworkSetClientImpl : getNetworkSetByName : query = " + query);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.NETWORK_SETS_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("NetworkSetClientImpl : getNetworkSetsByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKSET, null);
        }
        // Call adaptor to convert to DTO
        NetworkSets networkSetDto;
        final NetworkSetCollection networkSetCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (networkSetCollectionDto.getCount() != 0) {
            networkSetDto = networkSetCollectionDto.getMembers().get(0);
        } else {
            networkSetDto = null;
        }
        if (networkSetDto == null) {
            LOGGER.error("NetworkSetClientImpl : getNetworkSetByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.NETWORKSET, null);
        }
        LOGGER.info("NetworkSetClientImpl : getNetworkSetsByName : End");

        return networkSetDto;
    }

    @Override
    public TaskResourceV2 createNetworkSet(final RestParams params, final NetworkSets networkSetDto, final boolean aSync,
            final boolean useJsonRequest) {
        LOGGER.info("NetworkSetClientImpl : createNetworkSet : Start");
        String returnObj = null;

        // validate params
        if (networkSetDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.NETWORKSET, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.NETWORK_SETS_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(networkSetDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkSetClientImpl : createNetworkSet : returnObj =" + returnObj);
        LOGGER.debug("NetworkSetClientImpl : createNetworkSet : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("NetworkSetClientImpl : createNetworkSet : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateNetworkSet(final RestParams params, final String resourceId, final NetworkSets networkSetDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("NetworkSetClientImpl : updateNetworkSet : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (networkSetDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.NETWORKSET, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.NETWORK_SETS_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(networkSetDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkSetClientImpl : updateNetworkSet : returnObj =" + returnObj);
        LOGGER.debug("NetworkSetClientImpl : updateNetworkSet : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("NetworkSetClientImpl : updateNetworkSet : End");

        return taskResourceV2;

    }

    @Override
    public TaskResourceV2 deleteNetworkSet(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.info("NetworkSetClientImpl : deleteNetworkSet : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.NETWORK_SETS_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params);
        LOGGER.debug("NetworkSetClientImpl : deleteNetworkSet : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKSET, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkSetClientImpl : deleteNetworkSet : returnObj =" + returnObj);
        LOGGER.debug("NetworkSetClientImpl : deleteNetworkSet : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("NetworkSetClientImpl : deleteNetworkSet : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        NetworkSets networkSets = getNetworkSetsByName(creds, name);

        if (null != networkSets.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(networkSets.getUri());
        }
        return resourceId;
    }

}
