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

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanProviderResponse;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;


public class FcSansProviderClientImpl implements FcSansProviderClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSansProviderClientImpl.class);

    private final ResourceAdaptor adaptor;
    private final HttpRestClient httpClient;

    private FcSansProviderClientImpl(HttpRestClient httpClient, ResourceAdaptor adaptor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
    }

    public static FcSansProviderClient getClient() {
        return new FcSansProviderClientImpl(HttpRestClient.getClient(), new ResourceAdaptor());
    }

    @Override
    public ResourceCollection<SanProviderResponse> getAllProviders(final RestParams params) {
        ResourceCollection<SanProviderResponse> sanProviderResponseCollectionDto = null;
        LOGGER.info("ProviderClientImpl : getAllProviders : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_PROVIDER_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("ProviderClientImpl : getAllProviders : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.PROVIDERS, null);
        }

        sanProviderResponseCollectionDto = adaptor.buildResourceCollection(returnObj, SanProviderResponse.class);

        LOGGER.debug("ProviderClientImpl : getAllProviders : count :" + sanProviderResponseCollectionDto.getCount());
        LOGGER.info("ProviderClientImpl : getAllProviders : End");

        return sanProviderResponseCollectionDto;
    }

    @Override
    public SanProviderResponse getProviderByName(final RestParams params, final String displayName) {
        LOGGER.info("ProviderClientImpl : getProviderByName : start");
        ResourceCollection<SanProviderResponse> sanProviderResponseCollectionDto = getAllProviders(params);

        for (final SanProviderResponse sanProviderResponseDto : new ArrayList<>(sanProviderResponseCollectionDto.getMembers())) {
            if (sanProviderResponseDto.getDisplayName().equals(displayName)) {
                System.out.println(sanProviderResponseDto.getName());
                LOGGER.info("ProviderClientImpl : getProviderByName : End");
                return sanProviderResponseDto;
            }
        }
        LOGGER.error("ProviderClientImpl : getProviderByName : resource not Found for name :" + displayName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.PROVIDERS, null);
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        SanProviderResponse sanProviderResponseDto = getProviderByName(params, name);

        if (null != sanProviderResponseDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(sanProviderResponseDto.getUri());
        }
        return resourceId;
    }
}
