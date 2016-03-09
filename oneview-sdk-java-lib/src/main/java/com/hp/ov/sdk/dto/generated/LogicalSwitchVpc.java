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

public final class LogicalSwitchVpc implements Serializable {

    private static final long serialVersionUID = -4091594702806094928L;

    private String domainId;
    private String firmware;
    private String ipAddress;
    private String masterMACId;
    private String memberId;
    private String model;
    private VpcPeer peer;
    private String role;
    private String state;

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getFirmware() {
        return firmware;
    }

    public void setFirmware(String firmware) {
        this.firmware = firmware;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMasterMACId() {
        return masterMACId;
    }

    public void setMasterMACId(String masterMACId) {
        this.masterMACId = masterMACId;
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

    public VpcPeer getPeer() {
        return peer;
    }

    public void setPeer(VpcPeer peer) {
        this.peer = peer;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        LogicalSwitchVpc that = (LogicalSwitchVpc) obj;

        return new EqualsBuilder()
                .append(domainId, that.domainId)
                .append(firmware, that.firmware)
                .append(ipAddress, that.ipAddress)
                .append(masterMACId, that.masterMACId)
                .append(memberId, that.memberId)
                .append(model, that.model)
                .append(peer, that.peer)
                .append(role, that.role)
                .append(state, that.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(domainId)
                .append(firmware)
                .append(ipAddress)
                .append(masterMACId)
                .append(memberId)
                .append(model)
                .append(peer)
                .append(role)
                .append(state)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("domainId", domainId)
                .append("firmware", firmware)
                .append("ipAddress", ipAddress)
                .append("masterMACId", masterMACId)
                .append("memberId", memberId)
                .append("model", model)
                .append("peer", peer)
                .append("role", role)
                .append("state", state)
                .toString();
    }
}
