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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class BootModeSettingsTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean manageMode;
    private String mode;
    private String pxeBootPolicy;

    /**
     * @return the manageMode
     */
    public Boolean getManageMode() {
        return manageMode;
    }

    /**
     * @param manageMode the manageMode to set
     */
    public void setManageMode(Boolean manageMode) {
        this.manageMode = manageMode;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the pxeBootPolicy
     */
    public String getPxeBootPolicy() {
        return pxeBootPolicy;
    }

    /**
     * @param pxeBootPolicy the pxeBootPolicy to set
     */
    public void setPxeBootPolicy(String pxeBootPolicy) {
        this.pxeBootPolicy = pxeBootPolicy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        BootModeSettingsTemplate that = (BootModeSettingsTemplate) obj;

        return new EqualsBuilder()
                .append(manageMode, that.manageMode)
                .append(mode, that.mode)
                .append(pxeBootPolicy, that.pxeBootPolicy)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(manageMode)
                .append(mode)
                .append(pxeBootPolicy)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("manageMode", manageMode)
                .append("mode", mode)
                .append("pxeBootPolicy", pxeBootPolicy)
                .toString();
    }

}
