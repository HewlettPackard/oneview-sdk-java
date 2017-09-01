/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.samples;

import java.util.List;

import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.Bios;
import com.hp.ov.sdk.dto.servers.Boot;
import com.hp.ov.sdk.dto.servers.Firmware;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.serverprofile.LocalStorage;
import com.hp.ov.sdk.dto.servers.serverprofile.OsDeploymentSettings;

public class ServerProfileValue {

    private String templateName;
    private String bayName;
    private String description;
    private Boolean useBayNameForServerHardwareUri;
    private String enclosureGroupName;
    private ProfileAffinity affinity;
    private SanStorageForServerProfile sanStorageForServerProfile;
    private LocalStorage localStorage;
    private List<NetworkForServerProfile> networkForServerProfile;
    private Boot boot;
    private Bios bios;
    private Firmware firmware;
    private AssignmentType macType;
    private AssignmentType wwnType;
    private AssignmentType serialNumberType;
    private OsDeploymentSettings osDeploymentSettings;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }

    public String getBayName() {
        return bayName;
    }

    public void setBayName(final String bayName) {
        this.bayName = bayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Boolean getUseBayNameForServerHardwareUri() {
        return useBayNameForServerHardwareUri;
    }

    public void setUseBayNameForServerHardwareUri(final Boolean useBayNameForServerHardwareUri) {
        this.useBayNameForServerHardwareUri = useBayNameForServerHardwareUri;
    }

    public String getEnclosureGroupName() {
        return enclosureGroupName;
    }

    public void setEnclosureGroupName(final String enclosureGroupName) {
        this.enclosureGroupName = enclosureGroupName;
    }

    public ProfileAffinity getAffinity() {
        return affinity;
    }

    public void setAffinity(final ProfileAffinity affinity) {
        this.affinity = affinity;
    }

    public SanStorageForServerProfile getStorageVolumeForServerProfile() {
        return sanStorageForServerProfile;
    }

    public void setStorageVolumeForServerProfile(final SanStorageForServerProfile sanStorageForServerProfile) {
        this.sanStorageForServerProfile = sanStorageForServerProfile;
    }

    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    public void setLocalStorage(final LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    public List<NetworkForServerProfile> getNetworkForServerProfile() {
        return networkForServerProfile;
    }

    public void setNetworkForServerProfile(final List<NetworkForServerProfile> networkForServerProfile) {
        this.networkForServerProfile = networkForServerProfile;
    }

    public Boot getBoot() {
        return boot;
    }

    public void setBoot(final Boot boot) {
        this.boot = boot;
    }

    public Bios getBios() {
        return bios;
    }

    public void setBios(final Bios bios) {
        this.bios = bios;
    }

    public Firmware getFirmware() {
        return firmware;
    }

    public void setFirmware(final Firmware firmware) {
        this.firmware = firmware;
    }

    public AssignmentType getMacType() {
        return macType;
    }

    public void setMacType(final AssignmentType macType) {
        this.macType = macType;
    }

    public AssignmentType getWwnType() {
        return wwnType;
    }

    public void setWwnType(final AssignmentType wwnType) {
        this.wwnType = wwnType;
    }

    public AssignmentType getSerialNumberType() {
        return serialNumberType;
    }

    public void setSerialNumberType(final AssignmentType serialNumberType) {
        this.serialNumberType = serialNumberType;
    }

    public OsDeploymentSettings getOsDeploymentSettings() {
        return osDeploymentSettings;
    }

    public void setOsDeploymentSettings(final OsDeploymentSettings osDeploymentSettings) {
        this.osDeploymentSettings = osDeploymentSettings;
    }

}
