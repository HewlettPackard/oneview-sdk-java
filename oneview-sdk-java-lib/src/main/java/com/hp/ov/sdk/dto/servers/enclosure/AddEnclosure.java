/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.servers.enclosure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.hp.ov.sdk.dto.facilities.unmanageddevice.UnmanagedDevice;
import com.hp.ov.sdk.dto.servers.LicensingIntent;

public class AddEnclosure implements Serializable {

    private static final long serialVersionUID = 1L;

    private String enclosureGroupUri;
    private String enclosureUri;
    private String firmwareBaselineUri;
    private Boolean force;
    private Boolean forceInstallFirmware;
    private String hostname;
    private LicensingIntent licensingIntent;
    @Since(300)
    private MigrationState migrationState;
    private String password;
    @Since(300)
    private List<String> scopeUris = new ArrayList<>();
    private String state;
    @Since(200)
    private String type;
    @Until(299)
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
     * @param enclosureGroupUri the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     * @return the firmwareBaselineUri
     */
    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }

    /**
     * @param firmwareBaselineUri the firmwareBaselineUri to set
     */
    public void setFirmwareBaselineUri(String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
    }

    /**
     * @return the force
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * @param force the force to set
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * @return the forceInstallFirmware
     */
    public Boolean getForceInstallFirmware() {
        return forceInstallFirmware;
    }

    /**
     * @param forceInstallFirmware the forceInstallFirmware to set
     */
    public void setForceInstallFirmware(Boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the licensingIntent
     */
    public LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     * @param licensingIntent the licensingIntent to set
     */
    public void setLicensingIntent(LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    /**
     * @return the migrationState
     */
    public MigrationState getMigrationState() {
        return migrationState;
    }

    /**
     * @param migrationState the migrationState to set
     */
    public void setMigrationState(MigrationState migrationState) {
        this.migrationState = migrationState;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the scopeUris
     */
    public List<String> getScopeUris() {
        return scopeUris;
    }

    /**
     * @param scopeUris the scopeUris to set
     */
    public void setScopeUris(List<String> scopeUris) {
        this.scopeUris = scopeUris;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the unmanagedEnclosure
     */
    public UnmanagedDevice getUnmanagedEnclosure() {
        return unmanagedEnclosure;
    }

    /**
     * @param unmanagedEnclosure the unmanagedEnclosure to set
     */
    public void setUnmanagedEnclosure(UnmanagedDevice unmanagedEnclosure) {
        this.unmanagedEnclosure = unmanagedEnclosure;
    }

    /**
     * @return the updateFirmwareOn
     */
    public FwBaselineOptions getUpdateFirmwareOn() {
        return updateFirmwareOn;
    }

    /**
     * @param updateFirmwareOn the updateFirmwareOn to set
     */
    public void setUpdateFirmwareOn(FwBaselineOptions updateFirmwareOn) {
        this.updateFirmwareOn = updateFirmwareOn;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AddEnclosure) == false) {
            return false;
        }
        final AddEnclosure rhs = ((AddEnclosure) other);
        return new EqualsBuilder()
                .append(enclosureGroupUri, rhs.enclosureGroupUri)
                .append(enclosureUri, rhs.enclosureUri)
                .append(firmwareBaselineUri, rhs.firmwareBaselineUri)
                .append(force, rhs.force)
                .append(forceInstallFirmware, rhs.forceInstallFirmware)
                .append(hostname, rhs.hostname)
                .append(licensingIntent, rhs.licensingIntent)
                .append(migrationState, rhs.migrationState)
                .append(password, rhs.password)
                .append(scopeUris, rhs.scopeUris)
                .append(state, rhs.state)
                .append(type, rhs.type)
                .append(unmanagedEnclosure, rhs.unmanagedEnclosure)
                .append(updateFirmwareOn, rhs.updateFirmwareOn)
                .append(username, rhs.username)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(enclosureGroupUri)
                .append(enclosureUri)
                .append(firmwareBaselineUri)
                .append(force)
                .append(forceInstallFirmware)
                .append(hostname)
                .append(licensingIntent)
                .append(migrationState)
                .append(password)
                .append(scopeUris)
                .append(state)
                .append(type)
                .append(unmanagedEnclosure)
                .append(updateFirmwareOn)
                .append(username)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
