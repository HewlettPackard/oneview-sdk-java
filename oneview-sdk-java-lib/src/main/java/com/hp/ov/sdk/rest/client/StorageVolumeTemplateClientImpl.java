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
import com.hp.ov.sdk.adaptors.StorageVolumeTemplateAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplate;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;


public class StorageVolumeTemplateClientImpl implements StorageVolumeTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeTemplateClientImpl.class);

    private final HttpRestClient restClient;
    private final ResourceAdaptor resourceAdaptor;
    private final StorageVolumeTemplateAdaptor adaptor;

    protected StorageVolumeTemplateClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor resourceAdaptor,
            StorageVolumeTemplateAdaptor adaptor) {

        this.restClient = restClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
    }

    public static StorageVolumeTemplateClient getClient() {
        return new StorageVolumeTemplateClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new StorageVolumeTemplateAdaptor());
    }

    @Override
    public StorageVolumeTemplate getStorageVolumeTemplate(final RestParams params, final String resourceId) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        final StorageVolumeTemplate storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : Name :" + storageVolumeTemplateDto.getName());
        LOGGER.trace("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public ResourceCollection<StorageVolumeTemplate> getAllStorageVolumeTemplates(final RestParams params) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATES, null);
        }
        ResourceCollection<StorageVolumeTemplate> storageVolumeTemplateCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, StorageVolumeTemplate.class);

        LOGGER.debug("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : members count :"
                + storageVolumeTemplateCollectionDto.getCount());
        LOGGER.trace("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : End");

        return storageVolumeTemplateCollectionDto;
    }

    @Override
    public StorageVolumeTemplate getStorageVolumeTemplateByName(final RestParams params, final String name) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : getNetworkSetByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }

        StorageVolumeTemplate storageVolumeTemplateDto = null;
        ResourceCollection<StorageVolumeTemplate> storageVolumeTemplateCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, StorageVolumeTemplate.class);

        if (!storageVolumeTemplateCollectionDto.isEmpty()) {
            storageVolumeTemplateDto = storageVolumeTemplateCollectionDto.getMembers().get(0);
        }

        if (storageVolumeTemplateDto == null) {
            LOGGER.error("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        LOGGER.trace("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplate createStorageVolumeTemplate(final RestParams params,
            StorageVolumeTemplate storageVolumeTemplateDto, final boolean aSync, final boolean useJsonRequest) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : Start");

        // validate params
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (storageVolumeTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolumeTemplate dto.

        // create JSON request from dto
        JSONObject jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeTemplateDto);
        String returnObj = restClient.sendRequest(params, jsonObject);

        // convert returnObj to storageVolumeTemplate
        storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : StorageVolumeTemplate ="
                + storageVolumeTemplateDto.toString());

        LOGGER.trace("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplate updateStorageVolumeTemplate(final RestParams params, final String resourceId,
            StorageVolumeTemplate storageVolumeTemplateDto, final boolean useJsonRequest) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        } else if (storageVolumeTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolumeTemplate dto.

        // create JSON request from dto
        JSONObject jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeTemplateDto);
        String returnObj = restClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : storageVolumeTemplate ="
                + storageVolumeTemplateDto.toString());

        LOGGER.trace("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;

    }

    @Override
    public String deleteStorageVolumeTemplate(final RestParams params, final String resourceId) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        String returnObj = restClient.sendRequest(params);

        if (!Strings.isNullOrEmpty(returnObj)) {
            returnObj = "Deleted";
        }
        LOGGER.debug("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }

        LOGGER.debug("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : returnObj =" + returnObj);
        LOGGER.trace("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : End");

        return returnObj;
    }

    @Override
    public ResourceCollection<ConnectableStorageVolumeTemplate> getConnectableVolumeTemplates(final RestParams params) {
        LOGGER.trace("StorageVolumeTemplateClientImpl : getConnectableVolumeTemplates : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_VOLUME_TEMPLATE_URI,
                ResourceUris.STORAGE_VOLUME_TEMPLATE_CONNECTABLE_URI));

        String returnObj = restClient.sendRequest(params);

        LOGGER.debug("StorageVolumeTemplateClientImpl : getConnectableVolumeTemplates : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATES, null);
        }
        ResourceCollection<ConnectableStorageVolumeTemplate> connectableCollection
                = resourceAdaptor.buildResourceCollection(returnObj, ConnectableStorageVolumeTemplate.class);

        LOGGER.debug("StorageVolumeTemplateClientImpl : getConnectableVolumeTemplates : members count :"
                + connectableCollection.getCount());
        LOGGER.trace("StorageVolumeTemplateClientImpl : getConnectableVolumeTemplates : End");

        return connectableCollection;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        StorageVolumeTemplate storageVolumeTemplateDto = getStorageVolumeTemplateByName(params, name);

        if (null != storageVolumeTemplateDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storageVolumeTemplateDto.getUri());
        }
        return resourceId;
    }


}
