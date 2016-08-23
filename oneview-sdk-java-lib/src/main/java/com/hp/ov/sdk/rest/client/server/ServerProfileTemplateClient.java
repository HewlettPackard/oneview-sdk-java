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
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class ServerProfileTemplateClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileTemplateClient.class);
    private static final int TIMEOUT = 1200000;

    private final BaseClient baseClient;

    public ServerProfileTemplateClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link ServerProfileTemplate} details for the specified server profile template.
     *
     * @param resourceId server profile template resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfileTemplate} object containing the details.
     */
    public ServerProfileTemplate getById(String resourceId) {
        LOGGER.info("ServerProfileTemplateClient : getById : Start");

        ServerProfileTemplate serverProfileTemplate = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId),
                ServerProfileTemplate.class);

        LOGGER.info("ServerProfileTemplateClient : getById : End");

        return serverProfileTemplate;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerProfileTemplate}&gt; containing the details
     * for all the available server profile template found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link ServerProfileTemplate}&gt; containing
     * the details for all found server profile templates.
     */
    public ResourceCollection<ServerProfileTemplate> getAll() {
        LOGGER.info("ServerProfileTemplateClient : getAll : Start");

        ResourceCollection<ServerProfileTemplate> serverProfileTemplates = baseClient.getResourceCollection(
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI, ServerProfileTemplate.class);

        LOGGER.info("ServerProfileTemplateClient : getAll : End");

        return serverProfileTemplates;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link ServerProfileTemplate}&gt; containing details
     * for the available server profile template found under the current HPE OneView that match the name.
     *
     * @param name server profile template name as seen in HPE OneView.
     *
     * @return {@link ServerProfileTemplate} object containing the details.
     */
    public ResourceCollection<ServerProfileTemplate> getByName(String name) {
        LOGGER.info("ServerProfileTemplateClient : getByName : Start");

        ResourceCollection<ServerProfileTemplate> serverProfileTemplates = baseClient.getResourceCollection(
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI, ServerProfileTemplate.class,
                UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("ServerProfileTemplateClient : getByName : End");

        return serverProfileTemplates;
    }

    /**
     * Creates a server profile template according to the provided {@link ServerProfileTemplate} object.
     * The request can be processed synchronously or asynchronously.
     *
     * @param serverProfileTemplate object containing the server profile template details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 create(ServerProfileTemplate serverProfileTemplate, boolean aSync) {
        LOGGER.info("ServerProfileTemplateClient : create : Start");

        Request request = new Request(HttpMethodType.POST,
                ResourceUris.SERVER_PROFILE_TEMPLATE_URI, serverProfileTemplate);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileTemplateClient : create : End");

        return taskResource;
    }

    /**
     * Removes the server profile template identified by the given resource identifier.
     *
     * @param resourceId server profile template resource identifier as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 delete(String resourceId, boolean aSync) {
        LOGGER.info("ServerProfileTemplateClient : delete : Start");

        Request request = new Request(HttpMethodType.DELETE,
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId));

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileTemplateClient : delete : End");

        return taskResource;
    }

    /**
     * Updates a {@link ServerProfileTemplate} identified by the given resource identifier.
     * 
     * @param resourceId server profile template resource identifier as seen in HPE OneView.
     * @param serverProfileTemplate object containing the server profile template details.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    public TaskResourceV2 update(String resourceId, ServerProfileTemplate serverProfileTemplate, boolean aSync) {
        LOGGER.info("ServerProfileTemplateClient : update : Start");

        String resourceUri = UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId);
        Request request = new Request(HttpMethodType.PUT, resourceUri, serverProfileTemplate);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 taskResource = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("ServerProfileTemplateClient : update : End");

        return taskResource;
    }

    /**
     * A server profile object will be returned with the configuration based on this template.
     * This profile object can subsequently be used to create a new server profile.
     *
     * @param resourceId server profile template resource identifier as seen in HPE OneView.
     *
     * @return {@link ServerProfile} containing the server profile details.
     */
    public ServerProfile getNewServerProfile(String resourceId) {
        LOGGER.info("ServerProfileTemplateClient : getNewServerProfile : Start");

        ServerProfile serverProfile = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.SERVER_PROFILE_TEMPLATE_URI, resourceId,
                        ResourceUris.SERVER_PROFILE_TEMPLATE_NEW_PROFILE_URI),
                ServerProfile.class);

        LOGGER.info("ServerProfileTemplateClient : getNewServerProfile : End");

        return serverProfile;
    }

}
