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
package com.hp.ov.sdk.dto.storage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.networking.Location;

public class Drive extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String authentic;
    private Integer blockSize;
    private String capacity;
    private DeviceInterface deviceInterface;
    private Location driveLocation;
    private DriveMedia driveMedia;
    private List<String> drivePaths = new ArrayList<>();
    private String firmwareVersion;
    private Integer linkRateInGbs;
    private String model;
    private RefreshState refreshState;
    private Integer rotationalRpms;
    private String serialNumber;
    private String stateReason;
    private Integer temperature;
    private String wwid;

    /**
     * @return the authentic
     */
    public String getAuthentic() {
        return authentic;
    }

    /**
     * @param authentic the authentic to set
     */
    public void setAuthentic(String authentic) {
        this.authentic = authentic;
    }

    /**
     * @return the blockSize
     */
    public Integer getBlockSize() {
        return blockSize;
    }

    /**
     * @param blockSize the blockSize to set
     */
    public void setBlockSize(Integer blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * @return the capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

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
     * @return the driveLocation
     */
    public Location getDriveLocation() {
        return driveLocation;
    }

    /**
     * @param driveLocation the driveLocation to set
     */
    public void setDriveLocation(Location driveLocation) {
        this.driveLocation = driveLocation;
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

    /**
     * @return the drivePaths
     */
    public List<String> getDrivePaths() {
        return drivePaths;
    }

    /**
     * @param drivePaths the drivePaths to set
     */
    public void setDrivePaths(List<String> drivePaths) {
        this.drivePaths = drivePaths;
    }

    /**
     * @return the firmwareVersion
     */
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    /**
     * @param firmwareVersion the firmwareVersion to set
     */
    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    /**
     * @return the linkRateInGbs
     */
    public Integer getLinkRateInGbs() {
        return linkRateInGbs;
    }

    /**
     * @param linkRateInGbs the linkRateInGbs to set
     */
    public void setLinkRateInGbs(Integer linkRateInGbs) {
        this.linkRateInGbs = linkRateInGbs;
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
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    /**
     * @return the rotationalRpms
     */
    public Integer getRotationalRpms() {
        return rotationalRpms;
    }

    /**
     * @param rotationalRpms the rotationalRpms to set
     */
    public void setRotationalRpms(Integer rotationalRpms) {
        this.rotationalRpms = rotationalRpms;
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
     * @return the stateReason
     */
    public String getStateReason() {
        return stateReason;
    }

    /**
     * @param stateReason the stateReason to set
     */
    public void setStateReason(String stateReason) {
        this.stateReason = stateReason;
    }

    /**
     * @return the temperature
     */
    public Integer getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the wwid
     */
    public String getWwid() {
        return wwid;
    }

    /**
     * @param wwid the wwid to set
     */
    public void setWwid(String wwid) {
        this.wwid = wwid;
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
