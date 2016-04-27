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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class QosTrafficClass implements Serializable {

    private static final long serialVersionUID = 1L;

    private String bandwidthShare;
    private Integer egressDot1pValue;
    private Integer maxBandwidth;
    private Boolean realTime;
    private String className;
    private Boolean enabled;

    /**
     *
     * @return
     *     The bandwidthShare
     */
    public String getBandwidthShare() {
        return bandwidthShare;
    }

    /**
     *
     * @param bandwidthShare
     *     The bandwidthShare
     */
    public void setBandwidthShare(String bandwidthShare) {
        this.bandwidthShare = bandwidthShare;
    }

    /**
     *
     * @return
     *     The egressDot1pValue
     */
    public Integer getEgressDot1pValue() {
        return egressDot1pValue;
    }

    /**
     *
     * @param egressDot1pValue
     *     The egressDot1pValue
     */
    public void setEgressDot1pValue(Integer egressDot1pValue) {
        this.egressDot1pValue = egressDot1pValue;
    }

    /**
     *
     * @return
     *     The maxBandwidth
     */
    public Integer getMaxBandwidth() {
        return maxBandwidth;
    }

    /**
     *
     * @param maxBandwidth
     *     The maxBandwidth
     */
    public void setMaxBandwidth(Integer maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }

    /**
     *
     * @return
     *     The realTime
     */
    public Boolean getRealTime() {
        return realTime;
    }

    /**
     *
     * @param realTime
     *     The realTime
     */
    public void setRealTime(Boolean realTime) {
        this.realTime = realTime;
    }

    /**
     *
     * @return
     *     The className
     */
    public String getClassName() {
        return className;
    }

    /**
     *
     * @param className
     *     The className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     *
     * @return
     *     The enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     *     The enabled
     */
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
