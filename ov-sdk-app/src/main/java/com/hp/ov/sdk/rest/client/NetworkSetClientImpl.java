/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.NetworkSetAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.NetworkSetCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class NetworkSetClientImpl implements NetworkSetClient
{

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    private static final Logger logger = LoggerFactory
            .getLogger(NetworkSetClientImpl.class);

    @Autowired
    private NetworkSetAdaptor adaptor;

    @Autowired
    private HttpRestClient restClient;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Override
    public NetworkSets getNetworkSets(final RestParams params, final String resourceId)
    {
        logger.info("NetworkSetClientImpl : getNetworkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.NETWORK_SETS_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkSetClientImpl : getNetworkSet : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKSET, null);
        }
        // Call adaptor to convert to DTO

        final NetworkSets networkSetDto = adaptor.buildDto(returnObj);

        logger.debug("NetworkSetClientImpl : getNetworkSet : Name :"
                + networkSetDto.getName());
        logger.info("NetworkSetClientImpl : getNetworkSet : End");

        return networkSetDto;
    }

    @Override
    public NetworkSetCollection getAllNetworkSets(final RestParams params)
    {
        logger.info("NetworkSetClientImpl : getAllNetworkSets : Start");
        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.NETWORK_SETS_URI));

        // call rest client
        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkSetClientImpl : getAllNetworkSets : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKSETS, null);
        }
        // Call adaptor to convert to DTO

        final NetworkSetCollection networkSetCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("NetworkSetClientImpl : getAllNetworkSets : members count :"
                + networkSetCollectionDto.getCount());
        logger.info("NetworkSetClientImpl : getAllNetworkSets : End");

        return networkSetCollectionDto;
    }

    @Override
    public NetworkSets getNetworkSetsByName(final RestParams params, final String name)
    {

        logger.info("NetworkSetClientImpl : getNetworkSetByName : Start");

        final String query = sdkUtils.createQueryString(name);
        logger.debug("NetworkSetClientImpl : getNetworkSetByName : query = "
                + query);

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.NETWORK_SETS_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkSetClientImpl : getNetworkSetsByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKSET, null);
        }
        // Call adaptor to convert to DTO
        NetworkSets networkSetDto;
        final NetworkSetCollection networkSetCollectionDto = adaptor.buildCollectionDto(returnObj);
        if (networkSetCollectionDto.getCount() != 0)
        {
            networkSetDto = networkSetCollectionDto.getMembers().get(0);
        }
        else
        {
            networkSetDto = null;
        }
        if (networkSetDto == null)
        {
            logger.error("NetworkSetClientImpl : getNetworkSetByName : Not found for name :"
                    + name);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.NETWORKSET, null);
        }
        logger.info("NetworkSetClientImpl : getNetworkSetsByName : End");

        return networkSetDto;
    }

    @Override
    public TaskResourceV2 createNetworkSet(final RestParams params,
            final NetworkSets networkSetDto, final boolean aSync, final boolean useJsonRequest)
    {
        logger.info("NetworkSetClientImpl : createNetworkSet : Start");
        String returnObj = null;

        // validate params
        if (networkSetDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.NETWORKSET, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.NETWORK_SETS_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(networkSetDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkSetClientImpl : createNetworkSet : returnObj ="
                + returnObj);
        logger.debug("NetworkSetClientImpl : createNetworkSet : taskResource ="
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
        logger.info("NetworkSetClientImpl : createNetworkSet : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateNetworkSet(final RestParams params,
            final String resourceId, final NetworkSets networkSetDto, final boolean aSync,
            final boolean useJsonRequest)
    {
        logger.info("NetworkSetClientImpl : updateNetworkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (networkSetDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.NETWORKSET, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.NETWORK_SETS_URI, resourceId));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(networkSetDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkSetClientImpl : updateNetworkSet : returnObj ="
                + returnObj);
        logger.debug("NetworkSetClientImpl : updateNetworkSet : taskResource ="
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
        logger.info("NetworkSetClientImpl : updateNetworkSet : End");

        return taskResourceV2;

    }

    @Override
    public TaskResourceV2 deleteNetworkSet(final RestParams params,
            final String resourceId, final boolean aSync)
    {
        logger.info("NetworkSetClientImpl : deleteNetworkSet : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.NETWORK_SETS_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("NetworkSetClientImpl : deleteNetworkSet : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.NETWORKSET, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("NetworkSetClientImpl : deleteNetworkSet : returnObj ="
                + returnObj);
        logger.debug("NetworkSetClientImpl : deleteNetworkSet : taskResource ="
                + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("NetworkSetClientImpl : deleteNetworkSet : End");

        return taskResourceV2;
    }

}
