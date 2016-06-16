/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.serverprofiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.Patch.PatchOperation;
import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.ServerProfileHealth;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.generated.AvailableNetworks;
import com.hp.ov.sdk.dto.generated.AvailableServers;
import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.ProfilePorts;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.dto.generated.ServerProfile.ProfileAffinity;
import com.hp.ov.sdk.dto.generated.StoragePath.StorageTargetType;
import com.hp.ov.sdk.dto.samples.NetworkForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.dto.samples.ServerProfileValue;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.EnclosureGroupClientImpl;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.ServerHardwareClientImpl;
import com.hp.ov.sdk.rest.client.ServerProfileClient;
import com.hp.ov.sdk.rest.client.ServerProfileClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;
import com.hp.ov.sdk.util.samples.ResourceDtoUtilsWrapper;

/*
 * ServerProfileClientSample is a sample program capture/consume the entire server configuration managed
 * by HPE OneView. It invokes APIs of ServerProfileClient which is in sdk library to perform GET/PUT/POST/DELETE/COPY
 * operations on server profile resource
 */
public class ServerProfileClientSample {

    private final ServerProfileClient serverProfileClient;
    private final ServerHardwareClient serverHardwareClient;
    private final EnclosureGroupClient enclosureGroupClient;
    private final OneViewClient oneViewClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String serverProfileName = "ServerProfile";
    private static final String bayName = "Encl1, bay 15";
    private static final String enclosureGroupName = "encl_group";
    private static final List<String> networkNames = Arrays.asList("Prod_401", "Prod_402");
    private static final List<String> storageVolumeName = Arrays.asList("Volume101");
    private static final List<String> fcNetworkNames = Arrays.asList("FC_Network_A", "FC_Network_B");
    private static final Boolean useBayNameForServerHardwareUri = false;
    private static final String resourceId = "97655d5c-31a1-4c2a-a01e-92570dee88f8";
    // ================================

    private ServerProfileClientSample() {
        serverProfileClient = ServerProfileClientImpl.getClient();
        serverHardwareClient = ServerHardwareClientImpl.getClient();
        enclosureGroupClient = EnclosureGroupClientImpl.getClient();
        oneViewClient = OneViewClientSample.getOneViewClient();
    }

    private void getServerProfileById() throws InstantiationException, IllegalAccessException {
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

    private void getAllServerProfile() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<ServerProfile> serverProfileCollectionDto = null;
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

    private void getServerProfileByName() throws InstantiationException, IllegalAccessException {
        ServerProfile serverProfileDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileDto = serverProfileClient.getServerProfileByName(params, serverProfileName);

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

    private void getServerProfileCompliancePreview() {
        ServerProfileCompliancePreview compliancePreviewDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            compliancePreviewDto = serverProfileClient.getServerProfileCompliancePreview(params, resourceId);

            System.out.println("ServerProfileClientTest : getServerProfileCompliancePreview : Compliance Preview object returned to client : "
                    + compliancePreviewDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileCompliancePreview : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileCompliancePreview : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getServerProfileCompliancePreview : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileCompliancePreview : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileCompliancePreview : arguments are null ");
            return;
        }
    }

    private void getServerProfileMessages() {
        ServerProfileHealth healthDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            healthDto = serverProfileClient.getServerProfileMessages(params, resourceId);

            System.out.println("ServerProfileClientTest : getServerProfileMessages : Compliance Preview object returned to client : "
                    + healthDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileMessages : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileMessages : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getServerProfileMessages : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileMessages : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileMessages : arguments are null ");
            return;
        }
    }

    private void getServerProfileTransformation() {
        ServerProfile serverProfileDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch enclosure group uri from enclosure name
            final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();

            // fetch server hardware type uri from server hardware
            final String serverHardwareTypeUri = serverHardwareClient.getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            serverProfileDto = serverProfileClient.getServerProfileTransformation(params, resourceId, serverHardwareTypeUri,
                    enclosureGroupUri);

            System.out
                    .println("ServerProfileClientTest : getServerProfileTransformation : serverProfile object returned to client : "
                            + serverProfileDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getServerProfileTransformation : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileTransformation : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getServerProfileTransformation : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileTransformation : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getServerProfileTransformation : arguments are null ");
            return;
        }
    }

    private void getAvailableNetworksForServerProfile() {
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
                    .println("ServerProfileClientTest : getAvailableNetworksForServerProfile : AvailableNetworks object returned to client : "
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

    private void getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup() {
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

    private void getAvailableServersForServerProfile() {
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

    private void getAvailableServersForServerProfileUsingProfile() {
        List<AvailableServers> availableServersCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch server profile uri using template name
            final String profileUri = serverProfileClient.getServerProfileByName(params, serverProfileName).getUri();
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

    private void getAvailableStorageSystemsForServerProfile() {
        ResourceCollection<AvailableStorageSystem> storageSystemDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch enclosure group uri from enclosure name
            final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();

            // fetch server hardware type uri from server hardware
            final String serverHardwareTypeUri = serverHardwareClient.getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // then make sdk service call to get resource
            storageSystemDto = serverProfileClient.getAvailableStorageSystemsForServerProfile(params, enclosureGroupUri, serverHardwareTypeUri);

            System.out
                    .println("ServerProfileClientTest : getAvailableStorageSystemsForServerProfile : storage system object returned to client : "
                            + storageSystemDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableStorageSystemsForServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemsForServerProfile : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemsForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemsForServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemsForServerProfile : arguments are null ");
            return;
        }
    }

    private void getAvailableStorageSystemForServerProfile() {
        AvailableStorageSystem storageSystemDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch enclosure group uri from enclosure name
            final String enclosureGroupUri = enclosureGroupClient.getEnclosureGroupByName(params, enclosureGroupName).getUri();

            // fetch server hardware type uri from server hardware
            final String serverHardwareTypeUri = serverHardwareClient.getServerHardwareByName(params, bayName)
                    .getServerHardwareTypeUri();

            // fetch storage system uri from server hardware
            String storageSystemId = serverProfileClient.getAvailableStorageSystemsForServerProfile(params, enclosureGroupUri, serverHardwareTypeUri)
                    .getMembers().get(0).getStorageSystemUri();
            // Use just the {ID} not "/rest/storage-systems/{ID}"
            storageSystemId = storageSystemId.replace("/rest/storage-systems/", "");

            // then make sdk service call to get resource
            storageSystemDto = serverProfileClient.getAvailableStorageSystemForServerProfile(params, enclosureGroupUri, serverHardwareTypeUri,
                    storageSystemId);

            System.out
                    .println("ServerProfileClientTest : getAvailableStorageSystemForServerProfile : storage system object returned to client : "
                            + storageSystemDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableStorageSystemForServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemForServerProfile : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemForServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAvailableStorageSystemForServerProfile : arguments are null ");
            return;
        }
    }

    private void getAvailableTargetsForServerProfile() {
        AvailableTargets targetsDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            targetsDto = serverProfileClient.getAvailableTargetsForServerProfile(params);

            System.out
                    .println("ServerProfileClientTest : getAvailableTargetsForServerProfile : storage system object returned to client : "
                            + targetsDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out
                    .println("ServerProfileClientTest : getAvailableTargetsForServerProfile : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : getAvailableTargetsForServerProfile : no such url : "
                    + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : getAvailableTargetsForServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : getAvailableTargetsForServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : getAvailableTargetsForServerProfile : arguments are null ");
            return;
        }
    }

    private void getProfilePortsForServerProfile() {
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

    private void createServerProfile() throws InstantiationException, IllegalAccessException {
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

    private void updateServerProfile() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        ServerProfile serverProfileDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            serverProfileDto = serverProfileClient.getServerProfileByName(params, serverProfileName);

            serverProfileDto.setName(serverProfileName + "_Update");

            if (null != serverProfileDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(serverProfileDto.getUri());
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

    private void patchServerProfile() {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            Patch patchDto = new Patch();

            // Server Profile patch supports the update of template compliance status
            patchDto.setOp(PatchOperation.replace);
            patchDto.setPath("/templateCompliance");
            patchDto.setValue("Compliant");

            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileClient.patchServerProfile(params, resourceId, patchDto, false);

            System.out.println("ServerProfileClientTest : patchServerProfile : task object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileClientTest : patchServerProfile : resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerProfileClientTest : patchServerProfile : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileClientTest : patchServerProfile : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileClientTest : patchServerProfile : Applicance Not reachabe at : " + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileClientTest : patchServerProfile : No response from appliance : " + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileClientTest : patchServerProfile : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerProfileClientTest : patchServerProfile : errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteServerProfile() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // get resource ID
            resourceId = serverProfileClient.getId(params, serverProfileName);

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

    private void deleteServerProfileByFilter() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        Boolean matches = false;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfileByFilter(params, serverProfileName, matches, false);

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

    private void deleteServerProfileByFilterWithMatch() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        Boolean matches = true;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileClient.deleteServerProfileByFilter(params, "_Temp", matches, false);

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

    private ServerProfile buildTestServerProfileDto(final RestParams params) {

        final ServerProfileValue serverProfileValue = new ServerProfileValue();
        final Firmware firmware = new Firmware();
        firmware.setFirmwareBaselineUri(null);
        firmware.setForceInstallFirmware(false);
        firmware.setManageFirmware(false);

        final Boot boot = new Boot();
        final List<String> order = new ArrayList<String>();
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
        serverProfileValue.setTemplateName(serverProfileName);
        serverProfileValue.setUseBayNameForServerHardwareUri(useBayNameForServerHardwareUri);
        serverProfileValue.setWwnType(ServerProfile.AssignmentType.Virtual);

        ResourceDtoUtilsWrapper resourceDtoUtilsWrapper = new ResourceDtoUtilsWrapper(new ResourceDtoUtils(oneViewClient));
        return resourceDtoUtilsWrapper.buildServerProfile(params, serverProfileValue);
    }

    public static void main(final String[] args) throws Exception {
        ServerProfileClientSample client = new ServerProfileClientSample();

        client.createServerProfile();
        client.getServerProfileById();
        client.getAllServerProfile();
        client.getServerProfileByName();
        client.getAvailableNetworksForServerProfile();

        client.getAvailableServersForServerProfile();
        client.getAvailableServersForServerProfileUsingProfile();
        client.getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup();

        client.getProfilePortsForServerProfile();

        /*
        server profile must be manually associated with a server profile template
        before running some of the methods below
        */
        client.getServerProfileCompliancePreview(); //needs a template
        client.getServerProfileMessages();
        client.getServerProfileTransformation();

        client.getAvailableStorageSystemsForServerProfile(); //needs a template
        client.getAvailableStorageSystemForServerProfile(); //needs a template

        client.getAvailableTargetsForServerProfile();

        client.patchServerProfile(); //needs a template

        client.updateServerProfile();

        client.deleteServerProfile();
        client.deleteServerProfileByFilter();
        client.deleteServerProfileByFilterWithMatch();
    }

}
