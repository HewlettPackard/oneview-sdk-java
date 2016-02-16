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
import java.util.List;

public class InterconnectCapabilities {

    private List<InterconnectCapabilityType> capabilities;
    private Integer maxBandwidthInGbps;

    public InterconnectCapabilities() {
        this.capabilities = new ArrayList<>();
    }

    public List<InterconnectCapabilityType> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<InterconnectCapabilityType> capabilities) {
        this.capabilities = capabilities;
    }

    public Integer getMaxBandwidthInGbps() {
        return maxBandwidthInGbps;
    }

    public void setMaxBandwidthInGbps(Integer maxBandwidthInGbps) {
        this.maxBandwidthInGbps = maxBandwidthInGbps;
    }

}
