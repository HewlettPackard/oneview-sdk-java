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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ServerPowerControlRequest implements Serializable {

    private static final long serialVersionUID = 7954518736138061854L;

    private PhysicalServerPowerControl powerControl;
    private Boolean powerRequestIssued;
    private PhysicalServerPowerState powerState;

    /**
     * @return the powerControl
     */
    public PhysicalServerPowerControl getPowerControl() {
        return powerControl;
    }

    /**
     * @param powerControl the powerControl to set
     */
    public void setPowerControl(PhysicalServerPowerControl powerControl) {
        this.powerControl = powerControl;
    }

    /**
     * @return the powerRequestIssued
     */
    public Boolean getPowerRequestIssued() {
        return powerRequestIssued;
    }

    /**
     * @param powerRequestIssued the powerRequestIssued to set
     */
    public void setPowerRequestIssued(Boolean powerRequestIssued) {
        this.powerRequestIssued = powerRequestIssued;
    }

    /**
     * @return the powerState
     */
    public PhysicalServerPowerState getPowerState() {
        return powerState;
    }

    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(PhysicalServerPowerState powerState) {
        this.powerState = powerState;
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
