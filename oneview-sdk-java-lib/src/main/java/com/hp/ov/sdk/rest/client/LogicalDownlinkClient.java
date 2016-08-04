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
import com.hp.ov.sdk.dto.networking.logicaldownlinks.LogicalDownlink;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalDownlinkClient {

    /**
     * The module aids in fetching the logical downlink when provided with the
     * LogicalDownlink resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param id
     *            The resource identifier for the logical downlink as seen in HPE OneView.
     * @return {@link LogicalDownlink} containing the logical downlink details.
     */
    LogicalDownlink getLogicalDownlinkById(final RestParams params, final String id);

    /**
     * The module aids in fetching the logical downlink when provided with
     * the LogicalDownlink resource identifier, excluding any existing Ethernet networks.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param id
     *            The resource identifier for the logical downlink as seen in HPE OneView.
     * @return {@link LogicalDownlink} containing the logical downlink details.
     */
    LogicalDownlink getLogicalDownlinkByIdWithoutEthernet(final RestParams params, final String id);

    /**
     * The module aids in fetching the logical downlink when provided with
     * the logical downlink name.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceName
     *            The resourceName is the logical downlink name as seen in HPE OneView.
     * @return {@link LogicalDownlink} containing the logical downlink details.
     */
    LogicalDownlink getLogicalDownlinkByName(final RestParams params, final String resourceName);

    /**
     * The module aids in fetching the logical downlink details for all
     * networks found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing
     * the details for all found logical downlinks.
     */
    ResourceCollection<LogicalDownlink> getAllLogicalDownlinks(final RestParams params);

    /**
     * The module aids in fetching the logical downlink details for all networks found
     * under the current HPE OneView, excluding any existing Ethernet networks.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing
     * the details for all found logical downlinks.
     */
    ResourceCollection<LogicalDownlink> getAllLogicalDownlinksWithoutEthernet(final RestParams params);


}
