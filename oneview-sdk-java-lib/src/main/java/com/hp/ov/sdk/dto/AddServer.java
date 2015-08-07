/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import com.hp.ov.sdk.dto.generated.ServerHardware;

public class AddServer implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ConfigurationState configurationState;
    private Boolean force;
    private String hostname;
    private ServerHardware.LicensingIntent licensingIntent;
    private String password;
    private Boolean restore;
    private String username;

    /**
     * @return the configurationState
     */
    public ConfigurationState getConfigurationState() {
        return configurationState;
    }

    /**
     * @param configurationState
     *            the configurationState to set
     */
    public void setConfigurationState(ConfigurationState configurationState) {
        this.configurationState = configurationState;
    }

    /**
     * @return the force
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * @param force
     *            the force to set
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname
     *            the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the licensingIntent
     */
    public ServerHardware.LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     * @param licensingIntent
     *            the licensingIntent to set
     */
    public void setLicensingIntent(ServerHardware.LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the restore
     */
    public Boolean getRestore() {
        return restore;
    }

    /**
     * @param restore
     *            the restore to set
     */
    public void setRestore(Boolean restore) {
        this.restore = restore;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
