/*******************************************************************************
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
 *******************************************************************************/
package com.hp.ov.sdk.dto.networking.logicalinterconnectgroup;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class QosConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private ActiveQosConfig activeQosConfig;
    private InactiveNonFCoEQosConfig inactiveNonFCoEQosConfig;
    private InactiveFCoEQosConfig inactiveFCoEQosConfig;

    /**
     *
     * @return
     *     The activeQosConfig
     */
    public ActiveQosConfig getActiveQosConfig() {
        return activeQosConfig;
    }

    /**
     *
     * @param activeQosConfig
     *     The activeQosConfig
     */
    public void setActiveQosConfig(ActiveQosConfig activeQosConfig) {
        this.activeQosConfig = activeQosConfig;
    }

    /**
     *
     * @return
     *     The inactiveNonFCoEQosConfig
     */
    public InactiveNonFCoEQosConfig getInactiveNonFCoEQosConfig() {
        return inactiveNonFCoEQosConfig;
    }

    /**
     *
     * @param inactiveNonFCoEQosConfig
     *     The inactiveNonFCoEQosConfig
     */
    public void setInactiveNonFCoEQosConfig(InactiveNonFCoEQosConfig inactiveNonFCoEQosConfig) {
        this.inactiveNonFCoEQosConfig = inactiveNonFCoEQosConfig;
    }

    /**
     *
     * @return
     *     The inactiveFCoEQosConfig
     */
    public InactiveFCoEQosConfig getInactiveFCoEQosConfig() {
        return inactiveFCoEQosConfig;
    }

    /**
     *
     * @param inactiveFCoEQosConfig
     *     The inactiveFCoEQosConfig
     */
    public void setInactiveFCoEQosConfig(InactiveFCoEQosConfig inactiveFCoEQosConfig) {
        this.inactiveFCoEQosConfig = inactiveFCoEQosConfig;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(activeQosConfig)
                .append(inactiveNonFCoEQosConfig)
                .append(inactiveFCoEQosConfig)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof QosConfiguration);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosConfiguration) == false) {
            return false;
        }
        QosConfiguration rhs = ((QosConfiguration) other);
        return new EqualsBuilder()
                .append(activeQosConfig, rhs.activeQosConfig)
                .append(inactiveNonFCoEQosConfig, rhs.inactiveNonFCoEQosConfig)
                .append(inactiveFCoEQosConfig, rhs.inactiveFCoEQosConfig)
                .appendSuper(super.equals(other))
                .isEquals();
    }

}
