/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "uri", "uuid", "name", "serialNumber", "partNumber", "assetTag", "enclosureType", "enclosureGroupUri",
        "rackName", "deviceBayCount", "deviceBays", "interconnectBayCount", "interconnectBays", "powerSupplyBayCount",
        "powerSupplyBays", "fanBayCount", "fanBays", "oa", "oaBayCount", "activeOaPreferredIP", "standbyOaPreferredIP",
        "isFwManaged", "fwBaselineName", "fwBaselineUri", "vcmMode", "vcmUrl", "vcmDomainName", "vcmDomainId", "category",
        "description", "created", "modified", "eTag", "licensingIntent", "state", "stateReason", "refreshState", "status",
        "consistentWithGroup", "reconfigurationState", "type" })
public class Enclosures implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("name")
    private String name;
    @JsonProperty("serialNumber")
    private String serialNumber;
    @JsonProperty("partNumber")
    private String partNumber;
    @JsonProperty("assetTag")
    private String assetTag;
    @JsonProperty("enclosureType")
    private String enclosureType;
    @JsonProperty("enclosureGroupUri")
    private String enclosureGroupUri;
    @JsonProperty("rackName")
    private String rackName;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("deviceBayCount")
    private Integer deviceBayCount;
    @JsonProperty("deviceBays")
    private List<Object> deviceBays = new ArrayList<Object>();
    @JsonProperty("interconnectBayCount")
    private Integer interconnectBayCount;
    @JsonProperty("interconnectBays")
    private List<InterconnectBay> interconnectBays = new ArrayList<InterconnectBay>();
    @JsonProperty("powerSupplyBayCount")
    private Integer powerSupplyBayCount;
    @JsonProperty("powerSupplyBays")
    private List<PowerSupplyBay> powerSupplyBays = new ArrayList<PowerSupplyBay>();
    @JsonProperty("fanBayCount")
    private Integer fanBayCount;
    @JsonProperty("fanBays")
    private List<FanBay> fanBays = new ArrayList<FanBay>();
    @JsonProperty("oa")
    private List<Oa> oa = new ArrayList<Oa>();
    @JsonProperty("oaBayCount")
    private Integer oaBayCount;
    @JsonProperty("activeOaPreferredIP")
    private String activeOaPreferredIP;
    @JsonProperty("standbyOaPreferredIP")
    private String standbyOaPreferredIP;
    @JsonProperty("isFwManaged")
    private Boolean isFwManaged;
    @JsonProperty("fwBaselineName")
    private String fwBaselineName;
    @JsonProperty("fwBaselineUri")
    private String fwBaselineUri;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("vcmMode")
    private Boolean vcmMode;
    @JsonProperty("vcmUrl")
    private String vcmUrl;
    @JsonProperty("vcmDomainName")
    private String vcmDomainName;
    @JsonProperty("vcmDomainId")
    private String vcmDomainId;
    @JsonProperty("category")
    private String category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created")
    private String created;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("eTag")
    private String eTag;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("licensingIntent")
    private Enclosures.LicensingIntent licensingIntent;
    @JsonProperty("state")
    private String state;
    /**
     * Indicates the reason the resource in its current state
     * 
     */
    @JsonProperty("stateReason")
    private String stateReason;
    /**
     * Indicates if the resource is currently refreshing.
     * 
     */
    @JsonProperty("refreshState")
    private Enclosures.RefreshState refreshState;
    @JsonProperty("status")
    private String status;
    @JsonProperty("consistentWithGroup")
    private Boolean consistentWithGroup;
    @JsonProperty("type")
    private String type;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("reconfigurationState")
    private Enclosures.ReconfigurationState reconfigurationState;

    /**
     * 
     * @return The uri
     */
    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    /**
     * 
     * @param uri
     *            The uri
     */
    @JsonProperty("uri")
    public void setUri(final String uri) {
        this.uri = uri;
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
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            The name
     */
    @JsonProperty("name")
    public void setName(final String name) {
        this.name = name;
    }

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
     * @return The partNumber
     */
    @JsonProperty("partNumber")
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * 
     * @param partNumber
     *            The partNumber
     */
    @JsonProperty("partNumber")
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * 
     * @return The assetTag
     */
    @JsonProperty("assetTag")
    public String getAssetTag() {
        return assetTag;
    }

    /**
     * 
     * @param assetTag
     *            The assetTag
     */
    @JsonProperty("assetTag")
    public void setAssetTag(final String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     * 
     * @return The enclosureType
     */
    @JsonProperty("enclosureType")
    public String getEnclosureType() {
        return enclosureType;
    }

    /**
     * 
     * @param enclosureType
     *            The enclosureType
     */
    @JsonProperty("enclosureType")
    public void setEnclosureType(final String enclosureType) {
        this.enclosureType = enclosureType;
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
     * @return The rackName
     */
    @JsonProperty("rackName")
    public String getRackName() {
        return rackName;
    }

    /**
     * 
     * @param rackName
     *            The rackName
     */
    @JsonProperty("rackName")
    public void setRackName(final String rackName) {
        this.rackName = rackName;
    }

    /**
     * 
     * (Required)
     * 
     * @return The deviceBayCount
     */
    @JsonProperty("deviceBayCount")
    public Integer getDeviceBayCount() {
        return deviceBayCount;
    }

    /**
     * 
     * (Required)
     * 
     * @param deviceBayCount
     *            The deviceBayCount
     */
    @JsonProperty("deviceBayCount")
    public void setDeviceBayCount(final Integer deviceBayCount) {
        this.deviceBayCount = deviceBayCount;
    }

    /**
     * 
     * @return The deviceBays
     */
    @JsonProperty("deviceBays")
    public List<Object> getDeviceBays() {
        return deviceBays;
    }

    /**
     * 
     * @param deviceBays
     *            The deviceBays
     */
    @JsonProperty("deviceBays")
    public void setDeviceBays(final List<Object> deviceBays) {
        this.deviceBays = deviceBays;
    }

    /**
     * 
     * @return The interconnectBayCount
     */
    @JsonProperty("interconnectBayCount")
    public Integer getInterconnectBayCount() {
        return interconnectBayCount;
    }

    /**
     * 
     * @param interconnectBayCount
     *            The interconnectBayCount
     */
    @JsonProperty("interconnectBayCount")
    public void setInterconnectBayCount(final Integer interconnectBayCount) {
        this.interconnectBayCount = interconnectBayCount;
    }

    /**
     * 
     * @return The interconnectBays
     */
    @JsonProperty("interconnectBays")
    public List<InterconnectBay> getInterconnectBays() {
        return interconnectBays;
    }

    /**
     * 
     * @param interconnectBays
     *            The interconnectBays
     */
    @JsonProperty("interconnectBays")
    public void setInterconnectBays(final List<InterconnectBay> interconnectBays) {
        this.interconnectBays = interconnectBays;
    }

    /**
     * 
     * @return The powerSupplyBayCount
     */
    @JsonProperty("powerSupplyBayCount")
    public Integer getPowerSupplyBayCount() {
        return powerSupplyBayCount;
    }

    /**
     * 
     * @param powerSupplyBayCount
     *            The powerSupplyBayCount
     */
    @JsonProperty("powerSupplyBayCount")
    public void setPowerSupplyBayCount(final Integer powerSupplyBayCount) {
        this.powerSupplyBayCount = powerSupplyBayCount;
    }

    /**
     * 
     * @return The powerSupplyBays
     */
    @JsonProperty("powerSupplyBays")
    public List<PowerSupplyBay> getPowerSupplyBays() {
        return powerSupplyBays;
    }

    /**
     * 
     * @param powerSupplyBays
     *            The powerSupplyBays
     */
    @JsonProperty("powerSupplyBays")
    public void setPowerSupplyBays(final List<PowerSupplyBay> powerSupplyBays) {
        this.powerSupplyBays = powerSupplyBays;
    }

    /**
     * 
     * @return The fanBayCount
     */
    @JsonProperty("fanBayCount")
    public Integer getFanBayCount() {
        return fanBayCount;
    }

    /**
     * 
     * @param fanBayCount
     *            The fanBayCount
     */
    @JsonProperty("fanBayCount")
    public void setFanBayCount(final Integer fanBayCount) {
        this.fanBayCount = fanBayCount;
    }

    /**
     * 
     * @return The fanBays
     */
    @JsonProperty("fanBays")
    public List<FanBay> getFanBays() {
        return fanBays;
    }

    /**
     * 
     * @param fanBays
     *            The fanBays
     */
    @JsonProperty("fanBays")
    public void setFanBays(final List<FanBay> fanBays) {
        this.fanBays = fanBays;
    }

    /**
     * 
     * @return The oa
     */
    @JsonProperty("oa")
    public List<Oa> getOa() {
        return oa;
    }

    /**
     * 
     * @param oa
     *            The oa
     */
    @JsonProperty("oa")
    public void setOa(final List<Oa> oa) {
        this.oa = oa;
    }

    /**
     * 
     * @return The oaBayCount
     */
    @JsonProperty("oaBayCount")
    public Integer getOaBayCount() {
        return oaBayCount;
    }

    /**
     * 
     * @param oaBayCount
     *            The oaBayCount
     */
    @JsonProperty("oaBayCount")
    public void setOaBayCount(final Integer oaBayCount) {
        this.oaBayCount = oaBayCount;
    }

    /**
     * 
     * @return The activeOaPreferredIP
     */
    @JsonProperty("activeOaPreferredIP")
    public String getActiveOaPreferredIP() {
        return activeOaPreferredIP;
    }

    /**
     * 
     * @param activeOaPreferredIP
     *            The activeOaPreferredIP
     */
    @JsonProperty("activeOaPreferredIP")
    public void setActiveOaPreferredIP(final String activeOaPreferredIP) {
        this.activeOaPreferredIP = activeOaPreferredIP;
    }

    /**
     * 
     * @return The standbyOaPreferredIP
     */
    @JsonProperty("standbyOaPreferredIP")
    public String getStandbyOaPreferredIP() {
        return standbyOaPreferredIP;
    }

    /**
     * 
     * @param standbyOaPreferredIP
     *            The standbyOaPreferredIP
     */
    @JsonProperty("standbyOaPreferredIP")
    public void setStandbyOaPreferredIP(final String standbyOaPreferredIP) {
        this.standbyOaPreferredIP = standbyOaPreferredIP;
    }

    /**
     * 
     * @return The isFwManaged
     */
    @JsonProperty("isFwManaged")
    public Boolean getIsFwManaged() {
        return isFwManaged;
    }

    /**
     * 
     * @param isFwManaged
     *            The isFwManaged
     */
    @JsonProperty("isFwManaged")
    public void setIsFwManaged(final Boolean isFwManaged) {
        this.isFwManaged = isFwManaged;
    }

    /**
     * 
     * @return The fwBaselineName
     */
    @JsonProperty("fwBaselineName")
    public String getFwBaselineName() {
        return fwBaselineName;
    }

    /**
     * 
     * @param fwBaselineName
     *            The fwBaselineName
     */
    @JsonProperty("fwBaselineName")
    public void setFwBaselineName(final String fwBaselineName) {
        this.fwBaselineName = fwBaselineName;
    }

    /**
     * 
     * @return The fwBaselineUri
     */
    @JsonProperty("fwBaselineUri")
    public String getFwBaselineUri() {
        return fwBaselineUri;
    }

    /**
     * 
     * @param fwBaselineUri
     *            The fwBaselineUri
     */
    @JsonProperty("fwBaselineUri")
    public void setFwBaselineUri(final String fwBaselineUri) {
        this.fwBaselineUri = fwBaselineUri;
    }

    /**
     * 
     * (Required)
     * 
     * @return The vcmMode
     */
    @JsonProperty("vcmMode")
    public Boolean getVcmMode() {
        return vcmMode;
    }

    /**
     * 
     * (Required)
     * 
     * @param vcmMode
     *            The vcmMode
     */
    @JsonProperty("vcmMode")
    public void setVcmMode(final Boolean vcmMode) {
        this.vcmMode = vcmMode;
    }

    /**
     * 
     * @return The vcmUrl
     */
    @JsonProperty("vcmUrl")
    public String getVcmUrl() {
        return vcmUrl;
    }

    /**
     * 
     * @param vcmUrl
     *            The vcmUrl
     */
    @JsonProperty("vcmUrl")
    public void setVcmUrl(final String vcmUrl) {
        this.vcmUrl = vcmUrl;
    }

    /**
     * 
     * @return The vcmDomainName
     */
    @JsonProperty("vcmDomainName")
    public String getVcmDomainName() {
        return vcmDomainName;
    }

    /**
     * 
     * @param vcmDomainName
     *            The vcmDomainName
     */
    @JsonProperty("vcmDomainName")
    public void setVcmDomainName(final String vcmDomainName) {
        this.vcmDomainName = vcmDomainName;
    }

    /**
     * 
     * @return The vcmDomainId
     */
    @JsonProperty("vcmDomainId")
    public String getVcmDomainId() {
        return vcmDomainId;
    }

    /**
     * 
     * @param vcmDomainId
     *            The vcmDomainId
     */
    @JsonProperty("vcmDomainId")
    public void setVcmDomainId(final String vcmDomainId) {
        this.vcmDomainId = vcmDomainId;
    }

    /**
     * 
     * @return The category
     */
    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *            The category
     */
    @JsonProperty("category")
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     * 
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *            The description
     */
    @JsonProperty("description")
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * 
     * @return The created
     */
    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    /**
     * 
     * @param created
     *            The created
     */
    @JsonProperty("created")
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     * 
     * @return The modified
     */
    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    /**
     * 
     * @param modified
     *            The modified
     */
    @JsonProperty("modified")
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     * 
     * @return The eTag
     */
    @JsonProperty("eTag")
    public String getETag() {
        return eTag;
    }

    /**
     * 
     * @param eTag
     *            The eTag
     */
    @JsonProperty("eTag")
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     * 
     * (Required)
     * 
     * @return The licensingIntent
     */
    @JsonProperty("licensingIntent")
    public Enclosures.LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     * 
     * (Required)
     * 
     * @param licensingIntent
     *            The licensingIntent
     */
    @JsonProperty("licensingIntent")
    public void setLicensingIntent(final Enclosures.LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    /**
     * 
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            The state
     */
    @JsonProperty("state")
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * Indicates the reason the resource in its current state
     * 
     * @return The stateReason
     */
    @JsonProperty("stateReason")
    public String getStateReason() {
        return stateReason;
    }

    /**
     * Indicates the reason the resource in its current state
     * 
     * @param stateReason
     *            The stateReason
     */
    @JsonProperty("stateReason")
    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * Indicates if the resource is currently refreshing.
     * 
     * @return The refreshState
     */
    @JsonProperty("refreshState")
    public Enclosures.RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * Indicates if the resource is currently refreshing.
     * 
     * @param refreshState
     *            The refreshState
     */
    @JsonProperty("refreshState")
    public void setRefreshState(final Enclosures.RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * 
     * @return The status
     */
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *            The status
     */
    @JsonProperty("status")
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 
     * @return The consistentWithGroup
     */
    @JsonProperty("consistentWithGroup")
    public Boolean getConsistentWithGroup() {
        return consistentWithGroup;
    }

    /**
     * 
     * @param consistentWithGroup
     *            The consistentWithGroup
     */
    @JsonProperty("consistentWithGroup")
    public void setConsistentWithGroup(final Boolean consistentWithGroup) {
        this.consistentWithGroup = consistentWithGroup;
    }

    /**
     * 
     * (Required)
     * 
     * @return The reconfigurationState
     */
    @JsonProperty("reconfigurationState")
    public Enclosures.ReconfigurationState getReconfigurationState() {
        return reconfigurationState;
    }

    /**
     * 
     * (Required)
     * 
     * @param reconfigurationState
     *            The reconfigurationState
     */
    @JsonProperty("reconfigurationState")
    public void setReconfigurationState(final Enclosures.ReconfigurationState reconfigurationState) {
        this.reconfigurationState = reconfigurationState;
    }

    /**
     * 
     * (Required)
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * (Required)
     * 
     * @param type
     *            The type
     */
    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(uri).append(uuid).append(name).append(serialNumber).append(partNumber).append(assetTag)
                .append(enclosureType).append(enclosureGroupUri).append(rackName).append(deviceBayCount).append(deviceBays)
                .append(interconnectBayCount).append(interconnectBays).append(powerSupplyBayCount).append(powerSupplyBays)
                .append(fanBayCount).append(fanBays).append(oa).append(oaBayCount).append(activeOaPreferredIP)
                .append(standbyOaPreferredIP).append(isFwManaged).append(fwBaselineName).append(fwBaselineUri).append(vcmMode)
                .append(vcmUrl).append(vcmDomainName).append(vcmDomainId).append(category).append(description).append(created)
                .append(modified).append(eTag).append(licensingIntent).append(state).append(stateReason).append(refreshState)
                .append(status).append(consistentWithGroup).append(reconfigurationState).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Enclosures) == false) {
            return false;
        }
        final Enclosures rhs = ((Enclosures) other);
        return new EqualsBuilder().append(uri, rhs.uri).append(uuid, rhs.uuid).append(name, rhs.name)
                .append(serialNumber, rhs.serialNumber).append(partNumber, rhs.partNumber).append(assetTag, rhs.assetTag)
                .append(enclosureType, rhs.enclosureType).append(enclosureGroupUri, rhs.enclosureGroupUri)
                .append(rackName, rhs.rackName).append(deviceBayCount, rhs.deviceBayCount).append(deviceBays, rhs.deviceBays)
                .append(interconnectBayCount, rhs.interconnectBayCount).append(interconnectBays, rhs.interconnectBays)
                .append(powerSupplyBayCount, rhs.powerSupplyBayCount).append(powerSupplyBays, rhs.powerSupplyBays)
                .append(fanBayCount, rhs.fanBayCount).append(fanBays, rhs.fanBays).append(oa, rhs.oa)
                .append(oaBayCount, rhs.oaBayCount).append(activeOaPreferredIP, rhs.activeOaPreferredIP)
                .append(standbyOaPreferredIP, rhs.standbyOaPreferredIP).append(isFwManaged, rhs.isFwManaged)
                .append(fwBaselineName, rhs.fwBaselineName).append(fwBaselineUri, rhs.fwBaselineUri).append(vcmMode, rhs.vcmMode)
                .append(vcmUrl, rhs.vcmUrl).append(vcmDomainName, rhs.vcmDomainName).append(vcmDomainId, rhs.vcmDomainId)
                .append(category, rhs.category).append(description, rhs.description).append(created, rhs.created)
                .append(modified, rhs.modified).append(eTag, rhs.eTag).append(licensingIntent, rhs.licensingIntent)
                .append(state, rhs.state).append(stateReason, rhs.stateReason).append(refreshState, rhs.refreshState)
                .append(status, rhs.status).append(consistentWithGroup, rhs.consistentWithGroup)
                .append(reconfigurationState, rhs.reconfigurationState).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum LicensingIntent {

        OneView("OneView"), OneViewNoiLO("OneViewNoiLO"), OneViewStandard("OneViewStandard");
        private final String value;
        private static Map<String, Enclosures.LicensingIntent> constants = new HashMap<String, Enclosures.LicensingIntent>();

        static {
            for (final Enclosures.LicensingIntent c : values()) {
                constants.put(c.value, c);
            }
        }

        private LicensingIntent(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Enclosures.LicensingIntent fromValue(final String value) {
            final Enclosures.LicensingIntent constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum ReconfigurationState {

        NotReapplyingConfiguration("NotReapplyingConfiguration"), ReapplyingConfiguration("ReapplyingConfiguration"), ReapplyingConfigurationFailed(
                "ReapplyingConfigurationFailed"), PENDING("Pending");
        private final String value;
        private static Map<String, Enclosures.ReconfigurationState> constants = new HashMap<String, Enclosures.ReconfigurationState>();

        static {
            for (final Enclosures.ReconfigurationState c : values()) {
                constants.put(c.value, c);
            }
        }

        private ReconfigurationState(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Enclosures.ReconfigurationState fromValue(final String value) {
            final Enclosures.ReconfigurationState constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum RefreshState {

        NotRefreshing("NotRefreshing"), RefreshPending("RefreshPending"), Refreshing("Refreshing"), RefreshFailed("RefreshFailed");
        private final String value;
        private static Map<String, Enclosures.RefreshState> constants = new HashMap<String, Enclosures.RefreshState>();

        static {
            for (final Enclosures.RefreshState c : values()) {
                constants.put(c.value, c);
            }
        }

        private RefreshState(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static Enclosures.RefreshState fromValue(final String value) {
            final Enclosures.RefreshState constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
