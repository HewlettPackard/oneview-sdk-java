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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class InterconnectTypeClientImpl implements InterconnectTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectTypeClientImpl.class);

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;

    private InterconnectTypeClientImpl(HttpRestClient restClient,
            ResourceAdaptor adaptor) {
        this.restClient = restClient;
        this.adaptor = adaptor;
    }

    public static InterconnectTypeClient getClient() {
        return new InterconnectTypeClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor());
    }

    @Override
    public InterconnectType getInterconnectType(final RestParams params, final String resourceId) {
        LOGGER.trace("InterconnectTypeClientImpl : getInterconnectType : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_TYPE_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);

        LOGGER.debug("InterconnectTypeClientImpl : getInterconnectType : response from OV :" + returnObj);
        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.INTERCONNECT_TYPE, null);
        }

        final InterconnectType interconnectTypeDto = adaptor.buildResourceObject(returnObj, InterconnectType.class);

        LOGGER.debug("InterconnectTypeClientImpl : getInterconnectType : name :" + interconnectTypeDto.getName());
        LOGGER.trace("InterconnectTypeClientImpl : getInterconnectType : End");

        return interconnectTypeDto;
    }

    @Override
    public ResourceCollection<InterconnectType> getAllInterconnectType(final RestParams params) {
        LOGGER.trace("InterconnectTypeClientImpl : getAllInterconnectTypeV2s : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_TYPE_URI));

        // call rest client
        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("InterconnectTypeClientImpl : getAllInterconnectTypes : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.INTERCONNECT_TYPES, null);
        }

        ResourceCollection<InterconnectType> interconnectTypeCollectionDto
                = adaptor.buildResourceCollection(returnObj, InterconnectType.class);

        LOGGER.debug("InterconnectTypeClientImpl : getAllInterconnectTypes : members count :"
                + interconnectTypeCollectionDto.getCount());
        LOGGER.trace("InterconnectTypeClientImpl : getAllInterconnectTypes : End");

        return interconnectTypeCollectionDto;
    }

    @Override
    public InterconnectType getInterconnectTypeByName(final RestParams params, final String name) {
        InterconnectType interconnectTypeDto = null;
        LOGGER.trace("InterconnectTypeClientImpl : getInterconnectTypeByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.INTERCONNECT_TYPE_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("InterconnectTypeClientImpl : getInterconnectTypeByName : response from OV :" + returnObj);
        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.INTERCONNECT_TYPE, null);
        }

        ResourceCollection<InterconnectType> interconnectTypeCollectionDto
                = adaptor.buildResourceCollection(returnObj, InterconnectType.class);

        if (interconnectTypeCollectionDto.getCount() != 0) {
            interconnectTypeDto = interconnectTypeCollectionDto.getMembers().get(0);
        } else {
            interconnectTypeDto = null;
        }

        if (interconnectTypeDto == null) {
            LOGGER.error("InterconnectTypeClientImpl : getInterconnectTypeByName : resource not Found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.INTERCONNECT_TYPE,
                    null);
        }
        LOGGER.trace("InterconnectTypeClientImpl : getInterconnectTypeByName : End");

        return interconnectTypeDto;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        InterconnectType interconnectTypesDto = getInterconnectTypeByName(params, name);

        if (null != interconnectTypesDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(interconnectTypesDto.getUri());
        }
        return resourceId;
    }
}
