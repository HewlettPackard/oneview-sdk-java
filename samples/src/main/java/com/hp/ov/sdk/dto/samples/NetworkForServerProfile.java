/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.samples;

import com.hp.ov.sdk.dto.servers.serverprofile.BootControl;
import com.hp.ov.sdk.dto.servers.serverprofile.FunctionType;
import com.hp.ov.sdk.dto.servers.serverprofile.ProfileConnection;

public class NetworkForServerProfile {
    private String networkName;
    private String requestedMbps;
    private Integer allocatedMbps;
    private Integer maximumMbps;
    private FunctionType networkType;
    private BootControl boot;

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(final String networkName) {
        this.networkName = networkName;
    }

    public String getRequestedMbps() {
        return requestedMbps;
    }

    public void setRequestedMbps(final String requestedMbps) {
        this.requestedMbps = requestedMbps;
    }

    public Integer getAllocatedMbps() {
        return allocatedMbps;
    }

    public void setAllocatedMbps(final Integer allocatedMbps) {
        this.allocatedMbps = allocatedMbps;
    }

    public Integer getMaximumMbps() {
        return maximumMbps;
    }

    public void setMaximumMbps(final Integer maximumMbps) {
        this.maximumMbps = maximumMbps;
    }

    public FunctionType getNetworkType() {
        return networkType;
    }

    public void setNetworkType(final FunctionType networkType) {
        this.networkType = networkType;
    }

    public BootControl getBoot() {
        return boot;
    }

    public void setBoot(final BootControl boot) {
        this.boot = boot;
    }

}
