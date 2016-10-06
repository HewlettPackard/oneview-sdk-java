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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.InterconnectFibDataEntry;
import com.hp.ov.sdk.dto.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.InternalVlanAssociation;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.LocationEntry;
import com.hp.ov.sdk.dto.LocationType;
import com.hp.ov.sdk.dto.PortMonitorUplinkPort;
import com.hp.ov.sdk.dto.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.QosConfiguration.QosConfigType;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.Command;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LiFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LogicalInterconnect;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PhysicalInterconnectFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PortMonitor;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureClient;
import com.hp.ov.sdk.rest.client.servers.EnclosureClientSample;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * LogicalInterconnectClientSample, sample program consumes the available networks through the interconnect uplinks and
 * downlink capabilities through a physical server’s interfaces of HPE OneView. It invokes APIs of LogicalInterconnectClient
 *  which is in sdk library to perform GET/PUT operations on logical interconnect resource
 */
public class LogicalInterconnectClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogicalInterconnectClientSample.class);

    private final LogicalInterconnectClient logicalInterconnectClient;
    private final FirmwareDriverClient firmwareDriverClient;
    private final EnclosureClient enclosureClient;
    private final OneViewClient oneViewClient;

    // These are variables to be defined by user
    // ================================
    private static final String SPP_NAME = "Service Pack for ProLiant";
    private static final String RESOURCE_NAME = "Encl1-LI";
    private static final String RESOURCE_ID = "1a26f2cc-8d41-4617-8e1f-55c2900148b1";
    private static final String TELEMETRY_ID = "2770fdeb-5c49-499c-aef7-3eac45f2887e";
    private static final String BAY_NUMBER = "1";
    private static final String NETWORK_NAME = "Prod_401";

    private static final String INTERCONNECT_NAME_ONE = "Encl1, interconnect 1";
    private static final String INTERCONNECT_NAME_TWO = "Encl1, interconnect 2";
    // ================================

    private LogicalInterconnectClientSample() {
        this.oneViewClient = OneViewClientSample.getOneViewClient();

        this.enclosureClient = oneViewClient.enclosure();
        this.logicalInterconnectClient = oneViewClient.logicalInterconnect();
        this.firmwareDriverClient = oneViewClient.firmwareDriver();
    }

    private void getLogicalInterconnectById() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getById(RESOURCE_ID);

        LOGGER.info("LogicalInterconnect object returned to client : " + logicalInterconnect.toJsonString());
    }

    private void getLogicalInterconnectByName() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LOGGER.info("LogicalInterconnect object returned to client : " + logicalInterconnect.toJsonString());
    }

    private void getAllLogicalInterconnects() {
        ResourceCollection<LogicalInterconnect> logicalInterconnects = logicalInterconnectClient.getAll();

        LOGGER.info("LogicalInterconnect returned to client : " + logicalInterconnects.toJsonString());
    }

    private void updateLogicalInterconnectSnmpConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        logicalInterconnect.getSnmpConfiguration().setReadCommunity("private");

        TaskResource task = logicalInterconnectClient.updateSnmpConfiguration(logicalInterconnect.getResourceId(),
                logicalInterconnect.getSnmpConfiguration());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectCompliance() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TaskResource task = logicalInterconnectClient.updateCompliance(logicalInterconnect.getResourceId());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectFirmware() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = new LiFirmware();

        liFirmware.setCommand(Command.Stage);
        liFirmware.setSppUri(firmwareDriverClient.getByName(SPP_NAME).get(0).getUri());
        liFirmware.setForce(true);

        TaskResource task = logicalInterconnectClient.updateFirmware(logicalInterconnect.getResourceId(),
                liFirmware);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectFirmware() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = logicalInterconnectClient.getFirmware(logicalInterconnect.getResourceId());

        LOGGER.info("LiFirmware object returned to client : " + JsonPrettyPrinter.print(liFirmware));
    }

    private void updateLogicalInterconnectFirmwareActive() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        LiFirmware liFirmware = logicalInterconnectClient.getFirmware(logicalInterconnect.getResourceId());

        liFirmware = buildLiFirmwareActive(liFirmware);

        TaskResource task = logicalInterconnectClient.updateFirmware(logicalInterconnect.getResourceId(),
                liFirmware);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private LiFirmware buildLiFirmwareActive(LiFirmware currentLiFirmware) {
        LiFirmware liFirmware = new LiFirmware();
        List<PhysicalInterconnectFirmware> interconnects = new ArrayList<>();

        liFirmware.setCommand(Command.Stage);
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
        liFirmware.setCommand(Command.Update);
        liFirmware.setSppUri(firmwareDriverClient.getByName(SPP_NAME).get(0).getUri());
        liFirmware.setForce(true);

        TaskResource task = logicalInterconnectClient.updateFirmware(logicalInterconnect.getResourceId(),
                liFirmware);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectForwardingInformationBase() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        ResourceCollection<InterconnectFibDataEntry> fibData = logicalInterconnectClient.getForwardingInformationBase(
                logicalInterconnect.getResourceId());

        LOGGER.info("InterconnectFibData returned to client : " + fibData.toJsonString());
    }

    private void createLogicalInterconnectForwardingInformationBase() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        InterconnectFibDataInfo fibData = logicalInterconnectClient.createForwardingInformationBase(
                logicalInterconnect.getResourceId());

        LOGGER.info("InterconnectFibDataInfo object returned to client : " + JsonPrettyPrinter.print(fibData));
    }

    private void getLogicalInterconnectSnmpConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        SnmpConfiguration snmpConfiguration = logicalInterconnectClient.getSnmpConfiguration(
                logicalInterconnect.getResourceId());

        LOGGER.info("SnmpConfiguration object returned to client : " + snmpConfiguration.toJsonString());
    }

    private void getLogicalInterconnectUnassignedUplinkPortsForPortMonitor() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        ResourceCollection<PortMonitorUplinkPort> uplinkPorts
                = logicalInterconnectClient.getUnassignedUplinkPortsForPortMonitor(logicalInterconnect.getResourceId());

        LOGGER.info("UplinkPorts returned to client : " + uplinkPorts.toJsonString());
    }

    private void updateLogicalInterconnectConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TaskResource task = logicalInterconnectClient.updateConfiguration(logicalInterconnect.getResourceId());

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectPortMonitorConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        PortMonitor portMonitor = logicalInterconnectClient.getPortMonitorConfiguration(
                logicalInterconnect.getResourceId());

        LOGGER.info("PortMonitor object returned to client : " + portMonitor.toJsonString());
    }

    private void updateLogicalInterconnectPortMonitorConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        PortMonitor portMonitor = logicalInterconnectClient.getPortMonitorConfiguration(
                logicalInterconnect.getResourceId());
        portMonitor.setEnablePortMonitor(false);

        TaskResource task = logicalInterconnectClient.updatePortMonitorConfiguration(
                logicalInterconnect.getResourceId(), portMonitor);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectTelemetryConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TelemetryConfiguration telemetryConfiguration = logicalInterconnectClient.getTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID);

        LOGGER.info("TelemetryConfiguration object returned to client : " + telemetryConfiguration.toJsonString());
    }

    private void updateLogicalInterconnectTelemetryConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TelemetryConfiguration telemetryConfiguration = logicalInterconnectClient.getTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID);
        telemetryConfiguration.setEnableTelemetry(!telemetryConfiguration.getEnableTelemetry());

        TelemetryConfiguration telemetryConfigurationUpdated = logicalInterconnectClient.updateTelemetryConfigurationV120(
                logicalInterconnect.getResourceId(), TELEMETRY_ID, telemetryConfiguration);

        LOGGER.info("TelemetryConfiguration object returned to client : " + telemetryConfigurationUpdated.toJsonString());
    }

    private void updateLogicalInterconnectTelemetryConfigurationV200() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        TelemetryConfiguration telemetryConfiguration = logicalInterconnectClient.getTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID);
        telemetryConfiguration.setEnableTelemetry(!telemetryConfiguration.getEnableTelemetry());

        TaskResource task = logicalInterconnectClient.updateTelemetryConfiguration(
                logicalInterconnect.getResourceId(), TELEMETRY_ID, telemetryConfiguration);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateEthernetSettings() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        EthernetInterconnectSettingsV2 ethSettingsDto = logicalInterconnect.getEthernetSettings();
        ethSettingsDto.setEnablePauseFloodProtection(!ethSettingsDto.getEnablePauseFloodProtection());

        TaskResource task = logicalInterconnectClient.updateEthernetSettings(logicalInterconnect.getResourceId(),
                ethSettingsDto);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void createLogicalInterconnect() {
        Location location = new Location();

        String enclosureUri = this.enclosureClient.getByName(EnclosureClientSample.RESOURCE_NAME_UPDATED).get(0).getUri();

        // ENCLOSURE
        LocationEntry enclosureEntry = new LocationEntry();
        enclosureEntry.setType(LocationType.Enclosure);
        enclosureEntry.setValue(enclosureUri);

        // BAY
        LocationEntry bayEntry = new LocationEntry();
        bayEntry.setType(LocationType.Bay);
        bayEntry.setValue(BAY_NUMBER);

        location.setLocationEntries(Arrays.asList(enclosureEntry, bayEntry));

        TaskResource task = logicalInterconnectClient.createInterconnect(location);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void deleteLogicalInterconnect() {
        String enclosureUri = this.enclosureClient.getByName(EnclosureClientSample.RESOURCE_NAME_UPDATED).get(0).getUri();

        TaskResource task = logicalInterconnectClient.deleteInterconnect(enclosureUri, BAY_NUMBER);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectInternalNetworks() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        String networkUri = oneViewClient.ethernetNetwork().getByName(NETWORK_NAME).get(0).getUri();

        TaskResource task = logicalInterconnectClient.updateInternalNetworks(
                logicalInterconnect.getResourceId(), Arrays.asList(networkUri));

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void getLogicalInterconnectInternalVlans() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        ResourceCollection<InternalVlanAssociation> vlans = logicalInterconnectClient.getInternalVlans(
                logicalInterconnect.getResourceId());

        LOGGER.info("Internal Vlans returned to client : " + vlans.toJsonString());
    }

    private void getLogicalInterconnectQosAggregatedConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        QosAggregatedConfiguration qosConfiguration = logicalInterconnectClient.getQosAggregatedConfiguration(
                logicalInterconnect.getResourceId());

        LOGGER.info("QosAggregatedConfiguration object returned to client : " + qosConfiguration.toJsonString());
    }

    private void updateLogicalInterconnectQosAggregatedConfiguration() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        QosAggregatedConfiguration qosConfiguration = logicalInterconnectClient.getQosAggregatedConfiguration(
                logicalInterconnect.getResourceId());
        qosConfiguration.getActiveQosConfig().setConfigType(QosConfigType.Passthrough);

        TaskResource task = logicalInterconnectClient.updateQosAggregatedConfiguration(
                logicalInterconnect.getResourceId(), qosConfiguration);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
    }

    private void updateLogicalInterconnectSettings() {
        LogicalInterconnect logicalInterconnect = logicalInterconnectClient.getByName(RESOURCE_NAME).get(0);

        InterconnectSettingsV2 settings = new InterconnectSettingsV2();
        settings.setType(ResourceCategory.RC_LOGICAL_INTERCONNECT_SETTINGS_V200);
        settings.setType(ResourceCategory.RC_LOGICAL_INTERCONNECT_SETTINGS_V300);
        settings.setEthernetSettings(logicalInterconnect.getEthernetSettings());
        settings.setFcoeSettings(logicalInterconnect.getFcoeSettings());
        settings.getEthernetSettings().setMacRefreshInterval(6);

        TaskResource task = logicalInterconnectClient.updateSettings(
                logicalInterconnect.getResourceId(), settings);

        LOGGER.info("Task object returned to client : " + task.toJsonString());
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
