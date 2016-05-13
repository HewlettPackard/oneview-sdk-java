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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class SwitchTypeClientImpl implements SwitchTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchTypeClientImpl.class);

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;

    public SwitchTypeClientImpl(HttpRestClient restClient,
            ResourceAdaptor adaptor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
    }

    public static SwitchTypeClient getClient() {
        return new SwitchTypeClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor());
    }

    @Override
    public InterconnectType getSwitchType(RestParams params, String resourceId) {
        LOGGER.trace("SwitchTypeClientImpl : getSwitchType : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SWITCH_TYPE_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchTypeClientImpl : getSwitchType : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCH_TYPES, null);
        }

        InterconnectType interconnectType = adaptor.buildResourceObject(response, InterconnectType.class);

        LOGGER.trace("SwitchTypeClientImpl : getSwitchType : End");

        return interconnectType;
    }

    @Override
    public InterconnectType getSwitchTypeByName(RestParams params, String name) {
        LOGGER.trace("SwitchTypeClientImpl : getSwitchTypeByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.SWITCH_TYPE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchTypeClientImpl : getSwitchTypeByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCH_TYPES, null);
        }

        InterconnectType interconnectType = null;
        InterconnectTypeCollection typeCollection = adaptor.buildResourceObject(response,
                InterconnectTypeCollection.class);

        if (typeCollection.getCount() > 0) {
            interconnectType = typeCollection.getMembers().get(0);
        }

        if (interconnectType == null) {
            LOGGER.error("SwitchTypeClientImpl : getSwitchTypeByName : no switch type " +
                    "found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.SWITCH_TYPES, null);
        }

        LOGGER.trace("SwitchTypeClientImpl : getSwitchTypeByName : End");

        return interconnectType;
    }

    @Override
    public InterconnectTypeCollection getAllSwitchTypes(RestParams params) {
        LOGGER.trace("SwitchTypeClientImpl : getAllSwitchTypes : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.SWITCH_TYPE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("SwitchTypeClientImpl : getAllSwitchTypes : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.SWITCH_TYPES, null);
        }

        InterconnectTypeCollection interconnectTypes = adaptor.buildResourceObject(response,
                InterconnectTypeCollection.class);

        LOGGER.trace("SwitchTypeClientImpl : getAllSwitchTypes : End");

        return interconnectTypes;
    }

}
