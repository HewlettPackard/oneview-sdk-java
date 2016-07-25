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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogicalEnclosureInterconnectBay {
    @SerializedName("bayNumber")
    @Expose
    private byte bayNumber;

    @SerializedName("licenseIntent")
    @Expose
    private InterconnectLicenseIntent licenseIntent;

    public byte getBayNumber() {
        return bayNumber;
    }

    public void setBayNumber(final byte bayNumber) {
        this.bayNumber = bayNumber;
    }

    public InterconnectLicenseIntent getLicenseIntent() {
        return licenseIntent;
    }

    public void setLicenseIntent(final InterconnectLicenseIntent licenseIntent) {
        this.licenseIntent = licenseIntent;
    }
}
