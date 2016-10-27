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

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.rack.Rack;
import com.hp.ov.sdk.dto.rack.TopologyInformation;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.URIQuery;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;
import com.hp.ov.sdk.rest.reflect.QueryParam;

@Api(RackClient.RACK_URI)
public interface RackClient extends
        SearchableResource<Rack> {

    String RACK_DEVICE_TOPOLOGY = "/deviceTopology";
    String RACK_URI = "/rest/racks";

    /**
     * Adds a resource according to the provided <code>resource</code> object.
     *
     * @param resource object containing the details of the resource that should be added.
     *
     * @return {@link Rack} object containing the result of this request.
     */
    @Endpoint(method = HttpMethod.POST)
    Rack add(@BodyParam Rack resource);

    /**
     * Updates the resource identified by the <code>resourceId</code> according to the
     * provided <code>resource</code> object.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     * @param resource object containing the details of the resource that should be created.
     *
     * @return {@link Rack} object containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.PUT)
    Rack update(@PathParam("resourceId") String resourceId, @BodyParam Rack resource);

    /**
     * Removes the resource identified by the provided <code>resourceId</code>.
     *
     * @param resourceId resource identifier as seen in HPE OneView.
     *
     * @return {@link String} containing the result of this request.
     */
    @Endpoint(uri = "/{resourceId}", method = HttpMethod.DELETE)
    String remove(@PathParam("resourceId") String resourceId);

    /**
     * Removes the {@link Rack}(s) matching the filter. A filter is required
     * to identify the set of resources to be removed. The actual deletion will proceed
     * asynchronously. Although, the method can process the request asynchronously or
     * synchronously, based on the aSync flag input.
     *
     * @param filter A general filter/query string that narrows the list of resources.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                 some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(method = HttpMethod.DELETE)
    TaskResource removeByFilter(@QueryParam(key = URIQuery.FILTER) String filter, RequestOption ... options);

    /**
     * Retrieves the topology information for the rack resource specified by
     * the given identifier.
     *
     * @param resourceId rack resource identifier as seen in HPE OneView.
     *
     * @return {@link TopologyInformation} containing the topology information for the rack.
     */
    @Endpoint(uri = "/{resourceId}" + RACK_DEVICE_TOPOLOGY)
    TopologyInformation getDeviceTopology(@PathParam("resourceId") String resourceId);

}
