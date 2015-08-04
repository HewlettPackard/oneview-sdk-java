/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.adaptors.FirmwareDriverAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;

@Component
public class FirmwareDriverClientImpl implements FirmwareDriverClient {

    private static final Logger logger = LoggerFactory.getLogger(FirmwareDriverClientImpl.class);
    @Autowired
    private HttpRestClient restClient;

    @Autowired
    private FirmwareDriverAdaptor adaptor;

    @Autowired
    private UrlUtils urlUtils;

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
    // TODO - implement the remaining update methods and GetByName method

}
