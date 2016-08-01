/*
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
 */
package com.hp.ov.sdk.dto.servers.serverhardware;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.LicensingIntent;
import com.hp.ov.sdk.dto.ManagementHostInformation;
import com.hp.ov.sdk.dto.PhysicalServerMpState;
import com.hp.ov.sdk.dto.PhysicalServerPowerState;
import com.hp.ov.sdk.dto.PortMap;
import com.hp.ov.sdk.dto.RefreshState;

/**
 * The ServerHardware data transfer object (DTO) contains the information used
 * to represent a server hardware in the system. It is passed in to the
 * add/update server hardware REST api, as well as the add/update server
 * hardware through java client api.
 */
public class ServerHardware extends BaseModelResource {

    private static final long serialVersionUID = -9046023900390473327L;

    private String assetTag;
    private String formFactor;
    @Since(200)
    private String intelligentProvisioningVersion;
    private LicensingIntent licensingIntent;
    private String locationUri;
    private Integer memoryMb;
    @Since(300)
    private MigrationState migrationState;
    private String model;
    @Until(199)
    private String mpDnsName;
    private String mpFirmwareVersion;
    @Since(200)
    private ManagementHostInformation mpHostInfo;
    @Until(199)
    private String mpIpAddress;
    private String mpModel;
    @Since(200)
    private PhysicalServerMpState mpState;
    private String partNumber;
    private PortMap portMap;
    private Integer position;
    private Boolean powerLock = false;
    private PhysicalServerPowerState powerState;
    private Integer processorCoreCount;
    private Integer processorCount;
    private Integer processorSpeedMhz;
    private String processorType;
    private String profileNetworkSettingsState;
    private RefreshState refreshState;
    @Since(300)
    private RemoteSupportSettings remoteSupportSettings;
    @Since(300)
    private String remoteSupportUri;
    private String romVersion;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private String serialNumber;
    @Since(300)
    private String serverFirmwareInventoryUri;
    private String serverGroupUri;
    private String serverHardwareTypeUri;
    private String serverProfileUri;
    @Since(200)
    private ServerSettings serverSettings;
    private String shortModel;
    private Signature signature;
    private String stateReason;
    @Since(300)
    private SupportDataCollectionState supportDataCollectionState;
    @Since(300)
    private SupportDataCollectionType supportDataCollectionType;
    @Since(300)
    private String supportDataCollectionsUri;
    @Since(300)
    private SupportState supportState;
    private String uuid;
    @Since(300)
    private PhysicalServerUidState uidState;
    private String virtualSerialNumber;
    private String virtualUuid;

    /**
     * @return the assetTag
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * @param assetTag the assetTag to set
     */
    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * @return the formFactor
     */
    public String getFormFactor() {
        return formFactor;
    }

    /**
     * @param formFactor the formFactor to set
     */
    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    /**
     * @return the intelligentProvisioningVersion
     */
    public String getIntelligentProvisioningVersion() {
        return intelligentProvisioningVersion;
    }

    /**
     * @param intelligentProvisioningVersion the intelligentProvisioningVersion to set
     */
    public void setIntelligentProvisioningVersion(String intelligentProvisioningVersion) {
        this.intelligentProvisioningVersion = intelligentProvisioningVersion;
    }

    /**
     * @return the licensingIntent
     */
    public LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     * @param licensingIntent the licensingIntent to set
     */
    public void setLicensingIntent(LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    /**
     * @return the locationUri
     */
    public String getLocationUri() {
        return locationUri;
    }

    /**
     * @param locationUri the locationUri to set
     */
    public void setLocationUri(String locationUri) {
        this.locationUri = locationUri;
    }

    /**
     * @return the memoryMb
     */
    public Integer getMemoryMb() {
        return memoryMb;
    }

    /**
     * @param memoryMb the memoryMb to set
     */
    public void setMemoryMb(Integer memoryMb) {
        this.memoryMb = memoryMb;
    }

    /**
     * @return the migrationState
     */
    public MigrationState getMigrationState() {
        return migrationState;
    }

    /**
     * @param migrationState the migrationState to set
     */
    public void setMigrationState(MigrationState migrationState) {
        this.migrationState = migrationState;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the mpDnsName
     */
    public String getMpDnsName() {
        return mpDnsName;
    }

    /**
     * @param mpDnsName the mpDnsName to set
     */
    public void setMpDnsName(String mpDnsName) {
        this.mpDnsName = mpDnsName;
    }

    /**
     * @return the mpFirmwareVersion
     */
    public String getMpFirmwareVersion() {
        return mpFirmwareVersion;
    }

    /**
     * @param mpFirmwareVersion the mpFirmwareVersion to set
     */
    public void setMpFirmwareVersion(String mpFirmwareVersion) {
        this.mpFirmwareVersion = mpFirmwareVersion;
    }

    /**
     * @return the mpHostInfo
     */
    public ManagementHostInformation getMpHostInfo() {
        return mpHostInfo;
    }

    /**
     * @param mpHostInfo the mpHostInfo to set
     */
    public void setMpHostInfo(ManagementHostInformation mpHostInfo) {
        this.mpHostInfo = mpHostInfo;
    }

    /**
     * @return the mpIpAddress
     */
    public String getMpIpAddress() {
        return mpIpAddress;
    }

    /**
     * @param mpIpAddress the mpIpAddress to set
     */
    public void setMpIpAddress(String mpIpAddress) {
        this.mpIpAddress = mpIpAddress;
    }

    /**
     * @return the mpModel
     */
    public String getMpModel() {
        return mpModel;
    }

    /**
     * @param mpModel the mpModel to set
     */
    public void setMpModel(String mpModel) {
        this.mpModel = mpModel;
    }

    /**
     * @return the mpState
     */
    public PhysicalServerMpState getMpState() {
        return mpState;
    }

    /**
     * @param mpState the mpState to set
     */
    public void setMpState(PhysicalServerMpState mpState) {
        this.mpState = mpState;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the portMap
     */
    public PortMap getPortMap() {
        return portMap;
    }

    /**
     * @param portMap the portMap to set
     */
    public void setPortMap(PortMap portMap) {
        this.portMap = portMap;
    }

    /**
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Integer position) {
        this.position = position;
    }

    /**
     * @return the powerLock
     */
    public Boolean getPowerLock() {
        return powerLock;
    }

    /**
     * @param powerLock the powerLock to set
     */
    public void setPowerLock(Boolean powerLock) {
        this.powerLock = powerLock;
    }

    /**
     * @return the powerState
     */
    public PhysicalServerPowerState getPowerState() {
        return powerState;
    }

    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(PhysicalServerPowerState powerState) {
        this.powerState = powerState;
    }

    /**
     * @return the processorCoreCount
     */
    public Integer getProcessorCoreCount() {
        return processorCoreCount;
    }

    /**
     * @param processorCoreCount the processorCoreCount to set
     */
    public void setProcessorCoreCount(Integer processorCoreCount) {
        this.processorCoreCount = processorCoreCount;
    }

    /**
     * @return the processorCount
     */
    public Integer getProcessorCount() {
        return processorCount;
    }

    /**
     * @param processorCount the processorCount to set
     */
    public void setProcessorCount(Integer processorCount) {
        this.processorCount = processorCount;
    }

    /**
     * @return the processorSpeedMhz
     */
    public Integer getProcessorSpeedMhz() {
        return processorSpeedMhz;
    }

    /**
     * @param processorSpeedMhz the processorSpeedMhz to set
     */
    public void setProcessorSpeedMhz(Integer processorSpeedMhz) {
        this.processorSpeedMhz = processorSpeedMhz;
    }

    /**
     * @return the processorType
     */
    public String getProcessorType() {
        return processorType;
    }

    /**
     * @param processorType the processorType to set
     */
    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    /**
     * @return the profileNetworkSettingsState
     */
    public String getProfileNetworkSettingsState() {
        return profileNetworkSettingsState;
    }

    /**
     * @param profileNetworkSettingsState the profileNetworkSettingsState to set
     */
    public void setProfileNetworkSettingsState(String profileNetworkSettingsState) {
        this.profileNetworkSettingsState = profileNetworkSettingsState;
    }

    /**
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * @return the remoteSupportSettings
     */
    public RemoteSupportSettings getRemoteSupportSettings() {
        return remoteSupportSettings;
    }

    /**
     * @param remoteSupportSettings the remoteSupportSettings to set
     */
    public void setRemoteSupportSettings(RemoteSupportSettings remoteSupportSettings) {
        this.remoteSupportSettings = remoteSupportSettings;
    }

    /**
     * @return the remoteSupportUri
     */
    public String getRemoteSupportUri() {
        return remoteSupportUri;
    }

    /**
     * @param remoteSupportUri the remoteSupportUri to set
     */
    public void setRemoteSupportUri(String remoteSupportUri) {
        this.remoteSupportUri = remoteSupportUri;
    }

    /**
     * @return the romVersion
     */
    public String getRomVersion() {
        return romVersion;
    }

    /**
     * @param romVersion the romVersion to set
     */
    public void setRomVersion(String romVersion) {
        this.romVersion = romVersion;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the serverFirmwareInventoryUri
     */
    public String getServerFirmwareInventoryUri() {
        return serverFirmwareInventoryUri;
    }

    /**
     * @param serverFirmwareInventoryUri the serverFirmwareInventoryUri to set
     */
    public void setServerFirmwareInventoryUri(String serverFirmwareInventoryUri) {
        this.serverFirmwareInventoryUri = serverFirmwareInventoryUri;
    }

    /**
     * @return the serverGroupUri
     */
    public String getServerGroupUri() {
        return serverGroupUri;
    }

    /**
     * @param serverGroupUri the serverGroupUri to set
     */
    public void setServerGroupUri(String serverGroupUri) {
        this.serverGroupUri = serverGroupUri;
    }

    /**
     * @return the serverHardwareTypeUri
     */
    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }

    /**
     * @param serverHardwareTypeUri the serverHardwareTypeUri to set
     */
    public void setServerHardwareTypeUri(String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     * @return the serverProfileUri
     */
    public String getServerProfileUri() {
        return serverProfileUri;
    }

    /**
     * @param serverProfileUri the serverProfileUri to set
     */
    public void setServerProfileUri(String serverProfileUri) {
        this.serverProfileUri = serverProfileUri;
    }

    /**
     * @return the serverSettings
     */
    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    /**
     * @param serverSettings the serverSettings to set
     */
    public void setServerSettings(ServerSettings serverSettings) {
        this.serverSettings = serverSettings;
    }

    /**
     * @return the shortModel
     */
    public String getShortModel() {
        return shortModel;
    }

    /**
     * @param shortModel the shortModel to set
     */
    public void setShortModel(String shortModel) {
        this.shortModel = shortModel;
    }

    /**
     * @return the signature
     */
    public Signature getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    /**
     * @return the stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * @param stateReason the stateReason to set
     */
    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * @return the supportDataCollectionState
     */
    public SupportDataCollectionState getSupportDataCollectionState() {
        return supportDataCollectionState;
    }

    /**
     * @param supportDataCollectionState the supportDataCollectionState to set
     */
    public void setSupportDataCollectionState(SupportDataCollectionState supportDataCollectionState) {
        this.supportDataCollectionState = supportDataCollectionState;
    }

    /**
     * @return the supportDataCollectionType
     */
    public SupportDataCollectionType getSupportDataCollectionType() {
        return supportDataCollectionType;
    }

    /**
     * @param supportDataCollectionType the supportDataCollectionType to set
     */
    public void setSupportDataCollectionType(SupportDataCollectionType supportDataCollectionType) {
        this.supportDataCollectionType = supportDataCollectionType;
    }

    /**
     * @return the supportDataCollectionsUri
     */
    public String getSupportDataCollectionsUri() {
        return supportDataCollectionsUri;
    }

    /**
     * @param supportDataCollectionsUri the supportDataCollectionsUri to set
     */
    public void setSupportDataCollectionsUri(String supportDataCollectionsUri) {
        this.supportDataCollectionsUri = supportDataCollectionsUri;
    }

    /**
     * @return the supportState
     */
    public SupportState getSupportState() {
        return supportState;
    }

    /**
     * @param supportState the supportState to set
     */
    public void setSupportState(SupportState supportState) {
        this.supportState = supportState;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the uidState
     */
    public PhysicalServerUidState getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(PhysicalServerUidState uidState) {
        this.uidState = uidState;
    }

    /**
     * @return the virtualSerialNumber
     */
    public String getVirtualSerialNumber() {
        return virtualSerialNumber;
    }

    /**
     * @param virtualSerialNumber the virtualSerialNumber to set
     */
    public void setVirtualSerialNumber(String virtualSerialNumber) {
        this.virtualSerialNumber = virtualSerialNumber;
    }

    /**
     * @return the virtualUuid
     */
    public String getVirtualUuid() {
        return virtualUuid;
    }

    /**
     * @param virtualUuid the virtualUuid to set
     */
    public void setVirtualUuid(String virtualUuid) {
        this.virtualUuid = virtualUuid;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof ServerHardware);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof ServerHardware) {
            ServerHardware that = (ServerHardware) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(assetTag, that.assetTag)
                    .append(formFactor, that.formFactor)
                    .append(intelligentProvisioningVersion, that.intelligentProvisioningVersion)
                    .append(licensingIntent, that.licensingIntent)
                    .append(locationUri, that.locationUri)
                    .append(memoryMb, that.memoryMb)
                    .append(migrationState, that.migrationState)
                    .append(model, that.model)
                    .append(mpDnsName, that.mpDnsName)
                    .append(mpFirmwareVersion, that.mpFirmwareVersion)
                    .append(mpHostInfo, that.mpHostInfo)
                    .append(mpIpAddress, that.mpIpAddress)
                    .append(mpModel, that.mpModel)
                    .append(mpState, that.mpState)
                    .append(partNumber, that.partNumber)
                    .append(portMap, that.portMap)
                    .append(position, that.position)
                    .append(powerLock, that.powerLock)
                    .append(powerState, that.powerState)
                    .append(processorCoreCount, that.processorCoreCount)
                    .append(processorCount, that.processorCount)
                    .append(processorSpeedMhz, that.processorSpeedMhz)
                    .append(processorType, that.processorType)
                    .append(profileNetworkSettingsState, that.profileNetworkSettingsState)
                    .append(refreshState, that.refreshState)
                    .append(remoteSupportSettings, that.remoteSupportSettings)
                    .append(remoteSupportUri, that.remoteSupportUri)
                    .append(romVersion, that.romVersion)
                    .append(scopeUris, that.scopeUris)
                    .append(serialNumber, that.serialNumber)
                    .append(serverFirmwareInventoryUri, that.serverFirmwareInventoryUri)
                    .append(serverGroupUri, that.serverGroupUri)
                    .append(serverHardwareTypeUri, that.serverHardwareTypeUri)
                    .append(serverProfileUri, that.serverProfileUri)
                    .append(serverSettings, that.serverSettings)
                    .append(shortModel, that.shortModel)
                    .append(signature, that.signature)
                    .append(stateReason, that.stateReason)
                    .append(supportDataCollectionState, that.supportDataCollectionState)
                    .append(supportDataCollectionType, that.supportDataCollectionType)
                    .append(supportDataCollectionsUri, that.supportDataCollectionsUri)
                    .append(supportState, that.supportState)
                    .append(uuid, that.uuid)
                    .append(uidState, that.uidState)
                    .append(virtualSerialNumber, that.virtualSerialNumber)
                    .append(virtualUuid, that.virtualUuid)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(assetTag)
                .append(formFactor)
                .append(intelligentProvisioningVersion)
                .append(licensingIntent)
                .append(locationUri)
                .append(memoryMb)
                .append(migrationState)
                .append(model)
                .append(mpDnsName)
                .append(mpFirmwareVersion)
                .append(mpHostInfo)
                .append(mpIpAddress)
                .append(mpModel)
                .append(mpState)
                .append(partNumber)
                .append(portMap)
                .append(position)
                .append(powerLock)
                .append(powerState)
                .append(processorCoreCount)
                .append(processorCount)
                .append(processorSpeedMhz)
                .append(processorType)
                .append(profileNetworkSettingsState)
                .append(refreshState)
                .append(remoteSupportSettings)
                .append(remoteSupportUri)
                .append(romVersion)
                .append(scopeUris)
                .append(serialNumber)
                .append(serverFirmwareInventoryUri)
                .append(serverGroupUri)
                .append(serverHardwareTypeUri)
                .append(serverProfileUri)
                .append(serverSettings)
                .append(shortModel)
                .append(signature)
                .append(stateReason)
                .append(supportDataCollectionState)
                .append(supportDataCollectionType)
                .append(supportDataCollectionsUri)
                .append(supportState)
                .append(uuid)
                .append(uidState)
                .append(virtualSerialNumber)
                .append(virtualUuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
