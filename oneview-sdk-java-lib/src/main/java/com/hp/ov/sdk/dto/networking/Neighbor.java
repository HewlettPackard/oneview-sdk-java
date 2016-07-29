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

package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;


public final class Neighbor implements Serializable {

    private static final long serialVersionUID = 3690264515818754364L;

    @Since(300)
    private String linkLabel;
    @Since(300)
    private String linkUri;
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

    /**
     * @return the linkLabel
     */
    public String getLinkLabel() {
        return linkLabel;
    }

    /**
     * @param linkLabel the linkLabel to set
     */
    public void setLinkLabel(String linkLabel) {
        this.linkLabel = linkLabel;
    }

    /**
     * @return the linkUri
     */
    public String getLinkUri() {
        return linkUri;
    }

    /**
     * @param linkUri the linkUri to set
     */
    public void setLinkUri(String linkUri) {
        this.linkUri = linkUri;
    }

    /**
     * @return the remoteChassisId
     */
    public String getRemoteChassisId() {
        return remoteChassisId;
    }

    /**
     * @param remoteChassisId the remoteChassisId to set
     */
    public void setRemoteChassisId(String remoteChassisId) {
        this.remoteChassisId = remoteChassisId;
    }

    /**
     * @return the remoteChassisIdType
     */
    public String getRemoteChassisIdType() {
        return remoteChassisIdType;
    }

    /**
     * @param remoteChassisIdType the remoteChassisIdType to set
     */
    public void setRemoteChassisIdType(String remoteChassisIdType) {
        this.remoteChassisIdType = remoteChassisIdType;
    }

    /**
     * @return the remoteMgmtAddress
     */
    public String getRemoteMgmtAddress() {
        return remoteMgmtAddress;
    }

    /**
     * @param remoteMgmtAddress the remoteMgmtAddress to set
     */
    public void setRemoteMgmtAddress(String remoteMgmtAddress) {
        this.remoteMgmtAddress = remoteMgmtAddress;
    }

    /**
     * @return the remoteMgmtAddressType
     */
    public String getRemoteMgmtAddressType() {
        return remoteMgmtAddressType;
    }

    /**
     * @param remoteMgmtAddressType the remoteMgmtAddressType to set
     */
    public void setRemoteMgmtAddressType(String remoteMgmtAddressType) {
        this.remoteMgmtAddressType = remoteMgmtAddressType;
    }

    /**
     * @return the remotePortDescription
     */
    public String getRemotePortDescription() {
        return remotePortDescription;
    }

    /**
     * @param remotePortDescription the remotePortDescription to set
     */
    public void setRemotePortDescription(String remotePortDescription) {
        this.remotePortDescription = remotePortDescription;
    }

    /**
     * @return the remotePortId
     */
    public String getRemotePortId() {
        return remotePortId;
    }

    /**
     * @param remotePortId the remotePortId to set
     */
    public void setRemotePortId(String remotePortId) {
        this.remotePortId = remotePortId;
    }

    /**
     * @return the remotePortIdType
     */
    public String getRemotePortIdType() {
        return remotePortIdType;
    }

    /**
     * @param remotePortIdType the remotePortIdType to set
     */
    public void setRemotePortIdType(String remotePortIdType) {
        this.remotePortIdType = remotePortIdType;
    }

    /**
     * @return the remoteSystemCapabilities
     */
    public String getRemoteSystemCapabilities() {
        return remoteSystemCapabilities;
    }

    /**
     * @param remoteSystemCapabilities the remoteSystemCapabilities to set
     */
    public void setRemoteSystemCapabilities(String remoteSystemCapabilities) {
        this.remoteSystemCapabilities = remoteSystemCapabilities;
    }

    /**
     * @return the remoteSystemDescription
     */
    public String getRemoteSystemDescription() {
        return remoteSystemDescription;
    }

    /**
     * @param remoteSystemDescription the remoteSystemDescription to set
     */
    public void setRemoteSystemDescription(String remoteSystemDescription) {
        this.remoteSystemDescription = remoteSystemDescription;
    }

    /**
     * @return the remoteSystemName
     */
    public String getRemoteSystemName() {
        return remoteSystemName;
    }

    /**
     * @param remoteSystemName the remoteSystemName to set
     */
    public void setRemoteSystemName(String remoteSystemName) {
        this.remoteSystemName = remoteSystemName;
    }

    /**
     * @return the remoteType
     */
    public String getRemoteType() {
        return remoteType;
    }

    /**
     * @param remoteType the remoteType to set
     */
    public void setRemoteType(String remoteType) {
        this.remoteType = remoteType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Neighbor neighbor = (Neighbor) obj;

        return new EqualsBuilder()
                .append(linkLabel, neighbor.linkLabel)
                .append(linkUri, neighbor.linkUri)
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
                .append(linkLabel)
                .append(linkUri)
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
        return ToStringBuilder.reflectionToString(this);
    }

}
