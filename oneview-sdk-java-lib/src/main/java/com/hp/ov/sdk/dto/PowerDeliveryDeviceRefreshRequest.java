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
package com.hp.ov.sdk.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class PowerDeliveryDeviceRefreshRequest extends ImportPdd {

    private static final long serialVersionUID = 1L;

    private RefreshState refreshState;

    /**
     * @return the refreshState
     */
    public RefreshState getRefreshState() {
        return refreshState;
    }

    /**
     * @param refreshState the refreshState to set
     */
    public void setRefreshState(RefreshState refreshState) {
        this.refreshState = refreshState;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof PowerDeliveryDeviceRefreshRequest) {
            PowerDeliveryDeviceRefreshRequest that = (PowerDeliveryDeviceRefreshRequest) obj;

            return new EqualsBuilder()
                    .appendSuper(super.equals(obj))
                    .append(refreshState, that.refreshState)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .appendSuper(super.hashCode())
                .append(refreshState)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("refreshState", refreshState)
                .toString();
    }
}
