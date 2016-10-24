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
package com.hp.ov.sdk.rest.client.servers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.servers.LicensingIntent;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.BiosSettings;
import com.hp.ov.sdk.dto.servers.serverhardware.ConfigurationState;
import com.hp.ov.sdk.dto.servers.serverhardware.IloSsoUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.PhysicalServerPowerControl;
import com.hp.ov.sdk.dto.servers.serverhardware.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.servers.serverhardware.RefreshStateRequest;
import com.hp.ov.sdk.dto.servers.serverhardware.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerFirmwareInventory;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerPowerControlRequest;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.FirmwareInventoryFilter;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * ServerHardwareClientSample is a sample program to captures/consume details about the physical configuration of server
 * hardware, defines which settings are available to the server profiles assigned to that type of server hardware
 * It invokes APIs of ServerHardwareClient which is in sdk library to perform GET/POST operations
 * on server hardware resource
 */
public class ServerHardwareClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHardwareClientSample.class);

    private final ServerHardwareClient serverHardwareClient;

    // These are variables to be defined by user
    // ================================
    private static final String SERVER_HARDWARE_RESOURCE_NAME = "172.18.6.32";
    private static final String SERVER_HARDWARE_RESOURCE_ID = "37333036-3831-584D-5131-303030333037";
    private static final String SCOPE_RESOURCE_ID = "ebd97670-330d-4d2c-9f09-16c8e2ea63c5";
    private static final String SERVER_HARDWARE_HOSTNAME = "172.18.6.32";
    private static final String SERVER_HARDWARE_USERNAME = "dcs";
    private static final String SERVER_HARDWARE_PASSWORD = "dcs";
    private static final String UNMANAGED_DEVICE_HARDWARE = "172.18.6.15";
    // ================================

    private ServerHardwareClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        serverHardwareClient = oneViewClient.serverHardware();
    }

    private void getServerHardware() {
        ServerHardware serverHardware = serverHardwareClient.getById(SERVER_HARDWARE_RESOURCE_ID);

        LOGGER.info("Server Hardware returned to client: {}", serverHardware.toJsonString());
    }

    private void getAllServerHardware() {
        ResourceCollection<ServerHardware> serverHardware = serverHardwareClient.getAll();

        LOGGER.info("Server Hardware returned to client: {}", serverHardware.toJsonString());
    }

    private void getServerHardwareByName() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        LOGGER.info("Server Hardware returned to client: {}", serverHardware.toJsonString());
    }

    private void addServerHardware() {
        AddServer addServer = this.buildServerHardware();

        TaskResource taskResource = this.serverHardwareClient.add(addServer);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void removeServerHardware() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        TaskResource taskResource = this.serverHardwareClient.remove(serverHardware.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateServerHardwarePowerState() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        ServerPowerControlRequest serverPowerControlRequest = buildServerPowerControlRequest();

        TaskResource taskResource = serverHardwareClient.updatePowerState(serverHardware.getResourceId(),
                serverPowerControlRequest);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateServerHardwareRefreshState() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        RefreshStateRequest refreshStateRequest = new RefreshStateRequest();

        refreshStateRequest.setHostname(SERVER_HARDWARE_HOSTNAME);
        refreshStateRequest.setUsername(SERVER_HARDWARE_USERNAME);
        refreshStateRequest.setPassword(SERVER_HARDWARE_PASSWORD);

        TaskResource taskResource = serverHardwareClient.updateRefreshState(serverHardware.getResourceId(),
                refreshStateRequest);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getServerHardwareBios() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        BiosSettings biosSettings = serverHardwareClient.getBios(serverHardware.getResourceId());

        LOGGER.info("Bios Settings object returned to client: {}", JsonPrettyPrinter.print(biosSettings));
    }

    private void getServerHardwareEnvironmentConfiguration() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        EnvironmentalConfiguration environmentalConfiguration
                = serverHardwareClient.getEnvironmentConfiguration(serverHardware.getResourceId());

        LOGGER.info("Environmental Configuration object returned to client: {}", JsonPrettyPrinter.print(environmentalConfiguration));
    }

    private void updateServerHardwareEnvironmentConfiguration() {
        ServerHardware serverHardware = serverHardwareClient.getByName(UNMANAGED_DEVICE_HARDWARE).get(0);

        EnvironmentalConfigurationUpdate request = new EnvironmentalConfigurationUpdate();

        request.setCalibratedMaxPower(Integer.valueOf(2500));

        EnvironmentalConfiguration environmentalConfiguration = serverHardwareClient.updateEnvironmentConfiguration(
                serverHardware.getResourceId(), request);

        LOGGER.info("Environmental Configuration object returned to client: {}", JsonPrettyPrinter.print(environmentalConfiguration));
    }

    private void updateServerHardwareMpFirmwareVersion() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        TaskResource taskResource = serverHardwareClient.updateMpFirmwareVersion(
                serverHardware.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getServerHardwareIloSsoUrl() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        IloSsoUrlResult iloSsoUrl = serverHardwareClient.getIloSsoUrl(serverHardware.getResourceId());

        LOGGER.info("IloSsoUrl Result object returned to client: {}", JsonPrettyPrinter.print(iloSsoUrl));
    }

    private void getServerHardwareJavaRemoteConsoleUrl() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        JavaRemoteConsoleUrlResult javaRemoteConsoleUrl = serverHardwareClient.getJavaRemoteConsoleUrl(
                serverHardware.getResourceId());

        LOGGER.info("Java Remote Console Url Result object returned to client: {}", JsonPrettyPrinter.print(javaRemoteConsoleUrl));
    }

    private void getServerHardwareRemoteConsoleUrl() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        RemoteConsoleUrlResult remoteConsoleUrl = serverHardwareClient.getRemoteConsoleUrl(
                serverHardware.getResourceId());

        LOGGER.info("Remote Console Url Result object returned to client: {}", JsonPrettyPrinter.print(remoteConsoleUrl));
    }

    private void getServerHardwareUtilization() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        UtilizationData utilization = serverHardwareClient.getUtilization(serverHardware.getResourceId());

        LOGGER.info("Utilization Data object returned to client: {}", JsonPrettyPrinter.print(utilization));
    }

    private void getServerFirmwareInventoryByFilter() {
        FirmwareInventoryFilter filter = new FirmwareInventoryFilter();
        filter.setComponentLocation("System Board");
        filter.setComponentName("System ROM");
        filter.setComponentVersion("P70 09/30/2010");
        filter.setServerModel("ProLiant DL380p Gen8");
        filter.setServerName("172.18.6.8");

        ResourceCollection<ServerFirmwareInventory> serverFirmwareInventory = serverHardwareClient
                .getServerFirmwareInventoryByFilter(filter);

        LOGGER.info("Server Firmware Inventory object returned to client: {}", serverFirmwareInventory.toJsonString());
    }


    private void getServerFirmwareInventory() {
        ServerHardware serverHardware = this.serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        ServerFirmwareInventory serverFirmwareInventory = serverHardwareClient.getServerFirmwareInventory(serverHardware.getResourceId());

        LOGGER.info("Server Firmware Inventory object returned to client: {}", serverFirmwareInventory.toJsonString());
    }

    private void patchServerHardware() {
        ServerHardware serverHardware = this.serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        Patch patch = new Patch();
        patch.setOp(Patch.PatchOperation.add);
        patch.setPath("/scopeUris/-");
        patch.setValue("/rest/scopes/" + SCOPE_RESOURCE_ID);

        TaskResource taskResource = serverHardwareClient.patch(serverHardware.getResourceId(), patch);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }


    private ServerPowerControlRequest buildServerPowerControlRequest() {
        ServerPowerControlRequest serverPowerControlRequest = new ServerPowerControlRequest();

        serverPowerControlRequest.setPowerControl(PhysicalServerPowerControl.MomentaryPress);
        serverPowerControlRequest.setPowerState(PhysicalServerPowerState.On);

        return serverPowerControlRequest;
    }

    private AddServer buildServerHardware() {
        AddServer server = new AddServer();

        server.setHostname(SERVER_HARDWARE_HOSTNAME);
        server.setUsername(SERVER_HARDWARE_USERNAME);
        server.setPassword(SERVER_HARDWARE_PASSWORD);
        server.setLicensingIntent(LicensingIntent.OneView);
        server.setConfigurationState(ConfigurationState.Managed);
        server.setForce(false);

        return server;
    }

    public static void main(final String[] args) throws Exception {
        ServerHardwareClientSample sample = new ServerHardwareClientSample();

        sample.addServerHardware();
        sample.getServerHardware();
        sample.getServerHardwareByName();
        sample.getAllServerHardware();

        sample.updateServerHardwarePowerState();
        sample.updateServerHardwareRefreshState();
        sample.getServerHardwareEnvironmentConfiguration();

        //update works only with unmanaged device hardware (for example, iPDU)
        sample.updateServerHardwareEnvironmentConfiguration();

        sample.getServerHardwareIloSsoUrl();
        sample.getServerHardwareJavaRemoteConsoleUrl();
        sample.updateServerHardwareMpFirmwareVersion();
        sample.getServerHardwareRemoteConsoleUrl();
        sample.getServerHardwareUtilization();

        //only works with GEN9 hardware types
        sample.getServerHardwareBios();

        //operations available only in OV 2.0
        sample.removeServerHardware();
        
        //operations available only in OV 3.0
        sample.getServerFirmwareInventoryByFilter();
        sample.getServerFirmwareInventory();
        sample.patchServerHardware();
    }

}
