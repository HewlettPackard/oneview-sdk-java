/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "bandwidthShare",
    "egressDot1pValue",
    "maxBandwidth",
    "realTime",
    "className",
    "enabled"
})
public class QosTrafficClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("bandwidthShare")
    private String bandwidthShare;
    @JsonProperty("egressDot1pValue")
    private Integer egressDot1pValue;
    @JsonProperty("maxBandwidth")
    private Integer maxBandwidth;
    @JsonProperty("realTime")
    private Boolean realTime;
    @JsonProperty("className")
    private String className;
    @JsonProperty("enabled")
    private Boolean enabled;

    /**
     *
     * @return
     *     The bandwidthShare
     */
    @JsonProperty("bandwidthShare")
    public String getBandwidthShare() {
        return bandwidthShare;
    }

    /**
     *
     * @param bandwidthShare
     *     The bandwidthShare
     */
    @JsonProperty("bandwidthShare")
    public void setBandwidthShare(String bandwidthShare) {
        this.bandwidthShare = bandwidthShare;
    }

    /**
     *
     * @return
     *     The egressDot1pValue
     */
    @JsonProperty("egressDot1pValue")
    public Integer getEgressDot1pValue() {
        return egressDot1pValue;
    }

    /**
     *
     * @param egressDot1pValue
     *     The egressDot1pValue
     */
    @JsonProperty("egressDot1pValue")
    public void setEgressDot1pValue(Integer egressDot1pValue) {
        this.egressDot1pValue = egressDot1pValue;
    }

    /**
     *
     * @return
     *     The maxBandwidth
     */
    @JsonProperty("maxBandwidth")
    public Integer getMaxBandwidth() {
        return maxBandwidth;
    }

    /**
     *
     * @param maxBandwidth
     *     The maxBandwidth
     */
    @JsonProperty("maxBandwidth")
    public void setMaxBandwidth(Integer maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }

    /**
     *
     * @return
     *     The realTime
     */
    @JsonProperty("realTime")
    public Boolean getRealTime() {
        return realTime;
    }

    /**
     *
     * @param realTime
     *     The realTime
     */
    @JsonProperty("realTime")
    public void setRealTime(Boolean realTime) {
        this.realTime = realTime;
    }

    /**
     *
     * @return
     *     The className
     */
    @JsonProperty("className")
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className
     *     The className
     */
    @JsonProperty("className")
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @return
     *     The enabled
     */
    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     *     The enabled
     */
    @JsonProperty("enabled")
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bandwidthShare)
                .append(egressDot1pValue)
                .append(maxBandwidth)
                .append(realTime)
                .append(className)
                .append(enabled)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosTrafficClass) == false) {
            return false;
        }
        QosTrafficClass rhs = ((QosTrafficClass) other);
        return new EqualsBuilder()
                .append(bandwidthShare, rhs.bandwidthShare)
                .append(egressDot1pValue, rhs.egressDot1pValue)
                .append(maxBandwidth, rhs.maxBandwidth)
                .append(realTime, rhs.realTime)
                .append(className, rhs.className)
                .append(enabled, rhs.enabled)
                .isEquals();
    }

}
