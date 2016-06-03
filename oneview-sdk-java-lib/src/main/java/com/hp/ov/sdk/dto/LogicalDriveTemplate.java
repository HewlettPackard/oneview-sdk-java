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
package com.hp.ov.sdk.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogicalDriveTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean bootable;
    private String driveName;
    private String driveTechnology;
    private Integer numPhysicalDrives;
    private String raidLevel;

    /**
     * @return the bootable
     */
    public Boolean getBootable() {
        return bootable;
    }

    /**
     * @param bootable the bootable to set
     */
    public void setBootable(Boolean bootable) {
        this.bootable = bootable;
    }

    /**
     * @return the driveName
     */
    public String getDriveName() {
        return driveName;
    }

    /**
     * @param driveName the driveName to set
     */
    public void setDriveName(String driveName) {
        this.driveName = driveName;
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
     * @return the raidLevel
     */
    public String getRaidLevel() {
        return raidLevel;
    }

    /**
     * @param raidLevel the raidLevel to set
     */
    public void setRaidLevel(String raidLevel) {
        this.raidLevel = raidLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        LogicalDriveTemplate that = (LogicalDriveTemplate) obj;

        return new EqualsBuilder()
                .append(bootable, that.bootable)
                .append(driveName, that.driveName)
                .append(driveTechnology, that.driveTechnology)
                .append(numPhysicalDrives, that.numPhysicalDrives)
                .append(raidLevel, that.raidLevel)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(bootable)
                .append(driveName)
                .append(driveTechnology)
                .append(numPhysicalDrives)
                .append(raidLevel)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("bootable", bootable)
                .append("driveName", driveName)
                .append("driveTechnology", driveTechnology)
                .append("numPhysicalDrives", numPhysicalDrives)
                .append("raidLevel", raidLevel)
                .toString();
    }

}
