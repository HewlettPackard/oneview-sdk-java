/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class StorageVolumeV2 extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String allocatedCapacity;
    private String deviceType;
    private String deviceVolumeName;
    private Boolean isPermanent;
    private String provisionType;
    private String provisionedCapacity;
    private String raidLevel;
    private RefreshState refreshState;
    private Boolean shareable;
    private String stateReason;
    private String storagePoolUri;
    private String storageSystemUri;

    /**
     * 
     * @return The allocatedCapacity
     */
    public String getAllocatedCapacity() {
        return allocatedCapacity;
    }

    /**
     * 
     * @param allocatedCapacity
     *            The allocatedCapacity
     */
    public void setAllocatedCapacity(final String allocatedCapacity) {
        this.allocatedCapacity = allocatedCapacity;
    }

    /**
     * 
     * @return The deviceType
     */
    public String getDeviceType() {
        return deviceType;
    }

    /**
     * 
     * @param deviceType
     *            The deviceType
     */
    public void setDeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * 
     * @return The deviceVolumeName
     */
    public String getDeviceVolumeName() {
        return deviceVolumeName;
    }

    /**
     * 
     * @param deviceVolumeName
     *            The deviceVolumeName
     */
    public void setDeviceVolumeName(final String deviceVolumeName) {
        this.deviceVolumeName = deviceVolumeName;
    }

    /**
     * 
     * @return The isPermanent
     */
    public Boolean getIsPermanent() {
        return isPermanent;
    }

    /**
     * 
     * @param isPermanent
     *            The isPermanent
     */
    public void setIsPermanent(final Boolean isPermanent) {
        this.isPermanent = isPermanent;
    }

    /**
     * 
     * @return The provisionType
     */
    public String getProvisionType() {
        return provisionType;
    }

    /**
     * 
     * @param provisionType
     *            The provisionType
     */
    public void setProvisionType(final String provisionType) {
        this.provisionType = provisionType;
    }

    /**
     * 
     * @return The provisionedCapacity
     */
    public String getProvisionedCapacity() {
        return provisionedCapacity;
    }

    /**
     * 
     * @param provisionedCapacity
     *            The provisionedCapacity
     */
    public void setProvisionedCapacity(final String provisionedCapacity) {
        this.provisionedCapacity = provisionedCapacity;
    }

    /**
     * 
     * @return The raidLevel
     */
    public String getRaidLevel() {
        return raidLevel;
    }

    /**
     * 
     * @param raidLevel
     *            The raidLevel
     */
    public void setRaidLevel(final String raidLevel) {
        this.raidLevel = raidLevel;
    }

    /**
     * 
     * @return The refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * 
     * @param refreshState
     *            The refreshState
     */
    public void setRefreshState(final RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * 
     * @return The shareable
     */
    public Boolean getShareable() {
        return shareable;
    }

    /**
     * 
     * @param shareable
     *            The shareable
     */
    public void setShareable(final Boolean shareable) {
        this.shareable = shareable;
    }

    /**
     * 
     * @return The stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * 
     * @param stateReason
     *            The stateReason
     */
    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * 
     * @return The storagePoolUri
     */
    public String getStoragePoolUri() {
        return storagePoolUri;
    }

    /**
     * 
     * @param storagePoolUri
     *            The storagePoolUri
     */
    public void setStoragePoolUri(final String storagePoolUri) {
        this.storagePoolUri = storagePoolUri;
    }

    /**
     * 
     * @return The storageSystemUri
     */
    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    /**
     * 
     * @param storageSystemUri
     *            The storageSystemUri
     */
    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

}
