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
package com.hp.ov.sdk.dto.servers.logicalenclosure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.servers.IpAddressingMode;

public class LogicalEnclosure extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Boolean deleteFailed;
    @Since(300)
    private DeploymentManagerSettings deploymentManagerSettings;
    private String enclosureGroupUri;
    private Map<String, LogicalEnclosureContainedEnclosure> enclosures = new HashMap<String, LogicalEnclosureContainedEnclosure>();
    private List<String> enclosureUris = new ArrayList<String>();
    private FirmwareLogicalEnclosure firmware;
    private IpAddressingMode ipAddressingMode;
    private List<Object> ipv4Ranges = new ArrayList<Object>();
    private List<String> logicalInterconnectUris = new ArrayList<String>();
    private Object powerMode;
    @Since(300)
    private ScalingState scalingState;

    /**
     * @return the deleteFailed
     */
    public Boolean getDeleteFailed() {
        return deleteFailed;
    }

    /**
     * @param deleteFailed the deleteFailed to set
     */
    public void setDeleteFailed(Boolean deleteFailed) {
        this.deleteFailed = deleteFailed;
    }

    /**
     * @return the deploymentManagerSettings
     */
    public DeploymentManagerSettings getDeploymentManagerSettings() {
        return deploymentManagerSettings;
    }

    /**
     * @param deploymentManagerSettings the deploymentManagerSettings to set
     */
    public void setDeploymentManagerSettings(DeploymentManagerSettings deploymentManagerSettings) {
        this.deploymentManagerSettings = deploymentManagerSettings;
    }

    /**
     * @return the enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * @param enclosureGroupUri the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return the enclosures
     */
    public Map<String, LogicalEnclosureContainedEnclosure> getEnclosures() {
        return enclosures;
    }

    /**
     * @param enclosures the enclosures to set
     */
    public void setEnclosures(Map<String, LogicalEnclosureContainedEnclosure> enclosures) {
        this.enclosures = enclosures;
    }

    /**
     * @return the enclosureUris
     */
    public List<String> getEnclosureUris() {
        return enclosureUris;
    }

    /**
     * @param enclosureUris the enclosureUris to set
     */
    public void setEnclosureUris(List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }

    /**
     * @return the firmware
     */
    public FirmwareLogicalEnclosure getFirmware() {
        return firmware;
    }

    /**
     * @param firmware the firmware to set
     */
    public void setFirmware(FirmwareLogicalEnclosure firmware) {
        this.firmware = firmware;
    }

    /**
     * @return the ipAddressingMode
     */
    public IpAddressingMode getIpAddressingMode() {
        return ipAddressingMode;
    }

    /**
     * @param ipAddressingMode the ipAddressingMode to set
     */
    public void setIpAddressingMode(IpAddressingMode ipAddressingMode) {
        this.ipAddressingMode = ipAddressingMode;
    }

    /**
     * @return the ipv4Ranges
     */
    public List<Object> getIpv4Ranges() {
        return ipv4Ranges;
    }

    /**
     * @param ipv4Ranges the ipv4Ranges to set
     */
    public void setIpv4Ranges(List<Object> ipv4Ranges) {
        this.ipv4Ranges = ipv4Ranges;
    }

    /**
     * @return the logicalInterconnectUris
     */
    public List<String> getLogicalInterconnectUris() {
        return logicalInterconnectUris;
    }

    /**
     * @param logicalInterconnectUris the logicalInterconnectUris to set
     */
    public void setLogicalInterconnectUris(List<String> logicalInterconnectUris) {
        this.logicalInterconnectUris = logicalInterconnectUris;
    }

    /**
     * @return the powerMode
     */
    public Object getPowerMode() {
        return powerMode;
    }

    /**
     * @param powerMode the powerMode to set
     */
    public void setPowerMode(Object powerMode) {
        this.powerMode = powerMode;
    }

    /**
     * @return the scalingState
     */
    public ScalingState getScalingState() {
        return scalingState;
    }

    /**
     * @param scalingState the scalingState to set
     */
    public void setScalingState(ScalingState scalingState) {
        this.scalingState = scalingState;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(deleteFailed)
                .append(deploymentManagerSettings)
                .append(enclosureGroupUri)
                .append(enclosures)
                .append(enclosureUris)
                .append(firmware)
                .append(ipAddressingMode)
                .append(ipv4Ranges)
                .append(logicalInterconnectUris)
                .append(powerMode)
                .append(scalingState)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof LogicalEnclosure) == false) {
            return false;
        }
        final LogicalEnclosure rhs = ((LogicalEnclosure) other);
        return new EqualsBuilder()
                .appendSuper(super.equals(other))
                .append(deleteFailed, rhs.deleteFailed)
                .append(deploymentManagerSettings, rhs.deploymentManagerSettings)
                .append(enclosureGroupUri, rhs.enclosureGroupUri)
                .append(enclosures, rhs.enclosures)
                .append(enclosureUris, rhs.enclosureUris)
                .append(firmware, rhs.firmware)
                .append(ipAddressingMode, rhs.ipAddressingMode)
                .append(ipv4Ranges, rhs.ipv4Ranges)
                .append(logicalInterconnectUris, rhs.logicalInterconnectUris)
                .append(powerMode, rhs.powerMode)
                .append(scalingState, rhs.scalingState)
                .isEquals();
    }
}
