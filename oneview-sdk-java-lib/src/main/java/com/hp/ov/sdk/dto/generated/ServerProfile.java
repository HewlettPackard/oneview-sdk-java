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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.ProfileConnectionV3;

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

    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("serverHardwareUri")
    private String serverHardwareUri;
    @JsonProperty("serverHardwareTypeUri")
    private String serverHardwareTypeUri;
    @JsonProperty("enclosureGroupUri")
    private String enclosureGroupUri;
    @JsonProperty("enclosureUri")
    private String enclosureUri;
    @JsonProperty("enclosureBay")
    private Integer enclosureBay;
    @JsonProperty("affinity")
    private ProfileAffinity affinity = ServerProfile.ProfileAffinity.Bay;
    @JsonProperty("associatedServer")
    private String associatedServer;
    @JsonProperty("hideUnusedFlexNics")
    private Boolean hideUnusedFlexNics;
    @JsonProperty("firmware")
    private Firmware firmware;
    @JsonProperty("macType")
    private AssignmentType macType = ServerProfile.AssignmentType.Virtual;
    @JsonProperty("wwnType")
    private AssignmentType wwnType = ServerProfile.AssignmentType.Virtual;
    @JsonProperty("serialNumberType")
    private AssignmentType serialNumberType = ServerProfile.AssignmentType.Virtual;
    @JsonProperty("inProgress")
    private Boolean inProgress;
    @JsonProperty("taskUri")
    private String taskUri;
    @JsonProperty("connections")
    private List<ProfileConnectionV3> connections = new ArrayList<ProfileConnectionV3>();
    @JsonProperty("bootMode")
    private BootMode bootMode;
    @JsonProperty("boot")
    private Boot boot;
    @JsonProperty("bios")
    private Bios bios;
    @JsonProperty("localStorage")
    private LocalStorage localStorage = null;
    @JsonProperty("sanStorage")
    private SanStorage sanStorage;
    @JsonProperty("serverProfileTemplateUri")
    @Since(200)
    private String serverProfileTemplateUri;
    @JsonProperty("templateCompliance")
    @Since(200)
    private TemplateCompliance templateCompliance = ServerProfile.TemplateCompliance.Unknown;

    /**
     *
     * @return The serialNumber
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     *            The serialNumber
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return The uuid
     */
    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    /**
     *
     * @param uuid
     *            The uuid
     */
    @JsonProperty("uuid")
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     * @return The serverHardwareUri
     */
    @JsonProperty("serverHardwareUri")
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }

    /**
     *
     * @param serverHardwareUri
     *            The serverHardwareUri
     */
    @JsonProperty("serverHardwareUri")
    public void setServerHardwareUri(final String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     *
     * @return The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public String getServerHardwareTypeUri() {
        return serverHardwareTypeUri;
    }

    /**
     *
     * @param serverHardwareTypeUri
     *            The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public void setServerHardwareTypeUri(final String serverHardwareTypeUri) {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     *
     * @return The enclosureGroupUri
     */
    @JsonProperty("enclosureGroupUri")
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     *
     * @param enclosureGroupUri
     *            The enclosureGroupUri
     */
    @JsonProperty("enclosureGroupUri")
    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     *
     * @return The enclosureUri
     */
    @JsonProperty("enclosureUri")
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     *
     * @param enclosureUri
     *            The enclosureUri
     */
    @JsonProperty("enclosureUri")
    public void setEnclosureUri(final String enclosureUri) {
        this.enclosureUri = enclosureUri;
    }

    /**
     *
     * @return The enclosureBay
     */
    @JsonProperty("enclosureBay")
    public Integer getEnclosureBay() {
        return enclosureBay;
    }

    /**
     *
     * @param enclosureBay
     *            The enclosureBay
     */
    @JsonProperty("enclosureBay")
    public void setEnclosureBay(final Integer enclosureBay) {
        this.enclosureBay = enclosureBay;
    }

    /**
     *
     * @return The affinity
     */
    @JsonProperty("affinity")
    public ServerProfile.ProfileAffinity getAffinity() {
        return affinity;
    }

    /**
     *
     * @param affinity
     *            The affinity
     */
    @JsonProperty("affinity")
    public void setAffinity(final ServerProfile.ProfileAffinity affinity) {
        this.affinity = affinity;
    }

    /**
     *
     * @return The associatedServer
     */
    @JsonProperty("associatedServer")
    public String getAssociatedServer() {
        return associatedServer;
    }

    /**
     *
     * @param associatedServer
     *            The associatedServer
     */
    @JsonProperty("associatedServer")
    public void setAssociatedServer(final String associatedServer) {
        this.associatedServer = associatedServer;
    }

    /**
     *
     * @return The hideUnusedFlexNics
     */
    @JsonProperty("hideUnusedFlexNics")
    public Boolean getHideUnusedFlexNics() {
        return hideUnusedFlexNics;
    }

    /**
     *
     * @param hideUnusedFlexNics
     *            The hideUnusedFlexNics
     */
    @JsonProperty("hideUnusedFlexNics")
    public void setHideUnusedFlexNics(final Boolean hideUnusedFlexNics) {
        this.hideUnusedFlexNics = hideUnusedFlexNics;
    }

    /**
     *
     * @return The firmware
     */
    @JsonProperty("firmware")
    public Firmware getFirmware() {
        return firmware;
    }

    /**
     *
     * @param firmware
     *            The firmware
     */
    @JsonProperty("firmware")
    public void setFirmware(final Firmware firmware) {
        this.firmware = firmware;
    }

    /**
     *
     * @return The macType
     */
    @JsonProperty("macType")
    public ServerProfile.AssignmentType getMacType() {
        return macType;
    }

    /**
     *
     * @param macType
     *            The macType
     */
    @JsonProperty("macType")
    public void setMacType(final ServerProfile.AssignmentType macType) {
        this.macType = macType;
    }

    /**
     *
     * @return The wwnType
     */
    @JsonProperty("wwnType")
    public AssignmentType getWwnType() {
        return wwnType;
    }

    /**
     *
     * @param wwnType
     *            The wwnType
     */
    @JsonProperty("wwnType")
    public void setWwnType(final AssignmentType wwnType) {
        this.wwnType = wwnType;
    }

    /**
     *
     * @return The serialNumberType
     */
    @JsonProperty("serialNumberType")
    public AssignmentType getSerialNumberType() {
        return serialNumberType;
    }

    /**
     *
     * @param serialNumberType
     *            The serialNumberType
     */
    @JsonProperty("serialNumberType")
    public void setSerialNumberType(final AssignmentType serialNumberType) {
        this.serialNumberType = serialNumberType;
    }

    /**
     *
     * @return The inProgress
     */
    @JsonProperty("inProgress")
    public Boolean getInProgress() {
        return inProgress;
    }

    /**
     *
     * @param inProgress
     *            The inProgress
     */
    @JsonProperty("inProgress")
    public void setInProgress(final Boolean inProgress) {
        this.inProgress = inProgress;
    }

    /**
     *
     * @return The taskUri
     */
    @JsonProperty("taskUri")
    public String getTaskUri() {
        return taskUri;
    }

    /**
     *
     * @param taskUri
     *            The taskUri
     */
    @JsonProperty("taskUri")
    public void setTaskUri(final String taskUri) {
        this.taskUri = taskUri;
    }

    /**
     *
     * @return The connections
     */
    @JsonProperty("connections")
    public List<ProfileConnectionV3> getConnections() {
        return connections;
    }

    /**
     *
     * @param connections
     *            The connections
     */
    @JsonProperty("connections")
    public void setConnections(final List<ProfileConnectionV3> connections) {
        this.connections = connections;
    }

    /**
     *
     * @return The bootMode
     */
    @JsonProperty("bootMode")
    public BootMode getBootMode() {
        return bootMode;
    }

    /**
     *
     * @param bootMode
     *            The bootMode
     */
    @JsonProperty("bootMode")
    public void setBootMode(final BootMode bootMode) {
        this.bootMode = bootMode;
    }

    /**
     *
     * @return The boot
     */
    @JsonProperty("boot")
    public Boot getBoot() {
        return boot;
    }

    /**
     *
     * @param boot
     *            The boot
     */
    @JsonProperty("boot")
    public void setBoot(final Boot boot) {
        this.boot = boot;
    }

    /**
     *
     * @return The bios
     */
    @JsonProperty("bios")
    public Bios getBios() {
        return bios;
    }

    /**
     *
     * @param bios
     *            The bios
     */
    @JsonProperty("bios")
    public void setBios(final Bios bios) {
        this.bios = bios;
    }

    /**
     *
     * @return The localStorage
     */
    @JsonProperty("localStorage")
    public LocalStorage getLocalStorage() {
        return localStorage;
    }

    /**
     *
     * @param localStorage
     *            The localStorage
     */
    @JsonProperty("localStorage")
    public void setLocalStorage(final LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    /**
     *
     * @return The sanStorage
     */
    @JsonProperty("sanStorage")
    public SanStorage getSanStorage() {
        return sanStorage;
    }

    /**
     *
     * @param sanStorage
     *            The sanStorage
     */
    @JsonProperty("sanStorage")
    public void setSanStorage(final SanStorage sanStorage) {
        this.sanStorage = sanStorage;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(serialNumber)
                .append(uuid)
                .append(serverHardwareUri)
                .append(serverHardwareTypeUri)
                .append(enclosureGroupUri)
                .append(enclosureUri)
                .append(enclosureBay)
                .append(affinity)
                .append(associatedServer)
                .append(hideUnusedFlexNics)
                .append(firmware)
                .append(macType)
                .append(wwnType)
                .append(serialNumberType)
                .append(inProgress)
                .append(taskUri)
                .append(connections)
                .append(bootMode)
                .append(boot)
                .append(bios)
                .append(localStorage)
                .append(sanStorage)
                .append(serverProfileTemplateUri)
                .append(templateCompliance)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ServerProfile) == false) {
            return false;
        }
        final ServerProfile rhs = ((ServerProfile) other);
        return new EqualsBuilder()
                .append(serialNumber, rhs.serialNumber)
                .append(uuid, rhs.uuid)
                .append(serverHardwareUri, rhs.serverHardwareUri)
                .append(serverHardwareTypeUri, rhs.serverHardwareTypeUri)
                .append(enclosureGroupUri, rhs.enclosureGroupUri)
                .append(enclosureUri, rhs.enclosureUri)
                .append(enclosureBay, rhs.enclosureBay)
                .append(affinity, rhs.affinity)
                .append(associatedServer, rhs.associatedServer)
                .append(hideUnusedFlexNics, rhs.hideUnusedFlexNics)
                .append(firmware, rhs.firmware)
                .append(macType, rhs.macType)
                .append(wwnType, rhs.wwnType)
                .append(serialNumberType, rhs.serialNumberType)
                .append(inProgress, rhs.inProgress)
                .append(taskUri, rhs.taskUri)
                .append(connections, rhs.connections)
                .append(bootMode, rhs.bootMode)
                .append(boot, rhs.boot)
                .append(bios, rhs.bios)
                .append(localStorage, rhs.localStorage)
                .append(sanStorage, rhs.sanStorage)
                .append(serverProfileTemplateUri, rhs.serverProfileTemplateUri)
                .append(templateCompliance, rhs.templateCompliance)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum ProfileAffinity {

        Bay("Bay"),
        BayAndServer("BayAndServer");

        private final String value;
        private static Map<String, ServerProfile.ProfileAffinity> constants = new HashMap<String, ServerProfile.ProfileAffinity>();

        static {
            for (final ServerProfile.ProfileAffinity c : values()) {
                constants.put(c.value, c);
            }
        }

        private ProfileAffinity(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static ServerProfile.ProfileAffinity fromValue(final String value) {
            final ServerProfile.ProfileAffinity constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum AssignmentType {

        Physical("Physical"),
        UserDefined("UserDefined"),
        Virtual("Virtual");

        private final String value;
        private static Map<String, ServerProfile.AssignmentType> constants = new HashMap<String, ServerProfile.AssignmentType>();

        static {
            for (final ServerProfile.AssignmentType c : values()) {
                constants.put(c.value, c);
            }
        }

        private AssignmentType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static ServerProfile.AssignmentType fromValue(final String value) {
            final ServerProfile.AssignmentType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }


    @Generated("org.jsonschema2pojo")
    public static enum TemplateCompliance {

        Compliant("Compliant"),
        NonCompliant("NonCompliant"),
        Unknown("Unknown");

        private final String value;
        private static Map<String, ServerProfile.TemplateCompliance> constants = new HashMap<String, ServerProfile.TemplateCompliance>();

        static {
            for (final ServerProfile.TemplateCompliance c : values()) {
                constants.put(c.value, c);
            }
        }

        private TemplateCompliance(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static ServerProfile.TemplateCompliance fromValue(final String value) {
            final ServerProfile.TemplateCompliance constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

}
