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
package com.hp.ov.sdk.dto.servers.serverprofile;

import java.io.Serializable;

public class AvailableStorageVolume implements Serializable {

    private static final long serialVersionUID = 1L;

    private String volumeName;
    private String volumeUri;
    private String poolName;
    private String poolUri;
    private String raidLevel;
    private Boolean shareable;
    private String capacityBytes;
    private String provisionType;

    /**
     * @return the volumeName
     */
    public String getVolumeName() {
        return volumeName;
    }
    /**
     * @param volumeName the volumeName to set
     */
    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }
    /**
     * @return the volumeUri
     */
    public String getVolumeUri() {
        return volumeUri;
    }
    /**
     * @param volumeUri the volumeUri to set
     */
    public void setVolumeUri(String volumeUri) {
        this.volumeUri = volumeUri;
    }
    /**
     * @return the poolName
     */
    public String getPoolName() {
        return poolName;
    }
    /**
     * @param poolName the poolName to set
     */
    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }
    /**
     * @return the poolUri
     */
    public String getPoolUri() {
        return poolUri;
    }
    /**
     * @param poolUri the poolUri to set
     */
    public void setPoolUri(String poolUri) {
        this.poolUri = poolUri;
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
    /**
     * @return the shareable
     */
    public Boolean getShareable() {
        return shareable;
    }
    /**
     * @param shareable the shareable to set
     */
    public void setShareable(Boolean shareable) {
        this.shareable = shareable;
    }
    /**
     * @return the capacityBytes
     */
    public String getCapacityBytes() {
        return capacityBytes;
    }
    /**
     * @param capacityBytes the capacityBytes to set
     */
    public void setCapacityBytes(String capacityBytes) {
        this.capacityBytes = capacityBytes;
    }
    /**
     * @return the provisionType
     */
    public String getProvisionType() {
        return provisionType;
    }
    /**
     * @param provisionType the provisionType to set
     */
    public void setProvisionType(String provisionType) {
        this.provisionType = provisionType;
    }


}
