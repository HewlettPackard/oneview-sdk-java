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

import com.hp.ov.sdk.adaptors.ManagedSanAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.SanResponse;
import com.hp.ov.sdk.dto.SanResponseCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FcSansManagedSanClientImpl implements FcSansManagedSanClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSansManagedSanClientImpl.class);

    private final ManagedSanAdaptor adaptor;

    private JSONObject jsonObject;

    protected FcSansManagedSanClientImpl(ManagedSanAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public static FcSansManagedSanClient getClient() {
        return new FcSansManagedSanClientImpl(new ManagedSanAdaptor());
    }

    @Override
    public SanResponse getManagedSan(final RestParams params, final String resourceId) {
        SanResponse sanResponseDto = null;
        LOGGER.info("ManagedSanClientImpl : getManagedSan : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("ManagedSanClientImpl : getManagedSan : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // Call adaptor to convert to DTO

        sanResponseDto = adaptor.buildDto(returnObj);

        LOGGER.debug("ManagedSanClientImpl : getManagedSan : name :" + sanResponseDto.getName());
        LOGGER.info("ManagedSanClientImpl : getManagedSan : End");

        return sanResponseDto;
    }

    @Override
    public SanResponseCollection getAllManagedSan(final RestParams params) {
        SanResponseCollection sanResponseCollectionDto = null;
        LOGGER.info("ManagedSanClientImpl : getAllManagedSan : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("ManagedSanClientImpl : getAllManagedSan : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // Call adaptor to convert to DTO

        sanResponseCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("ManagedSanClientImpl : getAllManagedSan : count :" + sanResponseCollectionDto.getCount());
        LOGGER.info("ManagedSanClientImpl : getAllManagedSan : End");

        return sanResponseCollectionDto;
    }

    @Override
    public SanResponse getManagedSanByName(final RestParams params, final String name) {
        SanResponse sanResponseDto = null;
        LOGGER.info("ManagedSanClientImpl : getManagedSanByName : Start");
        final String query = UrlUtils.createQueryString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.FC_SANS_MANAGED_SAN_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("ManagedSanClientImpl : getManagedSanByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        // Call adaptor to convert to DTO

        final SanResponseCollection sanResponseCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (sanResponseCollectionDto.getCount() != 0) {
            sanResponseDto = sanResponseCollectionDto.getMembers().get(0);
        } else {
            sanResponseDto = null;
        }

        if (sanResponseDto == null) {
            LOGGER.error("ManagedSanClientImpl : getManagedSanByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.MANAGED_SAN, null);
        }
        LOGGER.info("ManagedSanClientImpl : getManagedSanByName : End");

        return sanResponseDto;
    }

    @Override
    public SanResponse updateManagedSan(final RestParams params, final String resourceId, final SanResponse updateSanResponseDto,
            final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("ManagedSanClientImpl : updateManagedSan : Start");
        String returnObj = null;

        // validate params
        if (updateSanResponseDto == null) {
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
        jsonObject = adaptor.buildJsonObjectFromDto(updateSanResponseDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to sanResponseDto
        final SanResponse sanResponseDto = adaptor.buildDto(returnObj);

        LOGGER.debug("ManagedSanClientImpl : updateManagedSan : returnObj =" + returnObj);
        LOGGER.debug("ManagedSanClientImpl : updateManagedSan : SanResponse =" + sanResponseDto);

        LOGGER.info("ManagedSanClientImpl : updateManagedSan : End");

        return sanResponseDto;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        SanResponse sanResponseDto = getManagedSanByName(creds, name);

        if (null != sanResponseDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(sanResponseDto.getUri());
        }
        return resourceId;
    }

}
