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

import java.util.List;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.InterconnectsStatistics;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.dto.networking.interconnect.NameServer;
import com.hp.ov.sdk.dto.networking.interconnect.PortStatistics;
import com.hp.ov.sdk.dto.networking.interconnect.SubportStatistics;
import com.hp.ov.sdk.rest.client.common.PatchableResource;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(InterconnectClient.INTERCONNECT_URI)
public interface InterconnectClient extends
        SearchableResource<Interconnect>,
        PatchableResource {

    String INTERCONNECT_URI = "/rest/interconnects";
    String INTERCONNECT_NAME_SERVERS_URI = "/nameServers";
    String INTERCONNECT_PORTS_URI = "/ports";
    String INTERCONNECT_RESET_PORT_PROTECTION_URI = "/resetportprotection";
    String INTERCONNECT_STATISTICS_URI = "/statistics";
    String INTERCONNECT_SUBPORT_URI = "/subport";
    String INTERCONNECT_UPDATE_PORTS_URI = "/update-ports";

    /**
     * The module takes in a {@link Port} object and updates the existing
     * interconnect port based on the resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param port containing the interconnect port details, used to update a interconnect port.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_PORTS_URI, method = HttpMethod.PUT)
    TaskResource updatePort(@PathParam("resourceId") String resourceId,
            @BodyParam Port port, RequestOption... options);

    /**
     * The module takes in a {@link List}&lt;{@link Port}&gt; object and updates the existing
     * interconnect ports based on the resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param ports containing the interconnect ports details, used to update the interconnect ports.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_UPDATE_PORTS_URI, method = HttpMethod.PUT)
    TaskResource updatePorts(@PathParam("resourceId") String resourceId,
            @BodyParam List<Port> ports, RequestOption... options);

    /**
     * Triggers a reset of the interconnect port protection.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param options varargs of {@link RequestOption} which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_RESET_PORT_PROTECTION_URI, method = HttpMethod.PUT)
    TaskResource resetPortProtection(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * Retrieve the statistics of an interconnect for the specified
     * interconnect resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     *
     * @return {@link InterconnectsStatistics} containing the interconnect statistics details.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_STATISTICS_URI)
    InterconnectsStatistics getStatistics(@PathParam("resourceId") String resourceId);

    /**
     * Retrieve the statistics of an interconnect port for the specified
     * interconnect resource identifier and port name.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param portName name of the port as seen in HPE OneView
     *
     * @return {@link PortStatistics} containing the statistics of an interconnect port details.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_STATISTICS_URI + "/{portName}")
    PortStatistics getPortStatistics(@PathParam("resourceId") String resourceId, @PathParam("portName") String portName);

    /**
     * Retrieve the statistics of an interconnect subport for the specified
     * interconnect resource identifier, port name and subport number.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param portName name of the port as seen in HPE OneView
     * @param subportName number of the subport as seen in HPE OneView
     *
     * @return {@link SubportStatistics} containing the statistics of an interconnect subport details.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_STATISTICS_URI + "/{portName}" + INTERCONNECT_SUBPORT_URI + "/{subportName}")
    SubportStatistics getSubportStatistics(@PathParam("resourceId") String resourceId,
            @PathParam("portName") String portName,
            @PathParam("subportName") int subportName);

    /**
     * Retrieves the named servers for an interconnect found under the current HPE OneView.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link NameServer}&gt; containing the name servers
     * for the specified interconnect.
     */
    @Endpoint(uri = "/{resourceId}" + INTERCONNECT_NAME_SERVERS_URI)
    List<NameServer> getNamedServers(@PathParam("resourceId") String resourceId);

}
