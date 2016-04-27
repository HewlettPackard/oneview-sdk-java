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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class QosTrafficClassifier implements Serializable {

    private static final long serialVersionUID = 1L;

    private QosTrafficClass qosTrafficClass;
    private QosClassificationMapping qosClassificationMapping;

    /**
     *
     * @return
     *     The qosTrafficClass
     */
    public QosTrafficClass getQosTrafficClass() {
        return qosTrafficClass;
    }

    /**
     *
     * @param qosTrafficClass
     *     The qosTrafficClass
     */
    public void setQosTrafficClass(QosTrafficClass qosTrafficClass) {
        this.qosTrafficClass = qosTrafficClass;
    }

    /**
     *
     * @return
     *     The qosClassificationMapping
     */
    public QosClassificationMapping getQosClassificationMapping() {
        return qosClassificationMapping;
    }

    /**
     *
     * @param qosClassificationMapping
     *     The qosClassificationMapping
     */
    public void setQosClassificationMapping(QosClassificationMapping qosClassificationMapping) {
        this.qosClassificationMapping = qosClassificationMapping;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(qosTrafficClass)
                .append(qosClassificationMapping)
                .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosTrafficClassifier) == false) {
            return false;
        }
        QosTrafficClassifier rhs = ((QosTrafficClassifier) other);
        return new EqualsBuilder()
                .append(qosTrafficClass, rhs.qosTrafficClass)
                .append(qosClassificationMapping, rhs.qosClassificationMapping)
                .isEquals();
    }

}
