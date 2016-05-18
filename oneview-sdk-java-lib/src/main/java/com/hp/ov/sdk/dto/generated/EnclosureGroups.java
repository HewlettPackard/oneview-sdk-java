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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.StackingMode;

/**
 * The EnclosureGroups data transfer object (DTO) contains the information used
 * to represent a enclosure group in the system. It is used to pass details
 * about the LIG that the enclosure group uses while adding an enclosure. It is
 * passed to the add/update enclosureGroup REST api, as well as the add/update
 * enclosureGroup through java client api.
 */

public class EnclosureGroups implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Since(200)
    private List<String> associatedLogicalInterconnectGroups = new ArrayList<String>();
    private String uri;
    private String category;
    private String name;
    private String created;
    @Since(200)
    private List<String> ipRangeUris = new ArrayList<String>();
    private String modified;
    private String eTag;
    @Since(200)
    private Integer enclosureCount;
    @Since(200)
    private String enclosureTypeUri;
    private String status;
    private String state;

    private EnclosureGroups.PowerMode powerMode;
    /**
     *
     * (Required)
     *
     */
    private StackingMode stackingMode;
    private Integer portMappingCount;
    private List<PortMapping> portMappings = new ArrayList<PortMapping>();
    /**
     *
     * (Required)
     *
     */
    private Integer interconnectBayMappingCount;
    /**
     *
     * (Required)
     *
     */
    private List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
    private String description;
    private String type;


    /**
     *
     * @return The associatedLogicalInterconnectGroups
     */
    public List<String> getAssociatedLogicalInterconnectGroups() {
        return associatedLogicalInterconnectGroups;
    }

    /**
     *
     * @param associatedLogicalInterconnectGroups
     *            The associatedLogicalInterconnectGroups
     */
    public void setAssociatedLogicalInterconnectGroups(final List<String> associatedLogicalInterconnectGroups) {
        this.associatedLogicalInterconnectGroups = associatedLogicalInterconnectGroups;
    }

    /**
     *
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     *            The type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @return The uri
     */
    public String getUri() {
        return uri;
    }

    /**
     *
     * @param uri
     *            The uri
     */
    public void setUri(final String uri) {
        this.uri = uri;
    }

    /**
     *
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     *            The category
     */
    public void setCategory(final String category) {
        this.category = category;
    }

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     *            The name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return The created
     */
    public String getCreated() {
        return created;
    }

    /**
     *
     * @param created
     *            The created
     */
    public void setCreated(final String created) {
        this.created = created;
    }

    /**
     *
     * @return The ipRangeUris
     */
    public List<String> getIpRangeUris() {
        return ipRangeUris;
    }

    /**
     *
     * @param ipRangeUris
     *            The ipRangeUris
     */
    public void setIpRangeUris(final List<String> ipRangeUris) {
        this.ipRangeUris = ipRangeUris;
    }

    /**
     *
     * @return The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     *
     * @param modified
     *            The modified
     */
    public void setModified(final String modified) {
        this.modified = modified;
    }

    /**
     *
     * @return The eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     *
     * @param eTag
     *            The eTag
     */
    public void setETag(final String eTag) {
        this.eTag = eTag;
    }

    /**
     *
     * @return The enclosureCount
     */
    public Integer getEnclosureCount() {
        return enclosureCount;
    }

    /**
     *
     * @param enclosureCount
     *            The enclosureCount
     */
    public void setEnclosureCount(final Integer enclosureCount) {
        this.enclosureCount = enclosureCount;
    }

    /**
     *
     * @return The enclosureTypeUri
     */
    public String getEnclosureTypeUri() {
        return enclosureTypeUri;
    }

    /**
     *
     * @param enclosureTypeUri
     *            The enclosureTypeUri
     */
    public void setEnclosureTypeUri(final String enclosureTypeUri) {
        this.enclosureTypeUri = enclosureTypeUri;
    }

    /**
     *
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     *            The status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     *
     * @return The state
     */
    public String getState() {
        return state;
    }

    /**
     *
     * @param state
     *            The state
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     *
     * @return The powerMode
     */
    public EnclosureGroups.PowerMode getPowerMode() {
        return powerMode;
    }

    /**
     *
     * @param powerMode
     *            The powerMode
     */
    public void setPowerMode(final EnclosureGroups.PowerMode powerMode) {
        this.powerMode = powerMode;
    }

    /**
     *
     * (Required)
     *
     * @return The stackingMode
     */
    public StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     *
     * (Required)
     *
     * @param stackingMode
     *            The stackingMode
     */
    public void setStackingMode(final StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

    /**
     *
     * @return The portMappingCount
     */
    public Integer getPortMappingCount() {
        return portMappingCount;
    }

    /**
     *
     * @param portMappingCount
     *            The portMappingCount
     */
    public void setPortMappingCount(final Integer portMappingCount) {
        this.portMappingCount = portMappingCount;
    }

    /**
     *
     * @return The portMappings
     */
    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    /**
     *
     * @param portMappings
     *            The portMappings
     */
    public void setPortMappings(final List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    /**
     *
     * (Required)
     *
     * @return The interconnectBayMappingCount
     */
    public Integer getInterconnectBayMappingCount() {
        return interconnectBayMappingCount;
    }

    /**
     *
     * (Required)
     *
     * @param interconnectBayMappingCount
     *            The interconnectBayMappingCount
     */
    public void setInterconnectBayMappingCount(final Integer interconnectBayMappingCount) {
        this.interconnectBayMappingCount = interconnectBayMappingCount;
    }

    /**
     *
     * (Required)
     *
     * @return The interconnectBayMappings
     */
    public List<InterconnectBayMapping> getInterconnectBayMappings() {
        return interconnectBayMappings;
    }

    /**
     *
     * (Required)
     *
     * @param interconnectBayMappings
     *            The interconnectBayMappings
     */
    public void setInterconnectBayMappings(final List<InterconnectBayMapping> interconnectBayMappings) {
        this.interconnectBayMappings = interconnectBayMappings;
    }

    /**
     *
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     *            The description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(associatedLogicalInterconnectGroups)
                .append(uri)
                .append(category)
                .append(name)
                .append(created)
                .append(ipRangeUris)
                .append(modified)
                .append(eTag)
                .append(enclosureCount)
                .append(enclosureTypeUri)
                .append(status)
                .append(state)
                .append(powerMode)
                .append(stackingMode)
                .append(portMappingCount)
                .append(portMappings)
                .append(interconnectBayMappingCount)
                .append(interconnectBayMappings)
                .append(description)
                .append(type)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EnclosureGroups) == false) {
            return false;
        }
        final EnclosureGroups rhs = ((EnclosureGroups) other);
        return new EqualsBuilder()
                .append(associatedLogicalInterconnectGroups, rhs.associatedLogicalInterconnectGroups)
                .append(uri, rhs.uri)
                .append(category, rhs.category)
                .append(name, rhs.name)
                .append(created, rhs.created)
                .append(ipRangeUris, rhs.ipRangeUris)
                .append(modified, rhs.modified)
                .append(eTag, rhs.eTag)
                .append(enclosureCount, rhs.enclosureCount)
                .append(enclosureTypeUri, rhs.enclosureTypeUri)
                .append(status, rhs.status)
                .append(state, rhs.state)
                .append(powerMode, rhs.powerMode)
                .append(stackingMode, rhs.stackingMode)
                .append(portMappingCount, rhs.portMappingCount)
                .append(portMappings, rhs.portMappings)
                .append(interconnectBayMappingCount, rhs.interconnectBayMappingCount)
                .append(interconnectBayMappings, rhs.interconnectBayMappings)
                .append(description, rhs.description)
                .append(type, rhs.type)
                .isEquals();
    }

    public static enum PowerMode {

        RedundantPowerFeed("RedundantPowerFeed"), RedundantPowerSupply("RedundantPowerSupply");
        private final String value;
        private static Map<String, EnclosureGroups.PowerMode> constants = new HashMap<String, EnclosureGroups.PowerMode>();

        static {
            for (final EnclosureGroups.PowerMode c : values()) {
                constants.put(c.value, c);
            }
        }

        private PowerMode(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static EnclosureGroups.PowerMode fromValue(final String value) {
            final EnclosureGroups.PowerMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
