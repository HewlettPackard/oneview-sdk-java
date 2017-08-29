/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use file except in compliance with the License.
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

package com.hp.ov.sdk.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.Bios;
import com.hp.ov.sdk.dto.servers.Boot;
import com.hp.ov.sdk.dto.servers.Firmware;
import com.hp.ov.sdk.dto.servers.FunctionType;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.SanStorage;
import com.hp.ov.sdk.dto.servers.SourceType;
import com.hp.ov.sdk.dto.servers.StorageTargetType;
import com.hp.ov.sdk.dto.servers.VolumeAttachment;
import com.hp.ov.sdk.dto.servers.serverhardware.ServerHardware;
import com.hp.ov.sdk.dto.servers.serverprofile.AvailableServers;
import com.hp.ov.sdk.dto.servers.serverprofile.ProfileConnection;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfileCompliancePreview;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfileHealth;
import com.hp.ov.sdk.exceptions.SDKResourceNotFoundException;
import com.hp.ov.sdk.oneview.BasicResource;
import com.hp.ov.sdk.oneview.CreateResource;
import com.hp.ov.sdk.oneview.RemoveResource;
import com.hp.ov.sdk.oneview.UpdateResource;
import com.hp.ov.sdk.rest.client.server.ServerHardwareClient;
import com.hp.ov.sdk.rest.client.server.ServerProfileClient;
import com.hp.ov.sdk.util.ResourceDtoUtils;

public class ServerProfileResource extends BasicResource implements CreateResource, RemoveResource, UpdateResource {

    private static ServerProfileResource instance;

    private ServerProfileClient client;

    private ServerHardwareClient serverHardwareClient;

    private ResourceDtoUtils resourceDtoUtils;

    private String enclosureUri;

    private ServerProfileResource() {
        category.put("V_300", "ServerProfileV6");
        category.put("V_200", "ServerProfileV5");
        client = oneViewClient.serverProfile();
        serverHardwareClient = oneViewClient.serverHardware();
        resourceDtoUtils = new ResourceDtoUtils(oneViewClient);
    }

    public static ServerProfileResource getInstance() {
        if (instance == null) {
            instance = new ServerProfileResource();
        }
        return instance;
    }

    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    private ServerHardware getServerHardware() {
        ResourceCollection<ServerHardware> collection = serverHardwareClient.getAll();
        ServerHardware server = null;
        for (ServerHardware s : collection.getMembers()) {
            if (s.getServerGroupUri().equals(enclosureUri)) {
                server = s;
                break;
            }
        }
        return server;
    }

    @Override
    public Map<String, String> getResourceProperties(String id) {
        return getResourceProperties(client.getById(id));
    }

    @Override
    public String findByName(String name) {
        ServerProfile serverProfile = (ServerProfile) getResource(client.getByName(name));
        return serverProfile == null ? "" : serverProfile.getResourceId();
    }

    @Override
    public String findById(String id) {
        try {
            return client.getById(id).getName();
        } catch (final SDKResourceNotFoundException ex) {
            return "";
        }
    }

    @Override
    public int count() {
        return getCount(client.getAll());
    }

    @Override
    public void create() {
        client.create(builder());
    }

    @Override
    public String remove(String id) {
        return taskToString(client.delete(id));
    }

    @Override
    public String update(String id) {
        ServerProfile sp = client.getById(id);
        sp.setName(resourceProperties.get("name"));
        addConnections(sp);
        return taskToString(client.update(id, sp));
    }

    public int getAvailableServers() {
        List<AvailableServers> availableServers = client.getAvailableServers();
        return availableServers != null ? availableServers.size() : -1;
    }

    public int getAvailableServersUsingProfile(String resourceID) {
        ServerProfile sp = client.getById(resourceID);
        List<AvailableServers> availableServers = client.getAvailableServers(sp.getUri());
        return availableServers != null ? availableServers.size() : -1;
    }

    public String getCompliancePreview(String resourceID) {
        try {
            ServerProfileCompliancePreview compliance = client.getCompliancePreview(resourceID);
            return compliance != null ? compliance.toString() : "not-found";
        } catch (Exception e) {
            return "not-found";
        }
    }

    public String getProfileMessages(String resourceID) {
        try {
            ServerProfileHealth health = client.getMessages(resourceID);
            return health != null ? health.toString() : "not-found";
        } catch (Exception e) {
            return "not-found";
        }
    }

    @Override
    public ServerProfile builder() {
        ServerProfile sp = new ServerProfile();
        sp.setName(resourceProperties.get("name"));
        sp.setType(getCategory());
        sp.setDescription(resourceProperties.get("description"));
        sp.setAffinity(ProfileAffinity.Bay);
        sp.setMacType(AssignmentType.Virtual);
        sp.setWwnType(AssignmentType.Virtual);
        sp.setSerialNumberType(AssignmentType.valueOf(resourceProperties.get("serialNumberType")));
        sp.setEnclosureGroupUri(enclosureUri);

        sp.setLocalStorage(null);
        sp.setHideUnusedFlexNics(true);

        sp.setIscsiInitiatorNameType(SourceType.AutoGenerated);
        sp.setIscsiInitiatorName("");

        addFirmware(sp);
        addServerHardware(sp);
        addConnections(sp);
        addVolume(sp);
        addBoot(sp);
        addBios(sp);

        return sp;
    }

    private void addServerHardware(ServerProfile sp) {
        ServerHardware server = (ServerHardware) getResource(
                serverHardwareClient.getByName(resourceProperties.get("serverHardware")));
        if (server != null) {
            sp.setServerHardwareUri(server.getUri());
            sp.setServerHardwareTypeUri(server.getServerHardwareTypeUri());
        }
    }

    private void addConnections(ServerProfile sp) {
        String ethNetworks = resourceProperties.get("networks");
        String fcNetwork = resourceProperties.get("fcNetworks");
        if (ethNetworks != null) {
            sp.setConnections(configConnections(ethNetworks, FunctionType.Ethernet, "1000"));
        } else if (fcNetwork != null) {
            sp.setConnections(configConnections(fcNetwork, FunctionType.FibreChannel,
                    resourceProperties.get("requestBandwidth")));
        }

    }

    private List<ProfileConnection> configConnections(String network, FunctionType functionType, String mbps) {
        List<ProfileConnection> connections = new ArrayList<ProfileConnection>();
        List<String> networks = Arrays.asList(network.replaceAll(" ", "").split(","));
        int j = 1;
        for (String networkName : networks) {
            ProfileConnection profileConnection = resourceDtoUtils.buildProfileConnection(j++, networkName, mbps,
                    Integer.parseInt(mbps), Integer.parseInt(mbps), functionType, null, null);

            profileConnection.setPortId("Auto");
            profileConnection.setRequestedMbps(mbps);

            profileConnection.setAllocatedMbps(null);
            profileConnection.setMaximumMbps(null);

            profileConnection.setBoot(null);

            connections.add(profileConnection);
        }
        return connections;
    }

    private void addVolume(ServerProfile sp) {
        String hostOSType = resourceProperties.get("hostOSType");
        String volumeName = resourceProperties.get("volume");
        String fcNetworkName = resourceProperties.get("fcNetworks");

        if (hostOSType != null && volumeName != null && fcNetworkName != null) {
            List<VolumeAttachment> volumeAttachments = new ArrayList<VolumeAttachment>();
            HashMap<String, Integer> fcId = new HashMap<String, Integer>();

            List<String> networks = Arrays.asList(fcNetworkName.replaceAll(" ", "").split(","));
            int i = 1;
            for (String fc : networks) {
                fcId.put(fc, i++);
            }

            VolumeAttachment volumeAttachment = resourceDtoUtils.buildVolumeAttachment(volumeName, false, 1, true, null,
                    StorageTargetType.Auto, "Auto", fcId);

            volumeAttachments.add(volumeAttachment);

            SanStorage sanStorage = resourceDtoUtils.buildSanStorage(hostOSType, volumeAttachments);

            sp.setSanStorage(sanStorage);
        }
    }

    private void addFirmware(ServerProfile sp) {
        Firmware firmware = new Firmware();
        firmware.setFirmwareBaselineUri(null);
        firmware.setForceInstallFirmware(false);
        firmware.setManageFirmware(false);
        sp.setFirmware(firmware);
    }

    private void addBios(ServerProfile sp) {
        Bios bios = new Bios();
        bios.setManageBios(false);
        bios.setOverriddenSettings(null);
        sp.setBios(bios);
    }

    private void addBoot(ServerProfile sp) {
        Boot boot = new Boot();
        boot.setManageBoot(false);
        sp.setBoot(boot);
    }

}
