/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Oa implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Integer bayNumber;
    private Boolean dhcpEnable;
    private Boolean dhcpIpv6Enable;
    private String fqdnHostName;
    private String fwBuildDate;
    private String fwVersion;
    private String ipAddress;
    private List<OaIpv6Address> ipv6Addresses = new ArrayList<OaIpv6Address>();
    private OaRole role;
    private OaState state;

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber
     *            the bayNumber to set
     */
    public void setBayNumber(final Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the dhcpEnable
     */
    public Boolean getDhcpEnable() {
        return dhcpEnable;
    }

    /**
     * @param dhcpEnable
     *            the dhcpEnable to set
     */
    public void setDhcpEnable(final Boolean dhcpEnable) {
        this.dhcpEnable = dhcpEnable;
    }

    /**
     * @return the dhcpIpv6Enable
     */
    public Boolean getDhcpIpv6Enable() {
        return dhcpIpv6Enable;
    }

    /**
     * @param dhcpIpv6Enable
     *            the dhcpIpv6Enable to set
     */
    public void setDhcpIpv6Enable(final Boolean dhcpIpv6Enable) {
        this.dhcpIpv6Enable = dhcpIpv6Enable;
    }

    /**
     * @return the fqdnHostName
     */
    public String getFqdnHostName() {
        return fqdnHostName;
    }

    /**
     * @param fqdnHostName
     *            the fqdnHostName to set
     */
    public void setFqdnHostName(final String fqdnHostName) {
        this.fqdnHostName = fqdnHostName;
    }

    /**
     * @return the fwBuildDate
     */
    public String getFwBuildDate() {
        return fwBuildDate;
    }

    /**
     * @param fwBuildDate
     *            the fwBuildDate to set
     */
    public void setFwBuildDate(final String fwBuildDate) {
        this.fwBuildDate = fwBuildDate;
    }

    /**
     * @return the fwVersion
     */
    public String getFwVersion() {
        return fwVersion;
    }

    /**
     * @param fwVersion
     *            the fwVersion to set
     */
    public void setFwVersion(final String fwVersion) {
        this.fwVersion = fwVersion;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress
     *            the ipAddress to set
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * @return the ipv6Addresses
     */
    public List<OaIpv6Address> getIpv6Addresses() {
        return ipv6Addresses;
    }

    /**
     * @param ipv6Addresses
     *            the ipv6Addresses to set
     */
    public void setIpv6Addresses(final List<OaIpv6Address> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
    }

    /**
     * @return the role
     */
    public OaRole getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(final OaRole role) {
        this.role = role;
    }

    /**
     * @return the state
     */
    public OaState getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(final OaState state) {
        this.state = state;
    }

}
