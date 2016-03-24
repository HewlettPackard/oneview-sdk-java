/*
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
 */

package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class VlanInfo implements Serializable {

    private static final long serialVersionUID = -8115420911286947803L;

    private List<RestUri> restUri;
    private String vlanNumber;

    public List<RestUri> getRestUri() {
        return restUri;
    }

    public void setRestUri(List<RestUri> restUri) {
        this.restUri = restUri;
    }

    public String getVlanNumber() {
        return vlanNumber;
    }

    public void setVlanNumber(String vlanNumber) {
        this.vlanNumber = vlanNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        VlanInfo vlanInfo = (VlanInfo) obj;

        return new EqualsBuilder()
                .append(restUri, vlanInfo.restUri)
                .append(vlanNumber, vlanInfo.vlanNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(restUri)
                .append(vlanNumber)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("restUri", restUri)
                .append("vlanNumber", vlanNumber)
                .toString();
    }
}
