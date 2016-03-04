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

import java.io.Serializable;
import com.google.gson.annotations.Since;

public class AddStorageVolumeV2 implements Serializable {

    private static final long serialVersionUID = -2745402537486462067L;

    private String description;
    private Boolean isPermanent;
    private String name;
    private StorageVolumeProvisioningParameters provisioningParameters;

    @Since(200)
    private String snapshotPoolUri;
    @Since(200)
    private String snapshotUri;
    @Since(200)
    private String storageSystemVolumeName;

    private String storageSystemUri;
    private String templateUri;
    private String wwn;
    private String type;

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Boolean getIsPermanent() {
        return isPermanent;
    }

    public void setIsPermanent(final Boolean isPermanent) {
        this.isPermanent = isPermanent;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public StorageVolumeProvisioningParameters getProvisioningParameters() {
        return provisioningParameters;
    }

    public void setProvisioningParameters(final StorageVolumeProvisioningParameters provisioningParameters) {
        this.provisioningParameters = provisioningParameters;
    }

    public String getSnapshotPoolUri() {
        return snapshotPoolUri;
    }

    public void setSnapshotPoolUri(String snapshotPoolUri) {
        this.snapshotPoolUri = snapshotPoolUri;
    }

    public String getSnapshotUri() {
        return snapshotUri;
    }

    public void setSnapshotUri(String snapshotUri) {
        this.snapshotUri = snapshotUri;
    }

    public String getStorageSystemVolumeName() {
        return storageSystemVolumeName;
    }

    public void setStorageSystemVolumeName(String storageSystemVolumeName) {
        this.storageSystemVolumeName = storageSystemVolumeName;
    }

    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

    public String getTemplateUri() {
        return templateUri;
    }

    public void setTemplateUri(final String templateUri) {
        this.templateUri = templateUri;
    }

    public String getWwn() {
        return wwn;
    }

    public void setWwn(final String wwn) {
        this.wwn = wwn;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

}
