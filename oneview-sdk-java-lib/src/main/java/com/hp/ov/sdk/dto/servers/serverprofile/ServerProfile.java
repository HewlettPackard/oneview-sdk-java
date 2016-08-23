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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;

/**
 * The ServerProfile data transfer object (DTO) contains the information used to
 * represent a server profile in the system. The server profile Dto specifies
 * the connections, the bios setting, the storage details and firmware details
 * to the server hardware. It is also used in creation of templates which are to
 * be applied to servers. It is passed in to the add/update server profile REST
 * api, as well as the add/update server profile through java client api.
 */
public class ServerProfile extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private ProfileAffinity affinity = ProfileAffinity.Bay;
    private String associatedServer;
    private Bios bios;
    private Boot boot;
    private BootMode bootMode;
    private List<ProfileConnection> connections = new ArrayList<>();
    private Integer enclosureBay;
    private String enclosureGroupUri;
    private String enclosureUri;
    private Firmware firmware;
    private Boolean hideUnusedFlexNics;
    private Boolean inProgress;
    @Since(300)
    private String iscsiInitiatorName;
    @Since(300)
    private SourceType iscsiInitiatorNameType;
    private LocalStorage localStorage;
    private AssignmentType macType = AssignmentType.Virtual;
    @Since(300)
    private OsDeploymentSettings osDeploymentSettings;
    private SanStorage sanStorage;
    private String serialNumber;
    private AssignmentType serialNumberType = AssignmentType.Virtual;
    private String serverHardwareTypeUri;
    private String serverHardwareUri;
    @Since(200)
    private String serverProfileTemplateUri;
    private String taskUri;
    @Since(200)
    private TemplateCompliance templateCompliance = TemplateCompliance.Unknown;
    private String uuid;
    private AssignmentType wwnType = AssignmentType.Virtual;

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
     * @return the associatedServer
     */
    public String getAssociatedServer() {
        return associatedServer;
    }

    /**
     * @param associatedServer the associatedServer to set
     */
    public void setAssociatedServer(String associatedServer) {
        this.associatedServer = associatedServer;
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
    public List<ProfileConnection> getConnections() {
        return connections;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(List<ProfileConnection> connections) {
        this.connections = connections;
    }

    /**
     * @return the enclosureBay
     */
    public Integer getEnclosureBay() {
        return enclosureBay;
    }

    /**
     * @param enclosureBay the enclosureBay to set
     */
    public void setEnclosureBay(Integer enclosureBay) {
        this.enclosureBay = enclosureBay;
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
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
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
     * @return the inProgress
     */
    public Boolean getInProgress() {
        return inProgress;
    }

    /**
     * @param inProgress the inProgress to set
     */
    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    /**
     * @return the iscsiInitiatorName
     */
    public String getIscsiInitiatorName() {
        return iscsiInitiatorName;
    }

    /**
     * @param iscsiInitiatorName the iscsiInitiatorName to set
     */
    public void setIscsiInitiatorName(String iscsiInitiatorName) {
        this.iscsiInitiatorName = iscsiInitiatorName;
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
    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    /**
     * @param localStorage the localStorage to set
     */
    public void setLocalStorage(LocalStorage localStorage) {
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
     * @return the osDeploymentSettings
     */
    public OsDeploymentSettings getOsDeploymentSettings() {
        return osDeploymentSettings;
    }

    /**
     * @param osDeploymentSettings the osDeploymentSettings to set
     */
    public void setOsDeploymentSettings(OsDeploymentSettings osDeploymentSettings) {
        this.osDeploymentSettings = osDeploymentSettings;
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
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
     * @return the serverHardwareUri
     */
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }

    /**
     * @param serverHardwareUri the serverHardwareUri to set
     */
    public void setServerHardwareUri(String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     * @return the serverProfileTemplateUri
     */
    public String getServerProfileTemplateUri() {
        return serverProfileTemplateUri;
    }

    /**
     * @param serverProfileTemplateUri the serverProfileTemplateUri to set
     */
    public void setServerProfileTemplateUri(String serverProfileTemplateUri) {
        this.serverProfileTemplateUri = serverProfileTemplateUri;
    }

    /**
     * @return the taskUri
     */
    public String getTaskUri() {
        return taskUri;
    }

    /**
     * @param taskUri the taskUri to set
     */
    public void setTaskUri(String taskUri) {
        this.taskUri = taskUri;
    }

    /**
     * @return the templateCompliance
     */
    public TemplateCompliance getTemplateCompliance() {
        return templateCompliance;
    }

    /**
     * @param templateCompliance the templateCompliance to set
     */
    public void setTemplateCompliance(TemplateCompliance templateCompliance) {
        this.templateCompliance = templateCompliance;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
