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

    /**
     * @return the associatedUplinkSetUri
     */
    public String getAssociatedUplinkSetUri() {
        return associatedUplinkSetUri;
    }

    /**
     * @param associatedUplinkSetUri the associatedUplinkSetUri to set
     */
    public void setAssociatedUplinkSetUri(String associatedUplinkSetUri) {
        this.associatedUplinkSetUri = associatedUplinkSetUri;
    }

    /**
     * @return the available
     */
    public Boolean getAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the capability
     */
    public List<PortCapability> getCapability() {
        return capability;
    }

    /**
     * @param capability the capability to set
     */
    public void setCapability(List<PortCapability> capability) {
        this.capability = capability;
    }

    /**
     * @return the configPortTypes
     */
    public List<PortCapability> getConfigPortTypes() {
        return configPortTypes;
    }

    /**
     * @param configPortTypes the configPortTypes to set
     */
    public void setConfigPortTypes(List<PortCapability> configPortTypes) {
        this.configPortTypes = configPortTypes;
    }

    /**
     * @return the connectorType
     */
    public String getConnectorType() {
        return connectorType;
    }

    /**
     * @param connectorType the connectorType to set
     */
    public void setConnectorType(String connectorType) {
        this.connectorType = connectorType;
    }

    /**
     * @return the dcbxInfo
     */
    public DcbxInfo getDcbxInfo() {
        return dcbxInfo;
    }

    /**
     * @param dcbxInfo the dcbxInfo to set
     */
    public void setDcbxInfo(DcbxInfo dcbxInfo) {
        this.dcbxInfo = dcbxInfo;
    }

    /**
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the fcPortProperties
     */
    public FcPortProperties getFcPortProperties() {
        return fcPortProperties;
    }

    /**
     * @param fcPortProperties the fcPortProperties to set
     */
    public void setFcPortProperties(FcPortProperties fcPortProperties) {
        this.fcPortProperties = fcPortProperties;
    }

    /**
     * @return the interconnectName
     */
    public String getInterconnectName() {
        return interconnectName;
    }

    /**
     * @param interconnectName the interconnectName to set
     */
    public void setInterconnectName(String interconnectName) {
        this.interconnectName = interconnectName;
    }

    /**
     * @return the lagId
     */
    public Integer getLagId() {
        return lagId;
    }

    /**
     * @param lagId the lagId to set
     */
    public void setLagId(Integer lagId) {
        this.lagId = lagId;
    }

    /**
     * @return the lagStates
     */
    public List<LagState> getLagStates() {
        return lagStates;
    }

    /**
     * @param lagStates the lagStates to set
     */
    public void setLagStates(List<LagState> lagStates) {
        this.lagStates = lagStates;
    }

    /**
     * @return the neighbor
     */
    public Neighbor getNeighbor() {
        return neighbor;
    }

    /**
     * @param neighbor the neighbor to set
     */
    public void setNeighbor(Neighbor neighbor) {
        this.neighbor = neighbor;
    }

    /**
     * @return the operationalSpeed
     */
    public String getOperationalSpeed() {
        return operationalSpeed;
    }

    /**
     * @param operationalSpeed the operationalSpeed to set
     */
    public void setOperationalSpeed(String operationalSpeed) {
        this.operationalSpeed = operationalSpeed;
    }

    /**
     * @return the pairedPortName
     */
    public String getPairedPortName() {
        return pairedPortName;
    }

    /**
     * @param pairedPortName the pairedPortName to set
     */
    public void setPairedPortName(String pairedPortName) {
        this.pairedPortName = pairedPortName;
    }

    /**
     * @return the portHealthStatus
     */
    public PortHealthStatus getPortHealthStatus() {
        return portHealthStatus;
    }

    /**
     * @param portHealthStatus the portHealthStatus to set
     */
    public void setPortHealthStatus(PortHealthStatus portHealthStatus) {
        this.portHealthStatus = portHealthStatus;
    }

    /**
     * @return the portId
     */
    public String getPortId() {
        return portId;
    }

    /**
     * @param portId the portId to set
     */
    public void setPortId(String portId) {
        this.portId = portId;
    }

    /**
     * @return the portMonitorConfigInfo
     */
    public PortMonitorConfigInfo getPortMonitorConfigInfo() {
        return portMonitorConfigInfo;
    }

    /**
     * @param portMonitorConfigInfo the portMonitorConfigInfo to set
     */
    public void setPortMonitorConfigInfo(PortMonitorConfigInfo portMonitorConfigInfo) {
        this.portMonitorConfigInfo = portMonitorConfigInfo;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

    /**
     * @return the portRunningCapabilityType
     */
    public CapabilityType getPortRunningCapabilityType() {
        return portRunningCapabilityType;
    }

    /**
     * @param portRunningCapabilityType the portRunningCapabilityType to set
     */
    public void setPortRunningCapabilityType(CapabilityType portRunningCapabilityType) {
        this.portRunningCapabilityType = portRunningCapabilityType;
    }

    /**
     * @return the portSplitMode
     */
    public PortSplitMode getPortSplitMode() {
        return portSplitMode;
    }

    /**
     * @param portSplitMode the portSplitMode to set
     */
    public void setPortSplitMode(PortSplitMode portSplitMode) {
        this.portSplitMode = portSplitMode;
    }

    /**
     * @return the portStatus
     */
    public PortStatus getPortStatus() {
        return portStatus;
    }

    /**
     * @param portStatus the portStatus to set
     */
    public void setPortStatus(PortStatus portStatus) {
        this.portStatus = portStatus;
    }

    /**
     * @return the portStatusReason
     */
    public PortStatusReason getPortStatusReason() {
        return portStatusReason;
    }

    /**
     * @param portStatusReason the portStatusReason to set
     */
    public void setPortStatusReason(PortStatusReason portStatusReason) {
        this.portStatusReason = portStatusReason;
    }

    /**
     * @return the portType
     */
    public PortType getPortType() {
        return portType;
    }

    /**
     * @param portType the portType to set
     */
    public void setPortType(PortType portType) {
        this.portType = portType;
    }

    /**
     * @return the portTypeExtended
     */
    public PortTypeExtended getPortTypeExtended() {
        return portTypeExtended;
    }

    /**
     * @param portTypeExtended the portTypeExtended to set
     */
    public void setPortTypeExtended(PortTypeExtended portTypeExtended) {
        this.portTypeExtended = portTypeExtended;
    }

    /**
     * @return the subports
     */
    public List<SubPort> getSubports() {
        return subports;
    }

    /**
     * @param subports the subports to set
     */
    public void setSubports(List<SubPort> subports) {
        this.subports = subports;
    }

    /**
     * @return the vendorSpecificPortName
     */
    public String getVendorSpecificPortName() {
        return vendorSpecificPortName;
    }

    /**
     * @param vendorSpecificPortName the vendorSpecificPortName to set
     */
    public void setVendorSpecificPortName(String vendorSpecificPortName) {
        this.vendorSpecificPortName = vendorSpecificPortName;
    }

    /**
     * @return the vlans
     */
    public List<Integer> getVlans() {
        return vlans;
    }

    /**
     * @param vlans the vlans to set
     */
    public void setVlans(List<Integer> vlans) {
        this.vlans = vlans;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Port);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
