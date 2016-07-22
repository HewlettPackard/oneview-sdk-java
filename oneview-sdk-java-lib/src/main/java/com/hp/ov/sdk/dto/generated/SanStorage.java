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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class SanStorage implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private List<VolumeAttachment> volumeAttachments = new ArrayList<VolumeAttachment>();
    /**
     * 
     * (Required)
     * 
     */
    private String hostOSType;
    /**
     * 
     * (Required)
     * 
     */
    private Boolean manageSanStorage;

    /**
     * 
     * @return The volumeAttachments
     */
    public List<VolumeAttachment> getVolumeAttachments() {
        return volumeAttachments;
    }

    /**
     * 
     * @param volumeAttachments
     *            The volumeAttachments
     */
    public void setVolumeAttachments(final List<VolumeAttachment> volumeAttachments) {
        this.volumeAttachments = volumeAttachments;
    }

    /**
     * 
     * (Required)
     * 
     * @return The hostOSType
     */
    public String getHostOSType() {
        return hostOSType;
    }

    /**
     * 
     * (Required)
     * 
     * @param hostOSType
     *            The hostOSType
     */
    public void setHostOSType(final String hostOSType) {
        this.hostOSType = hostOSType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The manageSanStorage
     */
    public Boolean getManageSanStorage() {
        return manageSanStorage;
    }

    /**
     * 
     * (Required)
     * 
     * @param manageSanStorage
     *            The manageSanStorage
     */
    public void setManageSanStorage(final Boolean manageSanStorage) {
        this.manageSanStorage = manageSanStorage;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(volumeAttachments).append(hostOSType).append(manageSanStorage).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SanStorage) == false) {
            return false;
        }
        final SanStorage rhs = ((SanStorage) other);
        return new EqualsBuilder().append(volumeAttachments, rhs.volumeAttachments).append(hostOSType, rhs.hostOSType)
                .append(manageSanStorage, rhs.manageSanStorage).isEquals();
    }

}
