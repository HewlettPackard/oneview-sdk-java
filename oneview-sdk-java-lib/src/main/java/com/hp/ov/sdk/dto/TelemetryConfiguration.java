/*******************************************************************************
 * (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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

public class TelemetryConfiguration extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private Boolean enableTelemetry;
    private Integer sampleCount;
    private Integer sampleInterval;

    /**
     * @return the enableTelemetry
     */
    public Boolean getEnableTelemetry() {
        return enableTelemetry;
    }

    /**
     * @param enableTelemetry
     *            the enableTelemetry to set
     */
    public void setEnableTelemetry(final Boolean enableTelemetry) {
        this.enableTelemetry = enableTelemetry;
    }

    /**
     * @return the sampleCount
     */
    public Integer getSampleCount() {
        return sampleCount;
    }

    /**
     * @param sampleCount
     *            the sampleCount to set
     */
    public void setSampleCount(final Integer sampleCount) {
        this.sampleCount = sampleCount;
    }

    /**
     * @return the sampleInterval
     */
    public Integer getSampleInterval() {
        return sampleInterval;
    }

    /**
     * @param sampleInterval
     *            the sampleInterval to set
     */
    public void setSampleInterval(final Integer sampleInterval) {
        this.sampleInterval = sampleInterval;
    }

}
