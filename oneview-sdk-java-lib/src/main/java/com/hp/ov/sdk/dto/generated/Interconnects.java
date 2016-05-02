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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * The Interconnects data transfer object (DTO) contains the information used to
 * represent a interconnects in the system. It is passed in to the update
 * interconnects REST api, as well as the update interconnects through java
 * client api.
 */
public class Interconnects implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String serialNumber;
    private String logicalInterconnectUri;
    private List<Port> ports = new ArrayList<Port>();
    private String partNumber;
    private Interconnects.PowerStatus powerStatus;
    private List<IpAddressList> ipAddressList = new ArrayList<IpAddressList>();
    private SnmpConfiguration snmpConfiguration;
    private String enclosureUri;
    private String productName;
    private String interconnectTypeUri;
    private Integer portCount;
    private Integer igmpIdleTimeoutInterval;
    private Boolean enablePauseFloodProtection;
    private List<String> unsupportedCapabilities = new ArrayList<String>();
    private String enclosureName;
    private String firmwareVersion;
    private String interconnectIP;
    private String model;
    private InterconnectLocation interconnectLocation;
    private Interconnects.MaxBandwidth maxBandwidth;
    private Integer subPortCount;
    private Boolean edgeVirtualBridgingAvailable;
    private Boolean enableIgmpSnooping;
    private Integer networkLoopProtectionInterval;
    private Boolean enableNetworkLoopProtection;
    private Boolean enableFastMacCacheFailover;
    private List<String> roles = new ArrayList<String>();
    private String description;
    private String status;
    private String name;
    private String state;
    private String eTag;
    private String created;
    private String modified;
    private String category;
    private String uri;

    /**
     *
     * @return The serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     *            The serialNumber
     */
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return The logicalInterconnectUri
     */
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }

    /**
     *
     * @param logicalInterconnectUri
     *            The logicalInterconnectUri
     */
    public void setLogicalInterconnectUri(final String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    /**
     *
     * @return The ports
     */
    public List<Port> getPorts() {
        return ports;
    }

    /**
     *
     * @param ports
     *            The ports
     */
    public void setPorts(final List<Port> ports) {
        this.ports = ports;
    }

    /**
     *
     * @return The partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     *
     * @param partNumber
     *            The partNumber
     */
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     *
     * @return The powerStatus
     */
    public Interconnects.PowerStatus getPowerStatus() {
        return powerStatus;
    }

    /**
     *
     * @param powerStatus
     *            The powerStatus
     */
    public void setPowerStatus(final Interconnects.PowerStatus powerStatus) {
        this.powerStatus = powerStatus;
    }

    /**
     *
     * @return The ipAddressList
     */
    public List<IpAddressList> getIpAddressList() {
        return ipAddressList;
    }

    /**
     *
     * @param ipAddressList
     *            The ipAddressList
     */
    public void setIpAddressList(final List<IpAddressList> ipAddressList) {
        this.ipAddressList = ipAddressList;
    }

    /**
     *
     * @return The snmpConfiguration
     */
    public SnmpConfiguration getSnmpConfiguration() {
        return snmpConfiguration;
    }

    /**
     *
     * @param snmpConfiguration
     *            The snmpConfiguration
     */
    public void setSnmpConfiguration(final SnmpConfiguration snmpConfiguration) {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     *
     * @return The enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     *
     * @param enclosureUri
     *            The enclosureUri
     */
    public void setEnclosureUri(final String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     *
     * @return The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     *            The productName
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return The interconnectTypeUri
     */
    public String getInterconnectTypeUri() {
        return interconnectTypeUri;
    }

    /**
     *
     * @param interconnectTypeUri
     *            The interconnectTypeUri
     */
    public void setInterconnectTypeUri(final String interconnectTypeUri) {
        this.interconnectTypeUri = interconnectTypeUri;
    }

    /**
     *
     * @return The portCount
     */
    public Integer getPortCount() {
        return portCount;
    }

    /**
     *
     * @param portCount
     *            The portCount
     */
    public void setPortCount(final Integer portCount) {
        this.portCount = portCount;
    }

    /**
     *
     * @return The igmpIdleTimeoutInterval
     */
    public Integer getIgmpIdleTimeoutInterval() {
        return igmpIdleTimeoutInterval;
    }

    /**
     *
     * @param igmpIdleTimeoutInterval
     *            The igmpIdleTimeoutInterval
     */
    public void setIgmpIdleTimeoutInterval(final Integer igmpIdleTimeoutInterval) {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     *
     * @return The enablePauseFloodProtection
     */
    public Boolean getEnablePauseFloodProtection() {
        return enablePauseFloodProtection;
    }

    /**
     *
     * @param enablePauseFloodProtection
     *            The enablePauseFloodProtection
     */
    public void setEnablePauseFloodProtection(final Boolean enablePauseFloodProtection) {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     *
     * @return The unsupportedCapabilities
     */
    public List<String> getUnsupportedCapabilities() {
        return unsupportedCapabilities;
    }

    /**
     *
     * @param unsupportedCapabilities
     *            The unsupportedCapabilities
     */
    public void setUnsupportedCapabilities(final List<String> unsupportedCapabilities) {
        this.unsupportedCapabilities = unsupportedCapabilities;
    }

    /**
     *
     * @return The enclosureName
     */
    public String getEnclosureName() {
        return enclosureName;
    }

    /**
     *
     * @param enclosureName
     *            The enclosureName
     */
    public void setEnclosureName(final String enclosureName) {
        this.enclosureName = enclosureName;
    }

    /**
     *
     * @return The firmwareVersion
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     *
     * @param firmwareVersion
     *            The firmwareVersion
     */
    public void setFirmwareVersion(final String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     *
     * @return The interconnectIP
     */
    public String getInterconnectIP() {
        return interconnectIP;
    }

    /**
     *
     * @param interconnectIP
     *            The interconnectIP
     */
    public void setInterconnectIP(final String interconnectIP) {
        this.interconnectIP = interconnectIP;
    }

    /**
     *
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     *
     * @param model
     *            The model
     */
    public void setModel(final String model) {
        this.model = model;
    }

    /**
     *
     * @return The interconnectLocation
     */
    public InterconnectLocation getInterconnectLocation() {
        return interconnectLocation;
    }

    /**
     *
     * @param interconnectLocation
     *            The interconnectLocation
     */
    public void setInterconnectLocation(final InterconnectLocation interconnectLocation) {
        this.interconnectLocation = interconnectLocation;
    }

    /**
     *
     * @return The maxBandwidth
     */
    public Interconnects.MaxBandwidth getMaxBandwidth() {
        return maxBandwidth;
    }

    /**
     *
     * @param maxBandwidth
     *            The maxBandwidth
     */
    public void setMaxBandwidth(final Interconnects.MaxBandwidth maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }

    /**
     *
     * @return The subPortCount
     */
    public Integer getSubPortCount() {
        return subPortCount;
    }

    /**
     *
     * @param subPortCount
     *            The subPortCount
     */
    public void setSubPortCount(final Integer subPortCount) {
        this.subPortCount = subPortCount;
    }

    /**
     *
     * @return The edgeVirtualBridgingAvailable
     */
    public Boolean getEdgeVirtualBridgingAvailable() {
        return edgeVirtualBridgingAvailable;
    }

    /**
     *
     * @param edgeVirtualBridgingAvailable
     *            The edgeVirtualBridgingAvailable
     */
    public void setEdgeVirtualBridgingAvailable(final Boolean edgeVirtualBridgingAvailable) {
        this.edgeVirtualBridgingAvailable = edgeVirtualBridgingAvailable;
    }

    /**
     *
     * @return The enableIgmpSnooping
     */
    public Boolean getEnableIgmpSnooping() {
        return enableIgmpSnooping;
    }

    /**
     *
     * @param enableIgmpSnooping
     *            The enableIgmpSnooping
     */
    public void setEnableIgmpSnooping(final Boolean enableIgmpSnooping) {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     *
     * @return The networkLoopProtectionInterval
     */
    public Integer getNetworkLoopProtectionInterval() {
        return networkLoopProtectionInterval;
    }

    /**
     *
     * @param networkLoopProtectionInterval
     *            The networkLoopProtectionInterval
     */
    public void setNetworkLoopProtectionInterval(final Integer networkLoopProtectionInterval) {
        this.networkLoopProtectionInterval = networkLoopProtectionInterval;
    }

    /**
     *
     * @return The enableNetworkLoopProtection
     */
    public Boolean getEnableNetworkLoopProtection() {
        return enableNetworkLoopProtection;
    }

    /**
     *
     * @param enableNetworkLoopProtection
     *            The enableNetworkLoopProtection
     */
    public void setEnableNetworkLoopProtection(final Boolean enableNetworkLoopProtection) {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     *
     * @return The enableFastMacCacheFailover
     */
    public Boolean getEnableFastMacCacheFailover() {
        return enableFastMacCacheFailover;
    }

    /**
     *
     * @param enableFastMacCacheFailover
     *            The enableFastMacCacheFailover
     */
    public void setEnableFastMacCacheFailover(final Boolean enableFastMacCacheFailover) {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     *
     * @return The roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     *
     * @param roles
     *            The roles
     */
    public void setRoles(final List<String> roles) {
        this.roles = roles;
    }

    /**
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *            The description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     *            The state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     *
     * @return The eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     *
     * @param eTag
     *            The eTag
     */
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     *
     * @return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *            The created
     */
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     *
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     *            The modified
     */
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *            The category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     *
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     *            The uri
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(serialNumber).append(logicalInterconnectUri).append(ports).append(partNumber)
                .append(powerStatus).append(ipAddressList).append(snmpConfiguration).append(enclosureUri).append(productName)
                .append(interconnectTypeUri).append(portCount).append(igmpIdleTimeoutInterval).append(enablePauseFloodProtection)
                .append(unsupportedCapabilities).append(enclosureName).append(firmwareVersion).append(interconnectIP).append(model)
                .append(interconnectLocation).append(maxBandwidth).append(subPortCount).append(edgeVirtualBridgingAvailable)
                .append(enableIgmpSnooping).append(networkLoopProtectionInterval).append(enableNetworkLoopProtection)
                .append(enableFastMacCacheFailover).append(roles).append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category).append(uri).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Interconnects) == false) {
            return false;
        }
        final Interconnects rhs = ((Interconnects) other);
        return new EqualsBuilder().append(serialNumber, rhs.serialNumber)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri).append(ports, rhs.ports)
                .append(partNumber, rhs.partNumber).append(powerStatus, rhs.powerStatus).append(ipAddressList, rhs.ipAddressList)
                .append(snmpConfiguration, rhs.snmpConfiguration).append(enclosureUri, rhs.enclosureUri)
                .append(productName, rhs.productName).append(interconnectTypeUri, rhs.interconnectTypeUri)
                .append(portCount, rhs.portCount).append(igmpIdleTimeoutInterval, rhs.igmpIdleTimeoutInterval)
                .append(enablePauseFloodProtection, rhs.enablePauseFloodProtection)
                .append(unsupportedCapabilities, rhs.unsupportedCapabilities).append(enclosureName, rhs.enclosureName)
                .append(firmwareVersion, rhs.firmwareVersion).append(interconnectIP, rhs.interconnectIP).append(model, rhs.model)
                .append(interconnectLocation, rhs.interconnectLocation).append(maxBandwidth, rhs.maxBandwidth)
                .append(subPortCount, rhs.subPortCount).append(edgeVirtualBridgingAvailable, rhs.edgeVirtualBridgingAvailable)
                .append(enableIgmpSnooping, rhs.enableIgmpSnooping)
                .append(networkLoopProtectionInterval, rhs.networkLoopProtectionInterval)
                .append(enableNetworkLoopProtection, rhs.enableNetworkLoopProtection)
                .append(enableFastMacCacheFailover, rhs.enableFastMacCacheFailover).append(roles, rhs.roles)
                .append(description, rhs.description).append(status, rhs.status).append(name, rhs.name).append(state, rhs.state)
                .append(eTag, rhs.eTag).append(created, rhs.created).append(modified, rhs.modified).append(category, rhs.category)
                .append(uri, rhs.uri).isEquals();
    }

    public static enum MaxBandwidth {

        Auto("Auto"), Speed_1M("Speed_1M"), Speed_10M("Speed_10M"), Speed_100M("Speed_100M"), Speed_1G("Speed_1G"), Speed_2G(
                "Speed_2G"), Speed_2_5G("Speed_2_5G"), Speed_4G("Speed_4G"), Speed_5G("Speed_5G"), Speed_8G("Speed_8G"), Speed_10G(
                "Speed_10G"), Speed_12G("Speed_12G"), Speed_13G("Speed_13G"), Speed_15G("Speed_15G"), Speed_16G("Speed_16G"), Speed_20G(
                "Speed_20G"), Speed_24G("Speed_24G"), Speed_30G("Speed_30G"), Speed_40G("Speed_40G"), Speed_100G("Speed_100G");
        private final String value;
        private static Map<String, Interconnects.MaxBandwidth> constants = new HashMap<String, Interconnects.MaxBandwidth>();

        static {
            for (final Interconnects.MaxBandwidth c : values()) {
                constants.put(c.value, c);
            }
        }

        private MaxBandwidth(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Interconnects.MaxBandwidth fromValue(final String value) {
            final Interconnects.MaxBandwidth constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum PowerStatus {

        On("On"), Off("Off"), Unknown("Unknown");
        private final String value;
        private static Map<String, Interconnects.PowerStatus> constants = new HashMap<String, Interconnects.PowerStatus>();

        static {
            for (final Interconnects.PowerStatus c : values()) {
                constants.put(c.value, c);
            }
        }

        private PowerStatus(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Interconnects.PowerStatus fromValue(final String value) {
            final Interconnects.PowerStatus constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
