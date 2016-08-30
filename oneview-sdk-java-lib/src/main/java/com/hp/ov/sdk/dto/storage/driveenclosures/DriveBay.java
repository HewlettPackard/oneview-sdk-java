/*
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
 */
package com.hp.ov.sdk.dto.storage.driveenclosures;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.storage.DeviceInterface;
import com.hp.ov.sdk.dto.storage.Drive;

public class DriveBay extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private DeviceInterface attachedDeviceInterface;
    private String attachedDeviceWWID;
    private Drive drive;
    private Location driveBayLocation;
    private String model;

    /**
     * @return the attachedDeviceInterface
     */
    public DeviceInterface getAttachedDeviceInterface() {
        return attachedDeviceInterface;
    }

    /**
     * @param attachedDeviceInterface the attachedDeviceInterface to set
     */
    public void setAttachedDeviceInterface(DeviceInterface attachedDeviceInterface) {
        this.attachedDeviceInterface = attachedDeviceInterface;
    }

    /**
     * @return the attachedDeviceWWID
     */
    public String getAttachedDeviceWWID() {
        return attachedDeviceWWID;
    }

    /**
     * @param attachedDeviceWWID the attachedDeviceWWID to set
     */
    public void setAttachedDeviceWWID(String attachedDeviceWWID) {
        this.attachedDeviceWWID = attachedDeviceWWID;
    }

    /**
     * @return the drive
     */
    public Drive getDrive() {
        return drive;
    }

    /**
     * @param drive the drive to set
     */
    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    /**
     * @return the driveBayLocation
     */
    public Location getDriveBayLocation() {
        return driveBayLocation;
    }

    /**
     * @param driveBayLocation the driveBayLocation to set
     */
    public void setDriveBayLocation(Location driveBayLocation) {
        this.driveBayLocation = driveBayLocation;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
