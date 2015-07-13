/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

public class TelemetryConfiguration extends BaseModelResource
{

    private static final long serialVersionUID = 1L;

    private Boolean enableTelemetry;
    private Integer sampleCount;
    private Integer sampleInterval;

    /**
     * @return the enableTelemetry
     */
    public Boolean getEnableTelemetry()
    {
        return enableTelemetry;
    }

    /**
     * @param enableTelemetry the enableTelemetry to set
     */
    public void setEnableTelemetry(Boolean enableTelemetry)
    {
        this.enableTelemetry = enableTelemetry;
    }

    /**
     * @return the sampleCount
     */
    public Integer getSampleCount()
    {
        return sampleCount;
    }

    /**
     * @param sampleCount the sampleCount to set
     */
    public void setSampleCount(Integer sampleCount)
    {
        this.sampleCount = sampleCount;
    }

    /**
     * @return the sampleInterval
     */
    public Integer getSampleInterval()
    {
        return sampleInterval;
    }

    /**
     * @param sampleInterval the sampleInterval to set
     */
    public void setSampleInterval(Integer sampleInterval)
    {
        this.sampleInterval = sampleInterval;
    }

}
