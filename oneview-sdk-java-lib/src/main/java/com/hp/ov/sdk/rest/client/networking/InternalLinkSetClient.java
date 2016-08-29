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
import com.hp.ov.sdk.dto.networking.internallinksets.InternalLinkSet;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class InternalLinkSetClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InternalLinkSetClient.class);

    protected static final String INTERNAL_LINK_SET_URI = "/rest/internal-link-sets";

    private final BaseClient baseClient;

    public InternalLinkSetClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link InternalLinkSetClient} details for the specified Internal link set.
     *
     * @param resourceId Internal link set resource identifier as seen in HPE OneView.
     *
     * @return {@link InternalLinkSetClient} object containing the details.
     */
    public InternalLinkSet getById(String resourceId) {
        LOGGER.info("InternalLinkSetClient : getById : Start");

        InternalLinkSet internalLinkSet = baseClient.getResource(
                UrlUtils.createUrl(INTERNAL_LINK_SET_URI, resourceId), InternalLinkSet.class);

        LOGGER.info("InternalLinkSetClient : getById : End");

        return internalLinkSet;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InternalLinkSet}&gt; containing the details
     * for all the available Internal link sets found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InternalLinkSet}&gt; containing
     * the details for all found Internal link sets.
     */
    public ResourceCollection<InternalLinkSet> getAll() {
        LOGGER.info("InternalLinkSetClient : getAll : Start");

        ResourceCollection<InternalLinkSet> internalLinkSets = baseClient.getResourceCollection(
                INTERNAL_LINK_SET_URI, InternalLinkSet.class);

        LOGGER.info("InternalLinkSetClient : getAll : End");

        return internalLinkSets;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InternalLinkSet}&gt; containing details
     * for the available Internal link sets found under the current HPE OneView that match the name.
     *
     * @param name Internal link set name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InternalLinkSet}&gt; containing
     * the details for the found Internal link sets.
     */
    public ResourceCollection<InternalLinkSet> getByName(String name) {
        LOGGER.info("InternalLinkSetClient : getByName : Start");

        ResourceCollection<InternalLinkSet> internalLinkSets = baseClient.getResourceCollection(
                INTERNAL_LINK_SET_URI, InternalLinkSet.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("InternalLinkSetClient : getByName : End");

        return internalLinkSets;
    }

}
