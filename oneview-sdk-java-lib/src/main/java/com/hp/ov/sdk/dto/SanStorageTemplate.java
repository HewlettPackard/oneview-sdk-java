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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SanStorageTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String hostOSType;
    private Boolean manageSanStorage;
    private List<VolumeAttachmentTemplate> volumeAttachments;

    /**
     * @return the hostOSType
     */
    public String getHostOSType() {
        return hostOSType;
    }

    /**
     * @param hostOSType the hostOSType to set
     */
    public void setHostOSType(String hostOSType) {
        this.hostOSType = hostOSType;
    }

    /**
     * @return the manageSanStorage
     */
    public Boolean getManageSanStorage() {
        return manageSanStorage;
    }

    /**
     * @param manageSanStorage the manageSanStorage to set
     */
    public void setManageSanStorage(Boolean manageSanStorage) {
        this.manageSanStorage = manageSanStorage;
    }

    /**
     * @return the volumeAttachments
     */
    public List<VolumeAttachmentTemplate> getVolumeAttachments() {
        return volumeAttachments;
    }

    /**
     * @param volumeAttachments the volumeAttachments to set
     */
    public void setVolumeAttachments(List<VolumeAttachmentTemplate> volumeAttachments) {
        this.volumeAttachments = volumeAttachments;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        SanStorageTemplate that = (SanStorageTemplate) obj;

        return new EqualsBuilder()
                .append(hostOSType, that.hostOSType)
                .append(manageSanStorage, that.manageSanStorage)
                .append(volumeAttachments, that.volumeAttachments)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(hostOSType)
                .append(manageSanStorage)
                .append(volumeAttachments)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("hostOSType", hostOSType)
                .append("manageSanStorage", manageSanStorage)
                .append("volumeAttachments", volumeAttachments)
                .toString();
    }

}
