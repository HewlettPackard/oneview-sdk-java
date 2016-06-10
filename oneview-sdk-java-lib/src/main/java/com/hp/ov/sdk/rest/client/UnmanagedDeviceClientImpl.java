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

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.hp.ov.sdk.adaptors.ResourceAdaptor;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.http.core.client.HttpRestClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.tasks.TaskMonitorManager;
import com.hp.ov.sdk.util.UrlUtils;

public class UnmanagedDeviceClientImpl implements UnmanagedDeviceClient {

    private Logger LOGGER = LoggerFactory.getLogger(UnmanagedDeviceClientImpl.class);
    private static final int TIMEOUT = 60000; // in milliseconds = 1 mins

    private final HttpRestClient restClient;
    private final ResourceAdaptor adaptor;
    private final TaskMonitorManager taskMonitor;

    private UnmanagedDeviceClientImpl(
            HttpRestClient restClient,
            ResourceAdaptor adaptor,
            TaskMonitorManager taskMonitor) {

        this.restClient = restClient;
        this.adaptor = adaptor;
        this.taskMonitor = taskMonitor;
    }

    public static UnmanagedDeviceClient getClient() {
        return new UnmanagedDeviceClientImpl(
                HttpRestClient.getClient(),
                new ResourceAdaptor(),
                TaskMonitorManager.getInstance());
    }

    @Override
    public UnmanagedDevice getUnmanagedDevice(RestParams params, String resourceId) {
        LOGGER.trace("UnmanagedDeviceClientImpl : getUnmanagedDevice : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("UnmanagedDeviceClientImpl : getUnmanagedDevice : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        UnmanagedDevice unmanagedDevice = adaptor.buildResourceObject(response, UnmanagedDevice.class);

        LOGGER.trace("UnmanagedDeviceClientImpl : getUnmanagedDevice : End");

        return unmanagedDevice;
    }

    @Override
    public ResourceCollection<UnmanagedDevice> getAllUnmanagedDevices(RestParams params) {
        LOGGER.trace("UnmanagedDeviceClientImpl : getAllUnmanagedDevices : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("UnmanagedDeviceClientImpl : getAllUnmanagedDevices : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICES, null);
        }

        ResourceCollection<UnmanagedDevice> unmanagedDevices
                = adaptor.buildResourceCollection(response, UnmanagedDevice.class);

        LOGGER.trace("UnmanagedDeviceClientImpl : getAllUnmanagedDevices : End");

        return unmanagedDevices;
    }

    @Override
    public UnmanagedDevice getUnmanagedDeviceByName(RestParams params, String name) {
        LOGGER.trace("UnmanagedDeviceClientImpl : getUnmanagedDeviceByName : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        Map<String, String> query = new HashMap<>();
        query.put("filter", "name='" + name + "'");
        params.setQuery(query);

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("UnmanagedDeviceClientImpl : getUnmanagedDeviceByName : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICES, null);
        }

        UnmanagedDevice unmanagedDevice = null;
        ResourceCollection<UnmanagedDevice> unmanagedDevices
                = adaptor.buildResourceCollection(response, UnmanagedDevice.class);

        if (!unmanagedDevices.isEmpty()) {
            unmanagedDevice = unmanagedDevices.getMembers().get(0);
        }

        if (unmanagedDevice == null) {
            LOGGER.warn("UnmanagedDeviceClientImpl : getUnmanagedDeviceByName : Not found for name : " + name);
            throw new SDKResourceNotFoundException(SDKErrorEnum.resourceNotFound, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        LOGGER.trace("UnmanagedDeviceClientImpl : getUnmanagedDeviceByName : End");

        return unmanagedDevice;
    }

    @Override
    public UnmanagedDevice addUnmanagedDevice(RestParams params, UnmanagedDevice unmanagedDevice) {
        LOGGER.trace("UnmanagedDeviceClientImpl : addUnmanagedDevice : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        if (unmanagedDevice == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        params.setType(HttpMethodType.POST);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI));

        JSONObject jsonObject = adaptor.buildJsonRequest(unmanagedDevice, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("UnmanagedDeviceClientImpl : addUnmanagedDevice : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        UnmanagedDevice addedUnmanagedDevice = adaptor.buildResourceObject(response, UnmanagedDevice.class);

        LOGGER.trace("UnmanagedDeviceClientImpl : addUnmanagedDevice : End");

        return addedUnmanagedDevice;
    }

    @Override
    public UnmanagedDevice updateUnmanagedDevice(RestParams params, String resourceId,
            UnmanagedDevice unmanagedDevice) {

        LOGGER.trace("UnmanagedDeviceClientImpl : updateUnmanagedDevice : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }
        if (unmanagedDevice == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        params.setType(HttpMethodType.PUT);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI, resourceId));

        JSONObject jsonObject = adaptor.buildJsonRequest(unmanagedDevice, params.getApiVersion());
        String response = restClient.sendRequest(params, jsonObject);

        LOGGER.debug("UnmanagedDeviceClientImpl : updateUnmanagedDevice : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        UnmanagedDevice updatedUnmanagedDevice = adaptor.buildResourceObject(response, UnmanagedDevice.class);

        LOGGER.trace("UnmanagedDeviceClientImpl : updateUnmanagedDevice : End");

        return updatedUnmanagedDevice;
    }

    @Override
    public String removeUnmanagedDevice(RestParams params, String resourceId) {
        LOGGER.trace("UnmanagedDeviceClientImpl : removeUnmanagedDevice : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI, resourceId));

        String response = restClient.sendRequest(params);

        LOGGER.debug("UnmanagedDeviceClientImpl : removeUnmanagedDevice : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        LOGGER.trace("UnmanagedDeviceClientImpl : removeUnmanagedDevice : End");

        return "Removed";
    }

    @Override
    public TaskResourceV2 removeUnmanagedDeviceByFilter(RestParams params, String filter, boolean aSync) {
        LOGGER.trace("UnmanagedDeviceClientImpl : removeUnmanagedDeviceByFilter : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        if (StringUtils.isBlank(filter)) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        Map<String, String> query = new HashMap<>();
        query.put("filter", filter);
        params.setQuery(query);

        params.setType(HttpMethodType.DELETE);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("UnmanagedDeviceClientImpl : removeUnmanagedDeviceByFilter : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        TaskResourceV2 taskResource = adaptor.buildResourceObject(response, TaskResourceV2.class);

        if ((taskResource != null) && (aSync == false)) {
            taskResource = taskMonitor.checkStatus(params, taskResource.getUri(), TIMEOUT);
        }
        LOGGER.trace("UnmanagedDeviceClientImpl : removeUnmanagedDeviceByFilter : End");

        return taskResource;
    }

    @Override
    public EnvironmentalConfiguration getEnvironmentalConfiguration(RestParams params, String resourceId) {
        LOGGER.trace("UnmanagedDeviceClientImpl : getEnvironmentalConfiguration : Start");

        if (params == null) {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null,
                    SdkConstants.APPLIANCE, null);
        }

        params.setType(HttpMethodType.GET);
        params.setUrl(UrlUtils.createRestUrl(params.getHostname(), ResourceUris.UNMANAGED_DEVICE_URI,
                resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI));

        String response = restClient.sendRequest(params);

        LOGGER.debug("UnmanagedDeviceClientImpl : getEnvironmentalConfiguration : response from OV : " + response);

        if (Strings.isNullOrEmpty(response)) {
            throw new SDKNoResponseException(SDKErrorEnum.noResponseFromAppliance, null, null, null,
                    SdkConstants.UNMANAGED_DEVICE, null);
        }

        EnvironmentalConfiguration environmentalConfiguration = adaptor.buildResourceObject(response,
                EnvironmentalConfiguration.class);

        LOGGER.trace("UnmanagedDeviceClientImpl : getEnvironmentalConfiguration : End");

        return environmentalConfiguration;
    }

    @Override
    public String getId(RestParams params, String name) {
        String resourceId = "";
        // fetch resource Id using resource name
        UnmanagedDevice unmanagedDevice = this.getUnmanagedDeviceByName(params, name);

        if (null != unmanagedDevice) {
            resourceId = UrlUtils.getResourceIdFromUri(unmanagedDevice.getUri());
        }
        return resourceId;
    }
}
