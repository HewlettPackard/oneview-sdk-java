/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.rest.client.networking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InternalVlanAssociation;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.QosConfiguration.QosConfigType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.LocationEntry;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.Command;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LiFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LogicalInterconnect;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PhysicalInterconnectFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PortMonitor;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * LogicalInterconnectClientSample, sample program consumes the available networks through the interconnect uplinks and
 * downlink capabilities through a physical server’s interfaces of HPE OneView. It invokes APIs of LogicalInterconnectClient
 *  which is in sdk library to perform GET/PUT operations on logical interconnect resource
 */
public class LogicalInterconnectClientSample {

    private final LogicalInterconnectClient logicalInterconnectClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final OneViewClient oneViewClient;

    // These are variables to be defined by user
    // ================================
    private static final String SPP_NAME = "Service Pack for ProLiant";
    private static final String RESOURCE_NAME = "Encl1-LIG_PROD";
    private static final String RESOURCE_ID = "f4a1ad7c-c282-4089-b57c-dd28052cde6a";
    private static final String TELEMETRY_ID = "2770fdeb-5c49-499c-aef7-3eac45f2887e";
    private static final String ENCLOSURE_URI = "/rest/enclosures/09SGH100X6J1";
    private static final String NETWORK_NAME = "Prod_401";

    private static final String INTERCONNECT_NAME_ONE = "Encl1, interconnect 1";
    private static final String INTERCONNECT_NAME_TWO = "Encl1, interconnect 2";
    // ================================

    private LogicalInterconnectClientSample() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();

        this.logicalInterconnectClient = oneViewClient.logicalInterconnect();
        this.firmwareDriverClient = oneViewClient.firmwareDriver();
    }

    private void getLogicalInterconnectById() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getById(RESOURCE_ID);

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectById : " +
                "LogicalInterconnect object returned to client : " + logicalInterconnect.toJsonString());
    }

    private void getLogicalInterconnectByName() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectByName : " +
                "LogicalInterconnect object returned to client : " + logicalInterconnect.toJsonString());
    }

    private void getAllLogicalInterconnects() {
        ResourceCollection<LogicalInterconnect> logicalInterconnects = logicalInterconnectClient.getAll();

        System.out.println("LogicalInterconnectClientSample : getAllLogicalInterconnects : " +
                "LogicalInterconnect returned to client : " + logicalInterconnects.toJsonString());
    }

    private void updateLogicalInterconnectSnmpConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        logicalInterconnect.getSnmpConfiguration().setReadCommunity("private");

        TaskResourceV2 task = logicalInterconnectClient.updateSnmpConfiguration(logicalInterconnect.getResourceId(),
                logicalInterconnect.getSnmpConfiguration(), false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectSnmpConfiguration : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectCompliance() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 task = logicalInterconnectClient.updateCompliance(logicalInterconnect.getResourceId(), false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectCompliance : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectFirmware() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.STAGE);
        liFirmware.setSppUri(firmwareDriverClient.getByName(SPP_NAME).get(0).getUri());
        liFirmware.setForce(true);

        TaskResourceV2 task = logicalInterconnectClient.updateFirmware(logicalInterconnect.getResourceId(),
                liFirmware, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectFirmware : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectFirmware() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = logicalInterconnectClient.getFirmware(logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectFirmware : " +
                "LiFirmware object returned to client : " + JsonPrettyPrinter.print(liFirmware));
    }

    private void updateLogicalInterconnectFirmwareActive() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = logicalInterconnectClient.getFirmware(logicalInterconnect.getResourceId());

        liFirmware = buildLiFirmwareActive(liFirmware);

        TaskResourceV2 task = logicalInterconnectClient.updateFirmware(logicalInterconnect.getResourceId(),
                liFirmware, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectFirmwareActive : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private LiFirmware buildLiFirmwareActive(LiFirmware currentLiFirmware) {
        LiFirmware liFirmware = new LiFirmware();
        List<PhysicalInterconnectFirmware> interconnects = new ArrayList<>();

        liFirmware.setCommand(Command.STAGE);
        liFirmware.setSppUri(firmwareDriverClient.getByName(SPP_NAME).get(0).getUri());

        for (int i = 0; i < currentLiFirmware.getInterconnects().size(); i++) {
            String interconnectName = null;
            if (currentLiFirmware.getInterconnects().get(i).getInterconnectName().equals(INTERCONNECT_NAME_ONE)) {
                interconnectName = INTERCONNECT_NAME_ONE;
            } else if (currentLiFirmware.getInterconnects().get(i).getInterconnectName().equals(INTERCONNECT_NAME_TWO)) {
                interconnectName = INTERCONNECT_NAME_TWO;
            }
            if (interconnectName != null) {
                final PhysicalInterconnectFirmware interconnect = new PhysicalInterconnectFirmware();
                interconnect.setInterconnectUri(currentLiFirmware.getInterconnects().get(i).getInterconnectUri());
                interconnect.setInterconnectName(interconnectName);
                interconnects.add(interconnect);
            }
        }
        liFirmware.setInterconnects(interconnects);

        return liFirmware;
    }

    private void updateLogicalInterconnectFirmwareUpdate() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = new LiFirmware();
        liFirmware.setCommand(Command.UPDATE);
        liFirmware.setSppUri(firmwareDriverClient.getByName(SPP_NAME).get(0).getUri());
        liFirmware.setForce(true);

        TaskResourceV2 task = logicalInterconnectClient.updateFirmware(logicalInterconnect.getResourceId(),
                liFirmware, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectFirmwareUpdate : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectForwardingInformationBase() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        ResourceCollection<InterconnectFibDataEntry> fibData = logicalInterconnectClient.getForwardingInformationBase(
                logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectForwardingInformationBase : " +
                "InterconnectFibData returned to client : " + fibData.toJsonString());
    }

    private void createLogicalInterconnectForwardingInformationBase() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        InterconnectFibDataInfo fibData = logicalInterconnectClient.createForwardingInformationBase(
                logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : createLogicalInterconnectForwardingInformationBase : " +
                "InterconnectFibDataInfo object returned to client : " + JsonPrettyPrinter.print(fibData));
    }

    private void getLogicalInterconnectSnmpConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        SnmpConfiguration snmpConfiguration = logicalInterconnectClient.getSnmpConfiguration(
                logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectSnmpConfiguration : " +
                "SnmpConfiguration object returned to client : " + snmpConfiguration.toJsonString());
    }

    private void getLogicalInterconnectUnassignedUplinkPortsForPortMonitor() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        ResourceCollection<PortMonitorUplinkPort> uplinkPorts
                = logicalInterconnectClient.getUnassignedUplinkPortsForPortMonitor(logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectUnassignedUplinkPortsForPortMonitor : " +
                "UplinkPorts returned to client : " + uplinkPorts.toJsonString());
    }

    private void updateLogicalInterconnectConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TaskResourceV2 task = logicalInterconnectClient.updateConfiguration(logicalInterconnect.getResourceId(), false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectConfiguration : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectPortMonitorConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        PortMonitor portMonitor = logicalInterconnectClient.getPortMonitorConfiguration(
                logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectPortMonitorConfiguration : " +
                "PortMonitor object returned to client : " + portMonitor.toJsonString());
    }

    private void updateLogicalInterconnectPortMonitorConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        PortMonitor portMonitor = logicalInterconnectClient.getPortMonitorConfiguration(
                logicalInterconnect.getResourceId());
        portMonitor.setEnablePortMonitor(false);

        TaskResourceV2 task = logicalInterconnectClient.updatePortMonitorConfiguration(
                logicalInterconnect.getResourceId(), portMonitor, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectPortMonitorConfiguration : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectTelemetryConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TelemetryConfiguration telemetryConfiguration = logicalInterconnectClient.getTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID);

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectTelemetryConfiguration : " +
                "TelemetryConfiguration object returned to client : " + telemetryConfiguration.toJsonString());
    }

    private void updateLogicalInterconnectTelemetryConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TelemetryConfiguration telemetryConfiguration = logicalInterconnectClient.getTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID);
        telemetryConfiguration.setEnableTelemetry(!telemetryConfiguration.getEnableTelemetry());

        TelemetryConfiguration telemetryConfigurationUpdated = logicalInterconnectClient.updateTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID, telemetryConfiguration);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectTelemetryConfiguration : " +
                "TelemetryConfiguration object returned to client : " + telemetryConfigurationUpdated.toJsonString());
    }

    private void updateLogicalInterconnectTelemetryConfigurationV200() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TelemetryConfiguration telemetryConfiguration = logicalInterconnectClient.getTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID);
        telemetryConfiguration.setEnableTelemetry(!telemetryConfiguration.getEnableTelemetry());

        TaskResourceV2 task = logicalInterconnectClient.updateTelemetryConfigurationV200(
                logicalInterconnect.getResourceId(), TELEMETRY_ID, telemetryConfiguration, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectTelemetryConfigurationV200 : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateEthernetSettings() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        EthernetInterconnectSettingsV2 ethSettingsDto = logicalInterconnect.getEthernetSettings();
        ethSettingsDto.setEnablePauseFloodProtection(!ethSettingsDto.getEnablePauseFloodProtection());

        TaskResourceV2 task = logicalInterconnectClient.updateEthernetSettings(logicalInterconnect.getResourceId(),
                ethSettingsDto, false);

        System.out.println("LogicalInterconnectClientSample : updateEthernetSettings : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void createLogicalInterconnect() {
        Location location = new Location();

        // ENCLOSURE
        LocationEntry enclosureEntry = new LocationEntry();
        enclosureEntry.setType(LocationType.Enclosure);
        enclosureEntry.setValue(ENCLOSURE_URI);

        // BAY
        LocationEntry bayEntry = new LocationEntry();
        bayEntry.setType(LocationType.Bay);
        bayEntry.setValue("1");

        location.setLocationEntries(Arrays.asList(enclosureEntry, bayEntry));

        TaskResourceV2 task = logicalInterconnectClient.createInterconnect(location, false);

        System.out.println("LogicalInterconnectClientSample : createLogicalInterconnect : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void deleteLogicalInterconnect() {
        TaskResourceV2 task = logicalInterconnectClient.deleteInterconnect(ENCLOSURE_URI, "1", false);

        System.out.println("LogicalInterconnectClientSample : deleteLogicalInterconnect : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectInternalNetworks() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        String networkUri = oneViewClient.ethernetNetwork().getByName(NETWORK_NAME).getUri();

        TaskResourceV2 task = logicalInterconnectClient.updateInternalNetworks(
                logicalInterconnect.getResourceId(), Arrays.asList(networkUri), false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectInternalNetworks : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectInternalVlans() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        ResourceCollection<InternalVlanAssociation> vlans = logicalInterconnectClient.getInternalVlans(
                logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectInternalVlans : " +
                "Internal Vlans returned to client : " + vlans.toJsonString());
    }

    private void getLogicalInterconnectQosAggregatedConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        QosAggregatedConfiguration qosConfiguration = logicalInterconnectClient.getQosAggregatedConfiguration(
                logicalInterconnect.getResourceId());

        System.out.println("LogicalInterconnectClientSample : getLogicalInterconnectQosAggregatedConfiguration : " +
                "QosAggregatedConfiguration object returned to client : " + qosConfiguration.toJsonString());
    }

    private void updateLogicalInterconnectQosAggregatedConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        QosAggregatedConfiguration qosConfiguration = logicalInterconnectClient.getQosAggregatedConfiguration(
                logicalInterconnect.getResourceId());
        qosConfiguration.getActiveQosConfig().setConfigType(QosConfigType.Passthrough);

        TaskResourceV2 task = logicalInterconnectClient.updateQosAggregatedConfiguration(
                logicalInterconnect.getResourceId(), qosConfiguration, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectQosAggregatedConfiguration : " +
                "Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectSettings() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        InterconnectSettingsV2 settings = new InterconnectSettingsV2();
        settings.setType("InterconnectSettingsV3");
        settings.setEthernetSettings(logicalInterconnect.getEthernetSettings());
        settings.setFcoeSettings(logicalInterconnect.getFcoeSettings());
        settings.getEthernetSettings().setMacRefreshInterval(6);

        TaskResourceV2 task = logicalInterconnectClient.updateSettings(
                logicalInterconnect.getResourceId(), settings, false);

        System.out.println("LogicalInterconnectClientSample : updateLogicalInterconnectSettings : " +
                "Task object returned to client : " + task.toJsonString());
    }

    public static void main(final String[] args) {
        LogicalInterconnectClientSample client = new LogicalInterconnectClientSample();

        client.getLogicalInterconnectById();
        client.getLogicalInterconnectByName();
        client.getAllLogicalInterconnects();
        client.updateLogicalInterconnectCompliance();

        client.getLogicalInterconnectFirmware();
        client.updateLogicalInterconnectFirmware();

        client.getLogicalInterconnectSnmpConfiguration();
        client.updateLogicalInterconnectSnmpConfiguration();

        client.getLogicalInterconnectForwardingInformationBase();
        client.createLogicalInterconnectForwardingInformationBase();

        client.getLogicalInterconnectUnassignedUplinkPortsForPortMonitor();
        client.updateLogicalInterconnectConfiguration();

        client.getLogicalInterconnectPortMonitorConfiguration();
        client.updateLogicalInterconnectPortMonitorConfiguration();

        client.getLogicalInterconnectTelemetryConfiguration();
        client.updateLogicalInterconnectTelemetryConfiguration(); //OneView 1.2
        client.updateLogicalInterconnectTelemetryConfigurationV200(); //OneView 2.0

        client.updateEthernetSettings();
        client.createLogicalInterconnect();
        client.deleteLogicalInterconnect();
        client.updateLogicalInterconnectInternalNetworks();
        client.getLogicalInterconnectInternalVlans();
        client.getLogicalInterconnectQosAggregatedConfiguration();
        client.updateLogicalInterconnectQosAggregatedConfiguration();
        client.updateLogicalInterconnectSettings();

        client.updateLogicalInterconnectFirmwareActive();
        client.updateLogicalInterconnectFirmwareUpdate();
    }

}
