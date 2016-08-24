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
package com.hp.ov.sdk.dto.servers.serverprofiletemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

public class LocalStorageControllerTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Since(300)
    private String deviceSlot;
    private Boolean initialize;
    private List<LogicalDriveTemplate> logicalDrives = new ArrayList<>();
    @Until(299)
    private Boolean managed;
    private String mode;
    @Until(299)
    private String slotNumber;

    /**
     * @return the deviceSlot
     */
    public String getDeviceSlot() {
        return deviceSlot;
    }

    /**
     * @param deviceSlot the deviceSlot to set
     */
    public void setDeviceSlot(String deviceSlot) {
        this.deviceSlot = deviceSlot;
    }

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
