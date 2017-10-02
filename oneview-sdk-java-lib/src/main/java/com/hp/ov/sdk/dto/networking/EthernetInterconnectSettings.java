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

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

public class EthernetInterconnectSettings extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String dependentResourceUri;
    private Boolean enableFastMacCacheFailover;
    private Boolean enableIgmpSnooping;
    private Boolean enableNetworkLoopProtection;
    private Boolean enablePauseFloodProtection;
    @Since(200)
    private Boolean enableRichTLV;
    @Since(300)
    private Boolean enableTaggedLldp;
    private String id;
    private Integer igmpIdleTimeoutInterval;
    private NetworkType interconnectType;
    @Since(300)
    private String lldpIpv4Address;
    @Since(300)
    private String lldpIpv6Address;
    private Integer macRefreshInterval;

    /**
     * @return the dependentResourceUri
     */
    public String getDependentResourceUri() {
        return dependentResourceUri;
    }

    /**
     * @param dependentResourceUri
     *            the dependentResourceUri to set
     */
    public void setDependentResourceUri(final String dependentResourceUri) {
        this.dependentResourceUri = dependentResourceUri;
    }

    /**
     * @return the enableFastMacCacheFailover
     */
    public Boolean getEnableFastMacCacheFailover() {
        return enableFastMacCacheFailover;
    }

    /**
     * @param enableFastMacCacheFailover
     *            the enableFastMacCacheFailover to set
     */
    public void setEnableFastMacCacheFailover(final Boolean enableFastMacCacheFailover) {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     * @return the enableIgmpSnooping
     */
    public Boolean getEnableIgmpSnooping() {
        return enableIgmpSnooping;
    }

    /**
     * @param enableIgmpSnooping
     *            the enableIgmpSnooping to set
     */
    public void setEnableIgmpSnooping(final Boolean enableIgmpSnooping) {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     * @return the enableNetworkLoopProtection
     */
    public Boolean getEnableNetworkLoopProtection() {
        return enableNetworkLoopProtection;
    }

    /**
     * @param enableNetworkLoopProtection
     *            the enableNetworkLoopProtection to set
     */
    public void setEnableNetworkLoopProtection(final Boolean enableNetworkLoopProtection) {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     * @return the enablePauseFloodProtection
     */
    public Boolean getEnablePauseFloodProtection() {
        return enablePauseFloodProtection;
    }

    /**
     * @param enablePauseFloodProtection
     *            the enablePauseFloodProtection to set
     */
    public void setEnablePauseFloodProtection(final Boolean enablePauseFloodProtection) {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return the igmpIdleTimeoutInterval
     */
    public Integer getIgmpIdleTimeoutInterval() {
        return igmpIdleTimeoutInterval;
    }

    /**
     * @param igmpIdleTimeoutInterval
     *            the igmpIdleTimeoutInterval to set
     */
    public void setIgmpIdleTimeoutInterval(final Integer igmpIdleTimeoutInterval) {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     * @return the interconnectType
     */
    public NetworkType getInterconnectType() {
        return interconnectType;
    }

    /**
     * @param interconnectType
     *            the interconnectType to set
     */
    public void setInterconnectType(final NetworkType interconnectType) {
        this.interconnectType = interconnectType;
    }

    /**
     * @return the macRefreshInterval
     */
    public Integer getMacRefreshInterval() {
        return macRefreshInterval;
    }

    /**
     * @param macRefreshInterval
     *            the macRefreshInterval to set
     */
    public void setMacRefreshInterval(final Integer macRefreshInterval) {
        this.macRefreshInterval = macRefreshInterval;
    }

    /**
     * @return the enableRichTLV
     */
    public Boolean getEnableRichTLV() {
        return enableRichTLV;
    }

    /**
     * @param enableRichTLV
     *            the enableRichTLV to set
     */
    public void setEnableRichTLV(final Boolean enableRichTLV) {
        this.enableRichTLV = enableRichTLV;
    }

    /**
     * @return the enableTaggedLldp
     */
    public Boolean getEnableTaggedLldp() {
        return enableTaggedLldp;
    }

    /**
     * @param enableTaggedLldp
     *            the enableTaggedLldp to set
     */
    public void setEnableTaggedLldp(final Boolean enableTaggedLldp) {
        this.enableTaggedLldp = enableTaggedLldp;
    }

    /**
     * @return the lldpIpv4Address
     */
    public String getLldpIpv4Address() {
        return lldpIpv4Address;
    }

    /**
     * @param lldpIpv4Address
     *            the lldpIpv4Address to set
     */
    public void setLldpIpv4Address(final String lldpIpv4Address) {
        this.lldpIpv4Address = lldpIpv4Address;
    }

    /**
     * @return the lldpIpv6Address
     */
    public String getLldpIpv6Address() {
        return lldpIpv6Address;
    }

    /**
     * @param lldpIpv6Address
     *            the lldpIpv6Address to set
     */
    public void setLldpIpv6Address(final String lldpIpv6Address) {
        this.lldpIpv6Address = lldpIpv6Address;
    }

}
