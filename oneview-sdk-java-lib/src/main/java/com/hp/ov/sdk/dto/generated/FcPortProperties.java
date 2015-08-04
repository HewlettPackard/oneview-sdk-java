/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "principleInterconnectName", "wwpn", "wwnn", "logins", "opOnline", "opOnlineReason", "loginsCount", "fcfMac",
        "principleInterconnectNameList", "neighborInterconnectName" })
public class FcPortProperties implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("principleInterconnectName")
    private String principleInterconnectName;
    @JsonProperty("wwpn")
    private String wwpn;
    @JsonProperty("wwnn")
    private String wwnn;
    @JsonProperty("logins")
    private String logins;
    @JsonProperty("opOnline")
    private Boolean opOnline;
    @JsonProperty("opOnlineReason")
    private String opOnlineReason;
    @JsonProperty("loginsCount")
    private Integer loginsCount;
    @JsonProperty("fcfMac")
    private String fcfMac;
    @JsonProperty("principleInterconnectNameList")
    private List<String> principleInterconnectNameList = new ArrayList<String>();
    @JsonProperty("neighborInterconnectName")
    private String neighborInterconnectName;

    /**
     * 
     * @return The principleInterconnectName
     */
    @JsonProperty("principleInterconnectName")
    public String getPrincipleInterconnectName() {
        return principleInterconnectName;
    }

    /**
     * 
     * @param principleInterconnectName
     *            The principleInterconnectName
     */
    @JsonProperty("principleInterconnectName")
    public void setPrincipleInterconnectName(final String principleInterconnectName) {
        this.principleInterconnectName = principleInterconnectName;
    }

    /**
     * 
     * @return The wwpn
     */
    @JsonProperty("wwpn")
    public String getWwpn() {
        return wwpn;
    }

    /**
     * 
     * @param wwpn
     *            The wwpn
     */
    @JsonProperty("wwpn")
    public void setWwpn(final String wwpn) {
        this.wwpn = wwpn;
    }

    /**
     * 
     * @return The wwnn
     */
    @JsonProperty("wwnn")
    public String getWwnn() {
        return wwnn;
    }

    /**
     * 
     * @param wwnn
     *            The wwnn
     */
    @JsonProperty("wwnn")
    public void setWwnn(final String wwnn) {
        this.wwnn = wwnn;
    }

    /**
     * 
     * @return The logins
     */
    @JsonProperty("logins")
    public String getLogins() {
        return logins;
    }

    /**
     * 
     * @param logins
     *            The logins
     */
    @JsonProperty("logins")
    public void setLogins(final String logins) {
        this.logins = logins;
    }

    /**
     * 
     * @return The opOnline
     */
    @JsonProperty("opOnline")
    public Boolean getOpOnline() {
        return opOnline;
    }

    /**
     * 
     * @param opOnline
     *            The opOnline
     */
    @JsonProperty("opOnline")
    public void setOpOnline(final Boolean opOnline) {
        this.opOnline = opOnline;
    }

    /**
     * 
     * @return The opOnlineReason
     */
    @JsonProperty("opOnlineReason")
    public String getOpOnlineReason() {
        return opOnlineReason;
    }

    /**
     * 
     * @param opOnlineReason
     *            The opOnlineReason
     */
    @JsonProperty("opOnlineReason")
    public void setOpOnlineReason(final String opOnlineReason) {
        this.opOnlineReason = opOnlineReason;
    }

    /**
     * 
     * @return The loginsCount
     */
    @JsonProperty("loginsCount")
    public Integer getLoginsCount() {
        return loginsCount;
    }

    /**
     * 
     * @param loginsCount
     *            The loginsCount
     */
    @JsonProperty("loginsCount")
    public void setLoginsCount(final Integer loginsCount) {
        this.loginsCount = loginsCount;
    }

    /**
     * 
     * @return The fcfMac
     */
    @JsonProperty("fcfMac")
    public String getFcfMac() {
        return fcfMac;
    }

    /**
     * 
     * @param fcfMac
     *            The fcfMac
     */
    @JsonProperty("fcfMac")
    public void setFcfMac(final String fcfMac) {
        this.fcfMac = fcfMac;
    }

    /**
     * 
     * @return The principleInterconnectNameList
     */
    @JsonProperty("principleInterconnectNameList")
    public List<String> getPrincipleInterconnectNameList() {
        return principleInterconnectNameList;
    }

    /**
     * 
     * @param principleInterconnectNameList
     *            The principleInterconnectNameList
     */
    @JsonProperty("principleInterconnectNameList")
    public void setPrincipleInterconnectNameList(final List<String> principleInterconnectNameList) {
        this.principleInterconnectNameList = principleInterconnectNameList;
    }

    /**
     * 
     * @return The neighborInterconnectName
     */
    @JsonProperty("neighborInterconnectName")
    public String getNeighborInterconnectName() {
        return neighborInterconnectName;
    }

    /**
     * 
     * @param neighborInterconnectName
     *            The neighborInterconnectName
     */
    @JsonProperty("neighborInterconnectName")
    public void setNeighborInterconnectName(final String neighborInterconnectName) {
        this.neighborInterconnectName = neighborInterconnectName;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(principleInterconnectName).append(wwpn).append(wwnn).append(logins).append(opOnline)
                .append(opOnlineReason).append(loginsCount).append(fcfMac).append(principleInterconnectNameList)
                .append(neighborInterconnectName).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FcPortProperties) == false) {
            return false;
        }
        final FcPortProperties rhs = ((FcPortProperties) other);
        return new EqualsBuilder().append(principleInterconnectName, rhs.principleInterconnectName).append(wwpn, rhs.wwpn)
                .append(wwnn, rhs.wwnn).append(logins, rhs.logins).append(opOnline, rhs.opOnline)
                .append(opOnlineReason, rhs.opOnlineReason).append(loginsCount, rhs.loginsCount).append(fcfMac, rhs.fcfMac)
                .append(principleInterconnectNameList, rhs.principleInterconnectNameList)
                .append(neighborInterconnectName, rhs.neighborInterconnectName).isEquals();
    }

}
