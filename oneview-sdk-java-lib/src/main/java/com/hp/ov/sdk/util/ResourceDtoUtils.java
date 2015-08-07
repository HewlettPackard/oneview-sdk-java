/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hp.ov.sdk.constants.ResourceCategory;
import com.hp.ov.sdk.constants.SdkConstants;
import com.hp.ov.sdk.dto.ConnectionBoot;
import com.hp.ov.sdk.dto.ConnectionBoot.BootControl;
import com.hp.ov.sdk.dto.ProfileConnectionV3;
import com.hp.ov.sdk.dto.StorageTargetPortCollection;
import com.hp.ov.sdk.dto.generated.Bandwidth;
import com.hp.ov.sdk.dto.generated.Bios;
import com.hp.ov.sdk.dto.generated.Boot;
import com.hp.ov.sdk.dto.generated.FcNetwork;
import com.hp.ov.sdk.dto.generated.FcNetwork.FabricType;
import com.hp.ov.sdk.dto.generated.Firmware;
import com.hp.ov.sdk.dto.generated.InterconnectMapEntryTemplate;
import com.hp.ov.sdk.dto.generated.InterconnectMapTemplate;
import com.hp.ov.sdk.dto.generated.InterconnectTypes;
import com.hp.ov.sdk.dto.generated.LocalStorage;
import com.hp.ov.sdk.dto.generated.LocationEntry;
import com.hp.ov.sdk.dto.generated.LogicalInterconnectGroups;
import com.hp.ov.sdk.dto.generated.LogicalLocation;
import com.hp.ov.sdk.dto.generated.LogicalPortConfigInfo;
import com.hp.ov.sdk.dto.generated.NetworkSets;
import com.hp.ov.sdk.dto.generated.PortInfo;
import com.hp.ov.sdk.dto.generated.SanStorage;
import com.hp.ov.sdk.dto.generated.ServerProfile;
import com.hp.ov.sdk.dto.generated.StoragePath;
import com.hp.ov.sdk.dto.generated.StoragePath.StorageTargetType;
import com.hp.ov.sdk.dto.generated.UplinkSet;
import com.hp.ov.sdk.dto.generated.VolumeAttachment;
import com.hp.ov.sdk.exceptions.SDKErrorEnum;
import com.hp.ov.sdk.exceptions.SDKInvalidArgumentException;
import com.hp.ov.sdk.rest.client.InterconnectTypeClient;
import com.hp.ov.sdk.rest.client.StorageSystemClient;
import com.hp.ov.sdk.rest.http.core.client.RestParams;

@Component
public class ResourceDtoUtils {

    @Autowired
    private SdkUtils utils;

    @Autowired
    private InterconnectTypeClient interconnectTypeClient;

    @Autowired
    private StorageSystemClient storageSystemClient;

    private static final String active = "Active";

    public NetworkSets buildNetworkSetDto(final RestParams params, final String networkSetName, final List<String> networkNames,
            final String nativeNetworkName) {
        final NetworkSets dto = buildNetworkSetDto(params, networkSetName, networkNames);
        dto.setNativeNetworkUri(utils.getNetworkUri(params, nativeNetworkName));
        return dto;
    }

    public NetworkSets buildNetworkSetDto(final RestParams params, final String networkSetName, final List<String> networkNames) {
        final NetworkSets dto = new NetworkSets();
        dto.setName(networkSetName);
        dto.setNativeNetworkUri(null);
        dto.setNetworkUris(utils.getNetworkUris(params, networkNames));
        dto.setConnectionTemplateUri(null);
        dto.setType(ResourceCategory.RC_NETWORKSET);

        return dto;
    }

    public Bandwidth buildBandwidth(final Double maxBandwidth, final Double minBandwidth) {

        final Bandwidth bandwidth = new Bandwidth();
        bandwidth.setMaximumBandwidth(maxBandwidth);
        bandwidth.setTypicalBandwidth(minBandwidth);
        return bandwidth;
    }

    public FcNetwork buildFcNetworkDto(final String fcNetworkName) {
        final FcNetwork fcNetworkDto = new FcNetwork();

        fcNetworkDto.setName(fcNetworkName);
        fcNetworkDto.setConnectionTemplateUri(null);
        fcNetworkDto.setLinkStabilityTime(30);
        fcNetworkDto.setAutoLoginRedistribution(true);
        fcNetworkDto.setFabricType(FabricType.FabricAttach);
        // fcNetworkDto.setManagedSanUri(managedSanUri);
        fcNetworkDto.setType(ResourceCategory.RC_FCNETWORK);

        return fcNetworkDto;
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
        dto.setState(active);
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
            final LogicalLocation logicalLocationDto = new LogicalLocation();
            logicalLocationDto.setLocationEntries(locationEntriesDto);
            interconnectMapEntryTemplateDto.setLogicalLocation(logicalLocationDto);
            if (bayPermittedInterconnectMaps.get((i + 1)) != null) {
                interconnectMapEntryTemplateDto.setPermittedInterconnectTypeUri(utils.getPermittedInterconnectTypeUri(params,
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
        dto.setType(ResourceCategory.RC_LOGICALINTERCONNECTGROUP);
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

        for (final Entry<Integer, List<String>> entry : bayPortMap.entrySet()) {
            final Integer bayRelativeValue = entry.getKey();
            final List<String> portNames = entry.getValue();

            final String permittedInterconnectTypeUri = utils.getPermittedInterconnectTypeUriForLigBasedOnBay(params, ligName,
                    bayRelativeValue);
            final InterconnectTypes interconnectTypeDto = interconnectTypeClient.getInterconnectType(params,
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
                logicalPortConfigInfo.setDesiredSpeed(LogicalPortConfigInfo.DesiredSpeed.Auto);

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
            uplinkSetDto.setNetworkUris(utils.getNetworkUris(params, networkNames));
        } else if (uplinkSetType.equalsIgnoreCase(SdkConstants.FIBRE_CHANNEL)) {
            uplinkSetDto.setNetworkType(UplinkSet.NetworkType.FibreChannel);
            uplinkSetDto.setNetworkUris(utils.getFcNetworkUris(params, networkNames));
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
            connection.setNetworkUri(utils.getNetworkUri(params, networkName));
        } else if (functionType.toString().equalsIgnoreCase("FibreChannel")) {
            connection.setNetworkUri(utils.getFcNetworkUri(params, networkName));
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
        final Boolean volumeIsSharable = utils.isVolumeSharable(params, volumeName);
        if (volumeIsSharable || !(useBayNameForServerHardwareUri)) {
            final VolumeAttachment volumeAttachment = new VolumeAttachment();
            volumeAttachment.setId(Double.parseDouble(j.toString()));
            volumeAttachment.setLunType(lunType);
            volumeAttachment.setVolumeUri(utils.getVolumeUri(params, volumeName));
            volumeAttachment.setVolumeStoragePoolUri(utils.getStoragePoolFromVolume(params, volumeName));
            volumeAttachment.setVolumeStorageSystemUri(utils.getStorageSystemFromVolume(params, volumeName));

            final StorageTargetPortCollection storageTargetPortCollectionDto = storageSystemClient
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
        serverProfileDto.setType(ResourceCategory.RC_SERVER_PROFILE);
        serverProfileDto.setName(profileName);
        if (serverHardwareName != null && serverHardwareName.length() != 0) {
            final String serverHardwareTypeUri = utils.getServerHardwareTypeUri(params, serverHardwareName);
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
            final String serverHardwareUri = utils.getServerHardwareUri(params, serverHardwareName);
            serverProfileDto
                    .setServerHardwareUri((serverHardwareUri != null && serverHardwareUri.length() != 0) ? serverHardwareUri : null);
        }
        serverProfileDto.setEnclosureGroupUri(utils.getEnclosureGroupUri(params, enclosureGroupName));
        serverProfileDto.setAffinity(affinity);
        serverProfileDto.setHideUnusedFlexNics(false);
        serverProfileDto.setFirmware(firmware);

        serverProfileDto.setMacType(macType);
        serverProfileDto.setWwnType(wwnType);
        serverProfileDto.setSerialNumberType(serialNumberType);
        serverProfileDto.setCategory(SdkConstants.SERVER_PROFILES);

        serverProfileDto.setConnections(connections);
        serverProfileDto.setBoot(boot);
        serverProfileDto.setBios(bios);
        serverProfileDto.setLocalStorage(localStorage);

        serverProfileDto.setSanStorage(sanStorage);

        return serverProfileDto;
    }
}
