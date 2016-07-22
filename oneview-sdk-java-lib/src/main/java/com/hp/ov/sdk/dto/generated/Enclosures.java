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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.ReconfigurationState;

/**
 * The Enclosures data transfer object (DTO) contains the information used to
 * represent a enclosure in the system. It is passed in to the add/update
 * enclosure REST api, as well as the add/update enclosure through java client
 * api.
 */

public class Enclosures implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String uri;
    private String uuid;
    @Since(200)
    private Enclosures.UuidState uuidState;
    private String name;
    private String serialNumber;
    private String partNumber;
    private String assetTag;
    private String enclosureType;
    private String enclosureGroupUri;
    @Since(200)
    private String enclosureModel;
    @Since(200)
    private String enclosureTypeUri;
    private String rackName;
    /**
     *
     *(Required)
     *
     */
    private Integer deviceBayCount;
    private List<Object> deviceBays = new ArrayList<Object>();
    private Integer interconnectBayCount;
    private List<InterconnectBay> interconnectBays = new ArrayList<InterconnectBay>();
    private Integer powerSupplyBayCount;
    private List<PowerSupplyBay> powerSupplyBays = new ArrayList<PowerSupplyBay>();
    private Integer fanBayCount;
    private List<FanBay> fanBays = new ArrayList<FanBay>();
    private List<Oa> oa = new ArrayList<Oa>();
    private Integer oaBayCount;
    @Since(200)
    private Integer oaBays;
    private String activeOaPreferredIP;
    private String standbyOaPreferredIP;
    private Boolean isFwManaged;
    @Since(200)
    private String logicalEnclosureUri;
    private String fwBaselineName;
    private String fwBaselineUri;
    /**
     *
     *(Required)
     *
     */
    private Boolean vcmMode;
    private String vcmUrl;
    private String vcmDomainName;
    private String vcmDomainId;
    private String category;
    private String description;
    private String created;
    private String modified;
    private String eTag;
    /**
     *
     *(Required)
     *
     */
    private Enclosures.LicensingIntent licensingIntent;
    private String state;
    /**
     *Indicates the reason the resource in its current state
     *
     */
    private String stateReason;
    /**
     *Indicates if the resource is currently refreshing.
     *
     */
    private Enclosures.RefreshState refreshState;
    private String status;
    private Boolean consistentWithGroup;
    private String type;
    /**
     *
     *(Required)
     *
     */
    private ReconfigurationState reconfigurationState;

    /**
     *
     *@return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     *@param uri
     *           The uri
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     *
     *@return The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     *
     *@param uuid
     *           The uuid
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }

    /**
     *
     *@return The uuid
     */
    public String getUuidState() {
        return uuid;
    }

    /**
     *
     *@param uuidState
     *           The uuidState
     */
    public void setUuidState(final UuidState uuidState) {
        this.uuidState = uuidState;
    }

    /**
     *
     *@return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     *@param name
     *           The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     *@return The serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     *@param serialNumber
     *           The serialNumber
     */
    public void setSerialNumber(final String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     *@return The partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     *
     *@param partNumber
     *           The partNumber
     */
    public void setPartNumber(final String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     *
     *@return The assetTag
     */
    public String getAssetTag() {
        return assetTag;
    }

    /**
     *
     *@param assetTag
     *           The assetTag
     */
    public void setAssetTag(final String assetTag) {
        this.assetTag = assetTag;
    }

    /**
     *
     *@return The enclosureType
     */
    public String getEnclosureType() {
        return enclosureType;
    }

    /**
     *
     *@param enclosureType
     *           The enclosureType
     */
    public void setEnclosureType(final String enclosureType) {
        this.enclosureType = enclosureType;
    }

    /**
     *
     *@return The enclosureGroupUri
     */
    public String getEnclosureGroupUri() {
        return enclosureGroupUri;
    }

    /**
     *
     *@param enclosureGroupUri
     *           The enclosureGroupUri
     */
    public void setEnclosureGroupUri(final String enclosureGroupUri) {
        this.enclosureGroupUri = enclosureGroupUri;
    }

    /**
     *
     *@return The enclosureModel
     */
    public String getEnclosureModel() {
        return enclosureModel;
    }

    /**
     *
     *@param enclosureModel
     *           The enclosureModel
     */
    public void setEnclosureModel(final String enclosureModel) {
        this.enclosureModel = enclosureModel;
    }

    /**
     *
     *@return The enclosureTypeUri
     */
    public String getEnclosureTypeUri() {
        return enclosureTypeUri;
    }

    /**
     *
     *@param enclosureTypeUri
     *           The enclosureTypeUri
     */
    public void setEnclosureTypeUri(final String enclosureTypeUri) {
        this.enclosureTypeUri = enclosureTypeUri;
    }

    /**
     *
     *@return The rackName
     */
    public String getRackName() {
        return rackName;
    }

    /**
     *
     *@param rackName
     *           The rackName
     */
    public void setRackName(final String rackName) {
        this.rackName = rackName;
    }

    /**
     *
     *(Required)
     *
     *@return The deviceBayCount
     */
    public Integer getDeviceBayCount() {
        return deviceBayCount;
    }

    /**
     *
     *(Required)
     *
     *@param deviceBayCount
     *           The deviceBayCount
     */
    public void setDeviceBayCount(final Integer deviceBayCount) {
        this.deviceBayCount = deviceBayCount;
    }

    /**
     *
     *@return The deviceBays
     */
    public List<Object> getDeviceBays() {
        return deviceBays;
    }

    /**
     *
     *@param deviceBays
     *           The deviceBays
     */
    public void setDeviceBays(final List<Object> deviceBays) {
        this.deviceBays = deviceBays;
    }

    /**
     *
     *@return The interconnectBayCount
     */
    public Integer getInterconnectBayCount() {
        return interconnectBayCount;
    }

    /**
     *
     *@param interconnectBayCount
     *           The interconnectBayCount
     */
    public void setInterconnectBayCount(final Integer interconnectBayCount) {
        this.interconnectBayCount = interconnectBayCount;
    }

    /**
     *
     *@return The interconnectBays
     */
    public List<InterconnectBay> getInterconnectBays() {
        return interconnectBays;
    }

    /**
     *
     *@param interconnectBays
     *           The interconnectBays
     */
    public void setInterconnectBays(final List<InterconnectBay> interconnectBays) {
        this.interconnectBays = interconnectBays;
    }

    /**
     *
     *@return The powerSupplyBayCount
     */
    public Integer getPowerSupplyBayCount() {
        return powerSupplyBayCount;
    }

    /**
     *
     *@param powerSupplyBayCount
     *           The powerSupplyBayCount
     */
    public void setPowerSupplyBayCount(final Integer powerSupplyBayCount) {
        this.powerSupplyBayCount = powerSupplyBayCount;
    }

    /**
     *
     *@return The powerSupplyBays
     */
    public List<PowerSupplyBay> getPowerSupplyBays() {
        return powerSupplyBays;
    }

    /**
     *
     *@param powerSupplyBays
     *           The powerSupplyBays
     */
    public void setPowerSupplyBays(final List<PowerSupplyBay> powerSupplyBays) {
        this.powerSupplyBays = powerSupplyBays;
    }

    /**
     *
     *@return The fanBayCount
     */
    public Integer getFanBayCount() {
        return fanBayCount;
    }

    /**
     *
     *@param fanBayCount
     *           The fanBayCount
     */
    public void setFanBayCount(final Integer fanBayCount) {
        this.fanBayCount = fanBayCount;
    }

    /**
     *
     *@return The fanBays
     */
    public List<FanBay> getFanBays() {
        return fanBays;
    }

    /**
     *
     *@param fanBays
     *           The fanBays
     */
    public void setFanBays(final List<FanBay> fanBays) {
        this.fanBays = fanBays;
    }

    /**
     *
     *@return The oa
     */
    public List<Oa> getOa() {
        return oa;
    }

    /**
     *
     *@param oa
     *           The oa
     */
    public void setOa(final List<Oa> oa) {
        this.oa = oa;
    }

    /**
     *
     *@return The oaBayCount
     */
    public Integer getOaBayCount() {
        return oaBayCount;
    }

    /**
     *
     *@param oaBayCount
     *           The oaBayCount
     */
    public void setOaBayCount(final Integer oaBayCount) {
        this.oaBayCount = oaBayCount;
    }

    /**
     *
     *@return The oaBays
     */
    public Integer getOaBays() {
        return oaBayCount;
    }

    /**
     *
     *@param oaBays
     *           The oaBays
     */
    public void setOaBays(final Integer oaBays) {
        this.oaBays = oaBays;
    }

    /**
     *
     *@return The activeOaPreferredIP
     */
    public String getActiveOaPreferredIP() {
        return activeOaPreferredIP;
    }

    /**
     *
     *@param activeOaPreferredIP
     *           The activeOaPreferredIP
     */
    public void setActiveOaPreferredIP(final String activeOaPreferredIP) {
        this.activeOaPreferredIP = activeOaPreferredIP;
    }

    /**
     *
     *@return The standbyOaPreferredIP
     */
    public String getStandbyOaPreferredIP() {
        return standbyOaPreferredIP;
    }

    /**
     *
     *@param standbyOaPreferredIP
     *           The standbyOaPreferredIP
     */
    public void setStandbyOaPreferredIP(final String standbyOaPreferredIP) {
        this.standbyOaPreferredIP = standbyOaPreferredIP;
    }

    /**
     *
     *@return The isFwManaged
     */
    public Boolean getIsFwManaged() {
        return isFwManaged;
    }

    /**
     *
     *@param isFwManaged
     *           The isFwManaged
     */
    public void setIsFwManaged(final Boolean isFwManaged) {
        this.isFwManaged = isFwManaged;
    }

    /**
     *
     *@return The logicalEnclosureUri
     */
    public String getLogicalEnclosureUri() {
        return logicalEnclosureUri;
    }

    /**
     *
     *@param logicalEnclosureUri
     *           The logicalEnclosureUri
     */
    public void setLogicalEnclosureUri(final String logicalEnclosureUri) {
        this.logicalEnclosureUri = logicalEnclosureUri;
    }

    /**
     *
     *@return The fwBaselineName
     */
    public String getFwBaselineName() {
        return fwBaselineName;
    }

    /**
     *
     *@param fwBaselineName
     *           The fwBaselineName
     */
    public void setFwBaselineName(final String fwBaselineName) {
        this.fwBaselineName = fwBaselineName;
    }

    /**
     *
     *@return The fwBaselineUri
     */
    public String getFwBaselineUri() {
        return fwBaselineUri;
    }

    /**
     *
     *@param fwBaselineUri
     *           The fwBaselineUri
     */
    public void setFwBaselineUri(final String fwBaselineUri) {
        this.fwBaselineUri = fwBaselineUri;
    }

    /**
     *
     *(Required)
     *
     *@return The vcmMode
     */
    public Boolean getVcmMode() {
        return vcmMode;
    }

    /**
     *
     *(Required)
     *
     *@param vcmMode
     *           The vcmMode
     */
    public void setVcmMode(final Boolean vcmMode) {
        this.vcmMode = vcmMode;
    }

    /**
     *
     *@return The vcmUrl
     */
    public String getVcmUrl() {
        return vcmUrl;
    }

    /**
     *
     *@param vcmUrl
     *           The vcmUrl
     */
    public void setVcmUrl(final String vcmUrl) {
        this.vcmUrl = vcmUrl;
    }

    /**
     *
     *@return The vcmDomainName
     */
    public String getVcmDomainName() {
        return vcmDomainName;
    }

    /**
     *
     *@param vcmDomainName
     *           The vcmDomainName
     */
    public void setVcmDomainName(final String vcmDomainName) {
        this.vcmDomainName = vcmDomainName;
    }

    /**
     *
     *@return The vcmDomainId
     */
    public String getVcmDomainId() {
        return vcmDomainId;
    }

    /**
     *
     *@param vcmDomainId
     *           The vcmDomainId
     */
    public void setVcmDomainId(final String vcmDomainId) {
        this.vcmDomainId = vcmDomainId;
    }

    /**
     *
     *@return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     *@param category
     *           The category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     *
     *@return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     *@param description
     *           The description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     *
     *@return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     *@param created
     *           The created
     */
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     *
     *@return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     *@param modified
     *           The modified
     */
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     *
     *@return The eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     *
     *@param eTag
     *           The eTag
     */
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     *
     *(Required)
     *
     *@return The licensingIntent
     */
    public Enclosures.LicensingIntent getLicensingIntent() {
        return licensingIntent;
    }

    /**
     *
     *(Required)
     *
     *@param licensingIntent
     *           The licensingIntent
     */
    public void setLicensingIntent(final Enclosures.LicensingIntent licensingIntent) {
        this.licensingIntent = licensingIntent;
    }

    /**
     *
     *@return The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     *@param state
     *           The state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     *Indicates the reason the resource in its current state
     *
     *@return The stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     *Indicates the reason the resource in its current state
     *
     *@param stateReason
     *           The stateReason
     */
    public void setStateReason(final String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     *Indicates if the resource is currently refreshing.
     *
     *@return The refreshState
     */
    public Enclosures.RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     *Indicates if the resource is currently refreshing.
     *
     *@param refreshState
     *           The refreshState
     */
    public void setRefreshState(final Enclosures.RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     *
     *@return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     *@param status
     *           The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     *
     *@return The consistentWithGroup
     */
    public Boolean getConsistentWithGroup() {
        return consistentWithGroup;
    }

    /**
     *
     *@param consistentWithGroup
     *           The consistentWithGroup
     */
    public void setConsistentWithGroup(final Boolean consistentWithGroup) {
        this.consistentWithGroup = consistentWithGroup;
    }

    /**
     *
     *(Required)
     *
     *@return The reconfigurationState
     */
    public ReconfigurationState getReconfigurationState() {
        return reconfigurationState;
    }

    /**
     *
     *(Required)
     *
     *@param reconfigurationState
     *           The reconfigurationState
     */
    public void setReconfigurationState(final ReconfigurationState reconfigurationState) {
        this.reconfigurationState = reconfigurationState;
    }

    /**
     *
     *(Required)
     *
     *@return The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     *(Required)
     *
     *@param type
     *           The type
     */
    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(uri)
                .append(uuid)
                .append(uuidState)
                .append(name)
                .append(serialNumber)
                .append(partNumber)
                .append(assetTag)
                .append(enclosureType)
                .append(enclosureGroupUri)
                .append(enclosureModel)
                .append(enclosureTypeUri)
                .append(rackName)
                .append(deviceBayCount)
                .append(deviceBays)
                .append(interconnectBayCount)
                .append(interconnectBays)
                .append(powerSupplyBayCount)
                .append(powerSupplyBays)
                .append(fanBayCount)
                .append(fanBays)
                .append(oa)
                .append(oaBayCount)
                .append(oaBays)
                .append(activeOaPreferredIP)
                .append(standbyOaPreferredIP)
                .append(isFwManaged)
                .append(logicalEnclosureUri)
                .append(fwBaselineName)
                .append(fwBaselineUri)
                .append(vcmMode)
                .append(vcmUrl)
                .append(vcmDomainName)
                .append(vcmDomainId)
                .append(category)
                .append(description)
                .append(created)
                .append(modified)
                .append(eTag)
                .append(licensingIntent)
                .append(state)
                .append(stateReason)
                .append(refreshState)
                .append(status)
                .append(consistentWithGroup)
                .append(reconfigurationState)
                .toHashCode();
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
        return new EqualsBuilder()
                .append(uri, rhs.uri)
                .append(uuid, rhs.uuid)
                .append(uuidState, rhs.uuidState)
                .append(name, rhs.name)
                .append(serialNumber, rhs.serialNumber)
                .append(partNumber, rhs.partNumber)
                .append(assetTag, rhs.assetTag)
                .append(enclosureType, rhs.enclosureType)
                .append(enclosureGroupUri, rhs.enclosureGroupUri)
                .append(enclosureModel, rhs.enclosureModel)
                .append(enclosureTypeUri, rhs.enclosureTypeUri)
                .append(rackName, rhs.rackName)
                .append(deviceBayCount, rhs.deviceBayCount)
                .append(deviceBays, rhs.deviceBays)
                .append(interconnectBayCount, rhs.interconnectBayCount)
                .append(interconnectBays, rhs.interconnectBays)
                .append(powerSupplyBayCount, rhs.powerSupplyBayCount)
                .append(powerSupplyBays, rhs.powerSupplyBays)
                .append(fanBayCount, rhs.fanBayCount)
                .append(fanBays, rhs.fanBays)
                .append(oa, rhs.oa)
                .append(oaBayCount, rhs.oaBayCount)
                .append(oaBays, rhs.oaBays)
                .append(activeOaPreferredIP, rhs.activeOaPreferredIP)
                .append(standbyOaPreferredIP, rhs.standbyOaPreferredIP)
                .append(isFwManaged, rhs.isFwManaged)
                .append(logicalEnclosureUri, rhs.logicalEnclosureUri)
                .append(fwBaselineName, rhs.fwBaselineName)
                .append(fwBaselineUri, rhs.fwBaselineUri)
                .append(vcmMode, rhs.vcmMode)
                .append(vcmUrl, rhs.vcmUrl)
                .append(vcmDomainName, rhs.vcmDomainName)
                .append(vcmDomainId, rhs.vcmDomainId)
                .append(category, rhs.category)
                .append(description, rhs.description)
                .append(created, rhs.created)
                .append(modified, rhs.modified)
                .append(eTag, rhs.eTag)
                .append(licensingIntent, rhs.licensingIntent)
                .append(state, rhs.state)
                .append(stateReason, rhs.stateReason)
                .append(refreshState, rhs.refreshState)
                .append(status, rhs.status)
                .append(consistentWithGroup, rhs.consistentWithGroup)
                .append(reconfigurationState, rhs.reconfigurationState)
                .isEquals();
    }

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

        @Override
        public String toString() {
            return this.value;
        }

        public static Enclosures.LicensingIntent fromValue(final String value) {
            final Enclosures.LicensingIntent constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

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

        @Override
        public String toString() {
            return this.value;
        }

        public static Enclosures.RefreshState fromValue(final String value) {
            final Enclosures.RefreshState constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

    public static enum UuidState {

        Blink("Blink"),
        Off("Off"),
        On("On");

        private final String value;
        private static Map<String, Enclosures.UuidState> constants = new HashMap<String, Enclosures.UuidState>();

        static {
            for (final Enclosures.UuidState c : values()) {
                constants.put(c.value, c);
            }
        }

        private UuidState(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static Enclosures.UuidState fromValue(final String value) {
            final Enclosures.UuidState constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

}
