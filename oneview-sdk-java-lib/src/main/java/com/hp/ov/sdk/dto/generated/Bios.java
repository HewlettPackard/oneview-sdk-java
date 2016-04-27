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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class Bios implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Boolean manageBios;
    private List<OverriddenSetting> overriddenSettings = new ArrayList<OverriddenSetting>();

    /**
     * 
     * @return The manageBios
     */
    public Boolean getManageBios() {
        return manageBios;
    }

    /**
     * 
     * @param manageBios
     *            The manageBios
     */
    public void setManageBios(final Boolean manageBios) {
        this.manageBios = manageBios;
    }

    /**
     * 
     * @return The overriddenSettings
     */
    public List<OverriddenSetting> getOverriddenSettings() {
        return overriddenSettings;
    }

    /**
     * 
     * @param overriddenSettings
     *            The overriddenSettings
     */
    public void setOverriddenSettings(final List<OverriddenSetting> overriddenSettings) {
        this.overriddenSettings = overriddenSettings;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(manageBios).append(overriddenSettings).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Bios) == false) {
            return false;
        }
        final Bios rhs = ((Bios) other);
        return new EqualsBuilder().append(manageBios, rhs.manageBios).append(overriddenSettings, rhs.overriddenSettings).isEquals();
    }

}
