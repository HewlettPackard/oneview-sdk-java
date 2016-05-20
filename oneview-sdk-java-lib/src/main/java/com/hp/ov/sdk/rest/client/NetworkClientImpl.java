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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.NetworkAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.BulkEthernetNetwork;
import com.hp.ov.sdk.dto.generated.Network;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class NetworkClientImpl implements NetworkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NetworkClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final NetworkAdaptor adaptor;
    private final ResourceAdaptor resourceAdaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private NetworkClientImpl(HttpRestClient restClient,
            NetworkAdaptor adaptor,
            ResourceAdaptor resourceAdaptor,
            TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.resourceAdaptor = resourceAdaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static NetworkClient getClient() {
        return new NetworkClientImpl(
                HttpRestClient.getClient(),
                new NetworkAdaptor(),
                new ResourceAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public Network getNetwork(final RestParams params, final String resourceId) {
        LOGGER.trace("NetworkClientImpl : getNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ETHERNET_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("NetworkClient : getNetwork : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }
        final Network networkDto = adaptor.buildDto(returnObj);

        LOGGER.debug("NetworkClient : getNetwork : vlanID :" + networkDto.getVlanId());
        LOGGER.trace("NetworkClientImpl : getNetwork : End");

        return networkDto;
    }

    @Override
    public ResourceCollection<Network> getAllNetworks(final RestParams params) {
        LOGGER.trace("NetworkClientImpl : getAllNetworks : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ETHERNET_URI));

        // call rest client
        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("NetworkClientImpl : getAllNetworks : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }
        ResourceCollection<Network> networkCollection = resourceAdaptor.buildResourceCollection(returnObj, Network.class);

        LOGGER.debug("NetworkClient : getAllNetworks : members count :" + networkCollection.getCount());
        LOGGER.trace("NetworkClientImpl : getAllNetworks : End");

        return networkCollection;
    }

    @Override
    public Network getNetworkByName(final RestParams params, final String name) {
        LOGGER.trace("NetworkClientImpl : getNetworkByName : Start");


        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        LOGGER.debug("NetworkClientImpl : getNetworkByName : query = " + query);
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ETHERNET_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("NetworkClientImpl : getNetworkByName : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }

        Network networkDto = null;
        ResourceCollection<Network> networkCollection = resourceAdaptor.buildResourceCollection(returnObj, Network.class);

        LOGGER.debug("total matches: " + networkCollection.getCount());
        if (networkCollection.getCount() != 0) {
            networkDto = networkCollection.getMembers().get(0);
        }

        if (networkDto == null) {
            LOGGER.error("NetworkClientImpl : getNetworkByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.NETWORKS, null);
        }
        LOGGER.trace("NetworkClientImpl : getNetworkByName : End");

        return networkDto;
    }

    @Override
    public TaskResourceV2 createNetwork(final RestParams params, final Network dto, final boolean aSync,
            final boolean useJsonRequest) {
        LOGGER.trace("NetworkClientImpl : createNetwork : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                SdkConstants.APPLIANCE, null);
        } else if (dto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                SdkConstants.NETWORK, null);
        }

        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ETHERNET_URI));

        // check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.
        JSONObject jsonObject = null;

        if (useJsonRequest) {
            Network networkDto = adaptor.buildDto(dto.getJsonRequest().getBody());
            // create json object
            networkDto.setConnectionTemplate(null);
            jsonObject = adaptor.buildJsonObjectFromDto(networkDto, params.getApiVersion());
        } else {
            dto.setConnectionTemplate(null);
            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(dto, params.getApiVersion());
        }

        String returnObj = restClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkClientImpl : createNetwork : returnObj = " + returnObj);
        LOGGER.debug("NetworkClientImpl : createNetwork : taskResource = " + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("NetworkClientImpl : createNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateNetwork(final RestParams params, final String resourceId, final Network dto,
            final boolean asyncOrSyncMode, final boolean useJsonRequest) {
        LOGGER.trace("NetworkClientImpl : updateNetwork : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        } else if (dto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.NETWORK, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ETHERNET_URI, resourceId));

        // check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.
        JSONObject jsonObject = null;

        if (useJsonRequest) {
            Network networkDto = adaptor.buildDto(dto.getJsonRequest().getBody());
            // create json object
            jsonObject = adaptor.buildJsonObjectFromDto(networkDto, params.getApiVersion());
        } else {
            // create JSON request from dto
            jsonObject = adaptor.buildJsonObjectFromDto(dto, params.getApiVersion());
        }

        String returnObj = restClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkClientImpl : updateNetwork : returnObj =" + returnObj);
        LOGGER.debug("NetworkClientImpl : updateNetwork : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("NetworkClientImpl : updateNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteNetwork(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.trace("NetworkClientImpl : deleteNetwork : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.ETHERNET_URI, resourceId));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("NetworkClient : deleteNetwork : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkClientImpl : deleteNetwork : returnObj =" + returnObj);
        LOGGER.debug("NetworkClientImpl : deleteNetwork : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("NetworkClientImpl : deleteNetwork : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 createNetworkInBulk(final RestParams params, final BulkEthernetNetwork bulkEthernetDto,
            final boolean aSync, final boolean useJsonRequest) {

        LOGGER.trace("NetworkClientImpl : createNetworkInBulk : Start");

        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (bulkEthernetDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.BULK_ETHERNET_NETWORK, null);
        }

        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.BULK_ETHERNET_URI));

        // check for json request in the input bulkEthernetDto. if it is
        // present, then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network bulkEthernetDto.

        JSONObject jsonObject = null;

        if (useJsonRequest) {
            final BulkEthernetNetwork bulkEthernetNetworkDto = adaptor.buildBulkEthernetDto(
                    bulkEthernetDto.getJsonRequest().getBody());
            jsonObject = adaptor.buildJsonObjectFromBulkEthernetDto(bulkEthernetNetworkDto);
        } else {
            // create JSON request from bulkEthernetDto
            jsonObject = adaptor.buildJsonObjectFromBulkEthernetDto(bulkEthernetDto);
        }
        String returnObj = restClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("NetworkClientImpl : createNetworkInBulk : returnObj =" + returnObj);
        LOGGER.debug("NetworkClientImpl : createNetworkInBulk : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.trace("NetworkClientImpl : createNetworkInBulk : End");

        return taskResourceV2;
    }

    @Override
    public List<String> getNetworkAssociatedProfiles(RestParams params, String resourceId) {
        LOGGER.trace("NetworkClientImpl : getNetworkAssociatedProfiles : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI, resourceId, ResourceUris.ASSOCIATED_PROFILES));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("NetworkClient : getNetworkAssociatedProfiles : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }

        List<String> uris = adaptor.buildCollectionOfUris(returnObj);

        LOGGER.debug("NetworkClient : getNetworkAssociatedProfiles : uris :" + uris);
        LOGGER.trace("NetworkClientImpl : getNetworkAssociatedProfiles : End");

        return uris;
    }

    @Override
    public List<String> getNetworkAssociatedUplinkGroups(RestParams params, String resourceId) {
        LOGGER.trace("NetworkClientImpl : getNetworkAssociatedUplinkGroups : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.ETHERNET_URI, resourceId, ResourceUris.ASSOCIATED_UPLINK_GROUPS));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("NetworkClient : getNetworkAssociatedUplinkGroups : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.NETWORKS, null);
        }

        List<String> uris = adaptor.buildCollectionOfUris(returnObj);

        LOGGER.debug("NetworkClient : getNetworkAssociatedUplinkGroups : uris :" + uris);
        LOGGER.trace("NetworkClientImpl : getNetworkAssociatedUplinkGroups : End");

        return uris;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        Network network = getNetworkByName(params, name);

        if (null != network.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(network.getUri());
        }
        return resourceId;
    }

}
