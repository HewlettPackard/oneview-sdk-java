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

public class UplinkSet implements Serializable {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private UplinkSet.Mode mode;
    private List<String> networkUris = new ArrayList<String>();
    private UplinkSet.Reachability reachability;
    private String type;
    /**
     *
     * (Required)
     *
     */
    private UplinkSet.NetworkType networkType;
    /**
     *
     * (Required)
     *
     */
    private List<LogicalPortConfigInfo> logicalPortConfigInfos = new ArrayList<LogicalPortConfigInfo>();
    private UplinkSet.LacpTimer lacpTimer;
    /**
     *
     * (Required)
     *
     */
    private UplinkSet.EthernetNetworkType ethernetNetworkType;
    private PrimaryPort primaryPort;
    private String nativeNetworkUri;
    private String name;

    /**
     *
     * @return The mode
     */
    public UplinkSet.Mode getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     *            The mode
     */
    public void setMode(final UplinkSet.Mode mode) {
        this.mode = mode;
    }

    /**
     *
     * @return The networkUris
     */
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     *
     * @param networkUris
     *            The networkUris
     */
    public void setNetworkUris(final List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     *
     * @return The reachability
     */
    public UplinkSet.Reachability getReachability() {
        return reachability;
    }

    /**
     *
     * @param reachability
     *            The reachability
     */
    public void setReachability(final UplinkSet.Reachability reachability) {
        this.reachability = reachability;
    }

    /**
     *
     * (Required)
     *
     * @return The networkType
     */
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
    public void setNetworkType(final UplinkSet.NetworkType networkType) {
        this.networkType = networkType;
    }

    /**
     *
     * (Required)
     *
     * @return The logicalPortConfigInfos
     */
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
    public void setLogicalPortConfigInfos(final List<LogicalPortConfigInfo> logicalPortConfigInfos) {
        this.logicalPortConfigInfos = logicalPortConfigInfos;
    }

    /**
     *
     * @return The lacpTimer
     */
    public UplinkSet.LacpTimer getLacpTimer() {
        return lacpTimer;
    }

    /**
     *
     * @param lacpTimer
     *            The lacpTimer
     */
    public void setLacpTimer(final UplinkSet.LacpTimer lacpTimer) {
        this.lacpTimer = lacpTimer;
    }

    /**
     *
     * (Required)
     *
     * @return The ethernetNetworkType
     */
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
    public void setEthernetNetworkType(final UplinkSet.EthernetNetworkType ethernetNetworkType) {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    /**
     *
     * @return The primaryPort
     */
    public PrimaryPort getPrimaryPort() {
        return primaryPort;
    }

    /**
     *
     * @param primaryPort
     *            The primaryPort
     */
    public void setPrimaryPort(final PrimaryPort primaryPort) {
        this.primaryPort = primaryPort;
    }

    /**
     *
     * @return The nativeNetworkUri
     */
    public String getNativeNetworkUri() {
        return nativeNetworkUri;
    }

    /**
     *
     * @param nativeNetworkUri
     *            The nativeNetworkUri
     */
    public void setNativeNetworkUri(final String nativeNetworkUri) {
        this.nativeNetworkUri = nativeNetworkUri;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
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

        @Override
        public String toString() {
            return this.value;
        }

        public static UplinkSet.EthernetNetworkType fromValue(final String value) {
            final UplinkSet.EthernetNetworkType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

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

        @Override
        public String toString() {
            return this.value;
        }

        public static UplinkSet.LacpTimer fromValue(final String value) {
            final UplinkSet.LacpTimer constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

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

        @Override
        public String toString() {
            return this.value;
        }

        public static UplinkSet.Mode fromValue(final String value) {
            final UplinkSet.Mode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

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

        @Override
        public String toString() {
            return this.value;
        }

        public static UplinkSet.NetworkType fromValue(final String value) {
            final UplinkSet.NetworkType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

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

        @Override
        public String toString() {
            return this.value;
        }

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
