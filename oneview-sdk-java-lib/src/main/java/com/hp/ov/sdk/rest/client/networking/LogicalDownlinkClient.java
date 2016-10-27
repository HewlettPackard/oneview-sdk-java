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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.logicaldownlinks.LogicalDownlink;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(LogicalDownlinkClient.LOGICAL_DOWNLINK_URI)
public interface LogicalDownlinkClient extends SearchableResource<LogicalDownlink> {

    String LOGICAL_DOWNLINK_URI = "/rest/logical-downlinks";
    String WITHOUT_ETHERNET_URI = "/withoutEthernet";

    /**
     * Retrieves the {@link LogicalDownlink} details for the specified logical downlink,
     * excluding any existing ethernet networks.
     *
     * @param resourceId logical downlink resource identifier as seen in HPE OneView.
     *
     * @return {@link LogicalDownlink} object containing the details.
     */
    @Endpoint(uri = "/{resourceId}" + WITHOUT_ETHERNET_URI)
    LogicalDownlink getByIdWithoutEthernet(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing details
     * for all available logical downlinks found under the current HPE OneView, excluding any
     * existing ethernet networks.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalDownlink}&gt; containing
     * the details for all logical downlinks found.
     */
    @Endpoint(uri = WITHOUT_ETHERNET_URI)
    ResourceCollection<LogicalDownlink> getAllWithoutEthernet();

}
