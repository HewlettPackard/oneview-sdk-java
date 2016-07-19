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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.StackingMode;

/**
 * The EnclosureGroup data transfer object (DTO) contains the information used
 * to represent a enclosure group in the system. It is used to pass details
 * about the LIG that the enclosure group uses while adding an enclosure. It is
 * passed to the add/update enclosureGroup REST api, as well as the add/update
 * enclosureGroup through java client api.
 */
public class EnclosureGroup extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    @Since(200)
    private List<String> associatedLogicalInterconnectGroups = new ArrayList<String>();
    @Since(200)
    private List<String> ipRangeUris = new ArrayList<String>();
    @Since(200)
    private Integer enclosureCount;
    @Since(200)
    private String enclosureTypeUri;

    private EnclosureGroup.PowerMode powerMode;
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
     * @return The powerMode
     */
    public EnclosureGroup.PowerMode getPowerMode() {
        return powerMode;
    }

    /**
     *
     * @param powerMode
     *            The powerMode
     */
    public void setPowerMode(final EnclosureGroup.PowerMode powerMode) {
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

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof EnclosureGroup);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof EnclosureGroup) {
            EnclosureGroup that = (EnclosureGroup) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(associatedLogicalInterconnectGroups, that.associatedLogicalInterconnectGroups)
                    .append(ipRangeUris, that.ipRangeUris)
                    .append(enclosureCount, that.enclosureCount)
                    .append(enclosureTypeUri, that.enclosureTypeUri)
                    .append(powerMode, that.powerMode)
                    .append(stackingMode, that.stackingMode)
                    .append(portMappingCount, that.portMappingCount)
                    .append(portMappings, that.portMappings)
                    .append(interconnectBayMappingCount, that.interconnectBayMappingCount)
                    .append(interconnectBayMappings, that.interconnectBayMappings)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(associatedLogicalInterconnectGroups)
                .append(ipRangeUris)
                .append(enclosureCount)
                .append(enclosureTypeUri)
                .append(powerMode)
                .append(stackingMode)
                .append(portMappingCount)
                .append(portMappings)
                .append(interconnectBayMappingCount)
                .append(interconnectBayMappings)
                .toHashCode();
    }

    public static enum PowerMode {

        RedundantPowerFeed("RedundantPowerFeed"), RedundantPowerSupply("RedundantPowerSupply");
        private final String value;
        private static Map<String, EnclosureGroup.PowerMode> constants = new HashMap<String, EnclosureGroup.PowerMode>();

        static {
            for (final EnclosureGroup.PowerMode c : values()) {
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

        public static EnclosureGroup.PowerMode fromValue(final String value) {
            final EnclosureGroup.PowerMode constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
