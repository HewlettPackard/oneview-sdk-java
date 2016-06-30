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
package com.hp.ov.sdk.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * The StoragePool data transfer object (DTO) contains the information used to
 * represent a storage pool in the system. It is passed in to the add/update
 * storage pool REST api, as well as the add/update storage pool through java
 * client api.
 */
public class StoragePool extends BaseModelResource {

    private static final long serialVersionUID = 1944401684918563303L;

    public static final String ALLOCATED_CAPACITY_FIELD = "allocatedCapacity";

    /**
     * This field has a special treatment when serialization/deserialization occurs
     *
     * @see com.hp.ov.sdk.adaptors.StoragePoolSerializationAdapter
     */
    private AllocatedCapacity allocatedCapacity;

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

    public AllocatedCapacity getAllocatedCapacityDetails() {
        if (allocatedCapacity == null) {
            this.allocatedCapacity = new AllocatedCapacity();
        }
        return allocatedCapacity;
    }

    public void setAllocatedCapacityDetails(AllocatedCapacity allocatedCapacityDetails) {
        this.allocatedCapacity = allocatedCapacityDetails;
    }

    public String getAllocatedCapacity() {
        return getAllocatedCapacityDetails().getTotalAllocatedCapacity();
    }

    public void setAllocatedCapacity(final String allocatedCapacity) {
        this.getAllocatedCapacityDetails().setTotalAllocatedCapacity(allocatedCapacity);
    }

    public String getCapacityLimit() {
        return capacityLimit;
    }

    public void setCapacityLimit(final String capacityLimit) {
        this.capacityLimit = capacityLimit;
    }

    public String getCapacityWarningLimit() {
        return capacityWarningLimit;
    }

    public void setCapacityWarningLimit(final String capacityWarningLimit) {
        this.capacityWarningLimit = capacityWarningLimit;
    }

    public String getDeviceSpeed() {
        return deviceSpeed;
    }

    public void setDeviceSpeed(final String deviceSpeed) {
        this.deviceSpeed = deviceSpeed;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(final String domain) {
        this.domain = domain;
    }

    public String getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(final String freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(final RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

    public String getSupportedRAIDLevel() {
        return supportedRAIDLevel;
    }

    public void setSupportedRAIDLevel(final String supportedRAIDLevel) {
        this.supportedRAIDLevel = supportedRAIDLevel;
    }
    public String getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(final String totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof StoragePool);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof StoragePool) {
            StoragePool that = (StoragePool) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(allocatedCapacity, that.allocatedCapacity)
                    .append(capacityLimit, that.capacityLimit)
                    .append(capacityWarningLimit, that.capacityWarningLimit)
                    .append(deviceSpeed, that.deviceSpeed)
                    .append(deviceType, that.deviceType)
                    .append(domain, that.domain)
                    .append(freeCapacity, that.freeCapacity)
                    .append(refreshState, that.refreshState)
                    .append(stateReason, that.stateReason)
                    .append(storageSystemUri, that.storageSystemUri)
                    .append(supportedRAIDLevel, that.supportedRAIDLevel)
                    .append(totalCapacity, that.totalCapacity)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(allocatedCapacity)
                .append(capacityLimit)
                .append(capacityWarningLimit)
                .append(deviceSpeed)
                .append(deviceType)
                .append(domain)
                .append(freeCapacity)
                .append(refreshState)
                .append(stateReason)
                .append(storageSystemUri)
                .append(supportedRAIDLevel)
                .append(totalCapacity)
                .toHashCode();
    }
}
