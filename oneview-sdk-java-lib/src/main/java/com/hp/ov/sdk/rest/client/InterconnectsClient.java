/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.rest.client;

import java.util.List;

import com.hp.ov.sdk.dto.InterconnectsCollection;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Interconnects;
import com.hp.ov.sdk.dto.generated.Port;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface InterconnectsClient {
    /**
     * The module aids in fetching the Interconnect details for the specified
     * Interconnect resourceId
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @return interconnectTypesDto, which is a object containing the
     *         InterconnectType details.
     */
    public Interconnects getInterconnectById(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Interconnect details for the
     * FirmwareDriver name as specified in HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param interconnectName
     *            The interconnectName is the Interconnect name as seen in HP
     *            OneView.
     * @return InterconnectsDto, which is a object containing the FirmwareDriver
     *         details.
     */
    public Interconnects getInterconnectByName(final RestParams params, final String interconnectName);

    /**
     * The module aids in fetching the Interconnects details for all the
     * Interconnects found under the current HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return InterconnectsCollectionDto, which is a object containing a
     *         collection of Interconnects details.
     */
    public InterconnectsCollection getAllInterconnects(final RestParams params);

    /**
     * Performs a specific patch operation for the given interconnect. There are a limited
     * set of interconnect properties which may be changed. They are: 'powerState',
     * 'uidState', 'deviceResetState'. If the interconnect supports the operation, the
     * operation is performed and a task is returned through which the results are reported.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     *            details.
     * @param patchDto
     *            This is a object containing the update to be made to existing
     *            interconnect
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 patchInterconnect(RestParams params, String resourceId, Patch patchDto,  boolean aSync);

    /**
     * Updates an interconnect port.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     *            details.
     * @param portDto
     *            This is a object containing the update to be made to existing
     *            Interconnect pointed to by the above mentioned resourceId
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of Interconnect Object which takes
     *            in a String containing the update to be made, which is
     *            converted to Interconnect Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateInterconnectPort(final RestParams params, final String resourceId, final Port portDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Triggers a reset of port protection.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     *            details.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 resetInterconnectPortProtection(final RestParams params, final String resourceId, final boolean aSync);

    /**
     * Gets the statistics from an interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @return InterconnectsStatistics, which is a object containing the
     *         Interconnect statistics details.
     */
    public InterconnectsStatistics getInterconnectStatistics(final RestParams params, final String resourceId);

    /**
     * Gets the Port statistics from an interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @param portName
     *            The name of the port as seen in HP OneView.
     * @return PortStatistics, which is a object containing the Port statistics details.
     */
    public PortStatistics getInterconnectPortStatistics(final RestParams params, final String resourceId, final String portName);

    /**
     * Gets the Subport statistics from an interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @param portName
     *            The name of the port as seen in HP OneView.
     * @param subportNumber
     *            The number of the subport as seen in HP OneView.
     * @return SubportStatistics, which is a object containing the Subport statistics details.
     */
    public SubportStatistics getInterconnectSubportStatistics(final RestParams params, final String resourceId,
            final String portName, final int subportNumber);

    /**
     * Updates the interconnect ports.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     *            details.
     * @param portsDto
     *            This is an array of objects containing the update to be made to existing
     *            Interconnect ports
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of List of ports Object which takes
     *            in a String containing the update to be made, which is
     *            converted to an array of Ports Object using adaptor and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateInterconnectPorts(final RestParams params, final String resourceId, final List<Port> portsDto,
            final boolean aSync, final boolean useJsonRequest);

    /**
     * Gets the named servers for an interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for InterConnect as seen in HP OneView.
     * @return List of NameServer, which is an array of objects containing the name servers details.
     */
    public List<NameServer> getInterconnectNamedServers(final RestParams params, final String resourceId);

    /**
     * The module aids in fetching the Interconnect driver details for the
     * Interconnect driver name as specified in HP OneView.
     *
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the Interconnect driver name as seen in HP
     *            OneView.
     * @return String, which is a resource Id for the Interconnect driver name
     *         as seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);

}
