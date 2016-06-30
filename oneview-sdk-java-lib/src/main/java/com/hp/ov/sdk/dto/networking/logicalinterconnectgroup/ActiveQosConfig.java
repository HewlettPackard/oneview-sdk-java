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

import com.hp.ov.sdk.dto.BaseModelResource;

public class ActiveQosConfig extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private ActiveQosConfig.ConfigType configType;
    private ActiveQosConfig.LinkClassificationType uplinkClassificationType;
    private ActiveQosConfig.LinkClassificationType downlinkClassificationType;
    private List<QosTrafficClassifier> qosTrafficClassifiers = new ArrayList<QosTrafficClassifier>();

    /**
     *
     * @return
     *     The configType
     */
    public ActiveQosConfig.ConfigType getConfigType() {
        return configType;
    }

    /**
     *
     * @param configType
     *     The configType
     */
    public void setConfigType(ActiveQosConfig.ConfigType configType) {
        this.configType = configType;
    }

    /**
     *
     * @return
     *     The uplinkClassificationType
     */
    public ActiveQosConfig.LinkClassificationType getUplinkClassificationType() {
        return uplinkClassificationType;
    }

    /**
     *
     * @param uplinkClassificationType
     *     The uplinkClassificationType
     */
    public void setUplinkClassificationType(ActiveQosConfig.LinkClassificationType uplinkClassificationType) {
        this.uplinkClassificationType = uplinkClassificationType;
    }

    /**
     *
     * @return
     *     The downlinkClassificationType
     */
    public ActiveQosConfig.LinkClassificationType getDownlinkClassificationType() {
        return downlinkClassificationType;
    }

    /**
     *
     * @param downlinkClassificationType
     *     The downlinkClassificationType
     */
    public void setDownlinkClassificationType(ActiveQosConfig.LinkClassificationType downlinkClassificationType) {
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
        return (obj instanceof ActiveQosConfig);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof ActiveQosConfig) {
            ActiveQosConfig rhs = ((ActiveQosConfig) other);

            return new EqualsBuilder()
                    .append(configType, rhs.configType)
                    .append(uplinkClassificationType, rhs.uplinkClassificationType)
                    .append(downlinkClassificationType, rhs.downlinkClassificationType)
                    .append(qosTrafficClassifiers, rhs.qosTrafficClassifiers)
                    .appendSuper(super.equals(other))
                    .isEquals();
        }
        return false;
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
        private final static Map<String, ActiveQosConfig.ConfigType> CONSTANTS = new HashMap<String, ActiveQosConfig.ConfigType>();

        static {
            for (ActiveQosConfig.ConfigType c: values()) {
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

        public static ActiveQosConfig.ConfigType fromValue(String value) {
            ActiveQosConfig.ConfigType constant = CONSTANTS.get(value);
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
        private final static Map<String, ActiveQosConfig.LinkClassificationType> CONSTANTS = new HashMap<String, ActiveQosConfig.LinkClassificationType>();

        static {
            for (ActiveQosConfig.LinkClassificationType c: values()) {
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

        public static ActiveQosConfig.LinkClassificationType fromValue(String value) {
            ActiveQosConfig.LinkClassificationType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

}
