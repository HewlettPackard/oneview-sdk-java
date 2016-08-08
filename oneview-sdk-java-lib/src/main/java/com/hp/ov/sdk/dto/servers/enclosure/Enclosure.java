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
package com.hp.ov.sdk.dto.servers.enclosure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.ReconfigurationState;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.generated.InterconnectBay;
import com.hp.ov.sdk.dto.generated.Oa;
import com.hp.ov.sdk.dto.servers.LicensingIntent;
import com.hp.ov.sdk.dto.servers.RemoteSupportSettings;

/**
 * The Enclosures data transfer object (DTO) contains the information used to
 * represent a enclosure in the system. It is passed in to the add/update
 * enclosure REST api, as well as the add/update enclosure through java client
 * api.
 */

public class Enclosure extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String activeOaPreferredIP;
    @Since(300)
    private Integer applianceBayCount;
    @Since(300)
    private List<Composer> applianceBays = new ArrayList<Composer>();
    private String assetTag;
    private Boolean consistentWithGroup;
    private Integer deviceBayCount;
    @Since(300)
    private Integer deviceBayWatts;
    private List<Object> deviceBays = new ArrayList<Object>();
    @Since(300)
    private Integer emBays;
    private String enclosureGroupUri;
    @Since(200)
    private String enclosureModel;
    private String enclosureType;
    @Since(200)
    private String enclosureTypeUri;
    private Integer fanBayCount;
    private List<FanBay> fanBays = new ArrayList<FanBay>();
    @Since(300)
    private Integer fansAndManagementDevicesWatts;
    @Since(200)
    private Boolean forceInstallFirmware;
    @Since(300)
    private String frameLinkModuleDomain;
    private String fwBaselineName;
    private String fwBaselineUri;
    private Integer interconnectBayCount;
    @Since(300)
    private Integer interconnectBayWatts;
    private List<InterconnectBay> interconnectBays = new ArrayList<InterconnectBay>();
    private Boolean isFwManaged;
    private LicensingIntent licensingIntent;
    @Since(200)
    private String logicalEnclosureUri;
    @Since(200)
    private List<EnclosureManager> managerBays = new ArrayList<EnclosureManager>();
    @Since(300)
    private Integer minimumPowerSupplies;
    @Since(300)
    private Integer minimumPowerSuppliesForRedundantPowerFeed;
    private List<Oa> oa = new ArrayList<Oa>();
    private Integer oaBayCount;
    @Since(200)
    private Integer oaBays;
    private String partNumber;
    @Since(300)
    private Integer powerAllocatedWatts;
    @Since(300)
    private Integer powerAvailableWatts;
    @Since(300)
    private Integer powerCapacityBoostWatts;
    @Since(300)
    private Integer powerCapacityWatts;
    @Since(300)
    private String powerMode;
    private Integer powerSupplyBayCount;
    private List<PowerSupplyBay> powerSupplyBays = new ArrayList<PowerSupplyBay>();
    private String rackName;
    private ReconfigurationState reconfigurationState;
    private RefreshState refreshState;
    @Since(300)
    private RemoteSupportSettings remoteSupportSettings;
    @Since(300)
    private String remoteSupportUri;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private String serialNumber;
    private String standbyOaPreferredIP;
    private String stateReason;
    @Since(300)
    private String supportDataCollectionState;
    @Since(300)
    private String supportDataCollectionType;
    @Since(300)
    private String supportDataCollectionsUri;
    @Since(300)
    private String supportState;
    private String uuid;
    @Since(200)
    private UidState uidState;
    private String vcmDomainId;
    private String vcmDomainName;
    private Boolean vcmMode;
    private String vcmUrl;

    /**
     * @return the activeOaPreferredIP
     */
    public String getActiveOaPreferredIP() {
        return activeOaPreferredIP;
    }

    /**
     * @param activeOaPreferredIP the activeOaPreferredIP to set
     */
    public void setActiveOaPreferredIP(String activeOaPreferredIP) {
        this.activeOaPreferredIP = activeOaPreferredIP;
    }

    /**
     * @return the applianceBayCount
     */
    public Integer getApplianceBayCount() {
        return applianceBayCount;
    }

    /**
     * @param applianceBayCount the applianceBayCount to set
     */
    public void setApplianceBayCount(Integer applianceBayCount) {
        this.applianceBayCount = applianceBayCount;
    }

    /**
     * @return the applianceBays
     */
    public List<Composer> getApplianceBays() {
        return applianceBays;
    }

    /**
     * @param applianceBays the applianceBays to set
     */
    public void setApplianceBays(List<Composer> applianceBays) {
        this.applianceBays = applianceBays;
    }

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
     * @return the consistentWithGroup
     */
    public Boolean getConsistentWithGroup() {
        return consistentWithGroup;
    }

    /**
     * @param consistentWithGroup the consistentWithGroup to set
     */
    public void setConsistentWithGroup(Boolean consistentWithGroup) {
        this.consistentWithGroup = consistentWithGroup;
    }

    /**
     * @return the deviceBayCount
     */
    public Integer getDeviceBayCount() {
        return deviceBayCount;
    }

    /**
     * @param deviceBayCount the deviceBayCount to set
     */
    public void setDeviceBayCount(Integer deviceBayCount) {
        this.deviceBayCount = deviceBayCount;
    }

    /**
     * @return the deviceBayWatts
     */
    public Integer getDeviceBayWatts() {
        return deviceBayWatts;
    }

    /**
     * @param deviceBayWatts the deviceBayWatts to set
     */
    public void setDeviceBayWatts(Integer deviceBayWatts) {
        this.deviceBayWatts = deviceBayWatts;
    }

    /**
     * @return the deviceBays
     */
    public List<Object> getDeviceBays() {
        return deviceBays;
    }

    /**
     * @param deviceBays the deviceBays to set
     */
    public void setDeviceBays(List<Object> deviceBays) {
        this.deviceBays = deviceBays;
    }

    /**
     * @return the emBays
     */
    public Integer getEmBays() {
        return emBays;
    }

    /**
     * @param emBays the emBays to set
     */
    public void setEmBays(Integer emBays) {
        this.emBays = emBays;
    }

    /**
     * @return the enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * @param enclosureGroupUri the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return the enclosureModel
     */
    public String getEnclosureModel() {
        return enclosureModel;
    }

    /**
     * @param enclosureModel the enclosureModel to set
     */
    public void setEnclosureModel(String enclosureModel) {
        this.enclosureModel = enclosureModel;
    }

    /**
     * @return the enclosureType
     */
    public String getEnclosureType() {
        return enclosureType;
    }

    /**
     * @param enclosureType the enclosureType to set
     */
    public void setEnclosureType(String enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * @return the enclosureTypeUri
     */
    public String getEnclosureTypeUri() {
        return enclosureTypeUri;
    }

    /**
     * @param enclosureTypeUri the enclosureTypeUri to set
     */
    public void setEnclosureTypeUri(String enclosureTypeUri) {
        this.enclosureTypeUri = enclosureTypeUri;
    }

    /**
     * @return the fanBayCount
     */
    public Integer getFanBayCount() {
        return fanBayCount;
    }

    /**
     * @param fanBayCount the fanBayCount to set
     */
    public void setFanBayCount(Integer fanBayCount) {
        this.fanBayCount = fanBayCount;
    }

    /**
     * @return the fanBays
     */
    public List<FanBay> getFanBays() {
        return fanBays;
    }

    /**
     * @param fanBays the fanBays to set
     */
    public void setFanBays(List<FanBay> fanBays) {
        this.fanBays = fanBays;
    }

    /**
     * @return the fansAndManagementDevicesWatts
     */
    public Integer getFansAndManagementDevicesWatts() {
        return fansAndManagementDevicesWatts;
    }

    /**
     * @param fansAndManagementDevicesWatts the fansAndManagementDevicesWatts to set
     */
    public void setFansAndManagementDevicesWatts(Integer fansAndManagementDevicesWatts) {
        this.fansAndManagementDevicesWatts = fansAndManagementDevicesWatts;
    }

    /**
     * @return the forceInstallFirmware
     */
    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    /**
     * @param forceInstallFirmware the forceInstallFirmware to set
     */
    public void setForceInstallFirmware(Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    /**
     * @return the frameLinkModuleDomain
     */
    public String getFrameLinkModuleDomain() {
        return frameLinkModuleDomain;
    }

    /**
     * @param frameLinkModuleDomain the frameLinkModuleDomain to set
     */
    public void setFrameLinkModuleDomain(String frameLinkModuleDomain) {
        this.frameLinkModuleDomain = frameLinkModuleDomain;
    }

    /**
     * @return the fwBaselineName
     */
    public String getFwBaselineName() {
        return fwBaselineName;
    }

    /**
     * @param fwBaselineName the fwBaselineName to set
     */
    public void setFwBaselineName(String fwBaselineName) {
        this.fwBaselineName = fwBaselineName;
    }

    /**
     * @return the fwBaselineUri
     */
    public String getFwBaselineUri() {
        return fwBaselineUri;
    }

    /**
     * @param fwBaselineUri the fwBaselineUri to set
     */
    public void setFwBaselineUri(String fwBaselineUri) {
        this.fwBaselineUri = fwBaselineUri;
    }

    /**
     * @return the interconnectBayCount
     */
    public Integer getInterconnectBayCount() {
        return interconnectBayCount;
    }

    /**
     * @param interconnectBayCount the interconnectBayCount to set
     */
    public void setInterconnectBayCount(Integer interconnectBayCount) {
        this.interconnectBayCount = interconnectBayCount;
    }

    /**
     * @return the interconnectBayWatts
     */
    public Integer getInterconnectBayWatts() {
        return interconnectBayWatts;
    }

    /**
     * @param interconnectBayWatts the interconnectBayWatts to set
     */
    public void setInterconnectBayWatts(Integer interconnectBayWatts) {
        this.interconnectBayWatts = interconnectBayWatts;
    }

    /**
     * @return the interconnectBays
     */
    public List<InterconnectBay> getInterconnectBays() {
        return interconnectBays;
    }

    /**
     * @param interconnectBays the interconnectBays to set
     */
    public void setInterconnectBays(List<InterconnectBay> interconnectBays) {
        this.interconnectBays = interconnectBays;
    }

    /**
     * @return the isFwManaged
     */
    public Boolean getIsFwManaged() {
        return isFwManaged;
    }

    /**
     * @param isFwManaged the isFwManaged to set
     */
    public void setIsFwManaged(Boolean isFwManaged) {
        this.isFwManaged = isFwManaged;
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
     * @return the logicalEnclosureUri
     */
    public String getLogicalEnclosureUri() {
        return logicalEnclosureUri;
    }

    /**
     * @param logicalEnclosureUri the logicalEnclosureUri to set
     */
    public void setLogicalEnclosureUri(String logicalEnclosureUri) {
        this.logicalEnclosureUri = logicalEnclosureUri;
    }

    /**
     * @return the managerBays
     */
    public List<EnclosureManager> getManagerBays() {
        return managerBays;
    }

    /**
     * @param managerBays the managerBays to set
     */
    public void setManagerBays(List<EnclosureManager> managerBays) {
        this.managerBays = managerBays;
    }

    /**
     * @return the minimumPowerSupplies
     */
    public Integer getMinimumPowerSupplies() {
        return minimumPowerSupplies;
    }

    /**
     * @param minimumPowerSupplies the minimumPowerSupplies to set
     */
    public void setMinimumPowerSupplies(Integer minimumPowerSupplies) {
        this.minimumPowerSupplies = minimumPowerSupplies;
    }

    /**
     * @return the minimumPowerSuppliesForRedundantPowerFeed
     */
    public Integer getMinimumPowerSuppliesForRedundantPowerFeed() {
        return minimumPowerSuppliesForRedundantPowerFeed;
    }

    /**
     * @param minimumPowerSuppliesForRedundantPowerFeed the minimumPowerSuppliesForRedundantPowerFeed to set
     */
    public void setMinimumPowerSuppliesForRedundantPowerFeed(Integer minimumPowerSuppliesForRedundantPowerFeed) {
        this.minimumPowerSuppliesForRedundantPowerFeed = minimumPowerSuppliesForRedundantPowerFeed;
    }

    /**
     * @return the oa
     */
    public List<Oa> getOa() {
        return oa;
    }

    /**
     * @param oa the oa to set
     */
    public void setOa(List<Oa> oa) {
        this.oa = oa;
    }

    /**
     * @return the oaBayCount
     */
    public Integer getOaBayCount() {
        return oaBayCount;
    }

    /**
     * @param oaBayCount the oaBayCount to set
     */
    public void setOaBayCount(Integer oaBayCount) {
        this.oaBayCount = oaBayCount;
    }

    /**
     * @return the oaBays
     */
    public Integer getOaBays() {
        return oaBays;
    }

    /**
     * @param oaBays the oaBays to set
     */
    public void setOaBays(Integer oaBays) {
        this.oaBays = oaBays;
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
     * @return the powerAllocatedWatts
     */
    public Integer getPowerAllocatedWatts() {
        return powerAllocatedWatts;
    }

    /**
     * @param powerAllocatedWatts the powerAllocatedWatts to set
     */
    public void setPowerAllocatedWatts(Integer powerAllocatedWatts) {
        this.powerAllocatedWatts = powerAllocatedWatts;
    }

    /**
     * @return the powerAvailableWatts
     */
    public Integer getPowerAvailableWatts() {
        return powerAvailableWatts;
    }

    /**
     * @param powerAvailableWatts the powerAvailableWatts to set
     */
    public void setPowerAvailableWatts(Integer powerAvailableWatts) {
        this.powerAvailableWatts = powerAvailableWatts;
    }

    /**
     * @return the powerCapacityBoostWatts
     */
    public Integer getPowerCapacityBoostWatts() {
        return powerCapacityBoostWatts;
    }

    /**
     * @param powerCapacityBoostWatts the powerCapacityBoostWatts to set
     */
    public void setPowerCapacityBoostWatts(Integer powerCapacityBoostWatts) {
        this.powerCapacityBoostWatts = powerCapacityBoostWatts;
    }

    /**
     * @return the powerCapacityWatts
     */
    public Integer getPowerCapacityWatts() {
        return powerCapacityWatts;
    }

    /**
     * @param powerCapacityWatts the powerCapacityWatts to set
     */
    public void setPowerCapacityWatts(Integer powerCapacityWatts) {
        this.powerCapacityWatts = powerCapacityWatts;
    }

    /**
     * @return the powerMode
     */
    public String getPowerMode() {
        return powerMode;
    }

    /**
     * @param powerMode the powerMode to set
     */
    public void setPowerMode(String powerMode) {
        this.powerMode = powerMode;
    }

    /**
     * @return the powerSupplyBayCount
     */
    public Integer getPowerSupplyBayCount() {
        return powerSupplyBayCount;
    }

    /**
     * @param powerSupplyBayCount the powerSupplyBayCount to set
     */
    public void setPowerSupplyBayCount(Integer powerSupplyBayCount) {
        this.powerSupplyBayCount = powerSupplyBayCount;
    }

    /**
     * @return the powerSupplyBays
     */
    public List<PowerSupplyBay> getPowerSupplyBays() {
        return powerSupplyBays;
    }

    /**
     * @param powerSupplyBays the powerSupplyBays to set
     */
    public void setPowerSupplyBays(List<PowerSupplyBay> powerSupplyBays) {
        this.powerSupplyBays = powerSupplyBays;
    }

    /**
     * @return the rackName
     */
    public String getRackName() {
        return rackName;
    }

    /**
     * @param rackName the rackName to set
     */
    public void setRackName(String rackName) {
        this.rackName = rackName;
    }

    /**
     * @return the reconfigurationState
     */
    public ReconfigurationState getReconfigurationState() {
        return reconfigurationState;
    }

    /**
     * @param reconfigurationState the reconfigurationState to set
     */
    public void setReconfigurationState(ReconfigurationState reconfigurationState) {
        this.reconfigurationState = reconfigurationState;
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
     * @return the standbyOaPreferredIP
     */
    public String getStandbyOaPreferredIP() {
        return standbyOaPreferredIP;
    }

    /**
     * @param standbyOaPreferredIP the standbyOaPreferredIP to set
     */
    public void setStandbyOaPreferredIP(String standbyOaPreferredIP) {
        this.standbyOaPreferredIP = standbyOaPreferredIP;
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
    public String getSupportDataCollectionState() {
        return supportDataCollectionState;
    }

    /**
     * @param supportDataCollectionState the supportDataCollectionState to set
     */
    public void setSupportDataCollectionState(String supportDataCollectionState) {
        this.supportDataCollectionState = supportDataCollectionState;
    }

    /**
     * @return the supportDataCollectionType
     */
    public String getSupportDataCollectionType() {
        return supportDataCollectionType;
    }

    /**
     * @param supportDataCollectionType the supportDataCollectionType to set
     */
    public void setSupportDataCollectionType(String supportDataCollectionType) {
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
    public String getSupportState() {
        return supportState;
    }

    /**
     * @param supportState the supportState to set
     */
    public void setSupportState(String supportState) {
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
    public UidState getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(UidState uidState) {
        this.uidState = uidState;
    }

    /**
     * @return the vcmDomainId
     */
    public String getVcmDomainId() {
        return vcmDomainId;
    }

    /**
     * @param vcmDomainId the vcmDomainId to set
     */
    public void setVcmDomainId(String vcmDomainId) {
        this.vcmDomainId = vcmDomainId;
    }

    /**
     * @return the vcmDomainName
     */
    public String getVcmDomainName() {
        return vcmDomainName;
    }

    /**
     * @param vcmDomainName the vcmDomainName to set
     */
    public void setVcmDomainName(String vcmDomainName) {
        this.vcmDomainName = vcmDomainName;
    }

    /**
     * @return the vcmMode
     */
    public Boolean getVcmMode() {
        return vcmMode;
    }

    /**
     * @param vcmMode the vcmMode to set
     */
    public void setVcmMode(Boolean vcmMode) {
        this.vcmMode = vcmMode;
    }

    /**
     * @return the vcmUrl
     */
    public String getVcmUrl() {
        return vcmUrl;
    }

    /**
     * @param vcmUrl the vcmUrl to set
     */
    public void setVcmUrl(String vcmUrl) {
        this.vcmUrl = vcmUrl;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Enclosure);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj  instanceof Enclosure) {
            Enclosure that = (Enclosure) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(activeOaPreferredIP, that.activeOaPreferredIP)
                    .append(applianceBayCount, that.applianceBayCount)
                    .append(applianceBays, that.applianceBays)
                    .append(assetTag, that.assetTag)
                    .append(consistentWithGroup, that.consistentWithGroup)
                    .append(deviceBayCount, that.deviceBayCount)
                    .append(deviceBayWatts, that.deviceBayWatts)
                    .append(deviceBays, that.deviceBays)
                    .append(emBays, that.emBays)
                    .append(enclosureGroupUri, that.enclosureGroupUri)
                    .append(enclosureModel, that.enclosureModel)
                    .append(enclosureType, that.enclosureType)
                    .append(enclosureTypeUri, that.enclosureTypeUri)
                    .append(fanBayCount, that.fanBayCount)
                    .append(fanBays, that.fanBays)
                    .append(fansAndManagementDevicesWatts, that.fansAndManagementDevicesWatts)
                    .append(forceInstallFirmware, that.forceInstallFirmware)
                    .append(frameLinkModuleDomain, that.frameLinkModuleDomain)
                    .append(fwBaselineName, that.fwBaselineName)
                    .append(fwBaselineUri, that.fwBaselineUri)
                    .append(interconnectBayCount, that.interconnectBayCount)
                    .append(interconnectBayWatts, that.interconnectBayWatts)
                    .append(interconnectBays, that.interconnectBays)
                    .append(isFwManaged, that.isFwManaged)
                    .append(licensingIntent, that.licensingIntent)
                    .append(logicalEnclosureUri, that.logicalEnclosureUri)
                    .append(managerBays, that.managerBays)
                    .append(minimumPowerSupplies, that.minimumPowerSupplies)
                    .append(minimumPowerSuppliesForRedundantPowerFeed, that.minimumPowerSuppliesForRedundantPowerFeed)
                    .append(oa, that.oa)
                    .append(oaBayCount, that.oaBayCount)
                    .append(oaBays, that.oaBays)
                    .append(partNumber, that.partNumber)
                    .append(powerAllocatedWatts, that.powerAllocatedWatts)
                    .append(powerAvailableWatts, that.powerAvailableWatts)
                    .append(powerCapacityBoostWatts, that.powerCapacityBoostWatts)
                    .append(powerCapacityWatts, that.powerCapacityWatts)
                    .append(powerMode, that.powerMode)
                    .append(powerSupplyBayCount, that.powerSupplyBayCount)
                    .append(powerSupplyBays, that.powerSupplyBays)
                    .append(rackName, that.rackName)
                    .append(reconfigurationState, that.reconfigurationState)
                    .append(refreshState, that.refreshState)
                    .append(remoteSupportSettings, that.remoteSupportSettings)
                    .append(remoteSupportUri, that.remoteSupportUri)
                    .append(scopeUris, that.scopeUris)
                    .append(serialNumber, that.serialNumber)
                    .append(standbyOaPreferredIP, that.standbyOaPreferredIP)
                    .append(stateReason, that.stateReason)
                    .append(supportDataCollectionState, that.supportDataCollectionState)
                    .append(supportDataCollectionType, that.supportDataCollectionType)
                    .append(supportDataCollectionsUri, that.supportDataCollectionsUri)
                    .append(supportState, that.supportState)
                    .append(uuid, that.uuid)
                    .append(uidState, that.uidState)
                    .append(vcmDomainId, that.vcmDomainId)
                    .append(vcmDomainName, that.vcmDomainName)
                    .append(vcmMode, that.vcmMode)
                    .append(vcmUrl, that.vcmUrl)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(activeOaPreferredIP)
                .append(applianceBayCount)
                .append(applianceBays)
                .append(assetTag)
                .append(consistentWithGroup)
                .append(deviceBayCount)
                .append(deviceBayWatts)
                .append(deviceBays)
                .append(emBays)
                .append(enclosureGroupUri)
                .append(enclosureModel)
                .append(enclosureType)
                .append(enclosureTypeUri)
                .append(fanBayCount)
                .append(fanBays)
                .append(fansAndManagementDevicesWatts)
                .append(forceInstallFirmware)
                .append(frameLinkModuleDomain)
                .append(fwBaselineName)
                .append(fwBaselineUri)
                .append(interconnectBayCount)
                .append(interconnectBayWatts)
                .append(interconnectBays)
                .append(isFwManaged)
                .append(licensingIntent)
                .append(logicalEnclosureUri)
                .append(managerBays)
                .append(minimumPowerSupplies)
                .append(minimumPowerSuppliesForRedundantPowerFeed)
                .append(oa)
                .append(oaBayCount)
                .append(oaBays)
                .append(partNumber)
                .append(powerAllocatedWatts)
                .append(powerAvailableWatts)
                .append(powerCapacityBoostWatts)
                .append(powerCapacityWatts)
                .append(powerMode)
                .append(powerSupplyBayCount)
                .append(powerSupplyBays)
                .append(rackName)
                .append(reconfigurationState)
                .append(refreshState)
                .append(remoteSupportSettings)
                .append(remoteSupportUri)
                .append(scopeUris)
                .append(serialNumber)
                .append(standbyOaPreferredIP)
                .append(stateReason)
                .append(supportDataCollectionState)
                .append(supportDataCollectionType)
                .append(supportDataCollectionsUri)
                .append(supportState)
                .append(uuid)
                .append(uidState)
                .append(vcmDomainId)
                .append(vcmDomainName)
                .append(vcmMode)
                .append(vcmUrl)
                .toHashCode();
    }

}
