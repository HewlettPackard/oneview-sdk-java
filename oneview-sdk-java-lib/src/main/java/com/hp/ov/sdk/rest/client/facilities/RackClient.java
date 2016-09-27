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
package com.hp.ov.sdk.rest.client.facilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class RackClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RackClient.class);

    private final BaseClient baseClient;

    public RackClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * Retrieves the {@link Rack} details for the specified rack.
     *
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return {@link Rack} object containing the details.
     */
    public Rack getById(String resourceId) {
        LOGGER.info("RackClient : getById : Start");

        Rack rack = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.RACK_URI, resourceId), Rack.class);

        LOGGER.info("RackClient : getById : End");

        return rack;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Rack}&gt; containing details
     * for all the available racks found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Rack}&gt; containing
     * the details for all found racks.
     */
    public ResourceCollection<Rack> getAll() {
        LOGGER.info("RackClient : getAll : Start");

        ResourceCollection<Rack> racks = baseClient.getResourceCollection(
                ResourceUris.RACK_URI, Rack.class);

        LOGGER.info("RackClient : getAll : End");

        return racks;
    }

    /**
     * Retrieves a {@link ResourceCollection}&lt;{@link Rack}&gt; containing details
     * for the available racks found under the current HPE OneView that match the name.
     *
     * @param name rack name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Rack}&gt; containing
     * the details for the found racks.
     */
    public ResourceCollection<Rack> getByName(String name) {
        LOGGER.info("RackClient : getByName : Start");

        ResourceCollection<Rack> racks = baseClient.getResourceCollection(
                ResourceUris.RACK_URI, Rack.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("RackClient : getByName : End");

        return racks;
    }

    /**
     * Adds a rack according to the provided {@link Rack} object.
     *
     * @param rack object containing the rack details.
     *
     * @return {@link Rack} containing the added rack.
     */
    public Rack add(Rack rack) {
        LOGGER.info("RackClient : add : Start");

        Request request = new Request(HttpMethod.POST, ResourceUris.RACK_URI, rack);
        Rack createdRack = this.baseClient.executeRequest(request, Rack.class);

        LOGGER.info("RackClient : add : End");

        return createdRack;
    }

    /**
     * Updates a {@link Rack} identified by the given resource identifier.
     *
     * @param resourceId rack resource identifier as seen in HPE OneView.
     * @param rack object containing the rack details.
     *
     * @return {@link Rack} containing the rack updated.
     */
    public Rack update(String resourceId, Rack rack) {
        LOGGER.info("RackClient : update : Start");

        Request request = new Request(HttpMethod.PUT,
                UrlUtils.createUrl(ResourceUris.RACK_URI, resourceId), rack);
        Rack updatedRack = this.baseClient.executeRequest(request, Rack.class);

        LOGGER.info("RackClient : update : End");

        return updatedRack;
    }

    /**
     * Removes the {@link Rack} identified by the given resource identifier.
     *
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return String value containing the result of the process.
     */
    public String remove(String resourceId) {
        LOGGER.info("RackClient : remove : Start");

        Request request = new Request(HttpMethod.DELETE,
                UrlUtils.createUrl(ResourceUris.RACK_URI, resourceId));
        String response = this.baseClient.executeRequest(request, String.class);

        LOGGER.info("RackClient : remove : End");

        return response;
    }

    /**
     * Removes the {@link Rack}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param aSync Flag input to process request asynchronously or synchronously.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    public TaskResource removeByFilter(String filter, boolean aSync) {
        LOGGER.info("RackClient : removeByFilter : Start");

        TaskResource taskResource = baseClient.deleteResource(ResourceUris.RACK_URI, aSync,
                new UrlParameter("filter", filter));

        LOGGER.info("RackClient : removeByFilter : End");

        return taskResource;
    }

    /**
     * Retrieves the topology information for the rack resource specified by
     * the given identifier.
     *
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return {@link TopologyInformation} containing the topology information for the rack.
     */
    public TopologyInformation getDeviceTopology(String resourceId) {
        LOGGER.info("RackClient : getDeviceTopology : Start");

        TopologyInformation topologyInformation = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.RACK_URI, resourceId, ResourceUris.RACK_DEVICE_TOPOLOGY),
                TopologyInformation.class);

        LOGGER.info("RackClient : getDeviceTopology : End");

        return topologyInformation;
    }

}
