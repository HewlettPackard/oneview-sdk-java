/*******************************************************************************
 * (C) Copyright 2015-2016 Hewlett Packard Enterprise Development LP
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
package com.hp.ov.sdk.dto.networking.ethernet;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Bandwidth implements Serializable {

    private static final long serialVersionUID = 8074702168200387351L;

    private Double maximumBandwidth;
    private Double typicalBandwidth;

    public Double getMaximumBandwidth() {
        return maximumBandwidth;
    }

    public void setMaximumBandwidth(final Double maximumBandwidth) {
        this.maximumBandwidth = maximumBandwidth;
    }

    public Double getTypicalBandwidth() {
        return typicalBandwidth;
    }

    public void setTypicalBandwidth(final Double typicalBandwidth) {
        this.typicalBandwidth = typicalBandwidth;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Bandwidth) {
            Bandwidth bandwidth = (Bandwidth) obj;

            return new EqualsBuilder()
                    .append(maximumBandwidth, bandwidth.maximumBandwidth)
                    .append(typicalBandwidth, bandwidth.typicalBandwidth)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(maximumBandwidth)
                .append(typicalBandwidth)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("maximumBandwidth", maximumBandwidth)
                .append("typicalBandwidth", typicalBandwidth)
                .toString();
    }
}
