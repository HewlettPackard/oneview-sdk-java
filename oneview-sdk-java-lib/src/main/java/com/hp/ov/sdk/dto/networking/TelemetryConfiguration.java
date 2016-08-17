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
package com.hp.ov.sdk.dto.networking;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class TelemetryConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Integer sampleInterval = 300;
    private Boolean enableTelemetry = true;
    private Integer sampleCount = 12;

    /**
     * @return the sampleInterval
     */
    public Integer getSampleInterval() {
        return sampleInterval;
    }

    /**
     * @param sampleInterval the sampleInterval to set
     */
    public void setSampleInterval(Integer sampleInterval) {
        this.sampleInterval = sampleInterval;
    }

    /**
     * @return the enableTelemetry
     */
    public Boolean getEnableTelemetry() {
        return enableTelemetry;
    }

    /**
     * @param enableTelemetry the enableTelemetry to set
     */
    public void setEnableTelemetry(Boolean enableTelemetry) {
        this.enableTelemetry = enableTelemetry;
    }

    /**
     * @return the sampleCount
     */
    public Integer getSampleCount() {
        return sampleCount;
    }

    /**
     * @param sampleCount the sampleCount to set
     */
    public void setSampleCount(Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(sampleInterval)
                .append(enableTelemetry)
                .append(sampleCount)
                .appendSuper(super.hashCode())
                .toHashCode();
    }

    @Override
    public boolean canEqual(Object obj) {
        return (obj instanceof TelemetryConfiguration);
    }

    @Override
    public boolean equals(final Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TelemetryConfiguration) == false) {
            return false;
        }
        final TelemetryConfiguration rhs = ((TelemetryConfiguration) other);
        return new EqualsBuilder()
                .append(sampleInterval, rhs.sampleInterval)
                .append(enableTelemetry, rhs.enableTelemetry)
                .append(sampleCount, rhs.sampleCount)
                .appendSuper(super.equals(other))
                .isEquals();
    }

}
