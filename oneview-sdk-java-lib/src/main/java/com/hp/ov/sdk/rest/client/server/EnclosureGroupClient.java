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
package com.hp.ov.sdk.rest.client.server;

import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.ContentType;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(EnclosureGroupClient.ENCLOSURE_GROUP_URI)
public interface EnclosureGroupClient extends
        SearchableResource<EnclosureGroup> {

    String ENCLOSURE_GROUP_URI = "/rest/enclosure-groups";
    String ENCLOSURE_GROUP_SCRIPT_URI = "/script";

    /**
     * Creates an enclosure group according to the provided {@link EnclosureGroup} object.
     *
     * @param enclosureGroup object containing the enclosure group details.
     *
     * @return {@link EnclosureGroup} containing the created enclosure group.
     */
    @Endpoint(method = HttpMethod.POST)
    EnclosureGroup create(@BodyParam EnclosureGroup enclosureGroup);

    /**
     * Updates an {@link EnclosureGroup} identified by the given resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     * @param enclosureGroup object containing the enclosure group details.
     *
     * @return {@link EnclosureGroup} containing the updated enclosure group.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    EnclosureGroup update(@PathParam("resourceId") String resourceId, @BodyParam EnclosureGroup enclosureGroup);

    /**
     * Deletes the {@link EnclosureGroup} identified by the given resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the response.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String delete(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves the configuration script for the specified enclosure group resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     *
     * @return the configuration script for the specified enclosure group.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_GROUP_SCRIPT_URI)
    String getConfigurationScript(@PathParam("resourceId") String resourceId);

    /**
     * Updates the configuration script for the specified enclosure group resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     * @param scriptData script data to be updated for enclosure group.
     *
     * @return the configuration script for the specified enclosure group.
     */
    @Endpoint(uri = "/{resourceId}" + ENCLOSURE_GROUP_SCRIPT_URI, method = HttpMethod.PUT)
    String updateConfigurationScript(@PathParam("resourceId") String resourceId,
            @BodyParam(type = ContentType.TEXT_PLAIN) String scriptData);

}
