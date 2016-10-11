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
package com.hp.ov.sdk.messaging.core;

import java.util.ArrayList;
import java.util.List;

import com.hp.ov.sdk.dto.BaseModelResource;
import com.hp.ov.sdk.messaging.msmb.services.ResourceDataList;

public class MsmbAlertsResource extends BaseModelResource {

    private static final long serialVersionUID = 1L;

    private String numberOfSamples;
    private String resourceType;
    private String startTime;
    private long sampleIntervalInSeconds;
    private List<ResourceDataList> resourceDataList = new ArrayList<ResourceDataList>();

    public String getNumberOfSamples() {
        return numberOfSamples;
    }

    public void setNumberOfSamples(final String numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(final String resourceType) {
        this.resourceType = resourceType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    public long getSampleIntervalInSeconds() {
        return sampleIntervalInSeconds;
    }

    public void setSampleIntervalInSeconds(final long sampleIntervalInSeconds) {
        this.sampleIntervalInSeconds = sampleIntervalInSeconds;
    }

    public List<ResourceDataList> getResourceDataList() {
        return resourceDataList;
    }

    public void setResourceDataList(final List<ResourceDataList> resourceDataList) {
        this.resourceDataList = resourceDataList;
    }

}
