/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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


public class StorageVolumeSnapshot extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String capacity;
    private String deviceSnapshotName;
    private Boolean readOnly;
    private RefreshState refreshState;
    private String snapshotType;
    private String stateReason;
    private String storageVolumeUri;
    private String wwn;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getDeviceSnapshotName() {
        return deviceSnapshotName;
    }

    public void setDeviceSnapshotName(String deviceSnapshotName) {
        this.deviceSnapshotName = deviceSnapshotName;
    }

    public Boolean getReadOnly() {
        return readOnly;
    }

    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getSnapshotType() {
        return snapshotType;
    }

    public void setSnapshotType(String snapshotType) {
        this.snapshotType = snapshotType;
    }

    public String getStateReason() {
        return stateReason;
    }

    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    public String getStorageVolumeUri() {
        return storageVolumeUri;
    }

    public void setStorageVolumeUri(String storageVolumeUri) {
        this.storageVolumeUri = storageVolumeUri;
    }

    public String getWwn() {
        return wwn;
    }

    public void setWwn(String wwn) {
        this.wwn = wwn;
    }
}
