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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;

public class PhysicalPort {

    private Integer mapping;
    private Integer maxSpeedMbps;
    @Since(200)
    private Integer maxVFsSupported;
    private Integer number;
    private Integer physicalFunctionCount;
    private String type;

    public Integer getMapping() {
        return mapping;
    }

    public void setMapping(Integer mapping) {
        this.mapping = mapping;
    }

    public Integer getMaxSpeedMbps() {
        return maxSpeedMbps;
    }

    public void setMaxSpeedMbps(Integer maxSpeedMbps) {
        this.maxSpeedMbps = maxSpeedMbps;
    }

    public Integer getMaxVFsSupported() {
        return maxVFsSupported;
    }

    public void setMaxVFsSupported(Integer maxVFsSupported) {
        this.maxVFsSupported = maxVFsSupported;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPhysicalFunctionCount() {
        return physicalFunctionCount;
    }

    public void setPhysicalFunctionCount(Integer physicalFunctionCount) {
        this.physicalFunctionCount = physicalFunctionCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof PhysicalPort) {
            PhysicalPort that = (PhysicalPort) obj;

            return new EqualsBuilder()
                    .append(mapping, that.mapping)
                    .append(maxSpeedMbps, that.maxSpeedMbps)
                    .append(maxVFsSupported, that.maxVFsSupported)
                    .append(number, that.number)
                    .append(physicalFunctionCount, that.physicalFunctionCount)
                    .append(type, that.type)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(mapping)
                .append(maxSpeedMbps)
                .append(maxVFsSupported)
                .append(number)
                .append(physicalFunctionCount)
                .append(type)
                .toHashCode();
    }
}
