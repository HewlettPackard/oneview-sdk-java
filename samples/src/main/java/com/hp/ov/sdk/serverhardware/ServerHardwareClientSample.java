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
package com.hp.ov.sdk.serverhardware;

import com.hp.ov.sdk.dto.AddServer;
import com.hp.ov.sdk.dto.BiosSettings;
import com.hp.ov.sdk.dto.ConfigurationState;
import com.hp.ov.sdk.dto.EnvironmentalConfigurationUpdate;
import com.hp.ov.sdk.dto.IloSsoUrlResult;
import com.hp.ov.sdk.dto.JavaRemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.LicensingIntent;
import com.hp.ov.sdk.dto.PhysicalServerPowerControl;
import com.hp.ov.sdk.dto.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.RefreshStateRequest;
import com.hp.ov.sdk.dto.RemoteConsoleUrlResult;
import com.hp.ov.sdk.dto.ServerHardware;
import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.UtilizationData;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

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
    private static final String resourceName = "172.18.6.31";
    private static final String resourceId = "37333036-3831-584D-5131-303030323038";
    private static final String hostname = "172.18.6.31";
    private static final String username = "dcs";
    private static final String password = "dcs";
    // ================================

    private ServerHardwareClientSample() {
        this.serverHardwareClient = ServerHardwareClientImpl.getClient();
    }

    private void getServerHardware() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            ServerHardware serverHardwareDto = serverHardwareClient.getServerHardware(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardware : "
                    + "ServerHardware object returned to client : " + serverHardwareDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardware : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardware : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardware : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardware : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardware : " + "arguments are null ");
            return;
        }
    }

    private void getServerHardwareByName() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            ServerHardware serverHardwareDto = serverHardwareClient.getServerHardwareByName(params, resourceName);
            if (serverHardwareDto != null) {
                System.out.println("ServerHardwareClientTest : getServerHardwareByName : "
                        + "server hardware object returned to client : " + serverHardwareDto.toString());
            } else {
                System.out.println("ServerHardwareClientTest : getServerHardwareByName : "
                        + "server hardware object returned to client : no server hardware found for the name" + resourceName);
            }
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareByName : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareByName : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareByName : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareByName : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareByName : " + "arguments are null ");
            return;
        }
    }

    private void getAllServerHardware() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            ServerHardwareCollection serverHardwareCollectionDto = serverHardwareClient.getAllServerHardware(params);

            System.out.println("ServerHardwareClientTest : getAllServerHardware : "
                    + "server hardware object returned to client (count) : " + serverHardwareCollectionDto.getCount());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardware : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardware : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getAllServerHardware : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardware : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardware : arguments are null ");
            return;
        }
    }

    private void updateServerHardwarePowerState() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            ServerPowerControlRequest serverPowerControlRequestDto = buildTestServerPowerControlRequest();

            String resourceId = serverHardwareClient.getId(params, resourceName);

            TaskResourceV2 taskResourceV2 = serverHardwareClient.updateServerHardwarePowerState(params, resourceId, serverPowerControlRequestDto, false);

            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwarePowerState : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void createServerHardware() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            final AddServer addServerDto = buildTestServerHardware();

            TaskResourceV2 taskResourceV2 = serverHardwareClient.createServerHardware(params, addServerDto, false);

            System.out.println("ServerHardwareClientTest : createServerHardware : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : createServerHardware : resource you are looking is not found ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareClientTest : createServerHardware : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : createServerHardware : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : createServerHardware : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : createServerHardware : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerHardwareClientTest : createServerHardware : errors in task, please check task resource for more details ");
            return;
        }
    }
    
    private void deleteServerHardware() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = serverHardwareClient.getId(params, resourceName);

            TaskResourceV2 taskResourceV2 = serverHardwareClient.deleteServerHardware(params, resourceId, false);

            System.out.println("ServerHardwareClientTest : deleteServerHardware : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : deleteServerHardware : resource you are looking is not found for delete");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : deleteServerHardware : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : deleteServerHardware : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : deleteServerHardware : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : deleteServerHardware : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerHardwareClientTest : deleteServerHardware : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getServerHardwareBios() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            BiosSettings biosSettingsDto = serverHardwareClient.getServerHardwareBios(params, resourceId);

            System.out.println("ServerHardwareClientImpl : getServerHardwareBios : "
                    + "server hardware bios object returned to client : " + biosSettingsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientImpl : getServerHardwareBios : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientImpl : getServerHardwareBios : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientImpl : getServerHardwareBios : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientImpl : getServerHardwareBios : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientImpl : getServerHardwareBios : " + "arguments are null ");
            return;
        }
    }

    private void getServerHardwareIloSsoUrl() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            IloSsoUrlResult iloSsoUrlResult = serverHardwareClient.getServerHardwareIloSsoUrl(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardwareIloSsoUrl : "
                    + "server hardware ilo sso url object returned to client : " + iloSsoUrlResult.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareIloSsoUrl : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareIloSsoUrl : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareIloSsoUrl : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareIloSsoUrl : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareIloSsoUrl : " + "arguments are null ");
            return;
        }
    }

    private void getServerHardwareJavaRemoteConsoleUrl() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            JavaRemoteConsoleUrlResult javaRemoteConsoleUrlResult
                    = serverHardwareClient.getServerHardwareJavaRemoteConsoleUrl(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardwareJavaRemoteConsoleUrl : "
                    + "server hardware java remote console url object returned to client : " + javaRemoteConsoleUrlResult.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareJavaRemoteConsoleUrl : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareJavaRemoteConsoleUrl : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareJavaRemoteConsoleUrl : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareJavaRemoteConsoleUrl : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareJavaRemoteConsoleUrl : " + "arguments are null ");
            return;
        }
    }

    private void updateServerHardwareMpFirmwareVersion() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            String resourceId = serverHardwareClient.getId(params, resourceName);

            TaskResourceV2 taskResourceV2
                    = serverHardwareClient.updateServerHardwareMpFirmwareVersion(params, resourceId, false);

            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareMpFirmwareVersion : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getServerHardwareRemoteConsoleUrl() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            RemoteConsoleUrlResult remoteConsoleUrlResult
                    = serverHardwareClient.getServerHardwareRemoteConsoleUrl(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardwareRemoteConsoleUrl : "
                    + "server hardware remote console url object returned to client : " + remoteConsoleUrlResult.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareRemoteConsoleUrl : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareRemoteConsoleUrl : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareRemoteConsoleUrl : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareRemoteConsoleUrl : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareRemoteConsoleUrl : " + "arguments are null ");
            return;
        }
    }

    private void getServerHardwareUtilization() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            UtilizationData utilizationData = serverHardwareClient.getServerHardwareUtilization(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardwareUtilization : "
                    + "server hardware utilization data object returned to client : " + utilizationData.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareUtilization : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareUtilization : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareUtilization : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareUtilization : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareUtilization : " + "arguments are null ");
            return;
        }
    }

    private void updateServerHardwareEnvironmentConfiguration() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            EnvironmentalConfigurationUpdate request = new EnvironmentalConfigurationUpdate();

            request.setCalibratedMaxPower(Integer.valueOf(2500));

            String resourceId = serverHardwareClient.getId(params, resourceName);

            EnvironmentalConfiguration response = serverHardwareClient.updateServerHardwareEnvironmentConfiguration(params,
                    resourceId, request);

            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : "
                    + "server hardware environmental configuration object returned to client : " + response.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareEnvironmentConfiguration : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getServerHardwareEnvironmentConfiguration() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            EnvironmentalConfiguration environmentalConfiguration
                    = serverHardwareClient.getServerHardwareEnvironmentConfiguration(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardwareEnvironmentConfiguration : "
                    + "server hardware environmental configuration object returned to client : " + environmentalConfiguration.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareEnvironmentConfiguration : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareEnvironmentConfiguration : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareEnvironmentConfiguration : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareEnvironmentConfiguration : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareEnvironmentConfiguration : " + "arguments are null ");
            return;
        }
    }

    private void updateServerHardwareRefreshState() {
        RestParams params = null;

        try {
            params = HPOneViewCredential.createCredentials();

            RefreshStateRequest request = new RefreshStateRequest();

            request.setHostname(hostname);
            request.setUsername(username);
            request.setPassword(password);

            String resourceId = serverHardwareClient.getId(params, resourceName);

            TaskResourceV2 taskResourceV2 = serverHardwareClient.updateServerHardwareRefreshState(params,
                    resourceId, request, false);

            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerHardwareClientTest : updateServerHardwareRefreshState : " +
                    "errors in task, please check task resource for more details ");
            return;
        }
    }

    private ServerPowerControlRequest buildTestServerPowerControlRequest() {
        final ServerPowerControlRequest serverPowerControlRequestDto = new ServerPowerControlRequest();
        serverPowerControlRequestDto.setPowerControl(PhysicalServerPowerControl.MomentaryPress);
        serverPowerControlRequestDto.setPowerState(PhysicalServerPowerState.On);
        return serverPowerControlRequestDto;
    }

    private AddServer buildTestServerHardware() {
        final AddServer dto = new AddServer();

        dto.setHostname(hostname);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setLicensingIntent(LicensingIntent.OneView);
        dto.setConfigurationState(ConfigurationState.Managed);
        dto.setForce(false);
        return dto;
    }
    
    public static void main(final String[] args) throws Exception {
        ServerHardwareClientSample client = new ServerHardwareClientSample();

        client.createServerHardware();
        client.getServerHardware();
        client.getServerHardwareByName();
        client.getAllServerHardware();
        client.updateServerHardwarePowerState();
        client.updateServerHardwareRefreshState();
        client.getServerHardwareEnvironmentConfiguration();

        /* update not working as expected */
        client.updateServerHardwareEnvironmentConfiguration();

        client.getServerHardwareIloSsoUrl();
        client.getServerHardwareJavaRemoteConsoleUrl();
        client.updateServerHardwareMpFirmwareVersion();
        client.getServerHardwareRemoteConsoleUrl();
        client.getServerHardwareUtilization();

        /* only works with GEN9 hardware types*/
        client.getServerHardwareBios();

        //operations available only in OV 2.0
        client.deleteServerHardware();
    }

}
