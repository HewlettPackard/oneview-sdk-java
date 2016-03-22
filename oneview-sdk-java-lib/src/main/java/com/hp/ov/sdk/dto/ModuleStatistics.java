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

package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ModuleStatistics implements Serializable {

    private static final long serialVersionUID = 3747272367091068449L;

    private String cpuUsage;
    private String memoryUsage;

    /**
     * This field has a special treatment when serialization/deserialization occurs
     *
     * @see com.hp.ov.sdk.adaptors.PortTelemetrySerializationAdapter
     */
    private PortTelemetry portTelemetry;
    private Integer portTelemetryEntryCount;
    private Integer portTelemetryPeriod;
    private String type;

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public PortTelemetry getPortTelemetry() {
        return portTelemetry;
    }

    public void setPortTelemetry(PortTelemetry portTelemetry) {
        this.portTelemetry = portTelemetry;
    }

    public Integer getPortTelemetryEntryCount() {
        return portTelemetryEntryCount;
    }

    public void setPortTelemetryEntryCount(Integer portTelemetryEntryCount) {
        this.portTelemetryEntryCount = portTelemetryEntryCount;
    }

    public Integer getPortTelemetryPeriod() {
        return portTelemetryPeriod;
    }

    public void setPortTelemetryPeriod(Integer portTelemetryPeriod) {
        this.portTelemetryPeriod = portTelemetryPeriod;
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

        if (obj == null || getClass() != obj.getClass()) return false;

        ModuleStatistics that = (ModuleStatistics) obj;

        return new EqualsBuilder()
                .append(cpuUsage, that.cpuUsage)
                .append(memoryUsage, that.memoryUsage)
                .append(portTelemetry, that.portTelemetry)
                .append(portTelemetryEntryCount, that.portTelemetryEntryCount)
                .append(portTelemetryPeriod, that.portTelemetryPeriod)
                .append(type, that.type)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(cpuUsage)
                .append(memoryUsage)
                .append(portTelemetry)
                .append(portTelemetryEntryCount)
                .append(portTelemetryPeriod)
                .append(type)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("cpuUsage", cpuUsage)
                .append("memoryUsage", memoryUsage)
                .append("portTelemetry", portTelemetry)
                .append("portTelemetryEntryCount", portTelemetryEntryCount)
                .append("portTelemetryPeriod", portTelemetryPeriod)
                .append("type", type)
                .toString();
    }
}
