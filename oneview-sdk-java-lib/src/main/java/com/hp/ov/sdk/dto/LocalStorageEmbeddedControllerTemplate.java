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

public class LocalStorageEmbeddedControllerTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean initialize;
    private List<LogicalDriveTemplate> logicalDrives;
    private Boolean managed;
    private String mode;
    private String slotNumber;

    /**
     * @return the initialize
     */
    public Boolean getInitialize() {
        return initialize;
    }

    /**
     * @param initialize the initialize to set
     */
    public void setInitialize(Boolean initialize) {
        this.initialize = initialize;
    }

    /**
     * @return the logicalDrives
     */
    public List<LogicalDriveTemplate> getLogicalDrives() {
        return logicalDrives;
    }

    /**
     * @param logicalDrives the logicalDrives to set
     */
    public void setLogicalDrives(List<LogicalDriveTemplate> logicalDrives) {
        this.logicalDrives = logicalDrives;
    }

    /**
     * @return the managed
     */
    public Boolean getManaged() {

        return managed;
    }
    /**
     * @param managed the managed to set
     */
    public void setManaged(Boolean managed) {
        this.managed = managed;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the slotNumber
     */
    public String getSlotNumber() {
        return slotNumber;
    }

    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        LocalStorageEmbeddedControllerTemplate that = (LocalStorageEmbeddedControllerTemplate) obj;

        return new EqualsBuilder()
                .append(initialize, that.initialize)
                .append(logicalDrives, that.logicalDrives)
                .append(managed, that.managed)
                .append(mode, that.mode)
                .append(slotNumber, that.slotNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(initialize)
                .append(logicalDrives)
                .append(managed)
                .append(mode)
                .append(slotNumber)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("initialize", initialize)
                .append("logicalDrives", logicalDrives)
                .append("managed", managed)
                .append("mode", mode)
                .append("slotNumber", slotNumber)
                .toString();
    }

}
