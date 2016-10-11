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
package com.hp.ov.sdk.dto.networking.logicalinterconnects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class QosConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private QosConfiguration.QosConfigType configType;
    private QosConfiguration.QosClassificationType downlinkClassificationType;
    private QosConfiguration.QosClassificationType uplinkClassificationType;

    private List<QosTrafficClassifier> qosTrafficClassifiers;

    /**
     * @return the configType
     */
    public QosConfigType getConfigType() {
        return configType;
    }

    /**
     * @param configType the configType to set
     */
    public void setConfigType(QosConfigType configType) {
        this.configType = configType;
    }

    /**
     * @return the downlinkClassificationType
     */
    public QosClassificationType getDownlinkClassificationType() {
        return downlinkClassificationType;
    }

    /**
     * @param downlinkClassificationType the downlinkClassificationType to set
     */
    public void setDownlinkClassificationType(QosClassificationType downlinkClassificationType) {
        this.downlinkClassificationType = downlinkClassificationType;
    }

    /**
     * @return the uplinkClassificationType
     */
    public QosClassificationType getUplinkClassificationType() {
        return uplinkClassificationType;
    }

    /**
     * @param uplinkClassificationType the uplinkClassificationType to set
     */
    public void setUplinkClassificationType(QosClassificationType uplinkClassificationType) {
        this.uplinkClassificationType = uplinkClassificationType;
    }

    /**
     * @return the qosTrafficClassifiers
     */
    public List<QosTrafficClassifier> getQosTrafficClassifiers() {
        return qosTrafficClassifiers;
    }

    /**
     * @param qosTrafficClassifiers the qosTrafficClassifiers to set
     */
    public void setQosTrafficClassifiers(List<QosTrafficClassifier> qosTrafficClassifiers) {
        this.qosTrafficClassifiers = qosTrafficClassifiers;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(configType)
                .append(downlinkClassificationType)
                .append(uplinkClassificationType)
                .append(qosTrafficClassifiers)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof QosConfiguration);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosConfiguration) == false) {
            return false;
        }
        final QosConfiguration rhs = ((QosConfiguration) other);
        return new EqualsBuilder()
                .append(configType, rhs.configType)
                .append(downlinkClassificationType, rhs.downlinkClassificationType)
                .append(uplinkClassificationType, rhs.uplinkClassificationType)
                .append(qosTrafficClassifiers, rhs.qosTrafficClassifiers)
                .appendSuper(super.equals(other))
                .isEquals();
    }

    public static enum QosConfigType {

        Passthrough("Passthrough"),
        CustomWithFCoE("CustomWithFCoE"),
        CustomNoFCoE("CustomNoFCoE"),
        Unknown("Unknown"),
        NA("NA");

        private final String value;
        private static Map<String, QosConfiguration.QosConfigType> constants = new HashMap<String, QosConfiguration.QosConfigType>();

        static {
            for (final QosConfiguration.QosConfigType c : values()) {
                constants.put(c.value, c);
            }
        }

        private QosConfigType(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static QosConfiguration.QosConfigType fromValue(final String value) {
            final QosConfiguration.QosConfigType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

    public static enum QosClassificationType {

        DOT1P("DOT1P"),
        DSCP("DSCP"),
        DOT1P_AND_DSCP("DOT1P_AND_DSCP"),
        Unknown("Unknown"),
        NA("NA");

        private final String value;
        private static Map<String, QosConfiguration.QosClassificationType> constants = new HashMap<String, QosConfiguration.QosClassificationType>();

        static {
            for (final QosConfiguration.QosClassificationType c : values()) {
                constants.put(c.value, c);
            }
        }

        private QosClassificationType(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public static QosConfiguration.QosClassificationType fromValue(final String value) {
            final QosConfiguration.QosClassificationType constant = constants.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

}
