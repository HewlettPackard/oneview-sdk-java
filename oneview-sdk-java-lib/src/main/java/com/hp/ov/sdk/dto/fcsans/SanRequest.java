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
package com.hp.ov.sdk.dto.fcsans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.Property;
import com.hp.ov.sdk.dto.RefreshState;

public class SanRequest implements Serializable{

    private static final long serialVersionUID = 1L;

    private String deviceManagerUri;
    @Since(200)
    private Boolean isExpectedFc;
    @Since(200)
    private Boolean isExpectedFcoe;
    private List<Property> publicAttributes = new ArrayList<>();
    private RefreshState refreshState;
    private SanPolicy sanPolicy;
    @Since(200)
    private Integer vLanId;

    /**
     * @return the deviceManagerUri
     */
    public String getDeviceManagerUri() {
        return deviceManagerUri;
    }

    /**
     * @param deviceManagerUri the deviceManagerUri to set
     */
    public void setDeviceManagerUri(String deviceManagerUri) {
        this.deviceManagerUri = deviceManagerUri;
    }

    /**
     * @return the isExpectedFc
     */
    public Boolean getIsExpectedFc() {
        return isExpectedFc;
    }

    /**
     * @param isExpectedFc the isExpectedFc to set
     */
    public void setIsExpectedFc(Boolean isExpectedFc) {
        this.isExpectedFc = isExpectedFc;
    }

    /**
     * @return the isExpectedFcoe
     */
    public Boolean getIsExpectedFcoe() {
        return isExpectedFcoe;
    }

    /**
     * @param isExpectedFcoe the isExpectedFcoe to set
     */
    public void setIsExpectedFcoe(Boolean isExpectedFcoe) {
        this.isExpectedFcoe = isExpectedFcoe;
    }

    /**
     * @return the publicAttributes
     */
    public List<Property> getPublicAttributes() {
        return publicAttributes;
    }

    /**
     * @param publicAttributes the publicAttributes to set
     */
    public void setPublicAttributes(List<Property> publicAttributes) {
        this.publicAttributes = publicAttributes;
    }

    /**
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * @return the sanPolicy
     */
    public SanPolicy getSanPolicy() {
        return sanPolicy;
    }

    /**
     * @param sanPolicy the sanPolicy to set
     */
    public void setSanPolicy(SanPolicy sanPolicy) {
        this.sanPolicy = sanPolicy;
    }

    /**
     * @return the vLanId
     */
    public Integer getvLanId() {
        return vLanId;
    }

    /**
     * @param vLanId the vLanId to set
     */
    public void setvLanId(Integer vLanId) {
        this.vLanId = vLanId;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof SanRequest) {
            SanRequest that = (SanRequest) obj;

            return new EqualsBuilder()
                    .append(deviceManagerUri, that.deviceManagerUri)
                    .append(isExpectedFc, that.isExpectedFc)
                    .append(isExpectedFcoe, that.isExpectedFcoe)
                    .append(publicAttributes, that.publicAttributes)
                    .append(refreshState, that.refreshState)
                    .append(sanPolicy, that.sanPolicy)
                    .append(vLanId, that.vLanId)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(deviceManagerUri)
                .append(isExpectedFc)
                .append(isExpectedFcoe)
                .append(publicAttributes)
                .append(refreshState)
                .append(sanPolicy)
                .append(vLanId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
