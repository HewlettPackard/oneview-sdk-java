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
package com.hp.ov.sdk.rest.client;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.serverhardwaretype.ServerHardwareType;
import com.hp.ov.sdk.dto.serverhardwaretype.ServerHardwareTypeUpdate;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface ServerHardwareTypeClient {

    /**
     * The module aids in fetching the server hardware type details for the specified
     * server hardware type resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server hardware type as seen in HPE OneView.
     * @return {@link ServerHardwareType} containing the server hardware type details.
     */
    ServerHardwareType getServerHardwareType(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the server hardware type details for all the
     * server hardware types found under the current HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link ServerHardwareType}&gt; containing
     * the details for all found server hardware types.
     */
    ResourceCollection<ServerHardwareType> getAllServerHardwareTypes(final RestParams params);

    /**
     * The module aids in fetching the server hardware type details for the
     * server hardware type name as specified in HPE OneView.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the server hardware type name as seen in HPE OneView.
     * @return {@link ServerHardwareType} containing the server hardware type details.
     */
    ServerHardwareType getServerHardwareTypeByName(final RestParams params, final String name);

    /**
     * The module takes in a {@link ServerHardwareTypeUpdate} object and updates the existing
     * server hardware type based on the resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for the server hardware type as seen in HPE OneView.
     * @param serverHardwareTypeUpdate
     *            Object containing the server hardware type details, used to update
     *            the specified server hardware type.
     * @return {@link ServerHardwareType} containing the server hardware type with updated fields.
     */
    ServerHardwareType updateServerHardwareType(final RestParams params, final String resourceId,
            final ServerHardwareTypeUpdate serverHardwareTypeUpdate);

    /**
     * The module aids in deleting a server hardware type for the specified server hardware
     * type resource identifier.
     * 
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for server hardware type as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} which returns the task status for the process.
     */
    TaskResourceV2 deleteServerHardwareType(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * The module aids in fetching the server hardware type resource identifier for the
     * server hardware type name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the server hardware type name as seen in HPE OneView.
     * @return String which is the resource Id for the ServerHardwareType name as
     *         seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);

}
