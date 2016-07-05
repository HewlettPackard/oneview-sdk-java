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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface UnmanagedDeviceClient {

    /**
     * Retrieves the {@link UnmanagedDevice} details for the specified
     * unmanaged device.
     *
     * @param params structure containing the connection details.
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return {@link UnmanagedDevice} object containing the details.
     */
    UnmanagedDevice getUnmanagedDevice(RestParams params, String resourceId);

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link UnmanagedDevice}&gt; containing details
     * for all the available unmanaged devices found under the current HPE OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ResourceCollection}&lt;{@link UnmanagedDevice}&gt; containing
     * the details for all found unmanaged devices.
     */
    ResourceCollection<UnmanagedDevice> getAllUnmanagedDevices(RestParams params);

    /**
     * Retrieves the {@link UnmanagedDevice} details for the specified
     * unmanaged device identified by name.
     *
     * @param params structure containing the connection details.
     * @param name unmanaged device name as seen in HPE OneView.
     *
     * @return {@link UnmanagedDevice} object containing the details.
     */
    UnmanagedDevice getUnmanagedDeviceByName(RestParams params, String name);

    /**
     * Adds an unmanaged device according to the provided {@link UnmanagedDevice} object.
     *
     * @param params structure containing the connection details.
     * @param unmanagedDevice object containing the unmanaged device details.
     *
     * @return {@link UnmanagedDevice} containing the added unmanaged device.
     */
    UnmanagedDevice addUnmanagedDevice(RestParams params, UnmanagedDevice unmanagedDevice);

    /**
     * Updates an {@link UnmanagedDevice} identified by the given resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     * @param unmanagedDevice object containing the unmanaged device details.
     *
     * @return {@link UnmanagedDevice} containing the unmanaged device updated.
     */
    UnmanagedDevice updateUnmanagedDevice(RestParams params, String resourceId, UnmanagedDevice unmanagedDevice);

    /**
     * Removes the {@link UnmanagedDevice} identified by the given resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return String value containing the result of the process.
     */
    String removeUnmanagedDevice(RestParams params, String resourceId);

    /**
     * Removes the {@link UnmanagedDevice}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual removal will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param params structure containing the connection details.
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 removeUnmanagedDeviceByFilter(RestParams params, String filter, boolean aSync);

    /**
     * Returns a description of the environmental configuration (supported feature set,
     * calibrated minimum and maximum power, location and dimensions, ...) of the resource.
     *
     * @param params structure containing the connection details.
     * @param resourceId unmanaged device resource identifier as seen in HPE OneView.
     *
     * @return {@link EnvironmentalConfiguration} containing the description of the
     * environmental configuration.
     */
    EnvironmentalConfiguration getEnvironmentalConfiguration(RestParams params, String resourceId);

    /**
     * Retrieves the unmanaged device resource identifier for the specified
     * unmanaged device identified by name.
     *
     * @param params structure containing the connection details.
     * @param name unmanaged device name as seen in HPE OneView.
     *
     * @return resource identifier for the unmanaged device name as seen in HPE OneView.
     */
    String getId(RestParams params, final String name);
}
