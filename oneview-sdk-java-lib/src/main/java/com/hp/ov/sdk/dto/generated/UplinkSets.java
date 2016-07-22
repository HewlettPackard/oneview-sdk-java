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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

public class UplinkSets extends BaseModelResource {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    private UplinkSets.Reachability reachability;
    /**
     *
     * (Required)
     *
     */
    private UplinkSets.NetworkType networkType;
    /**
     *
     * (Required)
     *
     */
    private List<PortConfigInfo> portConfigInfos = new ArrayList<PortConfigInfo>();
    /**
     *
     * (Required)
     *
     */
    private List<String> networkUris = new ArrayList<String>();
    /**
     *
     * (Required)
     *
     */
    private List<String> fcNetworkUris = new ArrayList<String>();
    /**
    *
    * (Required)
    *
    */
    @Since(200)
    private List<String> fcoeNetworkUris = new ArrayList<String>();
    /**
     *
     * (Required)
     *
     */
    private String logicalInterconnectUri;
    private UplinkSets.LacpTimer lacpTimer;
    /**
     *
     * (Required)
     *
     */
    private UplinkSets.ManualLoginRedistributionState manualLoginRedistributionState;
    /**
     *
     * (Required)
     *
     */
    private UplinkSets.ConnectionMode connectionMode;
    /**
     *
     * (Required)
     *
     */
    private UplinkSets.EthernetNetworkType ethernetNetworkType;
    private PrimaryPortLocation primaryPortLocation;
    private String nativeNetworkUri;

    /**
     *
     * @return The reachability
     */
    public UplinkSets.Reachability getReachability() {
        return reachability;
    }

    /**
     *
     * @param reachability
     *            The reachability
     */
    public void setReachability(final UplinkSets.Reachability reachability) {
        this.reachability = reachability;
    }

    /**
     *
     * (Required)
     *
     * @return The networkType
     */
    public UplinkSets.NetworkType getNetworkType() {
        return networkType;
    }

    /**
     *
     * (Required)
     *
     * @param networkType
     *            The networkType
     */
    public void setNetworkType(final UplinkSets.NetworkType networkType) {
        this.networkType = networkType;
    }

    /**
     *
     * (Required)
     *
     * @return The portConfigInfos
     */
    public List<PortConfigInfo> getPortConfigInfos() {
        return portConfigInfos;
    }

    /**
     *
     * (Required)
     *
     * @param portConfigInfos
     *            The portConfigInfos
     */
    public void setPortConfigInfos(final List<PortConfigInfo> portConfigInfos) {
        this.portConfigInfos = portConfigInfos;
    }

    /**
     *
     * (Required)
     *
     * @return The networkUris
     */
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     *
     * (Required)
     *
     * @param networkUris
     *            The networkUris
     */
    public void setNetworkUris(final List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     *
     * (Required)
     *
     * @return The fcNetworkUris
     */
    public List<String> getFcNetworkUris() {
        return fcNetworkUris;
    }

    /**
     *
     * (Required)
     *
     * @param fcNetworkUris
     *            The fcNetworkUris
     */
    public void setFcNetworkUris(final List<String> fcNetworkUris) {
        this.fcNetworkUris = fcNetworkUris;
    }

    /**
     *
     * (Required)
     *
     * @return The fcoeNetworkUris
     */
    public List<String> getFcoeNetworkUris() {
        return fcoeNetworkUris;
    }

    /**
     *
     * (Required)
     *
     * @param fcoeNetworkUris
     *            The fcoeNetworkUris
     */
    public void setFcoeNetworkUris(final List<String> fcoeNetworkUris) {
        this.fcoeNetworkUris = fcoeNetworkUris;
    }

    /**
     *
     * (Required)
     *
     * @return The logicalInterconnectUri
     */
    public String getLogicalInterconnectUri() {
        return logicalInterconnectUri;
    }

    /**
     *
     * (Required)
     *
     * @param logicalInterconnectUri
     *            The logicalInterconnectUri
     */
    public void setLogicalInterconnectUri(final String logicalInterconnectUri) {
        this.logicalInterconnectUri = logicalInterconnectUri;
    }

    /**
     *
     * @return The lacpTimer
     */
    public UplinkSets.LacpTimer getLacpTimer() {
        return lacpTimer;
    }

    /**
     *
     * @param lacpTimer
     *            The lacpTimer
     */
    public void setLacpTimer(final UplinkSets.LacpTimer lacpTimer) {
        this.lacpTimer = lacpTimer;
    }

    /**
     *
     * (Required)
     *
     * @return The manualLoginRedistributionState
     */
    public UplinkSets.ManualLoginRedistributionState getManualLoginRedistributionState() {
        return manualLoginRedistributionState;
    }

    /**
     *
     * (Required)
     *
     * @param manualLoginRedistributionState
     *            The manualLoginRedistributionState
     */
    public void setManualLoginRedistributionState(final UplinkSets.ManualLoginRedistributionState manualLoginRedistributionState) {
        this.manualLoginRedistributionState = manualLoginRedistributionState;
    }

    /**
     *
     * (Required)
     *
     * @return The connectionMode
     */
    public UplinkSets.ConnectionMode getConnectionMode() {
        return connectionMode;
    }

    /**
     *
     * (Required)
     *
     * @param connectionMode
     *            The connectionMode
     */
    public void setConnectionMode(final UplinkSets.ConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
    }

    /**
     *
     * (Required)
     *
     * @return The ethernetNetworkType
     */
    public UplinkSets.EthernetNetworkType getEthernetNetworkType() {
        return ethernetNetworkType;
    }

    /**
     *
     * (Required)
     *
     * @param ethernetNetworkType
     *            The ethernetNetworkType
     */
    public void setEthernetNetworkType(final UplinkSets.EthernetNetworkType ethernetNetworkType) {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    /**
     *
     * @return The primaryPortLocation
     */
    public PrimaryPortLocation getPrimaryPortLocation() {
        return primaryPortLocation;
    }

    /**
     *
     * @param primaryPortLocation
     *            The primaryPortLocation
     */
    public void setPrimaryPortLocation(final PrimaryPortLocation primaryPortLocation) {
        this.primaryPortLocation = primaryPortLocation;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(reachability)
                .append(networkType)
                .append(portConfigInfos)
                .append(networkUris)
                .append(fcNetworkUris)
                .append(fcoeNetworkUris)
                .append(logicalInterconnectUri)
                .append(lacpTimer)
                .append(manualLoginRedistributionState)
                .append(connectionMode)
                .append(ethernetNetworkType)
                .append(primaryPortLocation)
                .append(nativeNetworkUri)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UplinkSets) == false) {
            return false;
        }
        final UplinkSets rhs = ((UplinkSets) other);
        return new EqualsBuilder()
                .append(reachability, rhs.reachability)
                .append(networkType, rhs.networkType)
                .append(portConfigInfos, rhs.portConfigInfos)
                .append(networkUris, rhs.networkUris)
                .append(fcNetworkUris, rhs.fcNetworkUris)
                .append(fcoeNetworkUris, rhs.fcoeNetworkUris)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .append(lacpTimer, rhs.lacpTimer)
                .append(manualLoginRedistributionState, rhs.manualLoginRedistributionState)
                .append(connectionMode, rhs.connectionMode)
                .append(ethernetNetworkType, rhs.ethernetNetworkType)
                .append(primaryPortLocation, rhs.primaryPortLocation)
                .append(nativeNetworkUri, rhs.nativeNetworkUri)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    public static enum ConnectionMode {

        Auto("Auto"), Failover("Failover");
        private final String value;
        private static Map<String, UplinkSets.ConnectionMode> constants = new HashMap<String, UplinkSets.ConnectionMode>();

        static {
            for (final UplinkSets.ConnectionMode c : values()) {
                constants.put(c.value, c);
            }
        }

        private ConnectionMode(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static UplinkSets.ConnectionMode fromValue(final String value) {
            final UplinkSets.ConnectionMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum EthernetNetworkType {

        Tagged("Tagged"), Untagged("Untagged"), Tunnel("Tunnel"), Unknown("Unknown"), NotApplicable("NotApplicable");
        private final String value;
        private static Map<String, UplinkSets.EthernetNetworkType> constants = new HashMap<String, UplinkSets.EthernetNetworkType>();

        static {
            for (final UplinkSets.EthernetNetworkType c : values()) {
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

        public static UplinkSets.EthernetNetworkType fromValue(final String value) {
            final UplinkSets.EthernetNetworkType constant = constants.get(value);
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
        private static Map<String, UplinkSets.LacpTimer> constants = new HashMap<String, UplinkSets.LacpTimer>();

        static {
            for (final UplinkSets.LacpTimer c : values()) {
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

        public static UplinkSets.LacpTimer fromValue(final String value) {
            final UplinkSets.LacpTimer constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum ManualLoginRedistributionState {

        Supported("Supported"), NotSupported("NotSupported"), Distributing("Distributing"), Distributed("Distributed"), DistributionFailed(
                "DistributionFailed");
        private final String value;
        private static Map<String, UplinkSets.ManualLoginRedistributionState> constants = new HashMap<String, UplinkSets.ManualLoginRedistributionState>();

        static {
            for (final UplinkSets.ManualLoginRedistributionState c : values()) {
                constants.put(c.value, c);
            }
        }

        private ManualLoginRedistributionState(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static UplinkSets.ManualLoginRedistributionState fromValue(final String value) {
            final UplinkSets.ManualLoginRedistributionState constant = constants.get(value);
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
        private static Map<String, UplinkSets.NetworkType> constants = new HashMap<String, UplinkSets.NetworkType>();

        static {
            for (final UplinkSets.NetworkType c : values()) {
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

        public static UplinkSets.NetworkType fromValue(final String value) {
            final UplinkSets.NetworkType constant = constants.get(value);
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
        private static Map<String, UplinkSets.Reachability> constants = new HashMap<String, UplinkSets.Reachability>();

        static {
            for (final UplinkSets.Reachability c : values()) {
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

        public static UplinkSets.Reachability fromValue(final String value) {
            final UplinkSets.Reachability constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
