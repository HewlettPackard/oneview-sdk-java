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

import com.hp.ov.sdk.dto.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.InterconnectFibData;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.InternalVlanAssociationCollection;
import com.hp.ov.sdk.dto.LiFirmware;
import com.hp.ov.sdk.dto.LogicalInterconnectCollectionV2;
import com.hp.ov.sdk.dto.PortMonitor;
import com.hp.ov.sdk.dto.PortMonitorUplinkPortCollection;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.Location;
import com.hp.ov.sdk.dto.generated.LogicalInterconnects;
import com.hp.ov.sdk.dto.generated.SnmpConfiguration;
import com.hp.ov.sdk.dto.generated.TelemetryConfiguration;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public interface LogicalInterconnectClient {

    /**
     * The module aids in fetching the LogicalInterconnects details for the
     * specified LogicalInterconnects resourceId
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return logicalInterconnectsDto, which is a object containing the
     *         LogicalInterconnects details.
     */
    public LogicalInterconnects getLogicalInterconnect(RestParams params, String resourceId);

    /**
     * The module aids in fetching the LogicalInterconnects details for all
     * LogicalInterconnects found under current HP OneView.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @return logicalInterconnectsCollectionDto, which is a object containing a
     *         collection of LogicalInterconnects details.
     */
    public LogicalInterconnectCollectionV2 getAllLogicalInterconnects(final RestParams params);

    /**
     * Returns a logical interconnect to a consistent state. The current logical
     * interconnect state is compared to the associated logical interconnect
     * group. Any differences identified are corrected, bringing the logical
     * interconnect back to a consistent state. Changes are asynchronously
     * applied to all managed interconnects.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectComplianceById(final RestParams params, final String resourceId,
            final boolean asyncOrSyncMode);

    /**
     * Updates the Ethernet interconnect settings for the logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param ethernetInterconnectSettingsDto
     *            The Ethernet settings for LogicalInterconnect as seen in HP OneView.
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateEthernetSettings(final RestParams params, String resourceId,
            final EthernetInterconnectSettingsV2 ethernetInterconnectSettingsDto,
            final boolean asyncOrSyncMode);

    /**
     * Creates an interconnect at the given location.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param locationDto
     *            The location for the LogicalInterconnect as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnect Object
     *            which takes in a String containing the update to be made,
     *            which is converted to LogicalInterconnect Object using adaptor
     *            and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 createLogicalInterconnect(final RestParams params, final Location locationDto, final boolean aSync,
            final boolean useJsonRequest);

    /**
     * Deletes an interconnect from a location.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param enclosureUri
     *            The enclosure URI as seen in HP OneView.
     * @param bay
     *            The the bay as seen in HP OneView.
     * @param aSync
     *            Flag input to process request asynchronously or synchronously.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 deleteLogicalInterconnect(final RestParams params, final String enclosureUri,
            final String bay, final boolean aSync);

    /**
     * Installs firmware to a logical interconnect. The three operations that
     * are supported for the firmware update are STAGE (uploads firmware to the
     * interconnect), ACTIVATE (installs firmware on the interconnect) and
     * UPDATE (which does a STAGE and ACTIVATE in a sequential manner).
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param lIFirmwareDto
     *            This is a lIFirmware object containing the update to be made
     *            to existing LogicalInterconnect pointed to by the above
     *            mentioned resourceId
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnect Object
     *            which takes in a String containing the update to be made,
     *            which is converted to LogicalInterconnect Object using adaptor
     *            and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectFirmwareById(final RestParams params, String resourceId,
            final LiFirmware lIFirmwareDto, final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     * Updates the SNMP configuration of a logical interconnect. Changes to the
     * SNMP configuration are asynchronously applied to all managed
     * interconnects.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param snmpConfigurationDto
     *            This is a snmpConfiguration object containing the update to be
     *            made to existing LogicalInterconnect pointed to by the above
     *            mentioned resourceId
     * @param asyncOrSyncMode
     *            Flag input to process request asynchronously or synchronously.
     * @param useJsonRequest
     *            The JsonRequest body is part of LogicalInterconnect Object
     *            which takes in a String containing the update to be made,
     *            which is converted to LogicalInterconnect Object using adaptor
     *            and processed.
     * @return taskResource which returns the task status for the process
     */
    public TaskResourceV2 updateLogicalInterconnectSnmpConfigurationById(final RestParams params, String resourceId,
            final SnmpConfiguration snmpConfigurationDto, final boolean asyncOrSyncMode, final boolean useJsonRequest);

    /**
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param logicalInterconnectName
     *            The logicalInterconnectName is the LogicalInterconnect name as
     *            seen in HP OneView.
     * @return LogicalInterconnectDto, which is a object containing the
     *         LogicalInterconnect Details.
     */
    public LogicalInterconnects getLogicalInterconnectByName(RestParams params, String logicalInterconnectName);

    /**
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return LiFirmwareDto, which is a object containing the LiFirmware
     *         details.
     */
    public LiFirmware getLogicalInterconnectFirmwareById(RestParams params, String resourceId);

    /**
     * This module aids in fetching the forwarding information base data for a
     * logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return interconnectFibData, which is a object containing the
     *         InterconnectFibData details.
     */
    public InterconnectFibData getLogicalInterconnectForwardingInformationBase(final RestParams params, String resourceId);

    /**
     * This module aids in generating the forwarding information base dump file
     * for a logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return interconnectFibDataInfo, which is a object containing the
     *         InterconnectFibDataInfo details.
     */
    public InterconnectFibDataInfo createLogicalInterconnectForwardingInformationBase(final RestParams params, String resourceId);

    /**
     * Updates internal networks on the logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param networkUris
     *            A list containing the resourceIds for LogicalInterconnect as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectInternalNetworks(final RestParams params, final String resourceId, final List<String> networkUris);

    /**
     * Gets the internal VLAN IDs for the provisioned networks on a logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return InternalVlanAssociationCollection, which is a object containing the
     *         InternalVlanAssociationCollection details.
     */
    public InternalVlanAssociationCollection getLogicalInterconnectInternalVlans(final RestParams params, String resourceId);

    /**
     * Gets the QoS aggregated configuration for the logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return QosAggregatedConfiguration, which is a object containing the
     *         QoS configuration details.
     */
    public QosAggregatedConfiguration getLogicalInterconnectQosAggregatedConfiguration(final RestParams params, String resourceId);

    /**
     * Updates the QoS aggregated configuration for the logical interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param qosAggregatedConfigurationDto
     *            The QoS configuration for LogicalInterconnect as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectQosAggregatedConfiguration(final RestParams params,
            String resourceId, final QosAggregatedConfiguration qosAggregatedConfigurationDto);

    /**
     * Updates interconnect settings on the logical interconnect.
     * Changes to interconnect settings are asynchronously applied to all managed interconnects.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param interconnectSettings
     *            The interconnect settings for LogicalInterconnect as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectSettings(final RestParams params,
            String resourceId, final InterconnectSettingsV2 interconnectSettings);

    /**
     * This module aids in fetching the SNMP configuration for a logical
     * interconnect that is specified by resourceId.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return snmpConfiguration, which is a object containing the
     *         snmpConfiguration details.
     */
    public SnmpConfiguration getLogicalInterconnectSnmpConfigurationById(final RestParams params, String resourceId);

    /**
     * THis module aids in fetching a collection of uplink ports from the member
     * interconnects which are eligible for assignment to an analyzer port.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return portMonitorUplinkPortCollection, which is a object containing the
     *         portMonitorUplinkPortCollection details.
     */
    public PortMonitorUplinkPortCollection getLogicalInterconnectUnassignedUplinkPortsForPortMonitor(final RestParams params,
            final String resourceId);

    /**
     * This module aids in asynchronously applying or re-applying the logical
     * interconnect configuration to all managed interconnects.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in fetching the port monitor configuration of a logical
     * interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @return portMonitor, which is a object containing the portMonitor
     *         details.
     */
    public PortMonitor getLogicalInterconnectPortMonitorConfiguration(final RestParams params, final String resourceId);

    /**
     * This module aids in updating the port monitor configuration of a logical
     * interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param portMonitorDto
     *            The port monitor fom the LogicalInterconnects as seen in HP OneView.
     * @return taskResource
     */
    public TaskResourceV2 updateLogicalInterconnectPortMonitorConfiguration(final RestParams params, final String resourceId, PortMonitor portMonitorDto);

    /**
     * This module aids in fetching the telemetry configuration of a logical
     * interconnect.
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param telemetryConfigurationId
     *            The telemetry configuration ID for LogicalInterconnects as seen in HP OneView.
     * @return telemetryConfiguration, which is a object containing the
     *         telemetryConfiguration details.
     */
    public TelemetryConfiguration getLogicalInterconnectTelemetryConfiguration(final RestParams params, final String resourceId,
            final String telemetryConfigurationId);

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resourceId
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param TelemetryConfigurationId
     *            The TelemetryConfigurationId that specifies the Telemetry
     *            Id for the resource specified by resourceId
     * @param telemetryConfiguration
     *            which is a object containing the update to be made to existing
     *            Telemetry configuration for LogicalInterconnect pointed to
     *            by the above mentioned TelemetryConfigurationId specified
     *            for resourceId.
     * @return telemetryConfiguration, which is a object containing the
     *         telemetryConfiguration details.
     */
    public TelemetryConfiguration updateLogicalInterconnectTelemetryConfiguration(final RestParams params,
            final String resourceId, final String TelemetryConfigurationId, final TelemetryConfiguration telemetryConfiguration);

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resourceId on OneView 2.0
     *
     * @param params
     *            The RestParams is a structure containing the connection
     *            details.
     * @param resourceId
     *            The resourceId for LogicalInterconnects as seen in HP OneView.
     * @param TelemetryConfigurationId
     *            The TelemetryConfigurationId that specifies the Telemetry
     *            Id for the resource specified by resourceId
     * @param telemetryConfiguration
     *            which is a object containing the update to be made to existing
     *            Telemetry configuration for LogicalInterconnect pointed to
     *            by the above mentioned TelemetryConfigurationId specified
     *            for resourceId.
     * @return TaskResourceV2
     */
    public TaskResourceV2 updateLogicalInterconnectTelemetryConfigurationV200(final RestParams params,
            final String resourceId, final String TelemetryConfigurationId, final TelemetryConfiguration telemetryConfiguration);

    /**
     * The module aids in fetching the LogicalInterconnects details for the
     * LogicalInterconnects name as specified in HP OneView.
     *
     * @param creds
     *            The RestParams is a structure containing the connection
     *            details.
     * @param name
     *            The resourceName is the LogicalInterconnects name as seen in
     *            HP OneView.
     * @return String, which is a resource Id for the LogicalInterconnects name
     *         as seen in HPOneView.
     */
    public String getId(final RestParams creds, final String name);
}
