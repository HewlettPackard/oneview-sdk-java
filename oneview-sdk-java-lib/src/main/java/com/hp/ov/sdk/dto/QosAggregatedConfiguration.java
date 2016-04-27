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
package com.hp.ov.sdk.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class QosAggregatedConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private QosConfiguration activeQosConfig;
    private QosConfiguration inactiveFCoEQosConfig;
    private QosConfiguration inactiveNonFCoEQosConfig;

    /**
     * @return the activeQosConfig
     */
    public QosConfiguration getActiveQosConfig() {
        return activeQosConfig;
    }
    /**
     * @param activeQosConfig the activeQosConfig to set
     */
    public void setActiveQosConfig(QosConfiguration activeQosConfig) {
        this.activeQosConfig = activeQosConfig;
    }
    /**
     * @return the inactiveFCoEQosConfig
     */
    public QosConfiguration getInactiveFCoEQosConfig() {
        return inactiveFCoEQosConfig;
    }
    /**
     * @param inactiveFCoEQosConfig the inactiveFCoEQosConfig to set
     */
    public void setInactiveFCoEQosConfig(QosConfiguration inactiveFCoEQosConfig) {
        this.inactiveFCoEQosConfig = inactiveFCoEQosConfig;
    }
    /**
     * @return the inactiveNonFCoEQosConfig
     */
    public QosConfiguration getInactiveNonFCoEQosConfig() {
        return inactiveNonFCoEQosConfig;
    }
    /**
     * @param inactiveNonFCoEQosConfig the inactiveNonFCoEQosConfig to set
     */
    public void setInactiveNonFCoEQosConfig(QosConfiguration inactiveNonFCoEQosConfig) {
        this.inactiveNonFCoEQosConfig = inactiveNonFCoEQosConfig;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(activeQosConfig)
                .append(inactiveFCoEQosConfig)
                .append(inactiveNonFCoEQosConfig)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosAggregatedConfiguration) == false) {
            return false;
        }
        final QosAggregatedConfiguration rhs = ((QosAggregatedConfiguration) other);
        return new EqualsBuilder()
                .append(activeQosConfig, rhs.activeQosConfig)
                .append(inactiveFCoEQosConfig, rhs.inactiveFCoEQosConfig)
                .append(inactiveNonFCoEQosConfig, rhs.inactiveNonFCoEQosConfig)
                .appendSuper(super.equals(other))
                .isEquals();
    }

}
