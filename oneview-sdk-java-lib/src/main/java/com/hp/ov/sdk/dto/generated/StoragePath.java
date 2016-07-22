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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class StoragePath implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private List<String> storageTargets = new ArrayList<String>();
    private StoragePath.StorageTargetType storageTargetType;
    /**
     * 
     * (Required)
     * 
     */
    private Integer connectionId;
    /**
     * 
     * (Required)
     * 
     */
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
    public StoragePath.StorageTargetType getStorageTargetType() {
        return storageTargetType;
    }

    /**
     * 
     * @param storageTargetType
     *            The storageTargetType
     */
    public void setStorageTargetType(final StoragePath.StorageTargetType storageTargetType) {
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
        return new HashCodeBuilder().append(storageTargets).append(storageTargetType).append(connectionId).append(isEnabled)
                .append(status).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StoragePath) == false) {
            return false;
        }
        final StoragePath rhs = ((StoragePath) other);
        return new EqualsBuilder().append(storageTargets, rhs.storageTargets).append(storageTargetType, rhs.storageTargetType)
                .append(connectionId, rhs.connectionId).append(isEnabled, rhs.isEnabled).append(status, rhs.status).isEquals();
    }

    public static enum StorageTargetType {

        Auto("Auto"), TargetPorts("TargetPorts");
        private final String value;
        private static Map<String, StoragePath.StorageTargetType> constants = new HashMap<String, StoragePath.StorageTargetType>();

        static {
            for (final StoragePath.StorageTargetType c : values()) {
                constants.put(c.value, c);
            }
        }

        private StorageTargetType(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static StoragePath.StorageTargetType fromValue(final String value) {
            final StoragePath.StorageTargetType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
