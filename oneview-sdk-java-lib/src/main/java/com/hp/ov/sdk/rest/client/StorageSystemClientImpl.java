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

import com.hp.ov.sdk.adaptors.StorageSystemAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.AddStorageSystemCredentials;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StoragePoolCollection;
import com.hp.ov.sdk.dto.StorageSystemCollection;
import com.hp.ov.sdk.dto.StorageSystemV2;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.StorageTargetPortV2;
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

public class StorageSystemClientImpl implements StorageSystemClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageSystemClientImpl.class);

    private final StorageSystemAdaptor adaptor;

    private JSONObject jsonObject;

    protected StorageSystemClientImpl(StorageSystemAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public static StorageSystemClient getClient() {
        return new StorageSystemClientImpl(new StorageSystemAdaptor());
    }

    @Override
    public StorageSystemV2 getStorageSystem(final RestParams params, final String resourceId) {
        LOGGER.info("StorageSystemClientImpl : getStorageSystem : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageSystemClientImpl : getStorageSystem : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEM,
                    null);
        }
        // Call adaptor to convert to DTO
        final StorageSystemV2 storageSystemDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageSystemClientImpl : getStorageSystem : name :" + storageSystemDto.getName());
        LOGGER.info("StorageSystemClientImpl : getStorageSystem : End");

        return storageSystemDto;
    }

    @Override
    public StoragePoolCollection getStoragePoolsForStorageSystem(final RestParams params, final String arrayId) {
        LOGGER.info("StorageSystemClientImpl : getStoragePoolsForStorageSystem : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, arrayId,
                ResourceUris.STORAGE_POOL_STORAGE_SYSTEM_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageSystemClientImpl : getStoragePoolsForStorageSystem : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // Call adaptor to convert to DTO

        final StoragePoolCollection storagePoolCollectionDto = adaptor.buildStoragePoolCollectionDto(returnObj);

        LOGGER.debug("StorageSystemClientImpl : getStoragePoolsForStorageSystem : count :" + storagePoolCollectionDto.getCount());
        LOGGER.info("StorageSystemClientImpl : getStoragePoolsForStorageSystem : End");

        return storagePoolCollectionDto;
    }

    @Override
    public StorageTargetPortCollection getAllManagedPortsForStorageSystem(final RestParams params, final String resourceId) {
        LOGGER.info("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, resourceId,
                ResourceUris.MANANGED_PORTS_STORAGE_SYSTEM_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageTargetPortCollection storageTargetPortCollectionDto = adaptor.buildManagedPortsCollectionDto(returnObj);

        LOGGER.debug("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : count :"
                + storageTargetPortCollectionDto.getCount());
        LOGGER.info("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : End");

        return storageTargetPortCollectionDto;
    }

    @Override
    public StorageTargetPortV2 getManagedPortsForStorageSystem(final RestParams params, final String resourceId,
            final String targetPortId) {
        LOGGER.info("StorageSystemClientImpl : getManagedPortsForStorageSystem : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, resourceId,
                ResourceUris.MANANGED_PORTS_STORAGE_SYSTEM_URI, targetPortId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageSystemClientImpl : getManagedPortsForStorageSystem : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageTargetPortV2 storageTargetPortDto = adaptor.buildManagedPortsDto(returnObj);

        LOGGER.debug("StorageSystemClientImpl : getManagedPortsForStorageSystem : name :" + storageTargetPortDto.getName());
        LOGGER.info("StorageSystemClientImpl : getManagedPortsForStorageSystem : End");

        return storageTargetPortDto;
    }

    @Override
    public StorageSystemCollection getAllStorageSystems(final RestParams params) {
        LOGGER.info("StorageSystemClientImpl : getAllStorageSystems : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageSystemClientImpl : getAllStorageSystems : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageSystemCollection storageSystemCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("StorageSystemClientImpl : getAllStorageSystems : count :" + storageSystemCollectionDto.getCount());
        LOGGER.info("StorageSystemClientImpl : getAllStorageSystems : End");

        return storageSystemCollectionDto;
    }

    @Override
    public StorageSystemV2 getStorageSystemByName(final RestParams params, final String name) {
        StorageSystemV2 storageSystemDto = null;
        LOGGER.info("StorageSystemClientImpl : getStorageSystemByName : Start");

        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = UrlUtils.createFilterString(name);
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageSystemClientImpl : getStorageSystemByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // Call adaptor to convert to DTO

        final StorageSystemCollection storageSystemCollectionDto = adaptor.buildCollectionDto(returnObj);

        if (storageSystemCollectionDto.getCount() != 0) {
            storageSystemDto = storageSystemCollectionDto.getMembers().get(0);
        } else {
            storageSystemDto = null;
        }

        if (storageSystemDto == null) {
            LOGGER.error("StorageSystemClientImpl : getStorageSystemByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.STORAGE_SYSTEM,
                    null);
        }
        LOGGER.info("StorageSystemClientImpl : getStorageSystemByName : End");

        return storageSystemDto;
    }

    @Override
    public String createStorageSystem(final RestParams params, final AddStorageSystemCredentials addStorageSystemCredentialsDto,
            final boolean useJsonRequest) {
        LOGGER.info("StorageSystemClientImpl : createStorageSystem : Start");
        String returnObj = null;

        // validate params
        if (addStorageSystemCredentialsDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI));

        // TODO - check for json request in the input storageSystemDto. if it is
        // present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem storageSystemDto.

        // create JSON request from storageSystemDto
        jsonObject = adaptor.buildJsonObjectFromDto(addStorageSystemCredentialsDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource

        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Created";
        }
        LOGGER.debug("StorageSystemClientImpl : createStorageSystem : returnObj =" + returnObj);

        LOGGER.info("StorageSystemClientImpl : createStorageSystem : End");

        return returnObj;

    }

    @Override
    public String updateStorageSystem(final RestParams params, final String resourceId, final StorageSystemV2 storageSystemDto,
            final boolean useJsonRequest) {
        LOGGER.info("StorageSystemClientImpl : updateStorageSystem : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (storageSystemDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageSystemDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Updated";
        }

        LOGGER.debug("StorageSystemClientImpl : updateStorageSystem : returnObj =" + returnObj);

        LOGGER.info("StorageSystemClientImpl : updateStorageSystem : End");

        return returnObj;
    }

    @Override
    public String deleteStorageSystem(final RestParams params, final String resourceId) {
        LOGGER.info("StorageSystemClientImpl : deleteStorageSystem : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_SYSTEM_URI, resourceId));

        String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Deleted";
        }

        LOGGER.debug("StorageSystemClientImpl : deleteStorageSystem : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.STORAGE_SYSTEMS,
                    null);
        }

        LOGGER.debug("StorageSystemClientImpl : deleteStorageSystem : returnObj =" + returnObj);

        LOGGER.info("StorageSystemClientImpl : deleteStorageSystem : End");

        return returnObj;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        StorageSystemV2 storageSystemDto = getStorageSystemByName(creds, name);

        if (null != storageSystemDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storageSystemDto.getUri());
        }
        return resourceId;
    }

}
