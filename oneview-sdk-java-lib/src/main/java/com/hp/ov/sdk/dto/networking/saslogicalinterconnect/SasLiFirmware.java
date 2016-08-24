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

package com.hp.ov.sdk.dto.networking.saslogicalinterconnect;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.networking.logicalinterconnects.Command;
import com.hp.ov.sdk.dto.networking.logicalinterconnects.LIFirmwareValidationType;

public class SasLiFirmware implements Serializable {

    private static final long serialVersionUID = -7974542676781586143L;

    private Command command;
    private List<DriveEnclosureFirmware> driveEnclosures = new ArrayList<>();
    private String driveSelectedFw;
    private Boolean force;
    private SasLiFirmwareActivationMode fwActivationMode;
    private String fwBaseline;
    private List<SasPhysicalInterconnectFirmware> interconnects = new ArrayList<>();
    private String logicalSwitchId;
    private String sppName;
    private String sppUri;
    private LogicalSasSwitchFirmwareState state;
    private String switchSelectedFw;
    private LIFirmwareValidationType validationType;

    /**
     * @return the command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * @param command the command to set
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * @return the driveEnclosures
     */
    public List<DriveEnclosureFirmware> getDriveEnclosures() {
        return driveEnclosures;
    }

    /**
     * @param driveEnclosures the driveEnclosures to set
     */
    public void setDriveEnclosures(List<DriveEnclosureFirmware> driveEnclosures) {
        this.driveEnclosures = driveEnclosures;
    }

    /**
     * @return the driveSelectedFw
     */
    public String getDriveSelectedFw() {
        return driveSelectedFw;
    }

    /**
     * @param driveSelectedFw the driveSelectedFw to set
     */
    public void setDriveSelectedFw(String driveSelectedFw) {
        this.driveSelectedFw = driveSelectedFw;
    }

    /**
     * @return the force
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * @return the fwActivationMode
     */
    public SasLiFirmwareActivationMode getFwActivationMode() {
        return fwActivationMode;
    }

    /**
     * @param fwActivationMode the fwActivationMode to set
     */
    public void setFwActivationMode(SasLiFirmwareActivationMode fwActivationMode) {
        this.fwActivationMode = fwActivationMode;
    }

    /**
     * @return the fwBaseline
     */
    public String getFwBaseline() {
        return fwBaseline;
    }

    /**
     * @param fwBaseline the fwBaseline to set
     */
    public void setFwBaseline(String fwBaseline) {
        this.fwBaseline = fwBaseline;
    }

    /**
     * @return the interconnects
     */
    public List<SasPhysicalInterconnectFirmware> getInterconnects() {
        return interconnects;
    }

    /**
     * @param interconnects the interconnects to set
     */
    public void setInterconnects(List<SasPhysicalInterconnectFirmware> interconnects) {
        this.interconnects = interconnects;
    }

    /**
     * @return the logicalSwitchId
     */
    public String getLogicalSwitchId() {
        return logicalSwitchId;
    }

    /**
     * @param logicalSwitchId the logicalSwitchId to set
     */
    public void setLogicalSwitchId(String logicalSwitchId) {
        this.logicalSwitchId = logicalSwitchId;
    }

    /**
     * @return the sppName
     */
    public String getSppName() {
        return sppName;
    }

    /**
     * @param sppName the sppName to set
     */
    public void setSppName(String sppName) {
        this.sppName = sppName;
    }

    /**
     * @return the sppUri
     */
    public String getSppUri() {
        return sppUri;
    }

    /**
     * @param sppUri the sppUri to set
     */
    public void setSppUri(String sppUri) {
        this.sppUri = sppUri;
    }

    /**
     * @return the state
     */
    public LogicalSasSwitchFirmwareState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(LogicalSasSwitchFirmwareState state) {
        this.state = state;
    }

    /**
     * @return the switchSelectedFw
     */
    public String getSwitchSelectedFw() {
        return switchSelectedFw;
    }

    /**
     * @param switchSelectedFw the switchSelectedFw to set
     */
    public void setSwitchSelectedFw(String switchSelectedFw) {
        this.switchSelectedFw = switchSelectedFw;
    }

    /**
     * @return the validationType
     */
    public LIFirmwareValidationType getValidationType() {
        return validationType;
    }

    /**
     * @param validationType the validationType to set
     */
    public void setValidationType(LIFirmwareValidationType validationType) {
        this.validationType = validationType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
