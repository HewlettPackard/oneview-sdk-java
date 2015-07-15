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

import com.hp.ov.sdk.adaptors.LogicalInterconnectAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.SdkUtils;

@Component
public class LogicalInterconnectClientImpl implements LogicalInterconnectClient
{

    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins
    public static final Logger logger = LoggerFactory
            .getLogger(LogicalInterconnectClientImpl.class);

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private LogicalInterconnectAdaptor adaptor;

    private JSONObject jsonObject;

    @Autowired
    private SdkUtils sdkUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public LogicalInterconnects getLogicalInterconnect(final RestParams params,
            final String resourceId)
    {
        logger.info("LogicalInterconnectClientImpl : getLogicalInterconnect : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectClientImpl : getLogicalInterconnect : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final LogicalInterconnects logicalInterconnectDto = adaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectClientImpl : getLogicalInterconnect : Name :"
                + logicalInterconnectDto.getName());
        logger.info("LogicalInterconnectClientImpl : getLogicalInterconnect : End");

        return logicalInterconnectDto;
    }

    @Override
    public LogicalInterconnectCollectionV2 getAllLogicalInterconnects(
            final RestParams params)
    {
        logger.info("LogicalInterconnectClientImpl : getAllLogicalInterconnects : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectClientImpl : getAllLogicalInterconnects : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECTS, null);
        }
        // Call adaptor to convert to DTO

        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = adaptor
                .buildCollectionDto(returnObj);

        logger.debug("LogicalInterconnectClientImpl : getAllLogicalInterconnects : members count :"
                + logicalInterconnectCollectionDto.getCount());
        logger.info("LogicalInterconnectClientImpl : getAllLogicalInterconnects : End");

        return logicalInterconnectCollectionDto;
    }

    @Override
    public LogicalInterconnects getLogicalInterconnectByName(final RestParams params,
            final String logicalInterconnectName)
    {
        logger.info("LogicalInterconnectClientImpl : getLogicalInterconnectByName : start");
        final LogicalInterconnectCollectionV2 logicalInterconnectCollectionDto = getAllLogicalInterconnects(params);

        for (final LogicalInterconnects logicalInterconnectDto : new ArrayList<>(
                logicalInterconnectCollectionDto.getMembers()))
        {
            if (logicalInterconnectDto.getName().equals(logicalInterconnectName))
            {
                System.out.println(logicalInterconnectDto.getName());
                logger.info("LogicalInterconnectClientImpl : getLogicalInterconnectByName : End");
                return logicalInterconnectDto;
            }
        }
        logger.error("LogicalInterconnectClientImpl : getLogicalInterconnectByName : resource not Found for name :"
                + logicalInterconnectName);
        throw new SDKResourceNotFoundException(
                SDKErrorEnum.resourceNotFound, null, null, null,
                SdkConstants.LOGICAL_INTERCONNECT, null);
    }

    @Override
    public LiFirmware getLogicalInterconnectFirmware(final RestParams params,
            final String resourceId)
    {
        logger.info("LogicalInterconnectClientImpl : getLogicalInterconnectFirmware : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId, "firmware"));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("LogicalInterconnectClientImpl : getLogicalInterconnectFirmware : response from OV :"
                + returnObj);
        if (null == returnObj || returnObj.equals(""))
        {
            throw new SDKNoResponseException(
                    SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // Call adaptor to convert to DTO

        final LiFirmware liFirmwareDto = adaptor.buildFirmwareDto(returnObj);

        logger.info("LogicalInterconnectClientImpl : getLogicalInterconnectFirmware : End");

        return liFirmwareDto;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectSnmpConfigurationById(
            final RestParams params, final String resourceId,
            final SnmpConfiguration snmpConfigurationDto, final boolean asyncOrSyncMode,
            final boolean useJsonRequest)
    {

        logger.info("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (snmpConfigurationDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId + "/"
                        + "snmp-configuration"));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating logical interconnect dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(snmpConfigurationDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById : returnObj ="
                + returnObj);
        logger.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("LogicalInterconnectClientImpl : updateLogicalInterconnectSnmpConfigurationById  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectComplianceById(
            final RestParams params, final String resourceId, final boolean asyncOrSyncMode)
    {
        logger.info("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId + "/"
                        + "compliance"));
        String returnObj = null;

        // create JSON request from dto
        jsonObject = null;
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById : returnObj ="
                + returnObj);
        logger.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById : taskResource ="
                + taskResourceV2);

        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("LogicalInterconnectClientImpl : updateLogicalInterconnectComplianceById  : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateLogicalInterconnectFirmwareById(
            final RestParams params, final String resourceId, final LiFirmware lIFirmwareDto,
            final boolean asyncOrSyncMode, final boolean useJsonRequest)
    {

        logger.info("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById : Start");

        // validate args
        if (null == params)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (lIFirmwareDto == null)
        {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.LOGICAL_INTERCONNECT, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(sdkUtils.createRestUrl(params.getHostname(),
                ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId + "/"
                        + "firmware"));
        String returnObj = null;

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating logical interconnect dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(lIFirmwareDto);
        returnObj = restClient.sendRequestToHPOV(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById : returnObj ="
                + returnObj);
        logger.debug("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById : taskResource ="
                + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && asyncOrSyncMode == false)
        {
            taskResourceV2 = taskMonitor.checkStatus(params,
                    taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("LogicalInterconnectClientImpl : updateLogicalInterconnectFirmwareById  : End");

        return taskResourceV2;
    }

    // TODO - implement the remaining update methods and GetByName method
}
