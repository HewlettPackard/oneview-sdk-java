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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class QosTrafficClass implements Serializable {

    private static final long serialVersionUID = 1L;

    private String className;
    private boolean realTime;
    private String bandwidthShare;
    private int maxBandwidth;
    private boolean enabled;
    private QosDot1pValues egressDot1pValue;

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }
    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * @return the realTime
     */
    public boolean isRealTime() {
        return realTime;
    }
    /**
     * @param realTime the realTime to set
     */
    public void setRealTime(boolean realTime) {
        this.realTime = realTime;
    }
    /**
     * @return the bandwidthShare
     */
    public String getBandwidthShare() {
        return bandwidthShare;
    }
    /**
     * @param bandwidthShare the bandwidthShare to set
     */
    public void setBandwidthShare(String bandwidthShare) {
        this.bandwidthShare = bandwidthShare;
    }
    /**
     * @return the maxBandwidth
     */
    public int getMaxBandwidth() {
        return maxBandwidth;
    }
    /**
     * @param maxBandwidth the maxBandwidth to set
     */
    public void setMaxBandwidth(int maxBandwidth) {
        this.maxBandwidth = maxBandwidth;
    }
    /**
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }
    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    /**
     * @return the egressDot1pValue
     */
    public QosDot1pValues getEgressDot1pValue() {
        return egressDot1pValue;
    }
    /**
     * @param egressDot1pValue the egressDot1pValue to set
     */
    public void setEgressDot1pValue(QosDot1pValues egressDot1pValue) {
        this.egressDot1pValue = egressDot1pValue;
    }


}
