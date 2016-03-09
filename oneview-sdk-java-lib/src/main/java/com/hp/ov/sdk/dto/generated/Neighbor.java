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

package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public final class Neighbor implements Serializable {

    private static final long serialVersionUID = 3690264515818754364L;

    private String remoteChassisId;
    private String remoteChassisIdType;
    private String remoteMgmtAddress;
    private String remoteMgmtAddressType;
    private String remotePortDescription;
    private String remotePortId;
    private String remotePortIdType;
    private String remoteSystemCapabilities;
    private String remoteSystemDescription;
    private String remoteSystemName;
    private String remoteType;

    public String getRemoteChassisId() {
        return remoteChassisId;
    }

    public void setRemoteChassisId(final String remoteChassisId) {
        this.remoteChassisId = remoteChassisId;
    }

    public String getRemoteChassisIdType() {
        return remoteChassisIdType;
    }

    public void setRemoteChassisIdType(final String remoteChassisIdType) {
        this.remoteChassisIdType = remoteChassisIdType;
    }

    public String getRemoteMgmtAddress() {
        return remoteMgmtAddress;
    }

    public void setRemoteMgmtAddress(final String remoteMgmtAddress) {
        this.remoteMgmtAddress = remoteMgmtAddress;
    }

    public String getRemoteMgmtAddressType() {
        return remoteMgmtAddressType;
    }

    public void setRemoteMgmtAddressType(final String remoteMgmtAddressType) {
        this.remoteMgmtAddressType = remoteMgmtAddressType;
    }

    public String getRemotePortDescription() {
        return remotePortDescription;
    }

    public void setRemotePortDescription(final String remotePortDescription) {
        this.remotePortDescription = remotePortDescription;
    }

    public String getRemotePortId() {
        return remotePortId;
    }

    public void setRemotePortId(final String remotePortId) {
        this.remotePortId = remotePortId;
    }

    public String getRemotePortIdType() {
        return remotePortIdType;
    }

    public void setRemotePortIdType(final String remotePortIdType) {
        this.remotePortIdType = remotePortIdType;
    }

    public String getRemoteSystemCapabilities() {
        return remoteSystemCapabilities;
    }

    public void setRemoteSystemCapabilities(final String remoteSystemCapabilities) {
        this.remoteSystemCapabilities = remoteSystemCapabilities;
    }

    public String getRemoteSystemDescription() {
        return remoteSystemDescription;
    }

    public void setRemoteSystemDescription(final String remoteSystemDescription) {
        this.remoteSystemDescription = remoteSystemDescription;
    }

    public String getRemoteSystemName() {
        return remoteSystemName;
    }

    public void setRemoteSystemName(final String remoteSystemName) {
        this.remoteSystemName = remoteSystemName;
    }

    public String getRemoteType() {
        return remoteType;
    }

    public void setRemoteType(final String remoteType) {
        this.remoteType = remoteType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Neighbor neighbor = (Neighbor) obj;

        return new EqualsBuilder()
                .append(remoteChassisId, neighbor.remoteChassisId)
                .append(remoteChassisIdType, neighbor.remoteChassisIdType)
                .append(remoteMgmtAddress, neighbor.remoteMgmtAddress)
                .append(remoteMgmtAddressType, neighbor.remoteMgmtAddressType)
                .append(remotePortDescription, neighbor.remotePortDescription)
                .append(remotePortId, neighbor.remotePortId)
                .append(remotePortIdType, neighbor.remotePortIdType)
                .append(remoteSystemCapabilities, neighbor.remoteSystemCapabilities)
                .append(remoteSystemDescription, neighbor.remoteSystemDescription)
                .append(remoteSystemName, neighbor.remoteSystemName)
                .append(remoteType, neighbor.remoteType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(remoteChassisId)
                .append(remoteChassisIdType)
                .append(remoteMgmtAddress)
                .append(remoteMgmtAddressType)
                .append(remotePortDescription)
                .append(remotePortId)
                .append(remotePortIdType)
                .append(remoteSystemCapabilities)
                .append(remoteSystemDescription)
                .append(remoteSystemName)
                .append(remoteType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("remoteChassisId", remoteChassisId)
                .append("remoteChassisIdType", remoteChassisIdType)
                .append("remoteMgmtAddress", remoteMgmtAddress)
                .append("remoteMgmtAddressType", remoteMgmtAddressType)
                .append("remotePortDescription", remotePortDescription)
                .append("remotePortId", remotePortId)
                .append("remotePortIdType", remotePortIdType)
                .append("remoteSystemCapabilities", remoteSystemCapabilities)
                .append("remoteSystemDescription", remoteSystemDescription)
                .append("remoteSystemName", remoteSystemName)
                .append("remoteType", remoteType)
                .toString();
    }

}
