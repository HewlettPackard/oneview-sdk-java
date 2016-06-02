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

public class StoragePathTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer connectionId;
    private Boolean isEnabled;
    private String storageTargetType;
    private List<String> storageTargets;

    /**
     * @return the connectionId
     */
    public Integer getConnectionId() {
        return connectionId;
    }

    /**
     * @param connectionId the connectionId to set
     */
    public void setConnectionId(Integer connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * @return the isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * @param isEnabled the isEnabled to set
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * @return the storageTargetType
     */
    public String getStorageTargetType() {
        return storageTargetType;
    }

    /**
     * @param storageTargetType the storageTargetType to set
     */
    public void setStorageTargetType(String storageTargetType) {
        this.storageTargetType = storageTargetType;
    }

    /**
     * @return the storageTargets
     */
    public List<String> getStorageTargets() {
        return storageTargets;
    }

    /**
     * @param storageTargets the storageTargets to set
     */
    public void setStorageTargets(List<String> storageTargets) {
        this.storageTargets = storageTargets;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        StoragePathTemplate that = (StoragePathTemplate) obj;

        return new EqualsBuilder()
                .append(connectionId, that.connectionId)
                .append(isEnabled, that.isEnabled)
                .append(storageTargetType, that.storageTargetType)
                .append(storageTargets, that.storageTargets)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(connectionId)
                .append(isEnabled)
                .append(storageTargetType)
                .append(storageTargets)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("connectionId", connectionId)
                .append("isEnabled", isEnabled)
                .append("storageTargetType", storageTargetType)
                .append("storageTargets", storageTargets)
                .toString();
    }

}
