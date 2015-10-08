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
package com.hp.ov.sdk.serverprofiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.bean.factory.HPOneViewSdkBeanFactory;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;
import com.hp.ov.sdk.dto.samples.NetworkForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.dto.ServerProfileCollection;
import com.hp.ov.sdk.dto.samples.ServerProfileValue;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile.ProfileAffinity;
import com.hp.ov.sdk.dto.generated.StoragePath.StorageTargetType;
import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.ServerProfileClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;
import com.hp.ov.sdk.util.samples.ResourceDtoUtilsWrapper;

/*
 * ServerProfileClientSample is a sample program capture/consume the entire server configuration managed 
 * by HP OneView. It invokes APIs of ServerProfileClient which is in sdk library to perform GET/PUT/POST/DELETE/COPY
 * operations on server profile resource
 */
public class ServerProfileClientSample {
    private RestParams params;
    private static UrlUtils urlUtils;
    private static TaskResourceV2 taskResourceV2;
    private static ServerProfileClient serverProfileClient;
    private static ServerHardwareClient serverHardwareClient;
    private static EnclosureGroupClient enclosureGroupClient;

    // test values - user input
    // ================================
    private static final String templateName = "ServerProfile_Template"; // used
    // for
    // delete,
    // get of server
    // profile or server
    // profile template
    private static final String bayName = "Encl1, bay 15";
    private static final String profileName = "ServerProfile-Bay15";
    private static final String enclosureGroupName = "Enclosure_Test";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402");
    private static final List<String> storageVolumeName = Arrays.asList("Volume101");
    private static final List<String> fcNetworkNames = Arrays.asList("FC_Network_A", "FC_Network_B");
    private static final Boolean useBayNameForServerHardwareUri = false;
    private static final String resourceId = "10fbed31-21cd-4c40-9401-3d70511cff00";

    // ================================

    private static void init() {
        urlUtils = HPOneViewSdkBeanFactory.getUrlUtils();
        serverProfileClient = HPOneViewSdkBeanFactory.getServerProfileClient();
        serverHardwareClient = HPOneViewSdkBeanFactory.getServerHardwareClient();
        enclosureGroupClient = HPOneViewSdkBeanFactory.getEnclosureGroupClient();
    }

    public void getServerProfileById() throws InstantiationException, IllegalAccessException {
        ServerProfile serverProfileDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileDto = serverProfileClient.getServerProfile(params, resourceId);

            System.out.println("ServerProfileClientTest : getServerProfileById : serverProfile object returned to client : "
                    + serverProfileDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileById : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getServerProfileById : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileById : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileById : arguments are null ");
            return;
        }

    }

    public void getAllServerProfile() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ServerProfileCollection serverProfileCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileCollectionDto = serverProfileClient.getAllServerProfile(params);

            System.out.println("ServerProfileClientTest : getAllServerProfile : serverProfile object returned to client : "
                    + serverProfileCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : getAllServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAllServerProfile : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getAllServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getAllServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAllServerProfile : arguments are null ");
            return;
        }
    }

    public void getServerProfileByName() throws InstantiationException, IllegalAccessException {
        ServerProfile serverProfileDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileDto = serverProfileClient.getServerProfileByName(params, templateName);

            System.out.println("ServerProfileClientTest : getServerProfileByName : serverProfile object returned to client : "
                    + serverProfileDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getServerProfileByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileByName : arguments are null ");
            return;
        }

    }

    public void getAvailableNetworksForServerProfile() {
        AvailableNetworks availableNetworksDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch enclosure group uri from enclosure name
            final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();

            // fetch server hardware type uri from server hardware
            final String serverHardwareTypeUri = serverHardwareClient.getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            availableNetworksDto = serverProfileClient.getAvailableNetworksForServerProfile(params, serverHardwareTypeUri,
                    enclosureGroupUri);

            System.out
                    .println("ServerProfileClientTest : getAvailableNetworksForServerProfile : serverProfile object returned to client : "
                            + availableNetworksDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableNetworksForServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAvailableNetworksForServerProfile : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getAvailableNetworksForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getAvailableNetworksForServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAvailableNetworksForServerProfile : arguments are null ");
            return;
        }

    }

    public void getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup() {
        List<AvailableServers> availableServersCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch enclosure group uri from enclosure name
            final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();

            // fetch server hardware type uri from server hardware
            final String serverHardwareTypeUri = serverHardwareClient.getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            availableServersCollectionDto = serverProfileClient.getAvailableServersForServerProfile(params, serverHardwareTypeUri,
                    enclosureGroupUri);

            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : serverProfile object returned to client : "
                            + availableServersCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : no such url : "
                            + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : Applicance Not reachabe at : "
                            + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : No response from appliance : "
                            + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : arguments are null ");
            return;
        }
    }

    public void getAvailableServersForServerProfile() {
        List<AvailableServers> availableServersCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            availableServersCollectionDto = serverProfileClient.getAvailableServersForServerProfile(params);

            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfile : serverProfile object returned to client : "
                            + availableServersCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAvailableServersForServerProfile : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getAvailableServersForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getAvailableServersForServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAvailableServersForServerProfile : arguments are null ");
            return;
        }
    }

    public void getAvailableServersForServerProfileUsingProfile() {
        List<AvailableServers> availableServersCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch server profile uri using template name
            final String profileUri = serverProfileClient.getServerProfileByName(params, templateName).getUri();
            // then make sdk service call to get resource
            availableServersCollectionDto = serverProfileClient.getAvailableServersForServerProfile(params, profileUri);

            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingProfile : serverProfile object returned to client : "
                            + availableServersCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAvailableServersForServerProfileUsingProfile : no such url : "
                    + params.getHostname());
            return;

        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingProfile : Applicance Not reachabe at : "
                            + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableServersForServerProfileUsingProfile : No response from appliance : "
                            + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAvailableServersForServerProfileUsingProfile : arguments are null ");
            return;
        }
    }

    public void getProfilePortsForServerProfile() {
        ProfilePorts profilePortsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch enclosure group uri from enclosure name
            final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();

            // fetch server hardware type uri from server hardware
            final String serverHardwareTypeUri = serverHardwareClient.getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            profilePortsDto = serverProfileClient.getProfilePortsForServerProfile(params, serverHardwareTypeUri, enclosureGroupUri);

            System.out
                    .println("ServerProfileClientTest : getProfilePortsForServerProfile : serverProfile object returned to client : "
                            + profilePortsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : getProfilePortsForServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getProfilePortsForServerProfile : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getProfilePortsForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getProfilePortsForServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getProfilePortsForServerProfile : arguments are null ");
            return;
        }
    }

    public void createServerProfile() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create serverProfile request body
            final ServerProfile serverProfileDto = buildTestServerProfileDto(params);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileClient.createServerProfile(params, serverProfileDto, false, false);

            System.out.println("ServerProfileClientTest : createServerProfile : serverProfile object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : createServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerProfileClientTest : createServerProfile : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : createServerProfile : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : createServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : createServerProfile : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerProfileClientTest : createServerProfile : errors in task, please check task resource for more details ");
            return;
        }

    }

    public void updateServerProfile() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        ServerProfile serverProfileDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            serverProfileDto = serverProfileClient.getServerProfileByName(params, templateName);

            serverProfileDto.setName(templateName);

            if (null != serverProfileDto.getUri()) {
                resourceId = urlUtils.getResourceIdFromUri(serverProfileDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileClient.updateServerProfile(params, resourceId, serverProfileDto, false, false);

            System.out.println("ServerProfileClientTest : updateServerProfile : " + "serverProfile object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : updateServerProfile :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerProfileClientTest : updateServerProfile :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : updateServerProfile :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : updateServerProfile :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : updateServerProfile :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : updateServerProfile : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerProfileClientTest : updateServerProfile : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    public void deleteServerProfile() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = serverProfileClient.getId(params, templateName);

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfile(params, resourceId, false);

            System.out.println("ServerProfileClientTest : deleteServerProfile : serverProfile object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfile : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfile : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : deleteServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfile : arguments are null ");
            return;
        }

    }

    public void deleteServerProfileByFilter() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        Boolean matches = false;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfileByFilter(params, templateName, matches, false);

            System.out.println("ServerProfileClientTest : deleteServerProfileByFilter : serverProfile object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : deleteServerProfileByFilter : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilter : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilter : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilter : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilter : arguments are null ");
            return;
        }
    }

    public void deleteServerProfileByFilterWithMatch() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        Boolean matches = true;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfileByFilter(params, templateName, matches, false);

            System.out
                    .println("ServerProfileClientTest : deleteServerProfileByFilterWithMatch : serverProfile object returned to client : "
                            + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : deleteServerProfileByFilterWithMatch : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilterWithMatch : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilterWithMatch : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilterWithMatch : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : deleteServerProfileByFilterWithMatch : arguments are null ");
            return;
        }

    }

    public void copyServerProfile() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.copyServerProfile(params, templateName, bayName, profileName, false);

            System.out.println("ServerProfileClientTest : copyServerProfile : serverProfile object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : copyServerProfile : resource you are looking is not found : ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : copyServerProfile : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out
                    .println("ServerProfileClientTest : copyServerProfile : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out
                    .println("ServerProfileClientTest : copyServerProfile : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : copyServerProfile : arguments are null ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerProfileClientTest : copyServerProfile : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerProfileClientTest : copyServerProfile : errors in task, please check task resource for more details ");
            return;
        }

    }

    private ServerProfile buildTestServerProfileDto(final RestParams params) {

        final ServerProfileValue serverProfileValue = new ServerProfileValue();
        final Firmware firmware = new Firmware();
        firmware.setFirmwareBaselineUri(null);
        firmware.setForceInstallFirmware(false);
        firmware.setManageFirmware(false);

        final Boot boot = new Boot();
        final List<String> order = new ArrayList<String>();
        order.add("PXE");
        order.add("CD");
        order.add("Floppy");
        order.add("USB");
        order.add("HardDisk");
        boot.setOrder(order);
        boot.setManageBoot(true);

        final Bios bios = new Bios();
        bios.setManageBios(false);
        bios.setOverriddenSettings(null);

        final List<NetworkForServerProfile> networkForServerProfiles = new ArrayList<NetworkForServerProfile>();
        for (final String networkName : networkNames) {
            final NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(1000);
            networkForServerProfile.setAllocatedMbps(1000);
            networkForServerProfile.setRequestedMbps("1000");
            networkForServerProfile.setNetworkType(ProfileConnectionV3.FunctionType.Ethernet);
            networkForServerProfiles.add(networkForServerProfile);
        }

        for (final String networkName : fcNetworkNames) {
            final NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(2500);
            networkForServerProfile.setAllocatedMbps(2500);
            networkForServerProfile.setRequestedMbps("2500");
            networkForServerProfile.setNetworkType(ProfileConnectionV3.FunctionType.FibreChannel);
            networkForServerProfiles.add(networkForServerProfile);
        }

        final SanStorageForServerProfile sanStorageForServerProfile = new SanStorageForServerProfile();
        sanStorageForServerProfile.setHostOSType("Windows 2012 / WS2012 R2");
        final List<StorageVolume> storageVolumes = new ArrayList<StorageVolume>();
        for (final String volumeName : storageVolumeName) {
            final StorageVolume storageVolume = sanStorageForServerProfile.createStorageVolume();
            storageVolume.setIsEnabled(true);
            storageVolume.setStorageTargets(null);
            storageVolume.setStorageTargetType(StorageTargetType.Auto);
            storageVolume.setVolumeName(volumeName);
            storageVolume.setLunType("Auto");
            storageVolumes.add(storageVolume);
        }
        sanStorageForServerProfile.setStorageVolume(storageVolumes);

        serverProfileValue.setAffinity(ProfileAffinity.Bay);
        serverProfileValue.setBayName(bayName);
        serverProfileValue.setBios(bios);
        serverProfileValue.setBoot(boot);
        serverProfileValue.setDescription("Template Example");
        serverProfileValue.setEnclosureGroupName(enclosureGroupName);
        serverProfileValue.setFirmware(firmware);
        serverProfileValue.setLocalStorage(null);
        serverProfileValue.setMacType(ServerProfile.AssignmentType.Virtual);
        serverProfileValue.setNetworkForServerProfile(networkForServerProfiles);
        serverProfileValue.setSerialNumberType(ServerProfile.AssignmentType.Physical);
        serverProfileValue.setStorageVolumeForServerProfile(sanStorageForServerProfile);
        serverProfileValue.setTemplateName(templateName);
        serverProfileValue.setUseBayNameForServerHardwareUri(useBayNameForServerHardwareUri);
        serverProfileValue.setWwnType(ServerProfile.AssignmentType.Virtual);

        ResourceDtoUtilsWrapper resourceDtoUtilsWrapper = new ResourceDtoUtilsWrapper();
        return resourceDtoUtilsWrapper.buildServerProfile(params, serverProfileValue);
    }

    // Main
    public static void main(final String[] args) throws Exception {
        init();
        ServerProfileClientSample client = new ServerProfileClientSample();
        client.getServerProfileById();
        client.getAllServerProfile();
        client.createServerProfile();
        client.getServerProfileByName();
        client.getAvailableNetworksForServerProfile();
        client.getAvailableServersForServerProfile();
        client.getAvailableServersForServerProfileUsingProfile();
        client.getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup();
        client.getProfilePortsForServerProfile();
        client.updateServerProfile();
        client.copyServerProfile();
        client.deleteServerProfile();
        client.deleteServerProfileByFilter();
        client.deleteServerProfileByFilterWithMatch();
    }
}
