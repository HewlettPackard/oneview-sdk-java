/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

@Component
public class StorageVolumeTemplateClientImpl implements StorageVolumeTemplateClient {

    private static final Logger logger = LoggerFactory.getLogger(StorageVolumeTemplateClientImpl.class);

    @Autowired
    private StorageVolumeTemplateAdaptor adaptor;

    @Autowired
    private HttpRestClient restClient;

    private JSONObject jsonObject;

    @Autowired
    private UrlUtils urlUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Override
    public StorageVolumeTemplate getStorageVolumeTemplate(final RestParams params, final String resourceId) {
        logger.info("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeTemplate storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        logger.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : Name :" + storageVolumeTemplateDto.getName());
        logger.info("StorageVolumeTemplateClientImpl : getStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplateCollection getAllStorageVolumeTemplates(final RestParams params) {
        logger.info("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        // call rest client
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATES, null);
        }
        // Call adaptor to convert to DTO

        final StorageVolumeTemplateCollection storageVolumeTemplateCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : members count :"
                + storageVolumeTemplateCollectionDto.getCount());
        logger.info("StorageVolumeTemplateClientImpl : getAllStorageVolumeTemplates : End");

        return storageVolumeTemplateCollectionDto;
    }

    @Override
    public StorageVolumeTemplate getStorageVolumeTemplateByName(final RestParams params, final String name) {
        StorageVolumeTemplate storageVolumeTemplateDto = null;
        logger.info("StorageVolumeTemplateClientImpl : getNetworkSetByName : Start");
        // final String query = "filter=\"name=\'" + name + "\'\"";
        final String query = urlUtils.createQueryString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestQueryUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : response from OV :" + returnObj);
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
            logger.error("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        logger.info("StorageVolumeTemplateClientImpl : getStorageVolumeTemplateByName : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplate createStorageVolumeTemplate(final RestParams params,
            StorageVolumeTemplate storageVolumeTemplateDto, final boolean aSync, final boolean useJsonRequest) {
        logger.info("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : Start");
        String returnObj = null;

        // validate params
        if (storageVolumeTemplateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolumeTemplate dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeTemplateDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to storageVolumeTemplate
        storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        logger.debug("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : returnObj =" + returnObj);
        logger.debug("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : StorageVolumeTemplate ="
                + storageVolumeTemplateDto.toString());

        logger.info("StorageVolumeTemplateClientImpl : createStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;
    }

    @Override
    public StorageVolumeTemplate updateStorageVolumeTemplate(final RestParams params, final String resourceId,
            StorageVolumeTemplate storageVolumeTemplateDto, final boolean useJsonRequest) {
        logger.info("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : Start");

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
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageVolumeTemplate dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageVolumeTemplateDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        storageVolumeTemplateDto = adaptor.buildDto(returnObj);

        logger.debug("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : returnObj =" + returnObj);
        logger.debug("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : storageVolumeTemplate ="
                + storageVolumeTemplateDto.toString());

        logger.info("StorageVolumeTemplateClientImpl : updateStorageVolumeTemplate : End");

        return storageVolumeTemplateDto;

    }

    @Override
    public String deleteStorageVolumeTemplate(final RestParams params, final String resourceId) {
        logger.info("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.STORAGE_VOLUME_TEMPLATE_URI, resourceId));

        String returnObj = restClient.sendRequestToHPOV(params, null);
        if (!returnObj.isEmpty() || returnObj != null) {
            returnObj = "Deleted";
        }
        logger.debug("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_VOLUME_TEMPLATE, null);
        }
        /************ Returns Response code 204 *********************************/
        // taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : returnObj =" + returnObj);

        logger.info("StorageVolumeTemplateClientImpl : deleteStorageVolumeTemplate : End");

        return null;
    }

    @Override
    public ConnectableStorageVolumeTemplateCollection getConnectableVolumeTemplates(final RestParams params) {
        // TODO Auto-generated method stub
        return null;
    }

}
