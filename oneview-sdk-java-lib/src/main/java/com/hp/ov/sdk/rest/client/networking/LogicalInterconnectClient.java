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

import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettings;
import com.hp.ov.sdk.dto.networking.InterconnectSettings;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.InternalVlanAssociation;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LiFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LogicalInterconnect;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PortMonitor;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.QosAggregatedConfiguration;
import com.hp.ov.sdk.rest.client.common.SearchableResource;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.RequestOption;
import com.hp.ov.sdk.rest.reflect.Api;
import com.hp.ov.sdk.rest.reflect.BodyParam;
import com.hp.ov.sdk.rest.reflect.Endpoint;
import com.hp.ov.sdk.rest.reflect.PathParam;

@Api(LogicalInterconnectClient.LOGICAL_INTERCONNECT_URI)
public interface LogicalInterconnectClient extends SearchableResource<LogicalInterconnect> {

    String LOGICAL_INTERCONNECT_URI = "/rest/logical-interconnects";
    String LOGICAL_INTERCONNECT_FIRMWARE_URI = "/firmware";
    String LOGICAL_INTERCONNECT_LOCATION_URI = "/locations";
    String LOGICAL_INTERCONNECT_INTERCONNECTS_URI = "/interconnects";
    String LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI = "/snmp-configuration";
    String LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI = "/forwarding-information-base";
    String LOGICAL_INTERCONNECT_CONFIGURATION_URI = "/configuration";
    String LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI = "/qos-aggregated-configuration";
    String LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI = "/telemetry-configurations";
    String LOGICAL_INTERCONNECT_PORT_MONITOR_URI = "/port-monitor";
    String LOGICAL_INTERCONNECT_SETTINGS_URI = "/settings";
    String LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI = "/ethernetSettings";
    String LOGICAL_INTERCONNECT_COMPLIANCE_URI = "/compliance";
    String LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI = "/internalNetworks";
    String LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI = "/internalVlans";
    String LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI = "/unassignedUplinkPortsForPortMonitor";

    /**
     * The module aids in creating an interconnect at the given location.
     *
     * @param location location for the logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = LOGICAL_INTERCONNECT_LOCATION_URI + LOGICAL_INTERCONNECT_INTERCONNECTS_URI, method = HttpMethod.POST)
    TaskResource createInterconnect(@BodyParam Location location, RequestOption... options);

    /**
     * The module aids in removing an interconnect from a location.
     *
     * @param enclosureUri enclosure URI as seen in HPE OneView.
     * @param bay bay of the interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options..
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = LOGICAL_INTERCONNECT_LOCATION_URI
            + LOGICAL_INTERCONNECT_INTERCONNECTS_URI
            + "?location=Enclosure:{enclosureUri},Bay:{bay}", method = HttpMethod.DELETE)
    TaskResource deleteInterconnect(@PathParam("enclosureUri") String enclosureUri,
            @PathParam("bay") String bay, RequestOption... options);

    /**
     * This module aids in fetching the SNMP configuration for a logical
     * interconnect that is specified by resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link SnmpConfiguration} containing the SNMP configuration details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI)
    SnmpConfiguration getSnmpConfiguration(@PathParam("resourceId") String resourceId);

    /**
     * This modules aids in updating the SNMP configuration of a logical interconnect. Changes to the
     * SNMP configuration are asynchronously applied to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param snmpConfiguration object containing the update to be made to the logical interconnect.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI, method = HttpMethod.PUT)
    TaskResource updateSnmpConfiguration(@PathParam("resourceId") String resourceId,
            @BodyParam SnmpConfiguration snmpConfiguration, RequestOption... options);

    /**
     * This module aids in fetching the forwarding information base data for a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InterconnectFibDataEntry}&gt; containing
     * the details for all found interconnect forwarding information base data.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI)
    ResourceCollection<InterconnectFibDataEntry> getForwardingInformationBase(@PathParam("resourceId") String resourceId);

    /**
     * This module aids in generating the forwarding information base dump file
     * for a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link InterconnectFibDataInfo} containing the interconnect forwarding
     *         information base data details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI, method = HttpMethod.POST)
    InterconnectFibDataInfo createForwardingInformationBase(@PathParam("resourceId") String resourceId);

    /**
     * The module aids in fetching the logical interconnect firmware for the specified
     * logical interconnect identified by the resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link LiFirmware} containing the logical interconnect LI firmware details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_FIRMWARE_URI)
    LiFirmware getFirmware(@PathParam("resourceId") String resourceId);

    /**
     * The method installs firmware to a logical interconnect. The three operations that
     * are supported for the firmware update are STAGE (uploads firmware to the
     * interconnect), ACTIVATE (installs firmware on the interconnect) and
     * UPDATE (which does a STAGE and ACTIVATE in a sequential manner).
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param liFirmware object containing the update to be made to the logical interconnect.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_FIRMWARE_URI, method = HttpMethod.PUT)
    TaskResource updateFirmware(@PathParam("resourceId") String resourceId, @BodyParam LiFirmware liFirmware, RequestOption... options);

    /**
     * This method aids in fetching the QoS aggregated configuration for the logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link QosAggregatedConfiguration} containing the QoS configuration details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI)
    QosAggregatedConfiguration getQosAggregatedConfiguration(@PathParam("resourceId") String resourceId);

    /**
     * This modules aids in updating the QoS aggregated configuration for the logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param qosAggregatedConfiguration object containing the update to be made to the
     * logical interconnect.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI, method = HttpMethod.PUT)
    TaskResource updateQosAggregatedConfiguration(@PathParam("resourceId") String resourceId,
            @BodyParam QosAggregatedConfiguration qosAggregatedConfiguration, RequestOption... options);

    /**
     * This module aids in fetching the telemetry configuration of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param telemetryConfigurationId telemetry configuration identifier for the logical
     * interconnect as seen in HPE OneView.
     *
     * @return {@link TelemetryConfiguration} containing the telemetry configuration details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI + "/{telemetryConfigurationId}")
    TelemetryConfiguration getTelemetryConfiguration(@PathParam("resourceId") String resourceId,
            @PathParam("telemetryConfigurationId") String telemetryConfigurationId);

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
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
            + "/{telemetryConfigurationId}", method = HttpMethod.PUT)
    TelemetryConfiguration updateTelemetryConfigurationV120(@PathParam("resourceId") String resourceId,
            @PathParam("telemetryConfigurationId") String telemetryConfigurationId,
            @BodyParam TelemetryConfiguration telemetryConfiguration);

    /**
     * This module aids in updating the telemetry configuration of a logical
     * interconnect specified by resource identifier.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param telemetryConfigurationId telemetry configuration identifier for the logical
     * interconnect as seen in HPE OneView.
     * @param telemetryConfiguration object containing the update to be made to existing
     *            telemetry configuration for logical interconnect.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     *
     * @since HPE OneView 2.0
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
            + "/{telemetryConfigurationId}", method = HttpMethod.PUT)
    TaskResource updateTelemetryConfiguration(@PathParam("resourceId") String resourceId,
            @PathParam("telemetryConfigurationId") String telemetryConfigurationId,
            @BodyParam TelemetryConfiguration telemetryConfiguration, RequestOption... options);

    /**
     * This module aids in fetching the port monitor configuration of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link PortMonitor} containing the port monitor details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_PORT_MONITOR_URI)
    PortMonitor getPortMonitorConfiguration(@PathParam("resourceId") String resourceId);

    /**
     * This module aids in updating the port monitor configuration of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param portMonitor port monitor fom the logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_PORT_MONITOR_URI, method = HttpMethod.PUT)
    TaskResource updatePortMonitorConfiguration(@PathParam("resourceId") String resourceId,
            @BodyParam PortMonitor portMonitor, RequestOption... options);

    /**
     * This module aids in asynchronously applying or re-applying the logical
     * interconnect configuration to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_CONFIGURATION_URI, method = HttpMethod.PUT)
    TaskResource updateConfiguration(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * This modules aids in updating interconnect settings on the logical interconnect.
     * Changes to interconnect settings are asynchronously applied to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param interconnectSettings interconnect settings for logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_SETTINGS_URI, method = HttpMethod.PUT)
    TaskResource updateSettings(@PathParam("resourceId") String resourceId,
            @BodyParam InterconnectSettings interconnectSettings, RequestOption... options);

    /**
     * This module aids in fetching the ethernet settings for a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link EthernetInterconnectSettingsV2} containing the logical interconnect
     * ethernet settings details.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI)
    EthernetInterconnectSettingsV2 getEthernetSettings(@PathParam("resourceId") String resourceId);

    /**
     * This method aids in updating the ethernet interconnect settings for the logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param ethernetSettings ethernet settings for logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI, method = HttpMethod.PUT)
    TaskResource updateEthernetSettings(@PathParam("resourceId") String resourceId,
            @BodyParam EthernetInterconnectSettings ethernetSettings, RequestOption... options);

    /**
     * This module aids in fetching a logical interconnect to a consistent state. The current logical
     * interconnect state is compared to the associated logical interconnect group. Any differences
     * identified are corrected, bringing the logical interconnect back to a consistent state.
     * Changes are asynchronously applied to all managed interconnects.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_COMPLIANCE_URI, method = HttpMethod.PUT)
    TaskResource updateCompliance(@PathParam("resourceId") String resourceId, RequestOption... options);

    /**
     * This module aids in updating the internal networks of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     * @param networkUris list containing the resource identifiers for logical interconnect
     * as seen in HPE OneView.
     * @param options <code>varargs</code> of {@link RequestOption}, which can be used to specify
     *                some request options.
     *
     * @return {@link TaskResource} containing the task status for the process.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI, method = HttpMethod.PUT)
    TaskResource updateInternalNetworks(@PathParam("resourceId") String resourceId,
            @BodyParam List<String> networkUris, RequestOption... options);

    /**
     * This module aids in fetching the internal VLAN identifiers for the provisioned
     * networks of a logical interconnect.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link InternalVlanAssociation}&gt; containing
     * the details for all found internal VLAN associations.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI)
    ResourceCollection<InternalVlanAssociation> getInternalVlans(@PathParam("resourceId") String resourceId);

    /**
     * This module aids in fetching a collection of uplink ports from the member
     * interconnects, which are eligible for assignment to an analyzer port.
     *
     * @param resourceId resource identifier for logical interconnect as seen in HPE OneView.
     *
     * @return {@link ResourceCollection}&lt;{@link PortMonitorUplinkPort}&gt; containing
     * the details for all found uplink ports for port monitor.
     */
    @Endpoint(uri = "/{resourceId}" + LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI)
    ResourceCollection<PortMonitorUplinkPort> getUnassignedUplinkPortsForPortMonitor(@PathParam("resourceId") String resourceId);
}
