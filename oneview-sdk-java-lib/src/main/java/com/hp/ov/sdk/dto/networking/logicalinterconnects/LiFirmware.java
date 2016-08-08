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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;

public class LiFirmware implements Serializable {

    private static final long serialVersionUID = 1L;

    private Command command;
    @Since(200)
    private Integer ethernetActivationDelay;
    @Since(200)
    private LIFirmwareActivationType ethernetActivationType;
    @Since(200)
    private Integer fcActivationDelay;
    @Since(200)
    private LIFirmwareActivationType fcActivationType;
    private Boolean force;
    private String fwBaseline;
    private List<PhysicalInterconnectFirmware> interconnects = new ArrayList<PhysicalInterconnectFirmware>();
    @Since(200)
    private String logicalSwitchId;
    private String sppName;
    private String sppUri;
    private LsFwStateEnum state;
    @Since(300)
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
     * @return the ethernetActivationDelay
     */
    public Integer getEthernetActivationDelay() {
        return ethernetActivationDelay;
    }

    /**
     * @param ethernetActivationDelay the ethernetActivationDelay to set
     */
    public void setEthernetActivationDelay(Integer ethernetActivationDelay) {
        this.ethernetActivationDelay = ethernetActivationDelay;
    }

    /**
     * @return the ethernetActivationType
     */
    public LIFirmwareActivationType getEthernetActivationType() {
        return ethernetActivationType;
    }

    /**
     * @param ethernetActivationType the ethernetActivationType to set
     */
    public void setEthernetActivationType(LIFirmwareActivationType ethernetActivationType) {
        this.ethernetActivationType = ethernetActivationType;
    }

    /**
     * @return the fcActivationDelay
     */
    public Integer getFcActivationDelay() {
        return fcActivationDelay;
    }

    /**
     * @param fcActivationDelay the fcActivationDelay to set
     */
    public void setFcActivationDelay(Integer fcActivationDelay) {
        this.fcActivationDelay = fcActivationDelay;
    }

    /**
     * @return the fcActivationType
     */
    public LIFirmwareActivationType getFcActivationType() {
        return fcActivationType;
    }

    /**
     * @param fcActivationType the fcActivationType to set
     */
    public void setFcActivationType(LIFirmwareActivationType fcActivationType) {
        this.fcActivationType = fcActivationType;
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
    public List<PhysicalInterconnectFirmware> getInterconnects() {
        return interconnects;
    }

    /**
     * @param interconnects the interconnects to set
     */
    public void setInterconnects(List<PhysicalInterconnectFirmware> interconnects) {
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
    public LsFwStateEnum getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(LsFwStateEnum state) {
        this.state = state;
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
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LiFirmware) == false) {
            return false;
        }
        final LiFirmware rhs = ((LiFirmware) other);
        return new EqualsBuilder()
                .append(command, rhs.command)
                .append(ethernetActivationDelay, rhs.ethernetActivationDelay)
                .append(ethernetActivationType, rhs.ethernetActivationType)
                .append(fcActivationDelay, rhs.fcActivationDelay)
                .append(fcActivationType, rhs.fcActivationType)
                .append(force, rhs.force)
                .append(fwBaseline, rhs.fwBaseline)
                .append(interconnects, rhs.interconnects)
                .append(logicalSwitchId, rhs.logicalSwitchId)
                .append(sppName, rhs.sppName)
                .append(sppUri, rhs.sppUri)
                .append(state, rhs.state)
                .append(validationType, rhs.validationType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(command)
                .append(ethernetActivationDelay)
                .append(ethernetActivationType)
                .append(fcActivationDelay)
                .append(fcActivationType)
                .append(force)
                .append(fwBaseline)
                .append(interconnects)
                .append(logicalSwitchId)
                .append(sppName)
                .append(sppUri)
                .append(state)
                .append(validationType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
