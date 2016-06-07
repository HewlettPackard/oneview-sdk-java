/*
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
 */

package com.hp.ov.sdk.dto.serverhardwaretype;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class StorageCapabilities {

    private List<ControllerMode> controllerModes;
    private List<DriveTechnology> driveTechnologies;
    private Integer maximumDrives;
    private List<RaidLevel> raidLevels;

    public List<ControllerMode> getControllerModes() {
        return controllerModes;
    }

    public void setControllerModes(List<ControllerMode> controllerModes) {
        this.controllerModes = controllerModes;
    }

    public List<DriveTechnology> getDriveTechnologies() {
        return driveTechnologies;
    }

    public void setDriveTechnologies(List<DriveTechnology> driveTechnologies) {
        this.driveTechnologies = driveTechnologies;
    }

    public Integer getMaximumDrives() {
        return maximumDrives;
    }

    public void setMaximumDrives(Integer maximumDrives) {
        this.maximumDrives = maximumDrives;
    }

    public List<RaidLevel> getRaidLevels() {
        return raidLevels;
    }

    public void setRaidLevels(List<RaidLevel> raidLevels) {
        this.raidLevels = raidLevels;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof StorageCapabilities) {
            StorageCapabilities that = (StorageCapabilities) obj;

            return new EqualsBuilder()
                    .append(controllerModes, that.controllerModes)
                    .append(driveTechnologies, that.driveTechnologies)
                    .append(maximumDrives, that.maximumDrives)
                    .append(raidLevels, that.raidLevels)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(controllerModes)
                .append(driveTechnologies)
                .append(maximumDrives)
                .append(raidLevels)
                .toHashCode();
    }
}
