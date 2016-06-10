/*
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
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

public class MetricData implements Serializable {

    private static final long serialVersionUID = -5444319954535665091L;

    private Integer metricAllocation;
    private Integer metricCapacity;
    private String metricName;
    private List<Object> metricSamples = new ArrayList<>();

    /**
     * @return the metricAllocation
     */
    public Integer getMetricAllocation() {
        return metricAllocation;
    }

    /**
     * @param metricAllocation the metricAllocation to set
     */
    public void setMetricAllocation(Integer metricAllocation) {
        this.metricAllocation = metricAllocation;
    }

    /**
     * @return the metricCapacity
     */
    public Integer getMetricCapacity() {
        return metricCapacity;
    }

    /**
     * @param metricCapacity the metricCapacity to set
     */
    public void setMetricCapacity(Integer metricCapacity) {
        this.metricCapacity = metricCapacity;
    }

    /**
     * @return the metricName
     */
    public String getMetricName() {
        return metricName;
    }

    /**
     * @param metricName the metricName to set
     */
    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    /**
     * @return the metricSamples
     */
    public List<Object> getMetricSamples() {
        return metricSamples;
    }

    /**
     * @param metricSamples the metricSamples to set
     */
    public void setMetricSamples(List<Object> metricSamples) {
        this.metricSamples = metricSamples;
    }

}
