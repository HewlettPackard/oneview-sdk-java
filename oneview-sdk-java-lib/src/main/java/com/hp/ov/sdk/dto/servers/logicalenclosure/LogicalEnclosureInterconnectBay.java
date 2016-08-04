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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogicalEnclosureInterconnectBay {

    private Integer bayNumber;
    private InterconnectLicenseIntent licenseIntent;

    public Integer getBayNumber() {
        return bayNumber;
    }

    public void setBayNumber(Integer bayNumber) {
        this.bayNumber = bayNumber;
    }

    public InterconnectLicenseIntent getLicenseIntent() {
        return licenseIntent;
    }

    public void setLicenseIntent(InterconnectLicenseIntent licenseIntent) {
        this.licenseIntent = licenseIntent;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof LogicalEnclosureInterconnectBay) {
            LogicalEnclosureInterconnectBay that = (LogicalEnclosureInterconnectBay) obj;

            return new EqualsBuilder()
                    .append(bayNumber, that.bayNumber)
                    .append(licenseIntent, that.licenseIntent)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bayNumber)
                .append(licenseIntent)
                .toHashCode();
    }
}
