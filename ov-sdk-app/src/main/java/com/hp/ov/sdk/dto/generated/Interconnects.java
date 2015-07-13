/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "serialNumber", "logicalInterconnectUri", "ports",
        "partNumber", "powerStatus", "ipAddressList", "snmpConfiguration",
        "enclosureUri", "productName", "interconnectTypeUri", "portCount",
        "igmpIdleTimeoutInterval", "enablePauseFloodProtection",
        "unsupportedCapabilities", "enclosureName", "firmwareVersion",
        "interconnectIP", "model", "interconnectLocation", "maxBandwidth",
        "subPortCount", "edgeVirtualBridgingAvailable", "enableIgmpSnooping",
        "networkLoopProtectionInterval", "enableNetworkLoopProtection",
        "enableFastMacCacheFailover", "roles", "description", "status", "name",
        "state", "eTag", "created", "modified", "category", "uri"
})
public class Interconnects implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("logicalInterconnectUri")
    private String logicalInterconnectUri;
    @JsonProperty("ports")
    private List<Port> ports = new ArrayList<Port>();
    @JsonProperty("partNumber")
    private String partNumber;
    @JsonProperty("powerStatus")
    private Interconnects.PowerStatus powerStatus;
    @JsonProperty("ipAddressList")
    private List<IpAddressList> ipAddressList = new ArrayList<IpAddressList>();
    @JsonProperty("snmpConfiguration")
    private SnmpConfiguration snmpConfiguration;
    @JsonProperty("enclosureUri")
    private String enclosureUri;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("interconnectTypeUri")
    private String interconnectTypeUri;
    @JsonProperty("portCount")
    private Integer portCount;
    @JsonProperty("igmpIdleTimeoutInterval")
    private Integer igmpIdleTimeoutInterval;
    @JsonProperty("enablePauseFloodProtection")
    private Boolean enablePauseFloodProtection;
    @JsonProperty("unsupportedCapabilities")
    private List<String> unsupportedCapabilities = new ArrayList<String>();
    @JsonProperty("enclosureName")
    private String enclosureName;
    @JsonProperty("firmwareVersion")
    private String firmwareVersion;
    @JsonProperty("interconnectIP")
    private String interconnectIP;
    @JsonProperty("model")
    private String model;
    @JsonProperty("interconnectLocation")
    private InterconnectLocation interconnectLocation;
    @JsonProperty("maxBandwidth")
    private Interconnects.MaxBandwidth maxBandwidth;
    @JsonProperty("subPortCount")
    private Integer subPortCount;
    @JsonProperty("edgeVirtualBridgingAvailable")
    private Boolean edgeVirtualBridgingAvailable;
    @JsonProperty("enableIgmpSnooping")
    private Boolean enableIgmpSnooping;
    @JsonProperty("networkLoopProtectionInterval")
    private Integer networkLoopProtectionInterval;
    @JsonProperty("enableNetworkLoopProtection")
    private Boolean enableNetworkLoopProtection;
    @JsonProperty("enableFastMacCacheFailover")
    private Boolean enableFastMacCacheFailover;
    @JsonProperty("roles")
    private List<String> roles = new ArrayList<String>();
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("category")
    private String category;
    @JsonProperty("uri")
    private String uri;

    /**
     * 
     * @return The serialNumber
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber()
    {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber
     *        The serialNumber
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    /**
     * 
     * @return The logicalInterconnectUri
     */
    @JsonProperty("logicalInterconnectUri")
    public String getLogicalInterconnectUri()
    {
        return logicalInterconnectUri;
    }

    /**
     * 
     * @param logicalInterconnectUri
     *        The logicalInterconnectUri
     */
    @JsonProperty("logicalInterconnectUri")
    public void setLogicalInterconnectUri(String logicalInterconnectUri)
    {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    /**
     * 
     * @return The ports
     */
    @JsonProperty("ports")
    public List<Port> getPorts()
    {
        return ports;
    }

    /**
     * 
     * @param ports
     *        The ports
     */
    @JsonProperty("ports")
    public void setPorts(List<Port> ports)
    {
        this.ports = ports;
    }

    /**
     * 
     * @return The partNumber
     */
    @JsonProperty("partNumber")
    public String getPartNumber()
    {
        return partNumber;
    }

    /**
     * 
     * @param partNumber
     *        The partNumber
     */
    @JsonProperty("partNumber")
    public void setPartNumber(String partNumber)
    {
        this.partNumber = partNumber;
    }

    /**
     * 
     * @return The powerStatus
     */
    @JsonProperty("powerStatus")
    public Interconnects.PowerStatus getPowerStatus()
    {
        return powerStatus;
    }

    /**
     * 
     * @param powerStatus
     *        The powerStatus
     */
    @JsonProperty("powerStatus")
    public void setPowerStatus(Interconnects.PowerStatus powerStatus)
    {
        this.powerStatus = powerStatus;
    }

    /**
     * 
     * @return The ipAddressList
     */
    @JsonProperty("ipAddressList")
    public List<IpAddressList> getIpAddressList()
    {
        return ipAddressList;
    }

    /**
     * 
     * @param ipAddressList
     *        The ipAddressList
     */
    @JsonProperty("ipAddressList")
    public void setIpAddressList(List<IpAddressList> ipAddressList)
    {
        this.ipAddressList = ipAddressList;
    }

    /**
     * 
     * @return The snmpConfiguration
     */
    @JsonProperty("snmpConfiguration")
    public SnmpConfiguration getSnmpConfiguration()
    {
        return snmpConfiguration;
    }

    /**
     * 
     * @param snmpConfiguration
     *        The snmpConfiguration
     */
    @JsonProperty("snmpConfiguration")
    public void setSnmpConfiguration(SnmpConfiguration snmpConfiguration)
    {
        this.snmpConfiguration = snmpConfiguration;
    }

    /**
     * 
     * @return The enclosureUri
     */
    @JsonProperty("enclosureUri")
    public String getEnclosureUri()
    {
        return enclosureUri;
    }

    /**
     * 
     * @param enclosureUri
     *        The enclosureUri
     */
    @JsonProperty("enclosureUri")
    public void setEnclosureUri(String enclosureUri)
    {
        this.enclosureUri = enclosureUri;
    }

    /**
     * 
     * @return The productName
     */
    @JsonProperty("productName")
    public String getProductName()
    {
        return productName;
    }

    /**
     * 
     * @param productName
     *        The productName
     */
    @JsonProperty("productName")
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    /**
     * 
     * @return The interconnectTypeUri
     */
    @JsonProperty("interconnectTypeUri")
    public String getInterconnectTypeUri()
    {
        return interconnectTypeUri;
    }

    /**
     * 
     * @param interconnectTypeUri
     *        The interconnectTypeUri
     */
    @JsonProperty("interconnectTypeUri")
    public void setInterconnectTypeUri(String interconnectTypeUri)
    {
        this.interconnectTypeUri = interconnectTypeUri;
    }

    /**
     * 
     * @return The portCount
     */
    @JsonProperty("portCount")
    public Integer getPortCount()
    {
        return portCount;
    }

    /**
     * 
     * @param portCount
     *        The portCount
     */
    @JsonProperty("portCount")
    public void setPortCount(Integer portCount)
    {
        this.portCount = portCount;
    }

    /**
     * 
     * @return The igmpIdleTimeoutInterval
     */
    @JsonProperty("igmpIdleTimeoutInterval")
    public Integer getIgmpIdleTimeoutInterval()
    {
        return igmpIdleTimeoutInterval;
    }

    /**
     * 
     * @param igmpIdleTimeoutInterval
     *        The igmpIdleTimeoutInterval
     */
    @JsonProperty("igmpIdleTimeoutInterval")
    public void setIgmpIdleTimeoutInterval(Integer igmpIdleTimeoutInterval)
    {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     * 
     * @return The enablePauseFloodProtection
     */
    @JsonProperty("enablePauseFloodProtection")
    public Boolean getEnablePauseFloodProtection()
    {
        return enablePauseFloodProtection;
    }

    /**
     * 
     * @param enablePauseFloodProtection
     *        The enablePauseFloodProtection
     */
    @JsonProperty("enablePauseFloodProtection")
    public void setEnablePauseFloodProtection(Boolean enablePauseFloodProtection)
    {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     * 
     * @return The unsupportedCapabilities
     */
    @JsonProperty("unsupportedCapabilities")
    public List<String> getUnsupportedCapabilities()
    {
        return unsupportedCapabilities;
    }

    /**
     * 
     * @param unsupportedCapabilities
     *        The unsupportedCapabilities
     */
    @JsonProperty("unsupportedCapabilities")
    public void setUnsupportedCapabilities(List<String> unsupportedCapabilities)
    {
        this.unsupportedCapabilities = unsupportedCapabilities;
    }

    /**
     * 
     * @return The enclosureName
     */
    @JsonProperty("enclosureName")
    public String getEnclosureName()
    {
        return enclosureName;
    }

    /**
     * 
     * @param enclosureName
     *        The enclosureName
     */
    @JsonProperty("enclosureName")
    public void setEnclosureName(String enclosureName)
    {
        this.enclosureName = enclosureName;
    }

    /**
     * 
     * @return The firmwareVersion
     */
    @JsonProperty("firmwareVersion")
    public String getFirmwareVersion()
    {
        return firmwareVersion;
    }

    /**
     * 
     * @param firmwareVersion
     *        The firmwareVersion
     */
    @JsonProperty("firmwareVersion")
    public void setFirmwareVersion(String firmwareVersion)
    {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * 
     * @return The interconnectIP
     */
    @JsonProperty("interconnectIP")
    public String getInterconnectIP()
    {
        return interconnectIP;
    }

    /**
     * 
     * @param interconnectIP
     *        The interconnectIP
     */
    @JsonProperty("interconnectIP")
    public void setInterconnectIP(String interconnectIP)
    {
        this.interconnectIP = interconnectIP;
    }

    /**
     * 
     * @return The model
     */
    @JsonProperty("model")
    public String getModel()
    {
        return model;
    }

    /**
     * 
     * @param model
     *        The model
     */
    @JsonProperty("model")
    public void setModel(String model)
    {
        this.model = model;
    }

    /**
     * 
     * @return The interconnectLocation
     */
    @JsonProperty("interconnectLocation")
    public InterconnectLocation getInterconnectLocation()
    {
        return interconnectLocation;
    }

    /**
     * 
     * @param interconnectLocation
     *        The interconnectLocation
     */
    @JsonProperty("interconnectLocation")
    public void setInterconnectLocation(
            InterconnectLocation interconnectLocation)
    {
        this.interconnectLocation = interconnectLocation;
    }

    /**
     * 
     * @return The maxBandwidth
     */
    @JsonProperty("maxBandwidth")
    public Interconnects.MaxBandwidth getMaxBandwidth()
    {
        return maxBandwidth;
    }

    /**
     * 
     * @param maxBandwidth
     *        The maxBandwidth
     */
    @JsonProperty("maxBandwidth")
    public void setMaxBandwidth(Interconnects.MaxBandwidth maxBandwidth)
    {
        this.maxBandwidth = maxBandwidth;
    }

    /**
     * 
     * @return The subPortCount
     */
    @JsonProperty("subPortCount")
    public Integer getSubPortCount()
    {
        return subPortCount;
    }

    /**
     * 
     * @param subPortCount
     *        The subPortCount
     */
    @JsonProperty("subPortCount")
    public void setSubPortCount(Integer subPortCount)
    {
        this.subPortCount = subPortCount;
    }

    /**
     * 
     * @return The edgeVirtualBridgingAvailable
     */
    @JsonProperty("edgeVirtualBridgingAvailable")
    public Boolean getEdgeVirtualBridgingAvailable()
    {
        return edgeVirtualBridgingAvailable;
    }

    /**
     * 
     * @param edgeVirtualBridgingAvailable
     *        The edgeVirtualBridgingAvailable
     */
    @JsonProperty("edgeVirtualBridgingAvailable")
    public void setEdgeVirtualBridgingAvailable(
            Boolean edgeVirtualBridgingAvailable)
    {
        this.edgeVirtualBridgingAvailable = edgeVirtualBridgingAvailable;
    }

    /**
     * 
     * @return The enableIgmpSnooping
     */
    @JsonProperty("enableIgmpSnooping")
    public Boolean getEnableIgmpSnooping()
    {
        return enableIgmpSnooping;
    }

    /**
     * 
     * @param enableIgmpSnooping
     *        The enableIgmpSnooping
     */
    @JsonProperty("enableIgmpSnooping")
    public void setEnableIgmpSnooping(Boolean enableIgmpSnooping)
    {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     * 
     * @return The networkLoopProtectionInterval
     */
    @JsonProperty("networkLoopProtectionInterval")
    public Integer getNetworkLoopProtectionInterval()
    {
        return networkLoopProtectionInterval;
    }

    /**
     * 
     * @param networkLoopProtectionInterval
     *        The networkLoopProtectionInterval
     */
    @JsonProperty("networkLoopProtectionInterval")
    public void setNetworkLoopProtectionInterval(
            Integer networkLoopProtectionInterval)
    {
        this.networkLoopProtectionInterval = networkLoopProtectionInterval;
    }

    /**
     * 
     * @return The enableNetworkLoopProtection
     */
    @JsonProperty("enableNetworkLoopProtection")
    public Boolean getEnableNetworkLoopProtection()
    {
        return enableNetworkLoopProtection;
    }

    /**
     * 
     * @param enableNetworkLoopProtection
     *        The enableNetworkLoopProtection
     */
    @JsonProperty("enableNetworkLoopProtection")
    public void setEnableNetworkLoopProtection(
            Boolean enableNetworkLoopProtection)
    {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     * 
     * @return The enableFastMacCacheFailover
     */
    @JsonProperty("enableFastMacCacheFailover")
    public Boolean getEnableFastMacCacheFailover()
    {
        return enableFastMacCacheFailover;
    }

    /**
     * 
     * @param enableFastMacCacheFailover
     *        The enableFastMacCacheFailover
     */
    @JsonProperty("enableFastMacCacheFailover")
    public void setEnableFastMacCacheFailover(Boolean enableFastMacCacheFailover)
    {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     * 
     * @return The roles
     */
    @JsonProperty("roles")
    public List<String> getRoles()
    {
        return roles;
    }

    /**
     * 
     * @param roles
     *        The roles
     */
    @JsonProperty("roles")
    public void setRoles(List<String> roles)
    {
        this.roles = roles;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     * @param description
     *        The description
     */
    @JsonProperty("description")
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    /**
     * 
     * @param status
     *        The status
     */
    @JsonProperty("status")
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag()
    {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *        The eTag
     */
    @JsonProperty("eTag")
    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated()
    {
        return created;
    }

    /**
     * 
     * @param created
     *        The created
     */
    @JsonProperty("created")
    public void setCreated(String created)
    {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified()
    {
        return modified;
    }

    /**
     * 
     * @param modified
     *        The modified
     */
    @JsonProperty("modified")
    public void setModified(String modified)
    {
        this.modified = modified;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory()
    {
        return category;
    }

    /**
     * 
     * @param category
     *        The category
     */
    @JsonProperty("category")
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri()
    {
        return uri;
    }

    /**
     * 
     * @param uri
     *        The uri
     */
    @JsonProperty("uri")
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(serialNumber)
                .append(logicalInterconnectUri).append(ports)
                .append(partNumber).append(powerStatus).append(ipAddressList)
                .append(snmpConfiguration).append(enclosureUri)
                .append(productName).append(interconnectTypeUri)
                .append(portCount).append(igmpIdleTimeoutInterval)
                .append(enablePauseFloodProtection)
                .append(unsupportedCapabilities).append(enclosureName)
                .append(firmwareVersion).append(interconnectIP).append(model)
                .append(interconnectLocation).append(maxBandwidth)
                .append(subPortCount).append(edgeVirtualBridgingAvailable)
                .append(enableIgmpSnooping)
                .append(networkLoopProtectionInterval)
                .append(enableNetworkLoopProtection)
                .append(enableFastMacCacheFailover).append(roles)
                .append(description).append(status).append(name).append(state)
                .append(eTag).append(created).append(modified).append(category)
                .append(uri).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Interconnects) == false)
        {
            return false;
        }
        Interconnects rhs = ((Interconnects) other);
        return new EqualsBuilder()
                .append(serialNumber, rhs.serialNumber)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .append(ports, rhs.ports)
                .append(partNumber, rhs.partNumber)
                .append(powerStatus, rhs.powerStatus)
                .append(ipAddressList, rhs.ipAddressList)
                .append(snmpConfiguration, rhs.snmpConfiguration)
                .append(enclosureUri, rhs.enclosureUri)
                .append(productName, rhs.productName)
                .append(interconnectTypeUri, rhs.interconnectTypeUri)
                .append(portCount, rhs.portCount)
                .append(igmpIdleTimeoutInterval, rhs.igmpIdleTimeoutInterval)
                .append(enablePauseFloodProtection,
                        rhs.enablePauseFloodProtection)
                .append(unsupportedCapabilities, rhs.unsupportedCapabilities)
                .append(enclosureName, rhs.enclosureName)
                .append(firmwareVersion, rhs.firmwareVersion)
                .append(interconnectIP, rhs.interconnectIP)
                .append(model, rhs.model)
                .append(interconnectLocation, rhs.interconnectLocation)
                .append(maxBandwidth, rhs.maxBandwidth)
                .append(subPortCount, rhs.subPortCount)
                .append(edgeVirtualBridgingAvailable,
                        rhs.edgeVirtualBridgingAvailable)
                .append(enableIgmpSnooping, rhs.enableIgmpSnooping)
                .append(networkLoopProtectionInterval,
                        rhs.networkLoopProtectionInterval)
                .append(enableNetworkLoopProtection,
                        rhs.enableNetworkLoopProtection)
                .append(enableFastMacCacheFailover,
                        rhs.enableFastMacCacheFailover)
                .append(roles, rhs.roles).append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(eTag, rhs.eTag)
                .append(created, rhs.created).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum MaxBandwidth
    {

        Auto ("Auto"),
        Speed_1M ("Speed_1M"),
        Speed_10M ("Speed_10M"),
        Speed_100M (
                "Speed_100M"),
        Speed_1G ("Speed_1G"),
        Speed_2G ("Speed_2G"),
        Speed_2_5G (
                "Speed_2_5G"),
        Speed_4G ("Speed_4G"),
        Speed_5G ("Speed_5G"),
        Speed_8G (
                "Speed_8G"),
        Speed_10G ("Speed_10G"),
        Speed_12G ("Speed_12G"),
        Speed_13G (
                "Speed_13G"),
        Speed_15G ("Speed_15G"),
        Speed_16G ("Speed_16G"),
        Speed_20G (
                "Speed_20G"),
        Speed_24G ("Speed_24G"),
        Speed_30G ("Speed_30G"),
        Speed_40G (
                "Speed_40G"),
        Speed_100G ("Speed_100G");
        private final String value;
        private static Map<String, Interconnects.MaxBandwidth> constants = new HashMap<String, Interconnects.MaxBandwidth>();

        static
        {
            for (Interconnects.MaxBandwidth c : values())
            {
                constants.put(c.value, c);
            }
        }

        private MaxBandwidth(String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static Interconnects.MaxBandwidth fromValue(String value)
        {
            Interconnects.MaxBandwidth constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PowerStatus
    {

        On ("On"),
        Off ("Off"),
        Unknown ("Unknown");
        private final String value;
        private static Map<String, Interconnects.PowerStatus> constants = new HashMap<String, Interconnects.PowerStatus>();

        static
        {
            for (Interconnects.PowerStatus c : values())
            {
                constants.put(c.value, c);
            }
        }

        private PowerStatus(String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static Interconnects.PowerStatus fromValue(String value)
        {
            Interconnects.PowerStatus constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

}
