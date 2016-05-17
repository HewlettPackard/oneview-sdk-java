/*
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
 */

package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

/**
 * A device manager is a hardware system that the SAN Resource Manager can communicate
 * with in order to manage the zones on a SAN. For example, the SAN Resource Manager may
 * communicate with one or more Brocade Network Advisor systems, identified by IP addresses or
 * DNS names, in order to manage the zones on the SANs controlled by those BNA systems.
 */
public interface FcSansDeviceManagerClient {

    /**
     * The module aids in creating a device manager under the specified provider.
     *
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @param providerUrl The provider URL based on the switch provider obtained from HPE OneView.
     * @param deviceManagerResponseDto The {@link DeviceManagerResponse} object
     * containing the device manager details.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createDeviceManager(final RestParams params, final String providerUrl,
            final DeviceManagerResponse deviceManagerResponseDto, final boolean aSync);

    /**
     * The module aids in fetching all the device managers registered under the
     * current HPE OneView.
     *
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link DeviceManagerResponse}&gt; containing
     * the details for all found device managers.
     */
    ResourceCollection<DeviceManagerResponse> getAllDeviceManager(final RestParams params);

    /**
     * The module aids in fetching the device manager registered under current HPE OneView.
     *
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @param resourceId The resource identifier for device manager as seen in HPE OneView.
     * @return {@link DeviceManagerResponse} containing the device manager details.
     */
    DeviceManagerResponse getDeviceManager(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the device manager registered under the current HPE OneView.
     *
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @param name The name is the device manager name as seen in HPE
     * @return {@link DeviceManagerResponse} containing the device manager details.
     */
    DeviceManagerResponse getDeviceManagerByName(final RestParams params, final String name);

    /**
     * The module aids in deleting a device manager for the specified device manager
     * resource identifier. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @param resourceId The resource identifier for device manager as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteDeviceManager(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module takes in an DeviceManagerResponse object or JsonRequest and updates the
     * existing device manager based on the resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params The {@link RestParams} is a structure containing the connection details.
     * @param resourceId The resource identifier for device manager as seen in HPE OneView.
     * @param updateDeviceManagerResponseDto object containing the device manager details.
     * @param useJsonRequest
     *            The JsonRequest body is part of DeviceManagerResponse object which
     *            takes in a String containing the device manager details, which
     *            is converted to DeviceManagerResponse object using adaptor and
     *            processed.
     * @param aSync Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateDeviceManager(final RestParams params, final String resourceId,
            final DeviceManagerResponse updateDeviceManagerResponseDto, final boolean useJsonRequest, final boolean aSync);

    /**
     * The module aids in fetching the device manager resource identifier for the device
     * manager name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The resourceName is the device manager name as seen in HPE OneView.
     * @return String which is the resource identifier for the device manager name as
     *         seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
