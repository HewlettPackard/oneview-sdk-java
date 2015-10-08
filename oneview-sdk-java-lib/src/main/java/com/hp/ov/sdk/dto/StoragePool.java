/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto;

/**
 * The StoragePool data transfer object (DTO) contains the information used to
 * represent a storage pool in the system. It is passed in to the add/update
 * storage pool REST api, as well as the add/update storage pool through java
 * client api.
 */

public class StoragePool extends BaseModelResource {

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
     * @return The capacityLimit
     */
    public String getCapacityLimit() {
        return capacityLimit;
    }

    /**
     * 
     * @param capacityLimit
     *            The capacityLimit
     */
    public void setCapacityLimit(final String capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    /**
     * 
     * @return The capacityWarningLimit
     */
    public String getCapacityWarningLimit() {
        return capacityWarningLimit;
    }

    /**
     * 
     * @param capacityWarningLimit
     *            The capacityWarningLimit
     */
    public void setCapacityWarningLimit(final String capacityWarningLimit) {
        this.capacityWarningLimit = capacityWarningLimit;
    }

    /**
     * 
     * @return The deviceSpeed
     */
    public String getDeviceSpeed() {
        return deviceSpeed;
    }

    /**
     * 
     * @param deviceSpeed
     *            The deviceSpeed
     */
    public void setDeviceSpeed(final String deviceSpeed) {
        this.deviceSpeed = deviceSpeed;
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
     * @return The domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 
     * @param domain
     *            The domain
     */
    public void setDomain(final String domain) {
        this.domain = domain;
    }

    /**
     * 
     * @return The freeCapacity
     */
    public String getFreeCapacity() {
        return freeCapacity;
    }

    /**
     * 
     * @param freeCapacity
     *            The freeCapacity
     */
    public void setFreeCapacity(final String freeCapacity) {
        this.freeCapacity = freeCapacity;
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

    /**
     * 
     * @return The supportedRAIDLevel
     */
    public String getSupportedRAIDLevel() {
        return supportedRAIDLevel;
    }

    /**
     * 
     * @param supportedRAIDLevel
     *            The supportedRAIDLevel
     */
    public void setSupportedRAIDLevel(final String supportedRAIDLevel) {
        this.supportedRAIDLevel = supportedRAIDLevel;
    }

    /**
     * 
     * @return The totalCapacity
     */
    public String getTotalCapacity() {
        return totalCapacity;
    }

    /**
     * 
     * @param totalCapacity
     *            The totalCapacity
     */
    public void setTotalCapacity(final String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

}
