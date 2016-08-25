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

package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.HealthStatus;

public class LogicalJbod implements Serializable {

    private static final long serialVersionUID = 5079977361703726760L;

    private String deviceSlot;
    private Integer driveMaxSizeGB;
    private Integer driveMinSizeGB;
    private String driveTechnology;
    private Integer id;
    private String name;
    private Integer numPhysicalDrives;
    private String sasLogicalJBODUri;
    private HealthStatus status;

    /**
     * @return the deviceSlot
     */
    public String getDeviceSlot() {
        return deviceSlot;
    }

    /**
     * @param deviceSlot the deviceSlot to set
     */
    public void setDeviceSlot(String deviceSlot) {
        this.deviceSlot = deviceSlot;
    }

    /**
     * @return the driveMaxSizeGB
     */
    public Integer getDriveMaxSizeGB() {
        return driveMaxSizeGB;
    }

    /**
     * @param driveMaxSizeGB the driveMaxSizeGB to set
     */
    public void setDriveMaxSizeGB(Integer driveMaxSizeGB) {
        this.driveMaxSizeGB = driveMaxSizeGB;
    }

    /**
     * @return the driveMinSizeGB
     */
    public Integer getDriveMinSizeGB() {
        return driveMinSizeGB;
    }

    /**
     * @param driveMinSizeGB the driveMinSizeGB to set
     */
    public void setDriveMinSizeGB(Integer driveMinSizeGB) {
        this.driveMinSizeGB = driveMinSizeGB;
    }

    /**
     * @return the driveTechnology
     */
    public String getDriveTechnology() {
        return driveTechnology;
    }

    /**
     * @param driveTechnology the driveTechnology to set
     */
    public void setDriveTechnology(String driveTechnology) {
        this.driveTechnology = driveTechnology;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the numPhysicalDrives
     */
    public Integer getNumPhysicalDrives() {
        return numPhysicalDrives;
    }

    /**
     * @param numPhysicalDrives the numPhysicalDrives to set
     */
    public void setNumPhysicalDrives(Integer numPhysicalDrives) {
        this.numPhysicalDrives = numPhysicalDrives;
    }

    /**
     * @return the sasLogicalJBODUri
     */
    public String getSasLogicalJBODUri() {
        return sasLogicalJBODUri;
    }

    /**
     * @param sasLogicalJBODUri the sasLogicalJBODUri to set
     */
    public void setSasLogicalJBODUri(String sasLogicalJBODUri) {
        this.sasLogicalJBODUri = sasLogicalJBODUri;
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
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
