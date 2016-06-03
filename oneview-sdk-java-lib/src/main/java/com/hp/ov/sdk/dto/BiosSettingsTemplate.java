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

public class BiosSettingsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean manageBios;
    private List<OverriddenSettingsTemplate> overriddenSettings;

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
    public List<OverriddenSettingsTemplate> getOverriddenSettings() {
        return overriddenSettings;
    }

    /**
     * @param overriddenSettings the overriddenSettings to set
     */
    public void setOverriddenSettings(List<OverriddenSettingsTemplate> overriddenSettings) {
        this.overriddenSettings = overriddenSettings;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        BiosSettingsTemplate that = (BiosSettingsTemplate) obj;

        return new EqualsBuilder()
                .append(manageBios, that.manageBios)
                .append(overriddenSettings, that.overriddenSettings)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(manageBios)
                .append(overriddenSettings)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("manageBios", manageBios)
                .append("overriddenSettings", overriddenSettings)
                .toString();
    }

}
