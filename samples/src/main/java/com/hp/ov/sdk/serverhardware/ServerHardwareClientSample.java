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
package com.hp.ov.sdk.serverhardware;

import com.hp.ov.sdk.dto.AddServer;
import com.hp.ov.sdk.dto.BiosSettingsStateCollection;
import com.hp.ov.sdk.dto.ConfigurationState;
import com.hp.ov.sdk.dto.PhysicalServerPowerControl;
import com.hp.ov.sdk.dto.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.ServerHardwareCollection;
import com.hp.ov.sdk.dto.ServerPowerControlRequest;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.ServerHardware;
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

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // These are variables to be defined by user
    // ================================
    private static final String resourceName = "Encl1, bay 15";
    private static final String resourceId = "31393736-3831-4753-4831-30305837524E";
    private static final String hostname = "172.18.1.13";
    private static final String username = "dcs";
    private static final String password = "dcs";
    // ================================

    private ServerHardwareClientSample() {
        this.serverHardwareClient = ServerHardwareClientImpl.getClient();
    }

    private void getServerHardwareById() throws InstantiationException, IllegalAccessException {
        ServerHardware serverHardwareDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make rest call to get resource
            serverHardwareDto = serverHardwareClient.getServerHardware(params, resourceId);

            System.out.println("ServerHardwareClientTest : getServerHardwareById : "
                    + "server hardware object returned to client : " + serverHardwareDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareById : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareById : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareById : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareById : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareById : " + "arguments are null ");
            return;
        }

    }

    private void getAllServerHardwares() {
        ServerHardwareCollection serverHardwareCollectionDto = null;
        try {

            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make rest call to get resource
            serverHardwareCollectionDto = serverHardwareClient.getAllServerHardwares(params);

            System.out.println("ServerHardwareClientTest : getAllServerHardwares : "
                    + "server hardware object returned to client : " + serverHardwareCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardwares : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardwares : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getAllServerHardwares : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardwares : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getAllServerHardwares : arguments are null ");
            return;
        }

    }

    private void getServerHardwareWithNoProfile() throws InstantiationException, IllegalAccessException {

        ServerHardwareCollection serverHardwareCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make rest call to get resource
            serverHardwareCollectionDto = serverHardwareClient.getServerHardwareWithNoProfile(params);

            System.out.println("ServerHardwareClientTest : getServerHardwareWithNoProfile : "
                    + "server hardware collection object returned to client : " + serverHardwareCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareWithNoProfile : "
                    + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareWithNoProfile : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getServerHardwareWithNoProfile : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareWithNoProfile : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getServerHardwareWithNoProfile : " + "arguments are null ");
            return;
        }

    }

    private void getServerHardwareByName() throws InstantiationException, IllegalAccessException {
        ServerHardware serverHardwareDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make rest call to get resource
            serverHardwareDto = serverHardwareClient.getServerHardwareByName(params, resourceName);
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

    private void powerServer() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        ServerPowerControlRequest serverPowerControlRequestDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            serverPowerControlRequestDto = buildTestserverPowerControlRequestDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */

            // get resource ID
            resourceId = serverHardwareClient.getId(params, resourceName);

            taskResourceV2 = serverHardwareClient.powerServer(params, resourceId, serverPowerControlRequestDto, false, false);

            System.out
                    .println("ServerHardwareClientTest : powerServer : ServerPowerControlRequest group object returned to client : "
                            + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : powerServer : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerHardwareClientTest : powerServer : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : powerServer : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : powerServer : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : powerServer : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerHardwareClientTest : powerServer : errors in task, please check task resource for more details ");
            return;
        }
    }

    private void getPowerState() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        String powerState = null;
        String resourceId = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = serverHardwareClient.getId(params, resourceName);

            powerState = serverHardwareClient.getPowerState(params, resourceId);

            System.out.println("ServerHardwareClientTest : getPowerState : " + "server hardware power state returned to client : "
                    + powerState);
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientTest : getPowerState : " + "resource not found : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientTest : getPowerState : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientTest : getPowerState : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientTest : getPowerState : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientTest : getPowerState : " + "arguments are null ");
            return;
        }

    }

    private void createServerHardware() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create network request body
            final AddServer addServerDto = buildTestServerHardwareDto();
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverHardwareClient.createServerHardware(params, addServerDto, false, false);

            System.out.println("ServerHardwareClientTest : createServerHardware : server hardware object returned to client : "
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

    private void getBiosForServerHardware() throws InstantiationException, IllegalAccessException {
        BiosSettingsStateCollection biosSettingsStateCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make rest call to get resource
            biosSettingsStateCollectionDto = serverHardwareClient.getBiosForServerHardware(params, resourceId);

            System.out.println("ServerHardwareClientImpl : getBiosForServerHardware : "
                    + "server hardware object returned to client : " + biosSettingsStateCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerHardwareClientImpl : getBiosForServerHardware : " + "resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerHardwareClientImpl : getBiosForServerHardware : " + "no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerHardwareClientImpl : getBiosForServerHardware : " + "Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerHardwareClientImpl : getBiosForServerHardware : " + "No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerHardwareClientImpl : getBiosForServerHardware : " + "arguments are null ");
            return;
        }

    }

    private ServerPowerControlRequest buildTestserverPowerControlRequestDto() {
        final ServerPowerControlRequest serverPowerControlRequestDto = new ServerPowerControlRequest();
        serverPowerControlRequestDto.setPowerControl(PhysicalServerPowerControl.MomentaryPress);
        serverPowerControlRequestDto.setPowerState(PhysicalServerPowerState.On);
        return serverPowerControlRequestDto;
    }

    private AddServer buildTestServerHardwareDto() {
        final AddServer dto = new AddServer();
        dto.setHostname(hostname);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setLicensingIntent(ServerHardware.LicensingIntent.OneView);
        dto.setConfigurationState(ConfigurationState.Managed);
        dto.setForce(false);
        return dto;
    }

    public static void main(final String[] args) throws Exception {
        ServerHardwareClientSample client = new ServerHardwareClientSample();

        client.getServerHardwareById();
        client.getAllServerHardwares();
        client.getPowerState();
        client.getServerHardwareByName();
        client.powerServer();
        client.getServerHardwareWithNoProfile();
        client.createServerHardware();
        client.getBiosForServerHardware();
    }

}
