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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;

public class AvailableStorageSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Until(199)
    private List<String> connections;
    @Since(200)
    private List<NetworkId> availableNetworks;
    private String storageSystemName;
    private String storageSystemUri;
    private List<AvailableStorageVolume> volumes;

    /**
     * @return the connections
     */
    public List<String> getConnections() {
        return connections;
    }
    /**
     * @param connections the connections to set
     */
    public void setConnections(List<String> connections) {
        this.connections = connections;
    }
    /**
     * @return the availableNetworks
     */
    public List<NetworkId> getAvailableNetworks() {
        return availableNetworks;
    }
    /**
     * @param availableNetworks the availableNetworks to set
     */
    public void setAvailableNetworks(List<NetworkId> availableNetworks) {
        this.availableNetworks = availableNetworks;
    }
    /**
     * @return the storageSystemName
     */
    public String getStorageSystemName() {
        return storageSystemName;
    }
    /**
     * @param storageSystemName the storageSystemName to set
     */
    public void setStorageSystemName(String storageSystemName) {
        this.storageSystemName = storageSystemName;
    }
    /**
     * @return the storageSystemUri
     */
    public String getStorageSystemUri() {
        return storageSystemUri;
    }
    /**
     * @param storageSystemUri the storageSystemUri to set
     */
    public void setStorageSystemUri(String storageSystemUri) {
        this.storageSystemUri = storageSystemUri;
    }
    /**
     * @return the volumes
     */
    public List<AvailableStorageVolume> getVolumes() {
        return volumes;
    }
    /**
     * @param volumes the volumes to set
     */
    public void setVolumes(List<AvailableStorageVolume> volumes) {
        this.volumes = volumes;
    }

}
