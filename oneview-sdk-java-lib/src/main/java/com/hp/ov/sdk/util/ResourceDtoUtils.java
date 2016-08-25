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
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.InterconnectTypeName;
import com.hp.ov.sdk.dto.PortInfo;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.dto.StorageVolume;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.generated.InterconnectMapTemplate;
import com.hp.ov.sdk.dto.networking.LocationType;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.LogicalLocationEntry;
import com.hp.ov.sdk.dto.networking.NetworkType;
import com.hp.ov.sdk.dto.networking.OpSpeed;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.EnetPortSetAggregationMode;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalInterconnectGroup;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.LogicalPortConfigInfo;
import com.hp.ov.sdk.dto.networking.logicalinterconnectgroup.UplinkSetGroup;
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.Bios;
import com.hp.ov.sdk.dto.servers.Boot;
import com.hp.ov.sdk.dto.servers.serverprofile.BootControl;
import com.hp.ov.sdk.dto.servers.BootMode;
import com.hp.ov.sdk.dto.servers.serverprofile.ConnectionBoot;
import com.hp.ov.sdk.dto.servers.Firmware;
import com.hp.ov.sdk.dto.servers.FunctionType;
import com.hp.ov.sdk.dto.servers.serverprofile.LocalStorage;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.serverprofile.ProfileConnection;
import com.hp.ov.sdk.dto.servers.SanStorage;
import com.hp.ov.sdk.dto.servers.serverprofile.ServerProfile;
import com.hp.ov.sdk.dto.servers.StoragePath;
import com.hp.ov.sdk.dto.servers.StorageTargetType;
import com.hp.ov.sdk.dto.servers.VolumeAttachment;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.networking.InterconnectTypeClient;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;

public class ResourceDtoUtils {

    private static final String ACTIVE = "Active";

    private final OneViewClient oneViewClient;

    public ResourceDtoUtils(OneViewClient oneViewClient) {
        this.oneViewClient = oneViewClient;
    }

    public NetworkSet buildNetworkSetDto(String networkSetName, List<String> networkNames) {
        NetworkSet dto = new NetworkSet();

        dto.setName(networkSetName);
        dto.setNativeNetworkUri(null);
        dto.setNetworkUris(this.getNetworkUris(networkNames));
        dto.setConnectionTemplateUri(null);
        dto.setType(ResourceCategory.RC_NETWORKSET);

        return dto;
    }

    public List<String> getNetworkUris(List<String> networkNames) {
        List<String> networkUris = new ArrayList<String>();

        for (String networkName : networkNames) {
            Network dto = oneViewClient.ethernetNetwork().getByName(networkName).get(0);

            if (dto.getUri() != null) {
                String networkUri = dto.getUri();

                networkUris.add(networkUri);
            }
        }
        return networkUris;
    }

    public List<String> getFcNetworkUris(final List<String> networkNames) {
        List<String> networkUris = new ArrayList<String>();
        FcNetwork dto = null;
        String fcNetworkUri = null;

        for (String networkName : networkNames) {
            dto = oneViewClient.fcNetwork().getByName(networkName).get(0);

            if (null != dto.getUri()) {
                fcNetworkUri = dto.getUri();
                networkUris.add(fcNetworkUri);
            }

        }
        return networkUris;
    }

    public LogicalInterconnectGroup buildLogicalInterconnectGroupDto(final String logicalInterconnectGroupName,
            final HashMap<Integer, InterconnectTypeName> bayPermittedInterconnectMaps) {
        // local variable declaration.
        int i, j;
        final LogicalInterconnectGroup dto = new LogicalInterconnectGroup();
        dto.setCategory(null);
        dto.setCreated(null);
        dto.setDescription(null);
        dto.setETag(null);
        dto.setUplinkSets(null);
        dto.setModified(null);
        dto.setName(logicalInterconnectGroupName);
        dto.setState(ACTIVE);
        dto.setStatus(null);

        final List<InterconnectMapEntryTemplate> interconnectMapEntryTemplatesDto = new ArrayList<InterconnectMapEntryTemplate>();

        for (i = 0; i < 8; i++) {
            final InterconnectMapEntryTemplate interconnectMapEntryTemplateDto = new InterconnectMapEntryTemplate();
            interconnectMapEntryTemplateDto.setLogicalDownlinkUri(null);
            final List<LogicalLocationEntry> locationEntriesDto = new ArrayList<LogicalLocationEntry>();
            for (j = 0; j < 2; j++) {
                final LogicalLocationEntry locationEntryDto = new LogicalLocationEntry();
                if (j == 0) {
                    locationEntryDto.setRelativeValue(i + 1);
                    locationEntryDto.setType(LocationType.Bay);
                } else {
                    locationEntryDto.setRelativeValue(j);
                    locationEntryDto.setType(LocationType.Enclosure);
                }
                locationEntriesDto.add(locationEntryDto);
            }
            LogicalLocation logicalLocationDto = new LogicalLocation();

            logicalLocationDto.setLocationEntries(locationEntriesDto);
            interconnectMapEntryTemplateDto.setLogicalLocation(logicalLocationDto);

            if (bayPermittedInterconnectMaps.get((i + 1)) != null) {
                InterconnectTypeName bayPermittedInterconnect = bayPermittedInterconnectMaps.get(i + 1);
                String interconnectTypeUri = oneViewClient.interconnectType().getByName(bayPermittedInterconnect).get(0).getUri();

                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(interconnectTypeUri);
            } else {
                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(null);
            }
            interconnectMapEntryTemplatesDto.add(interconnectMapEntryTemplateDto);
        }

        final InterconnectMapTemplate interconnectMapTemplateDto = new InterconnectMapTemplate();
        interconnectMapTemplateDto.setInterconnectMapEntryTemplates(interconnectMapEntryTemplatesDto);
        dto.setInterconnectMapTemplate(interconnectMapTemplateDto);

        dto.setUri(null);

        return dto;
    }

    /*
     * public UplinkSet buildUplinkSetDto(final RestParams params, String
     * ligName, String uplinkSetName, String uplinkSetType, List<String>
     * networkNames, String nativeEthNetwork, HashMap<Integer, List<String>>
     * bayPortMap, String ethMode, String lacpTimer, String fcUplinkSpeed) {
     *
     * }
     */
    public UplinkSetGroup buildUplinkSetDto(final String ligName, final String uplinkSetName,
            final String uplinkSetType, final List<String> networkNames, final HashMap<Integer, List<String>> bayPortMap,
            final String lacpTimer, final String fcUplinkSpeed) {
        return buildUplinkSetDto(ligName, uplinkSetName, uplinkSetType, bayPortMap, networkNames);
    }

    public UplinkSetGroup buildUplinkSetDto(final String ligName, final String uplinkSetName,
            final String uplinkSetType, final HashMap<Integer, List<String>> bayPortMap, final List<String> networkNames) {
        final List<LogicalPortConfigInfo> logicalPortConfigInfos = new ArrayList<LogicalPortConfigInfo>();
        final UplinkSetGroup uplinkSetDto = new UplinkSetGroup();

        InterconnectTypeClient interconnectTypeClient = oneViewClient.interconnectType();

        for (final Entry<Integer, List<String>> entry : bayPortMap.entrySet()) {
            final Integer bayRelativeValue = entry.getKey();
            final List<String> portNames = entry.getValue();

            final String permittedInterconnectTypeUri = this.getPermittedInterconnectTypeUriForLigBasedOnBay(ligName, bayRelativeValue);
            final InterconnectType interconnectTypeDto = interconnectTypeClient.getById((permittedInterconnectTypeUri.substring(permittedInterconnectTypeUri.lastIndexOf("/") + 1)));

            for (int i = 0; i < portNames.size(); i++) {
                Integer portNumber = -1;
                for (final PortInfo portInfo : interconnectTypeDto.getPortInfos()) {
                    if (portInfo.getPortName().equalsIgnoreCase(portNames.get(i))) {
                        portNumber = portInfo.getPortNumber();
                    }
                }
                if (portNumber == -1) {
                    throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.UPLINKSET
                            + " port not found for Interconnect Type.", null);
                }

                final LogicalPortConfigInfo logicalPortConfigInfo = new LogicalPortConfigInfo();
                logicalPortConfigInfo.setDesiredSpeed(OpSpeed.Auto);

                final LogicalLocation logicalLocation = new LogicalLocation();
                final List<LogicalLocationEntry> locationEntriesList = new ArrayList<LogicalLocationEntry>();
                final LogicalLocationEntry locationEntries11 = new LogicalLocationEntry();
                locationEntries11.setRelativeValue(bayRelativeValue);
                locationEntries11.setType(LocationType.Bay);
                final LogicalLocationEntry locationEntries12 = new LogicalLocationEntry();
                locationEntries12.setRelativeValue(portNumber);
                locationEntries12.setType(LocationType.Port);
                final LogicalLocationEntry locationEntries13 = new LogicalLocationEntry();
                locationEntries13.setRelativeValue(1);
                locationEntries13.setType(LocationType.Enclosure);
                locationEntriesList.add(locationEntries11);
                locationEntriesList.add(locationEntries12);
                locationEntriesList.add(locationEntries13);
                logicalLocation.setLocationEntries(locationEntriesList);
                logicalPortConfigInfo.setLogicalLocation(logicalLocation);

                logicalPortConfigInfos.add(logicalPortConfigInfo);
            }
        }

        uplinkSetDto.setName(uplinkSetName);
        uplinkSetDto.setMode(EnetPortSetAggregationMode.Auto);
        if (uplinkSetType.equalsIgnoreCase(SdkConstants.ETHERNET)) {
            uplinkSetDto.setNetworkType(NetworkType.Ethernet);
            uplinkSetDto.setNetworkUris(this.getNetworkUris(networkNames));
        } else if (uplinkSetType.equalsIgnoreCase(SdkConstants.FIBRE_CHANNEL)) {
            uplinkSetDto.setNetworkType(NetworkType.FibreChannel);
            uplinkSetDto.setNetworkUris(this.getFcNetworkUris(networkNames));
        }
        uplinkSetDto.setPrimaryPort(null);

        uplinkSetDto.setLogicalPortConfigInfos(logicalPortConfigInfos);

        return uplinkSetDto;
    }

    private String getPermittedInterconnectTypeUriForLigBasedOnBay(final String ligName, final Integer bay) {
        ResourceCollection<LogicalInterconnectGroup> logicalInterconnectGroups = oneViewClient.logicalInterconnectGroup().getByName(ligName);
        if (logicalInterconnectGroups == null || logicalInterconnectGroups.getCount() < 1) {
            return null;
        }


        LogicalInterconnectGroup logicalInterconnectGroupsDto = logicalInterconnectGroups.getMembers().get(0);
        if (logicalInterconnectGroupsDto.getInterconnectMapTemplate() == null) {
            return null;
        }

        for (InterconnectMapEntryTemplate mapTemplate : logicalInterconnectGroupsDto.getInterconnectMapTemplate()
                .getInterconnectMapEntryTemplates()) {
            for (LogicalLocationEntry locationEntry : mapTemplate.getLogicalLocation().getLocationEntries()) {
                if (locationEntry.getType().equals(LocationType.Bay) && locationEntry.getRelativeValue().equals(bay)) {
                    return mapTemplate.getPermittedInterconnectTypeUri();
                }
            }
        }
        return null;
    }

    public ProfileConnection buildProfileConnection(final Integer j, final String networkName,
            final String requestedMbps, final Integer allocatedMbps, final Integer maximumMbps,
            final FunctionType functionType, final BootControl bootControl) {
        final ProfileConnection connection = new ProfileConnection();
        connection.setId(j);
        connection.setName(networkName);
        connection.setFunctionType(functionType);
        if (functionType.toString().equalsIgnoreCase("Ethernet")) {
            connection.setNetworkUri(oneViewClient.ethernetNetwork().getByName(networkName).getUri());
        } else if (functionType.toString().equalsIgnoreCase("FibreChannel")) {
            connection.setNetworkUri(oneViewClient.fcNetwork().getByName(networkName).get(0).getUri());
        }
        connection.setRequestedMbps(requestedMbps);
        connection.setAllocatedMbps(allocatedMbps);
        connection.setMaximumMbps(maximumMbps);
        final ConnectionBoot connBoot = new ConnectionBoot();
        connBoot.setPriority(bootControl);
        connection.setBoot(connBoot);
        return connection;
    }

    public SanStorage buildSanStorage(final String hostOSType,
            final List<VolumeAttachment> volumeAttachments) {
        final SanStorage sanStorage = new SanStorage();
        sanStorage.setHostOSType(hostOSType);
        sanStorage.setManageSanStorage(true);
        sanStorage.setVolumeAttachments(volumeAttachments);
        return sanStorage;
    }

    public VolumeAttachment buildVolumeAttachment(final String volumeName,
            final Boolean useBayNameForServerHardwareUri, final Integer volumeAttachmentId, final Boolean isEnabled,
            final List<String> storageTargets, final StorageTargetType storageTargetType, final String lunType,
            final HashMap<String, Integer> fcId) {

        StorageVolume storageVolume = oneViewClient.storageVolume().getByName(volumeName).get(0);

        if (storageVolume.getShareable() || !(useBayNameForServerHardwareUri)) {
            final VolumeAttachment volumeAttachment = new VolumeAttachment();
            volumeAttachment.setId(volumeAttachmentId);
            volumeAttachment.setLunType(lunType);
            volumeAttachment.setVolumeUri(storageVolume.getUri());
            volumeAttachment.setVolumeStoragePoolUri(storageVolume.getStoragePoolUri());
            volumeAttachment.setVolumeStorageSystemUri(storageVolume.getStorageSystemUri());

            ResourceCollection<StorageTargetPort> storageTargetPortCollectionDto = oneViewClient.storageSystem().getAllManagedPorts(
                    volumeAttachment.getVolumeStorageSystemUri().substring(
                            volumeAttachment.getVolumeStorageSystemUri().lastIndexOf("/") + 1));

            final List<StoragePath> storagePaths = new ArrayList<StoragePath>();
            for (int k = 0; k < storageTargetPortCollectionDto.getCount(); k++) {
                if (fcId.containsKey(storageTargetPortCollectionDto.getMembers().get(k).getExpectedNetworkName())) {
                    final StoragePath storagePath = new StoragePath();
                    storagePath.setIsEnabled(isEnabled);
                    storagePath.setConnectionId(fcId.get(storageTargetPortCollectionDto.getMembers().get(k)
                            .getExpectedNetworkName()));
                    storagePath.setStorageTargets(storageTargets);
                    storagePath.setStorageTargetType(storageTargetType);
                    storagePaths.add(storagePath);
                }
            }
            volumeAttachment.setStoragePaths(storagePaths);

            return volumeAttachment;
        }
        return null;
    }

    public ServerProfile buildServerProfile(ApiVersion apiVersion, final String profileName, final String serverHardwareName,
            final Boolean useBayNameForServerHardwareUri, final String enclosureGroupName,
            final ProfileAffinity affinity, final AssignmentType wwnType,
            final AssignmentType macType, final AssignmentType serialNumberType,
            final SanStorage sanStorage, final List<ProfileConnection> connections, final LocalStorage localStorage,
            final Boot boot, final Bios bios, final Firmware firmware) {
        final ServerProfile serverProfileDto = new ServerProfile();
        serverProfileDto.setDescription("profile");

        if (apiVersion.getValue() < ApiVersion.V_200.getValue()) {
            serverProfileDto.setType(ResourceCategory.RC_SERVER_PROFILE);
        } else {
            serverProfileDto.setType(ResourceCategory.RC_SERVER_PROFILE_V200);
        }

        serverProfileDto.setName(profileName);
        if (serverHardwareName != null && serverHardwareName.length() != 0) {
            final String serverHardwareTypeUri = getServerHardwareTypeUri(serverHardwareName);
            serverProfileDto
                    .setServerHardwareTypeUri((serverHardwareTypeUri != null && serverHardwareTypeUri.length() != 0) ? serverHardwareTypeUri
                            : null);
            if (serverProfileDto.getServerHardwareTypeUri() == null) {
                throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_HARDWARE,
                        null);
            }
        } else {
            throw new SDKInvalidArgumentException(SDKErrorEnum.invalidArgument, null, null, null, SdkConstants.SERVER_HARDWARE,
                    null);
        }
        if (useBayNameForServerHardwareUri) {
            final String serverHardwareUri = getServerHardwareUri(serverHardwareName);
            serverProfileDto
                    .setServerHardwareUri((serverHardwareUri != null && serverHardwareUri.length() != 0) ? serverHardwareUri : null);
        }

        serverProfileDto.setEnclosureGroupUri(oneViewClient.enclosureGroup().getByName(enclosureGroupName).get(0).getUri());

        serverProfileDto.setAffinity(affinity);
        serverProfileDto.setHideUnusedFlexNics(false);
        serverProfileDto.setFirmware(firmware);

        serverProfileDto.setMacType(macType);
        serverProfileDto.setWwnType(wwnType);
        serverProfileDto.setSerialNumberType(serialNumberType);
        serverProfileDto.setCategory(SdkConstants.SERVER_PROFILES);

        serverProfileDto.setConnections(connections);
        serverProfileDto.setBoot(boot);

        BootMode bootMode = new BootMode();
        bootMode.setManageMode(true);
        bootMode.setMode("UEFI");
        serverProfileDto.setBootMode(bootMode);
        serverProfileDto.setBios(bios);
        serverProfileDto.setLocalStorage(localStorage);

        serverProfileDto.setSanStorage(sanStorage);

        return serverProfileDto;
    }

    public String getServerHardwareUri(String serverHardwareName) {
        return oneViewClient.serverHardware().getByName(serverHardwareName).get(0).getUri();
    }

    public String getServerHardwareTypeUri(String serverHardwareName) {
        return oneViewClient.serverHardware().getByName(serverHardwareName).get(0).getServerHardwareTypeUri();
    }

}
