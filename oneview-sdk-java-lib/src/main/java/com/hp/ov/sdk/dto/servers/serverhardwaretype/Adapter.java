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

package com.hp.ov.sdk.dto.servers.serverhardwaretype;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.ServerFabricDeviceLocation;

public class Adapter {

    private List<String> capabilities;
    @Since(200)
    private Integer deviceNumber;
    private ServerFabricDeviceLocation location;
    @Since(200)
    private Integer maxVFsSupported;
    @Since(200)
    private Integer minVFsIncrement;
    private String model;
    @Until(199)
    private Integer oaSlotNumber;
    private List<PhysicalPort> ports;
    private Integer slot;

    public List<String> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
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

    public Integer getMaxVFsSupported() {
        return maxVFsSupported;
    }

    public void setMaxVFsSupported(Integer maxVFsSupported) {
        this.maxVFsSupported = maxVFsSupported;
    }

    public Integer getMinVFsIncrement() {
        return minVFsIncrement;
    }

    public void setMinVFsIncrement(Integer minVFsIncrement) {
        this.minVFsIncrement = minVFsIncrement;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getOaSlotNumber() {
        return oaSlotNumber;
    }

    public void setOaSlotNumber(Integer oaSlotNumber) {
        this.oaSlotNumber = oaSlotNumber;
    }

    public List<PhysicalPort> getPorts() {
        return ports;
    }

    public void setPorts(List<PhysicalPort> ports) {
        this.ports = ports;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Adapter) {
            Adapter adapter = (Adapter) obj;

            return new EqualsBuilder()
                    .append(capabilities, adapter.capabilities)
                    .append(deviceNumber, adapter.deviceNumber)
                    .append(location, adapter.location)
                    .append(maxVFsSupported, adapter.maxVFsSupported)
                    .append(minVFsIncrement, adapter.minVFsIncrement)
                    .append(model, adapter.model)
                    .append(oaSlotNumber, adapter.oaSlotNumber)
                    .append(ports, adapter.ports)
                    .append(slot, adapter.slot)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(capabilities)
                .append(deviceNumber)
                .append(location)
                .append(maxVFsSupported)
                .append(minVFsIncrement)
                .append(model)
                .append(oaSlotNumber)
                .append(ports)
                .append(slot)
                .toHashCode();
    }
}
