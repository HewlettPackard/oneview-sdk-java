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

package com.hp.ov.sdk.dto.servers.serverhardware;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class FirmwareAndDriversInstallState implements Serializable {

    private static final long serialVersionUID = 8803570601186448570L;

    private FirmwareInstallState installState;
    private String installedStateTimestamp;

    /**
     * @return the installState
     */
    public FirmwareInstallState getInstallState() {
        return installState;
    }

    /**
     * @param installState the installState to set
     */
    public void setInstallState(FirmwareInstallState installState) {
        this.installState = installState;
    }

    /**
     * @return the installedStateTimestamp
     */
    public String getInstalledStateTimestamp() {
        return installedStateTimestamp;
    }

    /**
     * @param installedStateTimestamp the installedStateTimestamp to set
     */
    public void setInstalledStateTimestamp(String installedStateTimestamp) {
        this.installedStateTimestamp = installedStateTimestamp;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof FirmwareAndDriversInstallState) {
            FirmwareAndDriversInstallState that = (FirmwareAndDriversInstallState) obj;

            return new EqualsBuilder()
                    .append(installState, that.installState)
                    .append(installedStateTimestamp, that.installedStateTimestamp)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(installState)
                .append(installedStateTimestamp)
                .toHashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
