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

import com.hp.ov.sdk.adaptors.StorageVolumeTemplateAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ConnectableStorageVolumeTemplateCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.StorageVolumeTemplate;
import com.hp.ov.sdk.dto.StorageVolumeTemplateCollection;
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


public class StorageVolumeTemplateClientImpl implements StorageVolumeTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(StorageVolumeTemplateClientImpl.class);

    private final StorageVolumeTemplateAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;

    private JSONObject jsonObject;

    protected StorageVolumeTemplateClientImpl(StorageVolumeTemplateAdaptor adaptor, TaskAdaptor taskAdaptor) {
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
    }

    public static StorageVolumeTemplateClient getClient() {
        return new StorageVolumeTemplateClientImpl(
                new StorageVolumeTemplateAdaptor(),
                TaskAdaptor.getInstance());
    }

    @Override
    public StorageVolumeTemplate getStorageVolumeTemplate(final RestParams params, final String resourceId) {
        LOGGER.info("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeTemplate storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : Name :" + storageVolumeTemplateDto.getName());
        LOGGER.info("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplateCollection getAllStorageVolumeTemplates(final RestParams params) {
        LOGGER.info("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        // call rest client
        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATES, null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : members count :"
                + storageVolumeTemplateCollectionDto.getCount());
        LOGGER.info("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : End");

        return storageVolumeTemplateCollectionDto;
    }

    @Override
    public StorageVolumeTemplate getStorageVolumeTemplateByName(final RestParams params, final String name) {
        StorageVolumeTemplate storageVolumeTemplateDto = null;
        LOGGER.info("StorageVolumeTemplateClientImpl : getNetworkSetByName : Start");
        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = UrlUtils.createFilterString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, query));

        final String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        LOGGER.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (storageVolumeTemplateCollectionDto.getCount() != 0) {
            storageVolumeTemplateDto = storageVolumeTemplateCollectionDto.getMembers().get(0);
        } else {
            storageVolumeTemplateDto = null;
        }

        if (storageVolumeTemplateDto == null) {
            LOGGER.error("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        LOGGER.info("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplate createStorageVolumeTemplate(final RestParams params,
            StorageVolumeTemplate storageVolumeTemplateDto, final boolean aSync, final boolean useJsonRequest) {
        LOGGER.info("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : Start");
        String returnObj = null;

        // validate params
        if (storageVolumeTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolumeTemplate dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeTemplateDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to storageVolumeTemplate
        storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : StorageVolumeTemplate ="
                + storageVolumeTemplateDto.toString());

        LOGGER.info("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplate updateStorageVolumeTemplate(final RestParams params, final String resourceId,
            StorageVolumeTemplate storageVolumeTemplateDto, final boolean useJsonRequest) {
        LOGGER.info("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (storageVolumeTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolumeTemplate dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeTemplateDto);
        returnObj = HttpRestClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : returnObj =" + returnObj);
        LOGGER.debug("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : storageVolumeTemplate ="
                + storageVolumeTemplateDto.toString());

        LOGGER.info("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;

    }

    @Override
    public String deleteStorageVolumeTemplate(final RestParams params, final String resourceId) {
        LOGGER.info("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        String returnObj = HttpRestClient.sendRequestToHPOV(params, null);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Deleted";
        }
        LOGGER.debug("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        /************ Returns Response code 204 *********************************/
        // taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : returnObj =" + returnObj);

        LOGGER.info("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : End");

        return null;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        StorageVolumeTemplate storageVolumeTemplateDto = getStorageVolumeTemplateByName(creds, name);

        if (null != storageVolumeTemplateDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(storageVolumeTemplateDto.getUri());
        }
        return resourceId;
    }

    @Override
    public ConnectableStorageVolumeTemplateCollection getConnectableVolumeTemplates(final RestParams params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
