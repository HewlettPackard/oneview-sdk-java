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

package com.hp.ov.sdk.rest.client.settings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.FwBaseline;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class FirmwareDriverClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirmwareDriverClient.class);

    private final BaseClient baseClient;

    public FirmwareDriverClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link FwBaseline} details for the specified firmware driver.
     *
     * @param resourceId firmware driver resource identifier as seen in HPE OneView.
     *
     * @return {@link FwBaseline} object containing the details.
     */
    public FwBaseline getById(String resourceId) {
        LOGGER.info("FirmwareDriverClient : getById : Start");

        FwBaseline firmwareDriver = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.FIRMWARE_DRIVER_URI, resourceId), FwBaseline.class);

        LOGGER.info("FirmwareDriverClient : getById : End");

        return firmwareDriver;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link FwBaseline}&gt; containing the details
     * for all the available firmware drivers found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link FwBaseline}&gt; containing
     * the details for all found firmware drivers.
     */
    public ResourceCollection<FwBaseline> getAll() {
        LOGGER.info("FirmwareDriverClient : getAll : Start");

        ResourceCollection<FwBaseline> firmwareDrivers = baseClient.getResourceCollection(
                ResourceUris.FIRMWARE_DRIVER_URI, FwBaseline.class);

        LOGGER.info("FirmwareDriverClient : getAll : End");

        return firmwareDrivers;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link FwBaseline}&gt; containing details
     * for the available firmware drivers found under the current HPE OneView that match the name.
     *
     * @param name firmware driver name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link FwBaseline}&gt; containing
     * the details for the found firmware drivers.
     */
    public ResourceCollection<FwBaseline> getByName(String name) {
        LOGGER.info("FirmwareDriverClient : getByName : Start");

        ResourceCollection<FwBaseline> firmwareDrivers = baseClient.getResourceCollection(
                ResourceUris.FIRMWARE_DRIVER_URI, FwBaseline.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("FirmwareDriverClient : getByName : End");

        return firmwareDrivers;
    }

    /**
     * Deletes the {@link FwBaseline} identified by the given resource identifier.
     *
     * @param resourceId firmware driver resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("FirmwareDriverClient : delete : Start");

        TaskResourceV2 taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.FIRMWARE_DRIVER_URI, resourceId), aSync);

        LOGGER.info("FirmwareDriverClient : delete : End");

        return taskResource;
    }

}
