/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class StoragePoolClientImpl implements StoragePoolClient
{

    private static final Logger logger = LoggerFactory
            .getLogger(StoragePoolClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private StoragePoolAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Override
    public StoragePool getStoragePool(final RestParams params, final String resourceId)
    {
        logger.info("StoragePoolClientImpl : getStoragePool : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_POOL_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StoragePoolClientImpl : getStoragePool : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }
        // Call adaptor to convert to DTO
        final StoragePool storagePoolDto = adaptor.buildDto(returnObj);

        logger.debug("StoragePoolClientImpl : getStoragePool : name :"
                + storagePoolDto.getName());
        logger.info("StoragePoolClientImpl : getStoragePool : End");

        return storagePoolDto;
    }

    @Override
    public StoragePoolCollection getAllStoragePools(final RestParams params)
    {
        logger.info("StoragePoolClientImpl : getAllStoragePools : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_POOL_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StoragePoolClientImpl : getAllStoragePools : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }
        // Call adaptor to convert to DTO

        final StoragePoolCollection storagePoolCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("StoragePoolClientImpl : getAllStoragePools : count :"
                + storagePoolCollectionDto.getCount());
        logger.info("StoragePoolClientImpl : getAllStoragePools : End");

        return storagePoolCollectionDto;
    }

    @Override
    public StoragePool getStoragePoolByName(final RestParams params, final String name)
    {
        StoragePool storagePoolDto = null;
        logger.info("StoragePoolClientImpl : getStoragePoolByName : Start");

        final String query = "filter=\"name=\'" + name + "\'\"";
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.STORAGE_POOL_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StoragePoolClientImpl : getStoragePoolByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }
        // Call adaptor to convert to DTO

        final StoragePoolCollection storagePoolCollectionDto = adaptor.buildCollectionDto(returnObj);

        if (storagePoolCollectionDto.getCount() != 0)
        {
            storagePoolDto = storagePoolCollectionDto.getMembers().get(0);
        }
        else
        {
            storagePoolDto = null;
        }

        if (storagePoolDto == null)
        {
            logger.error("StoragePoolClientImpl : getStoragePoolByName : Not found for name :"
                    + name);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }
        logger.info("StoragePoolClientImpl : getStoragePoolByName : End");

        return storagePoolDto;

    }

    @Override
    public String createStoragePool(final RestParams params,
            final AddStoragePool addStoragePoolDto, final boolean useJsonRequest)
    {
        logger.info("StoragePoolClientImpl : createStoragePool : Start");
        String returnObj = null;

        // validate params
        if (addStoragePoolDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.STORAGE_POOL, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_POOL_URI));

        // TODO - check for json request in the input storagePoolDto. if it is
        // present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem storagePoolDto.

        // create JSON request from storagePoolDto
        jsonObject = adaptor.buildJsonObjectFromDto(addStoragePoolDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        if (!returnObj.isEmpty() || returnObj != null)
        {
            returnObj = "Created";
        }

        logger.debug("StoragePoolClientImpl : createStoragePool : returnObj ="
                + returnObj);

        logger.info("StoragePoolClientImpl : createStoragePool : End");

        return returnObj;
    }

    @Override
    public String updateStoragePool(final RestParams params, final String resourceId,
            final StoragePool storagePoolDto, final boolean useJsonRequest)
    {
        logger.info("StoragePoolClientImpl : updateStoragePool : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (storagePoolDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.STORAGE_POOL, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_POOL_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storagePoolDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        if (!returnObj.isEmpty() || returnObj != null)
        {
            returnObj = "Updated";
        }

        logger.debug("StoragePoolClientImpl : updateStoragePool : returnObj ="
                + returnObj);

        logger.info("StoragePoolClientImpl : updateStoragePool : End");

        return returnObj;
    }

    @Override
    public String deleteStoragePool(final RestParams params, final String resourceId)
    {
        logger.info("StoragePoolClientImpl : deleteStoragePool : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_POOL_URI, resourceId));

        String returnObj = restClient.sendRequestToHPOV(params, null);
        if (!returnObj.isEmpty() || returnObj != null)
        {
            returnObj = "Deleted";
        }
        logger.debug("StoragePoolClientImpl : deleteStoragePool : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_POOL, null);
        }

        logger.debug("StoragePoolClientImpl : deleteStoragePool : returnObj ="
                + returnObj);

        logger.info("StoragePoolClientImpl : deleteStoragePool : End");

        return returnObj;
    }

}
