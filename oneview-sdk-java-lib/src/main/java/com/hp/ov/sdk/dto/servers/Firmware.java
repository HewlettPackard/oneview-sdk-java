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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.servers.serverprofile.FirmwareInstallType;

public class Firmware implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firmwareBaselineUri;
    @Since(200)
    private FirmwareInstallType firmwareInstallType;
    private Boolean forceInstallFirmware;
    private Boolean manageFirmware;

    /**
     * @return the firmwareBaselineUri
     */
    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }

    /**
     * @param firmwareBaselineUri the firmwareBaselineUri to set
     */
    public void setFirmwareBaselineUri(String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
    }

    /**
     * @return the firmwareInstallType
     */
    public FirmwareInstallType getFirmwareInstallType() {
        return firmwareInstallType;
    }

    /**
     * @param firmwareInstallType the firmwareInstallType to set
     */
    public void setFirmwareInstallType(FirmwareInstallType firmwareInstallType) {
        this.firmwareInstallType = firmwareInstallType;
    }

    /**
     * @return the forceInstallFirmware
     */
    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    /**
     * @param forceInstallFirmware the forceInstallFirmware to set
     */
    public void setForceInstallFirmware(Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    /**
     * @return the manageFirmware
     */
    public Boolean getManageFirmware() {
        return manageFirmware;
    }

    /**
     * @param manageFirmware the manageFirmware to set
     */
    public void setManageFirmware(Boolean manageFirmware) {
        this.manageFirmware = manageFirmware;
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
