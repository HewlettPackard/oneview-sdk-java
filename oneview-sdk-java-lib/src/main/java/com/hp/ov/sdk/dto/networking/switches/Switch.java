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
package com.hp.ov.sdk.dto.networking.switches;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.generated.EnvironmentalConfiguration;
import com.hp.ov.sdk.dto.networking.Port;

/**
 * The Switch data transfer object (DTO) contains the information used to
 * represent a switch in the system. It is passed in to the add/update switch
 * REST api, as well as the add/update server hardware through java client api.
 */
public final class Switch extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String chassisId;
    private EnvironmentalConfiguration environmentalConfiguration;
    private String firmwareVersion;
    @Since(200)
    private String logicalSwitchUri;
    @Since(200)
    private LogicalSwitchVpc logicalSwitchVPC;
    private String modelName;
    @Since(200)
    private List<Port> ports = new ArrayList<>();
    private List<SwitchRole> roles = new ArrayList<>();
    @Since(300)
    private List<String> scopeUris = new ArrayList<>();
    private String serialNumber;
    @Since(200)
    private Location switchLocation;
    private SwitchManagementConnection switchManagementConnection;

    /**
     * @return the chassisId
     */
    public String getChassisId() {
        return chassisId;
    }

    /**
     * @param chassisId the chassisId to set
     */
    public void setChassisId(String chassisId) {
        this.chassisId = chassisId;
    }

    /**
     * @return the environmentalConfiguration
     */
    public EnvironmentalConfiguration getEnvironmentalConfiguration() {
        return environmentalConfiguration;
    }

    /**
     * @param environmentalConfiguration the environmentalConfiguration to set
     */
    public void setEnvironmentalConfiguration(EnvironmentalConfiguration environmentalConfiguration) {
        this.environmentalConfiguration = environmentalConfiguration;
    }

    /**
     * @return the firmwareVersion
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * @param firmwareVersion the firmwareVersion to set
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * @return the logicalSwitchUri
     */
    public String getLogicalSwitchUri() {
        return logicalSwitchUri;
    }

    /**
     * @param logicalSwitchUri the logicalSwitchUri to set
     */
    public void setLogicalSwitchUri(String logicalSwitchUri) {
        this.logicalSwitchUri = logicalSwitchUri;
    }

    /**
     * @return the logicalSwitchVPC
     */
    public LogicalSwitchVpc getLogicalSwitchVPC() {
        return logicalSwitchVPC;
    }

    /**
     * @param logicalSwitchVPC the logicalSwitchVPC to set
     */
    public void setLogicalSwitchVPC(LogicalSwitchVpc logicalSwitchVPC) {
        this.logicalSwitchVPC = logicalSwitchVPC;
    }

    /**
     * @return the modelName
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * @param modelName the modelName to set
     */
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    /**
     * @return the ports
     */
    public List<Port> getPorts() {
        return ports;
    }

    /**
     * @param ports the ports to set
     */
    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    /**
     * @return the roles
     */
    public List<SwitchRole> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<SwitchRole> roles) {
        this.roles = roles;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the switchLocation
     */
    public Location getSwitchLocation() {
        return switchLocation;
    }

    /**
     * @param switchLocation the switchLocation to set
     */
    public void setSwitchLocation(Location switchLocation) {
        this.switchLocation = switchLocation;
    }

    /**
     * @return the switchManagementConnection
     */
    public SwitchManagementConnection getSwitchManagementConnection() {
        return switchManagementConnection;
    }

    /**
     * @param switchManagementConnection the switchManagementConnection to set
     */
    public void setSwitchManagementConnection(SwitchManagementConnection switchManagementConnection) {
        this.switchManagementConnection = switchManagementConnection;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Switch);
    }
    
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
