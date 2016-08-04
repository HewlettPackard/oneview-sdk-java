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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicaldownlinks.LogicalDownlink;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalDownlinkClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalDownlinkClient.class);

    private final BaseClient baseClient;

    public LogicalDownlinkClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link LogicalDownlink} details for the specified logical downlink.
     *
     * @param resourceId logical downlink resource identifier as seen in HPE OneView.
     *
     * @return {@link LogicalDownlink} object containing the details.
     */
    public LogicalDownlink getById(String resourceId) {
        LOGGER.info("LogicalDownlinkClient : getById : Start");

        LogicalDownlink logicalDownlink = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_DOWNLINK_URI, resourceId), LogicalDownlink.class);

        LOGGER.info("LogicalDownlinkClient : getById : End");

        return logicalDownlink;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing details
     * for all the available logical downlink found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing
     * the details for all found logical downlink.
     */
    public ResourceCollection<LogicalDownlink> getAll() {
        LOGGER.info("LogicalDownlinkClient : getAll : Start");

        ResourceCollection<LogicalDownlink> logicalDownlink = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_DOWNLINK_URI, LogicalDownlink.class);

        LOGGER.info("LogicalDownlinkClient : getAll : End");

        return logicalDownlink;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing details
     * for the available logical downlink found under the current HPE OneView that match the name.
     *
     * @param name logical downlink name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing
     * the details for the found logical downlink.
     */
    public ResourceCollection<LogicalDownlink> getByName(String name) {
        LOGGER.info("LogicalDownlinkClient : getByName : Start");

        ResourceCollection<LogicalDownlink> logicalDownlink = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_DOWNLINK_URI, LogicalDownlink.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("LogicalDownlinkClient : getByName : End");

        return logicalDownlink;
    }

    /**
     * Retrieves the {@link LogicalDownlink} details for the specified logical downlink,
     * excluding any existing ethernet networks.
     *
     * @param resourceId logical downlink resource identifier as seen in HPE OneView.
     *
     * @return {@link LogicalDownlink} object containing the details.
     */
    public LogicalDownlink getByIdWithoutEthernet(String resourceId) {
        LOGGER.info("LogicalDownlinkClient : getByIdWithoutEthernet : Start");

        LogicalDownlink logicalDownlink = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_DOWNLINK_URI, resourceId, ResourceUris.WITHOUT_ETHERNET),
                LogicalDownlink.class);

        LOGGER.info("LogicalDownlinkClient : getByIdWithoutEthernet : End");

        return logicalDownlink;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing details
     * for all the available logical downlink found under the current HPE OneView, excluding any
     * existing ethernet networks.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing
     * the details for all found logical downlink.
     */
    public ResourceCollection<LogicalDownlink> getAllWithoutEthernet() {
        LOGGER.info("LogicalDownlinkClient : getAllWithoutEthernet : Start");

        ResourceCollection<LogicalDownlink> logicalDownlink = baseClient.getResourceCollection(
                UrlUtils.createUrl(ResourceUris.LOGICAL_DOWNLINK_URI, ResourceUris.WITHOUT_ETHERNET),
                LogicalDownlink.class);

        LOGGER.info("LogicalDownlinkClient : getAllWithoutEthernet : End");

        return logicalDownlink;
    }

}
