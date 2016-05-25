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

import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.Fabric;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class FabricClientImpl implements FabricClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FabricClientImpl.class);

    private final HttpRestClient httpClient;
    private final ResourceAdaptor adaptor;

    private JSONObject jsonObject;

    private FabricClientImpl(HttpRestClient httpClient, ResourceAdaptor adaptor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
    }

    public static FabricClient getClient() {
        return new FabricClientImpl(HttpRestClient.getClient(), new ResourceAdaptor());
    }

    @Override
    public Fabric getFabricById(RestParams params, String resourceId) {
        Fabric fabricDto = null;
        LOGGER.info("FabricImpl : getFabric : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FABRIC_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FabricImpl : getFabricById : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FABRIC, null);
        }

        fabricDto = adaptor.buildResourceObject(returnObj, Fabric.class);

        LOGGER.debug("FabricImpl : getFabricById : name :" + fabricDto.getName());
        LOGGER.info("FabricImpl : getFabricById : End");

        return fabricDto;
    }

    @Override
    public Fabric getFabricByName(RestParams params, String resourceName) {
        Fabric fabricDto = null;
        ResourceCollection<Fabric> fabricCollectionDto = null;

        LOGGER.info("FabricClientImpl : getFabricByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + resourceName + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FABRIC_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FabricClientImpl : getFabricByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FABRIC, null);
        }
        // Call adaptor to convert to DTO

        fabricCollectionDto = adaptor.buildResourceCollection(returnObj, Fabric.class);
        if (fabricCollectionDto.getCount() != 0) {
            fabricDto = fabricCollectionDto.getMembers().get(0);
        } else {
            fabricDto = null;
        }

        if (fabricDto == null) {
            LOGGER.error("FabricClientImpl : getFabricByName : resource not Found for name :"
                    + resourceName);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.FABRIC, null);
        }
        LOGGER.info("FabricClientImpl : getFabricByName : End");

        return fabricDto;
    }

    @Override
    public ResourceCollection<Fabric> getAllFabrics(RestParams params) {
        ResourceCollection<Fabric> fabricCollectionDto = null;
        LOGGER.info("FabricImpl : getAllFabrics : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FABRIC_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FabricImpl : getAllFabrics : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.FABRIC, null);
        }
        fabricCollectionDto = adaptor.buildResourceCollection(returnObj, Fabric.class);

        LOGGER.debug("FabricImpl : getAllFabrics : count :" + fabricCollectionDto.getCount());
        LOGGER.info("FabricImpl : getAllFabrics : End");

        return fabricCollectionDto;
    }

}
