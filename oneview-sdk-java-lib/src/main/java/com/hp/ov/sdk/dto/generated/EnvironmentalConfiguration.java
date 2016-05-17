/*
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
 */
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public final class EnvironmentalConfiguration implements Serializable {

    private static final long serialVersionUID = -5811385306944745581L;

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
     */
    private Integer calibratedMaxPower;

    /**
     * Resource supports power cap value history. (Required)
     */
    private Boolean capHistorySupported;

    /**
     * The height of the device in u-slots (-1 for unspecified). (Required)
     */
    private Integer height;

    /**
     * Number of history samples maintained by the device. For example, 288
     * samples at 5 minute intervals cover 24 hours. (Required)
     */
    private Integer historyBufferSize;

    /**
     * Number of seconds in a power history sample interval. Typically 300
     * seconds (5 minutes). (Required)
     */
    private Integer historySampleIntervalSeconds;

    /**
     * Minimum power consumption seen (in Watts), 0 if unknown. The minimum
     * power consumption occurs when the device is in idle state. (Required)
     */
    private Integer idleMaxPower;

    /**
     * Reflects any known missing license requirement that prevents access to
     * environmental monitoring features of the resource. If the value is None,
     * then there are no known issues preventing access to environmental
     * monitoring features of the resource. If the value is iLOAdvanced, then
     * the server hardware must have an iLOAdvanced license applied to
     * environmental monitoring features. If the value is OneView, then it
     * indicates that there are insufficient HPE OneView licenses available to
     * the appliance to enable environmental management features on the
     * resource. (Required)
     */
    private LicenseRequirement licenseRequirement;

    /**
     * The type of power capping supported by this device.
     */
    private PowerCapType powerCapType;

    /**
     * Resource supports monitoring and retrieval of power consumption history
     * data. Lack of power consumption history also implies that the
     * calibratedMaxPower cannot be automatically calculated. (Required)
     */
    private Boolean powerHistorySupported;

    /**
     * The list of configuration data for each power supply of the device.
     */
    private List<PsuList> psuList = new ArrayList<>();

    /**
     * The GUID of the containing rack if available; otherwise null.
     */
    private String rackId;

    /**
     * The model of the Rack in which this device is mounted.
     */
    private String rackModel;

    /**
     * The rack name associated to this device (null for unspecified).
     */
    private String rackName;

    /**
     * The U height of the rack in which this device is mounted.
     */
    private Integer rackUHeight;

    /**
     * The relative order of a resource in a Rack when slot information cannot
     * be discovered and is not yet configured. This information is derived from
     * the management link cable connections in a BladeSystem enclosure. The
     * values increase from top to bottom, the lowest number (one at top of
     * rack) to highest number (bottom of rack). A value of -1 indicates
     * unspecified. (Required)
     */
    private Integer relativeOrder;

    /**
     * Resource supports ambient temperature history reporting. (Required)
     */
    private Boolean thermalHistorySupported;

    /**
     * The top-u-slot number of the device in the associated rack if available;
     * -1 otherwise. (Required)
     */
    private Integer uSlot;

    /**
     * Resource supports CPU utilization history. (Required)
     */
    private Boolean utilizationHistorySupported;

    public Integer getCalibratedMaxPower() {
        return calibratedMaxPower;
    }

    public void setCalibratedMaxPower(Integer calibratedMaxPower) {
        this.calibratedMaxPower = calibratedMaxPower;
    }

    public Boolean getCapHistorySupported() {
        return capHistorySupported;
    }

    public void setCapHistorySupported(Boolean capHistorySupported) {
        this.capHistorySupported = capHistorySupported;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getHistoryBufferSize() {
        return historyBufferSize;
    }

    public void setHistoryBufferSize(Integer historyBufferSize) {
        this.historyBufferSize = historyBufferSize;
    }

    public Integer getHistorySampleIntervalSeconds() {
        return historySampleIntervalSeconds;
    }

    public void setHistorySampleIntervalSeconds(Integer historySampleIntervalSeconds) {
        this.historySampleIntervalSeconds = historySampleIntervalSeconds;
    }

    public Integer getIdleMaxPower() {
        return idleMaxPower;
    }

    public void setIdleMaxPower(Integer idleMaxPower) {
        this.idleMaxPower = idleMaxPower;
    }

    public LicenseRequirement getLicenseRequirement() {
        return licenseRequirement;
    }

    public void setLicenseRequirement(LicenseRequirement licenseRequirement) {
        this.licenseRequirement = licenseRequirement;
    }

    public PowerCapType getPowerCapType() {
        return powerCapType;
    }

    public void setPowerCapType(PowerCapType powerCapType) {
        this.powerCapType = powerCapType;
    }

    public Boolean getPowerHistorySupported() {
        return powerHistorySupported;
    }

    public void setPowerHistorySupported(Boolean powerHistorySupported) {
        this.powerHistorySupported = powerHistorySupported;
    }

    public List<PsuList> getPsuList() {
        return psuList;
    }

    public void setPsuList(List<PsuList> psuList) {
        this.psuList = psuList;
    }

    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    public String getRackModel() {
        return rackModel;
    }

    public void setRackModel(String rackModel) {
        this.rackModel = rackModel;
    }

    public String getRackName() {
        return rackName;
    }

    public void setRackName(String rackName) {
        this.rackName = rackName;
    }

    public Integer getRackUHeight() {
        return rackUHeight;
    }

    public void setRackUHeight(Integer rackUHeight) {
        this.rackUHeight = rackUHeight;
    }

    public Integer getRelativeOrder() {
        return relativeOrder;
    }

    public void setRelativeOrder(Integer relativeOrder) {
        this.relativeOrder = relativeOrder;
    }

    public Boolean getThermalHistorySupported() {
        return thermalHistorySupported;
    }

    public void setThermalHistorySupported(Boolean thermalHistorySupported) {
        this.thermalHistorySupported = thermalHistorySupported;
    }

    public Integer getuSlot() {
        return uSlot;
    }

    public void setuSlot(Integer uSlot) {
        this.uSlot = uSlot;
    }

    public Boolean getUtilizationHistorySupported() {
        return utilizationHistorySupported;
    }

    public void setUtilizationHistorySupported(Boolean utilizationHistorySupported) {
        this.utilizationHistorySupported = utilizationHistorySupported;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        EnvironmentalConfiguration that = (EnvironmentalConfiguration) obj;

        return new EqualsBuilder()
                .append(calibratedMaxPower, that.calibratedMaxPower)
                .append(capHistorySupported, that.capHistorySupported)
                .append(height, that.height)
                .append(historyBufferSize, that.historyBufferSize)
                .append(historySampleIntervalSeconds, that.historySampleIntervalSeconds)
                .append(idleMaxPower, that.idleMaxPower)
                .append(licenseRequirement, that.licenseRequirement)
                .append(powerCapType, that.powerCapType)
                .append(powerHistorySupported, that.powerHistorySupported)
                .append(psuList, that.psuList)
                .append(rackId, that.rackId)
                .append(rackModel, that.rackModel)
                .append(rackName, that.rackName)
                .append(rackUHeight, that.rackUHeight)
                .append(relativeOrder, that.relativeOrder)
                .append(thermalHistorySupported, that.thermalHistorySupported)
                .append(uSlot, that.uSlot)
                .append(utilizationHistorySupported, that.utilizationHistorySupported)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(calibratedMaxPower)
                .append(capHistorySupported)
                .append(height)
                .append(historyBufferSize)
                .append(historySampleIntervalSeconds)
                .append(idleMaxPower)
                .append(licenseRequirement)
                .append(powerCapType)
                .append(powerHistorySupported)
                .append(psuList)
                .append(rackId)
                .append(rackModel)
                .append(rackName)
                .append(rackUHeight)
                .append(relativeOrder)
                .append(thermalHistorySupported)
                .append(uSlot)
                .append(utilizationHistorySupported)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("calibratedMaxPower", calibratedMaxPower)
                .append("capHistorySupported", capHistorySupported)
                .append("height", height)
                .append("historyBufferSize", historyBufferSize)
                .append("historySampleIntervalSeconds", historySampleIntervalSeconds)
                .append("idleMaxPower", idleMaxPower)
                .append("licenseRequirement", licenseRequirement)
                .append("powerCapType", powerCapType)
                .append("powerHistorySupported", powerHistorySupported)
                .append("psuList", psuList)
                .append("rackId", rackId)
                .append("rackModel", rackModel)
                .append("rackName", rackName)
                .append("rackUHeight", rackUHeight)
                .append("relativeOrder", relativeOrder)
                .append("thermalHistorySupported", thermalHistorySupported)
                .append("uSlot", uSlot)
                .append("utilizationHistorySupported", utilizationHistorySupported)
                .toString();
    }
}
