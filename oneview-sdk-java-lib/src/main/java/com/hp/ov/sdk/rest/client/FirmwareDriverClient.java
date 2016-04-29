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

import com.hp.ov.sdk.dto.FwBaselineCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FirmwareDriverClient {

    /**
     * The module aids in fetching the firmware driver details for the specified
     * firmware driver resource identifier.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for firmware driver as seen in HPE OneView.
     * @return {@link FwBaseline} containing the firmware driver details.
     */
    FwBaseline getFirmwareDriver(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the firmware driver details for all the
     * firmware driver found under the current HPE OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @return {@link FwBaselineCollection} containing a collection of firmware driver details.
     */
    FwBaselineCollection getAllFirmwareDrivers(final RestParams params);

    /**
     * The module aids in fetching the firmware driver details for the
     * firmware driver name as specified in HPE OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param firmwareName
     *            The firmwareName is the firmware driver name as seen in HPE OneView.
     * @return {@link FwBaseline} containing the firmware driver details.
     */
    FwBaseline getFirmwareDriverByName(final RestParams params, final String firmwareName);

    /**
     * This module aids in deletion of the firmware baseline resource with the
     * specified resource identifier. If force is set to true, the firmware baseline resource
     * will be deleted even if it is assigned to devices.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for firmware driver as seen in HPE OneView.
     * @param isForce
     *            Indicates whether the delete should run with forced mode or not.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteFirmwareDriver(final RestParams params, final String resourceId, final Boolean isForce,
            final boolean aSync);

    /**
     * The module aids in fetching the firmware driver resource identifier for the firmware
     * driver name as specified in HPE OneView.
     * 
     * @param params
     *            The RestParams is a structure containing the connection details.
     * @param name
     *            The resourceName is the firmware driver name as seen in HPE OneView.
     * @return String which is the resource identifier for the firmware driver name as
     *         seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
