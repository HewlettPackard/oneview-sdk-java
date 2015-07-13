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
        "reachability", "networkType", "portConfigInfos",
        "networkUris", "fcNetworkUris", "logicalInterconnectUri", "lacpTimer",
        "manualLoginRedistributionState", "connectionMode",
        "ethernetNetworkType", "primaryPortLocation", "nativeNetworkUri",
        "description", "status", "name", "state", "created", "eTag",
        "modified", "category", "uri", "type"
})
public class UplinkSets implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("reachability")
    private UplinkSets.Reachability reachability;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("networkType")
    private UplinkSets.NetworkType networkType;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("portConfigInfos")
    private List<PortConfigInfo> portConfigInfos = new ArrayList<PortConfigInfo>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("networkUris")
    private List<String> networkUris = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("fcNetworkUris")
    private List<String> fcNetworkUris = new ArrayList<String>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("logicalInterconnectUri")
    private String logicalInterconnectUri;
    @JsonProperty("lacpTimer")
    private UplinkSets.LacpTimer lacpTimer;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("manualLoginRedistributionState")
    private UplinkSets.ManualLoginRedistributionState manualLoginRedistributionState;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("connectionMode")
    private UplinkSets.ConnectionMode connectionMode;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("ethernetNetworkType")
    private UplinkSets.EthernetNetworkType ethernetNetworkType;
    @JsonProperty("primaryPortLocation")
    private PrimaryPortLocation primaryPortLocation;
    @JsonProperty("nativeNetworkUri")
    private String nativeNetworkUri;
    @JsonProperty("description")
    private String description;
    @JsonProperty("status")
    private String status;
    @JsonProperty("name")
    private String name;
    @JsonProperty("state")
    private String state;
    @JsonProperty("created")
    private String created;
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("category")
    private String category;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("type")
    private String type;

    /**
     * 
     * @return The reachability
     */
    @JsonProperty("reachability")
    public UplinkSets.Reachability getReachability()
    {
        return reachability;
    }

    /**
     * 
     * @param reachability
     *        The reachability
     */
    @JsonProperty("reachability")
    public void setReachability(UplinkSets.Reachability reachability)
    {
        this.reachability = reachability;
    }

    /**
     * 
     * (Required)
     * 
     * @return The networkType
     */
    @JsonProperty("networkType")
    public UplinkSets.NetworkType getNetworkType()
    {
        return networkType;
    }

    /**
     * 
     * (Required)
     * 
     * @param networkType
     *        The networkType
     */
    @JsonProperty("networkType")
    public void setNetworkType(UplinkSets.NetworkType networkType)
    {
        this.networkType = networkType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The portConfigInfos
     */
    @JsonProperty("portConfigInfos")
    public List<PortConfigInfo> getPortConfigInfos()
    {
        return portConfigInfos;
    }

    /**
     * 
     * (Required)
     * 
     * @param portConfigInfos
     *        The portConfigInfos
     */
    @JsonProperty("portConfigInfos")
    public void setPortConfigInfos(List<PortConfigInfo> portConfigInfos)
    {
        this.portConfigInfos = portConfigInfos;
    }

    /**
     * 
     * (Required)
     * 
     * @return The networkUris
     */
    @JsonProperty("networkUris")
    public List<String> getNetworkUris()
    {
        return networkUris;
    }

    /**
     * 
     * (Required)
     * 
     * @param networkUris
     *        The networkUris
     */
    @JsonProperty("networkUris")
    public void setNetworkUris(List<String> networkUris)
    {
        this.networkUris = networkUris;
    }

    /**
     * 
     * (Required)
     * 
     * @return The fcNetworkUris
     */
    @JsonProperty("fcNetworkUris")
    public List<String> getFcNetworkUris()
    {
        return fcNetworkUris;
    }

    /**
     * 
     * (Required)
     * 
     * @param fcNetworkUris
     *        The fcNetworkUris
     */
    @JsonProperty("fcNetworkUris")
    public void setFcNetworkUris(List<String> fcNetworkUris)
    {
        this.fcNetworkUris = fcNetworkUris;
    }

    /**
     * 
     * (Required)
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
     * (Required)
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
     * @return The lacpTimer
     */
    @JsonProperty("lacpTimer")
    public UplinkSets.LacpTimer getLacpTimer()
    {
        return lacpTimer;
    }

    /**
     * 
     * @param lacpTimer
     *        The lacpTimer
     */
    @JsonProperty("lacpTimer")
    public void setLacpTimer(UplinkSets.LacpTimer lacpTimer)
    {
        this.lacpTimer = lacpTimer;
    }

    /**
     * 
     * (Required)
     * 
     * @return The manualLoginRedistributionState
     */
    @JsonProperty("manualLoginRedistributionState")
    public UplinkSets.ManualLoginRedistributionState getManualLoginRedistributionState()
    {
        return manualLoginRedistributionState;
    }

    /**
     * 
     * (Required)
     * 
     * @param manualLoginRedistributionState
     *        The manualLoginRedistributionState
     */
    @JsonProperty("manualLoginRedistributionState")
    public void setManualLoginRedistributionState(
            UplinkSets.ManualLoginRedistributionState manualLoginRedistributionState)
    {
        this.manualLoginRedistributionState = manualLoginRedistributionState;
    }

    /**
     * 
     * (Required)
     * 
     * @return The connectionMode
     */
    @JsonProperty("connectionMode")
    public UplinkSets.ConnectionMode getConnectionMode()
    {
        return connectionMode;
    }

    /**
     * 
     * (Required)
     * 
     * @param connectionMode
     *        The connectionMode
     */
    @JsonProperty("connectionMode")
    public void setConnectionMode(UplinkSets.ConnectionMode connectionMode)
    {
        this.connectionMode = connectionMode;
    }

    /**
     * 
     * (Required)
     * 
     * @return The ethernetNetworkType
     */
    @JsonProperty("ethernetNetworkType")
    public UplinkSets.EthernetNetworkType getEthernetNetworkType()
    {
        return ethernetNetworkType;
    }

    /**
     * 
     * (Required)
     * 
     * @param ethernetNetworkType
     *        The ethernetNetworkType
     */
    @JsonProperty("ethernetNetworkType")
    public void setEthernetNetworkType(
            UplinkSets.EthernetNetworkType ethernetNetworkType)
    {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    /**
     * 
     * @return The primaryPortLocation
     */
    @JsonProperty("primaryPortLocation")
    public PrimaryPortLocation getPrimaryPortLocation()
    {
        return primaryPortLocation;
    }

    /**
     * 
     * @param primaryPortLocation
     *        The primaryPortLocation
     */
    @JsonProperty("primaryPortLocation")
    public void setPrimaryPortLocation(PrimaryPortLocation primaryPortLocation)
    {
        this.primaryPortLocation = primaryPortLocation;
    }

    /**
     * 
     * @return The nativeNetworkUri
     */
    @JsonProperty("nativeNetworkUri")
    public String getNativeNetworkUri()
    {
        return nativeNetworkUri;
    }

    /**
     * 
     * @param nativeNetworkUri
     *        The nativeNetworkUri
     */
    @JsonProperty("nativeNetworkUri")
    public void setNativeNetworkUri(String nativeNetworkUri)
    {
        this.nativeNetworkUri = nativeNetworkUri;
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

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(type).append(reachability)
                .append(networkType).append(portConfigInfos)
                .append(networkUris).append(fcNetworkUris)
                .append(logicalInterconnectUri).append(lacpTimer)
                .append(manualLoginRedistributionState).append(connectionMode)
                .append(ethernetNetworkType).append(primaryPortLocation)
                .append(nativeNetworkUri).append(description).append(status)
                .append(name).append(state).append(created).append(eTag)
                .append(modified).append(category).append(uri).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof UplinkSets) == false)
        {
            return false;
        }
        UplinkSets rhs = ((UplinkSets) other);
        return new EqualsBuilder()
                .append(type, rhs.type)
                .append(reachability, rhs.reachability)
                .append(networkType, rhs.networkType)
                .append(portConfigInfos, rhs.portConfigInfos)
                .append(networkUris, rhs.networkUris)
                .append(fcNetworkUris, rhs.fcNetworkUris)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .append(lacpTimer, rhs.lacpTimer)
                .append(manualLoginRedistributionState,
                        rhs.manualLoginRedistributionState)
                .append(connectionMode, rhs.connectionMode)
                .append(ethernetNetworkType, rhs.ethernetNetworkType)
                .append(primaryPortLocation, rhs.primaryPortLocation)
                .append(nativeNetworkUri, rhs.nativeNetworkUri)
                .append(description, rhs.description)
                .append(status, rhs.status).append(name, rhs.name)
                .append(state, rhs.state).append(created, rhs.created)
                .append(eTag, rhs.eTag).append(modified, rhs.modified)
                .append(category, rhs.category).append(uri, rhs.uri).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum ConnectionMode
    {

        Auto ("Auto"),
        Failover ("Failover");
        private final String value;
        private static Map<String, UplinkSets.ConnectionMode> constants = new HashMap<String, UplinkSets.ConnectionMode>();

        static
        {
            for (UplinkSets.ConnectionMode c : values())
            {
                constants.put(c.value, c);
            }
        }

        private ConnectionMode(String value)
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
        public static UplinkSets.ConnectionMode fromValue(String value)
        {
            UplinkSets.ConnectionMode constant = constants.get(value);
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
    public static enum EthernetNetworkType
    {

        Tagged ("Tagged"),
        Untagged ("Untagged"),
        Tunnel ("Tunnel"),
        Unknown (
                "Unknown"),
        NotApplicable ("NotApplicable");
        private final String value;
        private static Map<String, UplinkSets.EthernetNetworkType> constants = new HashMap<String, UplinkSets.EthernetNetworkType>();

        static
        {
            for (UplinkSets.EthernetNetworkType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private EthernetNetworkType(String value)
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
        public static UplinkSets.EthernetNetworkType fromValue(String value)
        {
            UplinkSets.EthernetNetworkType constant = constants.get(value);
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
    public static enum LacpTimer
    {

        Short ("Short"),
        Long ("Long");
        private final String value;
        private static Map<String, UplinkSets.LacpTimer> constants = new HashMap<String, UplinkSets.LacpTimer>();

        static
        {
            for (UplinkSets.LacpTimer c : values())
            {
                constants.put(c.value, c);
            }
        }

        private LacpTimer(String value)
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
        public static UplinkSets.LacpTimer fromValue(String value)
        {
            UplinkSets.LacpTimer constant = constants.get(value);
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
    public static enum ManualLoginRedistributionState
    {

        Supported ("Supported"),
        NotSupported ("NotSupported"),
        Distributing (
                "Distributing"),
        Distributed ("Distributed"),
        DistributionFailed (
                "DistributionFailed");
        private final String value;
        private static Map<String, UplinkSets.ManualLoginRedistributionState> constants = new HashMap<String, UplinkSets.ManualLoginRedistributionState>();

        static
        {
            for (UplinkSets.ManualLoginRedistributionState c : values())
            {
                constants.put(c.value, c);
            }
        }

        private ManualLoginRedistributionState(String value)
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
        public static UplinkSets.ManualLoginRedistributionState fromValue(
                String value)
        {
            UplinkSets.ManualLoginRedistributionState constant = constants
                    .get(value);
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
    public static enum NetworkType
    {

        Ethernet ("Ethernet"),
        FibreChannel ("FibreChannel");
        private final String value;
        private static Map<String, UplinkSets.NetworkType> constants = new HashMap<String, UplinkSets.NetworkType>();

        static
        {
            for (UplinkSets.NetworkType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private NetworkType(String value)
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
        public static UplinkSets.NetworkType fromValue(String value)
        {
            UplinkSets.NetworkType constant = constants.get(value);
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
    public static enum Reachability
    {

        Unknown ("Unknown"),
        NotReachable ("NotReachable"),
        Reachable ("Reachable"),
        RedundantlyReachable (
                "RedundantlyReachable");
        private final String value;
        private static Map<String, UplinkSets.Reachability> constants = new HashMap<String, UplinkSets.Reachability>();

        static
        {
            for (UplinkSets.Reachability c : values())
            {
                constants.put(c.value, c);
            }
        }

        private Reachability(String value)
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
        public static UplinkSets.Reachability fromValue(String value)
        {
            UplinkSets.Reachability constant = constants.get(value);
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
