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

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.interconnectlinktopologies.InterconnectLinkTopology;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class InterconnectLinkTopologyClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectLinkTopologyClient.class);

    protected static final String INTERCONNECT_LINK_TOPOLOGY_URI = "/rest/interconnect-link-topologies";

    private final BaseClient baseClient;

    public InterconnectLinkTopologyClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link InterconnectLinkTopology} details for the specified Interconnect Link Topology.
     *
     * @param resourceId Interconnect Link Topology resource identifier as seen in HPE OneView.
     *
     * @return {@link InterconnectLinkTopology} object containing the details.
     */
    public InterconnectLinkTopology getById(String resourceId) {
        LOGGER.info("InterconnectLinkTopologyClient : getById : Start");

        InterconnectLinkTopology fcNetwork = baseClient.getResource(
                UrlUtils.createUrl(INTERCONNECT_LINK_TOPOLOGY_URI, resourceId), InterconnectLinkTopology.class);

        LOGGER.info("InterconnectLinkTopologyClient : getById : End");

        return fcNetwork;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InterconnectLinkTopology}&gt; containing the details
     * for all the available Interconnect Link Topologies found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectLinkTopology}&gt; containing
     * the details for all found Interconnect Link Topologies.
     */
    public ResourceCollection<InterconnectLinkTopology> getAll() {
        LOGGER.info("InterconnectLinkTopologyClient : getAll : Start");

        ResourceCollection<InterconnectLinkTopology> interconnectLinkTopologies = baseClient.getResourceCollection(
                INTERCONNECT_LINK_TOPOLOGY_URI, InterconnectLinkTopology.class);

        LOGGER.info("InterconnectLinkTopologyClient : getAll : End");

        return interconnectLinkTopologies;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InterconnectLinkTopology}&gt; containing details
     * for the available Interconnect Link Topologies found under the current HPE OneView that match the name.
     *
     * @param name Interconnect Link Topology name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectLinkTopology}&gt; containing
     * the details for the found Interconnect Link Topologies.
     */
    public ResourceCollection<InterconnectLinkTopology> getByName(String name) {
        LOGGER.info("InterconnectLinkTopologyClient : getByName : Start");

        ResourceCollection<InterconnectLinkTopology> interconnectLinkTopologies = baseClient.getResourceCollection(
                INTERCONNECT_LINK_TOPOLOGY_URI, InterconnectLinkTopology.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("InterconnectLinkTopologyClient : getByName : End");

        return interconnectLinkTopologies;
    }

}
