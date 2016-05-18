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
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.StoragePoolAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

public class StoragePoolClientImpl implements StoragePoolClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoragePoolClientImpl.class);

    private final HttpRestClient restClient;
    private final ResourceAdaptor resourceAdaptor;
    private final StoragePoolAdaptor adaptor;

    private StoragePoolClientImpl(HttpRestClient restClient, ResourceAdaptor resourceAdaptor, StoragePoolAdaptor adaptor) {
        this.restClient = restClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
    }

    public static StoragePoolClient getClient() {
        return new StoragePoolClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new StoragePoolAdaptor());
    }

    @Override
    public StoragePool getStoragePool(final RestParams params, final String resourceId) {
        LOGGER.trace("StoragePoolClientImpl : getStoragePool : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StoragePoolClientImpl : getStoragePool : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }

        final StoragePool storagePoolDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StoragePoolClientImpl : getStoragePool : name :" + storagePoolDto.getName());
        LOGGER.trace("StoragePoolClientImpl : getStoragePool : End");

        return storagePoolDto;
    }

    @Override
    public ResourceCollection<StoragePool> getAllStoragePools(final RestParams params) {
        LOGGER.trace("StoragePoolClientImpl : getAllStoragePools : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StoragePoolClientImpl : getAllStoragePools : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }

        ResourceCollection<StoragePool> storagePoolCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, StoragePool.class);

        LOGGER.debug("StoragePoolClientImpl : getAllStoragePools : count :" + storagePoolCollectionDto.getCount());
        LOGGER.trace("StoragePoolClientImpl : getAllStoragePools : End");

        return storagePoolCollectionDto;
    }

    @Override
    public StoragePool getStoragePoolByName(final RestParams params, final String name, final String storageSystemUri) {
        LOGGER.trace("StoragePoolClientImpl : getStoragePoolByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StoragePoolClientImpl : getStoragePoolByName : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }

        ResourceCollection<StoragePool> storagePoolCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, StoragePool.class);

        StoragePool storagePoolDto = null;

        if (storagePoolCollectionDto.getCount() != 0) {
            for (StoragePool storagePool : storagePoolCollectionDto.getMembers()) {
                if (storageSystemUri.equalsIgnoreCase(storagePool.getStorageSystemUri())) {
                    storagePoolDto = storagePool;
                }
            }
        }

        if (storagePoolDto == null) {
            LOGGER.error("StoragePoolClientImpl : getStoragePoolByName : Not found for storage pool name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }
        LOGGER.trace("StoragePoolClientImpl : getStoragePoolByName : End");

        return storagePoolDto;
    }

    @Override
    public String createStoragePool(final RestParams params, final AddStoragePool addStoragePoolDto, final boolean useJsonRequest) {
        LOGGER.trace("StoragePoolClientImpl : createStoragePool : Start");

        // validate params
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (addStoragePoolDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI));

        // TODO - check for json request in the input storagePoolDto. if it is
        // present, then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem storagePoolDto.

        // create JSON request from storagePoolDto
        JSONObject jsonObject = adaptor.buildJsonObjectFromDto(addStoragePoolDto);

        String returnObj = restClient.sendRequest(params, jsonObject);

        if (!Strings.isNullOrEmpty(returnObj)) {
            returnObj = "Created";
        }

        LOGGER.debug("StoragePoolClientImpl : createStoragePool : returnObj =" + returnObj);
        LOGGER.trace("StoragePoolClientImpl : createStoragePool : End");

        return returnObj;
    }

    @Override
    public String updateStoragePool(final RestParams params, final String resourceId, final StoragePool storagePoolDto,
            final boolean useJsonRequest) {
        LOGGER.trace("StoragePoolClientImpl : updateStoragePool : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        } else  if (storagePoolDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_POOL, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem dto.

        // create JSON request from dto
        JSONObject jsonObject = adaptor.buildJsonObjectFromDto(storagePoolDto, params.getApiVersion());

        String returnObj = restClient.sendRequest(params, jsonObject);

        if (!Strings.isNullOrEmpty(returnObj)) {
            returnObj = "Updated";
        }

        LOGGER.debug("StoragePoolClientImpl : updateStoragePool : returnObj =" + returnObj);
        LOGGER.trace("StoragePoolClientImpl : updateStoragePool : End");

        return returnObj;
    }

    @Override
    public String deleteStoragePool(final RestParams params, final String resourceId) {
        LOGGER.trace("StoragePoolClientImpl : deleteStoragePool : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, resourceId));

        String returnObj = restClient.sendRequest(params);

        if (!Strings.isNullOrEmpty(returnObj)) {
            returnObj = "Deleted";
        }
        LOGGER.debug("StoragePoolClientImpl : deleteStoragePool : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }

        LOGGER.debug("StoragePoolClientImpl : deleteStoragePool : returnObj =" + returnObj);
        LOGGER.trace("StoragePoolClientImpl : deleteStoragePool : End");

        return returnObj;
    }

    @Override
    public String getId(final RestParams params, final String name, final String storageSystemUri) {
        String resourceId = "";
        // fetch resource Id using resource name
        StoragePool storagePoolDto = getStoragePoolByName(params, name, storageSystemUri);

        if (null != storagePoolDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storagePoolDto.getUri());
        }
        return resourceId;
    }

}
