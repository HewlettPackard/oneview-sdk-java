/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

public class AddEnclosureV2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private String enclosureGroupUri;
    private String enclosureUri;
    private String firmwareBaselineUri;
    private Boolean force;
    private Boolean forceInstallFirmware;
    private String hostname;
    private LicensingIntent licensingIntent;
    private String password;
    private String state;
    private UnmanagedDevice unmanagedEnclosure;
    private FwBaselineOptions updateFirmwareOn;
    private String username;

    /**
     * @return the enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * @param enclosureGroupUri
     *            the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri
     *            the enclosureUri to set
     */
    public void setEnclosureUri(final String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     * @return the firmwareBaselineUri
     */
    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }

    /**
     * @param firmwareBaselineUri
     *            the firmwareBaselineUri to set
     */
    public void setFirmwareBaselineUri(final String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
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
    public void setForce(final Boolean force) {
        this.force = force;
    }

    /**
     * @return the forceInstallFirmware
     */
    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    /**
     * @param forceInstallFirmware
     *            the forceInstallFirmware to set
     */
    public void setForceInstallFirmware(final Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
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
    public void setHostname(final String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the licensingIntent
     */
    public LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     * @param licensingIntent
     *            the licensingIntent to set
     */
    public void setLicensingIntent(final LicensingIntent licensingIntent) {
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
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     *            the state to set
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * @return the unmanagedEnclosure
     */
    public UnmanagedDevice getUnmanagedEnclosure() {
        return unmanagedEnclosure;
    }

    /**
     * @param unmanagedEnclosure
     *            the unmanagedEnclosure to set
     */
    public void setUnmanagedEnclosure(final UnmanagedDevice unmanagedEnclosure) {
        this.unmanagedEnclosure = unmanagedEnclosure;
    }

    /**
     * @return the updateFirmwareOn
     */
    public FwBaselineOptions getUpdateFirmwareOn() {
        return updateFirmwareOn;
    }

    /**
     * @param updateFirmwareOn
     *            the updateFirmwareOn to set
     */
    public void setUpdateFirmwareOn(final FwBaselineOptions updateFirmwareOn) {
        this.updateFirmwareOn = updateFirmwareOn;
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
    public void setUsername(final String username) {
        this.username = username;
    }

}
