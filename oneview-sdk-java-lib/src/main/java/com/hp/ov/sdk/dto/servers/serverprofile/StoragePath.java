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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class StoragePath implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> storageTargets = new ArrayList<>();
    private StorageTargetType storageTargetType;
    private Integer connectionId;
    private Boolean isEnabled;
    private String status;

    /**
     * 
     * @return The storageTargets
     */
    public List<String> getStorageTargets() {
        return storageTargets;
    }

    /**
     * 
     * @param storageTargets
     *            The storageTargets
     */
    public void setStorageTargets(final List<String> storageTargets) {
        this.storageTargets = storageTargets;
    }

    /**
     * 
     * @return The storageTargetType
     */
    public StorageTargetType getStorageTargetType() {
        return storageTargetType;
    }

    /**
     * 
     * @param storageTargetType
     *            The storageTargetType
     */
    public void setStorageTargetType(final StorageTargetType storageTargetType) {
        this.storageTargetType = storageTargetType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The connectionId
     */
    public Integer getConnectionId() {
        return connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @param connectionId
     *            The connectionId
     */
    public void setConnectionId(final Integer connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * 
     * (Required)
     * 
     * @return The isEnabled
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     * 
     * (Required)
     * 
     * @param isEnabled
     *            The isEnabled
     */
    public void setIsEnabled(final Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * 
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
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
