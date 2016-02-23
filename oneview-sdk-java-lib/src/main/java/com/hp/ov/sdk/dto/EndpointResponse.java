/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import com.google.gson.annotations.Since;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class EndpointResponse implements Serializable {

    private static final long serialVersionUID = -1870619388976992409L;

    private String uri;
    private String type;
    private Integer aliasCount;

    @Since(200)
    private String aliasName;

    private String aliasUri;
    private String category;
    private String created;

    @Since(200)
    private String deviceName;
    @Since(200)
    private String deviceNameUri;
    @Since(200)
    private String devicePortGroupName;
    @Since(200)
    private String devicePortName;

    private String eTag;

    @Since(200)
    private Boolean isOnline;

    private String modified;
    private String portType;
    private String sanName;
    private String sanUri;
    private String wwn;
    private Integer zoneCount;

    @Since(200)
    private String zoneName;

    private String zonesUri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getAliasCount() {
        return aliasCount;
    }

    public void setAliasCount(Integer aliasCount) {
        this.aliasCount = aliasCount;
    }

    public String getAliasName() {
        return aliasName;
    }

    public void setAliasName(String aliasName) {
        this.aliasName = aliasName;
    }

    public String getAliasUri() {
        return aliasUri;
    }

    public void setAliasUri(String aliasUri) {
        this.aliasUri = aliasUri;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceNameUri() {
        return deviceNameUri;
    }

    public void setDeviceNameUri(String deviceNameUri) {
        this.deviceNameUri = deviceNameUri;
    }

    public String getDevicePortGroupName() {
        return devicePortGroupName;
    }

    public void setDevicePortGroupName(String devicePortGroupName) {
        this.devicePortGroupName = devicePortGroupName;
    }

    public String getDevicePortName() {
        return devicePortName;
    }

    public void setDevicePortName(String devicePortName) {
        this.devicePortName = devicePortName;
    }

    public String geteTag() {
        return eTag;
    }

    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public String getSanName() {
        return sanName;
    }

    public void setSanName(String sanName) {
        this.sanName = sanName;
    }

    public String getSanUri() {
        return sanUri;
    }

    public void setSanUri(String sanUri) {
        this.sanUri = sanUri;
    }

    public String getWwn() {
        return wwn;
    }

    public void setWwn(String wwn) {
        this.wwn = wwn;
    }

    public Integer getZoneCount() {
        return zoneCount;
    }

    public void setZoneCount(Integer zoneCount) {
        this.zoneCount = zoneCount;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZonesUri() {
        return zonesUri;
    }

    public void setZonesUri(String zonesUri) {
        this.zonesUri = zonesUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof EndpointResponse) {
            EndpointResponse that = (EndpointResponse) obj;

            return new EqualsBuilder()
                    .append(uri, that.uri)
                    .append(type, that.type)
                    .append(aliasCount, that.aliasCount)
                    .append(aliasName, that.aliasName)
                    .append(aliasUri, that.aliasUri)
                    .append(category, that.category)
                    .append(created, that.created)
                    .append(deviceName, that.deviceName)
                    .append(deviceNameUri, that.deviceNameUri)
                    .append(devicePortGroupName, that.devicePortGroupName)
                    .append(devicePortName, that.devicePortName)
                    .append(eTag, that.eTag)
                    .append(isOnline, that.isOnline)
                    .append(modified, that.modified)
                    .append(portType, that.portType)
                    .append(sanName, that.sanName)
                    .append(sanUri, that.sanUri)
                    .append(wwn, that.wwn)
                    .append(zoneCount, that.zoneCount)
                    .append(zoneName, that.zoneName)
                    .append(zonesUri, that.zonesUri)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uri)
                .append(type)
                .append(aliasCount)
                .append(aliasName)
                .append(aliasUri)
                .append(category)
                .append(created)
                .append(deviceName)
                .append(deviceNameUri)
                .append(devicePortGroupName)
                .append(devicePortName)
                .append(eTag)
                .append(isOnline)
                .append(modified)
                .append(portType)
                .append(sanName)
                .append(sanUri)
                .append(wwn)
                .append(zoneCount)
                .append(zoneName)
                .append(zonesUri)
                .toHashCode();
    }
}
