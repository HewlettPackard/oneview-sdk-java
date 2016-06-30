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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class InactiveFCoEQosConfig extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private InactiveFCoEQosConfig.ConfigType configType;
    private InactiveFCoEQosConfig.LinkClassificationType uplinkClassificationType;
    private InactiveFCoEQosConfig.LinkClassificationType downlinkClassificationType;
    private List<QosTrafficClassifier> qosTrafficClassifiers = new ArrayList<QosTrafficClassifier>();

    /**
     *
     * @return
     *     The configType
     */
    public InactiveFCoEQosConfig.ConfigType getConfigType() {
        return configType;
    }

    /**
     *
     * @param configType
     *     The configType
     */
    public void setConfigType(InactiveFCoEQosConfig.ConfigType configType) {
        this.configType = configType;
    }

    /**
     *
     * @return
     *     The uplinkClassificationType
     */
    public InactiveFCoEQosConfig.LinkClassificationType getUplinkClassificationType() {
        return uplinkClassificationType;
    }

    /**
     *
     * @param uplinkClassificationType
     *     The uplinkClassificationType
     */
    public void setUplinkClassificationType(InactiveFCoEQosConfig.LinkClassificationType uplinkClassificationType) {
        this.uplinkClassificationType = uplinkClassificationType;
    }

    /**
     *
     * @return
     *     The downlinkClassificationType
     */
    public InactiveFCoEQosConfig.LinkClassificationType getDownlinkClassificationType() {
        return downlinkClassificationType;
    }

    /**
     *
     * @param downlinkClassificationType
     *     The downlinkClassificationType
     */
    public void setDownlinkClassificationType(InactiveFCoEQosConfig.LinkClassificationType downlinkClassificationType) {
        this.downlinkClassificationType = downlinkClassificationType;
    }

    /**
     *
     * @return
     *     The qosTrafficClassifiers
     */
    public List<QosTrafficClassifier> getQosTrafficClassifiers() {
        return qosTrafficClassifiers;
    }

    /**
     *
     * @param qosTrafficClassifiers
     *     The qosTrafficClassifiers
     */
    public void setQosTrafficClassifiers(List<QosTrafficClassifier> qosTrafficClassifiers) {
        this.qosTrafficClassifiers = qosTrafficClassifiers;
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof InactiveFCoEQosConfig);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InactiveFCoEQosConfig) == false) {
            return false;
        }
        InactiveFCoEQosConfig rhs = ((InactiveFCoEQosConfig) other);
        return new EqualsBuilder()
                .append(configType, rhs.configType)
                .append(uplinkClassificationType, rhs.uplinkClassificationType)
                .append(downlinkClassificationType, rhs.downlinkClassificationType)
                .append(qosTrafficClassifiers, rhs.qosTrafficClassifiers)
                .appendSuper(super.equals(other))
                .isEquals();
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

    public static enum ConfigType {

        Passthrough("Passthrough"),
        CustomWithFCoE("CustomWithFCoE"),
        CustomNoFCoE("CustomNoFCoE"),
        Unknown("Unknown"),
        NA("NA");
        private final String value;
        private final static Map<String, InactiveFCoEQosConfig.ConfigType> CONSTANTS = new HashMap<String, InactiveFCoEQosConfig.ConfigType>();

        static {
            for (InactiveFCoEQosConfig.ConfigType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ConfigType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static InactiveFCoEQosConfig.ConfigType fromValue(String value) {
            InactiveFCoEQosConfig.ConfigType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

    public static enum LinkClassificationType {

        DOT1P("DOT1P"),
        DSCP("DSCP"),
        DOT1P_AND_DSCP("DOT1P_AND_DSCP"),
        Unknown("Unknown"),
        NA("NA");
        private final String value;
        private final static Map<String, InactiveFCoEQosConfig.LinkClassificationType> CONSTANTS = new HashMap<String, InactiveFCoEQosConfig.LinkClassificationType>();

        static {
            for (InactiveFCoEQosConfig.LinkClassificationType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private LinkClassificationType(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static InactiveFCoEQosConfig.LinkClassificationType fromValue(String value) {
            InactiveFCoEQosConfig.LinkClassificationType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
