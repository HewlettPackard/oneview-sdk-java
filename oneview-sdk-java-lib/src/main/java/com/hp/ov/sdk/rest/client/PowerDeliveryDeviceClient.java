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

import com.hp.ov.sdk.dto.ImportPdd;
import com.hp.ov.sdk.dto.Light;
import com.hp.ov.sdk.dto.OutletState;
import com.hp.ov.sdk.dto.Power;
import com.hp.ov.sdk.dto.PowerDeliveryDevice;
import com.hp.ov.sdk.dto.PowerDeliveryDeviceRefreshRequest;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface PowerDeliveryDeviceClient {

    /**
     * The module aids in fetching the power delivery device details for the
     * specified power delivery device resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @return {@link PowerDeliveryDevice} containing the power delivery device
     *         details.
     */
    PowerDeliveryDevice getPowerDeliveryDeviceById(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the power delivery device details for all the
     * power delivery devices found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @return {@link ResourceCollection}&lt;{@link PowerDeliveryDevice}&gt;
     *         containing the details for all found power delivery devices.
     */
    ResourceCollection<PowerDeliveryDevice> getAllPowerDeliveryDevices(final RestParams params);

    /**
     * The module aids in fetching the power delivery device details for the
     * power delivery device name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param name
     *            The resourceName is the power delivery device name as seen in
     *            HPE OneView.
     * @return {@link PowerDeliveryDevice} containing the power delivery device details.
     */
    PowerDeliveryDevice getPowerDeliveryDeviceByName(RestParams params, String name);

    /**
     * The module aids in the inclusion of a power delivery device when provided
     * with the power delivery device details as a PowerDeliveryDevice object.
     * Use this method to create a representation of power delivery devices that
     * provide power to other resources but cannot otherwise be discovered by
     * the management appliance.It can process the request asynchronously or
     * synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param powerDeliveryDeviceDto
     *            Object containing the power delivery device details, used to
     *            create a power delivery device.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link PowerDeliveryDevice} the power delivery device resource as
     *         added to the appliance with default and assigned properties
     *         expanded.
     */
    PowerDeliveryDevice addPowerDeliveryDevice(final RestParams params,
            final PowerDeliveryDevice powerDeliveryDeviceDto, final boolean aSync);

    /**
     * The module aids in the inclusion of an HPE iPDU and bring all components
     * under management by discovery of its management module. Bring the
     * management module under exclusive management by the appliance, configure
     * any management or data collection settings, and create a private set of
     * administrative credentials to enable ongoing communication and management
     * of the iPDU. It can process the request asynchronously or synchronously,
     * based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param importPddDto
     *            Object containing the power delivery device connection
     *            details.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    TaskResourceV2 addPowerDeliveryDeviceByDiscover(final RestParams params, final ImportPdd importPddDto, final boolean aSync);

    /**
     * The module takes in a PowerDeliveryDevice object and updates the existing
     * power delivery device based on the resource identifier. It can process
     * the request asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @param powerDeliveryDeviceDto
     *            Object containing the power delivery device details, used to
     *            update a power delivery device.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link PowerDeliveryDevice} containing the updated power delivery
     *         device information.
     */
    PowerDeliveryDevice updatePowerDeliveryDevice(final RestParams params, final String resourceId,
            final PowerDeliveryDevice powerDeliveryDeviceDto, final boolean aSync);

    /**
     * The module aids in deleting a power delivery device for the specified
     * power delivery device resource identifier. It can process the request
     * asynchronously or synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    TaskResourceV2 removePowerDeliveryDevice(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * This module aids in deleting all power delivery device objects from the
     * appliance that match the provided filter.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param filter
     *            A general filter/query string that narrows the list of resources.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    TaskResourceV2 removePowerDeliveryDeviceByFilter(final RestParams params, final String filter, final boolean aSync);

    /**
     * The module aids in deleting a power delivery device for the specified
     * power delivery device resource identifier synchronously.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    String removePowerDeliveryDeviceSynchronously(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the power state for the specified power
     * delivery device resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @return {@link Power} representing the power state of the power delivery
     *         device.
     */
    Power getPowerDeliveryDevicePowerState(final RestParams params, final String resourceId);

    /**
     * The module takes in a OutletState object and updates the power state of
     * the power delivery device based on the resource identifier. It can
     * process the request asynchronously or synchronously, based on the flag
     * input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @param outletStateDto
     *            Object containing the power state of the power delivery
     *            device.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    TaskResourceV2 updatePowerDeliveryDevicePowerState(final RestParams params, final String resourceId,
            final OutletState outletStateDto, final boolean aSync);

    /**
     * The module takes in a PowerDeliveryDeviceRefreshRequest object and
     * updates the refresh state of the power delivery device based on the
     * resource identifier. It can process the request asynchronously or
     * synchronously, based on the flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @param powerDeliveryDeviceRefreshRequestDto
     *            Object containing the refresh state of the power delivery
     *            device.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    TaskResourceV2 updatePowerDeliveryDeviceRefreshState(final RestParams params, final String resourceId,
            final PowerDeliveryDeviceRefreshRequest powerDeliveryDeviceRefreshRequestDto, final boolean aSync);

    /**
     * The module aids in fetching the unit identification (UID) state (on, off,
     * unknown) of the specified power outlet or extension bar resource.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @return {@link Light} representing the UID state of the power delivery
     *         device.
     */
    Light getPowerDeliveryDeviceUidState(final RestParams params, final String resourceId);

    /**
     * The module takes in a OutletState object and updates the unit
     * identification (UID) light state of the specified power delivery device.
     * It can process the request asynchronously or synchronously, based on the
     * flag input.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @param outletStateDto
     *            Object containing the identification (UID) light state of the
     *            power delivery device.
     * @return {@link TaskResourceV2} containing the task status for the
     *         process.
     */
    TaskResourceV2 updatePowerDeliveryDeviceUidState(final RestParams params, final String resourceId,
            final OutletState outletStateDto, final boolean aSync);

    /**
     * The module aids in fetching the power delivery device utilization details
     * for the specified power delivery device resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the
     *            connection details.
     * @param resourceId
     *            The resource identifier for power delivery device as seen in
     *            HPE OneView.
     * @param outletStateDto
     *            Object containing the identification (UID) light state of the
     *            power delivery device.
     * @return {@link UtilizationData} containing the utilization information.
     */
    UtilizationData getPowerDeliveryDeviceUtilization(final RestParams params, final String resourceId);

}
