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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FirmwareSettingsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firmwareBaselineUri;
    private String firmwareInstallType;
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
    public String getFirmwareInstallType() {
        return firmwareInstallType;
    }

    /**
     * @param firmwareInstallType the firmwareInstallType to set
     */
    public void setFirmwareInstallType(String firmwareInstallType) {
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        FirmwareSettingsTemplate that = (FirmwareSettingsTemplate) obj;

        return new EqualsBuilder()
                .append(firmwareBaselineUri, that.firmwareBaselineUri)
                .append(firmwareInstallType, that.firmwareInstallType)
                .append(forceInstallFirmware, that.forceInstallFirmware)
                .append(manageFirmware, that.manageFirmware)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firmwareBaselineUri)
                .append(firmwareInstallType)
                .append(forceInstallFirmware)
                .append(manageFirmware)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firmwareBaselineUri", firmwareBaselineUri)
                .append("firmwareInstallType", firmwareInstallType)
                .append("forceInstallFirmware", forceInstallFirmware)
                .append("manageFirmware", manageFirmware)
                .toString();
    }

}
