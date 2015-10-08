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
@JsonPropertyOrder({ "powerHistorySupported", "thermalHistorySupported", "utilizationHistorySupported", "capHistorySupported",
        "historySampleIntervalSeconds", "historyBufferSize", "powerCapType", "idleMaxPower", "calibratedMaxPower", "psuList",
        "height", "rackId", "uSlot", "rackName", "relativeOrder", "rackModel", "rackUHeight", "licenseRequirement" })
public class EnvironmentalConfiguration {

    /**
     * Resource supports monitoring and retrieval of power consumption history
     * data. Lack of power consumption history also implies that the
     * calibratedMaxPower cannot be automatically calculated. (Required)
     * 
     */
    @JsonProperty("powerHistorySupported")
    private Boolean powerHistorySupported;
    /**
     * Resource supports ambient temperature history reporting. (Required)
     * 
     */
    @JsonProperty("thermalHistorySupported")
    private Boolean thermalHistorySupported;
    /**
     * Resource supports CPU utilization history. (Required)
     * 
     */
    @JsonProperty("utilizationHistorySupported")
    private Boolean utilizationHistorySupported;
    /**
     * Resource supports power cap value history. (Required)
     * 
     */
    @JsonProperty("capHistorySupported")
    private Boolean capHistorySupported;
    /**
     * Number of seconds in a power history sample interval. Typically 300
     * seconds (5 minutes). (Required)
     * 
     */
    @JsonProperty("historySampleIntervalSeconds")
    private Integer historySampleIntervalSeconds;
    /**
     * Number of history samples maintained by the device. For example, 288
     * samples at 5 minute intervals cover 24 hours. (Required)
     * 
     */
    @JsonProperty("historyBufferSize")
    private Integer historyBufferSize;
    /**
     * The type of power capping supported by this device.
     * 
     */
    @JsonProperty("powerCapType")
    private EnvironmentalConfiguration.PowerCapType powerCapType;
    /**
     * Minimum power consumption seen (in Watts), 0 if unknown. The minimum
     * power consumption occurs when the device is in idle state. (Required)
     * 
     */
    @JsonProperty("idleMaxPower")
    private Integer idleMaxPower;
    /**
     * The calibrated maximum power. Calibrated Maximum Power is defined as the
     * maximum potential power that the device can consume, subject to the
     * following requirements and constraints: 1. The value reported MUST be the
     * maximum which can be sustained for greater than 1/2 second (i.e., in-rush
     * currents and other spikes that may persist for less than a 1/2 second are
     * not to be included). 2. The value reported MUST represent the maximum
     * total AC input across all power supplies 3. The value reported MUST
     * represent the maximum AC input the device can sustain as configured at
     * the time this metric is reported. If additional components are added
     * later or if it is discovered at a later time that more power can be used,
     * the larger number MUST be reported when the device is next queried for
     * this metric. 4. The value reported does not represent potential input
     * power in the case of error conditions such as short circuits. 5. The
     * actual power used by the device MUST NOT exceed the reported Calibrated
     * Maximum Power by greater than 1%. 6. The Calibrated Maximum Power SHOULD
     * NOT exceed the actual maximum power that the device is capable of using
     * by more than 5%. (Required)
     * 
     */
    @JsonProperty("calibratedMaxPower")
    private Integer calibratedMaxPower;
    /**
     * The list of configuration data for each power supply of the device.
     * 
     */
    @JsonProperty("psuList")
    private List<PsuList> psuList = new ArrayList<PsuList>();
    /**
     * The height of the device in u-slots (-1 for unspecified). (Required)
     * 
     */
    @JsonProperty("height")
    private Integer height;
    /**
     * The GUID of the containing rack if available; otherwise null.
     * 
     */
    @JsonProperty("rackId")
    private String rackId;
    /**
     * The top-u-slot number of the device in the associated rack if available;
     * -1 otherwise. (Required)
     * 
     */
    @JsonProperty("uSlot")
    private Integer uSlot;
    /**
     * The rack name associated to this device (null for unspecified).
     * 
     */
    @JsonProperty("rackName")
    private String rackName;
    /**
     * The relative order of a resource in a Rack when slot information cannot
     * be discovered and is not yet configured. This information is derived from
     * the management link cable connections in a BladeSystem enclosure. The
     * values increase from top to bottom, the lowest number (one at top of
     * rack) to highest number (bottom of rack). A value of -1 indicates
     * unspecified. (Required)
     * 
     */
    @JsonProperty("relativeOrder")
    private Integer relativeOrder;
    /**
     * The model of the Rack in which this device is mounted.
     * 
     */
    @JsonProperty("rackModel")
    private String rackModel;
    /**
     * The U height of the rack in which this device is mounted.
     * 
     */
    @JsonProperty("rackUHeight")
    private Integer rackUHeight;
    /**
     * Reflects any known missing license requirement that prevents access to
     * environmental monitoring features of the resource. If the value is None,
     * then there are no known issues preventing access to environmental
     * monitoring features of the resource. If the value is iLOAdvanced, then
     * the server hardware must have an iLOAdvanced license applied to
     * environmental monitoring features. If the value is OneView, then it
     * indicates that there are insufficient HP OneView licenses available to
     * the appliance to enable environmental management features on the
     * resource. (Required)
     * 
     */
    @JsonProperty("licenseRequirement")
    private EnvironmentalConfiguration.LicenseRequirement licenseRequirement;

    /**
     * Resource supports monitoring and retrieval of power consumption history
     * data. Lack of power consumption history also implies that the
     * calibratedMaxPower cannot be automatically calculated. (Required)
     * 
     * @return The powerHistorySupported
     */
    @JsonProperty("powerHistorySupported")
    public Boolean getPowerHistorySupported() {
        return powerHistorySupported;
    }

    /**
     * Resource supports monitoring and retrieval of power consumption history
     * data. Lack of power consumption history also implies that the
     * calibratedMaxPower cannot be automatically calculated. (Required)
     * 
     * @param powerHistorySupported
     *            The powerHistorySupported
     */
    @JsonProperty("powerHistorySupported")
    public void setPowerHistorySupported(final Boolean powerHistorySupported) {
        this.powerHistorySupported = powerHistorySupported;
    }

    /**
     * Resource supports ambient temperature history reporting. (Required)
     * 
     * @return The thermalHistorySupported
     */
    @JsonProperty("thermalHistorySupported")
    public Boolean getThermalHistorySupported() {
        return thermalHistorySupported;
    }

    /**
     * Resource supports ambient temperature history reporting. (Required)
     * 
     * @param thermalHistorySupported
     *            The thermalHistorySupported
     */
    @JsonProperty("thermalHistorySupported")
    public void setThermalHistorySupported(final Boolean thermalHistorySupported) {
        this.thermalHistorySupported = thermalHistorySupported;
    }

    /**
     * Resource supports CPU utilization history. (Required)
     * 
     * @return The utilizationHistorySupported
     */
    @JsonProperty("utilizationHistorySupported")
    public Boolean getUtilizationHistorySupported() {
        return utilizationHistorySupported;
    }

    /**
     * Resource supports CPU utilization history. (Required)
     * 
     * @param utilizationHistorySupported
     *            The utilizationHistorySupported
     */
    @JsonProperty("utilizationHistorySupported")
    public void setUtilizationHistorySupported(final Boolean utilizationHistorySupported) {
        this.utilizationHistorySupported = utilizationHistorySupported;
    }

    /**
     * Resource supports power cap value history. (Required)
     * 
     * @return The capHistorySupported
     */
    @JsonProperty("capHistorySupported")
    public Boolean getCapHistorySupported() {
        return capHistorySupported;
    }

    /**
     * Resource supports power cap value history. (Required)
     * 
     * @param capHistorySupported
     *            The capHistorySupported
     */
    @JsonProperty("capHistorySupported")
    public void setCapHistorySupported(final Boolean capHistorySupported) {
        this.capHistorySupported = capHistorySupported;
    }

    /**
     * Number of seconds in a power history sample interval. Typically 300
     * seconds (5 minutes). (Required)
     * 
     * @return The historySampleIntervalSeconds
     */
    @JsonProperty("historySampleIntervalSeconds")
    public Integer getHistorySampleIntervalSeconds() {
        return historySampleIntervalSeconds;
    }

    /**
     * Number of seconds in a power history sample interval. Typically 300
     * seconds (5 minutes). (Required)
     * 
     * @param historySampleIntervalSeconds
     *            The historySampleIntervalSeconds
     */
    @JsonProperty("historySampleIntervalSeconds")
    public void setHistorySampleIntervalSeconds(final Integer historySampleIntervalSeconds) {
        this.historySampleIntervalSeconds = historySampleIntervalSeconds;
    }

    /**
     * Number of history samples maintained by the device. For example, 288
     * samples at 5 minute intervals cover 24 hours. (Required)
     * 
     * @return The historyBufferSize
     */
    @JsonProperty("historyBufferSize")
    public Integer getHistoryBufferSize() {
        return historyBufferSize;
    }

    /**
     * Number of history samples maintained by the device. For example, 288
     * samples at 5 minute intervals cover 24 hours. (Required)
     * 
     * @param historyBufferSize
     *            The historyBufferSize
     */
    @JsonProperty("historyBufferSize")
    public void setHistoryBufferSize(final Integer historyBufferSize) {
        this.historyBufferSize = historyBufferSize;
    }

    /**
     * The type of power capping supported by this device.
     * 
     * @return The powerCapType
     */
    @JsonProperty("powerCapType")
    public EnvironmentalConfiguration.PowerCapType getPowerCapType() {
        return powerCapType;
    }

    /**
     * The type of power capping supported by this device.
     * 
     * @param powerCapType
     *            The powerCapType
     */
    @JsonProperty("powerCapType")
    public void setPowerCapType(final EnvironmentalConfiguration.PowerCapType powerCapType) {
        this.powerCapType = powerCapType;
    }

    /**
     * Minimum power consumption seen (in Watts), 0 if unknown. The minimum
     * power consumption occurs when the device is in idle state. (Required)
     * 
     * @return The idleMaxPower
     */
    @JsonProperty("idleMaxPower")
    public Integer getIdleMaxPower() {
        return idleMaxPower;
    }

    /**
     * Minimum power consumption seen (in Watts), 0 if unknown. The minimum
     * power consumption occurs when the device is in idle state. (Required)
     * 
     * @param idleMaxPower
     *            The idleMaxPower
     */
    @JsonProperty("idleMaxPower")
    public void setIdleMaxPower(final Integer idleMaxPower) {
        this.idleMaxPower = idleMaxPower;
    }

    /**
     * The calibrated maximum power. Calibrated Maximum Power is defined as the
     * maximum potential power that the device can consume, subject to the
     * following requirements and constraints: 1. The value reported MUST be the
     * maximum which can be sustained for greater than 1/2 second (i.e., in-rush
     * currents and other spikes that may persist for less than a 1/2 second are
     * not to be included). 2. The value reported MUST represent the maximum
     * total AC input across all power supplies 3. The value reported MUST
     * represent the maximum AC input the device can sustain as configured at
     * the time this metric is reported. If additional components are added
     * later or if it is discovered at a later time that more power can be used,
     * the larger number MUST be reported when the device is next queried for
     * this metric. 4. The value reported does not represent potential input
     * power in the case of error conditions such as short circuits. 5. The
     * actual power used by the device MUST NOT exceed the reported Calibrated
     * Maximum Power by greater than 1%. 6. The Calibrated Maximum Power SHOULD
     * NOT exceed the actual maximum power that the device is capable of using
     * by more than 5%. (Required)
     * 
     * @return The calibratedMaxPower
     */
    @JsonProperty("calibratedMaxPower")
    public Integer getCalibratedMaxPower() {
        return calibratedMaxPower;
    }

    /**
     * The calibrated maximum power. Calibrated Maximum Power is defined as the
     * maximum potential power that the device can consume, subject to the
     * following requirements and constraints: 1. The value reported MUST be the
     * maximum which can be sustained for greater than 1/2 second (i.e., in-rush
     * currents and other spikes that may persist for less than a 1/2 second are
     * not to be included). 2. The value reported MUST represent the maximum
     * total AC input across all power supplies 3. The value reported MUST
     * represent the maximum AC input the device can sustain as configured at
     * the time this metric is reported. If additional components are added
     * later or if it is discovered at a later time that more power can be used,
     * the larger number MUST be reported when the device is next queried for
     * this metric. 4. The value reported does not represent potential input
     * power in the case of error conditions such as short circuits. 5. The
     * actual power used by the device MUST NOT exceed the reported Calibrated
     * Maximum Power by greater than 1%. 6. The Calibrated Maximum Power SHOULD
     * NOT exceed the actual maximum power that the device is capable of using
     * by more than 5%. (Required)
     * 
     * @param calibratedMaxPower
     *            The calibratedMaxPower
     */
    @JsonProperty("calibratedMaxPower")
    public void setCalibratedMaxPower(final Integer calibratedMaxPower) {
        this.calibratedMaxPower = calibratedMaxPower;
    }

    /**
     * The list of configuration data for each power supply of the device.
     * 
     * @return The psuList
     */
    @JsonProperty("psuList")
    public List<PsuList> getPsuList() {
        return psuList;
    }

    /**
     * The list of configuration data for each power supply of the device.
     * 
     * @param psuList
     *            The psuList
     */
    @JsonProperty("psuList")
    public void setPsuList(final List<PsuList> psuList) {
        this.psuList = psuList;
    }

    /**
     * The height of the device in u-slots (-1 for unspecified). (Required)
     * 
     * @return The height
     */
    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    /**
     * The height of the device in u-slots (-1 for unspecified). (Required)
     * 
     * @param height
     *            The height
     */
    @JsonProperty("height")
    public void setHeight(final Integer height) {
        this.height = height;
    }

    /**
     * The GUID of the containing rack if available; otherwise null.
     * 
     * @return The rackId
     */
    @JsonProperty("rackId")
    public String getRackId() {
        return rackId;
    }

    /**
     * The GUID of the containing rack if available; otherwise null.
     * 
     * @param rackId
     *            The rackId
     */
    @JsonProperty("rackId")
    public void setRackId(final String rackId) {
        this.rackId = rackId;
    }

    /**
     * The top-u-slot number of the device in the associated rack if available;
     * -1 otherwise. (Required)
     * 
     * @return The uSlot
     */
    @JsonProperty("uSlot")
    public Integer getUSlot() {
        return uSlot;
    }

    /**
     * The top-u-slot number of the device in the associated rack if available;
     * -1 otherwise. (Required)
     * 
     * @param uSlot
     *            The uSlot
     */
    @JsonProperty("uSlot")
    public void setUSlot(final Integer uSlot) {
        this.uSlot = uSlot;
    }

    /**
     * The rack name associated to this device (null for unspecified).
     * 
     * @return The rackName
     */
    @JsonProperty("rackName")
    public String getRackName() {
        return rackName;
    }

    /**
     * The rack name associated to this device (null for unspecified).
     * 
     * @param rackName
     *            The rackName
     */
    @JsonProperty("rackName")
    public void setRackName(final String rackName) {
        this.rackName = rackName;
    }

    /**
     * The relative order of a resource in a Rack when slot information cannot
     * be discovered and is not yet configured. This information is derived from
     * the management link cable connections in a BladeSystem enclosure. The
     * values increase from top to bottom, the lowest number (one at top of
     * rack) to highest number (bottom of rack). A value of -1 indicates
     * unspecified. (Required)
     * 
     * @return The relativeOrder
     */
    @JsonProperty("relativeOrder")
    public Integer getRelativeOrder() {
        return relativeOrder;
    }

    /**
     * The relative order of a resource in a Rack when slot information cannot
     * be discovered and is not yet configured. This information is derived from
     * the management link cable connections in a BladeSystem enclosure. The
     * values increase from top to bottom, the lowest number (one at top of
     * rack) to highest number (bottom of rack). A value of -1 indicates
     * unspecified. (Required)
     * 
     * @param relativeOrder
     *            The relativeOrder
     */
    @JsonProperty("relativeOrder")
    public void setRelativeOrder(final Integer relativeOrder) {
        this.relativeOrder = relativeOrder;
    }

    /**
     * The model of the Rack in which this device is mounted.
     * 
     * @return The rackModel
     */
    @JsonProperty("rackModel")
    public String getRackModel() {
        return rackModel;
    }

    /**
     * The model of the Rack in which this device is mounted.
     * 
     * @param rackModel
     *            The rackModel
     */
    @JsonProperty("rackModel")
    public void setRackModel(final String rackModel) {
        this.rackModel = rackModel;
    }

    /**
     * The U height of the rack in which this device is mounted.
     * 
     * @return The rackUHeight
     */
    @JsonProperty("rackUHeight")
    public Integer getRackUHeight() {
        return rackUHeight;
    }

    /**
     * The U height of the rack in which this device is mounted.
     * 
     * @param rackUHeight
     *            The rackUHeight
     */
    @JsonProperty("rackUHeight")
    public void setRackUHeight(final Integer rackUHeight) {
        this.rackUHeight = rackUHeight;
    }

    /**
     * Reflects any known missing license requirement that prevents access to
     * environmental monitoring features of the resource. If the value is None,
     * then there are no known issues preventing access to environmental
     * monitoring features of the resource. If the value is iLOAdvanced, then
     * the server hardware must have an iLOAdvanced license applied to
     * environmental monitoring features. If the value is OneView, then it
     * indicates that there are insufficient HP OneView licenses available to
     * the appliance to enable environmental management features on the
     * resource. (Required)
     * 
     * @return The licenseRequirement
     */
    @JsonProperty("licenseRequirement")
    public EnvironmentalConfiguration.LicenseRequirement getLicenseRequirement() {
        return licenseRequirement;
    }

    /**
     * Reflects any known missing license requirement that prevents access to
     * environmental monitoring features of the resource. If the value is None,
     * then there are no known issues preventing access to environmental
     * monitoring features of the resource. If the value is iLOAdvanced, then
     * the server hardware must have an iLOAdvanced license applied to
     * environmental monitoring features. If the value is OneView, then it
     * indicates that there are insufficient HP OneView licenses available to
     * the appliance to enable environmental management features on the
     * resource. (Required)
     * 
     * @param licenseRequirement
     *            The licenseRequirement
     */
    @JsonProperty("licenseRequirement")
    public void setLicenseRequirement(final EnvironmentalConfiguration.LicenseRequirement licenseRequirement) {
        this.licenseRequirement = licenseRequirement;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(powerHistorySupported).append(thermalHistorySupported)
                .append(utilizationHistorySupported).append(capHistorySupported).append(historySampleIntervalSeconds)
                .append(historyBufferSize).append(powerCapType).append(idleMaxPower).append(calibratedMaxPower).append(psuList)
                .append(height).append(rackId).append(uSlot).append(rackName).append(relativeOrder).append(rackModel)
                .append(rackUHeight).append(licenseRequirement).toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof EnvironmentalConfiguration) == false) {
            return false;
        }
        final EnvironmentalConfiguration rhs = ((EnvironmentalConfiguration) other);
        return new EqualsBuilder().append(powerHistorySupported, rhs.powerHistorySupported)
                .append(thermalHistorySupported, rhs.thermalHistorySupported)
                .append(utilizationHistorySupported, rhs.utilizationHistorySupported)
                .append(capHistorySupported, rhs.capHistorySupported)
                .append(historySampleIntervalSeconds, rhs.historySampleIntervalSeconds)
                .append(historyBufferSize, rhs.historyBufferSize).append(powerCapType, rhs.powerCapType)
                .append(idleMaxPower, rhs.idleMaxPower).append(calibratedMaxPower, rhs.calibratedMaxPower)
                .append(psuList, rhs.psuList).append(height, rhs.height).append(rackId, rhs.rackId).append(uSlot, rhs.uSlot)
                .append(rackName, rhs.rackName).append(relativeOrder, rhs.relativeOrder).append(rackModel, rhs.rackModel)
                .append(rackUHeight, rhs.rackUHeight).append(licenseRequirement, rhs.licenseRequirement).isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum LicenseRequirement {

        NONE("None"), I_LO_ADVANCED("iLOAdvanced"), ONE_VIEW("OneView");
        private final String value;
        private static Map<String, EnvironmentalConfiguration.LicenseRequirement> constants = new HashMap<String, EnvironmentalConfiguration.LicenseRequirement>();

        static {
            for (final EnvironmentalConfiguration.LicenseRequirement c : values()) {
                constants.put(c.value, c);
            }
        }

        private LicenseRequirement(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static EnvironmentalConfiguration.LicenseRequirement fromValue(final String value) {
            final EnvironmentalConfiguration.LicenseRequirement constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum PowerCapType {

        NONE("None"), THERMAL("Thermal"), ELECTRICAL("Electrical");
        private final String value;
        private static Map<String, EnvironmentalConfiguration.PowerCapType> constants = new HashMap<String, EnvironmentalConfiguration.PowerCapType>();

        static {
            for (final EnvironmentalConfiguration.PowerCapType c : values()) {
                constants.put(c.value, c);
            }
        }

        private PowerCapType(final String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static EnvironmentalConfiguration.PowerCapType fromValue(final String value) {
            final EnvironmentalConfiguration.PowerCapType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
