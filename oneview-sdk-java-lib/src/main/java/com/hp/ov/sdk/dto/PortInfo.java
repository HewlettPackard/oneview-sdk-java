/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
import java.util.ArrayList;
import java.util.List;

public class PortInfo implements Serializable {

    private static final long serialVersionUID = -7589558435931639188L;

    private Boolean downlinkCapable;
    private String pairedPortName;
    private List<PortCapabilityType> portCapability = new ArrayList<>();
    private String portName;
    private Integer portNumber;
    private Boolean uplinkCapable;

    public Boolean getDownlinkCapable() {
        return downlinkCapable;
    }

    public void setDownlinkCapable(final Boolean downlinkCapable) {
        this.downlinkCapable = downlinkCapable;
    }

    public String getPairedPortName() {
        return pairedPortName;
    }

    public void setPairedPortName(final String pairedPortName) {
        this.pairedPortName = pairedPortName;
    }

    public List<PortCapabilityType> getPortCapability() {
        return portCapability;
    }

    public void setPortCapability(final List<PortCapabilityType> portCapability) {
        this.portCapability = portCapability;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(final String portName) {
        this.portName = portName;
    }

    public Integer getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    public Boolean getUplinkCapable() {
        return uplinkCapable;
    }

    public void setUplinkCapable(final Boolean uplinkCapable) {
        this.uplinkCapable = uplinkCapable;
    }

}
