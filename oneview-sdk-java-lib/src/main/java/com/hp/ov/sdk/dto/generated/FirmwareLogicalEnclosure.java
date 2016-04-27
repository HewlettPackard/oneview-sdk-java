/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class FirmwareLogicalEnclosure implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private Boolean forceInstallFirmware;
    private String firmwareBaselineUri;
    private String firmwareUpdateOn;
    private String logicalInterconnectUpdateMode;
    private boolean updateFirmwareOnUnmanagedInterconnect;
    private boolean validateIfLIFirmwareUpdateIsNonDisruptive;
    private Boolean manageFirmware;

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
     * @return the firmwareUpdateOn
     */
    public String getFirmwareUpdateOn() {
        return firmwareUpdateOn;
    }

    /**
     * @param firmwareUpdateOn the firmwareUpdateOn to set
     */
    public void setFirmwareUpdateOn(String firmwareUpdateOn) {
        this.firmwareUpdateOn = firmwareUpdateOn;
    }


    /**
     * @return the logicalInterconnectUpdateMode
     */
    public String getLogicalInterconnectUpdateMode() {
        return logicalInterconnectUpdateMode;
    }

    /**
     * @param logicalInterconnectUpdateMode the logicalInterconnectUpdateMode to set
     */
    public void setLogicalInterconnectUpdateMode(String logicalInterconnectUpdateMode) {
        this.logicalInterconnectUpdateMode = logicalInterconnectUpdateMode;
    }

    /**
     * @return the updateFirmwareOnUnmanagedInterconnect
     */
    public boolean isUpdateFirmwareOnUnmanagedInterconnect() {
        return updateFirmwareOnUnmanagedInterconnect;
    }

    /**
     * @param updateFirmwareOnUnmanagedInterconnect the updateFirmwareOnUnmanagedInterconnect to set
     */
    public void setUpdateFirmwareOnUnmanagedInterconnect(boolean updateFirmwareOnUnmanagedInterconnect) {
        this.updateFirmwareOnUnmanagedInterconnect = updateFirmwareOnUnmanagedInterconnect;
    }

    /**
     * @return the validateIfLIFirmwareUpdateIsNonDisruptive
     */
    public boolean isValidateIfLIFirmwareUpdateIsNonDisruptive() {
        return validateIfLIFirmwareUpdateIsNonDisruptive;
    }

    /**
     * @param validateIfLIFirmwareUpdateIsNonDisruptive the validateIfLIFirmwareUpdateIsNonDisruptive to set
     */
    public void setValidateIfLIFirmwareUpdateIsNonDisruptive(boolean validateIfLIFirmwareUpdateIsNonDisruptive) {
        this.validateIfLIFirmwareUpdateIsNonDisruptive = validateIfLIFirmwareUpdateIsNonDisruptive;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(forceInstallFirmware)
                .append(firmwareBaselineUri)
                .append(firmwareUpdateOn)
                .append(logicalInterconnectUpdateMode)
                .append(updateFirmwareOnUnmanagedInterconnect)
                .append(validateIfLIFirmwareUpdateIsNonDisruptive)
                .append(manageFirmware)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FirmwareLogicalEnclosure) == false) {
            return false;
        }
        final FirmwareLogicalEnclosure rhs = ((FirmwareLogicalEnclosure) other);
        return new EqualsBuilder()
                .append(forceInstallFirmware, rhs.forceInstallFirmware)
                .append(firmwareBaselineUri, rhs.firmwareBaselineUri)
                .append(firmwareUpdateOn, rhs.firmwareUpdateOn)
                .append(logicalInterconnectUpdateMode, rhs.logicalInterconnectUpdateMode)
                .append(updateFirmwareOnUnmanagedInterconnect, rhs.updateFirmwareOnUnmanagedInterconnect)
                .append(validateIfLIFirmwareUpdateIsNonDisruptive, rhs.validateIfLIFirmwareUpdateIsNonDisruptive)
                .append(manageFirmware, rhs.manageFirmware)
                .isEquals();
    }
}
