/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.adaptors.UplinkSetAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UplinkSetCollectionV2;
import com.hp.ov.sdk.dto.generated.UplinkSets;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class UplinkSetClientImpl implements UplinkSetClient
{

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    public static final Logger logger = LoggerFactory
            .getLogger(UplinkSetClientImpl.class);

    private JSONObject jsonObject;

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private UplinkSetAdaptor adaptor;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public UplinkSets getUplinkSet(final RestParams params, final String resourceId)
    {
        logger.info("UplinkSetClientImpl : getUplinkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.UPLINK_SETS_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("UplinkSetClientImpl : getUplinkSet : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UPLINKSET, null);
        }
        // Call adaptor to convert to DTO

        final UplinkSets uplinkSetDto = adaptor.buildDto(returnObj);

        logger.debug("UplinkSetClientImpl : getUplinkSet : Name :"
                + uplinkSetDto.getName());
        logger.info("UplinkSetClientImpl : getUplinkSet : End");

        return uplinkSetDto;
    }

    @Override
    public UplinkSetCollectionV2 getAllUplinkSet(final RestParams params)
    {
        logger.info("UplinkSetClientImpl : getAllUplinkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.UPLINK_SETS_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("UplinkSetClientImpl : getAllUplinkSet : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UPLINKSETS, null);
        }
        // Call adaptor to convert to DTO

        final UplinkSetCollectionV2 uplinkSetCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("UplinkSetClientImpl : getAllUplinkSet : members count :"
                + uplinkSetCollectionDto.getCount());
        logger.info("UplinkSetClientImpl : getAllUplinkSet : End");

        return uplinkSetCollectionDto;
    }

    @Override
    public TaskResourceV2 deleteUplinkSet(final RestParams params, final String resourceId,
            final boolean aSync)
    {
        logger.info("UplinkSetClientImpl : deleteUplinkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.UPLINK_SETS_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("UplinkSetClientImpl : deleteUplinkSet : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UPLINKSETS, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("UplinkSetClientImpl : deleteUplinkSet : returnObj ="
                + returnObj);
        logger.debug("UplinkSetClientImpl : deleteUplinkSet : taskResource ="
                + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("UplinkSetClientImpl : deleteUplinkSet : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateUplinkSet(final RestParams params, final String resourceId,
            final UplinkSets uplinkDto, final boolean aSync, final boolean useJsonRequest)
    {

        logger.info("UplinkSetClientImpl : updateUplinkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (uplinkDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.UPLINKSETS, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.UPLINK_SETS_URI, resourceId));
        String returnObj = null;

        //TODO
        // check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating uplinksets dto.

        jsonObject = adaptor.buildJsonObjectFromDto(uplinkDto);

        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("UplinkSetClientImpl : updateUplinkSet : returnObj ="
                + returnObj);
        logger.debug("UplinkSetClientImpl : updateUplinkSet : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("UplinkSetClientImpl : updateUplinkSet : End");

        return taskResourceV2;
    }

    @Override
    public UplinkSets getUplinkSetsByName(final RestParams params,
            final String uplinkSetName)
    {
        logger.info("UplinkSetClientImpl : getUplinkSetsByName : start");
        final UplinkSetCollectionV2 uplinkSetCollectionDto = getAllUplinkSet(params);

        for (final UplinkSets uplinkSetDto : new ArrayList<>(
                uplinkSetCollectionDto.getMembers()))
        {
            if (uplinkSetDto.getName().equals(uplinkSetName))
            {
                System.out.println(uplinkSetDto.getName());
                logger.info("UplinkSetClientImpl : getUplinkSetsByName : End");
                return uplinkSetDto;
            }
        }
        logger.error("UplinkSetClientImpl : getUplinkSetsByName : Not found for name :" + uplinkSetName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.UPLINKSET, null);
    }
    // TODO - implement the remaining update methods and GetByName method
}
