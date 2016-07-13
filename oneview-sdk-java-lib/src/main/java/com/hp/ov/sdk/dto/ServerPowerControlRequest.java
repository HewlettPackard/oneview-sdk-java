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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ServerPowerControlRequest implements Serializable {

    private static final long serialVersionUID = 7954518736138061854L;

    private PhysicalServerPowerControl powerControl;
    private Boolean powerRequestIssued;
    private PhysicalServerPowerState powerState;

    public PhysicalServerPowerControl getPowerControl() {
        return powerControl;
    }

    public void setPowerControl(final PhysicalServerPowerControl powerControl) {
        this.powerControl = powerControl;
    }

    public Boolean getPowerRequestIssued() {
        return powerRequestIssued;
    }

    public void setPowerRequestIssued(final Boolean powerRequestIssued) {
        this.powerRequestIssued = powerRequestIssued;
    }

    public PhysicalServerPowerState getPowerState() {
        return powerState;
    }

    public void setPowerState(final PhysicalServerPowerState powerState) {
        this.powerState = powerState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof ServerPowerControlRequest) {
            ServerPowerControlRequest that = (ServerPowerControlRequest) obj;

            return new EqualsBuilder()
                    .append(powerControl, that.powerControl)
                    .append(powerRequestIssued, that.powerRequestIssued)
                    .append(powerState, that.powerState)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(powerControl)
                .append(powerRequestIssued)
                .append(powerState)
                .toHashCode();
    }
}
