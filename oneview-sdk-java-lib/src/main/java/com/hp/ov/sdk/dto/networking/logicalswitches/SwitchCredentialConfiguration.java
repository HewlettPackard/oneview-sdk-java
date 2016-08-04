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

package com.hp.ov.sdk.dto.networking.logicalswitches;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SwitchCredentialConfiguration implements Serializable {

    private static final long serialVersionUID = 5730829410835117574L;

    private String logicalSwitchManagementHost;
    private String logicalSwitchManagementHostIpAddress;
    private String memberId;
    private String snmpPort;
    private SnmpV1Configuration snmpV1Configuration;
    private SnmpV3Configuration snmpV3Configuration;
    private SnmpVersion snmpVersion;
    private String sshUsername;
    private String switchUri;

    public String getLogicalSwitchManagementHost() {
        return logicalSwitchManagementHost;
    }

    public void setLogicalSwitchManagementHost(String logicalSwitchManagementHost) {
        this.logicalSwitchManagementHost = logicalSwitchManagementHost;
    }

    public String getLogicalSwitchManagementHostIpAddress() {
        return logicalSwitchManagementHostIpAddress;
    }

    public void setLogicalSwitchManagementHostIpAddress(String logicalSwitchManagementHostIpAddress) {
        this.logicalSwitchManagementHostIpAddress = logicalSwitchManagementHostIpAddress;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSnmpPort() {
        return snmpPort;
    }

    public void setSnmpPort(String snmpPort) {
        this.snmpPort = snmpPort;
    }

    public SnmpV1Configuration getSnmpV1Configuration() {
        return snmpV1Configuration;
    }

    public void setSnmpV1Configuration(SnmpV1Configuration snmpV1Configuration) {
        this.snmpV1Configuration = snmpV1Configuration;
    }

    public SnmpV3Configuration getSnmpV3Configuration() {
        return snmpV3Configuration;
    }

    public void setSnmpV3Configuration(SnmpV3Configuration snmpV3Configuration) {
        this.snmpV3Configuration = snmpV3Configuration;
    }

    public SnmpVersion getSnmpVersion() {
        return snmpVersion;
    }

    public void setSnmpVersion(SnmpVersion snmpVersion) {
        this.snmpVersion = snmpVersion;
    }

    public String getSshUsername() {
        return sshUsername;
    }

    public void setSshUsername(String sshUsername) {
        this.sshUsername = sshUsername;
    }

    public String getSwitchUri() {
        return switchUri;
    }

    public void setSwitchUri(String switchUri) {
        this.switchUri = switchUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        SwitchCredentialConfiguration that = (SwitchCredentialConfiguration) obj;

        return new EqualsBuilder()
                .append(logicalSwitchManagementHost, that.logicalSwitchManagementHost)
                .append(logicalSwitchManagementHostIpAddress, that.logicalSwitchManagementHostIpAddress)
                .append(memberId, that.memberId)
                .append(snmpPort, that.snmpPort)
                .append(snmpV1Configuration, that.snmpV1Configuration)
                .append(snmpV3Configuration, that.snmpV3Configuration)
                .append(snmpVersion, that.snmpVersion)
                .append(sshUsername, that.sshUsername)
                .append(switchUri, that.switchUri)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(logicalSwitchManagementHost)
                .append(logicalSwitchManagementHostIpAddress)
                .append(memberId)
                .append(snmpPort)
                .append(snmpV1Configuration)
                .append(snmpV3Configuration)
                .append(snmpVersion)
                .append(sshUsername)
                .append(switchUri)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("logicalSwitchManagementHost", logicalSwitchManagementHost)
                .append("logicalSwitchManagementHostIpAddress", logicalSwitchManagementHostIpAddress)
                .append("memberId", memberId)
                .append("snmpPort", snmpPort)
                .append("snmpV1Configuration", snmpV1Configuration)
                .append("snmpV3Configuration", snmpV3Configuration)
                .append("snmpVersion", snmpVersion)
                .append("sshUsername", sshUsername)
                .append("switchUri", switchUri)
                .toString();
    }
}
