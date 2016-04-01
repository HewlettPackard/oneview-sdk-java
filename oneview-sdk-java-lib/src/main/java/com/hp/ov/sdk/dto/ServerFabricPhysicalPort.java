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
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Since;

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
    private PortType type;
    private List<ServerFabricVirtualPort> virtualPorts = new ArrayList<>();
    private String wwn;

    public Integer getInterconnectPort() {
        return interconnectPort;
    }

    public void setInterconnectPort(Integer interconnectPort) {
        this.interconnectPort = interconnectPort;
    }

    public String getInterconnectUri() {
        return interconnectUri;
    }

    public void setInterconnectUri(String interconnectUri) {
        this.interconnectUri = interconnectUri;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Integer getPhysicalInterconnectPort() {
        return physicalInterconnectPort;
    }

    public void setPhysicalInterconnectPort(Integer physicalInterconnectPort) {
        this.physicalInterconnectPort = physicalInterconnectPort;
    }

    public String getPhysicalInterconnectUri() {
        return physicalInterconnectUri;
    }

    public void setPhysicalInterconnectUri(String physicalInterconnectUri) {
        this.physicalInterconnectUri = physicalInterconnectUri;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public PortType getType() {
        return type;
    }

    public void setType(PortType type) {
        this.type = type;
    }

    public List<ServerFabricVirtualPort> getVirtualPorts() {
        return virtualPorts;
    }

    public void setVirtualPorts(List<ServerFabricVirtualPort> virtualPorts) {
        this.virtualPorts = virtualPorts;
    }

    public String getWwn() {
        return wwn;
    }

    public void setWwn(String wwn) {
        this.wwn = wwn;
    }
}
