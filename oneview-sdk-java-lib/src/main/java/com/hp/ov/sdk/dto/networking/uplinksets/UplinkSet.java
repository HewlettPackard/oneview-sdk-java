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
package com.hp.ov.sdk.dto.networking.uplinksets;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.networking.EthernetNetworkType;
import com.hp.ov.sdk.dto.networking.LacpTimer;
import com.hp.ov.sdk.dto.networking.Location;
import com.hp.ov.sdk.dto.networking.NetworkType;
import com.hp.ov.sdk.dto.networking.PortConfigInfo;
import com.hp.ov.sdk.dto.networking.Reachability;

public class UplinkSet extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private ConnectionMode connectionMode;
    private EthernetNetworkType ethernetNetworkType;
    private List<String> fcNetworkUris = new ArrayList<String>();
    @Since(200)
    private List<String> fcoeNetworkUris = new ArrayList<String>();
    private LacpTimer lacpTimer;
    private String logicalInterconnectUri;
    private ManualLoginRedistributionState manualLoginRedistributionState;
    private String nativeNetworkUri;
    private NetworkType networkType;
    private List<String> networkUris = new ArrayList<String>();
    private List<PortConfigInfo> portConfigInfos = new ArrayList<PortConfigInfo>();
    private Location primaryPortLocation;
    private Reachability reachability;

    /**
     * @return the connectionMode
     */
    public ConnectionMode getConnectionMode() {
        return connectionMode;
    }

    /**
     * @param connectionMode the connectionMode to set
     */
    public void setConnectionMode(ConnectionMode connectionMode) {
        this.connectionMode = connectionMode;
    }

    /**
     * @return the ethernetNetworkType
     */
    public EthernetNetworkType getEthernetNetworkType() {
        return ethernetNetworkType;
    }

    /**
     * @param ethernetNetworkType the ethernetNetworkType to set
     */
    public void setEthernetNetworkType(EthernetNetworkType ethernetNetworkType) {
        this.ethernetNetworkType = ethernetNetworkType;
    }

    /**
     * @return the fcNetworkUris
     */
    public List<String> getFcNetworkUris() {
        return fcNetworkUris;
    }

    /**
     * @param fcNetworkUris the fcNetworkUris to set
     */
    public void setFcNetworkUris(List<String> fcNetworkUris) {
        this.fcNetworkUris = fcNetworkUris;
    }

    /**
     * @return the fcoeNetworkUris
     */
    public List<String> getFcoeNetworkUris() {
        return fcoeNetworkUris;
    }

    /**
     * @param fcoeNetworkUris the fcoeNetworkUris to set
     */
    public void setFcoeNetworkUris(List<String> fcoeNetworkUris) {
        this.fcoeNetworkUris = fcoeNetworkUris;
    }

    /**
     * @return the lacpTimer
     */
    public LacpTimer getLacpTimer() {
        return lacpTimer;
    }

    /**
     * @param lacpTimer the lacpTimer to set
     */
    public void setLacpTimer(LacpTimer lacpTimer) {
        this.lacpTimer = lacpTimer;
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
     * @return the manualLoginRedistributionState
     */
    public ManualLoginRedistributionState getManualLoginRedistributionState() {
        return manualLoginRedistributionState;
    }

    /**
     * @param manualLoginRedistributionState the manualLoginRedistributionState to set
     */
    public void setManualLoginRedistributionState(ManualLoginRedistributionState manualLoginRedistributionState) {
        this.manualLoginRedistributionState = manualLoginRedistributionState;
    }

    /**
     * @return the nativeNetworkUri
     */
    public String getNativeNetworkUri() {
        return nativeNetworkUri;
    }

    /**
     * @param nativeNetworkUri the nativeNetworkUri to set
     */
    public void setNativeNetworkUri(String nativeNetworkUri) {
        this.nativeNetworkUri = nativeNetworkUri;
    }

    /**
     * @return the networkType
     */
    public NetworkType getNetworkType() {
        return networkType;
    }

    /**
     * @param networkType the networkType to set
     */
    public void setNetworkType(NetworkType networkType) {
        this.networkType = networkType;
    }

    /**
     * @return the networkUris
     */
    public List<String> getNetworkUris() {
        return networkUris;
    }

    /**
     * @param networkUris the networkUris to set
     */
    public void setNetworkUris(List<String> networkUris) {
        this.networkUris = networkUris;
    }

    /**
     * @return the portConfigInfos
     */
    public List<PortConfigInfo> getPortConfigInfos() {
        return portConfigInfos;
    }

    /**
     * @param portConfigInfos the portConfigInfos to set
     */
    public void setPortConfigInfos(List<PortConfigInfo> portConfigInfos) {
        this.portConfigInfos = portConfigInfos;
    }

    /**
     * @return the primaryPortLocation
     */
    public Location getPrimaryPortLocation() {
        return primaryPortLocation;
    }

    /**
     * @param primaryPortLocation the primaryPortLocation to set
     */
    public void setPrimaryPortLocation(Location primaryPortLocation) {
        this.primaryPortLocation = primaryPortLocation;
    }

    /**
     * @return the reachability
     */
    public Reachability getReachability() {
        return reachability;
    }

    /**
     * @param reachability the reachability to set
     */
    public void setReachability(Reachability reachability) {
        this.reachability = reachability;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof UplinkSet);
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
        return new EqualsBuilder()
                .append(connectionMode, rhs.connectionMode)
                .append(ethernetNetworkType, rhs.ethernetNetworkType)
                .append(fcNetworkUris, rhs.fcNetworkUris)
                .append(fcoeNetworkUris, rhs.fcoeNetworkUris)
                .append(lacpTimer, rhs.lacpTimer)
                .append(logicalInterconnectUri, rhs.logicalInterconnectUri)
                .append(manualLoginRedistributionState, rhs.manualLoginRedistributionState)
                .append(nativeNetworkUri, rhs.nativeNetworkUri)
                .append(networkType, rhs.networkType)
                .append(networkUris, rhs.networkUris)
                .append(portConfigInfos, rhs.portConfigInfos)
                .append(primaryPortLocation, rhs.primaryPortLocation)
                .append(reachability, rhs.reachability)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(connectionMode)
                .append(ethernetNetworkType)
                .append(fcNetworkUris)
                .append(fcoeNetworkUris)
                .append(lacpTimer)
                .append(logicalInterconnectUri)
                .append(manualLoginRedistributionState)
                .append(nativeNetworkUri)
                .append(networkType)
                .append(networkUris)
                .append(portConfigInfos)
                .append(primaryPortLocation)
                .append(reachability)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

}
