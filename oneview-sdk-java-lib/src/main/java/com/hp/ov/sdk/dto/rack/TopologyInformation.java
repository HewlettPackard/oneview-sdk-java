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

package com.hp.ov.sdk.dto.rack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.facilities.powerdeliverydevice.PowerDeliveryDevice;

public class TopologyInformation {

    private Integer capacity;
    private Rack device;
    private Boolean inferredPosition;
    private Integer max;
    private Integer peakTemp;
    private Integer peakTempTime;
    private Map<Integer, PowerSupply> unconnectedPowerSupplies = new HashMap<>();
    private List<PowerDeliveryDevice> powerDistributors = new ArrayList<>();
    private List<TopologyInformation> children = new ArrayList<>();
    private Integer height;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Rack getDevice() {
        return device;
    }

    public void setDevice(Rack device) {
        this.device = device;
    }

    public Boolean getInferredPosition() {
        return inferredPosition;
    }

    public void setInferredPosition(Boolean inferredPosition) {
        this.inferredPosition = inferredPosition;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getPeakTemp() {
        return peakTemp;
    }

    public void setPeakTemp(Integer peakTemp) {
        this.peakTemp = peakTemp;
    }

    public Integer getPeakTempTime() {
        return peakTempTime;
    }

    public void setPeakTempTime(Integer peakTempTime) {
        this.peakTempTime = peakTempTime;
    }

    public Map<Integer, PowerSupply> getUnconnectedPowerSupplies() {
        return unconnectedPowerSupplies;
    }

    public void setUnconnectedPowerSupplies(Map<Integer, PowerSupply> unconnectedPowerSupplies) {
        this.unconnectedPowerSupplies = unconnectedPowerSupplies;
    }

    public List<PowerDeliveryDevice> getPowerDistributors() {
        return powerDistributors;
    }

    public void setPowerDistributors(List<PowerDeliveryDevice> powerDistributors) {
        this.powerDistributors = powerDistributors;
    }

    public List<TopologyInformation> getChildren() {
        return children;
    }

    public void setChildren(List<TopologyInformation> children) {
        this.children = children;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof TopologyInformation) {
            TopologyInformation that = (TopologyInformation) obj;

            return new EqualsBuilder()
                    .append(capacity, that.capacity)
                    .append(device, that.device)
                    .append(inferredPosition, that.inferredPosition)
                    .append(max, that.max)
                    .append(peakTemp, that.peakTemp)
                    .append(peakTempTime, that.peakTempTime)
                    .append(unconnectedPowerSupplies, that.unconnectedPowerSupplies)
                    .append(powerDistributors, that.powerDistributors)
                    .append(children, that.children)
                    .append(height, that.height)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(capacity)
                .append(device)
                .append(inferredPosition)
                .append(max)
                .append(peakTemp)
                .append(peakTempTime)
                .append(unconnectedPowerSupplies)
                .append(powerDistributors)
                .append(children)
                .append(height)
                .toHashCode();
    }
}
