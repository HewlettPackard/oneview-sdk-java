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

public class NameServer implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> connectionMap;
    private String fcDomainId;
    private String fcPortId;
    private String nodeWwn;
    private String portName;
    private String portType;
    private String portWwn;
    private String symbolicNodeName;
    private String symbolicPortName;

    /**
     * @return the connectionMap
     */
    public List<String> getConnectionMap() {
        return connectionMap;
    }
    /**
     * @param connectionMap the connectionMap to set
     */
    public void setConnectionMap(List<String> connectionMap) {
        this.connectionMap = connectionMap;
    }
    /**
     * @return the fcDomainId
     */
    public String getFcDomainId() {
        return fcDomainId;
    }
    /**
     * @param fcDomainId the fcDomainId to set
     */
    public void setFcDomainId(String fcDomainId) {
        this.fcDomainId = fcDomainId;
    }
    /**
     * @return the fcPortId
     */
    public String getFcPortId() {
        return fcPortId;
    }
    /**
     * @param fcPortId the fcPortId to set
     */
    public void setFcPortId(String fcPortId) {
        this.fcPortId = fcPortId;
    }
    /**
     * @return the nodeWwn
     */
    public String getNodeWwn() {
        return nodeWwn;
    }
    /**
     * @param nodeWwn the nodeWwn to set
     */
    public void setNodeWwn(String nodeWwn) {
        this.nodeWwn = nodeWwn;
    }
    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }
    /**
     * @param portName the portName to set
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }
    /**
     * @return the portType
     */
    public String getPortType() {
        return portType;
    }
    /**
     * @param portType the portType to set
     */
    public void setPortType(String portType) {
        this.portType = portType;
    }
    /**
     * @return the portWwn
     */
    public String getPortWwn() {
        return portWwn;
    }
    /**
     * @param portWwn the portWwn to set
     */
    public void setPortWwn(String portWwn) {
        this.portWwn = portWwn;
    }
    /**
     * @return the symbolicNodeName
     */
    public String getSymbolicNodeName() {
        return symbolicNodeName;
    }
    /**
     * @param symbolicNodeName the symbolicNodeName to set
     */
    public void setSymbolicNodeName(String symbolicNodeName) {
        this.symbolicNodeName = symbolicNodeName;
    }
    /**
     * @return the symbolicPortName
     */
    public String getSymbolicPortName() {
        return symbolicPortName;
    }
    /**
     * @param symbolicPortName the symbolicPortName to set
     */
    public void setSymbolicPortName(String symbolicPortName) {
        this.symbolicPortName = symbolicPortName;
    }

}
