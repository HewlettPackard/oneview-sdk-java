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


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


public class BootMode implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Boolean manageMode = false;
    private String pxeBootPolicy;
    private String mode;

    /**
     * 
     * @return The manageMode
     */
    public Boolean getManageMode() {
        return manageMode;
    }

    /**
     * 
     * @param manageMode
     *            The manageMode
     */
    public void setManageMode(final Boolean manageMode) {
        this.manageMode = manageMode;
    }

    /**
     * 
     * @return The pxeBootPolicy
     */
    public String getPxeBootPolicy() {
        return pxeBootPolicy;
    }

    /**
     * 
     * @param pxeBootPolicy
     *            The pxeBootPolicy
     */
    public void setPxeBootPolicy(final String pxeBootPolicy) {
        this.pxeBootPolicy = pxeBootPolicy;
    }

    /**
     * 
     * @return The mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * 
     * @param mode
     *            The mode
     */
    public void setMode(final String mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(manageMode).append(pxeBootPolicy).append(mode).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BootMode) == false) {
            return false;
        }
        final BootMode rhs = ((BootMode) other);
        return new EqualsBuilder().append(manageMode, rhs.manageMode).append(pxeBootPolicy, rhs.pxeBootPolicy)
                .append(mode, rhs.mode).isEquals();
    }

}
