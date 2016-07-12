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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;

/**
 * The StorageVolume data transfer object (DTO) contains the information used
 * to represent a storage volume in the system. It is passed in to the
 * add/update storage volume REST api, as well as the add/update storage volume
 * through java client api.
 */
public class StorageVolume extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String allocatedCapacity;
    private String deviceType;
    private String deviceVolumeName;
    private Boolean isPermanent;
    private String provisionType;
    private String provisionedCapacity;
    private String raidLevel;
    private RefreshState refreshState;

    @Since(200)
    private String revertToSnapshotUri;
    @Since(200)
    private String snapshotPoolUri;
    @Since(200)
    private String snapshots;

    private Boolean shareable;
    private String stateReason;
    private String storagePoolUri;
    private String storageSystemUri;
    private String wwn;

    public String getAllocatedCapacity() {
        return allocatedCapacity;
    }

    public void setAllocatedCapacity(final String allocatedCapacity) {
        this.allocatedCapacity = allocatedCapacity;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(final String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceVolumeName() {
        return deviceVolumeName;
    }

    public void setDeviceVolumeName(final String deviceVolumeName) {
        this.deviceVolumeName = deviceVolumeName;
    }

    public Boolean getIsPermanent() {
        return isPermanent;
    }

    public void setIsPermanent(final Boolean isPermanent) {
        this.isPermanent = isPermanent;
    }

    public String getProvisionType() {
        return provisionType;
    }

    public void setProvisionType(final String provisionType) {
        this.provisionType = provisionType;
    }

    public String getProvisionedCapacity() {
        return provisionedCapacity;
    }

    public void setProvisionedCapacity(final String provisionedCapacity) {
        this.provisionedCapacity = provisionedCapacity;
    }

    public String getRaidLevel() {
        return raidLevel;
    }

    public void setRaidLevel(final String raidLevel) {
        this.raidLevel = raidLevel;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(final RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public Boolean getShareable() {
        return shareable;
    }

    public void setShareable(final Boolean shareable) {
        this.shareable = shareable;
    }

    public String getRevertToSnapshotUri() {
        return revertToSnapshotUri;
    }

    public void setRevertToSnapshotUri(String revertToSnapshotUri) {
        this.revertToSnapshotUri = revertToSnapshotUri;
    }

    public String getSnapshotPoolUri() {
        return snapshotPoolUri;
    }

    public void setSnapshotPoolUri(String snapshotPoolUri) {
        this.snapshotPoolUri = snapshotPoolUri;
    }

    public String getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(String snapshots) {
        this.snapshots = snapshots;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    public String getStoragePoolUri() {
        return storagePoolUri;
    }

    public void setStoragePoolUri(final String storagePoolUri) {
        this.storagePoolUri = storagePoolUri;
    }

    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

    public String getWwn() {
        return wwn;
    }

    public void setWwn(String wwn) {
        this.wwn = wwn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof StorageVolume) {
            StorageVolume that = (StorageVolume) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(allocatedCapacity, that.allocatedCapacity)
                    .append(deviceType, that.deviceType)
                    .append(deviceVolumeName, that.deviceVolumeName)
                    .append(isPermanent, that.isPermanent)
                    .append(provisionType, that.provisionType)
                    .append(provisionedCapacity, that.provisionedCapacity)
                    .append(raidLevel, that.raidLevel)
                    .append(refreshState, that.refreshState)
                    .append(revertToSnapshotUri, that.revertToSnapshotUri)
                    .append(snapshotPoolUri, that.snapshotPoolUri)
                    .append(snapshots, that.snapshots)
                    .append(shareable, that.shareable)
                    .append(stateReason, that.stateReason)
                    .append(storagePoolUri, that.storagePoolUri)
                    .append(storageSystemUri, that.storageSystemUri)
                    .append(wwn, that.wwn)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(allocatedCapacity)
                .append(deviceType)
                .append(deviceVolumeName)
                .append(isPermanent)
                .append(provisionType)
                .append(provisionedCapacity)
                .append(raidLevel)
                .append(refreshState)
                .append(revertToSnapshotUri)
                .append(snapshotPoolUri)
                .append(snapshots)
                .append(shareable)
                .append(stateReason)
                .append(storagePoolUri)
                .append(storageSystemUri)
                .append(wwn)
                .toHashCode();
    }
}
