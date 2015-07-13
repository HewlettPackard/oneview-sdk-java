/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class StorageSystemClientImpl implements StorageSystemClient
{

    private static final Logger logger = LoggerFactory
            .getLogger(StorageSystemClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private StorageSystemAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Override
    public StorageSystemV2 getStorageSystem(final RestParams params, final String resourceId)
    {
        logger.info("StorageSystemClientImpl : getStorageSystem : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageSystemClientImpl : getStorageSystem : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEM, null);
        }
        // Call adaptor to convert to DTO
        final StorageSystemV2 storageSystemDto = adaptor.buildDto(returnObj);

        logger.debug("StorageSystemClientImpl : getStorageSystem : name :"
                + storageSystemDto.getName());
        logger.info("StorageSystemClientImpl : getStorageSystem : End");

        return storageSystemDto;
    }

    @Override
    public StoragePoolCollection getStoragePoolsForStorageSystem(
            final RestParams params, final String arrayId)
    {
        logger.info("StorageSystemClientImpl : getStoragePoolsForStorageSystem : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI, arrayId,
                ResourceUris.STORAGE_POOL_STORAGE_SYSTEM_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageSystemClientImpl : getStoragePoolsForStorageSystem : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEMS, null);
        }
        // Call adaptor to convert to DTO

        final StoragePoolCollection storagePoolCollectionDto = adaptor
                .buildStoragePoolCollectionDto(returnObj);

        logger.debug("StorageSystemClientImpl : getStoragePoolsForStorageSystem : count :"
                + storagePoolCollectionDto.getCount());
        logger.info("StorageSystemClientImpl : getStoragePoolsForStorageSystem : End");

        return storagePoolCollectionDto;
    }

    @Override
    public StorageTargetPortCollection getAllManagedPortsForStorageSystem(
            final RestParams params, final String resourceId)
    {
        logger.info("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI, resourceId,
                ResourceUris.MANANGED_PORTS_STORAGE_SYSTEM_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEMS, null);
        }
        // Call adaptor to convert to DTO

        final StorageTargetPortCollection storageTargetPortCollectionDto = adaptor
                .buildManagedPortsCollectionDto(returnObj);

        logger.debug("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : count :"
                + storageTargetPortCollectionDto.getCount());
        logger.info("StorageSystemClientImpl : getAllManagedPortsForStorageSystem : End");

        return storageTargetPortCollectionDto;
    }

    @Override
    public StorageTargetPortV2 getManagedPortsForStorageSystem(
            final RestParams params, final String resourceId, final String targetPortId)
    {
        logger.info("StorageSystemClientImpl : getManagedPortsForStorageSystem : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI, resourceId,
                ResourceUris.MANANGED_PORTS_STORAGE_SYSTEM_URI, targetPortId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageSystemClientImpl : getManagedPortsForStorageSystem : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEMS, null);
        }
        // Call adaptor to convert to DTO

        final StorageTargetPortV2 storageTargetPortDto = adaptor.buildManagedPortsDto(returnObj);

        logger.debug("StorageSystemClientImpl : getManagedPortsForStorageSystem : name :"
                + storageTargetPortDto.getName());
        logger.info("StorageSystemClientImpl : getManagedPortsForStorageSystem : End");

        return storageTargetPortDto;
    }

    @Override
    public StorageSystemCollection getAllStorageSystems(final RestParams params)
    {
        logger.info("StorageSystemClientImpl : getAllStorageSystems : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageSystemClientImpl : getAllStorageSystems : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEMS, null);
        }
        // Call adaptor to convert to DTO

        final StorageSystemCollection storageSystemCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("StorageSystemClientImpl : getAllStorageSystems : count :"
                + storageSystemCollectionDto.getCount());
        logger.info("StorageSystemClientImpl : getAllStorageSystems : End");

        return storageSystemCollectionDto;
    }

    @Override
    public StorageSystemV2 getStorageSystemByName(final RestParams params, final String name)
    {
        StorageSystemV2 storageSystemDto = null;
        logger.info("StorageSystemClientImpl : getStorageSystemByName : Start");

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
                ResourceUris.STORAGE_SYSTEM_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("StorageSystemClientImpl : getStorageSystemByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEMS, null);
        }
        // Call adaptor to convert to DTO

        final StorageSystemCollection storageSystemCollectionDto = adaptor.buildCollectionDto(returnObj);

        if (storageSystemCollectionDto.getCount() != 0)
        {
            storageSystemDto = storageSystemCollectionDto.getMembers().get(0);
        }
        else
        {
            storageSystemDto = null;
        }

        if (storageSystemDto == null) {
        logger.error("StorageSystemClientImpl : getStorageSystemByName : Not found for name :"
                + name);
        throw new SDKResourceNotFoundException(
                SDKErrorEnum.resourceNotFound, null, null, null,
                SdkConstants.STORAGE_SYSTEM, null);
        }
        logger.info("StorageSystemClientImpl : getStorageSystemByName : End");

        return storageSystemDto;
    }

    @Override
    public String createStorageSystem(final RestParams params,
            final AddStorageSystemCredentials addStorageSystemCredentialsDto,
            final boolean useJsonRequest)
    {
        logger.info("StorageSystemClientImpl : createStorageSystem : Start");
        String returnObj = null;

        // validate params
        if (addStorageSystemCredentialsDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.STORAGE_SYSTEMS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI));

        // TODO - check for json request in the input storageSystemDto. if it is
        // present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem storageSystemDto.

        // create JSON request from storageSystemDto
        jsonObject = adaptor
                .buildJsonObjectFromDto(addStorageSystemCredentialsDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource

        if (!returnObj.isEmpty() || returnObj != null)
        {
            returnObj = "Created";
        }
        logger.debug("StorageSystemClientImpl : createStorageSystem : returnObj ="
                + returnObj);

        logger.info("StorageSystemClientImpl : createStorageSystem : End");

        return returnObj;

    }

    @Override
    public String updateStorageSystem(final RestParams params, final String resourceId,
            final StorageSystemV2 storageSystemDto, final boolean useJsonRequest)
    {
        logger.info("StorageSystemClientImpl : updateStorageSystem : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (storageSystemDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.STORAGE_SYSTEMS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating storageSystem dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(storageSystemDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        if (!returnObj.isEmpty() || returnObj != null)
        {
            returnObj = "Updated";
        }

        logger.debug("StorageSystemClientImpl : updateStorageSystem : returnObj ="
                + returnObj);

        logger.info("StorageSystemClientImpl : updateStorageSystem : End");

        return returnObj;
    }

    @Override
    public String deleteStorageSystem(final RestParams params, final String resourceId)
    {
        logger.info("StorageSystemClientImpl : deleteStorageSystem : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.STORAGE_SYSTEM_URI, resourceId));

        String returnObj = restClient.sendRequestToHPOV(params, null);
        if (!returnObj.isEmpty() || returnObj != null)
        {
            returnObj = "Deleted";
        }

        logger.debug("StorageSystemClientImpl : deleteStorageSystem : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.STORAGE_SYSTEMS, null);
        }

        logger.debug("StorageSystemClientImpl : deleteStorageSystem : returnObj ="
                + returnObj);

        logger.info("StorageSystemClientImpl : deleteStorageSystem : End");

        return returnObj;
    }

}
