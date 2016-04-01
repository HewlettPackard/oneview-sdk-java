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

    private Integer metricCapacity;
    private String metricName;
    private List<Object> metricSamples = new ArrayList<>();

    public Integer getMetricCapacity() {
        return metricCapacity;
    }

    public void setMetricCapacity(Integer metricCapacity) {
        this.metricCapacity = metricCapacity;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public List<Object> getMetricSamples() {
        return metricSamples;
    }

    public void setMetricSamples(List<Object> metricSamples) {
        this.metricSamples = metricSamples;
    }
}
