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
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.util.UrlUtils;

public class SwitchTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwitchTypeClient.class);

    private final BaseClient baseClient;

    public SwitchTypeClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link InterconnectType} details for the specified switch type.
     *
     * @param resourceId switch type resource identifier as seen in HPE OneView.
     *
     * @return {@link InterconnectType} object containing the details.
     */
    public InterconnectType getById(String resourceId) {
        LOGGER.info("SwitchTypeClient : getById : Start");

        InterconnectType switchType = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SWITCH_TYPE_URI, resourceId), InterconnectType.class);

        LOGGER.info("SwitchTypeClient : getById : End");

        return switchType;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing the details
     * for all the available switch types found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing
     * the details for all found switch types.
     */
    public ResourceCollection<InterconnectType> getAll() {
        LOGGER.info("SwitchTypeClient : getAll : Start");

        ResourceCollection<InterconnectType> switchTypes = baseClient.getResourceCollection(
                ResourceUris.SWITCH_TYPE_URI, InterconnectType.class);

        LOGGER.info("SwitchTypeClient : getAll : End");

        return switchTypes;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing details
     * for the available switch types found under the current HPE OneView that match the name.
     *
     * @param name switch type name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectType}&gt; containing
     * the details for the found switch types.
     */
    public ResourceCollection<InterconnectType> getByName(String name) {
        LOGGER.info("SwitchTypeClient : getByName : Start");

        ResourceCollection<InterconnectType> switchTypes = baseClient.getResourceCollection(
                ResourceUris.SWITCH_TYPE_URI, InterconnectType.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("SwitchTypeClient : getByName : End");

        return switchTypes;
    }


}
