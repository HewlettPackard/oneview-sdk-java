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

package com.hp.ov.sdk.rest.client.common;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

public interface RetrievableResource<T> {

    /**
     * Retrieves the resource identified by the given <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return object containing the details of the resource identified by <code>resourceId</code>.
     */
    @Endpoint(uri = "/{resourceId}")
    T getById(@PathParam("resourceId") String resourceId);

    /**
     * Retrieves a paginated collection ({@link ResourceCollection}) containing the details for the
     * available resources found under the current HPE OneView.
     *
     * @return {@link ResourceCollection} paginated collection containing the details for the
     * available resources.
     */
    @Endpoint
    ResourceCollection<T> get();

    /**
     * Retrieves a collection ({@link ResourceCollection}) containing the details for the <b>all</b>
     * available resources of a given type found under the current HPE OneView.
     *
     * @return {@link ResourceCollection} collection containing the details for the available resources.
     */
    @Endpoint
    ResourceCollection<T> getAll();

}
