/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.networking.ethernet.ConnectionTemplate;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ConnectionTemplateClient {

    /**
     * The module aids in fetching the connection template when provided with
     * the ConnectionTemplate resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param id
     *            The resource identifier for the connection template as seen in HPE OneView.
     * @return {@link ConnectionTemplate} containing the connection template details.
     */
    ConnectionTemplate getConnectionTemplate(final RestParams params, final String id);

    /**
     * The module aids in fetching the connection template when provided with
     * the connection template name.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceName
     *            The resourceName is the connection template name as seen in HPE OneView.
     * @return {@link ConnectionTemplate} containing the connection template details.
     */

    ConnectionTemplate getConnectionTemplateByName(final RestParams params, final String resourceName);

    /**
     * The module takes in a ConnectionTemplate object or JsonRequest and
     * updates the existing connection template based on the resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for the connection template as seen in HPE OneView.
     * @param connectionTemplateDto
     *            This is a object containing the update to be made to an existing
     *            ConnectionTemplate pointed to by the above mentioned
     *            resource identifier.
     * @param useJsonRequest
     *            The JsonRequest body is part of ConnectionTemplate Object
     *            which takes in a String containing the update to be made,
     *            which is converted to ConnectionTemplate Object using adaptor
     *            and processed.
     * @return {@link ConnectionTemplate} containing the updated connection template details.
     */
    ConnectionTemplate updateConnectionTemplate(final RestParams params, final String resourceId,
            final ConnectionTemplate connectionTemplateDto, final boolean useJsonRequest);

    /**
     * The module aids in fetching the connection template details for all
     * networks found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link ConnectionTemplate}&gt; containing
     * the details for all found connection templates.
     */
    ResourceCollection<ConnectionTemplate> getAllConnectionTemplates(final RestParams params);

    /**
     * The module aids in fetching the default connection template details for
     * the network found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ConnectionTemplate} containing the connection template details.
     */
    ConnectionTemplate getDefaultConnectionTemplateForConnectionTemplate(final RestParams params);

    /**
     * The module aids in fetching the connection template resource identifier for the
     * connection template name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the connection template name as seen in HPE OneView.
     * @return String which is the resource identifier for the connection template name
     *         as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
