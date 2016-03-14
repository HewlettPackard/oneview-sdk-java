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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;
import com.hp.ov.sdk.dto.BaseModelResource;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "configType",
    "uplinkClassificationType",
    "downlinkClassificationType",
    "qosTrafficClassifiers"
})
public class InactiveNonFCoEQosConfig extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    @JsonProperty("configType")
    private InactiveNonFCoEQosConfig.ConfigType configType;
    @JsonProperty("uplinkClassificationType")
    private InactiveNonFCoEQosConfig.LinkClassificationType uplinkClassificationType;
    @JsonProperty("downlinkClassificationType")
    private InactiveNonFCoEQosConfig.LinkClassificationType downlinkClassificationType;
    @JsonProperty("qosTrafficClassifiers")
    private List<QosTrafficClassifier> qosTrafficClassifiers = new ArrayList<QosTrafficClassifier>();

    /**
     *
     * @return
     *     The configType
     */
    @JsonProperty("configType")
    public InactiveNonFCoEQosConfig.ConfigType getConfigType() {
        return configType;
    }

    /**
     *
     * @param configType
     *     The configType
     */
    @JsonProperty("configType")
    public void setConfigType(InactiveNonFCoEQosConfig.ConfigType configType) {
        this.configType = configType;
    }

    /**
     *
     * @return
     *     The uplinkClassificationType
     */
    @JsonProperty("uplinkClassificationType")
    public InactiveNonFCoEQosConfig.LinkClassificationType getUplinkClassificationType() {
        return uplinkClassificationType;
    }

    /**
     *
     * @param uplinkClassificationType
     *     The uplinkClassificationType
     */
    @JsonProperty("uplinkClassificationType")
    public void setUplinkClassificationType(InactiveNonFCoEQosConfig.LinkClassificationType uplinkClassificationType) {
        this.uplinkClassificationType = uplinkClassificationType;
    }

    /**
     *
     * @return
     *     The downlinkClassificationType
     */
    @JsonProperty("downlinkClassificationType")
    public InactiveNonFCoEQosConfig.LinkClassificationType getDownlinkClassificationType() {
        return downlinkClassificationType;
    }

    /**
     *
     * @param downlinkClassificationType
     *     The downlinkClassificationType
     */
    @JsonProperty("downlinkClassificationType")
    public void setDownlinkClassificationType(InactiveNonFCoEQosConfig.LinkClassificationType downlinkClassificationType) {
        this.downlinkClassificationType = downlinkClassificationType;
    }

    /**
     *
     * @return
     *     The qosTrafficClassifiers
     */
    @JsonProperty("qosTrafficClassifiers")
    public List<QosTrafficClassifier> getQosTrafficClassifiers() {
        return qosTrafficClassifiers;
    }

    /**
     *
     * @param qosTrafficClassifiers
     *     The qosTrafficClassifiers
     */
    @JsonProperty("qosTrafficClassifiers")
    public void setQosTrafficClassifiers(List<QosTrafficClassifier> qosTrafficClassifiers) {
        this.qosTrafficClassifiers = qosTrafficClassifiers;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(configType)
                .append(uplinkClassificationType)
                .append(downlinkClassificationType)
                .append(qosTrafficClassifiers)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InactiveNonFCoEQosConfig) == false) {
            return false;
        }
        InactiveNonFCoEQosConfig rhs = ((InactiveNonFCoEQosConfig) other);
        return new EqualsBuilder()
                .append(configType, rhs.configType)
                .append(uplinkClassificationType, rhs.uplinkClassificationType)
                .append(downlinkClassificationType, rhs.downlinkClassificationType)
                .append(qosTrafficClassifiers, rhs.qosTrafficClassifiers)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    @Generated("org.jsonschema2pojo")
    public static enum ConfigType {

        Passthrough("Passthrough"),
        CustomWithFCoE("CustomWithFCoE"),
        CustomNoFCoE("CustomNoFCoE"),
        Unknown("Unknown"),
        NA("NA");
        private final String value;
        private final static Map<String, InactiveNonFCoEQosConfig.ConfigType> CONSTANTS = new HashMap<String, InactiveNonFCoEQosConfig.ConfigType>();

        static {
            for (InactiveNonFCoEQosConfig.ConfigType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ConfigType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static InactiveNonFCoEQosConfig.ConfigType fromValue(String value) {
            InactiveNonFCoEQosConfig.ConfigType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    @Generated("org.jsonschema2pojo")
    public static enum LinkClassificationType {

        DOT1P("DOT1P"),
        DSCP("DSCP"),
        DOT1P_AND_DSCP("DOT1P_AND_DSCP"),
        Unknown("Unknown"),
        NA("NA");
        private final String value;
        private final static Map<String, InactiveNonFCoEQosConfig.LinkClassificationType> CONSTANTS = new HashMap<String, InactiveNonFCoEQosConfig.LinkClassificationType>();

        static {
            for (InactiveNonFCoEQosConfig.LinkClassificationType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private LinkClassificationType(String value) {
            this.value = value;
        }

        @JsonValue
        @Override
        public String toString() {
            return this.value;
        }

        @JsonCreator
        public static InactiveNonFCoEQosConfig.LinkClassificationType fromValue(String value) {
            InactiveNonFCoEQosConfig.LinkClassificationType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
