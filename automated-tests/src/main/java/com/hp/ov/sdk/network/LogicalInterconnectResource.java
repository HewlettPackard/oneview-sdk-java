/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.network;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.firmware.FwBaseline;
import com.hp.ov.sdk.dto.networking.EthernetInterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.InterconnectSettingsV2;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.LocationEntry;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.TelemetryConfiguration;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.dto.networking.interconnect.InterconnectFibDataInfo;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.Command;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LiFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LogicalInterconnect;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PhysicalInterconnectFirmware;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.PortMonitor;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.QosAggregatedConfiguration;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.QosConfiguration.QosConfigType;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.networking.LogicalInterconnectClient;
import com.hp.ov.sdk.rest.client.settings.FirmwareDriverClient;

public class LogicalInterconnectResource extends BasicResource implements CreateResource, RemoveResource {

    private static LogicalInterconnectResource instance;

    private LogicalInterconnectClient client;

    private FirmwareDriverClient firmwareDriverClient;

    private EthernetNetworkClient ethernetNetworkClient;
    private FcNetworkClient fcNetworkClient;

    private static String resourceName;

    private String enclosureUri;

    public LogicalInterconnectResource() {
        client = oneViewClient.logicalInterconnect();
        firmwareDriverClient = oneViewClient.firmwareDriver();
        ethernetNetworkClient = oneViewClient.ethernetNetwork();
        fcNetworkClient = oneViewClient.fcNetwork();
    }

    public static LogicalInterconnectResource getInstance() {
        if (instance == null) {
            instance = new LogicalInterconnectResource();
        }
        return instance;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        LogicalInterconnect logicalInterconnect = (LogicalInterconnect) getResource(client.getByName(name));
        return logicalInterconnect == null ? "" : logicalInterconnect.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        TaskResource task = client.createInterconnect(builder());
        resourceName = task.getAssociatedResource().getResourceName();
    }

    @Override
    public String remove(String id) {
        return taskToString(client.deleteInterconnect(enclosureUri, resourceProperties.get("bay")));
    }

    public String updateCompliance(String id) {
        return taskToString(client.updateCompliance(id));
    }

    public String getLogicalInterconnectName() {
        return resourceName;
    }

    public String getFirmware(String id) {
        return objToString(client.getFirmware(id));
    }

    public String getSnmpConfiguration(String id) {
        return objToString(client.getSnmpConfiguration(id));
    }

    public String getForwardingInformationBase(String id) {
        return objToString(client.getForwardingInformationBase(id));
    }

    public String getUnassignedUplinkPortsForPortMonitor(String id) {
        return objToString(client.getUnassignedUplinkPortsForPortMonitor(id));
    }

    public String getPortMonitorConfiguration(String id) {
        return objToString(client.getPortMonitorConfiguration(id));
    }

    public String getTelemetryConfiguration(String id) {
        LogicalInterconnect logicalInterconnect = client.getById(id);
        return objToString(
                client.getTelemetryConfiguration(id, logicalInterconnect.getTelemetryConfiguration().getUri()));
    }

    public String getInternalVlans(String id) {
        return objToString(client.getInternalVlans(id));
    }

    public String getQosAggregatedConfiguration(String id) {
        return objToString(client.getQosAggregatedConfiguration(id));
    }

    public String updateFirmware(String id) {
        LiFirmware liFirmware = new LiFirmware();
        liFirmware.setCommand(Command.valueOf(resourceProperties.get("command")));
        liFirmware.setSppUri(firmwareDriverClient.getByName(resourceProperties.get("spp")).get(0).getUri());
        liFirmware.setForce(Boolean.valueOf(resourceProperties.get("force")));
        return taskToString(client.updateFirmware(id, liFirmware));
    }

    public String updateSnmpConfiguration(String id) {
        LogicalInterconnect logicalInterconnect = client.getById(id);
        logicalInterconnect.getSnmpConfiguration().setReadCommunity(resourceProperties.get("readCommunity"));
        return taskToString(client.updateSnmpConfiguration(id, logicalInterconnect.getSnmpConfiguration()));
    }

    public String createForwardingInformationBase(String id) {
        InterconnectFibDataInfo fibDataInfo = client.createForwardingInformationBase(id);
        return objToString(fibDataInfo);
    }

    public String updateForwardingInformationBase(String id) {
        return taskToString(client.updateConfiguration(id));
    }

    public String updatePortMonitorConfiguration(String id) {
        PortMonitor portMonitor = client.getPortMonitorConfiguration(id);
        portMonitor.setEnablePortMonitor(Boolean.valueOf(resourceProperties.get("enablePortMonitor")));
        return taskToString(client.updatePortMonitorConfiguration(id, portMonitor));
    }

    public String updateEthernetSettings(String id) {
        LogicalInterconnect logicalInterconnect = client.getById(id);
        EthernetInterconnectSettingsV2 ethSettings = logicalInterconnect.getEthernetSettings();
        ethSettings
                .setEnablePauseFloodProtection(Boolean.valueOf(resourceProperties.get("enablePauseFloodProtection")));
        return taskToString(client.updateEthernetSettings(id, ethSettings));
    }

    public String updateLogicalInterconnectInternalNetworks(String id) {
        String ethNetwork = resourceProperties.get("network");
        String fcNetwork = resourceProperties.get("fcNetwork");
        String taskStatus = null;
        if (ethNetwork != null) {
            Network network = (Network) getResource(ethernetNetworkClient.getByName(ethNetwork));
            taskStatus = taskToString(client.updateInternalNetworks(id, Arrays.asList(network.getUri())));
        } else if (fcNetwork != null) {
            FcNetwork network = (FcNetwork) getResource(fcNetworkClient.getByName(fcNetwork));
            taskStatus = taskToString(client.updateInternalNetworks(id, Arrays.asList(network.getUri())));
        }
        return taskStatus;

    }

    public String updateQosAggregatedConfiguration(String id) {
        QosAggregatedConfiguration qosAggregatedConfiguration = client.getQosAggregatedConfiguration(id);
        qosAggregatedConfiguration.getActiveQosConfig()
                .setConfigType(QosConfigType.valueOf(resourceProperties.get("configType")));
        return taskToString(client.updateQosAggregatedConfiguration(id, qosAggregatedConfiguration));
    }

    public String updateLogicalInterconnectSettings(String id) {
        LogicalInterconnect logicalInterconnect = client.getById(id);

        InterconnectSettingsV2 settings = new InterconnectSettingsV2();
        settings.setType(resourceProperties.get("type"));
        settings.setEthernetSettings(logicalInterconnect.getEthernetSettings());
        settings.setFcoeSettings(logicalInterconnect.getFcoeSettings());
        settings.getEthernetSettings()
                .setMacRefreshInterval(Integer.parseInt(resourceProperties.get("macRefreshInterval")));

        return taskToString(client.updateSettings(id, settings));
    }

    public String updateFirmwareActive(String id) {
        LiFirmware liFirmware = client.getFirmware(id);
        liFirmware = buildLiFirmwareActive(liFirmware);
        return taskToString(client.updateFirmware(id, liFirmware));
    }

    public String updateTelemetryConfigurationV2(String id) {
        LogicalInterconnect logicalInterconnect = client.getById(id);
        TelemetryConfiguration telemetryConfiguration = logicalInterconnect.getTelemetryConfiguration();
        telemetryConfiguration.setEnableTelemetry(Boolean.valueOf(resourceProperties.get("enableTelemetry")));
        return taskToString(client.updateTelemetryConfiguration(id, telemetryConfiguration.getResourceId(),
                telemetryConfiguration));
    }

    public String updateTelemetryConfigurationV1_2(String id) {
        LogicalInterconnect logicalInterconnect = client.getById(id);
        TelemetryConfiguration telemetryConfiguration = logicalInterconnect.getTelemetryConfiguration();
        telemetryConfiguration.setEnableTelemetry(Boolean.valueOf(resourceProperties.get("enableTelemetry")));
        return objToString(client.updateTelemetryConfiguration(id, telemetryConfiguration.getResourceId(),
                telemetryConfiguration));
    }

    @Override
    public Location builder() {
        Location location = new Location();
        LocationEntry enclosureEntry = new LocationEntry();
        enclosureEntry.setType(LocationType.Enclosure);
        enclosureEntry.setValue(enclosureUri);

        LocationEntry bayEntry = new LocationEntry();
        bayEntry.setType(LocationType.Bay);
        bayEntry.setValue(resourceProperties.get("bay"));

        location.setLocationEntries(Arrays.asList(enclosureEntry, bayEntry));
        return location;
    }

    public LiFirmware buildLiFirmwareActive(LiFirmware currentLiFirmware) {
        LiFirmware liFirmware = new LiFirmware();
        List<PhysicalInterconnectFirmware> interconnects = new ArrayList<>();

        liFirmware.setCommand(Command.valueOf(resourceProperties.get("command")));
        FwBaseline fwBaseline = (FwBaseline) getResource(firmwareDriverClient.getByName(resourceProperties.get("spp")));
        liFirmware.setSppUri(fwBaseline.getUri());

        String INTERCONNECT_NAME_ONE = resourceProperties.get("interconnectName1");
        String INTERCONNECT_NAME_TWO = resourceProperties.get("interconnectName2");

        for (int i = 0; i < currentLiFirmware.getInterconnects().size(); i++) {
            String interconnectName = null;
            if (currentLiFirmware.getInterconnects().get(i).getInterconnectName().equals(INTERCONNECT_NAME_ONE)) {
                interconnectName = INTERCONNECT_NAME_ONE;
            } else if (currentLiFirmware.getInterconnects().get(i).getInterconnectName()
                    .equals(INTERCONNECT_NAME_TWO)) {
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

}
