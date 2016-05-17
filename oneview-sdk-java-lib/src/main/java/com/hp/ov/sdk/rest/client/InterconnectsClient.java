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

import java.util.List;

import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Port;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectsClient {

    /**
     * The module aids in fetching the interconnect details for the specified
     * interconnect resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @return {@link Interconnects} containing the interconnect details.
     */
    Interconnects getInterconnectById(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the interconnect details for the interconnect name
     * as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param interconnectName
     *            The interconnectName is the interconnect name as seen in HPE OneView.
     * @return {@link Interconnects} containing the interconnect details.
     */
    Interconnects getInterconnectByName(final RestParams params, final String interconnectName);

    /**
     * The module aids in fetching the interconnects details for all the
     * interconnects found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link Interconnects}&gt; containing
     * the details for all found interconnects.
     */
    ResourceCollection<Interconnects> getAllInterconnects(final RestParams params);

    /**
     * Performs a specific patch operation for the given interconnect. There are a limited
     * set of interconnect properties which may be changed. They are: 'powerState',
     * 'uidState', 'deviceResetState'. If the interconnect supports the operation, the
     * operation is performed and a task is returned through which the results are reported.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @param patchDto
     *            This is a object containing the update to be made to existing interconnect.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 patchInterconnect(RestParams params, String resourceId, Patch patchDto,  boolean aSync);

    /**
     * Updates an interconnect port.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @param portDto
     *            This is a object containing the update to be made to existing
     *            interconnect pointed to by the above mentioned resource identifier.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Port object which takes
     *            in a String containing the update to be made, which is
     *            converted to Port object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateInterconnectPort(final RestParams params, final String resourceId, final Port portDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Triggers a reset of port protection.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 resetInterconnectPortProtection(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * Gets the statistics from an interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @return {@link InterconnectsStatistics} containing the interconnect statistics details.
     */
    InterconnectsStatistics getInterconnectStatistics(final RestParams params, final String resourceId);

    /**
     * Gets the port statistics from an interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @param portName
     *            The name of the port as seen in HPE OneView.
     * @return {@link PortStatistics} containing the port statistics details.
     */
    PortStatistics getInterconnectPortStatistics(final RestParams params, final String resourceId, final String portName);

    /**
     * Gets the subport statistics from an interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @param portName
     *            The name of the port as seen in HPE OneView.
     * @param subportNumber
     *            The number of the subport as seen in HPE OneView.
     * @return {@link SubportStatistics} containing the subport statistics details.
     */
    SubportStatistics getInterconnectSubportStatistics(final RestParams params, final String resourceId,
            final String portName, final int subportNumber);

    /**
     * Updates the interconnect ports.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @param portsDto
     *            This is an array of objects containing the update to be made to existing
     *            interconnect ports.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously..
     * @param useJsonRequest
     *            The JsonRequest body is part of list of Port object which takes
     *            in a String containing the update to be made, which is
     *            converted to an array of Port object using adaptor and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateInterconnectPorts(final RestParams params, final String resourceId, final List<Port> portsDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Gets the named servers for an interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for interconnect as seen in HPE OneView.
     * @return NameServer list, which is an array of objects containing the name servers details.
     */
    List<NameServer> getInterconnectNamedServers(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the interconnect driver resource identifier for
     * the interconnect name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the interconnect driver name as seen in HPE OneView.
     * @return String which is the resource identifier for the interconnect driver name
     *         as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);

}
