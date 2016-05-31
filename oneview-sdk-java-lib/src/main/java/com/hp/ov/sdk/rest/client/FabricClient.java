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

import com.hp.ov.sdk.dto.Fabric;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface FabricClient {

    /**
     * The module aids in fetching the fabric when provided with
     * the Fabric resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param id
     *            The resource identifier for the fabric as seen in HPE OneView.
     * @return {@link Fabric} containing the fabric details.
     */
    Fabric getFabricById(final RestParams params, final String id);

    /**
     * The module aids in fetching the fabric when provided with
     * the fabric name.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceName
     *            The resourceName is the fabric name as seen in HPE OneView.
     * @return {@link Fabric} containing the connection template details.
     */
    Fabric getFabricByName(final RestParams params, final String resourceName);

    /**
     * The module aids in fetching the details for all the fabrics
     * found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link Fabric}&gt; containing
     * the details for all found fabrics.
     */
    ResourceCollection<Fabric> getAllFabrics(final RestParams params);

}
