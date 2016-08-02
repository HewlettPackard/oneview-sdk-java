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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.PhysicalServerPowerControl;
import com.hp.ov.sdk.dto.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.servers.serverhardware.AddServer;
import com.hp.ov.sdk.dto.servers.serverhardware.ConfigurationState;
import com.hp.ov.sdk.dto.servers.LicensingIntent;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.util.JsonPrettyPrinter;

/*
 * ServerHardwareClientSample is a sample program to captures/consume details about the physical configuration of server
 * hardware, defines which settings are available to the server profiles assigned to that type of server hardware
 * It invokes APIs of ServerHardwareClient which is in sdk library to perform GET/POST operations
 * on server hardware resource
 */
public class ServerHardwareClientSample {

    private final ServerHardwareClient serverHardwareClient;

    // These are variables to be defined by user
    // ================================
    private static final String SERVER_HARDWARE_RESOURCE_NAME = "172.18.6.32";
    private static final String SERVER_HARDWARE_RESOURCE_ID = "37333036-3831-584D-5131-303030323038";
    private static final String SERVER_HARDWARE_HOSTNAME = "172.18.6.32";
    private static final String SERVER_HARDWARE_USERNAME = "dcs";
    private static final String SERVER_HARDWARE_PASSWORD = "dcs";
    // ================================

    private ServerHardwareClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        serverHardwareClient = oneViewClient.serverHardware();
    }

    private void getServerHardware() {
        ServerHardware serverHardware = serverHardwareClient.getById(SERVER_HARDWARE_RESOURCE_ID);

        System.out.println("ServerHardwareClient : getServerHardware : " +
                "ServerHardware object returned to client : " + serverHardware.toJsonString());
    }

    private void getAllServerHardware() {
        ResourceCollection<ServerHardware> serverHardware = serverHardwareClient.getAll();

        System.out.println("ServerHardwareClient : getAllServerHardware : " +
                "ServerHardware returned to client : " + serverHardware.toJsonString());
    }

    private void getServerHardwareByName() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        System.out.println("ServerHardwareClient : getServerHardwareByName : " +
                "ServerHardware object returned to client : " + serverHardware.toJsonString());
    }

    private void addServerHardware() {
        AddServer addServer = this.buildServerHardware();

        TaskResourceV2 taskResource = this.serverHardwareClient.add(addServer, false);

        System.out.println("ServerHardwareClient : addServerHardware : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void removeServerHardware() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        TaskResourceV2 taskResource = this.serverHardwareClient.remove(serverHardware.getResourceId(), false);

        System.out.println("ServerHardwareClient : removeServerHardware : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateServerHardwarePowerState() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        ServerPowerControlRequest serverPowerControlRequest = buildServerPowerControlRequest();

        TaskResourceV2 taskResource = serverHardwareClient.updatePowerState(serverHardware.getResourceId(),
                serverPowerControlRequest, false);

        System.out.println("ServerHardwareClient : updateServerHardwarePowerState : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateServerHardwareRefreshState() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        RefreshStateRequest refreshStateRequest = new RefreshStateRequest();

        refreshStateRequest.setHostname(SERVER_HARDWARE_HOSTNAME);
        refreshStateRequest.setUsername(SERVER_HARDWARE_USERNAME);
        refreshStateRequest.setPassword(SERVER_HARDWARE_PASSWORD);

        TaskResourceV2 taskResource = serverHardwareClient.updateRefreshState(serverHardware.getResourceId(),
                refreshStateRequest, false);

        System.out.println("ServerHardwareClient : updateServerHardwareRefreshState : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void getServerHardwareBios() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        BiosSettings biosSettings = serverHardwareClient.getBios(serverHardware.getResourceId());

        System.out.println("ServerHardwareClient : getServerHardwareBios : "
                + "BiosSettings object returned to client : "
                + JsonPrettyPrinter.print(biosSettings));
    }

    private void getServerHardwareEnvironmentConfiguration() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        EnvironmentalConfiguration environmentalConfiguration
                = serverHardwareClient.getEnvironmentConfiguration(serverHardware.getResourceId());

        System.out.println("ServerHardwareClient : getServerHardwareEnvironmentConfiguration : "
                + "EnvironmentalConfiguration object returned to client : "
                + JsonPrettyPrinter.print(environmentalConfiguration));
    }

    private void updateServerHardwareEnvironmentConfiguration() {
        ServerHardware serverHardware = serverHardwareClient.getByName("172.18.6.15").get(0);

        EnvironmentalConfigurationUpdate request = new EnvironmentalConfigurationUpdate();

        request.setCalibratedMaxPower(Integer.valueOf(2500));

        EnvironmentalConfiguration environmentalConfiguration = serverHardwareClient.updateEnvironmentConfiguration(
                serverHardware.getResourceId(), request);

        System.out.println("ServerHardwareClient : updateServerHardwareEnvironmentConfiguration : "
                + "EnvironmentalConfiguration object returned to client : "
                + JsonPrettyPrinter.print(environmentalConfiguration));
    }

    private void updateServerHardwareMpFirmwareVersion() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        TaskResourceV2 taskResource = serverHardwareClient.updateMpFirmwareVersion(
                serverHardware.getResourceId(), false);

        System.out.println("ServerHardwareClient : updateServerHardwareMpFirmwareVersion : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void getServerHardwareIloSsoUrl() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        IloSsoUrlResult iloSsoUrl = serverHardwareClient.getIloSsoUrl(serverHardware.getResourceId());

        System.out.println("ServerHardwareClient : getServerHardwareIloSsoUrl : "
                + "IloSsoUrlResult object returned to client : "
                + JsonPrettyPrinter.print(iloSsoUrl));
    }

    private void getServerHardwareJavaRemoteConsoleUrl() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        JavaRemoteConsoleUrlResult javaRemoteConsoleUrl = serverHardwareClient.getJavaRemoteConsoleUrl(
                serverHardware.getResourceId());

        System.out.println("ServerHardwareClient : getServerHardwareJavaRemoteConsoleUrl : "
                + "JavaRemoteConsoleUrlResult object returned to client : "
                + JsonPrettyPrinter.print(javaRemoteConsoleUrl));
    }

    private void getServerHardwareRemoteConsoleUrl() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        RemoteConsoleUrlResult remoteConsoleUrl = serverHardwareClient.getRemoteConsoleUrl(
                serverHardware.getResourceId());

        System.out.println("ServerHardwareClient : getServerHardwareRemoteConsoleUrl : "
                + "RemoteConsoleUrlResult object returned to client : "
                + JsonPrettyPrinter.print(remoteConsoleUrl));
    }

    private void getServerHardwareUtilization() {
        ServerHardware serverHardware = serverHardwareClient.getByName(SERVER_HARDWARE_RESOURCE_NAME).get(0);

        UtilizationData utilization = serverHardwareClient.getUtilization(serverHardware.getResourceId());

        System.out.println("ServerHardwareClient : getServerHardwareUtilization : "
                + "UtilizationData object returned to client : "
                + JsonPrettyPrinter.print(utilization));
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
    }

}
