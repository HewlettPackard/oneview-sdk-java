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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.dto.RefreshState;

public class SasLogicalJbod extends BaseModelResource {

    private static final long serialVersionUID = 7087288263683434250L;

    private Boolean clearMetaData;
    private DriveTechnology driveTechnology;
    private List<String> logicalDriveBayUris = new ArrayList<>();
    private Integer maxSizeGB;
    private Integer minSizeGB;
    private Integer numPhysicalDrives;
    private RefreshState refreshState;
    private String sasLogicalInterconnectUri;
    private String stateReason;

    /**
     * @return the clearMetaData
     */
    public Boolean getClearMetaData() {
        return clearMetaData;
    }

    /**
     * @param clearMetaData the clearMetaData to set
     */
    public void setClearMetaData(Boolean clearMetaData) {
        this.clearMetaData = clearMetaData;
    }

    /**
     * @return the driveTechnology
     */
    public DriveTechnology getDriveTechnology() {
        return driveTechnology;
    }

    /**
     * @param driveTechnology the driveTechnology to set
     */
    public void setDriveTechnology(DriveTechnology driveTechnology) {
        this.driveTechnology = driveTechnology;
    }

    /**
     * @return the logicalDriveBayUris
     */
    public List<String> getLogicalDriveBayUris() {
        return logicalDriveBayUris;
    }

    /**
     * @param logicalDriveBayUris the logicalDriveBayUris to set
     */
    public void setLogicalDriveBayUris(List<String> logicalDriveBayUris) {
        this.logicalDriveBayUris = logicalDriveBayUris;
    }

    /**
     * @return the maxSizeGB
     */
    public Integer getMaxSizeGB() {
        return maxSizeGB;
    }

    /**
     * @param maxSizeGB the maxSizeGB to set
     */
    public void setMaxSizeGB(Integer maxSizeGB) {
        this.maxSizeGB = maxSizeGB;
    }

    /**
     * @return the minSizeGB
     */
    public Integer getMinSizeGB() {
        return minSizeGB;
    }

    /**
     * @param minSizeGB the minSizeGB to set
     */
    public void setMinSizeGB(Integer minSizeGB) {
        this.minSizeGB = minSizeGB;
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
     * @return the sasLogicalInterconnectUri
     */
    public String getSasLogicalInterconnectUri() {
        return sasLogicalInterconnectUri;
    }

    /**
     * @param sasLogicalInterconnectUri the sasLogicalInterconnectUri to set
     */
    public void setSasLogicalInterconnectUri(String sasLogicalInterconnectUri) {
        this.sasLogicalInterconnectUri = sasLogicalInterconnectUri;
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

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
