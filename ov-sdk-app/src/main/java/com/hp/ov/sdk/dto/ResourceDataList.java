/*******************************************************************************
 * // (C) Copyright 2014-2015 Hewlett-Packard Development Company, L.P.
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class ResourceDataList
{

    private String resourceId;
    private List<MetricSampleList> metricSampleList = new ArrayList<MetricSampleList>();

    public String getResourceId()
    {
        return resourceId;
    }

    public void setResourceId(String resourceId)
    {
        this.resourceId = resourceId;
    }

    public List<MetricSampleList> getMetricSampleList()
    {
        return metricSampleList;
    }

    public void setMetricSampleList(List<MetricSampleList> metricSampleList)
    {
        this.metricSampleList = metricSampleList;
    }

}
