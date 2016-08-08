/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.networking;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class IcmLicenseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private int consumedCount;
    private String licenseType;
    private int requiredCount;
    private IcmLicenseStates state;

    /**
     * @return the consumedCount
     */
    public int getConsumedCount() {
        return consumedCount;
    }

    /**
     * @param consumedCount the consumedCount to set
     */
    public void setConsumedCount(int consumedCount) {
        this.consumedCount = consumedCount;
    }

    /**
     * @return the licenseType
     */
    public String getLicenseType() {
        return licenseType;
    }

    /**
     * @param licenseType the licenseType to set
     */
    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    /**
     * @return the requiredCount
     */
    public int getRequiredCount() {
        return requiredCount;
    }

    /**
     * @param requiredCount the requiredCount to set
     */
    public void setRequiredCount(int requiredCount) {
        this.requiredCount = requiredCount;
    }

    /**
     * @return the state
     */
    public IcmLicenseStates getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(IcmLicenseStates state) {
        this.state = state;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof IcmLicenseDto) == false) {
            return false;
        }
        final IcmLicenseDto rhs = ((IcmLicenseDto) other);
        return new EqualsBuilder()
                .append(consumedCount, rhs.consumedCount)
                .append(licenseType, rhs.licenseType)
                .append(requiredCount, rhs.requiredCount)
                .append(state, rhs.state)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(consumedCount)
                .append(licenseType)
                .append(requiredCount)
                .append(state)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
