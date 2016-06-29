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
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeName;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class InterconnectTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectTypeClient.class);

    private final BaseClient baseClient;

    public InterconnectTypeClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link InterconnectType} details for the specified interconnect type.
     *
     * @param resourceId interconnect type resource identifier as seen in HPE OneView.
     *
     * @return {@link InterconnectType} object containing the details.
     */
    public InterconnectType getById(String resourceId) {
        LOGGER.info("InterconnectTypeClient : getById : Start");

        InterconnectType interconnectType = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_TYPE_URI, resourceId), InterconnectType.class);

        LOGGER.info("InterconnectTypeClient : getById : End");

        return interconnectType;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing details
     * for all the available interconnect types found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing
     * the details for all found interconnect types.
     */
    public ResourceCollection<InterconnectType> getAll() {
        LOGGER.info("InterconnectTypeClient : getAll : Start");

        ResourceCollection<InterconnectType> interconnectTypes = baseClient.getResourceCollection(
                ResourceUris.INTERCONNECT_TYPE_URI, InterconnectType.class);

        LOGGER.info("InterconnectTypeClient : getAll : End");

        return interconnectTypes;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing details
     * for the available interconnect types found under the current HPE OneView that match the name.
     *
     * @param typeName interconnect type name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing
     * the details for the found interconnect types.
     */
    public ResourceCollection<InterconnectType> getByName(InterconnectTypeName typeName) {
        LOGGER.info("InterconnectTypeClient : getByName : Start");

        ResourceCollection<InterconnectType> interconnectTypes = baseClient.getResourceCollection(
                ResourceUris.INTERCONNECT_TYPE_URI, InterconnectType.class,
                UrlParameter.getFilterByNameParameter(typeName.getValue()));

        LOGGER.info("InterconnectTypeClient : getByName : End");

        return interconnectTypes;
    }

}
