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

import java.util.ArrayList;
import java.util.List;

public class PortInfoV2 extends BaseModelResource {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Boolean downlinkCapable;
    private String pairedPortName;
    private List<PortCapabilityType> portCapability = new ArrayList<PortCapabilityType>();
    private String portName;
    private Integer portNumber;
    private Boolean uplinkCapable;

    /**
     * @return the downlinkCapable
     */
    public Boolean getDownlinkCapable() {
        return downlinkCapable;
    }

    /**
     * @param downlinkCapable
     *            the downlinkCapable to set
     */
    public void setDownlinkCapable(final Boolean downlinkCapable) {
        this.downlinkCapable = downlinkCapable;
    }

    /**
     * @return the pairedPortName
     */
    public String getPairedPortName() {
        return pairedPortName;
    }

    /**
     * @param pairedPortName
     *            the pairedPortName to set
     */
    public void setPairedPortName(final String pairedPortName) {
        this.pairedPortName = pairedPortName;
    }

    /**
     * @return the portCapability
     */
    public List<PortCapabilityType> getPortCapability() {
        return portCapability;
    }

    /**
     * @param portCapability
     *            the portCapability to set
     */
    public void setPortCapability(final List<PortCapabilityType> portCapability) {
        this.portCapability = portCapability;
    }

    /**
     * @return the portName
     */
    public String getPortName() {
        return portName;
    }

    /**
     * @param portName
     *            the portName to set
     */
    public void setPortName(final String portName) {
        this.portName = portName;
    }

    /**
     * @return the portNumber
     */
    public Integer getPortNumber() {
        return portNumber;
    }

    /**
     * @param portNumber
     *            the portNumber to set
     */
    public void setPortNumber(final Integer portNumber) {
        this.portNumber = portNumber;
    }

    /**
     * @return the uplinkCapable
     */
    public Boolean getUplinkCapable() {
        return uplinkCapable;
    }

    /**
     * @param uplinkCapable
     *            the uplinkCapable to set
     */
    public void setUplinkCapable(final Boolean uplinkCapable) {
        this.uplinkCapable = uplinkCapable;
    }

}
