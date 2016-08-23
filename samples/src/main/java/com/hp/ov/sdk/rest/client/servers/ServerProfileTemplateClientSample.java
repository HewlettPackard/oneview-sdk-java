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

import com.hp.ov.sdk.OneViewClientSample;
import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.BiosSettingsTemplate;
import com.hp.ov.sdk.dto.BootModeSettingsTemplate;
import com.hp.ov.sdk.dto.BootSettingsTemplate;
import com.hp.ov.sdk.dto.ConnectionBootTemplate;
import com.hp.ov.sdk.dto.FirmwareSettingsTemplate;
import com.hp.ov.sdk.dto.LocalStorageSettingsTemplate;
import com.hp.ov.sdk.dto.ProfileConnectionTemplate;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.servers.OverriddenSetting;
import com.hp.ov.sdk.dto.servers.serverprofile.SanStorage;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofile.StoragePath;
import com.hp.ov.sdk.dto.servers.serverprofile.StorageTargetType;
import com.hp.ov.sdk.dto.servers.serverprofile.VolumeAttachment;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileTemplateClient;

/*
 * ServerProfileClientSample is a sample program capture/consume the entire server configuration managed
 * by HPE OneView. It invokes APIs of ServerProfileClient which is in sdk library to perform GET/PUT/POST/DELETE/COPY
 * operations on server profile resource
 */
public class ServerProfileTemplateClientSample {

    private final ServerProfileTemplateClient serverProfileTemplateClient;
    private final ServerProfileClient serverProfileClient;

    // test values - user input
    // ================================
    private static final String SERVER_PROFILE_TEMPLATE_NAME = "server-profile-template";
    private static final String SERVER_PROFILE_TEMPLATE_NAME_UPDATED = SERVER_PROFILE_TEMPLATE_NAME + "_Updated";
    private static final String RESOURCE_ID = "0fa0d573-4e38-4ed6-9523-e04d9a18c977";
    private static final String SERVER_HARDWARE_TYPE_URI = "/rest/server-hardware-types/F98A387B-07BE-4A2C-8A6A-0BAAFA586711";
    private static final String ENCLOSURE_GROUP_URI = "/rest/enclosure-groups/c6871f53-c5e1-483f-a273-a30da743f6b1";
    private static final String ETH_1_NETWORK_URI = "/rest/ethernet-networks/5b1ffd63-6787-4e5f-b409-3f86a998fbd6";
    private static final String ETH_2_NETWORK_URI = "/rest/ethernet-networks/bdc00905-20f3-46a9-87dd-8020fc0b6bab";
    private static final String FC_1_NETWORK_URI = "/rest/fc-networks/1f4a0491-2a41-4633-8362-377081dd0fcc";
    private static final String FC_2_NETWORK_URI = "/rest/fc-networks/4e261a94-125a-4654-9b93-12ba22cf13e4";
    private static final String STORAGE_VOLUME_URI = "/rest/storage-volumes/8CA32073-02F0-461C-A907-433EA0FAD8C5";
    private static final String SERVER_HARDWARE_URI = "/rest/server-hardware/37333036-3831-4753-4831-30355838524E";
    // ================================

    private ServerProfileTemplateClientSample() {
        OneViewClient oneViewClient = OneViewClientSample.getOneViewClient();

        this.serverProfileTemplateClient = oneViewClient.serverProfileTemplate();
        this.serverProfileClient = oneViewClient.serverProfile();
    }

    private void getServerProfileTemplateById() {
        ServerProfileTemplate serverProfileTemplate = serverProfileTemplateClient.getById(RESOURCE_ID);

        System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : " +
                "ServerProfileTemplate object returned to client : " + serverProfileTemplate.toJsonString());
    }

    private void getAllServerProfileTemplates() {
        ResourceCollection<ServerProfileTemplate> serverProfileTemplates = serverProfileTemplateClient.getAll();

        System.out.println("ServerProfileTemplateClientSample : getAllServerProfileTemplates : " +
                "Server profile templates returned to client : " + serverProfileTemplates.toJsonString());
    }

    private void getServerProfileTemplateByName() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateByName : " +
                "ServerProfileTemplate object returned to client : " + serverProfileTemplate.toJsonString());
    }

    private void createServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate = buildServerProfileTemplate();

        TaskResourceV2 taskResource = serverProfileTemplateClient.create(serverProfileTemplate, false);

        System.out.println("ServerProfileTemplateClientSample : createServerProfileTemplate : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void deleteServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME_UPDATED).get(0);

        TaskResourceV2 taskResource = serverProfileTemplateClient.delete(serverProfileTemplate.getResourceId(), false);

        System.out.println("ServerProfileTemplateClientSample : deleteServerProfileTemplate : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void updateServerProfileTemplate() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        serverProfileTemplate.setName(SERVER_PROFILE_TEMPLATE_NAME_UPDATED);

        TaskResourceV2 taskResource = serverProfileTemplateClient.update(serverProfileTemplate.getResourceId(),
                serverProfileTemplate, false);

        System.out.println("ServerProfileTemplateClientSample : updateServerProfileTemplate : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private void getNewServerProfile() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        ServerProfile serverProfile = serverProfileTemplateClient.getNewServerProfile(
                serverProfileTemplate.getResourceId());

        System.out.println("ServerProfileTemplateClientSample : getNewServerProfile : " +
                "ServerProfile object returned to client : " + serverProfile.toJsonString());
    }

    private void createServerProfileFromTemplate() {
        ServerProfileTemplate serverProfileTemplate
                = this.serverProfileTemplateClient.getByName(SERVER_PROFILE_TEMPLATE_NAME).get(0);

        ServerProfile serverProfile = serverProfileTemplateClient.getNewServerProfile(
                serverProfileTemplate.getResourceId());

        serverProfile.setName("ServerProfileFromTemplate");
        serverProfile.setServerHardwareUri(SERVER_HARDWARE_URI);

        TaskResourceV2 taskResource = serverProfileClient.create(serverProfile, false);

        System.out.println("ServerProfileTemplateClientSample : createServerProfileFromTemplate : " +
                "Task object returned to client : " + taskResource.toJsonString());
    }

    private ServerProfileTemplate buildServerProfileTemplate() {
        ServerProfileTemplate template = new ServerProfileTemplate();

        template.setName(SERVER_PROFILE_TEMPLATE_NAME);
        template.setType(ResourceCategory.RC_SERVER_PROFILE_TEMPLATE);
        template.setServerHardwareTypeUri(SERVER_HARDWARE_TYPE_URI);
        template.setEnclosureGroupUri(ENCLOSURE_GROUP_URI);
        template.setSerialNumberType("Virtual");
        template.setMacType("Virtual");
        template.setWwnType("Virtual");
        template.setAffinity("Bay");
        template.setHideUnusedFlexNics(true);

        template.setConnections(this.buildConnectionsTemplate());

        BootSettingsTemplate boot = new BootSettingsTemplate();
        boot.setManageBoot(true);
        boot.setOrder(Arrays.asList("HardDisk"));
        template.setBoot(boot);

        BootModeSettingsTemplate bootMode = new BootModeSettingsTemplate();
        bootMode.setManageMode(true);
        bootMode.setMode("UEFI");
        bootMode.setPxeBootPolicy("Auto");
        template.setBootMode(bootMode);

        FirmwareSettingsTemplate firmware = new FirmwareSettingsTemplate();
        firmware.setManageFirmware(false);
        firmware.setFirmwareBaselineUri("");
        firmware.setForceInstallFirmware(false);
        firmware.setFirmwareInstallType(null);
        template.setFirmware(firmware);

        BiosSettingsTemplate bios = new BiosSettingsTemplate();
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
        VolumeAttachment volume = new VolumeAttachment();
        volume.setId(1);
        volume.setVolumeUri(STORAGE_VOLUME_URI);
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

        ProfileConnectionTemplate eth1 = new ProfileConnectionTemplate();
        eth1.setNetworkUri(ETH_1_NETWORK_URI);
        eth1.setId(1L);
        eth1.setName("Prod_401");
        eth1.setFunctionType("Ethernet");
        eth1.setPortId("Auto");
        eth1.setRequestedMbps("3000");
        ConnectionBootTemplate eth1Boot = new ConnectionBootTemplate();
        eth1Boot.setPriority("NotBootable");
        eth1.setBoot(eth1Boot );
        connections.add(eth1);

        ProfileConnectionTemplate eth2 = new ProfileConnectionTemplate();
        eth2.setNetworkUri(ETH_2_NETWORK_URI);
        eth2.setId(2L);
        eth2.setName("Prod_402");
        eth2.setFunctionType("Ethernet");
        eth2.setPortId("Auto");
        eth2.setRequestedMbps("3000");
        ConnectionBootTemplate eth2Boot = new ConnectionBootTemplate();
        eth2Boot.setPriority("NotBootable");
        eth2.setBoot(eth2Boot );
        connections.add(eth2);

        ProfileConnectionTemplate fc1 = new ProfileConnectionTemplate();
        fc1.setNetworkUri(FC_1_NETWORK_URI);
        fc1.setId(3L);
        fc1.setName("FC_Network_A");
        fc1.setFunctionType("FibreChannel");
        fc1.setPortId("Auto");
        fc1.setRequestedMbps("2500");
        ConnectionBootTemplate fc1Boot = new ConnectionBootTemplate();
        fc1Boot.setPriority("NotBootable");
        fc1.setBoot(fc1Boot );
        connections.add(fc1);

        ProfileConnectionTemplate fc2 = new ProfileConnectionTemplate();
        fc2.setNetworkUri(FC_2_NETWORK_URI);
        fc2.setId(4L);
        fc2.setName("FC_Network_B");
        fc2.setFunctionType("FibreChannel");
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
    }

}
