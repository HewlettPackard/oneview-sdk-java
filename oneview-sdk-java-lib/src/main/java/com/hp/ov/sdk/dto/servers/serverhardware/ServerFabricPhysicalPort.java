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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Since;
import com.hp.ov.sdk.dto.ServerFabricVirtualPort;

public class ServerFabricPhysicalPort implements Serializable {

    private static final long serialVersionUID = -470629787839593201L;

    private Integer interconnectPort;
    private String interconnectUri;
    private String mac;

    @Since(200)
    private Integer physicalInterconnectPort;
    @Since(200)
    private String physicalInterconnectUri;

    private Integer portNumber;
    private ServerPortType type;
    private List<ServerFabricVirtualPort> virtualPorts = new ArrayList<>();
    private String wwn;

    /**
     * @return the interconnectPort
     */
    public Integer getInterconnectPort() {
        return interconnectPort;
    }

    /**
     * @param interconnectPort the interconnectPort to set
     */
    public void setInterconnectPort(Integer interconnectPort) {
        this.interconnectPort = interconnectPort;
    }

    /**
     * @return the interconnectUri
     */
    public String getInterconnectUri() {
        return interconnectUri;
    }

    /**
     * @param interconnectUri the interconnectUri to set
     */
    public void setInterconnectUri(String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    /**
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac the mac to set
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * @return the physicalInterconnectPort
     */
    public Integer getPhysicalInterconnectPort() {
        return physicalInterconnectPort;
    }

    /**
     * @param physicalInterconnectPort the physicalInterconnectPort to set
     */
    public void setPhysicalInterconnectPort(Integer physicalInterconnectPort) {
        this.physicalInterconnectPort = physicalInterconnectPort;
    }

    /**
     * @return the physicalInterconnectUri
     */
    public String getPhysicalInterconnectUri() {
        return physicalInterconnectUri;
    }

    /**
     * @param physicalInterconnectUri the physicalInterconnectUri to set
     */
    public void setPhysicalInterconnectUri(String physicalInterconnectUri) {
        this.physicalInterconnectUri = physicalInterconnectUri;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return the type
     */
    public ServerPortType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ServerPortType type) {
        this.type = type;
    }

    /**
     * @return the virtualPorts
     */
    public List<ServerFabricVirtualPort> getVirtualPorts() {
        return virtualPorts;
    }

    /**
     * @param virtualPorts the virtualPorts to set
     */
    public void setVirtualPorts(List<ServerFabricVirtualPort> virtualPorts) {
        this.virtualPorts = virtualPorts;
    }

    /**
     * @return the wwn
     */
    public String getWwn() {
        return wwn;
    }

    /**
     * @param wwn the wwn to set
     */
    public void setWwn(String wwn) {
        this.wwn = wwn;
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
