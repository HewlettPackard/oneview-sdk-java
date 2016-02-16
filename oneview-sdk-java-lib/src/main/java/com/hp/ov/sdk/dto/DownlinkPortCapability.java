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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownlinkPortCapability extends BaseModelResource {

    private static final long serialVersionUID = -152144978107189373L;

    private Map<Integer, String> downlinkSubPorts;
    private Integer maxBandwidthInGbps;
    private List<PortCapabilityType> portCapabilities;
    private Integer totalSubPort;

    public DownlinkPortCapability() {
        this.downlinkSubPorts = new HashMap<>();
        this.portCapabilities = new ArrayList<>();
    }

    public Map<Integer, String> getDownlinkSubPorts() {
        return downlinkSubPorts;
    }

    public void setDownlinkSubPorts(Map<Integer, String> downlinkSubPorts) {
        this.downlinkSubPorts = downlinkSubPorts;
    }

    public Integer getMaxBandwidthInGbps() {
        return maxBandwidthInGbps;
    }

    public void setMaxBandwidthInGbps(Integer maxBandwidthInGbps) {
        this.maxBandwidthInGbps = maxBandwidthInGbps;
    }

    public List<PortCapabilityType> getPortCapabilities() {
        return portCapabilities;
    }

    public void setPortCapabilities(List<PortCapabilityType> portCapabilities) {
        this.portCapabilities = portCapabilities;
    }

    public Integer getTotalSubPort() {
        return totalSubPort;
    }

    public void setTotalSubPort(Integer totalSubPort) {
        this.totalSubPort = totalSubPort;
    }

}
