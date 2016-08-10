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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectsStatistics;
import com.hp.ov.sdk.dto.NameServer;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.PortStatistics;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SubportStatistics;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.interconnect.Interconnect;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class InterconnectClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(InterconnectClient.class);

    private final BaseClient baseClient;

    public InterconnectClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * The module aids in fetching the interconnect details for the specified
     * interconnect resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     *
     * @return {@link Interconnect} containing the interconnect details.
     */
    public Interconnect getById(String resourceId) {
        LOGGER.info("InterconnectClient : getById : Start");

        Interconnect interconnect = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId), Interconnect.class);

        LOGGER.info("InterconnectClient : getById : End");

        return interconnect;
    }

    /**
     * The module aids in fetching the interconnect details for all the
     * interconnects found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Interconnect}&gt; containing
     * the details for all found interconnects.
     */
    public ResourceCollection<Interconnect> getAll() {
        LOGGER.info("InterconnectClient : getAll : Start");

        ResourceCollection<Interconnect> interconnects = baseClient.getResourceCollection(
                ResourceUris.INTERCONNECT_URI, Interconnect.class);

        LOGGER.info("InterconnectClient : getAll : End");

        return interconnects;
    }

    /**
     * The module aids in fetching the interconnect details for the interconnect
     * name as specified in HPE OneView.
     *
     * @param name interconnect name as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link Interconnect}&gt; containing
     * the details for all found interconnects.
     */
    public ResourceCollection<Interconnect> getByName(String name) {
        LOGGER.info("InterconnectClient : getByName : Start");

        ResourceCollection<Interconnect> interconnects = baseClient.getResourceCollection(
                ResourceUris.INTERCONNECT_URI, Interconnect.class, UrlParameter.getFilterByNameParameter(name));

        LOGGER.info("InterconnectClient : getByName : End");

        return interconnects;
    }

    /**
     * Performs a specific patch operation for the given interconnect. There are a limited
     * set of interconnect properties which may be changed. They are: 'powerState',
     * 'uidState', 'deviceResetState'. If the interconnect supports the operation, the
     * operation is performed and a task is returned through which the results are reported.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param patch containing the update to be made to existing interconnect.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 patch(String resourceId, Patch patch, boolean aSync) {
        LOGGER.info("InterconnectClient : patch : Start");

        Request request = new Request(HttpMethodType.PATCH,
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId), patch);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("InterconnectClient : patch : End");

        return taskResource;
    }

    /**
     * The module takes in a {@link Port} object and updates the existing
     * interconnect port based on the resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param port containing the interconnect port details, used to update a interconnect port.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updatePort(String resourceId, Port port, boolean aSync) {
        LOGGER.info("InterconnectClient : updatePort : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId, ResourceUris.INTERCONNECT_PORTS_URI),
                port, aSync);

        LOGGER.info("InterconnectClient : updatePort : End");

        return taskResource;
    }

    /**
     * The module takes in a {@link List}&lt;{@link Port}&gt; object and updates the existing
     * interconnect ports based on the resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param ports containing the interconnect ports details, used to update the interconnect ports.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updatePorts(String resourceId, List<Port> ports, boolean aSync) {
        LOGGER.info("InterconnectClient : updatePorts : Start");

        TaskResourceV2 taskResource = baseClient.updateResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId,
                        ResourceUris.INTERCONNECT_UPDATE_PORTS_URI),
                ports, aSync);

        LOGGER.info("InterconnectClient : updatePorts : End");

        return taskResource;
    }

    /**
     * Triggers a reset of the interconnect port protection.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 resetPortProtection(String resourceId, boolean aSync) {
        LOGGER.info("InterconnectClient : resetPortProtection : Start");

        String uri = UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId,
                ResourceUris.INTERCONNECT_RESET_PORT_PROTECTION_URI);
        Request request = new Request(HttpMethodType.PUT, uri);

        TaskResourceV2 taskResource = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("InterconnectClient : resetPortProtection : End");

        return taskResource;
    }

    /**
     * Retrieve the statistics of an interconnect for the specified
     * interconnect resource identifier.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     *
     * @return {@link InterconnectsStatistics} containing the interconnect statistics details.
     */
    public InterconnectsStatistics getStatistics(String resourceId) {
        LOGGER.info("InterconnectClient : getStatistics : Start");

        InterconnectsStatistics statistics = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId,
                        ResourceUris.INTERCONNECT_STATISTICS_URI),
                InterconnectsStatistics.class);

        LOGGER.info("InterconnectClient : getStatistics : End");

        return statistics;
    }

    /**
     * Retrieve the statistics of an interconnect port for the specified
     * interconnect resource identifier and port name.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     * @param portName name of the port as seen in HPE OneView
     *
     * @return {@link PortStatistics} containing the statistics of an interconnect port details.
     */
    public PortStatistics getPortStatistics(String resourceId, String portName) {
        LOGGER.info("InterconnectClient : getPortStatistics : Start");

        PortStatistics statistics = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId,
                        ResourceUris.INTERCONNECT_STATISTICS_URI, portName),
                PortStatistics.class);

        LOGGER.info("InterconnectClient : getPortStatistics : End");

        return statistics;
    }

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
    public SubportStatistics getSubportStatistics(String resourceId, String portName, int subportName) {
        LOGGER.info("InterconnectClient : getSubportStatistics : Start");

        SubportStatistics statistics = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId,
                        ResourceUris.INTERCONNECT_STATISTICS_URI, portName,
                        ResourceUris.INTERCONNECT_SUBPORT_URI, String.valueOf(subportName)),
                SubportStatistics.class);

        LOGGER.info("InterconnectClient : getSubportStatistics : End");

        return statistics;
    }

    /**
     * Retrieves the named servers for an interconnect found under the current HPE OneView.
     *
     * @param resourceId resource identifier for interconnect as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link NameServer}&gt; containing the name servers
     * for the specified interconnect.
     */
    public List<NameServer> getNamedServers(String resourceId) {
        LOGGER.info("InterconnectClient : getNamedServers : Start");

        List<NameServer> nameServers = baseClient.getResourceList(
                UrlUtils.createUrl(ResourceUris.INTERCONNECT_URI, resourceId,
                        ResourceUris.INTERCONNECT_NAME_SERVERS_URI),
                new TypeToken<List<NameServer>>(){});

        LOGGER.info("InterconnectClient : getNamedServers : End");

        return nameServers;
    }

}
