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

public class ServerSettings implements Serializable {

    private static final long serialVersionUID = 2019456641939795156L;

    private FirmwareAndDriversInstallState firmwareAndDriversInstallState;
    private HpSmartUpdateToolStatus hpSmartUpdateToolStatus;

    /**
     * @return the firmwareAndDriversInstallState
     */
    public FirmwareAndDriversInstallState getFirmwareAndDriversInstallState() {
        return firmwareAndDriversInstallState;
    }

    /**
     * @param firmwareAndDriversInstallState the firmwareAndDriversInstallState to set
     */
    public void setFirmwareAndDriversInstallState(FirmwareAndDriversInstallState firmwareAndDriversInstallState) {
        this.firmwareAndDriversInstallState = firmwareAndDriversInstallState;
    }

    /**
     * @return the hpSmartUpdateToolStatus
     */
    public HpSmartUpdateToolStatus getHpSmartUpdateToolStatus() {
        return hpSmartUpdateToolStatus;
    }

    /**
     * @param hpSmartUpdateToolStatus the hpSmartUpdateToolStatus to set
     */
    public void setHpSmartUpdateToolStatus(HpSmartUpdateToolStatus hpSmartUpdateToolStatus) {
        this.hpSmartUpdateToolStatus = hpSmartUpdateToolStatus;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof ServerSettings) {
            ServerSettings that = (ServerSettings) obj;

            return new EqualsBuilder()
                    .append(firmwareAndDriversInstallState, that.firmwareAndDriversInstallState)
                    .append(hpSmartUpdateToolStatus, that.hpSmartUpdateToolStatus)
                    .isEquals();
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return new HashCodeBuilder()
                .append(firmwareAndDriversInstallState)
                .append(hpSmartUpdateToolStatus)
                .toHashCode();
    }
}
