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
import com.hp.ov.sdk.dto.Location;
import com.hp.ov.sdk.dto.SasPort;

public class DriveEnclosureIOAdapter extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String firmwareVersion;
    private Location ioAdapterLocation;
    private String manufacturer;
    private String model;
    private String partNumber;
    private int portCount;
    private List<SasPort> ports = new ArrayList<SasPort>();
    private String redundantIoModule;
    private String serialNumber;
    private String wwid;

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
     * @return the ioAdapterLocation
     */
    public Location getIoAdapterLocation() {
        return ioAdapterLocation;
    }

    /**
     * @param ioAdapterLocation the ioAdapterLocation to set
     */
    public void setIoAdapterLocation(Location ioAdapterLocation) {
        this.ioAdapterLocation = ioAdapterLocation;
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
     * @return the portCount
     */
    public int getPortCount() {
        return portCount;
    }

    /**
     * @param portCount the portCount to set
     */
    public void setPortCount(int portCount) {
        this.portCount = portCount;
    }

    /**
     * @return the ports
     */
    public List<SasPort> getPorts() {
        return ports;
    }

    /**
     * @param ports the ports to set
     */
    public void setPorts(List<SasPort> ports) {
        this.ports = ports;
    }

    /**
     * @return the redundantIoModule
     */
    public String getRedundantIoModule() {
        return redundantIoModule;
    }

    /**
     * @param redundantIoModule the redundantIoModule to set
     */
    public void setRedundantIoModule(String redundantIoModule) {
        this.redundantIoModule = redundantIoModule;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
