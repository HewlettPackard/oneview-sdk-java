/*
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
 */
package com.hp.ov.sdk.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UtilizationData implements Serializable {

    private static final long serialVersionUID = 4455142302924227356L;

    private Boolean isFresh;
    private List<MetricData> metricList = new ArrayList<>();
    private String newestSampleTime;
    private String oldestSampleTime;
    private String refreshTaskUri;
    private Integer resolution;
    private String sliceEndTime;
    private String sliceStartTime;
    private String uri;

    public Boolean getFresh() {
        return isFresh;
    }

    public void setFresh(Boolean fresh) {
        isFresh = fresh;
    }

    public List<MetricData> getMetricList() {
        return metricList;
    }

    public void setMetricList(List<MetricData> metricList) {
        this.metricList = metricList;
    }

    public String getNewestSampleTime() {
        return newestSampleTime;
    }

    public void setNewestSampleTime(String newestSampleTime) {
        this.newestSampleTime = newestSampleTime;
    }

    public String getOldestSampleTime() {
        return oldestSampleTime;
    }

    public void setOldestSampleTime(String oldestSampleTime) {
        this.oldestSampleTime = oldestSampleTime;
    }

    public String getRefreshTaskUri() {
        return refreshTaskUri;
    }

    public void setRefreshTaskUri(String refreshTaskUri) {
        this.refreshTaskUri = refreshTaskUri;
    }

    public Integer getResolution() {
        return resolution;
    }

    public void setResolution(Integer resolution) {
        this.resolution = resolution;
    }

    public String getSliceEndTime() {
        return sliceEndTime;
    }

    public void setSliceEndTime(String sliceEndTime) {
        this.sliceEndTime = sliceEndTime;
    }

    public String getSliceStartTime() {
        return sliceStartTime;
    }

    public void setSliceStartTime(String sliceStartTime) {
        this.sliceStartTime = sliceStartTime;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
