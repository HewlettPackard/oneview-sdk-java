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

package com.hp.ov.sdk.dto.datacenter;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class DataCenter extends BaseModelResource {

    private List<PhysicalLocation> contents;
    private Integer coolingCapacity;
    private Double coolingMultiplier;
    private Double costPerKilowattHour;
    private String currency;
    private Integer defaultPowerLineVoltage;
    private Integer depth;
    private Double deratingPercentage;
    private DeratingType deratingType;
    private String id;
    private String uuid;
    private Integer width;

    public List<PhysicalLocation> getContents() {
        return contents;
    }

    public void setContents(List<PhysicalLocation> contents) {
        this.contents = contents;
    }

    public Integer getCoolingCapacity() {
        return coolingCapacity;
    }

    public void setCoolingCapacity(Integer coolingCapacity) {
        this.coolingCapacity = coolingCapacity;
    }

    public Double getCoolingMultiplier() {
        return coolingMultiplier;
    }

    public void setCoolingMultiplier(Double coolingMultiplier) {
        this.coolingMultiplier = coolingMultiplier;
    }

    public Double getCostPerKilowattHour() {
        return costPerKilowattHour;
    }

    public void setCostPerKilowattHour(Double costPerKilowattHour) {
        this.costPerKilowattHour = costPerKilowattHour;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getDefaultPowerLineVoltage() {
        return defaultPowerLineVoltage;
    }

    public void setDefaultPowerLineVoltage(Integer defaultPowerLineVoltage) {
        this.defaultPowerLineVoltage = defaultPowerLineVoltage;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Double getDeratingPercentage() {
        return deratingPercentage;
    }

    public void setDeratingPercentage(Double deratingPercentage) {
        this.deratingPercentage = deratingPercentage;
    }

    public DeratingType getDeratingType() {
        return deratingType;
    }

    public void setDeratingType(DeratingType deratingType) {
        this.deratingType = deratingType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof DataCenter);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof DataCenter) {
            DataCenter that = (DataCenter) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(contents, that.contents)
                    .append(coolingCapacity, that.coolingCapacity)
                    .append(coolingMultiplier, that.coolingMultiplier)
                    .append(costPerKilowattHour, that.costPerKilowattHour)
                    .append(currency, that.currency)
                    .append(defaultPowerLineVoltage, that.defaultPowerLineVoltage)
                    .append(depth, that.depth)
                    .append(deratingPercentage, that.deratingPercentage)
                    .append(deratingType, that.deratingType)
                    .append(id, that.id)
                    .append(uuid, that.uuid)
                    .append(width, that.width)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(contents)
                .append(coolingCapacity)
                .append(coolingMultiplier)
                .append(costPerKilowattHour)
                .append(currency)
                .append(defaultPowerLineVoltage)
                .append(depth)
                .append(deratingPercentage)
                .append(deratingType)
                .append(id)
                .append(uuid)
                .append(width)
                .toHashCode();
    }
}
