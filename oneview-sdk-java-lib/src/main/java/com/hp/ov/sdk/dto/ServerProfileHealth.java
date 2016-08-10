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
import java.util.Set;

public class ServerProfileHealth implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<ProfileConnectionStatus> connections;
    private String eTag;
    private ProfileFirmwareStatus firmwareStatus;
    private Set<ProfileServerHardwareStatus> serverHardware;
    private String type;

    /**
     * @return the connections
     */
    public Set<ProfileConnectionStatus> getConnections() {
        return connections;
    }

    /**
     * @param connections the connections to set
     */
    public void setConnections(Set<ProfileConnectionStatus> connections) {
        this.connections = connections;
    }

    /**
     * @return the eTag
     */
    public String geteTag() {
        return eTag;
    }

    /**
     * @param eTag the eTag to set
     */
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the firmwareStatus
     */
    public ProfileFirmwareStatus getFirmwareStatus() {
        return firmwareStatus;
    }

    /**
     * @param firmwareStatus the firmwareStatus to set
     */
    public void setFirmwareStatus(ProfileFirmwareStatus firmwareStatus) {
        this.firmwareStatus = firmwareStatus;
    }

    /**
     * @return the serverHardware
     */
    public Set<ProfileServerHardwareStatus> getServerHardware() {
        return serverHardware;
    }

    /**
     * @param serverHardware the serverHardware to set
     */
    public void setServerHardware(Set<ProfileServerHardwareStatus> serverHardware) {
        this.serverHardware = serverHardware;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
