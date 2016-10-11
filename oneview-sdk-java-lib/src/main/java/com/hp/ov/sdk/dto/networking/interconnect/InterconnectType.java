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
package com.hp.ov.sdk.dto.networking.interconnect;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.PortInfo;
import com.hp.ov.sdk.dto.UnsupportedCapability;
import com.hp.ov.sdk.dto.networking.DownlinkPortCapability;

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

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof InterconnectType) {
            InterconnectType that = (InterconnectType) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(downlinkCapabilities, that.downlinkCapabilities)
                    .append(downlinkCount, that.downlinkCount)
                    .append(downlinkPortCapability, that.downlinkPortCapability)
                    .append(interconnectCapabilities, that.interconnectCapabilities)
                    .append(maximumFirmwareVersion, that.maximumFirmwareVersion)
                    .append(minimumFirmwareVersion, that.minimumFirmwareVersion)
                    .append(partNumber, that.partNumber)
                    .append(portInfos, that.portInfos)
                    .append(unsupportedCapabilities, that.unsupportedCapabilities)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(downlinkCapabilities)
                .append(downlinkCount)
                .append(downlinkPortCapability)
                .append(interconnectCapabilities)
                .append(maximumFirmwareVersion)
                .append(minimumFirmwareVersion)
                .append(partNumber)
                .append(portInfos)
                .append(unsupportedCapabilities)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("downlinkCapabilities", downlinkCapabilities)
                .append("downlinkCount", downlinkCount)
                .append("downlinkPortCapability", downlinkPortCapability)
                .append("interconnectCapabilities", interconnectCapabilities)
                .append("maximumFirmwareVersion", maximumFirmwareVersion)
                .append("minimumFirmwareVersion", minimumFirmwareVersion)
                .append("partNumber", partNumber)
                .append("portInfos", portInfos)
                .append("unsupportedCapabilities", unsupportedCapabilities)
                .toString();
    }
}
