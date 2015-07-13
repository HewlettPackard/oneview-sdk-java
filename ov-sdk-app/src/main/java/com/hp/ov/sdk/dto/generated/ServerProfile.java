/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hp.ov.sdk.dto.ProfileConnectionV3;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "uri", "name", "description", "serialNumber", "uuid",
        "serverHardwareUri", "serverHardwareTypeUri", "enclosureGroupUri",
        "enclosureUri", "enclosureBay", "affinity", "associatedServer",
        "hideUnusedFlexNics", "firmware", "macType", "wwnType",
        "serialNumberType", "category", "created", "modified", "status",
        "state", "inProgress", "taskUri", "connections", "bootMode", "boot",
        "bios", "localStorage", "sanStorage", "eTag", "type"
})
public class ServerProfile implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @JsonProperty("uri")
    private String uri;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
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
    @JsonProperty("category")
    private String category;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("status")
    private String status;
    @JsonProperty("state")
    private String state;
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
    @JsonProperty("eTag")
    private String eTag;
    @JsonProperty("type")
    private String type;

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri()
    {
        return uri;
    }

    /**
     * 
     * @param uri
     *        The uri
     */
    @JsonProperty("uri")
    public void setUri(String uri)
    {
        this.uri = uri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * 
     * (Required)
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription()
    {
        return description;
    }

    /**
     * 
     * @param description
     *        The description
     */
    @JsonProperty("description")
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * 
     * @return The serialNumber
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber()
    {
        return serialNumber;
    }

    /**
     * 
     * @param serialNumber
     *        The serialNumber
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    /**
     * 
     * @return The uuid
     */
    @JsonProperty("uuid")
    public String getUuid()
    {
        return uuid;
    }

    /**
     * 
     * @param uuid
     *        The uuid
     */
    @JsonProperty("uuid")
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * 
     * @return The serverHardwareUri
     */
    @JsonProperty("serverHardwareUri")
    public String getServerHardwareUri()
    {
        return serverHardwareUri;
    }

    /**
     * 
     * @param serverHardwareUri
     *        The serverHardwareUri
     */
    @JsonProperty("serverHardwareUri")
    public void setServerHardwareUri(String serverHardwareUri)
    {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     * 
     * @return The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public String getServerHardwareTypeUri()
    {
        return serverHardwareTypeUri;
    }

    /**
     * 
     * @param serverHardwareTypeUri
     *        The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public void setServerHardwareTypeUri(String serverHardwareTypeUri)
    {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     * 
     * @return The enclosureGroupUri
     */
    @JsonProperty("enclosureGroupUri")
    public String getEnclosureGroupUri()
    {
        return enclosureGroupUri;
    }

    /**
     * 
     * @param enclosureGroupUri
     *        The enclosureGroupUri
     */
    @JsonProperty("enclosureGroupUri")
    public void setEnclosureGroupUri(String enclosureGroupUri)
    {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     * 
     * @return The enclosureUri
     */
    @JsonProperty("enclosureUri")
    public String getEnclosureUri()
    {
        return enclosureUri;
    }

    /**
     * 
     * @param enclosureUri
     *        The enclosureUri
     */
    @JsonProperty("enclosureUri")
    public void setEnclosureUri(String enclosureUri)
    {
        this.enclosureUri = enclosureUri;
    }

    /**
     * 
     * @return The enclosureBay
     */
    @JsonProperty("enclosureBay")
    public Integer getEnclosureBay()
    {
        return enclosureBay;
    }

    /**
     * 
     * @param enclosureBay
     *        The enclosureBay
     */
    @JsonProperty("enclosureBay")
    public void setEnclosureBay(Integer enclosureBay)
    {
        this.enclosureBay = enclosureBay;
    }

    /**
     * 
     * @return The affinity
     */
    @JsonProperty("affinity")
    public ServerProfile.ProfileAffinity getAffinity()
    {
        return affinity;
    }

    /**
     * 
     * @param affinity
     *        The affinity
     */
    @JsonProperty("affinity")
    public void setAffinity(ServerProfile.ProfileAffinity affinity)
    {
        this.affinity = affinity;
    }

    /**
     * 
     * @return The associatedServer
     */
    @JsonProperty("associatedServer")
    public String getAssociatedServer()
    {
        return associatedServer;
    }

    /**
     * 
     * @param associatedServer
     *        The associatedServer
     */
    @JsonProperty("associatedServer")
    public void setAssociatedServer(String associatedServer)
    {
        this.associatedServer = associatedServer;
    }

    /**
     * 
     * @return The hideUnusedFlexNics
     */
    @JsonProperty("hideUnusedFlexNics")
    public Boolean getHideUnusedFlexNics()
    {
        return hideUnusedFlexNics;
    }

    /**
     * 
     * @param hideUnusedFlexNics
     *        The hideUnusedFlexNics
     */
    @JsonProperty("hideUnusedFlexNics")
    public void setHideUnusedFlexNics(Boolean hideUnusedFlexNics)
    {
        this.hideUnusedFlexNics = hideUnusedFlexNics;
    }

    /**
     * 
     * @return The firmware
     */
    @JsonProperty("firmware")
    public Firmware getFirmware()
    {
        return firmware;
    }

    /**
     * 
     * @param firmware
     *        The firmware
     */
    @JsonProperty("firmware")
    public void setFirmware(Firmware firmware)
    {
        this.firmware = firmware;
    }

    /**
     * 
     * @return The macType
     */
    @JsonProperty("macType")
    public ServerProfile.AssignmentType getMacType()
    {
        return macType;
    }

    /**
     * 
     * @param macType
     *        The macType
     */
    @JsonProperty("macType")
    public void setMacType(ServerProfile.AssignmentType macType)
    {
        this.macType = macType;
    }

    /**
     * 
     * @return The wwnType
     */
    @JsonProperty("wwnType")
    public AssignmentType getWwnType()
    {
        return wwnType;
    }

    /**
     * 
     * @param wwnType
     *        The wwnType
     */
    @JsonProperty("wwnType")
    public void setWwnType(AssignmentType wwnType)
    {
        this.wwnType = wwnType;
    }

    /**
     * 
     * @return The serialNumberType
     */
    @JsonProperty("serialNumberType")
    public AssignmentType getSerialNumberType()
    {
        return serialNumberType;
    }

    /**
     * 
     * @param serialNumberType
     *        The serialNumberType
     */
    @JsonProperty("serialNumberType")
    public void setSerialNumberType(AssignmentType serialNumberType)
    {
        this.serialNumberType = serialNumberType;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory()
    {
        return category;
    }

    /**
     * 
     * @param category
     *        The category
     */
    @JsonProperty("category")
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated()
    {
        return created;
    }

    /**
     * 
     * @param created
     *        The created
     */
    @JsonProperty("created")
    public void setCreated(String created)
    {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified()
    {
        return modified;
    }

    /**
     * 
     * @param modified
     *        The modified
     */
    @JsonProperty("modified")
    public void setModified(String modified)
    {
        this.modified = modified;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus()
    {
        return status;
    }

    /**
     * 
     * @param status
     *        The status
     */
    @JsonProperty("status")
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    /**
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(String state)
    {
        this.state = state;
    }

    /**
     * 
     * @return The inProgress
     */
    @JsonProperty("inProgress")
    public Boolean getInProgress()
    {
        return inProgress;
    }

    /**
     * 
     * @param inProgress
     *        The inProgress
     */
    @JsonProperty("inProgress")
    public void setInProgress(Boolean inProgress)
    {
        this.inProgress = inProgress;
    }

    /**
     * 
     * @return The taskUri
     */
    @JsonProperty("taskUri")
    public String getTaskUri()
    {
        return taskUri;
    }

    /**
     * 
     * @param taskUri
     *        The taskUri
     */
    @JsonProperty("taskUri")
    public void setTaskUri(String taskUri)
    {
        this.taskUri = taskUri;
    }

    /**
     * 
     * @return The connections
     */
    @JsonProperty("connections")
    public List<ProfileConnectionV3> getConnections()
    {
        return connections;
    }

    /**
     * 
     * @param connections
     *        The connections
     */
    @JsonProperty("connections")
    public void setConnections(List<ProfileConnectionV3> connections)
    {
        this.connections = connections;
    }

    /**
     * 
     * @return The bootMode
     */
    @JsonProperty("bootMode")
    public BootMode getBootMode()
    {
        return bootMode;
    }

    /**
     * 
     * @param bootMode
     *        The bootMode
     */
    @JsonProperty("bootMode")
    public void setBootMode(BootMode bootMode)
    {
        this.bootMode = bootMode;
    }

    /**
     * 
     * @return The boot
     */
    @JsonProperty("boot")
    public Boot getBoot()
    {
        return boot;
    }

    /**
     * 
     * @param boot
     *        The boot
     */
    @JsonProperty("boot")
    public void setBoot(Boot boot)
    {
        this.boot = boot;
    }

    /**
     * 
     * @return The bios
     */
    @JsonProperty("bios")
    public Bios getBios()
    {
        return bios;
    }

    /**
     * 
     * @param bios
     *        The bios
     */
    @JsonProperty("bios")
    public void setBios(Bios bios)
    {
        this.bios = bios;
    }

    /**
     * 
     * @return The localStorage
     */
    @JsonProperty("localStorage")
    public LocalStorage getLocalStorage()
    {
        return localStorage;
    }

    /**
     * 
     * @param localStorage
     *        The localStorage
     */
    @JsonProperty("localStorage")
    public void setLocalStorage(LocalStorage localStorage)
    {
        this.localStorage = localStorage;
    }

    /**
     * 
     * @return The sanStorage
     */
    @JsonProperty("sanStorage")
    public SanStorage getSanStorage()
    {
        return sanStorage;
    }

    /**
     * 
     * @param sanStorage
     *        The sanStorage
     */
    @JsonProperty("sanStorage")
    public void setSanStorage(SanStorage sanStorage)
    {
        this.sanStorage = sanStorage;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag()
    {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *        The eTag
     */
    @JsonProperty("eTag")
    public void setETag(String eTag)
    {
        this.eTag = eTag;
    }

    /**
     * 
     * @return The type
     */
    public String getType()
    {
        return type;
    }

    /**
     * 
     * @param type
     *        The type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(type).append(uri).append(name)
                .append(description).append(serialNumber).append(uuid)
                .append(serverHardwareUri).append(serverHardwareTypeUri)
                .append(enclosureGroupUri).append(enclosureUri)
                .append(enclosureBay).append(affinity).append(associatedServer)
                .append(hideUnusedFlexNics).append(firmware).append(macType)
                .append(wwnType).append(serialNumberType).append(category)
                .append(created).append(modified).append(status).append(state)
                .append(inProgress).append(taskUri).append(connections)
                .append(bootMode).append(boot).append(bios)
                .append(localStorage).append(sanStorage).append(eTag)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof ServerProfile) == false)
        {
            return false;
        }
        ServerProfile rhs = ((ServerProfile) other);
        return new EqualsBuilder().append(type, rhs.type).append(uri, rhs.uri)
                .append(name, rhs.name).append(description, rhs.description)
                .append(serialNumber, rhs.serialNumber).append(uuid, rhs.uuid)
                .append(serverHardwareUri, rhs.serverHardwareUri)
                .append(serverHardwareTypeUri, rhs.serverHardwareTypeUri)
                .append(enclosureGroupUri, rhs.enclosureGroupUri)
                .append(enclosureUri, rhs.enclosureUri)
                .append(enclosureBay, rhs.enclosureBay)
                .append(affinity, rhs.affinity)
                .append(associatedServer, rhs.associatedServer)
                .append(hideUnusedFlexNics, rhs.hideUnusedFlexNics)
                .append(firmware, rhs.firmware).append(macType, rhs.macType)
                .append(wwnType, rhs.wwnType)
                .append(serialNumberType, rhs.serialNumberType)
                .append(category, rhs.category).append(created, rhs.created)
                .append(modified, rhs.modified).append(status, rhs.status)
                .append(state, rhs.state).append(inProgress, rhs.inProgress)
                .append(taskUri, rhs.taskUri)
                .append(connections, rhs.connections)
                .append(bootMode, rhs.bootMode).append(boot, rhs.boot)
                .append(bios, rhs.bios).append(localStorage, rhs.localStorage)
                .append(sanStorage, rhs.sanStorage).append(eTag, rhs.eTag)
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum ProfileAffinity
    {

        Bay ("Bay"),
        BayAndServer ("BayAndServer");
        private final String value;
        private static Map<String, ServerProfile.ProfileAffinity> constants = new HashMap<String, ServerProfile.ProfileAffinity>();

        static
        {
            for (ServerProfile.ProfileAffinity c : values())
            {
                constants.put(c.value, c);
            }
        }

        private ProfileAffinity(String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static ServerProfile.ProfileAffinity fromValue(String value)
        {
            ServerProfile.ProfileAffinity constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum AssignmentType
    {

        Physical ("Physical"),
        UserDefined ("UserDefined"),
        Virtual ("Virtual");
        private final String value;
        private static Map<String, ServerProfile.AssignmentType> constants = new HashMap<String, ServerProfile.AssignmentType>();

        static
        {
            for (ServerProfile.AssignmentType c : values())
            {
                constants.put(c.value, c);
            }
        }

        private AssignmentType(String value)
        {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString()
        {
            return this.value;
        }

        @JsonCreator
        public static ServerProfile.AssignmentType fromValue(String value)
        {
            ServerProfile.AssignmentType constant = constants.get(value);
            if (constant == null)
            {
                throw new IllegalArgumentException(value);
            }
            else
            {
                return constant;
            }
        }

    }

}
