/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.FirmwareDriverAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class FirmwareDriverClientImpl implements FirmwareDriverClient {

    private static final Logger logger = LoggerFactory.getLogger(FirmwareDriverClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private FirmwareDriverAdaptor adaptor;

    @Autowired
    private UrlUtils urlUtils;

    @Autowired
    private TaskAdaptor taskAdaptor;

    @Autowired
    private TaskMonitorManager taskMonitor;

    @Override
    public FwBaseline getFirmwareDriver(final RestParams params, final String resourceId) {
        logger.info("FirmwareDriverClientImpl : getFirmwareDriver : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FirmwareDriverClientImpl : getFirmwareDriver : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FIRMWARE_DRIVER,
                    null);
        }
        // Call adaptor to convert to DTO

        final FwBaseline fwBaselineDto = adaptor.buildDto(returnObj);

        logger.debug("FirmwareDriverClientImpl : getFirmwareDriver : name :" + fwBaselineDto.getName());
        logger.info("FirmwareDriverClientImpl : getFirmwareDriver : End");

        return fwBaselineDto;
    }

    @Override
    public FwBaselineCollection getAllFirmwareDrivers(final RestParams params) {
        logger.info("FirmwareDriverClientImpl : getAllFirmwareDrivers : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FirmwareDriverClientImpl : getAllFirmwareDrivers : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FIRMWARE_DRIVER,
                    null);
        }
        // Call adaptor to convert to DTO

        final FwBaselineCollection fwBaselineCollectionDto = adaptor.buildCollectionDto(returnObj);

        logger.debug("FirmwareDriverClientImpl : getAllFirmwareDrivers : count :" + fwBaselineCollectionDto.getCount());
        logger.info("FirmwareDriverClientImpl : getAllFirmwareDrivers : End");

        return fwBaselineCollectionDto;
    }

    @Override
    public FwBaseline getFirmwareDriverByName(final RestParams params, final String firmwareName) {
        logger.info("FirmwareDriverClientImpl : getFirmwareDriverByName : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        final FwBaselineCollection fwBaselineCollectionDto = getAllFirmwareDrivers(params);
        for (final FwBaseline fwBaselineDto : new ArrayList<>(fwBaselineCollectionDto.getMembers())) {
            if ((fwBaselineDto.getName().replaceAll(" ", "")).equalsIgnoreCase((firmwareName.replaceAll(" ", "")))) {
                System.out.println(fwBaselineDto.getName());
                logger.info("FirmwareDriverClientImpl : getFirmwareDriverByName : End");
                return fwBaselineDto;
            }
        }
        logger.error("FirmwareDriverClientImpl : getFirmwareDriverByName : resource not Found for name :" + firmwareName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.FIRMWARE_DRIVER, null);

    }

    @Override
    public TaskResourceV2 deleteFirmwareDriver(RestParams params, String resourceId, Boolean isForce, final boolean aSync,
            final boolean useJsonRequest) {
        logger.info("FirmwareDriverClientImpl : deleteFirmwareDriver : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(urlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI, resourceId));

        final String returnObj = restClient.sendRequestToHPOV(params, null);
        logger.debug("FirmwareDriverClientImpl : deleteFirmwareDriver : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FIRMWARE_DRIVER,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        logger.debug("FirmwareDriverClientImpl : deleteFirmwareDriver : returnObj =" + returnObj);
        logger.debug("FirmwareDriverClientImpl : deleteFirmwareDriver : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        logger.info("FirmwareDriverClientImpl : deleteFirmwareDriver : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams creds, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        FwBaseline fwBaselineDto = getFirmwareDriverByName(creds, name);

        if (null != fwBaselineDto.getUri()) {
            resourceId = urlUtils.getResourceIdFromUri(fwBaselineDto.getUri());
        }
        return resourceId;
    }

}
