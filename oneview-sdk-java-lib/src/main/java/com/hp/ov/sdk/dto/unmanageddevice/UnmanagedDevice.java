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

package com.hp.ov.sdk.dto.unmanageddevice;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class UnmanagedDevice extends BaseModelResource {

    private String deviceType;
    private Integer height;
    private String id;
    private String ipv4Address;
    private String ipv6Address;
    private String mac;
    private Integer maxPwrConsumed;
    private String model;
    private String uuid;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpv4Address() {
        return ipv4Address;
    }

    public void setIpv4Address(String ipv4Address) {
        this.ipv4Address = ipv4Address;
    }

    public String getIpv6Address() {
        return ipv6Address;
    }

    public void setIpv6Address(String ipv6Address) {
        this.ipv6Address = ipv6Address;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getMaxPwrConsumed() {
        return maxPwrConsumed;
    }

    public void setMaxPwrConsumed(Integer maxPwrConsumed) {
        this.maxPwrConsumed = maxPwrConsumed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof UnmanagedDevice);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof UnmanagedDevice) {
            UnmanagedDevice that = (UnmanagedDevice) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(deviceType, that.deviceType)
                    .append(height, that.height)
                    .append(id, that.id)
                    .append(ipv4Address, that.ipv4Address)
                    .append(ipv6Address, that.ipv6Address)
                    .append(mac, that.mac)
                    .append(maxPwrConsumed, that.maxPwrConsumed)
                    .append(model, that.model)
                    .append(uuid, that.uuid)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(deviceType)
                .append(height)
                .append(id)
                .append(ipv4Address)
                .append(ipv6Address)
                .append(mac)
                .append(maxPwrConsumed)
                .append(model)
                .append(uuid)
                .toHashCode();
    }
}
