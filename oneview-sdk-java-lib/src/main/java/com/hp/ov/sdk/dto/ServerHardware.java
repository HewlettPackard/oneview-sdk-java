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
package com.hp.ov.sdk.dto;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

/**
 * The ServerHardware data transfer object (DTO) contains the information used
 * to represent a server hardware in the system. It is passed in to the
 * add/update server hardware REST api, as well as the add/update server
 * hardware through java client api.
 */
public class ServerHardware extends BaseModelResource {

    private static final long serialVersionUID = -9046023900390473327L;

    /**
     * The current value of the asset tag for this server hardware. This value
     * can be set in the server hardware's BIOS interface.
     */
    private String assetTag;

    /**
     * The physical dimensions of this server. For a blade server this is either
     * HalfHeight or FullHeight. For a rack server this is expressed in U
     * height, e.g. 4U.
     */
    private String formFactor;

    @Since(200)
    private String intelligentProvisioningVersion;

    /**
     * Product license assigned to the server hardware.
     */
    private LicensingIntent licensingIntent;

    /**
     * For blade servers, the enclosure in which this blade server resides. This
     * URI can be used to retrieve information about the enclosure. This value
     * is not set for rack mount servers.
     */
    private String locationUri;

    /**
     * Amount of memory installed on this server hardware (in megabytes).
     */
    private Integer memoryMb;

    /**
     * The full server hardware model string.
     */
    private String model;

    /**
     * The DNS name of the iLO/Management Processor that resides on this server
     * hardware.
     */
    @Until(199)
    private String mpDnsName;

    /**
     * The version of the firmware installed on the iLO.
     */
    private String mpFirmwareVersion;

    /**
     * IP Address of the management processor (iLO) resident on this server
     * hardware.
     */
    @Until(199)
    private String mpIpAddress;

    @Since(200)
    private ManagementHostInformation mpHostInfo;

    /**
     * The model type of the iLO, such as iLO4.
     */
    private String mpModel;

    @Since(200)
    private PhysicalServerMpState mpState;

    /**
     * The part number for this server hardware.
     */
    private String partNumber;

    /**
     * A list of adapters/slots, their ports and attributes. This information is
     * available for blade servers but not rack servers.
     */
    private PortMap portMap;

    /**
     * For blade servers, the number of the physical enclosure bay in which the
     * server hardware resides. For rack mount servers, this value is null.
     */
    private Integer position;

    /**
     * Indicates if an operation is being performed on this server hardware
     * (such as a profile assignment) that prevents its power state from being
     * manipulated via the server hardware API.
     */
    private Boolean powerLock = false;

    /**
     * Current power state of the server hardware. Values are Unknown, On, Off,
     * PoweringOn, PoweringOff or Resetting. (Required)
     */
    private PhysicalServerPowerState powerState;

    /**
     * Number of cores available per processor.
     */
    private Integer processorCoreCount;

    /**
     * Number of processors installed on this server hardware.
     */
    private Integer processorCount;

    /**
     * Speed of the CPUs in megahertz.
     */
    private Integer processorSpeedMhz;

    /**
     * Type of CPU installed on this server hardware.
     */
    private String processorType;

    private String profileNetworkSettingsState;

    /**
     * Indicates if the resource is currently refreshing.
     */
    private RefreshState refreshState;

    /**
     * The version of the server hardware firmware (ROM). After updating the ROM
     * (BIOS) firmware for a server, the server hardware page and the REST API
     * may report an inaccurate ROM version until the server is next powered on
     * and allowed to complete the power-on self-test (POST).
     */
    private String romVersion;

    /**
     * Serial number of the server hardware.
     */
    private String serialNumber;

    /**
     * For blade servers, this is the URI of the containing enclosure's
     * enclosure group. This URI can be used to retrieve information about the
     * enclosure group or to identify all the servers in the same group. This
     * value is not set for rack mount servers.
     */
    private String serverGroupUri;

    /**
     * URI of the server hardware type associated with the server hardware. This
     * URI can be used to retrieve information about the server hardware type.
     */
    private String serverHardwareTypeUri;

    /**
     * URI of a server profile assigned to this server hardware, if one is
     * assigned. If not assigned this value is null.
     */
    private String serverProfileUri;

    @Since(200)
    private ServerSettings serverSettings;

    /**
     * Short version of the server hardware model string, typically something
     * like BL460 Gen8.
     */
    private String shortModel;

    /**
     * Data representing the current configuration or 'signature' of the server.
     */
    private Signature signature;

    /**
     * The reason for the current resource state of the server hardware. This
     * only applies if the state is 'Unmanaged', otherwise it is set to
     * 'NotApplicable'. Allowable values are: Unsupported (server model or
     * version not currently supported by the appliance), UpdatingFirmware
     * (server firmware update in progress), NotApplicable (when
     * PhysicalServerState is anything besides 'Unmanaged'), NotOwner (no claim
     * on server), Inventory (server added by PDU), Unconfigured (discovery data
     * incomplete or iLO configuration failure), UnsupportedFirmware (iLO
     * firmware version below minimum support level), Interrupted (when
     * PhysicalServerState is a result of an operation that was terminated
     * before completing), and CommunicationError (appliance cannot communicate
     * with iLO or OA).
     */
    private String stateReason;

    /**
     * Universally Unique ID (UUID) of the server hardware. (Required)
     */
    private String uuid;

    /**
     * Virtual serial number associated with this server hardware (if specified
     * in the profile assigned to this server).
     */
    private String virtualSerialNumber;

    /**
     * Virtual UUID associated with this server hardware (if specified in the
     * profile assigned to this server).
     */
    private String virtualUuid;

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getIntelligentProvisioningVersion() {
        return intelligentProvisioningVersion;
    }

    public void setIntelligentProvisioningVersion(String intelligentProvisioningVersion) {
        this.intelligentProvisioningVersion = intelligentProvisioningVersion;
    }

    public LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    public void setLicensingIntent(LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    public String getLocationUri() {
        return locationUri;
    }

    public void setLocationUri(String locationUri) {
        this.locationUri = locationUri;
    }

    public Integer getMemoryMb() {
        return memoryMb;
    }

    public void setMemoryMb(Integer memoryMb) {
        this.memoryMb = memoryMb;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMpDnsName() {
        return mpDnsName;
    }

    public void setMpDnsName(String mpDnsName) {
        this.mpDnsName = mpDnsName;
    }

    public String getMpFirmwareVersion() {
        return mpFirmwareVersion;
    }

    public void setMpFirmwareVersion(String mpFirmwareVersion) {
        this.mpFirmwareVersion = mpFirmwareVersion;
    }

    public String getMpIpAddress() {
        return mpIpAddress;
    }

    public void setMpIpAddress(String mpIpAddress) {
        this.mpIpAddress = mpIpAddress;
    }

    public ManagementHostInformation getMpHostInfo() {
        return mpHostInfo;
    }

    public void setMpHostInfo(ManagementHostInformation mpHostInfo) {
        this.mpHostInfo = mpHostInfo;
    }

    public String getMpModel() {
        return mpModel;
    }

    public void setMpModel(String mpModel) {
        this.mpModel = mpModel;
    }

    public PhysicalServerMpState getMpState() {
        return mpState;
    }

    public void setMpState(PhysicalServerMpState mpState) {
        this.mpState = mpState;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public PortMap getPortMap() {
        return portMap;
    }

    public void setPortMap(PortMap portMap) {
        this.portMap = portMap;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getPowerLock() {
        return powerLock;
    }

    public void setPowerLock(Boolean powerLock) {
        this.powerLock = powerLock;
    }

    public PhysicalServerPowerState getPowerState() {
        return powerState;
    }

    public void setPowerState(PhysicalServerPowerState powerState) {
        this.powerState = powerState;
    }

    public Integer getProcessorCoreCount() {
        return processorCoreCount;
    }

    public void setProcessorCoreCount(Integer processorCoreCount) {
        this.processorCoreCount = processorCoreCount;
    }

    public Integer getProcessorCount() {
        return processorCount;
    }

    public void setProcessorCount(Integer processorCount) {
        this.processorCount = processorCount;
    }

    public Integer getProcessorSpeedMhz() {
        return processorSpeedMhz;
    }

    public void setProcessorSpeedMhz(Integer processorSpeedMhz) {
        this.processorSpeedMhz = processorSpeedMhz;
    }

    public String getProcessorType() {
        return processorType;
    }

    public void setProcessorType(String processorType) {
        this.processorType = processorType;
    }

    public String getProfileNetworkSettingsState() {
        return profileNetworkSettingsState;
    }

    public void setProfileNetworkSettingsState(String profileNetworkSettingsState) {
        this.profileNetworkSettingsState = profileNetworkSettingsState;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getRomVersion() {
        return romVersion;
    }

    public void setRomVersion(String romVersion) {
        this.romVersion = romVersion;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getServerGroupUri() {
        return serverGroupUri;
    }

    public void setServerGroupUri(String serverGroupUri) {
        this.serverGroupUri = serverGroupUri;
    }

    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }

    public void setServerHardwareTypeUri(String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    public String getServerProfileUri() {
        return serverProfileUri;
    }

    public void setServerProfileUri(String serverProfileUri) {
        this.serverProfileUri = serverProfileUri;
    }

    public ServerSettings getServerSettings() {
        return serverSettings;
    }

    public void setServerSettings(ServerSettings serverSettings) {
        this.serverSettings = serverSettings;
    }

    public String getShortModel() {
        return shortModel;
    }

    public void setShortModel(String shortModel) {
        this.shortModel = shortModel;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVirtualSerialNumber() {
        return virtualSerialNumber;
    }

    public void setVirtualSerialNumber(String virtualSerialNumber) {
        this.virtualSerialNumber = virtualSerialNumber;
    }

    public String getVirtualUuid() {
        return virtualUuid;
    }

    public void setVirtualUuid(String virtualUuid) {
        this.virtualUuid = virtualUuid;
    }

}
