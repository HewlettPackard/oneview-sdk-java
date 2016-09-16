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
package com.hp.ov.sdk.rest.client.facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class UnmanagedDeviceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(UnmanagedDeviceClient.class);

    private final BaseClient baseClient;

    public UnmanagedDeviceClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link UnmanagedDevice} details for the specified unmanaged device.
     *
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return {@link UnmanagedDevice} object containing the details.
     */
    public UnmanagedDevice getById(String resourceId) {
        LOGGER.info("UnmanagedDeviceClient : getById : Start");

        UnmanagedDevice unmanagedDevice = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.UNMANAGED_DEVICE_URI, resourceId), UnmanagedDevice.class);

        LOGGER.info("UnmanagedDeviceClient : getById : End");

        return unmanagedDevice;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link UnmanagedDevice}&gt; containing details
     * for all the available unmanaged devices found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link UnmanagedDevice}&gt; containing
     * the details for all found unmanaged devices.
     */
    public ResourceCollection<UnmanagedDevice> getAll() {
        LOGGER.info("UnmanagedDeviceClient : getAll : Start");

        ResourceCollection<UnmanagedDevice> unmanagedDevices = baseClient.getResourceCollection(
                ResourceUris.UNMANAGED_DEVICE_URI, UnmanagedDevice.class);

        LOGGER.info("UnmanagedDeviceClient : getAll : End");

        return unmanagedDevices;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link UnmanagedDevice}&gt; containing details
     * for the available unmanaged devices found under the current HPE OneView that match the name.
     *
     * @param name unmanaged device name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link UnmanagedDevice}&gt; containing
     * the details for the found unmanaged devices.
     */
    public ResourceCollection<UnmanagedDevice> getByName(String name) {
        LOGGER.info("UnmanagedDeviceClient : getByName : Start");

        ResourceCollection<UnmanagedDevice> unmanagedDevices = baseClient.getResourceCollection(
                ResourceUris.UNMANAGED_DEVICE_URI, UnmanagedDevice.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("UnmanagedDeviceClient : getByName : End");

        return unmanagedDevices;
    }

    /**
     * Adds a unmanaged device according to the provided {@link UnmanagedDevice} object.
     *
     * @param unmanagedDevice object containing the unmanaged device details.
     *
     * @return {@link UnmanagedDevice} containing the added unmanaged device.
     */
    public UnmanagedDevice add(UnmanagedDevice unmanagedDevice) {
        LOGGER.info("UnmanagedDeviceClient : add : Start");

        Request request = new Request(HttpMethod.POST, ResourceUris.UNMANAGED_DEVICE_URI, unmanagedDevice);
        UnmanagedDevice createdUnmanagedDevice = this.baseClient.executeRequest(request, UnmanagedDevice.class);

        LOGGER.info("UnmanagedDeviceClient : add : End");

        return createdUnmanagedDevice;
    }

    /**
     * Updates a {@link UnmanagedDevice} identified by the given resource identifier.
     *
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     * @param unmanagedDevice object containing the unmanaged device details.
     *
     * @return {@link UnmanagedDevice} containing the unmanaged device updated.
     */
    public UnmanagedDevice update(String resourceId, UnmanagedDevice unmanagedDevice) {
        LOGGER.info("UnmanagedDeviceClient : update : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(ResourceUris.UNMANAGED_DEVICE_URI, resourceId), unmanagedDevice);
        UnmanagedDevice updatedUnmanagedDevice = this.baseClient.executeRequest(request, UnmanagedDevice.class);

        LOGGER.info("UnmanagedDeviceClient : update : End");

        return updatedUnmanagedDevice;
    }

    /**
     * Removes the {@link UnmanagedDevice} identified by the given resource identifier.
     *
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return String value containing the result of the process.
     */
    public String remove(String resourceId) {
        LOGGER.info("UnmanagedDeviceClient : remove : Start");

        Request request = new Request(HttpMethod.DELETE,
                UrlUtils.createUrl(ResourceUris.UNMANAGED_DEVICE_URI, resourceId));
        String response = this.baseClient.executeRequest(request, String.class);

        LOGGER.info("UnmanagedDeviceClient : remove : End");

        return response;
    }

    /**
     * Removes the {@link UnmanagedDevice}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 removeByFilter(String filter, boolean aSync) {
        LOGGER.info("UnmanagedDeviceClient : removeByFilter : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(ResourceUris.UNMANAGED_DEVICE_URI, aSync,
                new UrlParameter("filter", filter));

        LOGGER.info("UnmanagedDeviceClient : removeByFilter : End");

        return taskResource;
    }

    /**
     * Returns a description of the environmental configuration (supported feature set,
     * calibrated minimum and maximum power, location and dimensions, ...) of the resource.
     *
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} containing the description of the
     * environmental configuration.
     */
    public EnvironmentalConfiguration getEnvironmentalConfiguration(String resourceId) {
        LOGGER.info("UnmanagedDeviceClient : getEnvironmentalConfiguration : Start");

        EnvironmentalConfiguration environmentalConfiguration= baseClient.getResource(UrlUtils.createUrl(
                ResourceUris.UNMANAGED_DEVICE_URI, resourceId, ResourceUris.ENVIRONMENT_CONFIGURATION_URI),
                EnvironmentalConfiguration.class);

        LOGGER.info("UnmanagedDeviceClient : getEnvironmentalConfiguration : End");

        return environmentalConfiguration;
    }
}
