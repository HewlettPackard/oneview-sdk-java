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

import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.InternalVlanAssociation;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Location;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.dto.generated.TelemetryConfiguration;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalInterconnectClient {

    /**
     * The module aids in fetching the logical interconnect details for the
     * specified logical interconnect resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link LogicalInterconnects} containing the logical interconnect details.
     */
    LogicalInterconnects getLogicalInterconnect(RestParams params, String resourceId);

    /**
     * The module aids in fetching the logical interconnect details for all
     * logical interconnects found under the current HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @return {@link ResourceCollection}&lt;{@link LogicalInterconnects}&gt; containing
     * the details for all found logical interconnects.
     */
    ResourceCollection<LogicalInterconnects> getAllLogicalInterconnects(final RestParams params);

    /**
     * This modules aids in fetching a logical interconnect to a consistent state. The current logical
     * interconnect state is compared to the associated logical interconnect
     * group. Any differences identified are corrected, bringing the logical
     * interconnect back to a consistent state. Changes are asynchronously
     * applied to all managed interconnects.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectComplianceById(final RestParams params, final String resourceId,
            final boolean asyncOrSyncMode);

    /**
     * This method aids in updating the ethernet interconnect settings for the logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param ethernetInterconnectSettingsDto
     *            The ethernet settings for logical interconnect as seen in HPE OneView.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateEthernetSettings(final RestParams params, String resourceId,
            final EthernetInterconnectSettingsV2 ethernetInterconnectSettingsDto,
            final boolean asyncOrSyncMode);

    /**
     * The module aids in creating an interconnect at the given location.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param locationDto
     *            The location for the logical interconnect as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnect object
     *            which takes in a String containing the update to be made,
     *            which is converted to LogicalInterconnect object using adaptor
     *            and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 createLogicalInterconnect(final RestParams params, final Location locationDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * The module aids in removing an interconnect from a location.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param enclosureUri
     *            The enclosure URI as seen in HPE OneView.
     * @param bay
     *            The bay of the interconnect as seen in HPE OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 deleteLogicalInterconnect(final RestParams params, final String enclosureUri,
            final String bay, final boolean aSync);

    /**
     * The method installs firmware to a logical interconnect. The three operations that
     * are supported for the firmware update are STAGE (uploads firmware to the
     * interconnect), ACTIVATE (installs firmware on the interconnect) and
     * UPDATE (which does a STAGE and ACTIVATE in a sequential manner).
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param lIFirmwareDto
     *            This is a LiFirmware object containing the update to be made
     *            to existing logical interconnect pointed to by the above
     *            mentioned resource identifier.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LiFirmware object
     *            which takes in a String containing the update to be made,
     *            which is converted to LiFirmware object using adaptor
     *            and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectFirmwareById(final RestParams params, String resourceId,
            final LiFirmware lIFirmwareDto, final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     * This modules aids in updating the SNMP configuration of a logical interconnect. Changes to the
     * SNMP configuration are asynchronously applied to all managed interconnects.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param snmpConfigurationDto
     *            This is a {@link SnmpConfiguration} object containing the update to be
     *            made to existing logical interconnect pointed to by the above
     *            mentioned resource identifier.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of SnmpConfiguration object
     *            which takes in a String containing the update to be made,
     *            which is converted to SnmpConfiguration object using adaptor
     *            and processed.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectSnmpConfigurationById(final RestParams params, String resourceId,
            final SnmpConfiguration snmpConfigurationDto, final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     * The module aids in fetching the logical interconnect details for the specified
     * logical interconnect name.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param logicalInterconnectName
     *            The logicalInterconnectName is the logical interconnect name as
     *            seen in HPE OneView.
     * @return {@link LogicalInterconnects} containing the logical interconnect details.
     */
    LogicalInterconnects getLogicalInterconnectByName(RestParams params, String logicalInterconnectName);

    /**
     * The module aids in fetching the logical interconnect firmware for the specified
     * logical interconnect identified by the resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link LiFirmware} containing the LI firmware details.
     */
    LiFirmware getLogicalInterconnectFirmwareById(RestParams params, String resourceId);

    /**
     * This module aids in fetching the forwarding information base data for a
     * logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link ResourceCollection}&lt;{@link InterconnectFibDataEntry}&gt; containing
     * the details for all found interconnect forwarding information base data.
     */
    ResourceCollection<InterconnectFibDataEntry> getLogicalInterconnectForwardingInformationBase(final RestParams params, String resourceId);

    /**
     * This module aids in generating the forwarding information base dump file
     * for a logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link InterconnectFibDataInfo} containing the interconnect forwarding
     *         information base data details.
     */
    InterconnectFibDataInfo createLogicalInterconnectForwardingInformationBase(final RestParams params, String resourceId);

    /**
     * This module aids in updating the internal networks of a logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param networkUris
     *            A list containing the resource identifiers for logical interconnect
     *            as seen in HPE OneView.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectInternalNetworks(final RestParams params, final String resourceId, final List<String> networkUris);

    /**
     * This module aids in fetching the internal VLAN identifiers for the provisioned networks of a logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link ResourceCollection}&lt;{@link InternalVlanAssociation}&gt; containing
     * the details for all found internal VLAN associations.
     */
    ResourceCollection<InternalVlanAssociation> getLogicalInterconnectInternalVlans(final RestParams params, String resourceId);

    /**
     * This method aids in fetching the QoS aggregated configuration for the logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link QosAggregatedConfiguration} containing the QoS configuration details.
     */
    QosAggregatedConfiguration getLogicalInterconnectQosAggregatedConfiguration(final RestParams params, String resourceId);

    /**
     * This modules aids in updating the QoS aggregated configuration for the logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param qosAggregatedConfigurationDto
     *            The QoS configuration for logical interconnect as seen in HPE OneView.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectQosAggregatedConfiguration(final RestParams params,
            String resourceId, final QosAggregatedConfiguration qosAggregatedConfigurationDto);

    /**
     * This modules aids in updating interconnect settings on the logical interconnect.
     * Changes to interconnect settings are asynchronously applied to all managed interconnects.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param interconnectSettings
     *            The interconnect settings for logical interconnect as seen in HPE OneView.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectSettings(final RestParams params,
            String resourceId, final InterconnectSettingsV2 interconnectSettings);

    /**
     * This module aids in fetching the SNMP configuration for a logical
     * interconnect that is specified by resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link SnmpConfiguration} containing the SNMP configuration details.
     */
    SnmpConfiguration getLogicalInterconnectSnmpConfigurationById(final RestParams params, String resourceId);

    /**
     * This module aids in fetching a collection of uplink ports from the member
     * interconnects which are eligible for assignment to an analyzer port.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link ResourceCollection}&lt;{@link PortMonitorUplinkPort}&gt; containing
     * the details for all found uplink ports for port monitor.
     */
    ResourceCollection<PortMonitorUplinkPort> getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(final RestParams params,
            final String resourceId);

    /**
     * This module aids in asynchronously applying or re-applying the logical
     * interconnect configuration to all managed interconnects.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the port monitor configuration of a logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @return {@link PortMonitor} containing the port monitor details.
     */
    PortMonitor getLogicalInterconnectPortMonitorConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in updating the port monitor configuration of a logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param portMonitorDto
     *            The port monitor fom the logical interconnect as seen in HPE OneView.
     * @return {@link TaskResourceV2} containing the task status for the process.
     */
    TaskResourceV2 updateLogicalInterconnectPortMonitorConfiguration(final RestParams params, final String resourceId, PortMonitor portMonitorDto);

    /**
     * This module aids in fetching the telemetry configuration of a logical interconnect.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param telemetryConfigurationId
     *            The telemetry configuration identifier for the logical interconnect as seen in HPE OneView.
     * @return {@link TelemetryConfiguration} containing the telemetry configuration details.
     */
    TelemetryConfiguration getLogicalInterconnectTelemetryConfiguration(final RestParams params, final String resourceId,
            final String telemetryConfigurationId);

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param TelemetryConfigurationId
     *            The telemetry configuration identifier that specifies the telemetry identifier
     *            for the resource specified by resource identifier.
     * @param telemetryConfiguration
     *            Object containing the update to be made to existing
     *            telemetry configuration for logical interconnect pointed to
     *            by the above mentioned telemetry configuration identifier and resource identifier.
     * @return {@link TelemetryConfiguration} containing the telemetry configuration details.
     *
     * @since HPE OneView 2.0
     */
    TelemetryConfiguration updateLogicalInterconnectTelemetryConfiguration(final RestParams params,
            final String resourceId, final String TelemetryConfigurationId, final TelemetryConfiguration telemetryConfiguration);

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resource identifier.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param resourceId
     *            The resource identifier for logical interconnect as seen in HPE OneView.
     * @param TelemetryConfigurationId
     *            The telemetry configuration identifier that specifies the telemetry
     *            identifier for the resource specified by resource identifier.
     * @param telemetryConfiguration
     *            Object containing the update to be made to existing
     *            telemetry configuration for logical interconnect pointed to
     *            by the above mentioned telemetry configuration identifier and resource identifier.
     * @return {@link TaskResourceV2} containing the task status for the process.
     *
     * @since HPE OneView 2.0
     */
    TaskResourceV2 updateLogicalInterconnectTelemetryConfigurationV200(final RestParams params,
            final String resourceId, final String TelemetryConfigurationId, final TelemetryConfiguration telemetryConfiguration);

    /**
     * The module aids in fetching the logical interconnect resource identifier for the
     * logical interconnect name as specified in HPE OneView.
     *
     * @param params
     *            The {@link RestParams} is a structure containing the connection details.
     * @param name
     *            The name is the logical interconnect name as seen in HPE OneView.
     * @return String which is the resource identifier for the logical interconnect name
     *         as seen in HPE OneView.
     */
    String getId(final RestParams params, final String name);
}
