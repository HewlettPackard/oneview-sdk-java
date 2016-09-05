/*
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.servers.serverhardware;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class ServerFirmwareInventory extends BaseModelResource {

    private static final long serialVersionUID = 1L;
    
    private List<FirmwareComponent> components = new ArrayList<>();
    private String serverHardwareUri;
    private String serverModel;
    private String serverName;
    private ServerFirmwareInventoryStatus stateFirmware;
    
    /**
     * @return the components
     */
    public List<FirmwareComponent> getComponents() {
        return components;
    }

    /**
     * @param components the components to set
     */
    public void setComponents(List<FirmwareComponent> components) {
        this.components = components;
    }

    /**
     * @return the serverHardwareUri
     */
    public String getServerHardwareUri() {
        return serverHardwareUri;
    }

    /**
     * @param serverHardwareUri the serverHardwareUri to set
     */
    public void setServerHardwareUri(String serverHardwareUri) {
        this.serverHardwareUri = serverHardwareUri;
    }

    /**
     * @return the serverModel
     */
    public String getServerModel() {
        return serverModel;
    }

    /**
     * @param serverModel the serverModel to set
     */
    public void setServerModel(String serverModel) {
        this.serverModel = serverModel;
    }

    /**
     * @return the serverName
     */
    public String getServerName() {
        return serverName;
    }

    /**
     * @param serverName the serverName to set
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    /**
     * @return the stateFirmware
     */
    public ServerFirmwareInventoryStatus getStateFirmware() {
        return stateFirmware;
    }

    /**
     * @param stateFirmware the stateFirmware to set
     */
    public void setStateFirmware(ServerFirmwareInventoryStatus stateFirmware) {
        this.stateFirmware = stateFirmware;
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
