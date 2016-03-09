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

public final class FcPortProperties implements Serializable {

    private static final long serialVersionUID = -8628111161856552358L;

    private String fcfMac;
    private String logins;
    private Integer loginsCount;
    private String neighborInterconnectName;
    private Boolean opOnline;
    private String opOnlineReason;
    private String principleInterconnectName;
    private List<String> principleInterconnectNameList = new ArrayList<>();
    private String wwnn;
    private String wwpn;

    public String getFcfMac() {
        return fcfMac;
    }

    public void setFcfMac(String fcfMac) {
        this.fcfMac = fcfMac;
    }

    public String getLogins() {
        return logins;
    }

    public void setLogins(String logins) {
        this.logins = logins;
    }

    public Integer getLoginsCount() {
        return loginsCount;
    }

    public void setLoginsCount(Integer loginsCount) {
        this.loginsCount = loginsCount;
    }

    public String getNeighborInterconnectName() {
        return neighborInterconnectName;
    }

    public void setNeighborInterconnectName(String neighborInterconnectName) {
        this.neighborInterconnectName = neighborInterconnectName;
    }

    public Boolean getOpOnline() {
        return opOnline;
    }

    public void setOpOnline(Boolean opOnline) {
        this.opOnline = opOnline;
    }

    public String getOpOnlineReason() {
        return opOnlineReason;
    }

    public void setOpOnlineReason(String opOnlineReason) {
        this.opOnlineReason = opOnlineReason;
    }

    public String getPrincipleInterconnectName() {
        return principleInterconnectName;
    }

    public void setPrincipleInterconnectName(String principleInterconnectName) {
        this.principleInterconnectName = principleInterconnectName;
    }

    public List<String> getPrincipleInterconnectNameList() {
        return principleInterconnectNameList;
    }

    public void setPrincipleInterconnectNameList(List<String> principleInterconnectNameList) {
        this.principleInterconnectNameList = principleInterconnectNameList;
    }

    public String getWwnn() {
        return wwnn;
    }

    public void setWwnn(String wwnn) {
        this.wwnn = wwnn;
    }

    public String getWwpn() {
        return wwpn;
    }

    public void setWwpn(String wwpn) {
        this.wwpn = wwpn;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        FcPortProperties that = (FcPortProperties) obj;

        return new EqualsBuilder()
                .append(fcfMac, that.fcfMac)
                .append(logins, that.logins)
                .append(loginsCount, that.loginsCount)
                .append(neighborInterconnectName, that.neighborInterconnectName)
                .append(opOnline, that.opOnline)
                .append(opOnlineReason, that.opOnlineReason)
                .append(principleInterconnectName, that.principleInterconnectName)
                .append(principleInterconnectNameList, that.principleInterconnectNameList)
                .append(wwnn, that.wwnn)
                .append(wwpn, that.wwpn)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(fcfMac)
                .append(logins)
                .append(loginsCount)
                .append(neighborInterconnectName)
                .append(opOnline)
                .append(opOnlineReason)
                .append(principleInterconnectName)
                .append(principleInterconnectNameList)
                .append(wwnn)
                .append(wwpn)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("fcfMac", fcfMac)
                .append("logins", logins)
                .append("loginsCount", loginsCount)
                .append("neighborInterconnectName", neighborInterconnectName)
                .append("opOnline", opOnline)
                .append("opOnlineReason", opOnlineReason)
                .append("principleInterconnectName", principleInterconnectName)
                .append("principleInterconnectNameList", principleInterconnectNameList)
                .append("wwnn", wwnn)
                .append("wwpn", wwpn)
                .toString();
    }

}
