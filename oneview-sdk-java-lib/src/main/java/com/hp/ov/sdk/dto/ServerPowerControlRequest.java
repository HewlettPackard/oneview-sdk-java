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

public class ServerPowerControlRequest {

    private PhysicalServerPowerControl powerControl;
    private Boolean powerRequestIssued;
    private PhysicalServerPowerState powerState;

    /**
     * 
     * @return The powerControl
     */
    public PhysicalServerPowerControl getPowerControl() {
        return powerControl;
    }

    /**
     * 
     * @param powerControl
     *            The powerControl
     */
    public void setPowerControl(final PhysicalServerPowerControl powerControl) {
        this.powerControl = powerControl;
    }

    /**
     * 
     * @return The powerRequestIssued
     */
    public Boolean getPowerRequestIssued() {
        return powerRequestIssued;
    }

    /**
     * 
     * @param powerRequestIssued
     *            The powerRequestIssued
     */
    public void setPowerRequestIssued(final Boolean powerRequestIssued) {
        this.powerRequestIssued = powerRequestIssued;
    }

    /**
     * 
     * @return The powerState
     */
    public PhysicalServerPowerState getPowerState() {
        return powerState;
    }

    /**
     * 
     * @param powerState
     *            The powerState
     */
    public void setPowerState(final PhysicalServerPowerState powerState) {
        this.powerState = powerState;
    }

}
