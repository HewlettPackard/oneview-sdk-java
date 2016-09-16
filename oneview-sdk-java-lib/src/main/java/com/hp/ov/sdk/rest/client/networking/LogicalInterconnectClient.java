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

import com.google.common.base.Predicate;
import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.dto.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InternalVlanAssociation;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LiFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LogicalInterconnect;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PortMonitor;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.http.core.UrlParameter;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.util.UrlUtils;

public class LogicalInterconnectClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalInterconnectClient.class);
    private static final int TIMEOUT = 300000; // in milliseconds

    private final BaseClient baseClient;

    public LogicalInterconnectClient(BaseClient baseClient) {
        this.baseClient = baseClient;
    }

    /**
     * The module aids in fetching the logical interconnect details for the specified
     * logical interconnect resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link LogicalInterconnect} containing the logical interconnect details.
     */
    public LogicalInterconnect getById(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getById : Start");

        LogicalInterconnect interconnect = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId),
                LogicalInterconnect.class);

        LOGGER.info("LogicalInterconnectClient : getById : End");

        return interconnect;
    }

    /**
     * The module aids in fetching the logical interconnect details for all the
     * interconnects found under the current HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link LogicalInterconnect}&gt; containing
     * the details for all found interconnects.
     */
    public ResourceCollection<LogicalInterconnect> getAll() {
        LOGGER.info("LogicalInterconnectClient : getAll : Start");

        ResourceCollection<LogicalInterconnect> interconnects = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_INTERCONNECT_URI, LogicalInterconnect.class);

        LOGGER.info("LogicalInterconnectClient : getAll : End");

        return interconnects;
    }

    /**
     * The module aids in fetching the logical interconnect details for the logical interconnect
     * name as specified in HPE OneView.
     *
     * @param name logical interconnect name as seen in HPE OneView.
     *
     * @return {@link List}&lt;{@link LogicalInterconnect}&gt; containing
     * the details for all found logical interconnects.
     */
    public List<LogicalInterconnect> getByName(final String name) {
        LOGGER.info("LogicalInterconnectClient : getByName : Start");

        ResourceCollection<LogicalInterconnect> interconnects = baseClient.getResourceCollection(
                ResourceUris.LOGICAL_INTERCONNECT_URI, LogicalInterconnect.class);

        List<LogicalInterconnect> filteredInterconnects = interconnects.getMembers(
                new Predicate<LogicalInterconnect>() {
            @Override
            public boolean apply(LogicalInterconnect input) {
                return (name.equalsIgnoreCase(input.getName()));
            }
        });

        LOGGER.info("LogicalInterconnectClient : getByName : End");

        return filteredInterconnects;
    }

    /**
     * The module aids in creating an interconnect at the given location.
     *
     * @param location location for the logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 createInterconnect(Location location, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : create : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI,
                ResourceUris.LOGICAL_INTERCONNECT_LOCATION_URI,
                ResourceUris.LOGICAL_INTERCONNECT_INTERCONNECTS_URI);

        Request request = new Request(HttpMethod.POST, uri, location);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : create : End");

        return task;
    }

    /**
     * The module aids in removing an interconnect from a location.
     *
     * @param enclosureUri enclosure URI as seen in HPE OneView.
     * @param bay bay of the interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 deleteInterconnect(String enclosureUri, String bay, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : delete : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI,
                ResourceUris.LOGICAL_INTERCONNECT_LOCATION_URI,
                ResourceUris.LOGICAL_INTERCONNECT_INTERCONNECTS_URI);

        Request request = new Request(HttpMethod.DELETE, uri);

        request.setTimeout(TIMEOUT);
        request.addQuery(new UrlParameter("location", "Enclosure:" + enclosureUri + ",Bay:"+ bay));

        TaskResourceV2 task = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : delete : End");

        return task;
    }

    /**
     * This module aids in fetching the SNMP configuration for a logical
     * interconnect that is specified by resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link SnmpConfiguration} containing the SNMP configuration details.
     */
    public SnmpConfiguration getSnmpConfiguration(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getSnmpConfiguration : Start");

        SnmpConfiguration snmpConfiguration = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI),
                SnmpConfiguration.class);

        LOGGER.info("LogicalInterconnectClient : getSnmpConfiguration : End");

        return snmpConfiguration;
    }

    /**
     * This modules aids in updating the SNMP configuration of a logical interconnect. Changes to the
     * SNMP configuration are asynchronously applied to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param snmpConfiguration object containing the update to be made to the logical interconnect.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateSnmpConfiguration(String resourceId,
            SnmpConfiguration snmpConfiguration, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateSnmpConfiguration : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI,
                resourceId, ResourceUris.LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI);

        Request request = new Request(HttpMethod.PUT, uri, snmpConfiguration);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = this.baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateSnmpConfiguration : End");

        return task;
    }

    /**
     * This module aids in fetching the forwarding information base data for a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectFibDataEntry}&gt; containing
     * the details for all found interconnect forwarding information base data.
     */
    public ResourceCollection<InterconnectFibDataEntry> getForwardingInformationBase(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getForwardingInformationBase : Start");

        ResourceCollection<InterconnectFibDataEntry> forwardingInfo = baseClient.getResourceCollection(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI),
                InterconnectFibDataEntry.class);

        LOGGER.info("LogicalInterconnectClient : getForwardingInformationBase : End");

        return forwardingInfo;
    }

    /**
     * This module aids in generating the forwarding information base dump file
     * for a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link InterconnectFibDataInfo} containing the interconnect forwarding
     *         information base data details.
     */
    public InterconnectFibDataInfo createForwardingInformationBase(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : createForwardingInformationBase : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI);
        Request request = new Request(HttpMethod.POST, uri);

        InterconnectFibDataInfo forwardingInfo = baseClient.executeRequest(request,
                InterconnectFibDataInfo.class);

        LOGGER.info("LogicalInterconnectClient : createForwardingInformationBase : End");

        return forwardingInfo;
    }

    /**
     * The module aids in fetching the logical interconnect firmware for the specified
     * logical interconnect identified by the resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link LiFirmware} containing the logical interconnect LI firmware details.
     */
    public LiFirmware getFirmware(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getFirmware : Start");

        LiFirmware firmware = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_FIRMWARE_URI),
                LiFirmware.class);

        LOGGER.info("LogicalInterconnectClient : getFirmware : End");

        return firmware;
    }

    /**
     * The method installs firmware to a logical interconnect. The three operations that
     * are supported for the firmware update are STAGE (uploads firmware to the
     * interconnect), ACTIVATE (installs firmware on the interconnect) and
     * UPDATE (which does a STAGE and ACTIVATE in a sequential manner).
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param liFirmware object containing the update to be made to the logical interconnect.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateFirmware(String resourceId, LiFirmware liFirmware, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateFirmware : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_FIRMWARE_URI);
        Request request = new Request(HttpMethod.PUT, uri, liFirmware);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateFirmware : End");

        return task;
    }

    /**
     * This method aids in fetching the QoS aggregated configuration for the logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link QosAggregatedConfiguration} containing the QoS configuration details.
     */
    public QosAggregatedConfiguration getQosAggregatedConfiguration(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getQosAggregatedConfiguration : Start");

        QosAggregatedConfiguration qosAggregatedConfiguration = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI),
                QosAggregatedConfiguration.class);

        LOGGER.info("LogicalInterconnectClient : getQosAggregatedConfiguration : End");

        return qosAggregatedConfiguration;
    }

    /**
     * This modules aids in updating the QoS aggregated configuration for the logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param qosAggregatedConfiguration object containing the update to be made to the
     * logical interconnect.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateQosAggregatedConfiguration(String resourceId,
            QosAggregatedConfiguration qosAggregatedConfiguration, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateQosAggregatedConfiguration : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI);
        Request request = new Request(HttpMethod.PUT, uri, qosAggregatedConfiguration);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateQosAggregatedConfiguration : End");

        return task;
    }

    /**
     * This module aids in fetching the telemetry configuration of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param telemetryConfigurationId telemetry configuration identifier for the logical
     * interconnect as seen in HPE OneView.
     *
     * @return {@link TelemetryConfiguration} containing the telemetry configuration details.
     */
    public TelemetryConfiguration getTelemetryConfiguration(String resourceId, String telemetryConfigurationId) {
        LOGGER.info("LogicalInterconnectClient : getTelemetryConfiguration : Start");

        TelemetryConfiguration telemetryConfiguration = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI, telemetryConfigurationId),
                TelemetryConfiguration.class);

        LOGGER.info("LogicalInterconnectClient : getTelemetryConfiguration : End");

        return telemetryConfiguration;
    }

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param telemetryConfigurationId telemetry configuration identifier for the logical
     * interconnect as seen in HPE OneView.
     * @param telemetryConfiguration object containing the update to be made to existing
     *            telemetry configuration for logical interconnect.
     *
     * @return {@link TelemetryConfiguration} containing the telemetry configuration details.
     *
     */
    public TelemetryConfiguration updateTelemetryConfiguration(String resourceId,
            String telemetryConfigurationId, TelemetryConfiguration telemetryConfiguration) {
        LOGGER.info("LogicalInterconnectClient : updateTelemetryConfiguration : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI, telemetryConfigurationId);
        Request request = new Request(HttpMethod.PUT, uri, telemetryConfiguration);

        TelemetryConfiguration telemetryConfigurationUpdated = baseClient.executeRequest(
                request, TelemetryConfiguration.class);

        LOGGER.info("LogicalInterconnectClient : updateTelemetryConfiguration : End");

        return telemetryConfigurationUpdated;
    }

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param telemetryConfigurationId telemetry configuration identifier for the logical
     * interconnect as seen in HPE OneView.
     * @param telemetryConfiguration object containing the update to be made to existing
     *            telemetry configuration for logical interconnect.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     *
     * @since HPE OneView 2.0
     */
    public TaskResourceV2 updateTelemetryConfigurationV200(String resourceId,
            String telemetryConfigurationId, TelemetryConfiguration telemetryConfiguration, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateTelemetryConfigurationV200 : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI, telemetryConfigurationId);
        Request request = new Request(HttpMethod.PUT, uri, telemetryConfiguration);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateTelemetryConfigurationV200 : End");

        return task;
    }

    /**
     * This module aids in fetching the port monitor configuration of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link PortMonitor} containing the port monitor details.
     */
    public PortMonitor getPortMonitorConfiguration(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getPortMonitorConfiguration : Start");

        PortMonitor portMonitorConfiguration = baseClient.getResource(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_PORT_MONITOR_URI),
                PortMonitor.class);

        LOGGER.info("LogicalInterconnectClient : getPortMonitorConfiguration : End");

        return portMonitorConfiguration;
    }

    /**
     * This module aids in updating the port monitor configuration of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param portMonitor port monitor fom the logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updatePortMonitorConfiguration(String resourceId, PortMonitor portMonitor, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updatePortMonitorConfiguration : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_PORT_MONITOR_URI);
        Request request = new Request(HttpMethod.PUT, uri, portMonitor);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updatePortMonitorConfiguration : End");

        return task;
    }

    /**
     * This module aids in asynchronously applying or re-applying the logical
     * interconnect configuration to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateConfiguration(String resourceId, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateConfiguration : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_CONFIGURATION_URI);
        Request request = new Request(HttpMethod.PUT, uri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateConfiguration : End");

        return task;
    }

    /**
     * This modules aids in updating interconnect settings on the logical interconnect.
     * Changes to interconnect settings are asynchronously applied to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param interconnectSettings interconnect settings for logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateSettings(String resourceId,
            InterconnectSettingsV2 interconnectSettings, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateSettings : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_SETTINGS_URI);
        Request request = new Request(HttpMethod.PUT, uri, interconnectSettings);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateSettings : End");

        return task;
    }

    /**
     * This method aids in updating the ethernet interconnect settings for the logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param ethernetSettings ethernet settings for logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateEthernetSettings(String resourceId,
            EthernetInterconnectSettingsV2 ethernetSettings, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateEthernetSettings : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI);
        Request request = new Request(HttpMethod.PUT, uri, ethernetSettings);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateEthernetSettings : End");

        return task;
    }

    /**
     * This modules aids in fetching a logical interconnect to a consistent state. The current logical
     * interconnect state is compared to the associated logical interconnect group. Any differences
     * identified are corrected, bringing the logical interconnect back to a consistent state.
     * Changes are asynchronously applied to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateCompliance(String resourceId, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateCompliance : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_COMPLIANCE_URI);
        Request request = new Request(HttpMethod.PUT, uri);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateCompliance : End");

        return task;
    }

    /**
     * This module aids in updating the internal networks of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param networkUris list containing the resource identifiers for logical interconnect
     * as seen in HPE OneView.
     * @param aSync flag to indicate whether the request should be processed
     * synchronously or asynchronously.
     *
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    public TaskResourceV2 updateInternalNetworks(String resourceId, List<String> networkUris, boolean aSync) {
        LOGGER.info("LogicalInterconnectClient : updateInternalNetworks : Start");

        String uri = UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                ResourceUris.LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI);
        Request request = new Request(HttpMethod.PUT, uri, networkUris);

        request.setTimeout(TIMEOUT);

        TaskResourceV2 task = baseClient.executeMonitorableRequest(request, aSync);

        LOGGER.info("LogicalInterconnectClient : updateInternalNetworks : End");

        return task;
    }

    /**
     * This module aids in fetching the internal VLAN identifiers for the provisioned
     * networks of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InternalVlanAssociation}&gt; containing
     * the details for all found internal VLAN associations.
     */
    public ResourceCollection<InternalVlanAssociation> getInternalVlans(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getInternalVlans : Start");

        ResourceCollection<InternalVlanAssociation> internalVlans = baseClient.getResourceCollection(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI),
                InternalVlanAssociation.class);

        LOGGER.info("LogicalInterconnectClient : getInternalVlans : End");

        return internalVlans;
    }

    /**
     * This module aids in fetching a collection of uplink ports from the member
     * interconnects which are eligible for assignment to an analyzer port.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link PortMonitorUplinkPort}&gt; containing
     * the details for all found uplink ports for port monitor.
     */
    public ResourceCollection<PortMonitorUplinkPort> getUnassignedUplinkPortsForPortMonitor(String resourceId) {
        LOGGER.info("LogicalInterconnectClient : getUnassignedUplinkPortsForPortMonitor : Start");

        ResourceCollection<PortMonitorUplinkPort> unassignedPorts = baseClient.getResourceCollection(
                UrlUtils.createUrl(ResourceUris.LOGICAL_INTERCONNECT_URI, resourceId,
                        ResourceUris.LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI),
                PortMonitorUplinkPort.class);

        LOGGER.info("LogicalInterconnectClient : getUnassignedUplinkPortsForPortMonitor : End");

        return unassignedPorts;
    }
}
