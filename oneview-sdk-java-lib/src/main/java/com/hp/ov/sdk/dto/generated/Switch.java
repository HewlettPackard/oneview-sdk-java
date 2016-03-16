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
package com.hp.ov.sdk.dto.generated;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.Location;

/**
 * The Switch data transfer object (DTO) contains the information used to
 * represent a switch in the system. It is passed in to the add/update switch
 * REST api, as well as the add/update server hardware through java client api.
 */
public final class Switch extends BaseModelResource {

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
    private String serialNumber;
    @Since(200)
    private Location switchLocation;
    private SwitchManagementConnection switchManagementConnection;

    public String getChassisId() {
        return chassisId;
    }

    public void setChassisId(String chassisId) {
        this.chassisId = chassisId;
    }

    public EnvironmentalConfiguration getEnvironmentalConfiguration() {
        return environmentalConfiguration;
    }

    public void setEnvironmentalConfiguration(EnvironmentalConfiguration environmentalConfiguration) {
        this.environmentalConfiguration = environmentalConfiguration;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getLogicalSwitchUri() {
        return logicalSwitchUri;
    }

    public void setLogicalSwitchUri(String logicalSwitchUri) {
        this.logicalSwitchUri = logicalSwitchUri;
    }

    public LogicalSwitchVpc getLogicalSwitchVPC() {
        return logicalSwitchVPC;
    }

    public void setLogicalSwitchVPC(LogicalSwitchVpc logicalSwitchVPC) {
        this.logicalSwitchVPC = logicalSwitchVPC;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public List<Port> getPorts() {
        return ports;
    }

    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    public List<SwitchRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SwitchRole> roles) {
        this.roles = roles;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Location getSwitchLocation() {
        return switchLocation;
    }

    public void setSwitchLocation(Location switchLocation) {
        this.switchLocation = switchLocation;
    }

    public SwitchManagementConnection getSwitchManagementConnection() {
        return switchManagementConnection;
    }

    public void setSwitchManagementConnection(SwitchManagementConnection switchManagementConnection) {
        this.switchManagementConnection = switchManagementConnection;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Switch aSwitch = (Switch) obj;

        return new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(chassisId, aSwitch.chassisId)
                .append(environmentalConfiguration, aSwitch.environmentalConfiguration)
                .append(firmwareVersion, aSwitch.firmwareVersion)
                .append(logicalSwitchUri, aSwitch.logicalSwitchUri)
                .append(logicalSwitchVPC, aSwitch.logicalSwitchVPC)
                .append(modelName, aSwitch.modelName)
                .append(ports, aSwitch.ports)
                .append(roles, aSwitch.roles)
                .append(serialNumber, aSwitch.serialNumber)
                .append(switchLocation, aSwitch.switchLocation)
                .append(switchManagementConnection, aSwitch.switchManagementConnection)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(chassisId)
                .append(environmentalConfiguration)
                .append(firmwareVersion)
                .append(logicalSwitchUri)
                .append(logicalSwitchVPC)
                .append(modelName)
                .append(ports)
                .append(roles)
                .append(serialNumber)
                .append(switchLocation)
                .append(switchManagementConnection)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("chassisId", chassisId)
                .append("environmentalConfiguration", environmentalConfiguration)
                .append("firmwareVersion", firmwareVersion)
                .append("logicalSwitchUri", logicalSwitchUri)
                .append("logicalSwitchVPC", logicalSwitchVPC)
                .append("modelName", modelName)
                .append("ports", ports)
                .append("roles", roles)
                .append("serialNumber", serialNumber)
                .append("switchLocation", switchLocation)
                .append("switchManagementConnection", switchManagementConnection)
                .toString();
    }
}
