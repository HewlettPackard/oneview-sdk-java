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
package com.hp.ov.sdk.dto.generated;

import java.io.Serializable;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hp.ov.sdk.dto.BaseModelResource;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "activeQosConfig",
    "inactiveNonFCoEQosConfig",
    "inactiveFCoEQosConfig"
})
public class QosConfiguration extends BaseModelResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("activeQosConfig")
    private ActiveQosConfig activeQosConfig;
    @JsonProperty("inactiveNonFCoEQosConfig")
    private InactiveNonFCoEQosConfig inactiveNonFCoEQosConfig;
    @JsonProperty("inactiveFCoEQosConfig")
    private InactiveFCoEQosConfig inactiveFCoEQosConfig;

    /**
     *
     * @return
     *     The activeQosConfig
     */
    @JsonProperty("activeQosConfig")
    public ActiveQosConfig getActiveQosConfig() {
        return activeQosConfig;
    }

    /**
     *
     * @param activeQosConfig
     *     The activeQosConfig
     */
    @JsonProperty("activeQosConfig")
    public void setActiveQosConfig(ActiveQosConfig activeQosConfig) {
        this.activeQosConfig = activeQosConfig;
    }

    /**
     *
     * @return
     *     The inactiveNonFCoEQosConfig
     */
    @JsonProperty("inactiveNonFCoEQosConfig")
    public InactiveNonFCoEQosConfig getInactiveNonFCoEQosConfig() {
        return inactiveNonFCoEQosConfig;
    }

    /**
     *
     * @param inactiveNonFCoEQosConfig
     *     The inactiveNonFCoEQosConfig
     */
    @JsonProperty("inactiveNonFCoEQosConfig")
    public void setInactiveNonFCoEQosConfig(InactiveNonFCoEQosConfig inactiveNonFCoEQosConfig) {
        this.inactiveNonFCoEQosConfig = inactiveNonFCoEQosConfig;
    }

    /**
     *
     * @return
     *     The inactiveFCoEQosConfig
     */
    @JsonProperty("inactiveFCoEQosConfig")
    public InactiveFCoEQosConfig getInactiveFCoEQosConfig() {
        return inactiveFCoEQosConfig;
    }

    /**
     *
     * @param inactiveFCoEQosConfig
     *     The inactiveFCoEQosConfig
     */
    @JsonProperty("inactiveFCoEQosConfig")
    public void setInactiveFCoEQosConfig(InactiveFCoEQosConfig inactiveFCoEQosConfig) {
        this.inactiveFCoEQosConfig = inactiveFCoEQosConfig;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
