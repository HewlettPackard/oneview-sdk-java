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
package com.hp.ov.sdk.dto.storage;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.RefreshState;

/**
 * The StorageVolumeTemplate data transfer object (DTO) contains the information
 * used to represent a storage volume template in the system. It is passed in to
 * the add/update storage volume template REST api, as well as the add/update
 * storage volume template through java client api.
 */
public class StorageVolumeTemplate extends BaseModelResource {

    private static final long serialVersionUID = 188324615360018752L;

    private TemplateProvisioningData provisioning;
    private RefreshState refreshState;

    @Since(200)
    private String snapshotPoolUri;

    private String stateReason;
    private String storageSystemUri;

    public TemplateProvisioningData getProvisioning() {
        return provisioning;
    }

    public void setProvisioning(final TemplateProvisioningData provisioning) {
        this.provisioning = provisioning;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(final RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getSnapshotPoolUri() {
        return snapshotPoolUri;
    }

    public void setSnapshotPoolUri(String snapshotPoolUri) {
        this.snapshotPoolUri = snapshotPoolUri;
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

}
