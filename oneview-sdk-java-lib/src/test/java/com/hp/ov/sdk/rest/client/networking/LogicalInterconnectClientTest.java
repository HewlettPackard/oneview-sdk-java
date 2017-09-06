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

import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_COMPLIANCE_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_FIRMWARE_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_INTERCONNECTS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_LOCATION_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_PORT_MONITOR_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_SETTINGS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI;
import static com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient.LOGICAL_INTERCONNECT_URI;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.ResourceCollection;
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
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.rest.client.BaseClient;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.http.core.HttpMethod;
import com.hp.ov.sdk.rest.http.core.client.Request;
import com.hp.ov.sdk.rest.http.core.client.TaskTimeout;
import com.hp.ov.sdk.rest.reflect.ClientRequestHandler;
import com.hp.ov.sdk.util.ResourceDtoUtils;

@RunWith(MockitoJUnitRunner.class)
public class LogicalInterconnectClientTest {

    private static final String ANY_RESOURCE_ID = "random-UUID";
    private static final String ANY_RESOURCE_NAME = "random-Name";
    private static final String ANY_TELEMETRY_ID = "random-Telemetry-UIID";
    private static final String ANY_ENCLOSURE_URI = "random-Enclosure-URI";
    private static final String ANY_BAY = "random-Bay";

    private BaseClient baseClient = mock(BaseClient.class);
    private LogicalInterconnectClient client = Reflection.newProxy(LogicalInterconnectClient.class,
            new ClientRequestHandler<>(baseClient, LogicalInterconnectClient.class));
    private LogicalInterconnectClient logicalInterconnectClient = mock(LogicalInterconnectClient.class);
    private ResourceCollection<LogicalInterconnect> resourceCollection = new ResourceCollection<LogicalInterconnect>();
    private OneViewClient oneViewClient = mock(OneViewClient.class);
    private ResourceDtoUtils resourceDtoUtils = new ResourceDtoUtils(oneViewClient);

    @Test
    public void shouldGetLogicalInterconnectById() {
        client.getById(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI + "/" + ANY_RESOURCE_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LogicalInterconnect.class).getType());
    }

    @Test
    public void shouldGetAllLogicalInterconnects() {
        given(this.baseClient.executeRequest(any(Request.class), any(Type.class))).willReturn(new ResourceCollection<>());

        client.getAll();

        Request expectedRequest = new Request(HttpMethod.GET, LOGICAL_INTERCONNECT_URI);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<LogicalInterconnect>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalInterconnectByName() {
        LogicalInterconnect logicalInterconnect = new LogicalInterconnect();
        logicalInterconnect.setName(ANY_RESOURCE_NAME);
        resourceCollection.addMembers(Lists.newArrayList(logicalInterconnect));
        
        given(logicalInterconnectClient.getAll()).willReturn(resourceCollection);

        given(oneViewClient.logicalInterconnect()).willReturn(logicalInterconnectClient);
        
        LogicalInterconnect logicalInterconnectByName = resourceDtoUtils.getLogicalInterconnectByName(ANY_RESOURCE_NAME);
        
        Assert.assertNotNull(logicalInterconnectByName);
    }
    
    @Test(expected=SDKResourceNotFoundException.class)
    public void shouldThrowExceptionWhenTryingToGetLogicalInterconnectByNameWhenElementIsNotFound() {
        given(logicalInterconnectClient.getAll()).willReturn(resourceCollection);

        given(oneViewClient.logicalInterconnect()).willReturn(logicalInterconnectClient);
        
        resourceDtoUtils.getLogicalInterconnectByName(ANY_RESOURCE_NAME);
    }

    @Test
    public void shouldCreateInterconnect() {
        Location location = new Location();

        client.createInterconnect(location, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + LOGICAL_INTERCONNECT_LOCATION_URI
                + LOGICAL_INTERCONNECT_INTERCONNECTS_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri, location);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldDeleteInterconnect() {
        client.deleteInterconnect(ANY_ENCLOSURE_URI, ANY_BAY, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + LOGICAL_INTERCONNECT_LOCATION_URI
                + LOGICAL_INTERCONNECT_INTERCONNECTS_URI
                + "?location=Enclosure:" + ANY_ENCLOSURE_URI
                + ",Bay:" + ANY_BAY;
        Request expectedRequest = new Request(HttpMethod.DELETE, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetLogicalInterconnectSnmpConfiguration() {
        client.getSnmpConfiguration(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(SnmpConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateLogicalInterconnectSnmpConfiguration() {
        SnmpConfiguration snmpConfiguration = new SnmpConfiguration();

        client.updateSnmpConfiguration(ANY_RESOURCE_ID, snmpConfiguration, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_SNMP_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, snmpConfiguration);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetLogicalInterconnectForwardingInformationBase() {
        client.getForwardingInformationBase(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<InterconnectFibDataEntry>>() {}.getType());
    }

    @Test
    public void shouldCreateLogicalInterconnectForwardingInformationBase() {
        client.createForwardingInformationBase(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_FORWARDING_INFORMATION_BASE_URI;
        Request expectedRequest = new Request(HttpMethod.POST, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(InterconnectFibDataInfo.class).getType());
    }

    @Test
    public void shouldGetLogicalInterconnectFirmware() {
        client.getFirmware(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_FIRMWARE_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(LiFirmware.class).getType());
    }

    @Test
    public void shouldUpdateLogicalInterconnectFirmware() {
        LiFirmware liFirmware = new LiFirmware();

        client.updateFirmware(ANY_RESOURCE_ID, liFirmware, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_FIRMWARE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, liFirmware);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetLogicalInterconnectQosAggregatedConfiguration() {
        client.getQosAggregatedConfiguration(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(QosAggregatedConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateLogicalInterconnectQosAggregatedConfiguration() {
        QosAggregatedConfiguration configuration = new QosAggregatedConfiguration();

        client.updateQosAggregatedConfiguration(ANY_RESOURCE_ID, configuration, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_QOS_AGGREGATED_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, configuration);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetLogicalInterconnectTelemetryConfiguration() {
        client.getTelemetryConfiguration(ANY_RESOURCE_ID, ANY_TELEMETRY_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
                + "/" + ANY_TELEMETRY_ID;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(TelemetryConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateLogicalInterconnectTelemetryConfigurationV120() {
        TelemetryConfiguration configuration = new TelemetryConfiguration();

        client.updateTelemetryConfigurationV120(ANY_RESOURCE_ID, ANY_TELEMETRY_ID, configuration);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
                + "/" + ANY_TELEMETRY_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, configuration);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(TelemetryConfiguration.class).getType());
    }

    @Test
    public void shouldUpdateLogicalInterconnectTelemetryConfiguration() {
        TelemetryConfiguration configuration = new TelemetryConfiguration();

        client.updateTelemetryConfiguration(ANY_RESOURCE_ID, ANY_TELEMETRY_ID, configuration, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_TELEMETRY_CONFIGURATION_URI
                + "/" + ANY_TELEMETRY_ID;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, configuration);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetLogicalInterconnectPortMonitorConfiguration() {
        client.getPortMonitorConfiguration(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_PORT_MONITOR_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest, TypeToken.of(PortMonitor.class).getType());
    }

    @Test
    public void shouldUpdateLogicalInterconnectPortMonitorConfiguration() {
        PortMonitor portMonitor = new PortMonitor();

        client.updatePortMonitorConfiguration(ANY_RESOURCE_ID, portMonitor, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_PORT_MONITOR_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, portMonitor);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalInterconnectConfiguration() {
        client.updateConfiguration(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_CONFIGURATION_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalInterconnectSettings() {
        InterconnectSettings settings = new InterconnectSettings();

        client.updateSettings(ANY_RESOURCE_ID, settings, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_SETTINGS_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, settings);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalInterconnectEthernetSettings() {
        EthernetInterconnectSettings settings = new EthernetInterconnectSettings();

        client.updateEthernetSettings(ANY_RESOURCE_ID, settings, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_ETHERNET_SETTINGS_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, settings);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalInterconnectCompliance() {
        client.updateCompliance(ANY_RESOURCE_ID, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_COMPLIANCE_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldUpdateLogicalInterconnectInternalNetworks() {
        List<String> networkUris = new ArrayList<>();

        client.updateInternalNetworks(ANY_RESOURCE_ID, networkUris, TaskTimeout.of(321));

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_INTERNAL_NETWORKS_URI;
        Request expectedRequest = new Request(HttpMethod.PUT, expectedUri, networkUris);
        expectedRequest.setTimeout(321);

        then(baseClient).should().executeMonitorableRequest(expectedRequest);
    }

    @Test
    public void shouldGetLogicalInterconnectInternalVlans() {
        client.getInternalVlans(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_INTERNAL_VLANS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<InternalVlanAssociation>>() {}.getType());
    }

    @Test
    public void shouldGetLogicalInterconnectUnassignedUplinkPortsForPortMonitor() {
        client.getUnassignedUplinkPortsForPortMonitor(ANY_RESOURCE_ID);

        String expectedUri = LOGICAL_INTERCONNECT_URI
                + "/" + ANY_RESOURCE_ID
                + LOGICAL_INTERCONNECT_UNASSIGNED_UPLINK_PORTS_URI;
        Request expectedRequest = new Request(HttpMethod.GET, expectedUri);

        then(baseClient).should().executeRequest(expectedRequest,
                new TypeToken<ResourceCollection<PortMonitorUplinkPort>>() {}.getType());
    }

}
