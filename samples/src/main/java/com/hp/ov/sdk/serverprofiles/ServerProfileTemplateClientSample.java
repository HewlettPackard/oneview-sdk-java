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
package com.hp.ov.sdk.serverprofiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.dto.BiosSettingsTemplate;
import com.hp.ov.sdk.dto.BootModeSettingsTemplate;
import com.hp.ov.sdk.dto.BootSettingsTemplate;
import com.hp.ov.sdk.dto.ConnectionBootTemplate;
import com.hp.ov.sdk.dto.FirmwareSettingsTemplate;
import com.hp.ov.sdk.dto.LocalStorageSettingsTemplate;
import com.hp.ov.sdk.dto.OverriddenSettingsTemplate;
import com.hp.ov.sdk.dto.ProfileConnectionTemplate;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.SanStorageTemplate;
import com.hp.ov.sdk.dto.ServerProfileTemplate;
import com.hp.ov.sdk.dto.StoragePathTemplate;
import com.hp.ov.sdk.dto.TaskResourceV2;
import com.hp.ov.sdk.dto.VolumeAttachmentTemplate;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.exceptions.SDKApplianceNotReachableException;
import com.hp.ov.sdk.exceptions.SDKBadRequestException;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.exceptions.SDKNoResponseException;
import com.hp.ov.sdk.exceptions.SDKNoSuchUrlException;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.exceptions.SDKTasksException;
import com.hp.ov.sdk.rest.client.ServerProfileClient;
import com.hp.ov.sdk.rest.client.ServerProfileClientImpl;
import com.hp.ov.sdk.rest.client.ServerProfileTemplateClient;
import com.hp.ov.sdk.rest.client.ServerProfileTemplateClientImpl;
import com.hp.ov.sdk.rest.http.core.client.RestParams;
import com.hp.ov.sdk.util.UrlUtils;
import com.hp.ov.sdk.util.samples.HPOneViewCredential;

/*
 * ServerProfileClientSample is a sample program capture/consume the entire server configuration managed
 * by HPE OneView. It invokes APIs of ServerProfileClient which is in sdk library to perform GET/PUT/POST/DELETE/COPY
 * operations on server profile resource
 */
public class ServerProfileTemplateClientSample {

    private final ServerProfileTemplateClient serverProfileTemplateClient;

    private RestParams params;
    private TaskResourceV2 taskResourceV2;

    // test values - user input
    // ================================
    private static final String serverProfileTemplateName = "sp_template";
    private static final String serverProfileTemplateNameUpdated = serverProfileTemplateName + "_Update";
    private static final String resourceId = "09305c00-df29-4147-9a2c-bd0827d2726d";
    private static final String serverHardwareTypeUri = "/rest/server-hardware-types/F98A387B-07BE-4A2C-8A6A-0BAAFA586711";
    private static final String enclosureGroupUri = "/rest/enclosure-groups/c6871f53-c5e1-483f-a273-a30da743f6b1";
    private static final String eth1NetworkUri = "/rest/ethernet-networks/5b1ffd63-6787-4e5f-b409-3f86a998fbd6";
    private static final String eth2NetworkUri = "/rest/ethernet-networks/bdc00905-20f3-46a9-87dd-8020fc0b6bab";
    private static final String fc1NetworkUri = "/rest/fc-networks/1f4a0491-2a41-4633-8362-377081dd0fcc";
    private static final String fc2NetworkUri = "/rest/fc-networks/4e261a94-125a-4654-9b93-12ba22cf13e4";
    private static final String storageVolumeUri = "/rest/storage-volumes/8CA32073-02F0-461C-A907-433EA0FAD8C5";
    private static final String serverHardwareUri = "/rest/server-hardware/30303437-3933-4753-4831-30305835524E";
    // ================================

    private ServerProfileTemplateClientSample() {
        serverProfileTemplateClient = ServerProfileTemplateClientImpl.getClient();
    }

    private void getServerProfileTemplateById() throws InstantiationException, IllegalAccessException {
        ServerProfileTemplate serverProfileTemplateDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileTemplateDto = serverProfileTemplateClient.getServerProfileTemplateById(params, resourceId);

            System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : serverProfileTemplate object returned to client : "
                    + serverProfileTemplateDto.getName());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientSample : getServerProfileTemplateById : arguments are null ");
            return;
        }

    }

    private void getAllServerProfileTemplate() throws InstantiationException, IllegalAccessException, SDKResourceNotFoundException,
            SDKNoSuchUrlException {
        ResourceCollection<ServerProfileTemplate> serverProfileTemplateCollectionDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileTemplateCollectionDto = serverProfileTemplateClient.getAllServerProfileTemplates(params);

            System.out.println("ServerProfileTemplateClientTest : getAllServerProfileTemplate : serverProfileTemplate object returned to client : "
                    + serverProfileTemplateCollectionDto.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : getAllServerProfileTemplate : resource you are looking is not found");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : getAllServerProfileTemplate : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : getAllServerProfileTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientTest : getAllServerProfileTemplate : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : getAllServerProfileTemplate : arguments are null ");
            return;
        }
    }

    private void getServerProfileTemplateByName() throws InstantiationException, IllegalAccessException {
        ServerProfileTemplate serverProfileTemplateDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileTemplateDto = serverProfileTemplateClient.getServerProfileTemplateByName(params, serverProfileTemplateName);

            System.out.println("ServerProfileTemplateClientTest : getServerProfileTemplateByName : serverProfileTemplate object returned to client : "
                    + serverProfileTemplateDto.getName());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : getServerProfileTemplateByName : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : getServerProfileTemplateByName : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : getServerProfileTemplateByName : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientTest : getServerProfileTemplateByName : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : getServerProfileTemplateByName : arguments are null ");
            return;
        }

    }

    private void getNewServerProfile() throws InstantiationException, IllegalAccessException {
        ServerProfile serverProfileDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileDto = serverProfileTemplateClient.getNewServerProfile(params, resourceId);

            System.out.println("ServerProfileTemplateClientTest : getNewServerProfile : serverProfile object returned to client : "
                    + serverProfileDto.getName());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : getNewServerProfile : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : getNewServerProfile : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : getNewServerProfile : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientTest : getNewServerProfile : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : getNewServerProfile : arguments are null ");
            return;
        }

    }

    private void createServerProfileFromTemplate() {
        ServerProfile serverProfileDto = null;
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            serverProfileDto = serverProfileTemplateClient.getNewServerProfile(params, resourceId);

            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : serverProfile object returned to client : "
                    + serverProfileDto.getName());

            serverProfileDto.setName("ServerProfileFromTemplate");
            serverProfileDto.setServerHardwareUri(serverHardwareUri);

            ServerProfileClient spClient = ServerProfileClientImpl.getClient();
            taskResourceV2 = spClient.createServerProfile(params, serverProfileDto, false, false);

            System.out.println("Server Profile creation status: " + taskResourceV2.getStatus());

        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : resource you are looking is not found ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileFromTemplate : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void createServerProfileTemplate() throws InstantiationException, IllegalAccessException {
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // create serverProfile request body
            final ServerProfileTemplate serverProfileTemplateDto = buildServerProfileTemplateDto(params);
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileTemplateClient.createServerProfileTemplate(params, serverProfileTemplateDto, false);

            System.out.println("ServerProfileTemplateClientTest : createServerProfileTemplate : serverProfileTemplate object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileTemplate : resource you are looking is not found");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileTemplate : bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileTemplate : no such url : " + params.getHostname());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : createServerProfileTemplate : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerProfileTemplateClientTest : createServerProfileTemplate : errors in task, please check task resource for more details ");
            return;
        }

    }

    private ServerProfileTemplate buildServerProfileTemplateDto(RestParams restParams) {
        ServerProfileTemplate template = new ServerProfileTemplate();

        template.setName(serverProfileTemplateName);
        template.setType(ResourceCategory.RC_SERVER_PROFILE_TEMPLATE);
        template.setServerHardwareTypeUri(serverHardwareTypeUri);
        template.setEnclosureGroupUri(enclosureGroupUri);
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
        bios.setOverriddenSettings(Collections.<OverriddenSettingsTemplate>emptyList());
        template.setBios(bios);

        template.setLocalStorage(new LocalStorageSettingsTemplate());

        SanStorageTemplate sanStorage = new SanStorageTemplate();
        sanStorage.setHostOSType("Windows 2012 / WS2012 R2");
        sanStorage.setManageSanStorage(true);

        sanStorage.setVolumeAttachments(this.buildVolumeAttachments());
        template.setSanStorage(sanStorage);

        return template;
    }

    private List<VolumeAttachmentTemplate> buildVolumeAttachments() {
        List<VolumeAttachmentTemplate> volumeAttachments = new ArrayList<VolumeAttachmentTemplate>();
        VolumeAttachmentTemplate volume = new VolumeAttachmentTemplate();
        volume.setId(1L);
        volume.setVolumeUri(storageVolumeUri);
        volume.setLunType("Manual");
        volume.setLun("0");

        // Storage Paths
        List<StoragePathTemplate> storagePaths = new ArrayList<StoragePathTemplate>();

        StoragePathTemplate storagePath1 = new StoragePathTemplate();
        storagePath1.setIsEnabled(true);
        storagePath1.setConnectionId(3);
        storagePath1.setStorageTargetType("Auto");
        storagePath1.setStorageTargets(Collections.<String>emptyList());
        storagePaths.add(storagePath1);

        StoragePathTemplate storagePath2 = new StoragePathTemplate();
        storagePath2.setIsEnabled(true);
        storagePath2.setConnectionId(4);
        storagePath2.setStorageTargetType("Auto");
        storagePath2.setStorageTargets(Collections.<String>emptyList());
        storagePaths.add(storagePath2);

        volume.setStoragePaths(storagePaths);

        volumeAttachments.add(volume);

        return volumeAttachments;
    }

    private List<ProfileConnectionTemplate> buildConnectionsTemplate() {
        List<ProfileConnectionTemplate> connections = new ArrayList<ProfileConnectionTemplate>();

        ProfileConnectionTemplate eth1 = new ProfileConnectionTemplate();
        eth1.setNetworkUri(eth1NetworkUri);
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
        eth2.setNetworkUri(eth2NetworkUri);
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
        fc1.setNetworkUri(fc1NetworkUri);
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
        fc2.setNetworkUri(fc2NetworkUri);
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

    private void updateServerProfileTemplate() throws InstantiationException, IllegalAccessException {
        String resourceId = null;
        ServerProfileTemplate serverProfileTemplateDto = null;
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // fetch resource Id using resource name
            serverProfileTemplateDto = serverProfileTemplateClient.getServerProfileTemplateByName(params, serverProfileTemplateName);

            serverProfileTemplateDto.setName(serverProfileTemplateNameUpdated);

            if (null != serverProfileTemplateDto.getUri()) {
                resourceId = UrlUtils.getResourceIdFromUri(serverProfileTemplateDto.getUri());
            }
            /**
             * then make sdk service call to get resource aSync parameter
             * indicates sync vs async useJsonRequest parameter indicates
             * whether json input request present or not
             */
            taskResourceV2 = serverProfileTemplateClient.updateServerProfileTemplate(params, resourceId, serverProfileTemplateDto, false);

            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate : " + "serverProfileTemplate object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate :"
                    + " resource you are looking is not found for update ");
            return;
        } catch (final SDKBadRequestException ex) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate :" + " bad request, try again : "
                    + "may be duplicate resource name or invalid inputs. check inputs and try again");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate :" + " no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate :" + " Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate :" + " No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate : " + "arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out.println("ServerProfileTemplateClientTest : updateServerProfileTemplate : " + "errors in task, please check task "
                    + "resource for more details ");
            return;
        }
    }

    private void deleteServerProfileTemplate() throws InstantiationException, IllegalAccessException {
        // first get the session Id
        try {
            // OneView credentials
            params = HPOneViewCredential.createCredentials();

            // then make sdk service call to get resource
            taskResourceV2 = serverProfileTemplateClient.deleteServerProfileTemplate(params, resourceId, false);

            System.out.println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : serverProfileTemplate object returned to client : "
                    + taskResourceV2.toString());
        } catch (final SDKResourceNotFoundException ex) {
            System.out.println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : resource you are looking is not found for delete ");
            return;
        } catch (final SDKNoSuchUrlException ex) {
            System.out.println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : no such url : " + params.getUrl());
            return;
        } catch (final SDKApplianceNotReachableException e) {
            System.out.println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : Applicance Not reachabe at : "
                    + params.getHostname());
            return;
        } catch (final SDKNoResponseException ex) {
            System.out.println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : No response from appliance : "
                    + params.getHostname());
            return;
        } catch (final SDKInvalidArgumentException ex) {
            System.out.println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : arguments are null ");
            return;
        } catch (final SDKTasksException e) {
            System.out
                    .println("ServerProfileTemplateClientTest : deleteServerProfileTemplate : errors in task, please check task resource for more details ");
            return;
        }

    }

    public static void main(final String[] args) throws Exception {
        ServerProfileTemplateClientSample client = new ServerProfileTemplateClientSample();

        client.getAllServerProfileTemplate();
        client.createServerProfileTemplate();
        client.getServerProfileTemplateById();
        client.getServerProfileTemplateByName();
        client.updateServerProfileTemplate();
        client.getNewServerProfile();
        client.createServerProfileFromTemplate();
        client.deleteServerProfileTemplate();
    }

}
