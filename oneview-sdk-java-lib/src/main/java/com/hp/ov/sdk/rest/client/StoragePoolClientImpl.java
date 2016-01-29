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

import com.hp.ov.sdk.adaptors.StoragePoolAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddStoragePool;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StoragePool;
import com.hp.ov.sdk.dto.StoragePoolCollection;
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

public class StoragePoolClientImpl implements StoragePoolClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoragePoolClientImpl.class);

    private final StoragePoolAdaptor adaptor;

    private JSONObject jsonObject;

    protected StoragePoolClientImpl(StoragePoolAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public static StoragePoolClient getClient() {
        return new StoragePoolClientImpl(new StoragePoolAdaptor());
    }

    @Override
    public StoragePool getStoragePool(final RestParams params, final String resourceId) {
        LOGGER.info("StoragePoolClientImpl : getStoragePool : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StoragePoolClientImpl : getStoragePool : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_POOL,
                    null);
        }
        // Call adaptor to convert to DTO
        final StoragePool storagePoolDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StoragePoolClientImpl : getStoragePool : name :" + storagePoolDto.getName());
        LOGGER.info("StoragePoolClientImpl : getStoragePool : End");

        return storagePoolDto;
    }

    @Override
    public StoragePoolCollection getAllStoragePools(final RestParams params) {
        LOGGER.info("StoragePoolClientImpl : getAllStoragePools : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StoragePoolClientImpl : getAllStoragePools : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_POOL,
                    null);
        }
        // Call adaptor to convert to DTO

        final StoragePoolCollection storagePoolCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("StoragePoolClientImpl : getAllStoragePools : count :" + storagePoolCollectionDto.getCount());
        LOGGER.info("StoragePoolClientImpl : getAllStoragePools : End");

        return storagePoolCollectionDto;
    }

    @Override
    public StoragePool getStoragePoolByName(final RestParams params, final String name, final String storageSystemUri) {
        StoragePool storagePoolDto = null;
        LOGGER.info("StoragePoolClientImpl : getStoragePoolByName : Start");

        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = UrlUtils.createFilterString(name);
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StoragePoolClientImpl : getStoragePoolByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_POOL,
                    null);
        }
        // Call adaptor to convert to DTO

        final StoragePoolCollection storagePoolCollectionDto = adaptor.buildCollectionDto(returnObj);

        storagePoolDto = null;
        if (storagePoolCollectionDto.getCount() != 0) {
            for (int i = 0; i < storagePoolCollectionDto.getCount(); i++) {
                if (storagePoolCollectionDto.getMembers().get(i).getStorageSystemUri().equalsIgnoreCase(storageSystemUri)) {
                    storagePoolDto = storagePoolCollectionDto.getMembers().get(i);
                }
            }
        }

        if (storagePoolDto == null) {
            LOGGER.error("StoragePoolClientImpl : getStoragePoolByName : Not found for storage pool name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.STORAGE_POOL, null);
        }
        LOGGER.info("StoragePoolClientImpl : getStoragePoolByName : End");

        return storagePoolDto;

    }

    @Override
    public String createStoragePool(final RestParams params, final AddStoragePool addStoragePoolDto, final boolean useJsonRequest) {
        LOGGER.info("StoragePoolClientImpl : createStoragePool : Start");
        String returnObj = null;

        // validate params
        if (addStoragePoolDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_POOL, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI));

        // TODO - check for json request in the input storagePoolDto. if it is
        // present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem storagePoolDto.

        // create JSON request from storagePoolDto
        jsonObject = adaptor.buildJsonObjectFromDto(addStoragePoolDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Created";
        }

        LOGGER.debug("StoragePoolClientImpl : createStoragePool : returnObj =" + returnObj);

        LOGGER.info("StoragePoolClientImpl : createStoragePool : End");

        return returnObj;
    }

    @Override
    public String updateStoragePool(final RestParams params, final String resourceId, final StoragePool storagePoolDto,
            final boolean useJsonRequest) {
        LOGGER.info("StoragePoolClientImpl : updateStoragePool : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (storagePoolDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_POOL, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storagePoolDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Updated";
        }

        LOGGER.debug("StoragePoolClientImpl : updateStoragePool : returnObj =" + returnObj);

        LOGGER.info("StoragePoolClientImpl : updateStoragePool : End");

        return returnObj;
    }

    @Override
    public String deleteStoragePool(final RestParams params, final String resourceId) {
        LOGGER.info("StoragePoolClientImpl : deleteStoragePool : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_POOL_URI, resourceId));

        String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Deleted";
        }
        LOGGER.debug("StoragePoolClientImpl : deleteStoragePool : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_POOL,
                    null);
        }

        LOGGER.debug("StoragePoolClientImpl : deleteStoragePool : returnObj =" + returnObj);

        LOGGER.info("StoragePoolClientImpl : deleteStoragePool : End");

        return returnObj;
    }

    @Override
    public String getId(final RestParams creds, final String name, final String storageSystemUri) {
        String resourceId = "";
        // fetch resource Id using resource name
        StoragePool storagePoolDto = getStoragePoolByName(creds, name, storageSystemUri);

        if (null != storagePoolDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storagePoolDto.getUri());
        }
        return resourceId;
    }

}
