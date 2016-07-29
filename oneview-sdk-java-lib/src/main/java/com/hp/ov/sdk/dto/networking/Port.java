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
package com.hp.ov.sdk.dto.networking;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.CapabilityType;
import com.hp.ov.sdk.dto.DcbxInfo;
import com.hp.ov.sdk.dto.PortMonitorConfigInfo;
import com.hp.ov.sdk.dto.PortStatus;

public final class Port extends BaseModelResource {

    private static final long serialVersionUID = 2168079423384287367L;

    private String associatedUplinkSetUri;
    @Since(200)
    private Boolean available;
    private Integer bayNumber;
    private List<PortCapability> capability = new ArrayList<>();
    private List<PortCapability> configPortTypes = new ArrayList<>();
    private String connectorType;
    @Since(200)
    private DcbxInfo dcbxInfo;
    private Boolean enabled;
    private FcPortProperties fcPortProperties;
    private String interconnectName;
    private Integer lagId;
    @Since(200)
    private List<LagState> lagStates = new ArrayList<>();
    private Neighbor neighbor;
    private String operationalSpeed;
    private String pairedPortName;
    private PortHealthStatus portHealthStatus;
    private String portId;

    private PortMonitorConfigInfo portMonitorConfigInfo;
    private String portName;
    @Since(200)
    private CapabilityType portRunningCapabilityType;
    @Since(200)
    private PortSplitMode portSplitMode;

    private PortStatus portStatus;
    private PortStatusReason portStatusReason;
    private PortType portType;
    private PortTypeExtended portTypeExtended;
    private List<SubPort> subports = new ArrayList<>();
    @Since(200)
    private String vendorSpecificPortName;
    @Since(200)
    private List<Integer> vlans = new ArrayList<>();

    public String getAssociatedUplinkSetUri() {
        return associatedUplinkSetUri;
    }

    public void setAssociatedUplinkSetUri(String associatedUplinkSetUri) {
        this.associatedUplinkSetUri = associatedUplinkSetUri;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getBayNumber() {
        return bayNumber;
    }

    public void setBayNumber(Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    public List<PortCapability> getCapability() {
        return this.capability;
    }

    public void setCapability(List<PortCapability> capability) {
        this.capability = capability;
    }

    public List<PortCapability> getConfigPortTypes() {
        return this.configPortTypes;
    }

    public void setConfigPortTypes(List<PortCapability> configPortTypes) {
        this.configPortTypes = configPortTypes;
    }

    public String getConnectorType() {
        return connectorType;
    }

    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    public DcbxInfo getDcbxInfo() {
        return dcbxInfo;
    }

    public void setDcbxInfo(DcbxInfo dcbxInfo) {
        this.dcbxInfo = dcbxInfo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public FcPortProperties getFcPortProperties() {
        return fcPortProperties;
    }

    public void setFcPortProperties(FcPortProperties fcPortProperties) {
        this.fcPortProperties = fcPortProperties;
    }

    public String getInterconnectName() {
        return interconnectName;
    }

    public void setInterconnectName(String interconnectName) {
        this.interconnectName = interconnectName;
    }

    public Integer getLagId() {
        return lagId;
    }

    public void setLagId(Integer lagId) {
        this.lagId = lagId;
    }

    public List<LagState> getLagStates() {
        return this.lagStates;
    }

    public void setLagStates(List<LagState> lagStates) {
        this.lagStates = lagStates;
    }

    public Neighbor getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Neighbor neighbor) {
        this.neighbor = neighbor;
    }

    public String getOperationalSpeed() {
        return operationalSpeed;
    }

    public void setOperationalSpeed(String operationalSpeed) {
        this.operationalSpeed = operationalSpeed;
    }

    public String getPairedPortName() {
        return pairedPortName;
    }

    public void setPairedPortName(String pairedPortName) {
        this.pairedPortName = pairedPortName;
    }

    public PortHealthStatus getPortHealthStatus() {
        return portHealthStatus;
    }

    public void setPortHealthStatus(PortHealthStatus portHealthStatus) {
        this.portHealthStatus = portHealthStatus;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }

    public PortMonitorConfigInfo getPortMonitorConfigInfo() {
        return portMonitorConfigInfo;
    }

    public void setPortMonitorConfigInfo(PortMonitorConfigInfo portMonitorConfigInfo) {
        this.portMonitorConfigInfo = portMonitorConfigInfo;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public CapabilityType getPortRunningCapabilityType() {
        return portRunningCapabilityType;
    }

    public void setPortRunningCapabilityType(CapabilityType portRunningCapabilityType) {
        this.portRunningCapabilityType = portRunningCapabilityType;
    }

    public PortSplitMode getPortSplitMode() {
        return portSplitMode;
    }

    public void setPortSplitMode(PortSplitMode portSplitMode) {
        this.portSplitMode = portSplitMode;
    }

    public PortStatus getPortStatus() {
        return portStatus;
    }

    public void setPortStatus(PortStatus portStatus) {
        this.portStatus = portStatus;
    }

    public PortStatusReason getPortStatusReason() {
        return portStatusReason;
    }

    public void setPortStatusReason(PortStatusReason portStatusReason) {
        this.portStatusReason = portStatusReason;
    }

    public PortType getPortType() {
        return portType;
    }

    public void setPortType(PortType portType) {
        this.portType = portType;
    }

    public PortTypeExtended getPortTypeExtended() {
        return portTypeExtended;
    }

    public void setPortTypeExtended(PortTypeExtended portTypeExtended) {
        this.portTypeExtended = portTypeExtended;
    }

    public List<SubPort> getSubports() {
        return subports;
    }

    public void setSubports(List<SubPort> subports) {
        this.subports = subports;
    }

    public String getVendorSpecificPortName() {
        return vendorSpecificPortName;
    }

    public void setVendorSpecificPortName(String vendorSpecificPortName) {
        this.vendorSpecificPortName = vendorSpecificPortName;
    }

    public List<Integer> getVlans() {
        return vlans;
    }

    public void setVlans(List<Integer> vlans) {
        this.vlans = vlans;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Port);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Port port = (Port) obj;

        return port.canEqual(this) && new EqualsBuilder()
                .appendSuper(super.equals(obj))
                .append(associatedUplinkSetUri, port.associatedUplinkSetUri)
                .append(available, port.available)
                .append(bayNumber, port.bayNumber)
                .append(capability, port.capability)
                .append(configPortTypes, port.configPortTypes)
                .append(connectorType, port.connectorType)
                .append(dcbxInfo, port.dcbxInfo)
                .append(enabled, port.enabled)
                .append(fcPortProperties, port.fcPortProperties)
                .append(interconnectName, port.interconnectName)
                .append(lagId, port.lagId)
                .append(lagStates, port.lagStates)
                .append(neighbor, port.neighbor)
                .append(operationalSpeed, port.operationalSpeed)
                .append(pairedPortName, port.pairedPortName)
                .append(portHealthStatus, port.portHealthStatus)
                .append(portId, port.portId)
                .append(portMonitorConfigInfo, port.portMonitorConfigInfo)
                .append(portName, port.portName)
                .append(portRunningCapabilityType, port.portRunningCapabilityType)
                .append(portSplitMode, port.portSplitMode)
                .append(portStatus, port.portStatus)
                .append(portStatusReason, port.portStatusReason)
                .append(portType, port.portType)
                .append(portTypeExtended, port.portTypeExtended)
                .append(subports, port.subports)
                .append(vendorSpecificPortName, port.vendorSpecificPortName)
                .append(vlans, port.vlans)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(associatedUplinkSetUri)
                .append(available)
                .append(bayNumber)
                .append(capability)
                .append(configPortTypes)
                .append(connectorType)
                .append(dcbxInfo)
                .append(enabled)
                .append(fcPortProperties)
                .append(interconnectName)
                .append(lagId)
                .append(lagStates)
                .append(neighbor)
                .append(operationalSpeed)
                .append(pairedPortName)
                .append(portHealthStatus)
                .append(portId)
                .append(portMonitorConfigInfo)
                .append(portName)
                .append(portRunningCapabilityType)
                .append(portSplitMode)
                .append(portStatus)
                .append(portStatusReason)
                .append(portType)
                .append(portTypeExtended)
                .append(subports)
                .append(vendorSpecificPortName)
                .append(vlans)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
