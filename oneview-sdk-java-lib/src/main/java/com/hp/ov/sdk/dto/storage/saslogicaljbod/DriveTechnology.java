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

package com.hp.ov.sdk.dto.storage.saslogicaljbod;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.storage.DeviceInterface;
import com.hp.ov.sdk.dto.storage.DriveMedia;

public class DriveTechnology implements Serializable {

    private static final long serialVersionUID = -4952515089418718441L;

    private DeviceInterface deviceInterface;
    private DriveMedia driveMedia;

    /**
     * @return the deviceInterface
     */
    public DeviceInterface getDeviceInterface() {
        return deviceInterface;
    }

    /**
     * @param deviceInterface the deviceInterface to set
     */
    public void setDeviceInterface(DeviceInterface deviceInterface) {
        this.deviceInterface = deviceInterface;
    }

    /**
     * @return the driveMedia
     */
    public DriveMedia getDriveMedia() {
        return driveMedia;
    }

    /**
     * @param driveMedia the driveMedia to set
     */
    public void setDriveMedia(DriveMedia driveMedia) {
        this.driveMedia = driveMedia;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
