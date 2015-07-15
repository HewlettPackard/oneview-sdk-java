/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.LogicalInterconnectGroupAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.LogicalInterconnectGroupCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class LogicalInterconnectGroupClientImpl implements
        LogicalInterconnectGroupClient
{

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    public static final Logger logger = LoggerFactory
            .getLogger(LogicalInterconnectGroupClientImpl.class);

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private LogicalInterconnectGroupAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    /*
     * (non-Javadoc)
     * 
     * @see com.hp.ov.sdk.rest.services.LogicalInterconnectGroupClient#
     * getLogicalInterconnectGroup(com.hp.ov.sdk.rest.client.RestParams,
     * java.lang.String)
     */
    @Override
    public LogicalInterconnectGroups getLogicalInterconnectGroup(
            final RestParams params, final String resourceId)
    {

        logger.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO
        final LogicalInterconnectGroups logicalInterconnectGroupDto = adaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : Name :"
                + logicalInterconnectGroupDto.getName());
        logger.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroup : End");

        return logicalInterconnectGroupDto;
    }

    @Override
    public LogicalInterconnectGroupCollectionV2 getAllLogicalInterconnectGroups(
            final RestParams params)
    {
        logger.info("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO

        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = adaptor
                .buildCollectionDto(returnObj);

        logger.debug("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : members count :"
                + logicalInterconnectGroupCollectionDto.getCount());
        logger.info("LogicalInterconnectGroupClientImpl : getAllLogicalInterconnectGroups : End");

        return logicalInterconnectGroupCollectionDto;

    }

    @Override
    public LogicalInterconnectGroups getLogicalInterconnectGroupByName(
            final RestParams params, final String name)
    {
        logger.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : Start");
        // String query = "filter=\"name=\'" + name + "\'\"";
        final String query = sdkUtils.createQueryString(name);
        logger.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : query = "
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
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, query));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }
        // Call adaptor to convert to DTO
        LogicalInterconnectGroups logicalInterconnectGroupDto;
        final LogicalInterconnectGroupCollectionV2 logicalInterconnectGroupCollectionDto = adaptor
                .buildCollectionDto(returnObj);
        if (logicalInterconnectGroupCollectionDto.getCount() != 0)
        {
            logicalInterconnectGroupDto = logicalInterconnectGroupCollectionDto
                    .getMembers().get(0);
        }
        else
        {
            logicalInterconnectGroupDto = null;
        }

        if (logicalInterconnectGroupDto == null)
        {
            logger.error("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : resource not found for name :"
                    + name);
            throw new SDKResourceNotFoundException(
                    SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUPS, null);
        }
        logger.info("LogicalInterconnectGroupClientImpl : getLogicalInterconnectGroupByName : End");

        return logicalInterconnectGroupDto;
    }

    @Override
    public TaskResourceV2 createLogicalInterconnectGroup(final RestParams params,
            final LogicalInterconnectGroups logicalInterconnectGroupDto,
            final boolean aSync, final boolean useJsonRequest)
    {
        logger.info("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : Start");
        String returnObj = null;

        // validate params
        if (logicalInterconnectGroupDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.LOGICAL_INTERCONNECT_GROUP,
                    null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating dto.

        // create JSON request from dto
        jsonObject = adaptor
                .buildJsonObjectFromDto(logicalInterconnectGroupDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : returnObj ="
                + returnObj);
        logger.debug("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : taskResource ="
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
        logger.info("LogicalInterconnectGroupClientImpl : createLogicalInterconnectGroup : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectGroup(final RestParams params,
            final String resourceId, final List<UplinkSet> uplinkSetDto, final boolean aSync,
            final boolean useJsonRequest)
    {

        logger.info("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : Start");
        String returnObj = null;

        // validate params
        if (uplinkSetDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.UPLINKSET, null);
        }

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating dto.

        // fetch LIG data using resourceId and create JSON request from dto
        final LogicalInterconnectGroups logicalInterconnectGroupDto = getLogicalInterconnectGroup(params,
                resourceId);
        logicalInterconnectGroupDto.setUplinkSets(uplinkSetDto);
        jsonObject = adaptor
                .buildJsonObjectFromDto(logicalInterconnectGroupDto);

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : returnObj ="
                + returnObj);
        logger.debug("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : taskResource ="
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
        logger.info("LogicalInterconnectGroupClientImpl : updateLogicalInterconnectGroup : End");
        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 deleteLogicalInterconnectGroup(final RestParams params,
            final String resourceId, final boolean aSync)
    {
        logger.info("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_GROUPS_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectGroupClient : deleteLogicalInterconnectGroup : response from OV :"
                + returnObj);

        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT_GROUP, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : returnObj ="
                + returnObj);
        logger.debug("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : taskResource ="
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
        logger.info("LogicalInterconnectGroupClientImpl : deleteLogicalInterconnectGroup : End");

        return taskResourceV2;
    }

    //TODO
    @Override
    public InterconnectSettingsV2 getDefaultInterconnectSettings(
            final RestParams params)
    {
        // TODO Auto-generated method stub
        return null;
    }

    //TODO
    @Override
    public InterconnectSettingsV2 getInterconnectSettings(final RestParams params,
            final String resourceId, final String settingId)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
