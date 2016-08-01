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
package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.servers.LicensingIntent;

public class AddServer implements Serializable {

    private static final long serialVersionUID = -8835431804296816200L;

    private ConfigurationState configurationState;
    private Boolean force;
    private String hostname;
    private LicensingIntent licensingIntent;
    private String password;
    private Boolean restore;
    @Since(300)
    private List<String> scopeUris = new ArrayList<String>();
    private String username;

    /**
     * @return the configurationState
     */
    public ConfigurationState getConfigurationState() {
        return configurationState;
    }

    /**
     * @param configurationState the configurationState to set
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
     * @param force the force to set
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
     * @return the restore
     */
    public Boolean getRestore() {
        return restore;
    }

    /**
     * @param restore the restore to set
     */
    public void setRestore(Boolean restore) {
        this.restore = restore;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof AddServer) {
            AddServer that = (AddServer) obj;

            return new EqualsBuilder()
                    .append(configurationState, that.configurationState)
                    .append(force, that.force)
                    .append(hostname, that.hostname)
                    .append(licensingIntent, that.licensingIntent)
                    .append(password, that.password)
                    .append(restore, that.restore)
                    .append(scopeUris, that.scopeUris)
                    .append(username, that.username)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(configurationState)
                .append(force)
                .append(hostname)
                .append(licensingIntent)
                .append(password)
                .append(restore)
                .append(scopeUris)
                .append(username)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
