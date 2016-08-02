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

import org.apache.http.entity.ContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class EnclosureGroupClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EnclosureGroupClient.class);

    private final BaseClient baseClient;

    public EnclosureGroupClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link EnclosureGroup} details for the specified enclosure group.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     *
     * @return {@link EnclosureGroup} object containing the details.
     */
    public EnclosureGroup getById(String resourceId) {
        LOGGER.info("EnclosureGroupClient : getById : Start");

        EnclosureGroup enclosureGroup = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_GROUP_URI, resourceId), EnclosureGroup.class);

        LOGGER.info("EnclosureGroupClient : getById : End");

        return enclosureGroup;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link EnclosureGroup}&gt; containing the details
     * for all the available enclosure groups found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link EnclosureGroup}&gt; containing
     * the details for all found enclosure groups.
     */
    public ResourceCollection<EnclosureGroup> getAll() {
        LOGGER.info("EnclosureGroupClient : getAll : Start");

        ResourceCollection<EnclosureGroup> enclosureGroups = baseClient.getResourceCollection(
                ResourceUris.ENCLOSURE_GROUP_URI, EnclosureGroup.class);

        LOGGER.info("EnclosureGroupClient : getAll : End");

        return enclosureGroups;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link EnclosureGroup}&gt; containing details
     * for the available enclosure groups found under the current HPE OneView that match the name.
     *
     * @param name enclosure group name as seen in HPE OneView.
     *
     * @return {@link EnclosureGroup} object containing the details.
     */
    public ResourceCollection<EnclosureGroup> getByName(String name) {
        LOGGER.info("EnclosureGroupClient : getByName : Start");

        ResourceCollection<EnclosureGroup> enclosureGroups = baseClient.getResourceCollection(
                ResourceUris.ENCLOSURE_GROUP_URI, EnclosureGroup.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("EnclosureGroupClient : getByName : End");

        return enclosureGroups;
    }

    /**
     * Creates a enclosure group according to the provided {@link EnclosureGroup} object.
     *
     * @param enclosureGroup object containing the enclosure group details.
     *
     * @return {@link EnclosureGroup} containing the created enclosure group.
     */
    public EnclosureGroup create(EnclosureGroup enclosureGroup) {
        LOGGER.info("EnclosureGroupClient : create : Start");

        Request request = new Request(HttpMethodType.POST,
                ResourceUris.ENCLOSURE_GROUP_URI, enclosureGroup);

        EnclosureGroup enclosureGroupCreated = baseClient.executeRequest(request, EnclosureGroup.class);

        LOGGER.info("EnclosureGroupClient : create : End");

        return enclosureGroupCreated;
    }

    /**
     * Updates a {@link EnclosureGroup} identified by the given resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     * @param enclosureGroup object containing the enclosure group details.
     *
     * @return {@link EnclosureGroup} containing the updated enclosure group.
     */
    public EnclosureGroup update(String resourceId, EnclosureGroup enclosureGroup) {
        LOGGER.info("EnclosureGroupClient : update : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_GROUP_URI, resourceId), enclosureGroup);

        EnclosureGroup enclosureGroupUpdated = baseClient.executeRequest(request, EnclosureGroup.class);

        LOGGER.info("EnclosureGroupClient : update : End");

        return enclosureGroupUpdated;
    }

    /**
     * Deletes the {@link EnclosureGroup} identified by the given resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the response.
     */
    public String delete(String resourceId) {
        LOGGER.info("EnclosureGroupClient : delete : Start");

        Request request = new Request(HttpMethodType.DELETE,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_GROUP_URI, resourceId));

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("EnclosureGroupClient : delete : End");

        return response;
    }

    /**
     * Retrieves the configuration script for the specified enclosure group resource identifier.
     *
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     *
     * @return the configuration script for the specified enclosure group.
     */
    public String getConfigurationScript(String resourceId) {
        LOGGER.info("EnclosureGroupClient : getConfigurationScript : Start");

        Request request = new Request(HttpMethodType.GET,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_GROUP_URI, resourceId,
                        ResourceUris.ENCLOSURE_GROUP_SCRIPT_URI));

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("EnclosureGroupClient : getConfigurationScript : End");

        return response;
    }

    /**
     * Retrieves the configuration script for the specified enclosure group resource identifier.
     * 
     * @param resourceId enclosure group resource identifier as seen in HPE OneView.
     * @param scriptData script data to be updated for enclosure group.
     * @return the configuration script for the specified enclosure group.
     */
    public String updateConfigurationScript(String resourceId, String scriptData) {
        LOGGER.info("EnclosureGroupClient : updateConfigurationScript : Start");

        Request request = new Request(HttpMethodType.PUT,
                UrlUtils.createUrl(ResourceUris.ENCLOSURE_GROUP_URI, resourceId,
                        ResourceUris.ENCLOSURE_GROUP_SCRIPT_URI),
                scriptData);

        request.setContentType(ContentType.TEXT_PLAIN);

        /*
        in this particular request, even though the documentation states that the content type
        must ba application/json, it is necessary to change the content type to text/plain
        otherwise the request fails!
         */

        String response = baseClient.executeRequest(request, String.class);

        LOGGER.info("EnclosureGroupClient : updateConfigurationScript : End");

        return response;
    }

}
