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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.ReplaceDriveEnclosure;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLiFirmware;
import com.hp.ov.sdk.dto.networking.saslogicalinterconnect.SasLogicalInterconnect;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class SasLogicalInterconnectClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SasLogicalInterconnectClient.class);
    private static final int TIMEOUT = 720000; // in milliseconds

    protected static final String SAS_LOGICAL_INTERCONNECT_URI = "/rest/sas-logical-interconnects";
    protected static final String FIRMWARE_URI = "firmware";
    protected static final String REPLACE_DRIVE_ENCLOSURE_URI = "replaceDriveEnclosure";
    protected static final String CONFIGURATION_URI = "configuration";
    protected static final String COMPLIANCE_URI = "compliance";

    private final BaseClient baseClient;

    public SasLogicalInterconnectClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * The module aids in fetching the SAS logical interconnect details for the specified
     * SAS logical interconnect resource identifier.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     *
     * @return {@link SasLogicalInterconnect} containing the SAS logical interconnect details.
     */
    public SasLogicalInterconnect getById(String resourceId) {
        LOGGER.info("SasLogicalInterconnectClient : getById : Start");

        SasLogicalInterconnect interconnect = baseClient.getResource(
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, resourceId),
                SasLogicalInterconnect.class);

        LOGGER.info("SasLogicalInterconnectClient : getById : End");

        return interconnect;
    }

    /**
     * The module aids in fetching the SAS logical interconnect details for all the
     * SAS logical interconnects found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link SasLogicalInterconnect}&gt; containing
     * the details for all found SAS logical interconnects.
     */
    public ResourceCollection<SasLogicalInterconnect> getAll() {
        LOGGER.info("SasLogicalInterconnectClient : getAll : Start");

        ResourceCollection<SasLogicalInterconnect> interconnects = baseClient.getResourceCollection(
                SAS_LOGICAL_INTERCONNECT_URI,
                SasLogicalInterconnect.class);

        LOGGER.info("SasLogicalInterconnectClient : getAll : End");

        return interconnects;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link SasLogicalInterconnect}&gt; containing details
     * for the available SAS logical interconnects found under the current HPE OneView that match the name.
     *
     * @param name SAS logical interconnect name as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link SasLogicalInterconnect}&gt; containing
     * the details for all found SAS logical interconnects.
     */
    public ResourceCollection<SasLogicalInterconnect> getByName(final String name) {
        LOGGER.info("SasLogicalInterconnectClient : getByName : Start");

        ResourceCollection<SasLogicalInterconnect> interconnects = baseClient.getResourceCollection(
                SAS_LOGICAL_INTERCONNECT_URI,
                SasLogicalInterconnect.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("SasLogicalInterconnectClient : getByName : End");

        return interconnects;
    }

    /**
     * The module aids in fetching the SAS logical interconnect firmware for the specified
     * SAS logical interconnect identified by the resource identifier.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     *
     * @return {@link SasLiFirmware} containing the SAS logical interconnect LI firmware details.
     */
    public SasLiFirmware getFirmware(String resourceId) {
        LOGGER.info("SasLogicalInterconnectClient : getFirmware : Start");

        SasLiFirmware firmware = baseClient.getResource(
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, resourceId, FIRMWARE_URI),
                SasLiFirmware.class);

        LOGGER.info("SasLogicalInterconnectClient : getFirmware : End");

        return firmware;
    }

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
     * @param aSync flag to indicate whether the request should be processed synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateFirmware(String resourceId, SasLiFirmware sasLiFirmware, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectClient : updateFirmware : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, resourceId, FIRMWARE_URI),
                sasLiFirmware);

        request.setTimeout(TIMEOUT);
        request.setForceTaskReturn(true);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SasLogicalInterconnectClient : updateFirmware : End");

        return task;
    }

    /**
     * Initiates the replacement operation that enables the new drive enclosure to take over
     * as a replacement for the prior drive enclosure when a drive enclosure has been physically
     * replaced. The request requires specification of both the serial numbers of the original
     * drive enclosure and its replacement to be provided. Track the completion of the
     * replacement process by the task returned in the location header.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     * @param replace specification of both the serial numbers of the original drive enclosure and its replacement.
     * @param aSync flag to indicate whether the request should be processed synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 replaceDriveEnclosure(String resourceId, ReplaceDriveEnclosure replace, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectClient : replaceDriveEnclosure : Start");

        Request request = new Request(HttpMethod.POST,
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, resourceId, REPLACE_DRIVE_ENCLOSURE_URI), replace);

        request.setTimeout(TIMEOUT);
        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SasLogicalInterconnectClient : replaceDriveEnclosure : End");

        return taskResource;
    }

    /**
     * Asynchronously applies or re-applies the SAS logical interconnect configuration to all
     * managed SAS interconnects of a SAS logical interconnect.
     *
     * @param resourceId resource identifier for SAS logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 applyConfiguration(String resourceId, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectClient : applyConfiguration : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, resourceId, CONFIGURATION_URI));

        request.setTimeout(TIMEOUT);
        request.setForceTaskReturn(true);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SasLogicalInterconnectClient : applyConfiguration : End");

        return taskResource;
    }

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
     * @param interconnect {@link SasLogicalInterconnect} SAS logical interconnect.
     * @param aSync flag to indicate whether the request should be processed synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateCompliance(SasLogicalInterconnect interconnect, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectClient : updateCompliance : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, interconnect.getResourceId(), COMPLIANCE_URI),
                interconnect);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SasLogicalInterconnectClient : updateCompliance : End");

        return task;
    }

    /**
     * Returns all the SAS logical interconnects to a consistent state. The SAS logical interconnects
     * state is compared to the associated SAS logical interconnect group. Any differences identified
     * are corrected, bringing the SAS logical interconnect back to a consistent state.
     * Changes are asynchronously applied to all managed SAS interconnects. Note that if the changes
     * detected involve differences in the interconnect map between the SAS logical interconnect group
     * and the SAS logical interconnect, the process of bringing the SAS logical interconnect back to a
     * consistent state may involve automatically removing existing interconnects from management
     * and/or adding new interconnects for management.
     *
     * @param sasLogicalInterconnectUris list containing the URIs of SAS logical interconnects as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateCompliance(List<String> sasLogicalInterconnectUris, boolean aSync) {
        LOGGER.info("SasLogicalInterconnectClient : updateCompliance : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(SAS_LOGICAL_INTERCONNECT_URI, COMPLIANCE_URI),
                sasLogicalInterconnectUris);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("SasLogicalInterconnectClient : updateCompliance : End");

        return task;
    }

}
