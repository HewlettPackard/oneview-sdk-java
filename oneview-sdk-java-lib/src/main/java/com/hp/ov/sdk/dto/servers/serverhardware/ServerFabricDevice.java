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
package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.servers.ServerFabricDeviceLocation;

public class ServerFabricDevice implements Serializable {

    private static final long serialVersionUID = 542979167339380410L;

    private String deviceName;
    @Since(200)
    private Integer deviceNumber;
    private ServerFabricDeviceLocation location;
    @Until(199)
    private Integer oaSlotNumber;
    private List<ServerFabricPhysicalPort> physicalPorts = new ArrayList<>();
    private Integer slotNumber;

    /**
     * @return the deviceName
     */
    public String getDeviceName() {
        return deviceName;
    }

    /**
     * @param deviceName the deviceName to set
     */
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    /**
     * @return the deviceNumber
     */
    public Integer getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * @param deviceNumber the deviceNumber to set
     */
    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * @return the location
     */
    public ServerFabricDeviceLocation getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(ServerFabricDeviceLocation location) {
        this.location = location;
    }

    /**
     * @return the oaSlotNumber
     */
    public Integer getOaSlotNumber() {
        return oaSlotNumber;
    }

    /**
     * @param oaSlotNumber the oaSlotNumber to set
     */
    public void setOaSlotNumber(Integer oaSlotNumber) {
        this.oaSlotNumber = oaSlotNumber;
    }

    /**
     * @return the physicalPorts
     */
    public List<ServerFabricPhysicalPort> getPhysicalPorts() {
        return physicalPorts;
    }

    /**
     * @param physicalPorts the physicalPorts to set
     */
    public void setPhysicalPorts(List<ServerFabricPhysicalPort> physicalPorts) {
        this.physicalPorts = physicalPorts;
    }

    /**
     * @return the slotNumber
     */
    public Integer getSlotNumber() {
        return slotNumber;
    }

    /**
     * @param slotNumber the slotNumber to set
     */
    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
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
