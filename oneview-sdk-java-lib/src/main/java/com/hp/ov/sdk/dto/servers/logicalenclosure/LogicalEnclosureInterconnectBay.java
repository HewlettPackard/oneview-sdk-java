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
package com.hp.ov.sdk.dto.servers.logicalenclosure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

public class LogicalEnclosureInterconnectBay implements Serializable {

    private static final long serialVersionUID = 3078173735872737670L;

    private Integer bayNumber;
    @Until(299)
    private InterconnectLicenseIntent licenseIntent;
    @Since(300)
    private Map<LicenseIntentName, InterconnectLicenseIntent> licenseIntents = new HashMap<>();

    /**
     * @return the bayNumber
     */
    public Integer getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the licenseIntent
     */
    public InterconnectLicenseIntent getLicenseIntent() {
        return licenseIntent;
    }

    /**
     * @param licenseIntent the licenseIntent to set
     */
    public void setLicenseIntent(InterconnectLicenseIntent licenseIntent) {
        this.licenseIntent = licenseIntent;
    }

    /**
     * @return the licenseIntents
     */
    public Map<LicenseIntentName, InterconnectLicenseIntent> getLicenseIntents() {
        return licenseIntents;
    }

    /**
     * @param licenseIntents the licenseIntents to set
     */
    public void setLicenseIntents(Map<LicenseIntentName, InterconnectLicenseIntent> licenseIntents) {
        this.licenseIntents = licenseIntents;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
