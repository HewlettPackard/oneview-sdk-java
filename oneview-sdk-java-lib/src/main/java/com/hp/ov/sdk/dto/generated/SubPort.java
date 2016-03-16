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

package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.PortStatus;


public final class SubPort implements Serializable {

    private static final long serialVersionUID = -470998644380158072L;

    private Integer portNumber;
    private PortStatus portStatus;
    private PortStatusReason portStatusReason;

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public PortStatus getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(PortStatus portStatus) {
        this.portStatus = portStatus;
    }

    public PortStatusReason getPortStatusReason() {
        return portStatusReason;
    }

    public void setPortStatusReason(PortStatusReason portStatusReason) {
        this.portStatusReason = portStatusReason;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SubPort subPort = (SubPort) obj;

        return new EqualsBuilder()
                .append(portNumber, subPort.portNumber)
                .append(portStatus, subPort.portStatus)
                .append(portStatusReason, subPort.portStatusReason)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(portNumber)
                .append(portStatus)
                .append(portStatusReason)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("portNumber", portNumber)
                .append("portStatus", portStatus)
                .append("portStatusReason", portStatusReason)
                .toString();
    }
}
