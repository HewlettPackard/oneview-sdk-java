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
package com.hp.ov.sdk.dto.networking.logicaldownlinks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.DownlinkPortCapability;
import com.hp.ov.sdk.dto.networking.OpSpeed;

public class LogicalDownlink extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private List<DownlinkCapabilities> downlinkCapabilities;
    private DownlinkPortCapability downlinkPortCapability;
    private int maxNetworks;
    private OpSpeed maxSpeed = OpSpeed.Unknown;
    private int minNetworks;
    private List<String> networkUris;
    private final List<OpSpeed> permittedSpeedRange = new ArrayList<OpSpeed>();

    /**
     * @return the downlinkCapabilities
     */
    public List<DownlinkCapabilities> getDownlinkCapabilities() {
        return downlinkCapabilities;
    }

    /**
     * @param downlinkCapabilities the downlinkCapabilities to set
     */
    public void setDownlinkCapabilities(List<DownlinkCapabilities> downlinkCapabilities) {
        this.downlinkCapabilities = downlinkCapabilities;
    }

    /**
     * @return the downlinkPortCapability
     */
    public DownlinkPortCapability getDownlinkPortCapability() {
        return downlinkPortCapability;
    }

    /**
     * @param downlinkPortCapability the downlinkPortCapability to set
     */
    public void setDownlinkPortCapability(DownlinkPortCapability downlinkPortCapability) {
        this.downlinkPortCapability = downlinkPortCapability;
    }

    /**
     * @return the maxNetworks
     */
    public int getMaxNetworks() {
        return maxNetworks;
    }

    /**
     * @param maxNetworks the maxNetworks to set
     */
    public void setMaxNetworks(int maxNetworks) {
        this.maxNetworks = maxNetworks;
    }

    /**
     * @return the maxSpeed
     */
    public OpSpeed getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed the maxSpeed to set
     */
    public void setMaxSpeed(OpSpeed maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * @return the minNetworks
     */
    public int getMinNetworks() {
        return minNetworks;
    }

    /**
     * @param minNetworks the minNetworks to set
     */
    public void setMinNetworks(int minNetworks) {
        this.minNetworks = minNetworks;
    }

    /**
     * @return the networkUris
     */
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     * @param networkUris the networkUris to set
     */
    public void setNetworkUris(List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     * @return the permittedSpeedRange
     */
    public List<OpSpeed> getPermittedSpeedRange() {
        return permittedSpeedRange;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
