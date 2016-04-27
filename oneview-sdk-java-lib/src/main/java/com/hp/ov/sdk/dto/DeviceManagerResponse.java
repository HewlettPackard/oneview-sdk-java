/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class DeviceManagerResponse extends BaseModelResource {

    private static final long serialVersionUID = 1002171687002022317L;

    private List<Property> connectionInfo = new ArrayList<>();
    private String deviceManagerUrl;
    private String deviceManagerVersion;
    private Boolean isInternal;
    private String managedSansUri;
    private String providerDisplayName;
    private String providerUri;
    private RefreshState refreshState;
    private String unimportedSansUri;

    public List<Property> getConnectionInfo() {
        return connectionInfo;
    }

    public void setConnectionInfo(List<Property> connectionInfo) {
        this.connectionInfo = connectionInfo;
    }

    public String getDeviceManagerUrl() {
        return deviceManagerUrl;
    }

    public void setDeviceManagerUrl(String deviceManagerUrl) {
        this.deviceManagerUrl = deviceManagerUrl;
    }

    public String getDeviceManagerVersion() {
        return deviceManagerVersion;
    }

    public void setDeviceManagerVersion(String deviceManagerVersion) {
        this.deviceManagerVersion = deviceManagerVersion;
    }

    public Boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(Boolean isInternal) {
        this.isInternal = isInternal;
    }

    public String getManagedSansUri() {
        return managedSansUri;
    }

    public void setManagedSansUri(String managedSansUri) {
        this.managedSansUri = managedSansUri;
    }

    public String getProviderDisplayName() {
        return providerDisplayName;
    }

    public void setProviderDisplayName(String providerDisplayName) {
        this.providerDisplayName = providerDisplayName;
    }

    public String getProviderUri() {
        return providerUri;
    }

    public void setProviderUri(String providerUri) {
        this.providerUri = providerUri;
    }

    public RefreshState getRefreshState() {
        return refreshState;
    }

    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    public String getUnimportedSansUri() {
        return unimportedSansUri;
    }

    public void setUnimportedSansUri(String unimportedSansUri) {
        this.unimportedSansUri = unimportedSansUri;
    }

    @Override
    public final boolean canEqual(Object obj) {
        return (obj instanceof DeviceManagerResponse);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof DeviceManagerResponse) {
            DeviceManagerResponse that = (DeviceManagerResponse) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(connectionInfo, that.connectionInfo)
                    .append(deviceManagerUrl, that.deviceManagerUrl)
                    .append(deviceManagerVersion, that.deviceManagerVersion)
                    .append(isInternal, that.isInternal)
                    .append(managedSansUri, that.managedSansUri)
                    .append(providerDisplayName, that.providerDisplayName)
                    .append(providerUri, that.providerUri)
                    .append(refreshState, that.refreshState)
                    .append(unimportedSansUri, that.unimportedSansUri)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(connectionInfo)
                .append(deviceManagerUrl)
                .append(deviceManagerVersion)
                .append(isInternal)
                .append(managedSansUri)
                .append(providerDisplayName)
                .append(providerUri)
                .append(refreshState)
                .append(unimportedSansUri)
                .toHashCode();
    }

    @Override
    public final String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("connectionInfo", connectionInfo)
                .append("deviceManagerUrl", deviceManagerUrl)
                .append("deviceManagerVersion", deviceManagerVersion)
                .append("isInternal", isInternal)
                .append("managedSansUri", managedSansUri)
                .append("providerDisplayName", providerDisplayName)
                .append("providerUri", providerUri)
                .append("refreshState", refreshState)
                .append("unimportedSansUri", unimportedSansUri)
                .toString();
    }
}
