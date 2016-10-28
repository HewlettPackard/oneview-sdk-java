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

package com.hp.ov.sdk.rest.client.networking;

import java.util.List;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.ReplaceDriveEnclosure;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLiFirmware;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLogicalInterconnect;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(SasLogicalInterconnectClient.SAS_LOGICAL_INTERCONNECT_URI)
public interface SasLogicalInterconnectClient extends SearchableResource<SasLogicalInterconnect> {

    String SAS_LOGICAL_INTERCONNECT_URI = "/rest/sas-logical-interconnects";
    String FIRMWARE_URI = "/firmware";
    String REPLACE_DRIVE_ENCLOSURE_URI = "/replaceDriveEnclosure";
    String CONFIGURATION_URI = "/configuration";
    String COMPLIANCE_URI = "/compliance";

    /**
     * The module aids in fetching the SAS logical interconnect firmware for the specified
     * SAS logical interconnect identified by the resource identifier.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     *
     * @return {@link SasLiFirmware} containing the SAS logical interconnect LI firmware details.
     */
    @Endpoint(uri = "/{resourceId}" + FIRMWARE_URI)
    SasLiFirmware getFirmware(@PathParam("resourceId") String resourceId);

    /**
     * The method installs firmware to the member interconnects of a SAS logical interconnect.
     * The three operations that
     * are supported for the firmware update are:<br>
     * <ul>
     *     <li>{@link com.hp.ov.sdk.dto.networking.logicalinterconnects.Command#Stage}<br>
     *         Stage firmware to the interconnects that are members of the SAS logical interconnect.</li>
     *     <li>{@link com.hp.ov.sdk.dto.networking.logicalinterconnects.Command#Activate}<br>
     *         Activates the last staged firmware on the interconnect.</li>
     *     <li>{@link com.hp.ov.sdk.dto.networking.logicalinterconnects.Command#Update}<br>
     *         Stages and activates the provided firmware baseline on the interconnect.</li>
     * </ul>
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     * @param sasLiFirmware object containing the update to be made to the SAS logical interconnect.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + FIRMWARE_URI, method = HttpMethod.PUT)
    TaskResource updateFirmware(@PathParam("resourceId") String resourceId,
            @BodyParam SasLiFirmware sasLiFirmware, RequestOption ... options);

    /**
     * Initiates the replacement operation that enables the new drive enclosure replace the
     * prior drive enclosure when a drive enclosure has been physically replaced. The request
     * requires specification of both the serial numbers of the original drive enclosure and
     * its replacement to be provided. Track the completion of the replacement process by the
     * task returned in the location header.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     * @param replace specification of both the serial numbers of the original drive enclosure
     *        and its replacement.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + REPLACE_DRIVE_ENCLOSURE_URI, method = HttpMethod.POST)
    TaskResource replaceDriveEnclosure(@PathParam("resourceId") String resourceId,
            @BodyParam ReplaceDriveEnclosure replace, RequestOption ... options);

    /**
     * Asynchronously applies or re-applies the SAS logical interconnect configuration to all
     * managed SAS interconnects of a SAS logical interconnect.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + CONFIGURATION_URI, method = HttpMethod.PUT)
    TaskResource applyConfiguration(@PathParam("resourceId") String resourceId, RequestOption ... options);

    /**
     * Returns a SAS logical interconnect to a consistent state. The current SAS logical interconnect
     * state is compared to the associated SAS logical interconnect group. Any differences identified
     * are corrected, bringing the SAS logical interconnect back to a consistent state.
     * Changes are asynchronously applied to all managed SAS interconnects. Note that if the changes
     * detected involve differences in the interconnect map between the SAS logical interconnect group
     * and the SAS logical interconnect, the process of bringing the SAS logical interconnect back to a
     * consistent state may involve automatically removing existing interconnects from management
     * and/or adding new interconnects for management.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     * @param interconnect {@link SasLogicalInterconnect} SAS logical interconnect.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + COMPLIANCE_URI, method = HttpMethod.PUT)
    TaskResource updateCompliance(@PathParam("resourceId") String resourceId,
            @BodyParam SasLogicalInterconnect interconnect, RequestOption ... options);

    /**
     * Returns all SAS logical interconnects to a consistent state. The SAS logical interconnects
     * state is compared to the associated SAS logical interconnect group. Any differences identified
     * are corrected, bringing the SAS logical interconnect back to a consistent state.
     * Changes are asynchronously applied to all managed SAS interconnects. Note that if the changes
     * detected involve differences in the interconnect map between the SAS logical interconnect group
     * and the SAS logical interconnect, the process of bringing the SAS logical interconnect back to a
     * consistent state may involve automatically removing existing interconnects from management
     * and/or adding new interconnects for management.
     *
     * @param sasLogicalInterconnectUris list containing the URIs of SAS logical interconnects as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = COMPLIANCE_URI, method = HttpMethod.PUT)
    TaskResource updateCompliance(@BodyParam List<String> sasLogicalInterconnectUris, RequestOption ... options);

}
