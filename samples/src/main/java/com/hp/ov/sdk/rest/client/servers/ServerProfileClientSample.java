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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.dto.Patch;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.samples.NetworkForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile;
import com.hp.ov.sdk.dto.samples.SanStorageForServerProfile.StorageVolume;
import com.hp.ov.sdk.dto.samples.ServerProfileValue;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.Bios;
import com.hp.ov.sdk.dto.servers.Boot;
import com.hp.ov.sdk.dto.servers.Firmware;
import com.hp.ov.sdk.dto.servers.FunctionType;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.StorageTargetType;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableNetworks;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableServers;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableStorageSystem;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableTargets;
import com.hp.ov.sdk.dto.servers.serverprofile.BootControl;
import com.hp.ov.sdk.dto.servers.serverprofile.ProfilePorts;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfileHealth;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClientSample;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClientSample;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileClientSample.class);

    private final ServerProfileClient serverProfileClient;
    private final EnclosureGroupClient enclosureGroupClient;
    private final OneViewClient oneViewClient;

    // test values - user input
    // ================================
    private static final String RESOURCE_ID = "72a9376c-0caf-4cae-aee6-7e8d46f6a61e";
    private static final String SERVER_PROFILE_NAME = "server_profile";
    private static final String SERVER_PROFILE_NAME_UPDATED = SERVER_PROFILE_NAME + "_Updated";
    private static final String BAY_NAME = "Encl1, bay 15";
    private static final List<String> NETWORK_NAMES = Arrays.asList("Prod_401", "Prod_402");
    private static final List<String> STORAGE_VOLUME_NAME = Arrays.asList(StorageVolumeClientSample.STORAGE_VOLUME_NAME);
    private static final List<String> FC_NETWORK_NAMES = Arrays.asList(FcNetworkClientSample.FC_NETWORK_NAME_A, FcNetworkClientSample.FC_NETWORK_NAME_B);
    private static final Boolean USE_BAY_NAME_FOR_SERVER_HARDWARE_URI = false;
    // ================================

    private ServerProfileClientSample() {
        oneViewClient = OneViewClientSample.getOneViewClient();

        serverProfileClient = oneViewClient.serverProfile();
        enclosureGroupClient = oneViewClient.enclosureGroup();
    }

    private void getServerProfileById() {
        ServerProfile serverProfile = serverProfileClient.getById(RESOURCE_ID);

        LOGGER.info("ServerProfile object returned to client : " + serverProfile.toJsonString());
    }

    private void getAllServerProfiles() {
        ResourceCollection<ServerProfile> serverProfiles = serverProfileClient.getAll();

        LOGGER.info("Server profiles returned to client : " + serverProfiles.toJsonString());
    }

    private void getServerProfileByName() {
        ServerProfile serverProfile = serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        LOGGER.info("ServerProfile object returned to client : " + serverProfile.toJsonString());
    }

    private void createServerProfile() {
        ServerProfile serverProfile = buildServerProfile();

        TaskResource taskResource = serverProfileClient.create(serverProfile);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void deleteServerProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME_UPDATED).get(0);

        TaskResource taskResource = serverProfileClient.delete(serverProfile.getResourceId());

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void deleteServerProfileByFilter() {
        String filter = "name='" + SERVER_PROFILE_NAME +"'";

        TaskResource taskResource = this.serverProfileClient.deleteByFilter(filter);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateServerProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        serverProfile.setName(SERVER_PROFILE_NAME_UPDATED);

        TaskResource taskResource = serverProfileClient.update(serverProfile.getResourceId(), serverProfile);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void patchServerProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        Patch patch = new Patch();

        // Server Profile patch supports the update of template compliance status
        patch.setOp(Patch.PatchOperation.replace);
        patch.setPath("/templateCompliance");
        patch.setValue("Compliant");

        TaskResource taskResource = serverProfileClient.patch(serverProfile.getResourceId(), patch);

        LOGGER.info("Task object returned to client : " + taskResource.toJsonString());
    }

    private void getServerProfileCompliancePreview() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        ServerProfileCompliancePreview compliance = serverProfileClient.getCompliancePreview(
                serverProfile.getResourceId());

        LOGGER.info("ServerProfileCompliancePreview object returned to client : " + JsonPrettyPrinter.print(compliance));
    }

    private void getServerProfileMessages() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        ServerProfileHealth health = serverProfileClient.getMessages(serverProfile.getResourceId());

        LOGGER.info("ServerProfileCompliancePreview object returned to client : " + JsonPrettyPrinter.print(health));
    }

    private void getServerProfileTransformation() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        ServerProfile serverProfileUpdated = serverProfileClient.getTransformation(
                serverProfile.getResourceId(), ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI, enclosureGroupUri);

        LOGGER.info("ServerProfile object returned to client : " + serverProfileUpdated.toJsonString());
    }

    private void getAvailableNetworksForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        AvailableNetworks availableNetworks = serverProfileClient.getAvailableNetworks(
                ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI, enclosureGroupUri);

        LOGGER.info("AvailableNetworks object returned to client : " + JsonPrettyPrinter.print(availableNetworks));
    }

    private void getAvailableServersForServerProfile() {
        List<AvailableServers> availableServers = serverProfileClient.getAvailableServers();

        LOGGER.info("AvailableServers returned to client : " + JsonPrettyPrinter.print(availableServers));
    }

    private void getAvailableServersForServerProfileUsingServerHardwareTypeAndEnclosureGroup() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        List<AvailableServers> availableServers = serverProfileClient.getAvailableServers(
                ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI, enclosureGroupUri);

        LOGGER.info("AvailableServers returned to client : " + JsonPrettyPrinter.print(availableServers));
    }

    private void getAvailableServersForServerProfileUsingProfile() {
        ServerProfile serverProfile = this.serverProfileClient.getByName(SERVER_PROFILE_NAME).get(0);

        List<AvailableServers> availableServers = serverProfileClient.getAvailableServers(serverProfile.getUri());

        LOGGER.info("AvailableServers returned to client : " + JsonPrettyPrinter.print(availableServers));
    }

    private void getAvailableStorageSystemsForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        ResourceCollection<AvailableStorageSystem> storageSystems = serverProfileClient.getAvailableStorageSystems(
                ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI, enclosureGroupUri);

        LOGGER.info("AvailableStorageSystem returned to client : " + storageSystems.toJsonString());
    }

    private void getAvailableStorageSystemForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        AvailableStorageSystem storageSystem = serverProfileClient.getAvailableStorageSystems(
                ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI, enclosureGroupUri).getMembers().get(0);

        // Use just the {ID} not "/rest/storage-systems/{ID}"
        AvailableStorageSystem availableStorageSystem = serverProfileClient.getAvailableStorageSystem(
                ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI,
                enclosureGroupUri,
                UrlUtils.getResourceIdFromUri(storageSystem.getStorageSystemUri()));

        LOGGER.info("AvailableStorageSystem object returned to client : " + JsonPrettyPrinter.print(availableStorageSystem));
    }

    private void getAvailableTargetsForServerProfile() {
        AvailableTargets targets = serverProfileClient.getAvailableTargets(
                "",
                "",
                "");

        LOGGER.info("AvailableTargets object returned to client : " + targets.toJsonString());
    }

    private void getProfilePortsForServerProfile() {
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        ProfilePorts profilePorts = serverProfileClient.getProfilePorts(ServerHardwareTypeClientSample.SERVER_HARDWARE_TYPE_URI, enclosureGroupUri);

        LOGGER.info("ProfilePorts object returned to client : " + JsonPrettyPrinter.print(profilePorts));
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
            networkForServerProfile.setNetworkType(FunctionType.Ethernet);
            networkForServerProfiles.add(networkForServerProfile);
        }

        for (String networkName : FC_NETWORK_NAMES) {
            NetworkForServerProfile networkForServerProfile = new NetworkForServerProfile();
            networkForServerProfile.setNetworkName(networkName);
            networkForServerProfile.setBoot(BootControl.NotBootable);
            networkForServerProfile.setMaximumMbps(2500);
            networkForServerProfile.setAllocatedMbps(2500);
            networkForServerProfile.setRequestedMbps("2500");
            networkForServerProfile.setNetworkType(FunctionType.FibreChannel);
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
        serverProfileValue.setEnclosureGroupName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME);
        serverProfileValue.setFirmware(firmware);
        serverProfileValue.setLocalStorage(null);
        serverProfileValue.setMacType(AssignmentType.Virtual);
        serverProfileValue.setNetworkForServerProfile(networkForServerProfiles);
        serverProfileValue.setSerialNumberType(AssignmentType.Physical);
        serverProfileValue.setStorageVolumeForServerProfile(sanStorageForServerProfile);
        serverProfileValue.setTemplateName(SERVER_PROFILE_NAME);
        serverProfileValue.setUseBayNameForServerHardwareUri(USE_BAY_NAME_FOR_SERVER_HARDWARE_URI);
        serverProfileValue.setWwnType(AssignmentType.Virtual);

        ResourceDtoUtilsWrapper resourceDtoUtilsWrapper = new ResourceDtoUtilsWrapper(new ResourceDtoUtils(oneViewClient));

//        return resourceDtoUtilsWrapper.buildServerProfile(ApiVersion.V_120, serverProfileValue);
        return resourceDtoUtilsWrapper.buildServerProfile(ApiVersion.V_201, serverProfileValue);
    }

    public static void main(final String[] args) {
        ServerProfileClientSample client = new ServerProfileClientSample();

        client.getAllServerProfiles();
        client.createServerProfile();
        client.getServerProfileById();
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
