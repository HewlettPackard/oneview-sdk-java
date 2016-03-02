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

import java.util.HashMap;
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
import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "interconnectType",
    "enableIgmpSnooping",
    "igmpIdleTimeoutInterval",
    "enableFastMacCacheFailover",
    "macRefreshInterval",
    "enableNetworkLoopProtection",
    "enablePauseFloodProtection",
    "enableRichTLV",
    "dependentResourceUri",
    "id"})
public class EthernetSettings extends BaseModelResource {

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("interconnectType")
    private EthernetSettings.InterconnectType interconnectType = EthernetSettings.InterconnectType.fromValue("Ethernet");
    @JsonProperty("enableIgmpSnooping")
    private Boolean enableIgmpSnooping = false;
    @JsonProperty("igmpIdleTimeoutInterval")
    private Integer igmpIdleTimeoutInterval = 260;
    @JsonProperty("enableFastMacCacheFailover")
    private Boolean enableFastMacCacheFailover = true;
    @JsonProperty("macRefreshInterval")
    private Integer macRefreshInterval = 5;
    @JsonProperty("enableNetworkLoopProtection")
    private Boolean enableNetworkLoopProtection = true;
    @JsonProperty("enablePauseFloodProtection")
    private Boolean enablePauseFloodProtection = true;
    @Since(200)
    @JsonProperty("enableRichTLV")
    private Boolean enableRichTLV = true;
    @JsonProperty("dependentResourceUri")
    private String dependentResourceUri;
    @JsonProperty("id")
    private String id;

    /**
     *
     * @return The interconnectType
     */
    @JsonProperty("interconnectType")
    public EthernetSettings.InterconnectType getInterconnectType() {
        return interconnectType;
    }

    /**
     *
     * @param interconnectType
     *            The interconnectType
     */
    @JsonProperty("interconnectType")
    public void setInterconnectType(final EthernetSettings.InterconnectType interconnectType) {
        this.interconnectType = interconnectType;
    }

    /**
     *
     * @return The enableIgmpSnooping
     */
    @JsonProperty("enableIgmpSnooping")
    public Boolean getEnableIgmpSnooping() {
        return enableIgmpSnooping;
    }

    /**
     *
     * @param enableIgmpSnooping
     *            The enableIgmpSnooping
     */
    @JsonProperty("enableIgmpSnooping")
    public void setEnableIgmpSnooping(final Boolean enableIgmpSnooping) {
        this.enableIgmpSnooping = enableIgmpSnooping;
    }

    /**
     *
     * @return The igmpIdleTimeoutInterval
     */
    @JsonProperty("igmpIdleTimeoutInterval")
    public Integer getIgmpIdleTimeoutInterval() {
        return igmpIdleTimeoutInterval;
    }

    /**
     *
     * @param igmpIdleTimeoutInterval
     *            The igmpIdleTimeoutInterval
     */
    @JsonProperty("igmpIdleTimeoutInterval")
    public void setIgmpIdleTimeoutInterval(final Integer igmpIdleTimeoutInterval) {
        this.igmpIdleTimeoutInterval = igmpIdleTimeoutInterval;
    }

    /**
     *
     * @return The enableFastMacCacheFailover
     */
    @JsonProperty("enableFastMacCacheFailover")
    public Boolean getEnableFastMacCacheFailover() {
        return enableFastMacCacheFailover;
    }

    /**
     *
     * @param enableFastMacCacheFailover
     *            The enableFastMacCacheFailover
     */
    @JsonProperty("enableFastMacCacheFailover")
    public void setEnableFastMacCacheFailover(final Boolean enableFastMacCacheFailover) {
        this.enableFastMacCacheFailover = enableFastMacCacheFailover;
    }

    /**
     *
     * @return The macRefreshInterval
     */
    @JsonProperty("macRefreshInterval")
    public Integer getMacRefreshInterval() {
        return macRefreshInterval;
    }

    /**
     *
     * @param macRefreshInterval
     *            The macRefreshInterval
     */
    @JsonProperty("macRefreshInterval")
    public void setMacRefreshInterval(final Integer macRefreshInterval) {
        this.macRefreshInterval = macRefreshInterval;
    }

    /**
     *
     * @return The enableNetworkLoopProtection
     */
    @JsonProperty("enableNetworkLoopProtection")
    public Boolean getEnableNetworkLoopProtection() {
        return enableNetworkLoopProtection;
    }

    /**
     *
     * @param enableNetworkLoopProtection
     *            The enableNetworkLoopProtection
     */
    @JsonProperty("enableNetworkLoopProtection")
    public void setEnableNetworkLoopProtection(final Boolean enableNetworkLoopProtection) {
        this.enableNetworkLoopProtection = enableNetworkLoopProtection;
    }

    /**
     *
     * @return The enablePauseFloodProtection
     */
    @JsonProperty("enablePauseFloodProtection")
    public Boolean getEnablePauseFloodProtection() {
        return enablePauseFloodProtection;
    }

    /**
     *
     * @param enablePauseFloodProtection
     *            The enablePauseFloodProtection
     */
    @JsonProperty("enablePauseFloodProtection")
    public void setEnablePauseFloodProtection(final Boolean enablePauseFloodProtection) {
        this.enablePauseFloodProtection = enablePauseFloodProtection;
    }

    /**
     *
     * @return The enableRichTLV
     */
    @JsonProperty("enableRichTLV")
    public Boolean getEnableRichTLV() {
        return enableRichTLV;
    }

    /**
     *
     * @param enableRichTLV
     *            The enableRichTLV
     */
    @JsonProperty("enableRichTLV")
    public void setEnableRichTLV(final Boolean enableRichTLV) {
        this.enableRichTLV = enableRichTLV;
    }

    /**
     *
     * @return The dependentResourceUri
     */
    @JsonProperty("dependentResourceUri")
    public String getDependentResourceUri() {
        return dependentResourceUri;
    }

    /**
     *
     * @param dependentResourceUri
     *            The dependentResourceUri
     */
    @JsonProperty("dependentResourceUri")
    public void setDependentResourceUri(final String dependentResourceUri) {
        this.dependentResourceUri = dependentResourceUri;
    }

    /**
     *
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     *            The id
     */
    @JsonProperty("id")
    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(interconnectType)
                .append(enableIgmpSnooping)
                .append(igmpIdleTimeoutInterval)
                .append(enableFastMacCacheFailover)
                .append(macRefreshInterval)
                .append(enableNetworkLoopProtection)
                .append(enablePauseFloodProtection)
                .append(enableRichTLV)
                .append(dependentResourceUri)
                .append(id)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EthernetSettings) == false) {
            return false;
        }
        final EthernetSettings rhs = ((EthernetSettings) other);
        return new EqualsBuilder()
                .append(interconnectType, rhs.interconnectType)
                .append(enableIgmpSnooping, rhs.enableIgmpSnooping)
                .append(igmpIdleTimeoutInterval, rhs.igmpIdleTimeoutInterval)
                .append(enableFastMacCacheFailover, rhs.enableFastMacCacheFailover)
                .append(macRefreshInterval, rhs.macRefreshInterval)
                .append(enableNetworkLoopProtection, rhs.enableNetworkLoopProtection)
                .append(enablePauseFloodProtection, rhs.enablePauseFloodProtection)
                .append(enableRichTLV, rhs.enableRichTLV)
                .append(dependentResourceUri, rhs.dependentResourceUri)
                .append(id, rhs.id)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum InterconnectType {

        Ethernet("Ethernet"), FibreChannel("FibreChannel");
        private final String value;
        private static Map<String, EthernetSettings.InterconnectType> constants = new HashMap<String, EthernetSettings.InterconnectType>();

        static {
            for (final EthernetSettings.InterconnectType c : values()) {
                constants.put(c.value, c);
            }
        }

        private InterconnectType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static EthernetSettings.InterconnectType fromValue(final String value) {
            final EthernetSettings.InterconnectType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
