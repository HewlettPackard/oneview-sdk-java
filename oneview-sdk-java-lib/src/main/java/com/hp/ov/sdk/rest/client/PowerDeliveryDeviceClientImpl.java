/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
 */
package com.hp.ov.sdk.rest.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.adaptors.PowerDeliveryDeviceAdaptor;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class PowerDeliveryDeviceClientImpl implements PowerDeliveryDeviceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PowerDeliveryDeviceClientImpl.class);
    private static final int TIMEOUT = 1200000; // in milliseconds = 20 mins

    private final ResourceAdaptor resourceAdaptor;
    private final PowerDeliveryDeviceAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;
    private final HttpRestClient httpClient;

    private JSONObject jsonObject;

    private PowerDeliveryDeviceClientImpl(HttpRestClient httpClient, ResourceAdaptor resourceAdaptor,
            PowerDeliveryDeviceAdaptor adaptor, TaskAdaptor taskAdaptor,
            TaskMonitorManager taskMonitor) {

        this.httpClient = httpClient;
        this.resourceAdaptor = resourceAdaptor;
        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
    }

    public static PowerDeliveryDeviceClient getClient() {
        return new PowerDeliveryDeviceClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                new PowerDeliveryDeviceAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public PowerDeliveryDevice getPowerDeliveryDeviceById(RestParams params, String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceById : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceById : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }
        // Call adaptor to convert to DTO
        final PowerDeliveryDevice powerDeliveryDeviceDto = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceById : Name :" + powerDeliveryDeviceDto.getName());
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceById : End");

        return powerDeliveryDeviceDto;
    }

    @Override
    public ResourceCollection<PowerDeliveryDevice> getAllPowerDeliveryDevices(RestParams params) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : getAllPowerDeliveryDevice : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : getAllPowerDeliveryDevice : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICES,
                    null);
        }

        ResourceCollection<PowerDeliveryDevice> powerDeliveryDeviceCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, PowerDeliveryDevice.class);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : getAllPowerDeliveryDevice : Count :" + powerDeliveryDeviceCollectionDto.getCount());
        LOGGER.info("PowerDeliveryDeviceClientImpl : getAllPowerDeliveryDevice : End");

        return powerDeliveryDeviceCollectionDto;
    }

    @Override
    public PowerDeliveryDevice getPowerDeliveryDeviceByName(final RestParams params, final String name) {
        PowerDeliveryDevice powerDeliveryDeviceDto = null;
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceByName : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<String, String>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceByName : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICES,
                    null);
        }

        ResourceCollection<PowerDeliveryDevice> powerDeliveryDeviceCollectionDto
                = resourceAdaptor.buildResourceCollection(returnObj, PowerDeliveryDevice.class);

        if (powerDeliveryDeviceCollectionDto.getCount() != 0) {
            powerDeliveryDeviceDto = powerDeliveryDeviceCollectionDto.getMembers().get(0);
        } else {
            powerDeliveryDeviceDto = null;
        }

        if (powerDeliveryDeviceDto == null) {
            LOGGER.error("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceByName : End");

        return powerDeliveryDeviceDto;
    }

    @Override
    public PowerDeliveryDevice addPowerDeliveryDevice(RestParams params, PowerDeliveryDevice powerDeliveryDeviceDto,
            boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : addPowerDeliveryDevice : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // validate params
        if (powerDeliveryDeviceDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.POWER_DEVICE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI));

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(powerDeliveryDeviceDto, params.getApiVersion());
        final String returnObj = httpClient.sendRequest(params, jsonObject);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : addPowerDeliveryDevice : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }
        // Call adaptor to convert to DTO
        final PowerDeliveryDevice powerDeliveryDeviceDtoResponse = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("PowerDeliveryDeviceClientImpl : addPowerDeliveryDevice : Name :" + powerDeliveryDeviceDtoResponse.getName());
        LOGGER.info("PowerDeliveryDeviceClientImpl : addPowerDeliveryDevice : End");

        return powerDeliveryDeviceDtoResponse;
    }

    @Override
    public TaskResourceV2 addPowerDeliveryDeviceByDiscover(RestParams params, ImportPdd importPddDto, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : addPowerDeliveryDeviceByDiscover : Start");
        String returnObj = null;

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // validate params
        if (importPddDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.POWER_DEVICE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, SdkConstants.DISCOVER));

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(importPddDto, params.getApiVersion());
        returnObj = httpClient.sendRequest(params, jsonObject);
        // convert returnObj to taskResource
        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : addPowerDeliveryDeviceByDiscover : returnObj =" + returnObj);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : addPowerDeliveryDeviceByDiscover : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : addPowerDeliveryDeviceByDiscover : End");

        return taskResourceV2;
    }

    @Override
    public PowerDeliveryDevice updatePowerDeliveryDevice(RestParams params, String resourceId,
            PowerDeliveryDevice powerDeliveryDeviceDto, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevice : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (powerDeliveryDeviceDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId));

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(powerDeliveryDeviceDto, params.getApiVersion());
        final String returnObj = httpClient.sendRequest(params, jsonObject);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevice : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }
        // Call adaptor to convert to DTO
        final PowerDeliveryDevice powerDeliveryDeviceDtoResponse = adaptor.buildDto(returnObj, params.getApiVersion());

        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevice : Name :"
                + powerDeliveryDeviceDtoResponse.getName());
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevice : End");

        return powerDeliveryDeviceDtoResponse;
    }

    @Override
    public TaskResourceV2 removePowerDeliveryDevice(RestParams params, String resourceId, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : removePowerDeliveryDevice : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI,
                resourceId));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDevice : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDevice : returnObj =" + returnObj);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDevice : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : removePowerDeliveryDevice : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 removePowerDeliveryDeviceByFilter(RestParams params, String filter, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceByFilter : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isNotBlank(filter)) {
                Map<String, String> query = new HashMap<String, String>();
                query.put("filter", filter);
                params.setQuery(query);
        } else {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceByFilter : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceByFilter : returnObj =" + returnObj);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceByFilter : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceByFilter : End");

        return taskResourceV2;
    }

    @Override
    public String removePowerDeliveryDeviceSynchronously(RestParams params, String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceSynchronously : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(
                params.getHostname(),
                ResourceUris.POWER_DEVICE_URI,
                resourceId,
                SdkConstants.SYNCHRONOUS));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceSynchronously : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }

        LOGGER.debug("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceSynchronously : returnObj =" + returnObj);
        LOGGER.info("PowerDeliveryDeviceClientImpl : removePowerDeliveryDeviceSynchronously : End");

        return returnObj;
    }

    @Override
    public Power getPowerDeliveryDevicePowerState(RestParams params, String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDevicePowerState : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.POWER_STATE));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDevicePowerState : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);

        }
        final Power powerState = Enum.valueOf(Power.class, returnObj.replaceAll("^\"|\"$", ""));

        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDevicePowerState : Value :" + powerState.toString());
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDevicePowerState : End");

        return powerState;
    }

    @Override
    public TaskResourceV2 updatePowerDeliveryDevicePowerState(RestParams params, String resourceId,
            OutletState outletStateDto, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevicePowerState : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (outletStateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.POWER_STATE));

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(outletStateDto, params.getApiVersion());
        final String returnObj = httpClient.sendRequest(params, jsonObject);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevicePowerState : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevicePowerState : returnObj =" + returnObj);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevicePowerState : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDevicePowerState : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updatePowerDeliveryDeviceRefreshState(RestParams params, String resourceId,
            PowerDeliveryDeviceRefreshRequest powerDeliveryDeviceRefreshRequestDto, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceRefreshState : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (powerDeliveryDeviceRefreshRequestDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.REFRESH_STATE));

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(powerDeliveryDeviceRefreshRequestDto, params.getApiVersion());
        final String returnObj = httpClient.sendRequest(params, jsonObject);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceRefreshState : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceRefreshState : returnObj =" + returnObj);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceRefreshState : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceRefreshState : End");

        return taskResourceV2;
    }

    @Override
    public Light getPowerDeliveryDeviceUidState(RestParams params, String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUidState : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.UID_STATE));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUidState : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }

        final Light light = Light.valueOf(returnObj.replaceAll("^\"|\"$", ""));

        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUidState : Value :" + light.toString());
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUidState : End");

        return light;
    }

    @Override
    public TaskResourceV2 updatePowerDeliveryDeviceUidState(RestParams params, String resourceId,
            OutletState outletStateDto, boolean aSync) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceUidState : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        // validate params
        if (outletStateDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.UID_STATE));

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(outletStateDto, params.getApiVersion());
        final String returnObj = httpClient.sendRequest(params, jsonObject);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceUidState : response from OV :" + returnObj);

        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.POWER_DEVICE, null);
        }

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceUidState : returnObj =" + returnObj);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceUidState : taskResource =" + taskResourceV2);

        // check for asyncOrSyncMode. if user is askign async mode, return the
        // directly the TaskResourceV2
        // if user is asking for sync mode, callig the tasking polling method
        // and send the update
        // once task is complete.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("PowerDeliveryDeviceClientImpl : updatePowerDeliveryDeviceUidState : End");

        return taskResourceV2;
    }

    @Override
    public UtilizationData getPowerDeliveryDeviceUtilization(RestParams params, String resourceId) {
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUtilization : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.POWER_DEVICE_URI, resourceId, SdkConstants.UTILIZATION));

        final String returnObj = httpClient.sendRequest(params);
        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUtilization : response from OV :" + returnObj);
        if (null == returnObj || returnObj.equals("")) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.POWER_DEVICE,
                    null);
        }
        // Call adaptor to convert to DTO
        final UtilizationData UtilizationDataDto = adaptor.buildUtilizationDataDto(returnObj, params.getApiVersion());

        LOGGER.debug("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUtilization : Object :" + UtilizationDataDto.toString());
        LOGGER.info("PowerDeliveryDeviceClientImpl : getPowerDeliveryDeviceUtilization : End");

        return UtilizationDataDto;
    }

}
