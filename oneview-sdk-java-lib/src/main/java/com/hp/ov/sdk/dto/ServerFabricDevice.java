/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(Integer deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public ServerFabricDeviceLocation getLocation() {
        return location;
    }

    public void setLocation(ServerFabricDeviceLocation location) {
        this.location = location;
    }

    public Integer getOaSlotNumber() {
        return oaSlotNumber;
    }

    public void setOaSlotNumber(Integer oaSlotNumber) {
        this.oaSlotNumber = oaSlotNumber;
    }

    public List<ServerFabricPhysicalPort> getPhysicalPorts() {
        return physicalPorts;
    }

    public void setPhysicalPorts(List<ServerFabricPhysicalPort> physicalPorts) {
        this.physicalPorts = physicalPorts;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }
}
