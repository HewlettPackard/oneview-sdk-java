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
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class ConnectionTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionTemplateClient.class);

    private final BaseClient baseClient;

    public ConnectionTemplateClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link ConnectionTemplate} details for the specified connection template.
     *
     * @param resourceId connection template resource identifier as seen in HPE OneView.
     *
     * @return {@link ConnectionTemplate} object containing the details.
     */
    public ConnectionTemplate getById(String resourceId) {
        LOGGER.info("ConnectionTemplateClient : getById : Start");

        ConnectionTemplate connectionTemplate = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.CONNECTION_TEMPLATE_URI, resourceId), ConnectionTemplate.class);

        LOGGER.info("ConnectionTemplateClient : getById : End");

        return connectionTemplate;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ConnectionTemplate}&gt; containing details
     * for all the available connection templates found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ConnectionTemplate}&gt; containing
     * the details for all found connection templates.
     */
    public ResourceCollection<ConnectionTemplate> getAll() {
        LOGGER.info("ConnectionTemplateClient : getAll : Start");

        ResourceCollection<ConnectionTemplate> connectionTemplates = baseClient.getResourceCollection(
                ResourceUris.CONNECTION_TEMPLATE_URI, ConnectionTemplate.class);

        LOGGER.info("ConnectionTemplateClient : getAll : End");

        return connectionTemplates;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ConnectionTemplate}&gt; containing details
     * for the available connection templates found under the current HPE OneView that match the name.
     *
     * @param name connection template name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ConnectionTemplate}&gt; containing
     * the details for the found connection templates.
     */
    public ResourceCollection<ConnectionTemplate> getByName(String name) {
        LOGGER.info("ConnectionTemplateClient : getByName : Start");

        ResourceCollection<ConnectionTemplate> connectionTemplates = baseClient.getResourceCollection(
                ResourceUris.CONNECTION_TEMPLATE_URI, ConnectionTemplate.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("ConnectionTemplateClient : getByName : End");

        return connectionTemplates;
    }

    /**
     * Retrieves the default connection template details for the network
     * found under the current HPE OneView.
     *
     * @return {@link ConnectionTemplate} object containing the default details.
     */
    public ConnectionTemplate getDefaultConnectionTemplate() {
        LOGGER.info("ConnectionTemplateClient : getDefaultConnectionTemplate : Start");

        ConnectionTemplate connectionTemplate = baseClient.getResource(
                ResourceUris.DEFAULT_CONNECTION_TEMPLATE_URI, ConnectionTemplate.class);

        LOGGER.info("ConnectionTemplateClient : getDefaultConnectionTemplate : End");

        return connectionTemplate;
    }

    /**
     * Updates a {@link ConnectionTemplate} identified by the given resource identifier.
     *
     * @param resourceId connection template resource identifier as seen in HPE OneView.
     * @param connectionTemplate object containing the connection template details.
     *
     * @return {@link ConnectionTemplate} containing the connection template updated.
     */
    public ConnectionTemplate update(String resourceId, ConnectionTemplate connectionTemplate) {
        LOGGER.info("ConnectionTemplateClient : update : Start");

        Request request = new Request(HttpMethod.PUT, UrlUtils.createUrl(
                ResourceUris.CONNECTION_TEMPLATE_URI, resourceId), connectionTemplate);
        ConnectionTemplate updatedConnectionTemplate = this.baseClient.executeRequest(request,
                ConnectionTemplate.class);

        LOGGER.info("ConnectionTemplateClient : update : End");

        return updatedConnectionTemplate;
    }

}
