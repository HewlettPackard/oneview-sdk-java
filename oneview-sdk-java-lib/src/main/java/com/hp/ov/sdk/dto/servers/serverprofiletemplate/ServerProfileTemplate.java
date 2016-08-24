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
package com.hp.ov.sdk.dto.servers.serverprofiletemplate;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.servers.Bios;
import com.hp.ov.sdk.dto.servers.Boot;
import com.hp.ov.sdk.dto.servers.BootMode;
import com.hp.ov.sdk.dto.servers.Firmware;
import com.hp.ov.sdk.dto.servers.ProfileAffinity;
import com.hp.ov.sdk.dto.servers.SourceType;
import com.hp.ov.sdk.dto.servers.AssignmentType;
import com.hp.ov.sdk.dto.servers.SanStorage;

public class ServerProfileTemplate extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private ProfileAffinity affinity;
    private Bios bios;
    private Boot boot;
    private BootMode bootMode;
    private List<ProfileConnectionTemplate> connections;
    private String enclosureGroupUri;
    private Firmware firmware;
    private Boolean hideUnusedFlexNics;
    @Since(300)
    private SourceType iscsiInitiatorNameType;
    private LocalStorageSettingsTemplate localStorage;
    private AssignmentType macType;
    private SanStorage sanStorage;
    private AssignmentType serialNumberType;
    private String serverHardwareTypeUri;
    private String serverProfileDescription;
    private AssignmentType wwnType;

    /**
     * @return the affinity
     */
    public ProfileAffinity getAffinity() {
        return affinity;
    }

    /**
     * @param affinity the affinity to set
     */
    public void setAffinity(ProfileAffinity affinity) {
        this.affinity = affinity;
    }

    /**
     * @return the bios
     */
    public Bios getBios() {
        return bios;
    }

    /**
     * @param bios the bios to set
     */
    public void setBios(Bios bios) {
        this.bios = bios;
    }

    /**
     * @return the boot
     */
    public Boot getBoot() {
        return boot;
    }

    /**
     * @param boot the boot to set
     */
    public void setBoot(Boot boot) {
        this.boot = boot;
    }

    /**
     * @return the bootMode
     */
    public BootMode getBootMode() {
        return bootMode;
    }

    /**
     * @param bootMode the bootMode to set
     */
    public void setBootMode(BootMode bootMode) {
        this.bootMode = bootMode;
    }

    /**
     * @return the connections
     */
    public List<ProfileConnectionTemplate> getConnections() {
        return connections;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(List<ProfileConnectionTemplate> connections) {
        this.connections = connections;
    }

    /**
     * @return the enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     * @param enclosureGroupUri the enclosureGroupUri to set
     */
    public void setEnclosureGroupUri(String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * @return the firmware
     */
    public Firmware getFirmware() {
        return firmware;
    }

    /**
     * @param firmware the firmware to set
     */
    public void setFirmware(Firmware firmware) {
        this.firmware = firmware;
    }

    /**
     * @return the hideUnusedFlexNics
     */
    public Boolean getHideUnusedFlexNics() {
        return hideUnusedFlexNics;
    }

    /**
     * @param hideUnusedFlexNics the hideUnusedFlexNics to set
     */
    public void setHideUnusedFlexNics(Boolean hideUnusedFlexNics) {
        this.hideUnusedFlexNics = hideUnusedFlexNics;
    }

    /**
     * @return the iscsiInitiatorNameType
     */
    public SourceType getIscsiInitiatorNameType() {
        return iscsiInitiatorNameType;
    }

    /**
     * @param iscsiInitiatorNameType the iscsiInitiatorNameType to set
     */
    public void setIscsiInitiatorNameType(SourceType iscsiInitiatorNameType) {
        this.iscsiInitiatorNameType = iscsiInitiatorNameType;
    }

    /**
     * @return the localStorage
     */
    public LocalStorageSettingsTemplate getLocalStorage() {
        return localStorage;
    }

    /**
     * @param localStorage the localStorage to set
     */
    public void setLocalStorage(LocalStorageSettingsTemplate localStorage) {
        this.localStorage = localStorage;
    }

    /**
     * @return the macType
     */
    public AssignmentType getMacType() {
        return macType;
    }

    /**
     * @param macType the macType to set
     */
    public void setMacType(AssignmentType macType) {
        this.macType = macType;
    }

    /**
     * @return the sanStorage
     */
    public SanStorage getSanStorage() {
        return sanStorage;
    }

    /**
     * @param sanStorage the sanStorage to set
     */
    public void setSanStorage(SanStorage sanStorage) {
        this.sanStorage = sanStorage;
    }

    /**
     * @return the serialNumberType
     */
    public AssignmentType getSerialNumberType() {
        return serialNumberType;
    }

    /**
     * @param serialNumberType the serialNumberType to set
     */
    public void setSerialNumberType(AssignmentType serialNumberType) {
        this.serialNumberType = serialNumberType;
    }

    /**
     * @return the serverHardwareTypeUri
     */
    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }

    /**
     * @param serverHardwareTypeUri the serverHardwareTypeUri to set
     */
    public void setServerHardwareTypeUri(String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     * @return the serverProfileDescription
     */
    public String getServerProfileDescription() {
        return serverProfileDescription;
    }

    /**
     * @param serverProfileDescription the serverProfileDescription to set
     */
    public void setServerProfileDescription(String serverProfileDescription) {
        this.serverProfileDescription = serverProfileDescription;
    }

    /**
     * @return the wwnType
     */
    public AssignmentType getWwnType() {
        return wwnType;
    }

    /**
     * @param wwnType the wwnType to set
     */
    public void setWwnType(AssignmentType wwnType) {
        this.wwnType = wwnType;
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
