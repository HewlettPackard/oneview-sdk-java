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

import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(ConnectionTemplateClient.CONNECTION_TEMPLATE_URI)
public interface ConnectionTemplateClient extends
        SearchableResource<ConnectionTemplate> {

    String CONNECTION_TEMPLATE_URI = "/rest/connection-templates";
    String DEFAULT_CONNECTION_TEMPLATE_URI = "/defaultConnectionTemplate";

    /**
     * Retrieves the default connection template details for the network
     * found under the current HPE OneView.
     *
     * @return {@link ConnectionTemplate} object containing the default details.
     */
    @Endpoint(uri = DEFAULT_CONNECTION_TEMPLATE_URI)
    ConnectionTemplate getDefaultConnectionTemplate();

    /**
     * Updates a {@link ConnectionTemplate} identified by the given resource identifier.
     *
     * @param resourceId connection template resource identifier as seen in HPE OneView.
     * @param connectionTemplate object containing the connection template details.
     *
     * @return {@link ConnectionTemplate} containing the connection template updated.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    ConnectionTemplate update(@PathParam("resourceId") String resourceId, @BodyParam ConnectionTemplate connectionTemplate);

}
