/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "mode", "networkUris", "reachability", "networkType", "logicalPortConfigInfos", "lacpTimer",
        "ethernetNetworkType", "primaryPort", "nativeNetworkUri", "name", "type" })
public class UplinkSet implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("mode")
    private UplinkSet.Mode mode;
    @JsonProperty("networkUris")
    private List<String> networkUris = new ArrayList<String>();
    @JsonProperty("reachability")
    private UplinkSet.Reachability reachability;
    @JsonProperty("type")
    private String type;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("networkType")
    private UplinkSet.NetworkType networkType;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("logicalPortConfigInfos")
    private List<LogicalPortConfigInfo> logicalPortConfigInfos = new ArrayList<LogicalPortConfigInfo>();
    @JsonProperty("lacpTimer")
    private UplinkSet.LacpTimer lacpTimer;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("ethernetNetworkType")
    private UplinkSet.EthernetNetworkType ethernetNetworkType;
    @JsonProperty("primaryPort")
    private PrimaryPort primaryPort;
    @JsonProperty("nativeNetworkUri")
    private String nativeNetworkUri;
    @JsonProperty("name")
    private String name;

    /**
     * 
     * @return The mode
     */
    @JsonProperty("mode")
    public UplinkSet.Mode getMode() {
        return mode;
    }

    /**
     * 
     * @param mode
     *            The mode
     */
    @JsonProperty("mode")
    public void setMode(final UplinkSet.Mode mode) {
        this.mode = mode;
    }

    /**
     * 
     * @return The networkUris
     */
    @JsonProperty("networkUris")
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     * 
     * @param networkUris
     *            The networkUris
     */
    @JsonProperty("networkUris")
    public void setNetworkUris(final List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     * 
     * @return The reachability
     */
    @JsonProperty("reachability")
    public UplinkSet.Reachability getReachability() {
        return reachability;
    }

    /**
     * 
     * @param reachability
     *            The reachability
     */
    @JsonProperty("reachability")
    public void setReachability(final UplinkSet.Reachability reachability) {
        this.reachability = reachability;
    }

    /**
     * 
     * (Required)
     * 
     * @return The networkType
     */
    @JsonProperty("networkType")
    public UplinkSet.NetworkType getNetworkType() {
        return networkType;
    }

    /**
     * 
     * (Required)
     * 
     * @param networkType
     *            The networkType
     */
    @JsonProperty("networkType")
    public void setNetworkType(final UplinkSet.NetworkType networkType) {
        this.networkType = networkType;
    }

    /**
     * 
     * (Required)
     * 
     * @return The logicalPortConfigInfos
     */
    @JsonProperty("logicalPortConfigInfos")
    public List<LogicalPortConfigInfo> getLogicalPortConfigInfos() {
        return logicalPortConfigInfos;
    }

    /**
     * 
     * (Required)
     * 
     * @param logicalPortConfigInfos
     *            The logicalPortConfigInfos
     */
    @JsonProperty("logicalPortConfigInfos")
    public void setLogicalPortConfigInfos(final List<LogicalPortConfigInfo> logicalPortConfigInfos) {
        this.logicalPortConfigInfos = logicalPortConfigInfos;
    }

    /**
     * 
     * @return The lacpTimer
     */
    @JsonProperty("lacpTimer")
    public UplinkSet.LacpTimer getLacpTimer() {
        return lacpTimer;
    }

    /**
     * 
     * @param lacpTimer
     *            The lacpTimer
     */
    @JsonProperty("lacpTimer")
    public void setLacpTimer(final UplinkSet.LacpTimer lacpTimer) {
        this.lacpTimer = lacpTimer;
    }

    /**
     * 
     * (Required)
     * 
     * @return The ethernetNetworkType
     */
    @JsonProperty("ethernetNetworkType")
    public UplinkSet.EthernetNetworkType getEthernetNetworkType() {
        return ethernetNetworkType;
    }

    /**
     * 
     * (Required)
     * 
     * @param ethernetNetworkType
     *            The ethernetNetworkType
     */
    @JsonProperty("ethernetNetworkType")
    public void setEthernetNetworkType(final UplinkSet.EthernetNetworkType ethernetNetworkType) {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    /**
     * 
     * @return The primaryPort
     */
    @JsonProperty("primaryPort")
    public PrimaryPort getPrimaryPort() {
        return primaryPort;
    }

    /**
     * 
     * @param primaryPort
     *            The primaryPort
     */
    @JsonProperty("primaryPort")
    public void setPrimaryPort(final PrimaryPort primaryPort) {
        this.primaryPort = primaryPort;
    }

    /**
     * 
     * @return The nativeNetworkUri
     */
    @JsonProperty("nativeNetworkUri")
    public String getNativeNetworkUri() {
        return nativeNetworkUri;
    }

    /**
     * 
     * @param nativeNetworkUri
     *            The nativeNetworkUri
     */
    @JsonProperty("nativeNetworkUri")
    public void setNativeNetworkUri(final String nativeNetworkUri) {
        this.nativeNetworkUri = nativeNetworkUri;
    }

    /**
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    @JsonProperty("name")
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(mode).append(networkUris).append(reachability).append(networkType)
                .append(logicalPortConfigInfos).append(lacpTimer).append(ethernetNetworkType).append(primaryPort)
                .append(nativeNetworkUri).append(name).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UplinkSet) == false) {
            return false;
        }
        final UplinkSet rhs = ((UplinkSet) other);
        return new EqualsBuilder().append(mode, rhs.mode).append(networkUris, rhs.networkUris)
                .append(reachability, rhs.reachability).append(networkType, rhs.networkType)
                .append(logicalPortConfigInfos, rhs.logicalPortConfigInfos).append(lacpTimer, rhs.lacpTimer)
                .append(ethernetNetworkType, rhs.ethernetNetworkType).append(primaryPort, rhs.primaryPort)
                .append(nativeNetworkUri, rhs.nativeNetworkUri).append(name, rhs.name).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum EthernetNetworkType {

        Tagged("Tagged"), Untagged("Untagged"), Tunnel("Tunnel"), Unknown("Unknown"), NotApplicable("NotApplicable");
        private final String value;
        private static Map<String, UplinkSet.EthernetNetworkType> constants = new HashMap<String, UplinkSet.EthernetNetworkType>();

        static {
            for (final UplinkSet.EthernetNetworkType c : values()) {
                constants.put(c.value, c);
            }
        }

        private EthernetNetworkType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static UplinkSet.EthernetNetworkType fromValue(final String value) {
            final UplinkSet.EthernetNetworkType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum LacpTimer {

        Short("Short"), Long("Long");
        private final String value;
        private static Map<String, UplinkSet.LacpTimer> constants = new HashMap<String, UplinkSet.LacpTimer>();

        static {
            for (final UplinkSet.LacpTimer c : values()) {
                constants.put(c.value, c);
            }
        }

        private LacpTimer(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static UplinkSet.LacpTimer fromValue(final String value) {
            final UplinkSet.LacpTimer constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Mode {

        Auto("Auto"), Failover("Failover");
        private final String value;
        private static Map<String, UplinkSet.Mode> constants = new HashMap<String, UplinkSet.Mode>();

        static {
            for (final UplinkSet.Mode c : values()) {
                constants.put(c.value, c);
            }
        }

        private Mode(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static UplinkSet.Mode fromValue(final String value) {
            final UplinkSet.Mode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum NetworkType {

        Ethernet("Ethernet"), FibreChannel("FibreChannel");
        private final String value;
        private static Map<String, UplinkSet.NetworkType> constants = new HashMap<String, UplinkSet.NetworkType>();

        static {
            for (final UplinkSet.NetworkType c : values()) {
                constants.put(c.value, c);
            }
        }

        private NetworkType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static UplinkSet.NetworkType fromValue(final String value) {
            final UplinkSet.NetworkType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum Reachability {

        Unknown("Unknown"), NotReachable("NotReachable"), Reachable("Reachable"), RedundantlyReachable("RedundantlyReachable");
        private final String value;
        private static Map<String, UplinkSet.Reachability> constants = new HashMap<String, UplinkSet.Reachability>();

        static {
            for (final UplinkSet.Reachability c : values()) {
                constants.put(c.value, c);
            }
        }

        private Reachability(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static UplinkSet.Reachability fromValue(final String value) {
            final UplinkSet.Reachability constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
