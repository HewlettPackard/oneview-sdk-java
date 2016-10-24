/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.TaskResource;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.Bios;
import com.hp.ov.sdk.dto.servers.Boot;
import com.hp.ov.sdk.dto.servers.BootMode;
import com.hp.ov.sdk.dto.servers.Firmware;
import com.hp.ov.sdk.dto.servers.FunctionType;
import com.hp.ov.sdk.dto.servers.OverriddenSetting;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.SanStorage;
import com.hp.ov.sdk.dto.servers.StoragePath;
import com.hp.ov.sdk.dto.servers.StorageTargetType;
import com.hp.ov.sdk.dto.servers.VolumeAttachment;
import com.hp.ov.sdk.dto.servers.enclosuregroup.EnclosureGroup;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.ConnectionBootTemplate;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.LocalStorageSettingsTemplate;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.ProfileConnectionTemplate;
import com.hp.ov.sdk.dto.servers.serverprofiletemplate.ServerProfileTemplate;
import com.hp.ov.sdk.dto.storage.StorageVolume;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClient;
import com.hp.ov.sdk.rest.client.networking.EthernetNetworkClientSample;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClient;
import com.hp.ov.sdk.rest.client.networking.FcNetworkClientSample;
import com.hp.ov.sdk.rest.client.server.EnclosureGroupClient;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClient;
import com.hp.ov.sdk.rest.client.storage.StorageVolumeClientSample;

/*
 * ServerProfileClientSample is a sample program capture/consume the entire server configuration managed
 * by HPE OneView. It invokes APIs of ServerProfileClient which is in sdk library to perform GET/PUT/POST/DELETE/COPY
 * operations on server profile resource
 */
public class ServerProfileTemplateClientSample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerProfileTemplateClientSample.class);

    private final ServerProfileTemplateClient serverProfileTemplateClient;
    private final ServerProfileClient serverProfileClient;
    private final ServerHardwareClient serverHardwareClient;
    private final EnclosureGroupClient enclosureGroupClient;
    private final FcNetworkClient fcNetworkClient;
    private final EthernetNetworkClient ethernetNetworkClient;
    private final StorageVolumeClient storageVolumeClient;

    // test values - user input
    // ================================
    private static final String SERVER_PROFILE_TEMPLATE_NAME = "server-profile-template";
    private static final String SERVER_PROFILE_TEMPLATE_NAME_UPDATED = SERVER_PROFILE_TEMPLATE_NAME + "_Updated";
    private static final String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types/3FF68C4E-342D-46C8-A07C-72C04010E14B";
    private static final String SERVER_HARDWARE_URI = "/rest/server-hardware/37333036-3831-4753-4831-30355838524E";
    // ================================

    private ServerProfileTemplateClientSample() {
        OneViewClient oneViewClient = new OneViewClientSample().getOneViewClient();

        this.serverProfileTemplateClient = oneViewClient.serverProfileTemplate();
        this.serverProfileClient = oneViewClient.serverProfile();
        this.serverHardwareClient = oneViewClient.serverHardware();
        this.enclosureGroupClient = oneViewClient.enclosureGroup();
        this.fcNetworkClient = oneViewClient.fcNetwork();
        this.ethernetNetworkClient = oneViewClient.ethernetNetwork();
        this.storageVolumeClient = oneViewClient.storageVolume();
    }

    private void getServerProfileTemplateById() {
        ServerProfileTemplate template = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        template = serverProfileTemplateClient.getById(template.getResourceId());

        LOGGER.info("Server Profile Template object returned to client: {}", template.toJsonString());
    }

    private void getAllServerProfileTemplates() {
        ResourceCollection<ServerProfileTemplate> serverProfileTemplates = serverProfileTemplateClient.getAll();

        LOGGER.info("Server profile templates returned to client: {}", serverProfileTemplates.toJsonString());
    }

    private void getServerProfileTemplateByName() {
        ServerProfileTemplate template = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        LOGGER.info("Server Profile Template object returned to client: {}", template.toJsonString());
    }

    private void createServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate = buildServerProfileTemplate();

        TaskResource taskResource = serverProfileTemplateClient.create(serverProfileTemplate);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void deleteServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME_UPDATED).get(0);

        TaskResource taskResource = serverProfileTemplateClient.delete(serverProfileTemplate.getResourceId());

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void updateServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        serverProfileTemplate.setName(SERVER_PROFILE_TEMPLATE_NAME_UPDATED);

        TaskResource taskResource = serverProfileTemplateClient.update(serverProfileTemplate.getResourceId(),
                serverProfileTemplate);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getNewServerProfile() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        ServerProfile serverProfile = serverProfileTemplateClient.getNewServerProfile(
                serverProfileTemplate.getResourceId());

        LOGGER.info("Server Profile object returned to client: {}", serverProfile.toJsonString());
    }

    private void createServerProfileFromTemplate() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        ServerProfile serverProfile = serverProfileTemplateClient.getNewServerProfile(
                serverProfileTemplate.getResourceId());

        serverProfile.setName("ServerProfileFromTemplate");
        serverProfile.setServerHardwareUri(SERVER_HARDWARE_URI);

        TaskResource taskResource = serverProfileClient.create(serverProfile);

        LOGGER.info("Task object returned to client: {}", taskResource.toJsonString());
    }

    private void getServerProfileTemplateTransformation() {
        ServerProfileTemplate serverProfileTemplate = serverProfileTemplateClient
                .getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        String serverHardwareTypeUri = serverHardwareClient.getAll().get(0).getServerHardwareTypeUri();
        String enclosureGroupUri = enclosureGroupClient.getByName(EnclosureGroupClientSample.ENCLOSURE_GROUP_NAME).get(0).getUri();

        ServerProfileTemplate serverProfileTemplateUpdated = serverProfileTemplateClient
                .getTransformation(serverProfileTemplate.getResourceId(), serverHardwareTypeUri, enclosureGroupUri);

        LOGGER.info("Server Profile Template object returned to client: {}",
                serverProfileTemplateUpdated.toJsonString());
    }

    private ServerProfileTemplate buildServerProfileTemplate() {
        ServerProfileTemplate template = new ServerProfileTemplate();

        EnclosureGroup enclosureGroup = this.enclosureGroupClient.getByName("enclosure-group").get(0);

        template.setName(SERVER_PROFILE_TEMPLATE_NAME);
        template.setType(ResourceCategory.RC_SERVER_PROFILE_TEMPLATE); //v120 & v200
        template.setType(ResourceCategory.RC_SERVER_PROFILE_TEMPLATE_V300); //v300
        template.setServerHardwareTypeUri(SERVER_HARDWARE_TYPE_URI);
        template.setEnclosureGroupUri(enclosureGroup.getUri());
        template.setSerialNumberType(AssignmentType.Virtual);
        template.setMacType(AssignmentType.Virtual);
        template.setWwnType(AssignmentType.Virtual);
        template.setAffinity(ProfileAffinity.Bay);
        template.setHideUnusedFlexNics(true);

        template.setConnections(this.buildConnectionsTemplate());

        Boot boot = new Boot();
        boot.setManageBoot(true);
        boot.setOrder(Arrays.asList("HardDisk"));
        template.setBoot(boot);

        BootMode bootMode = new BootMode();
        bootMode.setManageMode(true);
        bootMode.setMode("UEFI");
        bootMode.setPxeBootPolicy("Auto");
        template.setBootMode(bootMode);

        Firmware firmware = new Firmware();
        firmware.setManageFirmware(false);
        firmware.setFirmwareBaselineUri("");
        firmware.setForceInstallFirmware(false);
        firmware.setFirmwareInstallType(null);
        template.setFirmware(firmware);

        Bios bios = new Bios();
        bios.setManageBios(false);
        bios.setOverriddenSettings(Collections.<OverriddenSetting>emptyList());
        template.setBios(bios);

        template.setLocalStorage(new LocalStorageSettingsTemplate());

        SanStorage sanStorage = new SanStorage();
        sanStorage.setHostOSType("Windows 2012 / WS2012 R2");
        sanStorage.setManageSanStorage(true);

        sanStorage.setVolumeAttachments(this.buildVolumeAttachments());
        template.setSanStorage(sanStorage);

        return template;
    }

    private List<VolumeAttachment> buildVolumeAttachments() {
        List<VolumeAttachment> volumeAttachments = new ArrayList<>();

        StorageVolume storageVolume = this.storageVolumeClient.getByName(
                StorageVolumeClientSample.STORAGE_VOLUME_NAME).get(0);

        VolumeAttachment volume = new VolumeAttachment();
        volume.setId(1);
        volume.setVolumeUri(storageVolume.getUri());
        volume.setLunType("Manual");
        volume.setLun("0");

        // Storage Paths
        List<StoragePath> storagePaths = new ArrayList<>();

        StoragePath storagePath1 = new StoragePath();
        storagePath1.setIsEnabled(true);
        storagePath1.setConnectionId(3);
        storagePath1.setStorageTargetType(StorageTargetType.Auto);
        storagePath1.setStorageTargets(Collections.<String>emptyList());
        storagePaths.add(storagePath1);

        StoragePath storagePath2 = new StoragePath();
        storagePath2.setIsEnabled(true);
        storagePath2.setConnectionId(4);
        storagePath2.setStorageTargetType(StorageTargetType.Auto);
        storagePath2.setStorageTargets(Collections.<String>emptyList());
        storagePaths.add(storagePath2);

        volume.setStoragePaths(storagePaths);

        volumeAttachments.add(volume);

        return volumeAttachments;
    }

    private List<ProfileConnectionTemplate> buildConnectionsTemplate() {
        List<ProfileConnectionTemplate> connections = new ArrayList<>();

        FcNetwork fcNetworkA = this.fcNetworkClient.getByName(FcNetworkClientSample.FC_NETWORK_NAME_A).get(0);
        FcNetwork fcNetworkB = this.fcNetworkClient.getByName(FcNetworkClientSample.FC_NETWORK_NAME_B).get(0);
        Network ethNetwork401 = this.ethernetNetworkClient.getByName(
                EthernetNetworkClientSample.ETHERNET_NETWORK_PROD_401).get(0);
        Network ethNetwork402 = this.ethernetNetworkClient.getByName(
                EthernetNetworkClientSample.ETHERNET_NETWORK_PROD_402).get(0);

        ProfileConnectionTemplate eth1 = new ProfileConnectionTemplate();
        eth1.setNetworkUri(ethNetwork401.getUri());
        eth1.setId(1L);
        eth1.setName(EthernetNetworkClientSample.ETHERNET_NETWORK_PROD_401);
        eth1.setFunctionType(FunctionType.Ethernet);
        eth1.setPortId("Auto");
        eth1.setRequestedMbps("3000");
        ConnectionBootTemplate eth1Boot = new ConnectionBootTemplate();
        eth1Boot.setPriority("NotBootable");
        eth1.setBoot(eth1Boot );
        connections.add(eth1);

        ProfileConnectionTemplate eth2 = new ProfileConnectionTemplate();
        eth2.setNetworkUri(ethNetwork402.getUri());
        eth2.setId(2L);
        eth2.setName(EthernetNetworkClientSample.ETHERNET_NETWORK_PROD_402);
        eth2.setFunctionType(FunctionType.Ethernet);
        eth2.setPortId("Auto");
        eth2.setRequestedMbps("3000");
        ConnectionBootTemplate eth2Boot = new ConnectionBootTemplate();
        eth2Boot.setPriority("NotBootable");
        eth2.setBoot(eth2Boot );
        connections.add(eth2);

        ProfileConnectionTemplate fc1 = new ProfileConnectionTemplate();
        fc1.setNetworkUri(fcNetworkA.getUri());
        fc1.setId(3L);
        fc1.setName(FcNetworkClientSample.FC_NETWORK_NAME_A);
        fc1.setFunctionType(FunctionType.FibreChannel);
        fc1.setPortId("Auto");
        fc1.setRequestedMbps("2500");
        ConnectionBootTemplate fc1Boot = new ConnectionBootTemplate();
        fc1Boot.setPriority("NotBootable");
        fc1.setBoot(fc1Boot );
        connections.add(fc1);

        ProfileConnectionTemplate fc2 = new ProfileConnectionTemplate();
        fc2.setNetworkUri(fcNetworkB.getUri());
        fc2.setId(4L);
        fc2.setName(FcNetworkClientSample.FC_NETWORK_NAME_B);
        fc2.setFunctionType(FunctionType.FibreChannel);
        fc2.setPortId("Auto");
        fc2.setRequestedMbps("2500");
        ConnectionBootTemplate fc2Boot = new ConnectionBootTemplate();
        fc2Boot.setPriority("NotBootable");
        fc2.setBoot(fc2Boot );
        connections.add(fc2);

        return connections;
    }

    public static void main(final String[] args) throws Exception {
        ServerProfileTemplateClientSample client = new ServerProfileTemplateClientSample();

        client.createServerProfileTemplate();

        client.getAllServerProfileTemplates();
        client.createServerProfileFromTemplate();
        client.getServerProfileTemplateById();
        client.getServerProfileTemplateByName();
        client.getNewServerProfile();
        client.updateServerProfileTemplate();
        client.deleteServerProfileTemplate();
        client.getServerProfileTemplateTransformation();
    }

}
