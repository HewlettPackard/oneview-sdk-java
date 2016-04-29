/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.DeviceManagerAdaptor;
import com.hp.ov.sdk.adaptors.TaskAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.DeviceManagerResponseCollection;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;


public class FcSansDeviceManagerClientImpl implements FcSansDeviceManagerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSansDeviceManagerClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final DeviceManagerAdaptor adaptor;
    private final TaskAdaptor taskAdaptor;
    private final TaskMonitorManager taskMonitor;

    private final HttpRestClient restClient;

    private JSONObject jsonObject;

    protected FcSansDeviceManagerClientImpl(DeviceManagerAdaptor adaptor, TaskAdaptor taskAdaptor, TaskMonitorManager taskMonitor,
            HttpRestClient restClient) {

        this.adaptor = adaptor;
        this.taskAdaptor = taskAdaptor;
        this.taskMonitor = taskMonitor;
        this.restClient = restClient;
    }

    public static FcSansDeviceManagerClient getClient() {
        return new FcSansDeviceManagerClientImpl(
                new DeviceManagerAdaptor(),
                TaskAdaptor.getInstance(),
                TaskMonitorManager.getInstance(),
                HttpRestClient.getClient());
    }

    @Override
    public TaskResourceV2 createDeviceManager(final RestParams params, final String providerUrl,
            final DeviceManagerResponse addDeviceManagerResponseDto, final boolean aSync) {
        LOGGER.trace("DeviceManagerClientImpl : createDeviceManager : Start");
        String returnObj = null;

        // validate params
        if (addDeviceManagerResponseDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.DEVICE_MANAGER, null);
        }
        // set the additional params
        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), providerUrl));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(addDeviceManagerResponseDto);
        returnObj = restClient.sendRequest(params, jsonObject, true);

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("DeviceManagerClientImpl : createDeviceManager : returnObj =" + returnObj);
        LOGGER.debug("DeviceManagerClientImpl : createDeviceManager : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("DeviceManagerClientImpl : createDeviceManager : End");

        return taskResourceV2;
    }

    @Override
    public DeviceManagerResponseCollection getAllDeviceManager(final RestParams params) {
        LOGGER.trace("DeviceManagerClientImpl : getAllDeviceManager : Start");
        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }

        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("DeviceManagerClientImpl : getAllDeviceManager : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance,
                    null, null, null, SdkConstants.DEVICE_MANAGER, null);
        }

        // Call adaptor to convert to DTO
        DeviceManagerResponseCollection deviceManagerResponseCollectionDto = adaptor.buildCollectionDto(returnObj);

        LOGGER.debug("DeviceManagerClientImpl : getAllDeviceManager : count :" + deviceManagerResponseCollectionDto.getCount());
        LOGGER.trace("DeviceManagerClientImpl : getAllDeviceManager : End");

        return deviceManagerResponseCollectionDto;
    }

    @Override
    public DeviceManagerResponse getDeviceManager(final RestParams params, final String resourceId) {
        DeviceManagerResponse deviceManagerResponseDto = null;
        LOGGER.trace("DeviceManagerClientImpl : getDeviceManager : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("DeviceManagerClientImpl : getDeviceManager : response from OV :" + returnObj);
        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }
        // Call adaptor to convert to DTO

        deviceManagerResponseDto = adaptor.buildDto(returnObj);

        LOGGER.debug("DeviceManagerClientImpl : getDeviceManager : name :" + deviceManagerResponseDto.getName());
        LOGGER.trace("DeviceManagerClientImpl : getDeviceManager : End");

        return deviceManagerResponseDto;
    }

    @Override
    public TaskResourceV2 deleteDeviceManager(final RestParams params, final String resourceId, final boolean aSync) {
        LOGGER.trace("DeviceManagerClientImpl : deleteDeviceManager : Start");

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument,
                    null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("DeviceManagerClientImpl : deleteDeviceManager : returnObj =" + returnObj);

        TaskResourceV2 taskResourceV2 = new TaskResourceV2();
        taskResourceV2.setTaskState(TaskState.Completed);

        // OV 2.0 returns code 200 with no task and the string ""OK"" in the body
        // This IF should catch responses from OV 3.0 +
        if (!returnObj.equalsIgnoreCase("\"OK\"")) {
            taskResourceV2 = taskAdaptor.buildDto(returnObj);

            LOGGER.debug("DeviceManagerClientImpl : deleteDeviceManager : taskResource =" + taskResourceV2);
            // check for aSync flag. if user is asking async mode, return directly
            // the TaskResourceV2
            // if user is asking for sync mode, call task monitor polling method and
            // send the update
            // once task is complete or exceeds the timeout.
            if (taskResourceV2 != null && aSync == false) {
                taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
            }
        }

        LOGGER.info("DeviceManagerClientImpl : deleteDeviceManager : End");

        return taskResourceV2;
    }

    @Override
    public TaskResourceV2 updateDeviceManager(final RestParams params, final String resourceId,
            final DeviceManagerResponse updateDeviceManagerResponseDto, final boolean useJsonRequest, final boolean aSync) {
        LOGGER.trace("DeviceManagerClientImpl : updateDeviceManager : Start");
        String returnObj = null;

        // validate params
        if (updateDeviceManagerResponseDto == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.DEVICE_MANAGER, null);
        }
        // set the additional params
        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId));

        // TODO - check for json request in the input dto. if it is present,
        // then
        // convert that into jsonObject and pass it rest client
        // idea is : user can create json string and call the sdk api.
        // user can save time in creating network dto.

        // create JSON request from dto
        jsonObject = adaptor.buildJsonObjectFromDto(updateDeviceManagerResponseDto);
        returnObj = restClient.sendRequest(params, jsonObject, true);

        TaskResourceV2 taskResourceV2 = taskAdaptor.buildDto(returnObj);

        LOGGER.debug("DeviceManagerClientImpl : updateDeviceManager : returnObj =" + returnObj);
        LOGGER.debug("DeviceManagerClientImpl : updateDeviceManager : taskResource =" + taskResourceV2);

        // check for aSync flag. if user is asking async mode, return directly
        // the TaskResourceV2
        // if user is asking for sync mode, call task monitor polling method and
        // send the update
        // once task is complete or exceeds the timeout.
        if (taskResourceV2 != null && aSync == false) {
            taskResourceV2 = taskMonitor.checkStatus(params, taskResourceV2.getUri(), TIMEOUT);
        }
        LOGGER.info("DeviceManagerClientImpl : updateDeviceManager : End");

        return taskResourceV2;
    }

    @Override
    public DeviceManagerResponse getDeviceManagerByName(final RestParams params, final String name) {
        DeviceManagerResponse deviceManagerResponseDto = null;
        LOGGER.trace("DeviceManagerClientImpl : getDeviceManagerByName : Start");
        final String query = UrlUtils.createQueryString(name);

        // validate args
        if (null == params) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.APPLIANCE, null);
        }
        // set the additional params
        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestQueryUrl(params.getHostname(),
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, query));

        final String returnObj = restClient.sendRequest(params);
        LOGGER.debug("DeviceManagerClientImpl : getDeviceManagerByName : response from OV :" + returnObj);

        if (Strings.isNullOrEmpty(returnObj)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }

        // Call adaptor to convert to DTO
        final DeviceManagerResponseCollection deviceManagerResponseCollectionDto = adaptor.buildCollectionDto(returnObj);

        if (deviceManagerResponseCollectionDto.getCount() != 0) {
            deviceManagerResponseDto = deviceManagerResponseCollectionDto.getMembers().get(0);
        } else {
            deviceManagerResponseDto = null;
        }

        if (deviceManagerResponseDto == null) {
            LOGGER.error("DeviceManagerClientImpl : getDeviceManagerByName : Not found for name :" + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null, SdkConstants.DEVICE_MANAGER,
                    null);
        }
        LOGGER.trace("DeviceManagerClientImpl : getDeviceManagerByName : End");

        return deviceManagerResponseDto;
    }

    @Override
    public String getId(final RestParams params, final String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        DeviceManagerResponse deviceManagerResponseDto = getDeviceManagerByName(params, name);

        if (null != deviceManagerResponseDto.getUri()) {
            resourceId = UrlUtils.getResourceIdFromUri(deviceManagerResponseDto.getUri());
        }
        return resourceId;
    }

}
