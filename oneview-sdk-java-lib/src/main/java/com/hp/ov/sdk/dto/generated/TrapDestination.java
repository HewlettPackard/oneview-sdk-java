/*
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
 */
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TrapDestination implements Serializable {

    private static final long serialVersionUID = 892678631593032566L;

    public enum TrapFormat {
        SNMPv1, SNMPv2, SNMPv3
    }

    public enum TrapSeverity {
        Critical, Info, Major, Minor, Normal, Unknown, Warning
    }

    public enum EnetTrapCategory {
        Other, PortStatus, PortThresholds
    }

    public enum FcTrapCategory {
        Other, PortStatus
    }

    public enum VcmTrapCategory {
        Legacy
    }

    private List<TrapSeverity> trapSeverities = new ArrayList<>();
    private List<EnetTrapCategory> enetTrapCategories = new ArrayList<>();
    private List<FcTrapCategory> fcTrapCategories = new ArrayList<>();
    private List<VcmTrapCategory> vcmTrapCategories = new ArrayList<>();
    private TrapFormat trapFormat = TrapFormat.SNMPv1;
    private String trapDestination = "";
    private String communityString = "public";

    public List<TrapSeverity> getTrapSeverities() {
        return trapSeverities;
    }

    public void setTrapSeverities(List<TrapSeverity> trapSeverities) {
        this.trapSeverities = trapSeverities;
    }

    public List<EnetTrapCategory> getEnetTrapCategories() {
        return enetTrapCategories;
    }

    public void setEnetTrapCategories(final List<EnetTrapCategory> enetTrapCategories) {
        this.enetTrapCategories = enetTrapCategories;
    }

    public List<FcTrapCategory> getFcTrapCategories() {
        return fcTrapCategories;
    }

    public void setFcTrapCategories(final List<FcTrapCategory> fcTrapCategories) {
        this.fcTrapCategories = fcTrapCategories;
    }

    public List<VcmTrapCategory> getVcmTrapCategories() {
        return vcmTrapCategories;
    }

    public void setVcmTrapCategories(final List<VcmTrapCategory> vcmTrapCategories) {
        this.vcmTrapCategories = vcmTrapCategories;
    }

    public TrapDestination.TrapFormat getTrapFormat() {
        return trapFormat;
    }

    public void setTrapFormat(final TrapDestination.TrapFormat trapFormat) {
        this.trapFormat = trapFormat;
    }

    public String getTrapDestination() {
        return trapDestination;
    }

    public void setTrapDestination(final String trapDestination) {
        this.trapDestination = trapDestination;
    }

    public String getCommunityString() {
        return communityString;
    }

    public void setCommunityString(final String communityString) {
        this.communityString = communityString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        TrapDestination that = (TrapDestination) obj;

        return new EqualsBuilder()
                .append(trapSeverities, that.trapSeverities)
                .append(enetTrapCategories, that.enetTrapCategories)
                .append(fcTrapCategories, that.fcTrapCategories)
                .append(vcmTrapCategories, that.vcmTrapCategories)
                .append(trapFormat, that.trapFormat)
                .append(trapDestination, that.trapDestination)
                .append(communityString, that.communityString)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(trapSeverities)
                .append(enetTrapCategories)
                .append(fcTrapCategories)
                .append(vcmTrapCategories)
                .append(trapFormat)
                .append(trapDestination)
                .append(communityString)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("trapSeverities", trapSeverities)
                .append("enetTrapCategories", enetTrapCategories)
                .append("fcTrapCategories", fcTrapCategories)
                .append("vcmTrapCategories", vcmTrapCategories)
                .append("trapFormat", trapFormat)
                .append("trapDestination", trapDestination)
                .append("communityString", communityString)
                .toString();
    }
}
