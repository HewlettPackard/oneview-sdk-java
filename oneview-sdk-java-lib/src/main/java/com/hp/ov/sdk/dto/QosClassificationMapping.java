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

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class QosClassificationMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<QosDot1pValues> dot1pClassMapping;

    private List<QosDscpValues> dscpClassMapping;

    /**
     * @return the dot1pClassMapping
     */
    public List<QosDot1pValues> getDot1pClassMapping() {
        return dot1pClassMapping;
    }

    /**
     * @param dot1pClassMapping the dot1pClassMapping to set
     */
    public void setDot1pClassMapping(List<QosDot1pValues> dot1pClassMapping) {
        this.dot1pClassMapping = dot1pClassMapping;
    }

    /**
     * @return the dscpClassMapping
     */
    public List<QosDscpValues> getDscpClassMapping() {
        return dscpClassMapping;
    }

    /**
     * @param dscpClassMapping the dscpClassMapping to set
     */
    public void setDscpClassMapping(List<QosDscpValues> dscpClassMapping) {
        this.dscpClassMapping = dscpClassMapping;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(dot1pClassMapping)
                .append(dscpClassMapping)
                .toHashCode();
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof QosClassificationMapping) == false) {
            return false;
        }
        final QosClassificationMapping rhs = ((QosClassificationMapping) other);
        return new EqualsBuilder()
                .append(dot1pClassMapping, rhs.dot1pClassMapping)
                .append(dscpClassMapping, rhs.dscpClassMapping)
                .isEquals();
    }

}
