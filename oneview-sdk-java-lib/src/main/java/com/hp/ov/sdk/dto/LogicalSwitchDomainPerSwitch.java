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

public class LogicalSwitchDomainPerSwitch implements Serializable {

    private static final long serialVersionUID = -8499154920028067543L;

    private String firmwareVersion;
    private String ipAddress;
    private String memberId;
    private String model;
    private String state;
    private String status;
    private String uri;
    private String vpcRole;

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getVpcRole() {
        return vpcRole;
    }

    public void setVpcRole(String vpcRole) {
        this.vpcRole = vpcRole;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        LogicalSwitchDomainPerSwitch that = (LogicalSwitchDomainPerSwitch) obj;

        return new EqualsBuilder()
                .append(firmwareVersion, that.firmwareVersion)
                .append(ipAddress, that.ipAddress)
                .append(memberId, that.memberId)
                .append(model, that.model)
                .append(state, that.state)
                .append(status, that.status)
                .append(uri, that.uri)
                .append(vpcRole, that.vpcRole)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(firmwareVersion)
                .append(ipAddress)
                .append(memberId)
                .append(model)
                .append(state)
                .append(status)
                .append(uri)
                .append(vpcRole)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firmwareVersion", firmwareVersion)
                .append("ipAddress", ipAddress)
                .append("memberId", memberId)
                .append("model", model)
                .append("state", state)
                .append("status", status)
                .append("uri", uri)
                .append("vpcRole", vpcRole)
                .toString();
    }
}
