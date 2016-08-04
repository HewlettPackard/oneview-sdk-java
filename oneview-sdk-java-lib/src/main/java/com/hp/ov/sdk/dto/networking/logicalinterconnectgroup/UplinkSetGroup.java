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
package com.hp.ov.sdk.dto.networking.logicalinterconnectgroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.NetworkType;
import com.hp.ov.sdk.dto.networking.EthernetNetworkType;
import com.hp.ov.sdk.dto.networking.LacpTimer;
import com.hp.ov.sdk.dto.networking.LogicalLocation;
import com.hp.ov.sdk.dto.networking.Reachability;

public class UplinkSetGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private EthernetNetworkType ethernetNetworkType;
    private LacpTimer lacpTimer;
    private List<LogicalPortConfigInfo> logicalPortConfigInfos = new ArrayList<LogicalPortConfigInfo>();
    private EnetPortSetAggregationMode mode;
    private String name;
    private String nativeNetworkUri;
    private NetworkType networkType;
    private List<String> networkUris = new ArrayList<String>();
    private LogicalLocation primaryPort;
    private Reachability reachability;
    private String type;

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
     * @return the logicalPortConfigInfos
     */
    public List<LogicalPortConfigInfo> getLogicalPortConfigInfos() {
        return logicalPortConfigInfos;
    }

    /**
     * @param logicalPortConfigInfos the logicalPortConfigInfos to set
     */
    public void setLogicalPortConfigInfos(List<LogicalPortConfigInfo> logicalPortConfigInfos) {
        this.logicalPortConfigInfos = logicalPortConfigInfos;
    }

    /**
     * @return the mode
     */
    public EnetPortSetAggregationMode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(EnetPortSetAggregationMode mode) {
        this.mode = mode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the primaryPort
     */
    public LogicalLocation getPrimaryPort() {
        return primaryPort;
    }

    /**
     * @param primaryPort the primaryPort to set
     */
    public void setPrimaryPort(LogicalLocation primaryPort) {
        this.primaryPort = primaryPort;
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

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(ethernetNetworkType)
                .append(lacpTimer)
                .append(logicalPortConfigInfos)
                .append(mode)
                .append(name)
                .append(nativeNetworkUri)
                .append(networkType)
                .append(networkUris)
                .append(primaryPort)
                .append(reachability)
                .append(type)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UplinkSetGroup) == false) {
            return false;
        }
        final UplinkSetGroup rhs = ((UplinkSetGroup) other);
        return new EqualsBuilder()
                .append(ethernetNetworkType, rhs.ethernetNetworkType)
                .append(lacpTimer, rhs.lacpTimer)
                .append(logicalPortConfigInfos, rhs.logicalPortConfigInfos)
                .append(mode, rhs.mode)
                .append(name, rhs.name)
                .append(nativeNetworkUri, rhs.nativeNetworkUri)
                .append(networkType, rhs.networkType)
                .append(networkUris, rhs.networkUris)
                .append(primaryPort, rhs.primaryPort)
                .append(reachability, rhs.reachability)
                .append(type, rhs.type)
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
