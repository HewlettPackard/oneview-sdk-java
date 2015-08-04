/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
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
