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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.servers.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class ServerHardwareTypeClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHardwareTypeClient.class);

    private final BaseClient baseClient;

    public ServerHardwareTypeClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link ServerHardwareType} details for the specified server hardware type.
     *
     * @param resourceId server hardware type resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerHardwareType} object containing the details.
     */
    public ServerHardwareType getById(String resourceId) {
        LOGGER.info("ServerHardwareTypeClient : getById : Start");

        ServerHardwareType serverHardwareType = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_TYPE_URI, resourceId), ServerHardwareType.class);

        LOGGER.info("ServerHardwareTypeClient : getById : End");

        return serverHardwareType;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerHardwareType}&gt; containing the details
     * for all the available server hardware types found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ServerHardwareType}&gt; containing
     * the details for all found server hardware types.
     */
    public ResourceCollection<ServerHardwareType> getAll() {
        LOGGER.info("ServerHardwareTypeClient : getAll : Start");

        ResourceCollection<ServerHardwareType> serverHardwareTypes = baseClient.getResourceCollection(
                ResourceUris.SERVER_HARDWARE_TYPE_URI, ServerHardwareType.class);

        LOGGER.info("ServerHardwareTypeClient : getAll : End");

        return serverHardwareTypes;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerHardwareType}&gt; containing details
     * for the available server hardware types found under the current HPE OneView that match the name.
     *
     * @param name server hardware type name as seen in HPE OneView.
     *
     * @return {@link ServerHardwareType} object containing the details.
     */
    public ResourceCollection<ServerHardwareType> getByName(String name) {
        LOGGER.info("ServerHardwareTypeClient : getByName : Start");

        ResourceCollection<ServerHardwareType> serverHardwareTypes = baseClient.getResourceCollection(
                ResourceUris.SERVER_HARDWARE_TYPE_URI, ServerHardwareType.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("ServerHardwareTypeClient : getByName : End");

        return serverHardwareTypes;
    }

    /**
     * Updates a {@link ServerHardwareType} identified by the given resource identifier.
     *
     * @param resourceId server hardware type resource identifier as seen in HPE OneView.
     * @param serverHardwareTypeUpdate object containing the logical switch details.
     *
     * @return {@link ServerHardwareType} containing the updated server hardware type.
     */
    public ServerHardwareType update(String resourceId, ServerHardwareTypeUpdate serverHardwareTypeUpdate) {
        LOGGER.info("ServerHardwareTypeClient : update : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_TYPE_URI, resourceId), serverHardwareTypeUpdate);

        ServerHardwareType serverHardwareTypeUpdated = baseClient.executeRequest(request, ServerHardwareType.class);

        LOGGER.info("ServerHardwareTypeClient : update : End");

        return serverHardwareTypeUpdated;
    }

    /**
     * Deletes the {@link ServerHardwareType} identified by the given resource identifier.
     *
     * @param resourceId server hardware type resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link String} containing the response.
     */
    public TaskResource delete(String resourceId, boolean aSync) {
        LOGGER.info("ServerHardwareTypeClient : delete : Start");

        TaskResource taskResource = baseClient.deleteResource(
                UrlUtils.createUrl(ResourceUris.SERVER_HARDWARE_TYPE_URI, resourceId), aSync);

        LOGGER.info("ServerHardwareTypeClient : delete : End");

        return taskResource;
    }

}
