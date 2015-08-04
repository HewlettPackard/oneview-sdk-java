/*******************************************************************************
 * // (C) Copyright 2015 Hewlett Packard Enterprise Development LP
 *******************************************************************************/
package com.hp.ov.sdk.dto;

import java.util.ArrayList;
import java.util.List;

public class ResourceDataList {

    private String resourceId;
    private List<MetricSampleList> metricSampleList = new ArrayList<MetricSampleList>();

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(final String resourceId) {
        this.resourceId = resourceId;
    }

    public List<MetricSampleList> getMetricSampleList() {
        return metricSampleList;
    }

    public void setMetricSampleList(final List<MetricSampleList> metricSampleList) {
        this.metricSampleList = metricSampleList;
    }

}
