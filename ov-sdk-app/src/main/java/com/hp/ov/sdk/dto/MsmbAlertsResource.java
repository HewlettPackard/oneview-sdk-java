/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class MsmbAlertsResource extends BaseModelResource
{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private String numberOfSamples;
    private String resourceTye;
    private String startTime;
    private long sampleIntervalInSeconds;
    private List<ResourceDataList> resourceDataList = new ArrayList<ResourceDataList>();

    public String getNumberOfSamples()
    {
        return numberOfSamples;
    }

    public void setNumberOfSamples(String numberOfSamples)
    {
        this.numberOfSamples = numberOfSamples;
    }

    public String getResourceTye()
    {
        return resourceTye;
    }

    public void setResourceTye(String resourceTye)
    {
        this.resourceTye = resourceTye;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    public long getSampleIntervalInSeconds()
    {
        return sampleIntervalInSeconds;
    }

    public void setSampleIntervalInSeconds(long sampleIntervalInSeconds)
    {
        this.sampleIntervalInSeconds = sampleIntervalInSeconds;
    }

    public List<ResourceDataList> getResourceDataList()
    {
        return resourceDataList;
    }

    public void setResourceDataList(List<ResourceDataList> resourceDataList)
    {
        this.resourceDataList = resourceDataList;
    }

}
