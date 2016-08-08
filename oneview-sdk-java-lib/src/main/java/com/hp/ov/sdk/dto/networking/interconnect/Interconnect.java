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
package com.hp.ov.sdk.dto.networking.interconnect;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.EnclosureType;
import com.hp.ov.sdk.dto.networking.IcmLicensesDto;
import com.hp.ov.sdk.dto.networking.Port;
import com.hp.ov.sdk.dto.networking.SnmpConfiguration;


/**
 * The Interconnects data transfer object (DTO) contains the information used to
 * represent a interconnects in the system. It is passed in to the update
 * interconnects REST api, as well as the update interconnects through java
 * client api.
 */
public class Interconnect extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Boolean edgeVirtualBridgingAvailable;
    private Boolean enableFastMacCacheFailover;
    private Boolean enableIgmpSnooping;
    private Boolean enableNetworkLoopProtection;
    private Boolean enablePauseFloodProtection;
    private String enclosureName;
    @Since(200)
    private EnclosureType enclosureType;
    private String enclosureUri;
    private String firmwareVersion;
    @Since(300)
    private IcmLicensesDto icmLicenses;
    private Integer igmpIdleTimeoutInterval;
    private String interconnectIP;
    private InterconnectLocation interconnectLocation;
    private String interconnectTypeUri;
    private List<IpAddressList> ipAddressList = new ArrayList<IpAddressList>();
    private String logicalInterconnectUri;
    private MaxBandwidth maxBandwidth;
    @Since(300)
    private MigrationState migrationState;
    private String model;
    private Integer networkLoopProtectionInterval;
    private String partNumber;
    private Integer portCount;
    private List<Port> ports = new ArrayList<Port>();
    private PowerStatus powerStatus;
    private String productName;
    private List<String> roles = new ArrayList<String>();
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private String serialNumber;
    private SnmpConfiguration snmpConfiguration;
    @Since(200)
    private SwitchStackingDomainRole stackingDomainRole;
    private Integer subPortCount;
    private List<String> unsupportedCapabilities = new ArrayList<String>();
    @Since(200)
    private UIDState uidState;

    /**
     * @return the edgeVirtualBridgingAvailable
     */
    public Boolean getEdgeVirtualBridgingAvailable() {
        return edgeVirtualBridgingAvailable;
    }

    /**
     * @param edgeVirtualBridgingAvailable the edgeVirtualBridgingAvailable to set
     */
    public void setEdgeVirtualBridgingAvailable(Boolean edgeVirtualBridgingAvailable) {
        this.edgeVirtualBridgingAvailable = edgeVirtualBridgingAvailable;
    }

    /**
     * @return the enableFastMacCacheFailover
     */
    public Boolean getEnableFastMacCacheFailover() {
        return enableFastMacCacheFailover;
    }

    /**
     * @param enableFastMacCacheFailover the enableFastMacCacheFailover to set
     */
    public void setEnableFastMacCacheFailover(Boolean enableFastMacCacheFailover) {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     * @return the enableIgmpSnooping
     */
    public Boolean getEnableIgmpSnooping() {
        return enableIgmpSnooping;
    }

    /**
     * @param enableIgmpSnooping the enableIgmpSnooping to set
     */
    public void setEnableIgmpSnooping(Boolean enableIgmpSnooping) {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     * @return the enableNetworkLoopProtection
     */
    public Boolean getEnableNetworkLoopProtection() {
        return enableNetworkLoopProtection;
    }

    /**
     * @param enableNetworkLoopProtection the enableNetworkLoopProtection to set
     */
    public void setEnableNetworkLoopProtection(Boolean enableNetworkLoopProtection) {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     * @return the enablePauseFloodProtection
     */
    public Boolean getEnablePauseFloodProtection() {
        return enablePauseFloodProtection;
    }

    /**
     * @param enablePauseFloodProtection the enablePauseFloodProtection to set
     */
    public void setEnablePauseFloodProtection(Boolean enablePauseFloodProtection) {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     * @return the enclosureName
     */
    public String getEnclosureName() {
        return enclosureName;
    }

    /**
     * @param enclosureName the enclosureName to set
     */
    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    /**
     * @return the enclosureType
     */
    public EnclosureType getEnclosureType() {
        return enclosureType;
    }

    /**
     * @param enclosureType the enclosureType to set
     */
    public void setEnclosureType(EnclosureType enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     * @return the firmwareVersion
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * @param firmwareVersion the firmwareVersion to set
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * @return the icmLicenses
     */
    public IcmLicensesDto getIcmLicenses() {
        return icmLicenses;
    }

    /**
     * @param icmLicenses the icmLicenses to set
     */
    public void setIcmLicenses(IcmLicensesDto icmLicenses) {
        this.icmLicenses = icmLicenses;
    }

    /**
     * @return the igmpIdleTimeoutInterval
     */
    public Integer getIgmpIdleTimeoutInterval() {
        return igmpIdleTimeoutInterval;
    }

    /**
     * @param igmpIdleTimeoutInterval the igmpIdleTimeoutInterval to set
     */
    public void setIgmpIdleTimeoutInterval(Integer igmpIdleTimeoutInterval) {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     * @return the interconnectIP
     */
    public String getInterconnectIP() {
        return interconnectIP;
    }

    /**
     * @param interconnectIP the interconnectIP to set
     */
    public void setInterconnectIP(String interconnectIP) {
        this.interconnectIP = interconnectIP;
    }

    /**
     * @return the interconnectLocation
     */
    public InterconnectLocation getInterconnectLocation() {
        return interconnectLocation;
    }

    /**
     * @param interconnectLocation the interconnectLocation to set
     */
    public void setInterconnectLocation(InterconnectLocation interconnectLocation) {
        this.interconnectLocation = interconnectLocation;
    }

    /**
     * @return the interconnectTypeUri
     */
    public String getInterconnectTypeUri() {
        return interconnectTypeUri;
    }

    /**
     * @param interconnectTypeUri the interconnectTypeUri to set
     */
    public void setInterconnectTypeUri(String interconnectTypeUri) {
        this.interconnectTypeUri = interconnectTypeUri;
    }

    /**
     * @return the ipAddressList
     */
    public List<IpAddressList> getIpAddressList() {
        return ipAddressList;
    }

    /**
     * @param ipAddressList the ipAddressList to set
     */
    public void setIpAddressList(List<IpAddressList> ipAddressList) {
        this.ipAddressList = ipAddressList;
    }

    /**
     * @return the logicalInterconnectUri
     */
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }

    /**
     * @param logicalInterconnectUri the logicalInterconnectUri to set
     */
    public void setLogicalInterconnectUri(String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    /**
     * @return the maxBandwidth
     */
    public MaxBandwidth getMaxBandwidth() {
        return maxBandwidth;
    }

    /**
     * @param maxBandwidth the maxBandwidth to set
     */
    public void setMaxBandwidth(MaxBandwidth maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }

    /**
     * @return the migrationState
     */
    public MigrationState getMigrationState() {
        return migrationState;
    }

    /**
     * @param migrationState the migrationState to set
     */
    public void setMigrationState(MigrationState migrationState) {
        this.migrationState = migrationState;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the networkLoopProtectionInterval
     */
    public Integer getNetworkLoopProtectionInterval() {
        return networkLoopProtectionInterval;
    }

    /**
     * @param networkLoopProtectionInterval the networkLoopProtectionInterval to set
     */
    public void setNetworkLoopProtectionInterval(Integer networkLoopProtectionInterval) {
        this.networkLoopProtectionInterval = networkLoopProtectionInterval;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the portCount
     */
    public Integer getPortCount() {
        return portCount;
    }

    /**
     * @param portCount the portCount to set
     */
    public void setPortCount(Integer portCount) {
        this.portCount = portCount;
    }

    /**
     * @return the ports
     */
    public List<Port> getPorts() {
        return ports;
    }

    /**
     * @param ports the ports to set
     */
    public void setPorts(List<Port> ports) {
        this.ports = ports;
    }

    /**
     * @return the powerStatus
     */
    public PowerStatus getPowerStatus() {
        return powerStatus;
    }

    /**
     * @param powerStatus the powerStatus to set
     */
    public void setPowerStatus(PowerStatus powerStatus) {
        this.powerStatus = powerStatus;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the snmpConfiguration
     */
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     * @param snmpConfiguration the snmpConfiguration to set
     */
    public void setSnmpConfiguration(SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     * @return the stackingDomainRole
     */
    public SwitchStackingDomainRole getStackingDomainRole() {
        return stackingDomainRole;
    }

    /**
     * @param stackingDomainRole the stackingDomainRole to set
     */
    public void setStackingDomainRole(SwitchStackingDomainRole stackingDomainRole) {
        this.stackingDomainRole = stackingDomainRole;
    }

    /**
     * @return the subPortCount
     */
    public Integer getSubPortCount() {
        return subPortCount;
    }

    /**
     * @param subPortCount the subPortCount to set
     */
    public void setSubPortCount(Integer subPortCount) {
        this.subPortCount = subPortCount;
    }

    /**
     * @return the unsupportedCapabilities
     */
    public List<String> getUnsupportedCapabilities() {
        return unsupportedCapabilities;
    }

    /**
     * @param unsupportedCapabilities the unsupportedCapabilities to set
     */
    public void setUnsupportedCapabilities(List<String> unsupportedCapabilities) {
        this.unsupportedCapabilities = unsupportedCapabilities;
    }

    /**
     * @return the uidState
     */
    public UIDState getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(UIDState uidState) {
        this.uidState = uidState;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof Interconnect);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Interconnect) == false) {
            return false;
        }
        final Interconnect rhs = ((Interconnect) other);
        return rhs.canEqual(this) && new EqualsBuilder()
                .append(edgeVirtualBridgingAvailable, rhs.edgeVirtualBridgingAvailable)
                .append(enableFastMacCacheFailover, rhs.enableFastMacCacheFailover)
                .append(enableIgmpSnooping, rhs.enableIgmpSnooping)
                .append(enableNetworkLoopProtection, rhs.enableNetworkLoopProtection)
                .append(enablePauseFloodProtection, rhs.enablePauseFloodProtection)
                .append(enclosureName, rhs.enclosureName)
                .append(enclosureType, rhs.enclosureType)
                .append(enclosureUri, rhs.enclosureUri)
                .append(firmwareVersion, rhs.firmwareVersion)
                .append(icmLicenses, rhs.icmLicenses)
                .append(igmpIdleTimeoutInterval, rhs.igmpIdleTimeoutInterval)
                .append(interconnectIP, rhs.interconnectIP)
                .append(interconnectLocation, rhs.interconnectLocation)
                .append(interconnectTypeUri, rhs.interconnectTypeUri)
                .append(ipAddressList, rhs.ipAddressList)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .append(maxBandwidth, rhs.maxBandwidth)
                .append(migrationState, rhs.migrationState)
                .append(model, rhs.model)
                .append(networkLoopProtectionInterval, rhs.networkLoopProtectionInterval)
                .append(partNumber, rhs.partNumber)
                .append(portCount, rhs.portCount)
                .append(ports, rhs.ports)
                .append(powerStatus, rhs.powerStatus)
                .append(productName, rhs.productName)
                .append(roles, rhs.roles)
                .append(scopeUris, rhs.scopeUris)
                .append(serialNumber, rhs.serialNumber)
                .append(snmpConfiguration, rhs.snmpConfiguration)
                .append(stackingDomainRole, rhs.stackingDomainRole)
                .append(subPortCount, rhs.subPortCount)
                .append(unsupportedCapabilities, rhs.unsupportedCapabilities)
                .append(uidState, rhs.uidState)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(edgeVirtualBridgingAvailable)
                .append(enableFastMacCacheFailover)
                .append(enableIgmpSnooping)
                .append(enableNetworkLoopProtection)
                .append(enablePauseFloodProtection)
                .append(enclosureName)
                .append(enclosureType)
                .append(enclosureUri)
                .append(firmwareVersion)
                .append(icmLicenses)
                .append(igmpIdleTimeoutInterval)
                .append(interconnectIP)
                .append(interconnectLocation)
                .append(interconnectTypeUri)
                .append(ipAddressList)
                .append(logicalInterconnectUri)
                .append(maxBandwidth)
                .append(migrationState)
                .append(model)
                .append(networkLoopProtectionInterval)
                .append(partNumber)
                .append(portCount)
                .append(ports)
                .append(powerStatus)
                .append(productName)
                .append(roles)
                .append(scopeUris)
                .append(serialNumber)
                .append(snmpConfiguration)
                .append(stackingDomainRole)
                .append(subPortCount)
                .append(unsupportedCapabilities)
                .append(uidState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
