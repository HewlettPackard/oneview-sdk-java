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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.HardResetState;
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.PowerState;
import com.hp.ov.sdk.dto.RefreshState;
import com.hp.ov.sdk.dto.UIDState;

public class DriveEnclosure extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Integer bay;
    private int driveBayCount;
    private List<DriveBay> driveBays = new ArrayList<DriveBay>();
    private Location driveEnclosureLocation;
    private DriveEnclosurePortMap driveEnclosurePortMap;
    private String enclosureName;
    private String enclosureUri;
    private String firmwareVersion;
    private HardResetState hardResetState;
    private Integer interconnectBaySet;
    private List<String> interconnectUri;
    private int ioAdapterCount;
    private List<DriveEnclosureIOAdapter> ioAdapters = new ArrayList<DriveEnclosureIOAdapter>();
    private String manufacturer;
    private String model;
    private String partNumber;
    private PowerState powerState;
    private String productName;
    private RefreshState refreshState;
    private String serialNumber;
    private String stateReason;
    private Integer temperature;
    private UIDState uidState;
    private String wwid;

    /**
     * @return the bay
     */
    public Integer getBay() {
        return bay;
    }

    /**
     * @param bay the bay to set
     */
    public void setBay(Integer bay) {
        this.bay = bay;
    }

    /**
     * @return the driveBayCount
     */
    public int getDriveBayCount() {
        return driveBayCount;
    }

    /**
     * @param driveBayCount the driveBayCount to set
     */
    public void setDriveBayCount(int driveBayCount) {
        this.driveBayCount = driveBayCount;
    }

    /**
     * @return the driveBays
     */
    public List<DriveBay> getDriveBays() {
        return driveBays;
    }

    /**
     * @param driveBays the driveBays to set
     */
    public void setDriveBays(List<DriveBay> driveBays) {
        this.driveBays = driveBays;
    }

    /**
     * @return the driveEnclosureLocation
     */
    public Location getDriveEnclosureLocation() {
        return driveEnclosureLocation;
    }

    /**
     * @param driveEnclosureLocation the driveEnclosureLocation to set
     */
    public void setDriveEnclosureLocation(Location driveEnclosureLocation) {
        this.driveEnclosureLocation = driveEnclosureLocation;
    }

    /**
     * @return the driveEnclosurePortMap
     */
    public DriveEnclosurePortMap getDriveEnclosurePortMap() {
        return driveEnclosurePortMap;
    }

    /**
     * @param driveEnclosurePortMap the driveEnclosurePortMap to set
     */
    public void setDriveEnclosurePortMap(DriveEnclosurePortMap driveEnclosurePortMap) {
        this.driveEnclosurePortMap = driveEnclosurePortMap;
    }

    /**
     * @return the enclosureName
     */
    public String getEnclosureName() {
        return enclosureName;
    }

    /**
     * @param enclosureName the enclosureName to set
     */
    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    /**
     * @return the enclosureUri
     */
    public String getEnclosureUri() {
        return enclosureUri;
    }

    /**
     * @param enclosureUri the enclosureUri to set
     */
    public void setEnclosureUri(String enclosureUri) {
        this.enclosureUri = enclosureUri;
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
     * @return the hardResetState
     */
    public HardResetState getHardResetState() {
        return hardResetState;
    }

    /**
     * @param hardResetState the hardResetState to set
     */
    public void setHardResetState(HardResetState hardResetState) {
        this.hardResetState = hardResetState;
    }

    /**
     * @return the interconnectBaySet
     */
    public Integer getInterconnectBaySet() {
        return interconnectBaySet;
    }

    /**
     * @param interconnectBaySet the interconnectBaySet to set
     */
    public void setInterconnectBaySet(Integer interconnectBaySet) {
        this.interconnectBaySet = interconnectBaySet;
    }

    /**
     * @return the interconnectUri
     */
    public List<String> getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * @param interconnectUri the interconnectUri to set
     */
    public void setInterconnectUri(List<String> interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the ioAdapterCount
     */
    public int getIoAdapterCount() {
        return ioAdapterCount;
    }

    /**
     * @param ioAdapterCount the ioAdapterCount to set
     */
    public void setIoAdapterCount(int ioAdapterCount) {
        this.ioAdapterCount = ioAdapterCount;
    }

    /**
     * @return the ioAdapters
     */
    public List<DriveEnclosureIOAdapter> getIoAdapters() {
        return ioAdapters;
    }

    /**
     * @param ioAdapters the ioAdapters to set
     */
    public void setIoAdapters(List<DriveEnclosureIOAdapter> ioAdapters) {
        this.ioAdapters = ioAdapters;
    }

    /**
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * @param manufacturer the manufacturer to set
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
     * @return the powerState
     */
    public PowerState getPowerState() {
        return powerState;
    }

    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(PowerState powerState) {
        this.powerState = powerState;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
     * @return the uidState
     */
    public UIDState getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(UIDState uidState) {
        this.uidState = uidState;
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
