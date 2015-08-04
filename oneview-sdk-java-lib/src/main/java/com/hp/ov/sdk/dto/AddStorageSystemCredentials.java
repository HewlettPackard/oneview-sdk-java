/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

public class AddStorageSystemCredentials implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String ip_hostname;
    private String password;
    private String username;

    /**
     * 
     * @return The ip_hostname
     */
    public String getIp_hostname() {
        return ip_hostname;
    }

    /**
     * 
     * @param ip_hostname
     *            The ip_hostname
     */
    public void setIp_hostname(final String ip_hostname) {
        this.ip_hostname = ip_hostname;
    }

    /**
     * 
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     *            The password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * 
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *            The username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

}
