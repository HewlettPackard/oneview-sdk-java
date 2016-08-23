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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class Firmware implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean forceInstallFirmware;
    private String firmwareBaselineUri;
    private Boolean manageFirmware;
    @Since(200)
    private FirmwareInstallType firmwareInstallType;

    /**
     *
     * @return The forceInstallFirmware
     */
    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    /**
     *
     * @param forceInstallFirmware
     *            The forceInstallFirmware
     */
    public void setForceInstallFirmware(final Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    /**
     *
     * @return The firmwareBaselineUri
     */
    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }

    /**
     *
     * @param firmwareBaselineUri
     *            The firmwareBaselineUri
     */
    public void setFirmwareBaselineUri(final String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
    }

    /**
     *
     * @return The manageFirmware
     */
    public Boolean getManageFirmware() {
        return manageFirmware;
    }

    /**
     *
     * @param manageFirmware
     *            The manageFirmware
     */
    public void setManageFirmware(final Boolean manageFirmware) {
        this.manageFirmware = manageFirmware;
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
