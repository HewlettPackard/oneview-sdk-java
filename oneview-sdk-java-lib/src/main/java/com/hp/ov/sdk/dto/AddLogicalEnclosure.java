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
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("AddLogicalEnclosure")
@JsonPropertyOrder({
    "name",
    "enclosureUris",
    "enclosureGroupUri",
    "firmwareBaselineUri",
    "forceInstallFirmware" })

public class AddLogicalEnclosure {
    private String name;
    private List<String> enclosureUris = new ArrayList<String>();
    private String enclosureGroupUri;
    private String firmwareBaselineUri;
    private boolean forceInstallFirmware;

    public AddLogicalEnclosure() {
        // an empty constructor
    }

    public AddLogicalEnclosure(final String name, final List<String> enclosureUris, final String enclosureGroupUri,
            final String firmwareBaselineUri, final boolean forceInstallFirmware) {
        this.name = name;
        if (enclosureUris != null) {
            this.enclosureUris.addAll(enclosureUris);
        }
        this.enclosureGroupUri = enclosureGroupUri;
        this.firmwareBaselineUri = firmwareBaselineUri;
        this.forceInstallFirmware = forceInstallFirmware;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    public String getFirmwareBaselineUri() {
        return firmwareBaselineUri;
    }

    public void setFirmwareBaselineUri(final String firmwareBaselineUri) {
        this.firmwareBaselineUri = firmwareBaselineUri;
    }

    public boolean isForceInstallFirmware() {
        return forceInstallFirmware;
    }

    public void setForceInstallFirmware(final boolean forceInstallFirmware) {
        this.forceInstallFirmware = forceInstallFirmware;
    }

    public List<String> getEnclosureUris() {
        return this.enclosureUris;
    }

    public void setEnclosureUris(final List<String> enclosureUris) {
        this.enclosureUris = enclosureUris;
    }
}