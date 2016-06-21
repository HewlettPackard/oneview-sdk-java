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

package com.hp.ov.sdk.dto.facilities.datacenter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VisualContent {

    private Integer depth;
    private String healthStatus;
    private Integer height;
    private Boolean inferred;
    private Double metric;
    private String metricTimeStamp;
    private String name;
    private String resourceUri;
    private Double rotation;
    private String uri;
    private Integer width;
    private Double x;
    private Double y;

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getInferred() {
        return inferred;
    }

    public void setInferred(Boolean inferred) {
        this.inferred = inferred;
    }

    public Double getMetric() {
        return metric;
    }

    public void setMetric(Double metric) {
        this.metric = metric;
    }

    public String getMetricTimeStamp() {
        return metricTimeStamp;
    }

    public void setMetricTimeStamp(String metricTimeStamp) {
        this.metricTimeStamp = metricTimeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public void setResourceUri(String resourceUri) {
        this.resourceUri = resourceUri;
    }

    public Double getRotation() {
        return rotation;
    }

    public void setRotation(Double rotation) {
        this.rotation = rotation;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof VisualContent) {
            VisualContent that = (VisualContent) obj;

            return new EqualsBuilder()
                    .append(depth, that.depth)
                    .append(healthStatus, that.healthStatus)
                    .append(height, that.height)
                    .append(inferred, that.inferred)
                    .append(metric, that.metric)
                    .append(metricTimeStamp, that.metricTimeStamp)
                    .append(name, that.name)
                    .append(resourceUri, that.resourceUri)
                    .append(rotation, that.rotation)
                    .append(uri, that.uri)
                    .append(width, that.width)
                    .append(x, that.x)
                    .append(y, that.y)
                    .isEquals();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(depth)
                .append(healthStatus)
                .append(height)
                .append(inferred)
                .append(metric)
                .append(metricTimeStamp)
                .append(name)
                .append(resourceUri)
                .append(rotation)
                .append(uri)
                .append(width)
                .append(x)
                .append(y)
                .toHashCode();
    }
}
