/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.facilities.powerdeliverydevice;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class PowerDeliveryDevice extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Integer coreId;
    private PowerDeliveryDeviceType deviceType;
    private String feedIdentifier;
    private String id;
    private Integer lineVoltage;
    private Integer loadSegmentId;
    private ManagementModule managedBy;
    private String model;
    private Integer outletId;
    private String partNumber;
    private PhaseType phaseType;
    private List<PowerConnection> powerConnections;
    private Integer ratedCapacity;
    private String serialNumber;
    private String uuid;

    /**
     * @return the coreId
     */
    public Integer getCoreId() {
        return coreId;
    }

    /**
     * @param coreId the coreId to set
     */
    public void setCoreId(Integer coreId) {
        this.coreId = coreId;
    }

    /**
     * @return the deviceType
     */
    public PowerDeliveryDeviceType getDeviceType() {
        return deviceType;
    }

    /**
     * @param deviceType the deviceType to set
     */
    public void setDeviceType(PowerDeliveryDeviceType deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * @return the feedIdentifier
     */
    public String getFeedIdentifier() {
        return feedIdentifier;
    }

    /**
     * @param feedIdentifier the feedIdentifier to set
     */
    public void setFeedIdentifier(String feedIdentifier) {
        this.feedIdentifier = feedIdentifier;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the lineVoltage
     */
    public Integer getLineVoltage() {
        return lineVoltage;
    }

    /**
     * @param lineVoltage the lineVoltage to set
     */
    public void setLineVoltage(Integer lineVoltage) {
        this.lineVoltage = lineVoltage;
    }

    /**
     * @return the loadSegmentId
     */
    public Integer getLoadSegmentId() {
        return loadSegmentId;
    }

    /**
     * @param loadSegmentId the loadSegmentId to set
     */
    public void setLoadSegmentId(Integer loadSegmentId) {
        this.loadSegmentId = loadSegmentId;
    }

    /**
     * @return the managedBy
     */
    public ManagementModule getManagedBy() {
        return managedBy;
    }

    /**
     * @param managedBy the managedBy to set
     */
    public void setManagedBy(ManagementModule managedBy) {
        this.managedBy = managedBy;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the outletId
     */
    public Integer getOutletId() {
        return outletId;
    }

    /**
     * @param outletId the outletId to set
     */
    public void setOutletId(Integer outletId) {
        this.outletId = outletId;
    }

    /**
     * @return the partNumber
     */
    public String getPartNumber() {
        return partNumber;
    }

    /**
     * @param partNumber the partNumber to set
     */
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    /**
     * @return the phaseType
     */
    public PhaseType getPhaseType() {
        return phaseType;
    }

    /**
     * @param phaseType the phaseType to set
     */
    public void setPhaseType(PhaseType phaseType) {
        this.phaseType = phaseType;
    }

    /**
     * @return the powerConnections
     */
    public List<PowerConnection> getPowerConnections() {
        return powerConnections;
    }

    /**
     * @param powerConnections the powerConnections to set
     */
    public void setPowerConnections(List<PowerConnection> powerConnections) {
        this.powerConnections = powerConnections;
    }

    /**
     * @return the ratedCapacity
     */
    public Integer getRatedCapacity() {
        return ratedCapacity;
    }

    /**
     * @param ratedCapacity the ratedCapacity to set
     */
    public void setRatedCapacity(Integer ratedCapacity) {
        this.ratedCapacity = ratedCapacity;
    }

    /**
     * @return the serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * @param serialNumber the serialNumber to set
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof PowerDeliveryDevice) {
            PowerDeliveryDevice that = (PowerDeliveryDevice) obj;

            return that.canEqual(this) && new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(coreId, that.coreId)
                    .append(deviceType, that.deviceType)
                    .append(feedIdentifier, that.feedIdentifier)
                    .append(id, that.id)
                    .append(lineVoltage, that.lineVoltage)
                    .append(loadSegmentId, that.loadSegmentId)
                    .append(managedBy, that.managedBy)
                    .append(model, that.model)
                    .append(outletId, that.outletId)
                    .append(partNumber, that.partNumber)
                    .append(phaseType, that.phaseType)
                    .append(powerConnections, that.powerConnections)
                    .append(ratedCapacity, that.ratedCapacity)
                    .append(serialNumber, that.serialNumber)
                    .append(uuid, that.uuid)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(coreId)
                .append(deviceType)
                .append(feedIdentifier)
                .append(id)
                .append(lineVoltage)
                .append(loadSegmentId)
                .append(managedBy)
                .append(model)
                .append(outletId)
                .append(partNumber)
                .append(phaseType)
                .append(powerConnections)
                .append(ratedCapacity)
                .append(serialNumber)
                .append(uuid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("coreId", coreId)
                .append("deviceType", deviceType)
                .append("feedIdentifier", feedIdentifier)
                .append("id", id)
                .append("lineVoltage", lineVoltage)
                .append("loadSegmentId", loadSegmentId)
                .append("managedBy", managedBy)
                .append("model", model)
                .append("outletId", outletId)
                .append("partNumber", partNumber)
                .append("phaseType", phaseType)
                .append("powerConnections", powerConnections)
                .append("ratedCapacity", ratedCapacity)
                .append("serialNumber", serialNumber)
                .append("uuid", uuid)
                .toString();
    }

}
