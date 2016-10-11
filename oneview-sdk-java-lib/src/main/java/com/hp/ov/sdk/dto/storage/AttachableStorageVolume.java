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
package com.hp.ov.sdk.dto.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Until;
import com.hp.ov.sdk.util.UrlUtils;

public class AttachableStorageVolume implements Serializable {

    private static final long serialVersionUID = 4480362764321679852L;

    private String provisionType;
    private String provisionedCapacity;

    @Until(199)
    private String provisioningType;

    private String raidLevel;
    private Boolean shareable;
    private String storagePoolName;
    private String storagePoolUri;
    private String storageSystemName;
    private String storageSystemUri;
    private String name;
    private String uri;
    private List<String> availableNetworks = new ArrayList<>();

    public String getProvisionType() {
        return provisionType;
    }

    public void setProvisionType(final String provisionType) {
        this.provisionType = provisionType;
    }

    public String getProvisionedCapacity() {
        return provisionedCapacity;
    }

    public void setProvisionedCapacity(final String provisionedCapacity) {
        this.provisionedCapacity = provisionedCapacity;
    }

    public String getProvisioningType() {
        return provisioningType;
    }

    public void setProvisioningType(final String provisioningType) {
        this.provisioningType = provisioningType;
    }

    public String getRaidLevel() {
        return raidLevel;
    }

    public void setRaidLevel(final String raidLevel) {
        this.raidLevel = raidLevel;
    }

    public Boolean getShareable() {
        return shareable;
    }

    public void setShareable(final Boolean shareable) {
        this.shareable = shareable;
    }

    public String getStoragePoolName() {
        return storagePoolName;
    }

    public void setStoragePoolName(final String storagePoolName) {
        this.storagePoolName = storagePoolName;
    }

    public String getStoragePoolUri() {
        return storagePoolUri;
    }

    public void setStoragePoolUri(final String storagePoolUri) {
        this.storagePoolUri = storagePoolUri;
    }

    public String getStorageSystemName() {
        return storageSystemName;
    }

    public void setStorageSystemName(final String storageSystemName) {
        this.storageSystemName = storageSystemName;
    }

    public String getStorageSystemUri() {
        return storageSystemUri;
    }

    public void setStorageSystemUri(final String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }

    public String getResourceId() {
        return UrlUtils.getResourceIdFromUri(this.getUri());
    }

    public List<String> getAvailableNetworks() {
        return availableNetworks;
    }

    public void setAvailableNetworks(final List<String> availableNetworks) {
        this.availableNetworks = availableNetworks;
    }
}
