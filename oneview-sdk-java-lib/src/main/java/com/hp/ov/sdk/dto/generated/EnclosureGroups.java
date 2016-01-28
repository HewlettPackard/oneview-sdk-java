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
import com.google.gson.annotations.Since;

/**
 * The EnclosureGroups data transfer object (DTO) contains the information used
 * to represent a enclosure group in the system. It is used to pass details
 * about the LIG that the enclosure group uses while adding an enclosure. It is
 * passed to the add/update enclosureGroup REST api, as well as the add/update
 * enclosureGroup through java client api.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
	"associatedLogicalInterconnectGroups",
	"uri",
	"category",
	"name",
	"created",
	"ipRangeUris",
	"modified",
	"eTag",
	"enclosureCount",
	"enclosureTypeUri",
	"status",
	"state",
	"powerMode",
	"stackingMode",
	"portMappingCount",
	"portMappings",
	"interconnectBayMappingCount",
	"interconnectBayMappings",
	"description",
	"type"
	})
public class EnclosureGroups implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Since(200)
    @JsonProperty("associatedLogicalInterconnectGroups")
    private List<String> associatedLogicalInterconnectGroups = new ArrayList<String>();
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("category")
    private String category;
    @JsonProperty("name")
    private String name;
    @JsonProperty("created")
    private String created;
    @Since(200)
    @JsonProperty("ipRangeUris")
    private List<String> ipRangeUris = new ArrayList<String>();
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("eTag")
    private String eTag;
    @Since(200)
    @JsonProperty("enclosureCount")
    private Integer enclosureCount;
    @Since(200)
    @JsonProperty("enclosureTypeUri")
    private String enclosureTypeUri;
    @JsonProperty("status")
    private String status;
    @JsonProperty("state")
    private String state;

    @JsonProperty("powerMode")
    private EnclosureGroups.PowerMode powerMode;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stackingMode")
    private EnclosureGroups.StackingMode stackingMode;
    @JsonProperty("portMappingCount")
    private Integer portMappingCount;
    @JsonProperty("portMappings")
    private List<PortMapping> portMappings = new ArrayList<PortMapping>();
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("interconnectBayMappingCount")
    private Integer interconnectBayMappingCount;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("interconnectBayMappings")
    private List<InterconnectBayMapping> interconnectBayMappings = new ArrayList<InterconnectBayMapping>();
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;


    /**
     *
     * @return The associatedLogicalInterconnectGroups
     */
    @JsonProperty("associatedLogicalInterconnectGroups")
    public List<String> getAssociatedLogicalInterconnectGroups() {
        return associatedLogicalInterconnectGroups;
    }

    /**
     *
     * @param associatedLogicalInterconnectGroups
     *            The associatedLogicalInterconnectGroups
     */
    @JsonProperty("associatedLogicalInterconnectGroups")
    public void setAssociatedLogicalInterconnectGroups(final List<String> associatedLogicalInterconnectGroups) {
        this.associatedLogicalInterconnectGroups = associatedLogicalInterconnectGroups;
    }

    /**
     * 
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *            The type
     */
    @JsonProperty("type")
    public void setType(final String type) {
        this.type = type;
    }

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
     * @return The ipRangeUris
     */
    @JsonProperty("ipRangeUris")
    public List<String> getIpRangeUris() {
        return ipRangeUris;
    }

    /**
     *
     * @param ipRangeUris
     *            The ipRangeUris
     */
    @JsonProperty("ipRangeUris")
    public void setIpRangeUris(final List<String> ipRangeUris) {
        this.ipRangeUris = ipRangeUris;
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
     * @return The enclosureCount
     */
    @JsonProperty("enclosureCount")
    public Integer getEnclosureCount() {
        return enclosureCount;
    }

    /**
     *
     * @param enclosureCount
     *            The enclosureCount
     */
    @JsonProperty("enclosureCount")
    public void setEnclosureCount(final Integer enclosureCount) {
        this.enclosureCount = enclosureCount;
    }

    /**
     *
     * @return The enclosureTypeUri
     */
    @JsonProperty("enclosureTypeUri")
    public String getEnclosureTypeUri() {
        return enclosureTypeUri;
    }

    /**
     *
     * @param enclosureTypeUri
     *            The enclosureTypeUri
     */
    @JsonProperty("enclosureTypeUri")
    public void setEnclosureTypeUri(final String enclosureTypeUri) {
        this.enclosureTypeUri = enclosureTypeUri;
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
     *
     * @return The powerMode
     */
    @JsonProperty("stackingMode")
    public EnclosureGroups.PowerMode getPowerMode() {
        return powerMode;
    }

    /**
     *
     * @param powerMode
     *            The powerMode
     */
    @JsonProperty("powerMode")
    public void setPowerMode(final EnclosureGroups.PowerMode powerMode) {
        this.powerMode = powerMode;
    }

    /**
     * 
     * (Required)
     * 
     * @return The stackingMode
     */
    @JsonProperty("stackingMode")
    public EnclosureGroups.StackingMode getStackingMode() {
        return stackingMode;
    }

    /**
     * 
     * (Required)
     * 
     * @param stackingMode
     *            The stackingMode
     */
    @JsonProperty("stackingMode")
    public void setStackingMode(final EnclosureGroups.StackingMode stackingMode) {
        this.stackingMode = stackingMode;
    }

    /**
     * 
     * @return The portMappingCount
     */
    @JsonProperty("portMappingCount")
    public Integer getPortMappingCount() {
        return portMappingCount;
    }

    /**
     * 
     * @param portMappingCount
     *            The portMappingCount
     */
    @JsonProperty("portMappingCount")
    public void setPortMappingCount(final Integer portMappingCount) {
        this.portMappingCount = portMappingCount;
    }

    /**
     * 
     * @return The portMappings
     */
    @JsonProperty("portMappings")
    public List<PortMapping> getPortMappings() {
        return portMappings;
    }

    /**
     * 
     * @param portMappings
     *            The portMappings
     */
    @JsonProperty("portMappings")
    public void setPortMappings(final List<PortMapping> portMappings) {
        this.portMappings = portMappings;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectBayMappingCount
     */
    @JsonProperty("interconnectBayMappingCount")
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
    @JsonProperty("interconnectBayMappingCount")
    public void setInterconnectBayMappingCount(final Integer interconnectBayMappingCount) {
        this.interconnectBayMappingCount = interconnectBayMappingCount;
    }

    /**
     * 
     * (Required)
     * 
     * @return The interconnectBayMappings
     */
    @JsonProperty("interconnectBayMappings")
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
    @JsonProperty("interconnectBayMappings")
    public void setInterconnectBayMappings(final List<InterconnectBayMapping> interconnectBayMappings) {
        this.interconnectBayMappings = interconnectBayMappings;
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

    @Generated("org.jsonschema2pojo")
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

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static EnclosureGroups.PowerMode fromValue(final String value) {
            final EnclosureGroups.PowerMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum StackingMode {

        None("None"), Enclosure("Enclosure"), SwitchPairs("SwitchPairs"), MultiEnclosure("MultiEnclosure");
        private final String value;
        private static Map<String, EnclosureGroups.StackingMode> constants = new HashMap<String, EnclosureGroups.StackingMode>();

        static {
            for (final EnclosureGroups.StackingMode c : values()) {
                constants.put(c.value, c);
            }
        }

        private StackingMode(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static EnclosureGroups.StackingMode fromValue(final String value) {
            final EnclosureGroups.StackingMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
