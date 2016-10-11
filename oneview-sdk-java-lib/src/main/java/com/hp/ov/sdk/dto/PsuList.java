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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.rack.PowerSupplySide;


public class PsuList implements Serializable {

    private static final long serialVersionUID = -9161938174515294930L;

    /**
     * The size of the power supply in Watts.
     */
    private Integer capacity;

    /**
     * The line voltage (input) to the power supply (sampled or nominal).
     */
    private Integer inputVoltage;

    /**
     * The type of input power (AC or DC) of the power supply.
     */
    private PowerSupplyType powerType;

    /**
     * A small integer identifying the slot or bay number of the power supply
     * starting at one (1).
     */
    private Integer psuId;

    /**
     * The logical power delivery grouping of the power supply. In a known
     * non-redundant configuration, all power supplies should be considered on
     * side A. In a known AC-redundant configuration, the 2 sets of redundant
     * power supplies should be identified as side A/B groupings. For an
     * AC_REDUNDNAT C-Class BladeSystem slot 1,2,3 is on Side B (right from the
     * front), and 4,5,6 are on Side A (left from the front). If there is no
     * inherent side-ness, then the side will be Dynamic.
     */
    private PowerSupplySide side;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getInputVoltage() {
        return inputVoltage;
    }

    public void setInputVoltage(final Integer inputVoltage) {
        this.inputVoltage = inputVoltage;
    }

    public PowerSupplyType getPowerType() {
        return powerType;
    }

    public void setPowerType(final PowerSupplyType powerSupplyType) {
        this.powerType = powerSupplyType;
    }

    public Integer getPsuId() {
        return psuId;
    }

    public void setPsuId(final Integer psuId) {
        this.psuId = psuId;
    }

    public PowerSupplySide getSide() {
        return side;
    }

    public void setSide(final PowerSupplySide side) {
        this.side = side;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        PsuList psuList = (PsuList) obj;

        return new EqualsBuilder()
                .append(capacity, psuList.capacity)
                .append(inputVoltage, psuList.inputVoltage)
                .append(powerType, psuList.powerType)
                .append(psuId, psuList.psuId)
                .append(side, psuList.side)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(capacity)
                .append(inputVoltage)
                .append(powerType)
                .append(psuId)
                .append(side)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("capacity", capacity)
                .append("inputVoltage", inputVoltage)
                .append("powerType", powerType)
                .append("psuId", psuId)
                .append("side", side)
                .toString();
    }
}
