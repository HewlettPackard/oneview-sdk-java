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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ManagementModule implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firmwareVersion;
    private String hostName;
    private String id;
    private String ipv4Address;
    private String ipv6Address;
    private String model;
    private String name;
    private String partNumber;
    private String serialNumber;
    private ManagementModuleState state;

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
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ipv4Address
     */
    public String getIpv4Address() {
        return ipv4Address;
    }

    /**
     * @param ipv4Address the ipv4Address to set
     */
    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    /**
     * @return the ipv6Address
     */
    public String getIpv6Address() {
        return ipv6Address;
    }

    /**
     * @param ipv6Address the ipv6Address to set
     */
    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
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
     * @return the state
     */
    public ManagementModuleState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(ManagementModuleState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        ManagementModule that = (ManagementModule) obj;

        return new EqualsBuilder()
                .append(firmwareVersion, that.firmwareVersion)
                .append(hostName, that.hostName)
                .append(id, that.id)
                .append(ipv4Address, that.ipv4Address)
                .append(ipv6Address, that.ipv6Address)
                .append(model, that.model)
                .append(name, that.name)
                .append(partNumber, that.partNumber)
                .append(serialNumber, that.serialNumber)
                .append(state, that.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firmwareVersion)
                .append(hostName)
                .append(id)
                .append(ipv4Address)
                .append(ipv6Address)
                .append(model)
                .append(name)
                .append(partNumber)
                .append(serialNumber)
                .append(state)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firmwareVersion", firmwareVersion)
                .append("hostName", hostName)
                .append("id", id)
                .append("ipv4Address", ipv4Address)
                .append("ipv6Address", ipv6Address)
                .append("model", model)
                .append("name", name)
                .append("partNumber", partNumber)
                .append("serialNumber", serialNumber)
                .append("state", state)
                .toString();
    }

}
