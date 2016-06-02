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

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ServerProfileTemplate extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String affinity;
    private BiosSettingsTemplate bios;
    private BootSettingsTemplate boot;
    private BootModeSettingsTemplate bootMode;
    private List<ProfileConnectionTemplate> connections;
    private String enclosureGroupUri;
    private FirmwareSettingsTemplate firmware;
    private Boolean hideUnusedFlexNics;
    private LocalStorageSettingsTemplate localStorage;
    private String macType;
    private SanStorageTemplate sanStorage;
    private String serialNumberType;
    private String serverHardwareTypeUri;
    private String serverProfileDescription;
    private String wwnType;

    /**
     * @return the affinity
     */
    public String getAffinity() {
        return affinity;
    }

    /**
     * @param affinity the affinity to set
     */
    public void setAffinity(String affinity) {
        this.affinity = affinity;
    }

    /**
     * @return the bios
     */
    public BiosSettingsTemplate getBios() {
        return bios;
    }

    /**
     * @param bios the bios to set
     */
    public void setBios(BiosSettingsTemplate bios) {
        this.bios = bios;
    }

    /**
     * @return the boot
     */
    public BootSettingsTemplate getBoot() {
        return boot;
    }

    /**
     * @param boot the boot to set
     */
    public void setBoot(BootSettingsTemplate boot) {
        this.boot = boot;
    }

    /**
     * @return the bootMode
     */
    public BootModeSettingsTemplate getBootMode() {
        return bootMode;
    }

    /**
     * @param bootMode the bootMode to set
     */
    public void setBootMode(BootModeSettingsTemplate bootMode) {
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
    public FirmwareSettingsTemplate getFirmware() {
        return firmware;
    }

    /**
     * @param firmware the firmware to set
     */
    public void setFirmware(FirmwareSettingsTemplate firmware) {
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
    public String getMacType() {
        return macType;
    }

    /**
     * @param macType the macType to set
     */
    public void setMacType(String macType) {
        this.macType = macType;
    }

    /**
     * @return the sanStorage
     */
    public SanStorageTemplate getSanStorage() {
        return sanStorage;
    }

    /**
     * @param sanStorage the sanStorage to set
     */
    public void setSanStorage(SanStorageTemplate sanStorage) {
        this.sanStorage = sanStorage;
    }

    /**
     * @return the serialNumberType
     */
    public String getSerialNumberType() {
        return serialNumberType;
    }

    /**
     * @param serialNumberType the serialNumberType to set
     */
    public void setSerialNumberType(String serialNumberType) {
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
    public String getWwnType() {
        return wwnType;
    }

    /**
     * @param wwnType the wwnType to set
     */
    public void setWwnType(String wwnType) {
        this.wwnType = wwnType;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof InterconnectType) {
            ServerProfileTemplate that = (ServerProfileTemplate) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(affinity, that.affinity)
                    .append(bios, that.bios)
                    .append(boot, that.boot)
                    .append(bootMode, that.bootMode)
                    .append(connections, that.connections)
                    .append(enclosureGroupUri, that.enclosureGroupUri)
                    .append(firmware, that.firmware)
                    .append(hideUnusedFlexNics, that.hideUnusedFlexNics)
                    .append(localStorage, that.localStorage)
                    .append(macType, that.macType)
                    .append(sanStorage, that.sanStorage)
                    .append(serialNumberType, that.serialNumberType)
                    .append(serverHardwareTypeUri, that.serverHardwareTypeUri)
                    .append(serverProfileDescription, that.serverProfileDescription)
                    .append(wwnType, that.wwnType)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(affinity)
                .append(bios)
                .append(boot)
                .append(bootMode)
                .append(connections)
                .append(enclosureGroupUri)
                .append(firmware)
                .append(hideUnusedFlexNics)
                .append(localStorage)
                .append(macType)
                .append(sanStorage)
                .append(serialNumberType)
                .append(serverHardwareTypeUri)
                .append(serverProfileDescription)
                .append(wwnType)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("affinity", affinity)
                .append("bios", bios)
                .append("boot", boot)
                .append("bootMode", bootMode)
                .append("connections", connections)
                .append("enclosureGroupUri", enclosureGroupUri)
                .append("firmware", firmware)
                .append("hideUnusedFlexNics", hideUnusedFlexNics)
                .append("localStorage", localStorage)
                .append("macType", macType)
                .append("sanStorage", sanStorage)
                .append("serialNumberType", serialNumberType)
                .append("serverHardwareTypeUri", serverHardwareTypeUri)
                .append("serverProfileDescription", serverProfileDescription)
                .append("wwnType", wwnType)
                .toString();
    }
}
