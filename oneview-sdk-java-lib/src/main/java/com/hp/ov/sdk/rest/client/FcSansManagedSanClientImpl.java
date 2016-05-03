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

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.FcIssueResponseAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.EndpointResponse;
import com.hp.ov.sdk.dto.EndpointsCsvFileResponse;
import com.hp.ov.sdk.dto.FcSansManagedSanTask;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanRequest;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class FcSansManagedSanClientImpl implements FcSansManagedSanClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSansManagedSanClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private static final String EMPTY_JSON_BODY = "{}";

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    protected FcSansManagedSanClientImpl(HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static FcSansManagedSanClient getClient() {
        return new FcSansManagedSanClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public SanResponse getManagedSan(final RestParams params, final String resourceId) {
        SanResponse sanResponseDto = null;
        LOGGER.trace("ManagedSanClientImpl : getManagedSan : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ManagedSanClientImpl : getManagedSan : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }

        sanResponseDto = adaptor.buildResourceObject(returnObj, SanResponse.class);

        LOGGER.debug("ManagedSanClientImpl : getManagedSan : name :" + sanResponseDto.getName());
        LOGGER.trace("ManagedSanClientImpl : getManagedSan : End");

        return sanResponseDto;
    }

    @Override
    public ResourceCollection<SanResponse> getAllManagedSan(final RestParams params) {
        LOGGER.trace("ManagedSanClientImpl : getAllManagedSan : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ManagedSanClientImpl : getAllManagedSan : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }

        ResourceCollection<SanResponse> sanResponseCollectionDto
                = adaptor.buildResourceCollection(returnObj, SanResponse.class);

        LOGGER.debug("ManagedSanClientImpl : getAllManagedSan : count :" + sanResponseCollectionDto.getCount());

        LOGGER.trace("ManagedSanClientImpl : getAllManagedSan : End");

        return sanResponseCollectionDto;
    }

    @Override
    public SanResponse getManagedSanByName(final RestParams params, final String name) {
        LOGGER.trace("ManagedSanClientImpl : getManagedSanByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ManagedSanClientImpl : getManagedSanByName : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }

        SanResponse sanResponseDto = null;
        ResourceCollection<SanResponse> sanResponseCollectionDto
                = adaptor.buildResourceCollection(returnObj, SanResponse.class);

        if (sanResponseCollectionDto.getCount() != 0) {
            sanResponseDto = sanResponseCollectionDto.getMembers().get(0);
        }

        if (sanResponseDto == null) {
            LOGGER.error("ManagedSanClientImpl : getManagedSanByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        LOGGER.trace("ManagedSanClientImpl : getManagedSanByName : End");

        return sanResponseDto;
    }

    @Override
    public SanResponse updateManagedSan(final RestParams params, final String resourceId, final SanRequest updateSanRequest,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.trace("ManagedSanClientImpl : updateManagedSan : Start");

        // validate params
        if (updateSanRequest == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        JSONObject jsonObject = adaptor.buildJsonRequest(updateSanRequest, params.getApiVersion());
        String returnObj = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("ManagedSanClientImpl : updateManagedSan : returnObj =" + returnObj);

        // convert returnObj to sanResponseDto
        final SanResponse sanResponseDto = adaptor.buildResourceObject(returnObj, SanResponse.class);
        LOGGER.debug("ManagedSanClientImpl : updateManagedSan : SanResponse =" + sanResponseDto);

        LOGGER.trace("ManagedSanClientImpl : updateManagedSan : End");

        return sanResponseDto;
    }

    @Override
    public ResourceCollection<EndpointResponse> getEndpointsOfManagedSan(RestParams params, String resourceId) {
        LOGGER.trace("ManagedSanClientImpl : getEndpointsOfManagedSan : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId, ResourceUris.FC_SANS_MANAGED_SAN_ENDPOINTS));

        String returnObj = restClient.sendRequest(params);
        LOGGER.debug("ManagedSanClientImpl : getEndpointsOfManagedSan : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, params.getUrl(), null);
        }

        ResourceCollection<EndpointResponse> endpointResponseCollection
                 = adaptor.buildResourceCollection(returnObj, EndpointResponse.class);

        LOGGER.debug("ManagedSanClientImpl : getEndpointsOfManagedSan : response =" + endpointResponseCollection);
        LOGGER.trace("ManagedSanClientImpl : getEndpointsOfManagedSan : End");

        return endpointResponseCollection;
    }

    @Override
    public FcSansManagedSanTask createManagedSanIssuesReport(RestParams params, String resourceId, boolean aSync) {
        LOGGER.trace("ManagedSanClientImpl : createManagedSanIssuesReport : Start");
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId, ResourceUris.FC_SANS_MANAGED_SAN_ISSUES));

        final String returnObj = restClient.sendRequest(params, new JSONObject(EMPTY_JSON_BODY));
        LOGGER.debug("ManagedSanClientImpl : createManagedSanIssuesReport : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, params.getUrl(), null);
        }

        TaskResourceV2 task = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("ManagedSanClientImpl : createManagedSanIssuesReport : taskResource =" + task);

        // check for asyncOrSyncMode. if user is asking async mode, return
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update once task is complete.
        if (task != null && aSync == false) {
            task = taskMonitor.checkStatus(params, task.getUri(), TIMEOUT);
        }
        LOGGER.trace("ManagedSanClientImpl : createManagedSanIssuesReport : End");
        return new FcSansManagedSanTask(task, FcIssueResponseAdaptor.getInstance());
    }

    @Override
    public EndpointsCsvFileResponse createEndpointsCsvOfManagedSan(RestParams params, String resourceId) {
        LOGGER.trace("ManagedSanClientImpl : createEndpointsCsvOfManagedSan : Start");
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId, ResourceUris.FC_SANS_MANAGED_SAN_ENDPOINTS));

        String returnObj = restClient.sendRequest(params, new JSONObject(EMPTY_JSON_BODY));
        LOGGER.debug("ManagedSanClientImpl : createEndpointsCsvOfManagedSan : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, params.getUrl(), null);
        }

        EndpointsCsvFileResponse response = adaptor.buildResourceObject(returnObj, EndpointsCsvFileResponse.class);

        LOGGER.debug("ManagedSanClientImpl : createEndpointsCsvOfManagedSan : response =" + response);
        LOGGER.trace("ManagedSanClientImpl : createEndpointsCsvOfManagedSan : End");

        return response;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        SanResponse sanResponseDto = getManagedSanByName(params, name);

        if (null != sanResponseDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(sanResponseDto.getUri());
        }
        return resourceId;
    }

}
