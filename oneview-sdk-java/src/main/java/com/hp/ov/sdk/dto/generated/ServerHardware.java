/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * A server hardware resource.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "name", "state", "stateReason", "assetTag", "category",
        "created", "description", "eTag", "formFactor", "licensingIntent",
        "locationUri", "memoryMb", "model", "modified", "mpDnsName",
        "mpFirmwareVersion", "mpIpAddress", "mpModel", "partNumber", "portMap",
        "position", "powerLock", "powerState", "processorCoreCount",
        "processorCount", "processorSpeedMhz", "processorType", "refreshState",
        "romVersion", "serialNumber", "serverGroupUri",
        "serverHardwareTypeUri", "serverProfileUri", "shortModel", "signature",
        "status", "uri", "uuid", "virtualSerialNumber", "virtualUuid", "type"
})
public class ServerHardware implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * The name of the server.
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * The current resource state of the server hardware. Allowable values are:
     * Unknown (not initialized), Adding (server being added), NoProfileApplied
     * (server successfully added), Monitored (server being monitored),
     * Unmanaged (discovered a supported server), Removing (server being
     * removed), RemoveFailed (unsuccessful server removal), Removed (server
     * successfully removed), ApplyingProfile (profile being applied to server),
     * ProfileApplied (profile successfully applied), RemovingProfile (profile
     * being removed), ProfileError (unsuccessful profile apply or removal),
     * Unsupported (server model or version not currently supported by the
     * appliance), and UpdatingFirmware (server firmware update in progress).
     * 
     */
    @JsonProperty("state")
    private String state;
    /**
     * The reason for the current resource state of the server hardware. This
     * only applies if the state is 'Unmanaged', otherwise it is set to
     * 'NotApplicable'. Allowable values are: Unsupported (server model or
     * version not currently supported by the appliance), UpdatingFirmware
     * (server firmware update in progress), NotApplicable (when
     * PhysicalServerState is anything besides 'Unmanaged'), NotOwner (no claim
     * on server), Inventory (server added by PDU), Unconfigured (discovery data
     * incomplete or iLO configuration failure), UnsupportedFirmware (iLO
     * firmware version below minimum support level), Interrupted (when
     * PhysicalServerState is a result of an operation that was terminated
     * before completing), and CommunicationError (appliance cannot communicate
     * with iLO or OA).
     * 
     */
    @JsonProperty("stateReason")
    private String stateReason;
    /**
     * The current value of the asset tag for this server hardware. This value
     * can be set in the server hardware's BIOS interface.
     * 
     */
    @JsonProperty("assetTag")
    private String assetTag;
    @JsonProperty("category")
    private String category;
    @JsonProperty("created")
    private String created;
    @JsonProperty("description")
    private String description;
    @JsonProperty("eTag")
    private String eTag;
    /**
     * The physical dimensions of this server. For a blade server this is either
     * HalfHeight or FullHeight. For a rack server this is expressed in U
     * height, e.g. 4U.
     * 
     */
    @JsonProperty("formFactor")
    private String formFactor;
    /**
     * Product license assigned to the server hardware.
     * 
     */
    @JsonProperty("licensingIntent")
    private ServerHardware.LicensingIntent licensingIntent;
    /**
     * For blade servers, the enclosure in which this blade server resides. This
     * URI can be used to retrieve information about the enclosure. This value
     * is not set for rack mount servers.
     * 
     */
    @JsonProperty("locationUri")
    private String locationUri;
    /**
     * Amount of memory installed on this server hardware (in megabytes).
     * 
     */
    @JsonProperty("memoryMb")
    private Integer memoryMb;
    /**
     * The full server hardware model string.
     * 
     */
    @JsonProperty("model")
    private String model;
    @JsonProperty("modified")
    private String modified;
    /**
     * The DNS name of the iLO/Management Processor that resides on this server
     * hardware.
     * 
     */
    @JsonProperty("mpDnsName")
    private String mpDnsName;
    /**
     * The version of the firmware installed on the iLO.
     * 
     */
    @JsonProperty("mpFirmwareVersion")
    private String mpFirmwareVersion;
    /**
     * IP Address of the management processor (iLO) resident on this server
     * hardware.
     * 
     */
    @JsonProperty("mpIpAddress")
    private String mpIpAddress;
    /**
     * The model type of the iLO, such as iLO4.
     * 
     */
    @JsonProperty("mpModel")
    private String mpModel;
    /**
     * The part number for this server hardware.
     * 
     */
    @JsonProperty("partNumber")
    private String partNumber;
    /**
     * A list of adapters/slots, their ports and attributes. This information is
     * available for blade servers but not rack servers.
     * 
     */
    @JsonProperty("portMap")
    private PortMap portMap;
    /**
     * For blade servers, the number of the physical enclosure bay in which the
     * server hardware resides. For rack mount servers, this value is null.
     * 
     */
    @JsonProperty("position")
    private Integer position;
    /**
     * Indicates if an operation is being performed on this server hardware
     * (such as a profile assignment) that prevents its power state from being
     * manipulated via the server hardware API.
     * 
     */
    @JsonProperty("powerLock")
    private Boolean powerLock = false;
    /**
     * Current power state of the server hardware. Values are Unknown, On, Off,
     * PoweringOn, PoweringOff or Resetting. (Required)
     * 
     */
    @JsonProperty("powerState")
    private ServerHardware.PowerState powerState;
    /**
     * Number of cores available per processor.
     * 
     */
    @JsonProperty("processorCoreCount")
    private Integer processorCoreCount;
    /**
     * Number of processors installed on this server hardware.
     * 
     */
    @JsonProperty("processorCount")
    private Integer processorCount;
    /**
     * Speed of the CPUs in megahertz.
     * 
     */
    @JsonProperty("processorSpeedMhz")
    private Integer processorSpeedMhz;
    /**
     * Type of CPU installed on this server hardware.
     * 
     */
    @JsonProperty("processorType")
    private String processorType;
    /**
     * Indicates if the resource is currently refreshing.
     * 
     */
    @JsonProperty("refreshState")
    private ServerHardware.RefreshState refreshState;
    /**
     * The version of the server hardware firmware (ROM). After updating the ROM
     * (BIOS) firmware for a server, the server hardware page and the REST API
     * may report an inaccurate ROM version until the server is next powered on
     * and allowed to complete the power-on self-test (POST).
     * 
     */
    @JsonProperty("romVersion")
    private String romVersion;
    /**
     * Serial number of the server hardware.
     * 
     */
    @JsonProperty("serialNumber")
    private String serialNumber;
    /**
     * For blade servers, this is the URI of the containing enclosure's
     * enclosure group. This URI can be used to retrieve information about the
     * enclosure group or to identify all the servers in the same group. This
     * value is not set for rack mount servers.
     * 
     */
    @JsonProperty("serverGroupUri")
    private String serverGroupUri;
    /**
     * URI of the server hardware type associated with the server hardware. This
     * URI can be used to retrieve information about the server hardware type.
     * 
     */
    @JsonProperty("serverHardwareTypeUri")
    private String serverHardwareTypeUri;
    /**
     * URI of a server profile assigned to this server hardware, if one is
     * assigned. If not assigned this value is null.
     * 
     */
    @JsonProperty("serverProfileUri")
    private String serverProfileUri;
    /**
     * Short version of the server hardware model string, typically something
     * like BL460 Gen8.
     * 
     */
    @JsonProperty("shortModel")
    private String shortModel;
    /**
     * Data representing the current configuration or 'signature' of the server.
     * 
     */
    @JsonProperty("signature")
    private Signature signature;
    @JsonProperty("status")
    private String status;
    @JsonProperty("uri")
    private String uri;
    /**
     * Universally Unique ID (UUID) of the server hardware. (Required)
     * 
     */
    @JsonProperty("uuid")
    private String uuid;
    /**
     * Virtual serial number associated with this server hardware (if specified
     * in the profile assigned to this server).
     * 
     */
    @JsonProperty("virtualSerialNumber")
    private String virtualSerialNumber;
    /**
     * Virtual UUID associated with this server hardware (if specified in the
     * profile assigned to this server).
     * 
     */
    @JsonProperty("virtualUuid")
    private String virtualUuid;
    @JsonProperty("type")
    private String type;

    /**
     * The name of the server.
     * 
     * @return The name
     */
    @JsonProperty("name")
    public String getName()
    {
        return name;
    }

    /**
     * The name of the server.
     * 
     * @param name
     *        The name
     */
    @JsonProperty("name")
    public void setName(final String name)
    {
        this.name = name;
    }

    /**
     * The current resource state of the server hardware. Allowable values are:
     * Unknown (not initialized), Adding (server being added), NoProfileApplied
     * (server successfully added), Monitored (server being monitored),
     * Unmanaged (discovered a supported server), Removing (server being
     * removed), RemoveFailed (unsuccessful server removal), Removed (server
     * successfully removed), ApplyingProfile (profile being applied to server),
     * ProfileApplied (profile successfully applied), RemovingProfile (profile
     * being removed), ProfileError (unsuccessful profile apply or removal),
     * Unsupported (server model or version not currently supported by the
     * appliance), and UpdatingFirmware (server firmware update in progress).
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState()
    {
        return state;
    }

    /**
     * The current resource state of the server hardware. Allowable values are:
     * Unknown (not initialized), Adding (server being added), NoProfileApplied
     * (server successfully added), Monitored (server being monitored),
     * Unmanaged (discovered a supported server), Removing (server being
     * removed), RemoveFailed (unsuccessful server removal), Removed (server
     * successfully removed), ApplyingProfile (profile being applied to server),
     * ProfileApplied (profile successfully applied), RemovingProfile (profile
     * being removed), ProfileError (unsuccessful profile apply or removal),
     * Unsupported (server model or version not currently supported by the
     * appliance), and UpdatingFirmware (server firmware update in progress).
     * 
     * @param state
     *        The state
     */
    @JsonProperty("state")
    public void setState(final String state)
    {
        this.state = state;
    }

    /**
     * The reason for the current resource state of the server hardware. This
     * only applies if the state is 'Unmanaged', otherwise it is set to
     * 'NotApplicable'. Allowable values are: Unsupported (server model or
     * version not currently supported by the appliance), UpdatingFirmware
     * (server firmware update in progress), NotApplicable (when
     * PhysicalServerState is anything besides 'Unmanaged'), NotOwner (no claim
     * on server), Inventory (server added by PDU), Unconfigured (discovery data
     * incomplete or iLO configuration failure), UnsupportedFirmware (iLO
     * firmware version below minimum support level), Interrupted (when
     * PhysicalServerState is a result of an operation that was terminated
     * before completing), and CommunicationError (appliance cannot communicate
     * with iLO or OA).
     * 
     * @return The stateReason
     */
    @JsonProperty("stateReason")
    public String getStateReason()
    {
        return stateReason;
    }

    /**
     * The reason for the current resource state of the server hardware. This
     * only applies if the state is 'Unmanaged', otherwise it is set to
     * 'NotApplicable'. Allowable values are: Unsupported (server model or
     * version not currently supported by the appliance), UpdatingFirmware
     * (server firmware update in progress), NotApplicable (when
     * PhysicalServerState is anything besides 'Unmanaged'), NotOwner (no claim
     * on server), Inventory (server added by PDU), Unconfigured (discovery data
     * incomplete or iLO configuration failure), UnsupportedFirmware (iLO
     * firmware version below minimum support level), Interrupted (when
     * PhysicalServerState is a result of an operation that was terminated
     * before completing), and CommunicationError (appliance cannot communicate
     * with iLO or OA).
     * 
     * @param stateReason
     *        The stateReason
     */
    @JsonProperty("stateReason")
    public void setStateReason(final String stateReason)
    {
        this.stateReason = stateReason;
    }

    /**
     * The current value of the asset tag for this server hardware. This value
     * can be set in the server hardware's BIOS interface.
     * 
     * @return The assetTag
     */
    @JsonProperty("assetTag")
    public String getAssetTag()
    {
        return assetTag;
    }

    /**
     * The current value of the asset tag for this server hardware. This value
     * can be set in the server hardware's BIOS interface.
     * 
     * @param assetTag
     *        The assetTag
     */
    @JsonProperty("assetTag")
    public void setAssetTag(final String assetTag)
    {
        this.assetTag = assetTag;
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
    public void setCategory(final String category)
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
    public void setCreated(final String created)
    {
        this.created = created;
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
    public void setDescription(final String description)
    {
        this.description = description;
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
    public void setETag(final String eTag)
    {
        this.eTag = eTag;
    }

    /**
     * The physical dimensions of this server. For a blade server this is either
     * HalfHeight or FullHeight. For a rack server this is expressed in U
     * height, e.g. 4U.
     * 
     * @return The formFactor
     */
    @JsonProperty("formFactor")
    public String getFormFactor()
    {
        return formFactor;
    }

    /**
     * The physical dimensions of this server. For a blade server this is either
     * HalfHeight or FullHeight. For a rack server this is expressed in U
     * height, e.g. 4U.
     * 
     * @param formFactor
     *        The formFactor
     */
    @JsonProperty("formFactor")
    public void setFormFactor(final String formFactor)
    {
        this.formFactor = formFactor;
    }

    /**
     * Product license assigned to the server hardware.
     * 
     * @return The licensingIntent
     */
    @JsonProperty("licensingIntent")
    public ServerHardware.LicensingIntent getLicensingIntent()
    {
        return licensingIntent;
    }

    /**
     * Product license assigned to the server hardware.
     * 
     * @param licensingIntent
     *        The licensingIntent
     */
    @JsonProperty("licensingIntent")
    public void setLicensingIntent(
            final ServerHardware.LicensingIntent licensingIntent)
    {
        this.licensingIntent = licensingIntent;
    }

    /**
     * For blade servers, the enclosure in which this blade server resides. This
     * URI can be used to retrieve information about the enclosure. This value
     * is not set for rack mount servers.
     * 
     * @return The locationUri
     */
    @JsonProperty("locationUri")
    public String getLocationUri()
    {
        return locationUri;
    }

    /**
     * For blade servers, the enclosure in which this blade server resides. This
     * URI can be used to retrieve information about the enclosure. This value
     * is not set for rack mount servers.
     * 
     * @param locationUri
     *        The locationUri
     */
    @JsonProperty("locationUri")
    public void setLocationUri(final String locationUri)
    {
        this.locationUri = locationUri;
    }

    /**
     * Amount of memory installed on this server hardware (in megabytes).
     * 
     * @return The memoryMb
     */
    @JsonProperty("memoryMb")
    public Integer getMemoryMb()
    {
        return memoryMb;
    }

    /**
     * Amount of memory installed on this server hardware (in megabytes).
     * 
     * @param memoryMb
     *        The memoryMb
     */
    @JsonProperty("memoryMb")
    public void setMemoryMb(final Integer memoryMb)
    {
        this.memoryMb = memoryMb;
    }

    /**
     * The full server hardware model string.
     * 
     * @return The model
     */
    @JsonProperty("model")
    public String getModel()
    {
        return model;
    }

    /**
     * The full server hardware model string.
     * 
     * @param model
     *        The model
     */
    @JsonProperty("model")
    public void setModel(final String model)
    {
        this.model = model;
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
    public void setModified(final String modified)
    {
        this.modified = modified;
    }

    /**
     * The DNS name of the iLO/Management Processor that resides on this server
     * hardware.
     * 
     * @return The mpDnsName
     */
    @JsonProperty("mpDnsName")
    public String getMpDnsName()
    {
        return mpDnsName;
    }

    /**
     * The DNS name of the iLO/Management Processor that resides on this server
     * hardware.
     * 
     * @param mpDnsName
     *        The mpDnsName
     */
    @JsonProperty("mpDnsName")
    public void setMpDnsName(final String mpDnsName)
    {
        this.mpDnsName = mpDnsName;
    }

    /**
     * The version of the firmware installed on the iLO.
     * 
     * @return The mpFirmwareVersion
     */
    @JsonProperty("mpFirmwareVersion")
    public String getMpFirmwareVersion()
    {
        return mpFirmwareVersion;
    }

    /**
     * The version of the firmware installed on the iLO.
     * 
     * @param mpFirmwareVersion
     *        The mpFirmwareVersion
     */
    @JsonProperty("mpFirmwareVersion")
    public void setMpFirmwareVersion(final String mpFirmwareVersion)
    {
        this.mpFirmwareVersion = mpFirmwareVersion;
    }

    /**
     * IP Address of the management processor (iLO) resident on this server
     * hardware.
     * 
     * @return The mpIpAddress
     */
    @JsonProperty("mpIpAddress")
    public String getMpIpAddress()
    {
        return mpIpAddress;
    }

    /**
     * IP Address of the management processor (iLO) resident on this server
     * hardware.
     * 
     * @param mpIpAddress
     *        The mpIpAddress
     */
    @JsonProperty("mpIpAddress")
    public void setMpIpAddress(final String mpIpAddress)
    {
        this.mpIpAddress = mpIpAddress;
    }

    /**
     * The model type of the iLO, such as iLO4.
     * 
     * @return The mpModel
     */
    @JsonProperty("mpModel")
    public String getMpModel()
    {
        return mpModel;
    }

    /**
     * The model type of the iLO, such as iLO4.
     * 
     * @param mpModel
     *        The mpModel
     */
    @JsonProperty("mpModel")
    public void setMpModel(final String mpModel)
    {
        this.mpModel = mpModel;
    }

    /**
     * The part number for this server hardware.
     * 
     * @return The partNumber
     */
    @JsonProperty("partNumber")
    public String getPartNumber()
    {
        return partNumber;
    }

    /**
     * The part number for this server hardware.
     * 
     * @param partNumber
     *        The partNumber
     */
    @JsonProperty("partNumber")
    public void setPartNumber(final String partNumber)
    {
        this.partNumber = partNumber;
    }

    /**
     * A list of adapters/slots, their ports and attributes. This information is
     * available for blade servers but not rack servers.
     * 
     * @return The portMap
     */
    @JsonProperty("portMap")
    public PortMap getPortMap()
    {
        return portMap;
    }

    /**
     * A list of adapters/slots, their ports and attributes. This information is
     * available for blade servers but not rack servers.
     * 
     * @param portMap
     *        The portMap
     */
    @JsonProperty("portMap")
    public void setPortMap(final PortMap portMap)
    {
        this.portMap = portMap;
    }

    /**
     * For blade servers, the number of the physical enclosure bay in which the
     * server hardware resides. For rack mount servers, this value is null.
     * 
     * @return The position
     */
    @JsonProperty("position")
    public Integer getPosition()
    {
        return position;
    }

    /**
     * For blade servers, the number of the physical enclosure bay in which the
     * server hardware resides. For rack mount servers, this value is null.
     * 
     * @param position
     *        The position
     */
    @JsonProperty("position")
    public void setPosition(final Integer position)
    {
        this.position = position;
    }

    /**
     * Indicates if an operation is being performed on this server hardware
     * (such as a profile assignment) that prevents its power state from being
     * manipulated via the server hardware API.
     * 
     * @return The powerLock
     */
    @JsonProperty("powerLock")
    public Boolean getPowerLock()
    {
        return powerLock;
    }

    /**
     * Indicates if an operation is being performed on this server hardware
     * (such as a profile assignment) that prevents its power state from being
     * manipulated via the server hardware API.
     * 
     * @param powerLock
     *        The powerLock
     */
    @JsonProperty("powerLock")
    public void setPowerLock(final Boolean powerLock)
    {
        this.powerLock = powerLock;
    }

    /**
     * Current power state of the server hardware. Values are Unknown, On, Off,
     * PoweringOn, PoweringOff or Resetting. (Required)
     * 
     * @return The powerState
     */
    @JsonProperty("powerState")
    public ServerHardware.PowerState getPowerState()
    {
        return powerState;
    }

    /**
     * Current power state of the server hardware. Values are Unknown, On, Off,
     * PoweringOn, PoweringOff or Resetting. (Required)
     * 
     * @param powerState
     *        The powerState
     */
    @JsonProperty("powerState")
    public void setPowerState(final ServerHardware.PowerState powerState)
    {
        this.powerState = powerState;
    }

    /**
     * Number of cores available per processor.
     * 
     * @return The processorCoreCount
     */
    @JsonProperty("processorCoreCount")
    public Integer getProcessorCoreCount()
    {
        return processorCoreCount;
    }

    /**
     * Number of cores available per processor.
     * 
     * @param processorCoreCount
     *        The processorCoreCount
     */
    @JsonProperty("processorCoreCount")
    public void setProcessorCoreCount(final Integer processorCoreCount)
    {
        this.processorCoreCount = processorCoreCount;
    }

    /**
     * Number of processors installed on this server hardware.
     * 
     * @return The processorCount
     */
    @JsonProperty("processorCount")
    public Integer getProcessorCount()
    {
        return processorCount;
    }

    /**
     * Number of processors installed on this server hardware.
     * 
     * @param processorCount
     *        The processorCount
     */
    @JsonProperty("processorCount")
    public void setProcessorCount(final Integer processorCount)
    {
        this.processorCount = processorCount;
    }

    /**
     * Speed of the CPUs in megahertz.
     * 
     * @return The processorSpeedMhz
     */
    @JsonProperty("processorSpeedMhz")
    public Integer getProcessorSpeedMhz()
    {
        return processorSpeedMhz;
    }

    /**
     * Speed of the CPUs in megahertz.
     * 
     * @param processorSpeedMhz
     *        The processorSpeedMhz
     */
    @JsonProperty("processorSpeedMhz")
    public void setProcessorSpeedMhz(final Integer processorSpeedMhz)
    {
        this.processorSpeedMhz = processorSpeedMhz;
    }

    /**
     * Type of CPU installed on this server hardware.
     * 
     * @return The processorType
     */
    @JsonProperty("processorType")
    public String getProcessorType()
    {
        return processorType;
    }

    /**
     * Type of CPU installed on this server hardware.
     * 
     * @param processorType
     *        The processorType
     */
    @JsonProperty("processorType")
    public void setProcessorType(final String processorType)
    {
        this.processorType = processorType;
    }

    /**
     * Indicates if the resource is currently refreshing.
     * 
     * @return The refreshState
     */
    @JsonProperty("refreshState")
    public ServerHardware.RefreshState getRefreshState()
    {
        return refreshState;
    }

    /**
     * Indicates if the resource is currently refreshing.
     * 
     * @param refreshState
     *        The refreshState
     */
    @JsonProperty("refreshState")
    public void setRefreshState(final ServerHardware.RefreshState refreshState)
    {
        this.refreshState = refreshState;
    }

    /**
     * The version of the server hardware firmware (ROM). After updating the ROM
     * (BIOS) firmware for a server, the server hardware page and the REST API
     * may report an inaccurate ROM version until the server is next powered on
     * and allowed to complete the power-on self-test (POST).
     * 
     * @return The romVersion
     */
    @JsonProperty("romVersion")
    public String getRomVersion()
    {
        return romVersion;
    }

    /**
     * The version of the server hardware firmware (ROM). After updating the ROM
     * (BIOS) firmware for a server, the server hardware page and the REST API
     * may report an inaccurate ROM version until the server is next powered on
     * and allowed to complete the power-on self-test (POST).
     * 
     * @param romVersion
     *        The romVersion
     */
    @JsonProperty("romVersion")
    public void setRomVersion(final String romVersion)
    {
        this.romVersion = romVersion;
    }

    /**
     * Serial number of the server hardware.
     * 
     * @return The serialNumber
     */
    @JsonProperty("serialNumber")
    public String getSerialNumber()
    {
        return serialNumber;
    }

    /**
     * Serial number of the server hardware.
     * 
     * @param serialNumber
     *        The serialNumber
     */
    @JsonProperty("serialNumber")
    public void setSerialNumber(final String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    /**
     * For blade servers, this is the URI of the containing enclosure's
     * enclosure group. This URI can be used to retrieve information about the
     * enclosure group or to identify all the servers in the same group. This
     * value is not set for rack mount servers.
     * 
     * @return The serverGroupUri
     */
    @JsonProperty("serverGroupUri")
    public String getServerGroupUri()
    {
        return serverGroupUri;
    }

    /**
     * For blade servers, this is the URI of the containing enclosure's
     * enclosure group. This URI can be used to retrieve information about the
     * enclosure group or to identify all the servers in the same group. This
     * value is not set for rack mount servers.
     * 
     * @param serverGroupUri
     *        The serverGroupUri
     */
    @JsonProperty("serverGroupUri")
    public void setServerGroupUri(final String serverGroupUri)
    {
        this.serverGroupUri = serverGroupUri;
    }

    /**
     * URI of the server hardware type associated with the server hardware. This
     * URI can be used to retrieve information about the server hardware type.
     * 
     * @return The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public String getServerHardwareTypeUri()
    {
        return serverHardwareTypeUri;
    }

    /**
     * URI of the server hardware type associated with the server hardware. This
     * URI can be used to retrieve information about the server hardware type.
     * 
     * @param serverHardwareTypeUri
     *        The serverHardwareTypeUri
     */
    @JsonProperty("serverHardwareTypeUri")
    public void setServerHardwareTypeUri(final String serverHardwareTypeUri)
    {
        this.serverHardwareTypeUri = serverHardwareTypeUri;
    }

    /**
     * URI of a server profile assigned to this server hardware, if one is
     * assigned. If not assigned this value is null.
     * 
     * @return The serverProfileUri
     */
    @JsonProperty("serverProfileUri")
    public String getServerProfileUri()
    {
        return serverProfileUri;
    }

    /**
     * URI of a server profile assigned to this server hardware, if one is
     * assigned. If not assigned this value is null.
     * 
     * @param serverProfileUri
     *        The serverProfileUri
     */
    @JsonProperty("serverProfileUri")
    public void setServerProfileUri(final String serverProfileUri)
    {
        this.serverProfileUri = serverProfileUri;
    }

    /**
     * Short version of the server hardware model string, typically something
     * like BL460 Gen8.
     * 
     * @return The shortModel
     */
    @JsonProperty("shortModel")
    public String getShortModel()
    {
        return shortModel;
    }

    /**
     * Short version of the server hardware model string, typically something
     * like BL460 Gen8.
     * 
     * @param shortModel
     *        The shortModel
     */
    @JsonProperty("shortModel")
    public void setShortModel(final String shortModel)
    {
        this.shortModel = shortModel;
    }

    /**
     * Data representing the current configuration or 'signature' of the server.
     * 
     * @return The signature
     */
    @JsonProperty("signature")
    public Signature getSignature()
    {
        return signature;
    }

    /**
     * Data representing the current configuration or 'signature' of the server.
     * 
     * @param signature
     *        The signature
     */
    @JsonProperty("signature")
    public void setSignature(final Signature signature)
    {
        this.signature = signature;
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
    public void setStatus(final String status)
    {
        this.status = status;
    }

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
    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    /**
     * Universally Unique ID (UUID) of the server hardware. (Required)
     * 
     * @return The uuid
     */
    @JsonProperty("uuid")
    public String getUuid()
    {
        return uuid;
    }

    /**
     * Universally Unique ID (UUID) of the server hardware. (Required)
     * 
     * @param uuid
     *        The uuid
     */
    @JsonProperty("uuid")
    public void setUuid(final String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * Virtual serial number associated with this server hardware (if specified
     * in the profile assigned to this server).
     * 
     * @return The virtualSerialNumber
     */
    @JsonProperty("virtualSerialNumber")
    public String getVirtualSerialNumber()
    {
        return virtualSerialNumber;
    }

    /**
     * Virtual serial number associated with this server hardware (if specified
     * in the profile assigned to this server).
     * 
     * @param virtualSerialNumber
     *        The virtualSerialNumber
     */
    @JsonProperty("virtualSerialNumber")
    public void setVirtualSerialNumber(final String virtualSerialNumber)
    {
        this.virtualSerialNumber = virtualSerialNumber;
    }

    /**
     * Virtual UUID associated with this server hardware (if specified in the
     * profile assigned to this server).
     * 
     * @return The virtualUuid
     */
    @JsonProperty("virtualUuid")
    public String getVirtualUuid()
    {
        return virtualUuid;
    }

    /**
     * Virtual UUID associated with this server hardware (if specified in the
     * profile assigned to this server).
     * 
     * @param virtualUuid
     *        The virtualUuid
     */
    @JsonProperty("virtualUuid")
    public void setVirtualUuid(final String virtualUuid)
    {
        this.virtualUuid = virtualUuid;
    }

    /**
     * 
     * (Required)
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType()
    {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     * @param type
     *        The type
     */
    @JsonProperty("type")
    public void setType(final String type)
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
        return new HashCodeBuilder().append(name).append(state)
                .append(stateReason).append(assetTag).append(category)
                .append(created).append(description).append(eTag)
                .append(formFactor).append(licensingIntent).append(locationUri)
                .append(memoryMb).append(model).append(modified)
                .append(mpDnsName).append(mpFirmwareVersion)
                .append(mpIpAddress).append(mpModel).append(partNumber)
                .append(portMap).append(position).append(powerLock)
                .append(powerState).append(processorCoreCount)
                .append(processorCount).append(processorSpeedMhz)
                .append(processorType).append(refreshState).append(romVersion)
                .append(serialNumber).append(serverGroupUri)
                .append(serverHardwareTypeUri).append(serverProfileUri)
                .append(shortModel).append(signature).append(status)
                .append(uri).append(uuid).append(virtualSerialNumber)
                .append(virtualUuid).toHashCode();
    }

    @Override
    public boolean equals(final Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof ServerHardware) == false)
        {
            return false;
        }
        final ServerHardware rhs = ((ServerHardware) other);
        return new EqualsBuilder().append(name, rhs.name)
                .append(state, rhs.state).append(stateReason, rhs.stateReason)
                .append(assetTag, rhs.assetTag).append(category, rhs.category)
                .append(created, rhs.created)
                .append(description, rhs.description).append(eTag, rhs.eTag)
                .append(formFactor, rhs.formFactor)
                .append(licensingIntent, rhs.licensingIntent)
                .append(locationUri, rhs.locationUri)
                .append(memoryMb, rhs.memoryMb).append(model, rhs.model)
                .append(modified, rhs.modified)
                .append(mpDnsName, rhs.mpDnsName)
                .append(mpFirmwareVersion, rhs.mpFirmwareVersion)
                .append(mpIpAddress, rhs.mpIpAddress)
                .append(mpModel, rhs.mpModel)
                .append(partNumber, rhs.partNumber)
                .append(portMap, rhs.portMap).append(position, rhs.position)
                .append(powerLock, rhs.powerLock)
                .append(powerState, rhs.powerState)
                .append(processorCoreCount, rhs.processorCoreCount)
                .append(processorCount, rhs.processorCount)
                .append(processorSpeedMhz, rhs.processorSpeedMhz)
                .append(processorType, rhs.processorType)
                .append(refreshState, rhs.refreshState)
                .append(romVersion, rhs.romVersion)
                .append(serialNumber, rhs.serialNumber)
                .append(serverGroupUri, rhs.serverGroupUri)
                .append(serverHardwareTypeUri, rhs.serverHardwareTypeUri)
                .append(serverProfileUri, rhs.serverProfileUri)
                .append(shortModel, rhs.shortModel)
                .append(signature, rhs.signature).append(status, rhs.status)
                .append(uri, rhs.uri).append(uuid, rhs.uuid)
                .append(virtualSerialNumber, rhs.virtualSerialNumber)
                .append(virtualUuid, rhs.virtualUuid).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum LicensingIntent
    {

        OneView ("OneView"),
        OneViewNoiLO ("OneViewNoiLO"),
        OneViewStandard (
                "OneViewStandard"),
        None ("None");
        private final String value;
        private static Map<String, ServerHardware.LicensingIntent> constants = new HashMap<String, ServerHardware.LicensingIntent>();

        static
        {
            for (final ServerHardware.LicensingIntent c : values())
            {
                constants.put(c.value, c);
            }
        }

        private LicensingIntent(final String value)
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
        public static ServerHardware.LicensingIntent fromValue(final String value)
        {
            final ServerHardware.LicensingIntent constant = constants.get(value);
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
    public static enum PowerState
    {

        Unknown ("Unknown"),
        On ("On"),
        Off ("Off"),
        PoweringOn ("PoweringOn"),
        PoweringOff (
                "PoweringOff"),
        Resetting ("Resetting");
        private final String value;
        private static Map<String, ServerHardware.PowerState> constants = new HashMap<String, ServerHardware.PowerState>();

        static
        {
            for (final ServerHardware.PowerState c : values())
            {
                constants.put(c.value, c);
            }
        }

        private PowerState(final String value)
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
        public static ServerHardware.PowerState fromValue(final String value)
        {
            final ServerHardware.PowerState constant = constants.get(value);
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
    public static enum RefreshState
    {

        NotRefreshing ("NotRefreshing"),
        RefreshPending ("RefreshPending"),
        Refreshing (
                "Refreshing"),
        RefreshFailed ("RefreshFailed");
        private final String value;
        private static Map<String, ServerHardware.RefreshState> constants = new HashMap<String, ServerHardware.RefreshState>();

        static
        {
            for (final ServerHardware.RefreshState c : values())
            {
                constants.put(c.value, c);
            }
        }

        private RefreshState(final String value)
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
        public static ServerHardware.RefreshState fromValue(final String value)
        {
            final ServerHardware.RefreshState constant = constants.get(value);
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
