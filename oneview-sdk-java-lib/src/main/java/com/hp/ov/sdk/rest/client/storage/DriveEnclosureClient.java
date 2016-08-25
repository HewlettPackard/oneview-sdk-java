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

import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosure;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosurePortMap;
import com.hp.ov.sdk.dto.storage.driveenclosures.DriveEnclosureRefreshRequest;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class DriveEnclosureClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriveEnclosureClient.class);
    private static final int TIMEOUT = 1200000; // in milliseconds

    protected static final String DRIVE_ENCLOSURE_URI = "/rest/drive-enclosures";
    protected static final String PORT_MAP_URI = "port-map";
    protected static final String REFRESH_STATE_URI = "refreshState";

    private final BaseClient baseClient;

    public DriveEnclosureClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link DriveEnclosure} details for the specified Drive Enclosure.
     *
     * @param resourceId Drive Enclosure resource identifier as seen in HPE OneView.
     *
     * @return {@link DriveEnclosure} object containing the details.
     */
    public DriveEnclosure getById(String resourceId) {
        LOGGER.info("DriveEnclosureClient : getById : Start");

        DriveEnclosure driveEnclosure = baseClient.getResource(
                UrlUtils.createUrl(DRIVE_ENCLOSURE_URI, resourceId),
                DriveEnclosure.class);

        LOGGER.info("DriveEnclosureClient : getById : End");

        return driveEnclosure;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DriveEnclosure}&gt; containing details
     * for all the available Drive Enclosures found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link DriveEnclosure}&gt; containing
     * the details for all found Drive Enclosures.
     */
    public ResourceCollection<DriveEnclosure> getAll() {
        LOGGER.info("DriveEnclosureClient : getAll : Start");

        ResourceCollection<DriveEnclosure> driveEnclosures = baseClient.getResourceCollection(
                DRIVE_ENCLOSURE_URI, DriveEnclosure.class);

        LOGGER.info("DriveEnclosureClient : getAll : End");

        return driveEnclosures;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link DriveEnclosure}&gt; containing details
     * for the available Drive Enclosures found under the current HPE OneView that match the name.
     *
     * @param name Drive Enclosure name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link DriveEnclosure}&gt; containing
     * the details for the found Drive Enclosures.
     */
    public ResourceCollection<DriveEnclosure> getByName(String name) {
        LOGGER.info("DriveEnclosureClient : getByName : Start");

        ResourceCollection<DriveEnclosure> driveEnclosures = baseClient.getResourceCollection(
                DRIVE_ENCLOSURE_URI, DriveEnclosure.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("DriveEnclosureClient : getByName : End");

        return driveEnclosures;
    }

    /**
     * Retrieves the drive enclosure I/O adapter port to SAS interconnect port connectivity.
     *
     * @param resourceId Drive Enclosure resource identifier as seen in HPE OneView.
     *
     * @return the port map for the specified Drive Enclosure.
     */
    public DriveEnclosurePortMap getPortMap(String resourceId) {
        LOGGER.info("DriveEnclosureClient : getPortMap : Start");

        DriveEnclosurePortMap response = baseClient.getResource(
                UrlUtils.createUrl(DRIVE_ENCLOSURE_URI, resourceId, PORT_MAP_URI),
                DriveEnclosurePortMap.class);

        LOGGER.info("DriveEnclosureClient : getPortMap : End");

        return response;
    }

    /**
     * Refresh the Drive Enclosure to fix any configuration issue.
     *
     * @param resourceId Drive Enclosure resource identifier as seen in HPE OneView.
     * @param refreshStateConfig refresh state details to fix configuration issues.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateRefreshState(String resourceId, DriveEnclosureRefreshRequest refreshStateConfig, boolean aSync) {
        LOGGER.info("DriveEnclosureClient : updateRefreshState : Start");

        String updateUri = UrlUtils.createUrl(DRIVE_ENCLOSURE_URI,
                resourceId,
                REFRESH_STATE_URI);
        Request request = new Request(HttpMethodType.PUT, updateUri, refreshStateConfig);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("DriveEnclosureClient : updateRefreshState : End");

        return taskResource;
    }

    /**
     * Performs a PATCH request and updates the existing Drive Enclosure based
     * on the resource identifier and the content of the {@link Patch} object.
     *
     * @param resourceId Drive Enclosure resource identifier as seen in HPE OneView.
     * @param patch object containing the update to be made to existing Drive Enclosure.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 patch(String resourceId, Patch patch, boolean aSync) {
        LOGGER.info("DriveEnclosureClient : patch : Start");

        Request request = new Request(HttpMethodType.PATCH,
                UrlUtils.createUrl(DRIVE_ENCLOSURE_URI, resourceId), patch);

        request.setTimeout(TIMEOUT);
        request.setContentType(ContentType.APPLICATION_JSON_PATCH);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("DriveEnclosureClient : patch : End");

        return taskResource;
    }

}
