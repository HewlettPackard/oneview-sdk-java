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
package com.hp.ov.sdk.rest.client.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.TaskState;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class FcSanDeviceManagerClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FcSanDeviceManagerClient.class);

    private final BaseClient baseClient;

    public FcSanDeviceManagerClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link DeviceManagerResponse} details for the specified SAN manager.
     *
     * @param resourceId SAN manager resource identifier as seen in HPE OneView.
     *
     * @return {@link DeviceManagerResponse} object containing the details.
     */
    public DeviceManagerResponse getById(String resourceId) {
        LOGGER.info("FcSanDeviceManagerClient : getById : Start");

        DeviceManagerResponse deviceManager = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId),
                DeviceManagerResponse.class);

        LOGGER.info("FcSanDeviceManagerClient : getById : End");

        return deviceManager;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DeviceManagerResponse}&gt; containing details
     * for all the available SAN managers found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link DeviceManagerResponse}&gt; containing
     * the details for all found SAN managers.
     */
    public ResourceCollection<DeviceManagerResponse> getAll() {
        LOGGER.info("FcSanDeviceManagerClient : getAll : Start");

        ResourceCollection<DeviceManagerResponse> deviceManagers = baseClient.getResourceCollection(
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, DeviceManagerResponse.class);

        LOGGER.info("FcSanDeviceManagerClient : getAll : End");

        return deviceManagers;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DeviceManagerResponse}&gt; containing details
     * for the available SAN managers found under the current HPE OneView that match the name.
     *
     * @param name SAN manager name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link DeviceManagerResponse}&gt; containing
     * the details for the found SAN managers.
     */
    public ResourceCollection<DeviceManagerResponse> getByName(String name) {
        LOGGER.info("FcSanDeviceManagerClient : getByName : Start");

        ResourceCollection<DeviceManagerResponse> deviceManagers = baseClient.getResourceCollection(
                ResourceUris.FC_SANS_DEVICE_MANAGER_URI, DeviceManagerResponse.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("FcSanDeviceManagerClient : getByName : End");

        return deviceManagers;
    }

    /**
     * Adds a SAN manager according to the provided {@link DeviceManagerResponse} object and provider URI.
     * The request can be processed synchronously or asynchronously.
     *
     * @param providerDeviceManagerUri URI for the provider of the device manager
     * @param deviceManager object containing the SAN manager credential details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 add(String providerDeviceManagerUri,
            DeviceManagerResponse deviceManager, boolean aSync) {
        LOGGER.info("FcSanDeviceManagerClient : add : Start");

        Request request = new Request(HttpMethodType.POST, providerDeviceManagerUri, deviceManager);

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("FcSanDeviceManagerClient : add : End");

        return taskResource;
    }

    /**
     * Updates a SAN manager identified by the given resource identifier.
     *
     * @param resourceId SAN manager resource identifier as seen in HPE OneView.
     * @param deviceManager object containing the SAN manager details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, DeviceManagerResponse deviceManager, boolean aSync) {
        LOGGER.info("FcSanDeviceManagerClient : update : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId), deviceManager);

        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("FcSanDeviceManagerClient : update : End");

        return taskResource;
    }

    /**
     * Removes the SAN manager identified by the given resource identifier.
     *
     * @param resourceId SAN manager resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 remove(String resourceId, boolean aSync) {
        LOGGER.info("FcSanDeviceManagerClient : remove : Start");

        String requestUri = UrlUtils.createUrl(ResourceUris.FC_SANS_DEVICE_MANAGER_URI, resourceId);

        TaskResourceV2 taskResource;

        // OV 2.0 returns code 200 with no task and the string ""OK"" in the body
        // This IF should catch responses from OV 3.0+
        if (this.baseClient.getApiVersion().getValue() >= ApiVersion.V_300.getValue()) {
            taskResource = baseClient.deleteResource(requestUri, aSync);
        } else {
            Request request = new Request(HttpMethodType.DELETE, requestUri);

            this.baseClient.executeRequest(request, String.class);

            taskResource = new TaskResourceV2();
            taskResource.setComputedPercentComplete(Integer.valueOf(100));
            taskResource.setTaskState(TaskState.Completed);
        }

        LOGGER.info("FcSanDeviceManagerClient : remove : End");

        return taskResource;
    }

}
