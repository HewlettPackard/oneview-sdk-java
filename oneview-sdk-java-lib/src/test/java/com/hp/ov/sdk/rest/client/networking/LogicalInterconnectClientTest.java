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

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.hp.ov.sdk.constants.ResourceUris;
import com.hp.ov.sdk.dto.HttpMethodType;
import com.hp.ov.sdk.dto.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InternalVlanAssociation;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.ResourceCollection;
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

@RunWith(MockitoJUnitRunner.class)
public class LogicalInterconnectClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_TELEMETRY_ID = "random-Telemetry-UIID";
    private static final String ANY_ENCLOSURE_URI = "random-Enclosure-URI";
    private static final String ANY_BAY = "random-Bay";

    @Mock
    private BaseClient baseClient;

    @InjectMocks
    private LogicalInterconnectClient client;

    @Test
    public void shouldGetLogicalInterconnectById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;

        then(baseClient).should().getResource(expectedUri, LogicalInterconnect.class);
    }

    @Test
    public void shouldGetAllLogicalInterconnects() {
        client.getAll();

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_INTERCONNECT_URI,
                LogicalInterconnect.class);
    }

    @Test
    public void shouldGetLogicalInterconnectByName() {
        given(this.baseClient.getResourceCollection(anyString(), eq(LogicalInterconnect.class)))
                .willReturn(new ResourceCollection<LogicalInterconnect>());

        client.getByName(ANY_RESOURCE_NAME);

        then(baseClient).should().getResourceCollection(ResourceUris.LOGICAL_INTERCONNECT_URI,
                LogicalInterconnect.class);
    }

    @Test
    public void shouldCreateInterconnect() {
        Location location = new Location();

        client.createInterconnect(location, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_LOCATION_URI
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_INTERCONNECTS_URI;
        Request expectedRequest = new Request(HttpMethodType.POST, expectedUri, location);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldDeleteInterconnect() {
        client.deleteInterconnect(ANY_ENCLOSURE_URI, ANY_BAY, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_LOCATION_URI
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_INTERCONNECTS_URI;
        Request expectedRequest = new Request(HttpMethodType.DELETE, expectedUri);

        expectedRequest.setTimeout(300000);
        expectedRequest.addQuery(new UrlParameter("location",
                "Enclosure:" + ANY_ENCLOSURE_URI + ",Bay:" + ANY_BAY));

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetLogicalInterconnectSnmpConfiguration() {
        client.getSnmpConfiguration(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI;

        then(baseClient).should().getResource(expectedUri, SnmpConfiguration.class);
    }

    @Test
    public void shouldUpdateLogicalInterconnectSnmpConfiguration() {
        SnmpConfiguration snmpConfiguration = new SnmpConfiguration();

        client.updateSnmpConfiguration(ANY_RESOURCE_ID, snmpConfiguration, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, snmpConfiguration);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetLogicalInterconnectForwardingInformationBase() {
        client.getForwardingInformationBase(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI;

        then(baseClient).should().getResourceCollection(expectedUri, InterconnectFibDataEntry.class);
    }

    @Test
    public void shouldCreateLogicalInterconnectForwardingInformationBase() {
        client.createForwardingInformationBase(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI;
        Request expectedRequest = new Request(HttpMethodType.POST, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, InterconnectFibDataInfo.class);
    }

    @Test
    public void shouldGetLogicalInterconnectFirmware() {
        client.getFirmware(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_FIRMWARE_URI;

        then(baseClient).should().getResource(expectedUri, LiFirmware.class);
    }

    @Test
    public void shouldUpdateLogicalInterconnectFirmware() {
        LiFirmware liFirmware = new LiFirmware();

        client.updateFirmware(ANY_RESOURCE_ID, liFirmware, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_FIRMWARE_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, liFirmware);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetLogicalInterconnectQosAggregatedConfiguration() {
        client.getQosAggregatedConfiguration(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI;

        then(baseClient).should().getResource(expectedUri, QosAggregatedConfiguration.class);
    }

    @Test
    public void shouldUpdateLogicalInterconnectQosAggregatedConfiguration() {
        QosAggregatedConfiguration configuration = new QosAggregatedConfiguration();

        client.updateQosAggregatedConfiguration(ANY_RESOURCE_ID, configuration, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, configuration);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetLogicalInterconnectTelemetryConfiguration() {
        client.getTelemetryConfiguration(ANY_RESOURCE_ID, ANY_TELEMETRY_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
                + "/" + ANY_TELEMETRY_ID;

        then(baseClient).should().getResource(expectedUri, TelemetryConfiguration.class);
    }

    @Test
    public void shouldUpdateLogicalInterconnectTelemetryConfiguration() {
        TelemetryConfiguration configuration = new TelemetryConfiguration();

        client.updateTelemetryConfiguration(ANY_RESOURCE_ID, ANY_TELEMETRY_ID, configuration);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
                + "/" + ANY_TELEMETRY_ID;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, configuration);

        then(baseClient).should().executeRequest(expectedRequest, TelemetryConfiguration.class);
    }

    @Test
    public void shouldUpdateLogicalInterconnectTelemetryConfigurationV200() {
        TelemetryConfiguration configuration = new TelemetryConfiguration();

        client.updateTelemetryConfigurationV200(ANY_RESOURCE_ID, ANY_TELEMETRY_ID, configuration, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
                + "/" + ANY_TELEMETRY_ID;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, configuration);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetLogicalInterconnectPortMonitorConfiguration() {
        client.getPortMonitorConfiguration(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_PORT_MONITOR_URI;

        then(baseClient).should().getResource(expectedUri, PortMonitor.class);
    }

    @Test
    public void shouldUpdateLogicalInterconnectPortMonitorConfiguration() {
        PortMonitor portMonitor = new PortMonitor();

        client.updatePortMonitorConfiguration(ANY_RESOURCE_ID, portMonitor, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_PORT_MONITOR_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, portMonitor);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateLogicalInterconnectConfiguration() {
        client.updateConfiguration(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateLogicalInterconnectSettings() {
        InterconnectSettingsV2 settings = new InterconnectSettingsV2();

        client.updateSettings(ANY_RESOURCE_ID, settings, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_SETTINGS_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, settings);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateLogicalInterconnectEthernetSettings() {
        EthernetInterconnectSettingsV2 settings = new EthernetInterconnectSettingsV2();

        client.updateEthernetSettings(ANY_RESOURCE_ID, settings, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, settings);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateLogicalInterconnectCompliance() {
        client.updateCompliance(ANY_RESOURCE_ID, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_COMPLIANCE_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldUpdateLogicalInterconnectInternalNetworks() {
        List<String> networkUris = new ArrayList<>();

        client.updateInternalNetworks(ANY_RESOURCE_ID, networkUris, false);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI;
        Request expectedRequest = new Request(HttpMethodType.PUT, expectedUri, networkUris);

        expectedRequest.setTimeout(300000);

        then(baseClient).should().executeMonitorableRequest(expectedRequest, false);
    }

    @Test
    public void shouldGetLogicalInterconnectInternalVlans() {
        client.getInternalVlans(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI;

        then(baseClient).should().getResourceCollection(expectedUri, InternalVlanAssociation.class);
    }

    @Test
    public void shouldGetLogicalInterconnectUnassignedUplinkPortsForPortMonitor() {
        client.getUnassignedUplinkPortsForPortMonitor(ANY_RESOURCE_ID);

        String expectedUri = ResourceUris.LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + "/" + ResourceUris.LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI;

        then(baseClient).should().getResourceCollection(expectedUri, PortMonitorUplinkPort.class);
    }

}
