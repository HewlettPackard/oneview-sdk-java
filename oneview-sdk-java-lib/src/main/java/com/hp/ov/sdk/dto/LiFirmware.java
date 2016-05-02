/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LiFirmware implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private Command command;
    private Boolean force;
    private String fwBaseline;
    private List<PhysicalInterconnectFirmware> interconnects = new ArrayList<PhysicalInterconnectFirmware>();
    private String sppName;
    private String sppUri;
    private LsFwStateEnum state;

    /**
     * 
     * @return The command
     */
    public Command getCommand() {
        return command;
    }

    /**
     * 
     * @param command
     *            The command
     */
    public void setCommand(final Command command) {
        this.command = command;
    }

    /**
     * 
     * @return The force
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * 
     * @param force
     *            The force
     */
    public void setForce(final Boolean force) {
        this.force = force;
    }

    /**
     * 
     * @return The fwBaseline
     */
    public String getFwBaseline() {
        return fwBaseline;
    }

    /**
     * 
     * @param fwBaseline
     *            The fwBaseline
     */
    public void setFwBaseline(final String fwBaseline) {
        this.fwBaseline = fwBaseline;
    }

    /**
     * 
     * @return The interconnects
     */
    public List<PhysicalInterconnectFirmware> getInterconnects() {
        return interconnects;
    }

    /**
     * 
     * @param interconnects
     *            The interconnects
     */
    public void setInterconnects(final List<PhysicalInterconnectFirmware> interconnects) {
        this.interconnects = interconnects;
    }

    /**
     * 
     * @return The sppName
     */
    public String getSppName() {
        return sppName;
    }

    /**
     * 
     * @param sppName
     *            The sppName
     */
    public void setSppName(final String sppName) {
        this.sppName = sppName;
    }

    /**
     * 
     * @return The sppUri
     */
    public String getSppUri() {
        return sppUri;
    }

    /**
     * 
     * @param sppUri
     *            The sppUri
     */
    public void setSppUri(final String sppUri) {
        this.sppUri = sppUri;
    }

    /**
     * 
     * @return The state
     */
    public LsFwStateEnum getState() {
        return state;
    }

    /**
     * 
     * @param state
     *            The state
     */
    public void setState(final LsFwStateEnum state) {
        this.state = state;
    }

}
