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
package com.hp.ov.sdk.dto.servers.enclosure;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Composer implements Serializable {

    private static final long serialVersionUID = 1L;

    private int bayNumber;
    private BayPowerState bayPowerState;
    private DevicePresence devicePresence;
    private String model;
    private String partNumber;
    private Boolean poweredOn;
    private String serialNumber;
    private String sparePartNumber;
    private HealthStatus status;

    /**
     * @return the bayNumber
     */
    public int getBayNumber() {
        return bayNumber;
    }

    /**
     * @param bayNumber the bayNumber to set
     */
    public void setBayNumber(int bayNumber) {
        this.bayNumber = bayNumber;
    }

    /**
     * @return the bayPowerState
     */
    public BayPowerState getBayPowerState() {
        return bayPowerState;
    }

    /**
     * @param bayPowerState the bayPowerState to set
     */
    public void setBayPowerState(BayPowerState bayPowerState) {
        this.bayPowerState = bayPowerState;
    }

    /**
     * @return the devicePresence
     */
    public DevicePresence getDevicePresence() {
        return devicePresence;
    }

    /**
     * @param devicePresence the devicePresence to set
     */
    public void setDevicePresence(DevicePresence devicePresence) {
        this.devicePresence = devicePresence;
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
     * @return the poweredOn
     */
    public Boolean getPoweredOn() {
        return poweredOn;
    }

    /**
     * @param poweredOn the poweredOn to set
     */
    public void setPoweredOn(Boolean poweredOn) {
        this.poweredOn = poweredOn;
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
     * @return the sparePartNumber
     */
    public String getSparePartNumber() {
        return sparePartNumber;
    }

    /**
     * @param sparePartNumber the sparePartNumber to set
     */
    public void setSparePartNumber(String sparePartNumber) {
        this.sparePartNumber = sparePartNumber;
    }

    /**
     * @return the status
     */
    public HealthStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HealthStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Composer) == false) {
            return false;
        }
        final Composer rhs = ((Composer) other);
        return new EqualsBuilder()
                .append(bayNumber, rhs.bayNumber)
                .append(bayPowerState, rhs.bayPowerState)
                .append(devicePresence, rhs.devicePresence)
                .append(model, rhs.model)
                .append(partNumber, rhs.partNumber)
                .append(poweredOn, rhs.poweredOn)
                .append(serialNumber, rhs.serialNumber)
                .append(sparePartNumber, rhs.sparePartNumber)
                .append(status, rhs.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bayNumber)
                .append(bayPowerState)
                .append(devicePresence)
                .append(model)
                .append(partNumber)
                .append(poweredOn)
                .append(serialNumber)
                .append(sparePartNumber)
                .append(status)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
