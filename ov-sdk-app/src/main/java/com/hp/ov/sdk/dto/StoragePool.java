/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class StoragePool extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private String allocatedCapacity;
    private String capacityLimit;
    private String capacityWarningLimit;
    private String deviceSpeed;
    private String deviceType;
    private String domain;
    private String freeCapacity;
    private RefreshState refreshState;
    private String stateReason;
    private String storageSystemUri;
    private String supportedRAIDLevel;
    private String totalCapacity;

    /**
     * 
     * @return
     *         The allocatedCapacity
     */
    public String getAllocatedCapacity()
    {
        return allocatedCapacity;
    }

    /**
     * 
     * @param allocatedCapacity
     *        The allocatedCapacity
     */
    public void setAllocatedCapacity(String allocatedCapacity)
    {
        this.allocatedCapacity = allocatedCapacity;
    }

    /**
     * 
     * @return
     *         The capacityLimit
     */
    public String getCapacityLimit()
    {
        return capacityLimit;
    }

    /**
     * 
     * @param capacityLimit
     *        The capacityLimit
     */
    public void setCapacityLimit(String capacityLimit)
    {
        this.capacityLimit = capacityLimit;
    }

    /**
     * 
     * @return
     *         The capacityWarningLimit
     */
    public String getCapacityWarningLimit()
    {
        return capacityWarningLimit;
    }

    /**
     * 
     * @param capacityWarningLimit
     *        The capacityWarningLimit
     */
    public void setCapacityWarningLimit(String capacityWarningLimit)
    {
        this.capacityWarningLimit = capacityWarningLimit;
    }

    /**
     * 
     * @return
     *         The deviceSpeed
     */
    public String getDeviceSpeed()
    {
        return deviceSpeed;
    }

    /**
     * 
     * @param deviceSpeed
     *        The deviceSpeed
     */
    public void setDeviceSpeed(String deviceSpeed)
    {
        this.deviceSpeed = deviceSpeed;
    }

    /**
     * 
     * @return
     *         The deviceType
     */
    public String getDeviceType()
    {
        return deviceType;
    }

    /**
     * 
     * @param deviceType
     *        The deviceType
     */
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    /**
     * 
     * @return
     *         The domain
     */
    public String getDomain()
    {
        return domain;
    }

    /**
     * 
     * @param domain
     *        The domain
     */
    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    /**
     * 
     * @return
     *         The freeCapacity
     */
    public String getFreeCapacity()
    {
        return freeCapacity;
    }

    /**
     * 
     * @param freeCapacity
     *        The freeCapacity
     */
    public void setFreeCapacity(String freeCapacity)
    {
        this.freeCapacity = freeCapacity;
    }

    /**
     * 
     * @return
     *         The refreshState
     */
    public RefreshState getRefreshState()
    {
        return refreshState;
    }

    /**
     * 
     * @param refreshState
     *        The refreshState
     */
    public void setRefreshState(RefreshState refreshState)
    {
        this.refreshState = refreshState;
    }

    /**
     * 
     * @return
     *         The stateReason
     */
    public String getStateReason()
    {
        return stateReason;
    }

    /**
     * 
     * @param stateReason
     *        The stateReason
     */
    public void setStateReason(String stateReason)
    {
        this.stateReason = stateReason;
    }

    /**
     * 
     * @return
     *         The storageSystemUri
     */
    public String getStorageSystemUri()
    {
        return storageSystemUri;
    }

    /**
     * 
     * @param storageSystemUri
     *        The storageSystemUri
     */
    public void setStorageSystemUri(String storageSystemUri)
    {
        this.storageSystemUri = storageSystemUri;
    }

    /**
     * 
     * @return
     *         The supportedRAIDLevel
     */
    public String getSupportedRAIDLevel()
    {
        return supportedRAIDLevel;
    }

    /**
     * 
     * @param supportedRAIDLevel
     *        The supportedRAIDLevel
     */
    public void setSupportedRAIDLevel(String supportedRAIDLevel)
    {
        this.supportedRAIDLevel = supportedRAIDLevel;
    }

    /**
     * 
     * @return
     *         The totalCapacity
     */
    public String getTotalCapacity()
    {
        return totalCapacity;
    }

    /**
     * 
     * @param totalCapacity
     *        The totalCapacity
     */
    public void setTotalCapacity(String totalCapacity)
    {
        this.totalCapacity = totalCapacity;
    }

}
