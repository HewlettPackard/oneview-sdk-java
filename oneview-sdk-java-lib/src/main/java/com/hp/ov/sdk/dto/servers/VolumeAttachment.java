/*
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
 */
package com.hp.ov.sdk.dto.servers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class VolumeAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @Since(300)
    private Boolean isBootVolume;
    private String lun;
    private String lunType;
    private Boolean permanent;
    private VolumeAttachmentState state;
    private String status;
    private List<StoragePath> storagePaths = new ArrayList<>();
    private String volumeDescription;
    private String volumeName;
    private String volumeProvisionType;
    private String volumeProvisionedCapacityBytes;
    private Boolean volumeShareable;
    private String volumeStoragePoolUri;
    private String volumeStorageSystemUri;
    private String volumeUri;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the isBootVolume
     */
    public Boolean getBootVolume() {
        return isBootVolume;
    }

    /**
     * @param isBootVolume the isBootVolume to set
     */
    public void setBootVolume(Boolean isBootVolume) {
        this.isBootVolume = isBootVolume;
    }

    /**
     * @return the lun
     */
    public String getLun() {
        return lun;
    }

    /**
     * @param lun the lun to set
     */
    public void setLun(String lun) {
        this.lun = lun;
    }

    /**
     * @return the lunType
     */
    public String getLunType() {
        return lunType;
    }

    /**
     * @param lunType the lunType to set
     */
    public void setLunType(String lunType) {
        this.lunType = lunType;
    }

    /**
     * @return the permanent
     */
    public Boolean getPermanent() {
        return permanent;
    }

    /**
     * @param permanent the permanent to set
     */
    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
    }

    /**
     * @return the state
     */
    public VolumeAttachmentState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(VolumeAttachmentState state) {
        this.state = state;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the storagePaths
     */
    public List<StoragePath> getStoragePaths() {
        return storagePaths;
    }

    /**
     * @param storagePaths the storagePaths to set
     */
    public void setStoragePaths(List<StoragePath> storagePaths) {
        this.storagePaths = storagePaths;
    }

    /**
     * @return the volumeDescription
     */
    public String getVolumeDescription() {
        return volumeDescription;
    }

    /**
     * @param volumeDescription the volumeDescription to set
     */
    public void setVolumeDescription(String volumeDescription) {
        this.volumeDescription = volumeDescription;
    }

    /**
     * @return the volumeName
     */
    public String getVolumeName() {
        return volumeName;
    }

    /**
     * @param volumeName the volumeName to set
     */
    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    /**
     * @return the volumeProvisionType
     */
    public String getVolumeProvisionType() {
        return volumeProvisionType;
    }

    /**
     * @param volumeProvisionType the volumeProvisionType to set
     */
    public void setVolumeProvisionType(String volumeProvisionType) {
        this.volumeProvisionType = volumeProvisionType;
    }

    /**
     * @return the volumeProvisionedCapacityBytes
     */
    public String getVolumeProvisionedCapacityBytes() {
        return volumeProvisionedCapacityBytes;
    }

    /**
     * @param volumeProvisionedCapacityBytes the volumeProvisionedCapacityBytes to set
     */
    public void setVolumeProvisionedCapacityBytes(String volumeProvisionedCapacityBytes) {
        this.volumeProvisionedCapacityBytes = volumeProvisionedCapacityBytes;
    }

    /**
     * @return the volumeShareable
     */
    public Boolean getVolumeShareable() {
        return volumeShareable;
    }

    /**
     * @param volumeShareable the volumeShareable to set
     */
    public void setVolumeShareable(Boolean volumeShareable) {
        this.volumeShareable = volumeShareable;
    }

    /**
     * @return the volumeStoragePoolUri
     */
    public String getVolumeStoragePoolUri() {
        return volumeStoragePoolUri;
    }

    /**
     * @param volumeStoragePoolUri the volumeStoragePoolUri to set
     */
    public void setVolumeStoragePoolUri(String volumeStoragePoolUri) {
        this.volumeStoragePoolUri = volumeStoragePoolUri;
    }

    /**
     * @return the volumeStorageSystemUri
     */
    public String getVolumeStorageSystemUri() {
        return volumeStorageSystemUri;
    }

    /**
     * @param volumeStorageSystemUri the volumeStorageSystemUri to set
     */
    public void setVolumeStorageSystemUri(String volumeStorageSystemUri) {
        this.volumeStorageSystemUri = volumeStorageSystemUri;
    }

    /**
     * @return the volumeUri
     */
    public String getVolumeUri() {
        return volumeUri;
    }

    /**
     * @param volumeUri the volumeUri to set
     */
    public void setVolumeUri(String volumeUri) {
        this.volumeUri = volumeUri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
