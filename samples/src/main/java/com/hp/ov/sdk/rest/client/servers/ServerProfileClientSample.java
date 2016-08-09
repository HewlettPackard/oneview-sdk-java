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
package com.hp.ov.sdk.rest.client.servers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Optional;
import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.AvailableStorageSystem;
import com.hp.ov.sdk.dto.AvailableTargets;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;
import com.hp.ov.sdk.dto.Patch;
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
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.util.JsonPrettyPrinter;
import com.hp.ov.sdk.util.ResourceDtoUtils;
import com.hp.ov.sdk.util.UrlUtils;
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

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "6e34a417-b64b-489f-8132-a3b17eeda285";
    private static final String SERVER_PROFILE_NAME = "server-profile";
    private static final String SERVER_PROFILE_NAME_UPDATED = SERVER_PROFILE_NAME + "_Updated";
    private static final String BAY_NAME = "Encl1, bay 15";
    private static final String ENCLOSURE_GROUP_NAME = "encl_group";
    private static final List<String> NETWORK_NAMES = Arrays.asList("Prod_401", "Prod_402");
    private static final List<String> STORAGE_VOLUME_NAME = Arrays.asList("Volume101");
    private static final List<String> FC_NETWORK_NAMES = Arrays.asList("FC_Network_A", "FC_Network_B");
    private static final Boolean USE_BAY_NAME_FOR_SERVER_HARDWARE_URI = false;
    // ================================

    private ServerProfileClientSample() {
        oneViewClient = OneViewClientSample.getOneViewClient();

        serverProfileClient = oneViewClient.serverProfile();
        serverHardwareClient = oneViewClient.serverHardware();
        enclosureGroupClient = oneViewClient.enclosureGroup();
    }

    private void getServerProfileById() {
        ServerProfile serverProfile = serverProfileClient.getById(RESOURCE_ID);

        System.out.println("ServerProfileClientSample : getServerProfileById : " +
                "ServerProfile object returned to client : " + serverProfile.toJsonString());
    }

    private void getAllServerProfiles() {
        ResourceCollection<ServerProfile> serverProfiles = serverProfileClient.getAll();

        System.out.println("ServerProfileClientSample : getAllServerProfiles : " +
                "Server profiles returned to client : " + serverProfiles.toJsonString());
    }

    private void getServerProfileByName() {
        ServerProfile serverProfile = serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        System.out.println("ServerProfileClientSample : getServerProfileByName : " +
                "ServerProfile object returned to client : " + serverProfile.toJsonString());
    }

    private void createServerProfile() {
        ServerProfile serverProfile = buildServerProfile();

        TaskResourceV2 taskResource = serverProfileClient.create(serverProfile, false);

        System.out.println("ServerProfileClientSample : createServerProfile : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void deleteServerProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME_UPDATED).get(0);

        TaskResourceV2 taskResource = serverProfileClient.delete(serverProfile.getResourceId(), false);

        System.out.println("ServerProfileClientSample : deleteServerProfile : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void deleteServerProfileByFilter() {
        String filter = "name='" + SERVER_PROFILE_NAME +"'";

        TaskResourceV2 taskResource = this.serverProfileClient.deleteByFilter(filter, false);

        System.out.println("ServerProfileClientSample : deleteServerProfileByFilter : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateServerProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        serverProfile.setName(SERVER_PROFILE_NAME_UPDATED);

        TaskResourceV2 taskResource = serverProfileClient.update(serverProfile.getResourceId(), serverProfile, false);

        System.out.println("ServerProfileClientSample : updateServerProfile : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void patchServerProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        Patch patch = new Patch();

        // Server Profile patch supports the update of template compliance status
        patch.setOp(Patch.PatchOperation.replace);
        patch.setPath("/templateCompliance");
        patch.setValue("Compliant");

        TaskResourceV2 taskResource = serverProfileClient.patch(serverProfile.getResourceId(), patch, false);

        System.out.println("ServerProfileClientSample : patchServerProfile : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void getServerProfileCompliancePreview() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        ServerProfileCompliancePreview compliance = serverProfileClient.getCompliancePreview(
                serverProfile.getResourceId());

        System.out.println("ServerProfileClientSample : getServerProfileCompliancePreview : " +
                "ServerProfileCompliancePreview object returned to client : " + JsonPrettyPrinter.print(compliance));
    }

    private void getServerProfileMessages() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        ServerProfileHealth health = serverProfileClient.getMessages(serverProfile.getResourceId());

        System.out.println("ServerProfileClientSample : getServerProfileCompliancePreview : " +
                "ServerProfileCompliancePreview object returned to client : " + JsonPrettyPrinter.print(health));
    }

    private void getServerProfileTransformation() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        String serverHardwareTypeUri = serverHardwareClient.getByName(BAY_NAME).get(0).getServerHardwareTypeUri();
        String enclosureGroupUri = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0).getUri();

        ServerProfile serverProfileUpdated = serverProfileClient.getTransformation(
                serverProfile.getResourceId(), serverHardwareTypeUri, enclosureGroupUri);

        System.out.println("ServerProfileClientSample : getServerProfileTransformation : " +
                "ServerProfile object returned to client : " + serverProfileUpdated.toJsonString());
    }

    private void getAvailableNetworksForServerProfile() {
        String serverHardwareTypeUri = serverHardwareClient.getByName(BAY_NAME).get(0).getServerHardwareTypeUri();
        String enclosureGroupUri = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0).getUri();

        AvailableNetworks availableNetworks = serverProfileClient.getAvailableNetworks(
                serverHardwareTypeUri, enclosureGroupUri);

        System.out.println("ServerProfileClientSample : getServerProfileAvailableNetworks : " +
                "AvailableNetworks object returned to client : " + JsonPrettyPrinter.print(availableNetworks));
    }

    private void getAvailableServersForServerProfile() {
        List<AvailableServers> availableServers = serverProfileClient.getAvailableServers();

        System.out.println("ServerProfileClientSample : getAvailableServersForServerProfile : " +
                "AvailableServers returned to client : " + JsonPrettyPrinter.print(availableServers));
    }

    private void getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup() {
        String enclosureGroupUri = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0).getUri();
        String serverHardwareTypeUri = serverHardwareClient.getByName(BAY_NAME).get(0).getServerHardwareTypeUri();

        List<AvailableServers> availableServers = serverProfileClient.getAvailableServers(
                serverHardwareTypeUri, enclosureGroupUri);

        System.out.println("ServerProfileClientSample : getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup : " +
                "AvailableServers returned to client : " + JsonPrettyPrinter.print(availableServers));
    }

    private void getAvailableServersForServerProfileUsingProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        List<AvailableServers> availableServers = serverProfileClient.getAvailableServers(serverProfile.getUri());

        System.out.println("ServerProfileClientSample : getAvailableServersForServerProfileUsingProfile : " +
                "AvailableServers returned to client : " + JsonPrettyPrinter.print(availableServers));
    }

    private void getAvailableStorageSystemsForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0).getUri();
        String serverHardwareTypeUri = serverHardwareClient.getByName(BAY_NAME).get(0).getServerHardwareTypeUri();

        ResourceCollection<AvailableStorageSystem> storageSystems = serverProfileClient.getAvailableStorageSystems(
                serverHardwareTypeUri, enclosureGroupUri);

        System.out.println("ServerProfileClientSample : getAvailableStorageSystemsForServerProfile : " +
                "AvailableStorageSystem returned to client : " + storageSystems.toJsonString());
    }

    private void getAvailableStorageSystemForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0).getUri();
        String serverHardwareTypeUri = serverHardwareClient.getByName(BAY_NAME).get(0).getServerHardwareTypeUri();

        AvailableStorageSystem storageSystem = serverProfileClient.getAvailableStorageSystems(
                serverHardwareTypeUri, enclosureGroupUri).getMembers().get(0);

        // Use just the {ID} not "/rest/storage-systems/{ID}"
        AvailableStorageSystem availableStorageSystem = serverProfileClient.getAvailableStorageSystem(
                serverHardwareTypeUri,
                enclosureGroupUri,
                UrlUtils.getResourceIdFromUri(storageSystem.getStorageSystemUri()));

        System.out.println("ServerProfileClientSample : getAvailableStorageSystemForServerProfile : " +
                "AvailableStorageSystem object returned to client : " + JsonPrettyPrinter.print(availableStorageSystem));
    }

    private void getAvailableTargetsForServerProfile() {
        AvailableTargets targets = serverProfileClient.getAvailableTargets(
                Optional.<String>absent(), Optional.<String>absent(), Optional.<String>absent());

        System.out.println("ServerProfileClientSample : getAvailableTargetsForServerProfile : " +
                "AvailableTargets object returned to client : " + targets.toJsonString());
    }

    private void getProfilePortsForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(ENCLOSURE_GROUP_NAME).get(0).getUri();
        String serverHardwareTypeUri = serverHardwareClient.getByName(BAY_NAME).get(0).getServerHardwareTypeUri();

        ProfilePorts profilePorts = serverProfileClient.getProfilePorts(serverHardwareTypeUri, enclosureGroupUri);

        System.out.println("ServerProfileClientSample : getProfilePortsForServerProfile : " +
                "ProfilePorts object returned to client : " + JsonPrettyPrinter.print(profilePorts));
    }

    private ServerProfile buildServerProfile() {
        ServerProfileValue serverProfileValue = new ServerProfileValue();
        Firmware firmware = new Firmware();
        firmware.setFirmwareBaselineUri(null);
        firmware.setForceInstallFirmware(false);
        firmware.setManageFirmware(false);

        Boot boot = new Boot();
        List<String> order = new ArrayList<>();
        order.add("HardDisk");
        boot.setOrder(order);
        boot.setManageBoot(true);

        Bios bios = new Bios();
        bios.setManageBios(false);
        bios.setOverriddenSettings(null);

        List<NetworkForServerProfile> networkForServerProfiles = new ArrayList<>();
        for (String networkName : NETWORK_NAMES) {
            NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(1000);
            networkForServerProfile.setAllocatedMbps(1000);
            networkForServerProfile.setRequestedMbps("1000");
            networkForServerProfile.setNetworkType(ProfileConnectionV3.FunctionType.Ethernet);
            networkForServerProfiles.add(networkForServerProfile);
        }

        for (String networkName : FC_NETWORK_NAMES) {
            NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(2500);
            networkForServerProfile.setAllocatedMbps(2500);
            networkForServerProfile.setRequestedMbps("2500");
            networkForServerProfile.setNetworkType(ProfileConnectionV3.FunctionType.FibreChannel);
            networkForServerProfiles.add(networkForServerProfile);
        }

        SanStorageForServerProfile sanStorageForServerProfile = new SanStorageForServerProfile();
        sanStorageForServerProfile.setHostOSType("Windows 2012 / WS2012 R2");
        List<StorageVolume> storageVolumes = new ArrayList<>();
        for (String volumeName : STORAGE_VOLUME_NAME) {
            StorageVolume storageVolume = sanStorageForServerProfile.createStorageVolume();
            storageVolume.setIsEnabled(true);
            storageVolume.setStorageTargets(null);
            storageVolume.setStorageTargetType(StorageTargetType.Auto);
            storageVolume.setVolumeName(volumeName);
            storageVolume.setLunType("Auto");
            storageVolumes.add(storageVolume);
        }
        sanStorageForServerProfile.setStorageVolume(storageVolumes);

        serverProfileValue.setAffinity(ProfileAffinity.Bay);
        serverProfileValue.setBayName(BAY_NAME);
        serverProfileValue.setBios(bios);
        serverProfileValue.setBoot(boot);
        serverProfileValue.setDescription("Template Example");
        serverProfileValue.setEnclosureGroupName(ENCLOSURE_GROUP_NAME);
        serverProfileValue.setFirmware(firmware);
        serverProfileValue.setLocalStorage(null);
        serverProfileValue.setMacType(ServerProfile.AssignmentType.Virtual);
        serverProfileValue.setNetworkForServerProfile(networkForServerProfiles);
        serverProfileValue.setSerialNumberType(ServerProfile.AssignmentType.Physical);
        serverProfileValue.setStorageVolumeForServerProfile(sanStorageForServerProfile);
        serverProfileValue.setTemplateName(SERVER_PROFILE_NAME);
        serverProfileValue.setUseBayNameForServerHardwareUri(USE_BAY_NAME_FOR_SERVER_HARDWARE_URI);
        serverProfileValue.setWwnType(ServerProfile.AssignmentType.Virtual);

        ResourceDtoUtilsWrapper resourceDtoUtilsWrapper = new ResourceDtoUtilsWrapper(new ResourceDtoUtils(oneViewClient));

        return resourceDtoUtilsWrapper.buildServerProfile(ApiVersion.V_201, serverProfileValue);
    }

    public static void main(final String[] args) {
        ServerProfileClientSample client = new ServerProfileClientSample();

        client.createServerProfile();
        client.getServerProfileById();
        client.getAllServerProfiles();
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
    }
}
