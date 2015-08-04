/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class EnclosureV2 extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String activeOaPreferredIP;
    private String assetTag;
    private Boolean consistentWithGroup;
    private Integer deviceBayCount;
    private List<DeviceBay> deviceBays = new ArrayList<DeviceBay>();
    private String enclosureGroupUri;
    private String enclosureType;
    private Integer fanBayCount;
    private List<Fan> fanBays = new ArrayList<Fan>();
    private String fwBaselineName;
    private String fwBaselineUri;
    private Integer interconnectBayCount;
    private List<InterconnectBayV2> interconnectBays = new ArrayList<InterconnectBayV2>();
    private Boolean isFwManaged;
    private LicensingIntent licensingIntent;
    private List<Oa> oa = new ArrayList<Oa>();
    private Integer oaBayCount;
    private String partNumber;
    private Integer powerSupplyBayCount;
    private List<PowerSupply> powerSupplyBays = new ArrayList<PowerSupply>();
    private String rackName;
    private ReconfigurationState reconfigurationState;
    private RefreshState refreshState;
    private String serialNumber;
    private String standbyOaPreferredIP;
    private String stateReason;
    private String uuid;
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
     * @param activeOaPreferredIP
     *            the activeOaPreferredIP to set
     */
    public void setActiveOaPreferredIP(final String activeOaPreferredIP) {
        this.activeOaPreferredIP = activeOaPreferredIP;
    }

    /**
     * @return the assetTag
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * @param assetTag
     *            the assetTag to set
     */
    public void setAssetTag(final String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * @return the consistentWithGroup
     */
    public Boolean getConsistentWithGroup() {
        return consistentWithGroup;
    }

    /**
     * @param consistentWithGroup
     *            the consistentWithGroup to set
     */
    public void setConsistentWithGroup(final Boolean consistentWithGroup) {
        this.consistentWithGroup = consistentWithGroup;
    }

    /**
     * @return the deviceBayCount
     */
    public Integer getDeviceBayCount() {
        return deviceBayCount;
    }

    /**
     * @param deviceBayCount
     *            the deviceBayCount to set
     */
    public void setDeviceBayCount(final Integer deviceBayCount) {
        this.deviceBayCount = deviceBayCount;
    }

    /**
     * @return the deviceBays
     */
    public List<DeviceBay> getDeviceBays() {
        return deviceBays;
    }

    /**
     * @param deviceBays
     *            the deviceBays to set
     */
    public void setDeviceBays(final List<DeviceBay> deviceBays) {
        this.deviceBays = deviceBays;
    }

    /**
     * @return the enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * @param enclosureGroupUri
     *            the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return the enclosureType
     */
    public String getEnclosureType() {
        return enclosureType;
    }

    /**
     * @param enclosureType
     *            the enclosureType to set
     */
    public void setEnclosureType(final String enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * @return the fanBayCount
     */
    public Integer getFanBayCount() {
        return fanBayCount;
    }

    /**
     * @param fanBayCount
     *            the fanBayCount to set
     */
    public void setFanBayCount(final Integer fanBayCount) {
        this.fanBayCount = fanBayCount;
    }

    /**
     * @return the fanBays
     */
    public List<Fan> getFanBays() {
        return fanBays;
    }

    /**
     * @param fanBays
     *            the fanBays to set
     */
    public void setFanBays(final List<Fan> fanBays) {
        this.fanBays = fanBays;
    }

    /**
     * @return the fwBaselineName
     */
    public String getFwBaselineName() {
        return fwBaselineName;
    }

    /**
     * @param fwBaselineName
     *            the fwBaselineName to set
     */
    public void setFwBaselineName(final String fwBaselineName) {
        this.fwBaselineName = fwBaselineName;
    }

    /**
     * @return the fwBaselineUri
     */
    public String getFwBaselineUri() {
        return fwBaselineUri;
    }

    /**
     * @param fwBaselineUri
     *            the fwBaselineUri to set
     */
    public void setFwBaselineUri(final String fwBaselineUri) {
        this.fwBaselineUri = fwBaselineUri;
    }

    /**
     * @return the interconnectBayCount
     */
    public Integer getInterconnectBayCount() {
        return interconnectBayCount;
    }

    /**
     * @param interconnectBayCount
     *            the interconnectBayCount to set
     */
    public void setInterconnectBayCount(final Integer interconnectBayCount) {
        this.interconnectBayCount = interconnectBayCount;
    }

    /**
     * @return the interconnectBays
     */
    public List<InterconnectBayV2> getInterconnectBays() {
        return interconnectBays;
    }

    /**
     * @param interconnectBays
     *            the interconnectBays to set
     */
    public void setInterconnectBays(final List<InterconnectBayV2> interconnectBays) {
        this.interconnectBays = interconnectBays;
    }

    /**
     * @return the isFwManaged
     */
    public Boolean getIsFwManaged() {
        return isFwManaged;
    }

    /**
     * @param isFwManaged
     *            the isFwManaged to set
     */
    public void setIsFwManaged(final Boolean isFwManaged) {
        this.isFwManaged = isFwManaged;
    }

    /**
     * @return the licensingIntent
     */
    public LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     * @param licensingIntent
     *            the licensingIntent to set
     */
    public void setLicensingIntent(final LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    /**
     * @return the oa
     */
    public List<Oa> getOa() {
        return oa;
    }

    /**
     * @param oa
     *            the oa to set
     */
    public void setOa(final List<Oa> oa) {
        this.oa = oa;
    }

    /**
     * @return the oaBayCount
     */
    public Integer getOaBayCount() {
        return oaBayCount;
    }

    /**
     * @param oaBayCount
     *            the oaBayCount to set
     */
    public void setOaBayCount(final Integer oaBayCount) {
        this.oaBayCount = oaBayCount;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber
     *            the partNumber to set
     */
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the powerSupplyBayCount
     */
    public Integer getPowerSupplyBayCount() {
        return powerSupplyBayCount;
    }

    /**
     * @param powerSupplyBayCount
     *            the powerSupplyBayCount to set
     */
    public void setPowerSupplyBayCount(final Integer powerSupplyBayCount) {
        this.powerSupplyBayCount = powerSupplyBayCount;
    }

    /**
     * @return the powerSupplyBays
     */
    public List<PowerSupply> getPowerSupplyBays() {
        return powerSupplyBays;
    }

    /**
     * @param powerSupplyBays
     *            the powerSupplyBays to set
     */
    public void setPowerSupplyBays(final List<PowerSupply> powerSupplyBays) {
        this.powerSupplyBays = powerSupplyBays;
    }

    /**
     * @return the rackName
     */
    public String getRackName() {
        return rackName;
    }

    /**
     * @param rackName
     *            the rackName to set
     */
    public void setRackName(final String rackName) {
        this.rackName = rackName;
    }

    /**
     * @return the reconfigurationState
     */
    public ReconfigurationState getReconfigurationState() {
        return reconfigurationState;
    }

    /**
     * @param reconfigurationState
     *            the reconfigurationState to set
     */
    public void setReconfigurationState(final ReconfigurationState reconfigurationState) {
        this.reconfigurationState = reconfigurationState;
    }

    /**
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState
     *            the refreshState to set
     */
    public void setRefreshState(final RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber
     *            the serialNumber to set
     */
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the standbyOaPreferredIP
     */
    public String getStandbyOaPreferredIP() {
        return standbyOaPreferredIP;
    }

    /**
     * @param standbyOaPreferredIP
     *            the standbyOaPreferredIP to set
     */
    public void setStandbyOaPreferredIP(final String standbyOaPreferredIP) {
        this.standbyOaPreferredIP = standbyOaPreferredIP;
    }

    /**
     * @return the stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * @param stateReason
     *            the stateReason to set
     */
    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the vcmDomainId
     */
    public String getVcmDomainId() {
        return vcmDomainId;
    }

    /**
     * @param vcmDomainId
     *            the vcmDomainId to set
     */
    public void setVcmDomainId(final String vcmDomainId) {
        this.vcmDomainId = vcmDomainId;
    }

    /**
     * @return the vcmDomainName
     */
    public String getVcmDomainName() {
        return vcmDomainName;
    }

    /**
     * @param vcmDomainName
     *            the vcmDomainName to set
     */
    public void setVcmDomainName(final String vcmDomainName) {
        this.vcmDomainName = vcmDomainName;
    }

    /**
     * @return the vcmMode
     */
    public Boolean getVcmMode() {
        return vcmMode;
    }

    /**
     * @param vcmMode
     *            the vcmMode to set
     */
    public void setVcmMode(final Boolean vcmMode) {
        this.vcmMode = vcmMode;
    }

    /**
     * @return the vcmUrl
     */
    public String getVcmUrl() {
        return vcmUrl;
    }

    /**
     * @param vcmUrl
     *            the vcmUrl to set
     */
    public void setVcmUrl(final String vcmUrl) {
        this.vcmUrl = vcmUrl;
    }

}
