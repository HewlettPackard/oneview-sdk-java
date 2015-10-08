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
/**
 * A device manager is a hardware system that the SAN Resource Manager can communicate 
 * with in order to manage the zones on a SAN. For example, the SAN Resource Manager may 
 * communicate with one or more Brocade Network Advisor systems, identified by IP addresses or 
 * DNS names, in order to manage the zones on the SANs controlled by those BNA systems.
 */
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.DeviceManagerResponse;
import com.hp.ov.sdk.dto.DeviceManagerResponseCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FcSansDeviceManagerClient {
    /*
     * The module aids in creating a Device Manager under the specified
     * provider.
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @param providerUrl The provider url based on the switch provider obtained
     * from one view
     * 
     * @param deviceManagerResponseDto The deviceManagerResposne object
     * containing the connection details.
     * 
     * @param aSync Flag input to process request asynchronously or
     * synchronously.
     * 
     * @param useJsonRequest The JsonRequest body is part of AddEnclosure Object
     * which takes in a String containing the new Enclosure details, which is
     * converted to AddEnclosure Object using adaptor and processed.
     * 
     * @return sanProviderResponseCollectionDto, which is a object containing
     * the SanProviderResponse Collection details.
     */
    public DeviceManagerResponse createDeviceManager(final RestParams params, final String providerUrl,
            final DeviceManagerResponse deviceManagerResponseDto, final boolean aSync, final boolean useJsonRequest);

    /*
     * The module aids in fetching all the Device Managers registered under
     * current HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @return DeviceManagerResponseCollectionDto, which is a object containing
     * the DeviceManagerResponse Collection details.
     */
    public DeviceManagerResponseCollection getAllDeviceManager(final RestParams params);

    /*
     * The module aids in fetching the Device Managers registered under current
     * HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @param resourceId The resourceId for device manager as seen in HP
     * OneView.
     * 
     * @return DeviceManagerResponseDto, which is a object containing the
     * DeviceManagerResponse details.
     */
    public DeviceManagerResponse getDeviceManager(final RestParams params, final String resourceId);

    /*
     * The module aids in fetching the Device Managers registered under current
     * HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @param name The name of resource for device manager as seen in HP
     * OneView.
     * 
     * @return DeviceManagerResponseDto, which is a object containing the
     * DeviceManagerResponse details.
     */
    public DeviceManagerResponse getDeviceManagerByName(final RestParams params, final String name);

    /*
     * The module aids in fetching the Device Managers registered under current
     * HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @param resourceId The resourceId for device manager as seen in HP
     * OneView.
     * 
     * @return DeviceManagerResponseDto, which is a object containing the
     * DeviceManagerResponse details.
     */
    public String deleteDeviceManager(final RestParams params, final String resourceId);

    /*
     * The module aids in fetching the Device Managers registered under current
     * HP OneView
     * 
     * @param params The RestParams is a structure containing the connection
     * details.
     * 
     * @param resourceId The resourceId for device manager as seen in HP
     * OneView. *
     * 
     * @param aSync Flag input to process request asynchronously or
     * synchronously.
     * 
     * @return DeviceManagerResponseDto, which is a object containing the
     * DeviceManagerResponse details.
     */
    public DeviceManagerResponse updateDeviceManager(final RestParams params, final String resourceId,
            final DeviceManagerResponse updateDeviceManagerResponseDto, final boolean useJsonRequest);

    /**
     * The module aids in fetching the device manager details for the device
     * manager name as specified in HP OneView.
     * 
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the device manager name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the device manager name as
     *         seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
