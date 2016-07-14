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
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface RackClient {

    /**
     * Retrieves the {@link Rack} details for the specified rack.
     *
     * @param params structure containing the connection details.
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return {@link Rack} object containing the details.
     */
    Rack getRack(RestParams params, String resourceId);

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Rack}&gt; containing details
     * for all the available racks found under the current HPE OneView.
     *
     * @param params structure containing the connection details.
     *
     * @return {@link ResourceCollection}&lt;{@link Rack}&gt; containing
     * the details for all found racks.
     */
    ResourceCollection<Rack> getAllRacks(RestParams params);

    /**
     * Retrieves the {@link Rack} details for the specified
     * rack identified by name.
     *
     * @param params structure containing the connection details.
     * @param name rack name as seen in HPE OneView.
     *
     * @return {@link Rack} object containing the details.
     */
    Rack getRackByName(RestParams params, String name);

    /**
     * Adds a rack according to the provided {@link Rack} object.
     *
     * @param params structure containing the connection details.
     * @param rack object containing the rack details.
     *
     * @return {@link Rack} containing the added rack.
     */
    Rack addRack(RestParams params, Rack rack);

    /**
     * Updates a {@link Rack} identified by the given resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId rack resource identifier as seen in HPE OneView.
     * @param rack object containing the rack details.
     *
     * @return {@link Rack} containing the rack updated.
     */
    Rack updateRack(RestParams params, String resourceId, Rack rack);

    /**
     * Removes the {@link Rack} identified by the given resource identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return String value containing the result of the process.
     */
    String removeRack(RestParams params, String resourceId);

    /**
     * Removes the {@link Rack}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param params structure containing the connection details.
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 removeRackByFilter(RestParams params, String filter, boolean aSync);

    /**
     * Retrieves the topology information for the rack resource specified by
     * the given identifier.
     *
     * @param params structure containing the connection details.
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return {@link TopologyInformation} containing the topology information for the rack.
     */
    TopologyInformation getDeviceTopology(RestParams params, String resourceId);

    /**
     * Retrieves the rack resource identifier for the specified
     * rack identified by name.
     *
     * @param params structure containing the connection details.
     * @param name rack name as seen in HPE OneView.
     *
     * @return resource identifier for the rack name as seen in HPE OneView.
     */
    String getId(RestParams params, final String name);
}
