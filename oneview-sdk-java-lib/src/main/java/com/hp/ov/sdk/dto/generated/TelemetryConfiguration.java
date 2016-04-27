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
package com.hp.ov.sdk.dto.generated;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.hp.ov.sdk.dto.BaseModelResource;

public class TelemetryConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;
    /**
     *
     * (Required)
     *
     */
    private Integer sampleInterval = 300;
    /**
     *
     * (Required)
     *
     */
    private Boolean enableTelemetry = true;
    /**
     *
     * (Required)
     *
     */
    private Integer sampleCount = 12;

    /**
     *
     * (Required)
     *
     * @return The sampleInterval
     */
    public Integer getSampleInterval() {
        return sampleInterval;
    }

    /**
     *
     * (Required)
     *
     * @param sampleInterval
     *            The sampleInterval
     */
    public void setSampleInterval(final Integer sampleInterval) {
        this.sampleInterval = sampleInterval;
    }

    /**
     *
     * (Required)
     *
     * @return The enableTelemetry
     */
    public Boolean getEnableTelemetry() {
        return enableTelemetry;
    }

    /**
     *
     * (Required)
     *
     * @param enableTelemetry
     *            The enableTelemetry
     */
    public void setEnableTelemetry(final Boolean enableTelemetry) {
        this.enableTelemetry = enableTelemetry;
    }

    /**
     *
     * (Required)
     *
     * @return The sampleCount
     */
    public Integer getSampleCount() {
        return sampleCount;
    }

    /**
     *
     * (Required)
     *
     * @param sampleCount
     *            The sampleCount
     */
    public void setSampleCount(final Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
