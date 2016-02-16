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

import java.util.ArrayList;
import java.util.List;

public class InterconnectType extends BaseModelResource {

    private static final long serialVersionUID = 5335129001173822652L;

    private List<DownlinkCapability> downlinkCapabilities = new ArrayList<>();
    private Integer downlinkCount;
    private String minimumFirmwareVersion;
    private String maximumFirmwareVersion;
    private String partNumber;
    private List<PortInfo> portInfos = new ArrayList<>();
    private List<UnsupportedCapability> unsupportedCapabilities = new ArrayList<>();

    @Since(200)
    private InterconnectCapabilities interconnectCapabilities;
    @Since(200)
    private DownlinkPortCapability downlinkPortCapability;

    public List<DownlinkCapability> getDownlinkCapabilities() {
        return downlinkCapabilities;
    }

    public void setDownlinkCapabilities(final List<DownlinkCapability> downlinkCapabilities) {
        this.downlinkCapabilities = downlinkCapabilities;
    }

    public Integer getDownlinkCount() {
        return downlinkCount;
    }

    public void setDownlinkCount(final Integer downlinkCount) {
        this.downlinkCount = downlinkCount;
    }

    public String getMinimumFirmwareVersion() {
        return minimumFirmwareVersion;
    }

    public void setMinimumFirmwareVersion(final String minimumFirmwareVersion) {
        this.minimumFirmwareVersion = minimumFirmwareVersion;
    }

    public String getMaximumFirmwareVersion() {
        return maximumFirmwareVersion;
    }

    public void setMaximumFirmwareVersion(final String maximumFirmwareVersion) {
        this.maximumFirmwareVersion = maximumFirmwareVersion;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    public List<PortInfo> getPortInfos() {
        return portInfos;
    }

    public void setPortInfos(final List<PortInfo> portInfos) {
        this.portInfos = portInfos;
    }

    public List<UnsupportedCapability> getUnsupportedCapabilities() {
        return unsupportedCapabilities;
    }

    public void setUnsupportedCapabilities(List<UnsupportedCapability> unsupportedCapabilities) {
        this.unsupportedCapabilities = unsupportedCapabilities;
    }

    public InterconnectCapabilities getInterconnectCapabilities() {
        return interconnectCapabilities;
    }

    public void setInterconnectCapabilities(InterconnectCapabilities interconnectCapabilities) {
        this.interconnectCapabilities = interconnectCapabilities;
    }

    public DownlinkPortCapability getDownlinkPortCapability() {
        return downlinkPortCapability;
    }

    public void setDownlinkPortCapability(DownlinkPortCapability downlinkPortCapability) {
        this.downlinkPortCapability = downlinkPortCapability;
    }

    //TODO equals && hashcode && toString
}
