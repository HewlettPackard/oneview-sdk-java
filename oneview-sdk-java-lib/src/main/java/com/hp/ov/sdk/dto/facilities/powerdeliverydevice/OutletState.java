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
package com.hp.ov.sdk.dto.facilities.powerdeliverydevice;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OutletState implements Serializable {

    private static final long serialVersionUID = 1L;

    private Power powerState;
    private Light uidState;

    /**
     * @return the powerState
     */
    public Power getPowerState() {
        return powerState;
    }

    /**
     * @param powerState the powerState to set
     */
    public void setPowerState(Power powerState) {
        this.powerState = powerState;
    }

    /**
     * @return the uidState
     */
    public Light getUidState() {
        return uidState;
    }

    /**
     * @param uidState the uidState to set
     */
    public void setUidState(Light uidState) {
        this.uidState = uidState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        OutletState that = (OutletState) obj;

        return new EqualsBuilder()
                .append(powerState, that.powerState)
                .append(uidState, that.uidState)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(powerState)
                .append(uidState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("powerState", powerState)
                .append("uidState", uidState)
                .toString();
    }

}
