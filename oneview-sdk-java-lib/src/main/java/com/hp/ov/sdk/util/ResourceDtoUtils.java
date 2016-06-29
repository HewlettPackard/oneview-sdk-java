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
import com.hp.ov.sdk.dto.ConnectionBoot;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;
import com.hp.ov.sdk.dto.InterconnectType;
import com.hp.ov.sdk.dto.OpSpeed;
import com.hp.ov.sdk.dto.PortInfo;
import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.ResourceCollection;
import com.hp.ov.sdk.dto.StorageTargetPort;
import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.BootMode;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.generated.InterconnectMapTemplate;
import com.hp.ov.sdk.dto.generated.LocalStorage;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.LogicalLocation;
import com.hp.ov.sdk.dto.generated.LogicalPortConfigInfo;
import com.hp.ov.sdk.dto.generated.SanStorage;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.dto.generated.StoragePath;
import com.hp.ov.sdk.dto.generated.StoragePath.StorageTargetType;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.dto.generated.VolumeAttachment;
import com.hp.ov.sdk.dto.networking.ethernet.Network;
import com.hp.ov.sdk.dto.networking.fcnetworks.FcNetwork;
import com.hp.ov.sdk.dto.networking.networkset.NetworkSet;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.client.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.InterconnectTypeClientImpl;
import com.hp.ov.sdk.rest.client.OneViewClient;
import com.hp.ov.sdk.rest.client.StorageSystemClient;
import com.hp.ov.sdk.rest.client.StorageSystemClientImpl;
import com.hp.ov.sdk.rest.http.core.client.ApiVersion;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

public class ResourceDtoUtils {

    private static final String ACTIVE = "Active";

    private final OneViewClient oneViewClient;
    private final StorageSystemClient storageSystemClient;

    public ResourceDtoUtils(OneViewClient oneViewClient) {
        this.oneViewClient = oneViewClient;

        this.storageSystemClient = StorageSystemClientImpl.getClient();
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
            Network dto = oneViewClient.ethernetNetwork().getByName(networkName);

            if (dto.getUri() != null) {
                String networkUri = dto.getUri();

                networkUris.add(networkUri);
            }
        }
        return networkUris;
    }

    public List<String> getFcNetworkUris(final RestParams params, final List<String> networkNames) {
        List<String> networkUris = new ArrayList<String>();
        FcNetwork dto = null;
        String fcNetworkUri = null;

        for (String networkName : networkNames) {
            dto = oneViewClient.fcNetwork().getByName(networkName);

            if (null != dto.getUri()) {
                fcNetworkUri = dto.getUri();
                networkUris.add(fcNetworkUri);
            }

        }
        return networkUris;
    }

    public LogicalInterconnectGroups buildLogicalInterconnectGroupDto(final RestParams params,
            final String logicalInterconnectGroupName, final HashMap<Integer, String> bayPermittedInterconnectMaps) {
        // local variable declaration.
        int i, j;
        final LogicalInterconnectGroups dto = new LogicalInterconnectGroups();
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
            final List<LocationEntry> locationEntriesDto = new ArrayList<LocationEntry>();
            for (j = 0; j < 2; j++) {
                final LocationEntry locationEntryDto = new LocationEntry();
                if (j == 0) {
                    locationEntryDto.setRelativeValue(i + 1);
                    locationEntryDto.setType(LocationEntry.Type.Bay);
                } else {
                    locationEntryDto.setRelativeValue(j);
                    locationEntryDto.setType(LocationEntry.Type.Enclosure);
                }
                locationEntriesDto.add(locationEntryDto);
            }
            LogicalLocation logicalLocationDto = new LogicalLocation();

            logicalLocationDto.setLocationEntries(locationEntriesDto);
            interconnectMapEntryTemplateDto.setLogicalLocation(logicalLocationDto);

            if (bayPermittedInterconnectMaps.get((i + 1)) != null) {
                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(SdkUtils.getInstance().getPermittedInterconnectTypeUri(params,
                        bayPermittedInterconnectMaps.get((i + 1))));
            } else {
                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(null);
            }
            interconnectMapEntryTemplatesDto.add(interconnectMapEntryTemplateDto);
        }

        final InterconnectMapTemplate interconnectMapTemplateDto = new InterconnectMapTemplate();
        interconnectMapTemplateDto.setInterconnectMapEntryTemplates(interconnectMapEntryTemplatesDto);
        dto.setInterconnectMapTemplate(interconnectMapTemplateDto);

        dto.setUri(null);
        if (params.getApiVersion().getValue() < ApiVersion.V_200.getValue()) {
            dto.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP);
        } else {
            dto.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP_V200);
        }
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
    public UplinkSet buildUplinkSetDto(final RestParams params, final String ligName, final String uplinkSetName,
            final String uplinkSetType, final List<String> networkNames, final HashMap<Integer, List<String>> bayPortMap,
            final String lacpTimer, final String fcUplinkSpeed) {
        return buildUplinkSetDto(params, ligName, uplinkSetName, uplinkSetType, bayPortMap, networkNames);
    }

    public UplinkSet buildUplinkSetDto(final RestParams params, final String ligName, final String uplinkSetName,
            final String uplinkSetType, final HashMap<Integer, List<String>> bayPortMap, final List<String> networkNames) {
        final List<LogicalPortConfigInfo> logicalPortConfigInfos = new ArrayList<LogicalPortConfigInfo>();
        final UplinkSet uplinkSetDto = new UplinkSet();

        InterconnectTypeClient interconnectTypeClient = InterconnectTypeClientImpl.getClient();

        for (final Entry<Integer, List<String>> entry : bayPortMap.entrySet()) {
            final Integer bayRelativeValue = entry.getKey();
            final List<String> portNames = entry.getValue();

            final String permittedInterconnectTypeUri = SdkUtils.getInstance().getPermittedInterconnectTypeUriForLigBasedOnBay(params, ligName,
                    bayRelativeValue);
            final InterconnectType interconnectTypeDto = interconnectTypeClient.getInterconnectType(params,
                    (permittedInterconnectTypeUri.substring(permittedInterconnectTypeUri.lastIndexOf("/") + 1)));

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
                final List<LocationEntry> locationEntriesList = new ArrayList<LocationEntry>();
                final LocationEntry locationEntries11 = new LocationEntry();
                locationEntries11.setRelativeValue(bayRelativeValue);
                locationEntries11.setType(LocationEntry.Type.Bay);
                final LocationEntry locationEntries12 = new LocationEntry();
                locationEntries12.setRelativeValue(portNumber);
                locationEntries12.setType(LocationEntry.Type.Port);
                final LocationEntry locationEntries13 = new LocationEntry();
                locationEntries13.setRelativeValue(1);
                locationEntries13.setType(LocationEntry.Type.Enclosure);
                locationEntriesList.add(locationEntries11);
                locationEntriesList.add(locationEntries12);
                locationEntriesList.add(locationEntries13);
                logicalLocation.setLocationEntries(locationEntriesList);
                logicalPortConfigInfo.setLogicalLocation(logicalLocation);

                logicalPortConfigInfos.add(logicalPortConfigInfo);
            }
        }

        uplinkSetDto.setName(uplinkSetName);
        uplinkSetDto.setMode(UplinkSet.Mode.Auto);
        if (uplinkSetType.equalsIgnoreCase(SdkConstants.ETHERNET)) {
            uplinkSetDto.setNetworkType(UplinkSet.NetworkType.Ethernet);
            uplinkSetDto.setNetworkUris(this.getNetworkUris(networkNames));
        } else if (uplinkSetType.equalsIgnoreCase(SdkConstants.FIBRE_CHANNEL)) {
            uplinkSetDto.setNetworkType(UplinkSet.NetworkType.FibreChannel);
            uplinkSetDto.setNetworkUris(this.getFcNetworkUris(params, networkNames));
        }
        uplinkSetDto.setPrimaryPort(null);

        uplinkSetDto.setLogicalPortConfigInfos(logicalPortConfigInfos);

        return uplinkSetDto;
    }

    public ProfileConnectionV3 buildProfileConnection(final RestParams params, final Integer j, final String networkName,
            final String requestedMbps, final Integer allocatedMbps, final Integer maximumMbps,
            final ProfileConnectionV3.FunctionType functionType, final BootControl bootControl) {
        final ProfileConnectionV3 connection = new ProfileConnectionV3();
        connection.setId(j);
        connection.setName(networkName);
        connection.setFunctionType(functionType);
        if (functionType.toString().equalsIgnoreCase("Ethernet")) {
            connection.setNetworkUri(oneViewClient.ethernetNetwork().getByName(networkName).getUri());
        } else if (functionType.toString().equalsIgnoreCase("FibreChannel")) {
            connection.setNetworkUri(oneViewClient.fcNetwork().getByName(networkName).getUri());
        }
        connection.setRequestedMbps(requestedMbps);
        connection.setAllocatedMbps(allocatedMbps);
        connection.setMaximumMbps(maximumMbps);
        final ConnectionBoot connBoot = new ConnectionBoot();
        connBoot.setPriority(bootControl);
        connection.setBoot(connBoot);
        return connection;
    }

    public SanStorage buildSanStorage(final RestParams params, final String hostOSType,
            final List<VolumeAttachment> volumeAttachments) {
        final SanStorage sanStorage = new SanStorage();
        sanStorage.setHostOSType(hostOSType);
        sanStorage.setManageSanStorage(true);
        sanStorage.setVolumeAttachments(volumeAttachments);
        return sanStorage;
    }

    public VolumeAttachment buildVolumeAttachment(final RestParams params, final String volumeName,
            final Boolean useBayNameForServerHardwareUri, final Integer j, final Boolean isEnabled,
            final List<String> storageTargets, final StorageTargetType storageTargetType, final String lunType,
            final HashMap<String, Integer> fcId) {
        final Boolean volumeIsSharable = SdkUtils.getInstance().isVolumeSharable(params, volumeName);
        if (volumeIsSharable || !(useBayNameForServerHardwareUri)) {
            final VolumeAttachment volumeAttachment = new VolumeAttachment();
            volumeAttachment.setId(Double.parseDouble(j.toString()));
            volumeAttachment.setLunType(lunType);
            volumeAttachment.setVolumeUri(SdkUtils.getInstance().getVolumeUri(params, volumeName));
            volumeAttachment.setVolumeStoragePoolUri(SdkUtils.getInstance().getStoragePoolFromVolume(params, volumeName));
            volumeAttachment.setVolumeStorageSystemUri(SdkUtils.getInstance().getStorageSystemFromVolume(params, volumeName));

            final ResourceCollection<StorageTargetPort> storageTargetPortCollectionDto = storageSystemClient
                    .getAllManagedPortsForStorageSystem(
                            params,
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

    public ServerProfile buildServerProfile(final RestParams params, final String profileName, final String serverHardwareName,
            final Boolean useBayNameForServerHardwareUri, final String enclosureGroupName,
            final ServerProfile.ProfileAffinity affinity, final ServerProfile.AssignmentType wwnType,
            final ServerProfile.AssignmentType macType, final ServerProfile.AssignmentType serialNumberType,
            final SanStorage sanStorage, final List<ProfileConnectionV3> connections, final LocalStorage localStorage,
            final Boot boot, final Bios bios, final Firmware firmware) {
        final ServerProfile serverProfileDto = new ServerProfile();
        serverProfileDto.setDescription("profile");

        if (params.getApiVersion().getValue() < ApiVersion.V_200.getValue()) {
            serverProfileDto.setType(ResourceCategory.RC_SERVER_PROFILE);
        } else {
            serverProfileDto.setType(ResourceCategory.RC_SERVER_PROFILE_V200);
        }

        serverProfileDto.setName(profileName);
        if (serverHardwareName != null && serverHardwareName.length() != 0) {
            final String serverHardwareTypeUri = SdkUtils.getInstance().getServerHardwareTypeUri(params, serverHardwareName);
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
            final String serverHardwareUri = SdkUtils.getInstance().getServerHardwareUri(params, serverHardwareName);
            serverProfileDto
                    .setServerHardwareUri((serverHardwareUri != null && serverHardwareUri.length() != 0) ? serverHardwareUri : null);
        }
        serverProfileDto.setEnclosureGroupUri(SdkUtils.getInstance().getEnclosureGroupUri(params, enclosureGroupName));
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
}
