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

package com.hp.ov.sdk.messaging.msmb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MetricData implements Serializable {

    private static final long serialVersionUID = 5347042280040398641L;

    private Date startTime;
    private Integer sampleIntervalInSeconds;
    private Integer numberOfSamples;
    private String resourceType;
    private List<ResourceData> resourceDataList = new ArrayList<>();
    private String uri;
    private String category;
    private Date created;
    private Date modified;
    private String eTag;
    private String type;

    /**
     * @return the startTime
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the sampleIntervalInSeconds
     */
    public Integer getSampleIntervalInSeconds() {
        return sampleIntervalInSeconds;
    }

    /**
     * @param sampleIntervalInSeconds the sampleIntervalInSeconds to set
     */
    public void setSampleIntervalInSeconds(Integer sampleIntervalInSeconds) {
        this.sampleIntervalInSeconds = sampleIntervalInSeconds;
    }

    /**
     * @return the numberOfSamples
     */
    public Integer getNumberOfSamples() {
        return numberOfSamples;
    }

    /**
     * @param numberOfSamples the numberOfSamples to set
     */
    public void setNumberOfSamples(Integer numberOfSamples) {
        this.numberOfSamples = numberOfSamples;
    }

    /**
     * @return the resourceType
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType the resourceType to set
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return the resourceDataList
     */
    public List<ResourceData> getResourceDataList() {
        return resourceDataList;
    }

    /**
     * @param resourceDataList the resourceDataList to set
     */
    public void setResourceDataList(List<ResourceData> resourceDataList) {
        this.resourceDataList = resourceDataList;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return the modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified the modified to set
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * @return the eTag
     */
    public String getETag() {
        return eTag;
    }

    /**
     * @param eTag the eTag to set
     */
    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
