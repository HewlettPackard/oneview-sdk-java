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

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class FirmwareDriverClientImpl implements FirmwareDriverClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmwareDriverClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final FirmwareDriverAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private HttpRestClient httpClient;

    protected FirmwareDriverClientImpl(HttpRestClient httpClient, FirmwareDriverAdaptor adaptor,
        TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor) {
        this.httpClient = httpClient;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static FirmwareDriverClient getClient() {
        return new FirmwareDriverClientImpl(
                HttpRestClient.getClient(),
                new FirmwareDriverAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public FwBaseline getFirmwareDriver(final RestParams params, final String resourceId) {
        LOGGER.info("FirmwareDriverClientImpl : getFirmwareDriver : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FirmwareDriverClientImpl : getFirmwareDriver : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FIRMWARE_DRIVER,
                    null);
        }
        // Call adaptor to convert to DTO

        final FwBaseline fwBaselineDto = adaptor.buildDto(returnObj);

        LOGGER.debug("FirmwareDriverClientImpl : getFirmwareDriver : name :" + fwBaselineDto.getName());
        LOGGER.info("FirmwareDriverClientImpl : getFirmwareDriver : End");

        return fwBaselineDto;
    }

    @Override
    public FwBaselineCollection getAllFirmwareDrivers(final RestParams params) {
        LOGGER.info("FirmwareDriverClientImpl : getAllFirmwareDrivers : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FirmwareDriverClientImpl : getAllFirmwareDrivers : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FIRMWARE_DRIVER,
                    null);
        }
        // Call adaptor to convert to DTO

        final FwBaselineCollection fwBaselineCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("FirmwareDriverClientImpl : getAllFirmwareDrivers : count :" + fwBaselineCollectionDto.getCount());
        LOGGER.info("FirmwareDriverClientImpl : getAllFirmwareDrivers : End");

        return fwBaselineCollectionDto;
    }

    @Override
    public FwBaseline getFirmwareDriverByName(final RestParams params, final String firmwareName) {
        LOGGER.info("FirmwareDriverClientImpl : getFirmwareDriverByName : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        final FwBaselineCollection fwBaselineCollectionDto = getAllFirmwareDrivers(params);
        for (final FwBaseline fwBaselineDto : new ArrayList<>(fwBaselineCollectionDto.getMembers())) {
            if ((fwBaselineDto.getName().replaceAll(" ", "")).equalsIgnoreCase((firmwareName.replaceAll(" ", "")))) {
                System.out.println(fwBaselineDto.getName());
                LOGGER.info("FirmwareDriverClientImpl : getFirmwareDriverByName : End");
                return fwBaselineDto;
            }
        }
        LOGGER.error("FirmwareDriverClientImpl : getFirmwareDriverByName : resource not Found for name :" + firmwareName);
        throw new SDKResourceNotFoundException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.FIRMWARE_DRIVER, null);

    }

    @Override
    public TaskResourceV2 deleteFirmwareDriver(RestParams params, String resourceId, Boolean isForce, final boolean aSync) {
        LOGGER.info("FirmwareDriverClientImpl : deleteFirmwareDriver : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FIRMWARE_DRIVER_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("FirmwareDriverClientImpl : deleteFirmwareDriver : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.FIRMWARE_DRIVER,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("FirmwareDriverClientImpl : deleteFirmwareDriver : returnObj =" + returnObj);
        LOGGER.debug("FirmwareDriverClientImpl : deleteFirmwareDriver : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is asking async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, calling the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("FirmwareDriverClientImpl : deleteFirmwareDriver : End");

        return taskResourceV2;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        FwBaseline fwBaselineDto = getFirmwareDriverByName(params, name);

        if (null != fwBaselineDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(fwBaselineDto.getUri());
        }
        return resourceId;
    }

}
