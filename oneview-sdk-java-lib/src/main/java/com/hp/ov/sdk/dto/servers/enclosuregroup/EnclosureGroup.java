/*
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
 */
package com.hp.ov.sdk.dto.servers.enclosuregroup;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.StackingMode;
import com.hp.ov.sdk.dto.generated.InterconnectBayMapping;
import com.hp.ov.sdk.dto.generated.PortMapping;

/**
 * The EnclosureGroup data transfer object (DTO) contains the information used
 * to represent a enclosure group in the system. It is used to pass details
 * about the LIG that the enclosure group uses while adding an enclosure. It is
 * passed to the add/update enclosureGroup REST api, as well as the add/update
 * enclosureGroup through java client api.
 */

public class EnclosureGroup extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    @Since(200)
    private List<String> associatedLogicalInterconnectGroups = new ArrayList<String>();
    @Since(200)
    private Integer enclosureCount;
    @Since(200)
    private String enclosureTypeUri;
    @Since(300)
    private IpAddressingMode ipAddressingMode;
    @Since(200)
    private List<String> ipRangeUris = new ArrayList<String>();
    private Integer interconnectBayMappingCount;
    private List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
    @Since(300)
    private OSDeploymentSettings osDeploymentSettings;
    private Integer portMappingCount;
    private List<PortMapping> portMappings = new ArrayList<PortMapping>();
    private PowerMode powerMode;
    private StackingMode stackingMode;

    /**
     * @return the associatedLogicalInterconnectGroups
     */
    public List<String> getAssociatedLogicalInterconnectGroups() {
        return associatedLogicalInterconnectGroups;
    }

    /**
     * @param associatedLogicalInterconnectGroups the associatedLogicalInterconnectGroups to set
     */
    public void setAssociatedLogicalInterconnectGroups(List<String> associatedLogicalInterconnectGroups) {
        this.associatedLogicalInterconnectGroups = associatedLogicalInterconnectGroups;
    }

    /**
     * @return the enclosureCount
     */
    public Integer getEnclosureCount() {
        return enclosureCount;
    }

    /**
     * @param enclosureCount the enclosureCount to set
     */
    public void setEnclosureCount(Integer enclosureCount) {
        this.enclosureCount = enclosureCount;
    }

    /**
     * @return the enclosureTypeUri
     */
    public String getEnclosureTypeUri() {
        return enclosureTypeUri;
    }

    /**
     * @param enclosureTypeUri the enclosureTypeUri to set
     */
    public void setEnclosureTypeUri(String enclosureTypeUri) {
        this.enclosureTypeUri = enclosureTypeUri;
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
     * @return the ipRangeUris
     */
    public List<String> getIpRangeUris() {
        return ipRangeUris;
    }

    /**
     * @param ipRangeUris the ipRangeUris to set
     */
    public void setIpRangeUris(List<String> ipRangeUris) {
        this.ipRangeUris = ipRangeUris;
    }

    /**
     * @return the interconnectBayMappingCount
     */
    public Integer getInterconnectBayMappingCount() {
        return interconnectBayMappingCount;
    }

    /**
     * @param interconnectBayMappingCount the interconnectBayMappingCount to set
     */
    public void setInterconnectBayMappingCount(Integer interconnectBayMappingCount) {
        this.interconnectBayMappingCount = interconnectBayMappingCount;
    }

    /**
     * @return the interconnectBayMappings
     */
    public List<InterconnectBayMapping> getInterconnectBayMappings() {
        return interconnectBayMappings;
    }

    /**
     * @param interconnectBayMappings the interconnectBayMappings to set
     */
    public void setInterconnectBayMappings(List<InterconnectBayMapping> interconnectBayMappings) {
        this.interconnectBayMappings = interconnectBayMappings;
    }

    /**
     * @return the osDeploymentSettings
     */
    public OSDeploymentSettings getOsDeploymentSettings() {
        return osDeploymentSettings;
    }

    /**
     * @param osDeploymentSettings the osDeploymentSettings to set
     */
    public void setOsDeploymentSettings(OSDeploymentSettings osDeploymentSettings) {
        this.osDeploymentSettings = osDeploymentSettings;
    }

    /**
     * @return the portMappingCount
     */
    public Integer getPortMappingCount() {
        return portMappingCount;
    }

    /**
     * @param portMappingCount the portMappingCount to set
     */
    public void setPortMappingCount(Integer portMappingCount) {
        this.portMappingCount = portMappingCount;
    }

    /**
     * @return the portMappings
     */
    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    /**
     * @param portMappings the portMappings to set
     */
    public void setPortMappings(List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    /**
     * @return the powerMode
     */
    public PowerMode getPowerMode() {
        return powerMode;
    }

    /**
     * @param powerMode the powerMode to set
     */
    public void setPowerMode(PowerMode powerMode) {
        this.powerMode = powerMode;
    }

    /**
     * @return the stackingMode
     */
    public StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     * @param stackingMode the stackingMode to set
     */
    public void setStackingMode(StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof EnclosureGroup);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof EnclosureGroup) {
            EnclosureGroup that = (EnclosureGroup) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(associatedLogicalInterconnectGroups, that.associatedLogicalInterconnectGroups)
                    .append(enclosureCount, that.enclosureCount)
                    .append(enclosureTypeUri, that.enclosureTypeUri)
                    .append(ipAddressingMode, that.ipAddressingMode)
                    .append(ipRangeUris, that.ipRangeUris)
                    .append(interconnectBayMappingCount, that.interconnectBayMappingCount)
                    .append(interconnectBayMappings, that.interconnectBayMappings)
                    .append(osDeploymentSettings, that.osDeploymentSettings)
                    .append(portMappingCount, that.portMappingCount)
                    .append(portMappings, that.portMappings)
                    .append(powerMode, that.powerMode)
                    .append(stackingMode, that.stackingMode)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(associatedLogicalInterconnectGroups)
                .append(enclosureCount)
                .append(enclosureTypeUri)
                .append(ipAddressingMode)
                .append(ipRangeUris)
                .append(interconnectBayMappingCount)
                .append(interconnectBayMappings)
                .append(osDeploymentSettings)
                .append(portMappingCount)
                .append(portMappings)
                .append(powerMode)
                .append(stackingMode)
                .toHashCode();
    }
}
