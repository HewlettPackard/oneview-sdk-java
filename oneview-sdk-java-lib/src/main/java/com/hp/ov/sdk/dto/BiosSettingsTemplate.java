/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.servers.OverriddenSetting;

public class BiosSettingsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean manageBios;
    private List<OverriddenSetting> overriddenSettings;

    /**
     * @return the manageBios
     */
    public Boolean getManageBios() {
        return manageBios;
    }

    /**
     * @param manageBios the manageBios to set
     */
    public void setManageBios(Boolean manageBios) {
        this.manageBios = manageBios;
    }

    /**
     * @return the overriddenSettings
     */
    public List<OverriddenSetting> getOverriddenSettings() {
        return overriddenSettings;
    }

    /**
     * @param overriddenSettings the overriddenSettings to set
     */
    public void setOverriddenSettings(List<OverriddenSetting> overriddenSettings) {
        this.overriddenSettings = overriddenSettings;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
