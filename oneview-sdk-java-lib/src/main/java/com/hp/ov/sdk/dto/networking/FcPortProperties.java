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

package com.hp.ov.sdk.dto.networking;

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

    /**
     * @return the fcfMac
     */
    public String getFcfMac() {
        return fcfMac;
    }

    /**
     * @param fcfMac the fcfMac to set
     */
    public void setFcfMac(String fcfMac) {
        this.fcfMac = fcfMac;
    }

    /**
     * @return the logins
     */
    public String getLogins() {
        return logins;
    }

    /**
     * @param logins the logins to set
     */
    public void setLogins(String logins) {
        this.logins = logins;
    }

    /**
     * @return the loginsCount
     */
    public Integer getLoginsCount() {
        return loginsCount;
    }

    /**
     * @param loginsCount the loginsCount to set
     */
    public void setLoginsCount(Integer loginsCount) {
        this.loginsCount = loginsCount;
    }

    /**
     * @return the neighborInterconnectName
     */
    public String getNeighborInterconnectName() {
        return neighborInterconnectName;
    }

    /**
     * @param neighborInterconnectName the neighborInterconnectName to set
     */
    public void setNeighborInterconnectName(String neighborInterconnectName) {
        this.neighborInterconnectName = neighborInterconnectName;
    }

    /**
     * @return the opOnline
     */
    public Boolean getOpOnline() {
        return opOnline;
    }

    /**
     * @param opOnline the opOnline to set
     */
    public void setOpOnline(Boolean opOnline) {
        this.opOnline = opOnline;
    }

    /**
     * @return the opOnlineReason
     */
    public String getOpOnlineReason() {
        return opOnlineReason;
    }

    /**
     * @param opOnlineReason the opOnlineReason to set
     */
    public void setOpOnlineReason(String opOnlineReason) {
        this.opOnlineReason = opOnlineReason;
    }

    /**
     * @return the principleInterconnectName
     */
    public String getPrincipleInterconnectName() {
        return principleInterconnectName;
    }

    /**
     * @param principleInterconnectName the principleInterconnectName to set
     */
    public void setPrincipleInterconnectName(String principleInterconnectName) {
        this.principleInterconnectName = principleInterconnectName;
    }

    /**
     * @return the principleInterconnectNameList
     */
    public List<String> getPrincipleInterconnectNameList() {
        return principleInterconnectNameList;
    }

    /**
     * @param principleInterconnectNameList the principleInterconnectNameList to set
     */
    public void setPrincipleInterconnectNameList(List<String> principleInterconnectNameList) {
        this.principleInterconnectNameList = principleInterconnectNameList;
    }

    /**
     * @return the wwnn
     */
    public String getWwnn() {
        return wwnn;
    }

    /**
     * @param wwnn the wwnn to set
     */
    public void setWwnn(String wwnn) {
        this.wwnn = wwnn;
    }

    /**
     * @return the wwpn
     */
    public String getWwpn() {
        return wwpn;
    }

    /**
     * @param wwpn the wwpn to set
     */
    public void setWwpn(String wwpn) {
        this.wwpn = wwpn;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
